package litebans.api.impl;

import java.util.Collection;
import litebans.api.Events$Listener;
import litebans.database.PunishmentStorage;

public final class BroadcastSentNotifyTask implements Runnable {
   public final Collection collection;
   public final PunishmentStorage punishmentStorage;
   public final CharSequence charSequence;
   public final String string;

   public BroadcastSentNotifyTask(Collection var1, PunishmentStorage var2, CharSequence var3, String var4) {
      this.collection = var1;
      this.punishmentStorage = var2;
      this.charSequence = var3;
      this.string = var4;
   }

   @Override
   public final void run() {
      Iterable var1 = this.collection;
      PunishmentStorage var2 = this.punishmentStorage;

      for (Object var5 : var1) {
         Events$Listener var6 = (Events$Listener)var5;

         try {
            var6.broadcastSent(this.charSequence.toString(), this.string);
         } catch (Throwable var11) {
            EventDispatcher.INSTANCE.handlePunishmentStorage(var2, var11);
         }
      }
   }
}
