package litebans.lib.kotlin.ranges;

public class RangesKt___RangesKt extends RangesKt__RangesKt {
   public static final IntProgression getIntProgression(int var0, int var1) {
      return IntProgression.Companion.getIntProgression(var0, var1, -1);
   }

   public static final IntRange getIntRange(int var0, int var1) {
      return var1 <= Integer.MIN_VALUE ? IntRange.Companion2.getIntRange() : new IntRange(var0, var1 - 1);
   }

   public static final int getInt(int var0, int var1) {
      return var0 < var1 ? var1 : var0;
   }

   public static final int getInt2(int var0, int var1) {
      return var0 > var1 ? var1 : var0;
   }

   public static final int getInt(int var0, int var1, int var2) {
      if (var1 > var2) {
         throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + var2 + " is less than minimum " + var1 + 46);
      } else if (var0 < var1) {
         return var1;
      } else {
         return var0 > var2 ? var2 : var0;
      }
   }
}
