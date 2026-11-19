package litebans.punishment;

import litebans.command.CommandRunnable;
import litebans.core.LiteBansCore;
import litebans.database.DatabaseManager;
import litebans.database.PunishmentStorage;
import litebans.lib.kotlin.Unit;
import litebans.platform.LiteBansPlatform;
import litebans.randomid.LongBitSet;
import org.jetbrains.annotations.NotNull;

public final class PunishmentCheckTask extends CommandRunnable {
   public PunishmentCheckTask(@NotNull LiteBansPlatform var1) {
      super(var1);
   }

   @Override
   public void run() {
      CommandRunnable var1 = this;
      ((DatabaseManager)var1.getPlatform().getModule(DatabaseManager.class)).runWithStorageAsync(st -> getUnit(this, (PunishmentStorage) st));
   }

   private static final Unit getUnit(PunishmentCheckTask var0, PunishmentStorage var1) {
      CommandRunnable var2 = var0;
      MuteManager var3 = (MuteManager)var2.getPlatform().getModule(MuteManager.class);
      DatabaseManager var5 = var1.getDatabaseManager();
      long var6 = DatabaseManager.currentTimeMillisDefault(var1.getDatabaseManager(), false, 1, null);
      int var8 = 21600000 + new LongBitSet(0L, 1, null).getInt(32400000);
      var5.setLongValue2(var6 + (long)var8);
      CommandRunnable var9 = var0;
      if (((LiteBansCore)var9.getPlatform().getModule(LiteBansCore.class)).getConfig().isMutesEnabled()) {
         var3.handlePunishmentStorage(var1);
      }

      return Unit.INSTANCE;
   }
}
