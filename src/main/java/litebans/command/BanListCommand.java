package litebans.command;

import java.io.Closeable;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import litebans.config.Message;
import litebans.core.LiteBansCore;
import litebans.database.DatabaseManager;
import litebans.database.EntryReader;
import litebans.database.PunishmentStorage;
import litebans.database.Table;
import litebans.lib.kotlin.io.CloseableKt;
import litebans.platform.LiteBansPlatform;
import litebans.platform.Sender;
import litebans.punishment.EntryPlaceholders;
import litebans.punishment.HistoryPaginator;
import litebans.punishment.PlayerHistory;
import litebans.punishment.PunishmentService;
import litebans.punishment.PunishmentType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BanListCommand extends BaseCommand implements PagedCommand {
   public BanListCommand(@NotNull LiteBansPlatform var1) {
      super("banlist", var1);
   }

   @Override
   public boolean isArgsRequired() {
      return false;
   }

   @Override
   public CharSequence getCharSequence() {
      return "";
   }

   public PunishmentType getPunishmentType() {
      return PunishmentType.BAN;
   }

   @Override
   public void execute(@NotNull Sender var1, @NotNull String[] var2) {
      CommandRunnable var3 = this;
      DatabaseManager var37 = (DatabaseManager)var3.getPlatform().getModule(DatabaseManager.class);

      try {
         PunishmentStorage var5 = var37.openStorage();
         Closeable var6 = var5;
         Throwable var7 = null;

         try {
            Table var10001 = this.getPunishmentType().getTable();
            CommandRunnable var12 = this;
            double var16 = (double)EntryReader.getLong(
               var5, var10001, null, ((LiteBansCore)var12.getPlatform().getModule(LiteBansCore.class)).getConfig().isBanlistShowActiveOnly(), 2, null
            );
            CommandRunnable var14 = this;
            double var20 = (double)((LiteBansCore)var14.getPlatform().getModule(LiteBansCore.class)).getConfig().getBanlistBansPerPage();
            double var22 = Math.floor(var16 / var20);
            double var24 = var22;
            double var26 = ArgParser.getDouble(var2, 0, var22);
            var26 = Math.min(var22, var26);
            if (var26 > 0.0 && var26 == var22 && (int)var16 % (int)var20 == 0) {
               var26 += -1.0;
               var24 = var22 + -1.0;
            }

            PunishmentService.reply(
               this,
               EntryPlaceholders.getString(EntryPlaceholders.getString(var5.getCharSequence(Message.BANLIST_START), "page", (int)var26), "total", (int)var24)
            );
            PunishmentType var39 = this.getPunishmentType();
            long var10002 = (long)var26;
            long var10003 = (long)var20;
            CommandRunnable var28 = this;
            Collection var30 = var5.findEntriesInRange(
               var39, var10002, var10003, ((LiteBansCore)var28.getPlatform().getModule(LiteBansCore.class)).getConfig().isBanlistShowActiveOnly()
            );
            HistoryPaginator.handlePagedCommand(this, var5, var30, null, null, null, 0, 30, null);
         } catch (Throwable var34) {
            var7 = var34;
            throw var34;
         } finally {
            CloseableKt.handleCloseable(var6, var7);
         }
      } catch (Exception var36) {
         if (!(var36 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var36); }
         if (!var37.isConnectionError(var36)) {
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
