package litebans.util;

import java.util.Comparator;
import litebans.lib.kotlin.comparisons.ComparisonsKt;
import litebans.punishment.BanMuteLimits;
import litebans.punishment.GroupLimits;
import litebans.punishment.PunishmentType;

public final class SortComparator8 implements Comparator {
   public final PunishmentType punishmentType;

   public SortComparator8(PunishmentType var1) {
      this.punishmentType = var1;
   }

   @Override
   public final int compare(Object var1, Object var2) {
      GroupLimits var3 = (GroupLimits)var2;
      BanMuteLimits var5 = var3.getDuration2();
      Comparable var10000 = var5.getLong(this.punishmentType);
      var3 = (GroupLimits)var1;
      Comparable var6 = var10000;
      var5 = var3.getDuration2();
      return ComparisonsKt.getInt(var6, var5.getLong(this.punishmentType));
   }
}
