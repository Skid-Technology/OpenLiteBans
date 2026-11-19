package litebans.lib.kotlin.collections;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public class CollectionsKt__CollectionsJVMKt {
   public static final List getList(Object var0) {
      return Collections.singletonList(var0);
   }

   public static final Object[] getObjectArray(@NotNull Object[] var0, boolean var1) {
      Intrinsics.handleObject3(var0, "<this>");
      return var1 && Intrinsics.checkObject(var0.getClass(), Object[].class) ? var0 : Arrays.copyOf(var0, var0.length, Object[].class);
   }
}
