package litebans.platform.bungee;

import java.util.List;
import java.util.Locale;
import litebans.BungeePlugin;
import litebans.core.LiteBansCore;
import litebans.core.NotificationManager;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.platform.ListenerHandle;
import litebans.platform.LiteBansPlatform;
import litebans.platform.Sender;
import litebans.punishment.LoginCheck;
import litebans.punishment.ProxyLoginCheck;
import litebans.sync.QueueFeederTask;
import litebans.sync.SyncQueue;
import litebans.util.DecimalFormats;
import net.md_5.bungee.api.connection.Server;
import net.md_5.bungee.api.event.LoginEvent;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.event.ServerConnectedEvent;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;
import org.jetbrains.annotations.NotNull;

public final class BungeeLoginListener implements BungeeListener {
   private final LiteBansPlatform liteBansPlatform;
   private final SyncQueue syncQueue;
   private final LiteBansCore liteBansCore;
   private final NotificationManager notificationManager;

   public BungeeLoginListener(@NotNull LiteBansPlatform var1) {
      this.liteBansPlatform = var1;
      this.syncQueue = (SyncQueue)this.liteBansPlatform.getModule(SyncQueue.class);
      this.liteBansCore = (LiteBansCore)this.liteBansPlatform.getModule(LiteBansCore.class);
      this.notificationManager = (NotificationManager)this.liteBansPlatform.getModule(NotificationManager.class);
      this.liteBansCore.onEnable();
   }

   public final LiteBansPlatform getLiteBansPlatform() {
      return this.liteBansPlatform;
   }

   public final SyncQueue getSyncQueue() {
      return this.syncQueue;
   }

   public final LiteBansCore getLiteBansCore() {
      return this.liteBansCore;
   }

   public final NotificationManager getNotificationManager() {
      return this.notificationManager;
   }

   @EventHandler
   public final void handleLoginEvent(@NotNull LoginEvent var1) {
      if (!var1.isCancelled()) {
         LiteBansPlatform var10001 = this.liteBansPlatform;
         Intrinsics.handleObject(this.liteBansPlatform, "");
         var1.registerIntent((BungeePlugin)var10001);
         ((BungeePlugin)this.liteBansPlatform).runAsync(() -> handleBungeeLoginListener(this, var1));
      }
   }

   @EventHandler
   public final void handleServerConnectEvent(@NotNull ServerConnectEvent var1) {
      if (!var1.isCancelled()) {
         this.notificationManager.getLoginCheck().handleObject(var1);
      }
   }

   @EventHandler
   public final void handleServerConnectedEvent(@NotNull ServerConnectedEvent var1) {
      Sender var2 = this.liteBansPlatform.getSender(var1.getPlayer());
      List var3 = this.liteBansCore.getConfig().getIgnoredIphistoryServers();
      Server var4 = var1.getServer();
      String var6 = var4.getInfo().getName();
      String var5 = var6.toLowerCase(Locale.ENGLISH);
      if (!var3.contains(var5)) {
         new QueueFeederTask(this.liteBansPlatform, var2.getName(), var2.getUuidString(), var2.getIp()).run();
         Intrinsics.handleObject(var2);
         String var9 = "litebans.exempt.dupeip_join";
         if (!var2.hasPermission(var9)) {
            SyncQueue var8 = this.syncQueue;
            Intrinsics.handleObject(var2);
            var8.queueJoin(var2.getName(), var2.getUuidString(), var2.getIp());
         }
      }
   }

   public BungeeLoginListener getBungeeLoginListener() {
      BungeeLoginListener var2 = this;
      LiteBansPlatform var10000 = var2.liteBansPlatform;
      ListenerHandle[] var4 = new ListenerHandle[]{var2};
      var10000.registerListeners(var4);
      return this;
   }

   private static final void handleBungeeLoginListener(BungeeLoginListener var0, LoginEvent var1) {
      try {
         long var2 = System.nanoTime();
         LoginCheck var10000 = var0.notificationManager.getLoginCheck();
         Intrinsics.handleObject(var10000, "");
         ProxyLoginCheck var4 = (ProxyLoginCheck)var10000;
         var4.handleLoginEvent(var1);
         LiteBansCore var5 = var0.liteBansCore;
         byte var6 = 1;
         if (var5.isDebugLevel(var6)) {
            var5.debug("Handled login event: " + DecimalFormats.getString((double)(System.nanoTime() - var2) / 1000.0 / 1000.0) + " ms.");
         }
      } finally {
         var1.completeIntent((Plugin)var0.liteBansPlatform);
      }
   }

   @Override
   public ListenerHandle getListenerHandle() {
      return this.getBungeeLoginListener();
   }
}