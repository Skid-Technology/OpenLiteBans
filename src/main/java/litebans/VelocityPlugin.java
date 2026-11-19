package litebans;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.google.inject.Inject;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.scheduler.ScheduledTask;
import com.velocitypowered.api.scheduler.TaskStatus;
import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import litebans.config.ConfigFile;
import litebans.config.ConfigLoader;
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
import litebans.platform.velocity.VelocityBridge;
import litebans.platform.velocity.VelocityCommandRegistryBase;
import litebans.platform.velocity.VelocityOnlinePlayers;
import litebans.platform.velocity.VelocitySender;
import org.slf4j.Logger;

@Plugin(
   id = "litebans",
   name = "LiteBans",
   version = "2.19.0",
   url = "https://www.spigotmc.org/resources/litebans.3715/",
   description = "Lightweight banning plugin with full UUID support.",
   authors = {"Ruan"}
)
public class VelocityPlugin implements LiteBansPlatform {
   private final LiteBansLifecycle liteBansLifecycle = new LiteBansLifecycle(this);
   public final ProxyServer proxyServer;
   private final Slf4jJulLogger slf4jJulLogger;
   private final Path path;
   private final List list = Collections.synchronizedList(new ArrayList());

   @Inject
   public VelocityPlugin(ProxyServer var1, Logger var2, @DataDirectory Path var3) {
      this.proxyServer = var1;
      this.slf4jJulLogger = new Slf4jJulLogger(var2, this.getName());
      this.path = var3;
   }

   @Subscribe
   public void handleProxyInitializeEvent(ProxyInitializeEvent var1) {
      this.enable();
   }

   @Subscribe
   public void handleProxyShutdownEvent(ProxyShutdownEvent var1) {
      this.disable();
   }

