package litebans.platform.bukkit;

import litebans.command.CommandRunnable;
import litebans.core.NotificationManager;
import litebans.database.DatabaseManager;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.platform.ListenerHandle;
import litebans.platform.LiteBansPlatform;
import litebans.platform.Sender;
import litebans.punishment.LockdownTask;
import litebans.punishment.LoginKickResult;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;
import org.jetbrains.annotations.NotNull;

public final class BukkitLockdownListener implements BukkitListener {
   private final LiteBansPlatform liteBansPlatform;
   private final NotificationManager notificationManager;

   public BukkitLockdownListener(@NotNull LiteBansPlatform var1) {
      this.liteBansPlatform = var1;
      this.notificationManager = (NotificationManager)this.liteBansPlatform.getModule(NotificationManager.class);
   }

   public final LiteBansPlatform getLiteBansPlatform() {
      return this.liteBansPlatform;
   }

   public final NotificationManager getNotificationManager() {
      return this.notificationManager;
   }

   @EventHandler(
      priority = EventPriority.LOW
   )
   public final void handlePlayerLoginEvent(@NotNull PlayerLoginEvent var1) {
      LockdownTask var2 = this.notificationManager.getLockdownTask();
      if (var1.getResult() == Result.ALLOWED && var2 != null) {
         Sender var5 = var2.getPlatform().getSender(var1.getPlayer());
         Intrinsics.handleObject(var5);
         String var7 = "litebans.lockdown.bypass";
         if (!var5.hasPermission(var7)) {
            CommandRunnable var10 = var2;
            String var6 = var2.getString(((DatabaseManager)var10.getPlatform().getModule(DatabaseManager.class)).getString(), false);
            LoginKickResult.handlePlatformBridge(var2.getPlatform().createBridge(), var1, var6, null, false, "KICK_OTHER", 12, null);
         }
      }
   }

   public BukkitLockdownListener getBukkitLockdownListener() {
      BukkitLockdownListener var2 = this;
      LiteBansPlatform var10000 = var2.liteBansPlatform;
      ListenerHandle[] var4 = new ListenerHandle[]{var2};
      var10000.registerListeners(var4);
      return this;
   }

   @Override
   public ListenerHandle getListenerHandle() {
      return this.getBukkitLockdownListener();
   }
}
