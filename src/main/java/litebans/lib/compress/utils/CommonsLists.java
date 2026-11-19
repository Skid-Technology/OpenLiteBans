package litebans.lib.compress.utils;

import java.util.ArrayList;
import java.util.Iterator;

public class CommonsLists {
   public static final ArrayList getArrayList() {
      return new ArrayList();
   }

   public static final ArrayList getArrayList(Iterator var0) {
      ArrayList var1 = getArrayList();
      CommonsIterators.checkCollection(var1, var0);
      return var1;
   }
}
