package litebans.lib.compress.archivers;

import java.io.InputStream;
import java.util.Set;

public interface ArchiveStreamProvider {
   ArchiveInputStream getArchiveInputStream(String var1, InputStream var2, String var3);

   Set getSet();
}
