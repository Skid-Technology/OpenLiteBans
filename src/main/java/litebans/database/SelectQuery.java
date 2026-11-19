package litebans.database;

import java.sql.ResultSet;
import litebans.lib.kotlin.jvm.internal.DefaultConstructorMarker;
import litebans.lib.kotlin.jvm.internal.Function1;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.text.StringsKt;
import litebans.punishment.IdType;
import litebans.punishment.PunishmentType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SelectQuery implements CharSequence {
   public static final SelectQueryFactory selectQueryFactory = new SelectQueryFactory(null);
   private final PunishmentStorage punishmentStorage;
   private final String string;
   private final IdType idType;
   private final boolean flag;
   private final ServerScopeFilter serverScopeFilter;
   private final String string2;
   private final boolean flag2;
   private final Number number;
   private final Number number2;
   private Function1 function1;

   public SelectQuery(
      @NotNull PunishmentStorage var1,
      @Nullable String var2,
      @NotNull IdType var3,
      boolean var4,
      @NotNull ServerScopeFilter var5,
      @Nullable String var6,
      boolean var7,
      @NotNull Number var8,
      @NotNull Number var9
   ) {
      this.punishmentStorage = var1;
      this.string = var2;
      this.idType = var3;
      this.flag = var4;
      this.serverScopeFilter = var5;
      this.string2 = var6;
      this.flag2 = var7;
      this.number = var8;
      this.number2 = var9;
      this.function1 = SqlStorage.sqlWhereBuilder.getFunction12();
   }

   public SelectQuery(
      PunishmentStorage var1,
      String var2,
      IdType var3,
      boolean var4,
      ServerScopeFilter var5,
      String var6,
      boolean var7,
      Number var8,
      Number var9,
      int var10,
      DefaultConstructorMarker var11
   ) {
      this(var1, var2, ((var10 & 4) != 0 ? IdType.UUID : var3), ((var10 & 8) != 0 ? false : var4), var5, ((var10 & 32) != 0 ? null : var6), ((var10 & 64) != 0 ? true : var7), ((var10 & 128) != 0 ? 0 : var8), ((var10 & 256) != 0 ? 0 : var9));
   }

   public final String getString() {
      return this.string;
   }

   public final Function1 getFunction1() {
      return this.function1;
   }

   public final void setFunction1(@NotNull Function1 var1) {
      this.function1 = var1;
   }

   public int getInt() {
      return this.toString().length();
   }

   public char getChar(int var1) {
      return this.toString().charAt(var1);
   }

   @Override
   public CharSequence subSequence(int var1, int var2) {
      return this.toString().subSequence(var1, var2);
   }

   @Override
   public String toString() {
      this.run();
      CharSequence var1 = "";
      var1 = this.getCharSequence2(this.getCharSequence(this.getCharSequence3(var1)));
      if (this.string2 != null) {
         String var2 = this.flag2 ? "DESC" : "ASC";
         CharSequence var4 = " ORDER BY " + this.string2 + ' ' + var2;
         var1 = "" + var1 + var4;
      }

      Number var6 = this.number;
      byte var3 = 0;
      if (Intrinsics.getInt(var6.intValue(), var3) > 0) {
         CharSequence var7 = " LIMIT ? OFFSET ?";
         var1 = "" + var1 + var7;
         this.function1 = SqlStorage.sqlWhereBuilder.getFunction12(this.function1, (Function1) qs -> getQueryStatement(this, (QueryStatement) qs));
      }

      return var1.toString();
   }

   private final CharSequence getCharSequence(CharSequence var1) {
      if (this.flag) {
         this.function1 = SqlStorage.sqlWhereBuilder.getFunction12(this.function1, (Function1) qs -> getQueryStatement((QueryStatement) qs));
         return this.getCharSequence(var1, "(active={true} AND(until<1 OR until>?))");
      } else {
         return var1;
      }
   }

   private final CharSequence getCharSequence2(CharSequence var1) {
      this.function1 = SqlStorage.sqlWhereBuilder.getFunction12(this.function1, (Function1) qs -> getQueryStatement2(this, (QueryStatement) qs));
      CharSequence var3 = this.serverScopeFilter;
      return "" + var1 + var3;
   }

   public void run() {
      if (!Intrinsics.checkObject(this.function1, SqlStorage.sqlWhereBuilder.getFunction1())) {
         this.function1 = SqlStorage.sqlWhereBuilder.getFunction12();
      }
   }

   public CharSequence getCharSequence3(@NotNull CharSequence var1) {
      if (this.string == null) {
         return var1;
      } else {
         switch (SelectQueryWhenMappings.switchMap[this.idType.ordinal()]) {
            case 1:
               return this.getCharSequence(this.string, "uuid", var1);
            case 2:
               return this.getCharSequence(this.string, "banned_by_uuid", var1);
            case 3:
               String var10001 = this.getString();
               if (var10001 != null) {
                  Long var3 = StringsKt.getLong(var10001);
                  if (var3 != null) {
                     return this.getCharSequence(var3, "id", var1);
                  }
               }

               return this.getCharSequence(-1L, "id", var1);
            default:
               return this.getCharSequence(var1, '(' + this.string + ')');
         }
      }
   }

   private final CharSequence getCharSequence(String var1, String var2, CharSequence var3) {
      this.function1 = SqlStorage.sqlWhereBuilder.getFunction12(this.function1, (Function1) qs -> getQueryStatement(var1, (QueryStatement) qs));
      return this.getCharSequence(var3, var2 + "=?");
   }

   private final CharSequence getCharSequence(long var1, String var3, CharSequence var4) {
      this.function1 = SqlStorage.sqlWhereBuilder.getFunction12(this.function1, (Function1) qs -> getQueryStatement(var1, (QueryStatement) qs));
      return this.getCharSequence(var4, var3 + "=?");
   }

   public final CharSequence getCharSequence(@NotNull CharSequence var1, @NotNull CharSequence var2) {
      return var1.length() == 0 ? var2 : (var2.length() == 0 ? var1 : var1 + " AND " + var2);
   }

   public final ResultSet getResultSet(@NotNull PunishmentType var1, @NotNull CharSequence var2) {
      return this.getResultSet(var1.getTable(), var2);
   }

   public static ResultSet getResultSetDefault(SelectQuery var0, PunishmentType var1, CharSequence var2, int var3, Object var4) {
      if (var4 != null) {
         throw new UnsupportedOperationException("");
      } else {
         if ((var3 & 2) != 0) {
            var2 = "*";
         }

         return var0.getResultSet(var1, var2);
      }
   }

   public final ResultSet getResultSet(@NotNull Table var1, @NotNull CharSequence var2) {
      if (this.punishmentStorage instanceof NullStorage) {
         return EmptyResultSetHolder.getEmptyResultSet();
      } else {
         PunishmentStorage var10000 = this.punishmentStorage;
         Intrinsics.handleObject(this.punishmentStorage, "");
         return QueryRunner.getResultSet(
            ((SqlStorage)var10000).prepare(SqlString.getSqlString(SqlString.getString2("SELECT " + var2 + " FROM " + var1 + " WHERE " + this))), this
         );
      }
   }

   private static final QueryStatement getQueryStatement(SelectQuery var0, QueryStatement var1) {
      QueryStatement var2 = var1;
      Number[] var3 = new Number[]{var0.number, var0.number2};
      Number[] var8 = var3;
      int var10 = 0;

      for (int var11 = var3.length; var10 < var11; var10++) {
         Number var12 = var8[var10];
         var2.getQueryStatement(var12);
      }

      return var1;
   }

   private static final QueryStatement getQueryStatement(QueryStatement var0) {
      return QueryRunner.getQueryStatement(var0);
   }

   private static final QueryStatement getQueryStatement2(SelectQuery var0, QueryStatement var1) {
      ServerScopeFilter var3 = var0.serverScopeFilter;
      var3.handleQueryStatement(var1);
      return var1;
   }

   private static final QueryStatement getQueryStatement(String var0, QueryStatement var1) {
      return var1.getQueryStatement(var0);
   }

   private static final QueryStatement getQueryStatement(long var0, QueryStatement var2) {
      return var2.getQueryStatement(var0);
   }

   @Override
   public final int length() {
      return this.getInt();
   }

   @Override
   public final char charAt(int var1) {
      return this.getChar(var1);
   }
}
