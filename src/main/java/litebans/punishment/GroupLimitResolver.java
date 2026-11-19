package litebans.punishment;

import java.util.Iterator;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.platform.Sender;
import org.jetbrains.annotations.NotNull;

public final class GroupLimitResolver {
   public static final GroupLimits getGroupLimits(@NotNull Iterable var0, @NotNull Sender var1) {
      boolean var10000;
      Iterator var3 = var0.iterator();

      Object var9;
      while (true) {
         if (!var3.hasNext()) {
            var9 = null;
            break;
         }

         Object var4;
         label21: {
            var4 = var3.next();
            GroupLimits var5 = (GroupLimits)var4;
            if (!Intrinsics.checkObject(var5.getString(), "none")) {
               String var8 = var5.getString();
               if (!var1.hasPermission(var8)) {
                  var10000 = false;
                  break label21;
               }
            }

            var10000 = true;
         }

         if (var10000) {
            var9 = var4;
            break;
         }
      }

      return (GroupLimits)var9;
   }
}
