package litebans.lib.compress.archivers;

public class ArchiveException extends Exception {
   private static final long longValue = 2772690708123267100L;

   public ArchiveException(String var1) {
      super(var1);
   }

   public ArchiveException(String var1, Exception var2) {
      super(var1);
      this.initCause(var2);
   }
}
