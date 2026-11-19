package litebans.lib.compress.archivers.tar;

import litebans.lib.compress.archivers.zip.ZipEncoding;

public final class TarFallbackEncoding implements ZipEncoding {
   @Override
   public String getString(byte[] var1) {
      int var2 = var1.length;
      StringBuilder var3 = new StringBuilder(var2);

      for (byte var7 : var1) {
         if (var7 == 0) {
            break;
         }

         var3.append((char)(var7 & 255));
      }

      return var3.toString();
   }
}
