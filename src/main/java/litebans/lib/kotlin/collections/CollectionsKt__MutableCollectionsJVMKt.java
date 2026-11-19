package litebans.lib.kotlin.collections;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public class CollectionsKt__MutableCollectionsJVMKt extends CollectionsKt__IteratorsKt {
   public static final void handleList(@NotNull List var0) {
      Intrinsics.handleObject3(var0, "<this>");
      if (var0.size() > 1) {
         Collections.sort(var0);
      }
   }

   public static final void handleList(@NotNull List list, @NotNull Comparator comparator) {
      Intrinsics.handleObject3(list, "<this>");
      Intrinsics.handleObject3(comparator, "comparator");
      if (list.size() > 1) {
         Collections.sort(list, comparator);
      }
   }
}
