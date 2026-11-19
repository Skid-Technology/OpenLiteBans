package litebans.platform.fabric;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.level.ServerPlayer;

public class FabricNetworking {
   public static void handleObject(Object var0, String var1, byte[] var2) {
      ServerPlayNetworking.send((ServerPlayer)var0, new PluginMessagePayload(var1, var2));
   }
}
