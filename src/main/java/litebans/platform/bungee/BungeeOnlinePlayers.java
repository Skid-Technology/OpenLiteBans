package litebans.platform.bungee;

import java.util.Collection;
import litebans.BungeePlugin;
import litebans.lib.kotlin.jvm.internal.MetadataAnnotation;
import litebans.platform.LiteBansPlatform;
import litebans.platform.OnlinePlayers;
import litebans.platform.Sender;
import net.md_5.bungee.api.connection.ProxiedPlayer;

@MetadataAnnotation(
   a = 1
)
public class BungeeOnlinePlayers extends OnlinePlayers {
   public BungeeOnlinePlayers(LiteBansPlatform var1) {
      super(var1);
   }

   @Override
   public int getPlayerCount() {
      Collection var1 = this.getCollection();
      return var1.size();
   }

   private final Collection getCollection() {
      return ((BungeePlugin)this.platform).getProxy().getPlayers();
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
            ProxiedPlayer var6 = (ProxiedPlayer) var6o;
            var3[var4++] = var1.getSender(var6);
         }

         return var3;
      }
   }
}
