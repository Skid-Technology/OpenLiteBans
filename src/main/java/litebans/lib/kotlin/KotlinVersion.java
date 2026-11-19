package litebans.lib.kotlin;

public final class KotlinVersion implements Comparable {
   public static final KotlinVersionCompanion Companion = new KotlinVersionCompanion(null);
   private final int intValue;
   public static final int intValue2 = 0;
   public static final int intValue3 = -1;
   public static final int intValue4 = 4;
   public static final int intValue5 = 32;

   public static String getString(int var0) {
      return String.valueOf((long)var0 & 4294967295L);
   }

   @Override
   public String toString() {
      return getString(this.intValue);
   }

   public static int getInt(int var0) {
      return Integer.hashCode(var0);
   }

   @Override
   public int hashCode() {
      return getInt(this.intValue);
   }

   public static boolean checkInt(int var0, Object var1) {
      if (!(var1 instanceof KotlinVersion)) {
         return false;
      } else {
         int var2 = ((KotlinVersion)var1).getIntValue();
         return var0 == var2;
      }
   }

   @Override
   public boolean equals(Object var1) {
      return checkInt(this.intValue, var1);
   }

   private KotlinVersion(int var1) {
      this.intValue = var1;
   }

   public static int getInt2(int var0) {
      return var0;
   }

   public static final KotlinVersion getKotlinVersion(int var0) {
      return new KotlinVersion(var0);
   }

   public final int getIntValue() {
      return this.intValue;
   }

   @Override
   public int compareTo(Object var1) {
      int var3 = ((KotlinVersion)var1).getIntValue();
      return IntCompare.getInt(this.getIntValue(), var3);
   }
}
