package litebans.sync;

import litebans.punishment.Flag;
import litebans.punishment.PunishmentType;

// $VF: synthetic class
public final class SyncManagerWhenMappings {
   public static final int[] switchMap;
   public static final int[] switchMap2;

   static {
      int[] var0 = new int[PunishmentType.values().length];

      try {
         var0[PunishmentType.BAN.ordinal()] = 1;
      } catch (NoSuchFieldError var21) {
      }

      try {
         var0[PunishmentType.MUTE.ordinal()] = 2;
      } catch (NoSuchFieldError var20) {
      }

      try {
         var0[PunishmentType.WARN.ordinal()] = 3;
      } catch (NoSuchFieldError var19) {
      }

      try {
         var0[PunishmentType.KICK.ordinal()] = 4;
      } catch (NoSuchFieldError var18) {
      }

      switchMap = var0;
      var0 = new int[Flag.values().length];

      try {
         var0[Flag.BAN.ordinal()] = 1;
      } catch (NoSuchFieldError var17) {
      }

      try {
         var0[Flag.MUTE.ordinal()] = 2;
      } catch (NoSuchFieldError var16) {
      }

      try {
         var0[Flag.WARN.ordinal()] = 3;
      } catch (NoSuchFieldError var15) {
      }

      try {
         var0[Flag.UNBAN.ordinal()] = 4;
      } catch (NoSuchFieldError var14) {
      }

      try {
         var0[Flag.UNMUTE.ordinal()] = 5;
      } catch (NoSuchFieldError var13) {
      }

      try {
         var0[Flag.BROADCAST.ordinal()] = 6;
      } catch (NoSuchFieldError var12) {
      }

      try {
         var0[Flag.BROADCAST_SILENT.ordinal()] = 7;
      } catch (NoSuchFieldError var11) {
      }

      try {
         var0[Flag.BROADCAST_EXTRA_SILENT.ordinal()] = 8;
      } catch (NoSuchFieldError var10) {
      }

      try {
         var0[Flag.BROADCAST_CUSTOM.ordinal()] = 9;
      } catch (NoSuchFieldError var9) {
      }

      try {
         var0[Flag.NOTIFY_BANNED_JOIN.ordinal()] = 10;
      } catch (NoSuchFieldError var8) {
      }

      try {
         var0[Flag.NOTIFY_DUPEIP_JOIN.ordinal()] = 11;
      } catch (NoSuchFieldError var7) {
      }

      try {
         var0[Flag.NOTIFY_MUTE.ordinal()] = 12;
      } catch (NoSuchFieldError var6) {
      }

      try {
         var0[Flag.KICK_REQUEST.ordinal()] = 13;
      } catch (NoSuchFieldError var5) {
      }

      try {
         var0[Flag.EXPIRED_NOTIFY.ordinal()] = 14;
      } catch (NoSuchFieldError var4) {
      }

      try {
         var0[Flag.PING.ordinal()] = 15;
      } catch (NoSuchFieldError var3) {
      }

      try {
         var0[Flag.SERVERS_RELOAD.ordinal()] = 16;
      } catch (NoSuchFieldError var2) {
      }

      try {
         var0[Flag.CONFIG_RELOAD.ordinal()] = 17;
      } catch (NoSuchFieldError var1) {
      }

      switchMap2 = var0;
   }
}
