package litebans.lib.compress.archivers;

import java.io.UnsupportedEncodingException;

public class ArchiveUtils {
   private static final int intValue = 255;

   public static final boolean checkString(String var0, byte[] var1, int var2, int var3) {
      byte[] var4;
      try {
         var4 = var0.getBytes("US-ASCII");
      } catch (UnsupportedEncodingException var6) {
         throw new RuntimeException(var6);
      }

      return checkByteArray(var4, 0, var4.length, var1, var2, var3, false);
   }

   public static final boolean checkByteArray(byte[] var0, int var1, int var2, byte[] var3, int var4, int var5, boolean var6) {
      int var7 = var2 < var5 ? var2 : var5;

      for (int var8 = 0; var8 < var7; var8++) {
         if (var0[var1 + var8] != var3[var4 + var8]) {
            return false;
         }
      }

      if (var2 == var5) {
         return true;
      } else if (!var6) {
         return false;
      } else {
         if (var2 > var5) {
            for (int var9 = var5; var9 < var2; var9++) {
               if (var0[var1 + var9] != 0) {
                  return false;
               }
            }
         } else {
            for (int var10 = var2; var10 < var5; var10++) {
               if (var3[var4 + var10] != 0) {
                  return false;
               }
            }
         }

         return true;
      }
   }

   public static final boolean checkByteArray(byte[] var0, int var1) {
      for (int var2 = 0; var2 < var1; var2++) {
         if (var0[var2] != 0) {
            return false;
         }
      }

      return true;
   }
}
