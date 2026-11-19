package litebans.punishment;

// $VF: synthetic class
public final class PunishmentEntryWhenMappings {
   public static final int[] switchMap;

   static {
      int[] var0 = new int[PunishmentType.values().length];

      try {
         var0[PunishmentType.UNBAN.ordinal()] = 1;
      } catch (NoSuchFieldError var5) {
      }

      try {
         var0[PunishmentType.UNMUTE.ordinal()] = 2;
      } catch (NoSuchFieldError var4) {
      }

      try {
         var0[PunishmentType.UNWARN.ordinal()] = 3;
      } catch (NoSuchFieldError var3) {
      }

      try {
         var0[PunishmentType.WARN.ordinal()] = 4;
      } catch (NoSuchFieldError var2) {
      }

      try {
         var0[PunishmentType.KICK.ordinal()] = 5;
      } catch (NoSuchFieldError var1) {
      }

      switchMap = var0;
   }
}
