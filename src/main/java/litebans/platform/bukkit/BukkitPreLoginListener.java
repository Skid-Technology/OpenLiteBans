package litebans.platform.bukkit;

import java.util.concurrent.atomic.AtomicBoolean;
import litebans.core.LiteBansCore;
import litebans.core.NotificationManager;
import litebans.hook.ServerListPlusBanProvider;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.platform.ListenerHandle;
import litebans.platform.LiteBansPlatform;
import litebans.platform.PlatformDetector;
import litebans.util.DecimalFormats;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent.Result;
import org.jetbrains.annotations.NotNull;

public final class BukkitPreLoginListener implements BukkitListener {
   private final LiteBansPlatform liteBansPlatform;
   private final NotificationManager notificationManager;
   private final LiteBansCore liteBansCore;

   public BukkitPreLoginListener(@NotNull LiteBansPlatform var1) {
      this.liteBansPlatform = var1;
      this.notificationManager = (NotificationManager)this.liteBansPlatform.getModule(NotificationManager.class);
      this.liteBansCore = (LiteBansCore)this.liteBansPlatform.getModule(LiteBansCore.class);
   }

   public final NotificationManager getNotificationManager() {
      return this.notificationManager;
   }

   public final LiteBansCore getLiteBansCore() {
      return this.liteBansCore;
   }

   @EventHandler(
      priority = EventPriority.LOW
   )
   public final void handleAsyncPlayerPreLoginEvent(@NotNull AsyncPlayerPreLoginEvent var1) {
      if (this.liteBansCore.isDebugLevel(1)) {
         long var5 = System.nanoTime();
         this.handleAsyncPlayerPreLoginEvent3(var1);
         long var8 = System.nanoTime() - var5;
         double var10 = (double)var8 / 1000.0 / 1000.0;
         this.liteBansCore.debug("Handled login event: " + DecimalFormats.getString(var10) + " ms.");
      } else {
         this.handleAsyncPlayerPreLoginEvent3(var1);
      }
   }

   private final void handleAsyncPlayerPreLoginEvent2(AsyncPlayerPreLoginEvent var1) {
      long var6 = System.nanoTime();
      this.handleAsyncPlayerPreLoginEvent3(var1);
      long var3 = System.nanoTime() - var6;
      double var5 = (double)var3 / 1000.0 / 1000.0;
      this.liteBansCore.debug("Handled login event: " + DecimalFormats.getString(var5) + " ms.");
   }

   private final void handleAsyncPlayerPreLoginEvent3(AsyncPlayerPreLoginEvent var1) {
      if (var1.getLoginResult() == Result.ALLOWED) {
         if (!this.liteBansPlatform.isDisabling()) {
            if (!this.liteBansPlatform.isFullyEnabled()) {
               this.liteBansPlatform.getLogger().severe("Ignored " + var1.getClass().getSimpleName() + "; plugin is not enabled yet!");
               if (PlatformDetector.isFlag2()) {
                  this.liteBansPlatform
                     .getLogger()
                     .severe("Enable late-bind in spigot.yml to fix this problem, otherwise banned players can join before LiteBans has been loaded.");
               }
            } else {
               this.run();
               this.notificationManager.getLoginCheck().handleObject(var1);
            }
         }
      }
   }

   private final void run() {
      if (PlatformDetector.isFlag3()) {
         if (this.liteBansCore.getServerListPlusHook() != null) {
            Object var10000 = this.liteBansCore.getServerListPlusHook();
            Intrinsics.handleObject(var10000, "");
            AtomicBoolean var1 = ((ServerListPlusBanProvider)var10000).getAtomicBoolean();
            if (var1.compareAndSet(false, true)) {
               var10000 = this.liteBansCore.getServerListPlusHook();
               Intrinsics.handleObject(var10000, "");
               ((ServerListPlusBanProvider)var10000).handleObject(this.liteBansCore.createServerListPlusHook());
            }
         }
      }
   }

   public BukkitPreLoginListener getBukkitPreLoginListener() {
      BukkitPreLoginListener var2 = this;
      LiteBansPlatform var10000 = var2.liteBansPlatform;
      ListenerHandle[] var4 = new ListenerHandle[]{var2};
      var10000.registerListeners(var4);
      return this;
   }

   @Override
   public ListenerHandle getListenerHandle() {
      return this.getBukkitPreLoginListener();
   }
}
