package litebans.platform.bukkit;

import litebans.lib.kotlin.jvm.internal.MetadataAnnotation;
import litebans.platform.CommandRegistry;
import litebans.platform.LiteBansPlatform;
import litebans.platform.Sender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

@MetadataAnnotation(
   a = 0
)
public abstract class BukkitCommandRegistryBase extends CommandRegistry implements CommandExecutor {
   public BukkitCommandRegistryBase(LiteBansPlatform var1) {
      super(var1);
   }

   public boolean onCommand(CommandSender var1, Command var2, String var3, String[] var4) {
      Sender var5 = this.platform.getSender(var1);
      this.dispatch(var5, var2.getName(), var4);
      return true;
   }
}
