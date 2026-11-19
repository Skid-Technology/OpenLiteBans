package litebans.lib.compress.archivers.zip;

import java.io.Serializable;
import litebans.lib.compress.utils.ByteUtils;

public final class ZipShort implements Cloneable, Serializable {
   public static final ZipShort INSTANCE = new ZipShort(0);
   private static final long longValue = 1L;
   private final int intValue;

   public ZipShort(int var1) {
      this.intValue = var1;
   }

   public ZipShort(byte[] var1, int var2) {
      this.intValue = getInt(var1, var2);
   }

   public byte[] getByteArray() {
      byte[] var1 = new byte[2];
      ByteUtils.handleByteArray(var1, (long)this.intValue, 0, 2);
      return var1;
   }

   public int getIntValue() {
      return this.intValue;
   }

   public static final byte[] getByteArray(int var0) {
      byte[] var1 = new byte[2];
      handleInt(var0, var1, 0);
      return var1;
   }

   public static final void handleInt(int var0, byte[] var1, int var2) {
      ByteUtils.handleByteArray(var1, (long)var0, var2, 2);
   }

   public static final int getInt(byte[] var0, int var1) {
      return (int)ByteUtils.getLong(var0, var1, 2);
   }

   @Override
   public boolean equals(Object var1) {
      return var1 != null && var1 instanceof ZipShort ? this.intValue == ((ZipShort)var1).getIntValue() : false;
   }

   @Override
   public int hashCode() {
      return this.intValue;
   }

   @Override
   public Object clone() {
      try {
         return super.clone();
      } catch (CloneNotSupportedException var2) {
         throw new RuntimeException(var2);
      }
   }

   @Override
   public String toString() {
      return "ZipShort value: " + this.intValue;
   }
}
