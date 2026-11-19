package litebans.config;

import java.io.File;
import litebans.core.LiteBansCore;
import litebans.platform.LiteBansPlatform;

public final class ClassConfigLoader implements ConfigLoader {
   public final LiteBansCore liteBansCore;
   public final Class classValue;

   public ClassConfigLoader(LiteBansCore var1, Class var2) {
      this.liteBansCore = var1;
      this.classValue = var2;
   }

   @Override
   public ConfigFile getConfigFile(LiteBansPlatform var1, File var2) {
      LazyConfigFormat var3 = new LazyConfigFormat(var2, this.classValue);
      return new GenericConfigFile(this.liteBansCore.platform, var3).getConfigFile(var2);
   }
}
