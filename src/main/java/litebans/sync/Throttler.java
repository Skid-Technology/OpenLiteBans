package litebans.sync;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import litebans.core.LiteBansCore;
import litebans.core.PluginModule;
import litebans.lib.kotlin.Lazy;
import litebans.lib.kotlin.LazyKt;
import litebans.platform.LiteBansPlatform;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class Throttler extends PluginModule {
   private final Lazy lazy = LazyKt.getLazy(() -> getLiteBansCore(this));
   private final Set active = Collections.newSetFromMap(new ConcurrentHashMap());

   public Throttler(@NotNull LiteBansPlatform var1) {
      super(var1);
   }

   public final LiteBansCore getLiteBansCore() {
      Lazy var1 = this.lazy;
      return (LiteBansCore)var1.getValue();
   }

   @Override
   public void onEnable() {
      this.getLiteBansCore().onEnable();
      this.active.clear();
      long var1 = (long)this.getLiteBansCore().getConfig().getNotificationThrottleTicks();
      if (var1 > 0L) {
         this.platform.runAsyncTimer(this.active::clear, var1, var1);
      }
   }

   @Override
   public void onReload() {
      this.onEnable();
   }

   public final boolean isThrottled(byte var1, @Nullable String var2) {
      if (var2 == null) {
         return false;
      } else if (this.getLiteBansCore().getConfig().getNotificationThrottleTicks() <= 0) {
         return false;
      } else {
         boolean var3 = !this.active.add("" + var1 + ':' + var2);
         LiteBansCore var4 = this.getLiteBansCore();
         byte var5 = 10;
         if (var4.isDebugLevel(var5)) {
            var4.debug("Throttled! " + var2);
         }

         return var3;
      }
   }

   public final void release(byte var1, @Nullable String var2) {
      if (var2 != null) {
         if (this.getLiteBansCore().getConfig().getNotificationThrottleTicks() > 0) {
            this.active.remove("" + var1 + ':' + var2);
         }
      }
   }

   private static final LiteBansCore getLiteBansCore(Throttler var0) {
      return (LiteBansCore)var0.platform.getModule(LiteBansCore.class);
   }
}
