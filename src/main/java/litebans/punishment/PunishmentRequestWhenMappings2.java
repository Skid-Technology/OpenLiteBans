package litebans.punishment;

// $VF: synthetic class
public final class PunishmentRequestWhenMappings2 {
   public static final int[] intArray;

   static {
      int[] var0 = new int[PunishmentType.values().length];

      try {
         var0[PunishmentType.WARN.ordinal()] = 1;
      } catch (NoSuchFieldError var3) {
      }

      try {
         var0[PunishmentType.MUTE.ordinal()] = 2;
      } catch (NoSuchFieldError var2) {
      }

      try {
         var0[PunishmentType.KICK.ordinal()] = 3;
      } catch (NoSuchFieldError var1) {
      }

      intArray = var0;
   }
}
