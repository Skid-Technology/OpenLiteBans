package litebans.lib.hikari;

public final class KeepaliveTask implements Runnable {
   private final PoolEntry poolEntry;
   public final HikariPool hikariPool;

   public KeepaliveTask(HikariPool var1, PoolEntry var2) {
      this.hikariPool = var1;
      this.poolEntry = var2;
   }

   @Override
   public void run() {
      if (HikariPool.getConcurrentBag(this.hikariPool).checkIConcurrentBagEntry2(this.poolEntry)) {
         if (!this.hikariPool.checkConnection(this.poolEntry.connection)) {
            HikariPool.checkHikariPool(this.hikariPool, this.poolEntry, "(connection is dead)", true);
            this.hikariPool.handleInt(HikariPool.getConcurrentBag(this.hikariPool).getInt());
         } else {
            HikariPool.getConcurrentBag(this.hikariPool).handleIConcurrentBagEntry3(this.poolEntry);
            HikariPool.getHikariLogger(this.hikariPool)
               .handleString("%s - keepalive: connection %s is alive", new Object[]{this.hikariPool.string, this.poolEntry.connection.toString()});
         }
      }
   }
}
