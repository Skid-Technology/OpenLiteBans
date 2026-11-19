package litebans.punishment;

import litebans.command.CommandRunnable;
import litebans.core.NotificationManager;
import litebans.database.DatabaseManager;
import litebans.database.PunishmentStorage;
import litebans.lib.compress.archivers.tar.TargetParser;
import litebans.lib.kotlin.Unit;
import litebans.lib.kotlin.collections.ArraysKt;
import litebans.platform.LiteBansPlatform;
import litebans.sync.Throttler;
import org.jetbrains.annotations.NotNull;

public final class BannedJoinNotifier extends CommandRunnable {
   private final String string;
   private final CharSequence charSequence;
   private final boolean flag;

   public BannedJoinNotifier(@NotNull LiteBansPlatform var1, @NotNull String var2, @NotNull CharSequence var3, boolean var4) {
      super(var1);
      this.string = var2;
      this.charSequence = var3;
      this.flag = var4;
   }

   @Override
   public void run() {
      Throttler var1 = (Throttler)this.getPlatform().getModule(Throttler.class);
      if (!var1.isThrottled((byte)2, TargetParser.getString2(this.string))) {
         CommandRunnable var2 = this;
         ((DatabaseManager)var2.getPlatform().getModule(DatabaseManager.class)).runWithStorageAsync(st -> getUnit(this, (PunishmentStorage) st));
      }
   }

   private static final Unit getUnit(BannedJoinNotifier var0, PunishmentStorage var1) {
      CommandRunnable var2 = var0;
      NotificationManager var3 = (NotificationManager)var2.getPlatform().getModule(NotificationManager.class);
      NotificationManager.broadcastDefault(var3, var1, var0.charSequence, "banned_join", null, null, false, false, null, 124, null);
      if (var0.flag && var1.getDatabaseManager().isSyncEnabled()) {
         Flag var10001 = Flag.NOTIFY_BANNED_JOIN;
         CharSequence[] var4 = new CharSequence[]{var0.string, var0.charSequence};
         PunishmentService.broadcastDefault(var1, var10001, ArraysKt.getString(var4, "\ufeff", null, null, 0, null, null, 62, null), null, null, 12, null);
      }

      return Unit.INSTANCE;
   }
}
