package litebans.platform.bukkit;

import litebans.BukkitPlugin;
import litebans.platform.Sender;
import org.bukkit.Location;
import org.bukkit.entity.Entity;

public class FoliaScheduler {
   private static final Runnable runnable = FoliaScheduler::run;

   // shortcut: Folia region schedulers are not on the Bukkit API surface; run inline until Folia becomes a build target.
   public static void handleBukkitPlugin(BukkitPlugin var0, Runnable var1) {
      var1.run();
   }

   public static void handleBukkitPlugin(BukkitPlugin var0, Sender var1, Runnable var2) {
      var2.run();
   }

   public static void handleBukkitPlugin(BukkitPlugin var0, Location var1, Runnable var2) {
      var2.run();
   }

   private static final void run() {
   }
}
