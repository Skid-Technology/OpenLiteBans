package litebans.punishment;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import litebans.command.ArgParser;
import litebans.command.BaseCommand;
import litebans.command.CommandRunnable;
import litebans.command.PagedCommand;
import litebans.config.Message;
import litebans.core.LiteBansCore;
import litebans.core.NotificationManager;
import litebans.database.DatabaseManager;
import litebans.database.EntryReader;
import litebans.database.PunishmentStorage;
import litebans.lib.kotlin.collections.CollectionsKt;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.text.StringsKt;
import litebans.platform.LiteBansPlatform;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class HistoryPaginator {
   public static int getInt(@NotNull PagedCommand var0, @NotNull LiteBansPlatform var1, @Nullable String[] var2, @NotNull Collection var3, int var4) {
      if (var2 == null) {
         return Integer.MAX_VALUE;
      } else {
         int var5 = ArgParser.getInt(var2, var4);
         if (!var3.isEmpty()) {
            var5 = Math.min(var5, var3.size());
         }

         return Math.max(1, var5);
      }
   }

   public static int getInt(PagedCommand var0, LiteBansPlatform var1, String[] var2, Collection var3, int var4, int var5, Object var6) {
      if (var6 != null) {
         throw new UnsupportedOperationException("");
      } else {
         if ((var5 & 8) != 0) {
            var4 = ((LiteBansCore)var1.getModule(LiteBansCore.class)).getConfig().getDefaultHistoryLimit();
         }

         return var0.parsePage(var1, var2, var3, var4);
      }
   }

   public static void handlePagedCommand(
      @NotNull PagedCommand var0,
      @NotNull PunishmentStorage var1,
      @NotNull Collection var2,
      @Nullable PlayerHistory var3,
      @Nullable String[] var4,
      @NotNull BaseCommand var5,
      int var6
   ) {
      boolean var47;
      BaseCommand var7 = var5;
      CommandRunnable var9 = var5;
      long var11 = DatabaseManager.currentTimeMillisDefault((DatabaseManager)var9.getPlatform().getModule(DatabaseManager.class), false, 1, null);
      HashMap var32 = new HashMap();
      if (!StringsKt.checkCharSequence(var0.getCharSequence()) && var3 != null) {
         PunishmentService.reply(
            var5,
            EntryPlaceholders.getString(
               EntryPlaceholders.getString(EntryReader.getCharSequence(var1, var0.getCharSequence(), var3, false, 2, null), "limit", var6),
               "total",
               var2.size()
            )
         );
      }

      for (Object var13o : CollectionsKt.getList(var2, var6)) {
         PunishmentEntry var13 = (PunishmentEntry) var13o;
         String var14 = var13.getUuid();
         PlayerHistory var10000 = var3;
         if (var3 == null) {
            var10000 = var0.lookupHistory(var1, var32, var14);
         }

         PlayerHistory var15 = var10000;
         if (var15 != null) {
            label128: {
               if (var13.isIpban()) {
                  String var46 = var13.getIp();
                  if ((var46 != null ? StringsKt.checkCharSequence(var46, '#', false, 2, null) : false)
                     && !Intrinsics.checkObject(var13.getUuid(), var15.getUuid())) {
                     var47 = true;
                     break label128;
                  }
               }

               var47 = false;
            }

            if (var47) {
               CommandRunnable var33 = var7;
               LiteBansCore var34 = (LiteBansCore)var33.getPlatform().getModule(LiteBansCore.class);
               byte var35 = 100;
               if (var34.isDebugLevel(var35)) {
                  var34.debug("r0 " + var13);
               }
               continue;
            }
         }

         boolean var17 = var14 == null || StringsKt.checkCharSequence(var14, '#', false, 2, null);
         String var18 = var13.getIp();
         if (var17 && !var7.getCurrentSender().isConsole()) {
            CommandRunnable var20 = var7;
            if (((LiteBansCore)var20.getPlatform().getModule(LiteBansCore.class)).getConfig().isSecurityIpban()) {
               var18 = Message.BAN_HIDDEN_IP.toString();
            }
         }

         CharSequence var36 = Message.getCharSequence(var13.getType());
         PlayerHistory var16 = !var17 ? var0.lookupHistory(var1, var32, var14) : new PlayerHistory(var18, null, var18, new Date(var13.getDateStart()));
         if (var16 == null) {
            CommandRunnable var38 = var7;
            LiteBansCore var39 = (LiteBansCore)var38.getPlatform().getModule(LiteBansCore.class);
            byte var41 = 100;
            if (var39.isDebugLevel(var41)) {
               var39.debug("r2 " + var13);
            }
         } else {
            boolean var21 = var1.appliesToTarget(var13);
            if (var21 && var13.getType() != PunishmentType.KICK) {
               CharSequence var23 = var13.isPermanent() ? Message.HISTORY_SUFFIX_ACTIVE : Message.HISTORY_SUFFIX_ACTIVE_TEMP;
               var36 = "" + var36 + var23;
            }

            String var22 = var13.getRemovedByName();
            if (Intrinsics.checkObject("#expired", var22) || !var21 && var13.isExpired(var11)) {
               CharSequence var24 = Message.HISTORY_SUFFIX_EXPIRED;
               var36 = "" + var36 + var24;
            }

            var36 = EntryReader.getCharSequence(var1, var36, var13, var16, false, 4, null);
            CharSequence var42 = null;
            CharSequence var44 = var22;
            if (var44 != null && !StringsKt.checkCharSequence(var44) && !StringsKt.checkCharSequence(var22, '#', false, 2, null)) {
               CommandRunnable var45 = var7;
               var22 = ((NotificationManager)var45.getPlatform().getModule(NotificationManager.class)).getRemoverName(var1, var13);
               if (var22 != null) {
                  Message var48;
                  switch (HistoryPaginatorWhenMappings.switchMap[var13.getType().ordinal()]) {
                     case 1:
                        var48 = Message.HISTORY_UNBAN;
                        break;
                     case 2:
                        var48 = Message.HISTORY_UNMUTE;
                        break;
                     case 3:
                        var48 = Message.HISTORY_UNWARN;
                        break;
                     default:
                        var48 = null;
                  }

                  var42 = var48;
                  if (var42 != null) {
                     CommandRunnable var26 = var7;
                     long var28 = ((DatabaseManager)var26.getPlatform().getModule(DatabaseManager.class)).currentTimeMillis(true) - var13.getRemovedByDate();
                     var42 = ((Message)var42).getString("timeSince", DurationParser.getStringDefault(DurationUnit.durationParser, var28, false, 2, null));
                     var42 = EntryReader.getCharSequence(
                        var1,
                        EntryPlaceholders.getString(
                           EntryReader.getCharSequence(var1, var42, var22, var13.getRemovedByUuid(), false, 4, null), "displayName", var22
                        ),
                        var13,
                        var16,
                        false,
                        4,
                        null
                     );
                  }
               }
            }

            PunishmentService.reply(var7, var36);
            if (var42 != null) {
               PunishmentService.reply(var7, var42);
            }
         }
      }
   }

   public static void handlePagedCommand(
      PagedCommand var0, PunishmentStorage var1, Collection var2, PlayerHistory var3, String[] var4, BaseCommand var5, int var6, int var7, Object var8
   ) {
      if (var8 != null) {
         throw new UnsupportedOperationException("");
      } else {
         if ((var7 & 2) != 0) {
            var3 = null;
         }

         if ((var7 & 4) != 0) {
            var4 = null;
         }

         if ((var7 & 8) != 0) {
            Intrinsics.handleObject(var0, "");
            var5 = (BaseCommand)var0;
         }

         if ((var7 & 16) != 0) {
            var6 = getInt(var0, var1.getPlatform(), var4, var2, 0, 8, null);
         }

         var0.printPage(var1, var2, var3, var4, var5, var6);
      }
   }

   public static PlayerHistory getPlayerHistory(@NotNull PagedCommand var0, @NotNull PunishmentStorage var1, @NotNull HashMap var2, @Nullable String var3) {
      Map var4 = var2;
      Intrinsics.handleObject(var3);
      Object var7 = var4.get(var3);
      Object var10000;
      if (var7 == null) {
         PlayerHistory var9 = var1.findLatestHistory(var3);
         var4.put(var3, var9);
         var10000 = var9;
      } else {
         var10000 = var7;
      }

      return (PlayerHistory)var10000;
   }
}
