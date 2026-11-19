package litebans.lib.compress.archivers.zip;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.charset.UnsupportedCharsetException;

public abstract class ZipEncodingHelper {
   public static final String string = "UTF8";
   public static final ZipEncoding zipEncoding = getZipEncoding("UTF8");

   public static final ZipEncoding getZipEncoding(String var0) {
      Charset var1 = Charset.defaultCharset();
      if (var0 != null) {
         try {
            var1 = Charset.forName(var0);
         } catch (UnsupportedCharsetException var3) {
         }
      }

      boolean var2 = checkString(var1.name());
      return new NioZipEncoding(var1, var2);
   }

   public static final boolean checkString(String var0) {
      if (var0 == null) {
         var0 = Charset.defaultCharset().name();
      }

      if (StandardCharsets.UTF_8.name().equalsIgnoreCase(var0)) {
         return true;
      } else {
         for (String var2 : StandardCharsets.UTF_8.aliases()) {
            if (var2.equalsIgnoreCase(var0)) {
               return true;
            }
         }

         return false;
      }
   }
}
