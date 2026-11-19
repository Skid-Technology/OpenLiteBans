package litebans.sync;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import litebans.command.AliasPredicate;
import litebans.command.ArgParser;
import litebans.command.ArgUtils;
import litebans.command.CommandAlias;
import litebans.command.KickCommand;
import litebans.command.TextArgs;
import litebans.config.Config;
import litebans.config.Message;
import litebans.config.Template;
import litebans.core.LiteBansCore;
import litebans.core.NotificationManager;
import litebans.core.PluginModule;
import litebans.database.DatabaseManager;
import litebans.database.EmptyResultSetHolder;
import litebans.database.EntryReader;
import litebans.database.PunishmentStorage;
import litebans.database.QueryRunner;
import litebans.database.QueryStatement;
import litebans.database.SqlString;
import litebans.database.Table;
import litebans.lib.compress.archivers.tar.TargetParser;
import litebans.lib.compress.utils.PublicByteArrayOutputStream;
import litebans.lib.kotlin.KotlinMisc;
import litebans.lib.kotlin.KotlinVersion;
import litebans.lib.kotlin.Lazy;
import litebans.lib.kotlin.LazyKt;
import litebans.lib.kotlin.Pair;
import litebans.lib.kotlin.Triple;
import litebans.lib.kotlin.TuplesKt;
import litebans.lib.kotlin.UByte;
import litebans.lib.kotlin.collections.ArraysKt;
import litebans.lib.kotlin.collections.CollectionsKt;
import litebans.lib.kotlin.collections.IntIterator;
import litebans.lib.kotlin.collections.MapsKt;
import litebans.lib.kotlin.io.AutoCloseableKt;
import litebans.lib.kotlin.io.CloseableKt;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.ranges.IntRange;
import litebans.lib.kotlin.text.Charsets;
import litebans.lib.kotlin.text.StringsKt;
import litebans.platform.LiteBansPlatform;
import litebans.platform.LiteCommand;
import litebans.platform.Sender;
import litebans.punishment.CollectionAddFunction;
import litebans.punishment.EntryFilterFunction;
import litebans.punishment.EntryPlaceholders;
import litebans.punishment.Flag;
import litebans.punishment.FlagWhenMappings;
import litebans.punishment.MuteManager;
import litebans.punishment.PlayerHistory;
import litebans.punishment.PunishmentCache;
import litebans.punishment.PunishmentEntry;
import litebans.punishment.PunishmentRequest;
import litebans.punishment.PunishmentService;
import litebans.punishment.PunishmentType;
import litebans.punishment.ResetCounterFunction;
import litebans.punishment.ServerScope;
import litebans.punishment.ServerScopeParser;
import litebans.randomid.IdScrambler;
import litebans.randomid.IdScramblerState;
import litebans.randomid.LongBitSet;
import litebans.randomid.RandomIdState;
import litebans.util.ChunkSplitter;
import litebans.util.FileUtils;
import litebans.util.SortComparator3;
import litebans.util.SortComparator5;
import litebans.util.SortComparator7;
import org.jetbrains.annotations.NotNull;

public final class SyncManager extends PluginModule implements Runnable {
   public static final ChunkSplitter chunkSplitter = new ChunkSplitter(null);
   private final Lazy lazy = LazyKt.getLazy(() -> getLiteBansCore(this));
   private final Lazy lazy2 = LazyKt.getLazy(() -> getDatabaseManager(this));
   private final Lazy lazy3 = LazyKt.getLazy(() -> getNotificationManager(this));
   private final Lazy lazy4 = LazyKt.getLazy(() -> getMuteManager(this));
   private final AtomicBoolean atomicBoolean = new AtomicBoolean();
   private final CacheSyncTask cacheSyncTask = new CacheSyncTask(this.platform);
   private long longValue;
   private int intValue;
   private byte byteValue;
   private byte[][] byteArrayArray = new byte[0][];
   private final byte[][] byteArrayArray2;
   private CopyOnWriteArrayList copyOnWriteArrayList;
   private final CopyOnWriteArrayList copyOnWriteArrayList2;
   private final Triple triple;
   public static final char charValue = '\ufeff';
   public static final String string = "\ufeff";

   public SyncManager(@NotNull LiteBansPlatform var1) {
      super(var1);
      int var2 = 0;

      byte[][] var3;
      for (var3 = new byte[4][]; var2 < 4; var2++) {
         var3[var2] = ArgParser.getByteArray();
      }

      this.byteArrayArray2 = var3;
      this.copyOnWriteArrayList = new CopyOnWriteArrayList();
      this.copyOnWriteArrayList2 = new CopyOnWriteArrayList();
      this.triple = new Triple(new AtomicLong(), new AtomicLong(), new AtomicLong());
   }

   public final LiteBansCore getLiteBansCore() {
      Lazy var1 = this.lazy;
      return (LiteBansCore)var1.getValue();
   }

   public final DatabaseManager getDatabaseManager() {
      Lazy var1 = this.lazy2;
      return (DatabaseManager)var1.getValue();
   }

   public final NotificationManager getNotificationManager() {
      Lazy var1 = this.lazy3;
      return (NotificationManager)var1.getValue();
   }

   public final MuteManager getMuteManager() {
      Lazy var1 = this.lazy4;
      return (MuteManager)var1.getValue();
   }

   public final CacheSyncTask getCacheSyncTask() {
      return this.cacheSyncTask;
   }

   public final int getIntValue() {
      return this.intValue;
   }

   public final void setIntValue(int var1) {
      this.intValue = var1;
   }

   public final byte getByteValue() {
      return this.byteValue;
   }

   public final void setByteValue(byte var1) {
      this.byteValue = var1;
   }

   public final void setByteArrayArray(@NotNull byte[][] var1) {
      this.byteArrayArray = var1;
   }

   public final CopyOnWriteArrayList getCopyOnWriteArrayList() {
      return this.copyOnWriteArrayList;
   }

   public final void setCopyOnWriteArrayList(@NotNull CopyOnWriteArrayList var1) {
      this.copyOnWriteArrayList = var1;
   }

   public final Triple getTriple() {
      return this.triple;
   }

   public final boolean addListener(@NotNull AliasPredicate var1) {
      return this.copyOnWriteArrayList2.add(var1);
   }

   public final boolean removeListener(@NotNull AliasPredicate var1) {
      return this.copyOnWriteArrayList2.remove(var1);
   }

   public final ByteKeyedMap getByteKeyedMap(int var1) {
      Iterable var2 = this.copyOnWriteArrayList;
      Iterator var3 = var2.iterator();

      Object var10000;
      while (true) {
         if (var3.hasNext()) {
            Object var4 = var3.next();
            Reference var5 = (Reference)var4;
            var10000 = (ByteKeyedMap)var5.get();
            if ((var10000 != null ? ((ByteKeyedMap)var10000).getByteValue() : -1) != var1) {
               continue;
            }

            var10000 = var4;
            break;
         }

         var10000 = null;
         break;
      }

      return (Reference)var10000 != null ? (ByteKeyedMap)((Reference)var10000).get() : null;
   }

