package litebans.lib.hikari;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLTransientConnectionException;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;
import java.util.concurrent.ThreadPoolExecutor.DiscardOldestPolicy;
import java.util.concurrent.ThreadPoolExecutor.DiscardPolicy;
import javax.sql.DataSource;

public final class HikariPool extends PoolBase implements HikariPoolMXBean, IBagStateListener {
   public static final int intValue9 = 0;
   public static final int intValue10 = 1;
   public static final int intValue11 = 2;
   private static final String string4 = "(connection was evicted)";
   private static final String string5 = "(connection is dead)";
   private final HikariLogger hikariLogger2 = HikariLogger.getHikariLogger(HikariPool.class);
   private final long longValue3 = Long.getLong("com.zaxxer.hikari.aliveBypassWindowMs", TimeUnit.MILLISECONDS.toMillis(500L));
   private final long longValue4 = Long.getLong("com.zaxxer.hikari.housekeeping.periodMs", TimeUnit.SECONDS.toMillis(30L));
   private final PoolEntryCreator poolEntryCreator = new PoolEntryCreator(this, null);
   private final PoolEntryCreator poolEntryCreator2 = new PoolEntryCreator(this, "After adding ");
   private final Collection collection;
   private final ThreadPoolExecutor threadPoolExecutor;
   private final ThreadPoolExecutor threadPoolExecutor2;
   private final ConcurrentBag concurrentBag = new ConcurrentBag(this);
   private final ProxyLeakTaskFactory proxyLeakTaskFactory;
   private final SuspendResumeLock suspendResumeLock;
   private final ScheduledExecutorService scheduledExecutorService;
   public volatile int intValue12;
   private ScheduledFuture scheduledFuture;

   public HikariPool(HikariConfig var1) {
      super(var1);
      this.suspendResumeLock = var1.isFlag6() ? new SuspendResumeLock() : SuspendResumeLock.INSTANCE;
      this.scheduledExecutorService = this.getScheduledExecutorService();
      this.run6();
      this.handleHikariPool(this, true);
      ThreadFactory var2 = var1.getThreadFactory();
      int var3 = var1.getIntValue2();
      LinkedBlockingQueue var4 = new LinkedBlockingQueue(var3);
      this.collection = Collections.unmodifiableCollection(var4);
      this.threadPoolExecutor = UtilityElf.getThreadPoolExecutor(var4, this.string + " innocent connection adder (Async)", var2, new DiscardOldestPolicy());
      this.threadPoolExecutor2 = UtilityElf.getThreadPoolExecutor(var3, this.string + " innocent connection closer (Async)", var2, new CallerRunsPolicy());
      this.proxyLeakTaskFactory = new ProxyLeakTaskFactory(var1.getLongValue10(), this.scheduledExecutorService);
      this.scheduledFuture = this.scheduledExecutorService.scheduleWithFixedDelay(new HouseKeeper(this, null), 100L, this.longValue4, TimeUnit.MILLISECONDS);
      if (Boolean.getBoolean("com.zaxxer.hikari.blockUntilFilled") && var1.getLongValue12() > 1L) {
         this.threadPoolExecutor.setMaximumPoolSize(Math.min(16, Runtime.getRuntime().availableProcessors()));
         this.threadPoolExecutor.setCorePoolSize(Math.min(16, Runtime.getRuntime().availableProcessors()));
         long var5 = ClockSource.getLong2();

         while (ClockSource.getLong3(var5) < var1.getLongValue12() && this.getInt3() < var1.getIntValue3()) {
            UtilityElf.handleLong(TimeUnit.MILLISECONDS.toMillis(100L));
         }

         this.threadPoolExecutor.setCorePoolSize(1);
         this.threadPoolExecutor.setMaximumPoolSize(1);
      }
   }

   public Connection getConnection() throws SQLException {
      return this.getConnection(this.longValue);
   }

