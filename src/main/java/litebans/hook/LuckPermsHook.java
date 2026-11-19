package litebans.hook;

import java.util.Iterator;
import java.util.OptionalInt;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import litebans.lib.compress.archivers.tar.TargetParser;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.group.Group;
import net.luckperms.api.model.group.GroupManager;
import net.luckperms.api.model.user.User;
import net.luckperms.api.model.user.UserManager;
import org.jetbrains.annotations.NotNull;

public final class LuckPermsHook {
   public static final LuckPermsHook INSTANCE = new LuckPermsHook();

   private LuckPermsHook() {
   }

   public final Object getObject() {
      return LuckPermsProvider.get();
   }

   public final LuckPerms getLuckPerms(@NotNull Object var1) {
      Intrinsics.handleObject(var1, "");
      return (LuckPerms)var1;
   }

   public final User getUser(@NotNull Object var1) {
      Intrinsics.handleObject(var1, "");
      return (User)var1;
   }

   public final Object getObject(@NotNull Object var1, @NotNull String var2) {
      if (!TargetParser.checkString7(var2)) {
         return null;
      } else {
         UserManager var3 = this.getLuckPerms(var1).getUserManager();
         CompletableFuture var4 = var3.loadUser(UUID.fromString(var2));
         return var4.join();
      }
   }

   public final Set getSet(@NotNull Object var1) {
      GroupManager var2 = this.getLuckPerms(var1).getGroupManager();
      return var2.getLoadedGroups();
   }

   public final String getString(@NotNull Object var1) {
      return this.getUser(var1).getCachedData().getMetaData().getPrefix();
   }

   public final String getString2(@NotNull Object var1) {
      return this.getUser(var1).getCachedData().getMetaData().getSuffix();
   }

   public final int getInt(@NotNull Object var1, @NotNull Object var2) {
      Set var3 = this.getSet(var1);
      String var4 = this.getUser(var2).getCachedData().getMetaData().getPrimaryGroup();
      Iterable var6 = var3;
      Iterator var7 = var6.iterator();

      Group var10000;
      while (true) {
         if (var7.hasNext()) {
            Object var8 = var7.next();
            Group var9 = (Group)var8;
            if (!Intrinsics.checkObject(var9.getName(), var4)) {
               continue;
            }

            var10000 = var9;
            break;
         }

         var10000 = null;
         break;
      }

      OptionalInt var5 = var10000 != null ? var10000.getWeight() : OptionalInt.empty();
      return var5.orElse(0);
   }

   public final boolean checkObject(@NotNull Object var1, @NotNull String var2) {
      return this.getUser(var1).getCachedData().getPermissionData().checkPermission(var2).asBoolean();
   }
}