   @Override
   public void onEnable() {
      this.getLiteBansCore().onEnable();
      Config var1 = this.getLiteBansCore().getConfig();
      if (DatabaseManager.databaseDriver.checkString2(var1.getSqlDriver()) && var1.isSync()) {
         this.atomicBoolean.set(false);
         var1.getLiteBansPlatform().runAsyncTimer(this, var1.getSyncPollTicks(), var1.getSyncPollTicks());
      }
   }

   @Override
   public void onReload() {
      this.onEnable();
   }

   @Override
   public void run() {
      AtomicBoolean var1 = this.atomicBoolean;
      if (var1.compareAndSet(false, true)) {
         try {
            DatabaseManager var3 = this.getDatabaseManager();

            try {
               PunishmentStorage var5 = var3.openStorage();
               Closeable var6 = var5;
               Throwable var7 = null;

               try {
                  this.longValue = this.poll(var5, this.copyOnWriteArrayList2, this.longValue);
               } catch (Throwable var21) {
                  var7 = var21;
                  throw var21;
               } finally {
                  CloseableKt.handleCloseable(var6, var7);
               }
            } catch (Exception var23) {
               if (!(var23 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var23); }
               if (!var3.isConnectionError(var23)) {
                  throw var23;
               }
            }
         } catch (Exception var24) {
            if (!(var24 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var24); }
            this.getDatabaseManager().reportError(var24);
         } finally {
            var1.set(false);
         }
      }
   }

   private final void heartbeat(PunishmentStorage var1) {
      long var2 = ((AtomicLong)this.triple.getObject3()).incrementAndGet();
      long var4 = var2 - ((AtomicLong)this.triple.getObject2()).get();
      if (var4 > 1L) {
         this.registerServer(var1);
      }
   }

   private final void registerServer(PunishmentStorage var1) {
      this.getLiteBansCore().debug("[Sync] Detected duplicate server. This will be resolved automatically.");
      ArgParser.handleTriple(this.triple, ResetCounterFunction.INSTANCE);
      File var2 = var1.getPlatform().getDataFolder();
      String var3 = "server.yml";
      FileUtils.getFile2(new File(var2, var3));
      var2 = var1.getPlatform().getDataFolder();
      var3 = "server.json";
      FileUtils.getFile2(new File(var2, var3));
      Pair var5 = this.getLiteBansCore().getConfig().getPair(true);
      this.getLiteBansCore().getConfig().setString2((String)var5.getObject());
      this.getLiteBansCore().getConfig().setString((String)var5.getObject2());
      DatabaseManager.updateServerInfoDefault(var1.getDatabaseManager(), var1, 0L, false, 6, null);
   }

   public final void publish(@NotNull Flag var1, @NotNull PunishmentStorage var2, @NotNull CharSequence var3, int var4) {
      if (this.getDatabaseManager().isSyncEnabled() && !var2.isClosed()) {
         int var7 = 4092;
         String var5 = ArgUtils.getString(var3.toString(), var7);
         CommandAlias var6 = this.getDatabaseManager().getCommandAlias();
         if (var6 != null && (!var6.check2() || var4 != -1)) {
            try {
               var7 = var6.getIntValue();
               if (var4 != -1 && var4 != var7) {
                  var7 = var4;
               } else {
                  ((AtomicLong)this.triple.getObject2()).incrementAndGet();
               }

               int var33 = (var1.getInt() << 16 | var7) + 42;
               Table var9 = Table.SYNC;
               CharSequence var35 = "info,msg";
               byte var13 = 44;
               CharSequence var15 = var35;
               int var17 = 0;

               for (int var18 = 0; var18 < var15.length(); var18++) {
                  char var19 = var15.charAt(var18);
                  if (var19 == var13) {
                     var17++;
                  }
               }

               String var12 = "?";
               if (var17 > 0) {
                  StringBuilder var37 = new StringBuilder(var17 * 2);
                  Iterable var14 = new IntRange(0, var17);
                  Iterator var16 = var14.iterator();

                  while (var16.hasNext()) {
                     ((IntIterator)var16).getInt();
                     var37.append("?,");
                  }

                  var12 = StringsKt.getString(var37.toString(), 1);
               }

               QueryStatement var34 = var2.prepare(SqlString.getSqlString(SqlString.getString2("INSERT INTO " + var9 + '(' + var35 + ")VALUES(" + var12 + ')')))
                  .getQueryStatement(var33);
               var34.getQueryStatement(var5);
               QueryRunner.getResultSet(var34);
               PunishmentCache var36 = (PunishmentCache)var2.getPlatform().getModule(PunishmentCache.class);
               long var38 = System.currentTimeMillis();
               if (var38 - var2.getDatabaseManager().getLongValue() > 28800000L) {
                  var2.getDatabaseManager().setLongValue(var38);
                  String var39 = "1 minute";
                  if (Intrinsics.checkObject(this.getLiteBansCore().getConfig().getSqlDriver(), "pgsql")) {
                     var39 = '\'' + var39 + '\'';
                  }

                  Table var40 = Table.SYNC;
                  String var41 = SqlString.getString2("DELETE FROM " + var40);
                  CharSequence var42 = "time < (NOW() - INTERVAL " + var39 + ')';
                  CharSequence[] var20 = new CharSequence[]{SqlString.getSqlString(var41), var42};
                  CharSequence[] var22 = var20;
                  int var24 = 0;

                  for (int var25 = var20.length; var24 < var25; var24++) {
                     CharSequence var26 = var22[var24];
                     if (var26.length() <= 0) {
                        String var29 = "Failed requirement.";
                        throw new IllegalArgumentException(var29.toString());
                     }
                  }

                  QueryRunner.getResultSet(var2.prepare(SqlString.getSqlString(SqlString.getString2(SqlString.getString(var41) + " WHERE " + var42))));
                  var36.handlePunishmentStorage(var2);
               }
            } catch (Exception var31) {
               if (!(var31 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var31); }
               this.getDatabaseManager().reportError(var31);
            }
         } else {
            char var8 = 'x';
            LiteBansCore var10 = this.getLiteBansCore();
            if (var10.isDebugEnabled()) {
               var10.debug("0x11 <" + var8 + "> " + var6);
            }
         }
      }
   }

   public static void publishDefault(SyncManager var0, Flag var1, PunishmentStorage var2, CharSequence var3, int var4, int var5, Object var6) {
      if ((var5 & 4) != 0) {
         var3 = "";
      }

      if ((var5 & 8) != 0) {
         var4 = -1;
      }

      var0.publish(var1, var2, var3, var4);
   }

