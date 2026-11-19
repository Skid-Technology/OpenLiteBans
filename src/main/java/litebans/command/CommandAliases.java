package litebans.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import litebans.config.Template;
import litebans.core.LiteBansCore;
import litebans.database.DatabaseManager;
import litebans.lib.kotlin.UninitializedPropertyAccessException;
import litebans.lib.kotlin.collections.ArrayIteratorKt;
import litebans.lib.kotlin.collections.ArraysKt;
import litebans.lib.kotlin.collections.CollectionsKt;
import litebans.lib.kotlin.jvm.internal.DefaultConstructorMarker;
import litebans.lib.kotlin.text.StringsKt;
import litebans.platform.LiteBansPlatform;
import litebans.platform.LiteCommand;
import litebans.platform.Sender;
import litebans.punishment.PunishmentType;
import litebans.sync.ByteKeyedMap;
import litebans.sync.SyncManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class CommandAliases {
   private CommandAliases() {
   }

   public final boolean checkLiteBansPlatform(@NotNull LiteBansPlatform var1, @Nullable String var2) {
      LiteBansCore var3 = (LiteBansCore)var1.getModule(LiteBansCore.class);
      if (!var3.getConfig().isFlag()) {
         List var10000 = var3.getConfig().getIgnoredIphistoryServers();
         String var10001 = var2;
         if (var2 == null) {
            var10001 = var3.getConfig().getString2();
         }

         if (!var10000.contains(var10001)) {
            return true;
         }
      }

      return false;
   }

   public final String[] getStringArray(@NotNull LiteBansCore var1) {
      String[] var4 = new String[]{
         "mute",
         "warn",
         "unban",
         "unmute",
         "unwarn",
         "tempban",
         "tempmute",
         "ipban",
         "banip",
         "ban-ip",
         "ipmute",
         "muteip",
         "tempipban",
         "tempbanip",
         "tempipmute",
         "tempmuteip"
      };
      var4 = var4;
      Collection var5 = new ArrayList();
      int var7 = 0;

      for (int var8 = var4.length; var7 < var8; var7++) {
         String var9 = var4[var7];
         if (var1.isCommandEnabled(var9)) {
            var5.add(var9);
         }
      }

      Collection var2 = (List)var5;
      return /* cast-fix */(String[])(var2.toArray(new String[0]));
   }

   public final void handleLiteBansPlatform(@NotNull LiteBansPlatform var1, int var2) {
      SyncManager var3 = (SyncManager)var1.getModule(SyncManager.class);
      var3.getCacheSyncTask().getCopyOnWriteArrayList().add((byte)var2);
      if (var3.getCacheSyncTask().getAtomicBoolean().compareAndSet(false, true)) {
         var1.execute(var3.getCacheSyncTask());
      }
   }

   public static void handleLiteBansPlatformDefault(CommandAliases var0, LiteBansPlatform var1, int var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = -1;
      }

      var0.handleLiteBansPlatform(var1, var2);
   }

   public final List getList(@Nullable LiteCommand var1, @NotNull String var2, @NotNull Sender var3, @NotNull LiteBansPlatform var4, @NotNull String[] var5) {
      boolean var68;
      Object var69;
      try {
         handleLiteBansPlatformDefault(this, var4, 0, 2, null);
         LiteBansCore var6 = (LiteBansCore)var4.getModule(LiteBansCore.class);
         byte var7 = 8;
         ArrayList var8 = new ArrayList();
         String var9 = (String)ArraysKt.getObject3(var5);
         int var10000 = var5.length;
         String[] var11 = var5;
         int var34 = var10000;
         int var13 = 0;
         int var14 = 0;

         for (int var15 = var5.length; var14 < var15; var14++) {
            String var16 = var11[var14];
            if (StringsKt.checkCharSequence(var16, '-', false, 2, null)) {
               var13++;
            }
         }

         int var10 = var34 - var13;
         if (var4.getPlatformId() == 3) {
            var10--;
         }

         if (var10 > 1) {
            if (StringsKt.checkString(var9, "server:", false, 2, null)) {
               String var39 = StringsKt.getString(var9, "server:", null, 2, null);
               handleLiteBansPlatformDefault(this, var4, var39, var8, 0, 8, null);
            } else if (var10 == 2 && !StringsKt.checkString(var2, "un", false, 2, null)) {
               PunishmentType[] var44 = PunishmentType.values();
               var14 = 0;
               int var53 = var44.length;

               while (true) {
                  if (var14 >= var53) {
                     var69 = null;
                     break;
                  }

                  PunishmentType var56;
                  label237: {
                     var56 = var44[var14];
                     label142:
                     if (!StringsKt.checkString(var2, var56.toString(), false, 2, null) && !StringsKt.checkString2(var2, var56.toString(), false, 2, null)) {
                        if (StringsKt.checkString2(var2, "ip", false, 2, null)) {
                           CharSequence var67 = var2;
                           CharSequence var59 = var56;
                           CharSequence var61 = "ip";
                           if (StringsKt.checkCharSequence2(var67, "" + var59 + var61, false, 2, null)) {
                              break label142;
                           }
                        }

                        var68 = false;
                        break label237;
                     }

                     var68 = true;
                  }

                  if (var68) {
                     var69 = var56;
                     break;
                  }

                  var14++;
               }

               PunishmentType var40 = /* cast-fix */(PunishmentType)(var69);
               if (var40 != null) {
                  Iterable var45 = var6.getTemplateManager().getArrayList();
                  Collection var57 = new ArrayList();

                  for (Object var60 : var45) {
                     Template var62 = (Template)var60;
                     if (var62.getType() == var40) {
                        var57.add(var60);
                     }
                  }

                  for (Object var50o : (List)var57) {
                     Template var50 = (Template) var50o;
                     if (StringsKt.checkString2(var50.getName(), var9, true)) {
                        if (var8.size() >= var7) {
                           return var8;
                        }

                        String var54 = var50.getDefaults().getString4();
                        if (var54 == null || var3.hasPermission(var54)) {
                           var8.add(var50.getName());
                        }
                     }
                  }
               }
            }
         } else {
            Iterator var37 = ArrayIteratorKt.getIterator(var4.getOnlineSenders());

            while (var37.hasNext()) {
               Sender var12 = (Sender)var37.next();
               if (StringsKt.checkString2(var12.getName(), var9, true)) {
                  var8.add(var12.getName());
                  if (var8.size() >= var7) {
                     return var8;
                  }
               }
            }

            if (var6.getConfig().getOfflineTabSize() > 0) {
               String var63 = (String)ArraysKt.getObject2(var5);
               if (var63 == null) {
                  return var8;
               }

               String var47 = var63;
               String var64 = var47.toLowerCase(Locale.ENGLISH);
               if (var64 == null) {
                  return var8;
               }

               String var38 = var64;
               String var41 = "";
               boolean var43 = StringsKt.checkCharSequence(var38, '.', false, 2, null) || StringsKt.checkCharSequence(var38, '*', false, 2, null);
               if (var43) {
                  var41 = String.valueOf(StringsKt.getChar(var38));
                  var38 = var38.substring(1);
               }

               if (!StringsKt.checkCharSequence(var38)) {
                  SyncManager var48 = (SyncManager)var4.getModule(SyncManager.class);
                  int var52 = var48.getInt(StringsKt.getChar(var38));
                  ByteKeyedMap var55 = var48.getByteKeyedMap(var52);
                  if (var55 == null) {
                     this.handleLiteBansPlatform(var4, var52);
                     return var8;
                  }

                  int[] var17 = var48.getIntArray(var38);
                  int var18 = 0;

                  for (int var19 = var17.length; var18 < var19; var18++) {
                     int var20 = var17[var18];
                     Iterable var65 = (Iterable)var55.getMap().get(var20);
                     if (var65 != null) {
                        for (Object var25 : var65) {
                           String var26 = (String)var25;
                           if (StringsKt.checkString(var26, var38, false, 2, null)) {
                              Iterable var28 = var8;
                              boolean var66;
                              if (var28 instanceof Collection && ((Collection)var28).isEmpty()) {
                                 var66 = true;
                              } else {
                                 Iterator var30 = var28.iterator();

                                 while (true) {
                                    if (!var30.hasNext()) {
                                       var66 = true;
                                       break;
                                    }

                                    Object var31 = var30.next();
                                    String var32 = (String)var31;
                                    if (StringsKt.checkString(var32, var26, true)) {
                                       var66 = false;
                                       break;
                                    }
                                 }
                              }

                              if (var66) {
                                 var8.add(var41 + var26);
                                 if (var8.size() >= var7) {
                                    return var8;
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         }

         if (var6.isDebugEnabled()) {
            CharSequence var70 = var2;
            Collection var51 = var8;
            var6.debug(ArgUtils.getCharSequence(var70, Arrays.toString(var51.toArray(new String[0]))));
         }

         return var8;
      } catch (UninitializedPropertyAccessException var36) {
         return CollectionsKt.getList();
      }
   }

   private final void handleLiteBansPlatform(LiteBansPlatform var1, String var2, ArrayList var3, int var4) {
      DatabaseManager var5 = (DatabaseManager)var1.getModule(DatabaseManager.class);
      String[] var7 = new String[]{"global", "local"};
      String[] var6 = var7;
      Collection var13 = var1.createBridge().getPermissionGroups();
      Collection var8 = var5.getHashMap().values();
      HashSet var9 = new HashSet();
      int var10 = 0;

      for (int var11 = var7.length; var10 < var11; var10++) {
         String var12 = var6[var10];
         if (this.checkString(var12, var2, var9, var4)) {
            break;
         }
      }

      for (Object var16o : var13) {
         String var16 = (String) var16o;
         if (this.checkString(var16, var2, var9, var4)) {
            break;
         }
      }

      for (Object var17o : var8) {
         CommandAlias var17 = (CommandAlias) var17o;
         if (this.checkString(var17.getString(), var2, var9, var4)) {
            break;
         }
      }

      var3.addAll(var9);
   }

   public static void handleLiteBansPlatformDefault(CommandAliases var0, LiteBansPlatform var1, String var2, ArrayList var3, int var4, int var5, Object var6) {
      if ((var5 & 8) != 0) {
         var4 = 5;
      }

      var0.handleLiteBansPlatform(var1, var2, var3, var4);
   }

   private final boolean checkString(String var1, String var2, HashSet var3, int var4) {
      if (var1 == null) {
         return false;
      } else {
         if (StringsKt.checkCharSequence(var2) || StringsKt.checkString2(var1, var2, true)) {
            var3.add("server:" + var1);
         }

         return var3.size() >= var4;
      }
   }

   public CommandAliases(DefaultConstructorMarker var1) {
      this();
   }
}
