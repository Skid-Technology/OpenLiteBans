package litebans.importer;

import java.net.InetAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Locale;
import java.util.UUID;
import litebans.database.EntryReader;
import litebans.database.PunishmentStorage;
import litebans.lib.kotlin.collections.ArraysKt;
import litebans.lib.kotlin.io.AutoCloseableKt;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.text.StringsKt;
import litebans.platform.LiteBansPlatform;
import litebans.punishment.PunishmentEntry;
import litebans.punishment.PunishmentType;
import litebans.punishment.ServerScope;
import litebans.randomid.UuidBytes;
import litebans.sync.QueueFeederTask;
import org.jetbrains.annotations.NotNull;

public final class BanManagerV5Importer extends Importer {
   public BanManagerV5Importer(@NotNull LiteBansPlatform var1) {
      super(var1, "BanManagerV5", "bm_", 0, 0L, 24, null);
   }

   @Override
   public boolean checkString(@NotNull String var1) {
      if (!super.checkString(var1)) {
         String[] var2 = new String[]{"banmanager5", "banmanager7", "banmanagerv7"};
         if (!ArraysKt.checkObjectArray(var2, var1.toLowerCase(Locale.ENGLISH))) {
            return false;
         }
      }

      return true;
   }

   @Override
   public void handleString(@NotNull String var1, @NotNull Connection var2, @NotNull PunishmentStorage var3, boolean var4) {
      boolean var5 = StringsKt.checkCharSequence2(var1, '7', false, 2, null);
      this.handleString("bans", PunishmentType.BAN, var2, var3, var4, var5);
      this.handleString("mutes", PunishmentType.MUTE, var2, var3, var4, var5);
   }

   private final void handleString(String var1, PunishmentType var2, Connection var3, PunishmentStorage var4, boolean var5, boolean var6) {
      try {
      PreparedStatement var7 = var3.prepareStatement(
         "SELECT * FROM "
            + this.getString2()
            + "player_"
            + var1
            + " INNER JOIN "
            + this.getString2()
            + "players ON "
            + this.getString2()
            + "player_"
            + var1
            + ".player_id="
            + this.getString2()
            + "players.id"
      );
      AutoCloseable var8 = var7;
      Throwable var9 = null;

      try {
         if (var7.execute()) {
            ResultSet var12 = var7.getResultSet();
            AutoCloseable var13 = var12;
            Throwable var14 = null;

            try {
               while (var12.next()) {
                  String var17 = var12.getString("name");
                  UUID var18 = UuidBytes.getUUID(var12.getBytes("player_id"));
                  String var19 = var12.getString("reason");
                  UUID var20 = UuidBytes.getUUID(var12.getBytes("actor_id"));
                  long var21 = var12.getLong("created") * 1000L;
                  long var23 = var12.getLong("expires") * 1000L;
                  String var25 = var18.toString();
                  String var26 = this.getString(var20);
                  Intrinsics.handleObject(var19);
                  PunishmentEntry var27 = new PunishmentEntry(
                     var2,
                     var25,
                     null,
                     var19,
                     var20.toString(),
                     var26,
                     ServerScope.serverScopeParser.getString(),
                     null,
                     var21,
                     var23,
                     (byte)0,
                     false,
                     false,
                     false,
                     0L,
                     31744,
                     null
                  );
                  if (EntryReader.getPunishmentEntry(var4, var25, null, null, false, false, 30, null) == null) {
                     var4.insertEntry(var27);
                     if (var2 == PunishmentType.BAN) {
                        this.getAtomicLong().incrementAndGet();
                     }
                  } else {
                     this.getLogger().warning("Ignoring duplicate ban for " + var25);
                  }

                  LiteBansPlatform var10002 = this.getPlatform();
                  Intrinsics.handleObject(var17);
                  new QueueFeederTask(var10002, var17, var25, "#").run();
               }
            } catch (Throwable var79) {
               var14 = var79;
               throw var79;
            } finally {
               AutoCloseableKt.handleAutoCloseable(var13, var14);
            }
         }

         if (var5) {
            this.getLogger().info("Importing IP-bans...");
            PreparedStatement var87 = var3.prepareStatement("SELECT * FROM " + this.getString2() + "ip_bans");
            AutoCloseable var88 = var87;
            Throwable var89 = null;

            try {
               if (var87.execute()) {
                  ResultSet var90 = var87.getResultSet();
                  AutoCloseable var91 = var90;
                  Throwable var92 = null;

                  try {
                     while (var90.next()) {
                        String var10000;
                        if (var6) {
                           byte[] var30 = var90.getBytes("ip");
                           var10000 = InetAddress.getByAddress(var30).getHostAddress().toString();
                        } else {
                           long var32 = var90.getLong("ip");
                           var10000 = "" + (var32 >> 24 & 255L) + '.' + (var32 >> 16 & 255L) + '.' + (var32 >> 8 & 255L) + '.' + (var32 & 255L);
                        }

                        String var34 = var10000;
                        String var29 = var90.getString("reason");
                        UUID var93 = UuidBytes.getUUID(var90.getBytes("actor_id"));
                        long var36 = var90.getLong("created");
                        long var39 = var36 * 1000L;
                        long var42 = var90.getLong("expires");
                        long var45 = var42 * 1000L;
                        String var41 = this.getString(var93);
                        Intrinsics.handleObject(var29);
                        PunishmentEntry var38 = new PunishmentEntry(
                           var2,
                           null,
                           var34,
                           var29,
                           var93.toString(),
                           var41,
                           ServerScope.serverScopeParser.getString(),
                           null,
                           var39,
                           var45,
                           (byte)0,
                           false,
                           true,
                           false,
                           0L,
                           27648,
                           null
                        );
                        this.handlePunishmentStorage(var4, var38, this.getAtomicLong(), this.getAtomicLong2());
                     }
                  } catch (Throwable var81) {
                     var92 = var81;
                     throw var81;
                  } finally {
                     AutoCloseableKt.handleAutoCloseable(var91, var92);
                  }
               }
            } catch (Throwable var83) {
               var89 = var83;
               throw var83;
            } finally {
               AutoCloseableKt.handleAutoCloseable(var88, var89);
            }
         }
      } catch (Throwable var85) {
         var9 = var85;
         throw var85;
      } finally {
         AutoCloseableKt.handleAutoCloseable(var8, var9);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }
}
