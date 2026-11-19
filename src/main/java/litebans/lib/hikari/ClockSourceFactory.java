package litebans.lib.hikari;

public class ClockSourceFactory {
   private static final ClockSource getClockSource() {
      String var0 = System.getProperty("os.name");
      return (ClockSource)("Mac OS X".equals(var0) ? new NanosecondClockSource() : new MillisecondClockSource());
   }

   public static ClockSource getClockSource2() {
      return getClockSource();
   }
}
