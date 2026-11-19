package litebans.database;

import java.io.Closeable;
import java.sql.SQLException;
import litebans.core.PluginModule;
import litebans.lib.kotlin.io.CloseableKt;

public final class ServerResetTask implements Runnable {
   public final DatabaseManager databaseManager;

   public ServerResetTask(DatabaseManager var1) {
      this.databaseManager = var1;
   }

   @Override
   public final void run() {
      DatabaseManager var1 = this.databaseManager;
      DatabaseManager var2 = var1;

      try {
         PunishmentStorage var4 = var2.openStorage();
         Closeable var5 = var4;
         Throwable var6 = null;

         try {
            var1.cleanupServers(var4, true);
         } catch (Throwable var13) {
            var6 = var13;
            throw var13;
         } finally {
            CloseableKt.handleCloseable(var5, var6);
         }
      } catch (Exception var15) {
         if (!(var15 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var15); }
         if (!var1.isConnectionError(var15)) {
            throw var15;
         }
      }

      PluginModule var16 = this.databaseManager;
      String var3 = "Server information reset complete.";
      var16.platform.getLogger().severe(var3);
   }
}
