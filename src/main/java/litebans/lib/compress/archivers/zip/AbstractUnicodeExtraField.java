package litebans.lib.compress.archivers.zip;

import java.util.zip.ZipException;

public abstract class AbstractUnicodeExtraField implements ZipExtraField {
   private long longValue;
   private byte[] byteArray;
   private byte[] byteArray2;

   private final void run() {
      if (this.byteArray != null) {
         this.byteArray2 = new byte[5 + this.byteArray.length];
         this.byteArray2[0] = 1;
         System.arraycopy(ZipLong.getByteArray(this.longValue), 0, this.byteArray2, 1, 4);
         System.arraycopy(this.byteArray, 0, this.byteArray2, 5, this.byteArray.length);
      }
   }

   @Override
   public byte[] getByteArray2() {
      if (this.byteArray2 == null) {
         this.run();
      }

      byte[] var1 = null;
      if (this.byteArray2 != null) {
         var1 = new byte[this.byteArray2.length];
         System.arraycopy(this.byteArray2, 0, var1, 0, var1.length);
      }

      return var1;
   }

   @Override
   public ZipShort getZipShort3() {
      if (this.byteArray2 == null) {
         this.run();
      }

      return new ZipShort(this.byteArray2 != null ? this.byteArray2.length : 0);
   }

   @Override
   public byte[] getByteArray() {
      return this.getByteArray2();
   }

   @Override
   public ZipShort getZipShort2() {
      return this.getZipShort3();
   }

   @Override
   public void handleByteArray(byte[] var1, int var2, int var3) {
      try {
      if (var3 < 5) {
         throw new ZipException("UniCode path extra data must have at least 5 bytes.");
      } else {
         byte var4 = var1[var2];
         if (var4 != 1) {
            throw new ZipException("Unsupported version [" + var4 + "] for UniCode path extra data.");
         } else {
            this.longValue = ZipLong.getLong(var1, var2 + 1);
            this.byteArray = new byte[var3 - 5];
            System.arraycopy(var1, var2 + 5, this.byteArray, 0, var3 - 5);
            this.byteArray2 = null;
         }
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void handleByteArray2(byte[] var1, int var2, int var3) {
      this.handleByteArray(var1, var2, var3);
   }
}
