package litebans.lib.compress.archivers.zip;

import java.util.zip.ZipException;

public class ResourceAlignmentExtraField implements ZipExtraField {
   public static final ZipShort zipShort = new ZipShort(41246);
   public static final int intValue2 = 2;
   private static final int intValue3 = 32768;
   private short shortValue;
   private boolean flag;
   private int intValue4 = 0;

   @Override
   public ZipShort getZipShort() {
      return zipShort;
   }

   @Override
   public ZipShort getZipShort2() {
      return new ZipShort(2 + this.intValue4);
   }

   @Override
   public ZipShort getZipShort3() {
      return new ZipShort(2);
   }

   @Override
   public byte[] getByteArray() {
      byte[] var1 = new byte[2 + this.intValue4];
      ZipShort.handleInt(this.shortValue | (this.flag ? '耀' : 0), var1, 0);
      return var1;
   }

   @Override
   public byte[] getByteArray2() {
      return ZipShort.getByteArray(this.shortValue | (this.flag ? '耀' : 0));
   }

   @Override
   public void handleByteArray(byte[] var1, int var2, int var3) {
      this.handleByteArray2(var1, var2, var3);
      this.intValue4 = var3 - 2;
   }

   @Override
   public void handleByteArray2(byte[] var1, int var2, int var3) {
      try {
      if (var3 < 2) {
         throw new ZipException("Too short content for ResourceAlignmentExtraField (0xa11e): " + var3);
      } else {
         int var4 = ZipShort.getInt(var1, var2);
         this.shortValue = (short)(var4 & 32767);
         this.flag = (var4 & 32768) != 0;
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }
}
