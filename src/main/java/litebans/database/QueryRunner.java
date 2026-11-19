package litebans.database;

import java.io.Closeable;
import java.sql.ResultSet;
import litebans.core.PluginInstance;
import litebans.lib.kotlin.io.CloseableKt;
import litebans.lib.kotlin.jvm.internal.Function1;
import litebans.lib.kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

public final class QueryRunner {
   public static final ResultSet getResultSet(@NotNull QueryStatement var0) {
      DatabaseManager var1 = var0.getPunishmentStorage().getDatabaseManager();
      ResultSet var10000;
      if (var0.getString() == null) {
         var10000 = var1.executeQuery(var0);
      } else {
         String var2 = var0.getString();
         if (StringsKt.checkString(var2, "SELECT ", false, 2, null)) {
            var10000 = var1.execute(var0);
         } else {
            var2 = var0.getString();
            var10000 = StringsKt.checkString(var2, "INSERT ", false, 2, null)
                  || StringsKt.checkString(var2, "DELETE ", false, 2, null)
                  || StringsKt.checkString(var2, "UPDATE ", false, 2, null)
                  || StringsKt.checkString(var2, "ALTER ", false, 2, null)
                  || StringsKt.checkString(var2, "CREATE ", false, 2, null)
               ? var1.getEmptyResultSet(var0)
               : var1.executeQuery(var0);
         }
      }

      return var10000;
   }

   public static final ResultSet getResultSet(@NotNull QueryStatement var0, @NotNull SelectQuery var1) {
      Function1 var3 = var1.getFunction1();
      return getResultSet((QueryStatement)var3.invoke(var0));
   }

   public static final ResultSet getResultSet(@NotNull QueryStatement var0, @NotNull Function1 var1) {
      return getResultSet((QueryStatement)var1.invoke(var0));
   }

   public static final ResultSet getResultSet2(@NotNull QueryStatement var0) {
      DatabaseManager var1 = (DatabaseManager)PluginInstance.Companion.getLiteBansPlatform().getModule(DatabaseManager.class);
      return var1.execute(var0);
   }

   public static final ResultSet getResultSet2(@NotNull QueryStatement var0, @NotNull Function1 var1) {
      return getResultSet2((QueryStatement)var1.invoke(var0));
   }

   public static final int getInt(@NotNull QueryStatement var0) {
      DatabaseManager var1 = (DatabaseManager)PluginInstance.Companion.getLiteBansPlatform().getModule(DatabaseManager.class);
      Closeable var2 = var0;
      Throwable var3 = null;

      int var8;
      try {
         var8 = var1.executeUpdate(var0);
      } catch (Throwable var11) {
         var3 = var11;
         throw var11;
      } finally {
         CloseableKt.handleCloseable(var2, var3);
      }

      return var8;
   }

   public static final QueryStatement getQueryStatement(@NotNull QueryStatement var0) {
      return var0.getQueryStatement(DatabaseManager.currentTimeMillisDefault(var0.getPunishmentStorage().getDatabaseManager(), false, 1, null));
   }
}
