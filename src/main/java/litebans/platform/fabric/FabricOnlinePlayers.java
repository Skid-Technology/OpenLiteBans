package litebans.platform.fabric;

import java.util.List;
import litebans.FabricPlugin;
import litebans.platform.LiteBansPlatform;
import litebans.platform.OnlinePlayers;
import litebans.platform.Sender;

public class FabricOnlinePlayers extends OnlinePlayers {
   public FabricOnlinePlayers(LiteBansPlatform var1) {
      super(var1);
   }

   @Override
   public Sender[] getPlayers() {
      List var1 = this.getFabricPlugin().getFabricServerAdapter().getList();
      return var1 == null ? Sender.EMPTY_ARRAY : var1.stream().map(this.platform::getSender).toArray(FabricOnlinePlayers::getObject);
   }

   @Override
   public int getPlayerCount() {
      List var1 = this.getFabricPlugin().getFabricServerAdapter().getList();
      return var1 == null ? 0 : var1.size();
   }

   private final FabricPlugin getFabricPlugin() {
      return (FabricPlugin)this.platform;
   }

   private static final Object getObject(int var0) {
      return new Sender[var0];
   }
}
