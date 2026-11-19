package litebans.core;

import litebans.lib.kotlin.jvm.internal.KtAnnotation2;
import litebans.platform.LiteBansPlatform;

@KtAnnotation2
public abstract class PluginModule {
   public final LiteBansPlatform platform;

   public PluginModule(LiteBansPlatform var1) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         this.platform = var1;
      }
   }

   public void onDisable() {
   }

   public void onReload() {
   }

   public void onLoad() {
   }

   public boolean isSupported() {
      return true;
   }

   public abstract void onEnable();
}
