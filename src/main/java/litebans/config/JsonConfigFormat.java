package litebans.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.Reader;
import java.io.Writer;
import java.util.LinkedHashMap;
import java.util.Map;
import litebans.chat.DelegatingJsonSerializer;

public class JsonConfigFormat extends ConfigFormat {
   private final Gson gson = new GsonBuilder()
      .serializeNulls()
      .setPrettyPrinting()
      .registerTypeAdapter(ConfigNode.class, new DelegatingJsonSerializer(this))
      .create();

   @Override
   public void handleConfigNode(ConfigNode var1, Writer var2) {
      this.gson.toJson(var1.map, var2);
   }

   @Override
   public ConfigNode getConfigNode(Reader var1) {
      return this.getConfigNode(var1, null);
   }

   @Override
   public ConfigNode getConfigNode(Reader var1, ConfigNode var2) {
      Object var3 = (Map)this.gson.fromJson(var1, LinkedHashMap.class);
      if (var3 == null) {
         var3 = new LinkedHashMap();
      }

      return new ConfigNode((Map)var3, var2);
   }
}