   public Connection getConnection(long var1) throws SQLException {
      this.suspendResumeLock.run();
      long var3 = ClockSource.getLong2();

      try {
         long var5 = var1;

         do {
            PoolEntry var7 = (PoolEntry)this.concurrentBag.getIConcurrentBagEntry(var5, TimeUnit.MILLISECONDS);
            if (var7 == null) {
               break;
            }

            long var8 = ClockSource.getLong2();
            if (!var7.isFlag() && (ClockSource.getLong4(var7.longValue, var8) <= this.longValue3 || this.checkConnection(var7.connection))) {
               return var7.getConnection(this.proxyLeakTaskFactory.getProxyLeakTask(var7), var8);
            }

            this.handlePoolEntry(var7, var7.isFlag() ? "(connection was evicted)" : "(connection is dead)");
            var5 = var1 - ClockSource.getLong3(var3);
         } while (var5 > 0L);

         throw this.getSQLException(var3);
      } catch (Exception var14) {
         if (!(var14 instanceof InterruptedException)) {
            throw litebans.util.Unreachable.rethrow(var14);
         }

         Thread.currentThread().interrupt();
         throw new SQLException(this.string + " - Interrupted during connection acquisition", var14);
      } finally {
         this.suspendResumeLock.run2();
      }
   }

   public synchronized void run4() throws InterruptedException {
      try {
         this.intValue12 = 2;
         if (this.threadPoolExecutor != null) {
            this.handleStringArray(new String[]{"Before shutdown "});
            if (this.scheduledFuture != null) {
               this.scheduledFuture.cancel(false);
               this.scheduledFuture = null;
            }

            this.run();
            this.threadPoolExecutor.shutdown();
            this.threadPoolExecutor.awaitTermination(this.getLong(), TimeUnit.SECONDS);
            this.run7();
            this.concurrentBag.close();
            ThreadPoolExecutor var1 = UtilityElf.getThreadPoolExecutor(
               this.hikariConfig.getIntValue2(), this.string + " connection assassinator", this.hikariConfig.getThreadFactory(), new CallerRunsPolicy()
            );

            try {
               long var2 = ClockSource.getLong2();

               do {
                  this.handleExecutorService(var1);
                  this.run();
               } while (this.getInt3() > 0 && ClockSource.getLong3(var2) < TimeUnit.SECONDS.toMillis(10L));
            } finally {
               var1.shutdown();
               var1.awaitTermination(10L, TimeUnit.SECONDS);
            }

            this.run8();
            this.threadPoolExecutor2.shutdown();
            this.threadPoolExecutor2.awaitTermination(10L, TimeUnit.SECONDS);
            return;
         }
      } finally {
         this.handleStringArray(new String[]{"After shutdown "});
         this.handleHikariPool(this, false);
      }
   }

   @Override
   public void handleInt(int var1) {
      boolean var2 = var1 - this.collection.size() >= 0;
      if (var2) {
         this.threadPoolExecutor.submit(this.poolEntryCreator);
      } else {
         this.hikariLogger2.handleString("%s - Add connection elided, waiting %d, queue %d", new Object[]{this.string, var1, this.collection.size()});
      }
   }

   @Override
   public int getInt2() {
      return this.concurrentBag.getInt(1);
   }

   @Override
   public int getInt() {
      return this.concurrentBag.getInt(0);
   }

   @Override
   public int getInt3() {
      return this.concurrentBag.getInt2();
   }

   @Override
   public int getInt4() {
      return this.concurrentBag.getInt();
   }

   @Override
   public void run() {
      for (Object var1o : this.concurrentBag.getList()) {
         this.handlePoolEntry2((PoolEntry) var1o);
      }
   }

   @Override
   public synchronized void run2() {
      if (this.suspendResumeLock == SuspendResumeLock.INSTANCE) {
         throw new IllegalStateException(this.string + " - is not suspendable");
      } else {
         if (this.intValue12 != 1) {
            this.suspendResumeLock.run3();
            this.intValue12 = 1;
         }
      }
   }

