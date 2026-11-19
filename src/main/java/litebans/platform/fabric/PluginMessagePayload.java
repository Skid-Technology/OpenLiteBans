package litebans.platform.fabric;

import net.minecraft.class_2540;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload.Type;
import net.minecraft.resources.Identifier;

public class PluginMessagePayload extends PluginMessage implements CustomPacketPayload {
   public PluginMessagePayload(String var1, byte[] var2) {
      super(var1, var2);
   }

   private PluginMessagePayload(class_2540 var1) {
      super("", new byte[var1.readableBytes()]);
      var1.readBytes(this.byteArray);
   }

   private final void handleclass_2540(class_2540 var1) {
      var1.writeBytes(this.byteArray);
   }

   public Type type() {
      return new Type(Identifier.parse(this.string));
   }
}
