package litebans.lib.hikari;

import java.util.concurrent.ScheduledExecutorService;

public class ProxyLeakTaskFactory {
   private ScheduledExecutorService scheduledExecutorService;
   private long longValue;

   public ProxyLeakTaskFactory(long var1, ScheduledExecutorService var3) {
      this.scheduledExecutorService = var3;
      this.longValue = var1;
   }

   public ProxyLeakTask getProxyLeakTask(PoolEntry var1) {
      return this.longValue == 0L ? ProxyLeakTask.INSTANCE : this.getProxyLeakTask2(var1);
   }

   public void setLongValue(long var1) {
      this.longValue = var1;
   }

   private final ProxyLeakTask getProxyLeakTask2(PoolEntry var1) {
      ProxyLeakTask var2 = new ProxyLeakTask(var1);
      var2.handleScheduledExecutorService(this.scheduledExecutorService, this.longValue);
      return var2;
   }
}
