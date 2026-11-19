package litebans.platform.fabric;

import litebans.command.MuteChatHandler;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.platform.ListenerHandle;
import litebans.platform.LiteBansPlatform;
import litebans.platform.Sender;
import litebans.punishment.FlaggedValue;
import litebans.punishment.MuteManager;
import net.fabricmc.fabric.api.message.v1.ServerMessageEvents;
import net.minecraft.class_2168;
import net.minecraft.class_3222;
import net.minecraft.class_7471;
import net.minecraft.class_2556.class_7602;
import org.jetbrains.annotations.NotNull;

public final class FabricChatListenerIntermediary implements FabricListener {
   private final LiteBansPlatform liteBansPlatform;
   private final MuteManager muteManager;

   public FabricChatListenerIntermediary(@NotNull LiteBansPlatform var1) {
      this.liteBansPlatform = var1;
      this.muteManager = (MuteManager)this.liteBansPlatform.getModule(MuteManager.class);
   }

   public final LiteBansPlatform getLiteBansPlatform() {
      return this.liteBansPlatform;
   }

   public FabricChatListenerIntermediary getFabricChatListenerIntermediary() {
      FabricChatListenerIntermediary var2 = this;
      ServerMessageEvents.ALLOW_CHAT_MESSAGE.register(var2::checkclass_7471);
      ServerMessageEvents.ALLOW_COMMAND_MESSAGE.register(var2::checkclass_7471);
      return this;
   }

   public final boolean checkclass_7471(@NotNull class_7471 var1, @NotNull class_3222 var2, @NotNull class_7602 var3) {
      Sender var4 = this.liteBansPlatform.getSender(var2);
      String var5 = var1.method_46291().getString();
      FlaggedValue var6 = new FlaggedValue(var1, false, 2, null);
      MuteChatHandler var10000 = this.muteManager.getMuteChatHandler();
      Intrinsics.handleObject(var5);
      Intrinsics.handleObject(var4);
      var10000.handleObject(var6, var5, var4, true);
      return !var6.isFlag();
   }

   public final boolean checkclass_7471(@NotNull class_7471 var1, @NotNull class_2168 var2, @NotNull class_7602 var3) {
      Sender var4 = this.liteBansPlatform.getSender(var2);
      String var5 = var1.method_46291().getString();
      FlaggedValue var6 = new FlaggedValue(var1, false, 2, null);
      MuteChatHandler var10000 = this.muteManager.getMuteChatHandler();
      Intrinsics.handleObject(var5);
      Intrinsics.handleObject(var4);
      MuteChatHandler.handleObjectDefault(var10000, var6, var5, var4, false, 8, null);
      return !var6.isFlag();
   }

   @Override
   public ListenerHandle getListenerHandle() {
      return this.getFabricChatListenerIntermediary();
   }
}
