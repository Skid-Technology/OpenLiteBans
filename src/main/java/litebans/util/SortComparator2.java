package litebans.util;

import java.util.Comparator;
import litebans.lib.kotlin.comparisons.ComparisonsKt;
import litebans.punishment.GroupLimits;

public final class SortComparator2 implements Comparator {
   @Override
   public final int compare(Object var1, Object var2) {
      GroupLimits var3 = (GroupLimits)var1;
      boolean var5 = var3.getOther().isTemplateOnly();
      Comparable var10000 = var5 ? 1 : 0;
      var3 = (GroupLimits)var2;
      Comparable var7 = var10000;
      var5 = var3.getOther().isTemplateOnly();
      return ComparisonsKt.getInt(var7, var5 ? 1 : 0);
   }
}
