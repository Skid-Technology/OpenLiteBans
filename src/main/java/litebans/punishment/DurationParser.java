package litebans.punishment;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import litebans.config.Message;
import litebans.lib.kotlin.collections.CollectionsKt;
import litebans.lib.kotlin.jvm.internal.DefaultConstructorMarker;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.text.Regex;
import litebans.lib.kotlin.text.RegexReplace;
import litebans.lib.kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

public final class DurationParser {
   private DurationParser() {
   }

   public final Regex getRegex() {
      return DurationUnit.getRegex();
   }

   public final Regex getRegex2() {
      return DurationUnit.getRegex2();
   }

   public final DurationUnit getDurationUnit(@NotNull String var1) {
      boolean var10000;
      boolean var18;
      DurationUnit[] var2 = DurationUnit.values();
      int var3 = 0;
      int var4 = var2.length;

      DurationUnit var19;
      while (true) {
         if (var3 >= var4) {
            var19 = null;
            break;
         }

         DurationUnit var5;
         label36: {
            var5 = var2[var3];
            if (!var5.checkString(var1)) {
               String[] var12 = DurationUnit.getStringArray(var5);
               int var14 = 0;
               int var15 = var12.length;

               while (true) {
                  if (var14 >= var15) {
                     var10000 = false;
                     break;
                  }

                  String var16 = var12[var14];
                  if (StringsKt.checkString(var16, var1, true)) {
                     var10000 = true;
                     break;
                  }

                  var14++;
               }

               if (!var10000) {
                  var18 = false;
                  break label36;
               }
            }

            var18 = true;
         }

         if (var18) {
            var19 = var5;
            break;
         }

         var3++;
      }

      return var19;
   }

   public final double getDouble(@NotNull String var1, double var2) {
      if (!Intrinsics.checkObject(var1, "permanent")
         && !Intrinsics.checkObject(var1, "none")
         && !Intrinsics.checkObject(var1, "unlimited")
         && !Intrinsics.checkObject(var1, "never")
         && !Intrinsics.checkObject(var1, Message.FOREVER.toString())) {
         List var15;
         label57: {
            CharSequence var10000 = var1;
            char[] var5 = new char[]{' '};
            List var11 = StringsKt.getList(var10000, var5, false, 0, 6, null);
            if (!var11.isEmpty()) {
               ListIterator var7 = var11.listIterator(var11.size());

               while (var7.hasPrevious()) {
                  String var8 = (String)var7.previous();
                  if (var8.length() != 0) {
                     var15 = CollectionsKt.getList(var11, var7.nextIndex() + 1);
                     break label57;
                  }
               }
            }

            var15 = CollectionsKt.getList();
         }

         List var4 = var15;
         if (var4.isEmpty()) {
            return var2;
         } else {
            String var12 = StringsKt.getString((String)var4.get(0), ',', '.', false, 4, null);
            Double var6 = StringsKt.getDouble(var12);
            if (var6 != null) {
               String var16 = String.valueOf(StringsKt.getChar2(var12));
               Intrinsics.handleObject(var16, "");
               if (!Intrinsics.checkObject(var16.toLowerCase(Locale.ROOT), "d")) {
                  if (var4.size() == 1) {
                     return var6;
                  }

                  DurationUnit var19 = this.getDurationUnit((String)var4.get(1));
                  if (var19 == null) {
                     return var2;
                  }

                  DurationUnit var14 = var19;
                  return (double)var14.getLong(var6);
               }
            }

            Double var17 = StringsKt.getDouble(RegexReplace.getString(this.getRegex2(), var12, ""));
            if (var17 != null) {
               double var13 = var17;
               String var9 = RegexReplace.getString(this.getRegex(), var12, "");
               DurationUnit var18 = this.getDurationUnit(var9);
               if (var18 == null) {
                  return var2;
               } else {
                  DurationUnit var10 = var18;
                  return (double)var10.getLong(var13);
               }
            } else {
               return var2;
            }
         }
      } else {
         return 0.0;
      }
   }

   public static double getDoubleDefault(DurationParser var0, String var1, double var2, int var4, Object var5) {
      if ((var4 & 2) != 0) {
         var2 = 0.0;
      }

      return var0.getDouble(var1, var2);
   }

