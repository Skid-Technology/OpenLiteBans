package litebans.lib.compress.archivers;

public class StreamingNotSupportedException extends ArchiveException {
   private static final long longValue2 = 1L;
   private final String string;

   public StreamingNotSupportedException(String var1) {
      super("The " + var1 + " doesn't support streaming.");
      this.string = var1;
   }
}
