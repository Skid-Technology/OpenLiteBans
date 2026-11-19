package litebans.config;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.logging.Logger;
import litebans.command.ArgParser;
import litebans.command.ArgUtils;
import litebans.core.LiteBansCore;
import litebans.database.SqlStorage;
import litebans.hook.UsernamePattern;
import litebans.lib.json.JSONParser;
import litebans.lib.kotlin.Pair;
import litebans.lib.kotlin.TuplesKt;
import litebans.lib.kotlin.UByte;
import litebans.lib.kotlin.collections.ArraysKt;
import litebans.lib.kotlin.collections.CollectionsKt;
import litebans.lib.kotlin.jvm.internal.DefaultConstructorMarker;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.text.Regex;
import litebans.lib.kotlin.text.StringsKt;
import litebans.platform.LiteBansPlatform;
import litebans.punishment.BanMuteLimits;
import litebans.punishment.DurationUnit;
import litebans.punishment.GroupLimitOther;
import litebans.punishment.GroupLimits;
import litebans.punishment.PunishmentLimits;
import litebans.punishment.ServerScope;
import org.jetbrains.annotations.NotNull;

public final class Config {
   private final LiteBansPlatform liteBansPlatform;
   private final ConfigFile configFile;
   private String string;
   private String string2;
   private String sqlDriver;
   private final String sqlAddress;
   private final String string3;
   private final String string4;
   private final String string5;
   private final String string6;
   private final String sqlEngine;
   private final String sqlOptions;
   private final String[] sqlDrivers;
   private final int sqlPoolMinConnections;
   private final int sqlPoolMaxConnections;
   private final long sqlPoolKeepalive;
   private final long sqlPoolTimeout;
   private final long sqlPoolIdleTimeout;
   private final long sqlPoolMaxLifetime;
   private final List mutesCommandBlacklist;
   private final Set set;
   private final boolean warningsEnabled;
   private final String[] warningsActions;
   private final boolean warningsActionsExecuteAsConsole;
   private final boolean warningsDeleteWarningsAfterAction;
   private final String warningsExpireAfter;
   private final boolean exemptEnable;
   private final boolean exemptUseGroupWeights;
   private final boolean exemptPermitSameWeight;
   private final String exemptPermissionWorld;
   private final String[] exemptExemptGroups;
   private final String[] exemptExemptPlayers;
   private final boolean importImportIpbans;
   private final boolean importSqlite;
   private final String importSqliteFile;
   private final String importFrom;
   private final String importImportMysqlAddress;
   private final String importImportMysqlDatabase;
   private final String importImportMysqlUsername;
   private final String importImportMysqlPassword;
   private final String importImportTablePrefix;
   private final String defaultServerScope;
   private final String consoleSenderName;
   private final byte banAlts;
   private final byte ipreportMinimumAccounts;
   private final int banlistBansPerPage;
   private final int notificationThrottleTicks;
   private final int offlineTabSize;
   private final long durationsConsoleCooldown;
   private final long durationsEffectiveIpbanDuration;
   private final long securityObscureSeed;
   private final long notifyDupeipScanFilterDuration;
   private final int notifyDupeipScanLimit;
   private final int notifyDupeipOnJoinThreshold;
   private final String notifyNotifyDiscord;
   private final boolean notifyBannedPlayerJoin;
   private final boolean notifyMutedPlayerChat;
   private final boolean notifyDupeipOnJoin;
   private final boolean notifyDupeipOnBannedAccount;
   private final boolean notifyDupeipOnMutedAccount;
   private final boolean notifyDupeipShowMutedAccounts;
   private final boolean notifyNotifyConsole;
   private final boolean debugLevel;
   private final boolean onlineMode;
   private final boolean punishIp;
   private final boolean silent;
   private final boolean mutesPreventBypass;
   private final boolean mutesEnabled;
   private final boolean lockdownEnabled;
   private final boolean lockdownPersistEnabled;
   private final boolean supportAuthme;
   private final boolean autocompleteOnlinePlayerNames;
   private final boolean dupeipScanAllIps;
   private final boolean deletePreviousHistory;
   private final boolean unbanAllHistory;
   private final boolean banlistShowActiveOnly;
   private final boolean useDisplayNames;
   private final boolean banNames;
   private final boolean requireKickReason;
   private final boolean requireBanMuteReason;
   private final boolean requireUnbanUnmuteReason;
   private final boolean useDatabaseTime;
   private final boolean durationsReduceToLimit;
   private final boolean overrideLowerDuration;
   private final boolean sync;
   private final boolean syncBroadcasts;
   private final boolean syncNotifications;
   private final boolean syncDupeipNotifications;
   private final boolean localMessages;
   private final boolean updateCheck;
   private final boolean useProtocollib;
   private final boolean useTimezone;
   private final boolean securityDupeip;
   private final boolean securityStaffrollback;
   private final boolean securityIphistory;
   private final boolean securityIpban;
   private final byte securityIpbanCommand;
   private final boolean securityModify;
   private final boolean securityPrunehistory;
   private final boolean securitySenderCustom;
   private final boolean allowExemptBypass;
   private final boolean onlinePlayerSelectLimit;
   private final boolean disableLoginBanCheck;
   private final boolean disableWildcardBans;
   private final boolean disableExpiryUpdate;
   private final boolean checkAfterJoin;
   private final boolean flag;
   private final boolean geoipEnable;
   private final String geoipDownloadDb;
   private final String[] geoipBlacklist;
   private final String[] geoipWhitelist;
   private final String[] geoipDownloadApi;
   private final List ignoredKickMessages;
   private final List durationsLimits;
   private final Set hiddenDupeipPlayers;
   private final Set disableLoginBanCheckServers;
   private final List ignoredIphistoryServers;
   private final long databaseTimeOffset;
   private final long syncPollTicks;
   private final int syncPollSkip;
   private final int defaultHistoryLimit;
   private final byte usernameMaxLength;

