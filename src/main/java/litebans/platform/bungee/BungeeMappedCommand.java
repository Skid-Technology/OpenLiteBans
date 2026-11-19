package litebans.platform.bungee;

import litebans.command.BaseCommand;
import litebans.lib.kotlin.jvm.internal.KtAnnotation3;
import litebans.platform.Sender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@KtAnnotation3
public final class BungeeMappedCommand extends BaseCommand {
   private final String string2;
   private final BungeeCommandRegistry bungeeCommandRegistry;

   public BungeeMappedCommand(@NotNull String var1, @Nullable String var2, @NotNull BungeeCommandRegistry var3) {
      super(var1, var3.platform);
      this.string2 = var2;
      this.bungeeCommandRegistry = var3;
   }

   @Override
   public String getPermission() {
      return this.string2;
   }

   @Override
   public void execute(@NotNull Sender var1, @NotNull String[] var2) {
      this.bungeeCommandRegistry.dispatch(var1, this.getName(), var2);
   }
}
