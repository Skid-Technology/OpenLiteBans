package litebans.lib.compress.archivers.zip;

import java.util.zip.CRC32;
import java.util.zip.ZipException;

public class AsiExtraField implements ZipExtraField, UnixStat, Cloneable {
   private static final ZipShort zipShort = new ZipShort(30062);
   private static final int intValue9 = 4;
   private int intValue10 = 0;
   private int intValue11 = 0;
   private int intValue12 = 0;
   private String string = "";
   private boolean flag = false;
   private CRC32 crc32 = new CRC32();

   @Override
   public ZipShort getZipShort() {
      return zipShort;
   }

   @Override
   public ZipShort getZipShort2() {
      return new ZipShort(14 + this.getString().getBytes().length);
   }

   @Override
   public ZipShort getZipShort3() {
      return this.getZipShort2();
   }

   @Override
   public byte[] getByteArray() {
      byte[] var1 = new byte[this.getZipShort2().getIntValue() - 4];
      System.arraycopy(ZipShort.getByteArray(this.getIntValue10()), 0, var1, 0, 2);
      byte[] var2 = this.getString().getBytes();
      System.arraycopy(ZipLong.getByteArray((long)var2.length), 0, var1, 2, 4);
      System.arraycopy(ZipShort.getByteArray(this.getIntValue11()), 0, var1, 6, 2);
      System.arraycopy(ZipShort.getByteArray(this.getIntValue12()), 0, var1, 8, 2);
      System.arraycopy(var2, 0, var1, 10, var2.length);
      this.crc32.reset();
      this.crc32.update(var1);
      long var3 = this.crc32.getValue();
      byte[] var5 = new byte[var1.length + 4];
      System.arraycopy(ZipLong.getByteArray(var3), 0, var5, 0, 4);
      System.arraycopy(var1, 0, var5, 4, var1.length);
      return var5;
   }

   @Override
   public byte[] getByteArray2() {
      return this.getByteArray();
   }

   public int getIntValue11() {
      return this.intValue11;
   }

   public int getIntValue12() {
      return this.intValue12;
   }

   public String getString() {
      return this.string;
   }

   public boolean check() {
      return this.getString().length() != 0;
   }

   public void handleInt(int var1) {
      this.intValue10 = this.getInt(var1);
   }

   public int getIntValue10() {
      return this.intValue10;
   }

   public void handleBoolean(boolean var1) {
      this.flag = var1;
      this.intValue10 = this.getInt(this.intValue10);
   }

   public boolean check2() {
      return this.flag && !this.check();
   }

   @Override
   public void handleByteArray(byte[] var1, int var2, int var3) {
      try {
      long var4 = ZipLong.getLong(var1, var2);
      byte[] var6 = new byte[var3 - 4];
      System.arraycopy(var1, var2 + 4, var6, 0, var3 - 4);
      this.crc32.reset();
      this.crc32.update(var6);
      long var7 = this.crc32.getValue();
      if (var4 != var7) {
         throw new ZipException("Bad CRC checksum, expected " + Long.toHexString(var4) + " instead of " + Long.toHexString(var7));
      } else {
         int var9 = ZipShort.getInt(var6, 0);
         byte[] var10 = new byte[(int)ZipLong.getLong(var6, 2)];
         this.intValue11 = ZipShort.getInt(var6, 6);
         this.intValue12 = ZipShort.getInt(var6, 8);
         if (var10.length == 0) {
            this.string = "";
         } else {
            if (var10.length > var6.length - 10) {
               throw new ZipException("Bad symbolic link name length " + var10.length + " in ASI extra field");
            }

            System.arraycopy(var6, 10, var10, 0, var10.length);
            this.string = new String(var10);
         }

         this.handleBoolean((var9 & 16384) != 0);
         this.handleInt(var9);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void handleByteArray2(byte[] var1, int var2, int var3) {
      this.handleByteArray(var1, var2, var3);
   }

   public int getInt(int var1) {
      char var2 = '耀';
      if (this.check()) {
         var2 = 'ꀀ';
      } else if (this.check2()) {
         var2 = 16384;
      }

      return var2 | var1 & 4095;
   }

   @Override
   public Object clone() {
      try {
         AsiExtraField var1 = (AsiExtraField)super.clone();
         var1.crc32 = new CRC32();
         return var1;
      } catch (CloneNotSupportedException var2) {
         throw new RuntimeException(var2);
      }
   }
}
