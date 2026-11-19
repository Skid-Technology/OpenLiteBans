package litebans.util;

import org.jetbrains.annotations.NotNull;

public final class Formatter {
   public static final FormatterCompanion Companion = new FormatterCompanion(null);

   public static final String getString(@NotNull Object[] var0, @NotNull String var1) {
      return Companion.getString(var0, var1);
   }
}
