package litebans.util;

import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;
import litebans.lib.kotlin.comparisons.ComparisonsKt;

public final class SortComparator5 implements Comparator {
   @Override
   public final int compare(Object var1, Object var2) {
      Entry var3 = (Entry)var2;
      Comparable var10000 = ((List)var3.getValue()).size();
      var3 = (Entry)var1;
      Comparable var5 = var10000;
      return ComparisonsKt.getInt(var5, ((List)var3.getValue()).size());
   }
}
