package litebans.lib.kotlin.text;

import litebans.lib.kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public class StringsKt__StringNumberConversionsKt extends StringsKt__StringNumberConversionsJVMKt {
   public static final Integer getInteger(@NotNull String var0) {
      Intrinsics.handleObject3(var0, "<this>");
      return StringsKt.getInteger(var0, 10);
   }

   public static final Integer getInteger(@NotNull String var0, int var1) {
      Intrinsics.handleObject3(var0, "<this>");
      CharsKt.getInt(var1);
      int var2 = var0.length();
      if (var2 == 0) {
         return null;
      } else {
         char var6 = var0.charAt(0);
         byte var3;
         boolean var4;
         int var5;
         if (Intrinsics.getInt(var6, 48) < 0) {
            if (var2 == 1) {
               return null;
            }

            var3 = 1;
            switch (var6) {
               case '+':
                  var4 = false;
                  var5 = -2147483647;
                  break;
               case ',':
               default:
                  return null;
               case '-':
                  var4 = true;
                  var5 = Integer.MIN_VALUE;
            }
         } else {
            var3 = 0;
            var4 = false;
            var5 = -2147483647;
         }

         int var7 = -59652323;
         int var8 = var7;
         int var9 = 0;

         for (int var10 = var3; var10 < var2; var10++) {
            int var11 = CharsKt.getInt(var0.charAt(var10), var1);
            if (var11 < 0) {
               return null;
            }

            if (var9 < var8) {
               if (var8 != var7) {
                  return null;
               }

               var8 = var5 / var1;
               if (var9 < var8) {
                  return null;
               }
            }

            var9 *= var1;
            if (var9 < var5 + var11) {
               return null;
            }

            var9 -= var11;
         }

         return var4 ? var9 : -var9;
      }
   }

   public static final Long getLong(@NotNull String var0) {
      Intrinsics.handleObject3(var0, "<this>");
      return StringsKt.getLong(var0, 10);
   }

   public static final Long getLong(@NotNull String var0, int var1) {
      Intrinsics.handleObject3(var0, "<this>");
      CharsKt.getInt(var1);
      int var2 = var0.length();
      if (var2 == 0) {
         return null;
      } else {
         char var7 = var0.charAt(0);
         byte var3;
         boolean var4;
         long var5;
         if (Intrinsics.getInt(var7, 48) < 0) {
            if (var2 == 1) {
               return null;
            }

            var3 = 1;
            switch (var7) {
               case '+':
                  var4 = false;
                  var5 = -9223372036854775807L;
                  break;
               case ',':
               default:
                  return null;
               case '-':
                  var4 = true;
                  var5 = Long.MIN_VALUE;
            }
         } else {
            var3 = 0;
            var4 = false;
            var5 = -9223372036854775807L;
         }

         long var8 = -256204778801521550L;
         long var10 = var8;
         long var12 = 0L;

         for (int var14 = var3; var14 < var2; var14++) {
            int var15 = CharsKt.getInt(var0.charAt(var14), var1);
            if (var15 < 0) {
               return null;
            }

            if (var12 < var10) {
               if (var10 != var8) {
                  return null;
               }

               var10 = var5 / (long)var1;
               if (var12 < var10) {
                  return null;
               }
            }

            var12 *= (long)var1;
            if (var12 < var5 + (long)var15) {
               return null;
            }

            var12 -= (long)var15;
         }

         return var4 ? var12 : -var12;
      }
   }
}
