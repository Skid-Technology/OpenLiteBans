package litebans.platform.fabric;

import litebans.chat.ChatComponentVersion;
import litebans.chat.ComponentParser;
import litebans.chat.ComponentVersionConverter;
import litebans.platform.LiteBansPlatform;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.class_2540;
import net.minecraft.class_2561;
import net.minecraft.class_2960;
import net.minecraft.class_3222;

public class FabricServerAdapterIntermediary121 extends FabricServerAdapterIntermediary {
   public final ComponentParser componentParser2 = new FabricComponentParserIntermediary(this);

   public FabricServerAdapterIntermediary121(LiteBansPlatform var1) {
      super(var1);
   }

   @Override
   public Object getObject(Object var1) {
      return var1 instanceof class_3222 ? ((class_3222)var1).method_5671() : var1;
   }

   @Override
   public ComponentParser getComponentParser() {
      return this.componentParser2;
   }

   @Override
   public void handleObject(Object var1, String var2) {
      int var3 = this.getInt();
      ChatComponentVersion var4 = var3 >= 770 ? ChatComponentVersion.V1_21_5 : ChatComponentVersion.V1_16;
      ((class_3222)var1).field_13987.method_14367((class_2561)this.componentParser2.getObject2(ComponentVersionConverter.getString(var2, var4)));
   }

   @Override
   public void handleObject(Object var1, String var2, byte[] var3) {
      class_2540 var4 = PacketByteBufs.create();
      var4.writeBytes(var3);
      ((class_3222)var1).field_13987.method_14364(ServerPlayNetworking.createS2CPacket(class_2960.method_12829(var2), var4));
   }
}
