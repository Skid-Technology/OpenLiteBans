package litebans.command;

import litebans.config.Message;
import litebans.core.MessageSender;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.platform.Sender;
import litebans.punishment.EntryPlaceholders;
import litebans.punishment.Flag;
import litebans.punishment.PunishmentRequest;
import litebans.sync.SyncManager;

public final class PlayerAliasPredicate implements AliasPredicate {
   public final PunishmentRequest punishmentRequest;
   public final SyncManager syncManager;
   public final Sender sender;

   public PlayerAliasPredicate(PunishmentRequest var1, SyncManager var2, Sender var3) {
      this.punishmentRequest = var1;
      this.syncManager = var2;
      this.sender = var3;
   }

   @Override
   public boolean checkCommandAlias(CommandAlias var1, Flag var2, String var3) {
      if (var2 == Flag.KICK_SUCCESS && Intrinsics.checkObject(var3, this.punishmentRequest.getString5()) && this.syncManager.removeListener(this)) {
         MessageSender.handleSenderDefault(
            CommandRunnable.messageSender,
            this.sender,
            EntryPlaceholders.getString(
               Message.KICK_RESPONSE.getString("player", this.punishmentRequest.getString5()), "executor", this.punishmentRequest.getSenderName()
            ),
            null,
            4,
            null
         );
      }

      return true;
   }
}
