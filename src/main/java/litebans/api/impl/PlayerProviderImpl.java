package litebans.api.impl;

import litebans.api.PlayerProvider;
import litebans.core.LiteBansCore;
import litebans.platform.LiteBansPlatform;
import org.jetbrains.annotations.NotNull;

public final class PlayerProviderImpl extends PlayerProvider {
   private final LiteBansPlatform liteBansPlatform;
   private final LiteBansCore liteBansCore;

   public PlayerProviderImpl(@NotNull LiteBansPlatform var1) {
      this.liteBansPlatform = var1;
      this.liteBansCore = (LiteBansCore)this.liteBansPlatform.getModule(LiteBansCore.class);
   }

   @Override
   public String provide(@NotNull String var1) {
      return var1;
   }

   @Override
   public String toString() {
      return "PlayerProvider";
   }
}
