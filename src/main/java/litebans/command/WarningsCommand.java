package litebans.command;

import java.io.Closeable;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import litebans.config.Message;
import litebans.core.MessageSender;
import litebans.database.DatabaseManager;
import litebans.database.EntryReader;
import litebans.database.PunishmentStorage;
import litebans.lib.kotlin.NoWhenBranchMatchedException;
import litebans.lib.kotlin.collections.ArraysKt;
import litebans.lib.kotlin.collections.CollectionsKt;
import litebans.lib.kotlin.io.CloseableKt;
import litebans.lib.kotlin.text.StringsKt;
import litebans.platform.LiteBansPlatform;
import litebans.platform.Sender;
import litebans.punishment.EntryPlaceholders;
import litebans.punishment.HistoryPaginator;
import litebans.punishment.PlayerHistory;
import litebans.punishment.PunishmentEntry;
import litebans.punishment.PunishmentService;
import litebans.punishment.PunishmentType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class WarningsCommand extends BaseCommand implements PagedCommand {
   public WarningsCommand(@NotNull LiteBansPlatform var1) {
      super("warnings", var1);
   }

   @Override
   public String getPermission() {
      return "litebans.warnings.self";
   }

   @Override
   public boolean isArgsRequired() {
      return false;
   }

   @Override
   public void execute(@NotNull Sender var1, @NotNull String[] var2) {
      String var3 = var1.getUuidString();
      CommandRunnable var10000 = this;
      String var10001 = (String)ArraysKt.getObject2(var2);
      if (var10001 == null) {
         var10001 = var3;
      }

      String var4 = PunishmentService.lookupUuid(var10000, var10001);
      if (!StringsKt.checkString(var4, var1.getName(), true) && !StringsKt.checkString(var4, var3, true)) {
         CommandRunnable var34 = this;
         String var37 = "litebans.warnings";
         Sender var41 = var34.getCurrentSender();
         MessageSender var43 = CommandRunnable.messageSender;
         boolean var45 = !var41.hasPermission(var37);
         CharSequence var47 = Message.ERROR_NO_PERMISSION;
         if (var45) {
            var43.getVoid(var47);
            throw new NoWhenBranchMatchedException();
         }
      } else {
         CommandRunnable var5 = this;
         String var6 = "litebans.warnings.self";
         Sender var8 = var5.getCurrentSender();
         MessageSender var10 = CommandRunnable.messageSender;
         boolean var11 = !var8.hasPermission(var6);
         CharSequence var12 = Message.ERROR_NO_PERMISSION;
         if (var11) {
            var10.getVoid(var12);
            throw new NoWhenBranchMatchedException();
         }

         MessageSender var33 = CommandRunnable.messageSender;
         boolean var36 = var1.isPlayer();
         CharSequence var7 = Message.WARNINGS_USAGE;
         boolean var42 = !var36;
         if (var42) {
            var33.getVoid(var7);
            throw new NoWhenBranchMatchedException();
         }
      }

      String var35 = PunishmentService.parseServerScopeDefault(this, var1, var2, null, 4, null);
      int var38 = this.parsePage(this.getPlatform(), var2, CollectionsKt.getList(), 512);
      CommandRunnable var39 = this;
      DatabaseManager var40 = (DatabaseManager)var39.getPlatform().getModule(DatabaseManager.class);

      try {
         PunishmentStorage var9 = var40.openStorage();
         Closeable var44 = var9;
         Throwable var46 = null;

         try {
            PunishmentStorage var14 = var9;
            PlayerHistory var16 = PunishmentService.resolveTargetDefault(var9, var4, false, 2, null);
            List var17 = CollectionsKt.getList2(var9.getList(var16, PunishmentType.WARN, var35, true, var38));
            PunishmentService.reply(
               this, EntryPlaceholders.getString(EntryReader.getCharSequence(var9, this.getCharSequence(), var16, false, 2, null), "limit", var38)
            );

            for (Object var21 : var17) {
               PunishmentEntry var22 = (PunishmentEntry)var21;
               CharSequence var24 = Message.getCharSequence(var22.getType());
               Message var25 = var22.isPermanent() ? Message.HISTORY_SUFFIX_ACTIVE : Message.HISTORY_SUFFIX_ACTIVE_TEMP;
               if (var14.appliesToTarget(var22)) {
                  var24 = "" + var24 + var25;
               }

               PunishmentService.reply(this, EntryReader.getCharSequence(var14, var24, var22, var16, false, 4, null));
            }
         } catch (Throwable var30) {
            var46 = var30;
            throw var30;
         } finally {
            CloseableKt.handleCloseable(var44, var46);
         }
      } catch (Exception var32) {
         if (!(var32 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var32); }
         if (!var40.isConnectionError(var32)) {
            throw var32;
         }
      }
   }

   @Override
   public CharSequence getCharSequence() {
      return Message.WARNINGS_START;
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
