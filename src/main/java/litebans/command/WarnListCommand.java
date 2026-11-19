package litebans.command;

import litebans.platform.LiteBansPlatform;
import litebans.punishment.PunishmentType;
import org.jetbrains.annotations.NotNull;

public final class WarnListCommand extends BanListCommand {
   public WarnListCommand(@NotNull LiteBansPlatform var1) {
      super(var1);
   }

   @Override
   public PunishmentType getPunishmentType() {
      return PunishmentType.WARN;
   }

   @Override
   public String getName() {
      return "warnlist";
   }
}
