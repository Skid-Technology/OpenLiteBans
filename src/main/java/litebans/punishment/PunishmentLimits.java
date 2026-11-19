package litebans.punishment;

import litebans.lib.kotlin.NoWhenBranchMatchedException;
import litebans.util.Unreachable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class PunishmentLimits {
   private final long longValue;
   private final long mute;
   private final long warn;
   private final long kick;
   private final long redo;

   public PunishmentLimits(long var1, long var3, long var5, long var7, long var9) {
      this.longValue = var1;
      this.mute = var3;
      this.warn = var5;
      this.kick = var7;
      this.redo = var9;
   }

   public final long getRedo() {
      return this.redo;
   }

   public final long getLong(@NotNull PunishmentEntry var1) {
      long var10000;
      switch (PunishmentLimitsWhenMappings.switchMap[var1.getType().ordinal()]) {
         case 1:
            var10000 = this.longValue;
            break;
         case 2:
            var10000 = this.mute;
            break;
         case 3:
            var10000 = this.warn;
            break;
         case 4:
            var10000 = this.kick;
            break;
         default:
            Unreachable.getVoid();
            throw new NoWhenBranchMatchedException();
      }

      return var10000;
   }

   @Override
   public String toString() {
      return "(a=" + this.longValue + ", mute=" + this.mute + ", warn=" + this.warn + ", kick=" + this.kick + ", redo=" + this.redo + ')';
   }

   @Override
   public int hashCode() {
      int var1 = Long.hashCode(this.longValue);
      var1 = var1 * 31 + Long.hashCode(this.mute);
      var1 = var1 * 31 + Long.hashCode(this.warn);
      var1 = var1 * 31 + Long.hashCode(this.kick);
      return var1 * 31 + Long.hashCode(this.redo);
   }

   @Override
   public boolean equals(@Nullable Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof PunishmentLimits)) {
         return false;
      } else {
         PunishmentLimits var2 = (PunishmentLimits)var1;
         if (this.longValue != var2.longValue) {
            return false;
         } else if (this.mute != var2.mute) {
            return false;
         } else if (this.warn != var2.warn) {
            return false;
         } else {
            return this.kick != var2.kick ? false : this.redo == var2.redo;
         }
      }
   }
}
