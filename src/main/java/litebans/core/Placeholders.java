package litebans.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import litebans.platform.LiteBansPlatform;
import org.jetbrains.annotations.Nullable;

public final class Placeholders extends PluginModule {
   public static final PlaceholdersCompanion Companion = new PlaceholdersCompanion(null);
   public static final boolean flag = false;
   private static final Map map = new ConcurrentHashMap();

   public Placeholders(@Nullable LiteBansPlatform var1) {
      super(var1);
   }

   @Override
   public void onEnable() {
   }

   public static final Map getMap() {
      return map;
   }
}
