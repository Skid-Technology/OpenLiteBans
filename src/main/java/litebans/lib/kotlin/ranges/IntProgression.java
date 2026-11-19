package litebans.lib.kotlin.ranges;

import java.util.Iterator;
import litebans.lib.kotlin.collections.IntIterator;
import litebans.lib.kotlin.jvm.internal.KMappedMarker;
import org.jetbrains.annotations.Nullable;

public class IntProgression implements Iterable, KMappedMarker {
   public static final IntProgressionCompanion Companion = new IntProgressionCompanion(null);
   private final int intValue;
   private final int intValue2;
   private final int intValue3;

   public IntProgression(int var1, int var2, int var3) {
      if (var3 == 0) {
         throw new IllegalArgumentException("Step must be non-zero.");
      } else if (var3 == Integer.MIN_VALUE) {
         throw new IllegalArgumentException("Step must be greater than Int.MIN_VALUE to avoid overflow on negation.");
      } else {
         this.intValue = var1;
         this.intValue2 = ProgressionUtilKt.getInt2(var1, var2, var3);
         this.intValue3 = var3;
      }
   }

   public final int getIntValue() {
      return this.intValue;
   }

   public final int getIntValue2() {
      return this.intValue2;
   }

   public final int getIntValue3() {
      return this.intValue3;
   }

   public IntIterator getIntIterator() {
      return new IntProgressionIterator(this.intValue, this.intValue2, this.intValue3);
   }

   public boolean check() {
      return this.intValue3 > 0 ? this.intValue > this.intValue2 : this.intValue < this.intValue2;
   }

   @Override
   public boolean equals(@Nullable Object var1) {
      return var1 instanceof IntProgression
         && (
            this.check() && ((IntProgression)var1).check()
               || this.intValue == ((IntProgression)var1).intValue
                  && this.intValue2 == ((IntProgression)var1).intValue2
                  && this.intValue3 == ((IntProgression)var1).intValue3
         );
   }

   @Override
   public int hashCode() {
      return this.check() ? -1 : 31 * (31 * this.intValue + this.intValue2) + this.intValue3;
   }

   @Override
   public String toString() {
      return this.intValue3 > 0
         ? this.intValue + ".." + this.intValue2 + " step " + this.intValue3
         : this.intValue + " downTo " + this.intValue2 + " step " + -this.intValue3;
   }

   @Override
   public Iterator iterator() {
      return this.getIntIterator();
   }
}
