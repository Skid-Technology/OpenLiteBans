package litebans.lib.kotlin.io;

import litebans.lib.kotlin.ExceptionsKt;
import org.jetbrains.annotations.Nullable;

public final class AutoCloseableKt {
   public static final void handleAutoCloseable(@Nullable AutoCloseable var0, @Nullable Throwable var1) {
      try {
      if (var0 != null) {
         if (var1 == null) {
            var0.close();
         } else {
            try {
               var0.close();
            } catch (Throwable var3) {
               ExceptionsKt.handleThrowable(var1, var3);
            }
         }
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }
}
