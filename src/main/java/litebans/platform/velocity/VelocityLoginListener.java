package litebans.platform.velocity;

import com.velocitypowered.api.event.EventTask;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.player.ServerConnectedEvent;
import com.velocitypowered.api.event.player.ServerPreConnectEvent;
import com.velocitypowered.api.proxy.server.RegisteredServer;
import java.util.List;
import java.util.Locale;
import litebans.core.LiteBansCore;
import litebans.core.NotificationManager;
import litebans.database.DatabaseManager;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.platform.ListenerHandle;
import litebans.platform.LiteBansPlatform;
import litebans.platform.Sender;
import litebans.sync.QueueFeederTask;
import litebans.sync.SyncQueue;
import org.jetbrains.annotations.NotNull;

public final class VelocityLoginListener implements VelocityListener {
   private final LiteBansPlatform liteBansPlatform;
   private final SyncQueue syncQueue;
   private final LiteBansCore liteBansCore;
   private final NotificationManager notificationManager;
   private final DatabaseManager databaseManager;

   public VelocityLoginListener(@NotNull LiteBansPlatform var1) {
      this.liteBansPlatform = var1;
      this.syncQueue = (SyncQueue)this.liteBansPlatform.getModule(SyncQueue.class);
      this.liteBansCore = (LiteBansCore)this.liteBansPlatform.getModule(LiteBansCore.class);
      this.notificationManager = (NotificationManager)this.liteBansPlatform.getModule(NotificationManager.class);
      this.databaseManager = (DatabaseManager)this.liteBansPlatform.getModule(DatabaseManager.class);
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

   public final DatabaseManager getDatabaseManager() {
      return this.databaseManager;
   }

   @Subscribe
   public final EventTask getEventTask(@NotNull ServerPreConnectEvent var1) {
      return !var1.getResult().isAllowed() ? null : EventTask.async(() -> handleVelocityLoginListener(this, var1));
   }

   @Subscribe
   public final void handleServerConnectedEvent(@NotNull ServerConnectedEvent var1) {
      Sender var2 = this.liteBansPlatform.getSender(var1.getPlayer());
      List var3 = this.liteBansCore.getConfig().getIgnoredIphistoryServers();
      RegisteredServer var4 = var1.getServer();
      String var6 = var4.getServerInfo().getName();
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

   public VelocityLoginListener getVelocityLoginListener() {
      VelocityLoginListener var2 = this;
      LiteBansPlatform var10000 = var2.liteBansPlatform;
      ListenerHandle[] var4 = new ListenerHandle[]{var2};
      var10000.registerListeners(var4);
      return this;
   }

   private static final void handleVelocityLoginListener(VelocityLoginListener var0, ServerPreConnectEvent var1) {
      var0.notificationManager.getLoginCheck().handleObject(var1);
   }

   @Override
   public ListenerHandle getListenerHandle() {
      return this.getVelocityLoginListener();
   }
}