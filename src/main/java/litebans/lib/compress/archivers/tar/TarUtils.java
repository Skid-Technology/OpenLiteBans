package litebans.lib.compress.archivers.tar;

import java.io.IOException;
import java.math.BigInteger;
import litebans.lib.compress.archivers.zip.ZipEncoding;
import litebans.lib.compress.archivers.zip.ZipEncodingHelper;

public class TarUtils {
   private static final int intValue = 255;
   public static final ZipEncoding zipEncoding = ZipEncodingHelper.getZipEncoding(null);
   public static final ZipEncoding zipEncoding2 = new TarFallbackEncoding();

   public static final long getLong(byte[] var0, int var1, int var2) {
      long var3 = 0L;
      int var5 = var1 + var2;
      int var6 = var1;
      if (var2 < 2) {
         throw new IllegalArgumentException("Length " + var2 + " must be at least 2");
      } else if (var0[var1] == 0) {
         return 0L;
      } else {
         while (var6 < var5 && var0[var6] == 32) {
            var6++;
         }

         for (byte var7 = var0[var5 - 1]; var6 < var5 && (var7 == 0 || var7 == 32); var7 = var0[var5 - 1]) {
            var5--;
         }

         while (var6 < var5) {
            byte var8 = var0[var6];
            if (var8 < 48 || var8 > 55) {
               throw new IllegalArgumentException(getString(var0, var1, var2, var6, var8));
            }

            var3 = (var3 << 3) + (long)(var8 - 48);
            var6++;
         }

         return var3;
      }
   }

   public static final long getLong2(byte[] var0, int var1, int var2) {
      if ((var0[var1] & 128) == 0) {
         return getLong(var0, var1, var2);
      } else {
         boolean var3 = var0[var1] == -1;
         return var2 < 9 ? getLong(var0, var1, var2, var3) : getLong2(var0, var1, var2, var3);
      }
   }

   private static final long getLong(byte[] var0, int var1, int var2, boolean var3) {
      if (var2 >= 9) {
         throw new IllegalArgumentException("At offset " + var1 + ", " + var2 + " byte binary number exceeds maximum signed long value");
      } else {
         long var4 = 0L;

         for (int var6 = 1; var6 < var2; var6++) {
            var4 = (var4 << 8) + (long)(var0[var1 + var6] & 255);
         }

         if (var3) {
            var4 = --var4 ^ (long)Math.pow(2.0, (double)(var2 - 1) * 8.0) - 1L;
         }

         return var3 ? -var4 : var4;
      }
   }

   private static final long getLong2(byte[] var0, int var1, int var2, boolean var3) {
      byte[] var4 = new byte[var2 - 1];
      System.arraycopy(var0, var1 + 1, var4, 0, var2 - 1);
      BigInteger var5 = new BigInteger(var4);
      if (var3) {
         var5 = var5.add(BigInteger.valueOf(-1L)).not();
      }

      if (var5.bitLength() > 63) {
         throw new IllegalArgumentException("At offset " + var1 + ", " + var2 + " byte binary number exceeds maximum signed long value");
      } else {
         return var3 ? -var5.longValue() : var5.longValue();
      }
   }

   public static final boolean checkByteArray(byte[] var0, int var1) {
      return var0[var1] == 1;
   }

   private static final String getString(byte[] var0, int var1, int var2, int var3, byte var4) {
      String var5 = new String(var0, var1, var2);
      var5 = var5.replaceAll("\u0000", "{NUL}");
      return "Invalid byte " + var4 + " at offset " + (var3 - var1) + " in '" + var5 + "' len=" + var2;
   }

   public static final String getString(byte[] var0, int var1, int var2) {
      try {
         return getString(var0, var1, var2, zipEncoding);
      } catch (Exception var6) {
         if (!(var6 instanceof IOException)) { throw litebans.util.Unreachable.rethrow(var6); }
         try {
            return getString(var0, var1, var2, zipEncoding2);
         } catch (Exception var5) {
            if (!(var5 instanceof IOException)) { throw litebans.util.Unreachable.rethrow(var5); }
            throw new RuntimeException(var5);
         }
      }
   }

   public static final String getString(byte[] var0, int var1, int var2, ZipEncoding var3) {
      int var4 = 0;

      for (int var5 = var1; var4 < var2 && var0[var5] != 0; var5++) {
         var4++;
      }

      if (var4 > 0) {
         byte[] var6 = new byte[var4];
         System.arraycopy(var0, var1, var6, 0, var4);
         return var3.getString(var6);
      } else {
         return "";
      }
   }

   public static final TarArchiveStructSparse getTarArchiveStructSparse(byte[] var0, int var1) {
      long var2 = getLong2(var0, var1, 12);
      long var4 = getLong2(var0, var1 + 12, 12);
      return new TarArchiveStructSparse(var2, var4);
   }

   public static final boolean checkByteArray(byte[] var0) {
      long var1 = getLong(var0, 148, 8);
      long var3 = 0L;
      long var5 = 0L;

      for (int var7 = 0; var7 < var0.length; var7++) {
         byte var8 = var0[var7];
         if (148 <= var7 && var7 < 156) {
            var8 = 32;
         }

         var3 += (long)(255 & var8);
         var5 += (long)var8;
      }

      return var1 == var3 || var1 == var5;
   }
}
