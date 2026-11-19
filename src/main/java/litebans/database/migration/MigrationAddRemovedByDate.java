package litebans.database.migration;

import litebans.database.SqlString;
import litebans.database.Table;
import litebans.lib.kotlin.jvm.internal.Function1;

public final class MigrationAddRemovedByDate implements Function1 {
   public static final MigrationAddRemovedByDate INSTANCE = new MigrationAddRemovedByDate();

   public final String getString(Table var1) {
      return SqlString.getString6(SqlString.getString2("ALTER TABLE " + var1), "removed_by_date TIMESTAMP");
   }

   @Override
   public Object invoke(Object var1) {
      return SqlString.getSqlString(this.getString((Table)var1));
   }
}
