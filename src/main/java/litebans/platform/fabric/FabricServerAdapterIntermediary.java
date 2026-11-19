package litebans.platform.fabric;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.tree.CommandNode;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Collections;
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
import net.minecraft.class_155;
import net.minecraft.class_2168;
import net.minecraft.class_2170;
import net.minecraft.class_2535;
import net.minecraft.class_2561;
import net.minecraft.class_3222;
import net.minecraft.class_3244;
import net.minecraft.class_3324;
import net.minecraft.class_7157;
import net.minecraft.class_2170.class_5364;
import net.minecraft.server.MinecraftServer;
import org.jetbrains.annotations.Nullable;

public class FabricServerAdapterIntermediary implements FabricServerAdapter {
   public final LiteBansPlatform liteBansPlatform;
   public MinecraftServer minecraftServer;
   public final ComponentParser componentParser = new FabricComponentParserReflective();

   public FabricServerAdapterIntermediary(LiteBansPlatform var1) {
      this.liteBansPlatform = var1;
   }

   @Override
   public ComponentParser getComponentParser() {
      return this.componentParser;
   }

   @Override
   public void handleObject(Object var1, String var2) {
      int var3 = this.getInt();
      ChatComponentVersion var4 = var3 >= 770 ? ChatComponentVersion.V1_21_5 : ChatComponentVersion.V1_16;
      ((class_3222)var1).field_13987.method_52396((class_2561)this.getComponentParser().getObject2(ComponentVersionConverter.getString(var2, var4)));
   }

   @Override
   public Object getObject() {
      return this.minecraftServer.method_3739();
   }

   @Override
   public String getString(Object var1) {
      if (var1 instanceof class_2168) {
         class_3222 var5 = ((class_2168)var1).method_44023();
         return var5 == null ? null : this.getString(var5.field_13987);
      } else {
         if (var1 instanceof class_3244) {
            InetSocketAddress var2 = (InetSocketAddress)((class_3244)var1).method_48107();
            if (var2 != null) {
               InetAddress var3 = var2.getAddress();
               if (var3 != null) {
                  return var3.getHostAddress();
               }
            }
         }

         if (var1 instanceof class_2535) {
            SocketAddress var4 = ((class_2535)var1).method_10755();
            if (var4 instanceof InetSocketAddress) {
               InetAddress var6 = ((InetSocketAddress)var4).getAddress();
               if (var6 != null) {
                  return var6.getHostAddress();
               }
            }
         }

         return null;
      }
   }

   @Override
   public boolean check() {
      return this.minecraftServer.method_3828();
   }

   @Override
   public boolean check2() {
      return this.minecraftServer.method_18854();
   }

   @Override
   public List getList() {
      class_3324 var1 = this.minecraftServer.method_3760();
      return var1 == null ? Collections.emptyList() : var1.method_14571();
   }

   @Override
   public void handleRunnable(Runnable var1) {
      this.minecraftServer.method_19537(var1);
   }

   @Override
   public void handleObject2(Object var1, String var2) {
      this.minecraftServer.method_3734().method_44252((class_2168)var1, var2);
   }

   @Override
   public int getInt() {
      try {
         return class_155.method_31372();
      } catch (LinkageError var1) {
         this.liteBansPlatform.getLogger().warning("Unable to determine server protocol version");
         return 770;
      }
   }

   @Override
   public Object getObject(Object var1) {
      return var1 instanceof class_3222 ? ((class_3222)var1).method_64396() : var1;
   }

   @Override
   public void handleObject3(Object var1, String var2) {
      if (var1 instanceof class_2168) {
         ((class_2168)var1).method_45068((class_2561)this.getComponentParser().getObject2(var2));
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
      if (var1 instanceof class_2168) {
         var2 = ((class_2168)var1).method_9214();
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
      if (var1 instanceof class_2168) {
         var5 = ((class_2168)var1).method_9214();
      } else {
         var5 = var1.toString();
      }

      Object var6 = (Sender)var4.get(var5);
      if (var6 == null || ((Sender)var6).getHandle() != var1) {
         var6 = new FabricIntermediarySender((FabricPlugin)this.liteBansPlatform, var1, var2);
         var3.trimSenderCache();
         var4.put(var5, var6);
      }

      return (Sender)var6;
   }

   @Override
   public void handleFunction(Function var1) {
      CommandRegistrationCallback.EVENT.register(FabricServerAdapterIntermediary::handleFunction);
   }

   @Override
   public void handleFabricCommand(FabricCommand var1, CommandDispatcher var2) {
      ArgumentBuilder var3 = ((LiteralArgumentBuilder)((LiteralArgumentBuilder)class_2170.method_9247(var1.getName()).requires(this::checkFabricCommand))
            .executes(var1))
         .then(class_2170.method_9244("args", StringArgumentType.greedyString()).suggests(var1).executes(var1));
      var2.getRoot().getChildren().removeIf(FabricServerAdapterIntermediary::checkFabricCommand);
      var2.register((LiteralArgumentBuilder)var3);
   }

   @Override
   public boolean checkObject(@Nullable Object var1, @Nullable String var2) {
      return !(var1 instanceof class_2168) ? false : ((class_2168)var1).method_9259(3);
   }

   @Override
   public void setMinecraftServer(MinecraftServer var1) {
      this.minecraftServer = var1;
   }

   private static final boolean checkFabricCommand(FabricCommand var0, CommandNode var1) {
      return var1.getName().equals(var0.getName());
   }

   private final boolean checkFabricCommand(FabricCommand var1, class_2168 var2) {
      return this.liteBansPlatform.getSender(var2).hasPermission(var1.getPermission());
   }

   private static final void handleFunction(Function var0, CommandDispatcher var1, class_7157 var2, class_5364 var3) {
      var0.apply(var1);
   }
}
