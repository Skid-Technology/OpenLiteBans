package litebans.platform;

import litebans.core.PluginModule;

public abstract class OnlinePlayers extends PluginModule {
   public OnlinePlayers(LiteBansPlatform var1) {
      super(var1);
   }

   public abstract Sender[] getPlayers();

   public abstract int getPlayerCount();

   @Override
   public void onEnable() {
   }
}
