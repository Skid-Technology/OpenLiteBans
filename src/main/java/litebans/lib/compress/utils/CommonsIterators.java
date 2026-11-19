package litebans.lib.compress.utils;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;

public class CommonsIterators {
   public static final boolean checkCollection(Collection var0, Iterator var1) {
      Objects.requireNonNull(var0);
      boolean var2 = false;

      while (var1.hasNext()) {
         var2 |= var0.add(var1.next());
      }

      return var2;
   }
}
