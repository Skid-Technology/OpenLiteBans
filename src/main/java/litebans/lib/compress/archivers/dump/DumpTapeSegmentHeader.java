package litebans.lib.compress.archivers.dump;

public class DumpTapeSegmentHeader {
   private DumpSegmentType dumpSegmentType;
   private int intValue;
   private int intValue2;
   private int intValue3;
   private int intValue4;
   private final byte[] byteArray = new byte[512];

   public DumpSegmentType getDumpSegmentType() {
      return this.dumpSegmentType;
   }

   public int getIntValue() {
      return this.intValue;
   }

   public int getIntValue3() {
      return this.intValue3;
   }

   public int getInt(int var1) {
      return this.byteArray[var1];
   }

   public static final DumpSegmentType getDumpSegmentType(DumpTapeSegmentHeader var0, DumpSegmentType var1) {
      return var0.dumpSegmentType = var1;
   }

   public static final int getInt(DumpTapeSegmentHeader var0, int var1) {
      return var0.intValue = var1;
   }

   public static final int getInt2(DumpTapeSegmentHeader var0, int var1) {
      return var0.intValue2 = var1;
   }

   public static final int getInt3(DumpTapeSegmentHeader var0, int var1) {
      return var0.intValue3 = var1;
   }

   public static final int getInt4(DumpTapeSegmentHeader var0, int var1) {
      return var0.intValue4 = var1;
   }

   public static final int getInt(DumpTapeSegmentHeader var0) {
      return var0.intValue3;
   }

   public static final int getInt2(DumpTapeSegmentHeader var0) {
      return var0.intValue4++;
   }

   public static final byte[] getByteArray(DumpTapeSegmentHeader var0) {
      return var0.byteArray;
   }
}
