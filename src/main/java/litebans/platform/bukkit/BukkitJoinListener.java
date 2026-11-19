package litebans.platform.bukkit;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import litebans.config.Message;
import litebans.core.LiteBansCore;
import litebans.core.NotificationManager;
import litebans.database.DatabaseManager;
import litebans.database.EntryReader;
import litebans.database.PunishmentStorage;
import litebans.lib.kotlin.Unit;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.platform.ListenerHandle;
import litebans.platform.LiteBansPlatform;
import litebans.platform.PlatformBridge;
import litebans.platform.Sender;
import litebans.punishment.EntryPlaceholders;
import litebans.punishment.PunishmentEntry;
import litebans.punishment.PunishmentType;
import litebans.util.SerializableValue;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;

public final class BukkitJoinListener implements BukkitListener {
   private final LiteBansPlatform liteBansPlatform;

   public BukkitJoinListener(@NotNull LiteBansPlatform var1) {
      this.liteBansPlatform = var1;
   }

   @EventHandler
   public final void handlePlayerJoinEvent(@NotNull PlayerJoinEvent var1) {
      Player var2;
      DatabaseManager var3;
      NotificationManager var4;
      LiteBansCore var5;
      String var6;
      String var12;
      label12: {
         var2 = var1.getPlayer();
         var3 = (DatabaseManager)this.liteBansPlatform.getModule(DatabaseManager.class);
         var4 = (NotificationManager)this.liteBansPlatform.getModule(NotificationManager.class);
         var5 = (LiteBansCore)this.liteBansPlatform.getModule(LiteBansCore.class);
         PlatformBridge var10000 = this.liteBansPlatform.createBridge();
         Intrinsics.handleObject(var10000, "");
         var6 = ((BukkitBridge)var10000).getOfflinePlayerName(var2);
         InetSocketAddress var10 = var2.getAddress();
         if (var10 != null) {
            InetAddress var11 = var10.getAddress();
            if (var11 != null) {
               var12 = var11.getHostAddress();
               break label12;
            }
         }

         var12 = null;
      }

      String var7 = var12;
      String var8 = var2.getUniqueId().toString();
      SerializableValue var9 = new SerializableValue();
      var3.runWithStorageAsync(st -> getUnit(var2, var6, var8, var5, var4, var7, this, var9, var3, (PunishmentStorage) st));
      var4.handleString(var6, var7, var8, var3.getString(), var9.flag);
   }

   private final void handlePunishmentStorage(
      PunishmentStorage var1,
      Sender var2,
      String var3,
      String var4,
      String var5,
      PunishmentEntry var6,
      NotificationManager var7,
      LiteBansCore var8,
      PunishmentStorage var9
   ) {
      try {
         if (var8.getConfig().isNotifyBannedPlayerJoin()) {
            CharSequence var10 = var1.getCharSequence2(EntryReader.getCharSequence(var1, Message.NOTIFY_BANNED_PLAYER_JOIN, var6, false, 2, null), var2);
            var7.notifyBannedJoin(var3, var10, true);
         }
      } finally {
         Intrinsics.handleObject(var6);
         var7.kickSync(var2, EntryPlaceholders.getCharSequence(var1, var6, var4, var3, var5, null, -1));
      }
   }

   public BukkitJoinListener getBukkitJoinListener() {
      BukkitJoinListener var2 = this;
      LiteBansPlatform var10000 = var2.liteBansPlatform;
      ListenerHandle[] var4 = new ListenerHandle[]{var2};
      var10000.registerListeners(var4);
      return this;
   }

   private static final Unit getUnit(
      Player var0,
      String var1,
      String var2,
      LiteBansCore var3,
      NotificationManager var4,
      String var5,
      BukkitJoinListener var6,
      SerializableValue var7,
      DatabaseManager var8,
      PunishmentStorage var9
   ) {
      Sender var11 = var9.getPlatform().getSender(var0);
      PunishmentEntry var12 = EntryReader.getPunishmentEntry(var9, var1, var2, PunishmentType.BAN, false, false, 24, null);
      if (var3.isDebugLevel(4)) {
         var3.debug("Ban: " + var12 + " (valid: " + (var12 != null) + ')');
      }

      if (var12 != null) {
         if (var4.checkPunishmentEntry(var12, var5)) {
            return Unit.INSTANCE;
         }

         Intrinsics.handleObject(var11);
         Intrinsics.handleObject(var4);
         Intrinsics.handleObject(var3);
         var6.handlePunishmentStorage(var9, var11, var1, var5, var2, var12, var4, var3, var9);
         var7.flag = true;
      }

      if (!var7.flag && var3.getConfig().isBanNames()) {
         PunishmentEntry var13 = EntryReader.getPunishmentEntry(var9, var5, var1, var8.getString(), null, 8, null);
         if (var13 != null) {
            Intrinsics.handleObject(var11);
            Intrinsics.handleObject(var4);
            Intrinsics.handleObject(var3);
            var6.handlePunishmentStorage(var9, var11, var1, var5, var2, var13, var4, var3, var9);
            var7.flag = true;
         }
      }

      return Unit.INSTANCE;
   }

   @Override
   public ListenerHandle getListenerHandle() {
      return this.getBukkitJoinListener();
   }
}
