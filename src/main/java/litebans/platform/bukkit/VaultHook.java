package litebans.platform.bukkit;

import java.util.Iterator;
import java.util.Locale;
import java.util.UUID;
import litebans.lib.kotlin.Lazy;
import litebans.lib.kotlin.LazyKt;
import litebans.lib.kotlin.collections.ArrayIteratorKt;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.jvm.internal.MetadataAnnotation;
import litebans.lib.kotlin.text.StringsKt;
import litebans.platform.PlatformBridge;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@MetadataAnnotation(
   a = 0
)
public final class VaultHook {
   private final PlatformBridge platformBridge;
   private final Lazy lazy;
   private final Lazy lazy2;

   public VaultHook(@NotNull PlatformBridge var1) {
      this.platformBridge = var1;
      this.lazy = LazyKt.getLazy(() -> getObject(this));
      this.lazy2 = LazyKt.getLazy(() -> getObject2(this));
   }

   public final PlatformBridge getPlatformBridge() {
      return this.platformBridge;
   }

   public final Object getObject() {
      Lazy var1 = this.lazy;
      return var1.getValue();
   }

   public final Object getObject2() {
      Lazy var1 = this.lazy2;
      return var1.getValue();
   }

   public final boolean checkString(
      @NotNull String var1, @NotNull UUID var2, @NotNull String var3, @NotNull String var4, @NotNull String var5, @NotNull String[] var6
   ) {
      if (StringsKt.checkCharSequence(var1, '#', false, 2, null)) {
         return false;
      } else {
         PlatformBridge var10000 = this.getPlatformBridge().getPlatform().createBridge();
         Intrinsics.handleObject(var10000, "");
         BukkitBridge var7 = (BukkitBridge)var10000;
         Object var10 = this.getObject();
         Permission var21 = var10 instanceof Permission ? (Permission)var10 : null;
         if ((var10 instanceof Permission ? (Permission)var10 : null) == null) {
            return false;
         } else {
            Permission var8 = var21;
            Object var11 = var7.getOfflinePlayer(var2);
            OfflinePlayer var22 = var11 instanceof OfflinePlayer ? (OfflinePlayer)var11 : null;
            if ((var11 instanceof OfflinePlayer ? (OfflinePlayer)var11 : null) == null) {
               return false;
            } else {
               OfflinePlayer var9 = var22;

               try {
                  var10 = Intrinsics.checkObject(var5, "default") ? null : var5;
                  if (var6.length != 0 && var8.hasGroupSupport()) {
                     var11 = var8.getPlayerGroups((String)var10, var9);
                     int var12 = 0;

                     for (int var13 = var6.length; var12 < var13; var12++) {
                        String var14 = var6[var12];
                        Iterator var15 = ArrayIteratorKt.getIterator((Object[])var11);

                        while (var15.hasNext()) {
                           String var16 = (String)var15.next();
                           if (StringsKt.checkString(var14, var16, true)) {
                              return true;
                           }
                        }
                     }
                  }

                  return var8.playerHas((String)var10, var9, var4) || var8.playerHas((String)var10, var9, var4 + '.' + var3.toLowerCase(Locale.ENGLISH));
               } catch (IllegalArgumentException var17) {
                  return false;
               } catch (NoSuchMethodError var18) {
                  return false;
               }
            }
         }
      }
   }

   public final boolean checkObject(@Nullable Object var1, @NotNull String var2, @Nullable String var3) {
      Object var4 = this.getObject();
      Permission var10000 = var4 instanceof Permission ? (Permission)var4 : null;
      if ((var4 instanceof Permission ? (Permission)var4 : null) == null) {
         return false;
      } else {
         Permission var5 = var10000;
         Intrinsics.handleObject(var1, "");
         return var5.playerHas(var3, (OfflinePlayer)var1, var2);
      }
   }

   public static boolean checkObjectDefault(VaultHook var0, Object var1, String var2, String var3, int var4, Object var5) {
      if ((var4 & 4) != 0) {
         var3 = null;
      }

      return var0.checkObject(var1, var2, var3);
   }

