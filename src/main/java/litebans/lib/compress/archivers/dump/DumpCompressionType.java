package litebans.lib.compress.archivers.dump;

public enum DumpCompressionType {
   ZLIB(0),
   BZLIB(1),
   LZO(2);
   public int intValue;
   private static final DumpCompressionType[] dumpCompressionTypeArray = new DumpCompressionType[]{ZLIB, BZLIB, LZO};

   public static final DumpCompressionType getDumpCompressionType(String var0) {
      return Enum.valueOf(DumpCompressionType.class, var0);
   }

   private DumpCompressionType(int var3) {
      this.intValue = var3;
   }

   public static final DumpCompressionType getDumpCompressionType(int var0) {
      for (DumpCompressionType var4 : values()) {
         if (var4.intValue == var0) {
            return var4;
         }
      }

      return null;
   }
}
