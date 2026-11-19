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

public final class StaffHistoryCommand extends BaseCommand implements PagedCommand {
   public StaffHistoryCommand(@NotNull LiteBansPlatform var1) {
      super("staffhistory", var1);
   }

   @Override
   public CharSequence getCharSequence() {
      return Message.STAFFHISTORY_START;
   }

   @Override
   public void execute(@NotNull Sender var1, @NotNull String[] var2) {
      int var3 = HistoryPaginator.getInt(this, this.getPlatform(), var2, CollectionsKt.getList(), 0, 8, null);
      CommandRunnable var5 = this;
      String var4 = PunishmentService.lookupUuid(var5, (String)ArraysKt.getObject(var2));
      var5 = this;
      DatabaseManager var38 = (DatabaseManager)var5.getPlatform().getModule(DatabaseManager.class);

      try {
         PunishmentStorage var7 = var38.openStorage();
         Closeable var8 = var7;
         Throwable var9 = null;

         try {
            PunishmentStorage var12 = var7;
            PlayerHistory var14 = PunishmentService.resolveTarget(var7, var4, false);
            String var15 = (String)ArraysKt.getObject(var2, 1);
            PunishmentType var16 = PunishmentType.Companion.getPunishmentType(var15);
            String var10000 = var14.getUuid();
            if (var10000 == null) {
               CommandRunnable.messageSender.getVoid(Message.HISTORY_ERROR_NO_LOGINRECORD);
               throw new NoWhenBranchMatchedException();
            }

            String var17 = var10000;
            String var18 = PunishmentService.parseServerScopeDefault(this, var1, var2, null, 4, null);
            List var19 = var16 != null ? CollectionsKt.getList(var16) : PunishmentType.Companion.getList();
            Iterable var20 = var19;
            Collection var23 = new ArrayList();

            for (Object var26 : var20) {
               PunishmentType var27 = (PunishmentType)var26;
               Iterable var29 = var12.getList(var17, var27, var18, var3);
               CollectionsKt.checkCollection(var23, var29);
            }

            List var30 = CollectionsKt.getList2((List)var23);
            HistoryPaginator.handlePagedCommand(this, var12, var30, var14, var2, null, 0, 24, null);
         } catch (Throwable var34) {
            var9 = var34;
            throw var34;
         } finally {
            CloseableKt.handleCloseable(var8, var9);
         }
      } catch (Exception var36) {
         if (!(var36 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var36); }
         if (!var38.isConnectionError(var36)) {
            throw var36;
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
