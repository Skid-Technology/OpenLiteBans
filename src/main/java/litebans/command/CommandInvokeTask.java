package litebans.command;

import litebans.platform.Sender;
import org.jetbrains.annotations.NotNull;

public class CommandInvokeTask implements Runnable {
   private final Sender sender;
   private final String[] stringArray;
   private final BaseCommand baseCommand;
   private final String string;

   public CommandInvokeTask(@NotNull Sender var1, @NotNull String[] var2, @NotNull BaseCommand var3, @NotNull String var4) {
      this.sender = var1;
      this.stringArray = var2;
      this.baseCommand = var3;
      this.string = var4;
   }

   @Override
   public void run() {
      this.baseCommand.dispatch(this.sender, this.stringArray, this.string);
   }
}
