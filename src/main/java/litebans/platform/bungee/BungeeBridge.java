package litebans.platform.bungee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import litebans.BungeePlugin;
import litebans.lib.kotlin.collections.CollectionsKt;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.jvm.internal.MetadataAnnotation;
import litebans.lib.kotlin.text.StringsKt;
import litebans.platform.LiteBansPlatform;
import litebans.platform.PlatformBridge;
import litebans.platform.Sender;
import litebans.punishment.LoginKickResult;
import litebans.punishment.PlayerInfo;
import net.md_5.bungee.api.AbstractReconnectHandler;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.ReconnectHandler;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ConfigurationAdapter;
import net.md_5.bungee.api.config.ListenerInfo;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.PendingConnection;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.event.LoginEvent;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Cancellable;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginDescription;
import net.md_5.bungee.chat.ComponentSerializer;
import org.jetbrains.annotations.NotNull;

@MetadataAnnotation(
   a = 1
)
public final class BungeeBridge implements PlatformBridge {
   private final LiteBansPlatform liteBansPlatform;
   private boolean flag;
   private boolean flag2;

   public BungeeBridge(@NotNull LiteBansPlatform var1) {
      try {
      this.liteBansPlatform = var1;
      this.flag = true;
      this.flag2 = true;
      BungeeBridge var4 = this;

      boolean var2;
      BungeeBridge var10000;
      try {
         var10000 = var4;
         Class.forName("net.md_5.bungee.api.event.LoginEvent").getMethod("getCancelReasonComponents");
         var2 = true;
      } catch (NoSuchMethodException var6) {
         var10000 = this;
         var2 = false;
      }

      var10000.flag = var2;
      var4 = this;

      try {
         var10000 = var4;
         Class var10001 = Class.forName("net.md_5.bungee.api.plugin.Command");
         Class[] var8 = new Class[]{String.class};
         var10001.getDeclaredMethod("setPermissionMessage", var8);
         var2 = true;
      } catch (NoSuchMethodException var5) {
         var10000 = this;
         var2 = false;
      }

      var10000.flag2 = var2;
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public LiteBansPlatform getPlatform() {
      return this.liteBansPlatform;
   }

   public final boolean isFlag2() {
      return this.flag2;
   }

   public final ProxyServer getProxyServer(@NotNull LiteBansPlatform var1) {
      return this.getBungeePlugin(var1).getProxy();
   }

   public final BungeePlugin getBungeePlugin(@NotNull LiteBansPlatform var1) {
      Intrinsics.handleObject(var1, "");
      return (BungeePlugin)var1;
   }

   public final boolean checkString(@NotNull String var1, @NotNull String var2, @NotNull String var3) {
      try {
         ConfigurationAdapter var5 = this.getProxyServer(this.getPlatform()).getConfigurationAdapter();
         Iterable var7 = var5.getGroups(var1);
         boolean var10000;
         if (var7 instanceof Collection && ((Collection)var7).isEmpty()) {
            var10000 = false;
         } else {
            Iterator var9 = var7.iterator();

            while (true) {
               if (!var9.hasNext()) {
                  var10000 = false;
                  break;
               }

               Object var10 = var9.next();
               String var11 = (String)var10;
               if (StringsKt.checkString(var11, var3, true)) {
                  var10000 = true;
                  break;
               }
            }
         }

         if (!var10000) {
            var7 = var5.getGroups(var2);
            if (var7 instanceof Collection && ((Collection)var7).isEmpty()) {
               var10000 = false;
            } else {
               Iterator var14 = var7.iterator();

               while (true) {
                  if (!var14.hasNext()) {
                     var10000 = false;
                     break;
                  }

                  Object var15 = var14.next();
                  String var16 = (String)var15;
                  if (StringsKt.checkString(var16, var3, true)) {
                     var10000 = true;
                     break;
                  }
               }
            }

            if (!var10000) {
               return false;
            }
         }

         return true;
      } catch (IllegalStateException var12) {
         return false;
      }
   }

   public final ThreadFactory getThreadFactory(@NotNull LiteBansPlatform var1) {
      BungeePlugin var2 = this.getBungeePlugin(var1);
      ExecutorService var10000 = var2.getProxy().getScheduler().unsafe().getExecutorService(var2);
      Intrinsics.handleObject(var10000, "");
      return ((ThreadPoolExecutor)var10000).getThreadFactory();
   }

   public final int getInt() {
      return this.getProxyServer(this.getPlatform()).getPlayers().size();
   }

   @Override
   public Object findPlugin(@NotNull String var1) {
      return ProxyServer.getInstance().getPluginManager().getPlugin(var1);
   }

   @Override
   public List getPluginNames(@NotNull String var1) {
      Iterable var2 = this.getProxyServer(this.getPlatform()).getPluginManager().getPlugins();
      Collection var5 = new ArrayList();

      for (Object var8 : var2) {
         Plugin var9;
         boolean var18;
         label65: {
            var9 = (Plugin)var8;
            if (var9 != null) {
               PluginDescription var10000 = var9.getDescription();
               if (var10000 != null) {
                  Set var17 = var10000.getDepends();
                  if (var17 != null) {
                     var18 = var17.contains(var1);
                     break label65;
                  }
               }
            }

            var18 = false;
         }

         label59: {
            if (!var18) {
               label56: {
                  if (var9 != null) {
                     PluginDescription var19 = var9.getDescription();
                     if (var19 != null) {
                        Set var20 = var19.getSoftDepends();
                        if (var20 != null) {
                           var18 = var20.contains(var1);
                           break label56;
                        }
                     }
                  }

                  var18 = false;
               }

               if (!var18) {
                  var18 = false;
                  break label59;
               }
            }

            var18 = true;
         }

         if (var18) {
            var5.add(var8);
         }
      }

      var2 = (List)var5;
      var5 = new ArrayList(CollectionsKt.getInt(var2, 10));

      for (Object var15 : var2) {
         String var24;
         label43: {
            Plugin var16 = (Plugin)var15;
            if (var16 != null) {
               PluginDescription var23 = var16.getDescription();
               if (var23 != null) {
                  var24 = var23.getName();
                  if (var24 != null) {
                     break label43;
                  }
               }
            }

            var24 = "";
         }

         var5.add(var24);
      }

      return (List)var5;
   }

   @Override
   public Collection getPermissionGroups() {
      return this.getProxyServer(this.getPlatform()).getServers().keySet();
   }

   @Override
   public String getServerVersion() {
      return this.getProxyServer(this.getPlatform()).getVersion();
   }

   public final boolean checkString2(@NotNull String var1, @NotNull String var2, @NotNull String var3) {
      try {
         ConfigurationAdapter var4 = this.getProxyServer(this.getPlatform()).getConfigurationAdapter();
         Iterable var6 = var4.getGroups(var1);
         boolean var10000;
         if (var6 instanceof Collection && ((Collection)var6).isEmpty()) {
            var10000 = false;
         } else {
            Iterator var8 = var6.iterator();

            while (true) {
               if (!var8.hasNext()) {
                  var10000 = false;
                  break;
               }

               Object var9 = var8.next();
               String var10 = (String)var9;
               if (var4.getPermissions(var10).contains(var3)) {
                  var10000 = true;
                  break;
               }
            }
         }

         if (!var10000) {
            var6 = var4.getGroups(var2);
            if (var6 instanceof Collection && ((Collection)var6).isEmpty()) {
               var10000 = false;
            } else {
               Iterator var13 = var6.iterator();

               while (true) {
                  if (!var13.hasNext()) {
                     var10000 = false;
                     break;
                  }

                  Object var14 = var13.next();
                  String var15 = (String)var14;
                  if (var4.getPermissions(var15).contains(var3)) {
                     var10000 = true;
                     break;
                  }
               }
            }

            if (!var10000) {
               return false;
            }
         }

         return true;
      } catch (Exception var11) {
         boolean var5 = var11 instanceof ClassCastException;
         if (var5) {
            this.getPlatform().getLogger().warning("Bungee permission config (in Bungee config.yml) is invalid, ignoring. No players will be exempt.");
         }

         if (!(var11 instanceof IllegalStateException) && !var5) {
            throw var11;
         } else {
            return false;
         }
      }
   }

   public final void handleSender(@NotNull Sender var1, @NotNull String var2) {
      ProxiedPlayer var10000 = (ProxiedPlayer)var1.getHandle();
      Intrinsics.handleObject(var10000, "");
      var10000 = var10000;
      BaseComponent[] var3 = ComponentSerializer.parse(var2);
      var10000.disconnect(Arrays.copyOf(var3, var3.length));
   }

   @Override
   public PlayerInfo getLoginInfo(@NotNull Object var1, boolean var2) {
      if (var1 instanceof ServerConnectEvent) {
         ProxiedPlayer var3 = ((ServerConnectEvent)var1).getPlayer();
         return new PlayerInfo(var3.getName(), var3.getUniqueId().toString(), var3.getAddress().getAddress().getHostAddress());
      } else {
         throw new UnsupportedOperationException();
      }
   }

   @Override
   public void handleObject(@NotNull Object var1, @NotNull CharSequence var2) {
      if (var1 instanceof ServerConnectEvent) {
         if (((ServerConnectEvent)var1).getPlayer().isConnected()) {
            ProxiedPlayer var10000 = ((ServerConnectEvent)var1).getPlayer();
            BaseComponent[] var4 = TextComponent.fromLegacyText(var2.toString());
            var10000.sendMessage(Arrays.copyOf(var4, var4.length));
         }
      } else if (!(var1 instanceof LoginEvent)) {
         throw new UnsupportedOperationException();
      }
   }

   @Override
   public String getString(@NotNull Object var1) {
      if (var1 instanceof ServerConnectEvent) {
         return ((ServerConnectEvent)var1).getTarget().getName();
      } else {
         throw new UnsupportedOperationException();
      }
   }

   @Override
   public void disallowLogin(@NotNull Object var1, @NotNull CharSequence var2, @NotNull String var3, boolean var4, @NotNull String var5) {
      if (var1 instanceof Cancellable) {
         ((Cancellable)var1).setCancelled(true);
      }

      if (var1 instanceof ChatEvent) {
         boolean var7 = StringsKt.checkCharSequence(((ChatEvent)var1).getMessage(), '/', false, 2, null);
         ((ChatEvent)var1).setMessage((var7 ? "/" : "") + var3 + ((ChatEvent)var1).getMessage());
      } else if (var1 instanceof LoginEvent) {
         if (this.flag) {
            LoginEvent var10000 = (LoginEvent)var1;
            BaseComponent[] var8 = TextComponent.fromLegacyText(var2.toString());
            var10000.setCancelReason(Arrays.copyOf(var8, var8.length));
         } else {
            BungeeCancelReasonHolder.bungeeCancelReasonSetter.handleObject(var1, var2);
         }
      } else if (var1 instanceof ServerConnectEvent && var4 && ((ServerConnectEvent)var1).getPlayer().getServer() == null) {
         ProxiedPlayer var10 = ((ServerConnectEvent)var1).getPlayer();
         BaseComponent[] var9 = TextComponent.fromLegacyText(var2.toString());
         var10.disconnect(Arrays.copyOf(var9, var9.length));
      }
   }

   public final ServerInfo getServerInfo(@NotNull Object var1) {
      ProxyServer var2 = this.getProxyServer(this.getPlatform());
      ServerInfo var5;
      if (var1 instanceof PendingConnection) {
         ReconnectHandler var10000 = var2.getReconnectHandler();
         if (var10000 == null) {
            return this.getServerInfo2(var1);
         }

         ReconnectHandler var4 = var10000;
         var5 = var4.getServer(new PendingProxiedPlayer(this.getPlatform(), (PendingConnection)var1));
         if (var5 == null) {
            return this.getServerInfo2(var1);
         }
      } else {
         var5 = null;
      }

      return var5;
   }

   public final ServerInfo getServerInfo2(@NotNull Object var1) {
      ProxyServer var2 = this.getProxyServer(this.getPlatform());
      ServerInfo var10000;
      if (var1 instanceof PendingConnection) {
         ListenerInfo var5 = ((PendingConnection)var1).getListener();
         var10000 = var2.getServerInfo(var5.getDefaultServer());
         if (var10000 == null) {
            var10000 = AbstractReconnectHandler.getForcedHost((PendingConnection)var1);
         }
      } else {
         var10000 = null;
      }

      return var10000;
   }

   @Override
   public boolean isPluginEnabled(@NotNull String var1) {
      return LoginKickResult.checkPlatformBridge(this, var1);
   }
}
