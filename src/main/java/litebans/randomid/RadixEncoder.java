package litebans.randomid;

import java.util.Arrays;
import litebans.lib.compress.archivers.zip.UnsupportedZipFeatureException;
import litebans.lib.compress.archivers.zip.ZipArchiveEntry;
import litebans.lib.compress.archivers.zip.ZipFeature;
import litebans.lib.compress.archivers.zip.ZipLong;
import litebans.lib.compress.archivers.zip.ZipMethod;

public abstract class RadixEncoder {
   private static final byte[] byteArray = ZipLong.getByteArray(8448L);

   public static final byte[] getByteArray(byte[] var0) {
      int var1 = var0.length - 1;

      for (int var2 = 0; var2 < var0.length / 2; var2++) {
         byte var3 = var0[var2];
         var0[var2] = var0[var1 - var2];
         var0[var1 - var2] = var3;
      }

      return var0;
   }

   public static final int getInt(byte var0) {
      return var0 >= 0 ? var0 : 256 + var0;
   }

   public static final byte getByte(int var0) {
      if (var0 > 255 || var0 < 0) {
         throw new IllegalArgumentException("Can only convert non-negative integers between [0,255] to byte: [" + var0 + "]");
      } else {
         return var0 < 128 ? (byte)var0 : (byte)(var0 - 256);
      }
   }

   public static final byte[] getByteArray2(byte[] var0) {
      return var0 != null ? Arrays.copyOf(var0, var0.length) : null;
   }

   private static final boolean checkZipArchiveEntry(ZipArchiveEntry var0) {
      return !var0.getGeneralPurposeBit().isFlag3();
   }

   private static final boolean checkZipArchiveEntry2(ZipArchiveEntry var0) {
      return var0.getMethod() == 0
         || var0.getMethod() == ZipMethod.UNSHRINKING.getIntValue2()
         || var0.getMethod() == ZipMethod.IMPLODING.getIntValue2()
         || var0.getMethod() == 8
         || var0.getMethod() == ZipMethod.ENHANCED_DEFLATED.getIntValue2()
         || var0.getMethod() == ZipMethod.BZIP2.getIntValue2();
   }

   public static final void handleZipArchiveEntry(ZipArchiveEntry var0) {
      try {
      if (!checkZipArchiveEntry(var0)) {
         throw new UnsupportedZipFeatureException(ZipFeature.ENCRYPTION, var0);
      } else if (!checkZipArchiveEntry2(var0)) {
         ZipMethod var1 = ZipMethod.getZipMethod(var0.getMethod());
         if (var1 == null) {
            throw new UnsupportedZipFeatureException(ZipFeature.COMPRESSION_METHOD, var0);
         } else {
            throw new UnsupportedZipFeatureException(var1, var0);
         }
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }
}