   @Override
   public synchronized void run3() {
      if (this.intValue12 == 1) {
         this.intValue12 = 0;
         this.run5();
         this.suspendResumeLock.run4();
      }
   }

   public void handleStringArray(String[] var1) {
      if (this.hikariLogger2.check()) {
         this.hikariLogger2
            .handleString(
               "{} - {}stats (total={}, active={}, idle={}, waiting={})",
               new Object[]{this.string, var1.length > 0 ? var1[0] : "", this.getInt3(), this.getInt2(), this.getInt(), this.getInt4()}
            );
      }
   }

   @Override
   public void handlePoolEntry(PoolEntry var1) {
      this.concurrentBag.handleIConcurrentBagEntry(var1);
   }

   public void handlePoolEntry(PoolEntry var1, String var2) {
      if (this.concurrentBag.checkIConcurrentBagEntry(var1)) {
         Connection var3 = var1.getConnection();
         this.threadPoolExecutor2.execute(() -> this.handleConnection(var3, "(connection evicted from pool)"));
      }
   }

   private final PoolEntry getPoolEntry() {
      try {
         PoolEntry var1 = this.getPoolEntry2();
         long var2 = this.hikariConfig.getLongValue11();
         if (var2 > 0L) {
            long var4 = var2 > 10000L ? ThreadLocalRandom.current().nextLong(var2 / 40L) : 0L;
            long var6 = var2 - var4;
            var1.setScheduledFuture(this.scheduledExecutorService.schedule(new MaxLifetimeTask(this, var1), var6, TimeUnit.MILLISECONDS));
         }

         long var12 = this.hikariConfig.getLongValue13();
         if (var12 > 0L) {
            long var13 = ThreadLocalRandom.current().nextLong(var12 / 10L);
            long var8 = var12 - var13;
            var1.setScheduledFuture2(this.scheduledExecutorService.scheduleWithFixedDelay(new KeepaliveTask(this, var1), var8, var8, TimeUnit.MILLISECONDS));
         }

         return var1;
      } catch (Exception var10) {
         if (var10 instanceof ConnectionSetupException) {
            if (this.intValue12 == 0) {
               this.hikariLogger2.handleString2("%s - Error thrown while acquiring connection from data source", new Object[]{this.string, var10.getCause()});
               this.atomicReference.set(var10);
            }
         } else if (this.intValue12 == 0) {
            this.hikariLogger2.handleString("%s - Cannot acquire connection from data source", new Object[]{this.string, var10});
         }
      }

      return null;
   }

   private final synchronized void run5() {
      int var1 = Math.min(this.hikariConfig.getIntValue2() - this.getInt3(), this.hikariConfig.getIntValue3() - this.getInt()) - this.collection.size();
      if (var1 <= 0) {
         this.hikariLogger2.handleString("%s - Fill pool skipped, pool is at sufficient level.", new Object[]{this.string});
      }

      for (int var2 = 0; var2 < var1; var2++) {
         this.threadPoolExecutor.submit(var2 < var1 - 1 ? this.poolEntryCreator : this.poolEntryCreator2);
      }
   }

   private final void handleExecutorService(ExecutorService var1) {
      for (Object var3o : this.concurrentBag.getList(1)) {
         PoolEntry var3 = (PoolEntry) var3o;
         Connection var4 = var3.getConnection();

         try {
            var4.abort(var1);
         } catch (Throwable var9) {
            this.handleConnection2(var4, "(connection aborted during shutdown)");
         } finally {
            this.concurrentBag.checkIConcurrentBagEntry(var3);
         }
      }
   }

