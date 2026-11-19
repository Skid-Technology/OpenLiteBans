package litebans.punishment;

// $VF: synthetic class
public final class DurationUnitWhenMappings {
   public static final int[] switchMap;

   static {
      int[] var0 = new int[DurationUnit.values().length];

      try {
         var0[DurationUnit.SECONDS.ordinal()] = 1;
      } catch (NoSuchFieldError var7) {
      }

      try {
         var0[DurationUnit.MINUTES.ordinal()] = 2;
      } catch (NoSuchFieldError var6) {
      }

      try {
         var0[DurationUnit.HOURS.ordinal()] = 3;
      } catch (NoSuchFieldError var5) {
      }

      try {
         var0[DurationUnit.DAYS.ordinal()] = 4;
      } catch (NoSuchFieldError var4) {
      }

      try {
         var0[DurationUnit.WEEKS.ordinal()] = 5;
      } catch (NoSuchFieldError var3) {
      }

      try {
         var0[DurationUnit.MONTHS.ordinal()] = 6;
      } catch (NoSuchFieldError var2) {
      }

      try {
         var0[DurationUnit.YEARS.ordinal()] = 7;
      } catch (NoSuchFieldError var1) {
      }

      switchMap = var0;
   }
}
