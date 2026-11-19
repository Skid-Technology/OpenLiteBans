package litebans.lib.compress.archivers.zip;

import java.io.Serializable;
import litebans.lib.compress.utils.ByteUtils;

public final class ZipLong implements Cloneable, Serializable {
   private static final long longValue = 1L;
   private final long longValue2;
   public static final ZipLong INSTANCE = new ZipLong(33639248L);
   public static final ZipLong INSTANCE2 = new ZipLong(67324752L);
   public static final ZipLong INSTANCE3 = new ZipLong(134695760L);
   public static final ZipLong INSTANCE4 = new ZipLong(4294967295L);
   public static final ZipLong INSTANCE5 = new ZipLong(808471376L);
   public static final ZipLong INSTANCE6 = new ZipLong(134630224L);

   public ZipLong(long var1) {
      this.longValue2 = var1;
   }

   public ZipLong(byte[] var1) {
      this(var1, 0);
   }

   public ZipLong(byte[] var1, int var2) {
      this.longValue2 = getLong(var1, var2);
   }

   public byte[] getByteArray() {
      return getByteArray(this.longValue2);
   }

   public long getLongValue2() {
      return this.longValue2;
   }

   public int getInt() {
      return (int)this.longValue2;
   }

   public static final byte[] getByteArray(long var0) {
      byte[] var2 = new byte[4];
      handleLong(var0, var2, 0);
      return var2;
   }

   public static final void handleLong(long var0, byte[] var2, int var3) {
      ByteUtils.handleByteArray(var2, var0, var3, 4);
   }

   public static final long getLong(byte[] var0, int var1) {
      return ByteUtils.getLong(var0, var1, 4);
   }

   public static final long getLong(byte[] var0) {
      return getLong(var0, 0);
   }

   @Override
   public boolean equals(Object var1) {
      return var1 != null && var1 instanceof ZipLong ? this.longValue2 == ((ZipLong)var1).getLongValue2() : false;
   }

   @Override
   public int hashCode() {
      return (int)this.longValue2;
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
      return "ZipLong value: " + this.longValue2;
   }
}
