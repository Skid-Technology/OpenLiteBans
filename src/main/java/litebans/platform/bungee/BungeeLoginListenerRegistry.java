package litebans.platform.bungee;

import litebans.core.LiteBansCore;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.jvm.internal.MetadataAnnotation;
import litebans.platform.ListenerHandle;
import litebans.platform.LiteBansPlatform;
import litebans.platform.LoginListenerRegistry;
import litebans.platform.Named;
import org.jetbrains.annotations.NotNull;

@MetadataAnnotation(
   a = 1
)
public final class BungeeLoginListenerRegistry extends LoginListenerRegistry {
   public BungeeLoginListenerRegistry(@NotNull LiteBansPlatform var1) {
      super(var1);
   }

   @Override
   public void onDisable() {
      this.platform.unregisterAllListeners();
   }

   @Override
   public boolean isSupported() {
      return this.platform.getPlatformId() == 1;
   }

   @Override
   public void onEnable() {
      LiteBansCore var1 = (LiteBansCore)this.platform.getModule(LiteBansCore.class);
      var1.onEnable();
      new BungeeLoginListener(this.platform).getBungeeLoginListener();
      if (var1.getConfig().isMutesEnabled()) {
         new BungeeChatListener(this.platform).getBungeeChatListener();
      }
   }

   public BungeeListener getBungeeListener(@NotNull Named var1) {
      return Intrinsics.checkObject(var1.getName(), "lockdown") ? new BungeeLockdownListener(this.platform) : (BungeeListener)super.getListenerHandle(var1);
   }

   @Override
   public ListenerHandle getListenerHandle(Named var1) {
      return this.getBungeeListener(var1);
   }
}
