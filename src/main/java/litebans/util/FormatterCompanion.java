package litebans.util;

import litebans.lib.kotlin.collections.ArraysKt;
import litebans.lib.kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;

public final class FormatterCompanion {
   private FormatterCompanion() {
   }

   public final String getString(@NotNull Object[] var1, @NotNull String var2) {
      return ArraysKt.getString(var1, var2, null, null, 0, null, null, 62, null);
   }

   public FormatterCompanion(DefaultConstructorMarker var1) {
      this();
   }
}