   public void handleMessage(@NotNull PunishmentStorage var1, @NotNull String var2, @NotNull String var3, @NotNull Flag var4, @NotNull Set var5) {
      boolean var44;
      boolean var47;
      if (var2.length() == 36) {
         PunishmentType var10000;
         switch (FlagWhenMappings.switchMap[var4.ordinal()]) {
            case 6:
               var10000 = PunishmentType.BAN;
               break;
            case 7:
               var10000 = PunishmentType.MUTE;
               break;
            case 8:
               var10000 = PunishmentType.WARN;
               break;
            case 9:
            case 10:
               var10000 = PunishmentType.KICK;
               break;
            case 11:
               var10000 = PunishmentType.BAN;
               break;
            case 12:
               var10000 = PunishmentType.MUTE;
               break;
            default:
               var10000 = null;
         }

         PunishmentType var6 = var10000;
         if (var6 == null) {
            LiteBansCore var26 = this.getLiteBansCore();
            if (var26.isDebugEnabled()) {
               var26.debug("[!!!] NF " + var4);
            }
         } else if (var4 == Flag.UNMUTE) {
            MuteManager var25 = this.getMuteManager();
            boolean var27 = CollectionsKt.checkIterable(var25.getMuteChatHandler().getConcurrentHashMap().entrySet(), new EntryFilterFunction(var2, var3));
            if (var27) {
               var25.syncMute(var2, var3);
            }
         } else if (!var5.isEmpty()) {
            if (!var4.check() && !var5.contains(var2)) {
               if (var3.length() == 0) {
                  return;
               }

               Object[] var7 = var1.getPlatform().getOnlineSenders();
               int var9 = 0;
               int var10 = var7.length;

               while (true) {
                  if (var9 >= var10) {
                     var44 = true;
                     break;
                  }

                  Object var11 = var7[var9];
                  Sender var12 = (Sender)var11;
                  if (PunishmentEntry.wildcardPattern.checkBoolean(true, var12.getIp(), var3, PunishmentEntry.wildcardPattern.checkString(var3))) {
                     var44 = false;
                     break;
                  }

                  var9++;
               }

               if (var44) {
                  return;
               }
            }

            PunishmentEntry var45 = EntryReader.getPunishmentEntry(var1, var2, var3, var6, true, false, 16, null);
            if (var45 != null) {
               PunishmentEntry var24 = var45;
               long var8 = DatabaseManager.currentTimeMillisDefault(var1.getDatabaseManager(), false, 1, null);
               if (var1.getPlatform().getPlatformId() == 1) {
                  NotificationManager var30 = this.getNotificationManager();
                  if (var4 == Flag.BAN) {
                     NotificationManager.handlePunishmentStorageDefault(var30, var1, var24, null, 2, null);
                  } else if (var4 == Flag.UNBAN) {
                     Object[] var13 = var30.platform.getOnlineSenders();
                     Object[] var15 = var13;
                     Collection var16 = new ArrayList();
                     int var18 = 0;

                     for (int var19 = var13.length; var18 < var19; var18++) {
                        Object var20 = var15[var18];
                        Sender var21 = (Sender)var20;
                        if (var24.matches(var21.getUuidString(), var21.getIp(), var8)) {
                           var16.add(var20);
                        }
                     }

                     for (Object var38 : (List)var16) {
                        Sender var17 = (Sender)var38;
                        Intrinsics.handleObject(var17);
                        var30.handlePunishmentStorage(var1, var17);
                     }
                  }
               }

               Object[] var28 = var1.getPlatform().getOnlineSenders();
               Object[] var31 = var28;
               Collection var34 = new ArrayList();
               int var37 = 0;

               for (int var39 = var28.length; var37 < var39; var37++) {
                  Object var42;
                  label111: {
                     var42 = var31[var37];
                     Sender var43 = (Sender)var42;
                     if (var24.matches(var43.getUuidString(), var43.getIp(), var8)) {
                        ServerScopeParser var46 = ServerScope.serverScopeParser;
                        String var10001 = var43.getServerName();
                        if (var10001 == null) {
                           var10001 = var1.getDatabaseManager().getString();
                        }

                        String var10002 = var24.getServerScope();
                        if (var10002 == null) {
                           var10002 = ServerScope.serverScopeParser.getString();
                        }

                        if (var46.checkString(var10001, var10002)) {
                           var47 = true;
                           break label111;
                        }
                     }

                     var47 = false;
                  }

                  if (var47) {
                     var34.add(var42);
                  }
               }

               for (Object var35 : (List)var34) {
                  Sender var14 = (Sender)var35;
                  switch (var6 == null ? -1 : SyncManagerWhenMappings.switchMap[var6.ordinal()]) {
                     case 1:
                        Intrinsics.handleObject(var14);
                        if (PunishmentService.checkPunishmentStorage(var1, var24, var14)) {
                           this.getNotificationManager().kickSync(var14, EntryPlaceholders.getCharSequence(var1, var24, var14, -1));
                        }
                        break;
                     case 2:
                        this.getMuteManager().cacheMute(var2, var24);
                        if (this.getLiteBansCore().getConfig().isSyncNotifications()) {
                           NotificationManager var41 = this.getNotificationManager();
                           Intrinsics.handleObject(var14);
                           var41.handlePunishmentStorage(var1, var14, var24, -1, "muted");
                        }
                        break;
                     case 3:
                        if (this.getLiteBansCore().getConfig().isSyncNotifications()) {
                           var1.markWarned(var2);
                           NotificationManager var40 = this.getNotificationManager();
                           Intrinsics.handleObject(var14);
                           var40.handlePunishmentStorage(var1, var14, var24, -1, "warned");
                        }
                        break;
                     case 4:
                        NotificationManager var48 = this.getNotificationManager();
                        Intrinsics.handleObject(var14);
                        var48.kickSync(var14, EntryPlaceholders.getString(var1, var24, var14));
                  }
               }
            }
         }
      }
   }

