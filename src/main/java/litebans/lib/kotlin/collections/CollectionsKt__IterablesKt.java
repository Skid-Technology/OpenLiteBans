package litebans.lib.kotlin.collections;

import java.util.Collection;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public class CollectionsKt__IterablesKt extends CollectionsKt__CollectionsKt {
   public static final int getInt(@NotNull Iterable var0, int var1) {
      Intrinsics.handleObject3(var0, "<this>");
      return var0 instanceof Collection ? ((Collection)var0).size() : var1;
   }
}
