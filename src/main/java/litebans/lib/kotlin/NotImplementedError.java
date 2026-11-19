package litebans.lib.kotlin;

import litebans.lib.kotlin.jvm.internal.DefaultConstructorMarker;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class NotImplementedError extends Error {
   public NotImplementedError(@NotNull String message) {
      super(message);
   }

   public NotImplementedError(String var1, int var2, DefaultConstructorMarker var3) {
      this(((var2 & 1) != 0 ? "An operation is not implemented." : var1));
   }

   public NotImplementedError() {
      this(null, 1, null);
   }
}
