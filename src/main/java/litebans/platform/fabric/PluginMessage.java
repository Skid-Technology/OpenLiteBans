package litebans.platform.fabric;

public class PluginMessage {
   public final String string;
   public final byte[] byteArray;

   public PluginMessage(String var1, byte[] var2) {
      this.string = var1;
      this.byteArray = var2;
   }

   public PluginMessage() {
      this.string = "";
      this.byteArray = new byte[0];
   }
}
