package litebans.lib.kotlin.collections;

import java.util.Iterator;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class ArrayIteratorKt {
   public static final Iterator getIterator(@NotNull Object[] array) {
      Intrinsics.handleObject3(array, "array");
      return new ArrayIterator(array);
   }
}