   public Config(@NotNull LiteBansPlatform var1, @NotNull ConfigFile var2) {
      int var197;
      this.liteBansPlatform = var1;
      this.configFile = var2;
      LiteBansCore var3 = (LiteBansCore)this.liteBansPlatform.getModule(LiteBansCore.class);
      var3.setConfig(this);
      Config var4 = this;
      String var6 = System.getenv("LITEBANS_CONFIG");
      if (var6 != null) {
         this.liteBansPlatform.getLogger().info("Loading environment options: " + var6);
         Object var10000 = new JSONParser().getObject(var6);
         Intrinsics.handleObject(var10000, "");
         HashMap var7 = (HashMap)var10000;

         for (Object var11 : var7.entrySet()) {
            Entry var12 = (Entry)var11;
            var4.configFile.getConfigFile((String)var12.getKey(), var12.getValue());
         }
      }

      SqlStorage.sqlWhereBuilder.handleInt(this.configFile.getInt("online_player_select_limit", 64));
      this.onlinePlayerSelectLimit = this.configFile
         .checkString(
            "i_accept_that_dupeip_geoip_iphistory_and_ipban_will_not_work_without_getAddress_and_just_want_to_only_disable_warnings_logged_to_console", false
         );
      this.disableWildcardBans = this.configFile.checkString("disable_wildcard_bans", false);
      this.disableExpiryUpdate = this.configFile.checkString("disable_expiry_update", false);
      String var5 = "database_time_offset";
      var6 = "0 seconds";
      this.databaseTimeOffset = (long)DurationUnit.durationParser
         .getDouble(this.getConfigFile().getString(var5, var6), DurationUnit.durationParser.getDouble(var6, 0.0));
      this.usernameMaxLength = UByte.getByte((byte)this.configFile.getInt("username_max_length", 16));
      var3.setDebugLevel((byte)Math.max(0, this.configFile.getInt("debug_level", 0)));
      this.debugLevel = this.configFile.checkString("bungeecord", false);
      this.onlineMode = this.configFile.checkString("online_mode", true);
      var5 = this.configFile.getString("sql.driver", "h2");
      Locale var66 = Locale.ENGLISH;
      String var62 = var5.toLowerCase(var66);
      if (StringsKt.checkString(var62, "postgres", false, 2, null)) {
         var62 = "pgsql";
      }

      this.sqlDriver = var62;
      var5 = this.configFile.getString("sql.address", "localhost:3306");
      var6 = this.configFile.getString("sql.database", "litebans");
      String var68 = this.configFile.getString("sql.username", "");
      String var69 = this.configFile.getString("sql.password", "");
      String var9 = this.configFile.getString("sql.table_prefix", "litebans_");
      if (Intrinsics.checkObject(var62, "h2")) {
         try {
            File var74 = this.liteBansPlatform.getDataFolder();
            String var80 = "h2.yml";
            File var70 = new File(var74, var80);
            if (!var70.exists() || GenericConfigFile.getConfigFormat3() == null) {
               var74 = this.liteBansPlatform.getDataFolder();
               var80 = "h2.json";
               var70 = new File(var74, var80);
            }

            if (var70.exists()) {
               ConfigFile var76 = this.liteBansPlatform.loadConfig(var70).getConfigFile4();
               var68 = var76.getString("user", var68);
               var69 = var76.getString("password", var69);
            }
         } catch (Exception var61) {
            var61.printStackTrace();
         }
      }

      if (!Intrinsics.checkObject(var9, "litebans_")) {
         Intrinsics.handleObject(var9);
         CharSequence var71 = var9;
         Regex var77 = new Regex("^[0-9a-zA-Z_]*$");
         if (!var77.checkCharSequence(var71)) {
            this.liteBansPlatform.getLogger().warning("Table prefix '" + var9 + "' is invalid! Using default ('litebans_').");
            var9 = "litebans_";
         }
      }

      String var10001;
      label323: {
         Intrinsics.handleObject(var5);
         this.sqlAddress = var5;
         Intrinsics.handleObject(var68);
         this.string3 = var68;
         Intrinsics.handleObject(var69);
         this.string4 = var69;
         Intrinsics.handleObject(var6);
         this.string5 = var6;
         Intrinsics.handleObject(var9);
         this.string6 = var9;
         String var78 = this.configFile.getString("sql.engine", "InnoDB");
         if (!Intrinsics.checkObject(var78, "InnoDB")) {
            String[] var13 = new String[]{"mysql", "mariadb", "h2", "sqlite"};
            if (ArraysKt.checkObjectArray(var13, var78.toLowerCase(Locale.ROOT))) {
               var10001 = "InnoDB";
               break label323;
            }
         }

         var10001 = var78;
      }

      this.sqlEngine = var10001;
      String var72 = this.configFile.getString("sql.options", "useSSL=false&serverTimezone=UTC");
      this.sqlOptions = var72;
      var72 = "version:8";
      String[] var82 = new String[]{
         var72,
         "mysql:com.mysql.cj.jdbc.Driver:https://repo1.maven.org/maven2/mysql/mysql-connector-java/{}/mysql-connector-java-{}.jar:8.0.29:d4e32d2a6026b5acc00300b73a86c28fb92681ae9629b21048ee67014c911db6",
         "pgsql:org.postgresql.Driver:https://repo1.maven.org/maven2/org/postgresql/postgresql/{}/postgresql-{}.jar:42.4.0:fe25b9c0a2c59458504ec88862853df522ee87f8a02564835d537c29ae4cb125",
         "mariadb:org.mariadb.jdbc.Driver:https://repo1.maven.org/maven2/org/mariadb/jdbc/mariadb-java-client/{}/mariadb-java-client-{}.jar:3.1.2:aaec1ad348d030a65b25c93c65cdaf472bf8b4b6b314b965e5ba13aec81bc622",
         "sqlite:org.sqlite.JDBC:https://repo1.maven.org/maven2/org/xerial/sqlite-jdbc/{}/sqlite-jdbc-{}.jar:3.8.11.1:58ab29176a24a85a0c76177561257a986c4865e45730b79882c688846371d341",
         "h2:org.h2.Driver:https://repo1.maven.org/maven2/com/h2database/h2/{}/h2-{}.jar:1.4.197:37f5216e14af2772930dff9b8734353f0a80e89ba3f33e065441de6537c5e842"
      };
      String var14 = "sql.drivers";
      String[] var15 = Arrays.copyOf(var82, var82.length);
      String[] var190;
      if (!this.getConfigFile().checkString3(var14)) {
         var190 = Arrays.copyOf(var15, var15.length);
      } else {
         Collection var17 = this.getConfigFile().getList(var14);
         var190 = (String[]) var17.toArray(new String[0]);
      }

      var82 = var190;
      if (!Intrinsics.checkObject(ArraysKt.getObject(var82, 0), var72)) {
         var82 = var82;
         var3.debug("Using default database drivers (" + var72 + ").");
      }

      this.sqlDrivers = var82;
      this.sqlPoolMinConnections = this.configFile.getInt("sql.pool.min_connections", 1);
      this.sqlPoolMaxConnections = this.configFile.getInt("sql.pool.max_connections", 10);
      var14 = "sql.pool.keepalive";
      String var90 = "0 seconds";
      this.sqlPoolKeepalive = (long)DurationUnit.durationParser
         .getDouble(this.getConfigFile().getString(var14, var90), DurationUnit.durationParser.getDouble(var90, 0.0));
      var14 = "sql.pool.timeout";
      String var91 = "95 seconds";
      this.sqlPoolTimeout = (long)DurationUnit.durationParser
         .getDouble(this.getConfigFile().getString(var14, var91), DurationUnit.durationParser.getDouble(var91, 0.0));
      var14 = "sql.pool.idle_timeout";
      String var92 = "1 minute";
      this.sqlPoolIdleTimeout = (long)DurationUnit.durationParser
         .getDouble(this.getConfigFile().getString(var14, var92), DurationUnit.durationParser.getDouble(var92, 0.0));
      var14 = "sql.pool.max_lifetime";
      String var93 = "15 minutes";
      this.sqlPoolMaxLifetime = (long)DurationUnit.durationParser
         .getDouble(this.getConfigFile().getString(var14, var93), DurationUnit.durationParser.getDouble(var93, 0.0));
      ConfigSection var84 = this.configFile.getConfigSection("durations.limits");
      ArrayList var89 = new ArrayList();
      if (var84 != null) {
         Collection var94 = var84.getCollection(false);
         Intrinsics.handleObject(var94);

         for (Object var19 : var94) {
            String var20 = (String)var19;
            ConfigSection var22 = var84.getConfigSection(var20);
            if (var22 != null) {
               String[] var25 = new String[]{"permission", "tempban", "tempmute"};
               String[] var27 = var25;
               Collection var28 = new ArrayList();
               int var30 = 0;

               for (int var31 = var25.length; var30 < var31; var30++) {
                  String var32 = var27[var30];
                  if (var22.getObject(var32) == null) {
                     var28.add(var32);
                  }
               }

               Iterable var167 = (List)var28;
               var28 = new ArrayList(CollectionsKt.getInt(var167, 10));

               for (Object var184 : var167) {
                  String var185 = (String)var184;
                  var28.add(
                     ArgUtils.getCharSequence(
                        "The duration limit group \"" + var20 + "\" does not have the \"" + var185 + "\" option specified.",
                        "Note that tempban/tempmute durations will default to 27 seconds and the permission will default to none."
                     )
                  );
               }

               Iterable var168 = (List)var28;
               Logger var26 = this.liteBansPlatform.getLogger();

               for (Object var29 : var168) {
                  CharSequence var182 = (CharSequence)var29;
                  var26.warning(var182.toString());
               }

               String var23 = "27 seconds";
               String var24 = "5 seconds";
               String var169 = var22.getString("permission", "none");
               String var183 = "tempban";
               long var36 = (long)DurationUnit.durationParser.getDouble(var22.getString(var183, var23), DurationUnit.durationParser.getDouble(var23, 0.0));
               String var186 = "tempmute";
               long var38 = (long)DurationUnit.durationParser.getDouble(var22.getString(var186, var23), DurationUnit.durationParser.getDouble(var23, 0.0));
               if (var36 <= 0L) {
                  var36 = Long.MAX_VALUE;
               }

               if (var38 <= 0L) {
                  var38 = Long.MAX_VALUE;
               }

               String var34 = "cooldown_ban";
               long var40 = (long)DurationUnit.durationParser.getDouble(var22.getString(var34, var24), DurationUnit.durationParser.getDouble(var24, 0.0));
               String var42 = "cooldown_mute";
               long var44 = (long)DurationUnit.durationParser.getDouble(var22.getString(var42, var24), DurationUnit.durationParser.getDouble(var24, 0.0));
               String var46 = "cooldown_warn";
               long var48 = (long)DurationUnit.durationParser.getDouble(var22.getString(var46, var24), DurationUnit.durationParser.getDouble(var24, 0.0));
               String var50 = "cooldown_kick";
               long var52 = (long)DurationUnit.durationParser.getDouble(var22.getString(var50, var24), DurationUnit.durationParser.getDouble(var24, 0.0));
               String var54 = "cooldown_redo";
               String var55 = "1 second";
               long var57 = (long)DurationUnit.durationParser.getDouble(var22.getString(var54, var55), DurationUnit.durationParser.getDouble(var55, 0.0));
               boolean var187 = var22.checkString("require_template", false);
               Intrinsics.handleObject(var169);
               GroupLimits var51 = new GroupLimits(
                  var169, new BanMuteLimits(var36, var38), new PunishmentLimits(var40, var44, var48, var52, var57), new GroupLimitOther(var187)
               );
               var89.add(var51);
            }
         }
      }

      this.durationsLimits = var89;
      String var96 = "durations.console_cooldown";
      String var100 = "1 second";
      this.durationsConsoleCooldown = (long)DurationUnit.durationParser
         .getDouble(this.getConfigFile().getString(var96, var100), DurationUnit.durationParser.getDouble(var100, 0.0));
      var96 = "durations.effective_ipban_duration";
      var100 = "permanent";
      this.durationsEffectiveIpbanDuration = (long)DurationUnit.durationParser
         .getDouble(this.getConfigFile().getString(var96, var100), DurationUnit.durationParser.getDouble(var100, 0.0));
      String var95 = this.configFile.getString("security.obscure_seed", "0");
      this.securityObscureSeed = (long)var95.hashCode();
      this.mutesEnabled = this.configFile.checkString("mutes.enabled", true);
      this.durationsReduceToLimit = this.configFile.checkString("durations.reduce_to_limit", this.configFile.checkString("durations.round_down", true));
      this.mutesPreventBypass = this.configFile.checkString("mutes.prevent_bypass", true);
      this.lockdownEnabled = this.configFile.checkString("lockdown.enabled", true);
      this.lockdownPersistEnabled = this.configFile.checkString("lockdown.persist_enabled", true);
      String var106 = "mutes.command_blacklist";
      String[] var122 = new String[]{"/me", "/say", "/msg", "/tell"};
      if (!this.getConfigFile().checkString3(var106)) {
         var190 = Arrays.copyOf(var122, var122.length);
      } else {
         Collection var21 = this.getConfigFile().getList(var106);
         var190 = (String[]) var21.toArray(new String[0]);
      }

      String[] var99 = var190;
      Iterable var123 = ArgParser.getList(var99);
      Collection var150 = new ArrayList(CollectionsKt.getInt(var123, 10));

      for (Object var170 : var123) {
         String var173 = (String)var170;
         var150.add(StringsKt.checkCharSequence2(var173, ' ', false, 2, null) ? var173 : var173 + ' ');
      }

      List var60 = (List)var150;
      this.mutesCommandBlacklist = var60;
      this.set = CollectionsKt.getHashSet(ArgParser.getList(var99));
      this.warningsEnabled = this.configFile.checkString("warnings.enabled", true);
      var100 = this.configFile.getString("warnings.expire_after", "3 days");
      this.warningsExpireAfter = var100;
      var106 = "warnings.actions";
      var122 = new String[0];
      String[] var196;
      if (!this.getConfigFile().checkString3(var106)) {
         var196 = Arrays.copyOf(var122, var122.length);
      } else {
         Collection var144 = this.getConfigFile().getList(var106);
         var196 = /* cast-fix */(String[])(var144.toArray(new String[0]));
      }

      label273: {
         this.warningsActions = var196;
         this.warningsActionsExecuteAsConsole = this.configFile.checkString("warnings.actions_execute_as_console", false);
         this.warningsDeleteWarningsAfterAction = this.configFile.checkString2("warnings.delete_warnings_after_action");
         var106 = this.configFile.getString("ban_alts", "");
         var100 = var106 != null ? var106.toLowerCase(Locale.ROOT) : null;
         if (var100 != null) {
            switch (var100.hashCode()) {
               case -1609594047:
                  if (var100.equals("enabled")) {
                     var197 = 1;
                     break label273;
                  }
                  break;
               case -1305285460:
                  if (var100.equals("extreme")) {
                     var197 = 3;
                     break label273;
                  }
                  break;
               case 1841730422:
                  if (var100.equals("enhanced")) {
                     var197 = 2;
                     break label273;
                  }
            }
         }

         var197 = 0;
      }

      this.banAlts = (byte) var197;
      ConfigSection var125 = this.configFile;
      String var134 = "notify.dupeip_scan_filter_duration";
      String var145 = "7d";
      this.notifyDupeipScanFilterDuration = (long)DurationUnit.durationParser
         .getDouble(var125.getString(var134, var145), DurationUnit.durationParser.getDouble(var145, 0.0));
      this.notifyDupeipOnJoinThreshold = this.configFile.getInt("notify.dupeip_on_join_threshold", 9999999);
      this.notifyDupeipScanLimit = this.configFile.getInt("notify.dupeip_scan_limit", 20);
      var106 = this.configFile.getString("notify.notify_discord", "");
      this.notifyNotifyDiscord = var106;
      this.notifyBannedPlayerJoin = this.configFile.checkString("notify.banned_player_join", true);
      this.notifyMutedPlayerChat = this.configFile.checkString("notify.muted_player_chat", true);
      this.notifyDupeipOnJoin = this.configFile.checkString("notify.dupeip_on_join", true) || this.banAlts > 1;
      this.notifyDupeipOnBannedAccount = this.configFile.checkString("notify.dupeip_on_banned_account", true);
      this.notifyDupeipOnMutedAccount = this.configFile.checkString("notify.dupeip_on_muted_account", false);
      this.notifyDupeipShowMutedAccounts = this.configFile.checkString("notify.dupeip_show_muted_accounts", false);
      this.notifyNotifyConsole = this.configFile.checkString("notify.notify_console", true);
      this.exemptEnable = this.configFile.checkString("exempt.enable", true);
      this.exemptUseGroupWeights = this.configFile.checkString("exempt.use_group_weights", false);
      this.exemptPermitSameWeight = this.configFile.checkString("exempt.permit_same_weight", true);
      var106 = this.configFile.getString("exempt.permission_world", "default");
      this.exemptPermissionWorld = var106;
      String var126 = "exempt.exempt_groups";
      String[] var135 = new String[0];
      String[] var198;
      if (!this.getConfigFile().checkString3(var126)) {
         var198 = Arrays.copyOf(var135, var135.length);
      } else {
         var150 = this.getConfigFile().getList(var126);
         var198 = /* cast-fix */(String[])(var150.toArray(new String[0]));
      }

      this.exemptExemptGroups = var198;
      String var127 = "exempt.exempt_players";
      String[] var136 = new String[0];
      String[] var199;
      if (!this.getConfigFile().checkString3(var127)) {
         var199 = Arrays.copyOf(var136, var136.length);
      } else {
         var150 = this.getConfigFile().getList(var127);
         var199 = /* cast-fix */(String[])(var150.toArray(new String[0]));
      }

      this.exemptExemptPlayers = var199;
      this.geoipEnable = this.configFile.checkString("geoip.enable", false);
      String var128 = "geoip.blacklist";
      String[] var137 = new String[0];
      String[] var200;
      if (!this.getConfigFile().checkString3(var128)) {
         var200 = Arrays.copyOf(var137, var137.length);
      } else {
         var150 = this.getConfigFile().getList(var128);
         var200 = /* cast-fix */(String[])(var150.toArray(new String[0]));
      }

      this.geoipBlacklist = var200;
      String var129 = "geoip.whitelist";
      String[] var138 = new String[0];
      String[] var201;
      if (!this.getConfigFile().checkString3(var129)) {
         var201 = Arrays.copyOf(var138, var138.length);
      } else {
         var150 = this.getConfigFile().getList(var129);
         var201 = /* cast-fix */(String[])(var150.toArray(new String[0]));
      }

      this.geoipWhitelist = var201;
      String var130 = "geoip.download.api";
      String[] var139 = new String[]{
         "https://repo1.maven.org/maven2/com/maxmind/geoip2/geoip2/2.1.0/geoip2-2.1.0.jar:81035e738fc392bcfb00be5d39f50fde56692ba305b21338412da9c0fc98e3fb",
         "https://repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-annotations/2.4.3/jackson-annotations-2.4.3.jar:3c680cfafad00cfe1432514f19c5c13fde17913aa2269a9844882404242f6452",
         "https://repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-core/2.4.3/jackson-core-2.4.3.jar:2c77b1c9441f257eb9e3dda476758a034a9383ef848f97b9949287a0519923e6",
         "https://repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-databind/2.4.3/jackson-databind-2.4.3.jar:d7ac4f5ce138ce63478673e64378c2efd27f0294602f0fc69cb867685c723797",
         "https://repo1.maven.org/maven2/com/maxmind/db/maxmind-db/1.0.0/maxmind-db-1.0.0.jar:3cd5c1c731d8d9cb561d4e53ad6017862e4917cc08c24f45111061b1c2170b06"
      };
      String[] var202;
      if (!this.getConfigFile().checkString3(var130)) {
         var202 = Arrays.copyOf(var139, var139.length);
      } else {
         var150 = this.getConfigFile().getList(var130);
         var202 = /* cast-fix */(String[])(var150.toArray(new String[0]));
      }

      this.geoipDownloadApi = var202;
      var106 = this.configFile
         .getString(
            "geoip.download.db", "https://download.maxmind.com/app/geoip_download?edition_id=GeoLite2-Country&license_key=YOUR_LICENSE_KEY&suffix=tar.gz"
         );
      this.geoipDownloadDb = var106;
      var106 = this.configFile.getString("import.from", "vanilla");
      this.importFrom = var106;
      this.importSqlite = this.configFile.checkString("import.sqlite", true);
      this.importImportIpbans = this.configFile.checkString("import.import_ipbans", true);
      var106 = this.configFile.getString("import.sqlite_file", "plugins/MaxBans/bans.db");
      this.importSqliteFile = var106;
      var106 = this.configFile.getString("import.import_mysql_address", "localhost:3306");
      this.importImportMysqlAddress = var106;
      var106 = this.configFile.getString("import.import_mysql_username", "");
      this.importImportMysqlUsername = var106;
      var106 = this.configFile.getString("import.import_mysql_password", "");
      this.importImportMysqlPassword = var106;
      var106 = this.configFile.getString("import.import_mysql_database", "maxbans");
      this.importImportMysqlDatabase = var106;
      var106 = this.configFile.getString("import.import_table_prefix", "default");
      this.importImportTablePrefix = var106;
      var106 = Message.getString(this.configFile.getString("console_sender_name", "Console"), true);
      CharSequence var120 = var106;
      byte var131 = this.usernameMaxLength;
      this.consoleSenderName = ArgUtils.getString(var120.toString(), var131 & 255);
      this.autocompleteOnlinePlayerNames = this.configFile.checkString("autocomplete_online_player_names", false);
      this.dupeipScanAllIps = this.configFile.checkString("dupeip_scan_all_ips", false);
      ConfigFile var132 = this.configFile;
      var134 = "ipreport_minimum_accounts";
      byte var146 = 1;
      this.ipreportMinimumAccounts = (byte)var132.getInt(var134, var146);
      this.deletePreviousHistory = this.configFile.checkString("delete_previous_history", true);
      this.unbanAllHistory = this.configFile.checkString("unban_all_history", false);
      this.supportAuthme = this.configFile.checkString("support_authme", true);
      this.useDisplayNames = this.configFile.checkString("use_display_names", false);
      this.banlistShowActiveOnly = this.configFile.checkString("banlist_show_active_only", false);
      this.banlistBansPerPage = Math.max(0, this.configFile.getInt("banlist_bans_per_page", 5));
      boolean var121 = this.configFile.checkString("ban_names", false);
      if (this.liteBansPlatform.getPlatformId() == 0 && this.liteBansPlatform.createBridge().isPluginEnabled("FastLogin")) {
         var3.debug("FastLogin detected; forcing `ban_names=true`.");
         var121 = true;
      }

      this.banNames = var121;
      this.offlineTabSize = this.configFile.getInt("offline_tab_size", 12000);
      this.notificationThrottleTicks = this.configFile.getInt("notification_throttle_ticks", 12000);
      this.requireKickReason = this.configFile.checkString("require_kick_reason", false);
      this.requireBanMuteReason = this.configFile.checkString("require_ban_mute_reason", false);
      this.requireUnbanUnmuteReason = this.configFile.checkString("require_unban_unmute_reason", false);
      Pair var133 = getPairDefault(this, false, 1, null);
      this.string2 = (String)var133.getObject();
      this.string = (String)var133.getObject2();
      var134 = this.configFile.getString("default_server_scope", "*");
      this.defaultServerScope = ArgUtils.getString(var134, 32);
      this.syncPollTicks = Math.max(40L, this.configFile.getLong("sync_poll_ticks", 55L));
      this.syncPollSkip = Math.max(1, this.configFile.getInt("sync_poll_skip", 3));
      var134 = "hidden_dupeip_players";
      String[] var161 = new String[0];
      String[] var203;
      if (!this.getConfigFile().checkString3(var134)) {
         var203 = Arrays.copyOf(var161, var161.length);
      } else {
         Collection var174 = this.getConfigFile().getList(var134);
         var203 = /* cast-fix */(String[])(var174.toArray(new String[0]));
      }

      this.hiddenDupeipPlayers = CollectionsKt.getSet(ArgParser.getList(var203));
      var145 = "ignored_kick_messages";
      String[] var156 = new String[]{"restarting"};
      String[] var204;
      if (!this.getConfigFile().checkString3(var145)) {
         var204 = Arrays.copyOf(var156, var156.length);
      } else {
         Collection var162 = this.getConfigFile().getList(var145);
         var204 = /* cast-fix */(String[])(var162.toArray(new String[0]));
      }

      this.ignoredKickMessages = ArgParser.getList(var204);
      var145 = "ignored_iphistory_servers";
      String var163 = "ignored_history_servers";
      String[] var171 = new String[0];
      String[] var205;
      if (!this.getConfigFile().checkString3(var163)) {
         var205 = Arrays.copyOf(var171, var171.length);
      } else {
         Collection var177 = this.getConfigFile().getList(var163);
         var205 = /* cast-fix */(String[])(var177.toArray(new String[0]));
      }

      String[] var157 = var205;
      String[] var158 = Arrays.copyOf(var157, var157.length);
      String[] var206;
      if (!this.getConfigFile().checkString3(var145)) {
         var206 = Arrays.copyOf(var158, var158.length);
      } else {
         Collection var164 = this.getConfigFile().getList(var145);
         var206 = /* cast-fix */(String[])(var164.toArray(new String[0]));
      }

      this.ignoredIphistoryServers = ArgParser.getList(var206);
      var134 = "disable_login_ban_check_servers";
      var161 = new String[0];
      String[] var207;
      if (!this.getConfigFile().checkString3(var134)) {
         var207 = Arrays.copyOf(var161, var161.length);
      } else {
         Collection var175 = this.getConfigFile().getList(var134);
         var207 = /* cast-fix */(String[])(var175.toArray(new String[0]));
      }

      this.disableLoginBanCheckServers = CollectionsKt.getSet(ArgParser.getList(var207));
      Iterable var149 = this.ignoredIphistoryServers;
      boolean var193;
      if (var149 instanceof Collection && ((Collection)var149).isEmpty()) {
         var193 = false;
      } else {
         Iterator var172 = var149.iterator();

         while (true) {
            if (!var172.hasNext()) {
               var193 = false;
               break;
            }

            Object var176 = var172.next();
            String var178 = (String)var176;
            if (ServerScope.serverScopeParser.checkString2(var178)) {
               var193 = true;
               break;
            }
         }
      }

      boolean var188 = var193;
      this.flag = var188 || this.ignoredIphistoryServers.contains(var133.getObject());
      this.defaultHistoryLimit = this.configFile.getInt("default_history_limit", 10);
      this.sync = this.configFile.checkString("sync", true);
      this.syncBroadcasts = this.configFile.checkString("sync_broadcasts", true);
      this.syncNotifications = this.configFile.checkString("sync_notifications", true);
      this.syncDupeipNotifications = this.configFile.checkString("sync_dupeip_notifications", false);
      this.useDatabaseTime = this.configFile.checkString("use_database_time", false);
      this.localMessages = this.configFile.checkString("local_messages", true);
      this.checkAfterJoin = this.configFile.checkString("check_after_join", false);
      this.useProtocollib = this.configFile.checkString("use_protocollib", true);
      this.overrideLowerDuration = this.configFile.checkString("override_lower_duration", true);
      this.allowExemptBypass = this.exemptUseGroupWeights || this.configFile.checkString("allow_exempt_bypass", true);
      this.silent = this.configFile.checkString("silent", false);
      this.punishIp = this.configFile.checkString("punish_ip", false);
      this.useTimezone = this.configFile.checkString("use_timezone", true);
      this.updateCheck = this.configFile.checkString("update_check", true);
      this.securityDupeip = this.configFile.checkString("security.dupeip", true);
      this.securityIphistory = this.configFile.checkString("security.iphistory", true);
      this.securityStaffrollback = this.configFile.checkString("security.staffrollback", true);
      this.securityModify = this.configFile.checkString("security.modify", false);
      this.securityPrunehistory = this.configFile.checkString("security.prunehistory", false);
      this.securitySenderCustom = this.configFile.checkString("security.sender_custom", true);
      this.securityIpban = this.configFile.checkString("security.ipban", true);
      this.securityIpbanCommand = (byte)Math.min(this.configFile.getInt("security.ipban_command", 1), 2);
      this.disableLoginBanCheck = this.configFile.checkString("disable_login_ban_check", false) && this.liteBansPlatform.getPlatformId() == 0;
      Logger var159 = this.liteBansPlatform.getLogger();
      if (!this.localMessages) {
         var159.warning("Local messages are disabled! If you don't see any broadcasts, try enabling local_messages.");
      }

      if (this.disableLoginBanCheck) {
         var159.warning("Login ban checks are disabled! Bans will not take effect here.");
      }

      if (this.disableWildcardBans) {
         var159.warning("Wildcard bans are disabled! Wildcard IP-bans and IP-mutes will not take effect.");
      }

      if (this.disableExpiryUpdate) {
         var159.warning("Expiry update query is disabled! One instance should have this enabled, at least.");
      }

      if (this.banAlts == 0) {
         String var190s = null;
         label221: {
            String var194 = this.configFile.getString("ban_alts", "");
            if (var194 != null) {
               var190s = var194.toLowerCase(Locale.ROOT);
               if (var190s != null) {
                  break label221;
               }
            }

            var190s = "";
         }

         String var166 = var190s;
         if (var166.length() > 0 && !Intrinsics.checkObject(var166, "disabled")) {
            var159.warning("Unrecognized setting for ban_alts: \"" + var166 + "\". This option will be disabled.");
         }
      }

      if (var3.isDebugEnabled()) {
         CharSequence var189 = ArgUtils.getCharSequence(
            ArgUtils.getCharSequence(
               CollectionsKt.getString(this.durationsLimits, null, null, null, 0, null, null, 63, null), "m:" + Arrays.toString((Object[])var99)
            ),
            "gw: " + Arrays.toString((Object[])this.geoipWhitelist)
         );
         var3.debug(var189);
      }
   }

