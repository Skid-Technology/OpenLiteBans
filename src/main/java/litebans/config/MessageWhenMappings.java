package litebans.config;

import litebans.punishment.PunishmentType;

// $VF: synthetic class
public class MessageWhenMappings {
   public static final int[] switchMap = new int[PunishmentType.values().length];

   static {
      try {
         switchMap[PunishmentType.BAN.ordinal()] = 1;
      } catch (NoSuchFieldError var3) {
      }

      try {
         switchMap[PunishmentType.MUTE.ordinal()] = 2;
      } catch (NoSuchFieldError var2) {
      }

      try {
         switchMap[PunishmentType.WARN.ordinal()] = 3;
      } catch (NoSuchFieldError var1) {
      }

      try {
         switchMap[PunishmentType.KICK.ordinal()] = 4;
      } catch (NoSuchFieldError var0) {
      }
   }
}
