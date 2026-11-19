package litebans.webhook;

import litebans.punishment.PunishmentType;

// $VF: synthetic class
public final class WebhookManagerWhenMappings {
   public static final int[] switchMap;

   static {
      int[] var0 = new int[PunishmentType.values().length];

      try {
         var0[PunishmentType.KICK.ordinal()] = 1;
      } catch (NoSuchFieldError var2) {
      }

      try {
         var0[PunishmentType.WARN.ordinal()] = 2;
      } catch (NoSuchFieldError var1) {
      }

      switchMap = var0;
   }
}
