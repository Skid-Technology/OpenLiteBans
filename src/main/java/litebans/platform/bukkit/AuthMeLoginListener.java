package litebans.platform.bukkit;

import fr.xephi.authme.events.LoginEvent;
import litebans.core.LiteBansCore;
import litebans.database.DatabaseManager;
import litebans.platform.ListenerHandle;
import litebans.platform.LiteBansPlatform;
import litebans.platform.Sender;
import litebans.sync.QueueFeederTask;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.jetbrains.annotations.NotNull;

public final class AuthMeLoginListener implements BukkitListener {
   private final LiteBansPlatform liteBansPlatform;

   public AuthMeLoginListener(@NotNull LiteBansPlatform var1) {
      this.liteBansPlatform = var1;
   }

   @EventHandler(
      priority = EventPriority.LOW,
      ignoreCancelled = true
   )
   public final void handleLoginEvent(@NotNull LoginEvent var1) {
      DatabaseManager var2 = (DatabaseManager)this.liteBansPlatform.getModule(DatabaseManager.class);

      try {
         Player var11 = var1.getPlayer();
         if (var11 == null) {
            return;
         }

         Player var3 = var11;
         Sender var9 = this.liteBansPlatform.getSender(var3);
         String var10 = var9.getName();
         String var6 = var9.getUuidString();
         String var7 = var9.getIp();
         new QueueFeederTask(this.liteBansPlatform, var10, var6, var7).run();
      } catch (Throwable var8) {
         this.liteBansPlatform.getLogger().severe("Handling AuthMe login event failed. Further events will not be handled.");
         LiteBansCore var4 = (LiteBansCore)this.liteBansPlatform.getModule(LiteBansCore.class);
         var2.reportError(var8);
         LiteBansPlatform var10000 = this.liteBansPlatform;
         ListenerHandle[] var5 = new ListenerHandle[]{this};
         var10000.unregisterListeners(var5);
         var4.getToggleState().setFlag(true);
      }
   }

   public AuthMeLoginListener getAuthMeLoginListener() {
      AuthMeLoginListener var2 = this;
      LiteBansPlatform var10000 = var2.liteBansPlatform;
      ListenerHandle[] var4 = new ListenerHandle[]{var2};
      var10000.registerListeners(var4);
      return this;
   }

   @Override
   public ListenerHandle getListenerHandle() {
      return this.getAuthMeLoginListener();
   }
}
