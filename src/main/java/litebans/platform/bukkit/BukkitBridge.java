package litebans.platform.bukkit;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import litebans.chat.ChatComponentVersion;
import litebans.chat.ComponentVersionConverter;
import litebans.lib.kotlin.Lazy;
import litebans.lib.kotlin.LazyKt;
import litebans.lib.kotlin.NoWhenBranchMatchedException;
import litebans.lib.kotlin.NotImplementedError;
import litebans.lib.kotlin.collections.CollectionsKt;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.jvm.internal.MetadataAnnotation;
import litebans.platform.LiteBansPlatform;
import litebans.platform.PlatformBridge;
import litebans.platform.Sender;
import litebans.punishment.LoginKickResult;
import litebans.punishment.PlayerInfo;
import litebans.update.Version;
import litebans.update.VersionParser;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Cancellable;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent.Result;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.jetbrains.annotations.NotNull;

@MetadataAnnotation(
   a = 0
)
public final class BukkitBridge implements PlatformBridge {
   private final LiteBansPlatform liteBansPlatform;
   private final Lazy lazy;

   public BukkitBridge(@NotNull LiteBansPlatform var1) {
      this.liteBansPlatform = var1;
      this.lazy = LazyKt.getLazy(() -> getVaultHook(this));
   }

   @Override
   public LiteBansPlatform getPlatform() {
      return this.liteBansPlatform;
   }

   public final VaultHook getVaultHook() {
      Lazy var1 = this.lazy;
      return (VaultHook)var1.getValue();
   }

   public final Plugin getPlugin(@NotNull LiteBansPlatform var1) {
      Intrinsics.handleObject(var1, "");
      return (Plugin)var1;
   }

   public static Plugin getPluginDefault(BukkitBridge var0, LiteBansPlatform var1, int var2, Object var3) {
      if ((var2 & 1) != 0) {
         var1 = var0.getPlatform();
      }

      return var0.getPlugin(var1);
   }

   public final void kickViaBungee(@NotNull Sender var1, @NotNull String var2) {
      if (!this.isChannelRegistered("BungeeCord")) {
         this.registerChannel("BungeeCord");
      }

      this.getPlatform().runAsyncLater(() -> kickViaBungeeLegacy(var1, var2), 8L);
      if (var1.isOnline()) {
         byte[] var3 = this.createKickPluginMessage(var1, var2);
         var1.sendPluginMessage("BungeeCord", var3);
      }

      this.getPlatform().getLogger().info("Kicking " + var1.getName() + " from BungeeCord.");
   }

   public final byte[] createKickPluginMessage(@NotNull Sender var1, @NotNull String var2) {
      Version var3 = VersionParser.getVersionDefault(Version.versionParser, this.getServerVersion(), null, 2, null);
      ByteArrayDataOutput var4 = ByteStreams.newDataOutput();
      var4.writeUTF("KickPlayerRaw");
      var4.writeUTF(var1.getName());
      ChatComponentVersion var5 = var3.checkInt(1, 21, 5) ? ChatComponentVersion.V1_21_5 : ChatComponentVersion.V1_16;
      var4.writeUTF(ComponentVersionConverter.getString(var2, var5));
      byte[] var6 = var4.toByteArray();
      Intrinsics.handleObject(var6);
      return var6;
   }

   public final Object getOfflinePlayer(@NotNull UUID var1) {
      getPluginDefault(this, null, 1, null).getServer().getVersion();
      return getPluginDefault(this, null, 1, null).getServer().getOfflinePlayer(var1);
   }

   public final String getOfflinePlayerName(@NotNull Object var1) {
      Intrinsics.handleObject(var1, "");
      String var10000 = ((OfflinePlayer)var1).getName();
      Intrinsics.handleObject(var10000);
      return var10000;
   }

   @Override
   public Object findPlugin(@NotNull String var1) {
      return Bukkit.getServer().getPluginManager().getPlugin(var1);
   }

   @Override
   public List getPluginNames(@NotNull String var1) {
      Object[] var2 = Bukkit.getServer().getPluginManager().getPlugins();
      Object[] var4 = var2;
      Collection var5 = new ArrayList(var2.length);
      int var7 = 0;

      for (int var8 = var2.length; var7 < var8; var7++) {
         Object var9 = var4[var7];
         Plugin var10 = (Plugin)var9;
         var5.add(var10.getDescription());
      }

      Iterable var13 = (List)var5;
      var5 = new ArrayList();

      for (Object var19 : var13) {
         PluginDescriptionFile var21 = (PluginDescriptionFile)var19;
         if (var21.getDepend().contains(var1) || var21.getSoftDepend().contains(var1)) {
            var5.add(var19);
         }
      }

      Iterable var14 = (List)var5;
      var5 = new ArrayList(CollectionsKt.getInt(var14, 10));

      for (Object var20 : var14) {
         PluginDescriptionFile var22 = (PluginDescriptionFile)var20;
         var5.add(var22.getName());
      }

      return (List)var5;
   }

