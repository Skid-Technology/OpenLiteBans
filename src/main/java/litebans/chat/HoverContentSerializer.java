package litebans.chat;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

public class HoverContentSerializer implements JsonSerializer, JsonDeserializer {
   public HoverText getHoverText(JsonElement var1, Type var2, JsonDeserializationContext var3) {
      if (var1.isJsonArray()) {
         return new HoverText((BaseComponent[])var3.deserialize(var1, BaseComponent[].class));
      } else {
         return var1.isJsonPrimitive()
            ? new HoverText(var1.getAsJsonPrimitive().getAsString())
            : new HoverText(new BaseComponent[]{(BaseComponent)var3.deserialize(var1, BaseComponent.class)});
      }
   }

   public JsonElement getJsonElement(HoverText var1, Type var2, JsonSerializationContext var3) {
      return var3.serialize(var1.getValue());
   }

   public JsonElement serialize(Object var1, Type var2, JsonSerializationContext var3) {
      return this.getJsonElement((HoverText)var1, var2, var3);
   }

   public Object deserialize(JsonElement var1, Type var2, JsonDeserializationContext var3) {
      return this.getHoverText(var1, var2, var3);
   }
}
