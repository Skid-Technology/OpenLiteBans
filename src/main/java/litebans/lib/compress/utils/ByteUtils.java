package litebans.lib.compress.utils;

public final class ByteUtils {
   public static final long getLong(byte[] var0, int var1, int var2) {
      handleInt(var2);
      long var3 = 0L;

      for (int var5 = 0; var5 < var2; var5++) {
         var3 |= ((long)var0[var1 + var5] & 255L) << 8 * var5;
      }

      return var3;
   }

   public static final void handleByteArray(byte[] var0, long var1, int var3, int var4) {
      long var5 = var1;

      for (int var7 = 0; var7 < var4; var7++) {
         var0[var3 + var7] = (byte)((int)(var5 & 255L));
         var5 >>= 8;
      }
   }

   private static final void handleInt(int var0) {
      if (var0 > 8) {
         throw new IllegalArgumentException("Can't read more than eight bytes into a long value");
      }
   }
}
