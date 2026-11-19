package litebans.database;

import java.sql.PreparedStatement;
import litebans.lib.kotlin.jvm.internal.DefaultConstructorMarker;

public final class StorageStatement extends QueryStatement {
   private StorageStatement(PunishmentStorage var1, PreparedStatement var2, String var3) {
      super(var1, var2, var3, null);
   }

   public StorageStatement getStorageStatement(boolean var1) {
      QueryStatement var5 = this;
      int var6 = var1 ? 49 : 48;
      var5.getQueryStatement(String.valueOf((char)var6));
      return this;
   }

   @Override
   public QueryStatement getQueryStatement(boolean var1) {
      return this.getStorageStatement(var1);
   }

   public StorageStatement(PunishmentStorage var1, PreparedStatement var2, String var3, DefaultConstructorMarker var4) {
      this(var1, var2, var3);
   }
}