   public final String getString(long var1, boolean var3) {
      if (var1 <= 0L) {
         return Message.FOREVER.toString();
      } else {
         long var4 = TimeUnit.MILLISECONDS.toDays(var1);
         long var6 = TimeUnit.MILLISECONDS.toHours(var1);
         long var8 = var6 - TimeUnit.DAYS.toHours(var4);
         long var10 = TimeUnit.MILLISECONDS.toMinutes(var1) - TimeUnit.HOURS.toMinutes(var6);
         long var12 = TimeUnit.MILLISECONDS.toSeconds(var1) - TimeUnit.MINUTES.toSeconds(var10);
         CharSequence var14;
         CharSequence var15;
         CharSequence var16;
         CharSequence var17;
         if (var3) {
            var14 = this.getCharSequence(var4, Message.DAY, Message.DAYS);
            var15 = this.getCharSequence(var8, Message.HOUR, Message.HOURS);
            var16 = this.getCharSequence(var10, Message.MINUTE, Message.MINUTES);
            var17 = this.getCharSequence(var12, Message.SECOND, Message.SECONDS);
         } else {
            var14 = this.getCharSequence(var4, "day", "days");
            var15 = this.getCharSequence(var8, "hour", "hours");
            var16 = this.getCharSequence(var10, "minute", "minutes");
            var17 = this.getCharSequence(var12, "second", "seconds");
         }

         String var18 = Message.DURATION_FORMAT.toString();
         String var19 = Message.DURATION_SEPARATOR.toString();
         String var10000;
         if (var4 <= 0L) {
            if (var8 <= 0L) {
               if (var10 <= 0L) {
                  Object[] var20 = new Object[]{var12, var17};
                  var10000 = String.format(var18, Arrays.copyOf(var20, var20.length));
               } else {
                  Object[] var22 = new Object[]{var10, var16};
                  var10000 = String.format(var18, Arrays.copyOf(var22, var22.length));
               }
            } else if (var10 == 0L) {
               Object[] var23 = new Object[]{var8, var15};
               var10000 = String.format(var18, Arrays.copyOf(var23, var23.length));
            } else {
               String var24 = var18 + var19 + var18;
               Object[] var21 = new Object[]{var8, var15, var10, var16};
               var10000 = String.format(var24, Arrays.copyOf(var21, var21.length));
            }
         } else if (var10 == 0L) {
            if (var8 == 0L) {
               Object[] var25 = new Object[]{var4, var14};
               var10000 = String.format(var18, Arrays.copyOf(var25, var25.length));
            } else {
               String var26 = var18 + var19 + var18;
               Object[] var29 = new Object[]{var4, var14, var8, var15};
               var10000 = String.format(var26, Arrays.copyOf(var29, var29.length));
            }
         } else if (var8 == 0L) {
            String var27 = var18 + var19 + var18;
            Object[] var30 = new Object[]{var4, var14, var10, var16};
            var10000 = String.format(var27, Arrays.copyOf(var30, var30.length));
         } else {
            String var28 = var18 + var19 + var18 + var19 + var18;
            Object[] var31 = new Object[]{var4, var14, var8, var15, var10, var16};
            var10000 = String.format(var28, Arrays.copyOf(var31, var31.length));
         }

         return var10000;
      }
   }

   public static String getStringDefault(DurationParser var0, long var1, boolean var3, int var4, Object var5) {
      if ((var4 & 2) != 0) {
         var3 = true;
      }

      return var0.getString(var1, var3);
   }

   private final boolean checkString(String var1, Object var2) {
      return StringsKt.checkString(var1, var2.toString(), true);
   }

   private final boolean checkString(String var1, Object var2, Object var3) {
      return this.checkString(var1, var2) || this.checkString(var1, var3);
   }

   public final CharSequence getCharSequence(@NotNull Number var1, @NotNull Object var2, @NotNull Object var3) {
      return (var1.doubleValue() == 1.0 ? var2 : var3).toString();
   }

   public static final boolean checkDurationParser(DurationParser var0, String var1, Object var2, Object var3) {
      return var0.checkString(var1, var2, var3);
   }

   public DurationParser(DefaultConstructorMarker var1) {
      this();
   }
}
