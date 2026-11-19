package litebans.randomid;

import litebans.lib.kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;

public final class ShuffleException extends RuntimeException {
   public ShuffleException(@NotNull String var1) {
      super(var1);
   }

   public ShuffleException(String var1, int var2, DefaultConstructorMarker var3) {
      this(((var2 & 1) != 0 ? "" : var1));
   }

   public ShuffleException() {
      this(null, 1, null);
   }
}
