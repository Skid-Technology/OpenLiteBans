package litebans.lib.compress.archivers.zip;

import java.util.Date;

public class X000A_NTFS implements ZipExtraField {
   private static final ZipShort zipShort = new ZipShort(10);
   private static final ZipShort zipShort2 = new ZipShort(1);
   private static final ZipShort zipShort3 = new ZipShort(24);
   private ZipEightByteInteger zipEightByteInteger = ZipEightByteInteger.INSTANCE;
   private ZipEightByteInteger zipEightByteInteger2 = ZipEightByteInteger.INSTANCE;
   private ZipEightByteInteger zipEightByteInteger3 = ZipEightByteInteger.INSTANCE;
   private static final long longValue = -116444736000000000L;

   @Override
   public ZipShort getZipShort() {
      return zipShort;
   }

   @Override
   public ZipShort getZipShort2() {
      return new ZipShort(32);
   }

   @Override
   public ZipShort getZipShort3() {
      return this.getZipShort2();
   }

   @Override
   public byte[] getByteArray() {
      byte[] var1 = new byte[this.getZipShort2().getIntValue()];
      int var2 = 4;
      System.arraycopy(zipShort2.getByteArray(), 0, var1, var2, 2);
      var2 += 2;
      System.arraycopy(zipShort3.getByteArray(), 0, var1, var2, 2);
      var2 += 2;
      System.arraycopy(this.zipEightByteInteger.getByteArray(), 0, var1, var2, 8);
      var2 += 8;
      System.arraycopy(this.zipEightByteInteger2.getByteArray(), 0, var1, var2, 8);
      var2 += 8;
      System.arraycopy(this.zipEightByteInteger3.getByteArray(), 0, var1, var2, 8);
      return var1;
   }

   @Override
   public byte[] getByteArray2() {
      return this.getByteArray();
   }

   @Override
   public void handleByteArray(byte[] var1, int var2, int var3) {
      int var4 = var2 + var3;
      var2 += 4;

      while (var2 + 4 <= var4) {
         ZipShort var5 = new ZipShort(var1, var2);
         var2 += 2;
         if (var5.equals(zipShort2)) {
            this.handleByteArray3(var1, var2, var4 - var2);
            break;
         }

         ZipShort var6 = new ZipShort(var1, var2);
         var2 += 2 + var6.getIntValue();
      }
   }

   @Override
   public void handleByteArray2(byte[] var1, int var2, int var3) {
      this.run();
      this.handleByteArray(var1, var2, var3);
   }

   public Date getDate() {
      return getDate(this.zipEightByteInteger);
   }

   public Date getDate2() {
      return getDate(this.zipEightByteInteger2);
   }

   public Date getDate3() {
      return getDate(this.zipEightByteInteger3);
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append("0x000A Zip Extra Field:")
         .append(" Modify:[")
         .append(this.getDate())
         .append("] ")
         .append(" Access:[")
         .append(this.getDate2())
         .append("] ")
         .append(" Create:[")
         .append(this.getDate3())
         .append("] ");
      return var1.toString();
   }

   @Override
   public boolean equals(Object var1) {
      if (!(var1 instanceof X000A_NTFS)) {
         return false;
      } else {
         X000A_NTFS var2 = (X000A_NTFS)var1;
         return (
               this.zipEightByteInteger == var2.zipEightByteInteger
                  || this.zipEightByteInteger != null && this.zipEightByteInteger.equals(var2.zipEightByteInteger)
            )
            && (
               this.zipEightByteInteger2 == var2.zipEightByteInteger2
                  || this.zipEightByteInteger2 != null && this.zipEightByteInteger2.equals(var2.zipEightByteInteger2)
            )
            && (
               this.zipEightByteInteger3 == var2.zipEightByteInteger3
                  || this.zipEightByteInteger3 != null && this.zipEightByteInteger3.equals(var2.zipEightByteInteger3)
            );
      }
   }

   @Override
   public int hashCode() {
      int var1 = -123;
      if (this.zipEightByteInteger != null) {
         var1 ^= this.zipEightByteInteger.hashCode();
      }

      if (this.zipEightByteInteger2 != null) {
         var1 ^= Integer.rotateLeft(this.zipEightByteInteger2.hashCode(), 11);
      }

      if (this.zipEightByteInteger3 != null) {
         var1 ^= Integer.rotateLeft(this.zipEightByteInteger3.hashCode(), 22);
      }

      return var1;
   }

   private final void run() {
      this.zipEightByteInteger = ZipEightByteInteger.INSTANCE;
      this.zipEightByteInteger2 = ZipEightByteInteger.INSTANCE;
      this.zipEightByteInteger3 = ZipEightByteInteger.INSTANCE;
   }

   private final void handleByteArray3(byte[] var1, int var2, int var3) {
      if (var3 >= 26) {
         ZipShort var4 = new ZipShort(var1, var2);
         if (zipShort3.equals(var4)) {
            var2 += 2;
            this.zipEightByteInteger = new ZipEightByteInteger(var1, var2);
            var2 += 8;
            this.zipEightByteInteger2 = new ZipEightByteInteger(var1, var2);
            var2 += 8;
            this.zipEightByteInteger3 = new ZipEightByteInteger(var1, var2);
         }
      }
   }

   private static final Date getDate(ZipEightByteInteger var0) {
      if (var0 != null && !ZipEightByteInteger.INSTANCE.equals(var0)) {
         long var1 = (var0.getLong() + -116444736000000000L) / 10000L;
         return new Date(var1);
      } else {
         return null;
      }
   }
}
