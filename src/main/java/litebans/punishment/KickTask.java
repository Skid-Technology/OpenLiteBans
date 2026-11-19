package litebans.punishment;

import litebans.platform.bukkit.BukkitSender;
import org.bukkit.entity.Player;

public class KickTask implements Runnable {
   public final Player player;
   public final String string;
   public final BukkitSender bukkitSender;

   public KickTask(BukkitSender var1, Player var2, String var3) {
      this.player = var2;
      this.string = var3;
      this.bukkitSender = var1;
   }

   @Override
   public void run() {
      this.player.kickPlayer(this.string);
   }
}
