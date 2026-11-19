package litebans.lib.kotlin;

import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.text.CharsKt;
import org.jetbrains.annotations.NotNull;

public final class KotlinMisc {
   public static final UByte getUByte(@NotNull String var0) {
      Intrinsics.handleObject3(var0, "<this>");
      return getUByte(var0, 10);
   }

   public static final UByte getUByte(@NotNull String var0, int var1) {
      Intrinsics.handleObject3(var0, "<this>");
      KotlinVersion var10000 = getKotlinVersion(var0, var1);
      if (var10000 != null) {
         int var2 = var10000.getIntValue();
         byte var3 = -1;
         return Integer.compareUnsigned(var2, KotlinVersion.getInt2(var3 & 255)) > 0 ? null : UByte.getUByte(UByte.getByte((byte)var2));
      } else {
         return null;
      }
   }

   public static final KotlinVersion getKotlinVersion(@NotNull String var0, int var1) {
      Intrinsics.handleObject3(var0, "<this>");
      CharsKt.getInt(var1);
      int var2 = var0.length();
      if (var2 == 0) {
         return null;
      } else {
         byte var3 = -1;
         char var5 = var0.charAt(0);
         byte var4;
         if (Intrinsics.getInt(var5, 48) < 0) {
            if (var2 == 1 || var5 != '+') {
               return null;
            }

            var4 = 1;
         } else {
            var4 = 0;
         }

         int var6 = 119304647;
         int var7 = var6;
         int var8 = KotlinVersion.getInt2(var1);
         int var9 = 0;

         for (int var10 = var4; var10 < var2; var10++) {
            int var11 = CharsKt.getInt(var0.charAt(var10), var1);
            if (var11 < 0) {
               return null;
            }

            if (Integer.compareUnsigned(var9, var7) > 0) {
               if (var7 != var6) {
                  return null;
               }

               var7 = Integer.divideUnsigned(var3, var8);
               if (Integer.compareUnsigned(var9, var7) > 0) {
                  return null;
               }
            }

            var9 = KotlinVersion.getInt2(var9 * var8);
            var9 = KotlinVersion.getInt2(var9 + KotlinVersion.getInt2(var11));
            if (Integer.compareUnsigned(var9, var9) < 0) {
               return null;
            }
         }

         return KotlinVersion.getKotlinVersion(var9);
      }
   }
}
