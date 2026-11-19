package litebans.punishment;

import litebans.command.CommandRunnable;
import litebans.database.DatabaseManager;
import litebans.platform.LiteBansPlatform;
import org.jetbrains.annotations.NotNull;

public final class LocalLoginCheck extends LoginCheck {
   public LocalLoginCheck(@NotNull LiteBansPlatform var1) {
      super(var1);
   }

   @Override
   public String getString(@NotNull Object var1) {
      CommandRunnable var2 = this;
      return ((DatabaseManager)var2.getPlatform().getModule(DatabaseManager.class)).getString();
   }
}
