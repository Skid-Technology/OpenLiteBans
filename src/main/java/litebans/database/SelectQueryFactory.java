package litebans.database;

import litebans.lib.kotlin.jvm.internal.DefaultConstructorMarker;
import litebans.punishment.IdType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class SelectQueryFactory {
   private SelectQueryFactory() {
   }

   public final SelectQuery getSelectQuery(
      @NotNull PunishmentStorage var1,
      @Nullable String var2,
      @NotNull String var3,
      @NotNull IdType var4,
      boolean var5,
      @Nullable String var6,
      boolean var7,
      @NotNull Number var8,
      @NotNull Number var9
   ) {
      return new SelectQuery(var1, var2, var4, var5, new ServerScopeFilter(var1.getPlatform(), var3), var6, var7, var8, var9);
   }

   public static SelectQuery getSelectQueryDefault(
      SelectQueryFactory var0,
      PunishmentStorage var1,
      String var2,
      String var3,
      IdType var4,
      boolean var5,
      String var6,
      boolean var7,
      Number var8,
      Number var9,
      int var10,
      Object var11
   ) {
      if ((var10 & 4) != 0) {
         var4 = IdType.UUID;
      }

      if ((var10 & 8) != 0) {
         var5 = false;
      }

      if ((var10 & 16) != 0) {
         var6 = null;
      }

      if ((var10 & 32) != 0) {
         var7 = true;
      }

      if ((var10 & 64) != 0) {
         var8 = 0;
      }

      if ((var10 & 128) != 0) {
         var9 = 0;
      }

      return var0.getSelectQuery(var1, var2, var3, var4, var5, var6, var7, var8, var9);
   }

   public SelectQueryFactory(DefaultConstructorMarker var1) {
      this();
   }
}
