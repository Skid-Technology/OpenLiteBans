package litebans.database;

import litebans.lib.kotlin.jvm.internal.DefaultConstructorMarker;
import litebans.lib.kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

public final class SqlColumnFormats {
   private SqlColumnFormats() {
   }

   public final SqlColumnFormat getSqlColumnFormat(@NotNull String var1, @NotNull String var2) {
      return DatabaseManager.databaseDriver.checkString(var1)
         ? new SqlColumnFormat(
            var1, StringsKt.checkCharSequence(var2) ? "" : "ENGINE=" + var2, " CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci", " UNSIGNED"
         )
         : new SqlColumnFormat(var1, "", "", " ");
   }

   public SqlColumnFormats(DefaultConstructorMarker var1) {
      this();
   }
}
