package litebans.lib.kotlin.ranges;

import litebans.lib.kotlin.jvm.internal.DefaultConstructorMarker;

public final class IntProgressionCompanion {
   private IntProgressionCompanion() {
   }

   public final IntProgression getIntProgression(int var1, int var2, int var3) {
      return new IntProgression(var1, var2, var3);
   }

   public IntProgressionCompanion(DefaultConstructorMarker var1) {
      this();
   }
}