   // $VF: Could not create synchronized statement, marking monitor enters and exits
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public final long poll(@NotNull PunishmentStorage var1, @NotNull Collection var2, long var3) {
      String[] var124;
      Object var257;
      long var72 = var3;

      try {
         CommandAlias var5 = var1.getDatabaseManager().getCommandAlias();
         if (var1.isClosed() || var5 == null) {
            char var95 = 's';
            LiteBansCore var9 = this.getLiteBansCore();
            if (var9.isDebugEnabled()) {
               var9.debug("0x11 <" + var95 + "> " + var5);
            }

            return var72;
         }

         int var6 = this.getLiteBansCore().getConfig().getSyncPollSkip();
         Sender[] var8 = var1.getPlatform().getOnlineSenders();
         Object[] var96 = var8;
         Object[] var10 = var96;
         Collection var11 = new ArrayList(var96.length);
         int var13 = 0;

         for (int var14 = var96.length; var13 < var14; var13++) {
            Object var15 = var10[var13];
            Sender var16 = (Sender)var15;
            String var70 = var16.getUuidString();
            var11.add(var70);
         }

         HashSet var7 = CollectionsKt.getHashSet((List)var11);
         if (var7.size() <= 0) {
            DatabaseManager var97 = var1.getDatabaseManager();
            var97.setByteValue((byte)(var97.getByteValue() + 1));
            if (var97.getByteValue() < var6) {
               return var72;
            }

            var1.getDatabaseManager().setByteValue((byte)0);
         }

         long var98 = DatabaseManager.currentTimeMillisDefault(var1.getDatabaseManager(), false, 1, null);
         if (var72 == 0L) {
            Table var101 = Table.SYNC;
            CharSequence var12 = "id";
            ResultSet var99 = QueryRunner.getResultSet(
               var1.prepare(
                  SqlString.getSqlString(SqlString.getString(SqlString.getString8(SqlString.getString2("SELECT " + var12 + " FROM " + var101), "id"), 1))
               )
            );
            if (var99.next()) {
               var72 = var99.getLong("id");
               LiteBansCore var102 = this.getLiteBansCore();
               if (var102.isDebugEnabled()) {
                  String var247 = "L+ " + var72;
                  var102.debug(var247);
               }
            }
         }

         ResultSet var100 = QueryRunner.getResultSet(var1.prepare("SELECT * FROM " + Table.SYNC + " WHERE id>?").getQueryStatement(var72));

         label1373:
         while (var100.next()) {
            long var103 = var100.getLong("id");
            long var106 = var100.getLong("info") - (long)42;
            int var109 = KotlinVersion.getInt2((int)(var106 & 65535L));
            long var110 = var106 >> 16;
            CommandAlias var18 = var1.getDatabaseManager().getCommandAlias(var1, var109, true);
            var72 = Math.max(var72, var103);
            if (var18 == null) {
               char var119 = 'l';
               LiteBansCore var134 = this.getLiteBansCore();
               if (var134.isDebugEnabled()) {
                  var134.debug("0x11 <" + var119 + "> " + var18);
               }
            } else if (var109 == var5.getIntValue()) {
               this.heartbeat(var1);
            } else {
               String var19 = var100.getString("msg");
               Flag var20 = Flag.Companion.getFlag((int)var110);
               if (var20 == null) {
                  char var133 = 'c';
                  Long var138 = var110;
                  LiteBansCore var154 = this.getLiteBansCore();
                  if (var154.isDebugEnabled()) {
                     var154.debug("0x11 <" + var133 + "> " + var138);
                  }
               } else {
                  ((AtomicLong)this.triple.getObject()).incrementAndGet();
                  LiteBansCore var21 = this.getLiteBansCore();
                  if (var21.isDebugEnabled()) {
                     String var248 = "id: " + var103 + ", type: " + var110 + ", server: " + KotlinVersion.getString(var109) + ", message: \"" + var19 + '"';
                     var21.debug(var248);
                  }

                  if (StringsKt.checkCharSequence3(var19, "server", false, 2, null) || StringsKt.checkCharSequence3(var19, "Server", false, 2, null)) {
                     String var22 = Message.getString(var18.getString(), false);
                     var19 = EntryPlaceholders.getString(EntryPlaceholders.getString(var19, "server", var22), "playerServer", var22);
                  }

                  Iterable var123 = var2;
                  boolean var10000;
                  if (var123 instanceof Collection && ((Collection)var123).isEmpty()) {
                     var10000 = false;
                  } else {
                     label1439: {
                        for (Object var24 : var123) {
                           AliasPredicate var25 = (AliasPredicate)var24;
                           if (!var25.checkCommandAlias(var18, var20, var19)) {
                              var10000 = true;
                              break label1439;
                           }
                        }

                        var10000 = false;
                     }
                  }

                  if (var10000) {
                     LiteBansCore var10002 = this.getLiteBansCore();
                     String var142 = "0x11 <Q> " + var110;
                     String[] var10003;
                     if (Intrinsics.checkObject(var142, "sort")) {
                        String[] var158 = new String[]{"00"};
                        var10003 = var158;
                     } else {
                        String[] var159 = new String[]{"01:" + var142};
                        var10003 = var159;
                     }

                     String[] var164 = var10003;
                     TextArgs var269 = new TextArgs(var10002, Arrays.copyOf(var164, var164.length));
                     var269.getInt();
                  } else {
                     Config var132 = this.getLiteBansCore().getConfig();
                     switch (SyncManagerWhenMappings.switchMap2[var20.ordinal()]) {
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                           ChunkSplitter var268 = chunkSplitter;
                           Intrinsics.handleObject2(var19, "element");
                           String[] var128 = ChunkSplitter.getStringArrayDefault(var268, var19, 0, 2, null);
                           this.handleMessage(var1, TargetParser.getString2(var128[0]), var128[1], var20, var7);
                           break;
                        case 6:
                        case 7:
                           if (var132.isSyncBroadcasts()) {
                              ChunkSplitter var265 = chunkSplitter;
                              Intrinsics.handleObject2(var19, "element");
                              String[] var127 = var265.getStringArray(var19, 3);
                              String var266 = (String)ArraysKt.getObject(var127, 1);
                              if (var266 == null) {
                                 var266 = "BAN";
                              }

                              String var153 = var266;
                              String var157 = var153.toUpperCase(Locale.ROOT);
                              PunishmentType[] var162 = PunishmentType.values();
                              int var173 = 0;

                              for (int var178 = var162.length; var173 < var178; var173++) {
                                 PunishmentType var184 = var162[var173];
                                 if (Intrinsics.checkObject(var184.name(), var157)) {
                                    String var267 = (String)ArraysKt.getObject(var127, 2);
                                    if (var267 == null) {
                                       var267 = var1.getDatabaseManager().getString();
                                    }

                                    String var163 = var267;
                                    NotificationManager var174 = this.getNotificationManager();
                                    NotificationManager.broadcastDefault(var174, var1, var127[0], var20, var184, var163, false, false, null, 112, null);
                                    continue label1373;
                                 }
                              }

                              throw new NoSuchElementException("Array contains no element matching the predicate.");
                           }
                           break;
                        case 8:
                           NotificationManager var152 = this.getNotificationManager();
                           Intrinsics.handleObject2(var19, "element");
                           NotificationManager.broadcastDefault(
                              var152,
                              var1,
                              var19,
                              "console",
                              null,
                              null,
                              var132.isSyncBroadcasts() && var132.isNotifyNotifyConsole() && var132.isLocalMessages(),
                              false,
                              null,
                              108,
                              null
                           );
                           break;
                        case 9:
                           ChunkSplitter var264 = chunkSplitter;
                           Intrinsics.handleObject2(var19, "element");
                           String[] var126 = ChunkSplitter.getStringArrayDefault(var264, var19, 0, 2, null);
                           String var156 = var126[0];
                           String var151 = var126[1];
                           NotificationManager var168 = this.getNotificationManager();
                           NotificationManager.broadcastDefault(var168, var1, var151, var156, null, null, var132.isSyncBroadcasts(), false, null, 108, null);
                           break;
                        case 10:
                           if (var132.isSyncNotifications()) {
                              ChunkSplitter var263 = chunkSplitter;
                              Intrinsics.handleObject2(var19, "element");
                              String[] var125 = ChunkSplitter.getStringArrayDefault(var263, var19, 0, 2, null);
                              this.getNotificationManager().notifyBannedJoin(var125[0], var125[1], false);
                           }
                           break;
                        case 11:
                           NotificationManager var150 = this.getNotificationManager();
                           Intrinsics.handleObject2(var19, "element");
                           NotificationManager.broadcastDefault(
                              var150,
                              var1,
                              var19,
                              var20,
                              null,
                              null,
                              var132.isSyncDupeipNotifications() && var132.isNotifyDupeipOnJoin() && var132.isSyncNotifications(),
                              false,
                              null,
                              108,
                              null
                           );
                           break;
                        case 12:
                           NotificationManager var149 = this.getNotificationManager();
                           Intrinsics.handleObject2(var19, "element");
                           NotificationManager.broadcastDefault(
                              var149, var1, var19, var20, PunishmentType.MUTE, null, var132.isSyncNotifications(), false, null, 104, null
                           );
                           break;
                        case 13:
                           Sender[] var155 = var132.getLiteBansPlatform().getOnlineSenders();
                           Intrinsics.handleObject(var155);
                           if (var155.length != 0) {
                              String var27;
                              String var28;
                              String var29;
                              boolean var30;
                              boolean var31;
                              String var148;
                              byte var190;
                              label1319: {
                                 ChunkSplitter var250 = chunkSplitter;
                                 Intrinsics.handleObject2(var19, "element");
                                 var124 = var250.getStringArray(var19, 8);
                                 var148 = TargetParser.getString2(var124[0]);
                                 var27 = var124[1];
                                 var28 = var124[2];
                                 var29 = var124[3];
                                 String var32 = var124[4];
                                 var30 = Intrinsics.checkObject(var32, "1");
                                 String var33 = var124[5];
                                 var31 = Intrinsics.checkObject(var33, "1");
                                 String var251 = var124[6];
                                 if (var251 != null) {
                                    UByte var252 = KotlinMisc.getUByte(var251);
                                    if (var252 != null) {
                                       var190 = var252.getByteValue();
                                       break label1319;
                                    }
                                 }
                                 
                                 var190 = (byte)-1;
                                 }
                                 
                                 int var194;
                                 label1314: {
                                 String var254 = var124[7];
                                 if (var254 != null) {
                                    Integer var255 = StringsKt.getInteger(var254);
                                    if (var255 != null) {
                                       var194 = var255;
                                       break label1314;
                                    }
                                 }
                                 
                                 var194 = 0;
                                 }
                              Sender[] var35 = var155;
                              int var36 = 0;
                              int var37 = var155.length;

                              while (true) {
                                 if (var36 >= var37) {
                                    var257 = null;
                                    break;
                                 }

                                 Sender var38 = var35[var36];
                                 if (StringsKt.checkString(var38.getName(), var28, true)) {
                                    var257 = var38;
                                    break;
                                 }

                                 var36++;
                              }

                              Sender var34 = /* cast-fix */(Sender)(var257);
                              if (var34 != null) {
                                 String var258 = var1.getDatabaseManager().getSenderServer(var34);
                                 if (var258 == null) {
                                    var258 = var1.getDatabaseManager().getString();
                                 }

                                 String var41 = var258;
                                 PunishmentEntry var197 = new PunishmentEntry(
                                    PunishmentType.KICK,
                                    var34.getUuidString(),
                                    var34.getIp(),
                                    var29,
                                    var148,
                                    var27,
                                    ServerScope.serverScopeParser.getString(),
                                    var41,
                                    var98,
                                    -1L,
                                    var190,
                                    var30,
                                    false,
                                    false,
                                    0L,
                                    16384,
                                    null
                                 );
                                 LiteCommand var259 = var132.getLiteBansPlatform().getCommandRegistry().getCommand("kick");
                                 Intrinsics.handleObject(var259, "");
                                 KickCommand var198 = (KickCommand)var259;
                                 Template var260 = this.getLiteBansCore().getTemplateManager().getTemplate(PunishmentType.KICK, null);
                                 if (var260 == null) {
                                    var260 = Template.Companion.getTemplate();
                                 }

                                 Template var201 = var260;
                                 PunishmentType var39 = PunishmentType.KICK;
                                 PlayerHistory var40 = new PlayerHistory(var34.getName(), var34.getUuidString(), var34.getIp(), new Date());
                                 String var261 = var27;
                                 if (var27 == null) {
                                    var261 = "";
                                 }

                                 String var42 = var261;
                                 String var43 = var197.getServerOrigin();
                                 String var262 = var197.getServerScope();
                                 if (var262 == null) {
                                    var262 = ServerScope.serverScopeParser.getString();
                                 }

                                 String var44 = var262;
                                 PunishmentRequest var204 = new PunishmentRequest(
                                    var39,
                                    false,
                                    var30,
                                    var31,
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
                                    var98,
                                    0L,
                                    0L,
                                    (byte)0,
                                    var201,
                                    null,
                                    false,
                                    null,
                                    var194,
                                    null,
                                    var40,
                                    null,
                                    null,
                                    var34,
                                    var42,
                                    var148,
                                    var44,
                                    var43,
                                    null,
                                    null,
                                    null,
                                    null,
                                    null,
                                    112967666,
                                    31,
                                    null
                                 );
                                 var204.setPunishmentEntry(var197);
                                 var198.kickTarget(var204, var1, var34);
                              }
                           }
                           break;
                        case 14:
                           DatabaseManager var26 = var1.getDatabaseManager();
                           int var147 = 21600000 + new LongBitSet(0L, 1, null).getInt(32400000);
                           var26.setLongValue2(var98 + (long)var147);
                           this.intValue = 0;
                           break;
                        case 15:
                           publishDefault(this, Flag.PONG, var1, var19, 0, 8, null);
                           break;
                        case 16:
                           DatabaseManager.updateServerInfoDefault(
                              var1.getDatabaseManager(), var1, this.getLiteBansCore().getConfig().getSyncPollTicks() * (long)2, false, 4, null
                           );
                           break;
                        case 17:
                           var1.getDatabaseManager().run9();
                     }
                  }
               }
            }
         }

         int var104 = this.getLiteBansCore().getConfig().getOfflineTabSize();
         if (var104 > 0 && (this.intValue <= 0 || this.byteValue >= 32)) {
            this.intValue = 0;
            ArrayList var105 = new ArrayList(6000);
            int[] var107 = new int[4];
            long var17 = System.currentTimeMillis();
            int var120 = var104 * 2;
            if (var120 >= 60000) {
               var120 = var104;
            }

            ResultSet var129 = this.selectOfflineNames(var1, var120);
            HashSet var135 = new HashSet(6000);
            HashMap var139 = new HashMap();
            ResultSet var143 = var129;
            AutoCloseable var160 = var129;
            Throwable var165 = null;

            try {
               while (var143.next()) {
                  String var191 = var143.getString("name");
                  String var195 = var191.toLowerCase(Locale.ENGLISH);
                  if (var195.length() >= 2) {
                     if (var195.charAt(0) == '.' || var195.charAt(0) == '*') {
                        var195 = var195.substring(1);
                     }

                     if (var135.add(var195)) {
                        Timestamp var270 = var143.getTimestamp("date");
                        long var196 = var270 != null ? var270.getTime() : System.currentTimeMillis();
                        var105.add(TuplesKt.getPair(var196, var195));
                     }
                  }
               }
            } catch (Throwable var92) {
               var165 = var92;
               throw var92;
            } finally {
               AutoCloseableKt.handleAutoCloseable(var160, var165);
            }

            if (var135.size() <= 5) {
               this.intValue = 1;
               return var72;
            }

            List var144 = var105;
            if (var144.size() > 1) {
               CollectionsKt.handleList(var144, new SortComparator7());
            }

            for (Object var166 : CollectionsKt.getList(var105, var104)) {
               Pair var169 = (Pair)var166;
               int var179 = this.getInt(StringsKt.getChar((CharSequence)var169.getObject2()));
               Map var185 = var139;
               Integer var192 = var179;
               Object var199 = var185.get(var192);
               Object var271;
               if (var199 == null) {
                  List var202 = new ArrayList((32 + var105.size()) / 5);
                  var185.put(var192, var202);
                  var271 = var202;
               } else {
                  var271 = var199;
               }

               List var205 = (List)var271;
               var205.add(var169);
               var107[var179] += ((String)var169.getObject2()).length() + 1;
            }

            IdScrambler var146 = (IdScrambler)RandomIdState.Companion.getSoftLazy().getValue();
            synchronized (var146) {
               ResultSet var167;
               if (this.byteValue >= 20 && var98 - var1.getDatabaseManager().getLongValue2() > 57600000L) {
                  this.clearMessages(var1);
                  var167 = EmptyResultSetHolder.getEmptyResultSet();
               } else {
                  var167 = this.getResultSet(var1);
               }

               int var170 = 0;
               int var175 = ((Object[])this.byteArrayArray2).length;

               byte[][] var180;
               for (var180 = new byte[var175][]; var170 < var175; var170++) {
                  var180[var170] = ArgParser.getByteArray();
               }

               byte[][] var193 = var180;
               ResultSet var171 = var167;
               AutoCloseable var181 = var167;
               Throwable var186 = null;

               try {
                  while (var171.next()) {
                     var193[var171.getInt("id")] = var171.getBytes("b");
                  }
               } catch (Throwable var89) {
                  var186 = var89;
                  throw var89;
               } finally {
                  AutoCloseableKt.handleAutoCloseable(var181, var186);
               }

               this.copyOnWriteArrayList.clear();
               this.byteArrayArray = new byte[0][];
               boolean var172 = var139.size() >= ((Object[])var193).length;
               var175 = 0;
               int var182 = ((Object[])this.byteArrayArray2).length;

               byte[][] var187 = new byte[var182][];
               for (; var175 < var182; var175++) {
                  var187[var175] = ArgParser.getByteArray();
               }
               
               var146.setByteArrayArray(var187);
               LongBitSet var177 = new LongBitSet(var146.getLongValue() << 32 << 32);
               byte[] var183 = new byte[40];
               Iterable var188 = var139.entrySet();

               for (Object var203 : CollectionsKt.getList(var188, new SortComparator5())) {
                  Entry var206 = (Entry)var203;
                  byte[] var207 = null;
                  Object[] var209 = (Object[])var193;
                  int var212 = 0;

                  boolean var272;
                  label1150: {
                     for (int var45 = var209.length; var212 < var45; var212++) {
                        Object var46 = var209[var212];
                        byte[] var47 = (byte[])var46;
                        if (var47.length == 0) {
                           var272 = false;
                           break label1150;
                        }
                     }

                     var272 = true;
                  }

                  if (var272) {
                     var207 = var193[((Number)var206.getKey()).intValue()];
                     var172 = false;
                  }

                  Intrinsics.handleObject(var206);
                  byte[] var273 = var207;
                  if (var207 == null) {
                     var273 = ArgParser.getByteArray();
                  }

                  byte[] var215 = var273;
                  Iterable var48 = (Iterable)var206.getValue();
                  Collection var51 = new ArrayList();

                  for (Object var54 : var48) {
                     label1176: {
                        Pair var55 = (Pair)var54;
                        if (((String)var55.getObject2()).length() < 20) {
                           CharSequence var57 = (CharSequence)var55.getObject2();

                           label1172: {
                              for (int var60 = 0; var60 < var57.length(); var60++) {
                                 char var61 = var57.charAt(var60);
                                 if (('a' <= var61 ? var61 >= '{' : true) && ('0' <= var61 ? var61 >= ':' : true) && var61 != ',') {
                                    var272 = false;
                                    break label1172;
                                 }
                              }

                              var272 = true;
                           }

                           if (var272) {
                              var272 = true;
                              break label1176;
                           }
                        }

                        var272 = false;
                     }

                     if (var272) {
                        var51.add(var54);
                     }
                  }

                  var48 = (List)var51;
                  var51 = new ArrayList(CollectionsKt.getInt(var48, 10));

                  for (Object var235 : var48) {
                     Pair var238 = (Pair)var235;
                     var51.add((String)var238.getObject2());
                  }

                  var48 = (List)var51;
                  List var67 = CollectionsKt.getList(var48, new SortComparator3(var146));
                  String var221 = CollectionsKt.getString(var67, ",", null, null, 0, null, null, 62, null);
                  IdScrambler var50 = var146;
                  byte[] var227 = var221.getBytes(Charsets.charset5);
                  byte[] var239 = var227;
                  int var56 = 0;
                  int var241 = 0;

                  for (int var58 = var227.length; var241 < var58; var241++) {
                     byte var59 = var239[var241];
                     int var245 = var56++;
                     var227[var245] = var50.getByteArray2()[var59 - 44];
                  }

                  byte[] var224 = new byte[var227.length];
                  var146.setFlag3(var146.isFlag());
                  if (var207 != null && var207.length != 0) {
                     var146.getBitflipper().setTable(var207);
                  } else {
                     byte[] var228 = new byte[8192];
                     var146.getBitflipper().setTable(var228);
                     var146.setFlag2(true);
                     IdScrambler.handleByteArrayDefault(var146, var227, var224, false, false, 12, null);
                     var215 = var146.getByteArray();
                     var146.setFlag2(false);
                  }

                  byte[][] var277 = var146.getByteArrayArray();
                  if (var277 != null) {
                     var277[((Number)var206.getKey()).intValue()] = var146.getBitflipper().getTable();
                  } else {
                     Object var278 = null;
                  }

                  byte[] var229 = var146.getByteArray(this.platform, var221, true, var146.isFlag(), var177, var183);
                  IdScrambler.handleByteArrayDefault(var146, var229, var224, false, false, 12, null);
                  boolean var52 = !this.checkIdScrambler(var146);
                  if (var52) {
                     IdScrambler.handleByteArray2Default(var146, var224, 600, false, false, 12, null);
                  }

                  byte[] var233;
                  if (var52) {
                     ArrayList var236 = new ArrayList(71);
                     var233 = IdScrambler.getByteArrayDefault(var146, var224, 0, 0, new CollectionAddFunction(var236, this), 6, null);
                     Collection var240 = var236;
                     this.setByteArrayArray((byte[][]) var240.toArray(new byte[0][]));
                     var146.run2();
                  } else {
                     var233 = IdScrambler.getByteArrayDefault(var146, var224, 0, 0, null, 14, null);
                  }

                  LiteBansCore var237 = this.getLiteBansCore();
                  if (var237.isDebugEnabled()) {
                     StringBuilder var279 = new StringBuilder()
                        .append("Bucket ")
                        .append(((Number)var206.getKey()).intValue())
                        .append(" filled with (")
                        .append(var229.length)
                        .append(" => ")
                        .append(var233.length)
                        .append(") bytes\n")
                        .append(ArraysKt.getString(var224, ",", null, null, 0, null, null, 62, null))
                        .append('\n')
                        .append(var221)
                        .append('\n');
                     LiteBansCore var281 = this.getLiteBansCore();
                     String var242 = ArraysKt.getString(var233, null, null, null, 0, null, null, 63, null);
                     String[] var10004;
                     if (Intrinsics.checkObject(var242, "sort")) {
                        String[] var243 = new String[]{"00"};
                        var10004 = var243;
                     } else {
                        String[] var244 = new String[]{"01:" + var242};
                        var10004 = var244;
                     }

                     String[] var246 = var10004;
                     TextArgs var10001 = new TextArgs(var281, Arrays.copyOf(var246, var246.length));
                     var237.debug(var279.append(KotlinVersion.getString(var10001.getInt())).toString());
                  }

                  var146.getBitflipper().setTable(ArgParser.getByteArray());
                  getByteArrayArray(this)[((Number)var206.getKey()).intValue()] = var233;
                  this.setIntValue(this.getIntValue() + ((List)var206.getValue()).size());
                  var207 = var215;
                  if (var172) {
                     try {
                        Table var211 = Table.CACHE8K;
                        CharSequence var214 = "id,b";
                        byte var217 = 44;
                        CharSequence var223 = var214;
                        int var230 = 0;

                        for (int var231 = 0; var231 < var223.length(); var231++) {
                           char var234 = var223.charAt(var231);
                           if (var234 == var217) {
                              var230++;
                           }
                        }

                        String var216 = "?";
                        if (var230 > 0) {
                           StringBuilder var218 = new StringBuilder(var230 * 2);
                           var48 = new IntRange(0, var230);
                           Iterator var225 = var48.iterator();

                           while (var225.hasNext()) {
                              ((IntIterator)var225).getInt();
                              var218.append("?,");
                           }

                           var216 = StringsKt.getString(var218.toString(), 1);
                        }

                        QueryStatement var210 = var1.prepare(
                           SqlString.getSqlString(SqlString.getString2("INSERT INTO " + var211 + '(' + var214 + ")VALUES(" + var216 + ')'))
                        );
                        QueryRunner.getResultSet(var210.getQueryStatement(((Number)var206.getKey()).intValue()).getQueryStatement(var207));
                     } catch (Exception var88) {
                        if (!(var88 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var88); }
                        String var280 = var88.getMessage();
                        if (var280 == null) {
                           var280 = "";
                        }

                        String var213 = var280;
                        if (!StringsKt.checkCharSequence3(var213, "Duplicate entry", false, 2, null)
                           && !StringsKt.checkCharSequence3(var213, "duplicate key", false, 2, null)) {
                           var1.getDatabaseManager().reportError(var88);
                        }
                     }
                  }
               }

               if (var172) {
                  publishDefault(this, Flag.EXPIRED_NOTIFY, var1, null, 0, 12, null);
               }
            }

            // $VF: monitorexit
            long var108 = System.currentTimeMillis() - var17;
            this.byteValue = 0;
            int var111 = ArraysKt.getInt2(var107);
            byte[][] var113 = this.byteArrayArray2;
            int var116 = 0;
            var120 = 0;

            for (int var130 = var113.length; var120 < var130; var120++) {
               byte[] var140 = var113[var120];
               int var249 = var140.length;
               var116 += var249;
            }

            var113 = this.byteArrayArray;
            var116 = 0;
            var120 = 0;

            for (int var131 = var113.length; var120 < var131; var120++) {
               byte[] var141 = var113[var120];
               int var71 = var141.length;
               var116 += var71;
            }

            int var112 = var116 + var116;
            int var115 = Math.abs(100 - (int)((double)var112 / (double)var111 * 100.0));
            String var118 = " (reduced " + var115 + "% from " + var111 + " bytes)";
            if (var112 >= var111) {
               var118 = "";
            }

            this.getLiteBansCore()
               .debug("[Sync] Cached " + this.intValue + " offline users for tab completion in " + var108 + " ms. Memory used: " + var112 + " bytes" + var118);
         }
      } catch (Throwable var94) {
         var1.getDatabaseManager().reportError(var94);
      }

      return var72;
   }

