package litebans.chat;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

public class TextComponentSerializer extends BaseComponentSerializer implements JsonSerializer, JsonDeserializer {
   public TextComponentSerializer(VersionedComponentSerializer var1) {
      super(var1);
   }

   public TextComponent getTextComponent(JsonElement var1, Type var2, JsonDeserializationContext var3) {
      TextComponent var4 = new TextComponent();
      JsonObject var5 = var1.getAsJsonObject();
      JsonElement var6 = var5.get("text");
      if (var6 != null) {
         var4.setText(var6.getAsString());
      }

      this.handleJsonObject(var5, var4, var3);
      return var4;
   }

   public JsonElement getJsonElement(TextComponent var1, Type var2, JsonSerializationContext var3) {
      JsonObject var4 = new JsonObject();
      this.handleJsonObject(var4, var1, var3);
      var4.addProperty("text", var1.getText());
      return var4;
   }

   public JsonElement serialize(Object var1, Type var2, JsonSerializationContext var3) {
      return this.getJsonElement((TextComponent)var1, var2, var3);
   }

   public Object deserialize(JsonElement var1, Type var2, JsonDeserializationContext var3) {
      return this.getTextComponent(var1, var2, var3);
   }
}
