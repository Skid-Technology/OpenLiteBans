package litebans.command;

import java.util.Collection;
import java.util.HashMap;
import litebans.database.PunishmentStorage;
import litebans.platform.LiteBansPlatform;
import litebans.punishment.PlayerHistory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface PagedCommand {
   CharSequence getCharSequence();

   int parsePage(@NotNull LiteBansPlatform var1, @Nullable String[] var2, @NotNull Collection var3, int var4);

   void printPage(
      @NotNull PunishmentStorage var1, @NotNull Collection var2, @Nullable PlayerHistory var3, @Nullable String[] var4, @NotNull BaseCommand var5, int var6
   );

   PlayerHistory lookupHistory(@NotNull PunishmentStorage var1, @NotNull HashMap var2, @Nullable String var3);
}