   @Override
   public void cancelScheduledTasks() {
      this.run5();
      synchronized (this.list) {
         for (Object var1 : this.list) {
            ((ScheduledTask) var1).cancel();
         }
         this.list.clear();
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
         if (var1 instanceof VelocityOnlinePlayers) {
            this.setOnlinePlayers((OnlinePlayers)var1);
         }

         if (var1 instanceof VelocityCommandRegistryBase) {
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
   public OnlinePlayers getOnlinePlayers() {
      return this.liteBansLifecycle.onlinePlayers;
   }

   @Override
   public void setCommandRegistry(CommandRegistry var1) {
      this.liteBansLifecycle.commandRegistry = var1;
   }

   @Override
   public Sender getConsole() {
      return this.liteBansLifecycle.consoleSender;
   }

   @Override
   public Sender createConsoleSender() {
      return this.getSender(this.proxyServer.getConsoleCommandSource());
   }

   @Override
   public ConfigFile loadMainConfig() {
      ConfigFile var1 = this.loadConfig(new File(this.getDataFolder(), "config.yml"));
      this.liteBansLifecycle.config = var1;
      return var1;
   }

   @Override
   public File getDataFolder() {
      return this.path.toFile();
   }

   @Override
   public java.util.logging.Logger getLogger() {
      return this.slf4jJulLogger;
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
      return "LiteBans";
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
   public byte getPlatformId() {
      return 2;
   }

   @Override
   public PlatformBridge createBridge() {
      return new VelocityBridge(this);
   }

   @Override
   public String getVersion() {
      return "2.19.0";
   }

   @Override
   public void enable() {
      this.liteBansLifecycle.loadConfig();
      this.liteBansLifecycle.enable();
   }

   @Override
   public boolean isEnabled() {
      return this.liteBansLifecycle.fullyEnabled;
   }

   @Override
   public boolean isFullyEnabled() {
      return this.liteBansLifecycle.fullyEnabled;
   }

   @Override
   public boolean isDisabling() {
      return this.liteBansLifecycle.disabling.get();
   }

   @Override
   public boolean isOnlineMode() {
      return this.proxyServer.getConfiguration().isOnlineMode();
   }

   @Override
   public boolean isPrimaryThread() {
      return false;
   }

   @Override
   public boolean isProxy() {
      return true;
   }

   @Override
   public ConfigFile loadConfig(File var1) {
      return this.liteBansLifecycle.configLoader.getConfigFile(this, var1);
   }

   @Override
   public void registerListeners(ListenerHandle[] var1) {
      for (ListenerHandle var5 : var1) {
         this.proxyServer.getEventManager().register(this, var5);
      }
   }

   @Override
   public boolean registerListenersIfClassExists(String var1, Supplier var2) {
      return this.liteBansLifecycle.registerListenersIfClassExists(var1, var2);
   }

   @Override
   public void runSync(Runnable var1) {
      this.runAsync(var1);
   }

   @Override
   public void runSyncLater(Runnable var1, long var2) {
      this.runAsyncLater(var1, var2);
   }

   @Override
   public void runAsync(Runnable var1) {
      this.getExecutor().execute(var1);
   }

   @Override
   public void runAsyncLater(Runnable var1, long var2) {
      if (var2 < 0L) {
         throw new IllegalArgumentException("Invalid delay: " + var2);
      } else {
         this.run5();
         this.list.add(this.proxyServer.getScheduler().buildTask(this, var1).delay(var2 * 50L, TimeUnit.MILLISECONDS).schedule());
      }
   }

   @Override
   public void execute(Runnable var1) {
      this.runAsync(var1);
   }

   @Override
   public void runSyncTimer(Runnable var1, long var2, long var4) {
      this.runAsyncTimer(var1, var2, var4);
   }

   @Override
   public void runAsyncTimer(Runnable var1, long var2, long var4) {
      this.run5();
      this.list
         .add(
            this.proxyServer.getScheduler().buildTask(this, var1).delay(var2 * 50L, TimeUnit.MILLISECONDS).repeat(var4 * 50L, TimeUnit.MILLISECONDS).schedule()
         );
   }

   public void run5() {
      synchronized (this.list) {
         this.list.removeIf(a -> checkScheduledTask((ScheduledTask) a));
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
      this.proxyServer.getEventManager().unregisterListeners(this);
   }

   @Override
   public void unregisterListeners(ListenerHandle[] var1) {
      for (ListenerHandle var5 : var1) {
         this.proxyServer.getEventManager().unregisterListener(this, var5);
      }
   }

   @Override
   public Sender removeCachedSender(Object var1) {
      if (!(var1 instanceof CommandSource)) {
         if (var1 instanceof Sender) {
            return (Sender)this.liteBansLifecycle.senderCache.remove(((Sender)var1).getName());
         } else {
            throw new IllegalArgumentException();
         }
      } else {
         String var2 = "[Console]";
         if (var1 instanceof Player) {
            var2 = ((Player)var1).getUsername();
         }

         return (Sender)this.liteBansLifecycle.senderCache.remove(var2);
      }
   }

   @Override
   public Sender getSender(Object var1) {
      if (!(var1 instanceof CommandSource)) {
         if (var1 instanceof Sender) {
            return (Sender)var1;
         } else {
            throw new IllegalArgumentException();
         }
      } else {
         CommandSource var2 = (CommandSource)var1;
         Map var3 = this.liteBansLifecycle.senderCache;
         String var4 = "[Console]";
         if (var2 instanceof Player) {
            var4 = ((Player)var2).getUsername();
         }

         Object var5 = (Sender)var3.get(var4);
         if (var5 == null || ((Sender)var5).getHandle() != var2) {
            var5 = new VelocitySender(this, var2);
            this.liteBansLifecycle.trimSenderCache();
            var3.put(var4, var5);
         }

         return (Sender)var5;
      }
   }

   public LiteBansLifecycle getLiteBansLifecycle() {
      return this.liteBansLifecycle;
   }

   private static final boolean checkScheduledTask(ScheduledTask var0) {
      return var0.status() != TaskStatus.SCHEDULED;
   }
}