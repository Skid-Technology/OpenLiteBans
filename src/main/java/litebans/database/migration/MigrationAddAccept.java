package litebans.database.migration;

import litebans.database.SqlString;
import litebans.database.Table;
import litebans.database.TableCreator;
import litebans.lib.kotlin.jvm.internal.Function1;
import litebans.lib.kotlin.jvm.internal.Intrinsics;

public final class MigrationAddAccept implements Function1 {
   public final TableCreator tableCreator;

   public MigrationAddAccept(TableCreator var1) {
      this.tableCreator = var1;
   }

   public final String getString(Table var1) {
      String var10000 = SqlString.getString2("ALTER TABLE " + var1);
      TableCreator var2 = this.tableCreator;
      String var3 = "accept";
      String var4 = " DEFAULT 0";
      String var6 = var2.getSqlColumnFormat().getString();
      return SqlString.getString6(
         var10000,
         !Intrinsics.checkObject(var6, "pgsql") && !Intrinsics.checkObject(var6, "h2")
            ? var3 + " TINYINT" + var2.getSqlColumnFormat().getUnsigned() + " NOT NULL" + var4
            : var3 + " SMALLINT NOT NULL" + var4
      );
   }

   @Override
   public Object invoke(Object var1) {
      return SqlString.getSqlString(this.getString((Table)var1));
   }
}
