package litebans.config;

import java.io.Reader;
import java.io.Writer;
import java.util.LinkedHashMap;
import java.util.Map;
import org.yaml.snakeyaml.Yaml;

public class YamlConfigFormat extends ConfigFormat {
   private final ThreadLocal threadLocal = new YamlThreadLocal(this);

   @Override
   public void handleConfigNode(ConfigNode var1, Writer var2) {
      ((Yaml)this.threadLocal.get()).dump(var1.map, var2);
   }

   @Override
   public ConfigNode getConfigNode(Reader var1) {
      return this.getConfigNode(var1, null);
   }

   @Override
   public ConfigNode getConfigNode(Reader var1, ConfigNode var2) {
      Object var3 = (Map)((Yaml)this.threadLocal.get()).loadAs(var1, LinkedHashMap.class);
      if (var3 == null) {
         var3 = new LinkedHashMap();
      }

      return new ConfigNode((Map)var3, var2);
   }
}
