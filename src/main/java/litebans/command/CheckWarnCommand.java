package litebans.command;

import litebans.config.Message;
import litebans.platform.LiteBansPlatform;
import litebans.punishment.PunishmentType;
import org.jetbrains.annotations.NotNull;

public final class CheckWarnCommand extends CheckBanCommand {
   public CheckWarnCommand(@NotNull LiteBansPlatform var1) {
      super(var1);
   }

   @Override
   public PunishmentType getPunishmentType() {
      return PunishmentType.WARN;
   }

   @Override
   public Message getMessage() {
      return Message.CHECKWARN_WARNED;
   }

   @Override
   public Message getMessage2() {
      return Message.CHECKWARN_NO_WARN;
   }

   @Override
   public String getName() {
      return "checkwarn";
   }
}
