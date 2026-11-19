package litebans.config;

import java.io.File;
import litebans.platform.LiteBansPlatform;

public interface ConfigLoader {
   ConfigFile getConfigFile(LiteBansPlatform var1, File var2);
}
