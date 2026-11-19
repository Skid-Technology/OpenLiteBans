package litebans.lib.kotlin;

import org.jetbrains.annotations.Nullable;

public final class UninitializedPropertyAccessException extends RuntimeException {
   public UninitializedPropertyAccessException() {
   }

   public UninitializedPropertyAccessException(@Nullable String var1) {
      super(var1);
   }
}
