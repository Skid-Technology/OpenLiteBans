package litebans.command;

import litebans.chat.ChatColorCode;
import litebans.database.DatabaseManager;
import litebans.database.PunishmentStorage;
import litebans.database.QueryRunner;
import litebans.database.SqlString;
import litebans.database.Table;
import litebans.lib.kotlin.Unit;
import litebans.platform.LiteBansPlatform;
import litebans.punishment.Flag;
import litebans.punishment.PunishmentService;
import litebans.sync.SyncManager;
import org.jetbrains.annotations.NotNull;

public final class AcceptDisclaimerSubcommand extends CommandRunnable {
   public AcceptDisclaimerSubcommand(@NotNull LiteBansPlatform var1) {
      super(var1);
   }

   @Override
   public void run() {
      CommandRunnable var1 = this;
      ((DatabaseManager)var1.getPlatform().getModule(DatabaseManager.class)).runWithStorageAsync(st -> getUnit(this, (PunishmentStorage) st));
   }

   private static final Unit getUnit(AcceptDisclaimerSubcommand var0, PunishmentStorage var1) {
      var1.getDatabaseManager().setFlag3(true);
      Table var2 = Table.CONFIG;
      QueryRunner.getResultSet(var1.prepare(SqlString.getSqlString(SqlString.getString4(SqlString.getString2("UPDATE " + var2), "accept=1"))));
      PunishmentService.reply(var0, ChatColorCode.getString2("Disclaimer accepted!"));
      SyncManager var3 = (SyncManager)var1.getPlatform().getModule(SyncManager.class);
      SyncManager.publishDefault(var3, Flag.CONFIG_RELOAD, var1, null, 0, 12, null);
      return Unit.INSTANCE;
   }
}
