package litebans.platform.bungee;

import java.util.Arrays;
import java.util.List;
import litebans.chat.JsonMessageParser;
import litebans.config.Message;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.platform.CommandRegistry;
import litebans.platform.LiteBansPlatform;
import litebans.platform.LiteCommand;
import litebans.platform.PlatformBridge;
import litebans.platform.Sender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class BungeeTabCommand extends BungeeCommand {
   private final LiteBansPlatform liteBansPlatform2;

   public BungeeTabCommand(@NotNull LiteCommand var1, @NotNull LiteBansPlatform var2, @Nullable String var3, @NotNull String[] var4) {
      super(var1, var3, var2, Arrays.copyOf(var4, var4.length));
      this.liteBansPlatform2 = var2;
      PlatformBridge var10000 = this.liteBansPlatform2.createBridge();
      Intrinsics.handleObject(var10000, "");
      if (((BungeeBridge)var10000).isFlag2()) {
         this.setPermissionMessage(JsonMessageParser.formatSplitter.getString(Message.ERROR_NO_PERMISSION.toString()));
      }
   }

   @Override
   public List suggest(@NotNull Sender var1, @NotNull String[] var2) {
      CommandRegistry var10000 = this.liteBansPlatform2.getCommandRegistry();
      Intrinsics.handleObject(var10000, "");
      return ((BungeeCommandRegistry)var10000).suggest(this, this.getName(), var1, var2);
   }
}
