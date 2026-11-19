package litebans.punishment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import litebans.api.PlayerProvider;
import litebans.api.impl.EventDispatcher;
import litebans.command.ArgUtils;
import litebans.command.CommandAliases;
import litebans.command.CommandRunnable;
import litebans.command.SilentAbortException;
import litebans.config.Message;
import litebans.config.Template;
import litebans.config.TemplateData;
import litebans.core.LiteBansCore;
import litebans.core.MessageSender;
import litebans.core.NotificationManager;
import litebans.database.DatabaseManager;
import litebans.database.EntryReader;
import litebans.database.PunishmentStorage;
import litebans.lib.compress.archivers.tar.Target;
import litebans.lib.compress.archivers.tar.TargetParser;
import litebans.lib.kotlin.NoWhenBranchMatchedException;
import litebans.lib.kotlin.collections.ArraysKt;
import litebans.lib.kotlin.collections.CollectionsKt;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.text.StringsKt;
import litebans.platform.Sender;
import litebans.sync.SyncManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class PunishmentService {
   public static final String parseServerScope(@NotNull CommandRunnable var0, @NotNull Sender var1, @NotNull String[] var2, @NotNull String var3) {
      ServerScopeParser var4 = ServerScope.serverScopeParser;
      String var5 = ((DatabaseManager)var0.getPlatform().getModule(DatabaseManager.class)).getString();
      ServerScope var7 = var4.getServerScope(var1, CollectionsKt.getList2(Arrays.copyOf(var2, var2.length)), var5, false);
      CharSequence var9 = var7.getString();
      return var9 == null || var9.length() == 0 ? ServerScope.serverScopeParser.getString(var3, var5, var1) : var7.getString();
   }

   public static String parseServerScopeDefault(CommandRunnable var0, Sender var1, String[] var2, String var3, int var4, Object var5) {
      if ((var4 & 4) != 0) {
         var3 = ((LiteBansCore)var0.getPlatform().getModule(LiteBansCore.class)).getConfig().getDefaultServerScope();
      }

      return parseServerScope(var0, var1, var2, var3);
   }

   public static final void reply(@NotNull CommandRunnable var0, @NotNull CharSequence var1) {
      Sender var2 = (Sender)CommandRunnable.messageSender.getThreadLocal().get();
      if (var2 != null) {
         CommandRunnable.messageSender.handleSender(var2, var1, var0.getPlatform());
      }
   }

   public static final PunishmentEntry addEntry(@NotNull PunishmentStorage var0, @NotNull PunishmentEntry var1, boolean var2, boolean var3) {
      if (var2) {
         EventDispatcher.INSTANCE.handlePunishmentStorage2(var0, var0.getPlatform(), var1);
         MuteManager var7 = (MuteManager)var0.getPlatform().getModule(MuteManager.class);
         NotificationManager var8 = (NotificationManager)var0.getPlatform().getModule(NotificationManager.class);
         if (var3) {
            var7.uncache(var1);
         }

         var8.checkPunishmentEntry(var1);
      } else {
         var0.insertEntry(var1);
         var1.setId(var0.lookupId(var1));
         EventDispatcher.INSTANCE.handlePunishmentStorage(var0, var0.getPlatform(), var1);
      }

      return var1;
   }

   public static PunishmentEntry addEntryDefault(PunishmentStorage var0, PunishmentEntry var1, boolean var2, boolean var3, int var4, Object var5) {
      if ((var4 & 2) != 0) {
         var2 = false;
      }

      if ((var4 & 4) != 0) {
         var3 = true;
      }

      return addEntry(var0, var1, var2, var3);
   }

   public static final void broadcast(
      @NotNull PunishmentStorage var0, @NotNull Flag var1, @NotNull CharSequence var2, @Nullable PunishmentType var3, @Nullable String var4
   ) {
      SyncManager var5 = (SyncManager)var0.getPlatform().getModule(SyncManager.class);
      CharSequence var6 = var2;
      if (var3 != null) {
         CharSequence[] var8 = new CharSequence[]{var2, var3, null};
         String var10002 = var4;
         if (var4 == null) {
            var10002 = "";
         }

         var8[2] = var10002;
         var6 = ArraysKt.getString(var8, "\ufeff", null, null, 0, null, null, 62, null);
      }

      SyncManager.publishDefault(var5, var1, var0, var6, 0, 8, null);
   }

   public static void broadcastDefault(PunishmentStorage var0, Flag var1, CharSequence var2, PunishmentType var3, String var4, int var5, Object var6) {
      if ((var5 & 4) != 0) {
         var3 = null;
      }

      if ((var5 & 8) != 0) {
         var4 = null;
      }

      broadcast(var0, var1, var2, var3, var4);
   }

   public static final void enforce(@NotNull PunishmentStorage var0, @NotNull PunishmentEntry var1, int var2) {
      NotificationManager var3 = (NotificationManager)var0.getPlatform().getModule(NotificationManager.class);
      LiteBansCore var4 = (LiteBansCore)var0.getPlatform().getModule(LiteBansCore.class);
      Sender[] var5 = var0.getPlatform().getOnlineSenders();
      Intrinsics.handleObject(var5);
      if (var5.length != 0) {
         String var6 = var1.getServerScope();
         if (var6 == null) {
            LiteBansCore.handleObjectDefault(var4, var1, 0, 2, null);
         } else {
            Sender[] var9 = var5;
            Collection var10 = new ArrayList();
            int var12 = 0;

            for (int var13 = var5.length; var12 < var13; var12++) {
               Sender var14 = var9[var12];
               Intrinsics.handleObject(var14);
               if (var0.appliesTo(var1, var14) && checkPunishmentStorage(var0, var1, var14)) {
                  var10.add(var14);
               }
            }

            for (Object var17 : (List)var10) {
               Sender var11 = (Sender)var17;
               Intrinsics.handleObject(var11);
               CharSequence var18 = EntryPlaceholders.getCharSequence(var0, var1, var11, var2);
               var3.kickSync(var11, var18);
            }
         }
      }
   }

   public static void enforceDefault(PunishmentStorage var0, PunishmentEntry var1, int var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = -1;
      }

      enforce(var0, var1, var2);
   }

   public static final boolean checkPunishmentStorage(@NotNull PunishmentStorage var0, @NotNull PunishmentEntry var1, @NotNull Sender var2) {
      return !var1.isIpban() || Intrinsics.checkObject(var1.getUuid(), var2.getUuidString()) || !var0.existsByUuid(var2.getUuid(), var1.getType());
   }

   public static final PunishmentEntry banLinkedAccount(
      @NotNull PunishmentStorage var0,
      @NotNull DupeIPResult var1,
      @NotNull PunishmentEntry var2,
      @Nullable String var3,
      @Nullable String var4,
      @Nullable String var5,
      @Nullable String var6,
      boolean var7
   ) {
      NotificationManager var10 = (NotificationManager)var0.getPlatform().getModule(NotificationManager.class);
      LiteBansCore var11 = (LiteBansCore)var0.getPlatform().getModule(LiteBansCore.class);
      Sender var10000 = (Sender)CommandRunnable.messageSender.getThreadLocal().get();
      if (var10000 == null) {
         var10000 = var0.getPlatform().getConsole();
      }

      Sender var12 = var10000;
      var0.getPlatform().getLogger().info("Banning linked account: " + var3 + " (linked to " + var1.getName() + " via " + var6 + ')');
      PunishmentType var13 = var2.getType();
      boolean var14 = var2.isSilent();
      long var15 = var2.getDateStart();
      long var17 = var2.getDateEnd();
      Template var29 = var11.getTemplateManager().getTemplate(var2);
      if (var29 == null) {
         var29 = Template.Companion.getTemplate();
      }

      Template var19 = var29;
      PlayerHistory var20 = new PlayerHistory(var3, var5, var6, null, 8, null);
      Intrinsics.handleObject(var12);
      String var22 = var0.getString(var12);
      String var23 = var12.getUuidString();
      String var24 = var2.getServerOrigin();
      String var30 = var2.getServerScope();
      if (var30 == null) {
         var30 = ServerScope.serverScopeParser.getString();
      }

      String var25 = var30;
      PunishmentRequest var26 = new PunishmentRequest(
         var13,
         false,
         var14,
         false,
         false,
         false,
         false,
         false,
         false,
         false,
         false,
         false,
         false,
         true,
         var15,
         var17,
         0L,
         (byte)0,
         var19,
         null,
         false,
         null,
         0,
         null,
         var20,
         null,
         null,
         var12,
         var22,
         var23,
         var25,
         var24,
         null,
         null,
         null,
         null,
         null,
         117121018,
         31,
         null
      );
      var26.getStringBuilder().append(var2.getReason());
      var26.fireStage(PunishmentStage.POST_TARGET);
      PunishmentType var10002 = var26.getPunishmentType();
      PlayerHistory var10003 = var26.getPlayerHistory();
      String var31 = var10003 != null ? var10003.getUuid() : null;
      PlayerHistory var10004 = var26.getPlayerHistory();
      PunishmentEntry var27 = new PunishmentEntry(
         var10002,
         var31,
         var10004 != null ? var10004.getIp() : null,
         var26.getStringBuilder().toString(),
         var2.getExecutorUUID(),
         var2.getExecutorName(),
         var26.getServerScope(),
         var26.getServerOrigin(),
         var26.getDateStart(),
         var26.getDateEnd(),
         var26.getTemplate().getId(),
         var26.isSilent(),
         var26.isIpBan() || var7,
         var2.isActive(),
         0L,
         16384,
         null
      );
      var26.setPunishmentEntry(var27);
      var26.fireStage(PunishmentStage.ENTRY_CREATED);
      addEntryDefault(var0, var27, false, false, 6, null);
      var26.fireStage(PunishmentStage.ENTRY_EMITTED);
      enforceDefault(var0, var27, 0, 2, null);
      var26.fireStage(PunishmentStage.ENTRY_APPLIED);
      var10.handlePunishmentStorage(var0, var27, var3);
      var26.fireStage(PunishmentStage.FINALIZED);
      return var27;
   }

   public static PunishmentEntry banLinkedAccountDefault(
      PunishmentStorage var0, DupeIPResult var1, PunishmentEntry var2, String var3, String var4, String var5, String var6, boolean var7, int var8, Object var9
   ) {
      if ((var8 & 16) != 0) {
         var5 = var1.getUuid();
      }

      if ((var8 & 32) != 0) {
         var6 = var4;
      }

      if ((var8 & 64) != 0) {
         var7 = false;
      }

      return banLinkedAccount(var0, var1, var2, var3, var4, var5, var6, var7);
   }

   public static final CharSequence getCharSequence(@NotNull PunishmentStorage var0, @NotNull PunishmentEntry var1, @Nullable PlayerHistory var2, long var3) {
      CharSequence var10001 = EntryReader.getCharSequence(var0, Message.BAN_COOLDOWN, var2, false, 2, null);
      CharSequence[] var5 = new CharSequence[]{"duration", DurationParser.getStringDefault(DurationUnit.durationParser, var3, false, 2, null)};
      return EntryReader.getCharSequence(
         var0, EntryPlaceholders.getString(ArgUtils.getString(var10001, var5), "seconds", var3 / (long)1000), var1, false, 2, null
      );
   }

   public static final void handlePunishmentStorage(
      @NotNull PunishmentStorage var0,
      @NotNull PunishmentStorage var1,
      @NotNull PunishmentEntry var2,
      long var3,
      long var5,
      long var7,
      @Nullable PlayerHistory var9,
      boolean var10
   ) {
      PunishmentEntry var11 = EntryReader.getPunishmentEntry(var1, var2.getUuid(), var2.getIp(), var2.getType(), var2.getServerScope(), true, false, 32, null);
      if ((var11 != null ? var11.isActive() : false) && checkPunishmentEntry(var11, var3, var5)) {
         long var15 = getLong(var11, var3, var5);
         CommandRunnable.messageSender.getVoid(getCharSequence(var0, var11, var9, var15));
         throw new NoWhenBranchMatchedException();
      } else {
         if (var10) {
            String var10001 = var2.getExecutorUUID();
            PunishmentType var10002 = var2.getType();
            String var10003 = var2.getServerScope();
            if (var10003 == null) {
               var10003 = ServerScope.serverScopeParser.getString();
            }

            PunishmentEntry var12 = (PunishmentEntry)CollectionsKt.getObject2(var1.getList(var10001, var10002, var10003, 1));
            if ((var12 != null ? var12.isActive() : false) && checkPunishmentEntry(var12, var3, var5)) {
               long var16 = getLong(var12, var3, var5);
               CommandRunnable.messageSender.getVoid(getCharSequence(var0, var12, var9, var16));
               throw new NoWhenBranchMatchedException();
            }

            if (var7 > 0L
               && Intrinsics.checkObject(var12 != null ? var12.getRemovedByUuid() : null, var2.getExecutorUUID())
               && !var12.isActive()
               && checkPunishmentEntry(var12, var3, var7)) {
               long var13 = getLong(var12, var3, var7);
               CommandRunnable.messageSender.getVoid(getCharSequence(var0, var12, var9, var13));
               throw new NoWhenBranchMatchedException();
            }
         }
      }
   }

   public static void handlePunishmentStorageDefault(
      PunishmentStorage var0,
      PunishmentStorage var1,
      PunishmentEntry var2,
      long var3,
      long var5,
      long var7,
      PlayerHistory var9,
      boolean var10,
      int var11,
      Object var12
   ) {
      if ((var11 & 64) != 0) {
         var10 = true;
      }

      handlePunishmentStorage(var0, var1, var2, var3, var5, var7, var9, var10);
   }

   private static final boolean checkPunishmentEntry(PunishmentEntry var0, long var1, long var3) {
      return var1 > var0.getDateStart() && var1 - var0.getDateStart() <= var3;
   }

   private static final long getLong(PunishmentEntry var0, long var1, long var3) {
      return Math.abs(var1 - var0.getDateStart() - (var3 + (long)1000));
   }

   public static final String lookupUuid(@NotNull CommandRunnable var0, @NotNull String var1) {
      String var2 = TargetParser.INSTANCE.getTarget(var1).getType() == 0 ? PlayerProvider.get().provide(var1) : var1;
      Intrinsics.handleObject(var2);
      return var2;
   }

   public static final String resolveRandomId(@NotNull CommandRunnable var0, @NotNull String var1) {
      if (!TargetParser.INSTANCE.checkString6(var1)) {
         return var1;
      } else {
         long var2 = ((LiteBansCore)var0.getPlatform().getModule(LiteBansCore.class)).getShuffler().getLong(var1);
         return var2 == -1L ? var1 : String.valueOf(var2);
      }
   }

   public static final Sender findOnline(@NotNull CommandRunnable var0, @NotNull String var1) {
      Target var2 = TargetParser.INSTANCE.getTarget(var1);
      if (var2.getType() != 1) {
         String var3 = var1;
         boolean var4 = ((LiteBansCore)var0.getPlatform().getModule(LiteBansCore.class)).getConfig().isAutocompleteOnlinePlayerNames();
         if (var4) {
            var3 = var1.toLowerCase(Locale.ENGLISH);
         }

         Sender var15 = null;
         Object[] var16 = var0.getPlatform().getOnlineSenders();
         int var17 = 0;

         for (int var18 = var16.length; var17 < var18; var17++) {
            Object var10 = var16[var17];
            Sender var11 = (Sender)var10;
            String var13 = var11.getName();
            if (var4 && StringsKt.checkString(var13.toLowerCase(Locale.ENGLISH), var3, false, 2, null)) {
               var15 = var11;
            }

            if (StringsKt.checkString(var13, var3, true)) {
               return var11;
            }
         }

         return var15;
      } else {
         Object[] var5 = var0.getPlatform().getOnlineSenders();
         int var6 = 0;
         int var7 = var5.length;

         Object var10000;
         while (true) {
            if (var6 >= var7) {
               var10000 = null;
               break;
            }

            Object var8 = var5[var6];
            Sender var9 = (Sender)var8;
            if (Intrinsics.checkObject(var9.getUuidString(), var1)) {
               var10000 = var8;
               break;
            }

            var6++;
         }

         return (Sender)var10000;
      }
   }

   public static final void requirePermission(@NotNull CommandRunnable var0, @NotNull String var1) {
      Sender var3 = var0.getCurrentSender();
      MessageSender var5 = CommandRunnable.messageSender;
      boolean var6 = !var3.hasPermission(var1);
      CharSequence var7 = Message.ERROR_NO_PERMISSION;
      if (var6) {
         var5.getVoid(var7);
         throw new NoWhenBranchMatchedException();
      }
   }

   public static final void requireAnyPermission(@NotNull CommandRunnable var0, @NotNull String[] var1) {
      MessageSender var2 = CommandRunnable.messageSender;
      Sender var3 = var0.getCurrentSender();
      String[] var4 = Arrays.copyOf(var1, var1.length);
      Sender var6 = var3;
      String[] var7 = Arrays.copyOf(var4, var4.length);
      String[] var9 = var7;
      int var11 = 0;
      int var12 = var7.length;

      boolean var10000;
      while (true) {
         if (var11 >= var12) {
            var10000 = false;
            break;
         }

         String var13 = var9[var11];
         if (var6.hasPermission(var13)) {
            var10000 = true;
            break;
         }

         var11++;
      }

      boolean var15 = !var10000;
      CharSequence var16 = Message.ERROR_NO_PERMISSION;
      if (var15) {
         var2.getVoid(var16);
         throw new NoWhenBranchMatchedException();
      }
   }

   public static final void requireTemplatePermission(@NotNull CommandRunnable var0, @NotNull Template var1, @NotNull TemplateData var2) {
      String var4;
      MessageSender var5;
      boolean var10000;
      label39: {
         String var3 = var1.getDefaults().getString4();
         var4 = var2.getString4();
         var5 = CommandRunnable.messageSender;
         if (var3 != null) {
            Sender var6 = var0.getCurrentSender();
            if (!var6.hasPermission(var3)) {
               var10000 = true;
               break label39;
            }
         }

         var10000 = false;
      }

      boolean var13 = var10000;
      CharSequence var7 = Message.ERROR_NO_PERMISSION;
      if (var13) {
         var5.getVoid(var7);
         throw new NoWhenBranchMatchedException();
      } else {
         label32: {
            var5 = CommandRunnable.messageSender;
            if (var4 != null) {
               Sender var14 = var0.getCurrentSender();
               if (!var14.hasPermission(var4)) {
                  var10000 = true;
                  break label32;
               }
            }

            var10000 = false;
         }

         var13 = var10000;
         var7 = Message.ERROR_NO_PERMISSION;
         if (var13) {
            var5.getVoid(var7);
            throw new NoWhenBranchMatchedException();
         }
      }
   }

   private static final PlayerHistory requireHistory(PunishmentStorage var0, String var1) {
      PlayerHistory var10000 = var0.findLatestHistory(var1);
      if (var10000 == null) {
         throw new SilentAbortException();
      } else {
         return var10000;
      }
   }

   public static final PlayerHistory resolveTarget(@NotNull PunishmentStorage var0, @NotNull String var1, boolean var2) {
      String var3 = var1;
      if (StringsKt.checkCharSequence3(var1, ':', false, 2, null)) {
         var3 = StringsKt.getString(var1, "user:", null, 2, null);
      }

      PlayerHistory var4 = requireHistory(var0, var3);
      if (var2) {
         boolean var8;
         label24: {
            String var5 = var4.getUuid();
            if (var5 != null) {
               Character var10000 = StringsKt.getCharacter(var5, 14);
               byte var7 = 52;
               if (var10000 != null && var10000 == var7) {
                  var8 = true;
                  break label24;
               }
            }

            var8 = false;
         }

         if (var8 && !TargetParser.checkString7(var3)) {
            String var10001 = var4.getUuid();
            Intrinsics.handleObject(var10001);
            return requireHistory(var0, var10001);
         }
      }

      return var4;
   }

   public static PlayerHistory resolveTargetDefault(PunishmentStorage var0, String var1, boolean var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = true;
      }

      return resolveTarget(var0, var1, var2);
   }

   public static final String[] expandAlias(@NotNull CommandAliases var0, @NotNull String var1, @NotNull String[] var2) {
      String var3 = var1;
      int var4 = var2.length;
      boolean var5 = StringsKt.checkString(var1, "temp", false, 2, null);
      if (var5) {
         var3 = var1.substring(4);
      }

      boolean var6 = StringsKt.checkString(var3, "litebans:", false, 2, null);
      if (var6) {
         var3 = var3.substring(9);
      }

      String var10000;
      label68: {
         label67: {
            label74: {
               switch (var3.hashCode()) {
                  case -1396405339:
                     if (var3.equals("ban-ip")) {
                        break label67;
                     }
                     break;
                  case -1182108736:
                     if (var3.equals("ipmute")) {
                        break label74;
                     }
                     break;
                  case -1062781696:
                     if (var3.equals("muteip")) {
                        break label74;
                     }
                     break;
                  case -840405966:
                     if (var3.equals("unmute")) {
                        var10000 = "-MU";
                        break label68;
                     }
                     break;
                  case -840127329:
                     if (var3.equals("unwarn")) {
                        var10000 = "-WU";
                        break label68;
                     }
                     break;
                  case 3363353:
                     if (var3.equals("mute")) {
                        var10000 = "-M";
                        break label68;
                     }
                     break;
                  case 3641990:
                     if (var3.equals("warn")) {
                        var10000 = "-W";
                        break label68;
                     }
                     break;
                  case 93503862:
                     if (var3.equals("banip")) {
                        break label67;
                     }
                     break;
                  case 100403592:
                     if (var3.equals("ipban")) {
                        break label67;
                     }
                     break;
                  case 111426262:
                     if (var3.equals("unban")) {
                        var10000 = "-U";
                        break label68;
                     }
               }

               if (var6) {
                  return var2;
               }

               var10000 = "-";
               break label68;
            }

            var10000 = "-IM";
            break label68;
         }

         var10000 = "-I";
      }

      String var7 = var10000;
      if (var5) {
         var7 = var7 + 'T';
      }

      String[] var8 = (String[])ArraysKt.getObjectArray(var2, new String[var4 + 1], 1, 0, 0, 12, null);
      var8[0] = var7;
      return Intrinsics.checkObject(var7, "-") ? var2 : var8;
   }
}
