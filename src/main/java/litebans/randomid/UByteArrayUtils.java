package litebans.randomid;

import java.util.Collection;
import litebans.lib.kotlin.UByte;
import litebans.lib.kotlin.jvm.internal.KMappedMarker;

public final class UByteArrayUtils {
   public static final byte getByte(byte[] var0, int var1) {
      return UByte.getByte(var0[var1]);
   }

   public static int getInt(byte[] var0) {
      return var0.length;
   }
}
