package litebans.database;

// $VF: synthetic class
public final class TableWhenMappings {
   public static final int[] switchMap;

   static {
      int[] var0 = new int[Table.values().length];

      try {
         var0[Table.BANS.ordinal()] = 1;
      } catch (NoSuchFieldError var4) {
      }

      try {
         var0[Table.MUTES.ordinal()] = 2;
      } catch (NoSuchFieldError var3) {
      }

      try {
         var0[Table.WARNINGS.ordinal()] = 3;
      } catch (NoSuchFieldError var2) {
      }

      try {
         var0[Table.KICKS.ordinal()] = 4;
      } catch (NoSuchFieldError var1) {
      }

      switchMap = var0;
   }
}
