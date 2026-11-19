package litebans.util;

import java.util.Comparator;
import litebans.lib.kotlin.Pair;
import litebans.lib.kotlin.comparisons.ComparisonsKt;

public final class SortComparator7 implements Comparator {
   @Override
   public final int compare(Object var1, Object var2) {
      Pair var3 = (Pair)var2;
      Comparable var10000 = (Long)var3.getObject();
      var3 = (Pair)var1;
      Comparable var5 = var10000;
      return ComparisonsKt.getInt(var5, (Long)var3.getObject());
   }
}
