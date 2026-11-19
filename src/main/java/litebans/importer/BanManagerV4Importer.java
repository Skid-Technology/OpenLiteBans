package litebans.importer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import litebans.database.EntryReader;
import litebans.database.PunishmentStorage;
import litebans.lib.kotlin.io.AutoCloseableKt;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.text.StringsKt;
import litebans.platform.LiteBansPlatform;
import litebans.punishment.PunishmentEntry;
import litebans.punishment.PunishmentType;
import litebans.punishment.ServerScope;
import litebans.sync.QueueFeederTask;
import org.jetbrains.annotations.NotNull;

public final class BanManagerV4Importer extends Importer {
   public BanManagerV4Importer(@NotNull LiteBansPlatform var1) {
      super(var1, "BanManagerV4", "bm_", 0, 0L, 24, null);
   }

   @Override
   public boolean checkString(@NotNull String var1) {
      return super.checkString(var1) || StringsKt.checkString(var1, "banmanager4", true);
   }

   @Override
   public void handleString(@NotNull String var1, @NotNull Connection var2, @NotNull PunishmentStorage var3, boolean var4) {
      try {
      PreparedStatement var5 = var2.prepareStatement("SELECT * FROM " + this.getString2() + "bans");
      AutoCloseable var6 = var5;
      Throwable var7 = null;

      try {
         if (var5.execute()) {
            ResultSet var10 = var5.getResultSet();
            AutoCloseable var11 = var10;
            Throwable var12 = null;

            try {
               while (var10.next()) {
                  String var15 = var10.getString("banned");
                  String var16 = var10.getString("banned_by");
                  String var17 = var10.getString("ban_reason");
                  long var18 = var10.getLong("ban_time") * 1000L;
                  long var20 = var10.getLong("ban_expires_on") * 1000L;
                  String var22 = this.getString(var15);
                  PunishmentType var10002 = PunishmentType.BAN;
                  Intrinsics.handleObject(var17);
                  PunishmentEntry var23 = new PunishmentEntry(
                     var10002,
                     var22,
                     null,
                     var17,
                     this.getString(var16),
                     var16,
                     ServerScope.serverScopeParser.getString(),
                     null,
                     var18,
                     var20,
                     (byte)0,
                     false,
                     false,
                     false,
                     0L,
                     31744,
                     null
                  );
                  if (EntryReader.getPunishmentEntry(var3, var22, null, null, false, false, 30, null) == null) {
                     var3.insertEntry(var23);
                     this.getAtomicLong().incrementAndGet();
                  } else {
                     this.getLogger().warning("Ignoring duplicate ban for " + var15);
                  }

                  if (var22 != null) {
                     LiteBansPlatform var86 = this.getPlatform();
                     Intrinsics.handleObject(var15);
                     new QueueFeederTask(var86, var15, var22, "#").run();
                  }
               }
            } catch (Throwable var71) {
               var12 = var71;
               throw var71;
            } finally {
               AutoCloseableKt.handleAutoCloseable(var11, var12);
            }
         }

         if (var4) {
            this.getLogger().info("Importing IP-bans...");
            PreparedStatement var79 = var2.prepareStatement("SELECT * FROM " + this.getString2() + "ip_bans");
            AutoCloseable var80 = var79;
            Throwable var81 = null;

            try {
               if (var79.execute()) {
                  ResultSet var82 = var79.getResultSet();
                  AutoCloseable var83 = var82;
                  Throwable var84 = null;

                  try {
                     while (var82.next()) {
                        String var26 = var82.getString("banned");
                        String var27 = var82.getString("banned_by");
                        String var85 = var82.getString("ban_reason");
                        long var28 = var82.getLong("ban_time");
                        long var31 = var28 * 1000L;
                        long var34 = var82.getLong("ban_expires_on");
                        long var37 = var34 * 1000L;
                        PunishmentType var87 = PunishmentType.BAN;
                        Intrinsics.handleObject(var85);
                        PunishmentEntry var33 = new PunishmentEntry(
                           var87,
                           null,
                           var26,
                           var85,
                           this.getString(var27),
                           var27,
                           ServerScope.serverScopeParser.getString(),
                           null,
                           var31,
                           var37,
                           (byte)0,
                           false,
                           true,
                           false,
                           0L,
                           27648,
                           null
                        );
                        this.handlePunishmentStorage(var3, var33, this.getAtomicLong(), this.getAtomicLong2());
                     }
                  } catch (Throwable var73) {
                     var84 = var73;
                     throw var73;
                  } finally {
                     AutoCloseableKt.handleAutoCloseable(var83, var84);
                  }
               }
            } catch (Throwable var75) {
               var81 = var75;
               throw var75;
            } finally {
               AutoCloseableKt.handleAutoCloseable(var80, var81);
            }
         }
      } catch (Throwable var77) {
         var7 = var77;
         throw var77;
      } finally {
         AutoCloseableKt.handleAutoCloseable(var6, var7);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }
}
