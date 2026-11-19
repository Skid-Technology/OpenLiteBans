package litebans.platform.bukkit;

import java.util.Arrays;
import litebans.config.Config;
import litebans.core.LiteBansCore;
import litebans.core.PluginModule;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.jvm.internal.MetadataAnnotation;
import litebans.platform.ListenerHandle;
import litebans.platform.LiteBansPlatform;
import litebans.platform.LoginListenerRegistry;
import litebans.platform.Named;
import org.jetbrains.annotations.NotNull;

@MetadataAnnotation(
   a = 0
)
public final class BukkitLoginListenerRegistry extends LoginListenerRegistry {
   private boolean flag;
   private Object[] objectArray = new Object[0];

   public BukkitLoginListenerRegistry(@NotNull LiteBansPlatform var1) {
      super(var1);
   }

   @Override
   public void onDisable() {
      this.platform.unregisterAllListeners();
      Object[] var1 = this.objectArray;
      int var3 = 0;

      for (int var4 = var1.length; var3 < var4; var3++) {
         Object var5 = var1[var3];
         ProtocolLibHook.handlePluginModule2(this, var5);
      }
   }

   @Override
   public boolean isSupported() {
      return this.platform.getPlatformId() == 0;
   }

   @Override
   public void onEnable() {
      LiteBansCore var1 = (LiteBansCore)this.platform.getModule(LiteBansCore.class);
      var1.onEnable();
      Config var2 = var1.getConfig();
      new BukkitPreLoginListener(this.platform).getBukkitPreLoginListener();
      LiteBansPlatform var3 = this.platform;
      boolean var4 = var2.isCheckAfterJoin();
      if (var4) {
         ListenerHandle[] var6 = new ListenerHandle[1];
         byte var10 = 0;
         ListenerHandle var11 = new BukkitJoinListener(this.platform);
         var6[var10] = var11;
         var3.registerListeners(var6);
      }

      this.platform.runSync(() -> handleLiteBansCore(var1, this));
      if (var1.getConfig().isMutesEnabled()) {
         this.platform.runSync(() -> handleBukkitLoginListenerRegistry(this, var2, var1));
         new BukkitCommandListener(this.platform).getBukkitCommandListener();
      }
   }

   public BukkitListener getBukkitListener(@NotNull Named var1) {
      return Intrinsics.checkObject(var1.getName(), "lockdown") ? new BukkitLockdownListener(this.platform) : (BukkitListener)super.getListenerHandle(var1);
   }

   private static final ListenerHandle[] getListenerHandleArray(BukkitLoginListenerRegistry var0) {
      return new ListenerHandle[0];
   }

   private static final void handleLiteBansCore(LiteBansCore var0, BukkitLoginListenerRegistry var1) {
      if (var0.isAuthMeEnabled()) {
         try {
            var1.platform.registerListenersIfClassExists("fr.xephi.authme.events.LoginEvent", () -> getListenerHandleArray(var1));
         } catch (Exception var5) {
            PluginModule var3 = var1;
            String var4 = "Hooking into AuthMe failed.";
            var3.platform.getLogger().warning(var4);
            var5.printStackTrace();
         }
      }
   }

   private static final void handleBukkitLoginListenerRegistry(BukkitLoginListenerRegistry var0, Config var1, LiteBansCore var2) {
      try {
         var0.flag = var1.isUseProtocollib() && var0.platform.createBridge().isPluginEnabled("ProtocolLib");
         if (var0.flag && ProtocolLibChat.INSTANCE.check()) {
            var0.platform.getLogger().warning("ProtocolLib error 00 - manager is missing.");
         } else if (var0.flag) {
            PluginModule var3 = var0;
            Object[] var4 = new Object[]{new ChatPacketAdapter(var0.platform), new LoginDisconnectPacketAdapter(var0.platform)};
            Object[] var7 = var4;
            int var8 = 0;

            for (int var9 = var4.length; var8 < var9; var8++) {
               Object var10 = var7[var8];
               ProtocolLibHook.handlePluginModule(var3, var10);
            }

            var0.objectArray = Arrays.copyOf(var4, var4.length);
         }
      } catch (Throwable var14) {
         var0.platform.getLogger().warning("Failed to add ProtocolLib packet listener.");
         if (!(var14 instanceof LinkageError) && !(var14 instanceof ReflectiveOperationException) || var2.isDebugEnabled()) {
            var2.logException(var14);
         }
      }
   }

   @Override
   public ListenerHandle getListenerHandle(Named var1) {
      return this.getBukkitListener(var1);
   }
}
