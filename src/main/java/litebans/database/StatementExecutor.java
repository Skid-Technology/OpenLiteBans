package litebans.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import litebans.core.LiteBansCore;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.text.StringsKt;
import litebans.platform.LiteBansPlatform;
import org.jetbrains.annotations.NotNull;

public class StatementExecutor {
   public static final StatementExecutorCompanion Companion = new StatementExecutorCompanion(null);
   private final LiteBansPlatform liteBansPlatform;
   public static final int intValue = 5;

   public StatementExecutor(@NotNull LiteBansPlatform var1) {
      this.liteBansPlatform = var1;
   }

   public ResultSet executeQuery(@NotNull QueryStatement var1) {
      try {
      if (var1.getPunishmentStorage().isClosed()) {
         return EmptyResultSetHolder.getEmptyResultSet();
      } else {
         ResultSet var3 = !var1.getPreparedStatement().isClosed() && var1.getPreparedStatement().execute() ? var1.getPreparedStatement().getResultSet() : null;
         ResultSet var10000 = var3;
         if (var3 == null) {
            var10000 = EmptyResultSetHolder.getEmptyResultSet();
         }

         return var10000;
      }
      } catch (SQLException var4) {
         throw litebans.util.Unreachable.rethrow(var4);
      }
   }

   public ResultSet execute(@NotNull QueryStatement var1) {
      try {
      if (var1.getPunishmentStorage().isClosed()) {
         return EmptyResultSetHolder.getEmptyResultSet();
      } else {
         ResultSet var10000;
         if (!var1.getPreparedStatement().isClosed()) {
            ResultSet var4 = var1.getPreparedStatement().executeQuery();
            var10000 = var4;
            if (var4 == null) {
               var10000 = EmptyResultSetHolder.getEmptyResultSet();
            }
         } else {
            var10000 = null;
         }

         ResultSet var3 = var10000;
         var10000 = var3;
         if (var3 == null) {
            var10000 = EmptyResultSetHolder.getEmptyResultSet();
         }

         return var10000;
      }
      } catch (SQLException var5) {
         throw litebans.util.Unreachable.rethrow(var5);
      }
   }

   public int executeUpdate(@NotNull QueryStatement var1) {
      try {
      if (var1.getPunishmentStorage().isClosed()) {
         return -1;
      } else {
         int var2 = 0;

         while (!var1.getPreparedStatement().isClosed()) {
            try {
               return var1.getPreparedStatement().executeUpdate();
            } catch (Exception var6) {
               var2++;
               if (var2 > 5 || !this.checkInt(var2, var6)) {
                  throw litebans.util.Unreachable.rethrow(var6);
               }
            }
         }

         return -1;
      }
      } catch (SQLException var7) {
         throw litebans.util.Unreachable.rethrow(var7);
      }
   }

   public int[] executeBatch(@NotNull Statement var1) {
      try {
      int[] var10000;
      if (!var1.isClosed()) {
         int[] var2 = var1.executeBatch();
         Intrinsics.handleObject(var2);
         var10000 = var2;
      } else {
         var10000 = new int[0];
      }

      return var10000;
      } catch (SQLException var3) {
         throw litebans.util.Unreachable.rethrow(var3);
      }
   }

   public String getString(@NotNull String var1) {
      return var1;
   }

   private final boolean checkInt(int var1, Throwable var2) {
      LiteBansCore var3 = (LiteBansCore)this.liteBansPlatform.getModule(LiteBansCore.class);
      String var10000 = var2.getMessage();
      if (var10000 == null) {
         var10000 = "";
      }

      boolean var5;
      if (StringsKt.checkCharSequence3(var10000, "Deadlock found when trying to get lock; try restarting transaction", false, 2, null)) {
         int var4 = var1 * var1 * 101;
         var3.debug("executeUpdate() failed due to deadlock. Wait " + var4 + " ms before retrying... (" + var1 + "/5)");
         try {
            Thread.sleep((long)var4);
         } catch (InterruptedException var6) {
            Thread.currentThread().interrupt();
         }
         var5 = true;
      } else {
         var5 = false;
      }

      return var5;
   }
}
