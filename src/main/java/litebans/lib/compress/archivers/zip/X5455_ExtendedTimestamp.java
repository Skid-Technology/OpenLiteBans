package litebans.lib.compress.archivers.zip;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.zip.ZipException;
import litebans.randomid.RadixEncoder;

public class X5455_ExtendedTimestamp implements ZipExtraField, Cloneable, Serializable {
   private static final ZipShort zipShort = new ZipShort(21589);
   private static final long longValue = 1L;
   public static final byte byteValue = 1;
   public static final byte byteValue2 = 2;
   public static final byte byteValue3 = 4;
   private byte Flags;
   private boolean flag;
   private boolean flag2;
   private boolean flag3;
   private ZipLong zipLong;
   private ZipLong zipLong2;
   private ZipLong zipLong3;

   @Override
   public ZipShort getZipShort() {
      return zipShort;
   }

   @Override
   public ZipShort getZipShort2() {
      return new ZipShort(1 + (this.flag ? 4 : 0) + (this.flag2 && this.zipLong2 != null ? 4 : 0) + (this.flag3 && this.zipLong3 != null ? 4 : 0));
   }

   @Override
   public ZipShort getZipShort3() {
      return new ZipShort(1 + (this.flag ? 4 : 0));
   }

   @Override
   public byte[] getByteArray() {
      byte[] var1 = new byte[this.getZipShort2().getIntValue()];
      int var2 = 0;
      var1[var2++] = 0;
      if (this.flag) {
         var1[0] = (byte)(var1[0] | 1);
         System.arraycopy(this.zipLong.getByteArray(), 0, var1, var2, 4);
         var2 += 4;
      }

      if (this.flag2 && this.zipLong2 != null) {
         var1[0] = (byte)(var1[0] | 2);
         System.arraycopy(this.zipLong2.getByteArray(), 0, var1, var2, 4);
         var2 += 4;
      }

      if (this.flag3 && this.zipLong3 != null) {
         var1[0] = (byte)(var1[0] | 4);
         System.arraycopy(this.zipLong3.getByteArray(), 0, var1, var2, 4);
         var2 += 4;
      }

      return var1;
   }

   @Override
   public byte[] getByteArray2() {
      return Arrays.copyOf(this.getByteArray(), this.getZipShort3().getIntValue());
   }

   @Override
   public void handleByteArray(byte[] var1, int var2, int var3) {
      try {
      this.run();
      if (var3 < 1) {
         throw new ZipException("X5455_ExtendedTimestamp too short, only " + var3 + " bytes");
      } else {
         int var4 = var2 + var3;
         this.handleByte(var1[var2++]);
         if (this.flag && var2 + 4 <= var4) {
            this.zipLong = new ZipLong(var1, var2);
            var2 += 4;
         }

         if (this.flag2 && var2 + 4 <= var4) {
            this.zipLong2 = new ZipLong(var1, var2);
            var2 += 4;
         }

         if (this.flag3 && var2 + 4 <= var4) {
            this.zipLong3 = new ZipLong(var1, var2);
            var2 += 4;
         }
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void handleByteArray2(byte[] var1, int var2, int var3) {
      this.run();
      this.handleByteArray(var1, var2, var3);
   }

   private final void run() {
      this.handleByte((byte)0);
      this.zipLong = null;
      this.zipLong2 = null;
      this.zipLong3 = null;
   }

   public void handleByte(byte var1) {
      this.Flags = var1;
      this.flag = (var1 & 1) == 1;
      this.flag2 = (var1 & 2) == 2;
      this.flag3 = (var1 & 4) == 4;
   }

   public Date getDate() {
      return getDate(this.zipLong);
   }

   public Date getDate2() {
      return getDate(this.zipLong2);
   }

   public Date getDate3() {
      return getDate(this.zipLong3);
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append("0x5455 Zip Extra Field: Flags=");
      var1.append(Integer.toBinaryString(RadixEncoder.getByte(this.Flags))).append(" ");
      if (this.flag && this.zipLong != null) {
         Date var2 = this.getDate();
         var1.append(" Modify:[").append(var2).append("] ");
      }

      if (this.flag2 && this.zipLong2 != null) {
         Date var3 = this.getDate2();
         var1.append(" Access:[").append(var3).append("] ");
      }

      if (this.flag3 && this.zipLong3 != null) {
         Date var4 = this.getDate3();
         var1.append(" Create:[").append(var4).append("] ");
      }

      return var1.toString();
   }

   @Override
   public Object clone() {
      try {
      return super.clone();
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean equals(Object var1) {
      if (!(var1 instanceof X5455_ExtendedTimestamp)) {
         return false;
      } else {
         X5455_ExtendedTimestamp var2 = (X5455_ExtendedTimestamp)var1;
         return (this.Flags & 7) == (var2.Flags & 7)
            && (this.zipLong == var2.zipLong || this.zipLong != null && this.zipLong.equals(var2.zipLong))
            && (this.zipLong2 == var2.zipLong2 || this.zipLong2 != null && this.zipLong2.equals(var2.zipLong2))
            && (this.zipLong3 == var2.zipLong3 || this.zipLong3 != null && this.zipLong3.equals(var2.zipLong3));
      }
   }

   @Override
   public int hashCode() {
      int var1 = -123 * (this.Flags & 7);
      if (this.zipLong != null) {
         var1 ^= this.zipLong.hashCode();
      }

      if (this.zipLong2 != null) {
         var1 ^= Integer.rotateLeft(this.zipLong2.hashCode(), 11);
      }

      if (this.zipLong3 != null) {
         var1 ^= Integer.rotateLeft(this.zipLong3.hashCode(), 22);
      }

      return var1;
   }

   private static final Date getDate(ZipLong var0) {
      return var0 != null ? new Date((long)var0.getInt() * 1000L) : null;
   }
}
