package litebans.lib.kotlin.text;

import org.jetbrains.annotations.NotNull;

public final class RegexReplace {
   public static final String getString(@NotNull Regex var0, @NotNull CharSequence var1, @NotNull String var2) {
      return var0.getNativePattern().matcher(var1).replaceAll(var2);
   }
}
