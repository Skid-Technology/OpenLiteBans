package litebans.command;

import litebans.lib.kotlin.jvm.internal.KtAnnotation3;
import litebans.platform.CommandRegistry;
import litebans.platform.Sender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@KtAnnotation3
public final class RegisteredCommand extends BaseCommand {
   private final String string2;
   private final CommandRegistry commandRegistry;

   public RegisteredCommand(@NotNull String var1, @Nullable String var2, @NotNull CommandRegistry var3) {
      super(var1, var3.platform);
      this.string2 = var2;
      this.commandRegistry = var3;
   }

   @Override
   public String getPermission() {
      return this.string2;
   }

   @Override
   public void execute(@NotNull Sender var1, @NotNull String[] var2) {
      this.commandRegistry.dispatch(var1, this.getName(), var2);
   }
}
