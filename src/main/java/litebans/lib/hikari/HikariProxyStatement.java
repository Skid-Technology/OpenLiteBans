package litebans.lib.hikari;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.sql.Wrapper;

public final class HikariProxyStatement extends ProxyStatement implements Wrapper, AutoCloseable, Statement {
   @Override
   public boolean isWrapperFor(Class var1) {
      try {
      try {
         return super.statement.isWrapperFor(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public ResultSet executeQuery(String var1) {
      try {
      try {
         return super.executeQuery(var1);
      } catch (Exception var3) {
         if (!(var3 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var3); }
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int executeUpdate(String var1) {
      try {
      try {
         return super.executeUpdate(var1);
      } catch (Exception var3) {
         if (!(var3 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var3); }
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int getMaxFieldSize() {
      try {
      try {
         return super.statement.getMaxFieldSize();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setMaxFieldSize(int var1) {
      try {
      try {
         super.statement.setMaxFieldSize(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int getMaxRows() {
      try {
      try {
         return super.statement.getMaxRows();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setMaxRows(int var1) {
      try {
      try {
         super.statement.setMaxRows(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setEscapeProcessing(boolean var1) {
      try {
      try {
         super.statement.setEscapeProcessing(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int getQueryTimeout() {
      try {
      try {
         return super.statement.getQueryTimeout();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setQueryTimeout(int var1) {
      try {
      try {
         super.statement.setQueryTimeout(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void cancel() {
      try {
      try {
         super.statement.cancel();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public SQLWarning getWarnings() {
      try {
      try {
         return super.statement.getWarnings();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void clearWarnings() {
      try {
      try {
         super.statement.clearWarnings();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setCursorName(String var1) {
      try {
      try {
         super.statement.setCursorName(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean execute(String var1) {
      try {
      try {
         return super.execute(var1);
      } catch (Exception var3) {
         if (!(var3 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var3); }
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public ResultSet getResultSet() {
      try {
      try {
         return super.getResultSet();
      } catch (Exception var2) {
         if (!(var2 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var2); }
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int getUpdateCount() {
      try {
      try {
         return super.statement.getUpdateCount();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean getMoreResults() {
      try {
      try {
         return super.statement.getMoreResults();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setFetchDirection(int var1) {
      try {
      try {
         super.statement.setFetchDirection(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int getFetchDirection() {
      try {
      try {
         return super.statement.getFetchDirection();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setFetchSize(int var1) {
      try {
      try {
         super.statement.setFetchSize(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int getFetchSize() {
      try {
      try {
         return super.statement.getFetchSize();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int getResultSetConcurrency() {
      try {
      try {
         return super.statement.getResultSetConcurrency();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int getResultSetType() {
      try {
      try {
         return super.statement.getResultSetType();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void addBatch(String var1) {
      try {
      try {
         super.statement.addBatch(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void clearBatch() {
      try {
      try {
         super.statement.clearBatch();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int[] executeBatch() {
      try {
      try {
         return super.executeBatch();
      } catch (Exception var2) {
         if (!(var2 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var2); }
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Connection getConnection() {
      try {
      try {
         return super.getConnection();
      } catch (Exception var2) {
         if (!(var2 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var2); }
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean getMoreResults(int var1) {
      try {
      try {
         return super.statement.getMoreResults(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public ResultSet getGeneratedKeys() {
      try {
      try {
         return super.getGeneratedKeys();
      } catch (Exception var2) {
         if (!(var2 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var2); }
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int executeUpdate(String var1, int var2) {
      try {
      try {
         return super.executeUpdate(var1, var2);
      } catch (Exception var4) {
         if (!(var4 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var4); }
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int executeUpdate(String var1, int[] var2) {
      try {
      try {
         return super.executeUpdate(var1, var2);
      } catch (Exception var4) {
         if (!(var4 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var4); }
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int executeUpdate(String var1, String[] var2) {
      try {
      try {
         return super.executeUpdate(var1, var2);
      } catch (Exception var4) {
         if (!(var4 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var4); }
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean execute(String var1, int var2) {
      try {
      try {
         return super.execute(var1, var2);
      } catch (Exception var4) {
         if (!(var4 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var4); }
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean execute(String var1, int[] var2) {
      try {
      try {
         return super.execute(var1, var2);
      } catch (Exception var4) {
         if (!(var4 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var4); }
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean execute(String var1, String[] var2) {
      try {
      try {
         return super.execute(var1, var2);
      } catch (Exception var4) {
         if (!(var4 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var4); }
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int getResultSetHoldability() {
      try {
      try {
         return super.statement.getResultSetHoldability();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean isClosed() {
      try {
      try {
         return super.isClosed();
      } catch (Exception var2) {
         if (!(var2 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var2); }
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setPoolable(boolean var1) {
      try {
      try {
         super.statement.setPoolable(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean isPoolable() {
      try {
      try {
         return super.statement.isPoolable();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void closeOnCompletion() {
      try {
      try {
         super.statement.closeOnCompletion();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean isCloseOnCompletion() {
      try {
      try {
         return super.statement.isCloseOnCompletion();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public long getLargeUpdateCount() {
      try {
      try {
         return super.statement.getLargeUpdateCount();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setLargeMaxRows(long var1) {
      try {
      try {
         super.statement.setLargeMaxRows(var1);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public long getLargeMaxRows() {
      try {
      try {
         return super.statement.getLargeMaxRows();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public long[] executeLargeBatch() {
      try {
      try {
         return super.statement.executeLargeBatch();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public long executeLargeUpdate(String var1) {
      try {
      try {
         return super.statement.executeLargeUpdate(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public long executeLargeUpdate(String var1, int var2) {
      try {
      try {
         return super.statement.executeLargeUpdate(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public long executeLargeUpdate(String var1, int[] var2) {
      try {
      try {
         return super.statement.executeLargeUpdate(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public long executeLargeUpdate(String var1, String[] var2) {
      try {
      try {
         return super.statement.executeLargeUpdate(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   public HikariProxyStatement(ProxyConnection var1, Statement var2) {
      super(var1, var2);
   }
}
