package litebans.lib.compress.archivers.zip;

import java.io.Serializable;
import java.math.BigInteger;

public final class ZipEightByteInteger implements Serializable {
   private static final long longValue = 1L;
   private static final int intValue = 1;
   private static final int intValue2 = 65280;
   private static final int intValue3 = 8;
   private static final int intValue4 = 2;
   private static final int intValue5 = 16711680;
   private static final int intValue6 = 16;
   private static final int intValue7 = 3;
   private static final long longValue2 = 4278190080L;
   private static final int intValue8 = 24;
   private static final int intValue9 = 4;
   private static final long longValue3 = 1095216660480L;
   private static final int intValue10 = 32;
   private static final int intValue11 = 5;
   private static final long longValue4 = 280375465082880L;
   private static final int intValue12 = 40;
   private static final int intValue13 = 6;
   private static final long longValue5 = 71776119061217280L;
   private static final int intValue14 = 48;
   private static final int intValue15 = 7;
   private static final long longValue6 = 9151314442816847872L;
   private static final int intValue16 = 56;
   private static final int intValue17 = 63;
   private static final byte byteValue = -128;
   private final BigInteger bigInteger;
   public static final ZipEightByteInteger INSTANCE = new ZipEightByteInteger(0L);

   public ZipEightByteInteger(long var1) {
      this(BigInteger.valueOf(var1));
   }

   public ZipEightByteInteger(BigInteger var1) {
      this.bigInteger = var1;
   }

   public ZipEightByteInteger(byte[] var1, int var2) {
      this.bigInteger = getBigInteger(var1, var2);
   }

   public byte[] getByteArray() {
      return getByteArray(this.bigInteger);
   }

   public long getLong() {
      return this.bigInteger.longValue();
   }

   public BigInteger getBigInteger() {
      return this.bigInteger;
   }

   public static final byte[] getByteArray(BigInteger var0) {
      byte[] var1 = new byte[8];
      long var2 = var0.longValue();
      var1[0] = (byte)((int)(var2 & 255L));
      var1[1] = (byte)((int)((var2 & 65280L) >> 8));
      var1[2] = (byte)((int)((var2 & 16711680L) >> 16));
      var1[3] = (byte)((int)((var2 & 4278190080L) >> 24));
      var1[4] = (byte)((int)((var2 & 1095216660480L) >> 32));
      var1[5] = (byte)((int)((var2 & 280375465082880L) >> 40));
      var1[6] = (byte)((int)((var2 & 71776119061217280L) >> 48));
      var1[7] = (byte)((int)((var2 & 9151314442816847872L) >> 56));
      if (var0.testBit(63)) {
         var1[7] |= -128;
      }

      return var1;
   }

   public static final long getLong(byte[] var0, int var1) {
      return getBigInteger(var0, var1).longValue();
   }

   public static final BigInteger getBigInteger(byte[] var0, int var1) {
      long var2 = (long)var0[var1 + 7] << 56 & 9151314442816847872L;
      var2 += (long)var0[var1 + 6] << 48 & 71776119061217280L;
      var2 += (long)var0[var1 + 5] << 40 & 280375465082880L;
      var2 += (long)var0[var1 + 4] << 32 & 1095216660480L;
      var2 += (long)var0[var1 + 3] << 24 & 4278190080L;
      var2 += (long)var0[var1 + 2] << 16 & 16711680L;
      var2 += (long)var0[var1 + 1] << 8 & 65280L;
      var2 += (long)var0[var1] & 255L;
      BigInteger var4 = BigInteger.valueOf(var2);
      return (var0[var1 + 7] & -128) == -128 ? var4.setBit(63) : var4;
   }

   public static final long getLong(byte[] var0) {
      return getLong(var0, 0);
   }

   @Override
   public boolean equals(Object var1) {
      return var1 != null && var1 instanceof ZipEightByteInteger ? this.bigInteger.equals(((ZipEightByteInteger)var1).getBigInteger()) : false;
   }

   @Override
   public int hashCode() {
      return this.bigInteger.hashCode();
   }

   @Override
   public String toString() {
      return "ZipEightByteInteger value: " + this.bigInteger;
   }
}
