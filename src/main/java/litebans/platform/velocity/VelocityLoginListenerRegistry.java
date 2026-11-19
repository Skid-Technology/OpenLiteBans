package litebans.platform.velocity;

import litebans.core.LiteBansCore;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.jvm.internal.MetadataAnnotation;
import litebans.platform.ListenerHandle;
import litebans.platform.LiteBansPlatform;
import litebans.platform.LoginListenerRegistry;
import litebans.platform.Named;
import org.jetbrains.annotations.NotNull;

@MetadataAnnotation(
   a = 2
)
public final class VelocityLoginListenerRegistry extends LoginListenerRegistry {
   public VelocityLoginListenerRegistry(@NotNull LiteBansPlatform var1) {
      super(var1);
   }

   @Override
   public void onDisable() {
      this.platform.unregisterAllListeners();
   }

   @Override
   public boolean isSupported() {
      return this.platform.getPlatformId() == 2;
   }

   @Override
   public void onEnable() {
      LiteBansCore var1 = (LiteBansCore)this.platform.getModule(LiteBansCore.class);
      var1.onEnable();
      new VelocityLoginListener(this.platform).getVelocityLoginListener();
      if (var1.getConfig().isMutesEnabled()) {
         new VelocityChatListener(this.platform).getVelocityChatListener();
      }
   }

   public VelocityListener getVelocityListener(@NotNull Named var1) {
      return Intrinsics.checkObject(var1.getName(), "lockdown") ? new VelocityLockdownListener(this.platform) : (VelocityListener)super.getListenerHandle(var1);
   }

   @Override
   public ListenerHandle getListenerHandle(Named var1) {
      return this.getVelocityListener(var1);
   }
}
