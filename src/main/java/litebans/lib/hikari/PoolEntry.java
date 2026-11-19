package litebans.lib.hikari;

import java.sql.Connection;
import java.sql.Statement;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public final class PoolEntry implements IConcurrentBagEntry {
   private static final HikariLogger hikariLogger = HikariLogger.getHikariLogger(PoolEntry.class);
   private static final AtomicIntegerFieldUpdater atomicIntegerFieldUpdater = AtomicIntegerFieldUpdater.newUpdater(PoolEntry.class, "intValue5");
   public Connection connection;
   public long longValue;
   private volatile int intValue5 = 0;
   private volatile boolean flag;
   private volatile ScheduledFuture scheduledFuture;
   private volatile ScheduledFuture scheduledFuture2;
   private final FastList fastList;
   private final HikariPool hikariPool;
   private final boolean flag2;
   private final boolean flag3;

   public PoolEntry(Connection var1, PoolBase var2, boolean var3, boolean var4) {
      this.connection = var1;
      this.hikariPool = (HikariPool)var2;
      this.flag2 = var3;
      this.flag3 = var4;
      this.longValue = ClockSource.getLong2();
      this.fastList = new FastList(Statement.class, 16);
   }

   public void handleLong(long var1) {
      if (this.connection != null) {
         this.longValue = var1;
         this.hikariPool.handlePoolEntry(this);
      }
   }

   public void setScheduledFuture(ScheduledFuture var1) {
      this.scheduledFuture = var1;
   }

   public void setScheduledFuture2(ScheduledFuture var1) {
      this.scheduledFuture2 = var1;
   }

   public Connection getConnection(ProxyLeakTask var1, long var2) {
      return ProxyFactory.getProxyConnection(this, this.connection, this.fastList, var1, var2, this.flag2, this.flag3);
   }

   public void handleProxyConnection(ProxyConnection var1, int var2) {
      this.hikariPool.handleConnection(this.connection, var1, var2);
   }

   public String getString() {
      return this.hikariPool.toString();
   }

   public boolean isFlag() {
      return this.flag;
   }

   public void run() {
      this.flag = true;
   }

   public void handleString(String var1) {
      this.hikariPool.handlePoolEntry(this, var1);
   }

   public PoolBase getHikariPool() {
      return this.hikariPool;
   }

   @Override
   public String toString() {
      long var1 = ClockSource.getLong2();
      return this.connection + ", accessed " + ClockSource.getString(this.longValue, var1) + " ago, " + this.getString2();
   }

   @Override
   public int getInt() {
      return atomicIntegerFieldUpdater.get(this);
   }

   @Override
   public boolean checkInt(int var1, int var2) {
      return atomicIntegerFieldUpdater.compareAndSet(this, var1, var2);
   }

   @Override
   public void handleInt(int var1) {
      atomicIntegerFieldUpdater.set(this, var1);
   }

   public Connection getConnection() {
      ScheduledFuture var1 = this.scheduledFuture;
      if (var1 != null && !var1.isDone() && !var1.cancel(false)) {
         hikariLogger.handleString4(
            "%s - maxLifeTime expiration task cancellation unexpectedly returned false for connection %s",
            new Object[]{this.getString(), this.connection.toString()}
         );
      }

      ScheduledFuture var2 = this.scheduledFuture2;
      if (var2 != null && !var2.isDone() && !var2.cancel(false)) {
         hikariLogger.handleString4(
            "%s - keepalive task cancellation unexpectedly returned false for connection %s", new Object[]{this.getString(), this.connection.toString()}
         );
      }

      Connection var3 = this.connection;
      this.connection = null;
      this.scheduledFuture = null;
      this.scheduledFuture2 = null;
      return var3;
   }

   private final String getString2() {
      switch (this.intValue5) {
         case -2:
            return "RESERVED";
         case -1:
            return "REMOVED";
         case 0:
            return "NOT_IN_USE";
         case 1:
            return "IN_USE";
         default:
            return "Invalid";
      }
   }
}
