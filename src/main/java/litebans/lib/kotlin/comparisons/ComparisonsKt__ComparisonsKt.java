package litebans.lib.kotlin.comparisons;

import org.jetbrains.annotations.Nullable;

public class ComparisonsKt__ComparisonsKt {
   public static final int getInt(@Nullable Comparable var0, @Nullable Comparable var1) {
      if (var0 == var1) {
         return 0;
      } else if (var0 == null) {
         return -1;
      } else {
         return var1 == null ? 1 : var0.compareTo(var1);
      }
   }
}
