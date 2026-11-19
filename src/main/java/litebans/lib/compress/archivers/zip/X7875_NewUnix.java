package litebans.lib.compress.archivers.zip;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.zip.ZipException;
import litebans.randomid.RadixEncoder;

public class X7875_NewUnix implements ZipExtraField, Cloneable, Serializable {
   private static final ZipShort zipShort = new ZipShort(30837);
   private static final ZipShort zipShort2 = new ZipShort(0);
   private static final BigInteger bigInteger = BigInteger.valueOf(1000L);
   private static final long longValue = 1L;
   private int intValue2 = 1;
   private BigInteger UID;
   private BigInteger GID;

   public X7875_NewUnix() {
      this.run();
   }

   @Override
   public ZipShort getZipShort() {
      return zipShort;
   }

   @Override
   public ZipShort getZipShort2() {
      byte[] var1 = getByteArray(this.UID.toByteArray());
      int var2 = var1 == null ? 0 : var1.length;
      var1 = getByteArray(this.GID.toByteArray());
      int var3 = var1 == null ? 0 : var1.length;
      return new ZipShort(3 + var2 + var3);
   }

   @Override
   public ZipShort getZipShort3() {
      return zipShort2;
   }

   @Override
   public byte[] getByteArray() {
      byte[] var1 = this.UID.toByteArray();
      byte[] var2 = this.GID.toByteArray();
      var1 = getByteArray(var1);
      int var3 = var1 != null ? var1.length : 0;
      var2 = getByteArray(var2);
      int var4 = var2 != null ? var2.length : 0;
      byte[] var5 = new byte[3 + var3 + var4];
      if (var1 != null) {
         RadixEncoder.getByteArray(var1);
      }

      if (var2 != null) {
         RadixEncoder.getByteArray(var2);
      }

      int var6 = 0;
      var5[var6++] = RadixEncoder.getByte(this.intValue2);
      var5[var6++] = RadixEncoder.getByte(var3);
      if (var1 != null) {
         System.arraycopy(var1, 0, var5, var6, var3);
      }

      var6 += var3;
      var5[var6++] = RadixEncoder.getByte(var4);
      if (var2 != null) {
         System.arraycopy(var2, 0, var5, var6, var4);
      }

      return var5;
   }

   @Override
   public byte[] getByteArray2() {
      return new byte[0];
   }

   @Override
   public void handleByteArray(byte[] var1, int var2, int var3) {
      try {
      this.run();
      if (var3 < 3) {
         throw new ZipException("X7875_NewUnix length is too short, only " + var3 + " bytes");
      } else {
         this.intValue2 = RadixEncoder.getInt(var1[var2++]);
         int var4 = RadixEncoder.getInt(var1[var2++]);
         if (var4 + 3 > var3) {
            throw new ZipException("X7875_NewUnix invalid: uidSize " + var4 + " doesn't fit into " + var3 + " bytes");
         } else {
            byte[] var5 = Arrays.copyOfRange(var1, var2, var2 + var4);
            var2 += var4;
            this.UID = new BigInteger(1, RadixEncoder.getByteArray(var5));
            int var6 = RadixEncoder.getInt(var1[var2++]);
            if (var4 + 3 + var6 > var3) {
               throw new ZipException("X7875_NewUnix invalid: gidSize " + var6 + " doesn't fit into " + var3 + " bytes");
            } else {
               byte[] var7 = Arrays.copyOfRange(var1, var2, var2 + var6);
               this.GID = new BigInteger(1, RadixEncoder.getByteArray(var7));
            }
         }
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void handleByteArray2(byte[] var1, int var2, int var3) {
   }

   private final void run() {
      this.UID = bigInteger;
      this.GID = bigInteger;
   }

   @Override
   public String toString() {
      return "0x7875 Zip Extra Field: UID=" + this.UID + " GID=" + this.GID;
   }

   @Override
   public Object clone() {
      try {
      return super.clone();
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean equals(Object var1) {
      if (!(var1 instanceof X7875_NewUnix)) {
         return false;
      } else {
         X7875_NewUnix var2 = (X7875_NewUnix)var1;
         return this.intValue2 == var2.intValue2 && this.UID.equals(var2.UID) && this.GID.equals(var2.GID);
      }
   }

   @Override
   public int hashCode() {
      int var1 = -1234567 * this.intValue2;
      var1 ^= Integer.rotateLeft(this.UID.hashCode(), 16);
      return var1 ^ this.GID.hashCode();
   }

   public static final byte[] getByteArray(byte[] var0) {
      if (var0 == null) {
         return var0;
      } else {
         int var1 = 0;

         for (byte var5 : var0) {
            if (var5 != 0) {
               break;
            }

            var1++;
         }

         byte[] var6 = new byte[Math.max(1, var0.length - var1)];
         int var7 = var6.length - (var0.length - var1);
         System.arraycopy(var0, var1, var6, var7, var6.length - var7);
         return var6;
      }
   }
}
