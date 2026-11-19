package litebans.randomid;

import java.util.Arrays;

public class AlphabetCodec {
   private static final char[] charArray = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
   private static final int[] intArray = new int[256];
   private static final byte[] byteArray = new byte[0];
   private static final char[] charArray2 = new char[0];

   public static byte[] getByteArray(String var0) {
      int var1 = var0 != null ? var0.length() : 0;
      if (var1 == 0) {
         return byteArray;
      } else {
         int var2 = 0;

         for (int var3 = 0; var3 < var1; var3++) {
            if (intArray[var0.charAt(var3)] < 0) {
               var2++;
            }
         }

         if ((var1 - var2) % 4 != 0) {
            return null;
         } else {
            int var11 = 0;
            int var4 = var1;

            while (var4 > 1) {
               if (intArray[var0.charAt(--var4)] > 0) {
                  break;
               }

               if (var0.charAt(var4) == '=') {
                  var11++;
               }
            }

            var4 = ((var1 - var2) * 6 >> 3) - var11;
            byte[] var5 = new byte[var4];
            int var6 = 0;
            int var7 = 0;

            while (var7 < var4) {
               int var8 = 0;

               for (int var9 = 0; var9 < 4; var9++) {
                  int var10 = intArray[var0.charAt(var6++)];
                  if (var10 >= 0) {
                     var8 |= var10 << 18 - var9 * 6;
                  } else {
                     var9--;
                  }
               }

               var5[var7++] = (byte)(var8 >> 16);
               if (var7 < var4) {
                  var5[var7++] = (byte)(var8 >> 8);
                  if (var7 < var4) {
                     var5[var7++] = (byte)var8;
                  }
               }
            }

            return var5;
         }
      }
   }

   public static char[] getCharArray(byte[] var0, boolean var1) {
      int var2 = var0 != null ? var0.length : 0;
      if (var0 != null && var2 != 0) {
         int var3 = var2 / 3 * 3;
         int var4 = (var2 - 1) / 3 + 1 << 2;
         int var5 = var4 + (var1 ? (var4 - 1) / 76 << 1 : 0);
         char[] var6 = new char[var5];
         int var7 = 0;
         int var8 = 0;
         int var9 = 0;

         while (var7 < var3) {
            int var10 = (var0[var7++] & 255) << 16 | (var0[var7++] & 255) << 8 | var0[var7++] & 255;
            var6[var8++] = charArray[var10 >>> 18 & 63];
            var6[var8++] = charArray[var10 >>> 12 & 63];
            var6[var8++] = charArray[var10 >>> 6 & 63];
            var6[var8++] = charArray[var10 & 63];
            if (var1) {
               if (++var9 == 19 && var8 < var5 - 2) {
                  var6[var8++] = '\r';
                  var6[var8++] = '\n';
                  var9 = 0;
               }
            }
         }

         var7 = var2 - var3;
         if (var7 > 0) {
            var8 = (var0[var3] & 255) << 10 | (var7 == 2 ? (var0[var2 - 1] & 255) << 2 : 0);
            var6[var5 - 4] = charArray[var8 >> 12];
            var6[var5 - 3] = charArray[var8 >>> 6 & 63];
            var6[var5 - 2] = var7 == 2 ? charArray[var8 & 63] : 61;
            var6[var5 - 1] = '=';
         }

         return var6;
      } else {
         return charArray2;
      }
   }

   public static String getString(byte[] var0, boolean var1) {
      return new String(getCharArray(var0, var1));
   }

   static {
      Arrays.fill(intArray, -1);
      int var0 = 0;
      int var1 = charArray.length;

      while (var0 < var1) {
         intArray[charArray[var0]] = var0++;
      }

      intArray[61] = 0;
   }
}
