package litebans.platform.bukkit;

import java.util.ArrayList;
import java.util.Collection;
import litebans.BukkitPlugin;
import litebans.lib.kotlin.jvm.internal.MetadataAnnotation;
import litebans.platform.LiteBansPlatform;
import litebans.platform.OnlinePlayers;
import litebans.platform.Sender;
import org.bukkit.entity.Player;

@MetadataAnnotation(
   a = 0
)
public class BukkitOnlinePlayers extends OnlinePlayers {
   public BukkitOnlinePlayers(LiteBansPlatform var1) {
      super(var1);
   }

   @Override
   public int getPlayerCount() {
      Collection var1 = ((BukkitPlugin)this.platform).getServer().getOnlinePlayers();
      return var1.size();
   }

   @Override
   public Sender[] getPlayers() {
      Collection var1 = ((BukkitPlugin)this.platform).getServer().getOnlinePlayers();
      if (var1.isEmpty()) {
         return Sender.EMPTY_ARRAY;
      } else {
         int var2 = var1.size();
         ArrayList var3 = new ArrayList(var2);

         for (Object var5o : var1) {
            Player var5 = (Player) var5o;
            var3.add(this.platform.getSender(var5));
         }

         return /* cast-fix */(Sender[])(var3.toArray(Sender.EMPTY_ARRAY));
      }
   }
}
