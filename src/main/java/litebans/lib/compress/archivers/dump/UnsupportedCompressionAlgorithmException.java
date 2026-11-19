package litebans.lib.compress.archivers.dump;

public class UnsupportedCompressionAlgorithmException extends DumpArchiveException {
   private static final long longValue2 = 1L;

   public UnsupportedCompressionAlgorithmException() {
      super("this file uses an unsupported compression algorithm.");
   }

   public UnsupportedCompressionAlgorithmException(String var1) {
      super("this file uses an unsupported compression algorithm: " + var1 + ".");
   }
}
