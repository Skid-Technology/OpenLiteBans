package litebans.lib.compress.archivers.zip;

import java.util.zip.ZipException;

public class Zip64ExtendedInformationExtraField implements ZipExtraField {
   public static final ZipShort zipShort = new ZipShort(1);
   private static final String string = "Zip64 extended information must contain both size values in the local file header.";
   private static final byte[] byteArray = new byte[0];
   private ZipEightByteInteger zipEightByteInteger;
   private ZipEightByteInteger zipEightByteInteger2;
   private ZipEightByteInteger zipEightByteInteger3;
   private ZipLong zipLong;
   private byte[] byteArray2;

   @Override
   public ZipShort getZipShort() {
      return zipShort;
   }

   @Override
   public ZipShort getZipShort2() {
      return new ZipShort(this.zipEightByteInteger != null ? 16 : 0);
   }

   @Override
   public ZipShort getZipShort3() {
      return new ZipShort(
         (this.zipEightByteInteger != null ? 8 : 0)
            + (this.zipEightByteInteger2 != null ? 8 : 0)
            + (this.zipEightByteInteger3 != null ? 8 : 0)
            + (this.zipLong != null ? 4 : 0)
      );
   }

   @Override
   public byte[] getByteArray() {
      if (this.zipEightByteInteger == null && this.zipEightByteInteger2 == null) {
         return byteArray;
      } else if (this.zipEightByteInteger != null && this.zipEightByteInteger2 != null) {
         byte[] var1 = new byte[16];
         this.getInt(var1);
         return var1;
      } else {
         throw new IllegalArgumentException("Zip64 extended information must contain both size values in the local file header.");
      }
   }

   @Override
   public byte[] getByteArray2() {
      byte[] var1 = new byte[this.getZipShort3().getIntValue()];
      int var2 = this.getInt(var1);
      if (this.zipEightByteInteger3 != null) {
         System.arraycopy(this.zipEightByteInteger3.getByteArray(), 0, var1, var2, 8);
         var2 += 8;
      }

      if (this.zipLong != null) {
         System.arraycopy(this.zipLong.getByteArray(), 0, var1, var2, 4);
         var2 += 4;
      }

      return var1;
   }

   @Override
   public void handleByteArray(byte[] var1, int var2, int var3) {
      try {
      if (var3 != 0) {
         if (var3 < 16) {
            throw new ZipException("Zip64 extended information must contain both size values in the local file header.");
         } else {
            this.zipEightByteInteger = new ZipEightByteInteger(var1, var2);
            var2 += 8;
            this.zipEightByteInteger2 = new ZipEightByteInteger(var1, var2);
            var2 += 8;
            int var4 = var3 - 16;
            if (var4 >= 8) {
               this.zipEightByteInteger3 = new ZipEightByteInteger(var1, var2);
               var2 += 8;
               var4 -= 8;
            }

            if (var4 >= 4) {
               this.zipLong = new ZipLong(var1, var2);
               var2 += 4;
               var4 -= 4;
            }
         }
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void handleByteArray2(byte[] var1, int var2, int var3) {
      this.byteArray2 = new byte[var3];
      System.arraycopy(var1, var2, this.byteArray2, 0, var3);
      if (var3 >= 28) {
         this.handleByteArray(var1, var2, var3);
      } else if (var3 == 24) {
         this.zipEightByteInteger = new ZipEightByteInteger(var1, var2);
         var2 += 8;
         this.zipEightByteInteger2 = new ZipEightByteInteger(var1, var2);
         var2 += 8;
         this.zipEightByteInteger3 = new ZipEightByteInteger(var1, var2);
      } else if (var3 % 8 == 4) {
         this.zipLong = new ZipLong(var1, var2 + var3 - 4);
      }
   }

   private final int getInt(byte[] var1) {
      byte var2 = 0;
      if (this.zipEightByteInteger != null) {
         System.arraycopy(this.zipEightByteInteger.getByteArray(), 0, var1, 0, 8);
         var2 += 8;
      }

      if (this.zipEightByteInteger2 != null) {
         System.arraycopy(this.zipEightByteInteger2.getByteArray(), 0, var1, var2, 8);
         var2 += 8;
      }

      return var2;
   }
}
