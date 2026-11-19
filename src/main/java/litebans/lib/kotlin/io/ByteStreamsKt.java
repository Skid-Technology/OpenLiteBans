package litebans.lib.kotlin.io;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class ByteStreamsKt {
   public static final long getLong(@NotNull InputStream inputStream, @NotNull OutputStream out, int intValue) {
      try {
      Intrinsics.handleObject3(inputStream, "<this>");
      Intrinsics.handleObject3(out, "out");
      long var3 = 0L;
      byte[] var5 = new byte[intValue];

      for (int var6 = inputStream.read(var5); var6 >= 0; var6 = inputStream.read(var5)) {
         out.write(var5, 0, var6);
         var3 += (long)var6;
      }

      return var3;
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   public static long getLongDefault(InputStream var0, OutputStream var1, int var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = 8192;
      }

      return getLong(var0, var1, var2);
   }

   public static final byte[] getByteArray(@NotNull InputStream var0) {
      try {
      Intrinsics.handleObject3(var0, "<this>");
      ByteArrayOutputStream var1 = new ByteArrayOutputStream(Math.max(8192, var0.available()));
      getLongDefault(var0, var1, 0, 2, null);
      return var1.toByteArray();
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }
}
