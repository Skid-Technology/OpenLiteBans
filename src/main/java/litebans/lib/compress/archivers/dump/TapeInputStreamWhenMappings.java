package litebans.lib.compress.archivers.dump;

// $VF: synthetic class
public class TapeInputStreamWhenMappings {
   public static final int[] switchMap = new int[DumpCompressionType.values().length];

   static {
      try {
         switchMap[DumpCompressionType.ZLIB.ordinal()] = 1;
      } catch (NoSuchFieldError var2) {
      }

      try {
         switchMap[DumpCompressionType.BZLIB.ordinal()] = 2;
      } catch (NoSuchFieldError var1) {
      }

      try {
         switchMap[DumpCompressionType.LZO.ordinal()] = 3;
      } catch (NoSuchFieldError var0) {
      }
   }
}
