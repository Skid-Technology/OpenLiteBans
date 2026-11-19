package litebans.core;

import java.io.Closeable;
import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.IllegalFormatException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;
import litebans.api.Database;
import litebans.api.Events;
import litebans.api.PlayerProvider;
import litebans.api.RandomID;
import litebans.api.exception.MissingImplementationException;
import litebans.api.impl.DatabaseImpl;
import litebans.api.impl.EventsImpl;
import litebans.api.impl.PlayerProviderImpl;
import litebans.api.impl.RandomIDImpl;
import litebans.command.AltsCommand;
import litebans.command.ArgUtils;
import litebans.command.CommandAlias;
import litebans.command.CommandRunnable;
import litebans.command.OperationCancelledException;
import litebans.config.ClassConfigLoader;
import litebans.config.Config;
import litebans.config.ConfigFile;
import litebans.config.ConfigSection;
import litebans.config.GenericConfigFile;
import litebans.config.Message;
import litebans.config.MessagesLoader;
import litebans.config.StoredTemplateData;
import litebans.config.Template;
import litebans.config.TemplateData;
import litebans.config.TemplateGroup;
import litebans.config.TemplateManager;
import litebans.config.YamlSupplier;
import litebans.database.DatabaseManager;
import litebans.database.DriverLibrary;
import litebans.database.EntryReader;
import litebans.database.PunishmentStorage;
import litebans.database.QueryRunner;
import litebans.database.SqlString;
import litebans.database.Table;
import litebans.geoip.GeoIp;
import litebans.geoip.GeoIpDownloader;
import litebans.hook.ServerListPlusBanProvider;
import litebans.importer.Importer;
import litebans.lib.compress.archivers.tar.TargetParser;
import litebans.lib.kotlin.KotlinVersion;
import litebans.lib.kotlin.Lazy;
import litebans.lib.kotlin.LazyKt;
import litebans.lib.kotlin.NoWhenBranchMatchedException;
import litebans.lib.kotlin.Pair;
import litebans.lib.kotlin.TuplesKt;
import litebans.lib.kotlin.UByte;
import litebans.lib.kotlin.UninitializedPropertyAccessException;
import litebans.lib.kotlin.collections.ArraysKt;
import litebans.lib.kotlin.collections.CollectionsKt;
import litebans.lib.kotlin.collections.MapsKt;
import litebans.lib.kotlin.io.CloseableKt;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.ranges.RangesKt;
import litebans.lib.kotlin.text.StringsKt;
import litebans.platform.ClassLoadDiagnostics;
import litebans.platform.LiteBansPlatform;
import litebans.platform.LiteCommand;
import litebans.platform.PlatformBridge;
import litebans.platform.PlatformDetector;
import litebans.platform.Sender;
import litebans.platform.bukkit.BukkitBridge;
import litebans.platform.bukkit.BukkitOnlinePlayers;
import litebans.platform.bukkit.VaultHook;
import litebans.punishment.DupeIpLookup;
import litebans.punishment.DurationParser;
import litebans.punishment.DurationUnit;
import litebans.punishment.LocaleLanguageFunction;
import litebans.punishment.LockdownTask;
import litebans.punishment.PlayerHistory;
import litebans.punishment.PunishmentEntry;
import litebans.punishment.PunishmentType;
import litebans.punishment.ServerScope;
import litebans.randomid.BitCounter;
import litebans.randomid.LongBitSet;
import litebans.randomid.Shuffler;
import litebans.update.BuildInfo;
import litebans.update.ChecksumMismatchException;
import litebans.update.Updater;
import litebans.util.FileUtils;
import litebans.util.LogTask;
import litebans.util.SoftLazy;
import litebans.util.ToggleState;
import litebans.webhook.WebhookManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class LiteBansCore extends PluginModule {
   public static final Updater updater = new Updater(null);
   private byte debugLevel;
   private final byte byteValue2;
   private byte byteValue3;
   private final ThreadLocal threadLocal = new ThreadLocalHolder(this);
   public Config config;
   public MessagesLoader messagesLoader;
   public TemplateManager templateManager;
   public WebhookManager webhookManager;
   private final SoftLazy softLazy = new SoftLazy(() -> createShuffler(this));
   private final Lazy lazy = LazyKt.getLazy(() -> hasAuthMe(this));
   private Object serverListPlusHook;
   private GeoIp geoIp;
   private boolean flag;
   private boolean flag2 = true;
   private int intValue;
   private final ToggleState toggleState = new ToggleState(false, false, 3, null);
   private CommandAlias commandAlias;
   private long longValue = Long.MAX_VALUE;
   private byte byteValue4;
   private String string;
   private LongBitSet longBitSet = new BitCounter();
   public static final int intValue2 = 8;
   public static final String string2 = "geoip";
   public static final int intValue3 = 40000;
   private static int intValue4;
   private static final AtomicBoolean atomicBoolean = new AtomicBoolean();
   private static final AtomicBoolean atomicBoolean2 = new AtomicBoolean();

   public LiteBansCore(@NotNull LiteBansPlatform var1) {
      super(var1);
      Integer[] var2 = new Integer[]{29, 5, 7, 1, 5};
      var2 = var2;
      int var3 = 0;
      int var4 = 0;

      for (int var5 = var2.length; var4 < var5; var4++) {
         Integer var6 = var2[var4];
         int var7 = var6.intValue();
         int var11 = this.byteValue4 ^ var7;
         var3 += var11;
      }

      this.byteValue2 = (byte)var3;
   }

   public final byte getDebugLevel() {
      return this.debugLevel;
   }

   public final void setDebugLevel(byte var1) {
      this.debugLevel = var1;
   }

   public final ThreadLocal getThreadLocal() {
      return this.threadLocal;
   }

   public final Config getConfig() {
      if (this.config != null) {
         return this.config;
      } else {
         Intrinsics.handleString3("");
         return null;
      }
   }

   public final void setConfig(@NotNull Config var1) {
      this.config = var1;
   }

   public final MessagesLoader getMessagesLoader() {
      if (this.messagesLoader != null) {
         return this.messagesLoader;
      } else {
         Intrinsics.handleString3("");
         return null;
      }
   }

   public final void setMessagesLoader(@NotNull MessagesLoader var1) {
      this.messagesLoader = var1;
   }

   public final TemplateManager getTemplateManager() {
      if (this.templateManager != null) {
         return this.templateManager;
      } else {
         Intrinsics.handleString3("");
         return null;
      }
   }

   public final void setTemplateManager(@NotNull TemplateManager var1) {
      this.templateManager = var1;
   }

   public final WebhookManager getWebhookManager() {
      if (this.webhookManager != null) {
         return this.webhookManager;
      } else {
         Intrinsics.handleString3("");
         return null;
      }
   }

   public final void setWebhookManager(@NotNull WebhookManager var1) {
      this.webhookManager = var1;
   }

   public final Shuffler getShuffler() {
      Lazy var1 = this.softLazy;
      return (Shuffler)var1.getValue();
   }

   public final boolean check2() {
      Lazy var1 = this.lazy;
      return (Boolean)var1.getValue();
   }

   public final Object getServerListPlusHook() {
      return this.serverListPlusHook;
   }

   public final GeoIp getGeoIp() {
      return this.geoIp;
   }

   public final void setGeoIp(@Nullable GeoIp var1) {
      this.geoIp = var1;
   }

   public final boolean isFlag2() {
      return this.flag2;
   }

   public final void setFlag2(boolean var1) {
      this.flag2 = var1;
   }

   public final int getIntValue() {
      return this.intValue;
   }

   public final void setIntValue(int var1) {
      this.intValue = var1;
   }

   public final ToggleState getToggleState() {
      return this.toggleState;
   }

   public final CommandAlias getCommandAlias() {
      return this.commandAlias;
   }

   public final void setCommandAlias(@Nullable CommandAlias var1) {
      this.commandAlias = var1;
   }

   public final long getLongValue() {
      return this.longValue;
   }

   public final void setLongValue(long var1) {
      this.longValue = var1;
   }

   public final byte getByteValue4() {
      return this.byteValue4;
   }

   public final void setByteValue4(byte var1) {
      this.byteValue4 = var1;
   }

   public final String getString() {
      return this.string;
   }

   public final void setString(@Nullable String var1) {
      this.string = var1;
   }

   public final LongBitSet getLongBitSet() {
      return this.longBitSet;
   }

   public final void setLongBitSet(@NotNull LongBitSet var1) {
      this.longBitSet = var1;
   }

   public final boolean isDebugLevel(int var1) {
      return this.debugLevel >= var1;
   }

   public final void debug(int var1, @Nullable Object var2) {
      if (this.isDebugLevel(var1)) {
         this.debug(var2);
      }
   }

   public final void debug(@Nullable Object var1) {
      if (var1 instanceof Throwable) {
         ((Throwable)var1).printStackTrace();
      } else {
         PluginModule var2 = this;
         String var3 = String.valueOf(var1);
         var2.platform.getLogger().info(var3);
      }
   }

   public final boolean isDebugEnabled() {
      return this.debugLevel == this.byteValue2;
   }

   public final int getInt(@NotNull CharSequence[] var1) {
      if (!this.isDebugEnabled()) {
         return -1;
      } else {
         CharSequence[] var3 = var1;
         int var4 = 0;

         for (int var5 = var1.length; var4 < var5; var4++) {
            CharSequence var6 = var3[var4];
            this.debug(var6);
         }

         return KotlinVersion.getInt2(var1.length);
      }
   }

   public final void handleObject(@Nullable Object var1, int var2) {
      if (this.isDebugEnabled()) {
         if (var1 instanceof Throwable) {
            PluginModule var3 = this;
            String var4 = "0xAAA";
            var3.platform.getLogger().warning(var4);
            ((Throwable)var1).printStackTrace();
         } else {
            PluginModule var13 = this;
            String var14 = "0xAAA " + var1;
            var13.platform.getLogger().warning(var14);
            Thread.dumpStack();
         }
      } else if (var2 == 1 && var1 instanceof Throwable) {
         CharSequence var15 = var1.getClass().getName();
         int var6 = 0;

         for (int var7 = 0; var7 < var15.length(); var7++) {
            char var8 = var15.charAt(var7);
            int var12 = var8 ^ 23;
            var6 += var12;
         }

         if (var6 == 3140) {
            LongBitSet var16 = new LongBitSet(0L, 1, null);
            this.byteValue4 = (byte)(var16.getInt(2) + 1);
            this.longValue = (long)var16.getInt(23) + 1L;
         }
      }
   }

   public static void handleObjectDefault(LiteBansCore var0, Object var1, int var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = 0;
      }

      var0.handleObject(var1, var2);
   }

   public final String getCountry(@Nullable String var1) {
      if (this.geoIp != null) {
         String var10000 = this.geoIp.getString(var1);
         if (var10000 != null) {
            return var10000;
         }
      }

      return "geoip.unavailable";
   }

   @Override
   public void onDisable() {
      this.byteValue3 = 0;
      this.platform.cancelScheduledTasks();
      atomicBoolean2.set(false);
      LiteBansCore var1 = this;
      if (this.getConfig().isLockdownPersistEnabled()) {
         try {
            NotificationManager var3 = (NotificationManager)var1.platform.getModule(NotificationManager.class);
            File var4 = var1.platform.getDataFolder();
            String var5 = "lockdown.yml";
            File var7 = new File(var4, var5);
            if (!var7.exists() || GenericConfigFile.getConfigFormat3() == null) {
               var4 = var1.platform.getDataFolder();
               var5 = "lockdown.json";
               var7 = new File(var4, var5);
            }

            LockdownTask var10000 = var3.getLockdownTask();
            if ((var10000 != null ? var10000.getCharSequence() : null) == null) {
               FileUtils.getFile2(var7);
            } else {
               ConfigFile var10 = var1.platform.loadConfig(var7).getConfigFile4();
               LockdownTask var10002 = var3.getLockdownTask();
               ConfigFile var12 = var10.getConfigFile("reason", var10002 != null ? var10002.getCharSequence() : null);
               var10002 = var3.getLockdownTask();
               var12.getConfigFile("scope", var10002 != null ? var10002.getString() : null).getConfigFile2();
            }
         } catch (Exception var8) {
            var8.printStackTrace();
         }
      }
   }

   public final void handleByte(byte var1) {
      try {
         this.getVoid(var1, false);
         throw new NoWhenBranchMatchedException();
      } catch (Exception var5) {
         PluginModule var3 = this;
         String var10000 = var5.getMessage();
         if (var10000 == null) {
            var10000 = "...";
         }

         String var4 = var10000;
         var3.platform.getLogger().warning(var4);
      }
   }

   public final Void getVoid(byte var1, boolean var2) {
      if (var2 && this.isDebugEnabled()) {
         this.debug("" + '!' + var1 + '!');
      }

      throw new RuntimeException("Error 0x" + var1);
   }

   @Override
   public void onLoad() {
      PluginInstance.Companion.handleBoolean(true);
      PluginInstance.Companion.handleLiteBansPlatform(this.platform);
      if (this.byteValue3 < 1) {
         this.byteValue3 = 1;
         this.loadLibraries();

         try {
            this.platform.saveDefaultResource("config.yml");
         } catch (Exception var6) {
            this.logException(var6);
         }

         this.platform.loadMainConfig();
         Logger var3 = this.platform.getLogger();
         Throwable var10000 = this.platform.getMainConfig().getThrowable();
         if (var10000 != null) {
            Throwable var5 = var10000;
            this.reportConfigError("config", var5);
            if (this.platform.getMainConfig().getFile().exists()) {
               var3.severe("[!!] Your configuration might be invalid. It can be verified with https://yaml-online-parser.appspot.com/");
            } else {
               var3.severe(
                  "[!!] Configuration does not exist! Ensure that read/write permissions are correct. (\""
                     + this.platform.getMainConfig().getFile().getAbsolutePath()
                     + "\")"
               );
            }

            if (this.isDebugLevel(2)) {
               this.logException(var5);
            }
         }

         Config var1 = new Config(this.platform, null, 2, null);
         this.setConfig(var1);
      }
   }

   private final void loadLibraries() {
      if (GenericConfigFile.getConfigFormat3() == null) {
         DatabaseManager var1 = (DatabaseManager)this.platform.getModule(DatabaseManager.class);
         DriverLibrary var2 = new DriverLibrary(
            "SnakeYAML",
            YamlSupplier.class.getName(),
            "https://repo1.maven.org/maven2/org/yaml/snakeyaml/2.4/snakeyaml-2.4.jar",
            "2.4",
            "ef779af5d29a9dde8cc70ce0341f5c6f7735e23edff9685ceaa9d35359b7bb7f"
         );
         Object var10000 = DatabaseManager.loadDriverDefault(var1, var2, false, false, 2, null);
         Intrinsics.handleObject(var10000, "");
         Class var3 = (Class)var10000;
         this.platform.setConfigLoader(new ClassConfigLoader(this, var3));
      }
   }

   @Override
   public void onEnable() {
      Object var213;
      LiteBansPlatform var1 = this.platform;
      NotificationManager var2 = (NotificationManager)var1.getModule(NotificationManager.class);
      if (this.byteValue3 < 2) {
         this.onLoad();
         this.byteValue3 = 2;
         var1.execute(() -> loadMessages(this, var1));
         var2.handleString(this.getConfig().getSqlDriver());
         Intrinsics.handleObject(var1);
         TemplateManager var3 = new TemplateManager(var1, null, false, 6, null);

         TemplateManager var10000;
         label520: {
            try {
               var3.getLiteBansPlatform().saveDefaultResource("templates.yml");
               ConfigFile var8 = var3.getLiteBansPlatform().loadConfig(var3.getFile()).getConfigFile4();
               var8.getConfigFile(ConfigFile.getString2("templates.yml", null));
               var3.setConfigFile(var8);
               var3.getArrayList().clear();
               TemplateManager var9 = var3;
               ConfigFile var207 = var3.getConfigFile();
               Intrinsics.handleObject(var207);
               ConfigFile var11 = var207;
               Iterable var12 = PunishmentType.Companion.getList();
               Collection var15 = new ArrayList(CollectionsKt.getInt(var12, 10));

               for (Object var18 : var12) {
                  PunishmentType var19 = (PunishmentType)var18;
                  var15.add(TuplesKt.getPair(var19, var11.getConfigSection(var19 + "-templates")));
               }

               for (Object var13o : (List) var15) {
                  Pair var13 = (Pair) var13o;
                  byte var14 = 0;
                  ConfigSection var102 = (ConfigSection)var13.getObject2();
                  if (var102 != null) {
                     Collection var16 = var102.getCollection(false);
                     Collection var103 = var9.getArrayList();
                     Intrinsics.handleObject(var16);
                     Iterable var105 = var16;
                     Collection var23 = new ArrayList(CollectionsKt.getInt(var105, 10));

                     for (Object var26 : var105) {
                        String var27 = (String)var26;
                        TemplateManager var29 = var9;
                        byte var30 = var14;
                        var14 = UByte.getByte((byte)(var14 + 1));
                        PunishmentType var31 = (PunishmentType)var13.getObject();
                        Intrinsics.handleObject(var13.getObject2());
                        ConfigSection var32 = var102.getConfigSection(var27);
                        Intrinsics.handleObject(var27);
                        if (var32 == null) {
                           String var131 = "Empty template: '" + var27 + '\'';
                           throw new IllegalArgumentException(var131.toString());
                        }

                        if (StringsKt.checkCharSequence3(var27, ' ', false, 2, null) || StringsKt.checkCharSequence3(var27, '.', false, 2, null)) {
                           String var130 = "Template name '" + var27 + "' cannot include dots or spaces! Use dashes or underscores instead.";
                           throw new IllegalArgumentException(var130.toString());
                        }

                        String var35 = "";
                        if (var32 == null) {
                           String var135 = var31 + " template '" + var35 + "' has invalid section.";
                           throw new IllegalArgumentException(var135.toString());
                        }

                        Collection var38 = var32.getCollection(false);
                        Long var37 = null;
                        String var39 = null;
                        String var40 = null;
                        String var41 = null;
                        String var42 = null;
                        List var43 = CollectionsKt.getList();
                        List var44 = CollectionsKt.getList();
                        Intrinsics.handleObject(var38);

                        for (Object var48 : var38) {
                           String var49 = (String)var48;
                           if (var49 != null) {
                              switch (var49.hashCode()) {
                                 case -1992012396:
                                    if (var49.equals("duration")) {
                                       var37 = (long)DurationParser.getDoubleDefault(DurationUnit.durationParser, var32.getString(var49), 0.0, 2, null);
                                    }
                                    break;
                                 case -1618876223:
                                    if (var49.equals("broadcast")) {
                                       var41 = var32.getString(var49);
                                    }
                                    break;
                                 case -1161803523:
                                    if (var49.equals("actions")) {
                                       var43 = var29.getList(var32, var49);
                                    }
                                    break;
                                 case -934964668:
                                    if (var49.equals("reason")) {
                                       var40 = var32.getString(var49);
                                    }
                                    break;
                                 case -517618225:
                                    if (var49.equals("permission")) {
                                       var42 = var32.getString(var49);
                                    }
                                    break;
                                 case 97513095:
                                    if (var49.equals("flags")) {
                                       var44 = var29.getList(var32, var49);
                                    }
                                    break;
                                 case 954925063:
                                    if (var49.equals("message")) {
                                       var39 = var32.getString(var49);
                                    }
                              }
                           }
                        }

                        TemplateData var52 = CoreWhenMappings.switchMap[var31.ordinal()] == 1
                           ? new TemplateData(var39, var40, var41, var42, var43, var44)
                           : new StoredTemplateData(var37, var39, var40, var41, var42, var43, var44);
                        TemplateManager var36 = var29;
                        ConfigSection var209 = var32.getConfigSection("ladder");
                        List var210;
                        if (var209 == null) {
                           var210 = CollectionsKt.getList();
                        } else {
                           ConfigSection var143 = var209;
                           Iterable var146 = var143.getCollection(false);
                           Collection var162 = new ArrayList(CollectionsKt.getInt(var146, 10));

                           for (Object var177 : var146) {
                              String var46 = (String)var177;
                              var162.add(var143.getConfigSection(var46));
                           }

                           Iterable var147 = (List)var162;
                           Collection var163 = new ArrayList(CollectionsKt.getInt(var147, 10));

                           for (Object var178 : var147) {
                              ConfigSection var183 = (ConfigSection)var178;
                              TemplateManager var193 = var36;
                              if (var183 == null) {
                                 String var195 = var31 + " template '" + var27 + "' has invalid section.";
                                 throw new IllegalArgumentException(var195.toString());
                              }

                              Collection var53 = var183.getCollection(false);
                              Long var51 = null;
                              String var54 = null;
                              String var55 = null;
                              String var56 = null;
                              String var57 = null;
                              List var58 = CollectionsKt.getList();
                              List var59 = CollectionsKt.getList();
                              Intrinsics.handleObject(var53);

                              for (Object var63 : var53) {
                                 String var64 = (String)var63;
                                 if (var64 != null) {
                                    switch (var64.hashCode()) {
                                       case -1992012396:
                                          if (var64.equals("duration")) {
                                             var51 = (long)DurationParser.getDoubleDefault(DurationUnit.durationParser, var183.getString(var64), 0.0, 2, null);
                                          }
                                          break;
                                       case -1618876223:
                                          if (var64.equals("broadcast")) {
                                             var56 = var183.getString(var64);
                                          }
                                          break;
                                       case -1161803523:
                                          if (var64.equals("actions")) {
                                             var58 = var193.getList(var183, var64);
                                          }
                                          break;
                                       case -934964668:
                                          if (var64.equals("reason")) {
                                             var55 = var183.getString(var64);
                                          }
                                          break;
                                       case -517618225:
                                          if (var64.equals("permission")) {
                                             var57 = var183.getString(var64);
                                          }
                                          break;
                                       case 97513095:
                                          if (var64.equals("flags")) {
                                             var59 = var193.getList(var183, var64);
                                          }
                                          break;
                                       case 954925063:
                                          if (var64.equals("message")) {
                                             var54 = var183.getString(var64);
                                          }
                                    }
                                 }
                              }

                              var163.add(
                                 CoreWhenMappings.switchMap[var31.ordinal()] == 1
                                    ? new TemplateData(var54, var55, var56, var57, var58, var59)
                                    : new StoredTemplateData(var51, var54, var55, var56, var57, var58, var59)
                              );
                           }

                           var210 = CollectionsKt.getList((List)var163);
                        }

                        List var129 = var210;
                        long var67 = (long)DurationParser.getDoubleDefault(DurationUnit.durationParser, var32.getString("expire_ladder", "0"), 0.0, 2, null);
                        boolean var144 = var32.checkString("ip_template", false);
                        var23.add(new Template(var30, var27, var31, var52, var144, var129, var67, null));
                     }

                     var105 = (List)var23;
                     CollectionsKt.checkCollection(var103, var105);
                  }
               }

               var9.getLiteBansPlatform().getLogger().info("Loaded " + var9.getArrayList().size() + " templates from templates.yml!");
               ConfigSection var99 = var11.getConfigSection("template-groups");
               if (var99 != null) {
                  Collection var100 = var99.getCollection(false);
                  Collection var10001 = var9.getList();
                  Intrinsics.handleObject(var100);
                  Iterable var101 = var100;
                  Collection var69 = var10001;
                  Collection var104 = new ArrayList(CollectionsKt.getInt(var101, 10));

                  for (Object var21 : var101) {
                     String var108 = (String)var21;
                     TemplateManager var109 = var9;
                     ConfigSection var110 = var99.getConfigSection(var108);
                     Intrinsics.handleObject(var108);
                     String var111 = var108;
                     ConfigSection var112 = var110 != null ? var110.getConfigSection("weights") : null;
                     if (var110 == null) {
                        String var116 = "Empty template group: '" + var108 + '\'';
                        throw new IllegalArgumentException(var116.toString());
                     }

                     if (var112 == null) {
                        String var115 = "No weights for template group '" + var108 + "'!";
                        throw new IllegalArgumentException(var115.toString());
                     }

                     PunishmentType[] var113 = PunishmentType.values();
                     int var119 = 0;

                     label450: {
                        for (int var33 = var113.length; var119 < var33; var119++) {
                           PunishmentType var34 = var113[var119];
                           String var211 = var110.getString("type", "");
                           String var212;
                           if (var211 != null) {
                              String var136 = var211;
                              var212 = var136.toLowerCase(Locale.ROOT);
                           } else {
                              var212 = null;
                           }

                           if (Intrinsics.checkObject(var212, var34.toString())) {
                              var213 = var34;
                              break label450;
                           }
                        }

                        var213 = null;
                     }

                     PunishmentType var145 = /* cast-fix */(PunishmentType)(var213);
                     if (var145 == null || var145 != var145.getPunishmentType()) {
                        String var118 = "Template group '" + var108 + "' type is missing: " + var145;
                        throw new IllegalArgumentException(var118.toString());
                     }

                     TemplateManager var117 = var9;
                     ConfigSection var214 = var110.getConfigSection("ladder");
                     List var215;
                     if (var214 == null) {
                        var215 = CollectionsKt.getList();
                     } else {
                        ConfigSection var120 = var214;
                        Iterable var122 = var120.getCollection(false);
                        Collection var137 = new ArrayList(CollectionsKt.getInt(var122, 10));

                        for (Object var156 : var122) {
                           String var164 = (String)var156;
                           var137.add(var120.getConfigSection(var164));
                        }

                        var122 = (List)var137;
                        var137 = new ArrayList(CollectionsKt.getInt(var122, 10));

                        for (Object var157 : var122) {
                           ConfigSection var165 = (ConfigSection)var157;
                           TemplateManager var179 = var117;
                           if (var165 == null) {
                              String var186 = var145 + " template '" + var108 + "' has invalid section.";
                              throw new IllegalArgumentException(var186.toString());
                           }

                           Collection var189 = var165.getCollection(false);
                           Long var185 = null;
                           String var194 = null;
                           String var50 = null;
                           String var196 = null;
                           String var197 = null;
                           List var199 = CollectionsKt.getList();
                           List var200 = CollectionsKt.getList();
                           Intrinsics.handleObject(var189);

                           for (Object var203 : var189) {
                              String var204 = (String)var203;
                              if (var204 != null) {
                                 switch (var204.hashCode()) {
                                    case -1992012396:
                                       if (var204.equals("duration")) {
                                          var185 = (long)DurationParser.getDoubleDefault(DurationUnit.durationParser, var165.getString(var204), 0.0, 2, null);
                                       }
                                       break;
                                    case -1618876223:
                                       if (var204.equals("broadcast")) {
                                          var196 = var165.getString(var204);
                                       }
                                       break;
                                    case -1161803523:
                                       if (var204.equals("actions")) {
                                          var199 = var179.getList(var165, var204);
                                       }
                                       break;
                                    case -934964668:
                                       if (var204.equals("reason")) {
                                          var50 = var165.getString(var204);
                                       }
                                       break;
                                    case -517618225:
                                       if (var204.equals("permission")) {
                                          var197 = var165.getString(var204);
                                       }
                                       break;
                                    case 97513095:
                                       if (var204.equals("flags")) {
                                          var200 = var179.getList(var165, var204);
                                       }
                                       break;
                                    case 954925063:
                                       if (var204.equals("message")) {
                                          var194 = var165.getString(var204);
                                       }
                                 }
                              }
                           }

                           var137.add(
                              CoreWhenMappings.switchMap[var145.ordinal()] == 1
                                 ? new TemplateData(var194, var50, var196, var197, var199, var200)
                                 : new StoredTemplateData(var185, var194, var50, var196, var197, var199, var200)
                           );
                        }

                        var215 = CollectionsKt.getList((List)var137);
                     }

                     List var114 = var215;
                     long var71 = (long)DurationParser.getDoubleDefault(DurationUnit.durationParser, var110.getString("expire_ladder", "0"), 0.0, 2, null);
                     if (var114.isEmpty()) {
                        String var128 = "No ladder for template group '" + var108 + "'!";
                        throw new IllegalArgumentException(var128.toString());
                     }

                     Iterable var124 = var112.getCollection(false);
                     LinkedHashMap var133 = new LinkedHashMap(RangesKt.getInt(MapsKt.getInt(CollectionsKt.getInt(var124, 10)), 16));

                     for (Object var158 : var124) {
                        Map var216 = var133;
                        String var166 = (String)var158;
                        Map var65 = var216;
                        Float var66 = (float)var112.getDouble(var166);
                        var65.put(var158, var66);
                     }

                     Map var125 = var133;
                     Map var139 = new LinkedHashMap(MapsKt.getInt(var125.size()));

                     for (Object var169 : var125.entrySet()) {
                        Entry var174 = (Entry)var169;
                        Template var184 = var109.getTemplate(var145, (String)var174.getKey());
                        if (var184 == null) {
                           String var187 = "Template group '" + var111 + "' refers to non-existing " + var145 + " template: '" + (String)var174.getKey() + '\'';
                           throw new IllegalArgumentException(var187.toString());
                        }

                        if (!var184.getLadder().isEmpty()) {
                           var109.getLiteBansPlatform()
                              .getLogger()
                              .warning(
                                 "Template '"
                                    + (String)var174.getKey()
                                    + "' belongs to template group '"
                                    + var111
                                    + "', its ladder will be ignored. Remove the ladder from '"
                                    + (String)var174.getKey()
                                    + "' to silence this warning."
                              );
                        }

                        Entry var190 = (Entry)var169;
                        Object var198 = var190.getValue();
                        var139.put(var184, var198);
                     }

                     if (var139.isEmpty()) {
                        String var132 = "No weights for template group '" + var111 + "'!";
                        throw new IllegalArgumentException(var132.toString());
                     }

                     var124 = var139.values();
                     Collection var140 = new ArrayList();

                     for (Object var159 : var124) {
                        float var168 = ((Number)var159).floatValue();
                        if ((double)var168 <= 0.0) {
                           var140.add(var159);
                        }
                     }

                     for (Object var141 : (List)var140) {
                        float var148 = ((Number)var141).floatValue();
                        var109.getLiteBansPlatform().getLogger().warning("Template group '" + var111 + "' includes a bad weight: " + var148);
                     }

                     Map var149 = new LinkedHashMap(MapsKt.getInt(var139.size()));

                     for (Object var175 : var139.entrySet()) {
                        Entry var180 = (Entry)var175;
                        var10001 = (Collection)var180.getKey();
                        Entry var191 = (Entry)var175;
                        Object var205 = var10001;
                        Template var206 = (Template)var191.getKey();
                        var149.put(var205, var206);
                     }

                     var149 = new LinkedHashMap(MapsKt.getInt(var149.size()));

                     for (Object var176 : var149.entrySet()) {
                        Entry var181 = (Entry)var176;
                        UByte var218 = UByte.getUByte(((Template)var181.getKey()).getId());
                        Entry var188 = (Entry)var176;
                        UByte var192 = var218;
                        var181 = (Entry)var188.getValue();
                        var149.put(var192, var181);
                     }

                     var104.add(new TemplateGroup(var111, var145, var139, var149, var114, var71));
                  }

                  List var70 = (List)var104;
                  var9.setList(CollectionsKt.getList(var69, var70));
               }

               if (!var9.getList().isEmpty()) {
                  var9.getLiteBansPlatform().getLogger().info("Loaded " + var9.getList().size() + " template groups from templates.yml!");
               } else {
                  var9.setList(CollectionsKt.getList());
               }
            } catch (Exception var77) {
               var3.handleException(var77);
               var10000 = var3;
               break label520;
            }

            var10000 = var3;
         }

         TemplateManager var74 = var10000;
         this.setTemplateManager(var74);
         this.setWebhookManager(new WebhookManager(var1, null, 2, null).getWebhookManager());
         if (var1.isProxy()) {
            LiteBansCore var78 = this;

            try {
               Object var5 = var78.createServerListPlusHook();
               if (var5 != null) {
                  ServerListPlusBanProvider var82 = new ServerListPlusBanProvider(var78.platform);
                  var78.serverListPlusHook = var82;
                  if (!PlatformDetector.isFlag3()) {
                     var82.handleObject(var5);
                  }
               }
            } catch (Throwable var76) {
               PluginModule var6 = this;
               CharSequence var7 = ArgUtils.getCharSequence("Failed to hook into ServerListPlus.", "Try using the latest ServerListPlus development build.");
               Logger var95 = var6.platform.getLogger();
               var95.warning(var7.toString());
               if (this.isDebugLevel(1)) {
                  var76.printStackTrace();
               }
            }
         } else {
            var1.runSync(() -> hookServerListPlus(this));
         }

         Config var79 = this.getConfig();
         if (var79.isGeoipEnable()) {
            File var83 = this.platform.getDataFolder();
            String var88 = "geoip";
            File var96 = FileUtils.getFile3(new File(var83, var88));
            this.platform.execute(new GeoIpDownloader(this, var96, var79));
         }

         try {
            this.flag = true;
            NotificationManager var80 = (NotificationManager)this.platform.getModule(NotificationManager.class);
            File var84 = this.platform.getDataFolder();
            String var89 = "lockdown.yml";
            File var97 = new File(var84, var89);
            if (!var97.exists() || GenericConfigFile.getConfigFormat3() == null) {
               var84 = this.platform.getDataFolder();
               var89 = "lockdown.json";
               var97 = new File(var84, var89);
            }

            if (var97.exists()) {
               ConfigFile var86 = this.platform.loadConfig(var97).getConfigFile4();
               var89 = var86.getString("scope", "*");
               String var93 = var86.getString("reason", "");
               if (this.getConfig().isLockdownPersistEnabled()) {
                  Intrinsics.handleObject(var93);
                  if (var93.length() > 0) {
                     this.platform.getLogger().info("Applying saved lockdown, reason: \"" + var93 + '"');
                     Intrinsics.handleObject(var89);
                     var80.startLockdown(var89, var93, true);
                  }
               }
            }

            this.registerApi();
            String var81 = BuildInfo.getString2();
            String var87 = this.platform.getVersion();
            if (!Intrinsics.checkObject(var81, var87)) {
               var89 = "This is LiteBans version " + var81 + ", NOT version " + var87 + '!';
               Runnable var94 = new LogTask(this, var89);
               var94.run();
               this.platform.runAsyncLater(var94, 20L);
            }

            if (this.isDebugEnabled()) {
               var1.runAsyncLater(() -> runSelfTest(var1, this), 140L);
            }

            if (var1.getPlatformId() == 0) {
               var1.runAsyncLater(() -> runDatabaseSelfTest(var1, this), 300L);
            }
         } catch (Throwable var75) {
            this.logException(var75);
         }
      }
   }

   private final void registerApi() {
      Database var1;
      Events var2;
      PlayerProvider var3;
      RandomID var4;
      try {
         Database var10000 = Database.get();
         Intrinsics.handleObject(var10000, "");
         var1 = (DatabaseImpl)var10000;
         Events var6 = Events.get();
         Intrinsics.handleObject(var6, "");
         var2 = (EventsImpl)var6;
         var3 = PlayerProvider.get();
         var4 = RandomID.get();
      } catch (MissingImplementationException var5) {
         var1 = new DatabaseImpl(this.platform);
         var2 = new EventsImpl();
         var3 = new PlayerProviderImpl(this.platform);
         var4 = new RandomIDImpl(this.platform);
      }

      Database.setInstance(var1);
      Events.setInstance(var2);
      PlayerProvider.setInstance(var3);
      RandomID.setInstance(var4);
   }

   @Override
   public void onReload() {
      this.platform.saveDefaultResource("config.yml");
      this.platform.getMainConfig().getConfigFile();
      this.byteValue3 = 0;
      this.onEnable();
   }

   public final void logException(@NotNull Throwable var1) {
      if (var1 instanceof ChecksumMismatchException) {
         PluginModule var4 = this;
         String var10000 = var1.getMessage();
         Intrinsics.handleObject(var10000);
         String var3 = var10000;
         var4.platform.getLogger().severe(var3);
      } else {
         StringWriter var2 = new StringWriter();
         var1.printStackTrace(new PrintWriter(var2));
         this.logStackTrace(var2.toString());
      }
   }

   public final void logStackTrace(@NotNull String var1) {
      CharSequence var10000 = var1;
      String[] var3 = new String[]{System.lineSeparator()};
      List var2 = StringsKt.getList(var10000, var3, false, 0, 6, null);
      LiteBansCore var14 = (LiteBansCore)this.platform.getModule(LiteBansCore.class);
      if (var2.size() > 2 && (var14 == null || !var14.isDebugEnabled())) {
         Iterable var4 = var2;
         Collection var7 = new ArrayList(CollectionsKt.getInt(var4, 10));

         for (Object var10 : var4) {
            String var11 = (String)var10;
            var7.add(this.trimStackTrace(var11));
         }

         var2 = (List)var7;
      }

      Iterable var15 = var2;
      Collection var17 = new ArrayList();

      for (Object var20 : var15) {
         String var22 = (String)var20;
         if (var22.length() > 0) {
            var17.add(var20);
         }
      }

      for (Object var18 : (List)var17) {
         String var8 = (String)var18;
         PluginModule var21 = this;
         var21.platform.getLogger().warning(var8);
      }
   }

   public final String trimStackTrace(@NotNull String var1) {
      CharSequence var5 = var1;
      int var7 = 0;
      int var8 = var5.length() - 1;
      boolean var9 = false;

      while (var7 <= var8) {
         int var10 = !var9 ? var7 : var8;
         char var11 = var5.charAt(var10);
         boolean var12 = Intrinsics.getInt(var11, 32) <= 0;
         if (!var9) {
            if (!var12) {
               var9 = true;
            } else {
               var7++;
            }
         } else {
            if (!var12) {
               break;
            }

            var8--;
         }
      }

      String var2 = var5.subSequence(var7, var8 + 1).toString();
      return !StringsKt.checkString(var2, "at java.lang.Thread.run(", false, 2, null)
            && !StringsKt.checkString(var2, "at java.util.concurrent.ThreadPoolExecutor$Worker.run(", false, 2, null)
            && !StringsKt.checkString(var2, "at java.util.concurrent.ThreadPoolExecutor.runWorker(", false, 2, null)
            && !StringsKt.checkString(var2, DatabaseManager.databaseDriver.getString(), false, 2, null)
            && !StringsKt.checkString(var2, DatabaseManager.databaseDriver.getString2(), false, 2, null)
            && !StringsKt.checkString(var2, DatabaseManager.databaseDriver.getString3(), false, 2, null)
            && !StringsKt.checkString(var2, "at sun.reflect.NativeConstructorAccessorImpl.newInstance", false, 2, null)
            && !StringsKt.checkString(var2, "at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(", false, 2, null)
            && !StringsKt.checkString(var2, "at java.lang.reflect.Constructor.newInstance(", false, 2, null)
         ? var1
         : "";
   }

   public final void reportConfigError(@NotNull String var1, @NotNull Throwable var2) {
      PluginModule var3 = this;
      String var4 = "[!!] Could not load " + var1 + ".yml.";
      var3.platform.getLogger().severe(var4);
      String var12 = "org.bukkit.configuration.InvalidConfigurationException: ";
      String[] var5 = new String[]{
         var12 + "while scanning an anchor",
         var12 + "while scanning a simple key",
         var12 + "while scanning for the next token",
         var12 + "while parsing a block mapping",
         var12 + "while parsing a block collection",
         var12
      };
      String var10000 = var2.getMessage();
      if (var10000 == null) {
         var10000 = "";
      }

      String var14 = var10000;
      String[] var6 = var5;
      int var8 = 0;

      for (int var9 = var5.length; var8 < var9; var8++) {
         String var10 = var6[var8];
         var14 = StringsKt.getString(var14, var10, "", false, 4, null);
      }

      String var15 = StringsKt.getString(var14, "\\t(TAB)", "TAB", false, 4, null);
      PluginModule var16 = this;
      String var7 = "[!!] Configuration error found: " + var15;
      var16.platform.getLogger().severe(var7);
      PluginModule var17 = this;
      var7 = "[!!] LiteBans will use the default " + var1 + " until errors have been fixed.";
      var17.platform.getLogger().severe(var7);
   }

   public final Object createServerListPlusHook() {
      return this.platform.createBridge().findPlugin("ServerListPlus");
   }

   public final LiteBansCore requireEnabled() {
      if (!this.flag) {
         PluginModule var1 = this;
         String var2 = this.platform.getName() + " is not enabled yet!";
         var1.platform.getLogger().severe(var2);
         throw new OperationCancelledException();
      } else {
         return this;
      }
   }

   public final CharSequence getCharSequence(@Nullable Object var1) {
      if (var1 == null) {
         return Message.STRINGS_NULL;
      } else {
         String var2 = var1.toString();
         if (Intrinsics.checkObject(var2, "true")) {
            return Message.STRINGS_TRUE;
         } else if (Intrinsics.checkObject(var2, "false")) {
            return Message.STRINGS_FALSE;
         } else {
            return ServerScope.serverScopeParser.checkString(var2) ? Message.STRINGS_GLOBAL : var2;
         }
      }
   }

   public final String getSenderIp(@NotNull Sender var1, @NotNull Object var2) {
      String var3 = var1.getIp();
      if (var3 == null && !this.getConfig().isOnlinePlayerSelectLimit()) {
         this.platform.getLogger().warning("Player[name=" + var1.getName() + "].getAddress() returned null on " + var2.getClass().getSimpleName());
      }

      return var3;
   }

   public final boolean isCommandEnabled(@NotNull String var1) {
      if (this.getConfig().isMutesEnabled() || !StringsKt.checkString2(var1, "mute", false, 2, null) && !StringsKt.checkString2(var1, "muteip", false, 2, null)
         )
       {
         return !this.getConfig().isWarningsEnabled() && StringsKt.checkString2(var1, "warn", false, 2, null)
            ? false
            : this.getConfig().isLockdownEnabled() || !Intrinsics.checkObject(var1, "lockdown");
      } else {
         return false;
      }
   }

   public final boolean isAuthMeEnabled() {
      return this.platform.getPlatformId() == 0 && this.check2() && !this.toggleState.isFlag() && this.getConfig().isSupportAuthme();
   }

   public final SimpleDateFormat createDateFormat(@NotNull CharSequence var1, @NotNull CharSequence var2) {
      SimpleDateFormat var3;
      try {
         var3 = new SimpleDateFormat(StringsKt.getString(var1.toString(), "YYYY", "yyyy", false, 4, null), this.getMessagesLoader().getLocale());
      } catch (Exception var9) {
         if (!(var9 instanceof UninitializedPropertyAccessException)) {
            PluginModule var5 = this;
            CharSequence var6 = ArgUtils.getCharSequence(
               "Invalid date format: \"" + var1 + "\". Using default date format \"" + var2 + "\".",
               "Date format documentation: https://docs.oracle.com/javase/tutorial/i18n/format/simpleDateFormat.html"
            );
            Logger var8 = var5.platform.getLogger();
            var8.warning(var6.toString());
         }

         var3 = new SimpleDateFormat(var2.toString());
      }

      var3.setTimeZone(TimeZone.getTimeZone("UTC"));
      return var3;
   }

   public static SimpleDateFormat createDateFormatDefault(LiteBansCore var0, CharSequence var1, CharSequence var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = "yyyy-MM-dd";
      }

      return var0.createDateFormat(var1, var2);
   }

   private static final Shuffler createShuffler(LiteBansCore var0) {
      return new Shuffler(var0.getConfig().getSecurityObscureSeed());
   }

   private static final boolean hasAuthMe(LiteBansCore var0) {
      return var0.platform.createBridge().isPluginEnabled("AuthMe");
   }

   private static final void loadMessages(LiteBansCore var0, LiteBansPlatform var1) {
      Intrinsics.handleObject(var1);
      MessagesLoader var2 = new MessagesLoader(var1, null, 2, null);

      MessagesLoader var10000;
      label80: {
         ConfigFile var7;
         try {
            var2.getLiteBansPlatform().saveDefaultResource("messages.yml");
            var7 = var2.getLiteBansPlatform().loadConfig(var2.getFile()).getConfigFile4();
            var7.getConfigFile(ConfigFile.getString2("messages.yml", null));
            var2.setConfigFile(var7);
            ConfigFile var32 = var2.getConfigFile();
            Intrinsics.handleObject(var32);
            ConfigFile var10 = var32;
            Message[] var11 = Message.values();
            int var13 = 0;

            for (int var14 = var11.length; var13 < var14; var13++) {
               Message var15 = var11[var13];
               String var18 = var15.string;
               CharSequence var19 = var15.charSequence;
               if (var19 != null) {
                  String var20 = var10.getString(var18, null);
                  if (var20 != null) {
                     var15.handleCharSequence(var10.getString(var18, var19.toString()));
                     if (var20.length() > 0) {
                        var10.getConfigFile(var18, Message.getString(var15, var20));
                     }
                  } else {
                     var10.getConfigFile(var18, StringsKt.getString(var19.toString(), '§', '&', false, 4, null));
                  }
               }
            }

            String var8 = var7.getString("locale", "system");
            if (!Intrinsics.checkObject(var8, "system")) {
               Locale var9 = var2.getLocale2();
               var2.setLocale(new Locale(var8));
               var2.getLiteBansPlatform().getLogger().info("Using configured locale (" + var2.getLocale().getLanguage() + ')');
               if (Intrinsics.checkObject(var2.getLocale(), var9)) {
                  String var25 = "Supported locales: "
                     + ArraysKt.getString(Locale.getAvailableLocales(), null, null, null, 0, null, LocaleLanguageFunction.INSTANCE, 31, null);
                  Logger var27 = var2.getLiteBansPlatform().getLogger();
                  CharSequence var12 = ArgUtils.getCharSequence("Note: If the configured locale is unrecognized, the system default locale is used.", var25);
                  var27.info(var12.toString());
               }
            } else {
               var2.getLiteBansPlatform().getLogger().info("Using system locale (" + var2.getLocale().getLanguage() + ')');
            }

            var2.setConfigFile(null);
         } catch (Exception var23) {
            var2.handleException(var23);
            var10000 = var2;
            break label80;
         }

         if (GenericConfigFile.getConfigFormat3() != null) {
            try {
               var7.getConfigFile2();
            } catch (Exception var22) {
               var2.getLiteBansPlatform().getLogger().severe("[!!] Could not save messages.yml.");
               var2.getLiteBansCore().logException(var22);
            }
         }

         Message var26 = Message.UNMUTE_BROADCAST;
         Message var28 = Message.UNWARN_BROADCAST;
         if (StringsKt.checkCharSequence3(var26, "$bannedPlayer", false, 2, null) || StringsKt.checkCharSequence3(var28, "$bannedPlayer", false, 2, null)) {
            var26.charSequence = var26.getString("$bannedPlayer", "$mutedPlayer");
            var28.charSequence = var28.getString("$bannedPlayer", "$warnedPlayer");
         }

         try {
            DurationParser.getStringDefault(DurationUnit.durationParser, 0L, false, 2, null);
         } catch (Exception var24) {
            if (!(var24 instanceof IllegalFormatException)) {
               throw var24;
            }

            String var29 = "%d %s";
            Logger var30 = var2.getLiteBansPlatform().getLogger();
            CharSequence var31 = ArgUtils.getCharSequence(
               "Duration format (\"" + Message.DURATION_FORMAT + "\") is invalid! (" + var24.getClass().getSimpleName() + ')',
               "Using default format instead (\"" + var29 + "\")."
            );
            var30.warning(var31.toString());
            Message.DURATION_FORMAT.handleCharSequence(var29);
         }

         var10000 = var2;
      }

      var0.setMessagesLoader(var10000);
   }

   private static final void hookServerListPlus(LiteBansCore var0) {
      LiteBansCore var1 = var0;

      try {
         Object var3 = var1.createServerListPlusHook();
         if (var3 != null) {
            ServerListPlusBanProvider var9 = new ServerListPlusBanProvider(var1.platform);
            var1.serverListPlusHook = var9;
            if (!PlatformDetector.isFlag3()) {
               var9.handleObject(var3);
            }
         }
      } catch (Throwable var8) {
         PluginModule var4 = var0;
         CharSequence var5 = ArgUtils.getCharSequence("Failed to hook into ServerListPlus.", "Try using the latest ServerListPlus development build.");
         Logger var7 = var4.platform.getLogger();
         var7.warning(var5.toString());
         if (var0.isDebugLevel(1)) {
            var8.printStackTrace();
         }
      }
   }

   private static final void runSelfTest(LiteBansPlatform var0, LiteBansCore var1) {
      NotificationManager var2 = (NotificationManager)var0.getModule(NotificationManager.class);
      int var3 = var0.getCommandRegistry().getCommands().size();
      if (var3 <= 8) {
         Integer var41 = var3;
         throw new IllegalArgumentException(var41.toString());
      } else {
         ClassLoadDiagnostics var10000 = var0.getDiagnostics();
         Class[] var5 = new Class[]{LiteBansPlatform.class};
         Object[] var39 = new Object[]{var0};
         List var4 = var10000.getList(Importer.class, var5, var39);
         if (var4.size() <= 1) {
            Intrinsics.handleObject(var4);
            throw new IllegalArgumentException(var4.toString());
         } else {
            DatabaseManager var40 = (DatabaseManager)var0.getModule(DatabaseManager.class);
            DatabaseManager var6 = var40;

            try {
               PunishmentStorage var8 = var6.openStorage();
               Closeable var9 = var8;
               Throwable var10 = null;

               try {
                  ResultSet var15 = QueryRunner.getResultSet(var8.prepare("SELECT NOW() as now"));
                  var15.next();
                  Timestamp var16 = var15.getTimestamp("now");
                  PluginModule var17 = var1;
                  String var18 = "t: " + var16.getTime();
                  var17.platform.getLogger().info(var18);
                  var17 = var1;
                  var18 = "f: " + var2.formatDate(var16.getTime(), true) + " +" + var2.getLong(true);
                  var17.platform.getLogger().info(var18);
                  Intrinsics.handleObject(var0);
                  AltsCommand var43 = new AltsCommand(var0, "", "", "");
                  PlayerHistory var54 = var8.findLatestHistory("[CONSOLE]");
                  Intrinsics.handleObject(var54);
                  PlayerHistory var20 = var54;
                  String var10005 = var20.getUuid();
                  Intrinsics.handleObject(var10005);
                  CharSequence var21 = var43.getCharSequence(var8, var20, new DupeIpLookup(var10005, var8, null, false, 0, 28, null).getDupeIpLookup());
                  if (StringsKt.checkCharSequence3(var21, "))", false, 2, null)) {
                     throw new IllegalArgumentException(var21.toString());
                  }

                  var8.findEntries(var20.getUuid(), var43.getString3(), PunishmentType.BAN, ServerScope.serverScopeParser.getString(), false, 1);
                  var8.findEntries(var20.getUuid(), var43.getString3(), PunishmentType.BAN, ServerScope.serverScopeParser.getString(), true, 1);
                  var18 = "*";
                  String var19 = ServerScope.serverScopeParser.getString("global");
                  if (!ServerScope.serverScopeParser.checkString(var19)) {
                     String var50 = var19 + ' ' + ServerScope.serverScopeParser.getString();
                     throw new IllegalArgumentException(var50.toString());
                  }

                  if (!Intrinsics.checkObject(var19, var18) || var19.charAt(0) != var18.charAt(0)) {
                     String var49 = var19 + ' ' + var18;
                     throw new IllegalArgumentException(var49.toString());
                  }

                  LiteCommand var55 = var0.getCommandRegistry().getCommand("ban");
                  Intrinsics.handleObject(var55);
                  LiteCommand var46 = var55;
                  MessageSender var47 = CommandRunnable.messageSender;
                  Sender var22 = var0.getConsole();

                  try {
                     var47.getThreadLocal().set(var22);
                     Sender var10001 = var0.getConsole();
                     String[] var25 = new String[]{"test", "server:*"};
                     var46.execute(var10001, var25);
                  } finally {
                     var47.getThreadLocal().remove();
                  }

                  PlayerHistory var56 = var8.findLatestHistory("test");
                  Intrinsics.handleObject(var56);
                  String var48 = var56.getUuid();
                  PunishmentEntry var51 = EntryReader.getPunishmentEntry(var8, var48, null, PunishmentType.BAN, var18, false, false, 48, null);
                  if (var51 == null || !Intrinsics.checkObject(var51.getServerScope(), var18)) {
                     String var53 = String.valueOf(var51);
                     throw new IllegalArgumentException(var53.toString());
                  }

                  if (var0.getPlatformId() == 0 && Intrinsics.checkObject(var0.getOnlinePlayers().getClass(), BukkitOnlinePlayers.class)) {
                     String var52 = "Failed requirement.";
                     throw new IllegalArgumentException(var52.toString());
                  }

                  PluginModule var23 = var1;
                  String var24 = "" + var0.getConsole().getHandle();
                  var23.platform.getLogger().info(var24);
               } catch (Throwable var36) {
                  var10 = var36;
                  throw var36;
               } finally {
                  CloseableKt.handleCloseable(var9, var10);
               }
            } catch (SQLException var38) {
               if (!var40.isConnectionError(var38)) {
                  throw new RuntimeException(var38);
               }
            }
         }
      }
   }

   private static final void runDatabaseSelfTest(LiteBansPlatform var0, LiteBansCore var1) {
      try {
         PlatformBridge var10000 = var0.createBridge();
         Intrinsics.handleObject(var10000, "");
         VaultHook var2 = ((BukkitBridge)var10000).getVaultHook();
         Object var3 = var2.getObject();
         Object var4 = var2.getObject2();
         DatabaseManager var5 = (DatabaseManager)var0.getModule(DatabaseManager.class);
         DatabaseManager var6 = var5;

         try {
            PunishmentStorage var8 = var6.openStorage();
            Closeable var9 = var8;
            Throwable var10 = null;

            try {
               if (var8.isOpen()) {
                  Table var15 = Table.HISTORY;
                  CharSequence var16 = "uuid";
                  ResultSet var18 = QueryRunner.getResultSet(
                     var8.prepare(
                        SqlString.getSqlString(SqlString.getString(SqlString.getString8(SqlString.getString2("SELECT " + var16 + " FROM " + var15), "id"), 1))
                     )
                  );
                  byte var17 = 1;
                  String var37;
                  if (var18.next()) {
                     ResultSet var20 = var18;

                     String var22;
                     try {
                        var22 = var20.getString(var17);
                     } catch (SQLException var30) {
                        var22 = null;
                     }

                     var37 = var22;
                  } else {
                     var37 = null;
                  }

                  String var35 = var37;
                  if (TargetParser.checkString7(var35)) {
                     UUID var36 = UUID.fromString(TargetParser.INSTANCE.getString(var35));
                     PlatformBridge var10001 = var0.createBridge();
                     Intrinsics.handleObject(var10001, "");
                     BukkitBridge var38 = (BukkitBridge)var10001;
                     Intrinsics.handleObject(var36);
                     VaultHook.checkObjectDefault(var2, var38.getOfflinePlayer(var36), "test", null, 4, null);
                     var2.getString(var36);
                  }
               }
            } catch (Throwable var31) {
               var10 = var31;
               throw var31;
            } finally {
               CloseableKt.handleCloseable(var9, var10);
            }
         } catch (SQLException var33) {
            if (!var5.isConnectionError(var33)) {
               throw new RuntimeException(var33);
            }
         }

         if (var1.isDebugEnabled()) {
            var1.debug("" + var3 + var4 + '\n');
         }
      } catch (LinkageError var34) {
         handleObjectDefault(var1, var34, 0, 2, null);
      }
   }

   public static final int getIntValue4() {
      return intValue4;
   }

   public static final void handleInt(int var0) {
      intValue4 = var0;
   }

   public static final AtomicBoolean getAtomicBoolean() {
      return atomicBoolean;
   }

   public static final AtomicBoolean getAtomicBoolean2() {
      return atomicBoolean2;
   }
}
