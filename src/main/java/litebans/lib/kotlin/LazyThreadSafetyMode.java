package litebans.lib.kotlin;

import litebans.lib.kotlin.collections.EnumEntries;
import litebans.lib.kotlin.collections.EnumEntriesKt;

public enum LazyThreadSafetyMode {
   SYNCHRONIZED,
   PUBLICATION,
   NONE;
   private static final LazyThreadSafetyMode[] lazyThreadSafetyModeArray = getLazyThreadSafetyModeArray();
   private static final EnumEntries enumEntries = EnumEntriesKt.getEnumEntries(lazyThreadSafetyModeArray);

   public static LazyThreadSafetyMode getLazyThreadSafetyMode(String var0) {
      return Enum.valueOf(LazyThreadSafetyMode.class, var0);
   }

   private static final LazyThreadSafetyMode[] getLazyThreadSafetyModeArray() {
      return new LazyThreadSafetyMode[]{SYNCHRONIZED, PUBLICATION, NONE};
   }
}
