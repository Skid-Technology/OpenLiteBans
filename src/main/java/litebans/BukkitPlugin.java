package litebans;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;
import litebans.config.ConfigFile;
import litebans.config.ConfigLoader;
import litebans.core.AsyncScheduler;
import litebans.core.LiteBansLifecycle;
import litebans.core.PluginModule;
import litebans.platform.ClassLoadDiagnostics;
import litebans.platform.CommandRegistry;
import litebans.platform.DiagnosticsTask;
import litebans.platform.ListenerHandle;
import litebans.platform.LiteBansPlatform;
import litebans.platform.LiteCommand;
import litebans.platform.OnlinePlayers;
import litebans.platform.PlatformBridge;
import litebans.platform.PlatformDetector;
import litebans.platform.Sender;
import litebans.platform.bukkit.BukkitBridge;
import litebans.platform.bukkit.BukkitCommandRegistryBase;
import litebans.platform.bukkit.BukkitConfigFile;
import litebans.platform.bukkit.BukkitListener;
import litebans.platform.bukkit.BukkitOnlinePlayers;
import litebans.platform.bukkit.BukkitSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class BukkitPlugin extends JavaPlugin implements LiteBansPlatform {
   private final LiteBansLifecycle liteBansLifecycle = new LiteBansLifecycle(this);
   private AsyncScheduler asyncScheduler = null;

   @Override
   public void cancelScheduledTasks() {
      try {
         if (PlatformDetector.isFlag3()) {
            this.asyncScheduler.run();
         } else {
            this.getScheduler().cancelTasks(this);
         }
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

   @Nullable
   public List onTabComplete(@NotNull CommandSender var1, @NotNull Command var2, @NotNull String var3, @NotNull String[] var4) {
      CommandRegistry var5 = this.getCommandRegistry();
      if (var5 == null) {
         return null;
      } else {
         LiteCommand var6 = var5.getCommand(var2.getName());
         return var5.suggest(var6, var3, this.getSender(var1), var4);
      }
   }

   @Override
   public boolean registerModule(PluginModule var1) {
      boolean var2 = var1.isSupported();
      if (var2) {
         if (var1 instanceof BukkitCommandRegistryBase) {
            this.setCommandRegistry((BukkitCommandRegistryBase)var1);
         }

         if (var1 instanceof BukkitOnlinePlayers) {
            this.setOnlinePlayers((BukkitOnlinePlayers)var1);
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

   public BukkitScheduler getScheduler() {
      return this.getServer().getScheduler();
   }

   @Override
   public ThreadPoolExecutor getExecutor() {
      return this.getOrCreateExecutor(false);
   }

   public ThreadPoolExecutor getOrCreateExecutor(boolean var1) {
      ThreadPoolExecutor var2 = this.liteBansLifecycle.executor;
      if (var2 != null && !var2.isShutdown() || !var1 && !this.liteBansLifecycle.enabled.get() && !this.liteBansLifecycle.loading.get()) {
         return var2;
      } else {
         ThreadFactoryBuilder var3 = this.liteBansLifecycle.createThreadFactory();
         return this.liteBansLifecycle.createExecutor(var3);
      }
   }

   @Override
   public long getUptimeMillis() {
      return this.liteBansLifecycle.getUptimeMillis();
   }

   @Override
   public final byte getPlatformId() {
      return 0;
   }

   @Override
   public PlatformBridge createBridge() {
      return new BukkitBridge(this);
   }

   @Override
   public String getVersion() {
      return this.getDescription().getVersion();
   }

   @Override
   public Sender createConsoleSender() {
      return this.getSender(this.getServer().getConsoleSender());
   }

   @Override
   public ConfigFile loadMainConfig() {
      BukkitConfigFile var1 = new BukkitConfigFile(this);
      var1.getConfigFile(new File(this.getDataFolder(), "config.yml"));
      return var1;
   }

   @Override
   public void enable() {
      this.liteBansLifecycle.loadConfig();
      this.liteBansLifecycle.enable();
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
      return this.getServer().getOnlineMode();
   }

   @Override
   public boolean isPrimaryThread() {
      return PlatformDetector.isFlag3() ? false : this.getServer().isPrimaryThread();
   }

   @Override
   public boolean isProxy() {
      return false;
   }

   @Override
   public ConfigFile loadConfig(File var1) {
      try {
         return new BukkitConfigFile(this).getConfigFile(var1);
      } catch (Throwable var3) {
         throw new RuntimeException(var3);
      }
   }

   public boolean onCommand(CommandSender var1, Command var2, String var3, String[] var4) {
      CommandRegistry var5 = this.getCommandRegistry();
      if (var5 != null && var5 instanceof BukkitCommandRegistryBase) {
         return ((BukkitCommandRegistryBase)var5).onCommand(var1, var2, var3, var4);
      } else {
         this.getLogger().severe("No command manager found!");
         return true;
      }
   }

   public void onDisable() {
      this.disable();
   }

   public void onEnable() {
      this.enable();
   }

   public void onLoad() {
      this.getOrCreateExecutor(true);
      if (PlatformDetector.isFlag3()) {
         this.asyncScheduler = new AsyncScheduler();
      }

      DiagnosticsTask var1 = new DiagnosticsTask(this);
      if (!this.isLegacyServer()) {
         this.execute(var1);
      } else {
         var1.run();
      }
   }

   public boolean isLegacyServer() {
      return !ClassLoadDiagnostics.checkString("org.bukkit.entity.Llama");
   }

   @Override
   public void registerListeners(@Nullable ListenerHandle[] var1) {
      if (var1 != null) {
         for (ListenerHandle var5 : var1) {
            if (var5 instanceof BukkitListener) {
               this.getServer().getPluginManager().registerEvents((BukkitListener)var5, this);
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
      if (PlatformDetector.isFlag3()) {
         var1.run();
      } else {
         this.getScheduler().runTask(this, var1);
      }
   }

   @Override
   public void runSyncLater(Runnable var1, long var2) {
      if (PlatformDetector.isFlag3()) {
         this.runAsyncLater(var1, var2);
      } else {
         this.getScheduler().runTaskLater(this, var1, var2);
      }
   }

   @Override
   public void runAsync(Runnable var1) {
      if (PlatformDetector.isFlag3()) {
         this.execute(var1);
      } else {
         this.getScheduler().runTaskAsynchronously(this, var1);
      }
   }

   @Override
   public void runAsyncLater(Runnable var1, long var2) {
      if (PlatformDetector.isFlag3()) {
         this.asyncScheduler.getScheduledExecutorService(this.liteBansLifecycle).schedule(var1, var2 * 50L, TimeUnit.MILLISECONDS);
      } else {
         this.getScheduler().runTaskLaterAsynchronously(this, var1, var2);
      }
   }

   @Override
   public void execute(Runnable var1) {
      this.getExecutor().execute(var1);
   }

   @Override
   public void runSyncTimer(Runnable var1, long var2, long var4) {
      if (PlatformDetector.isFlag3()) {
         this.runAsyncTimer(var1, var2, var4);
      } else {
         this.getScheduler().runTaskTimer(this, var1, var2, var4);
      }
   }

   @Override
   public void runAsyncTimer(Runnable var1, long var2, long var4) {
      if (PlatformDetector.isFlag3()) {
         this.asyncScheduler.getScheduledExecutorService(this.liteBansLifecycle).scheduleAtFixedRate(var1, var2 * 50L, var4 * 50L, TimeUnit.MILLISECONDS);
      } else {
         this.getScheduler().runTaskTimerAsynchronously(this, var1, var2, var4);
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
      if (!(var1 instanceof BukkitOnlinePlayers)) {
         throw new IllegalArgumentException();
      } else {
         this.liteBansLifecycle.onlinePlayers = var1;
      }
   }

   @Override
   public void unregisterAllListeners() {
      HandlerList.unregisterAll(this);
   }

   @Override
   public void unregisterListeners(@Nullable ListenerHandle[] var1) {
      if (var1 != null) {
         for (ListenerHandle var5 : var1) {
            if (var5 instanceof BukkitListener) {
               HandlerList.unregisterAll((BukkitListener)var5);
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
            throw new IllegalArgumentException(var1 == null ? "null" : var1.getClass().getName());
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
            throw new IllegalArgumentException(var1 == null ? "null" : var1.getClass().getName());
         }
      } else {
         CommandSender var2 = (CommandSender)var1;
         Map var3 = this.liteBansLifecycle.senderCache;
         Object var4 = (Sender)var3.get(var2.getName());
         if (var4 == null || ((Sender)var4).getHandle() != var2) {
            var4 = new BukkitSender(this, var2);
            this.liteBansLifecycle.trimSenderCache();
            var3.put(var2.getName(), var4);
         }

         return (Sender)var4;
      }
   }

   public LiteBansLifecycle getLifecycle() {
      return this.liteBansLifecycle;
   }
}
