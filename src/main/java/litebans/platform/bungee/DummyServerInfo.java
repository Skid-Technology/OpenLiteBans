package litebans.platform.bungee;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Collection;
import litebans.lib.kotlin.NoWhenBranchMatchedException;
import litebans.util.Unreachable;
import net.md_5.bungee.api.Callback;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.config.ServerInfo;

public final class DummyServerInfo implements ServerInfo {
   public String getName() {
      return "lobby";
   }

   public InetSocketAddress getAddress() {
      Unreachable.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   public SocketAddress getSocketAddress() {
      Unreachable.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   public Collection getPlayers() {
      return new ArrayList();
   }

   public String getMotd() {
      Unreachable.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   public boolean isRestricted() {
      return false;
   }

   public String getPermission() {
      return "";
   }

   public boolean canAccess(CommandSender var1) {
      return true;
   }

   public void sendData(String var1, byte[] var2) {
   }

   public boolean sendData(String var1, byte[] var2, boolean var3) {
      Unreachable.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   public void ping(Callback var1) {
   }
}
