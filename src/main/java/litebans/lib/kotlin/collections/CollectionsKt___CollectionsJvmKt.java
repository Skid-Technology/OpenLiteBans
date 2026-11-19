package litebans.lib.kotlin.collections;

import java.util.Collections;
import java.util.List;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public class CollectionsKt___CollectionsJvmKt extends CollectionsKt__ReversedViewsKt {
   public static final void handleList2(@NotNull List var0) {
      Intrinsics.handleObject3(var0, "<this>");
      Collections.reverse(var0);
   }
}
