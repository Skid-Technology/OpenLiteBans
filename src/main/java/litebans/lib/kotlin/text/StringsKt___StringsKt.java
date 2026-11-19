package litebans.lib.kotlin.text;

import java.util.NoSuchElementException;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.ranges.RangesKt;
import org.jetbrains.annotations.NotNull;

public class StringsKt___StringsKt extends StringsKt___StringsJvmKt {
   public static final char getChar(@NotNull CharSequence var0) {
      Intrinsics.handleObject3(var0, "<this>");
      if (var0.length() == 0) {
         throw new NoSuchElementException("Char sequence is empty.");
      } else {
         return var0.charAt(0);
      }
   }

   public static final Character getCharacter(@NotNull CharSequence var0, int var1) {
      Intrinsics.handleObject3(var0, "<this>");
      return (0 <= var1 ? var1 < var0.length() : false) ? var0.charAt(var1) : null;
   }

   public static final char getChar2(@NotNull CharSequence var0) {
      Intrinsics.handleObject3(var0, "<this>");
      if (var0.length() == 0) {
         throw new NoSuchElementException("Char sequence is empty.");
      } else {
         return var0.charAt(StringsKt.getInt(var0));
      }
   }

   public static final String getString(@NotNull String var0, int var1) {
      Intrinsics.handleObject3(var0, "<this>");
      if (var1 < 0) {
         String var2 = "Requested character count " + var1 + " is less than zero.";
         throw new IllegalArgumentException(var2.toString());
      } else {
         return StringsKt.getString2(var0, RangesKt.getInt(var0.length() - var1, 0));
      }
   }

   public static final String getString2(@NotNull String var0, int var1) {
      Intrinsics.handleObject3(var0, "<this>");
      if (var1 < 0) {
         String var5 = "Requested character count " + var1 + " is less than zero.";
         throw new IllegalArgumentException(var5.toString());
      } else {
         byte var3 = 0;
         int var4 = RangesKt.getInt2(var1, var0.length());
         return var0.substring(var3, var4);
      }
   }
}
