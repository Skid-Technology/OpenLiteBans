package litebans.lib.compress.utils;

import java.io.InputStream;

public final class IOUtils {
   private static final int intValue = 8024;
   private static final int intValue2 = 4096;
   private static final byte[] byteArray = new byte[4096];

   public static final long getLong(InputStream var0, long var1) {
      try {
      long var3 = var1;

      while (var1 > 0L) {
         long var5 = var0.skip(var1);
         if (var5 == 0L) {
            break;
         }

         var1 -= var5;
      }

      while (var1 > 0L) {
         int var7 = getInt(var0, byteArray, 0, (int)Math.min(var1, 4096L));
         if (var7 < 1) {
            break;
         }

         var1 -= (long)var7;
      }

      return var3 - var1;
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   public static final int getInt(InputStream var0, byte[] var1) {
      return getInt(var0, var1, 0, var1.length);
   }

   public static final int getInt(InputStream var0, byte[] var1, int var2, int var3) {
      try {
      if (var3 >= 0 && var2 >= 0 && var3 + var2 <= var1.length) {
         int var4 = 0;

         while (var4 != var3) {
            int var5 = var0.read(var1, var2 + var4, var3 - var4);
            if (var5 == -1) {
               break;
            }

            var4 += var5;
         }

         return var4;
      } else {
         throw new IndexOutOfBoundsException();
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }
}
