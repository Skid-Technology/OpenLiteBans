package litebans.platform.fabric;

import com.google.gson.JsonParser;
import litebans.chat.ComponentParser;
import net.minecraft.class_2561;
import net.minecraft.class_2561.class_2562;

public class FabricComponentParserIntermediary implements ComponentParser {
   public final FabricServerAdapterIntermediary121 fabricServerAdapterIntermediary121;

   public FabricComponentParserIntermediary(FabricServerAdapterIntermediary121 var1) {
      this.fabricServerAdapterIntermediary121 = var1;
   }

   public class_2561 getclass_2561(CharSequence var1) {
      throw new UnsupportedOperationException();
   }

   public class_2561 getclass_25612(CharSequence var1) {
      return class_2562.method_10872(JsonParser.parseString(var1.toString()));
   }

   @Override
   public Object getObject2(CharSequence var1) {
      return this.getclass_25612(var1);
   }

   @Override
   public Object getObject(CharSequence var1) {
      return this.getclass_2561(var1);
   }
}
