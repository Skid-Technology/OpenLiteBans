package litebans.punishment;

import litebans.config.Message;
import litebans.lib.kotlin.collections.EnumEntries;
import litebans.lib.kotlin.collections.EnumEntriesKt;
import litebans.lib.kotlin.text.Regex;
import litebans.util.UnexpectedValueException;
import org.jetbrains.annotations.NotNull;

public enum DurationUnit {
   SECONDS(new String[]{"s", "se", "sc", "sec", "secs", "second", "seconds"}),
   MINUTES(new String[]{"m", "mi", "min", "mins", "minute", "minutes"}),
   HOURS(new String[]{"h", "hr", "hrs", "hour", "hours"}),
   DAYS(new String[]{"d", "da", "dy", "day", "days"}),
   WEEKS(new String[]{"w", "we", "wk", "wks", "week", "weeks"}),
   MONTHS(new String[]{"mo", "mh", "mon", "month", "months"}),
   YEARS(new String[]{"y", "ye", "yr", "yrs", "year", "years"});
   public static final DurationParser durationParser = new DurationParser(null);
   private final String[] stringArray;
   public static final double doubleValue = 0.0;
   private static final Regex regex = new Regex("[0-9.]");
   private static final Regex regex2 = new Regex("[^0-9.]");
   private static final DurationUnit[] durationUnitArray = getDurationUnitArray();
   private static final EnumEntries enumEntries = EnumEntriesKt.getEnumEntries(durationUnitArray);

   private DurationUnit(String[] var3) {
      this.stringArray = var3;
   }

   public final long getLong(double var1) {
      double var3 = var1 * 1000.0;
      double var10000;
      switch (DurationUnitWhenMappings.switchMap[this.ordinal()]) {
         case 1:
            var10000 = var3;
            break;
         case 2:
            var10000 = var3 * 60.0;
            break;
         case 3:
            var10000 = var3 * 60.0 * 60.0;
            break;
         case 4:
            var10000 = var3 * 60.0 * 60.0 * 24.0;
            break;
         case 5:
            var10000 = var3 * 60.0 * 60.0 * 24.0 * 7.0;
            break;
         case 6:
            var10000 = var3 * 60.0 * 60.0 * 24.0 * 30.0;
            break;
         case 7:
            var10000 = var3 * 60.0 * 60.0 * 24.0 * 365.0;
            break;
         default:
            throw new UnexpectedValueException();
      }

      return (long)var10000;
   }

   public final boolean checkString(@NotNull String var1) {
      boolean var10000;
      switch (DurationUnitWhenMappings.switchMap[this.ordinal()]) {
         case 1:
            var10000 = DurationParser.checkDurationParser(durationParser, var1, Message.SECOND, Message.SECONDS);
            break;
         case 2:
            var10000 = DurationParser.checkDurationParser(durationParser, var1, Message.MINUTE, Message.MINUTES);
            break;
         case 3:
            var10000 = DurationParser.checkDurationParser(durationParser, var1, Message.HOUR, Message.HOURS);
            break;
         case 4:
            var10000 = DurationParser.checkDurationParser(durationParser, var1, Message.DAY, Message.DAYS);
            break;
         case 5:
            var10000 = DurationParser.checkDurationParser(durationParser, var1, Message.WEEK, Message.WEEKS);
            break;
         case 6:
            var10000 = DurationParser.checkDurationParser(durationParser, var1, Message.MONTH, Message.MONTHS);
            break;
         case 7:
            var10000 = DurationParser.checkDurationParser(durationParser, var1, Message.YEAR, Message.YEARS);
            break;
         default:
            throw new UnexpectedValueException();
      }

      return var10000;
   }

   public final CharSequence getCharSequence(double var1) {
      CharSequence var10000;
      switch (DurationUnitWhenMappings.switchMap[this.ordinal()]) {
         case 1:
            var10000 = durationParser.getCharSequence(var1, Message.SECOND, Message.SECONDS);
            break;
         case 2:
            var10000 = durationParser.getCharSequence(var1, Message.MINUTE, Message.MINUTES);
            break;
         case 3:
            var10000 = durationParser.getCharSequence(var1, Message.HOUR, Message.HOURS);
            break;
         case 4:
            var10000 = durationParser.getCharSequence(var1, Message.DAY, Message.DAYS);
            break;
         case 5:
            var10000 = durationParser.getCharSequence(var1, Message.WEEK, Message.WEEKS);
            break;
         case 6:
            var10000 = durationParser.getCharSequence(var1, Message.MONTH, Message.MONTHS);
            break;
         case 7:
            var10000 = durationParser.getCharSequence(var1, Message.YEAR, Message.YEARS);
            break;
         default:
            throw new UnexpectedValueException();
      }

      return var10000;
   }

   public static DurationUnit getDurationUnit(String var0) {
      return Enum.valueOf(DurationUnit.class, var0);
   }

   private static final DurationUnit[] getDurationUnitArray() {
      return new DurationUnit[]{SECONDS, MINUTES, HOURS, DAYS, WEEKS, MONTHS, YEARS};
   }

   public static final String[] getStringArray(DurationUnit var0) {
      return var0.stringArray;
   }

   public static final Regex getRegex() {
      return regex;
   }

   public static final Regex getRegex2() {
      return regex2;
   }
}
