package litebans.punishment;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import litebans.command.ArgUtils;
import litebans.config.Message;
import litebans.config.Template;
import litebans.config.TemplateData;
import litebans.config.TemplateGroup;
import litebans.core.LiteBansCore;
import litebans.core.NotificationManager;
import litebans.core.Placeholders;
import litebans.database.DatabaseManager;
import litebans.database.EntryReader;
import litebans.database.PunishmentStorage;
import litebans.database.SelectQuery;
import litebans.database.SelectQueryFactory;
import litebans.database.Table;
import litebans.geoip.GeoIp;
import litebans.hook.LuckPermsHook;
import litebans.lib.compress.archivers.tar.TargetParser;
import litebans.lib.kotlin.NoWhenBranchMatchedException;
import litebans.lib.kotlin.Pair;
import litebans.lib.kotlin.ResultFailure;
import litebans.lib.kotlin.collections.CollectionsKt;
import litebans.lib.kotlin.jvm.internal.Function0;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.text.StringsKt;
import litebans.platform.PlatformBridge;
import litebans.platform.Sender;
import litebans.platform.bukkit.BukkitBridge;
import litebans.platform.bukkit.VaultHook;
import litebans.util.Unreachable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class EntryPlaceholders {
   public static final String getString(@NotNull CharSequence var0, @NotNull CharSequence var1, @Nullable Object var2) {
      return Placeholders.Companion.getString(var0, "" + '$' + var1, var2);
   }

   public static final String getString(@NotNull CharSequence var0, @NotNull CharSequence var1, @NotNull Function0 var2) {
      return checkCharSequence2(var0, var1) ? getString(var0, var1, var2.invoke()) : var0.toString();
   }

   public static final CharSequence getCharSequence(@NotNull PunishmentStorage var0, @NotNull PunishmentEntry var1, int var2, int var3) {
      LiteBansCore var4 = (LiteBansCore)var0.getPlatform().getModule(LiteBansCore.class);
      if (var1 != null && var1.getTemplate() != -1) {
         Template var5 = var4.getTemplateManager().getTemplate(var1);
         if (var5 != null) {
            TemplateGroup var6 = var4.getTemplateManager().getTemplateGroup(var1.getType(), var5);
            int var7 = var2;
            TemplateData var8;
            if (var6 != null) {
               if (var2 == -1) {
                  String var10001 = var1.getUuid();
                  Intrinsics.handleObject(var10001);
                  Pair var9 = var0.computeTemplateStep(var10001, var1.getIp(), var6, var5);
                  var7 = (int)Math.floor(((Number)var9.getObject2()).doubleValue()) + var3;
               }

               var8 = var6.getTemplateData(var7);
            } else {
               if (var2 == -1) {
                  String var10002 = var1.getUuid();
                  Intrinsics.handleObject(var10002);
                  var7 = var0.countTemplateUses(var5, var10002, var1.getIp()) + var3;
               }

               var8 = var5.getTemplateData(var7);
            }

            String var10000 = var8.getString();
            CharSequence var10 = var10000 != null ? var10000 : var5.getDefaults().getString();
            if (var10 == null) {
               return null;
            }

            return getString(var0, var10, var5, var7);
         }
      }

      return null;
   }

   public static CharSequence getCharSequenceDefault(PunishmentStorage var0, PunishmentEntry var1, int var2, int var3, int var4, Object var5) {
      if ((var4 & 2) != 0) {
         var2 = -1;
      }

      if ((var4 & 4) != 0) {
         var3 = -1;
      }

      return getCharSequence(var0, var1, var2, var3);
   }

   public static final String getString(@NotNull PunishmentStorage var0, @NotNull CharSequence var1, @NotNull PunishmentRequest var2) {
      return getString(var0, var1, var2.getTemplate(), var2.getIntValue());
   }

   public static final String getString(@NotNull PunishmentStorage var0, @NotNull CharSequence var1, @NotNull Template var2, int var3) {
      Object var4 = Intrinsics.getInt(var2.getId() & 255, -1 & 255) < 0 ? var2.getName() : Message.STRINGS_NULL;
      int var5 = var3 + 1;
      CharSequence var10000 = getString(var1, "templateStep", var5);
      CharSequence[] var6 = new CharSequence[]{"templateName", (CharSequence)var4};
      return ArgUtils.getString(var10000, var6);
   }

   public static final Pair getPair(@NotNull PunishmentStorage var0, @Nullable String var1) {
      if (var1 == null) {
         return new Pair("", "");
      } else {
         Object var2 = null;
         if (var0.getPlatform().createBridge().findPlugin("LuckPerms") != null) {
            var2 = LuckPermsHook.INSTANCE.getObject();
         }

         if (var2 != null) {
            Object var3 = LuckPermsHook.INSTANCE.getObject(var2, var1);
            if (var3 != null) {
               String var10002 = LuckPermsHook.INSTANCE.getString(var3);
               if (var10002 == null) {
                  var10002 = "";
               }

               String var10003 = LuckPermsHook.INSTANCE.getString2(var3);
               if (var10003 == null) {
                  var10003 = "";
               }

               return new Pair(var10002, var10003);
            }
         }

         return new Pair("", "");
      }
   }

   public static final CharSequence getCharSequence(@NotNull PunishmentStorage var0, @NotNull PunishmentEntry var1, @NotNull Sender var2, int var3) {
      CharSequence var4 = getCharSequence(var0, var1, var3, -1);
      if (var4 == null) {
         var4 = var1.isPermanent() ? Message.BANNED_MESSAGE_PERMANENT : Message.BANNED_MESSAGE;
      }

      return getStringDefault(var0, var1, var2, var4, 0L, 8, null);
   }

   public static final String getString(@NotNull PunishmentStorage var0, @NotNull PunishmentEntry var1, @NotNull Sender var2) {
      String var3 = var1.getReason().toString();
      CharSequence var4 = var3.length() == 0 ? Message.KICK_REASON_EMPTY : Message.KICK_REASON;
      CharSequence[] var5 = new CharSequence[]{"reason", var3};
      var4 = ArgUtils.getString(var4, var5);
      return getStringDefault(var0, var1, var2, var4, 0L, 8, null);
   }

   public static final String getString(@NotNull PunishmentStorage var0, @NotNull String var1, @NotNull String var2) {
      CharSequence var10000 = Message.BANNED_MESSAGE_GEOIP.getString("geoip", var1);
      CharSequence[] var4 = new CharSequence[]{"serverOrigin", var2};
      String var3 = ArgUtils.getString(var10000, var4);
      return Message.getString(var3, true);
   }

   public static final CharSequence getCharSequence(
      @NotNull PunishmentStorage var0,
      @NotNull PunishmentEntry var1,
      @Nullable String var2,
      @Nullable String var3,
      @Nullable String var4,
      @Nullable String var5,
      int var6
   ) {
      CharSequence var7 = getCharSequence(var0, var1, var6, -1);
      if (var7 == null) {
         var7 = var1.isPermanent() ? Message.BANNED_MESSAGE_PERMANENT : Message.BANNED_MESSAGE;
      }

      var7 = getCharSequenceDefault(var0, var1, var7, 0L, false, 12, null);
      return getStringDefault(var0, var2, var2, var3, var4, var5, new Date(System.currentTimeMillis()), var7, false, 128, null);
   }

   public static final String getString(
      @NotNull PunishmentStorage var0, @NotNull PunishmentEntry var1, @NotNull Sender var2, @NotNull CharSequence var3, long var4
   ) {
      CharSequence var6 = getCharSequenceDefault(var0, var1, var3, var4, false, 8, null);
      return getStringDefault(var0, var2, var6, false, 4, null);
   }

   public static String getStringDefault(PunishmentStorage var0, PunishmentEntry var1, Sender var2, CharSequence var3, long var4, int var6, Object var7) {
      if ((var6 & 8) != 0) {
         var4 = DatabaseManager.currentTimeMillisDefault(var0.getDatabaseManager(), false, 1, null);
      }

      return getString(var0, var1, var2, var3, var4);
   }

   public static final CharSequence getCharSequence(
      @NotNull PunishmentStorage var0, @NotNull PunishmentEntry var1, @NotNull CharSequence var2, long var3, boolean var5
   ) {
      if (var2.length() == 0) {
         return "";
      } else {
         LiteBansCore var6 = (LiteBansCore)var0.getPlatform().getModule(LiteBansCore.class);
         NotificationManager var7 = (NotificationManager)var0.getPlatform().getModule(NotificationManager.class);
         boolean var9 = var1.isPermanent();
         String var10 = var1.getExecutorUUID();
         String var12 = var1.getType().name();
         String var11 = var12.toLowerCase(Locale.ENGLISH);
         var12 = var1.getServerScope();
         String var13 = var1.getServerOrigin();
         CharSequence var14 = var7.formatDate(var1.getDateStart(), false);
         Object var15 = var1.getRemovedByReason();
         if (var15 == null) {
            var15 = "";
         }

         CharSequence var16 = var9 ? Message.FOREVER : var7.formatDate(var1.getDateEnd(), false);
         long var17 = var1.getRemovedByDate();
         CharSequence var19 = var17 <= 0L ? Message.STRINGS_NULL : var7.formatDate(var17, true);
         long var20 = var3 - var1.getDateStart();
         long var22 = var1.getDateEnd() - var3;
         if (var20 - (long)999 <= 999L) {
            var22 += var20 + (long)450;
         }

         if (var22 <= 999L) {
            var22 += (long)999;
         }

         CharSequence var24 = var1.isExpired(var3) ? Message.EXPIRED : DurationParser.getStringDefault(DurationUnit.durationParser, var22, false, 2, null);
         String var8 = getString2(var2);
         CharSequence var10000 = var8;
         CharSequence[] var25 = new CharSequence[]{"duration", var24};
         var8 = ArgUtils.getString(var10000, var25);
         CharSequence var29 = var0.getCharSequence(var8, var7.getDisplayName(var1.getExecutorName(), var10), var10, var5);
         CharSequence[] var27 = new CharSequence[]{"removalReason", (CharSequence)var15};
         var8 = ArgUtils.getString(var29, var27);
         var10000 = var8;
         var27 = new CharSequence[]{"serverScope", var6.getCharSequence(var12)};
         var10000 = ArgUtils.getString(var10000, var27);
         var27 = new CharSequence[]{"serverOrigin", var6.getCharSequence(var13)};
         var8 = ArgUtils.getString(var10000, var27);
         var10000 = var8;
         var27 = new CharSequence[]{"dateStart", var14};
         var10000 = ArgUtils.getString(var10000, var27);
         var27 = new CharSequence[]{"dateEnd", var16};
         var10000 = ArgUtils.getString(var10000, var27);
         var27 = new CharSequence[]{"dateRemoved", var19};
         var10000 = ArgUtils.getString(var10000, var27);
         var27 = new CharSequence[]{"date", var14};
         var8 = ArgUtils.getString(var10000, var27);
         var10000 = var8;
         var27 = new CharSequence[]{
            "originalDuration",
            (CharSequence)(var9
               ? Message.FOREVER
               : DurationParser.getStringDefault(DurationUnit.durationParser, var1.getDateEnd() - var1.getDateStart(), false, 2, null))
         };
         var8 = ArgUtils.getString(var10000, var27);
         var10000 = var8;
         var27 = new CharSequence[]{"timeSince", DurationParser.getStringDefault(DurationUnit.durationParser, var20, false, 2, null)};
         var8 = ArgUtils.getString(var10000, var27);
         var8 = getString(var8, "uuid", var1.getUuid());
         var10000 = var8;
         var27 = new CharSequence[]{"permanent", var6.getCharSequence(var9)};
         var8 = ArgUtils.getString(var10000, var27);
         var10000 = var8;
         var27 = new CharSequence[]{"ipban", var6.getCharSequence(var1.isIpban())};
         var8 = ArgUtils.getString(var10000, var27);
         var10000 = var8;
         var27 = new CharSequence[]{"silent", var6.getCharSequence(var1.isSilent())};
         var8 = ArgUtils.getString(var10000, var27);
         var10000 = var8;
         var27 = new CharSequence[]{"type", var11};
         var8 = ArgUtils.getString(var10000, var27);
         var8 = getString(var0, var8);
         var10000 = var8;
         var25 = new CharSequence[]{"active", var6.getCharSequence(var1.isActive() && !var1.isExpired(var3))};
         var8 = ArgUtils.getString(var10000, var25);
         var8 = getString(var8, "geoip", () -> getObject(var6, var1));
         var10000 = var8;
         var25 = new CharSequence[]{"reason", var1.getReason()};
         var8 = ArgUtils.getString(var10000, var25);
         if (var5) {
            var8 = Message.getString(var8, true);
         }

         var8 = getString(var8, "idRandom", () -> getObject2(var6, var1));
         var8 = getString(var8, "id", var1.getId());
         return var8;
      }
   }

   public static CharSequence getCharSequenceDefault(
      PunishmentStorage var0, PunishmentEntry var1, CharSequence var2, long var3, boolean var5, int var6, Object var7
   ) {
      if ((var6 & 4) != 0) {
         var3 = DatabaseManager.currentTimeMillisDefault(var0.getDatabaseManager(), false, 1, null);
      }

      if ((var6 & 8) != 0) {
         var5 = true;
      }

      return getCharSequence(var0, var1, var2, var3, var5);
   }

   public static final String getString(@NotNull PunishmentStorage var0, @NotNull PlayerHistory var1, @NotNull CharSequence var2, boolean var3) {
      return getString(var0, var1.getString(), var1.getString(), var1.getUuid(), var1.getIp(), null, var1.getDate(), var2, var3);
   }

   public static final String getString(@NotNull PunishmentStorage var0, @NotNull Sender var1, @NotNull CharSequence var2, boolean var3) {
      String var4 = var2.toString();
      if (checkCharSequence(var4, "player")) {
         if (var0.getPlatform().getPlatformId() == 0) {
            PlatformBridge var10000 = var0.getPlatform().createBridge();
            Intrinsics.handleObject(var10000, "");
            VaultHook var5 = ((BukkitBridge)var10000).getVaultHook();
            var4 = getString(getString(var4, "playerPrefix", () -> getObject(var5, var1)), "playerSuffix", () -> getObject2(var5, var1));
         } else {
            Pair var6 = getPair(var0, var1.getUuidString());
            var4 = getString(getString(var4, "playerPrefix", () -> getObject(var6)), "playerSuffix", () -> getObject2(var6));
         }
      }

      return getString(var0, var1.getName(), var1.getDisplayName(), var1.getUuidString(), var1.getIp(), var1.getServerName(), new java.util.Date(), var4, var3);
   }

   public static String getStringDefault(PunishmentStorage var0, Sender var1, CharSequence var2, boolean var3, int var4, Object var5) {
      if ((var4 & 4) != 0) {
         var3 = true;
      }

      return getString(var0, var1, var2, var3);
   }

   public static final String getString(
      @NotNull PunishmentStorage var0,
      @Nullable String var1,
      @Nullable String var2,
      @Nullable String var3,
      @Nullable String var4,
      @Nullable String var5,
      @Nullable java.util.Date var6,
      @NotNull CharSequence var7,
      boolean var8
   ) {
      LiteBansCore var9 = (LiteBansCore)var0.getPlatform().getModule(LiteBansCore.class);
      ResultFailure var10 = new ResultFailure();
      String var10001 = var5;
      if (var5 == null) {
         var10001 = var9.getConfig().getString2();
      }

      var10.object = var10001;
      String var11 = var7.toString();
      if (StringsKt.checkCharSequence(var11)) {
         return var11;
      } else {
         var11 = getString(var11, "geoip", () -> getObject(var9, var4));
         var11 = getString(getString(var11, "name", var1), "target", var1);
         var11 = getString(var11, "uuid", var3);
         if (checkCharSequence(var11, "player")) {
            String var12 = var9.getConfig().isUseDisplayNames() ? var2 : var1;
            ResultFailure var13 = new ResultFailure();
            var13.object = "";
            ResultFailure var14 = new ResultFailure();
            var14.object = "";
            byte var15 = var0.getPlatform().getPlatformId();
            if (var3 != null && TargetParser.checkString7(var3)) {
               if (var15 == 0) {
                  PlatformBridge var10000 = var0.getPlatform().createBridge();
                  Intrinsics.handleObject(var10000, "");
                  VaultHook var16 = ((BukkitBridge)var10000).getVaultHook();

                  try {
                     UUID var17 = UUID.fromString(var3);
                     var11 = getString(var11, "playerPrefix", () -> getObject(var16, var17));
                     var11 = getString(var11, "playerSuffix", () -> getObject2(var16, var17));
                  } catch (IllegalArgumentException var18) {
                     LiteBansCore.handleObjectDefault(var9, var18, 0, 2, null);
                  }
               } else {
                  Pair var26 = getPair(var0, var3);
                  var13.object = var26.getObject();
                  var14.object = var26.getObject2();
               }
            }

            var11 = getString(var11, "playerPrefix", () -> getObject(var13));
            var11 = getString(var11, "playerSuffix", () -> getObject2(var14));
            CharSequence var28 = getString(
               getString(getString(getString(var11, "playerDisplayName", var12), "playerName", var1), "playerUUID", var3), "playerIP", var4
            );
            CharSequence[] var27 = new CharSequence[]{"playerServer", (CharSequence)var10.object};
            var11 = getString(ArgUtils.getString(var28, var27), "playerLoginDate", () -> getObject(var0, var6));
            var11 = getString(
               getString(
                  getString(getString(var11, "playerBans", () -> getObject(var0, var3, var10)), "playerMutes", () -> getObject2(var0, var3, var10)),
                  "playerWarnings",
                  () -> getObject3(var0, var3, var10)
               ),
               "playerKicks",
               () -> getObject4(var0, var3, var10)
            );
            var11 = getString(var11, "player", var12);
         }

         return !var8 ? var0.getCharSequence(var11).toString() : Message.getString(var0.getCharSequence(var11), true);
      }
   }

   public static String getStringDefault(
      PunishmentStorage var0,
      String var1,
      String var2,
      String var3,
      String var4,
      String var5,
      java.util.Date var6,
      CharSequence var7,
      boolean var8,
      int var9,
      Object var10
   ) {
      if ((var9 & 128) != 0) {
         var8 = true;
      }

      return getString(var0, var1, var2, var3, var4, var5, var6, var7, var8);
   }

   public static final String getString(@NotNull PunishmentStorage var0, @NotNull CharSequence var1) {
      String var2 = var1.toString();

      try {
         var2 = getString(
            getString(
               getString(
                  getString(
                     getString(
                        getString(getString(var2, "activeBans", () -> getObject(var0)), "activeMutes", () -> getObject2(var0)),
                        "activeWarnings",
                        () -> getObject3(var0)
                     ),
                     "totalBans",
                     () -> getObject4(var0)
                  ),
                  "totalMutes",
                  () -> getObject5(var0)
               ),
               "totalWarnings",
               () -> getObject6(var0)
            ),
            "totalKicks",
            () -> getObject7(var0)
         );
      } catch (Exception var5) {
         DatabaseManager var4 = (DatabaseManager)var0.getPlatform().getModule(DatabaseManager.class);
         var4.reportError(var5);
      }

      return var2;
   }

   public static final String getString(@NotNull CharSequence var0) {
      CharSequence var2 = "executor";
      CharSequence var1 = "" + '$' + var2;
      CharSequence[] var3 = new CharSequence[]{"bannerDisplayName", var1};
      CharSequence var10000 = ArgUtils.getString(var0, var3);
      var3 = new CharSequence[]{"banningPlayer", var1};
      var10000 = ArgUtils.getString(var10000, var3);
      var3 = new CharSequence[]{"bannerName", var1};
      var10000 = ArgUtils.getString(var10000, var3);
      var3 = new CharSequence[]{"bannedBy", var1};
      var10000 = ArgUtils.getString(var10000, var3);
      var3 = new CharSequence[]{"mutedBy", var1};
      var10000 = ArgUtils.getString(var10000, var3);
      var3 = new CharSequence[]{"warnedBy", var1};
      var10000 = ArgUtils.getString(var10000, var3);
      var3 = new CharSequence[]{"kickedBy", var1};
      var10000 = ArgUtils.getString(var10000, var3);
      var3 = new CharSequence[]{"kicker", var1};
      var10000 = ArgUtils.getString(var10000, var3);
      var3 = new CharSequence[]{"displayName", var1};
      var10000 = ArgUtils.getString(var10000, var3);
      var3 = new CharSequence[]{"bannedFrom", null};
      CharSequence var4 = "dateStart";
      var3[1] = "" + '$' + var4;
      var10000 = ArgUtils.getString(var10000, var3);
      var3 = new CharSequence[]{"bannedUntil", null};
      var4 = "dateEnd";
      var3[1] = "" + '$' + var4;
      var10000 = ArgUtils.getString(var10000, var3);
      var3 = new CharSequence[]{"banTimeLeft", null};
      var4 = "duration";
      var3[1] = "" + '$' + var4;
      String var15 = ArgUtils.getString(var10000, var3);
      Iterable var28 = PunishmentType.Companion.getList();
      Collection var6 = new ArrayList(CollectionsKt.getInt(var28, 10));

      for (Object var9 : var28) {
         PunishmentType var10 = (PunishmentType)var9;
         String var47;
         switch (EntryPlaceholdersWhenMappings.switchMap[var10.getPunishmentType().ordinal()]) {
            case 1:
               var47 = "banned";
               break;
            case 2:
               var47 = "muted";
               break;
            case 3:
               var47 = "warned";
               break;
            case 4:
               var47 = "kicked";
               break;
            default:
               Unreachable.getVoid();
               throw new NoWhenBranchMatchedException();
         }

         var6.add(var47);
      }

      for (Object var32 : (List)var6) {
         String var7 = (String)var32;
         var10000 = var15;
         CharSequence[] var33 = new CharSequence[]{var7 + "Player", null};
         CharSequence var35 = "playerDisplayName";
         var33[1] = "" + '$' + var35;
         String var16 = ArgUtils.getString(var10000, var33);
         var10000 = var16;
         var33 = new CharSequence[]{var7 + "IP", null};
         var35 = "playerDisplayName";
         var33[1] = "" + '$' + var35;
         var15 = ArgUtils.getString(var10000, var33);
      }

      return var15;
   }

   public static final String getString2(@NotNull CharSequence var0) {
      CharSequence[] var1 = new CharSequence[]{"base", Message.BANNED_MESSAGE_BASE};
      CharSequence var10000 = ArgUtils.getString(var0, var1);
      var1 = new CharSequence[]{"appealMessage", Message.BANNED_MESSAGE_APPEAL};
      return ArgUtils.getString(var10000, var1);
   }

   public static final CharSequence getCharSequence(@NotNull CharSequence var0) {
      return "" + '$' + var0;
   }

   public static final boolean checkCharSequence(@NotNull CharSequence var0, @NotNull CharSequence var1) {
      return StringsKt.checkCharSequence3(var0, "" + '$' + var1, false, 2, null);
   }

   public static final boolean checkCharSequence2(@NotNull CharSequence var0, @NotNull CharSequence var1) {
      return StringsKt.checkCharSequence3(var0, var1, false, 2, null) && checkCharSequence(var0, var1);
   }

   private static final Object getObject(LiteBansCore var0, PunishmentEntry var1) {
      GeoIp var10000 = var0.getGeoIp();
      return var10000 != null ? var10000.getString(var1.getIp()) : null;
   }

   private static final Object getObject2(LiteBansCore var0, PunishmentEntry var1) {
      return var0.getShuffler().getString(var1.getId());
   }

   private static final Object getObject(VaultHook var0, Sender var1) {
      return Message.getString(var0.getString(var1.getHandle()), true);
   }

   private static final Object getObject2(VaultHook var0, Sender var1) {
      return Message.getString(var0.getString2(var1.getHandle()), true);
   }

   private static final Object getObject(Pair var0) {
      return var0.getObject();
   }

   private static final Object getObject2(Pair var0) {
      return var0.getObject2();
   }

   private static final Object getObject(LiteBansCore var0, String var1) {
      return var0.getCountry(var1);
   }

   private static final Object getObject(VaultHook var0, UUID var1) {
      return var0.getString(var1);
   }

   private static final Object getObject2(VaultHook var0, UUID var1) {
      return var0.getString2(var1);
   }

   private static final Object getObject(ResultFailure var0) {
      return var0.object;
   }

   private static final Object getObject2(ResultFailure var0) {
      return var0.object;
   }

   private static final Object getObject(PunishmentStorage var0, java.util.Date var1) {
      return ((NotificationManager)var0.getPlatform().getModule(NotificationManager.class)).formatDate(var1 != null ? var1.getTime() : 0L, true);
   }

   private static final Object getObject(PunishmentStorage var0, String var1, ResultFailure var2) {
      return var0.count(
         SelectQueryFactory.getSelectQueryDefault(
            SelectQuery.selectQueryFactory, var0, var1, (String)var2.object, null, false, null, false, null, null, 252, null
         ),
         PunishmentType.BAN
      );
   }

   private static final Object getObject2(PunishmentStorage var0, String var1, ResultFailure var2) {
      return var0.count(
         SelectQueryFactory.getSelectQueryDefault(
            SelectQuery.selectQueryFactory, var0, var1, (String)var2.object, null, false, null, false, null, null, 252, null
         ),
         PunishmentType.MUTE
      );
   }

   private static final Object getObject3(PunishmentStorage var0, String var1, ResultFailure var2) {
      return var0.count(
         SelectQueryFactory.getSelectQueryDefault(
            SelectQuery.selectQueryFactory, var0, var1, (String)var2.object, null, false, null, false, null, null, 252, null
         ),
         PunishmentType.WARN
      );
   }

   private static final Object getObject4(PunishmentStorage var0, String var1, ResultFailure var2) {
      return var0.count(
         SelectQueryFactory.getSelectQueryDefault(
            SelectQuery.selectQueryFactory, var0, var1, (String)var2.object, null, false, null, false, null, null, 252, null
         ),
         PunishmentType.KICK
      );
   }

   private static final Object getObject(PunishmentStorage var0) {
      return EntryReader.getLong(var0, Table.BANS, null, true, 2, null);
   }

   private static final Object getObject2(PunishmentStorage var0) {
      return EntryReader.getLong(var0, Table.MUTES, null, true, 2, null);
   }

   private static final Object getObject3(PunishmentStorage var0) {
      return EntryReader.getLong(var0, Table.WARNINGS, null, true, 2, null);
   }

   private static final Object getObject4(PunishmentStorage var0) {
      return EntryReader.getLong(var0, Table.BANS, null, false, 6, null);
   }

   private static final Object getObject5(PunishmentStorage var0) {
      return EntryReader.getLong(var0, Table.MUTES, null, false, 6, null);
   }

   private static final Object getObject6(PunishmentStorage var0) {
      return EntryReader.getLong(var0, Table.WARNINGS, null, false, 6, null);
   }

   private static final Object getObject7(PunishmentStorage var0) {
      return EntryReader.getLong(var0, Table.KICKS, null, false, 6, null);
   }
}
