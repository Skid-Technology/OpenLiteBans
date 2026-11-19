package litebans.lib.compress.archivers.zip;

public final class GeneralPurposeBit implements Cloneable {
   private static final int intValue = 1;
   private static final int intValue2 = 2;
   private static final int intValue3 = 4;
   private static final int intValue4 = 8;
   private static final int intValue5 = 64;
   public static final int intValue6 = 2048;
   private boolean flag = false;
   private boolean flag2 = false;
   private boolean flag3 = false;
   private boolean flag4 = false;

   public boolean isFlag2() {
      return this.flag2;
   }

   public boolean isFlag3() {
      return this.flag3;
   }

   @Override
   public int hashCode() {
      return 3 * (7 * (13 * (17 * (this.flag3 ? 1 : 0) + (this.flag4 ? 1 : 0)) + (this.flag ? 1 : 0)) + (this.flag2 ? 1 : 0));
   }

   @Override
   public boolean equals(Object var1) {
      if (!(var1 instanceof GeneralPurposeBit)) {
         return false;
      } else {
         GeneralPurposeBit var2 = (GeneralPurposeBit)var1;
         return var2.flag3 == this.flag3 && var2.flag4 == this.flag4 && var2.flag == this.flag && var2.flag2 == this.flag2;
      }
   }

   @Override
   public Object clone() {
      try {
         return super.clone();
      } catch (CloneNotSupportedException var2) {
         throw new RuntimeException("GeneralPurposeBit is not Cloneable?", var2);
      }
   }
}
