package litebans.lib.kotlin.ranges;

import org.jetbrains.annotations.Nullable;

public final class IntRange extends IntProgression implements ClosedRange, OpenEndRange {
   public static final IntRangeCompanion Companion2 = new IntRangeCompanion(null);
   private static final IntRange INSTANCE = new IntRange(1, 0);

   public IntRange(int var1, int var2) {
      super(var1, var2, 1);
   }

   public Integer getInteger() {
      return this.getIntValue();
   }

   public Integer getInteger2() {
      return this.getIntValue2();
   }

   @Override
   public boolean check() {
      return this.getIntValue() > this.getIntValue2();
   }

   @Override
   public boolean equals(@Nullable Object var1) {
      return var1 instanceof IntRange
         && (
            this.check() && ((IntRange)var1).check()
               || this.getIntValue() == ((IntRange)var1).getIntValue() && this.getIntValue2() == ((IntRange)var1).getIntValue2()
         );
   }

   @Override
   public int hashCode() {
      return this.check() ? -1 : 31 * this.getIntValue() + this.getIntValue2();
   }

   @Override
   public String toString() {
      return this.getIntValue() + ".." + this.getIntValue2();
   }

   public static final IntRange getInstance() {
      return INSTANCE;
   }
}
