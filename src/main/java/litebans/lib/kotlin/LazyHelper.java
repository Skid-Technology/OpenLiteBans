package litebans.lib.kotlin;

import litebans.lib.kotlin.jvm.internal.Function0;
import org.jetbrains.annotations.NotNull;

public final class LazyHelper {
   public static final Lazy getLazy(@NotNull Function0 var0) {
      return LazyKt.getLazy(LazyThreadSafetyMode.NONE, var0);
   }
}
