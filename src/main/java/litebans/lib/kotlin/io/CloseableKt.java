package litebans.lib.kotlin.io;

import java.io.Closeable;
import litebans.lib.kotlin.ExceptionsKt;
import org.jetbrains.annotations.Nullable;

public final class CloseableKt {
   public static final void handleCloseable(@Nullable Closeable var0, @Nullable Throwable var1) {
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
