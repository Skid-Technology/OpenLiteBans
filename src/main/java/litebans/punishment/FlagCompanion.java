package litebans.punishment;

import litebans.lib.kotlin.NoWhenBranchMatchedException;
import litebans.lib.kotlin.jvm.internal.DefaultConstructorMarker;
import litebans.util.UnexpectedValueException;
import litebans.util.Unreachable;
import org.jetbrains.annotations.NotNull;

public final class FlagCompanion {
   private FlagCompanion() {
   }

   public final Flag getFlag(int var1) {
      Flag[] var2 = Flag.getFlagArray2();
      return var2.length <= var1 ? null : var2[var1];
   }

   public final Flag getFlag(@NotNull PunishmentType var1) {
      Flag var10000;
      switch (FlagCompanionWhenMappings.switchMap[var1.ordinal()]) {
         case 1:
            var10000 = Flag.BAN;
            break;
         case 2:
            var10000 = Flag.MUTE;
            break;
         case 3:
            var10000 = Flag.WARN;
            break;
         case 4:
            var10000 = Flag.UNBAN;
            break;
         case 5:
            var10000 = Flag.UNMUTE;
            break;
         case 6:
            var10000 = Flag.UNWARN;
            break;
         case 7:
            Unreachable.getVoid();
            throw new NoWhenBranchMatchedException();
         default:
            throw new UnexpectedValueException();
      }

      return var10000;
   }

   public FlagCompanion(DefaultConstructorMarker var1) {
      this();
   }
}
