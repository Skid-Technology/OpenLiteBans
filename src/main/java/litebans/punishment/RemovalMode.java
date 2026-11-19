package litebans.punishment;

import litebans.lib.kotlin.collections.EnumEntries;
import litebans.lib.kotlin.collections.EnumEntriesKt;

public enum RemovalMode {
   DELETE,
   DEACTIVATE,
   PRUNE;
   private static final RemovalMode[] removalModeArray = getRemovalModeArray();
   private static final EnumEntries enumEntries = EnumEntriesKt.getEnumEntries(removalModeArray);

   public static RemovalMode getRemovalMode(String var0) {
      return Enum.valueOf(RemovalMode.class, var0);
   }

   private static final RemovalMode[] getRemovalModeArray() {
      return new RemovalMode[]{DELETE, DEACTIVATE, PRUNE};
   }
}
