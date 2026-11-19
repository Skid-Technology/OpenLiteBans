package litebans.database;

import org.jetbrains.annotations.NotNull;

public final class SqlTrue {
   public static final String getString(@NotNull String var0, @NotNull String var1) {
      SqlFalse.handleCharSequence(var1);
      return SqlString.getString4(var0, var1 + "={true}");
   }
}
