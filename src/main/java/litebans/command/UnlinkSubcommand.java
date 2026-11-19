package litebans.command;

import litebans.chat.ChatColorCode;
import litebans.config.Message;
import litebans.core.MessageSender;
import litebans.database.DatabaseManager;
import litebans.database.PunishmentStorage;
import litebans.lib.kotlin.NoWhenBranchMatchedException;
import litebans.lib.kotlin.Unit;
import litebans.platform.LiteBansPlatform;
import litebans.punishment.PlayerHistory;
import litebans.punishment.PunishmentService;
import org.jetbrains.annotations.NotNull;

public final class UnlinkSubcommand extends CommandRunnable {
   private final String[] stringArray;

   public UnlinkSubcommand(@NotNull String[] var1, @NotNull LiteBansPlatform var2) {
      super(var2);
      this.stringArray = var1;
   }

   @Override
   public void run() {
      CommandRunnable var10000 = this;
      String[] var1 = new String[]{"litebans.admin", "litebans.unlink"};
      PunishmentService.requireAnyPermission(var10000, var1);
      CommandRunnable var2 = this;
      ((DatabaseManager)var2.getPlatform().getModule(DatabaseManager.class)).runWithStorageAsync(st -> getUnit(this, (PunishmentStorage) st));
   }

   private static final Unit getUnit(UnlinkSubcommand var0, PunishmentStorage var1) {
      MessageSender var2 = CommandRunnable.messageSender;
      boolean var3 = var1.isClosed();
      CharSequence var4 = Message.ERROR_NO_DATABASE;
      if (var3) {
         var2.getVoid(var4);
         throw new NoWhenBranchMatchedException();
      } else {
         String var9 = var0.stringArray[1];
         PlayerHistory var10000 = var1.findLatestHistory(var9);
         if (var10000 == null) {
            CommandRunnable.messageSender.getVoid(Message.ERROR_NO_UUID);
            throw new NoWhenBranchMatchedException();
         } else {
            PlayerHistory var10 = var10000;
            var1.updateHistoryIp(var10);
            PunishmentService.reply(
               var0,
               ChatColorCode.getString2("Player ") + ChatColorCode.getString(var10.getString()) + ChatColorCode.getString2(" has been unlinked successfully.")
            );
            return Unit.INSTANCE;
         }
      }
   }
}