   private final ResultSet selectOfflineNames(PunishmentStorage var1, int var2) {
      Table var3 = Table.HISTORY;
      CharSequence var4 = "name,date";
      return QueryRunner.getResultSet(
         var1.prepare(SqlString.getSqlString(SqlString.getString(SqlString.getString8(SqlString.getString2("SELECT " + var4 + " FROM " + var3), "id"), var2)))
      );
   }

   public final ResultSet getResultSet(@NotNull PunishmentStorage var1) {
      Table var2 = Table.CACHE8K;
      CharSequence var3 = "*";
      return QueryRunner.getResultSet(var1.prepare(SqlString.getSqlString(SqlString.getString2("SELECT " + var3 + " FROM " + var2))));
   }

   public final ResultSet clearMessages(@NotNull PunishmentStorage var1) {
      Table var2 = Table.CACHE8K;
      return QueryRunner.getResultSet(var1.prepare(SqlString.getSqlString(SqlString.getString2("DELETE FROM " + var2))));
   }

   public final boolean checkIdScrambler(@NotNull IdScrambler var1) {
      if (((Object[])this.byteArrayArray).length == 0) {
         return false;
      } else {
         if (var1.getList().isEmpty()) {
            byte var2 = -128;
            Object[] var3 = (Object[])this.byteArrayArray;
            Object[] var5 = var3;
            Collection var6 = new ArrayList(var3.length);
            int var8 = 0;

            for (int var9 = var3.length; var8 < var9; var8++) {
               Object var10 = var5[var8];
               byte[] var11 = (byte[])var10;
               byte var13 = var2++;
               var6.add(new IdScramblerState(var11, var13, (short)0, 4, null));
            }

            var1.setList((List)var6);
         }

         return true;
      }
   }

