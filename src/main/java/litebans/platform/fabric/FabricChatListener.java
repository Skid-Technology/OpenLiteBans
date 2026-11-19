package litebans.platform.fabric;

import litebans.command.MuteChatHandler;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.platform.ListenerHandle;
import litebans.platform.LiteBansPlatform;
import litebans.platform.Sender;
import litebans.punishment.FlaggedValue;
import litebans.punishment.MuteManager;
import net.fabricmc.fabric.api.message.v1.ServerMessageEvents;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.PlayerChatMessage;
import net.minecraft.network.chat.ChatType.Bound;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.NotNull;

public final class FabricChatListener implements FabricListener {
   private final LiteBansPlatform liteBansPlatform;
   private final MuteManager muteManager;

   public FabricChatListener(@NotNull LiteBansPlatform var1) {
      this.liteBansPlatform = var1;
      this.muteManager = (MuteManager)this.liteBansPlatform.getModule(MuteManager.class);
   }

   public final LiteBansPlatform getLiteBansPlatform() {
      return this.liteBansPlatform;
   }

   public FabricChatListener getFabricChatListener() {
      FabricChatListener var2 = this;
      ServerMessageEvents.ALLOW_CHAT_MESSAGE.register(var2::checkPlayerChatMessage);
      ServerMessageEvents.ALLOW_COMMAND_MESSAGE.register(var2::checkPlayerChatMessage);
      return this;
   }

   public final boolean checkPlayerChatMessage(@NotNull PlayerChatMessage var1, @NotNull ServerPlayer var2, @NotNull Bound var3) {
      Sender var4 = this.liteBansPlatform.getSender(var2);
      String var5 = var1.decoratedContent().getString();
      FlaggedValue var6 = new FlaggedValue(var1, false, 2, null);
      MuteChatHandler var10000 = this.muteManager.getMuteChatHandler();
      Intrinsics.handleObject(var4);
      var10000.handleObject(var6, var5, var4, true);
      return !var6.isFlag();
   }

   public final boolean checkPlayerChatMessage(@NotNull PlayerChatMessage var1, @NotNull CommandSourceStack var2, @NotNull Bound var3) {
      Sender var4 = this.liteBansPlatform.getSender(var2);
      String var5 = var1.decoratedContent().getString();
      FlaggedValue var6 = new FlaggedValue(var1, false, 2, null);
      MuteChatHandler var10000 = this.muteManager.getMuteChatHandler();
      Intrinsics.handleObject(var4);
      MuteChatHandler.handleObjectDefault(var10000, var6, var5, var4, false, 8, null);
      return !var6.isFlag();
   }

   @Override
   public ListenerHandle getListenerHandle() {
      return this.getFabricChatListener();
   }
}
