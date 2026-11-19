package litebans.database;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import litebans.lib.kotlin.collections.CollectionsKt;
import org.jetbrains.annotations.NotNull;

public final class TableCreator {
   private final SqlColumnFormat sqlColumnFormat;

   public TableCreator(@NotNull SqlColumnFormat var1) {
      this.sqlColumnFormat = var1;
   }

   public final SqlColumnFormat getSqlColumnFormat() {
      return this.sqlColumnFormat;
   }

   public final String getString(@NotNull Table var1, @NotNull String[] var2) {
      String[] var4 = Arrays.copyOf(var2, var2.length);
      SqlColumnFormat var6 = this.getSqlColumnFormat();
      StringBuilder var10000 = new StringBuilder();
      char var9 = '(';
      StringBuilder var11 = var10000;
      String[] var13 = var4;
      Collection var14 = new ArrayList();
      int var16 = 0;

      for (int var17 = var4.length; var16 < var17; var16++) {
         String var18 = var13[var16];
         if (var18.length() > 0) {
            var14.add(var18);
         }
      }

      String var10 = CollectionsKt.getString((List)var14, ",", null, null, 0, null, null, 62, null);
      CharSequence var20 = var11.append(var9 + var10).append(')').append(var6.getEngine()).append(var6.getCharset()).toString();
      return SqlString.getString2("CREATE TABLE IF NOT EXISTS " + var1 + var20);
   }
}
