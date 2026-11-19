package litebans.sync;

import litebans.command.AliasPredicate;
import litebans.command.CommandRunnable;

public final class SyncBroadcastTask implements Runnable {
   public final CommandRunnable commandRunnable;
   public final AliasPredicate aliasPredicate;

   public SyncBroadcastTask(CommandRunnable var1, AliasPredicate var2) {
      this.commandRunnable = var1;
      this.aliasPredicate = var2;
   }

   @Override
   public final void run() {
      SyncManager var1 = (SyncManager)this.commandRunnable.getPlatform().getModule(SyncManager.class);
      var1.removeListener(this.aliasPredicate);
   }
}
