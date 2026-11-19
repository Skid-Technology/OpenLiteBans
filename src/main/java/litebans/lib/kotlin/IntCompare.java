package litebans.lib.kotlin;

import litebans.lib.kotlin.jvm.internal.Intrinsics;

public final class IntCompare {
   public static final int getInt(int var0, int var1) {
      return Intrinsics.getInt(var0 ^ Integer.MIN_VALUE, var1 ^ Integer.MIN_VALUE);
   }
}
