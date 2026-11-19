package litebans.platform.bungee;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Arrays;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import litebans.lib.kotlin.NoWhenBranchMatchedException;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.jvm.internal.MetadataAnnotation;
import litebans.platform.LiteBansPlatform;
import litebans.platform.PlatformBridge;
import net.md_5.bungee.api.Callback;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.ServerConnectRequest;
import net.md_5.bungee.api.SkinConfiguration;
import net.md_5.bungee.api.Title;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.PendingConnection;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.connection.Server;
import net.md_5.bungee.api.connection.Connection.Unsafe;
import net.md_5.bungee.api.connection.ProxiedPlayer.ChatMode;
import net.md_5.bungee.api.connection.ProxiedPlayer.MainHand;
import net.md_5.bungee.api.event.ServerConnectEvent.Reason;
import net.md_5.bungee.api.score.Scoreboard;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@MetadataAnnotation(
   a = 1
)
public final class PendingProxiedPlayer implements ProxiedPlayer {
   private final LiteBansPlatform liteBansPlatform;
   private final PendingConnection pendingConnection;

   public PendingProxiedPlayer(@NotNull LiteBansPlatform var1, @Nullable PendingConnection var2) {
      this.liteBansPlatform = var1;
      this.pendingConnection = var2;
   }

   public final Void getVoid() {
      throw new UnsupportedOperationException();
   }

   public void connect(@Nullable ServerConnectRequest var1) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   public void connect(@Nullable ServerInfo var1, @Nullable Reason var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   public void connect(@Nullable ServerInfo var1, @Nullable Callback var2, @Nullable Reason var3) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   public SkinConfiguration getSkinParts() {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   public boolean hasChatColors() {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   public byte getViewDistance() {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   public MainHand getMainHand() {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   public ChatMode getChatMode() {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   public Scoreboard getScoreboard() {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   public void addGroups(@NotNull String[] var1) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   public void chat(@NotNull String var1) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   public void connect(@NotNull ServerInfo var1) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   public void connect(@NotNull ServerInfo var1, @NotNull Callback var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   public void disconnect(@NotNull String var1) {
      if (this.pendingConnection != null) {
         this.pendingConnection.disconnect(var1);
      }
   }

   public void disconnect(@NotNull BaseComponent[] var1) {
      if (this.pendingConnection != null) {
         this.pendingConnection.disconnect(Arrays.copyOf(var1, var1.length));
      }
   }

   public void disconnect(@NotNull BaseComponent var1) {
      if (this.pendingConnection != null) {
         this.pendingConnection.disconnect(var1);
      }
   }

   public InetSocketAddress getAddress() {
      return this.pendingConnection != null ? this.pendingConnection.getAddress() : null;
   }

   public String getDisplayName() {
      return this.getName();
   }

   public void setDisplayName(@NotNull String var1) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   public Collection getGroups() {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   public Locale getLocale() {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   public Map getModList() {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   public String getName() {
      if (this.pendingConnection != null) {
         String var10000 = this.pendingConnection.getName();
         if (var10000 != null) {
            return var10000;
         }
      }

      return "null";
   }

   public PendingConnection getPendingConnection() {
      PendingConnection var10000 = this.pendingConnection;
      Intrinsics.handleObject(this.pendingConnection);
      return var10000;
   }

   public Collection getPermissions() {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   public int getPing() {
      return 0;
   }

   public ServerInfo getReconnectServer() {
      PlatformBridge var10000 = this.liteBansPlatform.createBridge();
      Intrinsics.handleObject(var10000, "");
      BungeeBridge var1 = (BungeeBridge)var10000;
      PendingConnection var10001 = this.pendingConnection;
      Intrinsics.handleObject(this.pendingConnection);
      return var1.getServerInfo2(var10001);
   }

   public void setReconnectServer(@NotNull ServerInfo var1) {
   }

   public Server getServer() {
      return null;
   }

   public String getUUID() {
      return this.getUniqueId().toString();
   }

   public UUID getUniqueId() {
      if (this.pendingConnection != null) {
         UUID var10000 = this.pendingConnection.getUniqueId();
         if (var10000 != null) {
            return var10000;
         }
      }

      return new UUID(0L, 0L);
   }

   public boolean hasPermission(@NotNull String var1) {
      return false;
   }

   public boolean isConnected() {
      return false;
   }

   public boolean isForgeUser() {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   public void removeGroups(@NotNull String[] var1) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   public void resetTabHeader() {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   public SocketAddress getSocketAddress() {
      return this.pendingConnection != null ? this.pendingConnection.getSocketAddress() : null;
   }

   public void sendData(@NotNull String var1, @NotNull byte[] var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   public void sendMessage(@NotNull String var1) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   public void sendMessage(@NotNull BaseComponent[] var1) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   public void sendMessage(@NotNull BaseComponent var1) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   public void sendMessage(@NotNull ChatMessageType var1, @NotNull BaseComponent[] var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   public void sendMessage(@NotNull ChatMessageType var1, @NotNull BaseComponent var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   public void sendMessage(@Nullable UUID var1, @NotNull BaseComponent[] var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   public void sendMessage(@Nullable UUID var1, @Nullable BaseComponent var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   public void sendMessages(@NotNull String[] var1) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   public void sendTitle(@NotNull Title var1) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   public void setPermission(@NotNull String var1, boolean var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   public void setTabHeader(@NotNull BaseComponent var1, @NotNull BaseComponent var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   public void setTabHeader(@NotNull BaseComponent[] var1, @NotNull BaseComponent[] var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   public Unsafe unsafe() {
      PendingConnection var10000 = this.pendingConnection;
      Intrinsics.handleObject(this.pendingConnection);
      return var10000.unsafe();
   }

   public Void getVoid(@Nullable String var1, @NotNull byte[] var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   public void transfer(@Nullable String var1, int var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   public CompletableFuture retrieveCookie(@Nullable String var1) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   public void storeCookie(String var1, byte[] var2) {
      this.getVoid(var1, var2);
   }
   @Override
   public void sendServerLinks(java.util.List<net.md_5.bungee.api.ServerLink> var1) {
   }
   @Override
   public void showDialog(net.md_5.bungee.api.dialog.Dialog var1) {
   }
   @Override
   public String getClientBrand() {
      return "";
   }

   @Override
   public void clearDialog() {
   }
}
