package litebans.database;

import litebans.punishment.IdType;

// $VF: synthetic class
public final class SelectQueryWhenMappings {
   public static final int[] switchMap;

   static {
      int[] var0 = new int[IdType.values().length];

      try {
         var0[IdType.UUID.ordinal()] = 1;
      } catch (NoSuchFieldError var3) {
      }

      try {
         var0[IdType.EXECUTOR_UUID.ordinal()] = 2;
      } catch (NoSuchFieldError var2) {
      }

      try {
         var0[IdType.ID.ordinal()] = 3;
      } catch (NoSuchFieldError var1) {
      }

      switchMap = var0;
   }
}
