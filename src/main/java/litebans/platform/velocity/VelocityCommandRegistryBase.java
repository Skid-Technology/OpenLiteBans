package litebans.platform.velocity;

import com.velocitypowered.api.command.CommandManager;
import litebans.VelocityPlugin;
import litebans.lib.kotlin.jvm.internal.MetadataAnnotation;
import litebans.platform.CommandRegistry;
import litebans.platform.LiteBansPlatform;
import litebans.platform.LiteCommand;

@MetadataAnnotation(
   a = 2
)
public class VelocityCommandRegistryBase extends CommandRegistry {
   public VelocityCommandRegistryBase(LiteBansPlatform var1) {
      super(var1);
   }

   @Override
   public boolean isSupported() {
      return this.platform.getPlatformId() == 2;
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onLoad() {
      super.onLoad();

      for (Object var2o : this.getCommands()) {
         LiteCommand var2 = (LiteCommand) var2o;
         this.handleLiteCommand2(new VelocityCommand(var2, var2.getPermission(), this.platform, var2.getAliases()));
      }
   }

   public void handleLiteCommand2(LiteCommand var1) {
      VelocityPlugin var2 = (VelocityPlugin)this.platform;
      CommandManager var3 = var2.proxyServer.getCommandManager();
      var3.register(var1.getName(), (VelocityCommand)var1, var1.getAliases());
   }
}
