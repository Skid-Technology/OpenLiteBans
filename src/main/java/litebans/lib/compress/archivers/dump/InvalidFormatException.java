package litebans.lib.compress.archivers.dump;

public class InvalidFormatException extends DumpArchiveException {
   private static final long longValue2 = 1L;

   public InvalidFormatException() {
      super("there was an error decoding a tape segment");
   }
}
