package litebans.importer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import litebans.command.CommandRunnable;
import litebans.database.DatabaseManager;
import litebans.database.PunishmentStorage;
import litebans.lib.kotlin.io.AutoCloseableKt;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.platform.LiteBansPlatform;
import litebans.punishment.PunishmentEntry;
import litebans.punishment.PunishmentType;
import litebans.punishment.ServerScope;
import litebans.sync.QueueFeederTask;
import org.jetbrains.annotations.NotNull;

public final class UltrabansImporter extends Importer {
   public UltrabansImporter(@NotNull LiteBansPlatform var1) {
      super(var1, "Ultrabans", null, 0, 0L, 28, null);
   }

   @Override
   public void handleString(@NotNull String var1, @NotNull Connection var2, @NotNull PunishmentStorage var3, boolean var4) {
      try {
      AutoCloseable var5 = var2.prepareStatement("SELECT * FROM banlist WHERE type=0");
      Throwable var6 = null;

      try {
         PreparedStatement var7 = (PreparedStatement)var5;
         if (var7.execute()) {
            ResultSet var9 = var7.getResultSet();
            AutoCloseable var11 = var9;
            Throwable var12 = null;

            try {
               while (var9.next()) {
                  String var17 = var9.getString("name");
                  String var18 = var9.getString("reason");
                  String var19 = var9.getString("admin");
                  long var20 = var9.getLong("time") * 1000L;
                  long var22 = var9.getLong("temptime") * 1000L;
                  Importer var10000 = this;
                  Intrinsics.handleObject(var18);
                  Importer.handlePunishmentStorageDefault(var10000, var3, var17, var18, var20, var22, var19, null, null, null, null, null, 1984, null);
               }
            } catch (Throwable var62) {
               var12 = var62;
               throw var62;
            } finally {
               AutoCloseableKt.handleAutoCloseable(var11, var12);
            }
         }
      } catch (Throwable var64) {
         var6 = var64;
         throw var64;
      } finally {
         AutoCloseableKt.handleAutoCloseable(var5, var6);
      }

      if (var4) {
         CommandRunnable var67 = this;
         long var66 = DatabaseManager.currentTimeMillisDefault((DatabaseManager)var67.getPlatform().getModule(DatabaseManager.class), false, 1, null);
         AutoCloseable var68 = var2.prepareStatement("SELECT * FROM banlistip");
         Throwable var8 = null;

         try {
            PreparedStatement var69 = (PreparedStatement)var68;
            if (var69.execute()) {
               ResultSet var70 = var69.getResultSet();
               AutoCloseable var13 = var70;
               Throwable var14 = null;

               try {
                  while (var70.next()) {
                     String var71 = var70.getString("name");
                     String var72 = var70.getString("lastip");
                     long var21 = -1L;
                     String var23 = "#imported#";
                     String var24 = this.getString(var71);
                     PunishmentEntry var25 = new PunishmentEntry(
                        PunishmentType.BAN,
                        var24,
                        var72,
                        "#",
                        this.getString(var23),
                        var23,
                        ServerScope.serverScopeParser.getString(),
                        null,
                        var66,
                        var21,
                        (byte)0,
                        false,
                        true,
                        false,
                        0L,
                        27648,
                        null
                     );
                     this.handlePunishmentStorage(var3, var25, this.getAtomicLong(), this.getAtomicLong2());
                     if (var24 != null) {
                        LiteBansPlatform var10002 = this.getPlatform();
                        Intrinsics.handleObject(var71);
                        new QueueFeederTask(var10002, var71, var24, "#").run();
                     }
                  }
               } catch (Throwable var58) {
                  var14 = var58;
                  throw var58;
               } finally {
                  AutoCloseableKt.handleAutoCloseable(var13, var14);
               }
            }
         } catch (Throwable var60) {
            var8 = var60;
            throw var60;
         } finally {
            AutoCloseableKt.handleAutoCloseable(var68, var8);
         }
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }
}
