package litebans.platform;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import litebans.core.PluginModule;
import org.jetbrains.annotations.Nullable;

public abstract class CommandRegistry extends PluginModule {
   private final Map commandsByName = new HashMap();
   private Collection commands;

   public CommandRegistry(LiteBansPlatform var1) {
      super(var1);
   }

   @Override
   public void onLoad() {
      this.commands = this.discoverCommands();
      this.registerAll();
   }

   public Collection discoverCommands() {
      return this.platform.getDiagnostics().getList(LiteCommand.class, new Class[]{LiteBansPlatform.class}, new Object[]{this.platform});
   }

   public void executeCommand(LiteCommand var1, Sender var2, String[] var3) {
      var1.execute(var2, var3);
   }

   public void dispatch(Sender var1, String var2, String[] var3) {
      LiteCommand var4 = this.getCommand(var2);
      if (var4 != null) {
         this.executeCommand(var4, var1, var3);
      }
   }

   @Nullable
   public LiteCommand getCommand(String var1) {
      return (LiteCommand)this.commandsByName.get(var1);
   }

   public Collection getCommands() {
      return this.commands;
   }

   public List suggest(@Nullable LiteCommand var1, String var2, Sender var3, String[] var4) {
      this.platform.getLogger().warning("No suggest implementation for /" + var2);
      return Collections.emptyList();
   }

   private final void register(LiteCommand var1) {
      this.register(var1.getName(), var1);
   }

   public void register(String var1, LiteCommand var2) {
      this.commandsByName.put(var1, var2);
   }

   public void registerAll() {
      Collection var1 = this.commands;
      if (var1 == null) {
         throw new IllegalStateException();
      } else {
         for (Object var3 : var1) {
            this.register((LiteCommand) var3);
         }
      }
   }
}