   public final String getString(@Nullable Object var1) {
      if (var1 == null) {
         return "";
      } else {
         PlatformBridge var10000 = this.platformBridge;
         Intrinsics.handleObject(this.platformBridge, "");
         BukkitBridge var2 = (BukkitBridge)var10000;
         Object var5 = this.getObject();
         Permission var9 = var5 instanceof Permission ? (Permission)var5 : null;
         if ((var5 instanceof Permission ? (Permission)var5 : null) == null) {
            return "";
         } else {
            Permission var3 = var9;
            Object var6 = this.getObject2();
            Chat var10 = var6 instanceof Chat ? (Chat)var6 : null;
            if ((var6 instanceof Chat ? (Chat)var6 : null) == null) {
               return "";
            } else {
               Chat var4 = var10;
               if (var1 instanceof UUID) {
                  Object var7 = var2.getOfflinePlayer((UUID)var1);
                  OfflinePlayer var12 = var7 instanceof OfflinePlayer ? (OfflinePlayer)var7 : null;
                  if ((var7 instanceof OfflinePlayer ? (OfflinePlayer)var7 : null) == null) {
                     return "";
                  } else {
                     var5 = var12;
                     String var13 = var4.getPlayerPrefix(null, (OfflinePlayer)var5);
                     if (var13 == null) {
                        var13 = "";
                     }

                     return var13;
                  }
               } else {
                  String var11 = var4.getPlayerPrefix((Player)var1);
                  if (var11 == null) {
                     var11 = var4.getGroupPrefix(((Player)var1).getWorld().getName(), var3.getPrimaryGroup((Player)var1));
                  }

                  return var11;
               }
            }
         }
      }
   }

   public final String getString2(@Nullable Object var1) {
      if (var1 == null) {
         return "";
      } else {
         PlatformBridge var10000 = this.platformBridge;
         Intrinsics.handleObject(this.platformBridge, "");
         BukkitBridge var2 = (BukkitBridge)var10000;
         Object var5 = this.getObject();
         Permission var9 = var5 instanceof Permission ? (Permission)var5 : null;
         if ((var5 instanceof Permission ? (Permission)var5 : null) == null) {
            return "";
         } else {
            Permission var3 = var9;
            Object var6 = this.getObject2();
            Chat var10 = var6 instanceof Chat ? (Chat)var6 : null;
            if ((var6 instanceof Chat ? (Chat)var6 : null) == null) {
               return "";
            } else {
               Chat var4 = var10;
               if (var1 instanceof UUID) {
                  Object var7 = var2.getOfflinePlayer((UUID)var1);
                  OfflinePlayer var12 = var7 instanceof OfflinePlayer ? (OfflinePlayer)var7 : null;
                  if ((var7 instanceof OfflinePlayer ? (OfflinePlayer)var7 : null) == null) {
                     return "";
                  } else {
                     var5 = var12;
                     String var13 = var4.getPlayerSuffix(null, (OfflinePlayer)var5);
                     if (var13 == null) {
                        var13 = "";
                     }

                     return var13;
                  }
               } else {
                  String var11 = var4.getPlayerSuffix((Player)var1);
                  if (var11 == null) {
                     var11 = var4.getGroupSuffix(((Player)var1).getWorld().getName(), var3.getPrimaryGroup((Player)var1));
                  }

                  return var11;
               }
            }
         }
      }
   }

   public final Object getObject(@NotNull String var1) {
      PlatformBridge var10000 = this.platformBridge;
      Intrinsics.handleObject(this.platformBridge, "");
      BukkitBridge var2 = (BukkitBridge)var10000;

      try {
         RegisteredServiceProvider var3 = var2.getPlugin(this.getPlatformBridge().getPlatform())
            .getServer()
            .getServicesManager()
            .getRegistration(Class.forName(var1));
         if (var3 != null) {
            return var3.getProvider();
         }
      } catch (NoClassDefFoundError var6) {
      } catch (ClassNotFoundException var7) {
      } catch (IllegalStateException var8) {
         String var9 = var8.getMessage();
         if (var9 == null) {
            var9 = "";
         }

         String var4 = var9;
         if (StringsKt.checkCharSequence3(var4, "zip file", false, 2, null)) {
            this.getPlatformBridge().getPlatform().getLogger().warning("Zip file was closed during access: " + var1);
            return null;
         }

         throw var8;
      }

      return null;
   }

   private static final Object getObject(VaultHook var0) {
      return var0.getObject("net.milkbowl.vault.permission.Permission");
   }

   private static final Object getObject2(VaultHook var0) {
      return var0.getObject("net.milkbowl.vault.chat.Chat");
   }
}
