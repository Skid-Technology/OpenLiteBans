package litebans.platform;

import litebans.core.PluginModule;
import org.jetbrains.annotations.Nullable;

public abstract class LoginListenerRegistry extends PluginModule {
   public LoginListenerRegistry(LiteBansPlatform var1) {
      super(var1);
   }

   @Nullable
   public ListenerHandle getListenerHandle(Named var1) {
      return null;
   }
}
