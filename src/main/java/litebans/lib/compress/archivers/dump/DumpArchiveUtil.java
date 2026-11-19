package litebans.lib.compress.archivers.dump;

import java.util.Arrays;
import litebans.lib.compress.archivers.zip.ZipEncoding;
import litebans.lib.compress.utils.ByteUtils;

public class DumpArchiveUtil {
   public static final int getInt(byte[] var0) {
      int var1 = 0;

      for (int var2 = 0; var2 < 256; var2++) {
         var1 += getInt(var0, 4 * var2);
      }

      return 84446 - (var1 - getInt(var0, 28));
   }

   public static final boolean checkByteArray(byte[] var0) {
      int var1 = getInt(var0, 24);
      if (var1 != 60012) {
         return false;
      } else {
         int var2 = getInt(var0, 28);
         return var2 == getInt(var0);
      }
   }

   public static final long getLong(byte[] var0, int var1) {
      return ByteUtils.getLong(var0, var1, 8);
   }

   public static final int getInt(byte[] var0, int var1) {
      return (int)ByteUtils.getLong(var0, var1, 4);
   }

   public static final int getInt2(byte[] var0, int var1) {
      return (int)ByteUtils.getLong(var0, var1, 2);
   }

   public static final String getString(ZipEncoding var0, byte[] var1, int var2, int var3) {
      return var0.getString(Arrays.copyOfRange(var1, var2, var2 + var3));
   }
}
