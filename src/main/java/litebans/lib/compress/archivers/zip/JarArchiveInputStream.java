package litebans.lib.compress.archivers.zip;

import java.io.InputStream;

public class JarArchiveInputStream extends ZipArchiveInputStream {
   public JarArchiveInputStream(InputStream var1) {
      super(var1);
   }

   public JarArchiveInputStream(InputStream var1, String var2) {
      super(var1, var2);
   }
}
