package litebans.lib.hikari;

public final class MaxLifetimeTask implements Runnable {
   private final PoolEntry poolEntry;
   public final HikariPool hikariPool;

   public MaxLifetimeTask(HikariPool var1, PoolEntry var2) {
      this.hikariPool = var1;
      this.poolEntry = var2;
   }

   @Override
   public void run() {
      if (HikariPool.checkHikariPool(this.hikariPool, this.poolEntry, "(connection has passed maxLifetime)", false)) {
         this.hikariPool.handleInt(HikariPool.getConcurrentBag(this.hikariPool).getInt());
      }
   }
}
