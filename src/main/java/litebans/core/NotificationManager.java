package litebans.core;

import java.io.Closeable;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;
import litebans.api.impl.EventDispatcher;
import litebans.command.CommandRunnable;
import litebans.config.Config;
import litebans.config.ConfigFile;
import litebans.config.Message;
import litebans.database.DatabaseManager;
import litebans.database.EntryReader;
import litebans.database.PunishmentStorage;
import litebans.database.SqlDateFormat;
import litebans.hook.LuckPermsHook;
import litebans.lib.compress.archivers.tar.TargetParser;
import litebans.lib.hikari.ProxyResultSet;
import litebans.lib.kotlin.Lazy;
import litebans.lib.kotlin.LazyKt;
import litebans.lib.kotlin.NoWhenBranchMatchedException;
import litebans.lib.kotlin.Pair;
import litebans.lib.kotlin.Unit;
import litebans.lib.kotlin.collections.ArrayIteratorKt;
import litebans.lib.kotlin.collections.CollectionsKt;
import litebans.lib.kotlin.io.CloseableKt;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.text.StringsKt;
import litebans.platform.LiteBansPlatform;
import litebans.platform.PlatformBridge;
import litebans.platform.Sender;
import litebans.platform.bukkit.BukkitBridge;
import litebans.punishment.BannedJoinNotifier;
import litebans.punishment.EntryPlaceholders;
import litebans.punishment.Flag;
import litebans.punishment.LocalLoginCheck;
import litebans.punishment.LockdownTask;
import litebans.punishment.LoginCheck;
import litebans.punishment.MuteManager;
import litebans.punishment.MuteSyncTask;
import litebans.punishment.PlayerHistory;
import litebans.punishment.ProxyLoginCheck;
import litebans.punishment.PunishmentEntry;
import litebans.punishment.PunishmentRequest;
import litebans.punishment.PunishmentType;
import litebans.punishment.ServerScope;
import litebans.sync.QueueFeederTask;
import litebans.sync.SyncQueue;
import litebans.util.Unreachable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class NotificationManager extends PluginModule {
   public static final NotificationManagerCompanion Companion = new NotificationManagerCompanion(null);
   private final Lazy lazy = LazyKt.getLazy(() -> getDatabaseManager(this));
   private final Lazy lazy2 = LazyKt.getLazy(() -> getLiteBansCore(this));
   public CharSequence charSequence;
   public CharSequence charSequence2;
   public CharSequence charSequence3;
   public CharSequence charSequence4;
   public CharSequence charSequence5;
   private LockdownTask lockdownTask;
   private final Set set = Collections.newSetFromMap(new ConcurrentHashMap());
   private final Lazy lazy3 = LazyKt.getLazy(() -> getLoginCheck(this));
   private int intValue = 1;
   public static final int intValue2 = 512;

   public NotificationManager(@Nullable LiteBansPlatform var1) {
      super(var1);
   }

   public final DatabaseManager getDatabase() {
      Lazy var1 = this.lazy;
      return (DatabaseManager)var1.getValue();
   }

   public final LiteBansCore getCore() {
      Lazy var1 = this.lazy2;
      return (LiteBansCore)var1.getValue();
   }

   public final void setCharSequence(@NotNull CharSequence var1) {
      this.charSequence = var1;
   }

   public final CharSequence getCharSequence() {
      if (this.charSequence2 != null) {
         return this.charSequence2;
      } else {
         Intrinsics.handleString3("");
         return null;
      }
   }

   public final void setCharSequence2(@NotNull CharSequence var1) {
      this.charSequence2 = var1;
   }

   public final CharSequence getCharSequence2() {
      if (this.charSequence3 != null) {
         return this.charSequence3;
      } else {
         Intrinsics.handleString3("");
         return null;
      }
   }

   public final void setCharSequence3(@NotNull CharSequence var1) {
      this.charSequence3 = var1;
   }

   public final CharSequence getCharSequence3() {
      if (this.charSequence4 != null) {
         return this.charSequence4;
      } else {
         Intrinsics.handleString3("");
         return null;
      }
   }

   public final void setCharSequence4(@NotNull CharSequence var1) {
      this.charSequence4 = var1;
   }

   public final CharSequence getCharSequence4() {
      if (this.charSequence5 != null) {
         return this.charSequence5;
      } else {
         Intrinsics.handleString3("");
         return null;
      }
   }

   public final void setCharSequence5(@NotNull CharSequence var1) {
      this.charSequence5 = var1;
   }

   public final LockdownTask getLockdownTask() {
      return this.lockdownTask;
   }

   public final Set getSet() {
      return this.set;
   }

   public final LoginCheck getLoginCheck() {
      Lazy var1 = this.lazy3;
      return (LoginCheck)var1.getValue();
   }

   public final LoginCheck createLoginCheck(@NotNull LiteBansPlatform var1) {
      byte var2 = var1.getPlatformId();
      LoginCheck var10000;
      if (var2 == 0 || var2 == 3) {
         var10000 = new LocalLoginCheck(var1);
      } else if (var2 == 1) {
         var10000 = new ProxyLoginCheck(var1);
      } else {
         if (var2 != 2) {
            Unreachable.getVoid();
            throw new NoWhenBranchMatchedException();
         }

         var10000 = new LoginCheck(var1);
      }

      return var10000;
   }

   public final int getIntValue() {
      return this.intValue;
   }

   public final void setIntValue(int var1) {
      this.intValue = var1;
   }

   public final void broadcast(
      @NotNull PunishmentStorage var1,
      @NotNull CharSequence var2,
      @NotNull Flag var3,
      @Nullable PunishmentType var4,
      @Nullable String var5,
      boolean var6,
      boolean var7,
      @Nullable Sender var8
   ) {
      this.broadcast(var1, var2, var3.getString(), var4, var5, var6, var7, var8);
   }

   public static void broadcastDefault(
      NotificationManager var0,
      PunishmentStorage var1,
      CharSequence var2,
      Flag var3,
      PunishmentType var4,
      String var5,
      boolean var6,
      boolean var7,
      Sender var8,
      int var9,
      Object var10
   ) {
      if ((var9 & 4) != 0) {
         var4 = null;
      }

      if ((var9 & 8) != 0) {
         var5 = null;
      }

      if ((var9 & 16) != 0) {
         var6 = true;
      }

      if ((var9 & 32) != 0) {
         var7 = true;
      }

      if ((var9 & 64) != 0) {
         var8 = null;
      }

      var0.broadcast(var1, var2, var3, var4, var5, var6, var7, var8);
   }

   public final void broadcast(
      @NotNull PunishmentStorage var1,
      @NotNull CharSequence var2,
      @Nullable String var3,
      @Nullable PunishmentType var4,
      @Nullable String var5,
      boolean var6,
      boolean var7,
      @Nullable Sender var8
   ) {
      Config var9 = this.getCore().getConfig();
      if (!var9.isLocalMessages() && var7) {
         LiteBansCore var15 = this.getCore();
         byte var17 = 10;
         if (var15.isDebugLevel(var17)) {
            var15.debug("Local messages are disabled, discarding message: \"" + var2 + '"');
         }
      } else if (var6) {
         if (var9.isNotifyNotifyConsole()) {
            if (var1.getPlatform().getPlatformId() == 3) {
               var1.getPlatform().getLogger().info(var2.toString());
            } else {
               MessageSender.handleSenderDefault(CommandRunnable.messageSender, var1.getPlatform().getConsole(), var2, null, 4, null);
            }
         }

         if (var8 != null && !var8.isConsole()) {
            MessageSender.handleSenderDefault(CommandRunnable.messageSender, var8, var2, null, 4, null);
         }

         EventDispatcher.INSTANCE.handlePunishmentStorage(var1, var1.getPlatform(), var2, var3);
         if (!Intrinsics.checkObject("console", var3)) {
            Sender[] var10 = var1.getPlatform().getOnlineSenders();
            if (var3 == null) {
               Iterator var11 = ArrayIteratorKt.getIterator(var10);

               while (var11.hasNext()) {
                  Sender var12 = (Sender)var11.next();
                  MessageSender var10000 = CommandRunnable.messageSender;
                  Intrinsics.handleObject(var12);
                  MessageSender.handleSenderDefault(var10000, var12, var2, null, 4, null);
               }
            } else {
               Iterator var16 = ArrayIteratorKt.getIterator(var10);

               while (var16.hasNext()) {
                  Sender var18 = (Sender)var16.next();
                  if (!Intrinsics.checkObject(var18, var8)) {
                     Intrinsics.handleObject(var18);
                     if (this.canReceive(var18, var3, var4, var5)) {
                        MessageSender.handleSenderDefault(CommandRunnable.messageSender, var18, var2, null, 4, null);
                     }
                  }
               }
            }
         }
      }
   }

   public static void broadcastDefault(
      NotificationManager var0,
      PunishmentStorage var1,
      CharSequence var2,
      String var3,
      PunishmentType var4,
      String var5,
      boolean var6,
      boolean var7,
      Sender var8,
      int var9,
      Object var10
   ) {
      if ((var9 & 4) != 0) {
         var4 = null;
      }

      if ((var9 & 8) != 0) {
         var5 = null;
      }

      if ((var9 & 16) != 0) {
         var6 = true;
      }

      if ((var9 & 32) != 0) {
         var7 = true;
      }

      if ((var9 & 64) != 0) {
         var8 = null;
      }

      var0.broadcast(var1, var2, var3, var4, var5, var6, var7, var8);
   }

   public final boolean canReceive(@NotNull Sender var1, @NotNull String var2, @Nullable PunishmentType var3, @Nullable String var4) {
      Sender var5 = var1;
      String[] var6 = new String[]{"litebans.notify", "litebans.notify." + var2};
      String[] var8 = var6;
      int var10 = 0;
      int var11 = var6.length;

      boolean var10000;
      while (true) {
         if (var10 >= var11) {
            var10000 = false;
            break;
         }

         String var12 = var8[var10];
         if (var5.hasPermission(var12)) {
            var10000 = true;
            break;
         }

         var10++;
      }

      if (var10000) {
         return true;
      } else if (var3 != null && var4 != null) {
         String var14 = this.getDatabase().getString();
         String var15 = var1.getServerName();
         if (var15 == null) {
            var15 = var14;
         }

         String var16 = "litebans.notify." + var2 + '.' + var4 + '.' + var3;
         if (!var1.hasPermission(var16)) {
            if (Intrinsics.checkObject(var15, var4)) {
               String var17 = "litebans.notify." + var2 + ".local." + var3;
               if (var1.hasPermission(var17)) {
                  return true;
               }
            }

            return false;
         } else {
            return true;
         }
      } else {
         return false;
      }
   }

   public final boolean checkPunishmentEntry(@NotNull PunishmentEntry var1, @NotNull String var2) {
      if (!this.set.isEmpty() && this.set.remove(var2.toLowerCase(Locale.ROOT))) {
         Sender var3 = this.platform.getConsole();
         this.getDatabase().runWithStorageAsync(st -> getUnit(var1, var3, this, (PunishmentStorage) st));
         this.platform.getLogger().info("Unbanned player: " + var2 + '.');
         return true;
      } else {
         return false;
      }
   }

   public final void handleString(@NotNull String var1) {
      if (Intrinsics.checkObject(var1, "pgsql") || Intrinsics.checkObject(var1, "postgresql")) {
         this.setCharSequence("B'0'");
         this.setCharSequence2("B'1'");
         this.setCharSequence3(" ILIKE ");
      } else {
         this.setCharSequence("0");
         this.setCharSequence2("1");
         this.setCharSequence3("=");
      }

      if (Intrinsics.checkObject(var1, "sqlite")) {
         this.setCharSequence4("name,uuid,ip,datetime(date) as date");
         this.setCharSequence5(" COLLATE NOCASE ");
      } else if (DatabaseManager.databaseDriver.checkString(var1)) {
         this.setCharSequence4("name,uuid,ip,UNIX_TIMESTAMP(date) as date");
         this.setCharSequence5(" ");
      } else {
         this.setCharSequence4("*");
         this.setCharSequence5(" ");
      }
   }

   @Override
   public void onDisable() {
      if (this.lockdownTask != null) {
         LockdownTask var10000 = this.lockdownTask;
         Intrinsics.handleObject(this.lockdownTask);
         var10000.run4();
      }
   }

   public final Sender findOnlineByUuid(@NotNull String var1) {
      Object[] var2 = this.platform.getOnlineSenders();
      int var4 = 0;
      int var5 = var2.length;

      Object var10000;
      while (true) {
         if (var4 >= var5) {
            var10000 = null;
            break;
         }

         Object var6 = var2[var4];
         Sender var7 = (Sender)var6;
         if (Intrinsics.checkObject(var7.getUuidString(), var1)) {
            var10000 = var6;
            break;
         }

         var4++;
      }

      return (Sender)var10000;
   }

   public final CharSequence formatDate(long var1, boolean var3) {
      Pair var4 = this.getPair();
      return var1 == -1L ? Message.FOREVER : ((SimpleDateFormat)var4.getObject2()).format(new Date(var1 + this.getLong(var3)));
   }

   private final Pair getPair() {
      Pair var1 = (Pair)this.getCore().getThreadLocal().get();
      if (!Intrinsics.checkObject(var1.getObject(), Message.DATE_FORMAT.toString())) {
         this.getCore().getThreadLocal().remove();
         var1 = (Pair)this.getCore().getThreadLocal().get();
      }

      Intrinsics.handleObject(var1);
      return var1;
   }

   public final java.util.Date readDate(@NotNull ResultSet var1, @NotNull String var2, boolean var3, @Nullable String var4) {
      try {
      if (!this.checkObject(var1)) {
         String var10001 = var4;
         if (var4 == null) {
            var10001 = this.getCore().getConfig().getSqlDriver();
         }

         if (DatabaseManager.databaseDriver.checkString(var10001) && var3) {
            return new Date(var1.getLong(var2) * 1000L);
         } else {
            Calendar var9 = Calendar.getInstance();
            var9.setTimeZone(TimeZone.getTimeZone("UTC"));
            Timestamp var10000 = var1.getTimestamp(var2);
            if (var10000 == null) {
               return new java.util.Date();
            } else {
               Timestamp var7 = var10000;
               var9.setTime(var7);
               return var9.getTime();
            }
         }
      } else {
         java.util.Date var5;
         try {
            String var6 = var1.getString(var2);
            if (var6 == null || var6.length() == 0) {
               return new java.util.Date();
            }

            var5 = ((SimpleDateFormat)SqlDateFormat.sqliteToH2Converter.getThreadLocal().get()).parse(var6);
         } catch (Exception var8) {
            this.getCore().handleObject(var8, 0);
            var5 = new java.util.Date();
         }

         return var5;
      }
      } catch (SQLException var10) {
         throw new RuntimeException(var10);
      }
   }

   public final String getRemoverName(@NotNull PunishmentStorage var1, @NotNull PunishmentEntry var2) {
      String var10000 = var2.getRemovedByUuid();
      if (var10000 == null) {
         return null;
      } else {
         String var3 = var10000;
         if (!Intrinsics.checkObject(var3, "[Console]") && !Intrinsics.checkObject(var3, "CONSOLE")) {
            String var4 = var2.getRemovedByName();
            if (this.getCore().getConfig().isUseDisplayNames() && var4 != null) {
               return var4;
            } else {
               PlayerHistory var5 = var1.findLatestHistory(var3);
               return var5 != null ? var5.getString() : null;
            }
         } else {
            return this.getCore().getConfig().getConsoleSenderName();
         }
      }
   }

   public final long getLong(boolean var1) {
      if ((var1 || !this.getCore().getConfig().isUseDatabaseTime()) && this.getCore().getConfig().isUseTimezone()) {
         Calendar var2 = Calendar.getInstance();
         return (long)(var2.get(15) + var2.get(16));
      } else {
         return 0L;
      }
   }

   public final void handleString(@Nullable String var1, @Nullable String var2, @Nullable String var3, @Nullable String var4, boolean var5) {
      Intrinsics.handleObject(var3);
      String var6 = TargetParser.getString2(var3);
      Config var7 = this.getCore().getConfig();
      MuteManager var8 = (MuteManager)this.platform.getModule(MuteManager.class);
      boolean var9 = !this.getCore().isAuthMeEnabled() && !this.platform.isProxy() && !var7.isFlag();
      var9 = var9 || var5 && this.platform.isProxy() && this.platform.isOnlineMode();
      if (var9) {
         LiteBansPlatform var10002 = this.platform;
         Intrinsics.handleObject(var1);
         new QueueFeederTask(var10002, var1, var6, var2).run();
      }

      if (!var5) {
         LiteBansPlatform var12 = var8.platform;
         Intrinsics.handleObject(var6);
         new MuteSyncTask(var12, var1, var6, var2, var4).run();
         handleStringDefault(this, var6, var1, 0, 4, null);
      }
   }

   private final void handleString(String var1, String var2, int var3) {
      this.platform.runAsyncLater(() -> handleNotificationManager(this, var1, var3, var2), 30L * (long)var3 * (long)var3);
   }

   public static void handleStringDefault(NotificationManager var0, String var1, String var2, int var3, int var4, Object var5) {
      if ((var4 & 4) != 0) {
         var3 = 1;
      }

      var0.handleString(var1, var2, var3);
   }

   @Override
   public void onEnable() {
      this.getCore().onEnable();
      if (this.lockdownTask == null) {
         this.startLockdown("", null, false);
      }
   }

   public final Object getLuckPerms() {
      return this.platform.createBridge().isPluginEnabled("LuckPerms") ? LuckPermsHook.INSTANCE.getObject() : null;
   }

   public final boolean checkObject(@Nullable Object var1) {
      return var1 instanceof ProxyResultSet
         ? StringsKt.checkCharSequence3(((ProxyResultSet)var1).toString(), "org.sqlite", false, 2, null)
         : var1 != null && StringsKt.checkString(var1.getClass().getName(), "org.sqlite", false, 2, null);
   }

   public final void handlePunishmentStorage(@NotNull PunishmentStorage var1, @NotNull PunishmentRequest var2) {
      PunishmentEntry var10002 = var2.getPunishmentEntry();
      Intrinsics.handleObject(var10002);
      PlayerHistory var10003 = var2.getPlayerHistory();
      this.handlePunishmentStorage(var1, var10002, var10003 != null ? var10003.getString() : null);
   }

   public final void handlePunishmentStorage(@NotNull PunishmentStorage var1, @NotNull PunishmentEntry var2, @Nullable String var3) {
      if (var1.getPlatform().getPlatformId() == 1 && var2.getType() == PunishmentType.BAN) {
         String var4 = var2.getUuid();
         if (TargetParser.checkString7(var4)) {
            LoginCheck var10000 = this.getLoginCheck();
            Intrinsics.handleObject(var10000, "");
            ProxyLoginCheck var6 = (ProxyLoginCheck)var10000;
            String var10002 = var3;
            if (var3 == null) {
               PlayerHistory var7 = var1.findLatestHistory(var2.getUuid());
               var10002 = var7 != null ? var7.getString() : null;
               if (var10002 == null) {
                  var10002 = "";
               }
            }

            UUID var10003 = UUID.fromString(var2.getUuid());
            String var10004 = var2.getIp();
            String var10005 = var2.getServerScope();
            if (var10005 == null) {
               var10005 = ServerScope.serverScopeParser.getString();
            }

            var6.handlePunishmentStorage(var1, var10002, var10003, var10004, var10005, CollectionsKt.getList(var2));
         }
      }
   }

   public static void handlePunishmentStorageDefault(NotificationManager var0, PunishmentStorage var1, PunishmentEntry var2, String var3, int var4, Object var5) {
      if ((var4 & 2) != 0) {
         var3 = null;
      }

      var0.handlePunishmentStorage(var1, var2, var3);
   }

   public final void handlePunishmentStorage(@NotNull PunishmentStorage var1, @NotNull Sender var2) {
      List var3 = var1.findEntries(var2.getUuidString(), var2.getIp(), PunishmentType.BAN, "__ALL__", true, 0);
      LoginCheck var10000 = this.getLoginCheck();
      Intrinsics.handleObject(var10000, "");
      ProxyLoginCheck var4 = (ProxyLoginCheck)var10000;
      UUID var7 = var2.getUuid();
      int var5 = (int)var7.getMostSignificantBits();
      Integer var8 = var5;
      var4.getConcurrentHashMap().put(var8, var3);
   }

   public final boolean checkPunishmentEntry(@Nullable PunishmentEntry var1) {
      if ((var1 != null ? var1.getType() : null) == PunishmentType.BAN) {
         String var2 = var1.getUuid();
         if (TargetParser.checkString7(var2) && this.platform.getPlatformId() == 1) {
            LoginCheck var10000 = this.getLoginCheck();
            Intrinsics.handleObject(var10000, "");
            ProxyLoginCheck var15 = (ProxyLoginCheck)var10000;
            String var10001 = var1.getUuid();
            Intrinsics.handleObject(var10001);
            int var3 = var15.getInt(var10001);
            Collection var17 = (Collection)var15.getConcurrentHashMap().get(var3);
            if (var17 == null) {
               return false;
            }

            Collection var4 = var17;
            Map var5 = var15.getConcurrentHashMap();
            Integer var6 = var3;
            Iterable var7 = var4;
            Collection var10 = new ArrayList();

            for (Object var13 : var7) {
               PunishmentEntry var14 = (PunishmentEntry)var13;
               if (var14.getId() != var1.getId()) {
                  var10.add(var13);
               }
            }

            List var16 = (List)var10;
            var5.put(var6, var16);
            return true;
         }
      }

      return false;
   }

   public final void kick(@NotNull Sender var1, @Nullable String var2) {
      PlatformBridge var3 = this.platform.createBridge();
      if (var3 instanceof BukkitBridge && this.getCore().getConfig().isDebugLevel()) {
         BukkitBridge var10000 = (BukkitBridge)var3;
         Intrinsics.handleObject(var2);
         var10000.kickViaBungee(var1, var2);
      } else {
         Intrinsics.handleObject(var2);
         var1.kick(var2);
      }
   }

   public final void kickSync(@NotNull Sender var1, @NotNull CharSequence var2) {
      this.platform.runSync(() -> handleNotificationManager(this, var1, var2));
   }

   public final void startLockdown(@NotNull String var1, @Nullable CharSequence var2, boolean var3) {
      if (this.lockdownTask != null) {
         LockdownTask var10000 = this.lockdownTask;
         Intrinsics.handleObject(this.lockdownTask);
         var10000.run4();
      }

      this.lockdownTask = new LockdownTask(this.platform, var2, var1, var3);
      LockdownTask var4 = this.lockdownTask;
      Intrinsics.handleObject(this.lockdownTask);
      var4.run3();
   }

   public final void notifyBannedJoin(@NotNull String var1, @NotNull CharSequence var2, boolean var3) {
      new BannedJoinNotifier(this.platform, var1, var2, var3).run();
   }

   public final void handlePunishmentStorage(
      @NotNull PunishmentStorage var1, @NotNull Sender var2, @NotNull PunishmentEntry var3, int var4, @NotNull String var5
   ) {
      if (this.getCore().getConfig().isLocalMessages()) {
         String var7 = "litebans.notify." + var5;
         if (var2.hasPermission(var7)) {
            Message var10000;
            switch (NotificationManagerWhenMappings.switchMap[var3.getType().ordinal()]) {
               case 1:
                  var10000 = Message.BANNED_MESSAGE;
                  break;
               case 2:
                  var10000 = var3.isPermanent() ? Message.MUTE_MESSAGE_PERMANENT : Message.MUTE_MESSAGE;
                  break;
               default:
                  var10000 = Message.WARN_MESSAGE;
            }

            Message var6 = var10000;
            CharSequence var9 = EntryPlaceholders.getCharSequenceDefault(var1, var3, var4, 0, 4, null);
            if (var9 == null) {
               var9 = var6;
            }

            CharSequence var8 = var9;
            if (var3.getType() == PunishmentType.WARN) {
               var8 = EntryPlaceholders.getString(var8, "duration", this.getCore().getConfig().getWarningsExpireAfter());
            }

            MessageSender.handleSenderDefault(
               CommandRunnable.messageSender, var2, EntryPlaceholders.getStringDefault(var1, var3, var2, var8, 0L, 8, null), null, 4, null
            );
         }
      }
   }

   public final String getDisplayName(@Nullable String var1, @Nullable String var2) {
      if (this.getCore().getConfig().isUseDisplayNames() && var2 != null) {
         Sender var3 = this.findOnlineByUuid(var2);
         if (var3 != null) {
            return var3.getDisplayName();
         }
      }

      return var1;
   }

   private static final DatabaseManager getDatabaseManager(NotificationManager var0) {
      return (DatabaseManager)var0.platform.getModule(DatabaseManager.class);
   }

   private static final LiteBansCore getLiteBansCore(NotificationManager var0) {
      return (LiteBansCore)var0.platform.getModule(LiteBansCore.class);
   }

   private static final LoginCheck getLoginCheck(NotificationManager var0) {
      return var0.createLoginCheck(var0.platform);
   }

   private static final Unit getUnit(PunishmentEntry var0, Sender var1, NotificationManager var2, PunishmentStorage var3) {
      EntryReader.handlePunishmentStorage(var3, var0, var1.getUuidString(), var1.getName(), null, false, 24, null);
      var2.checkPunishmentEntry(var0);
      return Unit.INSTANCE;
   }

   private static final void handleNotificationManager(NotificationManager var0, String var1, int var2, String var3) {
      try {
         Sender var4 = var0.findOnlineByUuid(var1);
         if (var4 == null) {
            if (var2 < 5) {
               var0.handleString(var1, var3, var2 + 1);
            }

            return;
         }

         if (!var0.platform.isProxy()) {
            String var6 = "litebans.exempt.dupeip_join";
            if (!var4.hasPermission(var6)) {
               SyncQueue var5 = (SyncQueue)var0.platform.getModule(SyncQueue.class);
               var6 = var4.getIp();
               if (var6 != null) {
                  var5.queueJoin(var3, var4.getUuidString(), var6);
               }
            }
         }

         String var35 = "litebans.notify.warned.offline";
         if (var4.hasPermission(var35)) {
            DatabaseManager var32 = var0.getDatabase();

            try {
               PunishmentStorage var7 = var32.openStorage();
               Closeable var8 = var7;
               Throwable var9 = null;

               try {
                  PunishmentStorage var12 = var7;
                  Iterable var14 = var7.getList(var4, PunishmentType.WARN, true, 512);
                  Collection var17 = new ArrayList();

                  for (Object var20 : var14) {
                     PunishmentEntry var21 = (PunishmentEntry)var20;
                     if (!var21.isWarned()) {
                        var17.add(var20);
                     }
                  }

                  List var23 = (List)var17;
                  if (!var23.isEmpty()) {
                     var7.markWarned(var1);
                     CharSequence var15 = var7.getCharSequence2(Message.WARNED_JOIN_START, var4);
                     MessageSender.handleSenderDefault(CommandRunnable.messageSender, var4, var15, null, 4, null);

                     for (Object var39o : var23) {
                        PunishmentEntry var39 = (PunishmentEntry) var39o;
                        CharSequence var16 = EntryPlaceholders.getCharSequence(var12, var39, -1, -1);
                        if (var16 == null) {
                           var16 = Message.WARNED_JOIN_ENTRY;
                        }

                        MessageSender.handleSenderDefault(
                           CommandRunnable.messageSender, var4, EntryPlaceholders.getStringDefault(var12, var39, var4, var16, 0L, 8, null), null, 4, null
                        );
                     }

                     int var38 = var23.size();
                     Logger var10000 = var12.getPlatform().getLogger();
                     String var40 = "Notified %s of %d %s.";
                     Object[] var41 = new Object[]{var4.getName(), var38, var38 == 1 ? "warning" : "warnings"};
                     var10000.info(String.format(var40, Arrays.copyOf(var41, var41.length)));
                  }
               } catch (Throwable var28) {
                  var9 = var28;
                  throw var28;
               } finally {
                  CloseableKt.handleCloseable(var8, var9);
               }
            } catch (Exception var30) {
               if (!(var30 instanceof SQLException) || !var32.isConnectionError(var30)) {
                  throw var30;
               }
            }
         }

         var35 = "litebans.admin";
         if (var4.hasPermission(var35)) {
            ConfigFile var33 = var0.platform.getMainConfig();
            if (var33.getThrowable() != null) {
               MessageSender.handleSenderDefault(CommandRunnable.messageSender, var4, Message.RELOAD_FAIL_CONFIG, null, 4, null);
            }

            if (var0.getCore().getTemplateManager().getConfigFile() == null) {
               MessageSender.handleSenderDefault(CommandRunnable.messageSender, var4, Message.RELOAD_FAIL_CONFIG_TEMPLATES, null, 4, null);
            }

            ConfigFile var42 = var0.getCore().getMessagesLoader().getConfigFile();
            if ((var42 != null ? var42.getThrowable() : null) != null) {
               MessageSender.handleSenderDefault(CommandRunnable.messageSender, var4, Message.RELOAD_FAIL_CONFIG_MESSAGES, null, 4, null);
            }
         }
      } catch (Exception var31) {
         var0.getDatabase().reportError(var31);
      }
   }

   private static final void handleNotificationManager(NotificationManager var0, Sender var1, CharSequence var2) {
      var0.kick(var1, var2.toString());
   }
}
