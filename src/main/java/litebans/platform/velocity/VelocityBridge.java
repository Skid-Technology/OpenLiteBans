package litebans.platform.velocity;

import com.velocitypowered.api.event.command.CommandExecuteEvent;
import com.velocitypowered.api.event.command.CommandExecuteEvent.CommandResult;
import com.velocitypowered.api.event.player.PlayerChatEvent;
import com.velocitypowered.api.event.player.ServerPreConnectEvent;
import com.velocitypowered.api.event.player.PlayerChatEvent.ChatResult;
import com.velocitypowered.api.event.player.ServerPreConnectEvent.ServerResult;
import com.velocitypowered.api.plugin.PluginContainer;
import com.velocitypowered.api.plugin.PluginDescription;
import com.velocitypowered.api.plugin.meta.PluginDependency;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.server.RegisteredServer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import litebans.VelocityPlugin;
import litebans.lib.kotlin.collections.CollectionsKt;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.platform.LiteBansPlatform;
import litebans.platform.PlatformBridge;
import litebans.punishment.LoginKickResult;
import litebans.punishment.PlayerInfo;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.jetbrains.annotations.NotNull;

public final class VelocityBridge implements PlatformBridge {
   private final LiteBansPlatform liteBansPlatform;

   public VelocityBridge(@NotNull LiteBansPlatform var1) {
      this.liteBansPlatform = var1;
   }

   @Override
   public LiteBansPlatform getPlatform() {
      return this.liteBansPlatform;
   }

   public final VelocityPlugin getVelocityPlugin(@NotNull LiteBansPlatform var1) {
      Intrinsics.handleObject(var1, "");
      return (VelocityPlugin)var1;
   }

   public static VelocityPlugin getVelocityPluginDefault(VelocityBridge var0, LiteBansPlatform var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = var0.getPlatform();
      }

      return var0.getVelocityPlugin(var1);
   }

   @Override
   public Object findPlugin(@NotNull String var1) {
      Optional var2 = getVelocityPluginDefault(this, null, 1, null).proxyServer.getPluginManager().getPlugin(var1.toLowerCase(Locale.ENGLISH));
      return var2.isPresent() && ((PluginContainer)var2.get()).getInstance().isPresent() ? ((PluginContainer)var2.get()).getInstance().get() : null;
   }

   public List getList() {
      Iterable var1 = getVelocityPluginDefault(this, null, 1, null).proxyServer.getAllServers();
      Collection var4 = new ArrayList(CollectionsKt.getInt(var1, 10));

      for (Object var7 : var1) {
         RegisteredServer var8 = (RegisteredServer)var7;
         var4.add(var8.getServerInfo().getName());
      }

      return (List)var4;
   }

   @Override
   public String getServerVersion() {
      return getVelocityPluginDefault(this, null, 1, null).proxyServer.getVersion().getVersion();
   }

   @Override
   public List getPluginNames(@NotNull String var1) {
      Iterable var2 = getVelocityPluginDefault(this, null, 1, null).proxyServer.getPluginManager().getPlugins();
      Collection var5 = new ArrayList(CollectionsKt.getInt(var2, 10));

      for (Object var8 : var2) {
         PluginContainer var9 = (PluginContainer)var8;
         var5.add(var9.getDescription());
      }

      var2 = (List)var5;
      var5 = new ArrayList();

      for (Object var28 : var2) {
         PluginDescription var31 = (PluginDescription)var28;
         Iterable var11 = var31.getDependencies();
         boolean var10000;
         if (var11 instanceof Collection && ((Collection)var11).isEmpty()) {
            var10000 = false;
         } else {
            Iterator var13 = var11.iterator();

            while (true) {
               if (!var13.hasNext()) {
                  var10000 = false;
                  break;
               }

               Object var14 = var13.next();
               PluginDependency var15 = (PluginDependency)var14;
               if (Intrinsics.checkObject(var15.getId(), var1.toLowerCase(Locale.ENGLISH))) {
                  var10000 = true;
                  break;
               }
            }
         }

         if (var10000) {
            var5.add(var28);
         }
      }

      var2 = (List)var5;
      var5 = new ArrayList();

      for (Object var29 : var2) {
         PluginDescription var32 = (PluginDescription)var29;
         if (var32.getName().isPresent()) {
            var5.add(var29);
         }
      }

      var2 = (List)var5;
      var5 = new ArrayList(CollectionsKt.getInt(var2, 10));

      for (Object var30 : var2) {
         PluginDescription var33 = (PluginDescription)var30;
         var5.add((String)var33.getName().get());
      }

      return (List)var5;
   }

   @Override
   public void handleObject(@NotNull Object var1, @NotNull CharSequence var2) {
      if (var1 instanceof ServerPreConnectEvent) {
         ((ServerPreConnectEvent)var1).getPlayer().sendMessage((Component)this.getTextComponent(var2));
      } else {
         throw new UnsupportedOperationException();
      }
   }

   @Override
   public void disallowLogin(@NotNull Object var1, @NotNull CharSequence var2, @NotNull String var3, boolean var4, @NotNull String var5) {
      if (var1 instanceof PlayerChatEvent) {
         ((PlayerChatEvent)var1).setResult(ChatResult.denied());
      } else if (var1 instanceof CommandExecuteEvent) {
         ((CommandExecuteEvent)var1).setResult(CommandResult.denied());
      } else if (var1 instanceof ServerPreConnectEvent) {
         ((ServerPreConnectEvent)var1).setResult(ServerResult.denied());
         if (var4 && !((ServerPreConnectEvent)var1).getPlayer().getCurrentServer().isPresent()) {
            ((ServerPreConnectEvent)var1).getPlayer().disconnect((Component)this.getTextComponent(var2));
         }
      }
   }

   public final TextComponent getTextComponent(@NotNull CharSequence var1) {
      return LegacyComponentSerializer.legacySection().deserialize(var1.toString());
   }

   @Override
   public PlayerInfo getLoginInfo(@NotNull Object var1, boolean var2) {
      if (var1 instanceof ServerPreConnectEvent) {
         Player var3 = ((ServerPreConnectEvent)var1).getPlayer();
         return new PlayerInfo(var3.getUsername(), var3.getUniqueId().toString(), var3.getRemoteAddress().getAddress().getHostAddress());
      } else {
         throw new UnsupportedOperationException();
      }
   }

   @Override
   public String getString(@NotNull Object var1) {
      if (var1 instanceof ServerPreConnectEvent) {
         return ((RegisteredServer)((ServerPreConnectEvent)var1).getResult().getServer().get()).getServerInfo().getName();
      } else {
         throw new UnsupportedOperationException();
      }
   }

   @Override
   public boolean isPluginEnabled(@NotNull String var1) {
      return LoginKickResult.checkPlatformBridge(this, var1);
   }

   @Override
   public Collection getPermissionGroups() {
      return this.getList();
   }
}
