package litebans.platform;

import java.util.Collection;
import java.util.List;
import litebans.punishment.PlayerInfo;
import org.jetbrains.annotations.NotNull;

public interface PlatformBridge {
   LiteBansPlatform getPlatform();

   Object findPlugin(@NotNull String var1);

   List getPluginNames(@NotNull String var1);

   Collection getPermissionGroups();

   String getServerVersion();

   void disallowLogin(@NotNull Object var1, @NotNull CharSequence var2, @NotNull String var3, boolean var4, @NotNull String var5);

   PlayerInfo getLoginInfo(@NotNull Object var1, boolean var2);

   void handleObject(@NotNull Object var1, @NotNull CharSequence var2);

   String getString(@NotNull Object var1);

   boolean isPluginEnabled(@NotNull String var1);
}
