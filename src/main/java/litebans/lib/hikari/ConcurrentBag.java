package litebans.lib.hikari;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;
import java.util.stream.Collectors;

public class ConcurrentBag implements AutoCloseable {
   private static final HikariLogger hikariLogger = HikariLogger.getHikariLogger(ConcurrentBag.class);
   private final CopyOnWriteArrayList copyOnWriteArrayList;
   private final boolean flag;
   private final ThreadLocal threadLocal;
   private final IBagStateListener iBagStateListener;
   private final AtomicInteger atomicInteger;
   private volatile boolean flag2;
   private final SynchronousQueue synchronousQueue;

   public ConcurrentBag(IBagStateListener var1) {
      this.iBagStateListener = var1;
      this.flag = this.check();
      this.synchronousQueue = new SynchronousQueue(true);
      this.atomicInteger = new AtomicInteger();
      this.copyOnWriteArrayList = new CopyOnWriteArrayList();
      if (this.flag) {
         this.threadLocal = ThreadLocal.withInitial(ConcurrentBag::getList3);
      } else {
         this.threadLocal = ThreadLocal.withInitial(ConcurrentBag::getList2);
      }
   }

   public IConcurrentBagEntry getIConcurrentBagEntry(long var1, TimeUnit var3) {
      try {
      List var4 = (List)this.threadLocal.get();

      for (int var5 = var4.size() - 1; var5 >= 0; var5--) {
         Object var6 = var4.remove(var5);
         IConcurrentBagEntry var7 = this.flag ? (IConcurrentBagEntry)((WeakReference)var6).get() : (IConcurrentBagEntry)var6;
         if (var7 != null && var7.checkInt(0, 1)) {
            return var7;
         }
      }

      int var14 = this.atomicInteger.incrementAndGet();

      IConcurrentBagEntry var8;
      try {
         Iterator var15 = this.copyOnWriteArrayList.iterator();

         IConcurrentBagEntry var18;
         do {
            if (!var15.hasNext()) {
               this.iBagStateListener.handleInt(var14);
               var1 = var3.toNanos(var1);

               do {
                  long var16 = ClockSource.getLong2();
                  var8 = (IConcurrentBagEntry)this.synchronousQueue.poll(var1, TimeUnit.NANOSECONDS);
                  if (var8 == null || var8.checkInt(0, 1)) {
                     return var8;
                  }

                  var1 -= ClockSource.getLong4(var16);
               } while (var1 > 10000L);

               return null;
            }

            var18 = (IConcurrentBagEntry)var15.next();
         } while (!var18.checkInt(0, 1));

         if (var14 > 1) {
            this.iBagStateListener.handleInt(var14 - 1);
         }

         var8 = var18;
      } finally {
         this.atomicInteger.decrementAndGet();
      }

      return var8;
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   public void handleIConcurrentBagEntry(IConcurrentBagEntry var1) {
      var1.handleInt(0);

      for (int var2 = 0; this.atomicInteger.get() > 0; var2++) {
         if (var1.getInt() != 0 || this.synchronousQueue.offer(var1)) {
            return;
         }

         if ((var2 & 0xFF) == 255) {
            LockSupport.parkNanos(TimeUnit.MICROSECONDS.toNanos(10L));
         } else {
            Thread.yield();
         }
      }

      List var3 = (List)this.threadLocal.get();
      if (var3.size() < 50) {
         var3.add(this.flag ? new WeakReference<>(var1) : var1);
      }
   }

   public void handleIConcurrentBagEntry2(IConcurrentBagEntry var1) {
      if (this.flag2) {
         hikariLogger.info("ConcurrentBag has been closed, ignoring add()");
         throw new IllegalStateException("ConcurrentBag has been closed, ignoring add()");
      } else {
         this.copyOnWriteArrayList.add(var1);

         while (this.atomicInteger.get() > 0 && var1.getInt() == 0 && !this.synchronousQueue.offer(var1)) {
            Thread.yield();
         }
      }
   }

   public boolean checkIConcurrentBagEntry(IConcurrentBagEntry var1) {
      if (!var1.checkInt(1, -1) && !var1.checkInt(-2, -1) && !this.flag2) {
         hikariLogger.handleString4("Attempt to remove an object from the bag that was not borrowed or reserved: %s", new Object[]{var1.toString()});
         return false;
      } else {
         boolean var2 = this.copyOnWriteArrayList.remove(var1);
         if (!var2 && !this.flag2) {
            hikariLogger.handleString4("Attempt to remove an object from the bag that does not exist: %s", new Object[]{var1.toString()});
         }

         ((List)this.threadLocal.get()).remove(var1);
         return var2;
      }
   }

   @Override
   public void close() {
      this.flag2 = true;
   }

   public List getList(int var1) {
      List var2 = (List) this.copyOnWriteArrayList.stream().filter(o -> checkInt(var1, (IConcurrentBagEntry) o)).collect(Collectors.toList());
      Collections.reverse(var2);
      return var2;
   }

   public List getList() {
      return (List)this.copyOnWriteArrayList.clone();
   }

   public boolean checkIConcurrentBagEntry2(IConcurrentBagEntry var1) {
      return var1.checkInt(0, -2);
   }

   public void handleIConcurrentBagEntry3(IConcurrentBagEntry var1) {
      if (var1.checkInt(-2, 0)) {
         while (this.atomicInteger.get() > 0 && !this.synchronousQueue.offer(var1)) {
            Thread.yield();
         }
      } else {
         hikariLogger.handleString4("Attempt to relinquish an object to the bag that was not reserved: {}", new Object[]{var1});
      }
   }

   public int getInt() {
      return this.atomicInteger.get();
   }

   public int getInt(int var1) {
      int var2 = 0;

      for (Object var4o : this.copyOnWriteArrayList) {
         IConcurrentBagEntry var4 = (IConcurrentBagEntry) var4o;
         if (var4.getInt() == var1) {
            var2++;
         }
      }

      return var2;
   }

   public int getInt2() {
      return this.copyOnWriteArrayList.size();
   }

   private final boolean check() {
      try {
         return System.getProperty("com.zaxxer.hikari.useWeakReferences") != null
            ? Boolean.getBoolean("com.zaxxer.hikari.useWeakReferences")
            : this.getClass().getClassLoader() != ClassLoader.getSystemClassLoader();
      } catch (SecurityException var1) {
         return true;
      }
   }

   private static final void handleIConcurrentBagEntry4(IConcurrentBagEntry var0) {
      hikariLogger.info(var0.toString());
   }

   private static final boolean checkInt(int var0, IConcurrentBagEntry var1) {
      return var1.getInt() == var0;
   }

   private static final List getList2() {
      return new FastList(IConcurrentBagEntry.class, 16);
   }

   private static final List getList3() {
      return new ArrayList(16);
   }
}
