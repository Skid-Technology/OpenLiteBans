package litebans.platform.fabric;

import com.mojang.brigadier.CommandDispatcher;
import litebans.FabricPlugin;
import litebans.lib.kotlin.jvm.internal.MetadataAnnotation;
import litebans.platform.CommandRegistry;
import litebans.platform.LiteBansPlatform;
import litebans.platform.LiteCommand;

@MetadataAnnotation(
   a = 3
)
public class FabricCommandRegistryBase extends CommandRegistry {
   public FabricCommandRegistryBase(LiteBansPlatform var1) {
      super(var1);
   }

   @Override
   public boolean isSupported() {
      return this.platform.getPlatformId() == 3;
   }

   @Override
   public void onEnable() {
   }

   @Override
   public void onLoad() {
      super.onLoad();
      ((FabricPlugin)this.platform).getFabricServerAdapter().handleFunction(this::getObject);
   }

   public void handleFabricCommand(FabricCommand var1, CommandDispatcher var2) {
      ((FabricPlugin)this.platform).getFabricServerAdapter().handleFabricCommand(var1, var2);
   }

   private final Object getObject(Object var1) {
      for (LiteCommand var3 : this.getCommands()) {
         this.handleFabricCommand(new FabricCommand(var3, var3.getPermission(), this.platform, var3.getAliases()), (CommandDispatcher)var1);
      }

      return void.class;
   }
}
