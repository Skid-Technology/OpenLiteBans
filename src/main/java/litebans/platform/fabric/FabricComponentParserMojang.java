package litebans.platform.fabric;

import com.mojang.serialization.JsonOps;
import litebans.chat.ComponentParser;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentSerialization;
import net.minecraft.util.StrictJsonParser;

public class FabricComponentParserMojang implements ComponentParser {
   @Override
   public Object getObject(CharSequence var1) {
      return Component.literal(var1.toString());
   }

   @Override
   public Object getObject2(CharSequence var1) {
      return this.getComponent(var1);
   }

   private final Component getComponent(CharSequence var1) {
      return (Component)ComponentSerialization.CODEC
         .parse(RegistryAccess.EMPTY.createSerializationContext(JsonOps.INSTANCE), StrictJsonParser.parse(var1.toString()))
         .getOrThrow(IllegalArgumentException::new);
   }
}
