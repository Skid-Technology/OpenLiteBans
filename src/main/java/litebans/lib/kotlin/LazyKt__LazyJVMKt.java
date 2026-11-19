package litebans.lib.kotlin;

import litebans.lib.kotlin.jvm.internal.Function0;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.util.UnexpectedValueException;
import org.jetbrains.annotations.NotNull;

public class LazyKt__LazyJVMKt {
   public static final Lazy getLazy(@NotNull Function0 initializer) {
      Intrinsics.handleObject3(initializer, "initializer");
      return new SynchronizedLazyImpl(initializer, null, 2, null);
   }

   public static final Lazy getLazy(@NotNull LazyThreadSafetyMode mode, @NotNull Function0 initializer) {
      Intrinsics.handleObject3(mode, "mode");
      Intrinsics.handleObject3(initializer, "initializer");
      Lazy var10000;
      switch (LazyKtWhenMappings.switchMap[mode.ordinal()]) {
         case 1:
            var10000 = new SynchronizedLazyImpl(initializer, null, 2, null);
            break;
         case 2:
            var10000 = new SafePublicationLazyImpl(initializer);
            break;
         case 3:
            var10000 = new UnsafeLazyImpl(initializer);
            break;
         default:
            throw new UnexpectedValueException();
      }

      return var10000;
   }
}
