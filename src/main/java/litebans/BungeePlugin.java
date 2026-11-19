package litebans;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.io.File;
import java.util.Map;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import litebans.config.ConfigFile;
import litebans.config.ConfigLoader;
import litebans.core.LiteBansLifecycle;
import litebans.core.PluginModule;
import litebans.platform.ClassLoadDiagnostics;
import litebans.platform.CommandRegistry;
import litebans.platform.ListenerHandle;
import litebans.platform.LiteBansPlatform;
import litebans.platform.OnlinePlayers;
import litebans.platform.PlatformBridge;
import litebans.platform.Sender;
import litebans.platform.bungee.BungeeBridge;
import litebans.platform.bungee.BungeeCommandRegistryBase;
import litebans.platform.bungee.BungeeListener;
import litebans.platform.bungee.BungeeOnlinePlayers;
import litebans.platform.bungee.BungeeSender;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.scheduler.GroupedThreadFactory;
import org.jetbrains.annotations.Nullable;

public class BungeePlugin extends Plugin implements LiteBansPlatform {
   private final LiteBansLifecycle liteBansLifecycle = new LiteBansLifecycle(this);

   @Override
   public void cancelScheduledTasks() {
      this.getProxy().getScheduler().cancel(this);
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
         if (var1 instanceof BungeeOnlinePlayers) {
            this.setOnlinePlayers((OnlinePlayers)var1);
         }

         if (var1 instanceof BungeeCommandRegistryBase) {
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
      return this.getDescription().getName();
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
      try {
         ThreadPoolExecutor var1 = this.liteBansLifecycle.executor;
         if (var1 == null) {
            ThreadFactoryBuilder var2 = this.liteBansLifecycle.createThreadFactory();

            try {
               var2.setThreadFactory(new GroupedThreadFactory(this, this.getName()));
            } catch (NoSuchMethodError var3) {
               var2.setThreadFactory((ThreadFactory)GroupedThreadFactory.class.getConstructor(Plugin.class).newInstance(this));
            }

            return this.liteBansLifecycle.createExecutor(var2);
         } else {
            return var1;
         }
      } catch (Throwable var4) {
         throw new RuntimeException(var4);
      }
   }

   @Override
   public long getUptimeMillis() {
      return this.liteBansLifecycle.getUptimeMillis();
   }

   @Override
   public final byte getPlatformId() {
      return 1;
   }

   @Override
   public PlatformBridge createBridge() {
      return new BungeeBridge(this);
   }

   @Override
   public String getVersion() {
      return this.getDescription().getVersion();
   }

   @Override
   public Sender createConsoleSender() {
      return this.getSender(this.getProxy().getConsole());
   }

   @Override
   public ConfigFile loadMainConfig() {
      ConfigFile var1 = this.loadConfig(new File(this.getDataFolder(), "config.yml"));
      this.liteBansLifecycle.config = var1;
      return var1;
   }

   @Override
   public void enable() {
      this.liteBansLifecycle.loadConfig();
      this.liteBansLifecycle.enable();
   }

   @Override
   public boolean isDisabling() {
      return this.liteBansLifecycle.disabling.get();
   }

   @Override
   public boolean isOnlineMode() {
      return this.getProxy().getConfig().isOnlineMode();
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

   public void onDisable() {
      this.disable();
   }

   public void onEnable() {
      try {
         this.enable();
      } catch (Throwable var2) {
         throw new RuntimeException(var2);
      }
   }

   @Override
   public void registerListeners(@Nullable ListenerHandle[] var1) {
      if (var1 != null) {
         for (ListenerHandle var5 : var1) {
            if (var5 instanceof BungeeListener) {
               this.getProxy().getPluginManager().registerListener(this, (BungeeListener)var5);
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
         try {
            this.getProxy().getScheduler().schedule(this, var1, var2 * 50L, TimeUnit.MILLISECONDS);
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
      this.runAsyncTimer(var1, var2, var4);
   }

   @Override
   public void runAsyncTimer(Runnable var1, long var2, long var4) {
      try {
         this.getProxy().getScheduler().schedule(this, var1, var2 * 50L, var4 * 50L, TimeUnit.MILLISECONDS);
      } catch (RejectedExecutionException var7) {
         this.handleException(var7);
      }
   }

   private final void handleException(Exception var1) {
      try {
         if (this.liteBansLifecycle.loading.get()) {
            this.getLogger().warning("Startup was cancelled!!");
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
      this.getProxy().getPluginManager().unregisterListeners(this);
   }

   @Override
   public void unregisterListeners(@Nullable ListenerHandle[] var1) {
      if (var1 != null) {
         for (ListenerHandle var5 : var1) {
            if (var5 instanceof BungeeListener) {
               this.getProxy().getPluginManager().unregisterListener((BungeeListener)var5);
            }
         }
      }
   }

   @Override
   public Sender removeCachedSender(Object var1) {
      if (!(var1 instanceof CommandSender)) {
         if (var1 instanceof Sender) {
            return (Sender)this.liteBansLifecycle.senderCache.remove(((Sender)var1).getName());
         } else {
            throw new IllegalArgumentException();
         }
      } else {
         CommandSender var2 = (CommandSender)var1;
         return (Sender)this.liteBansLifecycle.senderCache.remove(var2.getName());
      }
   }

   @Override
   public Sender getSender(Object var1) {
      if (!(var1 instanceof CommandSender)) {
         if (var1 instanceof Sender) {
            return (Sender)var1;
         } else {
            throw new IllegalArgumentException();
         }
      } else {
         CommandSender var2 = (CommandSender)var1;
         Map var3 = this.liteBansLifecycle.senderCache;
         Object var4 = (Sender)var3.get(var2.getName());
         if (var4 == null || ((Sender)var4).getHandle() != var2) {
            var4 = new BungeeSender(this, var2);
            this.liteBansLifecycle.trimSenderCache();
            var3.put(var2.getName(), var4);
         }

         return (Sender)var4;
      }
   }

   public LiteBansLifecycle getLiteBansLifecycle() {
      return this.liteBansLifecycle;
   }
}
