package litebans.punishment;

import litebans.lib.kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class GroupLimits {
   private final String string;
   private final BanMuteLimits duration;
   private final PunishmentLimits cooldown;
   private final GroupLimitOther other;

   public GroupLimits(@NotNull String var1, @NotNull BanMuteLimits var2, @NotNull PunishmentLimits var3, @NotNull GroupLimitOther var4) {
      this.string = var1;
      this.duration = var2;
      this.cooldown = var3;
      this.other = var4;
   }

   public final String getString() {
      return this.string;
   }

   public final BanMuteLimits getDuration() {
      return this.duration;
   }

   public final PunishmentLimits getCooldown() {
      return this.cooldown;
   }

   public final GroupLimitOther getOther() {
      return this.other;
   }

   public final BanMuteLimits getDuration2() {
      return this.duration;
   }

   public final PunishmentLimits getCooldown2() {
      return this.cooldown;
   }

   @Override
   public String toString() {
      return "(a=" + this.string + ", duration=" + this.duration + ", cooldown=" + this.cooldown + ", other=" + this.other + ')';
   }

   @Override
   public int hashCode() {
      int var1 = this.string.hashCode();
      var1 = var1 * 31 + this.duration.hashCode();
      var1 = var1 * 31 + this.cooldown.hashCode();
      return var1 * 31 + this.other.hashCode();
   }

   @Override
   public boolean equals(@Nullable Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof GroupLimits)) {
         return false;
      } else {
         GroupLimits var2 = (GroupLimits)var1;
         if (!Intrinsics.checkObject(this.string, var2.string)) {
            return false;
         } else if (!Intrinsics.checkObject(this.duration, var2.duration)) {
            return false;
         } else {
            return !Intrinsics.checkObject(this.cooldown, var2.cooldown) ? false : Intrinsics.checkObject(this.other, var2.other);
         }
      }
   }
}
