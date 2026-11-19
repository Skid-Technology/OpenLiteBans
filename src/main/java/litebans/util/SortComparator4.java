package litebans.util;

import java.util.Comparator;
import litebans.lib.kotlin.comparisons.ComparisonsKt;
import litebans.punishment.GroupLimits;
import litebans.punishment.PunishmentEntry;
import litebans.punishment.PunishmentLimits;

public final class SortComparator4 implements Comparator {
   public final PunishmentEntry punishmentEntry;

   public SortComparator4(PunishmentEntry var1) {
      this.punishmentEntry = var1;
   }

   @Override
   public final int compare(Object var1, Object var2) {
      GroupLimits var3 = (GroupLimits)var1;
      PunishmentLimits var5 = var3.getCooldown2();
      Comparable var10000 = var5.getLong(this.punishmentEntry);
      var3 = (GroupLimits)var2;
      Comparable var6 = var10000;
      var5 = var3.getCooldown2();
      return ComparisonsKt.getInt(var6, var5.getLong(this.punishmentEntry));
   }
}
