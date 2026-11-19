package litebans.lib.hikari;

public enum SQLExceptionOverrideResult {
   CONTINUE_EVICT,
   DO_NOT_EVICT;
   private static final SQLExceptionOverrideResult[] sqlExceptionOverrideResultArray = new SQLExceptionOverrideResult[]{CONTINUE_EVICT, DO_NOT_EVICT};

   public static SQLExceptionOverrideResult getSQLExceptionOverrideResult(String var0) {
      return Enum.valueOf(SQLExceptionOverrideResult.class, var0);
   }
}
