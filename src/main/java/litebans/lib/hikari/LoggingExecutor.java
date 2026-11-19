package litebans.lib.hikari;

import java.util.concurrent.Executor;

public class LoggingExecutor implements Executor {
   private LoggingExecutor() {
   }

   @Override
   public void execute(Runnable var1) {
      try {
         var1.run();
      } catch (Exception var3) {
         HikariLogger.getHikariLogger(PoolBase.class).handleString("Failed to execute: %s", new Object[]{var1.toString(), var3});
      }
   }

   public LoggingExecutor(PoolBaseHelper var1) {
      this();
   }
}
