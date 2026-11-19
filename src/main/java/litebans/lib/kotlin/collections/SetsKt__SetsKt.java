package litebans.lib.kotlin.collections;

import java.util.Set;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public class SetsKt__SetsKt extends SetsKt__SetsJVMKt {
   public static final Set getSet() {
      return EmptySet.INSTANCE;
   }

   public static final Set getSet(@NotNull Object[] elements) {
      Intrinsics.handleObject3(elements, "elements");
      return ArraysKt.getSet(elements);
   }

   public static final Set getSet(@NotNull Set var0) {
      Intrinsics.handleObject3(var0, "<this>");
      Set var10000;
      switch (var0.size()) {
         case 0:
            var10000 = SetsKt.getSet();
            break;
         case 1:
            var10000 = SetsKt.getSet(var0.iterator().next());
            break;
         default:
            var10000 = var0;
      }

      return var10000;
   }
}
