package litebans.lib.kotlin.text;

import java.util.Locale;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.ranges.RangesKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class StringsKt__StringsJVMKt extends StringsKt__StringNumberConversionsKt {
   public static final boolean checkString(@Nullable String var0, @Nullable String var1, boolean var2) {
      if (var0 == null) {
         return var1 == null;
      } else {
         return !var2 ? var0.equals(var1) : var0.equalsIgnoreCase(var1);
      }
   }

   public static final String getString(@NotNull String var0, char var1, char var2, boolean var3) {
      Intrinsics.handleObject3(var0, "<this>");
      if (!var3) {
         return var0.replace(var1, var2);
      } else {
         int var4 = var0.length();
         StringBuilder var5 = new StringBuilder(var4);
         StringBuilder var6 = var5;
         CharSequence var8 = var0;

         for (int var10 = 0; var10 < var8.length(); var10++) {
            char var11 = var8.charAt(var10);
            var6.append(CharsKt.checkChar(var11, var1, var3) ? var2 : var11);
         }

         return var5.toString();
      }
   }

   public static String getString(String var0, char var1, char var2, boolean var3, int var4, Object var5) {
      if ((var4 & 4) != 0) {
         var3 = false;
      }

      return StringsKt.getString(var0, var1, var2, var3);
   }

   public static final String getString(@NotNull String string, @NotNull String oldValue, @NotNull String newValue, boolean flag) {
      Intrinsics.handleObject3(string, "<this>");
      Intrinsics.handleObject3(oldValue, "oldValue");
      Intrinsics.handleObject3(newValue, "newValue");
      String var4 = string;
      int var6 = StringsKt.getInt(string, oldValue, 0, flag);
      if (var6 < 0) {
         return string;
      } else {
         int var7 = oldValue.length();
         int var8 = RangesKt.getInt(var7, 1);
         int var9 = string.length() - var7 + newValue.length();
         if (var9 < 0) {
            throw new OutOfMemoryError();
         } else {
            StringBuilder var10 = new StringBuilder(var9);
            int var11 = 0;

            do {
               var10.append(var4, var11, var6).append(newValue);
               var11 = var6 + var7;
               if (var6 >= var4.length()) {
                  break;
               }

               var6 = StringsKt.getInt(var4, oldValue, var6 + var8, flag);
            } while (var6 > 0);

            return var10.append(var4, var11, var4.length()).toString();
         }
      }
   }

   public static String getString(String var0, String var1, String var2, boolean var3, int var4, Object var5) {
      if ((var4 & 4) != 0) {
         var3 = false;
      }

      return StringsKt.getString(var0, var1, var2, var3);
   }

   public static final boolean checkString2(@NotNull String string, @NotNull String prefix, boolean flag) {
      Intrinsics.handleObject3(string, "<this>");
      Intrinsics.handleObject3(prefix, "prefix");
      return !flag ? string.startsWith(prefix) : StringsKt.checkString(string, 0, prefix, 0, prefix.length(), flag);
   }

   public static boolean checkString(String var0, String var1, boolean var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = false;
      }

      return StringsKt.checkString2(var0, var1, var2);
   }

   public static final boolean checkString3(@NotNull String string, @NotNull String suffix, boolean flag) {
      Intrinsics.handleObject3(string, "<this>");
      Intrinsics.handleObject3(suffix, "suffix");
      return !flag ? string.endsWith(suffix) : StringsKt.checkString(string, string.length() - suffix.length(), suffix, 0, suffix.length(), true);
   }

   public static boolean checkString2(String var0, String var1, boolean var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = false;
      }

      return StringsKt.checkString3(var0, var1, var2);
   }

   public static final boolean checkString(@NotNull String string, int intValue, @NotNull String other, int intValue2, int intValue3, boolean flag) {
      Intrinsics.handleObject3(string, "<this>");
      Intrinsics.handleObject3(other, "other");
      return !flag ? string.regionMatches(intValue, other, intValue2, intValue3) : string.regionMatches(flag, intValue, other, intValue2, intValue3);
   }

   public static final String getString(@NotNull String string, @NotNull Locale locale) {
      Intrinsics.handleObject3(string, "<this>");
      Intrinsics.handleObject3(locale, "locale");
      if (string.length() > 0) {
         char var2 = string.charAt(0);
         if (Character.isLowerCase(var2)) {
            StringBuilder var3 = new StringBuilder();
            char var6 = Character.toTitleCase(var2);
            if (var6 != Character.toUpperCase(var2)) {
               var3.append(var6);
            } else {
               byte var8 = 0;
               byte var9 = 1;
               String var7 = string.substring(var8, var9);
               Intrinsics.handleObject(var7, "");
               var3.append(var7.toUpperCase(locale));
            }

            byte var10 = 1;
            var3.append(string.substring(var10));
            return var3.toString();
         }
      }

      return string;
   }
}