   @Override
   public Collection getPermissionGroups() {
      return CollectionsKt.getList();
   }

   @Override
   public String getServerVersion() {
      return getPluginDefault(this, null, 1, null).getServer().getBukkitVersion();
   }

   public final boolean isChannelRegistered(@NotNull String var1) {
      return getPluginDefault(this, null, 1, null).getServer().getMessenger().isOutgoingChannelRegistered(getPluginDefault(this, null, 1, null), var1);
   }

   public final void registerChannel(@NotNull String var1) {
      getPluginDefault(this, null, 1, null).getServer().getMessenger().registerOutgoingPluginChannel(getPluginDefault(this, null, 1, null), var1);
   }

   public final String getEventMessage(@NotNull Object var1) {
      String var10000;
      if (var1 instanceof AsyncPlayerChatEvent) {
         var10000 = ((AsyncPlayerChatEvent)var1).getMessage();
      } else {
         if (!(var1 instanceof PlayerCommandPreprocessEvent)) {
            this.getVoid(var1);
            throw new NoWhenBranchMatchedException();
         }

         var10000 = ((PlayerCommandPreprocessEvent)var1).getMessage();
      }

      String var2 = var10000;
      Intrinsics.handleObject(var2);
      return var2;
   }

   @Override
   public PlayerInfo getLoginInfo(@NotNull Object var1, boolean var2) {
      if (var1 instanceof AsyncPlayerPreLoginEvent) {
         String var3 = ((AsyncPlayerPreLoginEvent)var1).getName();
         UUID var10000 = ((AsyncPlayerPreLoginEvent)var1).getUniqueId();
         if (var10000 == null) {
            throw new NullPointerException(var1.getClass().getSimpleName() + ".getUniqueId() == null (" + var3 + ')');
         } else {
            UUID var4 = var10000;
            InetAddress var5 = ((AsyncPlayerPreLoginEvent)var1).getAddress();
            String var7;
            if (var5 == null) {
               if (!var2) {
                  this.getPlatform().getLogger().warning(var1.getClass().getSimpleName() + ".getAddress() == null (" + var3 + ')');
               }

               var7 = null;
            } else {
               var7 = var5.getHostAddress();
            }

            String var6 = var7;
            return new PlayerInfo(((AsyncPlayerPreLoginEvent)var1).getName(), var4.toString(), var6);
         }
      } else {
         this.getVoid(var1);
         throw new NoWhenBranchMatchedException();
      }
   }

   public Void getVoid(@NotNull Object var1, @NotNull CharSequence var2) {
      throw new UnsupportedOperationException();
   }

   @Override
   public String getString(@NotNull Object var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public void disallowLogin(@NotNull Object var1, @NotNull CharSequence var2, @NotNull String var3, boolean var4, @NotNull String var5) {
      if (var1 instanceof AsyncPlayerPreLoginEvent) {
         ((AsyncPlayerPreLoginEvent)var1).disallow(Result.valueOf(var5), var2.toString());
      } else if (var1 instanceof PlayerLoginEvent) {
         ((PlayerLoginEvent)var1).disallow(org.bukkit.event.player.PlayerLoginEvent.Result.valueOf(var5), var2.toString());
      } else if (var1 instanceof Cancellable) {
         if (var4) {
            ((Cancellable)var1).setCancelled(true);
         }

         if (var1 instanceof AsyncPlayerChatEvent) {
            AsyncPlayerChatEvent var7 = (AsyncPlayerChatEvent)var1;

            try {
               var7.getRecipients().clear();
            } catch (UnsupportedOperationException var8) {
            }

            ((AsyncPlayerChatEvent)var1).setMessage(var3 + ((AsyncPlayerChatEvent)var1).getMessage());
         } else if (var1 instanceof PlayerCommandPreprocessEvent) {
            ((PlayerCommandPreprocessEvent)var1).setMessage('/' + var3 + ((PlayerCommandPreprocessEvent)var1).getMessage());
         }
      } else {
         this.getVoid(var1);
         throw new NoWhenBranchMatchedException();
      }
   }

   private final Void getVoid(Object var1) {
      throw new NotImplementedError(var1.getClass().getName());
   }

   @Override
   public boolean isPluginEnabled(@NotNull String var1) {
      return LoginKickResult.checkPlatformBridge(this, var1);
   }

   private static final VaultHook getVaultHook(BukkitBridge var0) {
      return new VaultHook(var0);
   }

   private static final void kickViaBungeeLegacy(Sender var0, String var1) {
      if (var0.isOnline()) {
         ByteArrayDataOutput var2 = ByteStreams.newDataOutput();
         var2.writeUTF("KickPlayer");
         var2.writeUTF(var0.getName());
         var2.writeUTF(var1);
         var0.sendPluginMessage("BungeeCord", var2.toByteArray());
      }
   }

   @Override
   public void handleObject(Object var1, CharSequence var2) {
      this.getVoid(var1, var2);
   }
}
