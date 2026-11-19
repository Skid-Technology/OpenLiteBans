package litebans.lib.compress.archivers.zip;

import java.util.Arrays;
import litebans.randomid.RadixEncoder;

public final class UnparseableExtraFieldData implements ZipExtraField {
   private static final ZipShort zipShort = new ZipShort(44225);
   private byte[] byteArray;
   private byte[] byteArray2;

   @Override
   public ZipShort getZipShort() {
      return zipShort;
   }

   @Override
   public ZipShort getZipShort2() {
      return new ZipShort(this.byteArray == null ? 0 : this.byteArray.length);
   }

   @Override
   public ZipShort getZipShort3() {
      return this.byteArray2 == null ? this.getZipShort2() : new ZipShort(this.byteArray2.length);
   }

   @Override
   public byte[] getByteArray() {
      return RadixEncoder.getByteArray2(this.byteArray);
   }

   @Override
   public byte[] getByteArray2() {
      return this.byteArray2 == null ? this.getByteArray() : RadixEncoder.getByteArray2(this.byteArray2);
   }

   @Override
   public void handleByteArray(byte[] var1, int var2, int var3) {
      this.byteArray = Arrays.copyOfRange(var1, var2, var2 + var3);
   }

   @Override
   public void handleByteArray2(byte[] var1, int var2, int var3) {
      this.byteArray2 = Arrays.copyOfRange(var1, var2, var2 + var3);
      if (this.byteArray == null) {
         this.handleByteArray(var1, var2, var3);
      }
   }
}
