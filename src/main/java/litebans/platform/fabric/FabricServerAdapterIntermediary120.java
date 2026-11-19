package litebans.platform.fabric;

import litebans.platform.LiteBansPlatform;
import net.minecraft.class_3222;

public class FabricServerAdapterIntermediary120 extends FabricServerAdapterIntermediary {
   public FabricServerAdapterIntermediary120(LiteBansPlatform var1) {
      super(var1);
   }

   @Override
   public Object getObject(Object var1) {
      return var1 instanceof class_3222 ? ((class_3222)var1).method_5671() : var1;
   }
}
