package litebans.command;

import litebans.chat.ChatColorCode;
import litebans.config.Message;
import litebans.core.MessageSender;
import litebans.database.DatabaseManager;
import litebans.database.PunishmentStorage;
import litebans.database.Table;
import litebans.lib.kotlin.NoWhenBranchMatchedException;
import litebans.lib.kotlin.Unit;
import litebans.platform.LiteBansPlatform;
import litebans.punishment.PunishmentService;
import org.jetbrains.annotations.NotNull;

public final class TemplateResetSubcommand extends CommandRunnable {
   public TemplateResetSubcommand(@NotNull LiteBansPlatform var1) {
      super(var1);
   }

   @Override
   public void run() {
      CommandRunnable var1 = this;
      MessageSender var3 = CommandRunnable.messageSender;
      boolean var4 = var1.getCurrentSender().isConsole();
      CharSequence var5 = Message.ERROR_CONSOLE;
      boolean var8 = !var4;
      if (var8) {
         var3.getVoid(var5);
         throw new NoWhenBranchMatchedException();
      } else {
         var1 = this;
         ((DatabaseManager)var1.getPlatform().getModule(DatabaseManager.class)).runWithStorageAsync(st -> getUnit(this, (PunishmentStorage) st));
      }
   }

   private static final Unit getUnit(TemplateResetSubcommand var0, PunishmentStorage var1) {
      MessageSender var2 = CommandRunnable.messageSender;
      boolean var3 = var1.isClosed();
      CharSequence var4 = Message.ERROR_NO_DATABASE;
      if (var3) {
         var2.getVoid(var4);
         throw new NoWhenBranchMatchedException();
      } else {
         for (Object var5 : Table.Companion.getList()) {
            Table var6 = (Table)var5;
            var1.resetTemplates(var6);
         }

         PunishmentService.reply(var0, ArgUtils.getString(ChatColorCode.YELLOW, "All templates have been reset successfully."));
         return Unit.INSTANCE;
      }
   }
}
