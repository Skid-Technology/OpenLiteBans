package litebans.lib.hikari;

public enum IsolationLevel {
   TRANSACTION_NONE(0),
   TRANSACTION_READ_UNCOMMITTED(1),
   TRANSACTION_READ_COMMITTED(2),
   TRANSACTION_REPEATABLE_READ(4),
   TRANSACTION_SERIALIZABLE(8),
   TRANSACTION_SQL_SERVER_SNAPSHOT_ISOLATION_LEVEL(4096);
   private final int intValue;
   private static final IsolationLevel[] isolationLevelArray = new IsolationLevel[]{
      TRANSACTION_NONE,
      TRANSACTION_READ_UNCOMMITTED,
      TRANSACTION_READ_COMMITTED,
      TRANSACTION_REPEATABLE_READ,
      TRANSACTION_SERIALIZABLE,
      TRANSACTION_SQL_SERVER_SNAPSHOT_ISOLATION_LEVEL
   };

   public static IsolationLevel getIsolationLevel(String var0) {
      return Enum.valueOf(IsolationLevel.class, var0);
   }

   private IsolationLevel(int var3) {
      this.intValue = var3;
   }

   public int getIntValue() {
      return this.intValue;
   }
}