   public Config(LiteBansPlatform var1, ConfigFile var2, int var3, DefaultConstructorMarker var4) {
      this(var1, ((var3 & 2) != 0 ? var1.getMainConfig() : var2));
   }

   public final LiteBansPlatform getLiteBansPlatform() {
      return this.liteBansPlatform;
   }

   public final ConfigFile getConfigFile() {
      return this.configFile;
   }

   public final String getString() {
      return this.string;
   }

   public final void setString(@NotNull String var1) {
      this.string = var1;
   }

   public final String getString2() {
      return this.string2;
   }

   public final void setString2(@NotNull String var1) {
      this.string2 = var1;
   }

   public final String getSqlDriver() {
      return this.sqlDriver;
   }

   public final void setSqlDriver(@NotNull String var1) {
      this.sqlDriver = var1;
   }

   public final String getSqlAddress() {
      return this.sqlAddress;
   }

   public final String getString3() {
      return this.string3;
   }

   public final String getString4() {
      return this.string4;
   }

   public final String getString5() {
      return this.string5;
   }

   public final String getString6() {
      return this.string6;
   }

   public final String getSqlEngine() {
      return this.sqlEngine;
   }

   public final String getSqlOptions() {
      return this.sqlOptions;
   }

   public final String[] getSqlDrivers() {
      return this.sqlDrivers;
   }

