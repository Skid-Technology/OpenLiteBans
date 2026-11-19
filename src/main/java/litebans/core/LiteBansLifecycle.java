package litebans.core;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;
import litebans.config.ConfigFile;
import litebans.config.ConfigLoader;
import litebans.config.GenericConfigFile;
import litebans.platform.ClassLoadDiagnostics;
import litebans.platform.CommandRegistry;
import litebans.platform.ListenerHandle;
import litebans.platform.LiteBansPlatform;
import litebans.platform.OnlinePlayers;
import litebans.platform.PlatformDefaults;
import litebans.platform.Sender;
import org.jetbrains.annotations.NotNull;

public class LiteBansLifecycle implements ConfigLoader {
   private final LiteBansPlatform liteBansPlatform;
   public PluginModule[] modules = LiteBansPlatform.pluginModuleArray;
   public CommandRegistry commandRegistry;
   public OnlinePlayers onlinePlayers;
   public Map senderCache = new ConcurrentHashMap();
   public ClassLoadDiagnostics classLoadDiagnostics;
   public Sender consoleSender;
   public ThreadPoolExecutor executor;
   public ConfigFile config;
   public ConfigLoader configLoader = this;
   public AtomicBoolean enabled = new AtomicBoolean(false);
   public AtomicBoolean loading = new AtomicBoolean(false);
   public AtomicBoolean disabling = new AtomicBoolean(false);
   public long enableTimeMillis;
   public boolean fullyEnabled;

   public LiteBansLifecycle(LiteBansPlatform var1) {
      this.liteBansPlatform = var1;
      this.classLoadDiagnostics = new ClassLoadDiagnostics(var1);
   }

   public PluginModule getModule(Class var1) {
      for (PluginModule var5 : this.modules) {
         if (var5.getClass() == var1) {
            return var5;
         }
      }

      return null;
   }

   public PluginModule findModule(Class var1) {
      for (PluginModule var5 : this.modules) {
         if (var1.isAssignableFrom(var5.getClass())) {
            return var5;
         }
      }

      return null;
   }

   @Override
   public ConfigFile getConfigFile(LiteBansPlatform var1, File var2) {
      return new GenericConfigFile(var1).getConfigFile(var2);
   }

   public void loadConfig() {
      this.loading.set(true);
      this.config = this.liteBansPlatform.loadMainConfig();
      this.loadOrEnableModules(false);
   }

   public void enable() {
      try {
         if (this.enabled.get()) {
            this.liteBansPlatform.getLogger().warning("onEnable() called while plugin is already enabled! This might break something.");
            this.disable();
         }

         this.enableTimeMillis = System.currentTimeMillis();
         this.enabled.set(true);
         if (this.modules == LiteBansPlatform.pluginModuleArray) {
            this.loadConfig();
         }

         this.consoleSender = this.liteBansPlatform.createConsoleSender();
         this.loadOrEnableModules(true);
         this.liteBansPlatform.getLogger().info(this.getEnabledMessage());
         this.classLoadDiagnostics.handleBoolean(false);
         this.fullyEnabled = true;
      } finally {
         this.loading.set(false);
      }
   }

   @NotNull
   public ThreadFactoryBuilder createThreadFactory() {
      return new ThreadFactoryBuilder().setNameFormat(this.liteBansPlatform.getName() + "-%1$d").setDaemon(true);
   }

   public void loadOrEnableModules(boolean var1) {
      if (this.modules == LiteBansPlatform.pluginModuleArray) {
         this.modules = this.liteBansPlatform.createModules();
      }

      if (var1) {
         for (PluginModule var5 : this.liteBansPlatform.getModules()) {
            var5.onEnable();
         }
      } else {
         for (PluginModule var9 : this.liteBansPlatform.getModules()) {
            var9.onLoad();
         }
      }
   }

   public void disable() {
      if (this.loading.get()) {
         this.liteBansPlatform
            .getLogger()
            .severe(
               "onDisable() called while plugin hasn't finished loading yet! This will be ignored to prevent errors. It's likely that another plugin stopped your server asynchronously."
            );
      } else {
         this.disabling.set(true);
         this.enabled.set(false);
         this.fullyEnabled = false;

         for (PluginModule var4 : this.modules) {
            var4.onDisable();
         }

         this.liteBansPlatform.unregisterAllListeners();
         this.liteBansPlatform.cancelScheduledTasks();
         this.config = null;
         this.senderCache.clear();
         ThreadPoolExecutor var6 = this.executor;
         if (var6 != null) {
            try {
               var6.shutdown();
               if (!var6.awaitTermination(2L, TimeUnit.SECONDS)) {
                  var6.shutdownNow();
               }
            } catch (InterruptedException var5) {
               var5.printStackTrace();
            }
         }

         this.liteBansPlatform.getLogger().info(this.getDisabledMessage());
         this.consoleSender = null;
         this.executor = null;
         this.disabling.set(false);
      }
   }

   public Sender[] getOnlineSenders() {
      OnlinePlayers var1 = this.onlinePlayers;
      return var1 != null ? var1.getPlayers() : Sender.EMPTY_ARRAY;
   }

   public int getOnlineCount() {
      OnlinePlayers var1 = this.onlinePlayers;
      return var1 != null ? var1.getPlayerCount() : 0;
   }

   public PluginModule[] createModules() {
      return PlatformDefaults.getPluginModuleArray(this.liteBansPlatform);
   }

   public boolean registerListenersIfClassExists(String var1, Supplier var2) {
      boolean var3 = ClassLoadDiagnostics.checkString(var1);
      if (var3) {
         this.liteBansPlatform.registerListeners((ListenerHandle[])var2.get());
      }

      return var3;
   }

   public long getUptimeMillis() {
      return System.currentTimeMillis() - this.enableTimeMillis;
   }

   public String getDisabledMessage() {
      return 'v' + this.liteBansPlatform.getVersion() + " disabled.";
   }

   public String getEnabledMessage() {
      return 'v' + this.liteBansPlatform.getVersion() + " enabled. Startup took " + this.liteBansPlatform.getUptimeMillis() + " ms.";
   }

   public ThreadPoolExecutor createExecutor(ThreadFactoryBuilder var1) {
      return this.executor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 1000L, TimeUnit.SECONDS, new SynchronousQueue<>(), var1.build());
   }

   public void trimSenderCache() {
      int var1 = this.senderCache.size();
      if (var1 > 4 && var1 % 32 == 0 && var1 > this.getOnlineCount() + 4) {
         this.senderCache.clear();
      }
   }
}
