package litebans.database;

public final class EmptyResultSetHolder {
   private static final EmptyResultSet emptyResultSet = new EmptyResultSet();

   public static final EmptyResultSet getEmptyResultSet() {
      return emptyResultSet;
   }
}
