package litebans.database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;
import java.util.Map.Entry;
import litebans.command.ArgUtils;
import litebans.command.TextArgs;
import litebans.config.Config;
import litebans.config.Template;
import litebans.config.TemplateGroup;
import litebans.core.LiteBansCore;
import litebans.core.NotificationManager;
import litebans.database.migration.MigrationAddAccept;
import litebans.database.migration.MigrationAddRemovedByDate;
import litebans.database.migration.MigrationAddRemovedByName;
import litebans.database.migration.MigrationAddRemovedByReason;
import litebans.database.migration.MigrationAddRemovedByUuid;
import litebans.database.migration.MigrationAddServerOrigin;
import litebans.database.migration.MigrationAddTemplate;
import litebans.lib.compress.archivers.tar.Target;
import litebans.lib.compress.archivers.tar.TargetParser;
import litebans.lib.kotlin.NoWhenBranchMatchedException;
import litebans.lib.kotlin.Pair;
import litebans.lib.kotlin.ResultFailure;
import litebans.lib.kotlin.TuplesKt;
import litebans.lib.kotlin.UByte;
import litebans.lib.kotlin.collections.CollectionsKt;
import litebans.lib.kotlin.collections.IntIterator;
import litebans.lib.kotlin.io.AutoCloseableKt;
import litebans.lib.kotlin.jvm.internal.DefaultConstructorMarker;
import litebans.lib.kotlin.jvm.internal.Function1;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.ranges.IntRange;
import litebans.lib.kotlin.text.CharsKt;
import litebans.lib.kotlin.text.StringsKt;
import litebans.platform.LiteBansPlatform;
import litebans.platform.Sender;
import litebans.punishment.ExecutorInfo;
import litebans.punishment.Flag;
import litebans.punishment.IdType;
import litebans.punishment.MuteManager;
import litebans.punishment.PlayerHistory;
import litebans.punishment.PlayerInfo;
import litebans.punishment.PunishmentEntry;
import litebans.punishment.PunishmentType;
import litebans.punishment.ServerScope;
import litebans.sync.AsyncQueue;
import litebans.sync.SyncManager;
import litebans.update.BuildInfo;
import litebans.util.DecimalFormats;
import litebans.util.Unreachable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SqlStorage implements PunishmentStorage {
   public static final SqlWhereBuilder sqlWhereBuilder = new SqlWhereBuilder(null);
   private final LiteBansPlatform liteBansPlatform;
   private final Connection connection;
   private final MuteManager muteManager;
   private final NotificationManager notificationManager;
   private final LiteBansCore liteBansCore;
   private static final Function1 function1 = sqlWhereBuilder.getFunction1(a -> getQueryStatement4((QueryStatement) a));
   public static final int intValue = 72000000;
   public static final int intValue2 = 57600000;
   public static final int intValue3 = 28800000;
   public static final int intValue4 = 64;
   private static int intValue5 = 64;

   public SqlStorage(@NotNull LiteBansPlatform var1, @NotNull Connection var2) {
      this.liteBansPlatform = var1;
      this.connection = var2;
      this.muteManager = (MuteManager)this.liteBansPlatform.getModule(MuteManager.class);
      this.notificationManager = (NotificationManager)this.liteBansPlatform.getModule(NotificationManager.class);
      this.liteBansCore = (LiteBansCore)this.liteBansPlatform.getModule(LiteBansCore.class);
   }

   @Override
   public final LiteBansPlatform getPlatform() {
      return this.liteBansPlatform;
   }

   public final NotificationManager getNotificationManager() {
      return this.notificationManager;
   }

   public final LiteBansCore getLiteBansCore() {
      return this.liteBansCore;
   }

   public DatabaseMetaData getDatabaseMetaData() {
      try {
      return this.connection.getMetaData();
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean isClosed() {
      try {
      return this.connection.isClosed();
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public long countEntries() {
      Iterable var1 = Table.Companion.getList();
      long var2 = 0L;

      for (Object var5 : var1) {
         Table var6 = (Table)var5;
         long var10 = EntryReader.getLong(this, var6, null, false, 6, null);
         var2 += var10;
      }

      return var2;
   }

   public final Pair buildInsertColumns(@NotNull PunishmentEntry var1, boolean var2) {
      String var3 = "uuid,ip,reason,banned_by_uuid,banned_by_name,time,until,template,server_scope,server_origin,silent,ipban,ipban_wildcard,active"
         + var1.getString();
      CharSequence var5 = var3;
      byte var6 = 44;
      CharSequence var8 = var5;
      int var10 = 0;

      for (int var11 = 0; var11 < var8.length(); var11++) {
         char var12 = var8.charAt(var11);
         if (var12 == var6) {
            var10++;
         }
      }

      int var4 = var10 + 1;
      StringBuilder var14 = new StringBuilder((var4 + 1) * 2);

      for (int var15 = 0; var15 < var4; var15++) {
         if (var2 && var15 > 9) {
            var14.append("?::bit,");
         } else {
            var14.append('?').append(',');
         }
      }

      String var16 = var14.substring(0, var14.length() - 1);
      return TuplesKt.getPair(var3, var16);
   }

   @Override
   public void insertEntry(@NotNull PunishmentEntry var1) {
      Pair var2 = this.buildInsertColumns(var1, Intrinsics.checkObject(this.liteBansCore.getConfig().getSqlDriver(), "pgsql"));
      String var3 = (String)var2.getObject3();
      String var4 = (String)var2.getObject22();
      LiteBansCore var5 = this.liteBansCore;
      byte var6 = 1;
      if (var5.isDebugLevel(var6)) {
         var5.debug("Created new " + var1.getType() + " for server:" + ServerScope.serverScopeParser.getString(var1.getServerScope()));
      }

      var5 = this.liteBansCore;
      var6 = 1;
      if (var5.isDebugLevel(var6)) {
         var5.debug("Adding entry: " + var1);
      }

      String var30 = "INSERT INTO " + var1.getType().getTable() + '(' + var3 + ")VALUES(" + var4 + ')';
      QueryStatement var32 = this.prepare(var30);
      QueryStatement var10 = var32;
      String[] var11 = new String[5];
      String var10002 = var1.getUuid();
      var11[0] = var10002 != null ? ArgUtils.getString(var10002, 36) : null;
      var10002 = var1.getIp();
      var11[1] = var10002 != null ? ArgUtils.getString(var10002, 45) : null;
      CharSequence var12 = var1.getReason();
      short var13 = 2048;
      var11[2] = ArgUtils.getString(var12.toString(), var13);
      var10002 = var1.getExecutorUUID();
      var11[3] = var10002 != null ? ArgUtils.getString(var10002, 36) : null;
      var10002 = var1.getExecutorName();
      var11[4] = var10002 != null ? ArgUtils.getString(var10002, 128) : null;
      String[] var16 = var11;
      int var18 = 0;

      for (int var19 = var11.length; var18 < var19; var18++) {
         String var20 = var16[var18];
         var10.getQueryStatement(var20);
      }

      var10 = var32;
      long[] var36 = new long[]{var1.getDateStart(), var1.getDateEnd()};
      long[] var39 = var36;
      var18 = 0;

      for (int var45 = var36.length; var18 < var45; var18++) {
         long var23 = var39[var18];
         var10.getQueryStatement(var23);
      }

      var10 = var32.getQueryStatement(var1.getTemplate());
      var11 = new String[]{ServerScope.serverScopeParser.getString(var1.getServerScope()), ServerScope.serverScopeParser.getString(var1.getServerOrigin())};
      var16 = var11;
      var18 = 0;

      for (int var46 = var11.length; var18 < var46; var18++) {
         String var48 = var16[var18];
         var10.getQueryStatement(var48);
      }

      var10 = var10;
      boolean[] var38 = new boolean[]{var1.isSilent(), var1.isIpban(), var1.isWildcardIpBan(), var1.isActive()};
      boolean[] var41 = var38;
      var18 = 0;

      for (int var47 = var38.length; var18 < var47; var18++) {
         boolean var49 = var41[var18];
         var10.getQueryStatement(var49);
      }

      if (var1.getType() == PunishmentType.WARN) {
         var32.getQueryStatement(var1.isWarned());
      }

      QueryRunner.getInt(var10);
   }

   @Override
   public PlayerHistory getPlayerHistory(@NotNull PlayerHistory var1, boolean var2) {
      EntryReader.handlePunishmentStorage(this, var1.getString(), var1.getUuid(), var1.getIp(), var2, false, 16, null);
      return var1;
   }

   @Override
   public void recordLogin(@Nullable String var1, @Nullable String var2, @Nullable String var3, boolean var4, boolean var5) {
      String var10000 = var1;
      if (var1 == null) {
         var10000 = "#";
      }

      String var6 = var10000;
      var10000 = var3;
      if (var3 == null) {
         var10000 = "#";
      }

      String var7 = var10000;
      int var11 = var6.length();
      byte var12 = this.getLiteBansCore().getConfig().getUsernameMaxLength();
      if (Intrinsics.getInt(var11, var12 & 255) > 0) {
         String var47 = "Player name \"" + var6 + "\" length > " + UByte.getString(this.getLiteBansCore().getConfig().getUsernameMaxLength());
         throw new IllegalArgumentException(var47.toString());
      } else if (var2 != null && var2.length() > 36) {
         String var46 = "UUID \"" + var2 + "\" length > 36";
         throw new IllegalArgumentException(var46.toString());
      } else {
         try {
            if (this.historyExists(var6, var2, var7)) {
               if (var4 && !StringsKt.checkCharSequence3(var7, '#', false, 2, null) && var2 != null) {
                  Table var33 = Table.HISTORY;
                  String var34 = SqlString.getString4(SqlString.getString2("UPDATE " + var33), "date=CURRENT_TIMESTAMP");
                  CharSequence var39 = "ip=?";
                  CharSequence[] var43 = new CharSequence[]{SqlString.getSqlString(var34), var39};
                  CharSequence[] var50 = var43;
                  int var55 = 0;

                  for (int var58 = var43.length; var55 < var58; var55++) {
                     CharSequence var62 = var50[var55];
                     if (var62.length() <= 0) {
                        String var66 = "Failed requirement.";
                        throw new IllegalArgumentException(var66.toString());
                     }
                  }

                  QueryStatement var35 = this.prepare(
                     SqlString.getSqlString(
                        SqlString.getString(SqlString.getString(SqlString.getString2(SqlString.getString(var34) + " WHERE " + var39), "uuid=?"), "name=?")
                     )
                  );
                  String[] var40 = new String[]{var7, var2, var6};
                  String[] var53 = var40;
                  int var59 = 0;

                  for (int var63 = var40.length; var59 < var63; var59++) {
                     String var65 = var53[var59];
                     var35.getQueryStatement(var65);
                  }

                  QueryRunner.getInt(var35);
               }

               return;
            }

            if (this.liteBansCore.getConfig().isDeletePreviousHistory() && var5) {
               Table var8 = Table.HISTORY;
               String var28 = SqlString.getString2("DELETE FROM " + var8);
               CharSequence var9 = "name{=?}";
               CharSequence[] var41 = new CharSequence[]{SqlString.getSqlString(var28), var9};
               CharSequence[] var13 = var41;
               int var15 = 0;

               for (int var16 = var41.length; var15 < var16; var15++) {
                  CharSequence var17 = var13[var15];
                  if (var17.length() <= 0) {
                     String var20 = "Failed requirement.";
                     throw new IllegalArgumentException(var20.toString());
                  }
               }

               QueryStatement var29 = this.prepare(
                  SqlString.getSqlString(SqlString.getString2(SqlString.getString2(SqlString.getString(var28) + " WHERE " + var9), "uuid=?"))
               );
               String[] var36 = new String[]{var6, var2};
               String[] var14 = var36;
               int var56 = 0;

               for (int var60 = var36.length; var56 < var60; var56++) {
                  String var18 = var14[var56];
                  var29.getQueryStatement(var18);
               }

               QueryRunner.getInt(var29);
            }

            Table var30 = Table.HISTORY;
            CharSequence var37 = "name,uuid,ip";
            var12 = 44;
            CharSequence var51 = var37;
            int var57 = 0;

            for (int var61 = 0; var61 < var51.length(); var61++) {
               char var64 = var51.charAt(var61);
               if (var64 == var12) {
                  var57++;
               }
            }

            String var42 = "?";
            if (var57 > 0) {
               StringBuilder var45 = new StringBuilder(var57 * 2);
               Iterable var48 = new IntRange(0, var57);
               Iterator var54 = var48.iterator();

               while (var54.hasNext()) {
                  ((IntIterator)var54).getInt();
                  var45.append("?,");
               }

               var42 = StringsKt.getString(var45.toString(), 1);
            }

            QueryStatement var31 = this.prepare(SqlString.getSqlString(SqlString.getString2("INSERT INTO " + var30 + '(' + var37 + ")VALUES(" + var42 + ')')));
            QueryStatement var49 = var31;
            String[] var52 = new String[]{var6, var2, var7};
            String[] var19 = var52;
            int var21 = 0;

            for (int var22 = var52.length; var21 < var22; var21++) {
               String var23 = var19[var21];
               var49.getQueryStatement(var23);
            }

            QueryRunner.getResultSet(var31);
            LiteBansCore var32 = this.liteBansCore;
            byte var38 = 1;
            if (var32.isDebugLevel(var38)) {
               var32.debug("New login record: [" + var6 + ':' + var2 + ':' + var7 + ']');
            }
         } catch (Exception var27) {
            if (!(var27 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var27); }
            var10000 = var27.getMessage();
            if (var10000 != null ? StringsKt.checkCharSequence3(var10000, "Duplicate entry", false, 2, null) : false) {
               return;
            }

            this.getDatabaseManager().reportError(var27);
         }
      }
   }

   @Override
   public void close() {
      try {
      this.connection.close();
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   public final Statement getStatement() {
      try {
      return this.connection.createStatement();
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int count(@NotNull SelectQuery var1, @NotNull PunishmentType var2) {
      try {
      ResultSet var3 = var1.getResultSet(var2, "COUNT(*)");
      byte var4 = 1;
      Integer var10000 = var3.next() ? var3.getInt(var4) : null;
      return var10000 != null ? var10000 : 0;
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public List findEntries(@Nullable String var1, @Nullable String var2, @NotNull PunishmentType var3, @Nullable String var4, boolean var5, int var6) {
      ResultFailure var7 = new ResultFailure();
      var7.object = sqlWhereBuilder.getFunction12();
      long var8 = this.liteBansCore.getConfig().getDurationsEffectiveIpbanDuration();
      String var10 = "uuid=?";
      if (var2 != null && !StringsKt.checkCharSequence(var2, '#', false, 2, null)) {
         String var11 = " OR(ipban_wildcard={true} AND active={true} AND ? LIKE ip)";
         String var12 = " OR(ipban_wildcard={true})";
         if (var8 > 0L) {
            var10 = var10 + " OR(ipban={true} AND(ip=?" + var11 + ")AND((time+" + var8 + ")>?" + var12 + "))";
            var7.object = sqlWhereBuilder.getFunction12((Function1)var7.object, a -> getQueryStatement(var1, var2, (QueryStatement) a));
         } else {
            var10 = var10 + " OR(ipban={true} AND(ip=?" + var11 + "))";
            var7.object = sqlWhereBuilder.getFunction12((Function1)var7.object, a -> getQueryStatement2(var1, var2, (QueryStatement) a));
         }
      } else {
         var7.object = sqlWhereBuilder.getFunction12((Function1)var7.object, a -> getQueryStatement(var1, (QueryStatement) a));
      }

      List var17 = this.liteBansCore.getTemplateManager().getList(var3);
      if (!var17.isEmpty()) {
         var10 = '(' + var10 + ")AND template NOT IN(" + CollectionsKt.getString(var17, ",", null, null, 0, null, null, 62, null) + ')';
      }

      IdType var14 = IdType.CUSTOM;
      PunishmentStorage var16 = this;
      ServerScopeFilter var15 = new ServerScopeFilter(var16.getPlatform(), var4);
      TimeOrderedSelectQuery var18 = new TimeOrderedSelectQuery(this, var5, var6, var7, var10, var14, var15);
      return this.readEntries(SelectQuery.getResultSetDefault(var18, var3, null, 2, null), var3);
   }

   @Override
   public List getList(@NotNull String var1, @NotNull PunishmentType var2, @NotNull String var3, int var4) {
      SelectQueryFactory var6 = SelectQuery.selectQueryFactory;
      IdType var7 = IdType.EXECUTOR_UUID;
      SelectQuery var5 = SelectQueryFactory.getSelectQueryDefault(var6, this, var1, var3, var7, false, "time", false, var4, null, 168, null);
      return this.readEntries(SelectQuery.getResultSetDefault(var5, var2, null, 2, null), var2);
   }

   @Override
   public boolean columnExists(@NotNull Table var1, @NotNull String var2, boolean var3) {
      try {
      boolean var10000;
      if (var3) {
         var10000 = QueryRunner.getResultSet(
               this.prepare("SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME LIKE '" + var1 + "' AND COLUMN_NAME='" + var2 + "' LIMIT 1")
            )
            .next();
      } else {
         DatabaseMetaData var8 = this.getDatabaseMetaData();
         String var10003 = Table.getTableNameDefault(var1, null, false, 3, null);
         DatabaseManager var4 = this.getDatabaseManager();
         LiteBansCore var6 = (LiteBansCore)var4.platform.getModule(LiteBansCore.class);
         var10000 = var8.getColumns(
               null, null, var10003, Intrinsics.checkObject(var6.getConfig().getSqlDriver(), "h2") ? var2.toUpperCase(Locale.ENGLISH) : var2
            )
            .next();
      }

      return var10000;
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean tableExists(@NotNull CharSequence var1) {
      try {
      return this.getDatabaseMetaData().getTables(null, null, var1.toString(), null).next();
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public List findHistoryByIp(@Nullable String var1, int var2) {
      try {
      String var3 = var1;
      if (var1 != null && !StringsKt.checkCharSequence(var1, '#', false, 2, null)) {
         Target var4 = TargetParser.INSTANCE.getTarget(var1);
         String var5 = var4.getString();
         String var6 = sqlWhereBuilder.getString(this, var5);
         if (var4.getType() == 2 && StringsKt.checkCharSequence3(var1, '*', false, 2, null)) {
            var3 = StringsKt.getString(var1, '*', '%', false, 4, null);
            var6 = "ip LIKE ?";
         }

         Table var8 = Table.HISTORY;
         CharSequence var9 = this.notificationManager.getCharSequence3();
         String var21 = SqlString.getString2("SELECT " + var9 + " FROM " + var8);
         CharSequence[] var10 = new CharSequence[]{SqlString.getSqlString(var21), var6};
         CharSequence[] var12 = var10;
         int var14 = 0;

         for (int var15 = var10.length; var14 < var15; var14++) {
            CharSequence var16 = var12[var14];
            if (var16.length() <= 0) {
               String var19 = "Failed requirement.";
               throw new IllegalArgumentException(var19.toString());
            }
         }

         ResultSet var7 = QueryRunner.getResultSet(
            this.prepare(
                  SqlString.getSqlString(
                     SqlString.getString(SqlString.getString8(SqlString.getString2(SqlString.getString(var21) + " WHERE " + var6), "date"), var2)
                  )
               )
               .getQueryStatement(var3)
         );
         ArrayList var22 = new ArrayList(4);

         while (var7.next()) {
            var22.add(this.readHistory(var7, true, this.liteBansCore.getConfig().getSqlDriver()));
         }

         return var22;
      } else {
         return CollectionsKt.getList();
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public PlayerHistory readHistory(@NotNull ResultSet var1, boolean var2, @Nullable String var3) {
      try {
      return new PlayerHistory(
         var1.getString("name"),
         TargetParser.getString2(var1.getString("uuid")),
         var1.getString("ip"),
         this.notificationManager.readDate(var1, "date", var2, var3)
      );
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Collection findEntriesInRange(@NotNull PunishmentType var1, long var2, long var4, boolean var6) {
      long var7 = Math.max(0L, var2);
      long var9 = Math.max(0L, var7 * var4);
      SelectQuery var11 = SelectQuery.selectQueryFactory.getSelectQuery(this, "uuid IS NOT NULL", "__ALL__", IdType.CUSTOM, var6, "time", false, var4, var9);
      return this.readEntries(SelectQuery.getResultSetDefault(var11, var1, null, 2, null), var1);
   }

   @Override
   public PunishmentEntry findActiveFor(@NotNull PunishmentEntry var1, boolean var2) {
      return EntryReader.getPunishmentEntry(
         this, var1.getUuid(), var1.isIpban() ? var1.getIp() : null, var1.getType(), var1.getServerScope(), var2, false, 32, null
      );
   }

   @Override
   public PunishmentEntry findActive(@Nullable String var1, @Nullable String var2, @NotNull PunishmentType var3, boolean var4, boolean var5) {
      return this.findActive(var1, var2, var3, this.getDatabaseManager().getString(), var4, var5);
   }

   @Override
   public PunishmentEntry findActive(
      @Nullable String var1, @Nullable String var2, @NotNull PunishmentType var3, @Nullable String var4, boolean var5, boolean var6
   ) {
      try {
      String var7 = TargetParser.INSTANCE.getString(var1);
      EntryReader.handlePunishmentStorage(this, false, 1, null);
      ServerScopeFilter var10000;
      if (var6) {
         PunishmentStorage var9 = this;
         boolean var10 = true;
         var10000 = new SingleServerScopeFilter(var9.getPlatform(), var4, var10);
      } else {
         PunishmentStorage var31 = this;
         var10000 = new ServerScopeFilter(var31.getPlatform(), var4);
      }

      ServerScopeFilter var8 = var10000;
      String var32 = "AND " + "active=" + this.getNotificationManager().getCharSequence() + " AND(until<1 OR until>?)";
      String var33 = "";
      String var11 = "";
      if (var3 != PunishmentType.WARN && var3 != PunishmentType.KICK && !this.liteBansCore.getConfig().isDisableWildcardBans()) {
         var33 = " OR(ipban_wildcard="
            + this.notificationManager.getCharSequence()
            + " AND active="
            + this.notificationManager.getCharSequence()
            + " AND ? LIKE ip)";
         var11 = " OR(ipban_wildcard=" + this.notificationManager.getCharSequence() + ')';
      }

      boolean var12 = var7 == null || StringsKt.checkCharSequence(var7, '#', false, 2, null);
      boolean var13 = var2 == null || StringsKt.checkCharSequence(var2, '#', false, 2, null);
      if (var13 && var12) {
         return null;
      } else {
         String var14 = var5 ? "ORDER BY time DESC LIMIT 1" : "LIMIT 1";
         long var15 = this.liteBansCore.getConfig().getDurationsEffectiveIpbanDuration();
         String var17 = var15 > 0L
            ? "(ipban=" + this.notificationManager.getCharSequence() + " AND(ip=?" + var33 + ")AND((time+" + var15 + ")>?" + var11 + "))"
            : "(ipban=" + this.notificationManager.getCharSequence() + " AND(ip=?" + var33 + "))";
         String var18 = (
               var13
                  ? "SELECT * FROM " + var3.getTable() + " WHERE(uuid=?" + var8 + ')' + var32
                  : (
                     var12
                        ? "SELECT * FROM " + var3.getTable() + " WHERE(" + var17 + var8 + ')' + var32
                        : "SELECT * FROM " + var3.getTable() + " WHERE((uuid=? OR" + var17 + ')' + var8 + ')' + var32
                  )
            )
            + var14;
         LiteBansCore var19 = this.liteBansCore;
         if (var19.isDebugEnabled()) {
            var19.debug(var18);
         }

         QueryStatement var34 = this.prepare(var18);
         if (!var12) {
            var34.getQueryStatement(var7);
         }

         if (!var13) {
            var34.getQueryStatement(var2);
            if (!StringsKt.checkCharSequence(var33)) {
               var34.getQueryStatement(var2);
            }

            if (var15 > 0L) {
               QueryRunner.getQueryStatement(var34);
            }
         }

         var8.handleQueryStatement(var34);
         QueryRunner.getQueryStatement(var34);
         PunishmentEntry var20 = null;
         long var21 = System.nanoTime();

         try {
            ResultSet var23 = QueryRunner.getResultSet(var34);
            if (var23.next()) {
               PunishmentEntry var35 = this.readEntry(var23, var3);
               if (var35.matches(var7, var2, DatabaseManager.currentTimeMillisDefault(this.getDatabaseManager(), false, 1, null))) {
                  var20 = var35;
               }
            }
         } finally {
            long var24 = System.nanoTime() - var21;
            double var26 = (double)var24 / 1.0E9;
            if (var26 > 4.0) {
               this.liteBansPlatform
                  .getLogger()
                  .warning("Database is slow! Pooled connection took " + DecimalFormats.getString2(var26) + " seconds to retrieve result from database.");
            }
         }

         return var20;
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public PunishmentEntry findNameBan(@NotNull String var1, @Nullable String var2, @Nullable String var3, @NotNull PunishmentType var4) {
      List var5 = EntryReader.getList2(this, var1, 0, 2, null);
      if (var5.isEmpty()) {
         return null;
      } else {
         for (Object var7o : var5) {
            PlayerHistory var7 = (PlayerHistory) var7o;
            if (var7.getUuid() != null && !Intrinsics.checkObject(var7.getUuid(), var2)) {
               this.liteBansCore.debug(10, "UUID: " + var7.getUuid());
               PunishmentEntry var8 = EntryReader.getPunishmentEntry(this, var7.getUuid(), null, var4, var3, false, false, 32, null);
               LiteBansCore var10000 = this.liteBansCore;
               StringBuilder var10002 = new StringBuilder();
               String var11 = var4.toString();
               String var16;
               if (var11.length() > 0) {
                  StringBuilder var10003 = new StringBuilder();
                  char var12 = var11.charAt(0);
                  StringBuilder var13 = var10003;
                  StringBuilder var15 = var13.append(Character.isLowerCase(var12) ? CharsKt.getString2(var12, Locale.getDefault()) : String.valueOf(var12));
                  byte var14 = 1;
                  var16 = var15.append(var11.substring(var14)).toString();
               } else {
                  var16 = var11;
               }

               var10000.debug(10, var10002.append(var16).append(": ").append(var8).toString());
               if (var8 != null) {
                  this.liteBansPlatform.getLogger().info("Player is name-banned: " + var1 + " - " + var4 + " ID is " + var8.getId());
                  return var8;
               }
            }
         }

         return null;
      }
   }

   @Override
   public List getList(@NotNull String var1, @Nullable String var2, @Nullable String var3, @NotNull PunishmentType var4, boolean var5) {
      List var6 = EntryReader.getList2(this, var1, 0, 2, null);
      if (var6.isEmpty()) {
         return CollectionsKt.getList();
      } else {
         List var7 = new ArrayList();

         for (Object var9o : var6) {
            PlayerHistory var9 = (PlayerHistory) var9o;
            if (var9.getUuid() != null && !Intrinsics.checkObject(var9.getUuid(), var2)) {
               List var10 = this.findEntries(var9.getUuid(), null, var4, var3, true, 0);
               if (!var10.isEmpty()) {
                  var7.addAll(var10);
               }
            }
         }

         if (!var5) {
            return var7;
         } else {
            Iterable var18 = var7;
            Collection var11 = new ArrayList(CollectionsKt.getInt(var18, 10));

            for (Object var14 : var18) {
               PunishmentEntry var15 = (PunishmentEntry)var14;
               var11.add(
                  new PunishmentEntry(
                     var4,
                     var2,
                     var15.getIp(),
                     var15.getReason(),
                     var15.getExecutorUUID(),
                     var15.getExecutorName(),
                     var15.getServerScope(),
                     var15.getServerOrigin(),
                     var15.getDateStart(),
                     var15.getDateEnd(),
                     var15.getTemplate(),
                     var15.isSilent(),
                     var15.isIpban(),
                     var15.isActive(),
                     var15.getId(),
                     null
                  )
               );
            }

            return CollectionsKt.getList((List)var11);
         }
      }
   }

   @Override
   public PunishmentEntry findById(@NotNull String var1, @NotNull PunishmentType var2, @NotNull String var3, boolean var4) {
      if (!TargetParser.INSTANCE.checkString4(var1)) {
         return null;
      } else {
         SelectQuery var5 = SelectQueryFactory.getSelectQueryDefault(
            SelectQuery.selectQueryFactory, this, var1, var3, IdType.ID, var4, null, false, null, null, 240, null
         );
         ResultSet var6 = SelectQuery.getResultSetDefault(var5, var2, null, 2, null);
         PunishmentEntry var7 = this.readFirstEntry(var6, var2);
         return var4 && !this.appliesToTarget(var7) ? null : var7;
      }
   }

   @Override
   public PunishmentEntry findById(long var1, @NotNull PunishmentType var3, @NotNull String var4, boolean var5) {
      return this.findById(String.valueOf(var1), var3, var4, var5);
   }

   @Override
   public long countActive(@NotNull Table var1, @NotNull String var2, boolean var3) {
      try {
      String var4 = "SELECT COUNT(*) FROM " + var1;
      PunishmentStorage var6 = this;
      boolean var7 = true;
      SingleServerScopeFilter var5 = new SingleServerScopeFilter(var6.getPlatform(), var2, var7);
      String var12 = "";
      if (var3) {
         var12 = "WHERE " + "active=" + this.getNotificationManager().getCharSequence() + " AND(until<1 OR until>?)";
      }

      Function1 var14 = var12.length() == 0 ? sqlWhereBuilder.getFunction12() : sqlWhereBuilder.getFunction1(a -> getQueryStatement((QueryStatement) a));
      String var10 = "uuid IS NOT NULL";
      String var9 = "AND";
      String var13 = var12.length() == 0
         ? " WHERE " + var10
         : (!StringsKt.checkCharSequence3(var12, "WHERE", false, 2, null) ? " WHERE " + var12 + ' ' + var9 + ' ' + var10 : var12 + ' ' + var9 + ' ' + var10);
      if (var13.length() > 0) {
         var4 = var4 + ' ' + var13;
      }

      var4 = var4 + var5;
      Function1 var15 = sqlWhereBuilder.getFunction12(var14, a -> getQueryStatement(var5, (QueryStatement) a));
      ResultSet var16 = QueryRunner.getResultSet2(this.prepare((CharSequence)var4), var15);
      byte var17 = 1;
      Long var10000 = var16.next() ? var16.getLong(var17) : null;
      return var10000 != null ? var10000 : 0L;
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean existsByUuid(@NotNull UUID var1, @NotNull PunishmentType var2) {
      try {
      Table var3 = Table.ALLOW;
      CharSequence var4 = "1";
      String var17 = SqlString.getString2("SELECT " + var4 + " FROM " + var3);
      var4 = "uuid=?";
      CharSequence[] var6 = new CharSequence[]{SqlString.getSqlString(var17), var4};
      CharSequence[] var8 = var6;
      int var10 = 0;

      for (int var11 = var6.length; var10 < var11; var10++) {
         CharSequence var12 = var8[var10];
         if (var12.length() <= 0) {
            String var15 = "Failed requirement.";
            throw new IllegalArgumentException(var15.toString());
         }
      }

      return QueryRunner.getResultSet2(
            this.prepare(SqlString.getSqlString(SqlString.getString2(SqlString.getString(var17) + " WHERE " + var4))).getQueryStatement(var1)
         )
         .next();
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public ResultSet insertSyncRecord(@NotNull UUID var1, int var2) {
      Table var3 = Table.ALLOW;
      CharSequence var4 = "uuid,type";
      byte var7 = 44;
      CharSequence var9 = var4;
      int var11 = 0;

      for (int var12 = 0; var12 < var9.length(); var12++) {
         char var13 = var9.charAt(var12);
         if (var13 == var7) {
            var11++;
         }
      }

      String var6 = "?";
      if (var11 > 0) {
         StringBuilder var18 = new StringBuilder(var11 * 2);
         Iterable var8 = new IntRange(0, var11);
         Iterator var10 = var8.iterator();

         while (var10.hasNext()) {
            ((IntIterator)var10).getInt();
            var18.append("?,");
         }

         var6 = StringsKt.getString(var18.toString(), 1);
      }

      return QueryRunner.getResultSet(
         this.prepare(SqlString.getSqlString(SqlString.getString2("INSERT INTO " + var3 + '(' + var4 + ")VALUES(" + var6 + ')')))
            .getQueryStatement(var1)
            .getQueryStatement(var2)
      );
   }

   @Override
   public ResultSet deleteSyncRecords(@NotNull UUID var1) {
      Table var2 = Table.ALLOW;
      String var16 = SqlString.getString2("DELETE FROM " + var2);
      CharSequence var3 = "uuid=?";
      CharSequence[] var5 = new CharSequence[]{SqlString.getSqlString(var16), var3};
      CharSequence[] var7 = var5;
      int var9 = 0;

      for (int var10 = var5.length; var9 < var10; var9++) {
         CharSequence var11 = var7[var9];
         if (var11.length() <= 0) {
            String var14 = "Failed requirement.";
            throw new IllegalArgumentException(var14.toString());
         }
      }

      return QueryRunner.getResultSet(
         this.prepare(SqlString.getSqlString(SqlString.getString2(SqlString.getString(var16) + " WHERE " + var3))).getQueryStatement(var1)
      );
   }

   @Override
   public List findHistory(@NotNull String var1) {
      if (TargetParser.INSTANCE.checkString2(var1)) {
         return EntryReader.getList(this, var1, 0, 2, null);
      } else {
         PlayerHistory var2 = this.findLatestHistory(var1);
         return var2 != null ? EntryReader.getList(this, var2.getUuid(), 0, 2, null) : CollectionsKt.getList();
      }
   }

   @Override
   public PlayerHistory findLatestHistory(@Nullable String var1) {
      try {
      if (var1 == null) {
         return null;
      } else if (Intrinsics.checkObject(var1, "[CONSOLE]")) {
         return new PlayerHistory("[CONSOLE]", "CONSOLE", "#", null, 8, null);
      } else {
         Target var2 = TargetParser.INSTANCE.getTarget(var1);
         String var3 = var2.getString();
         String var4 = sqlWhereBuilder.getString(this, var3);
         AsyncQueue var5 = (AsyncQueue)this.liteBansPlatform.getModule(AsyncQueue.class);
         if (!var5.getQueue().isEmpty() && var2.getType() == 0 || var2.getType() == 1) {
            Iterable var8 = var5.getQueue();
            Iterator var9 = var8.iterator();

            Object var10000;
            while (true) {
               if (!var9.hasNext()) {
                  var10000 = null;
                  break;
               }

               Object var10 = var9.next();
               PlayerInfo var11 = (PlayerInfo)var10;
               if (Intrinsics.checkObject(var11.getString(), var1) || Intrinsics.checkObject(var11.getUuid(), var1)) {
                  var10000 = var10;
                  break;
               }
            }

            PlayerInfo var6 = (PlayerInfo)var10000;
            if (var6 != null) {
               return new PlayerHistory(var6.getString(), var6.getUuid(), var6.getIp(), null, 8, null);
            }
         }

         QueryStatement var7 = this.prepare(
            "SELECT " + this.notificationManager.getCharSequence3() + " FROM " + Table.HISTORY + " WHERE " + var4 + " ORDER BY date DESC LIMIT 1"
         );
         String var13 = var2.getTarget();
         var7.getQueryStatement(var13);
         ResultSet var12 = QueryRunner.getResultSet(var7);
         return var12.next() ? this.readHistory(var12, true, this.liteBansCore.getConfig().getSqlDriver()) : null;
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public List findHistoryByTarget(@Nullable String var1, int var2) {
      try {
      if (var1 == null) {
         return CollectionsKt.getList();
      } else if (Intrinsics.checkObject(var1, "[CONSOLE]")) {
         return CollectionsKt.getList(new PlayerHistory("[CONSOLE]", "CONSOLE", "#", null, 8, null));
      } else {
         Target var3 = TargetParser.INSTANCE.getTarget(var1);
         String var4 = var3.getString();
         String var5 = sqlWhereBuilder.getString(this, var4);
         QueryStatement var7 = this.prepare(
            "SELECT " + this.notificationManager.getCharSequence3() + " FROM " + Table.HISTORY + " WHERE " + var5 + " ORDER BY date DESC LIMIT " + var2
         );
         String var8 = var3.getTarget();
         var7.getQueryStatement(var8);
         ResultSet var6 = QueryRunner.getResultSet(var7);
         ArrayList var11 = new ArrayList();
         ArrayList var12 = new ArrayList();

         while (var6.next()) {
            PlayerHistory var9 = this.readHistory(var6, true, this.liteBansCore.getConfig().getSqlDriver());
            if (var9.getUuid() != null && !var11.contains(var9.getUuid())) {
               var11.add(var9.getUuid());
               var12.add(var9);
            }
         }

         return var12;
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public long lookupId(@NotNull PunishmentEntry var1) {
      try {
      Table var4 = var1.getType().getTable();
      CharSequence var5 = "{column.id}";
      String var18 = SqlString.getString2("SELECT " + var5 + " FROM " + var4);
      var5 = "uuid=?";
      CharSequence[] var8 = new CharSequence[]{SqlString.getSqlString(var18), var5};
      CharSequence[] var10 = var8;
      int var12 = 0;

      for (int var13 = var8.length; var12 < var13; var12++) {
         CharSequence var14 = var10[var12];
         if (var14.length() <= 0) {
            String var17 = "Failed requirement.";
            throw new IllegalArgumentException(var17.toString());
         }
      }

      ResultSet var19 = QueryRunner.getResultSet(
         this.prepare(
               SqlString.getSqlString(
                  SqlString.getString(
                     SqlString.getString8(SqlString.getString(SqlString.getString2(SqlString.getString(var18) + " WHERE " + var5), "time=?"), "id"), 1
                  )
               )
            )
            .getQueryStatement(var1.getUuid())
            .getQueryStatement(var1.getDateStart())
      );
      String var21 = "id";
      Long var10000 = var19.next() ? var19.getLong(var21) : null;
      return var10000 != null ? var10000 : 0L;
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean historyExists(@Nullable String var1, @Nullable String var2, @NotNull String var3) {
      try {
      boolean var10000;
      label56: {
         if (var1 != null) {
            int var7 = var1.length();
            byte var8 = this.getLiteBansCore().getConfig().getUsernameMaxLength();
            if (Intrinsics.getInt(var7, var8 & 255) > 0) {
               var10000 = false;
               break label56;
            }
         }

         var10000 = true;
      }

      if (!var10000) {
         String var30 = "Player name \"" + var1 + "\" length > " + UByte.getString(this.getLiteBansCore().getConfig().getUsernameMaxLength());
         throw new IllegalArgumentException(var30.toString());
      } else if (var2 != null && var2.length() > 36) {
         String var29 = "UUID \"" + var2 + "\" length > 36";
         throw new IllegalArgumentException(var29.toString());
      } else {
         Table var4 = Table.HISTORY;
         CharSequence var5 = "*";
         String var24 = SqlString.getString2("SELECT " + var5 + " FROM " + var4);
         var5 = "name{=?}";
         CharSequence[] var28 = new CharSequence[]{SqlString.getSqlString(var24), var5};
         CharSequence[] var9 = var28;
         int var11 = 0;

         for (int var12 = var28.length; var11 < var12; var11++) {
            CharSequence var13 = var9[var11];
            if (var13.length() <= 0) {
               String var16 = "Failed requirement.";
               throw new IllegalArgumentException(var16.toString());
            }
         }

         QueryStatement var25 = this.prepare(
            SqlString.getSqlString(
               SqlString.getString(
                  SqlString.getString(SqlString.getString(SqlString.getString2(SqlString.getString(var24) + " WHERE " + var5), "uuid=?"), "ip=?"), 1
               )
            )
         );
         String var27 = TargetParser.INSTANCE.getString(var2);
         QueryStatement var10 = var25;
         String[] var31 = new String[]{var1, var27, var3};
         String[] var32 = var31;
         int var18 = 0;

         for (int var19 = var31.length; var18 < var19; var18++) {
            String var20 = var32[var18];
            var10.getQueryStatement(var20);
         }

         return QueryRunner.getResultSet(var25).next();
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void copyTable(@Nullable Connection var1, @NotNull Table var2, boolean var3) {
      try {
      if (var1 != null) {
         boolean var4 = var2.check();
         boolean var5 = var2 == Table.HISTORY;
         if (!var3 || var2 != Table.SYNC && var2 != Table.SERVERS && var2 != Table.CONFIG) {
            String var6 = var3 ? "h2" : "mysql";
            String var7 = Table.getTableNameDefault(var2, null, var3, 1, null);
            AutoCloseable var8 = var1.prepareStatement("SELECT * FROM " + var7);
            Throwable var9 = null;

            try {
               PreparedStatement var10 = (PreparedStatement)var8;
               AutoCloseable var12 = var10.executeQuery();
               Throwable var13 = null;

               try {
                  ResultSet var14 = (ResultSet)var12;
                  if (!var4) {
                     if (var5) {
                        while (var14.next()) {
                           Intrinsics.handleObject(var14);
                           PlayerHistory var35 = this.readHistory(var14, false, var6);
                           String var36 = var35.getString2();
                           String var18 = var35.getUuid2();
                           String var19 = var35.getIp2();
                           EntryReader.handlePunishmentStorage(this, var36, var18, var19, false, false, 16, null);
                        }
                     }
                  } else {
                     while (var14.next()) {
                        Intrinsics.handleObject(var14);
                        PunishmentEntry var16 = this.readEntry(var14, var2.getPunishmentType());
                        PunishmentEntry var17 = EntryReader.getPunishmentEntry(this, var16, false, 2, null);
                        if (var17 == null || var17.isIpban() && !Intrinsics.checkObject(var17.getUuid(), var16.getUuid())) {
                           try {
                              this.insertEntry(var16);
                           } catch (Exception var30) {
                              if (!(var30 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var30); }
                              this.liteBansPlatform.getLogger().severe("Failed to add " + var16.getType() + ": " + var30.getMessage() + " - " + var16);
                              if (this.liteBansCore.isDebugLevel(1)) {
                                 var30.printStackTrace();
                              }
                           }
                        }
                     }
                  }
               } catch (Throwable var31) {
                  var13 = var31;
                  throw var31;
               } finally {
                  AutoCloseableKt.handleAutoCloseable(var12, var13);
               }
            } catch (Throwable var33) {
               var9 = var33;
               throw var33;
            } finally {
               AutoCloseableKt.handleAutoCloseable(var8, var9);
            }
         }
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public PunishmentEntry readEntry(@NotNull ResultSet var1, @NotNull PunishmentType var2) {
      try {
      PunishmentType var10000 = var2.getPunishmentType();
      String var10001 = TargetParser.getString2(var1.getString("uuid"));
      String var10002 = var1.getString("ip");
      CharSequence var10003 = var1.getString("reason");
      String var10004 = var1.getString("banned_by_uuid");
      if (var10004 == null) {
         var10004 = "#";
      }

      String var10005 = var1.getString("banned_by_name");
      ResultSet var4 = var1;
      String var5 = "server_scope";
      String var18 = var10005;
      String var17 = var10004;
      CharSequence var16 = var10003;
      String var15 = var10002;
      String var14 = var10001;
      PunishmentType var13 = var10000;

      String var7; String serverOrigin;
      try {
         var7 = var4.getString(var5);
      } catch (SQLException var45) {
         var7 = null;
      }

      var4 = var1;
      var5 = "server_origin";

      try {
         serverOrigin = var4.getString(var5);
      } catch (SQLException var44) {
         serverOrigin = null;
      }

      long var10008 = var1.getLong("time");
      long var10009 = var1.getLong("until");
      byte var10010 = UByte.getByte((byte)var1.getInt("template"));
      boolean var10011 = var1.getBoolean("silent");
      boolean var10012 = var1.getBoolean("ipban");
      boolean var10013 = var1.getBoolean("active");
      Object var21 = null;
      short var22 = 16384;
      long var23 = 0L;
      boolean var25 = var10013;
      boolean var26 = var10012;
      boolean var27 = var10011;
      byte var28 = var10010;
      long var29 = var10009;
      long var31 = var10008;
      PunishmentEntry var3 = new PunishmentEntry(
         var13, var14, var15, var16, var17, var18, var7, serverOrigin, var31, var29, var28, var27, var26, var25, var23, var22, (DefaultConstructorMarker)var21
      );
      if (var2 == PunishmentType.WARN) {
         var3.setWarned(var1.getBoolean("warned"));
      }

      if (!this.notificationManager.checkObject(var1)) {
         var3.setId(var1.getLong("id"));
      }

      if (var2 != PunishmentType.KICK) {
         Calendar var47 = Calendar.getInstance();
         var47.setTimeZone(TimeZone.getTimeZone("UTC"));
         Timestamp var49 = var1.getTimestamp("removed_by_date");
         if (var49 != null) {
            var47.setTime(var49);
            var3.setRemovedByDate(var47.getTimeInMillis() + (long)this.getDatabaseManager().getIntValue2());
         } else {
            var3.setRemovedByDate(0L);
         }

         ResultSet var6 = var1;
         var7 = "removed_by_uuid";
         TargetParser var12 = TargetParser.INSTANCE;

         String var9;
         try {
            var9 = var6.getString(var7);
         } catch (SQLException var43) {
            var9 = null;
         }

         var3.setRemovedByUuid(var12.getString(var9));
         var6 = var1;
         var7 = "removed_by_name";

         try {
            var9 = var6.getString(var7);
         } catch (SQLException var42) {
            var9 = null;
         }

         var3.setRemovedByName(var9);
         var6 = var1;
         var7 = "removed_by_reason";

         try {
            var9 = var6.getString(var7);
         } catch (SQLException var41) {
            var9 = null;
         }

         var3.setRemovedByReason(var9);
      }

      return var3;
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public PunishmentEntry readFirstEntry(@NotNull ResultSet var1, @NotNull PunishmentType var2) {
      try {
      return var1.next() ? this.readEntry(var1, var2) : null;
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public QueryStatement prepare(@NotNull CharSequence var1) {
      try {
      LiteBansCore var2 = this.liteBansCore;
      if (var2.isDebugEnabled()) {
         var2.debug(var1);
      }

      PreparedStatement var10000 = this.connection.prepareStatement(var1.toString());
      if (var10000 == null) {
         Unreachable.getVoid();
         throw new NoWhenBranchMatchedException();
      } else {
         PreparedStatement var4 = var10000;
         String var3 = var1 instanceof SqlString ? ((SqlString)var1).getString() : null;
         return Intrinsics.checkObject(this.liteBansCore.getConfig().getSqlDriver(), "pgsql")
            ? new StorageStatement(this, var4, var3, null)
            : new QueryStatement(this, var4, var3, null);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void pruneHistory(@Nullable String var1, long var2) {
      String var4 = TargetParser.INSTANCE.getString(var1);
      String var5 = "(active={false} OR(until>0 AND " + DatabaseManager.currentTimeMillisDefault(this.getDatabaseManager(), false, 1, null) + ">until))";
      Table[] var6 = Table.values();
      Table[] var8 = var6;
      Collection var9 = new ArrayList();
      int var11 = 0;

      for (int var12 = var6.length; var11 < var12; var11++) {
         Table var13 = var8[var11];
         if (var13.check()) {
            var9.add(var13);
         }
      }

      for (Object var28 : (List)var9) {
         Table var10 = (Table)var28;
         String var29 = SqlString.getString2("DELETE FROM " + var10);
         CharSequence var31 = "uuid=?";
         CharSequence[] var15 = new CharSequence[]{SqlString.getSqlString(var29), var31};
         CharSequence[] var17 = var15;
         int var19 = 0;

         for (int var20 = var15.length; var19 < var20; var19++) {
            CharSequence var21 = var17[var19];
            if (var21.length() <= 0) {
               String var24 = "Failed requirement.";
               throw new IllegalArgumentException(var24.toString());
            }
         }

         String var25 = SqlString.getString(SqlString.getString2(SqlString.getString(var29) + " WHERE " + var31), "time>?");
         if (var10 != Table.KICKS) {
            var25 = SqlString.getString(var25, var5);
         }

         QueryStatement var30 = this.prepare(SqlString.getSqlString(var25));
         var30.getQueryStatement(var4);
         QueryRunner.getResultSet(var30.getQueryStatement(var2));
      }
   }

   @Override
   public int countTemplateUses(@NotNull Template var1, @Nullable String var2, @Nullable String var3) {
      try {
      if (var2 == null) {
         return 0;
      } else {
         boolean var4 = var1.isIpTemplate() && var3 != null && !StringsKt.checkCharSequence3(var3, '#', false, 2, null);
         Table var5 = var1.getType().getTable();
         Function1 var6 = sqlWhereBuilder.getFunction12();
         CharSequence var9 = "COUNT(*)";
         String var7 = SqlString.getString2("SELECT " + var9 + " FROM " + var5);
         if (var4) {
            CharSequence var8 = "(uuid=? OR ipban={true} AND ip=?)";
            CharSequence[] var10 = new CharSequence[]{SqlString.getSqlString(var7), var8};
            CharSequence[] var12 = var10;
            int var14 = 0;

            for (int var15 = var10.length; var14 < var15; var14++) {
               CharSequence var16 = var12[var14];
               if (var16.length() <= 0) {
                  String var19 = "Failed requirement.";
                  throw new IllegalArgumentException(var19.toString());
               }
            }

            var7 = SqlString.getString(SqlString.getString2(SqlString.getString(var7) + " WHERE " + var8), "template=?");
            var6 = sqlWhereBuilder.getFunction12(var6, a -> getQueryStatement(var2, var3, var1, (QueryStatement) a));
         } else {
            CharSequence var22 = "uuid=?";
            CharSequence[] var25 = new CharSequence[]{SqlString.getSqlString(var7), var22};
            CharSequence[] var27 = var25;
            int var28 = 0;

            for (int var29 = var25.length; var28 < var29; var28++) {
               CharSequence var30 = var27[var28];
               if (var30.length() <= 0) {
                  String var31 = "Failed requirement.";
                  throw new IllegalArgumentException(var31.toString());
               }
            }

            var7 = SqlString.getString(SqlString.getString2(SqlString.getString(var7) + " WHERE " + var22), "template=?");
            var6 = sqlWhereBuilder.getFunction12(var6, a -> getQueryStatement(var2, var1, (QueryStatement) a));
         }

         if (var1.getType() == PunishmentType.WARN) {
            var7 = SqlString.getString(var7, "active=" + this.getNotificationManager().getCharSequence() + " AND(until<1 OR until>?)");
            var6 = sqlWhereBuilder.getFunction12(var6, a -> getQueryStatement2((QueryStatement) a));
         }

         if (var1.getLadderExpiry() > 0L) {
            long var23 = DatabaseManager.currentTimeMillisDefault(this.getDatabaseManager(), false, 1, null) - var1.getLadderExpiry();
            var7 = SqlString.getString(var7, "time>?");
            var6 = sqlWhereBuilder.getFunction12(var6, a -> getQueryStatement(var23, (QueryStatement) a));
         }

         ResultSet var24 = QueryRunner.getResultSet(this.prepare(SqlString.getSqlString(var7)), var6);
         byte var26 = 1;
         Integer var10000 = var24.next() ? var24.getInt(var26) : null;
         return var10000 != null ? var10000 : 0;
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Pair computeTemplateStep(@Nullable String var1, @Nullable String var2, @NotNull TemplateGroup var3, @NotNull Template var4) {
      try {
      if (var1 == null) {
         return TuplesKt.getPair(0.0, 0.0);
      } else {
         boolean var5 = var4.isIpTemplate() && var2 != null && !StringsKt.checkCharSequence3(var2, '#', false, 2, null);
         Table var6 = var3.getType().getTable();
         CharSequence var9 = "template";
         String var7 = SqlString.getString2("SELECT " + var9 + " FROM " + var6);
         Function1 var8 = sqlWhereBuilder.getFunction12();
         if (var5) {
            var9 = "(uuid=? OR ipban={true} AND ip=?)";
            CharSequence[] var11 = new CharSequence[]{SqlString.getSqlString(var7), var9};
            CharSequence[] var13 = var11;
            int var15 = 0;

            for (int var16 = var11.length; var15 < var16; var15++) {
               CharSequence var17 = var13[var15];
               if (var17.length() <= 0) {
                  String var20 = "Failed requirement.";
                  throw new IllegalArgumentException(var20.toString());
               }
            }

            var7 = SqlString.getString2(SqlString.getString(var7) + " WHERE " + var9);
            var8 = sqlWhereBuilder.getFunction12(var8, a -> getQueryStatement3(var1, var2, (QueryStatement) a));
         } else {
            var9 = "uuid=?";
            CharSequence[] var35 = new CharSequence[]{SqlString.getSqlString(var7), var9};
            CharSequence[] var38 = var35;
            int var42 = 0;

            for (int var46 = var35.length; var42 < var46; var42++) {
               CharSequence var49 = var38[var42];
               if (var49.length() <= 0) {
                  String var50 = "Failed requirement.";
                  throw new IllegalArgumentException(var50.toString());
               }
            }

            var7 = SqlString.getString2(SqlString.getString(var7) + " WHERE " + var9);
            var8 = sqlWhereBuilder.getFunction12(var8, a -> getQueryStatement2(var1, (QueryStatement) a));
         }

         if (var3.getType() == PunishmentType.WARN) {
            var7 = SqlString.getString(var7, "active=" + this.getNotificationManager().getCharSequence() + " AND(until<1 OR until>?)");
            var8 = sqlWhereBuilder.getFunction12(var8, a -> getQueryStatement3((QueryStatement) a));
         }

         if (var3.getLadderExpiry() > 0L) {
            long var31 = DatabaseManager.currentTimeMillisDefault(this.getDatabaseManager(), false, 1, null) - var3.getLadderExpiry();
            var7 = SqlString.getString(var7, "time>?");
            var8 = sqlWhereBuilder.getFunction12(var8, a -> getQueryStatement2(var31, (QueryStatement) a));
         }

         String var32 = "";
         Map var10 = var3.getWeights();

         for (Object var39o : var10.entrySet()) {
            Entry var39 = (Entry) var39o;
            Template var47 = (Template)var39.getKey();
            var32 = var32 + "template=? OR ";
            var8 = sqlWhereBuilder.getFunction12(var8, a -> getQueryStatement(var47, (QueryStatement) a));
         }

         String var33 = var32.substring(0, var32.length() - 4);
         var7 = SqlString.getString(var7, '(' + var33 + ')');
         ResultSet var34 = QueryRunner.getResultSet(this.prepare(SqlString.getSqlString(var7)), var8);
         double var24 = 0.0;

         while (true) {
            byte var43 = 1;
            Integer var10000 = var34.next() ? var34.getInt(var43) : null;
            if (var10000 == null) {
               break;
            }

            byte var36 = UByte.getByte((byte)var10000.intValue());
            Template var52 = (Template)var3.getTemplates().get(UByte.getUByte(var36));
            if (var52 == null) {
               break;
            } else {
               Template var40 = var52;
               var10000 = (Integer)var3.getWeights().get(var40);
               Intrinsics.handleObject(var10000);
               float var14 = var10000.floatValue();
               var24 += (double)var14;
               LiteBansCore var44 = this.liteBansCore;
               byte var48 = 10;
               if (var44.isDebugLevel(var48)) {
                  var44.debug(
                     '['
                        + var3.getName()
                        + "] Add "
                        + DecimalFormats.getString2((double)var14)
                        + " from "
                        + var40.getName()
                        + " = "
                        + DecimalFormats.getString2(var24)
                  );
               }

               LiteBansCore var45 = this.liteBansCore;
               if (var45.isDebugEnabled()) {
                  LiteBansCore var10002 = this.liteBansCore;
                  String var19 = "a:" + var14;
                  String[] var10003;
                  if (Intrinsics.checkObject(var19, "sort")) {
                     String[] var21 = new String[]{"00"};
                     var10003 = var21;
                  } else {
                     String[] var51 = new String[]{"01:" + var19};
                     var10003 = var51;
                  }

                  String[] var22 = var10003;
                  TextArgs var54 = new TextArgs(var10002, Arrays.copyOf(var22, var22.length));
                  var45.debug(var54);
               }
            }
         }

         Object var55 = var3.getWeights().get(var4);
         Intrinsics.handleObject(var55);
         float var37 = ((Number)var55).floatValue();
         double var41 = var24 + (double)var37;
         return TuplesKt.getPair(var24, var41);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public ResultSet resetTable(@NotNull Table var1, @NotNull String var2, int var3) {
      PunishmentStorage var5 = this;
      boolean var6 = false;
      SingleServerScopeFilter var4 = new SingleServerScopeFilter(var5.getPlatform(), var2, var6);
      if (var3 == 1) {
         return !var1.check()
            ? EmptyResultSetHolder.getEmptyResultSet()
            : QueryRunner.getResultSet(this.prepare(SqlString.getSqlString(SqlString.getString4(SqlString.getString2("UPDATE " + var1), "active={false}"))));
      } else if (var3 == 2) {
         if (!var1.check()) {
            return EmptyResultSetHolder.getEmptyResultSet();
         } else {
            String var20 = SqlString.getString2("DELETE FROM " + var1);
            CharSequence[] var22 = new CharSequence[]{SqlString.getSqlString(var20), var4};
            CharSequence[] var23 = var22;
            int var24 = 0;

            for (int var25 = var22.length; var24 < var25; var24++) {
               CharSequence var26 = var23[var24];
               if (var26.length() <= 0) {
                  String var27 = "Failed requirement.";
                  throw new IllegalArgumentException(var27.toString());
               }
            }

            QueryStatement var21 = this.prepare(
               SqlString.getSqlString(SqlString.getString(SqlString.getString2(SqlString.getString(var20) + " WHERE " + var4), "active={false}"))
            );
            var4.handleQueryStatement(var21);
            return QueryRunner.getResultSet(var21);
         }
      } else {
         ResultSet var10000;
         if (ServerScope.serverScopeParser.checkString(var2)) {
            var10000 = QueryRunner.getResultSet(this.prepare(SqlString.getSqlString(SqlString.getString2("DROP TABLE " + var1))));
         } else {
            String var18 = SqlString.getString2("DELETE FROM " + var1);
            CharSequence[] var7 = new CharSequence[]{SqlString.getSqlString(var18), var4};
            CharSequence[] var9 = var7;
            int var11 = 0;

            for (int var12 = var7.length; var11 < var12; var11++) {
               CharSequence var13 = var9[var11];
               if (var13.length() <= 0) {
                  String var16 = "Failed requirement.";
                  throw new IllegalArgumentException(var16.toString());
               }
            }

            QueryStatement var19 = this.prepare(SqlString.getSqlString(SqlString.getString2(SqlString.getString(var18) + " WHERE " + var4)));
            var4.handleQueryStatement(var19);
            var10000 = QueryRunner.getResultSet(var19);
         }

         return var10000;
      }
   }

   @Override
   public ResultSet resetTemplates(@NotNull Table var1) {
      return QueryRunner.getResultSet(this.prepare(SqlString.getSqlString(SqlString.getString4(SqlString.getString2("UPDATE " + var1), "template=255"))));
   }

   @Override
   public void updateDurationAndReason(@NotNull CharSequence var1, long var2, @NotNull PunishmentEntry var4) {
      PunishmentStorage var6 = this;
      String var7 = var4.getServerScope();
      boolean var8 = true;
      SingleServerScopeFilter var5 = new SingleServerScopeFilter(var6.getPlatform(), var7, var8);
      Table var21 = var4.getType().getTable();
      var7 = SqlString.getString4(SqlString.getString2("UPDATE " + var21), "until=?,reason=?");
      CharSequence var26 = "id=?";
      CharSequence[] var10 = new CharSequence[]{SqlString.getSqlString(var7), var26};
      CharSequence[] var12 = var10;
      int var14 = 0;

      for (int var15 = var10.length; var14 < var15; var14++) {
         CharSequence var16 = var12[var14];
         if (var16.length() <= 0) {
            String var19 = "Failed requirement.";
            throw new IllegalArgumentException(var19.toString());
         }
      }

      var7 = SqlString.getString(
         SqlString.getString2(SqlString.getString(var7) + " WHERE " + var26),
         "active=" + this.getNotificationManager().getCharSequence() + " AND(until<1 OR until>?)"
      );
      String var20 = SqlString.getString2(SqlString.getString(var7) + var5);
      QueryStatement var24 = this.prepare(SqlString.getSqlString(var20)).getQueryStatement(var2);
      var24.getQueryStatement(String.valueOf(var1));
      QueryStatement var25 = QueryRunner.getQueryStatement(var24.getQueryStatement(var4.getId()));
      var5.handleQueryStatement(var25);
      QueryRunner.getResultSet(var25);
   }

   @Override
   public void deactivate(@Nullable String var1, @Nullable String var2, @NotNull CharSequence var3, @NotNull PunishmentEntry var4, boolean var5) {
      String var6 = var2 != null ? ArgUtils.getString(var2, 128) : null;
      PunishmentStorage var8 = this;
      String var9 = var4.getServerScope();
      boolean var10 = true;
      SingleServerScopeFilter var7 = new SingleServerScopeFilter(var8.getPlatform(), var9, var10);
      boolean var25 = var4.isIpban() && var4.getIp() != null && !StringsKt.checkCharSequence3(var4.getIp(), '#', false, 2, null);
      Pair var26 = var5
         ? TuplesKt.getPair("id", String.valueOf(var4.getId()))
         : (var25 ? TuplesKt.getPair("ip", var4.getIp()) : TuplesKt.getPair("uuid", var4.getUuid()));
      Table var11 = var4.getType().getTable();
      String var28 = SqlString.getString4(
         SqlString.getString2("UPDATE " + var11), "active={false},removed_by_date=NOW(),removed_by_uuid=?,removed_by_name=?,removed_by_reason=?"
      );
      CharSequence var12 = (String)var26.getObject() + "=?";
      CharSequence[] var14 = new CharSequence[]{SqlString.getSqlString(var28), var12};
      CharSequence[] var16 = var14;
      int var18 = 0;

      for (int var19 = var14.length; var18 < var19; var18++) {
         CharSequence var20 = var16[var18];
         if (var20.length() <= 0) {
            String var23 = "Failed requirement.";
            throw new IllegalArgumentException(var23.toString());
         }
      }

      String var29 = SqlString.getString(
         SqlString.getString2(SqlString.getString(var28) + " WHERE " + var12),
         "active=" + this.getNotificationManager().getCharSequence() + " AND(until<1 OR until>?)"
      );
      String var27 = SqlString.getString2(SqlString.getString(var29) + var7);
      LiteBansCore var30 = this.liteBansCore;
      if (var30.isDebugEnabled()) {
         var30.debug(SqlString.getString(var27) + ' ' + var4.getServerScope() + ' ' + var26);
      }

      QueryStatement var31 = this.prepare(SqlString.getSqlString(var27));
      String[] var33 = new String[]{var1, var6, var3.toString(), (String)var26.getObject2()};
      String[] var17 = var33;
      int var34 = 0;

      for (int var35 = var33.length; var34 < var35; var34++) {
         String var21 = var17[var34];
         var31.getQueryStatement(var21);
      }

      QueryStatement var32 = QueryRunner.getQueryStatement(var31);
      var7.handleQueryStatement(var32);
      QueryRunner.getResultSet(var32);
      var4.setRemovedBy(var6, var1, var3);
   }

   @Override
   public void removeEntry(@NotNull PunishmentEntry var1, @Nullable String var2, @Nullable String var3, @NotNull CharSequence var4, boolean var5) {
      if (var5) {
         Table var7 = var1.getType().getTable();
         String var22 = SqlString.getString2("DELETE FROM " + var7);
         CharSequence var8 = "id=?";
         CharSequence[] var10 = new CharSequence[]{SqlString.getSqlString(var22), var8};
         CharSequence[] var12 = var10;
         int var14 = 0;

         for (int var15 = var10.length; var14 < var15; var14++) {
            CharSequence var16 = var12[var14];
            if (var16.length() <= 0) {
               String var19 = "Failed requirement.";
               throw new IllegalArgumentException(var19.toString());
            }
         }

         String var6 = SqlString.getString2(SqlString.getString(var22) + " WHERE " + var8);
         QueryRunner.getResultSet(this.prepare(SqlString.getSqlString(var6)).getQueryStatement(var1.getId()));
      } else {
         Table var23 = var1.getType().getTable();
         String var24 = SqlString.getString4(
            SqlString.getString2("UPDATE " + var23), "active={false},removed_by_date=NOW(),removed_by_uuid=?,removed_by_name=?,removed_by_reason=?"
         );
         CharSequence var27 = "id=?";
         CharSequence[] var30 = new CharSequence[]{SqlString.getSqlString(var24), var27};
         CharSequence[] var32 = var30;
         int var33 = 0;

         for (int var34 = var30.length; var33 < var34; var33++) {
            CharSequence var35 = var32[var33];
            if (var35.length() <= 0) {
               String var36 = "Failed requirement.";
               throw new IllegalArgumentException(var36.toString());
            }
         }

         String var21 = SqlString.getString2(SqlString.getString(var24) + " WHERE " + var27);
         LiteBansCore var25 = this.liteBansCore;
         if (var25.isDebugEnabled()) {
            SqlString var31 = SqlString.getSqlString(var21);
            CharSequence var11 = " id=" + var1.getId();
            var25.debug("" + var31 + var11);
         }

         QueryStatement var26 = this.prepare(SqlString.getSqlString(var21));
         var26.getQueryStatement(var2);
         String var28 = var3 != null ? ArgUtils.getString(var3, 128) : null;
         var26.getQueryStatement(var28);
         var27 = StringsKt.checkCharSequence(var4) ? null : var4;
         var26.getQueryStatement(String.valueOf(var27));
         QueryRunner.getResultSet(var26.getQueryStatement(var1.getId()));
         var1.setRemovedBy(var3, var2, var4);
      }
   }

   @Override
   public long rollbackStaff(@NotNull String var1, long var2, @NotNull String var4) {
      PunishmentStorage var6 = this;
      ServerScopeFilter var5 = new ServerScopeFilter(var6.getPlatform(), var4);
      Table[] var8 = Table.values();
      Table[] var10 = var8;
      Collection var11 = new ArrayList();
      int var13 = 0;

      for (int var14 = var8.length; var13 < var14; var13++) {
         Table var15 = var10[var13];
         if (var15.check()) {
            var11.add(var15);
         }
      }

      Iterable var32 = (List)var11;
      int var9 = 0;

      for (Object var40 : var32) {
         Table var12 = (Table)var40;
         String var46 = SqlString.getString2("DELETE FROM " + var12);
         CharSequence var50 = "banned_by_uuid=?";
         CharSequence[] var18 = new CharSequence[]{SqlString.getSqlString(var46), var50};
         CharSequence[] var20 = var18;
         int var22 = 0;

         for (int var23 = var18.length; var22 < var23; var22++) {
            CharSequence var24 = var20[var22];
            if (var24.length() <= 0) {
               String var27 = "Failed requirement.";
               throw new IllegalArgumentException(var27.toString());
            }
         }

         QueryStatement var47 = this.prepare(
            SqlString.getSqlString(SqlString.getString(SqlString.getString2(SqlString.getString(var46) + " WHERE " + var50), "time>?" + var5))
         );
         var47.getQueryStatement(var1);
         QueryStatement var48 = var47.getQueryStatement(var2);
         var5.handleQueryStatement(var48);
         int var30 = QueryRunner.getInt(var48);
         var9 += var30;
      }

      long var31 = (long)var9;
      Function1 var33 = sqlWhereBuilder.getFunction1(a -> getQueryStatement3(var1, (QueryStatement) a));
      if (var2 > 0L) {
         Timestamp var35 = new Timestamp(var2);
         var33 = sqlWhereBuilder.getFunction12(var33, a -> getQueryStatement(var35, (QueryStatement) a));
      }

      Function1 var34 = sqlWhereBuilder.getFunction12(var33, a -> getQueryStatement(var5, (QueryStatement) a));
      Table[] var36 = Table.values();
      Table[] var41 = var36;
      Collection var43 = new ArrayList();
      int var49 = 0;

      for (int var51 = var36.length; var49 < var51; var49++) {
         Table var16 = var41[var49];
         if (var16.check() && var16 != Table.KICKS) {
            var43.add(var16);
         }
      }

      for (Object var44 : (List)var43) {
         Table var45 = (Table)var44;
         String var52 = SqlString.getString4(
            SqlString.getString2("UPDATE " + var45), "active={true},removed_by_name=NULL,removed_by_uuid=NULL,removed_by_reason=NULL"
         );
         SqlString var53 = SqlString.getSqlString(SqlFalse.getString("active"));
         CharSequence[] var54 = new CharSequence[]{SqlString.getSqlString(var52), var53};
         CharSequence[] var55 = var54;
         int var56 = 0;

         for (int var57 = var54.length; var56 < var57; var56++) {
            CharSequence var58 = var55[var56];
            if (var58.length() <= 0) {
               String var59 = "Failed requirement.";
               throw new IllegalArgumentException(var59.toString());
            }
         }

         String var28 = SqlString.getString(SqlString.getString2(SqlString.getString(var52) + " WHERE " + var53), "removed_by_uuid=?" + var5);
         if (var2 > 0L) {
            var28 = SqlString.getString(var28, "removed_by_date>?");
         }

         QueryRunner.getResultSet(this.prepare(SqlString.getSqlString(var28)), var34);
      }

      if (this.liteBansCore.getConfig().isMutesEnabled()) {
         MuteManager var39 = this.muteManager;
         var39.handlePunishmentStorage(this);
      }

      return var31;
   }

   @Override
   public ResultSet markWarned(@NotNull String var1) {
      Table var2 = Table.WARNINGS;
      String var16 = SqlTrue.getString(SqlString.getString2("UPDATE " + var2), "warned");
      CharSequence var3 = "uuid=?";
      CharSequence[] var5 = new CharSequence[]{SqlString.getSqlString(var16), var3};
      CharSequence[] var7 = var5;
      int var9 = 0;

      for (int var10 = var5.length; var9 < var10; var9++) {
         CharSequence var11 = var7[var9];
         if (var11.length() <= 0) {
            String var14 = "Failed requirement.";
            throw new IllegalArgumentException(var14.toString());
         }
      }

      QueryStatement var17 = this.prepare(
         SqlString.getSqlString(
            SqlString.getString(SqlString.getString2(SqlString.getString(var16) + " WHERE " + var3), SqlString.getSqlString(SqlFalse.getString("warned")))
         )
      );
      var17.getQueryStatement(var1);
      return QueryRunner.getResultSet(var17);
   }

   @Override
   public void expireEntries(boolean var1) {
      try {
      try {
         if (!this.liteBansCore.getConfig().isDisableExpiryUpdate()) {
            long var2 = DatabaseManager.currentTimeMillisDefault(this.getDatabaseManager(), false, 1, null);
            if (var2 - this.getDatabaseManager().getLongValue2() > 72000000L || var1) {
               SyncManager var4 = (SyncManager)this.liteBansPlatform.getModule(SyncManager.class);
               DatabaseManager var5 = this.getDatabaseManager();
               byte var6 = 0;
               var5.setLongValue2(var2 + (long)var6);
               SyncManager.publishDefault(var4, Flag.EXPIRED_NOTIFY, this, null, 0, 12, null);
               AutoCloseable var41 = this.connection.createStatement();
               Throwable var42 = null;

               try {
                  Statement var7 = (Statement)var41;
                  Table[] var9 = Table.values();
                  Table[] var11 = var9;
                  Collection var12 = new ArrayList();
                  int var14 = 0;

                  for (int var15 = var9.length; var14 < var15; var14++) {
                     Table var16 = var11[var14];
                     if (var16.check() && var16 != Table.KICKS) {
                        var12.add(var16);
                     }
                  }

                  for (Object var48 : (List)var12) {
                     Table var13 = (Table)var48;
                     Intrinsics.handleObject(var7);
                     String var57 = SqlString.getString4(
                        SqlString.getString2("UPDATE " + var13), "active={false},removed_by_name='#expired',removed_by_date=NOW()"
                     );
                     CharSequence var17 = "active={true}";
                     CharSequence[] var19 = new CharSequence[]{SqlString.getSqlString(var57), var17};
                     CharSequence[] var21 = var19;
                     int var23 = 0;

                     for (int var24 = var19.length; var23 < var24; var23++) {
                        CharSequence var25 = var21[var23];
                        if (var25.length() <= 0) {
                           String var28 = "Failed requirement.";
                           throw new IllegalArgumentException(var28.toString());
                        }
                     }

                     SqlString var58 = SqlString.getSqlString(
                        SqlString.getString(
                           SqlString.getString(SqlString.getString2(SqlString.getString(var57) + " WHERE " + var17), "until>0"), "until<" + var2
                        )
                     );
                     var7.addBatch(var58.toString());
                  }

                  long var29 = this.liteBansCore.getConfig().getDurationsEffectiveIpbanDuration();
                  if (var29 > 0L) {
                     var11 = Table.values();
                     Table[] var49 = var11;
                     Collection var52 = new ArrayList();
                     int var59 = 0;

                     for (int var61 = var11.length; var59 < var61; var59++) {
                        Table var18 = var49[var59];
                        if (var18.check() && var18 != Table.KICKS) {
                           var52.add(var18);
                        }
                     }

                     for (Object var53 : (List)var52) {
                        Table var55 = (Table)var53;
                        Intrinsics.handleObject(var7);
                        String var63 = SqlString.getString4(SqlString.getString2("UPDATE " + var55), "ipban={false}");
                        CharSequence var65 = "ipban={true}";
                        CharSequence[] var66 = new CharSequence[]{SqlString.getSqlString(var63), var65};
                        CharSequence[] var67 = var66;
                        int var68 = 0;

                        for (int var26 = var66.length; var68 < var26; var68++) {
                           CharSequence var27 = var67[var68];
                           if (var27.length() <= 0) {
                              String var32 = "Failed requirement.";
                              throw new IllegalArgumentException(var32.toString());
                           }
                        }

                        SqlString var64 = SqlString.getSqlString(
                           SqlString.getString(
                              SqlString.getString(SqlString.getString2(SqlString.getString(var63) + " WHERE " + var65), "ipban_wildcard={false}"),
                              "(time+" + var29 + ")<" + var2
                           )
                        );
                        var7.addBatch(var64.toString());
                     }
                  }

                  DatabaseManager var69 = this.getDatabaseManager();
                  Intrinsics.handleObject(var7);
                  int[] var47 = var69.executeBatch(var7);
                  if (this.liteBansCore.isDebugLevel(100)) {
                     int[] var51 = var47;
                     var14 = 0;
                     int var56 = 0;

                     for (int var60 = var47.length; var56 < var60; var56++) {
                        int var62 = var51[var56];
                        int var34 = Math.max(var62, 0);
                        var14 += var34;
                     }

                     if (var14 > 0) {
                        this.liteBansCore.debug("Expired " + var14 + " entries(until<" + var2 + ')');
                     }
                  }
               } catch (Throwable var38) {
                  var42 = var38;
                  throw var38;
               } finally {
                  AutoCloseableKt.handleAutoCloseable(var41, var42);
               }
            }
         }
      } catch (SQLException var40) {
         String var10000 = var40.getMessage();
         if (var10000 != null ? !StringsKt.checkString(var10000, "Deadlock ", false, 2, null) : true) {
            throw var40;
         }
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int updateHistoryIp(@NotNull PlayerHistory var1) {
      Table var2 = Table.HISTORY;
      String var16 = SqlString.getString4(SqlString.getString2("UPDATE " + var2), "ip=?");
      CharSequence var3 = "uuid=?";
      CharSequence[] var5 = new CharSequence[]{SqlString.getSqlString(var16), var3};
      CharSequence[] var7 = var5;
      int var9 = 0;

      for (int var10 = var5.length; var9 < var10; var9++) {
         CharSequence var11 = var7[var9];
         if (var11.length() <= 0) {
            String var14 = "Failed requirement.";
            throw new IllegalArgumentException(var14.toString());
         }
      }

      QueryStatement var17 = this.prepare(
         SqlString.getSqlString(SqlString.getString2(SqlString.getString2(SqlString.getString(var16) + " WHERE " + var3), "ip=?"))
      );
      String[] var18 = new String[]{"#", var1.getUuid(), var1.getIp()};
      String[] var8 = var18;
      int var19 = 0;

      for (int var20 = var18.length; var19 < var20; var19++) {
         String var12 = var8[var19];
         var17.getQueryStatement(var12);
      }

      return QueryRunner.getInt(var17);
   }

   private final void renameLegacyTables(Statement var1) {
      try {
      Table[] var2 = Table.values();
      int var4 = 0;

      for (int var5 = var2.length; var4 < var5; var4++) {
         Table var6 = var2[var4];
         String var9 = Table.getTableNameDefault(var6, Locale.getDefault(), false, 2, null);
         String var10 = Table.getTableNameDefault(var6, null, false, 3, null);
         if (!Intrinsics.checkObject(var9, var10) && !this.tableExists(var10) && this.tableExists(var9)) {
            this.liteBansPlatform.getLogger().info("Renaming table \"" + var9 + "\" to \"" + var10 + '"');
            var1.execute("RENAME TABLE " + var9 + " TO " + var10);
         }
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   public final TableCreator getTableCreator() {
      Config var1 = this.liteBansCore.getConfig();
      return new TableCreator(SqlColumnFormat.sqlColumnFormats.getSqlColumnFormat(var1.getSqlDriver(), var1.getSqlEngine()));
   }

   public final CharSequence getCreateTableSql(@NotNull Table var1, @NotNull TableCreator var2) {
      String var3 = "";
      String var4 = "";
      if (var1 == Table.WARNINGS) {
         String var7 = "warned";
         String var8 = " NOT NULL";
         var4 = var7 + " BIT" + var8;
      }

      if (var1 != Table.KICKS) {
         StringBuilder var10000 = new StringBuilder();
         String var15 = "removed_by_uuid";
         short var19 = 36;
         String var9 = "";
         String var10 = var2.getSqlColumnFormat().getCharset();
         var10000 = var10000.append(
               DatabaseManager.databaseDriver.checkString(var2.getSqlColumnFormat().getString())
                  ? var15 + " VARCHAR(" + var19 + ')' + var10 + var9
                  : var15 + " VARCHAR(" + var19 + ')' + var9
            )
            .append(',');
         var15 = "removed_by_name";
         var19 = 128;
         var9 = "";
         var10 = var2.getSqlColumnFormat().getCharset();
         var10000 = var10000.append(
               DatabaseManager.databaseDriver.checkString(var2.getSqlColumnFormat().getString())
                  ? var15 + " VARCHAR(" + var19 + ')' + var10 + var9
                  : var15 + " VARCHAR(" + var19 + ')' + var9
            )
            .append(',');
         var15 = "removed_by_reason";
         var19 = 2048;
         var9 = "";
         var10 = var2.getSqlColumnFormat().getCharset();
         var10000 = var10000.append(
               DatabaseManager.databaseDriver.checkString(var2.getSqlColumnFormat().getString())
                  ? var15 + " VARCHAR(" + var19 + ')' + var10 + var9
                  : var15 + " VARCHAR(" + var19 + ')' + var9
            )
            .append(',');
         String var22 = "removed_by_date";
         var9 = "";
         var3 = var10000.append(var22 + " TIMESTAMP" + var9).toString();
      }

      String[] var18 = new String[18];
      String var36 = "id";
      var18[0] = Intrinsics.checkObject(var2.getSqlColumnFormat().getString(), "sqlite") ? var36 + " INTEGER PRIMARY KEY AUTOINCREMENT" : var36 + " SERIAL";
      String var23 = "uuid";
      byte var37 = 36;
      String var11 = " CHARACTER SET ascii COLLATE ascii_general_ci";
      String var54 = "";
      var18[1] = DatabaseManager.databaseDriver.checkString(var2.getSqlColumnFormat().getString())
         ? var23 + " VARCHAR(" + var37 + ')' + var11 + var54
         : var23 + " VARCHAR(" + var37 + ')' + var54;
      var23 = "ip";
      byte var38 = 45;
      var11 = " CHARACTER SET ascii COLLATE ascii_general_ci";
      var54 = "";
      var18[2] = DatabaseManager.databaseDriver.checkString(var2.getSqlColumnFormat().getString())
         ? var23 + " VARCHAR(" + var38 + ')' + var11 + var54
         : var23 + " VARCHAR(" + var38 + ')' + var54;
      var23 = "reason";
      short var39 = 2048;
      var54 = "";
      var11 = var2.getSqlColumnFormat().getCharset();
      var18[3] = DatabaseManager.databaseDriver.checkString(var2.getSqlColumnFormat().getString())
         ? var23 + " VARCHAR(" + var39 + ')' + var11 + var54
         : var23 + " VARCHAR(" + var39 + ')' + var54;
      var23 = "banned_by_uuid";
      byte var40 = 36;
      var54 = "";
      var11 = var2.getSqlColumnFormat().getCharset();
      String var14 = var54 + " NOT NULL";
      var18[4] = DatabaseManager.databaseDriver.checkString(var2.getSqlColumnFormat().getString())
         ? var23 + " VARCHAR(" + var40 + ')' + var11 + var14
         : var23 + " VARCHAR(" + var40 + ')' + var14;
      var23 = "banned_by_name";
      short var41 = 128;
      var54 = "";
      var11 = var2.getSqlColumnFormat().getCharset();
      var18[5] = DatabaseManager.databaseDriver.checkString(var2.getSqlColumnFormat().getString())
         ? var23 + " VARCHAR(" + var41 + ')' + var11 + var54
         : var23 + " VARCHAR(" + var41 + ')' + var54;
      var18[6] = var3;
      var36 = "time";
      var18[7] = var36 + " BIGINT NOT NULL";
      var36 = "until";
      var18[8] = var36 + " BIGINT NOT NULL";
      var36 = "template";
      var54 = " DEFAULT 255";
      String var12 = var2.getSqlColumnFormat().getString();
      var18[9] = !Intrinsics.checkObject(var12, "pgsql") && !Intrinsics.checkObject(var12, "h2")
         ? var36 + " TINYINT" + var2.getSqlColumnFormat().getUnsigned() + " NOT NULL" + var54
         : var36 + " SMALLINT NOT NULL" + var54;
      var23 = "server_scope";
      byte var45 = 32;
      var54 = "";
      var11 = var2.getSqlColumnFormat().getCharset();
      var18[10] = DatabaseManager.databaseDriver.checkString(var2.getSqlColumnFormat().getString())
         ? var23 + " VARCHAR(" + var45 + ')' + var11 + var54
         : var23 + " VARCHAR(" + var45 + ')' + var54;
      var23 = "server_origin";
      byte var46 = 32;
      var54 = "";
      var11 = var2.getSqlColumnFormat().getCharset();
      var18[11] = DatabaseManager.databaseDriver.checkString(var2.getSqlColumnFormat().getString())
         ? var23 + " VARCHAR(" + var46 + ')' + var11 + var54
         : var23 + " VARCHAR(" + var46 + ')' + var54;
      var23 = "silent";
      var36 = " NOT NULL";
      var18[12] = var23 + " BIT" + var36;
      var23 = "ipban";
      var36 = " NOT NULL";
      var18[13] = var23 + " BIT" + var36;
      var36 = "ipban_wildcard";
      var54 = " NOT NULL DEFAULT {false}";
      var18[14] = var36 + " BIT" + var54;
      var23 = "active";
      var36 = " NOT NULL";
      var18[15] = var23 + " BIT" + var36;
      var18[16] = var4;
      var36 = "id";
      var18[17] = "PRIMARY KEY(" + var36 + ')';
      return SqlString.getSqlString(var2.getString(var1, var18));
   }

   private final void executeAll(ConfiguredList var1) {
      try {
      ConfiguredList var2 = var1;
      AutoCloseable var4 = this.getStatement();
      Throwable var5 = null;

      try {
         Statement var6 = (Statement)var4;
         if (var2.isFlag()) {
            for (Object var24 : var2.getArrayList()) {
               String var25 = ((SqlString)var24).getString();

               try {
                  SqlString var15 = SqlString.getSqlString(var25);
                  var6.execute(var15.toString());
               } catch (SQLException var19) {
                  LiteBansCore.handleObjectDefault(var2.getLiteBansCore(), var19, 0, 2, null);
               }
            }
         } else {
            for (Object var11 : var2.getArrayList()) {
               CharSequence var12 = (CharSequence)var11;
               var6.addBatch(var12.toString());
            }

            if (!var2.getArrayList().isEmpty()) {
               var6.executeBatch();
            }
         }
      } catch (Throwable var20) {
         var5 = var20;
         throw var20;
      } finally {
         AutoCloseableKt.handleAutoCloseable(var4, var5);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   public final void upgradeTables(boolean var1, @NotNull LiteBansCore var2, @NotNull TableCreator var3, boolean var4) {
      try {
      ConfiguredList var5 = new ConfiguredList(this.liteBansPlatform, this, var1, null, 8, null);
      int var10000;
      if (var4) {
         var10000 = 0;
      } else {
         int var7;
         try {
            Table var9 = Table.CONFIG;
            CharSequence var10 = "build";
            ResultSet var54 = QueryRunner.getResultSet(
               this.prepare(SqlString.getSqlString(SqlString.getString(SqlString.getString2("SELECT " + var10 + " FROM " + var9), 1)))
            );
            byte var56 = 1;
            String var83;
            if (var54.next()) {
               ResultSet var12 = var54;

               String var14;
               try {
                  var14 = var12.getString(var56);
               } catch (SQLException var45) {
                  var14 = null;
               }

               var83 = var14;
            } else {
               var83 = null;
            }

            if (var83 == null) {
               var83 = BuildInfo.getString3();
            }

            String var51 = var83;
            Intrinsics.handleObject(var51);
            var7 = Integer.parseInt(var51);
         } catch (SQLException var46) {
            LiteBansCore.handleObjectDefault(var2, var46, 0, 2, null);
            var7 = 0;
         }

         var10000 = var7;
      }

      int var6 = var10000;
      if (var2.isDebugEnabled()) {
         var2.debug("Database build " + var6 + " => " + BuildInfo.getString3());
      }

      if (var6 <= 1168) {
         SyncManager var52 = (SyncManager)this.liteBansPlatform.getModule(SyncManager.class);
         var52.clearMessages(this);
      }

      AutoCloseable var53 = this.getStatement();
      Throwable var8 = null;

      try {
         Statement var55 = (Statement)var53;
         this.renameLegacyTables(var55);
         boolean var11 = Intrinsics.checkObject(var2.getConfig().getSqlDriver(), "mysql");
         boolean var57 = this.tableExists(Table.BANS);
         boolean var13 = this.tableExists(Table.MUTES);
         boolean var58 = this.tableExists(Table.WARNINGS);
         boolean var15 = this.columnExists(Table.BANS, "mode", var11);
         boolean var16 = this.columnExists(Table.WARNINGS, "warned", var11);
         if (!var13 && var57 && var15) {
            CharSequence var18 = this.getCreateTableSql(Table.MUTES, var3);
            var55.execute(var18.toString());
            var18 = this.getCreateTableSql(Table.WARNINGS, var3);
            var55.execute(var18.toString());
            Table var19 = Table.BANS;
            CharSequence var20 = "*";
            String var65 = SqlString.getString2("SELECT " + var20 + " FROM " + var19);
            var20 = "mode<>0";
            CharSequence[] var22 = new CharSequence[]{SqlString.getSqlString(var65), var20};
            CharSequence[] var24 = var22;
            int var26 = 0;

            for (int var27 = var22.length; var26 < var27; var26++) {
               CharSequence var28 = var24[var26];
               if (var28.length() <= 0) {
                  String var31 = "Failed requirement.";
                  throw new IllegalArgumentException(var31.toString());
               }
            }

            SqlString var66 = SqlString.getSqlString(SqlString.getString2(SqlString.getString(var65) + " WHERE " + var20));
            ResultSet var17 = var55.executeQuery(var66.toString());
            AutoCloseable var67 = var17;
            Throwable var76 = null;

            try {
               while (var17.next()) {
                  this.insertEntry(this.readEntry(var17, PunishmentType.values()[var17.getInt("mode")]));
               }
            } catch (Throwable var47) {
               var76 = var47;
               throw var47;
            } finally {
               AutoCloseableKt.handleAutoCloseable(var67, var76);
            }

            Table var61 = Table.BANS;
            String var62 = SqlString.getString2("DELETE FROM " + var61);
            CharSequence var68 = "mode<>0";
            CharSequence[] var21 = new CharSequence[]{SqlString.getSqlString(var62), var68};
            CharSequence[] var23 = var21;
            int var25 = 0;

            for (int var81 = var21.length; var25 < var81; var25++) {
               CharSequence var82 = var23[var25];
               if (var82.length() <= 0) {
                  String var30 = "Failed requirement.";
                  throw new IllegalArgumentException(var30.toString());
               }
            }

            SqlString var63 = SqlString.getSqlString(SqlString.getString2(SqlString.getString(var62) + " WHERE " + var68));
            var55.executeUpdate(var63.toString());
         }

         if (!Intrinsics.checkObject(var2.getConfig().getSqlDriver(), "sqlite")) {
            TableCreator var59 = this.getTableCreator();
            if (var15) {
               Table var69 = Table.BANS;
               SqlString var70 = SqlString.getSqlString(SqlString.getString7(SqlString.getString2("ALTER TABLE " + var69), "mode"));
               var55.execute(var70.toString());
            }

            if (var58 && !var16) {
               Table var71 = Table.WARNINGS;
               SqlString var72 = SqlString.getSqlString(SqlString.getString6(SqlString.getString2("ALTER TABLE " + var71), "warned BIT"));
               var55.execute(var72.toString());
               var71 = Table.WARNINGS;
               SqlString var74 = SqlString.getSqlString(SqlString.getString4(SqlString.getString2("UPDATE " + var71), "warned={true}"));
               var55.executeUpdate(var74.toString());
            }

            if (var1 || var57) {
               TableCreator var64 = var59;
               Table[] var77 = Table.values();
               int var78 = 0;

               for (int var79 = var77.length; var78 < var79; var78++) {
                  Table var80 = var77[var78];
                  if (var80.check()) {
                     var5.setTable(var80);
                     if (var80 != Table.KICKS) {
                        var5.handlePair(TuplesKt.getPair("removed_by_uuid", MigrationAddRemovedByUuid.INSTANCE));
                        var5.handlePair(TuplesKt.getPair("removed_by_name", MigrationAddRemovedByName.INSTANCE));
                        var5.handlePair(TuplesKt.getPair("removed_by_date", MigrationAddRemovedByDate.INSTANCE));
                        var5.handlePair(TuplesKt.getPair("removed_by_reason", MigrationAddRemovedByReason.INSTANCE));
                     }

                     var5.handlePair(TuplesKt.getPair("template", new MigrationAddTemplate(var64)));
                     var5.handlePair(TuplesKt.getPair("server_origin", new MigrationAddServerOrigin(var64)));
                     var5.handlePair(TuplesKt.getPair("server_scope", (Function1) t -> addServerScopeColumn(var3, (Table) t)));
                     var5.handlePair(TuplesKt.getPair("ipban_wildcard", (Function1) t -> addIpbanWildcardColumn(var3, (Table) t)));
                  }

                  if (var80 != Table.HISTORY && DatabaseManager.databaseDriver.checkString(var2.getConfig().getSqlDriver()) && var6 <= 680) {
                     SqlString.getString5(SqlString.getString2("ALTER TABLE " + var80), var59.getSqlColumnFormat().getCharset());
                  }
               }

               if (var1 || var6 < 883) {
                  var5.setTable(Table.CONFIG);
                  var5.handlePair(TuplesKt.getPair("accept", new MigrationAddAccept(var64)));
               }

               if (!var5.isEmpty()) {
                  this.liteBansPlatform.getLogger().info("Upgrading database tables...");
                  this.executeAll(var5);
                  this.liteBansPlatform.getLogger().info("Upgrade complete.");
               }
            }
         }
      } catch (Throwable var49) {
         var8 = var49;
         throw var49;
      } finally {
         AutoCloseableKt.handleAutoCloseable(var53, var8);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean isOpen() {
      return EntryReader.checkPunishmentStorage(this);
   }

   @Override
   public DatabaseManager getDatabaseManager() {
      return EntryReader.getDatabaseManager(this);
   }

   @Override
   public List getList(@NotNull PlayerHistory var1, @NotNull PunishmentType var2, @NotNull String var3, boolean var4, int var5) {
      return EntryReader.getList(this, var1, var2, var3, var4, var5);
   }

   @Override
   public List getList(@NotNull PunishmentEntry var1, int var2) {
      return EntryReader.getList(this, var1, var2);
   }

   @Override
   public List getList(@NotNull Sender var1, @NotNull PunishmentType var2, boolean var3, int var4) {
      return EntryReader.getList(this, var1, var2, var3, var4);
   }

   @Override
   public List readEntries(@NotNull ResultSet var1, @NotNull PunishmentType var2) {
      return EntryReader.getList(this, var1, var2);
   }

   @Override
   public String getString(@NotNull Sender var1) {
      return EntryReader.getString(this, var1);
   }

   @Override
   public CharSequence getCharSequence(@NotNull CharSequence var1, @NotNull Sender var2) {
      return EntryReader.getCharSequence(this, var1, var2);
   }

   @Override
   public CharSequence getCharSequence(@NotNull CharSequence var1, @Nullable String var2, @Nullable String var3, boolean var4) {
      return EntryReader.getCharSequence(this, var1, var2, var3, var4);
   }

   @Override
   public CharSequence getCharSequence(@NotNull CharSequence var1) {
      return EntryReader.getCharSequence(this, var1);
   }

   @Override
   public CharSequence getCharSequence(@NotNull CharSequence var1, @Nullable PlayerHistory var2, boolean var3) {
      return EntryReader.getCharSequence(this, var1, var2, var3);
   }

   @Override
   public CharSequence getCharSequence2(@NotNull CharSequence var1, @Nullable Sender var2) {
      return EntryReader.getCharSequence2(this, var1, var2);
   }

   @Override
   public CharSequence getCharSequence(@NotNull CharSequence var1, @NotNull ExecutorInfo var2) {
      return EntryReader.getCharSequence(this, var1, var2);
   }

   @Override
   public CharSequence getCharSequence(@NotNull CharSequence var1, @Nullable PunishmentEntry var2, boolean var3) {
      return EntryReader.getCharSequence(this, var1, var2, var3);
   }

   @Override
   public CharSequence getCharSequence(@NotNull CharSequence var1, @Nullable PunishmentEntry var2, @Nullable PlayerHistory var3, boolean var4) {
      return EntryReader.getCharSequence(this, var1, var2, var3, var4);
   }

   @Override
   public CharSequence getCharSequence(
      @NotNull CharSequence var1, @Nullable String var2, @Nullable String var3, @Nullable String var4, @Nullable String var5, @Nullable String var6
   ) {
      return EntryReader.getCharSequence(this, var1, var2, var3, var4, var5, var6);
   }

   @Override
   public boolean appliesTo(@NotNull PunishmentEntry var1, @NotNull Sender var2) {
      return EntryReader.checkPunishmentStorage(this, var1, var2);
   }

   @Override
   public boolean appliesToTarget(@Nullable PunishmentEntry var1) {
      return EntryReader.checkPunishmentStorage(this, var1);
   }

   private static final QueryStatement getQueryStatement(String var0, QueryStatement var1) {
      QueryStatement var2 = var1;
      String[] var3 = new String[]{var0};
      String[] var8 = var3;
      int var10 = 0;

      for (int var11 = var3.length; var10 < var11; var10++) {
         String var12 = var8[var10];
         var2.getQueryStatement(var12);
      }

      return var1;
   }

   private static final QueryStatement getQueryStatement(String var0, String var1, QueryStatement var2) {
      QueryStatement var3 = var2;
      String[] var4 = new String[]{var0, var1, var1};
      String[] var9 = var4;
      int var11 = 0;

      for (int var12 = var4.length; var11 < var12; var11++) {
         String var13 = var9[var11];
         var3.getQueryStatement(var13);
      }

      return QueryRunner.getQueryStatement(var2);
   }

   private static final QueryStatement getQueryStatement2(String var0, String var1, QueryStatement var2) {
      QueryStatement var3 = var2;
      String[] var4 = new String[]{var0, var1, var1};
      String[] var9 = var4;
      int var11 = 0;

      for (int var12 = var4.length; var11 < var12; var11++) {
         String var13 = var9[var11];
         var3.getQueryStatement(var13);
      }

      return var2;
   }

   private static final QueryStatement getQueryStatement(QueryStatement var0) {
      return QueryRunner.getQueryStatement(var0);
   }

   private static final QueryStatement getQueryStatement(SingleServerScopeFilter var0, QueryStatement var1) {
      var0.handleQueryStatement(var1);
      return var1;
   }

   private static final QueryStatement getQueryStatement(String var0, String var1, Template var2, QueryStatement var3) {
      QueryStatement var4 = var3;
      String[] var5 = new String[]{var0, var1};
      String[] var10 = var5;
      int var12 = 0;

      for (int var13 = var5.length; var12 < var13; var12++) {
         String var14 = var10[var12];
         var4.getQueryStatement(var14);
      }

      return var3.getQueryStatement(var2.getId());
   }

   private static final QueryStatement getQueryStatement(String var0, Template var1, QueryStatement var2) {
      QueryStatement var3 = var2.getQueryStatement(var0);
      return var3.getQueryStatement(var1.getId());
   }

   private static final QueryStatement getQueryStatement2(QueryStatement var0) {
      return QueryRunner.getQueryStatement(var0);
   }

   private static final QueryStatement getQueryStatement(long var0, QueryStatement var2) {
      return var2.getQueryStatement(var0);
   }

   private static final QueryStatement getQueryStatement3(String var0, String var1, QueryStatement var2) {
      QueryStatement var3 = var2;
      String[] var4 = new String[]{var0, var1};
      String[] var9 = var4;
      int var11 = 0;

      for (int var12 = var4.length; var11 < var12; var11++) {
         String var13 = var9[var11];
         var3.getQueryStatement(var13);
      }

      return var2;
   }

   private static final QueryStatement getQueryStatement2(String var0, QueryStatement var1) {
      return var1.getQueryStatement(var0);
   }

   private static final QueryStatement getQueryStatement3(QueryStatement var0) {
      return QueryRunner.getQueryStatement(var0);
   }

   private static final QueryStatement getQueryStatement2(long var0, QueryStatement var2) {
      return var2.getQueryStatement(var0);
   }

   private static final QueryStatement getQueryStatement(Template var0, QueryStatement var1) {
      return var1.getQueryStatement(var0.getId());
   }

   private static final QueryStatement getQueryStatement3(String var0, QueryStatement var1) {
      var1.getQueryStatement(var0);
      return var1;
   }

   private static final QueryStatement getQueryStatement(Timestamp var0, QueryStatement var1) {
      var1.getQueryStatement(var0);
      return var1;
   }

   private static final QueryStatement getQueryStatement(ServerScopeFilter var0, QueryStatement var1) {
      var0.handleQueryStatement(var1);
      return var1;
   }

   private static final SqlString[] addServerScopeColumn(TableCreator var0, Table var1) {
      SqlString[] var2 = new SqlString[2];
      String var10002 = SqlString.getString2("ALTER TABLE " + var1);
      String var3 = "server_scope";
      byte var4 = 32;
      String var5 = "";
      String var6 = var0.getSqlColumnFormat().getCharset();
      var2[0] = SqlString.getSqlString(
         SqlString.getString6(
            var10002,
            DatabaseManager.databaseDriver.checkString(var0.getSqlColumnFormat().getString())
               ? var3 + " VARCHAR(" + var4 + ')' + var6 + var5
               : var3 + " VARCHAR(" + var4 + ')' + var5
         )
      );
      var2[1] = SqlString.getSqlString(SqlString.getString3(SqlString.getString4(SqlString.getString2("UPDATE " + var1), "server_scope='*'"), "server_scope"));
      return var2;
   }

   private static final SqlString[] addIpbanWildcardColumn(TableCreator var0, Table var1) {
      SqlString[] var2 = new SqlString[2];
      String var10002 = SqlString.getString2("ALTER TABLE " + var1);
      String var4 = "ipban_wildcard";
      String var5 = " NOT NULL DEFAULT {false}";
      var2[0] = SqlString.getSqlString(SqlString.getString6(var10002, var4 + " BIT" + var5));
      String var3 = SqlString.getString4(SqlString.getString2("UPDATE " + var1), "ipban_wildcard={true}");
      CharSequence var19 = "ip like '%\\%%'";
      byte var17 = 1;
      CharSequence[] var6 = new CharSequence[]{SqlString.getSqlString(var3), var19};
      CharSequence[] var8 = var6;
      int var10 = 0;

      for (int var11 = var6.length; var10 < var11; var10++) {
         CharSequence var12 = var8[var10];
         if (var12.length() <= 0) {
            String var15 = "Failed requirement.";
            throw new IllegalArgumentException(var15.toString());
         }
      }

      String var18 = SqlString.getString2(SqlString.getString(var3) + " WHERE " + var19);
      var2[var17] = SqlString.getSqlString(var18);
      return var2;
   }

   private static final QueryStatement getQueryStatement4(QueryStatement var0) {
      return var0;
   }

   public static final Function1 getFunction1() {
      return function1;
   }

   public static final int getIntValue5() {
      return intValue5;
   }

   public static final void handleInt(int var0) {
      intValue5 = var0;
   }
}
