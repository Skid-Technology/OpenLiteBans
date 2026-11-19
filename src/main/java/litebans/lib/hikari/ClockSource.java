package litebans.lib.hikari;

import java.util.concurrent.TimeUnit;

public interface ClockSource {
   ClockSource INSTANCE = ClockSourceFactory.getClockSource2();
   TimeUnit[] timeUnitArray = new TimeUnit[]{
      TimeUnit.DAYS, TimeUnit.HOURS, TimeUnit.MINUTES, TimeUnit.SECONDS, TimeUnit.MILLISECONDS, TimeUnit.MICROSECONDS, TimeUnit.NANOSECONDS
   };
   String[] stringArray = new String[]{"ns", "µs", "ms", "s", "m", "h", "d"};

   static long getLong2() {
      return INSTANCE.getLong();
   }

   long getLong();

   static long getLong3(long a) {
      return INSTANCE.getLong(a);
   }

   long getLong(long var1);

   static long getLong4(long var1, long var3) {
      return INSTANCE.getLong(var1, var3);
   }

   long getLong(long var1, long var3);

   static long getLong4(long a) {
      return INSTANCE.getLong2(a);
   }

   long getLong2(long var1);

   long getLong2(long var1, long var3);

   static long getLong5(long var1, long var3) {
      return INSTANCE.getLong3(var1, var3);
   }

   long getLong3(long var1, long var3);

   static String getString(long var1, long var3) {
      return INSTANCE.getString2(var1, var3);
   }

   default String getString2(long var1, long var3) {
      long ax = this.getLong2(var1, var3);
      StringBuilder axx = new StringBuilder(ax < 0L ? "-" : "");
      ax = Math.abs(ax);

      for (TimeUnit axxx : timeUnitArray) {
         long axxxx = axxx.convert(ax, TimeUnit.NANOSECONDS);
         if (axxxx > 0L) {
            axx.append(axxxx).append(stringArray[axxx.ordinal()]);
            ax -= TimeUnit.NANOSECONDS.convert(axxxx, axxx);
         }
      }

      return axx.toString();
   }
}
