package litebans.platform.fabric;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import litebans.FabricPlugin;
import litebans.chat.ChatComponentVersion;
import litebans.chat.ComponentVersionConverter;
import litebans.core.LiteBansCore;
import litebans.core.NotificationManager;
import litebans.database.DatabaseManager;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.jvm.internal.MetadataAnnotation;
import litebans.platform.ClassLoadDiagnostics;
import litebans.platform.LiteBansPlatform;
import litebans.platform.LoginListenerRegistry;
import litebans.punishment.LockdownTask;
import litebans.punishment.PlayerInfo;
import me.lucko.fabric.api.permissions.v0.Permissions;
import net.fabricmc.fabric.api.networking.v1.ServerLoginNetworking.LoginSynchronizer;
import net.minecraft.class_2535;
import net.minecraft.class_2561;
import net.minecraft.class_3248;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@MetadataAnnotation(
   a = 3
)
public final class FabricLoginListenerRegistryIntermediary extends LoginListenerRegistry implements FabricLoginHandler {
   public FabricLoginListenerRegistryIntermediary(@NotNull LiteBansPlatform var1) {
      super(var1);
   }

   @Override
   public void onDisable() {
      this.platform.unregisterAllListeners();
   }

   @Override
   public boolean isSupported() {
      return this.platform.getPlatformId() == 3 && ClassLoadDiagnostics.checkString("net.minecraft.class_3248");
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onLoad() {
      LiteBansCore var1 = (LiteBansCore)this.platform.getModule(LiteBansCore.class);
      var1.onLoad();
      LiteBansPlatform var10000 = this.platform;
      Intrinsics.handleObject(this.platform, "");
      FabricServerAdapter var4 = ((FabricPlugin)var10000).getFabricServerAdapter();
      if (!this.platform.registerListenersIfClassExists("net.fabricmc.fabric.impl.gamerule.rpc.FabricTypedRule", new FabricLoginListenerSupplier2(this, var4))
         && !this.platform
            .registerListenersIfClassExists("net.fabricmc.fabric.api.networking.v1.LoginPacketSender", new FabricLoginListenerSupplier(this, var4))) {
         LiteBansPlatform var10002 = this.platform;
         Intrinsics.handleObject(var4);
         new FabricLoginListenerIntermediary2(var10002, var4).getFabricLoginListenerIntermediary2();
      }

      if (var1.getConfig().isMutesEnabled()) {
         new FabricChatListenerIntermediary(this.platform).getFabricChatListenerIntermediary();
      }
   }

   public final void handleclass_3248(@NotNull class_3248 var1, @NotNull LoginSynchronizer var2, @NotNull PlayerInfo var3, @NotNull class_2535 var4) {
      NotificationManager var5 = (NotificationManager)this.platform.getModule(NotificationManager.class);
      LiteBansCore var6 = (LiteBansCore)this.platform.getModule(LiteBansCore.class);
      DatabaseManager var7 = (DatabaseManager)this.platform.getModule(DatabaseManager.class);
      LiteBansPlatform var10000 = this.platform;
      Intrinsics.handleObject(this.platform, "");
      FabricPlugin var8 = (FabricPlugin)var10000;
      FabricPreLoginEvent var9 = new FabricPreLoginEvent(var3, var1, null, 4, null);
      var2.waitFor(CompletableFuture.runAsync(FabricLoginListenerRegistryIntermediary::handleLiteBansCore));
   }

   public void handleclass_32482(@Nullable class_3248 var1, @Nullable LoginSynchronizer var2, @Nullable PlayerInfo var3, @Nullable class_2535 var4) {
      Intrinsics.handleObject(var1);
      Intrinsics.handleObject(var2);
      Intrinsics.handleObject(var3);
      Intrinsics.handleObject(var4);
      this.handleclass_3248(var1, var2, var3, var4);
   }

   private static final void handleLiteBansCore(
      LiteBansCore var0, NotificationManager var1, FabricPreLoginEvent var2, FabricPlugin var3, class_3248 var4, PlayerInfo var5, DatabaseManager var6
   ) {
      if (var0.isDebugEnabled()) {
         var0.debug("Login " + var5);
      }

      var1.getLoginCheck().handleObject(var2);
      ChatComponentVersion var7 = ChatComponentVersion.V1_21_5;
      if (var3.getInt2() < 770) {
         var7 = ChatComponentVersion.V1_16;
      }

      if (var2.getCancelReason() != null) {
         Object var10000 = var3.getFabricServerAdapter().getComponentParser().getObject2(ComponentVersionConverter.getString(var2.getCancelReason(), var7));
         Intrinsics.handleObject(var10000, "");
         class_2561 var8 = (class_2561)var10000;
         var4.method_14380(var8);
      } else {
         LockdownTask var15 = var1.getLockdownTask();
         if (var15 == null) {
            return;
         }

         LockdownTask var14 = var15;
         if (var14.getCharSequence() != null && var3.isFlag() && !(Boolean)Permissions.check(UUID.fromString(var5.getUuid()), "litebans.lockdown.bypass").get()
            )
          {
            String var11 = var14.getString(var6.getString(), false);
            var15 = (LockdownTask)var3.getFabricServerAdapter().getComponentParser().getObject2(ComponentVersionConverter.getString(var11, var7));
            Intrinsics.handleObject(var15, "");
            class_2561 var12 = (class_2561)var15;
            var4.method_14380(var12);
         }
      }
   }

   @Override
   public void handleObject(Object var1, Object var2, Object var3, Object var4) {
      this.handleclass_32482((class_3248)var1, (LoginSynchronizer)var2, (PlayerInfo)var3, (class_2535)var4);
   }
}
