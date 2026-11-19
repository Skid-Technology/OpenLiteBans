package litebans.punishment;

import litebans.lib.kotlin.collections.EnumEntries;
import litebans.lib.kotlin.collections.EnumEntriesKt;

public enum Flag {
   PING,
   PONG,
   BROADCAST,
   BROADCAST_SILENT,
   BROADCAST_EXTRA_SILENT,
   NOTIFY_BANNED_JOIN,
   NOTIFY_DUPEIP_JOIN,
   NOTIFY_MUTE,
   BAN,
   MUTE,
   WARN,
   KICK_REQUEST,
   KICK_SUCCESS,
   UNBAN,
   UNMUTE,
   UNWARN,
   EXPIRED_NOTIFY,
   BROADCAST_CUSTOM,
   SERVERS_RELOAD,
   CONFIG_RELOAD;
   public static final FlagCompanion Companion = new FlagCompanion(null);
   private static final Flag[] flagArray = values();
   private static final Flag[] flagArray2 = getFlagArray();
   private static final EnumEntries enumEntries = EnumEntriesKt.getEnumEntries(flagArray2);

   public final int getInt() {
      return this.ordinal();
   }

   public final String getString() {
      String var10000;
      switch (FlagWhenMappings.switchMap[this.ordinal()]) {
         case 1:
            var10000 = "broadcast";
            break;
         case 2:
            var10000 = "silent";
            break;
         case 3:
            var10000 = "console";
            break;
         case 4:
            var10000 = "dupeip_join";
            break;
         case 5:
            var10000 = "mute";
            break;
         default:
            throw new UnsupportedOperationException(this.toString());
      }

      return var10000;
   }

   public final boolean check() {
      return this == UNBAN || this == UNMUTE || this == UNWARN;
   }

   public static Flag getFlag(String var0) {
      return Enum.valueOf(Flag.class, var0);
   }

   private static final Flag[] getFlagArray() {
      return new Flag[]{
         PING,
         PONG,
         BROADCAST,
         BROADCAST_SILENT,
         BROADCAST_EXTRA_SILENT,
         NOTIFY_BANNED_JOIN,
         NOTIFY_DUPEIP_JOIN,
         NOTIFY_MUTE,
         BAN,
         MUTE,
         WARN,
         KICK_REQUEST,
         KICK_SUCCESS,
         UNBAN,
         UNMUTE,
         UNWARN,
         EXPIRED_NOTIFY,
         BROADCAST_CUSTOM,
         SERVERS_RELOAD,
         CONFIG_RELOAD
      };
   }

   public static final Flag[] getFlagArray2() {
      return flagArray;
   }
}
