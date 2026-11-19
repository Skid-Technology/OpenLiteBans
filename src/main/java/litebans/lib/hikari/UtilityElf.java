package litebans.lib.hikari;

import java.lang.reflect.Constructor;
import java.util.Locale;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class UtilityElf {
   private UtilityElf() {
   }

   public static String getString(String var0) {
      return var0 == null ? null : (var0.trim().isEmpty() ? null : var0.trim());
   }

   public static void handleLong(long var0) {
      try {
         Thread.sleep(var0);
      } catch (InterruptedException var2) {
         Thread.currentThread().interrupt();
      }
   }

   public static Object getObject(String var0, Class var1, Object[] var2) {
      if (var0 == null) {
         return null;
      } else {
         try {
            Class var3 = UtilityElf.class.getClassLoader().loadClass(var0);
            if (var2.length == 0) {
               return var1.cast(var3.getDeclaredConstructor().newInstance());
            } else {
               Class[] var4 = new Class[var2.length];

               for (int var5 = 0; var5 < var2.length; var5++) {
                  var4[var5] = var2[var5].getClass();
               }

               Constructor var7 = var3.getConstructor(var4);
               return var1.cast(var7.newInstance(var2));
            }
         } catch (Exception var6) {
            throw new RuntimeException(var6);
         }
      }
   }

   public static ThreadPoolExecutor getThreadPoolExecutor(int var0, String var1, ThreadFactory var2, RejectedExecutionHandler var3) {
      if (var2 == null) {
         var2 = new DefaultThreadFactory(var1, true);
      }

      LinkedBlockingQueue var4 = new LinkedBlockingQueue(var0);
      ThreadPoolExecutor var5 = new ThreadPoolExecutor(1, 1, 5L, TimeUnit.SECONDS, var4, (ThreadFactory)var2, var3);
      var5.allowCoreThreadTimeOut(true);
      return var5;
   }

   public static ThreadPoolExecutor getThreadPoolExecutor(BlockingQueue var0, String var1, ThreadFactory var2, RejectedExecutionHandler var3) {
      if (var2 == null) {
         var2 = new DefaultThreadFactory(var1, true);
      }

      ThreadPoolExecutor var4 = new ThreadPoolExecutor(1, 1, 5L, TimeUnit.SECONDS, var0, (ThreadFactory)var2, var3);
      var4.allowCoreThreadTimeOut(true);
      return var4;
   }

   public static int getInt(String var0) {
      if (var0 != null) {
         try {
            String var1 = var0.toUpperCase(Locale.ENGLISH);
            return IsolationLevel.getIsolationLevel(var1).getIntValue();
         } catch (IllegalArgumentException var8) {
            try {
               int var2 = Integer.parseInt(var0);

               for (IsolationLevel var6 : IsolationLevel.values()) {
                  if (var6.getIntValue() == var2) {
                     return var6.getIntValue();
                  }
               }

               throw new IllegalArgumentException("Invalid transaction isolation value: " + var0);
            } catch (NumberFormatException var7) {
               throw new IllegalArgumentException("Invalid transaction isolation value: " + var0, var7);
            }
         }
      } else {
         return -1;
      }
   }
}
