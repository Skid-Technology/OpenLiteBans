package litebans.command;

import litebans.config.Message;
import litebans.platform.LiteBansPlatform;
import litebans.punishment.PunishmentType;
import org.jetbrains.annotations.NotNull;

public final class CheckMuteCommand extends CheckBanCommand {
   public CheckMuteCommand(@NotNull LiteBansPlatform var1) {
      super(var1);
   }

   @Override
   public PunishmentType getPunishmentType() {
      return PunishmentType.MUTE;
   }

   @Override
   public Message getMessage() {
      return Message.CHECKMUTE_MUTED;
   }

   @Override
   public Message getMessage2() {
      return Message.CHECKMUTE_NO_MUTE;
   }

   @Override
   public String getName() {
      return "checkmute";
   }
}
