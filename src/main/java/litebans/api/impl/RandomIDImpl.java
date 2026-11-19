package litebans.api.impl;

import litebans.api.RandomID;
import litebans.core.LiteBansCore;
import litebans.platform.LiteBansPlatform;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class RandomIDImpl extends RandomID {
   private final LiteBansPlatform liteBansPlatform;

   public RandomIDImpl(@NotNull LiteBansPlatform var1) {
      this.liteBansPlatform = var1;
   }

   @Override
   public String convert(long var1) {
      LiteBansCore var3 = (LiteBansCore)this.liteBansPlatform.getModule(LiteBansCore.class);
      return var3.getShuffler().getString(var1);
   }

   @Override
   public long reveal(@Nullable String var1) {
      if (var1 == null) {
         return -1L;
      } else {
         LiteBansCore var2 = (LiteBansCore)this.liteBansPlatform.getModule(LiteBansCore.class);
         return var2.getShuffler().getLong(var1);
      }
   }
}
