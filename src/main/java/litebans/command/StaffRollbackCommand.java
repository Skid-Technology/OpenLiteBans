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

public final class StaffRollbackCommand extends BaseCommand {
   public StaffRollbackCommand(@NotNull LiteBansPlatform var1) {
      super("staffrollback", var1);
   }

   @Override
   public void execute(@NotNull Sender var1, @NotNull String[] var2) {
      CommandRunnable var3 = this;
      if (((LiteBansCore)var3.getPlatform().getModule(LiteBansCore.class)).getConfig().isSecurityStaffrollback()) {
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
      String var40 = PunishmentService.lookupUuid(var4, (String)ArraysKt.getObject(var2));
      String var41 = PunishmentService.parseServerScopeDefault(this, var1, var2, null, 4, null);
      CommandRunnable var42 = this;
      DatabaseManager var43 = (DatabaseManager)var42.getPlatform().getModule(DatabaseManager.class);

      try {
         PunishmentStorage var44 = var43.openStorage();
         Closeable var8 = var44;
         Throwable var9 = null;

         try {
            PlayerHistory var14 = PunishmentService.resolveTargetDefault(var44, var40, false, 2, null);
            StringBuilder var10000 = new StringBuilder();
            byte var16 = 1;
            String var10001;
            if (var16 < var2.length) {
               var10001 = var2[var16];
            } else {
               StringBuilder var18 = var10000;
               var10001 = "";
               var10000 = var18;
            }

            var10000 = var10000.append(var10001);
            var16 = 2;
            if (var16 < var2.length) {
               var10001 = var2[var16];
            } else {
               StringBuilder var47 = var10000;
               var10001 = "";
               var10000 = var47;
            }

            String var20 = var10000.append(var10001).toString();
            double var21 = DurationUnit.durationParser.getDouble(var20, Double.MIN_VALUE);
            MessageSender var17 = CommandRunnable.messageSender;
            boolean var19 = var21 == Double.MIN_VALUE && !StringsKt.checkCharSequence(var20);
            CharSequence var23 = Message.ERROR_NO_DURATION;
            if (var19) {
               var17.getVoid(var23);
               throw new NoWhenBranchMatchedException();
            }

            String var49 = var14.getUuid();
            if (var49 == null) {
               CommandRunnable.messageSender.getVoid(Message.HISTORY_ERROR_NO_LOGINRECORD);
               throw new NoWhenBranchMatchedException();
            }

            String var46 = var49;
            long var29 = var21 == Double.MIN_VALUE
               ? 0L
               : (long)((double)DatabaseManager.currentTimeMillisDefault(var44.getDatabaseManager(), false, 1, null) - var21);
            long var31 = var44.rollbackStaff(var46, var29, var41);
            PunishmentService.reply(this, EntryReader.getCharSequence(var44, Message.STAFFROLLBACK_MESSAGE.getString("amount", var31), var14, false, 2, null));
         } catch (Throwable var36) {
            var9 = var36;
            throw var36;
         } finally {
            CloseableKt.handleCloseable(var8, var9);
         }
      } catch (Exception var38) {
         if (!(var38 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var38); }
         if (!var43.isConnectionError(var38)) {
            throw var38;
         }
      }
   }
}
