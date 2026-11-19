package litebans.importer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import litebans.command.CommandRunnable;
import litebans.core.LiteBansCore;
import litebans.core.NotificationManager;
import litebans.database.PunishmentStorage;
import litebans.lib.kotlin.io.AutoCloseableKt;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.platform.LiteBansPlatform;
import org.jetbrains.annotations.NotNull;

public final class BanHammerImporter extends Importer {
   public BanHammerImporter(@NotNull LiteBansPlatform var1) {
      super(var1, "BanHammer", "banhammer_", 0, 0L, 24, null);
   }

   @Override
   public void handleString(@NotNull String var1, @NotNull Connection var2, @NotNull PunishmentStorage var3, boolean var4) {
      try {
      PreparedStatement var5 = var2.prepareStatement("SELECT * FROM " + this.getString2() + "bans WHERE state=0");
      AutoCloseable var6 = var5;
      Throwable var7 = null;

      try {
         if (var5.execute()) {
            ResultSet var10 = var5.getResultSet();
            AutoCloseable var11 = var10;
            Throwable var12 = null;

            try {
               while (var10.next()) {
                  PreparedStatement var15 = var2.prepareStatement("SELECT * FROM " + this.getString2() + "players WHERE id=?");
                  AutoCloseable var16 = var15;
                  Throwable var17 = null;

                  try {
                     var15.setInt(1, var10.getInt("player_id"));
                     var15.execute();
                     ResultSet var20 = var15.getResultSet();
                     if (var20 != null && var20.next()) {
                        String var21 = var20.getString("name");
                        String var22 = var10.getString("reason");
                        CommandRunnable var23 = this;
                        NotificationManager var10000 = (NotificationManager)var23.getPlatform().getModule(NotificationManager.class);
                        Intrinsics.handleObject(var10);
                        var23 = this;
                        Date var25 = var10000.readDate(
                           var10, "created_at", false, ((LiteBansCore)var23.getPlatform().getModule(LiteBansCore.class)).getConfig().getSqlDriver()
                        );
                        long var26 = var25.getTime();
                        CommandRunnable var28 = this;
                        var10000 = (NotificationManager)var28.getPlatform().getModule(NotificationManager.class);
                        var28 = this;
                        Date var30 = var10000.readDate(
                           var10, "expires_at", false, ((LiteBansCore)var28.getPlatform().getModule(LiteBansCore.class)).getConfig().getSqlDriver()
                        );
                        long var31 = var30.getTime();
                        Importer var60 = this;
                        Intrinsics.handleObject(var22);
                        Importer.handlePunishmentStorageDefault(var60, var3, var21, var22, var26, var31, null, null, null, null, null, null, 1984, null);
                     }
                  } catch (Throwable var51) {
                     var17 = var51;
                     throw var51;
                  } finally {
                     AutoCloseableKt.handleAutoCloseable(var16, var17);
                  }
               }
            } catch (Throwable var53) {
               var12 = var53;
               throw var53;
            } finally {
               AutoCloseableKt.handleAutoCloseable(var11, var12);
            }
         }
      } catch (Throwable var55) {
         var7 = var55;
         throw var55;
      } finally {
         AutoCloseableKt.handleAutoCloseable(var6, var7);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }
}
