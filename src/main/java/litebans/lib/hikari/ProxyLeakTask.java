package litebans.lib.hikari;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ProxyLeakTask implements Runnable {
   private static final HikariLogger hikariLogger = HikariLogger.getHikariLogger(ProxyLeakTask.class);
   public static final ProxyLeakTask INSTANCE = new ProxyLeakTaskNoOp();
   private ScheduledFuture scheduledFuture;
   private String string;
   private Exception exception;
   private String string2;
   private boolean flag;

   public ProxyLeakTask(PoolEntry var1) {
      this.exception = new Exception("Apparent connection leak detected");
      this.string2 = Thread.currentThread().getName();
      this.string = var1.connection.toString();
   }

   ProxyLeakTask() {
   }

   public void handleScheduledExecutorService(ScheduledExecutorService var1, long var2) {
      this.scheduledFuture = var1.schedule(this, var2, TimeUnit.MILLISECONDS);
   }

   @Override
   public void run() {
      this.flag = true;
      StackTraceElement[] var1 = this.exception.getStackTrace();
      StackTraceElement[] var2 = new StackTraceElement[var1.length - 5];
      System.arraycopy(var1, 5, var2, 0, var2.length);
      this.exception.setStackTrace(var2);
      hikariLogger.handleString4(
         "Connection leak detection triggered for %s on thread %s, stack trace follows", new Object[]{this.string, this.string2, this.exception}
      );
   }

   public void run2() {
      this.scheduledFuture.cancel(false);
      if (this.flag) {
         hikariLogger.handleString3(
            "Previously reported leaked connection %s on thread %s was returned to the pool (unleaked)", new Object[]{this.string, this.string2}
         );
      }
   }

   public ProxyLeakTask(ProxyLeakTaskNoOp var1) {
      this();
   }
}
