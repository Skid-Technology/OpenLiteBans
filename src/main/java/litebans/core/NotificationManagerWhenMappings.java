package litebans.core;

import litebans.punishment.PunishmentType;

// $VF: synthetic class
public final class NotificationManagerWhenMappings {
   public static final int[] switchMap;

   static {
      int[] var0 = new int[PunishmentType.values().length];

      try {
         var0[PunishmentType.BAN.ordinal()] = 1;
      } catch (NoSuchFieldError var2) {
      }

      try {
         var0[PunishmentType.MUTE.ordinal()] = 2;
      } catch (NoSuchFieldError var1) {
      }

      switchMap = var0;
   }
}
