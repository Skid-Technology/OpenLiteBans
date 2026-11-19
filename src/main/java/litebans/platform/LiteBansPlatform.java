package litebans.platform;

import java.io.File;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.function.Supplier;
import java.util.logging.Logger;
import litebans.config.ConfigFile;
import litebans.config.ConfigLoader;
import litebans.core.PluginModule;

public interface LiteBansPlatform extends Named {
   PluginModule[] pluginModuleArray = new PluginModule[0];

   void cancelScheduledTasks();

   ConfigFile getMainConfig();

   PluginModule[] createModules();

   void disable();

   boolean registerModule(PluginModule var1);

   ClassLoadDiagnostics getDiagnostics();

   CommandRegistry getCommandRegistry();

   OnlinePlayers getOnlinePlayers();

   void setCommandRegistry(CommandRegistry var1);

   Sender getConsole();

   Sender createConsoleSender();

   ConfigFile loadMainConfig();

   File getDataFolder();

   Logger getLogger();

   PluginModule getModule(Class var1);

   PluginModule findModule(Class var1);

   PluginModule[] getModules();

   int getOnlineCount();

   Sender[] getOnlineSenders();

   Map getSenderCache();

   ThreadPoolExecutor getExecutor();

   long getUptimeMillis();

   byte getPlatformId();

   PlatformBridge createBridge();

   String getVersion();

   void enable();

   boolean isEnabled();

   boolean isFullyEnabled();

   boolean isDisabling();

   boolean isOnlineMode();

   boolean isPrimaryThread();

   boolean isProxy();

   ConfigFile loadConfig(File var1);

   void registerListeners(ListenerHandle[] var1);

   boolean registerListenersIfClassExists(String var1, Supplier var2);

   void runSync(Runnable var1);

   void runSyncLater(Runnable var1, long var2);

   void runAsync(Runnable var1);

   void runAsyncLater(Runnable var1, long var2);

   void execute(Runnable var1);

   void runSyncTimer(Runnable var1, long var2, long var4);

   void runAsyncTimer(Runnable var1, long var2, long var4);

   void saveDefaultResource(String var1);

   void setConfigLoader(ConfigLoader var1);

   void setOnlinePlayers(OnlinePlayers var1);

   void unregisterAllListeners();

   void unregisterListeners(ListenerHandle[] var1);

   Sender removeCachedSender(Object var1);

   Sender getSender(Object var1);
}
