package litebans.punishment;

import java.util.ArrayList;
import litebans.lib.kotlin.Unit;
import litebans.lib.kotlin.jvm.internal.Function1;
import litebans.randomid.IdScramblerState;
import litebans.sync.SyncManager;

public final class CollectionAddFunction implements Function1 {
   public final ArrayList arrayList;
   public final SyncManager syncManager;

   public CollectionAddFunction(ArrayList var1, SyncManager var2) {
      this.arrayList = var1;
      this.syncManager = var2;
   }

   public final void handleIdScramblerState(IdScramblerState var1) {
      this.arrayList.add(var1.getByteArray());
   }

   @Override
   public Object invoke(Object var1) {
      this.handleIdScramblerState((IdScramblerState)var1);
      return Unit.INSTANCE;
   }
}
