package litebans.command;

import java.util.concurrent.CopyOnWriteArrayList;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.punishment.Flag;

public final class ListAliasPredicate implements AliasPredicate {
   public final String string;
   public final CopyOnWriteArrayList copyOnWriteArrayList;

   public ListAliasPredicate(String var1, CopyOnWriteArrayList var2) {
      this.string = var1;
      this.copyOnWriteArrayList = var2;
   }

   @Override
   public boolean checkCommandAlias(CommandAlias var1, Flag var2, String var3) {
      if (var2 == Flag.PONG && Intrinsics.checkObject(var3, this.string) && var1 != null) {
         this.copyOnWriteArrayList.add(var1);
      }

      return true;
   }
}
