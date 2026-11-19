package litebans.command;

import java.io.Closeable;
import java.sql.SQLException;
import litebans.config.Message;
import litebans.core.LiteBansCore;
import litebans.core.MessageSender;
import litebans.database.DatabaseManager;
import litebans.database.EntryReader;
import litebans.database.PunishmentStorage;
import litebans.lib.kotlin.NoWhenBranchMatchedException;
import litebans.lib.kotlin.collections.ArraysKt;
import litebans.lib.kotlin.io.CloseableKt;
import litebans.lib.kotlin.text.StringsKt;
import litebans.platform.LiteBansPlatform;
import litebans.platform.Sender;
import litebans.punishment.DurationUnit;
import litebans.punishment.PlayerHistory;
import litebans.punishment.PunishmentService;
import org.jetbrains.annotations.NotNull;

public final class PruneHistoryCommand extends BaseCommand {
   public PruneHistoryCommand(@NotNull LiteBansPlatform var1) {
      super("prunehistory", var1);
   }

   @Override
   public void execute(@NotNull Sender var1, @NotNull String[] var2) {
      CommandRunnable var3 = this;
      if (((LiteBansCore)var3.getPlatform().getModule(LiteBansCore.class)).getConfig().isSecurityPrunehistory()) {
         var3 = this;
         MessageSender var5 = CommandRunnable.messageSender;
         boolean var6 = var3.getCurrentSender().isConsole();
         CharSequence var7 = Message.ERROR_CONSOLE;
         boolean var10 = !var6;
         if (var10) {
            var5.getVoid(var7);
            throw new NoWhenBranchMatchedException();
         }
      }

      CommandRunnable var4 = this;
      String var38 = PunishmentService.lookupUuid(var4, (String)ArraysKt.getObject(var2));
      var4 = this;
      DatabaseManager var40 = (DatabaseManager)var4.getPlatform().getModule(DatabaseManager.class);

      try {
         PunishmentStorage var41 = var40.openStorage();
         Closeable var42 = var41;
         Throwable var8 = null;

         try {
            PlayerHistory var13 = PunishmentService.resolveTargetDefault(var41, var38, false, 2, null);
            String var10000 = var13.getUuid();
            if (var10000 == null) {
               CommandRunnable.messageSender.getVoid(Message.HISTORY_ERROR_NO_LOGINRECORD);
               throw new NoWhenBranchMatchedException();
            }

            String var14 = var10000;
            StringBuilder var45 = new StringBuilder();
            byte var16 = 1;
            String var10001;
            if (var16 < var2.length) {
               var10001 = var2[var16];
            } else {
               StringBuilder var18 = var45;
               var10001 = "";
               var45 = var18;
            }

            StringBuilder var46 = var45.append(var10001);
            var16 = 2;
            if (var16 < var2.length) {
               var10001 = var2[var16];
            } else {
               StringBuilder var44 = var46;
               var10001 = "";
               var46 = var44;
            }

            String var20 = var46.append(var10001).toString();
            double var21 = DurationUnit.durationParser.getDouble(var20, Double.MIN_VALUE);
            MessageSender var17 = CommandRunnable.messageSender;
            boolean var19 = var21 == Double.MIN_VALUE && !StringsKt.checkCharSequence(var20);
            CharSequence var23 = Message.ERROR_NO_DURATION;
            if (var19) {
               var17.getVoid(var23);
               throw new NoWhenBranchMatchedException();
            }

            long var29 = var21 == Double.MIN_VALUE
               ? 0L
               : (long)((double)DatabaseManager.currentTimeMillisDefault(var41.getDatabaseManager(), false, 1, null) - var21);
            var41.pruneHistory(var14, var29);
            PunishmentService.reply(this, EntryReader.getCharSequence(var41, Message.PRUNEHISTORY_MESSAGE, var13, false, 2, null));
         } catch (Throwable var34) {
            var8 = var34;
            throw var34;
         } finally {
            CloseableKt.handleCloseable(var42, var8);
         }
      } catch (Exception var36) {
         if (!(var36 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var36); }
         if (!var40.isConnectionError(var36)) {
            throw var36;
         }
      }
   }
}
