package litebans.platform;

import java.util.List;
import litebans.core.PluginModule;

public abstract class PlatformDefaults implements LiteBansPlatform {
   public static PluginModule[] getPluginModuleArray(LiteBansPlatform var0) {
      List var1 = var0.getDiagnostics().getList(PluginModule.class, new Class[]{LiteBansPlatform.class}, new Object[]{var0});
      var1.removeIf(o -> checkLiteBansPlatform(var0, (PluginModule) o));
      return /* cast-fix */(PluginModule[])(var1.toArray(new PluginModule[var1.size()]));
   }

   private static final boolean checkLiteBansPlatform(LiteBansPlatform var0, PluginModule var1) {
      return !var0.registerModule(var1);
   }
}
