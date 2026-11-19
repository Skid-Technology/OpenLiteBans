package litebans.command;

import litebans.platform.LiteBansPlatform;
import litebans.punishment.PunishmentType;
import org.jetbrains.annotations.NotNull;

public final class MuteListCommand extends BanListCommand {
   public MuteListCommand(@NotNull LiteBansPlatform var1) {
      super(var1);
   }

   @Override
   public PunishmentType getPunishmentType() {
      return PunishmentType.MUTE;
   }

   @Override
   public String getName() {
      return "mutelist";
   }
}
