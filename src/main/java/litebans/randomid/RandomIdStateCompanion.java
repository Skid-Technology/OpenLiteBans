package litebans.randomid;

import litebans.lib.kotlin.jvm.internal.DefaultConstructorMarker;
import litebans.util.SoftLazy;

public final class RandomIdStateCompanion {
   private RandomIdStateCompanion() {
   }

   public final SoftLazy getSoftLazy() {
      return RandomIdState.getSoftLazy();
   }

   public RandomIdStateCompanion(DefaultConstructorMarker var1) {
      this();
   }
}
