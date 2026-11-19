package litebans.platform.fabric;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import litebans.lib.kotlin.collections.CollectionsKt;
import litebans.lib.kotlin.jvm.internal.Function1;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.platform.LiteBansPlatform;
import litebans.platform.PlatformBridge;
import litebans.punishment.FlaggedValue;
import litebans.punishment.LoginKickResult;
import litebans.punishment.PlayerInfo;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.metadata.ModDependency;
import net.fabricmc.loader.api.metadata.ModMetadata;
import org.jetbrains.annotations.NotNull;

public final class FabricBridge implements PlatformBridge {
   private final LiteBansPlatform liteBansPlatform;

   public FabricBridge(@NotNull LiteBansPlatform var1) {
      this.liteBansPlatform = var1;
   }

   @Override
   public LiteBansPlatform getPlatform() {
      return this.liteBansPlatform;
   }

   public ModContainer getModContainer(@NotNull String var1) {
      Iterable var2 = FabricLoader.getInstance().getAllMods();
      Iterator var4 = var2.iterator();

      Object var10000;
      while (true) {
         if (var4.hasNext()) {
            Object var5 = var4.next();
            ModContainer var6 = (ModContainer)var5;
            if (!Intrinsics.checkObject(var6.getMetadata().getName(), var1)) {
               continue;
            }

            var10000 = var5;
            break;
         }

         var10000 = null;
         break;
      }

      return (ModContainer)var10000;
   }

   @Override
   public List getPluginNames(@NotNull String var1) {
      Iterable var2 = FabricLoader.getInstance().getAllMods();
      Collection var5 = new ArrayList(CollectionsKt.getInt(var2, 10));

      for (Object var8 : var2) {
         ModContainer var9 = (ModContainer)var8;
         var5.add(var9.getMetadata());
      }

      var2 = (List)var5;
      var5 = new ArrayList();

      for (Object var24 : var2) {
         ModMetadata var26 = (ModMetadata)var24;
         Iterable var11 = var26.getDependencies();
         boolean var10000;
         if (var11 instanceof Collection && ((Collection)var11).isEmpty()) {
            var10000 = false;
         } else {
            Iterator var13 = var11.iterator();

            while (true) {
               if (!var13.hasNext()) {
                  var10000 = false;
                  break;
               }

               Object var14 = var13.next();
               ModDependency var15 = (ModDependency)var14;
               if (Intrinsics.checkObject(var15.getModId(), var1)) {
                  var10000 = true;
                  break;
               }
            }
         }

         if (var10000) {
            var5.add(var24);
         }
      }

      var2 = (List)var5;
      var5 = new ArrayList(CollectionsKt.getInt(var2, 10));

      for (Object var25 : var2) {
         ModMetadata var27 = (ModMetadata)var25;
         var5.add(var27.getName());
      }

      return (List)var5;
   }

   @Override
   public Collection getPermissionGroups() {
      return CollectionsKt.getList();
   }

   @Override
   public String getServerVersion() {
      return FabricLoader.getInstance().getModContainer("fabric-api").map(FabricBridge::getString).orElse("null");
   }

   @Override
   public void disallowLogin(@NotNull Object var1, @NotNull CharSequence var2, @NotNull String var3, boolean var4, @NotNull String var5) {
      if (var1 instanceof FabricPreLoginEvent) {
         ((FabricPreLoginEvent)var1).setCancelReason(var2.toString());
      } else if (var1 instanceof FlaggedValue) {
         ((FlaggedValue)var1).setFlag(true);
      }
   }

   @Override
   public PlayerInfo getLoginInfo(@NotNull Object var1, boolean var2) {
      if (var1 instanceof FabricPreLoginEvent) {
         return ((FabricPreLoginEvent)var1).getData();
      } else {
         throw new UnsupportedOperationException();
      }
   }

   public Void getVoid(@NotNull Object var1) {
      throw new UnsupportedOperationException();
   }

   public Void getVoid(@NotNull Object var1, @NotNull CharSequence var2) {
      throw new UnsupportedOperationException();
   }

   @Override
   public boolean isPluginEnabled(@NotNull String var1) {
      return LoginKickResult.checkPlatformBridge(this, var1);
   }

   private static final String getString(ModContainer var0) {
      return var0.getMetadata().getVersion().getFriendlyString();
   }

   private static final String getString(Function1 var0, Object var1) {
      return (String)var0.invoke(var1);
   }

   @Override
   public Object findPlugin(String var1) {
      return this.getModContainer(var1);
   }

   @Override
   public String getString(Object var1) {
      return (String)this.getVoid(var1);
   }

   @Override
   public void handleObject(Object var1, CharSequence var2) {
      this.getVoid(var1, var2);
   }
}
