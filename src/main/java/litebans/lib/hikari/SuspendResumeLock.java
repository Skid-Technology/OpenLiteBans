package litebans.lib.hikari;

import java.sql.SQLTransientException;
import java.util.concurrent.Semaphore;

public class SuspendResumeLock {
   public static final SuspendResumeLock INSTANCE = new FauxSuspendResumeLock(false);
   private static final int intValue = 10000;
   private final Semaphore semaphore;

   public SuspendResumeLock() {
      this(true);
   }

   private SuspendResumeLock(boolean var1) {
      this.semaphore = var1 ? new Semaphore(10000, true) : null;
   }

   public void run() {
      try {
      if (!this.semaphore.tryAcquire()) {
         if (Boolean.getBoolean("com.zaxxer.hikari.throwIfSuspended")) {
            throw new SQLTransientException("The pool is currently suspended and configured to throw exceptions upon acquisition");
         } else {
            this.semaphore.acquireUninterruptibly();
         }
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   public void run2() {
      this.semaphore.release();
   }

   public void run3() {
      this.semaphore.acquireUninterruptibly(10000);
   }

   public void run4() {
      this.semaphore.release(10000);
   }

   public SuspendResumeLock(boolean var1, FauxSuspendResumeLock var2) {
      this(var1);
   }
}
