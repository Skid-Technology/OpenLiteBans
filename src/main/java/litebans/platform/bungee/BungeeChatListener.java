package litebans.platform.bungee;

import litebans.command.MuteChatHandler;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.platform.ListenerHandle;
import litebans.platform.LiteBansPlatform;
import litebans.punishment.MuteManager;
import net.md_5.bungee.api.connection.Connection;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.event.EventHandler;
import org.jetbrains.annotations.NotNull;

public final class BungeeChatListener implements BungeeListener {
   private final LiteBansPlatform liteBansPlatform;
   private final MuteManager muteManager;

   public BungeeChatListener(@NotNull LiteBansPlatform var1) {
      this.liteBansPlatform = var1;
      this.muteManager = (MuteManager)this.liteBansPlatform.getModule(MuteManager.class);
   }

   public final LiteBansPlatform getLiteBansPlatform() {
      return this.liteBansPlatform;
   }

   @EventHandler(
      priority = -32
   )
   public final void handleChatEvent(@NotNull ChatEvent var1) {
      if (!var1.isCancelled()) {
         Connection var4 = var1.getSender();
         ProxiedPlayer var10000 = var4 instanceof ProxiedPlayer ? (ProxiedPlayer)var4 : null;
         if ((var4 instanceof ProxiedPlayer ? (ProxiedPlayer)var4 : null) != null) {
            ProxiedPlayer var2 = var10000;
            String var3 = var1.getMessage();
            MuteChatHandler var5 = this.muteManager.getMuteChatHandler();
            Intrinsics.handleObject(var3);
            MuteChatHandler.handleObjectDefault(var5, var1, var3, this.liteBansPlatform.getSender(var2), false, 8, null);
         }
      }
   }

   public BungeeChatListener getBungeeChatListener() {
      BungeeChatListener var2 = this;
      LiteBansPlatform var10000 = var2.liteBansPlatform;
      ListenerHandle[] var4 = new ListenerHandle[]{var2};
      var10000.registerListeners(var4);
      return this;
   }

   @Override
   public ListenerHandle getListenerHandle() {
      return this.getBungeeChatListener();
   }
}
