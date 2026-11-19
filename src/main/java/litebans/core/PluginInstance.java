package litebans.core;

import litebans.platform.LiteBansPlatform;

public final class PluginInstance {
   public static final PluginInstanceCompanion Companion = new PluginInstanceCompanion(null);
   private static boolean flag;
   public static LiteBansPlatform liteBansPlatform;

   public static final boolean isFlag() {
      return flag;
   }

   public static final void handleBoolean(boolean var0) {
      flag = var0;
   }
}
