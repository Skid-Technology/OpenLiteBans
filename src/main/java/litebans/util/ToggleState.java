package litebans.util;

import litebans.lib.kotlin.jvm.internal.DefaultConstructorMarker;

public final class ToggleState {
   private boolean flag;
   private boolean flag2;

   public ToggleState(boolean var1, boolean var2) {
      this.flag = var1;
      this.flag2 = var2;
   }

   public ToggleState(boolean var1, boolean var2, int var3, DefaultConstructorMarker var4) {
      this(((var3 & 1) != 0 ? false : var1), ((var3 & 2) != 0 ? false : var2));
   }

   public final boolean isFlag() {
      return this.flag;
   }

   public final void setFlag(boolean var1) {
      this.flag = var1;
   }

   public final boolean isFlag2() {
      return this.flag2;
   }

   public ToggleState() {
      this(false, false, 3, null);
   }
}
