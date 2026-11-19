package litebans.platform.bukkit;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.PacketType.Login.Server;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import java.util.Arrays;
import litebans.chat.BaseComponent;
import litebans.chat.ChatColorCode;
import litebans.chat.ComponentSerializer;
import litebans.chat.TextComponent;
import litebans.core.LiteBansCore;
import litebans.lib.kotlin.Lazy;
import litebans.lib.kotlin.LazyKt;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.text.StringsKt;
import litebans.platform.LiteBansPlatform;
import litebans.platform.PlatformBridge;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public final class LoginDisconnectPacketAdapter extends PacketAdapter {
   public static final LoginDisconnectPacketAdapterCompanion Companion = new LoginDisconnectPacketAdapterCompanion(null);
   private final LiteBansPlatform liteBansPlatform;
   private final LiteBansCore liteBansCore;
   private static final Lazy lazy = LazyKt.getLazy(LoginDisconnectPacketAdapter::getPacketType);

   public LoginDisconnectPacketAdapter(@NotNull LiteBansPlatform var1) {
      super(createParams(var1));
      this.liteBansPlatform = var1;
      this.liteBansCore = (LiteBansCore)this.liteBansPlatform.getModule(LiteBansCore.class);
   }

   private static PacketAdapter.AdapterParameteters createParams(LiteBansPlatform var1) {
      PlatformBridge var10001 = var1.createBridge();
      Intrinsics.handleObject(var10001, "");
      Plugin var3 = BukkitBridge.getPluginDefault((BukkitBridge)var10001, null, 1, null);
      PacketType[] var2 = new PacketType[1];
      Object var10004 = LoginDisconnectPacketAdapterCompanion.getObject(Companion);
      Intrinsics.handleObject(var10004, "");
      var2[0] = (PacketType)var10004;
      return PacketAdapter.params(var3, var2).optionAsync();
   }

   public void onPacketSending(@NotNull PacketEvent var1) {
      try {
         if (ProtocolLibHook.checkPlayer(var1.getPlayer())) {
            return;
         }

         PacketContainer var2 = var1.getPacket();
         WrappedChatComponent var3 = (WrappedChatComponent)var2.getChatComponents().read(0);
         LiteBansCore var4 = this.liteBansCore;
         if (var4.isDebugEnabled()) {
            var4.debug("1 " + (var3 != null ? var3.getJson() : null));
         }

         BaseComponent[] var5 = ComponentSerializer.getBaseComponentArray(var3.getJson());
         String var11 = TextComponent.getString(Arrays.copyOf(var5, var5.length));
         LiteBansCore var12 = this.liteBansCore;
         if (var12.isDebugEnabled()) {
            var12.debug("2 " + var11);
         }

         Intrinsics.handleObject(var11);
         if (StringsKt.checkCharSequence3(var11, ChatColorCode.MAGIC, false, 2, null)) {
            BaseComponent[] var6 = TextComponent.getBaseComponentArray(var11);
            WrappedChatComponent var13 = WrappedChatComponent.fromJson(ComponentSerializer.getString(Arrays.copyOf(var6, var6.length)));
            LiteBansCore var14 = this.liteBansCore;
            if (var14.isDebugEnabled()) {
               var14.debug("3 " + var13.getJson());
            }

            var2.getChatComponents().write(0, var13);
         }
      } catch (Throwable var10) {
         LiteBansCore.handleObjectDefault(this.liteBansCore, var10, 0, 2, null);
         ProtocolLibHook.handlePluginModule2(this.liteBansCore, this);
      }
   }

   private static final PacketType getPacketType() {
      PacketType var0;
      try {
         var0 = Server.DISCONNECT;
      } catch (Throwable var1) {
         var0 = null;
      }

      return var0;
   }

   public static final Lazy getLazy() {
      return lazy;
   }
}
