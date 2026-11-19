package litebans.lib.compress.archivers.zip;

import java.util.Arrays;
import java.util.zip.ZipException;
import litebans.randomid.RadixEncoder;

public abstract class PKWareExtraHeader implements ZipExtraField {
   private final ZipShort zipShort;
   private byte[] byteArray;
   private byte[] byteArray2;

   public PKWareExtraHeader(ZipShort var1) {
      this.zipShort = var1;
   }

   @Override
   public ZipShort getZipShort() {
      return this.zipShort;
   }

   public void handleByteArray(byte[] var1) {
      this.byteArray = RadixEncoder.getByteArray2(var1);
   }

   @Override
   public ZipShort getZipShort2() {
      return new ZipShort(this.byteArray != null ? this.byteArray.length : 0);
   }

   @Override
   public byte[] getByteArray() {
      return RadixEncoder.getByteArray2(this.byteArray);
   }

   public void handleByteArray2(byte[] var1) {
      this.byteArray2 = RadixEncoder.getByteArray2(var1);
   }

   @Override
   public ZipShort getZipShort3() {
      return this.byteArray2 != null ? new ZipShort(this.byteArray2.length) : this.getZipShort2();
   }

   @Override
   public byte[] getByteArray2() {
      return this.byteArray2 != null ? RadixEncoder.getByteArray2(this.byteArray2) : this.getByteArray();
   }

   @Override
   public void handleByteArray(byte[] var1, int var2, int var3) {
      this.handleByteArray(Arrays.copyOfRange(var1, var2, var2 + var3));
   }

   @Override
   public void handleByteArray2(byte[] var1, int var2, int var3) {
      byte[] var4 = Arrays.copyOfRange(var1, var2, var2 + var3);
      this.handleByteArray2(var4);
      if (this.byteArray == null) {
         this.handleByteArray(var4);
      }
   }

   public final void handleInt(int var1, int var2) {
      try {
      if (var2 < var1) {
         throw new ZipException(this.getClass().getName() + " is too short, only " + var2 + " bytes, expected at least " + var1);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }
}