   public final int getSqlPoolMinConnections() {
      return this.sqlPoolMinConnections;
   }

   public final int getSqlPoolMaxConnections() {
      return this.sqlPoolMaxConnections;
   }

   public final long getSqlPoolKeepalive() {
      return this.sqlPoolKeepalive;
   }

   public final long getSqlPoolTimeout() {
      return this.sqlPoolTimeout;
   }

   public final long getSqlPoolIdleTimeout() {
      return this.sqlPoolIdleTimeout;
   }

   public final List getMutesCommandBlacklist() {
      return this.mutesCommandBlacklist;
   }

   public final Set getSet() {
      return this.set;
   }

   public final boolean isWarningsEnabled() {
      return this.warningsEnabled;
   }

   public final String[] getWarningsActions() {
      return this.warningsActions;
   }

   public final boolean isWarningsActionsExecuteAsConsole() {
      return this.warningsActionsExecuteAsConsole;
   }

   public final boolean isWarningsDeleteWarningsAfterAction() {
      return this.warningsDeleteWarningsAfterAction;
   }

   public final String getWarningsExpireAfter() {
      return this.warningsExpireAfter;
   }

   public final boolean isExemptEnable() {
      return this.exemptEnable;
   }

   public final boolean isExemptUseGroupWeights() {
      return this.exemptUseGroupWeights;
   }

