package litebans.core;

import litebans.config.Config;

public class CoreFieldAccess {
   public static Object getObject(LiteBansCore var0) {
      return var0.config;
   }

   public static void handleLiteBansCore(LiteBansCore var0, Object var1) {
      var0.setConfig((Config)var1);
   }
}
