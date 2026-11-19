package litebans;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.io.File;
import java.util.Map;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import litebans.config.ConfigFile;
import litebans.config.ConfigLoader;
import litebans.core.AsyncScheduler;
import litebans.core.LiteBansLifecycle;
import litebans.core.PluginModule;
import litebans.lib.hikari.Slf4jJulLogger;
import litebans.platform.ClassLoadDiagnostics;
import litebans.platform.CommandRegistry;
import litebans.platform.ListenerHandle;
import litebans.platform.LiteBansPlatform;
import litebans.platform.OnlinePlayers;
import litebans.platform.PlatformBridge;
import litebans.platform.Sender;
import litebans.platform.fabric.FabricBridge;
import litebans.platform.fabric.FabricCommandRegistryBase;
import litebans.platform.fabric.FabricCompatibility;
import litebans.platform.fabric.FabricListener;
import litebans.platform.fabric.FabricModInfo;
import litebans.platform.fabric.FabricOnlinePlayers;
import litebans.platform.fabric.FabricServerAdapter;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.server.MinecraftServer;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FabricPlugin implements DedicatedServerModInitializer, LiteBansPlatform {
   private static final Logger logger = LoggerFactory.getLogger(FabricPlugin.class);
   private FabricModInfo fabricModInfo;
   private java.util.logging.Logger logger2;
   private ModContainer modContainer;
   private AsyncScheduler asyncScheduler;
   private final LiteBansLifecycle liteBansLifecycle = new LiteBansLifecycle(this);
   private FabricServerAdapter fabricServerAdapter;
   private boolean flag = false;

   @Override
   public void cancelScheduledTasks() {
      try {
         this.asyncScheduler.run();
      } catch (Throwable var2) {
         throw new RuntimeException(var2);
      }
   }

   @Override
   public ConfigFile getMainConfig() {
      return this.liteBansLifecycle.config;
   }

   @Override
   public PluginModule[] createModules() {
      return this.liteBansLifecycle.createModules();
   }

   @Override
   public void disable() {
      this.liteBansLifecycle.disable();
   }

   @Override
   public boolean registerModule(PluginModule var1) {
      boolean var2 = var1.isSupported();
      if (var2) {
         if (var1 instanceof FabricOnlinePlayers) {
            this.setOnlinePlayers((OnlinePlayers)var1);
         }

         if (var1 instanceof FabricCommandRegistryBase) {
            this.setCommandRegistry((CommandRegistry)var1);
         }
      }

      return var2;
   }

   @Override
   public ClassLoadDiagnostics getDiagnostics() {
      return this.liteBansLifecycle.classLoadDiagnostics;
   }

   @Override
   public CommandRegistry getCommandRegistry() {
      return this.liteBansLifecycle.commandRegistry;
   }

   @Override
   public void setCommandRegistry(CommandRegistry var1) {
      this.liteBansLifecycle.commandRegistry = var1;
   }

   @Override
   public OnlinePlayers getOnlinePlayers() {
      return this.liteBansLifecycle.onlinePlayers;
   }

   @Override
   public Sender getConsole() {
      return this.liteBansLifecycle.consoleSender;
   }

   @Override
   public PluginModule getModule(Class var1) {
      return this.liteBansLifecycle.getModule(var1);
   }

   @Override
   public PluginModule findModule(Class var1) {
      return this.liteBansLifecycle.findModule(var1);
   }

   @Override
   public PluginModule[] getModules() {
      return this.liteBansLifecycle.modules;
   }

   @Override
   public String getName() {
      return this.fabricModInfo.getName();
   }

   @Override
   public int getOnlineCount() {
      return this.liteBansLifecycle.getOnlineCount();
   }

   @Override
   public Sender[] getOnlineSenders() {
      return this.liteBansLifecycle.getOnlineSenders();
   }

   @Override
   public Map getSenderCache() {
      return this.liteBansLifecycle.senderCache;
   }

   @Override
   public ThreadPoolExecutor getExecutor() {
      ThreadPoolExecutor var1 = this.liteBansLifecycle.executor;
      if (var1 != null && !var1.isShutdown() || !this.liteBansLifecycle.enabled.get() && !this.liteBansLifecycle.loading.get()) {
         return var1;
      } else {
         ThreadFactoryBuilder var2 = this.liteBansLifecycle.createThreadFactory();
         return this.liteBansLifecycle.createExecutor(var2);
      }
   }

   @Override
   public long getUptimeMillis() {
      return this.liteBansLifecycle.getUptimeMillis();
   }

   @Override
   public final byte getPlatformId() {
      return 3;
   }

   @Override
   public PlatformBridge createBridge() {
      return new FabricBridge(this);
   }

   @Override
   public String getVersion() {
      return this.fabricModInfo.getVersion();
   }

   @Override
   public Sender createConsoleSender() {
      return this.fabricServerAdapter.getSender(this.fabricServerAdapter.getObject(), this::getObject);
   }

   @Override
   public ConfigFile loadMainConfig() {
      ConfigFile var1 = this.loadConfig(new File(this.getDataFolder(), "config.yml"));
      this.liteBansLifecycle.config = var1;
      return var1;
   }

   @Override
   public File getDataFolder() {
      return FabricLoader.getInstance().getConfigDir().resolve(this.fabricModInfo.getId()).toFile();
   }

   public int getInt2() {
      return this.fabricServerAdapter.getInt();
   }

   @Override
   public java.util.logging.Logger getLogger() {
      return this.logger2;
   }

   @Override
   public void enable() {
      this.asyncScheduler = new AsyncScheduler();
      this.liteBansLifecycle.enable();
   }

   @Override
   public boolean isDisabling() {
      return this.liteBansLifecycle.disabling.get();
   }

   @Override
   public boolean isEnabled() {
      return this.liteBansLifecycle.enabled.get();
   }

   @Override
   public boolean isFullyEnabled() {
      return this.liteBansLifecycle.fullyEnabled;
   }

   @Override
   public ConfigFile loadConfig(File var1) {
      try {
         return this.liteBansLifecycle.configLoader.getConfigFile(this, var1);
      } catch (Throwable var3) {
         throw new RuntimeException(var3);
      }
   }

   public ScheduledExecutorService getScheduledExecutorService() {
      return this.asyncScheduler.getScheduledExecutorService(this.liteBansLifecycle);
   }

   @Override
   public boolean isOnlineMode() {
      return this.fabricServerAdapter.check();
   }

   @Override
   public boolean isPrimaryThread() {
      return this.fabricServerAdapter.check2();
   }

   @Override
   public boolean isProxy() {
      return false;
   }

   public void onInitializeServer() {
      try {
         this.fabricModInfo = new FabricModInfo(ClassLoadDiagnostics.getString(FabricModInfo.class));
         String var1 = this.fabricModInfo.getId();
         this.logger2 = new Slf4jJulLogger(LoggerFactory.getLogger(var1), var1);
         this.fabricServerAdapter = FabricCompatibility.getFabricServerAdapter(
            this, ((ModContainer)FabricLoader.getInstance().getModContainer("fabric-api").get()).getMetadata().getVersion().getFriendlyString().split("\\+")[1]
         );
         ServerLifecycleEvents.SERVER_STARTING.register(this::handleMinecraftServer);
         ServerLifecycleEvents.SERVER_STOPPING.register(this::handleMinecraftServer2);
         this.liteBansLifecycle.loadConfig();
      } catch (Throwable var2) {
         throw new RuntimeException(var2);
      }
   }

   private final void handleMinecraftServer(MinecraftServer var1) {
      this.fabricServerAdapter.setMinecraftServer(var1);
      this.modContainer = (ModContainer)FabricLoader.getInstance().getModContainer(this.fabricModInfo.getId()).orElse(null);

      try {
         Class.forName("me.lucko.fabric.api.permissions.v0.Permissions");
         this.flag = true;
      } catch (ClassNotFoundException var2) {
      }

      this.enable();
   }

   private final void handleMinecraftServer2(MinecraftServer var1) {
      this.disable();
   }

   @Override
   public void registerListeners(@Nullable ListenerHandle[] var1) {
      if (var1 != null) {
         for (ListenerHandle var5 : var1) {
            if (var5 instanceof FabricListener) {
               FabricListener var6 = (FabricListener)var5;
               var6.getListenerHandle();
            }
         }
      }
   }

   @Override
   public boolean registerListenersIfClassExists(String var1, Supplier var2) {
      return this.liteBansLifecycle.registerListenersIfClassExists(var1, var2);
   }

   @Override
   public void runSync(Runnable var1) {
      this.fabricServerAdapter.handleRunnable(var1);
   }

   @Override
   public void runSyncLater(Runnable var1, long var2) {
      this.runAsyncLater(this::handleRunnable5, var2);
   }

   @Override
   public void runAsync(Runnable var1) {
      this.getExecutor().execute(var1);
   }

   @Override
   public void runAsyncLater(Runnable var1, long var2) {
      if (var2 < 0L) {
         throw new IllegalArgumentException("Invalid delay " + var2);
      } else {
         try {
            this.getScheduledExecutorService().schedule(var1, var2 * 50L, TimeUnit.MILLISECONDS);
         } catch (RejectedExecutionException var5) {
            this.handleException(var5);
         }
      }
   }

   @Override
   public void execute(Runnable var1) {
      this.runAsync(var1);
   }

   @Override
   public void runSyncTimer(Runnable var1, long var2, long var4) {
      this.runAsyncTimer(this::handleRunnable4, var2, var4);
   }

   @Override
   public void runAsyncTimer(Runnable var1, long var2, long var4) {
      try {
         this.getScheduledExecutorService().scheduleAtFixedRate(var1, var2 * 50L, var4 * 50L, TimeUnit.MILLISECONDS);
      } catch (RejectedExecutionException var7) {
         this.handleException(var7);
      }
   }

   private final void handleException(Exception var1) {
      try {
         if (this.liteBansLifecycle.loading.get()) {
            this.getLogger().warning("Startup was cancelled");
         } else {
            throw var1;
         }
      } catch (Throwable var3) {
         throw new RuntimeException(var3);
      }
   }

   @Override
   public void saveDefaultResource(String var1) {
      ConfigFile.handleFile(this.getDataFolder(), var1, null, false);
   }

   @Override
   public void setConfigLoader(ConfigLoader var1) {
      this.liteBansLifecycle.configLoader = var1;
   }

   @Override
   public void setOnlinePlayers(OnlinePlayers var1) {
      this.liteBansLifecycle.onlinePlayers = var1;
   }

   @Override
   public void unregisterAllListeners() {
   }

   @Override
   public void unregisterListeners(@Nullable ListenerHandle[] var1) {
   }

   @Override
   public Sender removeCachedSender(Object var1) {
      return this.fabricServerAdapter.getSender(var1);
   }

   @Override
   public Sender getSender(Object var1) {
      return this.fabricServerAdapter.getSender2(var1);
   }

   public ModContainer getModContainer() {
      return this.modContainer;
   }

   public LiteBansLifecycle getLiteBansLifecycle() {
      return this.liteBansLifecycle;
   }

   public FabricServerAdapter getFabricServerAdapter() {
      return this.fabricServerAdapter;
   }

   public boolean isFlag() {
      return this.flag;
   }

   private final void handleRunnable4(Runnable var1) {
      this.runSync(var1);
   }

   private final void handleRunnable5(Runnable var1) {
      this.runSync(var1);
   }

   private final Object getObject() {
      return this.fabricServerAdapter.getObject();
   }
}
