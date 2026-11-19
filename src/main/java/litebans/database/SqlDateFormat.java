package litebans.database;

public final class SqlDateFormat {
   public static final SqliteToH2Converter sqliteToH2Converter = new SqliteToH2Converter(null);
   private static final ThreadLocal threadLocal = new UtcDateFormatThreadLocal();

   public static final ThreadLocal getThreadLocal() {
      return threadLocal;
   }
}
