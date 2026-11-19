package litebans.punishment;

import litebans.lib.kotlin.NoWhenBranchMatchedException;
import litebans.util.Unreachable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class BanMuteLimits {
   private final long longValue;
   private final long mute;

   public BanMuteLimits(long var1, long var3) {
      this.longValue = var1;
      this.mute = var3;
   }

   public final long getLong(@NotNull PunishmentType var1) {
      long var10000;
      switch (BanMuteLimitsWhenMappings.switchMap[var1.ordinal()]) {
         case 1:
            var10000 = this.longValue;
            break;
         case 2:
            var10000 = this.mute;
            break;
         default:
            Unreachable.getVoid();
            throw new NoWhenBranchMatchedException();
      }

      return var10000;
   }

   @Override
   public String toString() {
      return "(a=" + this.longValue + ", mute=" + this.mute + ')';
   }

   @Override
   public int hashCode() {
      int var1 = Long.hashCode(this.longValue);
      return var1 * 31 + Long.hashCode(this.mute);
   }

   @Override
   public boolean equals(@Nullable Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof BanMuteLimits)) {
         return false;
      } else {
         BanMuteLimits var2 = (BanMuteLimits)var1;
         return this.longValue != var2.longValue ? false : this.mute == var2.mute;
      }
   }
}
