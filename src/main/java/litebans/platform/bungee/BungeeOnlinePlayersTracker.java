package litebans.platform.bungee;

import litebans.core.LiteBansCore;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.jvm.internal.MetadataAnnotation;
import litebans.platform.ListenerHandle;
import litebans.platform.LiteBansPlatform;
import litebans.platform.PlatformBridge;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.event.EventHandler;
import org.jetbrains.annotations.NotNull;

@MetadataAnnotation(
   a = 1
)
public final class BungeeOnlinePlayersTracker extends BungeeOnlinePlayers implements BungeeListener {
   public BungeeOnlinePlayersTracker(@NotNull LiteBansPlatform var1) {
      super(var1);
   }

   @Override
   public void onDisable() {
      LiteBansPlatform var10000 = this.platform;
      ListenerHandle[] var1 = new ListenerHandle[]{this};
      var10000.unregisterListeners(var1);
   }

   @Override
   public void onEnable() {
      if (this.platform.getPlatformId() != 1) {
         String var1 = "Failed requirement.";
         throw new IllegalArgumentException(var1.toString());
      } else {
         this.platform.setOnlinePlayers(this);
         this.getBungeeOnlinePlayersTracker();
      }
   }

   @EventHandler
   public final void handlePlayerDisconnectEvent(@NotNull PlayerDisconnectEvent var1) {
      this.platform.removeCachedSender(var1.getPlayer());
      LiteBansCore var2 = (LiteBansCore)this.platform.getModule(LiteBansCore.class);
      int var3 = var2.getIntValue();
      PlatformBridge var10001 = this.platform.createBridge();
      Intrinsics.handleObject(var10001, "");
      int var4 = ((BungeeBridge)var10001).getInt();
      var2.setIntValue(Math.max(var3, var4));
   }

   public BungeeOnlinePlayersTracker getBungeeOnlinePlayersTracker() {
      BungeeOnlinePlayersTracker var2 = this;
      LiteBansPlatform var10000 = var2.platform;
      ListenerHandle[] var4 = new ListenerHandle[]{var2};
      var10000.registerListeners(var4);
      return this;
   }

   @Override
   public ListenerHandle getListenerHandle() {
      return this.getBungeeOnlinePlayersTracker();
   }
}
