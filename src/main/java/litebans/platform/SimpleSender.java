package litebans.platform;

import java.util.UUID;
import litebans.lib.kotlin.NoWhenBranchMatchedException;
import litebans.util.Unreachable;

public final class SimpleSender implements Sender {
   public final String string;
   public final String string2;
   public final UUID uuid2;

   public SimpleSender(String var1, String var2, UUID var3) {
      this.string = var1;
      this.string2 = var2;
      this.uuid2 = var3;
   }

   public Void getVoid(String var1) {
      Unreachable.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public String getDisplayName() {
      return this.string;
   }

   public Void getVoid() {
      Unreachable.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public String getIp() {
      return this.string2;
   }

   @Override
   public String getName() {
      return this.string;
   }

   @Override
   public String getServerName() {
      return null;
   }

   @Override
   public UUID getUuid() {
      return this.uuid2;
   }

   @Override
   public String getUuidString() {
      return this.uuid2.toString();
   }

   @Override
   public boolean hasPermission(String var1) {
      return false;
   }

   @Override
   public boolean isConsole() {
      return false;
   }

   @Override
   public boolean isOnline() {
      return false;
   }

   @Override
   public boolean isPlayer() {
      return true;
   }

   @Override
   public void kick(String var1) {
   }

   @Override
   public void sendMessage(String var1) {
   }

   @Override
   public void sendJsonMessage(String var1) {
   }

   @Override
   public void sendPluginMessage(String var1, byte[] var2) {
   }

   @Override
   public void dispatchCommand(String var1) {
      this.getVoid(var1);
   }

   @Override
   public Object getHandle() {
      return this.getVoid();
   }
}
