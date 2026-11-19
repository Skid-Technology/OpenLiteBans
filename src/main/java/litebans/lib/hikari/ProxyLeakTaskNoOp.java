package litebans.lib.hikari;

import java.util.concurrent.ScheduledExecutorService;

public final class ProxyLeakTaskNoOp extends ProxyLeakTask {
   public ProxyLeakTaskNoOp() {
      super();
   }

   @Override
   public void handleScheduledExecutorService(ScheduledExecutorService var1, long var2) {
   }

   @Override
   public void run() {
   }

   @Override
   public void run2() {
   }
}
