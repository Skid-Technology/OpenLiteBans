package litebans.platform.fabric;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.permissions.LevelBasedPermissionSet;
import net.minecraft.server.permissions.Permission.Atom;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FabricPermissions {
   public static boolean checkObject(@Nullable Object var0, @NotNull String var1) {
      if (var0 instanceof CommandSourceStack) {
         return ((CommandSourceStack)var0).permissions() == LevelBasedPermissionSet.OWNER
            ? true
            : ((CommandSourceStack)var0).permissions().hasPermission(Atom.create(var1));
      } else {
         return false;
      }
   }
}
