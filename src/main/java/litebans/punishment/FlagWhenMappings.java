package litebans.punishment;

// $VF: synthetic class
public final class FlagWhenMappings {
   public static final int[] switchMap;

   static {
      int[] var0 = new int[Flag.values().length];

      try {
         var0[Flag.BROADCAST.ordinal()] = 1;
      } catch (NoSuchFieldError var12) {
      }

      try {
         var0[Flag.BROADCAST_SILENT.ordinal()] = 2;
      } catch (NoSuchFieldError var11) {
      }

      try {
         var0[Flag.BROADCAST_EXTRA_SILENT.ordinal()] = 3;
      } catch (NoSuchFieldError var10) {
      }

      try {
         var0[Flag.NOTIFY_DUPEIP_JOIN.ordinal()] = 4;
      } catch (NoSuchFieldError var9) {
      }

      try {
         var0[Flag.NOTIFY_MUTE.ordinal()] = 5;
      } catch (NoSuchFieldError var8) {
      }

      try {
         var0[Flag.BAN.ordinal()] = 6;
      } catch (NoSuchFieldError var7) {
      }

      try {
         var0[Flag.MUTE.ordinal()] = 7;
      } catch (NoSuchFieldError var6) {
      }

      try {
         var0[Flag.WARN.ordinal()] = 8;
      } catch (NoSuchFieldError var5) {
      }

      try {
         var0[Flag.KICK_REQUEST.ordinal()] = 9;
      } catch (NoSuchFieldError var4) {
      }

      try {
         var0[Flag.KICK_SUCCESS.ordinal()] = 10;
      } catch (NoSuchFieldError var3) {
      }

      try {
         var0[Flag.UNBAN.ordinal()] = 11;
      } catch (NoSuchFieldError var2) {
      }

      try {
         var0[Flag.UNMUTE.ordinal()] = 12;
      } catch (NoSuchFieldError var1) {
      }

      switchMap = var0;
   }
}
