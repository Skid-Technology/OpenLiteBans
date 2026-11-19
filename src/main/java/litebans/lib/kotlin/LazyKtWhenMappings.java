package litebans.lib.kotlin;

// $VF: synthetic class
public final class LazyKtWhenMappings {
   public static final int[] switchMap;

   static {
      int[] var0 = new int[LazyThreadSafetyMode.values().length];

      try {
         var0[LazyThreadSafetyMode.SYNCHRONIZED.ordinal()] = 1;
      } catch (NoSuchFieldError var3) {
      }

      try {
         var0[LazyThreadSafetyMode.PUBLICATION.ordinal()] = 2;
      } catch (NoSuchFieldError var2) {
      }

      try {
         var0[LazyThreadSafetyMode.NONE.ordinal()] = 3;
      } catch (NoSuchFieldError var1) {
      }

      switchMap = var0;
   }
}
