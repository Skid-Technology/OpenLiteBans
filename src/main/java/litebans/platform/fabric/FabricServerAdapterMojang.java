package litebans.platform.fabric;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.tree.CommandNode;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import litebans.FabricPlugin;
import litebans.chat.ChatComponentVersion;
import litebans.chat.ComponentParser;
import litebans.chat.ComponentVersionConverter;
import litebans.core.LiteBansLifecycle;
import litebans.platform.LiteBansPlatform;
import litebans.platform.Sender;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.SharedConstants;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.Commands.CommandSelection;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.server.players.PlayerList;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FabricServerAdapterMojang implements FabricServerAdapter {
   public final LiteBansPlatform liteBansPlatform;
   public MinecraftServer minecraftServer;
   public final FabricComponentParserMojang fabricComponentParserMojang = new FabricComponentParserMojang();

   public FabricServerAdapterMojang(LiteBansPlatform var1) {
      this.liteBansPlatform = var1;
   }

   @Override
   public void handleObject(Object var1, String var2) {
      int var3 = this.getInt();
      ChatComponentVersion var4 = var3 >= 770 ? ChatComponentVersion.V1_21_5 : ChatComponentVersion.V1_16;
      ((ServerPlayer)var1).connection.disconnect((Component)this.getFabricComponentParserMojang().getObject2(ComponentVersionConverter.getString(var2, var4)));
   }

   @Override
   public Object getObject() {
      return this.minecraftServer.createCommandSourceStack();
   }

   @Override
   public String getString(Object var1) {
      if (var1 instanceof CommandSourceStack) {
         ServerPlayer var5 = ((CommandSourceStack)var1).getPlayer();
         return var5 == null ? null : this.getString(var5.connection);
      } else {
         if (var1 instanceof Player) {
            InetSocketAddress var2 = (InetSocketAddress)((ServerPlayer)var1).connection.getRemoteAddress();
            InetAddress var3 = var2.getAddress();
            if (var3 != null) {
               return var3.getHostAddress();
            }
         }

         if (var1 instanceof ServerPlayer) {
            SocketAddress var4 = ((ServerPlayer)var1).connection.getRemoteAddress();
            if (var4 instanceof InetSocketAddress) {
               InetAddress var6 = ((InetSocketAddress)var4).getAddress();
               if (var6 != null) {
                  return var6.getHostAddress();
               }
            }
         }

         return var1 instanceof ServerGamePacketListenerImpl ? this.getString(((ServerGamePacketListenerImpl)var1).player) : null;
      }
   }

   @Override
   public boolean check() {
      return this.minecraftServer.usesAuthentication();
   }

   @Override
   public boolean check2() {
      return this.minecraftServer.isSameThread();
   }

   @Nullable
   @Override
   public List getList() {
      PlayerList var1 = this.minecraftServer.getPlayerList();
      return var1 == null ? null : var1.getPlayers();
   }

   @Override
   public void handleRunnable(Runnable var1) {
      this.minecraftServer.execute(var1);
   }

   @Override
   public void handleObject2(Object var1, String var2) {
      this.minecraftServer.getCommands().performPrefixedCommand((CommandSourceStack)var1, var2);
   }

   @Override
   public int getInt() {
      try {
         return SharedConstants.getProtocolVersion();
      } catch (LinkageError var1) {
         this.liteBansPlatform.getLogger().warning("Unable to determine server protocol version");
         return 770;
      }
   }

   @Override
   public Object getObject(Object var1) {
      return var1 instanceof ServerPlayer ? ((ServerPlayer)var1).createCommandSourceStack() : var1;
   }

   @Override
   public void handleObject3(Object var1, String var2) {
      if (var1 instanceof CommandSourceStack) {
         ((CommandSourceStack)var1).sendSystemMessage((Component)this.getFabricComponentParserMojang().getObject2(var2));
      } else {
         this.handleObject3(this.getObject(var1), var2);
      }
   }

   @Override
   public void handleObject(Object var1, String var2, byte[] var3) {
      FabricNetworking.handleObject(var1, var2, var3);
   }

   @Override
   public Sender getSender(Object var1) {
      var1 = this.getObject(var1);
      String var2;
      if (var1 instanceof CommandSourceStack) {
         var2 = ((CommandSourceStack)var1).getTextName();
      } else {
         var2 = var1.toString();
      }

      return (Sender)((FabricPlugin)this.liteBansPlatform).getLiteBansLifecycle().senderCache.remove(var2);
   }

   @Override
   public Sender getSender2(Object var1) {
      return this.getSender(var1, null);
   }

   @Override
   public Sender getSender(Object var1, @Nullable Supplier var2) {
      LiteBansLifecycle var3 = ((FabricPlugin)this.liteBansPlatform).getLiteBansLifecycle();
      Map var4 = var3.senderCache;
      var1 = this.getObject(var1);
      String var5;
      if (var1 instanceof CommandSourceStack) {
         var5 = ((CommandSourceStack)var1).getTextName();
      } else {
         var5 = var1.toString();
      }

      Object var6 = (Sender)var4.get(var5);
      if (var6 == null || ((Sender)var6).getHandle() != var1) {
         var6 = new FabricSender((FabricPlugin)this.liteBansPlatform, var1, var2);
         var3.trimSenderCache();
         var4.put(var5, var6);
      }

      return (Sender)var6;
   }

   @Override
   public void handleFunction(Function var1) {
      CommandRegistrationCallback.EVENT.register(FabricServerAdapterMojang::handleFunction);
   }

   @Override
   public void handleFabricCommand(FabricCommand var1, CommandDispatcher var2) {
      ArgumentBuilder var3 = ((LiteralArgumentBuilder)((LiteralArgumentBuilder)Commands.literal(var1.getName()).requires(this::checkFabricCommand))
            .executes(var1))
         .then(Commands.argument("args", StringArgumentType.greedyString()).suggests(var1).executes(var1));
      var2.getRoot().getChildren().removeIf(FabricServerAdapterMojang::checkFabricCommand);
      var2.register((LiteralArgumentBuilder)var3);
   }

   @Override
   public boolean checkObject(@Nullable Object var1, @NotNull String var2) {
      return FabricPermissions.checkObject(var1, var2);
   }

   @Override
   public void setMinecraftServer(MinecraftServer var1) {
      this.minecraftServer = var1;
   }

   public FabricComponentParserMojang getFabricComponentParserMojang() {
      return this.fabricComponentParserMojang;
   }

   @Override
   public ComponentParser getComponentParser() {
      return this.getFabricComponentParserMojang();
   }

   private static final boolean checkFabricCommand(FabricCommand var0, CommandNode var1) {
      return var1.getName().equals(var0.getName());
   }

   private final boolean checkFabricCommand(FabricCommand var1, CommandSourceStack var2) {
      return this.liteBansPlatform.getSender(var2).hasPermission(var1.getPermission());
   }

   private static final void handleFunction(Function var0, CommandDispatcher var1, CommandBuildContext var2, CommandSelection var3) {
      var0.apply(var1);
   }
}
