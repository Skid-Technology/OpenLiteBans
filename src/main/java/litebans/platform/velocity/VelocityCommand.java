package litebans.platform.velocity;

import com.velocitypowered.api.command.SimpleCommand;
import com.velocitypowered.api.command.SimpleCommand.Invocation;
import java.util.List;
import litebans.lib.kotlin.jvm.internal.KtAnnotation3;
import litebans.lib.kotlin.jvm.internal.MetadataAnnotation;
import litebans.platform.LiteBansPlatform;
import litebans.platform.LiteCommand;
import litebans.platform.Sender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@KtAnnotation3
@MetadataAnnotation(
   a = 2
)
public class VelocityCommand implements LiteCommand, SimpleCommand {
   private final LiteCommand liteCommand;
   private final String string;
   private final LiteBansPlatform liteBansPlatform;
   private final String[] stringArray;

   public VelocityCommand(LiteCommand var1, String var2, LiteBansPlatform var3, String[] var4) {
      this.liteCommand = var1;
      this.string = var2;
      this.liteBansPlatform = var3;
      this.stringArray = var4;
   }

   public List suggest(Invocation var1) {
      return this.suggest(this.liteBansPlatform.getSender(var1.source()), (String[])var1.arguments());
   }

   @Override
   public void execute(Invocation var1) {
      this.execute(this.liteBansPlatform.getSender(var1.source()), (String[])var1.arguments());
   }

   @Override
   public void execute(@NotNull Sender var1, String[] var2) {
      try {
         this.liteBansPlatform.getCommandRegistry().executeCommand(this.liteCommand, var1, var2);
      } catch (Throwable var4) {
         var4.printStackTrace();
      }
   }

   @Override
   public String getName() {
      return this.liteCommand.getName();
   }

   @Nullable
   @Override
   public String getPermission() {
      return this.string;
   }

   @NotNull
   @Override
   public List suggest(@NotNull Sender var1, String[] var2) {
      return this.liteBansPlatform.getCommandRegistry().suggest(this, this.getName(), var1, var2);
   }

   @Override
   public boolean hasPermission(Invocation var1) {
      String var2 = this.getPermission();
      return var2 == null ? true : var1.source().hasPermission(var2);
   }

   @Override
   public String[] getAliases() {
      return this.stringArray;
   }
}
