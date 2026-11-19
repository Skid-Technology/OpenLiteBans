package litebans.lib.compress.archivers.dump;

public enum DumpType {
   WHITEOUT(14),
   SOCKET(12),
   LINK(10),
   FILE(8),
   BLKDEV(6),
   DIRECTORY(4),
   CHRDEV(2),
   FIFO(1),
   UNKNOWN(15);
   private int intValue;
   private static final DumpType[] dumpTypeArray = new DumpType[]{WHITEOUT, SOCKET, LINK, FILE, BLKDEV, DIRECTORY, CHRDEV, FIFO, UNKNOWN};

   public static final DumpType getDumpType(String var0) {
      return Enum.valueOf(DumpType.class, var0);
   }

   private DumpType(int var3) {
      this.intValue = var3;
   }

   public static final DumpType getDumpType(int var0) {
      DumpType var1 = UNKNOWN;

      for (DumpType var5 : values()) {
         if (var0 == var5.intValue) {
            var1 = var5;
         }
      }

      return var1;
   }
}
