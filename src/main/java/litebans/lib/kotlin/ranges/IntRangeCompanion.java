package litebans.lib.kotlin.ranges;

import litebans.lib.kotlin.jvm.internal.DefaultConstructorMarker;

public final class IntRangeCompanion {
   private IntRangeCompanion() {
   }

   public final IntRange getIntRange() {
      return IntRange.getInstance();
   }

   public IntRangeCompanion(DefaultConstructorMarker var1) {
      this();
   }
}
