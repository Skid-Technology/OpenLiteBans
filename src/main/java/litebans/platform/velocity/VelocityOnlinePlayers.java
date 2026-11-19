package litebans.platform.velocity;

import com.velocitypowered.api.proxy.Player;
import java.util.Collection;
import litebans.VelocityPlugin;
import litebans.lib.kotlin.jvm.internal.MetadataAnnotation;
import litebans.platform.LiteBansPlatform;
import litebans.platform.OnlinePlayers;
import litebans.platform.Sender;

@MetadataAnnotation(
   a = 2
)
public class VelocityOnlinePlayers extends OnlinePlayers {
   public VelocityOnlinePlayers(LiteBansPlatform var1) {
      super(var1);
   }

   @Override
   public int getPlayerCount() {
      Collection var1 = this.getCollection();
      return var1.size();
   }

   private final Collection getCollection() {
      return ((VelocityPlugin)this.platform).proxyServer.getAllPlayers();
   }

   @Override
   public Sender[] getPlayers() {
      LiteBansPlatform var1 = this.platform;
      Collection var2 = this.getCollection();
      if (var2.isEmpty()) {
         return Sender.EMPTY_ARRAY;
      } else {
         Sender[] var3 = new Sender[var2.size()];
         int var4 = 0;

         for (Object var6o : var2) {
            Player var6 = (Player) var6o;
            var3[var4++] = var1.getSender(var6);
         }

         return var3;
      }
   }
}