   private final void run6() {
      long var1 = this.hikariConfig.getLongValue12();
      if (var1 >= 0L) {
         long var3 = ClockSource.getLong2();

         do {
            PoolEntry var5 = this.getPoolEntry();
            if (var5 != null) {
               if (this.hikariConfig.getIntValue3() > 0) {
                  this.concurrentBag.handleIConcurrentBagEntry2(var5);
                  this.hikariLogger2.handleString("%s - Added connection %s", new Object[]{this.string, var5.connection.toString()});
               } else {
                  this.handleConnection2(var5.getConnection(), "(initialization check complete and minimumIdle is zero)");
               }

               return;
            }

            if (this.getException() instanceof ConnectionSetupException) {
               this.handleThrowable(this.getException().getCause());
            }

            UtilityElf.handleLong(TimeUnit.SECONDS.toMillis(1L));
         } while (ClockSource.getLong3(var3) < var1);

         if (var1 > 0L) {
            this.handleThrowable(this.getException());
         }
      }
   }

   private final void handleThrowable(Throwable var1) {
      this.run7();
      throw new PoolInitializationException(var1);
   }

   private final boolean checkPoolEntry(PoolEntry var1, String var2, boolean var3) {
      var1.run();
      if (!var3 && !this.concurrentBag.checkIConcurrentBagEntry2(var1)) {
         return false;
      } else {
         this.handlePoolEntry(var1, var2);
         return true;
      }
   }

   private final ScheduledExecutorService getScheduledExecutorService() {
      if (this.hikariConfig.getScheduledExecutorService() == null) {
         ThreadFactory var1 = Optional.ofNullable(this.hikariConfig.getThreadFactory()).orElseGet(this::getThreadFactory);
         ScheduledThreadPoolExecutor var2 = new ScheduledThreadPoolExecutor(1, var1, new DiscardPolicy());
         var2.setExecuteExistingDelayedTasksAfterShutdownPolicy(false);
         var2.setRemoveOnCancelPolicy(true);
         return var2;
      } else {
         return this.hikariConfig.getScheduledExecutorService();
      }
   }

   private final void run7() {
      if (this.hikariConfig.getScheduledExecutorService() == null) {
         this.scheduledExecutorService.shutdownNow();
      }
   }

   private final SQLException getSQLException(long var1) {
      this.handleStringArray(new String[]{"Timeout failure "});
      String var3 = null;
      Exception var4 = this.getException();
      if (var4 instanceof SQLException) {
         var3 = ((SQLException)var4).getSQLState();
      }

      SQLTransientConnectionException var5 = new SQLTransientConnectionException(
         this.string + " - Connection is not available, request timed out after " + ClockSource.getLong3(var1) + "ms.", var3, var4
      );
      if (var4 instanceof SQLException) {
         var5.setNextException((SQLException)var4);
      }

      return var5;
   }

   @Override
   public DataSource getDataSource() {
      return super.getDataSource();
   }

   @Override
   public String toString() {
      return super.toString();
   }

   private final ThreadFactory getThreadFactory() {
      return new DefaultThreadFactory(this.string + " housekeeper", true);
   }

   private final void handleConnection(Connection var1, String var2) {
      this.handleConnection2(var1, var2);
      if (this.intValue12 == 0) {
         this.run5();
      }
   }

   private final void handlePoolEntry2(PoolEntry var1) {
      this.checkPoolEntry(var1, "(connection evicted)", false);
   }

   public static PoolEntry getPoolEntry(HikariPool var0) {
      return var0.getPoolEntry();
   }

   public static ConcurrentBag getConcurrentBag(HikariPool var0) {
      return var0.concurrentBag;
   }

   public static HikariLogger getHikariLogger(HikariPool var0) {
      return var0.hikariLogger2;
   }

   public static long getLong(HikariPool var0) {
      return var0.longValue4;
   }

   public static ProxyLeakTaskFactory getProxyLeakTaskFactory(HikariPool var0) {
      return var0.proxyLeakTaskFactory;
   }

   public static void handleHikariPool(HikariPool var0) {
      var0.run5();
   }

   public static boolean checkHikariPool(HikariPool var0, PoolEntry var1, String var2, boolean var3) {
      return var0.checkPoolEntry(var1, var2, var3);
   }
}