   public final boolean isExemptPermitSameWeight() {
      return this.exemptPermitSameWeight;
   }

   public final String getExemptPermissionWorld() {
      return this.exemptPermissionWorld;
   }

   public final String[] getExemptExemptGroups() {
      return this.exemptExemptGroups;
   }

   public final String[] getExemptExemptPlayers() {
      return this.exemptExemptPlayers;
   }

   public final boolean isImportImportIpbans() {
      return this.importImportIpbans;
   }

   public final boolean isImportSqlite() {
      return this.importSqlite;
   }

   public final String getImportSqliteFile() {
      return this.importSqliteFile;
   }

   public final String getImportFrom() {
      return this.importFrom;
   }

   public final String getImportImportMysqlAddress() {
      return this.importImportMysqlAddress;
   }

   public final String getImportImportMysqlDatabase() {
      return this.importImportMysqlDatabase;
   }

   public final String getImportImportMysqlUsername() {
      return this.importImportMysqlUsername;
   }

   public final String getImportImportMysqlPassword() {
      return this.importImportMysqlPassword;
   }

   public final String getImportImportTablePrefix() {
      return this.importImportTablePrefix;
   }

   public final String getDefaultServerScope() {
      return this.defaultServerScope;
   }

   public final String getConsoleSenderName() {
      return this.consoleSenderName;
   }

