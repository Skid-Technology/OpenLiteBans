package litebans.platform.fabric;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import litebans.platform.LiteBansPlatform;
import litebans.platform.LiteCommand;
import litebans.platform.Sender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FabricCommand implements LiteCommand, Command, SuggestionProvider {
   private final LiteCommand liteCommand;
   private final String string;
   private final LiteBansPlatform liteBansPlatform;
   private final String[] stringArray;

   public FabricCommand(LiteCommand var1, String var2, LiteBansPlatform var3, String[] var4) {
      this.liteCommand = var1;
      this.string = var2;
      this.liteBansPlatform = var3;
      this.stringArray = var4;
   }

   public int run(CommandContext var1) {
      Sender var2 = this.liteBansPlatform.getSender(var1.getSource());
      String[] var3 = var1.getInput().split(" ");
      if (var3.length >= 2) {
         String[] var4 = Arrays.copyOfRange(var3, 1, var3.length);
         this.execute(var2, var4);
      } else {
         this.execute(var2, new String[0]);
      }

      return 1;
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

   public CompletableFuture getSuggestions(CommandContext var1, SuggestionsBuilder var2) {
      Sender var3 = this.liteBansPlatform.getSender(var1.getSource());
      String[] var4 = var1.getInput().split(" ");
      List var5 = this.suggest(var3, var4);
      var5.forEach(var2::suggest);
      return var2.buildFuture();
   }

   public LiteCommand getLiteCommand() {
      return this.liteCommand;
   }

   @Override
   public String[] getAliases() {
      return this.stringArray;
   }
}
