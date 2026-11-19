package litebans.platform.bungee;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import litebans.lib.kotlin.NoWhenBranchMatchedException;
import litebans.platform.Sender;
import litebans.util.Unreachable;
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

public final class DummyProxiedPlayer implements ProxiedPlayer {
   public final Sender sender;
   public final ServerInfo serverInfo;

   public DummyProxiedPlayer(Sender var1, ServerInfo var2) {
      this.sender = var1;
      this.serverInfo = var2;
   }

   public InetSocketAddress getAddress() {
      try {
      return new InetSocketAddress(InetAddress.getByName(this.sender.getIp()), 0);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   public SocketAddress getSocketAddress() {
      return this.getAddress();
   }

   public void disconnect(String var1) {
   }

   public void disconnect(BaseComponent[] var1) {
   }

   public void disconnect(BaseComponent var1) {
   }

   public boolean isConnected() {
      return false;
   }

   public Unsafe unsafe() {
      return null;
   }

   public String getName() {
      return this.sender.getName();
   }

   public void sendMessage(ChatMessageType var1, BaseComponent[] var2) {
   }

   public void sendMessage(ChatMessageType var1, BaseComponent var2) {
   }

   public void sendMessage(UUID var1, BaseComponent[] var2) {
   }

   public void sendMessage(UUID var1, BaseComponent var2) {
   }

   public void sendMessage(String var1) {
   }

   public void sendMessage(BaseComponent[] var1) {
   }

   public void sendMessage(BaseComponent var1) {
   }

   public void sendMessages(String[] var1) {
   }

   public Collection getGroups() {
      return new ArrayList();
   }

   public void addGroups(String[] var1) {
   }

   public void removeGroups(String[] var1) {
   }

   public boolean hasPermission(String var1) {
      return false;
   }

   public Void getVoid(String var1, boolean var2) {
      Unreachable.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   public Collection getPermissions() {
      return new ArrayList();
   }

   public String getDisplayName() {
      return this.sender.getName();
   }

   public Void getVoid(String var1) {
      Unreachable.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   public void connect(ServerInfo var1) {
   }

   public void connect(ServerInfo var1, Reason var2) {
   }

   public void connect(ServerInfo var1, Callback var2) {
   }

   public void connect(ServerInfo var1, Callback var2, Reason var3) {
   }

   public void connect(ServerConnectRequest var1) {
   }

   public Server getServer() {
      return new DummyBungeeServer(this.serverInfo);
   }

   public int getPing() {
      return 0;
   }

   public void sendData(String var1, byte[] var2) {
   }

   public PendingConnection getPendingConnection() {
      Unreachable.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   public void chat(String var1) {
   }

   public ServerInfo getReconnectServer() {
      return null;
   }

   public void setReconnectServer(ServerInfo var1) {
   }

   public String getUUID() {
      return this.sender.getUuidString();
   }

   public UUID getUniqueId() {
      return this.sender.getUuid();
   }

   public Locale getLocale() {
      Unreachable.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   public byte getViewDistance() {
      Unreachable.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   public ChatMode getChatMode() {
      Unreachable.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   public boolean hasChatColors() {
      return false;
   }

   public SkinConfiguration getSkinParts() {
      Unreachable.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   public MainHand getMainHand() {
      Unreachable.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   public void setTabHeader(BaseComponent var1, BaseComponent var2) {
   }

   public void setTabHeader(BaseComponent[] var1, BaseComponent[] var2) {
   }

   public void resetTabHeader() {
   }

   public void sendTitle(Title var1) {
   }

   public boolean isForgeUser() {
      Unreachable.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   public Map getModList() {
      Unreachable.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   public Scoreboard getScoreboard() {
      Unreachable.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   public CompletableFuture retrieveCookie(String var1) {
      Unreachable.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   public Void getVoid(String var1, byte[] var2) {
      Unreachable.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   public Void getVoid(String var1, int var2) {
      Unreachable.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   public void setPermission(String var1, boolean var2) {
      this.getVoid(var1, var2);
   }

   public void setDisplayName(String var1) {
      this.getVoid(var1);
   }

   public void storeCookie(String var1, byte[] var2) {
      this.getVoid(var1, var2);
   }

   public void transfer(String var1, int var2) {
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