   public final byte getBanAlts() {
      return this.banAlts;
   }

   public final byte getIpreportMinimumAccounts() {
      return this.ipreportMinimumAccounts;
   }

   public final int getBanlistBansPerPage() {
      return this.banlistBansPerPage;
   }

   public final int getNotificationThrottleTicks() {
      return this.notificationThrottleTicks;
   }

   public final int getOfflineTabSize() {
      return this.offlineTabSize;
   }

   public final long getDurationsConsoleCooldown() {
      return this.durationsConsoleCooldown;
   }

   public final long getDurationsEffectiveIpbanDuration() {
      return this.durationsEffectiveIpbanDuration;
   }

   public final long getSecurityObscureSeed() {
      return this.securityObscureSeed;
   }

   public final long getNotifyDupeipScanFilterDuration() {
      return this.notifyDupeipScanFilterDuration;
   }

   public final int getNotifyDupeipScanLimit() {
      return this.notifyDupeipScanLimit;
   }

   public final int getNotifyDupeipOnJoinThreshold() {
      return this.notifyDupeipOnJoinThreshold;
   }

   public final String getNotifyNotifyDiscord() {
      return this.notifyNotifyDiscord;
   }

   public final boolean isNotifyBannedPlayerJoin() {
      return this.notifyBannedPlayerJoin;
   }

