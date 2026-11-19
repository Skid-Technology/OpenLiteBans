package litebans.lib.compress.archivers.zip;

import java.util.Arrays;
import java.util.zip.ZipException;

public class X0017_StrongEncryptionHeader extends PKWareExtraHeader {
   private int intValue2;
   private PkwareEncryptionAlgorithm pkwareEncryptionAlgorithm;
   private int intValue3;
   private int intValue4;
   private long longValue;
   private PkwareHashAlgorithm pkwareHashAlgorithm;
   private int intValue5;
   private byte[] byteArray3;
   private byte[] byteArray4;
   private byte[] byteArray5;
   private byte[] byteArray6;
   private byte[] byteArray7;
   private byte[] byteArray8;

   public X0017_StrongEncryptionHeader() {
      super(new ZipShort(23));
   }

   public void handleByteArray3(byte[] var1, int var2, int var3) {
      this.handleInt(12, var3);
      this.intValue2 = ZipShort.getInt(var1, var2);
      this.pkwareEncryptionAlgorithm = PkwareEncryptionAlgorithm.getPkwareEncryptionAlgorithm(ZipShort.getInt(var1, var2 + 2));
      this.intValue3 = ZipShort.getInt(var1, var2 + 4);
      this.intValue4 = ZipShort.getInt(var1, var2 + 6);
      this.longValue = ZipLong.getLong(var1, var2 + 8);
      if (this.longValue > 0L) {
         this.handleInt(16, var3);
         this.pkwareHashAlgorithm = PkwareHashAlgorithm.getPkwareHashAlgorithm(ZipShort.getInt(var1, var2 + 12));
         this.intValue5 = ZipShort.getInt(var1, var2 + 14);

         for (long var4 = 0L; var4 < this.longValue; var4++) {
            int var6 = 0;

            while (var6 < this.intValue5) {
               var6++;
            }
         }
      }
   }

   public void handleByteArray4(byte[] var1, int var2, int var3) {
      try {
      this.handleInt(4, var3);
      int var4 = ZipShort.getInt(var1, var2);
      this.handleString("ivSize", var4, 4, var3);
      this.byteArray3 = Arrays.copyOfRange(var1, var2 + 4, var4);
      this.handleInt(16 + var4, var3);
      this.intValue2 = ZipShort.getInt(var1, var2 + var4 + 6);
      this.pkwareEncryptionAlgorithm = PkwareEncryptionAlgorithm.getPkwareEncryptionAlgorithm(ZipShort.getInt(var1, var2 + var4 + 8));
      this.intValue3 = ZipShort.getInt(var1, var2 + var4 + 10);
      this.intValue4 = ZipShort.getInt(var1, var2 + var4 + 12);
      int var5 = ZipShort.getInt(var1, var2 + var4 + 14);
      this.handleString("erdSize", var5, var4 + 16, var3);
      this.byteArray4 = Arrays.copyOfRange(var1, var2 + var4 + 16, var5);
      this.handleInt(20 + var4 + var5, var3);
      this.longValue = ZipLong.getLong(var1, var2 + var4 + 16 + var5);
      if (this.longValue == 0L) {
         this.handleInt(var4 + 20 + var5 + 2, var3);
         int var6 = ZipShort.getInt(var1, var2 + var4 + 20 + var5);
         this.handleString("vSize", var6, var4 + 22 + var5, var3);
         if (var6 < 4) {
            throw new ZipException("Invalid X0017_StrongEncryptionHeader: vSize " + var6 + " is too small to hold CRC");
         }

         this.byteArray7 = Arrays.copyOfRange(var1, var2 + var4 + 22 + var5, var6 - 4);
         this.byteArray8 = Arrays.copyOfRange(var1, var2 + var4 + 22 + var5 + var6 - 4, 4);
      } else {
         this.handleInt(var4 + 20 + var5 + 6, var3);
         this.pkwareHashAlgorithm = PkwareHashAlgorithm.getPkwareHashAlgorithm(ZipShort.getInt(var1, var2 + var4 + 20 + var5));
         this.intValue5 = ZipShort.getInt(var1, var2 + var4 + 22 + var5);
         int var8 = ZipShort.getInt(var1, var2 + var4 + 24 + var5);
         this.byteArray5 = new byte[this.intValue5];
         if (var8 < this.intValue5) {
            throw new ZipException("Invalid X0017_StrongEncryptionHeader: resize " + var8 + " is too small to hold hashSize" + this.intValue5);
         }

         this.byteArray6 = new byte[var8 - this.intValue5];
         this.handleString("resize", var8, var4 + 24 + var5, var3);
         System.arraycopy(var1, var2 + var4 + 24 + var5, this.byteArray5, 0, this.intValue5);
         System.arraycopy(var1, var2 + var4 + 24 + var5 + this.intValue5, this.byteArray6, 0, var8 - this.intValue5);
         this.handleInt(var4 + 26 + var5 + var8 + 2, var3);
         int var7 = ZipShort.getInt(var1, var2 + var4 + 26 + var5 + var8);
         if (var7 < 4) {
            throw new ZipException("Invalid X0017_StrongEncryptionHeader: vSize " + var7 + " is too small to hold CRC");
         }

         this.handleString("vSize", var7, var4 + 22 + var5 + var8, var3);
         this.byteArray7 = new byte[var7 - 4];
         this.byteArray8 = new byte[4];
         System.arraycopy(var1, var2 + var4 + 22 + var5 + var8, this.byteArray7, 0, var7 - 4);
         System.arraycopy(var1, var2 + var4 + 22 + var5 + var8 + var7 - 4, this.byteArray8, 0, 4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void handleByteArray(byte[] var1, int var2, int var3) {
      super.handleByteArray(var1, var2, var3);
      this.handleByteArray4(var1, var2, var3);
   }

   @Override
   public void handleByteArray2(byte[] var1, int var2, int var3) {
      super.handleByteArray2(var1, var2, var3);
      this.handleByteArray3(var1, var2, var3);
   }

   private final void handleString(String var1, int var2, int var3, int var4) {
      try {
      if (var3 + var2 > var4) {
         throw new ZipException(
            "Invalid X0017_StrongEncryptionHeader: " + var1 + " " + var2 + " doesn't fit into " + var4 + " bytes of data at position " + var3
         );
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }
}
