package litebans.platform.fabric;

import litebans.chat.ComponentParser;
import net.minecraft.class_5455;
import net.minecraft.class_2561.class_2562;

public class FabricComponentParserLegacy implements ComponentParser {
   @Override
   public Object getObject(CharSequence var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public Object getObject2(CharSequence var1) {
      return class_2562.method_10877(var1.toString(), class_5455.field_40585);
   }
}