   public final boolean isNotifyMutedPlayerChat() {
      return this.notifyMutedPlayerChat;
   }

   public final boolean isNotifyDupeipOnJoin() {
      return this.notifyDupeipOnJoin;
   }

   public final boolean isNotifyDupeipOnBannedAccount() {
      return this.notifyDupeipOnBannedAccount;
   }

   public final boolean isNotifyDupeipOnMutedAccount() {
      return this.notifyDupeipOnMutedAccount;
   }

   public final boolean isNotifyDupeipShowMutedAccounts() {
      return this.notifyDupeipShowMutedAccounts;
   }

   public final boolean isNotifyNotifyConsole() {
      return this.notifyNotifyConsole;
   }

   public final boolean isDebugLevel() {
      return this.debugLevel;
   }

   public final boolean isOnlineMode() {
      return this.onlineMode;
   }

   public final boolean isPunishIp() {
      return this.punishIp;
   }

   public final boolean isSilent() {
      return this.silent;
   }

   public final boolean isMutesPreventBypass() {
      return this.mutesPreventBypass;
   }

   public final boolean isMutesEnabled() {
      return this.mutesEnabled;
   }

   public final boolean isLockdownEnabled() {
      return this.lockdownEnabled;
   }

   public final boolean isLockdownPersistEnabled() {
      return this.lockdownPersistEnabled;
   }

   public final boolean isSupportAuthme() {
      return this.supportAuthme;
   }

   public final boolean isAutocompleteOnlinePlayerNames() {
      return this.autocompleteOnlinePlayerNames;
   }

   public final boolean isDupeipScanAllIps() {
      return this.dupeipScanAllIps;
   }

   public final boolean isDeletePreviousHistory() {
      return this.deletePreviousHistory;
   }

