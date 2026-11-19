package litebans.lib.hikari;

import java.util.concurrent.TimeUnit;

public class MillisecondClockSource implements ClockSource {
   @Override
   public long getLong() {
      return System.nanoTime();
   }

   @Override
   public long getLong(long var1) {
      return TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - var1);
   }

   @Override
   public long getLong(long var1, long var3) {
      return TimeUnit.NANOSECONDS.toMillis(var3 - var1);
   }

   @Override
   public long getLong2(long var1) {
      return System.nanoTime() - var1;
   }

   @Override
   public long getLong2(long var1, long var3) {
      return var3 - var1;
   }

   @Override
   public long getLong3(long var1, long var3) {
      return var1 + TimeUnit.MILLISECONDS.toNanos(var3);
   }
}
