package litebans.chat;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import litebans.config.ConfigNode;
import litebans.config.JsonConfigFormat;

public class DelegatingJsonSerializer implements JsonSerializer {
   public final JsonConfigFormat jsonConfigFormat;

   public DelegatingJsonSerializer(JsonConfigFormat var1) {
      this.jsonConfigFormat = var1;
   }

   public JsonElement getJsonElement(ConfigNode var1, Type var2, JsonSerializationContext var3) {
      return var3.serialize(var1.map);
   }

   public JsonElement serialize(Object var1, Type var2, JsonSerializationContext var3) {
      return this.getJsonElement((ConfigNode)var1, var2, var3);
   }
}