   public final ByteArrayOutputStream getByteArrayOutputStream(int var1) {
      return new PublicByteArrayOutputStream(var1);
   }

   // $VF: Could not create synchronized statement, marking monitor enters and exits
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public final ByteKeyedMap loadCacheBucket(int var1, @NotNull IdScrambler var2) {
      LiteBansCore var3 = this.getLiteBansCore();
      if (var3.isDebugEnabled()) {
         StringBuilder var10000 = new StringBuilder()
            .append("Read bucket ")
            .append(var1)
            .append(". ")
            .append(var2.getList().size())
            .append(" loaded, ")
            .append(((Object[])this.byteArrayArray).length)
            .append(" stored\n")
            .append(CollectionsKt.getString(var2.getList(), "|", null, null, 0, null, a -> getCharSequence((IdScramblerState) a), 30, null))
            .append('\n');
         Object[] var6 = (Object[])this.byteArrayArray;
         StringBuilder var7 = var10000;
         Object[] var9 = var6;
         Collection var10 = new ArrayList(var6.length);
         int var12 = 0;

         for (int var13 = var6.length; var12 < var13; var12++) {
            Object var14 = var9[var12];
            byte[] var15 = (byte[])var14;
            var10.add(ArraysKt.getString(var15, null, null, null, 0, null, null, 63, null));
         }

         var3.debug(var7.append((List)var10).toString());
      }

      ByteKeyedMap var32;
      label114: {
         ByteKeyedMap var31;
         label113: {
            ByteKeyedMap var30;
            synchronized (var2) {
               this.checkIdScrambler(var2);
               if (var2.getList().isEmpty() || ((Object[])this.byteArrayArray).length == 0) {
                  this.getLiteBansCore().debug(10, "No cache");
                  var32 = new ByteKeyedMap((byte)var1, MapsKt.getMap());
                  break label114;
               }

               byte[] var5 = this.byteArrayArray2[var1];
               LiteBansCore var35 = this.getLiteBansCore();
               if (var35.isDebugEnabled()) {
                  var35.debug(ArraysKt.getString(var5, null, null, null, 0, null, null, 63, null));
               }

               byte[][] var36 = var2.getByteArrayArray();
               if (var5 == ArgParser.getByteArray() || var36 == null || var36[var1].length == 0) {
                  this.getLiteBansCore().debug(10, "No cache");
                  var31 = new ByteKeyedMap((byte)var1, MapsKt.getMap());
                  break label113;
               }

               var2.getBitflipper().setTable(var36[var1]);
               ByteArrayOutputStream var37 = this.getByteArrayOutputStream(var5.length * 4);
               var2.handleByteArray(var5, var37);
               byte[] var38 = new byte[var37.size()];
               var2.setFlag3(var2.isFlag());
               byte[] var39 = Arrays.copyOf(var37.toByteArray(), var37.size());
               Intrinsics.handleObject(var39);
               IdScrambler.handleByteArrayDefault(var2, var39, var38, false, false, 8, null);
               LiteBansCore var11 = this.getLiteBansCore();
               if (var11.isDebugEnabled()) {
                  var11.debug(ArraysKt.getString(var38, null, null, null, 0, null, null, 63, null));
               }

               String var40 = var2.getStringBuilder(var38).toString();
               CharSequence var56 = var40;
               char[] var42 = new char[]{','};
               Collection var43 = StringsKt.getList(var56, var42, false, 0, 6, null);
               String[] var41 = /* cast-fix */(String[])(var43.toArray(new String[0]));
               HashMap var44 = new HashMap(var41.length * 2, 0.5F);
               int var45 = 0;

               for (int var48 = var41.length; var45 < var48; var45++) {
                  String var16 = var41[var45];
                  if (var16.length() > 0) {
                     int[] var17 = this.getIntArray(var16);
                     int[] var18 = var17;
                     int var20 = 0;

                     for (int var21 = var17.length; var20 < var21; var20++) {
                        int var22 = var18[var20];
                        Map var25 = var44;
                        Integer var26 = var22;
                        Object var28 = var25.get(var26);
                        if (var28 == null) {
                           ArrayList var29 = new ArrayList(64);
                           var25.put(var26, var29);
                           var56 = /* cast-fix */(CharSequence)(var29);
                        } else {
                           var56 = (CharSequence)var28;
                        }

                        ((ArrayList)var56).add(var16);
                     }
                  }
               }

               Map var46 = var44;

               for (Object var51o : var46.entrySet()) {
                  Entry var51 = (Entry) var51o;
                  ((ArrayList)var51.getValue()).trimToSize();
               }

               LiteBansPlatform var49 = this.platform;
               if (var49 != null) {
                  LiteBansCore var58 = (LiteBansCore)var49.getModule(LiteBansCore.class);
                  if (var58 != null) {
                     LiteBansCore var52 = var58;
                     String var19 = var40.toString();
                     String[] var10003;
                     if (Intrinsics.checkObject(var19, "sort")) {
                        String[] var53 = new String[]{"00"};
                        var10003 = var53;
                     } else {
                        String[] var54 = new String[]{"01:" + var19};
                        var10003 = var54;
                     }

                     String[] var55 = var10003;
                     TextArgs var59 = new TextArgs(var52, Arrays.copyOf(var55, var55.length));
                     if (var59.getInt() == var52.hashCode()) {
                        throw new IllegalArgumentException();
                     }
                  }
               }

               ByteKeyedMap var47 = new ByteKeyedMap((byte)var1, new HashMap(var44));
               this.copyOnWriteArrayList.add(new SoftReference<>(var47));
               var30 = var47;
            }

            // $VF: monitorexit
            return var30;
         }

         // $VF: monitorexit
         return var31;
      }

      // $VF: monitorexit
      return var32;
   }

