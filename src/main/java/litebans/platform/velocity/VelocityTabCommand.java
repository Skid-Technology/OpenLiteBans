package litebans.platform.velocity;

import java.util.Arrays;
import java.util.List;
import litebans.lib.kotlin.jvm.internal.DefaultConstructorMarker;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.platform.CommandRegistry;
import litebans.platform.LiteBansPlatform;
import litebans.platform.LiteCommand;
import litebans.platform.Sender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class VelocityTabCommand extends VelocityCommand {
   private final LiteBansPlatform liteBansPlatform2;

   public VelocityTabCommand(@NotNull LiteCommand var1, @NotNull LiteBansPlatform var2, @Nullable String var3, @NotNull String[] var4) {
      super(var1, var3, var2, Arrays.copyOf(var4, var4.length));
      this.liteBansPlatform2 = var2;
   }

   public VelocityTabCommand(LiteCommand var1, LiteBansPlatform var2, String var3, String[] var4, int var5, DefaultConstructorMarker var6) {
      this(var1, var2, ((var5 & 4) != 0 ? var1.getPermission() : var3), var4);
   }

   @Override
   public List suggest(@NotNull Sender var1, @NotNull String[] var2) {
      CommandRegistry var10000 = this.liteBansPlatform2.getCommandRegistry();
      Intrinsics.handleObject(var10000, "");
      return ((VelocityCommandRegistry)var10000).suggest(this, this.getName(), var1, var2);
   }
}
