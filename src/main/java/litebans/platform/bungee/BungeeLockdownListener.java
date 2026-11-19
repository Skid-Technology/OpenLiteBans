package litebans.platform.bungee;

import litebans.command.CommandRunnable;
import litebans.core.MessageSender;
import litebans.core.NotificationManager;
import litebans.database.DatabaseManager;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.text.StringsKt;
import litebans.platform.ListenerHandle;
import litebans.platform.LiteBansPlatform;
import litebans.platform.PlatformBridge;
import litebans.platform.Sender;
import litebans.punishment.LockdownTask;
import litebans.punishment.ServerScope;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.event.EventHandler;
import org.jetbrains.annotations.NotNull;

public final class BungeeLockdownListener implements BungeeListener {
   private final LiteBansPlatform liteBansPlatform;
   private final NotificationManager notificationManager;

   public BungeeLockdownListener(@NotNull LiteBansPlatform var1) {
      this.liteBansPlatform = var1;
      this.notificationManager = (NotificationManager)this.liteBansPlatform.getModule(NotificationManager.class);
   }

   @EventHandler(
      priority = -64
   )
   public final void handleServerConnectEvent(@NotNull ServerConnectEvent var1) {
      boolean var21;
      if (!var1.isCancelled()) {
         Sender var2 = this.liteBansPlatform.getSender(var1.getPlayer());
         LockdownTask var10000 = this.notificationManager.getLockdownTask();
         if (var10000 != null) {
            LockdownTask var3 = var10000;
            PlatformBridge var20 = this.liteBansPlatform.createBridge();
            Intrinsics.handleObject(var20, "");
            ProxyServer var4 = ((BungeeBridge)var20).getBungeePlugin(this.liteBansPlatform).getProxy();
            LockdownTask var5 = var3;
            Intrinsics.handleObject(var2);
            String var8 = "litebans.lockdown.bypass";
            if (!var2.hasPermission(var8)) {
               String var7;
               label54: {
                  var7 = var1.getTarget().getName();
                  if (!ServerScope.serverScopeParser.checkString(var3.getString())) {
                     CommandRunnable var11 = var3;
                     String var19 = ((DatabaseManager)var11.getPlatform().getModule(DatabaseManager.class)).getString();
                     if (!StringsKt.checkString(var3.getString(), var19, true)) {
                        var21 = false;
                        break label54;
                     }
                  }

                  var21 = true;
               }

               if (var21) {
                  Intrinsics.handleObject(var7);
                  LockdownTask.handleObjectDefault(var3, var1, var3.getString(var7, true), false, 4, null);
               }

               Intrinsics.handleObject(var7);
               if (StringsKt.checkString(var3.getString(), var7, true)) {
                  var8 = var3.getString(var7, false);
                  if (var2.getServerName() == null) {
                     String var9;
                     label45: {
                        for (Object var13 : var1.getPlayer().getPendingConnection().getListener().getServerPriority()) {
                           String var14 = (String)var13;
                           Intrinsics.handleObject(var14);
                           if (!StringsKt.checkString(var5.getString(), var14, true)) {
                              var9 = var14;
                              break label45;
                           }
                        }

                        var9 = null;
                     }

                     if (var9 == null) {
                        LockdownTask.handleObjectDefault(var5, var1, var8, false, 4, null);
                     } else {
                        var1.setTarget(var4.getServerInfo(var9));
                        MessageSender.handleSenderDefault(CommandRunnable.messageSender, var2, var8, null, 4, null);
                     }
                  } else {
                     LockdownTask.handleObjectDefault(var3, var1, null, false, 2, null);
                     MessageSender.handleSenderDefault(CommandRunnable.messageSender, var2, var8, null, 4, null);
                  }
               }
            }
         }
      }
   }

   public BungeeLockdownListener getBungeeLockdownListener() {
      BungeeLockdownListener var2 = this;
      LiteBansPlatform var10000 = var2.liteBansPlatform;
      ListenerHandle[] var4 = new ListenerHandle[]{var2};
      var10000.registerListeners(var4);
      return this;
   }

   @Override
   public ListenerHandle getListenerHandle() {
      return this.getBungeeLockdownListener();
   }
}
