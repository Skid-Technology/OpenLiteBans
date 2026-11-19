package litebans.config;

import org.jetbrains.annotations.Nullable;

public interface ConfigSection extends ConfigReader {
   @Nullable
   ConfigSection getConfigSection(String var1);
}
