package litebans.command;

import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;
import litebans.chat.ChatColorCode;
import litebans.core.LiteBansCore;
import litebans.core.MessageSender;
import litebans.database.DatabaseManager;
import litebans.database.PunishmentStorage;
import litebans.lib.kotlin.Unit;
import litebans.platform.LiteBansPlatform;
import litebans.platform.Sender;
import litebans.punishment.Flag;
import litebans.punishment.PunishmentService;
import litebans.sync.SyncManager;
import litebans.util.Formatter;
import org.jetbrains.annotations.NotNull;

public final class ServersSubcommand extends CommandRunnable {
   public ServersSubcommand(@NotNull LiteBansPlatform var1) {
      super(var1);
   }

   @Override
   public void run() {
      CommandRunnable var1 = this;
      ((DatabaseManager)var1.getPlatform().getModule(DatabaseManager.class)).runWithStorageAsync(st -> getUnit(this, (PunishmentStorage) st));
   }

   private static final void handleSyncManager(SyncManager var0, ListAliasPredicate var1, CopyOnWriteArrayList var2, Sender var3) {
      var0.removeListener(var1);
      int var4 = var2.size();
      if (var4 <= 1) {
         CharSequence var6 = ArgUtils.getString(ChatColorCode.GREEN, "There are no other servers connected to this database.");
         MessageSender.handleSenderDefault(CommandRunnable.messageSender, var3, var6, null, 4, null);
      } else {
         StringBuilder var10000 = new StringBuilder()
            .append(ArgUtils.getString(ChatColorCode.GREEN, "There are "))
            .append(ChatColorCode.GOLD)
            .append(var4)
            .append(ChatColorCode.GREEN)
            .append(" servers connected to this database: ");
         Collection var9 = var2;
         CharSequence var10 = var10000.append(Formatter.Companion.getString(var9.toArray(new Object[0]), ", ")).toString();
         MessageSender.handleSenderDefault(CommandRunnable.messageSender, var3, var10, null, 4, null);
      }
   }

   private static final Unit getUnit(ServersSubcommand var0, PunishmentStorage var1) {
      if (var1.getDatabaseManager().isSyncEnabled()) {
         SyncManager var2 = (SyncManager)var1.getPlatform().getModule(SyncManager.class);
         CommandRunnable var4 = var0;
         String var3 = ((LiteBansCore)var4.getPlatform().getModule(LiteBansCore.class)).getConfig().getString6();
         String var9 = String.valueOf(System.nanoTime() % (long)1000);
         CopyOnWriteArrayList var5 = new CopyOnWriteArrayList();
         var5.add(var1.getDatabaseManager().getCommandAlias());
         PunishmentService.reply(
            var0, ArgUtils.getString(ChatColorCode.GREEN, "Table prefix of this instance: \"") + ChatColorCode.GOLD + var3 + ChatColorCode.GREEN + '"'
         );
         PunishmentService.reply(var0, ArgUtils.getString(ChatColorCode.GREEN, "Checking for other servers connected to the database, please wait..."));
         ListAliasPredicate var6 = new ListAliasPredicate(var9, var5);
         var2.addListener(var6);
         SyncManager.publishDefault(var2, Flag.PING, var1, var9, 0, 8, null);
         Sender var7 = var0.getCurrentSender();
         LiteBansPlatform var10000 = var1.getPlatform();
         Runnable var10001 = () -> handleSyncManager(var2, var6, var5, var7);
         CommandRunnable var8 = var0;
         long var10002 = ((LiteBansCore)var8.getPlatform().getModule(LiteBansCore.class)).getConfig().getSyncPollTicks();
         var8 = var0;
         var10000.runAsyncLater(var10001, var10002 * (long)(((LiteBansCore)var8.getPlatform().getModule(LiteBansCore.class)).getConfig().getSyncPollSkip() + 1));
      }

      return Unit.INSTANCE;
   }
}
