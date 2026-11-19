package litebans.platform.fabric;

import com.mojang.authlib.GameProfile;
import litebans.core.PluginModule;
import litebans.lib.kotlin.NoWhenBranchMatchedException;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.mixin.AccessLoginData_1_21;
import litebans.platform.ListenerHandle;
import litebans.platform.LiteBansPlatform;
import litebans.platform.LoginListenerRegistry;
import litebans.punishment.PlayerInfo;
import litebans.util.Unreachable;
import net.fabricmc.fabric.api.networking.v1.LoginPacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerLoginConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerLoginNetworking.LoginSynchronizer;
import net.minecraft.class_2535;
import net.minecraft.class_3248;
import net.minecraft.server.MinecraftServer;
import org.jetbrains.annotations.NotNull;

public final class FabricLoginListenerIntermediary3 implements FabricListener {
   private final LiteBansPlatform liteBansPlatform;
   private final FabricServerAdapter fabricServerAdapter;

   public FabricLoginListenerIntermediary3(@NotNull LiteBansPlatform var1, @NotNull FabricServerAdapter var2) {
      this.liteBansPlatform = var1;
      this.fabricServerAdapter = var2;
   }

   public final LiteBansPlatform getLiteBansPlatform() {
      return this.liteBansPlatform;
   }

   public final FabricServerAdapter getFabricServerAdapter() {
      return this.fabricServerAdapter;
   }

   public FabricLoginListenerIntermediary3 getFabricLoginListenerIntermediary3() {
      FabricLoginListenerIntermediary3 var2 = this;
      ServerLoginConnectionEvents.QUERY_START.register(var2::handleclass_3248);
      return this;
   }

   public final void handleclass_3248(@NotNull class_3248 var1, @NotNull MinecraftServer var2, @NotNull LoginPacketSender var3, @NotNull LoginSynchronizer var4) {
      PluginModule var10000 = this.liteBansPlatform.findModule(LoginListenerRegistry.class);
      Intrinsics.handleObject(var10000, "");
      FabricLoginHandler var5 = (FabricLoginHandler)var10000;
      if (var1 instanceof AccessLoginData_1_21) {
         GameProfile var6 = ((AccessLoginData_1_21)var1).getProfile();
         class_2535 var7 = ((AccessLoginData_1_21)var1).getConnection();
         var5.handleObject(var1, var4, new PlayerInfo(var6.name(), var6.id().toString(), this.fabricServerAdapter.getString(var7)), var7);
      } else {
         Unreachable.getVoid();
         throw new NoWhenBranchMatchedException();
      }
   }

   @Override
   public ListenerHandle getListenerHandle() {
      return this.getFabricLoginListenerIntermediary3();
   }
}
