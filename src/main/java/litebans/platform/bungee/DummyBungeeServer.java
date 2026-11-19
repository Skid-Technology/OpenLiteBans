package litebans.platform.bungee;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import litebans.lib.kotlin.NoWhenBranchMatchedException;
import litebans.util.Unreachable;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.Server;
import net.md_5.bungee.api.connection.Connection.Unsafe;

public final class DummyBungeeServer implements Server {
   public final ServerInfo serverInfo;

   public DummyBungeeServer(ServerInfo var1) {
      this.serverInfo = var1;
   }

   public InetSocketAddress getAddress() {
      Unreachable.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   public SocketAddress getSocketAddress() {
      Unreachable.getVoid();
      throw new NoWhenBranchMatchedException();
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
      Unreachable.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   public ServerInfo getInfo() {
      return this.serverInfo;
   }

   public void sendData(String var1, byte[] var2) {
   }
}
