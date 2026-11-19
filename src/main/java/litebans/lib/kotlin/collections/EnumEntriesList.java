package litebans.lib.kotlin.collections;

import java.io.Serializable;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class EnumEntriesList extends AbstractList implements EnumEntries, Serializable {
   private final Enum[] entries;

   public EnumEntriesList(@NotNull Enum[] entries) {
      Intrinsics.handleObject3(entries, "entries");
      this.entries = entries;
   }

   @Override
   public int getIntValue3() {
      return this.entries.length;
   }

   public Enum getEnum(int var1) {
      AbstractList.Companion.handleInt(var1, this.entries.length);
      return this.entries[var1];
   }

   public boolean checkEnum(@NotNull Enum element) {
      Intrinsics.handleObject3(element, "element");
      Enum var2 = (Enum)ArraysKt.getObject(this.entries, element.ordinal());
      return var2 == element;
   }

   public int getInt(@NotNull Enum element) {
      Intrinsics.handleObject3(element, "element");
      int var2 = element.ordinal();
      Enum var3 = (Enum)ArraysKt.getObject(this.entries, var2);
      return var3 == element ? var2 : -1;
   }

   public int getInt2(@NotNull Enum element) {
      Intrinsics.handleObject3(element, "element");
      return this.indexOf(element);
   }

   private final Object getObject() {
      return new EnumEntriesSerializationProxy(this.entries);
   }

   @Override
   public Object get(int var1) {
      return this.getEnum(var1);
   }

   @Override
   public final boolean contains(Object var1) {
      return !(var1 instanceof Enum) ? false : this.checkEnum((Enum)var1);
   }

   @Override
   public final int indexOf(Object var1) {
      return !(var1 instanceof Enum) ? -1 : this.getInt((Enum)var1);
   }

   @Override
   public final int lastIndexOf(Object var1) {
      return !(var1 instanceof Enum) ? -1 : this.getInt2((Enum)var1);
   }
}
