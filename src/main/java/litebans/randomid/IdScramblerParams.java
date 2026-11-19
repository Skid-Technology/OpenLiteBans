package litebans.randomid;

import litebans.lib.kotlin.jvm.internal.DefaultConstructorMarker;

public final class IdScramblerParams {
   private final int intValue;
   private final int intValue2;
   private final int intValue3;

   public IdScramblerParams(int var1, int var2, int var3) {
      this.intValue = var1;
      this.intValue2 = var2;
      this.intValue3 = var3;
   }

   public IdScramblerParams(int var1, int var2, int var3, int var4, DefaultConstructorMarker var5) {
      this(((var4 & 1) != 0 ? 0 : var1), ((var4 & 2) != 0 ? 0 : var2), ((var4 & 4) != 0 ? 0 : var3));
   }

   public final int getIntValue() {
      return this.intValue;
   }

   public final int getIntValue2() {
      return this.intValue2;
   }

   public final int getIntValue3() {
      return this.intValue3;
   }

   public IdScramblerParams() {
      this(0, 0, 0, 7, null);
   }
}
