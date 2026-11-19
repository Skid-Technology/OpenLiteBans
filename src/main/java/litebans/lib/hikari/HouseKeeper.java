package litebans.lib.hikari;

import java.util.List;

public final class HouseKeeper implements Runnable {
   private volatile long longValue;
   public final HikariPool hikariPool;

   private HouseKeeper(HikariPool var1) {
      this.hikariPool = var1;
      this.longValue = ClockSource.getLong5(ClockSource.getLong2(), -HikariPool.getLong(this.hikariPool));
   }

   @Override
   public void run() {
      try {
         this.hikariPool.longValue = this.hikariPool.hikariConfig.getLongValue7();
         this.hikariPool.longValue2 = this.hikariPool.hikariConfig.getLongValue8();
         HikariPool.getProxyLeakTaskFactory(this.hikariPool).setLongValue(this.hikariPool.hikariConfig.getLongValue10());
         this.hikariPool.string2 = this.hikariPool.hikariConfig.getString() != null
               && !this.hikariPool.hikariConfig.getString().equals(this.hikariPool.string2)
            ? this.hikariPool.hikariConfig.getString()
            : this.hikariPool.string2;
         long var1 = this.hikariPool.hikariConfig.getLongValue9();
         long var3 = ClockSource.getLong2();
         if (ClockSource.getLong5(var3, 128L) < ClockSource.getLong5(this.longValue, HikariPool.getLong(this.hikariPool))) {
            HikariPool.getHikariLogger(this.hikariPool)
               .handleString4(
                  "%s - Retrograde clock change detected (housekeeper delta=%s), soft-evicting connections from pool.",
                  new Object[]{this.hikariPool.string, ClockSource.getString(this.longValue, var3)}
               );
            this.longValue = var3;
            this.hikariPool.run();
            return;
         }

         this.longValue = var3;
         String var5 = "Pool ";
         if (var1 > 0L && this.hikariPool.hikariConfig.getIntValue3() < this.hikariPool.hikariConfig.getIntValue2()) {
            this.hikariPool.handleStringArray(new String[]{"Before cleanup "});
            var5 = "After cleanup  ";
            List var6 = HikariPool.getConcurrentBag(this.hikariPool).getList(0);
            int var7 = var6.size() - this.hikariPool.hikariConfig.getIntValue3();

            for (Object var9o : var6) {
               PoolEntry var9 = (PoolEntry) var9o;
               if (var7 > 0
                  && ClockSource.getLong4(var9.longValue, var3) > var1
                  && HikariPool.getConcurrentBag(this.hikariPool).checkIConcurrentBagEntry2(var9)) {
                  this.hikariPool.handlePoolEntry(var9, "(connection has passed idleTimeout)");
                  var7--;
               }
            }
         }

         this.hikariPool.handleStringArray(new String[]{var5});
         HikariPool.handleHikariPool(this.hikariPool);
      } catch (Exception var10) {
         HikariPool.getHikariLogger(this.hikariPool).handleString2("Unexpected exception in housekeeping task", new Object[]{var10});
      }
   }

   public HouseKeeper(HikariPool var1, HikariPoolHelper var2) {
      this(var1);
   }
}
