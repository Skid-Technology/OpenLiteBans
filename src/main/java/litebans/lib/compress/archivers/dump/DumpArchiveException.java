package litebans.lib.compress.archivers.dump;

import java.io.IOException;

public class DumpArchiveException extends IOException {
   private static final long longValue = 1L;

   public DumpArchiveException() {
   }

   public DumpArchiveException(String var1) {
      super(var1);
   }

   public DumpArchiveException(String var1, Throwable var2) {
      super(var1);
      this.initCause(var2);
   }
}
