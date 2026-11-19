package litebans.lib.compress.archivers.dump;

public class UnrecognizedFormatException extends DumpArchiveException {
   private static final long longValue2 = 1L;

   public UnrecognizedFormatException() {
      super("this is not a recognized format.");
   }
}
