package litebans.mixin;

import com.mojang.authlib.GameProfile;
import net.minecraft.network.Connection;
import net.minecraft.server.network.ServerLoginPacketListenerImpl;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin({ServerLoginPacketListenerImpl.class})
public interface AccessLoginData_26_ {
   @Accessor("authenticatedProfile")
   GameProfile getProfile();

   @Accessor("connection")
   Connection getConnection();
}
