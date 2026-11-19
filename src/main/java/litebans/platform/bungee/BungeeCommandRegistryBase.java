package litebans.platform.bungee;

import litebans.lib.kotlin.jvm.internal.MetadataAnnotation;
import litebans.platform.CommandRegistry;
import litebans.platform.LiteBansPlatform;
import litebans.platform.LiteCommand;
import net.md_5.bungee.api.plugin.Plugin;

@MetadataAnnotation(
   a = 1
)
public abstract class BungeeCommandRegistryBase extends CommandRegistry {
   public BungeeCommandRegistryBase(LiteBansPlatform var1) {
      super(var1);
   }

   @Override
   public boolean isSupported() {
      return this.platform.getPlatformId() == 1;
   }

   @Override
   public void onLoad() {
      super.onLoad();

      for (Object var3 : this.getCommands()) {
         LiteCommand var2 = (LiteCommand) var3;
         this.handleLiteCommand2(new BungeeCommand(var2, var2.getPermission(), this.platform, new String[0]));
      }
   }

   public void handleLiteCommand2(LiteCommand var1) {
      Plugin var2 = (Plugin)this.platform;
      var2.getProxy().getPluginManager().registerCommand(var2, (BungeeCommand)var1);
   }
}
