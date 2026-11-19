package litebans.lib.hikari;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public final class PoolEntryCreator implements Callable {
   private final String string;
   public final HikariPool hikariPool;

   public PoolEntryCreator(HikariPool var1, String var2) {
      this.hikariPool = var1;
      this.string = var2;
   }

   public Boolean getBoolean() {
      long var1 = 250L;

      while (this.hikariPool.intValue12 == 0 && this.check()) {
         PoolEntry var3 = HikariPool.getPoolEntry(this.hikariPool);
         if (var3 != null) {
            HikariPool.getConcurrentBag(this.hikariPool).handleIConcurrentBagEntry2(var3);
            HikariPool.getHikariLogger(this.hikariPool)
               .handleString("%s - Added connection %s", new Object[]{this.hikariPool.string, var3.connection.toString()});
            if (this.string != null) {
               this.hikariPool.handleStringArray(new String[]{this.string});
            }

            return Boolean.TRUE;
         }

         if (this.string != null) {
            HikariPool.getHikariLogger(this.hikariPool)
               .handleString("%s - Connection add failed, sleeping with backoff: %dms", new Object[]{this.hikariPool.string, var1});
         }

         UtilityElf.handleLong(var1);
         var1 = Math.min(TimeUnit.SECONDS.toMillis(10L), Math.min(this.hikariPool.longValue, (long)((double)var1 * 1.5)));
      }

      return Boolean.FALSE;
   }

   private final synchronized boolean check() {
      return this.hikariPool.getInt3() < this.hikariPool.hikariConfig.getIntValue2()
         && (HikariPool.getConcurrentBag(this.hikariPool).getInt() > 0 || this.hikariPool.getInt() < this.hikariPool.hikariConfig.getIntValue3());
   }

   @Override
   public Object call() {
      return this.getBoolean();
   }
}
