package litebans.lib.hikari;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import litebans.database.DatabaseManager;

public final class CustomDiscardPolicy implements RejectedExecutionHandler {
   public final DatabaseManager databaseManager;

   public CustomDiscardPolicy(DatabaseManager var1) {
      this.databaseManager = var1;
   }

   @Override
   public final void rejectedExecution(Runnable var1, ThreadPoolExecutor var2) {
      if (!var2.isShutdown()) {
         try {
            if (this.databaseManager.platform.isPrimaryThread()) {
               this.databaseManager.platform.runAsync(var1);
            } else {
               var1.run();
            }
         } catch (Exception var3) {
            if (!(var3 instanceof InterruptedException)) { throw litebans.util.Unreachable.rethrow(var3); }
         }
      }
   }
}
