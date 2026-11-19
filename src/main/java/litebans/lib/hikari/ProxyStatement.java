package litebans.lib.hikari;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class ProxyStatement implements Statement {
   public final ProxyConnection proxyConnection;
   public final Statement statement;
   private boolean flag;
   private ResultSet resultSet;

   public ProxyStatement(ProxyConnection var1, Statement var2) {
      this.proxyConnection = var1;
      this.statement = var2;
   }

   public final SQLException getSQLException(SQLException var1) {
      return this.proxyConnection.getSQLException(var1);
   }

   @Override
   public final String toString() {
      String var1 = this.statement.toString();
      return this.getClass().getSimpleName() + '@' + System.identityHashCode(this) + " wrapping " + var1;
   }

   @Override
   public final void close() {
      try {
      synchronized (this) {
         if (this.flag) {
            return;
         }

         this.flag = true;
      }

      this.proxyConnection.handleStatement(this.statement);

      try {
         this.statement.close();
      } catch (SQLException var3) {
         throw this.proxyConnection.getSQLException(var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean isClosed() {
      try {
      return this.statement.isClosed();
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Connection getConnection() {
      return this.proxyConnection;
   }

   @Override
   public boolean execute(String var1) {
      try {
      this.proxyConnection.run();
      return this.statement.execute(var1);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean execute(String var1, int var2) {
      try {
      this.proxyConnection.run();
      return this.statement.execute(var1, var2);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public ResultSet executeQuery(String var1) {
      try {
      this.proxyConnection.run();
      ResultSet var2 = this.statement.executeQuery(var1);
      return ProxyFactory.getResultSet(this.proxyConnection, this, var2);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int executeUpdate(String var1) {
      try {
      this.proxyConnection.run();
      return this.statement.executeUpdate(var1);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int[] executeBatch() {
      try {
      this.proxyConnection.run();
      return this.statement.executeBatch();
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int executeUpdate(String var1, int var2) {
      try {
      this.proxyConnection.run();
      return this.statement.executeUpdate(var1, var2);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int executeUpdate(String var1, int[] var2) {
      try {
      this.proxyConnection.run();
      return this.statement.executeUpdate(var1, var2);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int executeUpdate(String var1, String[] var2) {
      try {
      this.proxyConnection.run();
      return this.statement.executeUpdate(var1, var2);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean execute(String var1, int[] var2) {
      try {
      this.proxyConnection.run();
      return this.statement.execute(var1, var2);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean execute(String var1, String[] var2) {
      try {
      this.proxyConnection.run();
      return this.statement.execute(var1, var2);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public long[] executeLargeBatch() {
      try {
      this.proxyConnection.run();
      return this.statement.executeLargeBatch();
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public long executeLargeUpdate(String var1) {
      try {
      this.proxyConnection.run();
      return this.statement.executeLargeUpdate(var1);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public long executeLargeUpdate(String var1, int var2) {
      try {
      this.proxyConnection.run();
      return this.statement.executeLargeUpdate(var1, var2);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public long executeLargeUpdate(String var1, int[] var2) {
      try {
      this.proxyConnection.run();
      return this.statement.executeLargeUpdate(var1, var2);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public long executeLargeUpdate(String var1, String[] var2) {
      try {
      this.proxyConnection.run();
      return this.statement.executeLargeUpdate(var1, var2);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public ResultSet getResultSet() {
      try {
      ResultSet var1 = this.statement.getResultSet();
      if (var1 != null) {
         if (this.resultSet == null || ((ProxyResultSet)this.resultSet).resultSet != var1) {
            this.resultSet = ProxyFactory.getResultSet(this.proxyConnection, this, var1);
         }
      } else {
         this.resultSet = null;
      }

      return this.resultSet;
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public ResultSet getGeneratedKeys() {
      try {
      ResultSet var1 = this.statement.getGeneratedKeys();
      if (this.resultSet == null || ((ProxyResultSet)this.resultSet).resultSet != var1) {
         this.resultSet = ProxyFactory.getResultSet(this.proxyConnection, this, var1);
      }

      return this.resultSet;
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public final Object unwrap(Class var1) {
      try {
      if (var1.isInstance(this.statement)) {
         return this.statement;
      } else if (this.statement != null) {
         return this.statement.unwrap(var1);
      } else {
         throw new SQLException("Wrapped statement is not an instance of " + var1);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }
}
