package litebans.lib.kotlin.collections;

import litebans.lib.kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class EnumEntriesKt {
   public static final EnumEntries getEnumEntries(@NotNull Enum[] entries) {
      Intrinsics.handleObject3(entries, "entries");
      return new EnumEntriesList(entries);
   }
}
