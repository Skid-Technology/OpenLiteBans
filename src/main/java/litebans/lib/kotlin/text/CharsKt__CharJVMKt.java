package litebans.lib.kotlin.text;

import java.util.Locale;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.ranges.IntRange;
import org.jetbrains.annotations.NotNull;

public class CharsKt__CharJVMKt {
   public static final boolean checkChar(char var0) {
      return Character.isWhitespace(var0) || Character.isSpaceChar(var0);
   }

   public static final String getString(char charValue, @NotNull Locale locale) {
      Intrinsics.handleObject3(locale, "locale");
      String var10000 = String.valueOf(charValue);
      Intrinsics.handleObject(var10000, "");
      return var10000.toUpperCase(locale);
   }

   public static final String getString2(char charValue, @NotNull Locale locale) {
      Intrinsics.handleObject3(locale, "locale");
      String var2 = CharsKt.getString(charValue, locale);
      if (var2.length() > 1) {
         String var10000;
         if (charValue == 329) {
            var10000 = var2;
         } else {
            char var3 = var2.charAt(0);
            byte var5 = 1;
            Intrinsics.handleObject(var2, "");
            String var4 = var2.substring(var5);
            Intrinsics.handleObject(var4, "");
            var4 = var4.toLowerCase(Locale.ROOT);
            var10000 = var3 + var4;
         }

         return var10000;
      } else {
         String var10001 = String.valueOf(charValue);
         Intrinsics.handleObject(var10001, "");
         return !Intrinsics.checkObject(var2, var10001.toUpperCase(Locale.ROOT)) ? var2 : String.valueOf(Character.toTitleCase(charValue));
      }
   }

   public static final int getInt(char var0, int var1) {
      return Character.digit((int)var0, var1);
   }

   public static final int getInt(int var0) {
      if (2 <= var0 ? var0 >= 37 : true) {
         throw new IllegalArgumentException("radix " + var0 + " was not in valid range " + new IntRange(2, 36));
      } else {
         return var0;
      }
   }
}
