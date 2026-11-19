package litebans.lib.kotlin;

import litebans.lib.kotlin.jvm.internal.Intrinsics;

public final class UByte implements Comparable {
   public static final UByteCompanion Companion = new UByteCompanion(null);
   private final byte byteValue;
   public static final byte byteValue2 = 0;
   public static final byte byteValue3 = -1;
   public static final int intValue = 1;
   public static final int intValue2 = 8;

   public static String getString(byte var0) {
      return String.valueOf(var0 & 255);
   }

   @Override
   public String toString() {
      return getString(this.byteValue);
   }

   public static int getInt(byte var0) {
      return Byte.hashCode(var0);
   }

   @Override
   public int hashCode() {
      return getInt(this.byteValue);
   }

   public static boolean checkByte(byte var0, Object var1) {
      if (!(var1 instanceof UByte)) {
         return false;
      } else {
         byte var2 = ((UByte)var1).getByteValue();
         return var0 == var2;
      }
   }

   @Override
   public boolean equals(Object var1) {
      return checkByte(this.byteValue, var1);
   }

   private UByte(byte var1) {
      this.byteValue = var1;
   }

   public static byte getByte(byte var0) {
      return var0;
   }

   public static final UByte getUByte(byte var0) {
      return new UByte(var0);
   }

   public final byte getByteValue() {
      return this.byteValue;
   }

   @Override
   public int compareTo(Object var1) {
      byte var3 = ((UByte)var1).getByteValue();
      return Intrinsics.getInt(this.getByteValue() & 0xFF, var3 & 0xFF);
   }
}
