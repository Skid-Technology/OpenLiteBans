package litebans.platform.velocity;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.event.PostOrder;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.command.CommandExecuteEvent;
import com.velocitypowered.api.event.player.PlayerChatEvent;
import com.velocitypowered.api.proxy.Player;
import litebans.command.MuteChatHandler;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.platform.ListenerHandle;
import litebans.platform.LiteBansPlatform;
import litebans.punishment.MuteManager;
import org.jetbrains.annotations.NotNull;

public final class VelocityChatListener implements VelocityListener {
   private final LiteBansPlatform liteBansPlatform;
   private final MuteManager muteManager;

   public VelocityChatListener(@NotNull LiteBansPlatform var1) {
      this.liteBansPlatform = var1;
      this.muteManager = (MuteManager)this.liteBansPlatform.getModule(MuteManager.class);
   }

   public final LiteBansPlatform getLiteBansPlatform() {
      return this.liteBansPlatform;
   }

   @Subscribe(
      order = PostOrder.EARLY
   )
   public final void handlePlayerChatEvent(@NotNull PlayerChatEvent var1) {
      if (var1.getResult().isAllowed()) {
         Player var2 = var1.getPlayer();
         String var3 = var1.getMessage();
         MuteChatHandler var10000 = this.muteManager.getMuteChatHandler();
         Intrinsics.handleObject(var3);
         var10000.handleObject(var1, var3, this.liteBansPlatform.getSender(var2), true);
      }
   }

   @Subscribe(
      order = PostOrder.EARLY
   )
   public final void handleCommandExecuteEvent(@NotNull CommandExecuteEvent var1) {
      CommandSource var2 = var1.getCommandSource();
      String var3 = var1.getCommand();
      if (var1.getResult().isAllowed() && var2 instanceof Player) {
         MuteChatHandler.handleObjectDefault(this.muteManager.getMuteChatHandler(), var1, '/' + var3, this.liteBansPlatform.getSender(var2), false, 8, null);
      }
   }

   public VelocityChatListener getVelocityChatListener() {
      VelocityChatListener var2 = this;
      LiteBansPlatform var10000 = var2.liteBansPlatform;
      ListenerHandle[] var4 = new ListenerHandle[]{var2};
      var10000.registerListeners(var4);
      return this;
   }

   @Override
   public ListenerHandle getListenerHandle() {
      return this.getVelocityChatListener();
   }
}
