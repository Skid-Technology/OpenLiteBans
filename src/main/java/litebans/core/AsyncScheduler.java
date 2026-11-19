package litebans.core;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AsyncScheduler {
   private ScheduledExecutorService scheduledExecutorService = null;

   public void run() {
      try {
         if (this.scheduledExecutorService != null && !this.scheduledExecutorService.isShutdown()) {
            this.scheduledExecutorService.shutdown();
            if (!this.scheduledExecutorService.awaitTermination(2L, TimeUnit.SECONDS)) {
               this.scheduledExecutorService.shutdownNow();
            }
         }
      } catch (Throwable var2) {
         throw new RuntimeException(var2);
      }
   }

   public ScheduledExecutorService getScheduledExecutorService(LiteBansLifecycle var1) {
      ScheduledExecutorService var2 = this.scheduledExecutorService;
      if (var2 != null && !var2.isShutdown()) {
         return this.scheduledExecutorService;
      } else {
         ThreadFactoryBuilder var3 = var1.createThreadFactory();
         ScheduledExecutorService var4 = Executors.newScheduledThreadPool(1, var3.build());
         this.scheduledExecutorService = var4;
         return var4;
      }
   }
}
