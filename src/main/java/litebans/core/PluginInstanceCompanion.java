package litebans.core;

import litebans.lib.kotlin.jvm.internal.DefaultConstructorMarker;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.platform.LiteBansPlatform;
import org.jetbrains.annotations.NotNull;

public final class PluginInstanceCompanion {
   private PluginInstanceCompanion() {
   }

   public final boolean check() {
      return PluginInstance.isFlag();
   }

   public final void handleBoolean(boolean var1) {
      PluginInstance.handleBoolean(var1);
   }

   public final LiteBansPlatform getLiteBansPlatform() {
      if (PluginInstance.liteBansPlatform != null) {
         return PluginInstance.liteBansPlatform;
      } else {
         Intrinsics.handleString3("");
         return null;
      }
   }

   public final void handleLiteBansPlatform(@NotNull LiteBansPlatform var1) {
      PluginInstance.liteBansPlatform = var1;
   }

   public PluginInstanceCompanion(DefaultConstructorMarker var1) {
      this();
   }
}
