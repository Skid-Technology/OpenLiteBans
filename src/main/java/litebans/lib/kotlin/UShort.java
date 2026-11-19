package litebans.lib.kotlin;

import litebans.lib.kotlin.jvm.internal.Intrinsics;

public final class UShort implements Comparable {
   public static final UShortCompanion Companion = new UShortCompanion(null);
   private final short shortValue;
   public static final short shortValue2 = 0;
   public static final short shortValue3 = -1;
   public static final int intValue = 2;
   public static final int intValue2 = 16;

   public static String getString(short var0) {
      return String.valueOf(var0 & '\uffff');
   }

   @Override
   public String toString() {
      return getString(this.shortValue);
   }

   public static int getInt(short var0) {
      return Short.hashCode(var0);
   }

   @Override
   public int hashCode() {
      return getInt(this.shortValue);
   }

   public static boolean checkShort(short var0, Object var1) {
      if (!(var1 instanceof UShort)) {
         return false;
      } else {
         short var2 = ((UShort)var1).getShortValue();
         return var0 == var2;
      }
   }

   @Override
   public boolean equals(Object var1) {
      return checkShort(this.shortValue, var1);
   }

   private UShort(short var1) {
      this.shortValue = var1;
   }

   public static short getShort(short var0) {
      return var0;
   }

   public static final UShort getUShort(short var0) {
      return new UShort(var0);
   }

   public final short getShortValue() {
      return this.shortValue;
   }

   @Override
   public int compareTo(Object var1) {
      short var3 = ((UShort)var1).getShortValue();
      return Intrinsics.getInt(this.getShortValue() & 65535, var3 & 65535);
   }
}
