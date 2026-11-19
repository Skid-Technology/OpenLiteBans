package litebans.config;

import java.io.Reader;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public abstract class ConfigFormat {
   public static final Map map = new HashMap();

   public static ConfigFormat getConfigFormat(Class var0) {
      return (ConfigFormat)map.get(var0);
   }

   public abstract void handleConfigNode(ConfigNode var1, Writer var2);

   public abstract ConfigNode getConfigNode(Reader var1);

   public abstract ConfigNode getConfigNode(Reader var1, ConfigNode var2);

   static {
      try {
         map.put(YamlConfigFormat.class, new YamlConfigFormat());
      } catch (NoClassDefFoundError var1) {
      }

      try {
         map.put(JsonConfigFormat.class, new JsonConfigFormat());
      } catch (NoClassDefFoundError var0) {
      }
   }
}
