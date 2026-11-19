package litebans.chat;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.awt.Color;
import java.lang.reflect.Type;
import java.util.Map.Entry;

public class ChatStyleSerializer implements JsonSerializer, JsonDeserializer {
   private static final boolean checkJsonElement(JsonElement var0) {
      if (var0.isJsonPrimitive()) {
         JsonPrimitive var1 = (JsonPrimitive)var0;
         if (var1.isBoolean()) {
            return var1.getAsBoolean();
         }

         if (var1.isNumber()) {
            Number var2 = var1.getAsNumber();
            if (var2 instanceof Byte) {
               return var2.byteValue() != 0;
            }
         }
      }

      return false;
   }

   public static void handleChatStyle(ChatStyle var0, JsonObject var1) {
      if (var0.getBooleanValue() != null) {
         var1.addProperty("bold", var0.getBooleanValue());
      }

      if (var0.getBooleanValue2() != null) {
         var1.addProperty("italic", var0.getBooleanValue2());
      }

      if (var0.getBooleanValue3() != null) {
         var1.addProperty("underlined", var0.getBooleanValue3());
      }

      if (var0.getBooleanValue4() != null) {
         var1.addProperty("strikethrough", var0.getBooleanValue4());
      }

      if (var0.getBooleanValue5() != null) {
         var1.addProperty("obfuscated", var0.getBooleanValue5());
      }

      if (var0.check() && var0.getChatColor().getColor() != null) {
         var1.addProperty("color", var0.getChatColor().getString3());
      }

      if (var0.check2()) {
         var1.addProperty("shadow_color", var0.getColor().getRGB());
      }

      if (var0.check3()) {
         var1.addProperty("font", var0.getString());
      }
   }

   public ChatStyle getChatStyle(JsonElement var1, Type var2, JsonDeserializationContext var3) {
      ChatStyleBuilder var4 = ChatStyle.getChatStyleBuilder();
      JsonObject var5 = var1.getAsJsonObject();

      for (Entry var7 : var5.entrySet()) {
         String var8 = (String)var7.getKey();
         JsonElement var9 = (JsonElement)var7.getValue();
         switch (var8) {
            case "bold":
               var4.getChatStyleBuilder(checkJsonElement(var9));
               break;
            case "italic":
               var4.getChatStyleBuilder2(checkJsonElement(var9));
               break;
            case "underlined":
               var4.getChatStyleBuilder3(checkJsonElement(var9));
               break;
            case "strikethrough":
               var4.getChatStyleBuilder4(checkJsonElement(var9));
               break;
            case "obfuscated":
               var4.getChatStyleBuilder5(checkJsonElement(var9));
               break;
            case "color":
               var4.getChatStyleBuilder(ChatColor.getChatColor(var9.getAsString()));
               break;
            case "shadow_color":
               if (var9.isJsonArray()) {
                  JsonArray var12 = var9.getAsJsonArray();
                  var4.getChatStyleBuilder(
                     new Color(var12.get(0).getAsFloat(), var12.get(1).getAsFloat(), var12.get(2).getAsFloat(), var12.get(3).getAsFloat())
                  );
               } else if (var9.isJsonPrimitive()) {
                  var4.getChatStyleBuilder(new Color(var9.getAsNumber().intValue(), true));
               }
               break;
            case "font":
               var4.getChatStyleBuilder(var9.getAsString());
         }
      }

      return var4.getChatStyle();
   }

   public JsonElement getJsonElement(ChatStyle var1, Type var2, JsonSerializationContext var3) {
      JsonObject var4 = new JsonObject();
      handleChatStyle(var1, var4);
      return var4;
   }

   public JsonElement serialize(Object var1, Type var2, JsonSerializationContext var3) {
      return this.getJsonElement((ChatStyle)var1, var2, var3);
   }

   public Object deserialize(JsonElement var1, Type var2, JsonDeserializationContext var3) {
      return this.getChatStyle(var1, var2, var3);
   }
}
