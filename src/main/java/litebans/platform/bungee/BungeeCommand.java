package litebans.platform.bungee;

import java.util.List;
import litebans.lib.kotlin.jvm.internal.KtAnnotation3;
import litebans.lib.kotlin.jvm.internal.MetadataAnnotation;
import litebans.platform.LiteBansPlatform;
import litebans.platform.LiteCommand;
import litebans.platform.Sender;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;
import org.jetbrains.annotations.NotNull;

@KtAnnotation3
@MetadataAnnotation(
   a = 1
)
public class BungeeCommand extends Command implements LiteCommand, TabExecutor {
   private final LiteCommand liteCommand;
   private final LiteBansPlatform liteBansPlatform;
   private final String[] stringArray;

   public BungeeCommand(LiteCommand var1, String var2, LiteBansPlatform var3, String[] var4) {
      super(var1.getName(), var2, var4);
      this.liteCommand = var1;
      this.liteBansPlatform = var3;
      this.stringArray = var4;
   }

   public void execute(CommandSender var1, String[] var2) {
      this.execute(this.liteBansPlatform.getSender(var1), var2);
   }

   @Override
   public void execute(@NotNull Sender var1, String[] var2) {
      try {
         this.liteBansPlatform.getCommandRegistry().executeCommand(this.liteCommand, var1, var2);
      } catch (Throwable var4) {
         var4.printStackTrace();
      }
   }

   @NotNull
   @Override
   public List suggest(@NotNull Sender var1, String[] var2) {
      return this.liteBansPlatform.getCommandRegistry().suggest(this, this.getName(), var1, var2);
   }

   public Iterable onTabComplete(CommandSender var1, String[] var2) {
      return this.suggest(this.liteBansPlatform.getSender(var1), var2);
   }

   @Override
   public String[] getAliases() {
      return this.stringArray;
   }
}
