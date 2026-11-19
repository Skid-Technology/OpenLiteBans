package litebans.lib.kotlin.collections;

import java.util.RandomAccess;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class AbstractListSubList extends AbstractList implements RandomAccess {
   private final AbstractList list;
   private final int intValue2;
   private int intValue3;

   public AbstractListSubList(@NotNull AbstractList list, int intValue, int intValue2) {
      Intrinsics.handleObject3(list, "list");
      this.list = list;
      this.intValue2 = intValue;
      AbstractList.Companion.handleInt(this.intValue2, intValue2, this.list.size());
      this.intValue3 = intValue2 - this.intValue2;
   }

   @Override
   public Object get(int var1) {
      AbstractList.Companion.handleInt(var1, this.intValue3);
      return this.list.get(this.intValue2 + var1);
   }

   @Override
   public int getIntValue3() {
      return this.intValue3;
   }
}
