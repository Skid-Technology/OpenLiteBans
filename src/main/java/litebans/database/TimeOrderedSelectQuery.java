package litebans.database;

import litebans.lib.kotlin.ResultFailure;
import litebans.lib.kotlin.jvm.internal.Function1;
import litebans.punishment.IdType;

public final class TimeOrderedSelectQuery extends SelectQuery {
   public final ResultFailure resultFailure;

   public TimeOrderedSelectQuery(SqlStorage var1, boolean var2, int var3, ResultFailure var4, String var5, IdType var6, ServerScopeFilter var7) {
      super(var1, var5, var6, var2, var7, "time", false, var3, null, 320, null);
      this.resultFailure = var4;
   }

   @Override
   public void run() {
      this.setFunction1(SqlStorage.sqlWhereBuilder.getFunction12(this.getFunction1(), (Function1)this.resultFailure.object));
   }
}
