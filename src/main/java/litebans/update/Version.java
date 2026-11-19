package litebans.update;

public final class Version {
   public static final VersionParser versionParser = new VersionParser(null);
   private final int intValue;
   private final int intValue2;
   private final int intValue3;

   public Version(int var1, int var2, int var3) {
      this.intValue = var1;
      this.intValue2 = var2;
      this.intValue3 = var3;
   }

   public final boolean checkInt(int var1, int var2, int var3) {
      if (this.intValue > var1) {
         return true;
      } else if (this.intValue2 > var2) {
         return true;
      } else {
         return this.intValue2 < var2 ? false : this.intValue3 >= var3;
      }
   }

   @Override
   public String toString() {
      return this.intValue >= 100 && this.intValue2 == 0 && this.intValue3 == 0
         ? "" + '#' + this.intValue
         : "" + this.intValue + '.' + this.intValue2 + '.' + this.intValue3;
   }
}
