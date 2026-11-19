package litebans.command;

import java.io.Closeable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import litebans.config.Message;
import litebans.database.DatabaseManager;
import litebans.database.PunishmentStorage;
import litebans.lib.kotlin.NoWhenBranchMatchedException;
import litebans.lib.kotlin.collections.ArraysKt;
import litebans.lib.kotlin.collections.CollectionsKt;
import litebans.lib.kotlin.io.CloseableKt;
import litebans.platform.LiteBansPlatform;
import litebans.platform.Sender;
import litebans.punishment.HistoryPaginator;
import litebans.punishment.PlayerHistory;
import litebans.punishment.PunishmentService;
import litebans.punishment.PunishmentType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class HistoryCommand extends BaseCommand implements PagedCommand {
   public HistoryCommand(@NotNull LiteBansPlatform var1) {
      super("history", var1);
   }

   @Override
   public CharSequence getCharSequence() {
      return Message.HISTORY_START;
   }

   @Override
   public void execute(@NotNull Sender var1, @NotNull String[] var2) {
      CommandRunnable var3 = this;
      DatabaseManager var36 = (DatabaseManager)var3.getPlatform().getModule(DatabaseManager.class);

      try {
         PunishmentStorage var5 = var36.openStorage();
         Closeable var6 = var5;
         Throwable var7 = null;

         try {
            PunishmentStorage var10 = var5;
            CommandRunnable var12 = this;
            String var14 = PunishmentService.lookupUuid(var12, (String)ArraysKt.getObject(var2));
            PlayerHistory var37 = PunishmentService.resolveTarget(var5, var14, false);
            String var13 = (String)ArraysKt.getObject(var2, 1);
            PunishmentType var15 = PunishmentType.Companion.getPunishmentType(var13);
            String var16 = PunishmentService.parseServerScopeDefault(this, var1, var2, null, 4, null);
            if (var37.getUuid() == null) {
               CommandRunnable.messageSender.getVoid(var14 + "'s uuid is null");
               throw new NoWhenBranchMatchedException();
            }

            int var17 = HistoryPaginator.getInt(this, var5.getPlatform(), var2, CollectionsKt.getList(), 0, 8, null);
            List var18 = var15 != null ? CollectionsKt.getList(var15) : PunishmentType.Companion.getList();
            Iterable var19 = var18;
            Collection var22 = new ArrayList();

            for (Object var25 : var19) {
               PunishmentType var26 = (PunishmentType)var25;
               Iterable var28 = var10.getList(var37, var26, var16, false, var17);
               CollectionsKt.checkCollection(var22, var28);
            }

            List var29 = CollectionsKt.getList2((List)var22);
            HistoryPaginator.handlePagedCommand(this, var10, var29, var37, var2, null, 0, 24, null);
         } catch (Throwable var33) {
            var7 = var33;
            throw var33;
         } finally {
            CloseableKt.handleCloseable(var6, var7);
         }
      } catch (Exception var35) {
         if (!(var35 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var35); }
         if (!var36.isConnectionError(var35)) {
            throw var35;
         }
      }
   }

   @Override
   public int parsePage(@NotNull LiteBansPlatform var1, @Nullable String[] var2, @NotNull Collection var3, int var4) {
      return HistoryPaginator.getInt(this, var1, var2, var3, var4);
   }

   @Override
   public void printPage(
      @NotNull PunishmentStorage var1, @NotNull Collection var2, @Nullable PlayerHistory var3, @Nullable String[] var4, @NotNull BaseCommand var5, int var6
   ) {
      HistoryPaginator.handlePagedCommand(this, var1, var2, var3, var4, var5, var6);
   }

   @Override
   public PlayerHistory lookupHistory(@NotNull PunishmentStorage var1, @NotNull HashMap var2, @Nullable String var3) {
      return HistoryPaginator.getPlayerHistory(this, var1, var2, var3);
   }
}
