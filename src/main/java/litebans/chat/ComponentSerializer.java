package litebans.chat;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import java.lang.reflect.Type;

public class ComponentSerializer implements JsonDeserializer {
   public static BaseComponent[] getBaseComponentArray(String var0) {
      return VersionedComponentSerializer.getInstance().getBaseComponentArray(var0);
   }

   public static String getString(BaseComponent[] var0) {
      return VersionedComponentSerializer.getInstance().getString(var0);
   }

   @Override
   public Object deserialize(JsonElement var1, Type var2, JsonDeserializationContext var3) {
      return VersionedComponentSerializer.getInstance().getBaseComponent(var1, var2, var3);
   }
}
