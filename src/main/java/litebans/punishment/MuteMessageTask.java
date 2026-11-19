package litebans.punishment;

import litebans.command.CommandRunnable;
import litebans.config.Message;
import litebans.core.LiteBansCore;
import litebans.core.NotificationManager;
import litebans.database.DatabaseManager;
import litebans.database.PunishmentStorage;
import litebans.lib.kotlin.Unit;
import litebans.platform.LiteBansPlatform;
import litebans.platform.Sender;
import litebans.sync.Throttler;
import org.jetbrains.annotations.NotNull;

public final class MuteMessageTask extends CommandRunnable {
   private final PunishmentEntry punishmentEntry;
   private final CharSequence charSequence;

   public MuteMessageTask(@NotNull LiteBansPlatform var1, @NotNull PunishmentEntry var2, @NotNull CharSequence var3) {
      super(var1);
      this.punishmentEntry = var2;
      this.charSequence = var3;
   }

   @Override
   public void run() {
      CommandRunnable var1 = this;
      if (((LiteBansCore)var1.getPlatform().getModule(LiteBansCore.class)).getConfig().isLocalMessages()) {
         var1 = this;
         ((DatabaseManager)var1.getPlatform().getModule(DatabaseManager.class)).runWithStorageAsync(st -> getUnit(this, (PunishmentStorage) st));
      }
   }

   private final Message getMessage() {
      return this.punishmentEntry.isPermanent() ? Message.MUTED_PERMANENT : Message.MUTED;
   }

   private static final Unit getUnit(MuteMessageTask var0, PunishmentStorage var1) {
      Throttler var2 = (Throttler)var1.getPlatform().getModule(Throttler.class);
      CharSequence var10000 = EntryPlaceholders.getCharSequenceDefault(var1, var0.punishmentEntry, 0, 0, 6, null);
      if (var10000 == null) {
         var10000 = var0.getMessage();
      }

      CharSequence var3 = var10000;
      PunishmentService.reply(var0, EntryPlaceholders.getStringDefault(var1, var0.punishmentEntry, var0.getCurrentSender(), var3, 0L, 8, null));
      if (!var2.isThrottled((byte)1, var0.getCurrentSender().getUuidString())) {
         CommandRunnable var4 = var0;
         if (((LiteBansCore)var4.getPlatform().getModule(LiteBansCore.class)).getConfig().isNotifyMutedPlayerChat()) {
            PunishmentEntry var10001 = var0.punishmentEntry;
            Sender var10002 = var0.getCurrentSender();
            CommandRunnable var6 = var0;
            String var9 = EntryPlaceholders.getStringDefault(
               var1,
               var10001,
               var10002,
               EntryPlaceholders.getString(
                  Message.MUTE_NOTIFY
                     .getString(
                        "mutedPlayer",
                        ((LiteBansCore)var6.getPlatform().getModule(LiteBansCore.class)).getConfig().isUseDisplayNames()
                           ? var0.getCurrentSender().getDisplayName()
                           : var0.getCurrentSender().getName()
                     ),
                  "message",
                  var0.charSequence
               ),
               0L,
               8,
               null
            );
            CommandRunnable var5 = var0;
            NotificationManager var10 = (NotificationManager)var5.getPlatform().getModule(NotificationManager.class);
            NotificationManager.broadcastDefault(
               var10, var1, var9, "mute", PunishmentType.MUTE, var0.punishmentEntry.getServerOrigin(), false, false, null, 112, null
            );
            PunishmentService.broadcastDefault(var1, Flag.NOTIFY_MUTE, var9, null, null, 12, null);
         }
      }

      return Unit.INSTANCE;
   }
}
