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
import org.jetbrains.annotations.NotNull;

public final class MaxBansImporter extends Importer {
   public MaxBansImporter(@NotNull LiteBansPlatform var1) {
      super(var1, "MaxBans", null, 0, 0L, 28, null);
   }

   @Override
   public void handleString(@NotNull String var1, @NotNull Connection var2, @NotNull PunishmentStorage var3, boolean var4) {
      this.handleConnection(var2, var3);
      this.handleBoolean(var4, var2, var3);
   }

   private final void handleConnection(Connection var1, PunishmentStorage var2) {
      try {
      int var3 = 0;

      while (true) {
         AutoCloseable var4 = var1.prepareStatement("SELECT * FROM bans ORDER BY time LIMIT " + this.getIntValue() + " OFFSET " + var3);
         Throwable var5 = null;

         try {
            PreparedStatement var6 = (PreparedStatement)var4;
            Intrinsics.handleObject(var6);
            if (this.getInt(var6, var2) == 0) {
               return;
            }
         } catch (Throwable var10) {
            var5 = var10;
            throw var10;
         } finally {
            AutoCloseableKt.handleAutoCloseable(var4, var5);
         }

         var3 += this.getIntValue();
         Thread.sleep(this.getLongValue());
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   private final void handleBoolean(boolean var1, Connection var2, PunishmentStorage var3) {
      try {
      if (var1) {
         AutoCloseable var4 = var2.prepareStatement("SELECT * FROM ipbans");
         Throwable var5 = null;

         try {
            PreparedStatement var6 = (PreparedStatement)var4;
            if (var6.execute()) {
               ResultSet var8 = var6.getResultSet();
               AutoCloseable var10 = var8;
               Throwable var11 = null;

               try {
                  while (var8.next()) {
                     Importer.handlePunishmentStorageDefault(
                        this,
                        var3,
                        null,
                        var8.getString("reason"),
                        var8.getLong("time"),
                        var8.getLong("expires"),
                        var8.getString("banner"),
                        null,
                        null,
                        var8.getString("ip"),
                        null,
                        null,
                        1728,
                        null
                     );
                  }
               } catch (Throwable var23) {
                  var11 = var23;
                  throw var23;
               } finally {
                  AutoCloseableKt.handleAutoCloseable(var10, var11);
               }
            }
         } catch (Throwable var25) {
            var5 = var25;
            throw var25;
         } finally {
            AutoCloseableKt.handleAutoCloseable(var4, var5);
         }
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   private final int getInt(PreparedStatement var1, PunishmentStorage var2) {
      try {
      int var3 = 0;
      if (var1.execute()) {
         ResultSet var4 = var1.getResultSet();
         AutoCloseable var6 = var4;
         Throwable var7 = null;

         try {
            while (var4.next()) {
               ResultSet var10 = var4;
               var3++;

               try {
                  Importer.handlePunishmentStorageDefault(
                     this,
                     var2,
                     var10.getString("name"),
                     var10.getString("reason"),
                     var10.getLong("time"),
                     var10.getLong("expires"),
                     var10.getString("banner"),
                     null,
                     null,
                     null,
                     null,
                     null,
                     1984,
                     null
                  );
               } catch (Exception var17) {
                  this.getLogger().warning("Import failed for ban on '" + var4.getString("name") + "'; name changed, perhaps?");
                  CommandRunnable var13 = this;
                  ((DatabaseManager)var13.getPlatform().getModule(DatabaseManager.class)).reportError(var17);
               }
            }
         } catch (Throwable var18) {
            var7 = var18;
            throw var18;
         } finally {
            AutoCloseableKt.handleAutoCloseable(var6, var7);
         }
      }

      return var3;
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }
}
