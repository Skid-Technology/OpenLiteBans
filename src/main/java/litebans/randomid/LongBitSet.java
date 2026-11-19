package litebans.randomid;

import litebans.lib.kotlin.jvm.internal.DefaultConstructorMarker;

public class LongBitSet {
   private final long[] longArray;

   public LongBitSet(long var1) {
      long[] var3 = new long[]{var1, var1};
      this.longArray = var3;
   }

   public LongBitSet(long var1, int var3, DefaultConstructorMarker var4) {
      this(((var3 & 1) != 0 ? System.nanoTime() : var1));
   }

   public final long[] getLongArray() {
      return this.longArray;
   }

   public final void handleLong(long var1) {
      this.handleLong(var1, var1);
   }

   public final void handleLong(long var1, long var3) {
      this.longArray[0] = var1;
      this.longArray[1] = var3;
   }

   public final long getLong() {
      long var1 = this.longArray[0];
      long var3 = this.longArray[1];
      var1 ^= var1 << 23;
      var1 = var1 ^ var3 ^ var1 >> 17 ^ var3 >> 26;
      this.handleLong(var3, var1);
      return var1 + var3;
   }

   public int getInt(int var1) {
      if (var1 <= 0) {
         String var2 = String.valueOf(var1);
         throw new IllegalArgumentException(var2.toString());
      } else {
         return (int)Math.abs(this.getLong() % (long)var1);
      }
   }

   public final int getInt2(int var1) {
      return (int)Math.abs(this.getLong() % (long)var1);
   }

   public final boolean check() {
      return this.getInt2(2) == 0;
   }

   public LongBitSet() {
      this(0L, 1, null);
   }
}
