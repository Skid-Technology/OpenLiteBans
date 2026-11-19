package litebans.lib.compress.archivers.dump;

public class ShortFileException extends DumpArchiveException {
   private static final long longValue2 = 1L;

   public ShortFileException() {
      super("unexpected EOF");
   }
}
