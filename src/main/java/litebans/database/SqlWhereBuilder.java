package litebans.database;

import litebans.lib.kotlin.jvm.internal.DefaultConstructorMarker;
import litebans.lib.kotlin.jvm.internal.Function1;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class SqlWhereBuilder {
   private SqlWhereBuilder() {
   }

   public final Function1 getFunction1() {
      return SqlStorage.getFunction1();
   }

   public final int getInt() {
      return SqlStorage.getIntValue5();
   }

   public final void handleInt(int var1) {
      SqlStorage.handleInt(var1);
   }

   public final Function1 getFunction12() {
      return this.getFunction1();
   }

   public final Function1 getFunction1(@NotNull Function1 var1) {
      return var1;
   }

   public final Function1 getFunction1(@NotNull Function1 var1, @NotNull Function1 var2) {
      return qs -> getQueryStatement(var1, var2, (QueryStatement) qs);
   }

   public final Function1 getFunction12(@NotNull Function1 var1, @NotNull Function1 var2) {
      return Intrinsics.checkObject(var1, this.getFunction1()) ? var2 : this.getFunction1(var1, var2);
   }

   public final String getString(@NotNull SqlStorage var1, @NotNull String var2) {
      boolean var4 = Intrinsics.checkObject(var2, "name");
      StringBuilder var10000 = new StringBuilder().append(var2);
      CharSequence var10001;
      if (var4) {
         CharSequence var5 = var1.getNotificationManager().getCharSequence2();
         CharSequence var6 = "?";
         var10001 = "" + var5 + var6;
      } else {
         var10001 = "=?";
      }

      String var3 = var10000.append((Object)var10001).toString();
      if (var4) {
         var3 = var3 + var1.getNotificationManager().getCharSequence4();
      }

      return (String)var3;
   }

   private static final QueryStatement getQueryStatement(Function1 var0, Function1 var1, QueryStatement var2) {
      var0.invoke(var2);
      return (QueryStatement)var1.invoke(var2);
   }

   public SqlWhereBuilder(DefaultConstructorMarker var1) {
      this();
   }
}
