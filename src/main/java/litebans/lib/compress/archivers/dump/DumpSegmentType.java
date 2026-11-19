package litebans.lib.compress.archivers.dump;

public enum DumpSegmentType {
   TAPE(1),
   INODE(2),
   BITS(3),
   ADDR(4),
   END(5),
   CLRI(6);
   public int intValue;
   private static final DumpSegmentType[] dumpSegmentTypeArray = new DumpSegmentType[]{TAPE, INODE, BITS, ADDR, END, CLRI};

   public static final DumpSegmentType getDumpSegmentType(String var0) {
      return Enum.valueOf(DumpSegmentType.class, var0);
   }

   private DumpSegmentType(int var3) {
      this.intValue = var3;
   }

   public static final DumpSegmentType getDumpSegmentType(int var0) {
      for (DumpSegmentType var4 : values()) {
         if (var4.intValue == var0) {
            return var4;
         }
      }

      return null;
   }
}
