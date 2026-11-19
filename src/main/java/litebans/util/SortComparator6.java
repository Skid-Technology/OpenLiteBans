package litebans.util;

import java.util.Comparator;
import litebans.lib.kotlin.comparisons.ComparisonsKt;
import litebans.randomid.IdScramblerState;

public final class SortComparator6 implements Comparator {
   @Override
   public final int compare(Object var1, Object var2) {
      IdScramblerState var3 = (IdScramblerState)var2;
      Comparable var10000 = var3.getLong();
      var3 = (IdScramblerState)var1;
      Comparable var5 = var10000;
      return ComparisonsKt.getInt(var5, var3.getLong());
   }
}
