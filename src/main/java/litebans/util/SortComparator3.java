package litebans.util;

import java.util.Comparator;
import litebans.lib.kotlin.comparisons.ComparisonsKt;
import litebans.randomid.IdScrambler;

public final class SortComparator3 implements Comparator {
   public final IdScrambler idScrambler;

   public SortComparator3(IdScrambler var1) {
      this.idScrambler = var1;
   }

   @Override
   public final int compare(Object var1, Object var2) {
      String var3 = (String)var2;
      CharSequence var5 = var3;
      int var6 = 0;

      for (int var7 = 0; var7 < var5.length(); var7++) {
         char var8 = var5.charAt(var7);
         int var12 = this.idScrambler.getInt(var8);
         var6 += var12;
      }

      Comparable var10000 = var6;
      var3 = (String)var1;
      Comparable var13 = var10000;
      var5 = var3;
      var6 = 0;

      for (int var17 = 0; var17 < var5.length(); var17++) {
         char var18 = var5.charAt(var17);
         int var19 = this.idScrambler.getInt(var18);
         var6 += var19;
      }

      return ComparisonsKt.getInt(var13, var6);
   }
}
