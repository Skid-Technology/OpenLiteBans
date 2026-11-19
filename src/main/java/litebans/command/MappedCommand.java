package litebans.command;

import litebans.config.Message;
import litebans.lib.kotlin.jvm.internal.Function0;
import litebans.lib.kotlin.jvm.internal.KtAnnotation3;
import litebans.platform.CommandRegistry;
import litebans.platform.Sender;
import litebans.platform.fabric.FabricCommand;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@KtAnnotation3
public final class MappedCommand extends BaseCommand {
   private final String string2;
   private final FabricCommand fabricCommand;
   private final String string3;
   private final CommandRegistry commandRegistry;

   public MappedCommand(@NotNull String var1, @NotNull String var2, @Nullable FabricCommand var3, @Nullable String var4, @NotNull CommandRegistry var5) {
      super(var1, var5.platform);
      this.string2 = var2;
      this.fabricCommand = var3;
      this.string3 = var4;
      this.commandRegistry = var5;
   }

   @Override
   public String getPermission() {
      return this.string3;
   }

   @Override
   public void execute(@NotNull Sender var1, @NotNull String[] var2) {
      this.commandRegistry.dispatch(var1, this.string2, var2);
   }

   @Override
   public void requireArgs(@NotNull String[] var1, @NotNull Function0 var2) {
      super.requireArgs(var1, MappedCommand::getCharSequence);
   }

   private static final CharSequence getCharSequence(MappedCommand var0) {
      return Message.getMessage2(var0.string2 + "_USAGE");
   }
}
