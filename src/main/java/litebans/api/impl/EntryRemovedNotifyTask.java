package litebans.api.impl;

import java.util.Collection;
import litebans.api.Entry;
import litebans.api.Events$Listener;
import litebans.database.PunishmentStorage;

public final class EntryRemovedNotifyTask implements Runnable {
   public final Collection collection;
   public final PunishmentStorage punishmentStorage;
   public final Entry entry;

   public EntryRemovedNotifyTask(Collection var1, PunishmentStorage var2, Entry var3) {
      this.collection = var1;
      this.punishmentStorage = var2;
      this.entry = var3;
   }

   @Override
   public final void run() {
      Iterable var1 = this.collection;
      PunishmentStorage var2 = this.punishmentStorage;

      for (Object var5 : var1) {
         Events$Listener var6 = (Events$Listener)var5;

         try {
            var6.entryRemoved(this.entry);
         } catch (Throwable var11) {
            EventDispatcher.INSTANCE.handlePunishmentStorage(var2, var11);
         }
      }
   }
}
