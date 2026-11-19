package litebans.lib.compress.utils;

import java.util.Collections;
import java.util.HashSet;

public class CommonsSets {
   @SafeVarargs
   public static final HashSet getHashSet(Object... var0) {
      HashSet var1 = new HashSet(var0.length);
      Collections.addAll(var1, var0);
      return var1;
   }
}
