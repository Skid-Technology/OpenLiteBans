package litebans.platform.fabric;

import com.mojang.serialization.JsonOps;
import litebans.chat.ComponentParser;
import net.minecraft.class_11341;
import net.minecraft.class_2561;
import net.minecraft.class_5455;
import net.minecraft.class_8824;

public class FabricComponentParserReflective implements ComponentParser {
   private static final boolean flag;
   private FabricComponentParserLegacy fabricComponentParserLegacy;

   public FabricComponentParserReflective() {
      if (flag) {
         this.fabricComponentParserLegacy = new FabricComponentParserLegacy();
      }
   }

   @Override
   public Object getObject(CharSequence var1) {
      return class_2561.method_43470(var1.toString());
   }

   @Override
   public Object getObject2(CharSequence var1) {
      return flag ? this.fabricComponentParserLegacy.getObject2(var1) : this.getclass_2561(var1);
   }

   private final class_2561 getclass_2561(CharSequence var1) {
      return (class_2561)class_8824.field_46597
         .parse(class_5455.field_40585.method_57093(JsonOps.INSTANCE), class_11341.method_71360(var1.toString()))
         .getOrThrow(IllegalArgumentException::new);
   }

   static {
      boolean var0 = false;

      try {
         Class var1 = Class.forName("net.minecraft.class_11341");
         var1.getMethod("method_71360", String.class);
      } catch (ReflectiveOperationException var2) {
         var0 = true;
      }

      flag = var0;
   }
}
