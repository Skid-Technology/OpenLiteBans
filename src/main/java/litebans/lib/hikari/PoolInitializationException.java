package litebans.lib.hikari;

public class PoolInitializationException extends RuntimeException {
   private static final long longValue = 929872118275916520L;

   public PoolInitializationException(Throwable var1) {
      super("Failed to initialize pool: " + var1.getMessage(), var1);
   }
}
