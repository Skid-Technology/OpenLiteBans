package litebans.punishment;

import litebans.platform.PlatformBridge;
import org.jetbrains.annotations.NotNull;

public final class LoginKickResult {
   public static void handlePlatformBridge(PlatformBridge var0, Object var1, CharSequence var2, String var3, boolean var4, String var5, int var6, Object var7) {
      if (var7 != null) {
         throw new UnsupportedOperationException("");
      } else {
         if ((var6 & 2) != 0) {
            var2 = "";
         }

         if ((var6 & 4) != 0) {
            var3 = "[event cancelled]";
         }

         if ((var6 & 8) != 0) {
            var4 = true;
         }

         if ((var6 & 16) != 0) {
            var5 = "KICK_BANNED";
         }

         var0.disallowLogin(var1, var2, var3, var4, var5);
      }
   }

   public static boolean checkPlatformBridge(@NotNull PlatformBridge var0, @NotNull String var1) {
      return var0.findPlugin(var1) != null;
   }
}
