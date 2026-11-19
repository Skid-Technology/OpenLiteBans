package litebans.lib.kotlin.ranges;

public final class ProgressionUtilKt {
   private static final int getInt(int var0, int var1) {
      int var2 = var0 % var1;
      return var2 >= 0 ? var2 : var2 + var1;
   }

   private static final int getInt(int var0, int var1, int var2) {
      return getInt(getInt(var0, var2) - getInt(var1, var2), var2);
   }

   public static final int getInt2(int var0, int var1, int var2) {
      int var10000;
      if (var2 > 0) {
         var10000 = var0 >= var1 ? var1 : var1 - getInt(var1, var0, var2);
      } else {
         if (var2 >= 0) {
            throw new IllegalArgumentException("Step is zero.");
         }

         var10000 = var0 <= var1 ? var1 : var1 + getInt(var0, var1, -var2);
      }

      return var10000;
   }
}
