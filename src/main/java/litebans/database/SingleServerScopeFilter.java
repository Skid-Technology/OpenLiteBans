package litebans.database;

import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.platform.LiteBansPlatform;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class SingleServerScopeFilter extends ServerScopeFilter {
   private final boolean flag;

   public SingleServerScopeFilter(@NotNull LiteBansPlatform var1, @Nullable String var2, boolean var3) {
      super(var1, var2);
      this.flag = var3;
   }

   @Override
   public void handleQueryStatement(@NotNull QueryStatement var1) {
      if (!Intrinsics.checkObject(this.getString(), "__ALL__")) {
         var1.getQueryStatement(this.getString());
      }
   }

   @Override
   public String toString() {
      if (Intrinsics.checkObject(this.getString(), "__ALL__")) {
         return "";
      } else {
         return this.flag ? " AND(server_scope=?)" : "server_scope=?";
      }
   }
}
