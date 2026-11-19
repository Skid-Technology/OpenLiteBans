package litebans.punishment;

import litebans.lib.kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;

public final class FlaggedValue {
   private final Object object;
   private boolean flag;

   public FlaggedValue(@NotNull Object var1, boolean var2) {
      this.object = var1;
      this.flag = var2;
   }

   public FlaggedValue(Object var1, boolean var2, int var3, DefaultConstructorMarker var4) {
      this(var1, ((var3 & 2) != 0 ? false : var2));
   }

   public final boolean isFlag() {
      return this.flag;
   }

   public final void setFlag(boolean var1) {
      this.flag = var1;
   }
}
