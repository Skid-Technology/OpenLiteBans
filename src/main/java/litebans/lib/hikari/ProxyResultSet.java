package litebans.lib.hikari;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class ProxyResultSet implements ResultSet {
   public final ProxyConnection proxyConnection;
   public final ProxyStatement proxyStatement;
   public final ResultSet resultSet;

   public ProxyResultSet(ProxyConnection var1, ProxyStatement var2, ResultSet var3) {
      this.proxyConnection = var1;
      this.proxyStatement = var2;
      this.resultSet = var3;
   }

   public final SQLException getSQLException(SQLException var1) {
      return this.proxyConnection.getSQLException(var1);
   }

   @Override
   public String toString() {
      return this.getClass().getSimpleName() + '@' + System.identityHashCode(this) + " wrapping " + this.resultSet;
   }

   @Override
   public final Statement getStatement() {
      return this.proxyStatement;
   }

   @Override
   public void updateRow() {
      try {
      this.proxyConnection.run();
      this.resultSet.updateRow();
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void insertRow() {
      try {
      this.proxyConnection.run();
      this.resultSet.insertRow();
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void deleteRow() {
      try {
      this.proxyConnection.run();
      this.resultSet.deleteRow();
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public final Object unwrap(Class var1) {
      try {
      if (var1.isInstance(this.resultSet)) {
         return this.resultSet;
      } else if (this.resultSet != null) {
         return this.resultSet.unwrap(var1);
      } else {
         throw new SQLException("Wrapped ResultSet is not an instance of " + var1);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }
}
