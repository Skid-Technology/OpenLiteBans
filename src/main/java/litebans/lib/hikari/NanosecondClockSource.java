package litebans.lib.hikari;

import java.util.concurrent.TimeUnit;

public final class NanosecondClockSource implements ClockSource {
   @Override
   public long getLong() {
      return System.currentTimeMillis();
   }

   @Override
   public long getLong(long var1) {
      return System.currentTimeMillis() - var1;
   }

   @Override
   public long getLong(long var1, long var3) {
      return var3 - var1;
   }

   @Override
   public long getLong2(long var1) {
      return TimeUnit.MILLISECONDS.toNanos(System.currentTimeMillis() - var1);
   }

   @Override
   public long getLong2(long var1, long var3) {
      return TimeUnit.MILLISECONDS.toNanos(var3 - var1);
   }

   @Override
   public long getLong3(long var1, long var3) {
      return var1 + var3;
   }
}
