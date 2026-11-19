package litebans.util;

import litebans.lib.kotlin.KotlinAssertionError;

public final class Unreachable {
   public static final Void getVoid() {
      throw new KotlinAssertionError();
   }

   /** Rethrows a checked exception without a throws clause, keeping its runtime type. */
   public static RuntimeException rethrow(Throwable var0) {
      return Unreachable.<RuntimeException>rethrow0(var0);
   }

   private static <T extends Throwable> T rethrow0(Throwable var0) throws T {
      throw (T)var0;
   }
}
