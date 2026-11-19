package litebans.lib.kotlin.collections;

import java.io.Serializable;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class EnumEntriesSerializationProxy implements Serializable {
   private static final EnumEntriesSerializationProxyCompanion Companion = new EnumEntriesSerializationProxyCompanion(null);
   private final Class classValue;
   private static final long longValue = 0L;

   public EnumEntriesSerializationProxy(@NotNull Enum[] entries) {
      Intrinsics.handleObject3(entries, "entries");
      Class var10001 = entries.getClass().getComponentType();
      Intrinsics.handleObject(var10001);
      this.classValue = var10001;
   }

   private final Object getObject() {
      return EnumEntriesKt.getEnumEntries((Enum[])this.classValue.getEnumConstants());
   }
}