   public final boolean isUnbanAllHistory() {
      return this.unbanAllHistory;
   }

   public final boolean isBanlistShowActiveOnly() {
      return this.banlistShowActiveOnly;
   }

   public final boolean isUseDisplayNames() {
      return this.useDisplayNames;
   }

   public final boolean isBanNames() {
      return this.banNames;
   }

   public final boolean isRequireKickReason() {
      return this.requireKickReason;
   }

   public final boolean isRequireBanMuteReason() {
      return this.requireBanMuteReason;
   }

   public final boolean isRequireUnbanUnmuteReason() {
      return this.requireUnbanUnmuteReason;
   }

   public final boolean isUseDatabaseTime() {
      return this.useDatabaseTime;
   }

   public final boolean isDurationsReduceToLimit() {
      return this.durationsReduceToLimit;
   }

   public final boolean isOverrideLowerDuration() {
      return this.overrideLowerDuration;
   }

   public final boolean isSync() {
      return this.sync;
   }

   public final boolean isSyncBroadcasts() {
      return this.syncBroadcasts;
   }

   public final boolean isSyncNotifications() {
      return this.syncNotifications;
   }

   public final boolean isSyncDupeipNotifications() {
      return this.syncDupeipNotifications;
   }

   public final boolean isLocalMessages() {
      return this.localMessages;
   }

   public final boolean isUpdateCheck() {
      return this.updateCheck;
   }

   public final boolean isUseProtocollib() {
      return this.useProtocollib;
   }

   public final boolean isUseTimezone() {
      return this.useTimezone;
   }

   public final boolean isSecurityDupeip() {
      return this.securityDupeip;
   }

   public final boolean isSecurityStaffrollback() {
      return this.securityStaffrollback;
   }

   public final boolean isSecurityIphistory() {
      return this.securityIphistory;
   }

   public final boolean isSecurityIpban() {
      return this.securityIpban;
   }

   public final byte getSecurityIpbanCommand() {
      return this.securityIpbanCommand;
   }

   public final boolean isSecurityModify() {
      return this.securityModify;
   }

   public final boolean isSecurityPrunehistory() {
      return this.securityPrunehistory;
   }

   public final boolean isSecuritySenderCustom() {
      return this.securitySenderCustom;
   }

   public final boolean isAllowExemptBypass() {
      return this.allowExemptBypass;
   }

   public final boolean isOnlinePlayerSelectLimit() {
      return this.onlinePlayerSelectLimit;
   }

   public final boolean isDisableLoginBanCheck() {
      return this.disableLoginBanCheck;
   }

   public final boolean isDisableWildcardBans() {
      return this.disableWildcardBans;
   }

   public final boolean isDisableExpiryUpdate() {
      return this.disableExpiryUpdate;
   }

   public final boolean isCheckAfterJoin() {
      return this.checkAfterJoin;
   }

   public final boolean isFlag() {
      return this.flag;
   }

   public final boolean isGeoipEnable() {
      return this.geoipEnable;
   }

   public final String getGeoipDownloadDb() {
      return this.geoipDownloadDb;
   }

   public final String[] getGeoipBlacklist() {
      return this.geoipBlacklist;
   }

   public final String[] getGeoipWhitelist() {
      return this.geoipWhitelist;
   }

   public final String[] getGeoipDownloadApi() {
      return this.geoipDownloadApi;
   }

   public final List getIgnoredKickMessages() {
      return this.ignoredKickMessages;
   }

   public final List getDurationsLimits() {
      return this.durationsLimits;
   }

   public final Set getHiddenDupeipPlayers() {
      return this.hiddenDupeipPlayers;
   }

   public final Set getDisableLoginBanCheckServers() {
      return this.disableLoginBanCheckServers;
   }

   public final List getIgnoredIphistoryServers() {
      return this.ignoredIphistoryServers;
   }

   public final long getDatabaseTimeOffset() {
      return this.databaseTimeOffset;
   }

   public final long getSyncPollTicks() {
      return this.syncPollTicks;
   }

   public final int getSyncPollSkip() {
      return this.syncPollSkip;
   }

   public final int getDefaultHistoryLimit() {
      return this.defaultHistoryLimit;
   }

   public final byte getUsernameMaxLength() {
      return this.usernameMaxLength;
   }

   public final Pair getPair(boolean var1) {
      LiteBansCore var2 = (LiteBansCore)this.liteBansPlatform.getModule(LiteBansCore.class);
      String var3 = ArgUtils.getString(this.configFile.getString("server_name", "litebans"), 32);
      if (ServerScope.serverScopeParser.checkString(var3)) {
         var2.debug("[Sync] Server name cannot be \"" + var3 + "\" (global context)! Reverting to default.");
         var3 = "litebans";
      }

      String var4 = null;
      File var6 = this.liteBansPlatform.getDataFolder();
      String var7 = "server.yml";
      File var5 = new File(var6, var7);
      if (!var5.exists() || GenericConfigFile.getConfigFormat3() == null) {
         var6 = this.liteBansPlatform.getDataFolder();
         var7 = "server.json";
         var5 = new File(var6, var7);
      }

      try {
         boolean var14 = var1 || !var5.exists();
         ConfigFile var17 = this.liteBansPlatform.loadConfig(var5).getConfigFile4();
         String var8 = var17.getString("uuid", null);
         if (var8 != null && var8.length() == 32) {
            var4 = var8;
         } else {
            var4 = this.getString7();
            var14 = true;
         }

         if (var14) {
            var17.getConfigFile("uuid", var4);
            var17.getConfigFile2();
         } else {
            var2.setFlag2(false);
         }
      } catch (Exception var12) {
         var12.printStackTrace();
         var2.setFlag2(false);
      }

      String var10001 = var4;
      if (var4 == null) {
         var10001 = this.getString7();
      }

      Pair var15 = TuplesKt.getPair(var3, var10001);
      byte var18 = 10;
      if (var2.isDebugLevel(var18)) {
         var2.debug("Server UUID: \"" + (String)var15.getObject2() + '"');
      }

      return var15;
   }

   public static Pair getPairDefault(Config var0, boolean var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = false;
      }

      return var0.getPair(var1);
   }

   public final String getString7() {
      return UsernamePattern.mojangApi.getString(UUID.randomUUID());
   }
}
