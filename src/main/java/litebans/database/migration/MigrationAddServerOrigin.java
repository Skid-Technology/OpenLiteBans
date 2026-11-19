package litebans.database.migration;

import litebans.database.DatabaseManager;
import litebans.database.SqlString;
import litebans.database.Table;
import litebans.database.TableCreator;
import litebans.lib.kotlin.jvm.internal.Function1;

public final class MigrationAddServerOrigin implements Function1 {
   public final TableCreator tableCreator;

   public MigrationAddServerOrigin(TableCreator var1) {
      this.tableCreator = var1;
   }

   public final String getString(Table var1) {
      String var10000 = SqlString.getString2("ALTER TABLE " + var1);
      TableCreator var2 = this.tableCreator;
      String var3 = "server_origin";
      byte var4 = 32;
      String var5 = "";
      String var6 = var2.getSqlColumnFormat().getCharset();
      return SqlString.getString6(
         var10000,
         DatabaseManager.databaseDriver.checkString(var2.getSqlColumnFormat().getString())
            ? var3 + " VARCHAR(" + var4 + ')' + var6 + var5
            : var3 + " VARCHAR(" + var4 + ')' + var5
      );
   }

   @Override
   public Object invoke(Object var1) {
      return SqlString.getSqlString(this.getString((Table)var1));
   }
}
