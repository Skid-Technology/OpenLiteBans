package litebans.punishment;

// $VF: synthetic class
public final class PunishmentLimitsWhenMappings {
   public static final int[] switchMap;

   static {
      int[] var0 = new int[PunishmentType.values().length];

      try {
         var0[PunishmentType.BAN.ordinal()] = 1;
      } catch (NoSuchFieldError var4) {
      }

      try {
         var0[PunishmentType.MUTE.ordinal()] = 2;
      } catch (NoSuchFieldError var3) {
      }

      try {
         var0[PunishmentType.WARN.ordinal()] = 3;
      } catch (NoSuchFieldError var2) {
      }

      try {
         var0[PunishmentType.KICK.ordinal()] = 4;
      } catch (NoSuchFieldError var1) {
      }

      switchMap = var0;
   }
}