   public final int getInt(char var1) {
      return var1 % ((Object[])this.byteArrayArray2).length;
   }

   public final int[] getIntArray(@NotNull String var1) {
      if (var1.length() == 0) {
         return new int[]{0};
      } else {
         int var2 = var1.charAt(0) * 3;
         if (var1.length() <= 1) {
            return new int[]{var2};
         } else {
            int var3 = var1.substring(0, 2).hashCode() * 13;
            if (var1.length() <= 2) {
               return new int[]{var3, var2};
            } else {
               int var4 = var1.substring(0, 3).hashCode() * 17;
               return new int[]{var4, var3, var2};
            }
         }
      }
   }

   private static final LiteBansCore getLiteBansCore(SyncManager var0) {
      return (LiteBansCore)var0.platform.getModule(LiteBansCore.class);
   }

   private static final DatabaseManager getDatabaseManager(SyncManager var0) {
      return (DatabaseManager)var0.platform.getModule(DatabaseManager.class);
   }

   private static final NotificationManager getNotificationManager(SyncManager var0) {
      return (NotificationManager)var0.platform.getModule(NotificationManager.class);
   }

   private static final MuteManager getMuteManager(SyncManager var0) {
      return (MuteManager)var0.platform.getModule(MuteManager.class);
   }

   private static final CharSequence getCharSequence(IdScramblerState var0) {
      return ArraysKt.getString(var0.getByteArray(), null, null, null, 0, null, null, 63, null);
   }

   public static final byte[][] getByteArrayArray(SyncManager var0) {
      return var0.byteArrayArray2;
   }
}
