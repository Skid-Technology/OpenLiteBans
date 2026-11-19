package litebans.lib.hikari;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLType;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Wrapper;
import java.util.Calendar;
import java.util.Map;

public final class HikariProxyCallableStatement
   extends ProxyCallableStatement
   implements Wrapper,
   AutoCloseable,
   Statement,
   PreparedStatement,
   CallableStatement {
   @Override
   public boolean isWrapperFor(Class var1) {
      try {
      try {
         return ((CallableStatement)super.statement).isWrapperFor(var1);
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
         return ((CallableStatement)super.statement).getMaxFieldSize();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setMaxFieldSize(int var1) {
      try {
      try {
         ((CallableStatement)super.statement).setMaxFieldSize(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int getMaxRows() {
      try {
      try {
         return ((CallableStatement)super.statement).getMaxRows();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setMaxRows(int var1) {
      try {
      try {
         ((CallableStatement)super.statement).setMaxRows(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setEscapeProcessing(boolean var1) {
      try {
      try {
         ((CallableStatement)super.statement).setEscapeProcessing(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int getQueryTimeout() {
      try {
      try {
         return ((CallableStatement)super.statement).getQueryTimeout();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setQueryTimeout(int var1) {
      try {
      try {
         ((CallableStatement)super.statement).setQueryTimeout(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void cancel() {
      try {
      try {
         ((CallableStatement)super.statement).cancel();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public SQLWarning getWarnings() {
      try {
      try {
         return ((CallableStatement)super.statement).getWarnings();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void clearWarnings() {
      try {
      try {
         ((CallableStatement)super.statement).clearWarnings();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setCursorName(String var1) {
      try {
      try {
         ((CallableStatement)super.statement).setCursorName(var1);
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
         return ((CallableStatement)super.statement).getUpdateCount();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean getMoreResults() {
      try {
      try {
         return ((CallableStatement)super.statement).getMoreResults();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setFetchDirection(int var1) {
      try {
      try {
         ((CallableStatement)super.statement).setFetchDirection(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int getFetchDirection() {
      try {
      try {
         return ((CallableStatement)super.statement).getFetchDirection();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setFetchSize(int var1) {
      try {
      try {
         ((CallableStatement)super.statement).setFetchSize(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int getFetchSize() {
      try {
      try {
         return ((CallableStatement)super.statement).getFetchSize();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int getResultSetConcurrency() {
      try {
      try {
         return ((CallableStatement)super.statement).getResultSetConcurrency();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int getResultSetType() {
      try {
      try {
         return ((CallableStatement)super.statement).getResultSetType();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void addBatch(String var1) {
      try {
      try {
         ((CallableStatement)super.statement).addBatch(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void clearBatch() {
      try {
      try {
         ((CallableStatement)super.statement).clearBatch();
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
         return ((CallableStatement)super.statement).getMoreResults(var1);
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
         return ((CallableStatement)super.statement).getResultSetHoldability();
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
         ((CallableStatement)super.statement).setPoolable(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean isPoolable() {
      try {
      try {
         return ((CallableStatement)super.statement).isPoolable();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void closeOnCompletion() {
      try {
      try {
         ((CallableStatement)super.statement).closeOnCompletion();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean isCloseOnCompletion() {
      try {
      try {
         return ((CallableStatement)super.statement).isCloseOnCompletion();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public long getLargeUpdateCount() {
      try {
      try {
         return ((CallableStatement)super.statement).getLargeUpdateCount();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setLargeMaxRows(long var1) {
      try {
      try {
         ((CallableStatement)super.statement).setLargeMaxRows(var1);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public long getLargeMaxRows() {
      try {
      try {
         return ((CallableStatement)super.statement).getLargeMaxRows();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public long[] executeLargeBatch() {
      try {
      try {
         return ((CallableStatement)super.statement).executeLargeBatch();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public long executeLargeUpdate(String var1) {
      try {
      try {
         return ((CallableStatement)super.statement).executeLargeUpdate(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public long executeLargeUpdate(String var1, int var2) {
      try {
      try {
         return ((CallableStatement)super.statement).executeLargeUpdate(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public long executeLargeUpdate(String var1, int[] var2) {
      try {
      try {
         return ((CallableStatement)super.statement).executeLargeUpdate(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public long executeLargeUpdate(String var1, String[] var2) {
      try {
      try {
         return ((CallableStatement)super.statement).executeLargeUpdate(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public ResultSet executeQuery() {
      try {
      try {
         return super.executeQuery();
      } catch (Exception var2) {
         if (!(var2 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var2); }
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int executeUpdate() {
      try {
      try {
         return super.executeUpdate();
      } catch (Exception var2) {
         if (!(var2 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var2); }
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setNull(int var1, int var2) {
      try {
      try {
         ((CallableStatement)super.statement).setNull(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setBoolean(int var1, boolean var2) {
      try {
      try {
         ((CallableStatement)super.statement).setBoolean(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setByte(int var1, byte var2) {
      try {
      try {
         ((CallableStatement)super.statement).setByte(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setShort(int var1, short var2) {
      try {
      try {
         ((CallableStatement)super.statement).setShort(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setInt(int var1, int var2) {
      try {
      try {
         ((CallableStatement)super.statement).setInt(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setLong(int var1, long var2) {
      try {
      try {
         ((CallableStatement)super.statement).setLong(var1, var2);
      } catch (SQLException var5) {
         throw this.getSQLException((SQLException)var5);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setFloat(int var1, float var2) {
      try {
      try {
         ((CallableStatement)super.statement).setFloat(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setDouble(int var1, double var2) {
      try {
      try {
         ((CallableStatement)super.statement).setDouble(var1, var2);
      } catch (SQLException var5) {
         throw this.getSQLException((SQLException)var5);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setBigDecimal(int var1, BigDecimal var2) {
      try {
      try {
         ((CallableStatement)super.statement).setBigDecimal(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setString(int var1, String var2) {
      try {
      try {
         ((CallableStatement)super.statement).setString(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setBytes(int var1, byte[] var2) {
      try {
      try {
         ((CallableStatement)super.statement).setBytes(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setDate(int var1, Date var2) {
      try {
      try {
         ((CallableStatement)super.statement).setDate(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setTime(int var1, Time var2) {
      try {
      try {
         ((CallableStatement)super.statement).setTime(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setTimestamp(int var1, Timestamp var2) {
      try {
      try {
         ((CallableStatement)super.statement).setTimestamp(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setAsciiStream(int var1, InputStream var2, int var3) {
      try {
      try {
         ((CallableStatement)super.statement).setAsciiStream(var1, var2, var3);
      } catch (SQLException var5) {
         throw this.getSQLException((SQLException)var5);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setUnicodeStream(int var1, InputStream var2, int var3) {
      try {
      try {
         ((CallableStatement)super.statement).setUnicodeStream(var1, var2, var3);
      } catch (SQLException var5) {
         throw this.getSQLException((SQLException)var5);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setBinaryStream(int var1, InputStream var2, int var3) {
      try {
      try {
         ((CallableStatement)super.statement).setBinaryStream(var1, var2, var3);
      } catch (SQLException var5) {
         throw this.getSQLException((SQLException)var5);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void clearParameters() {
      try {
      try {
         ((CallableStatement)super.statement).clearParameters();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setObject(int var1, Object var2, int var3) {
      try {
      try {
         ((CallableStatement)super.statement).setObject(var1, var2, var3);
      } catch (SQLException var5) {
         throw this.getSQLException((SQLException)var5);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setObject(int var1, Object var2) {
      try {
      try {
         ((CallableStatement)super.statement).setObject(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean execute() {
      try {
      try {
         return super.execute();
      } catch (Exception var2) {
         if (!(var2 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var2); }
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void addBatch() {
      try {
      try {
         ((CallableStatement)super.statement).addBatch();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setCharacterStream(int var1, Reader var2, int var3) {
      try {
      try {
         ((CallableStatement)super.statement).setCharacterStream(var1, var2, var3);
      } catch (SQLException var5) {
         throw this.getSQLException((SQLException)var5);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setRef(int var1, Ref var2) {
      try {
      try {
         ((CallableStatement)super.statement).setRef(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setBlob(int var1, Blob var2) {
      try {
      try {
         ((CallableStatement)super.statement).setBlob(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setClob(int var1, Clob var2) {
      try {
      try {
         ((CallableStatement)super.statement).setClob(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setArray(int var1, Array var2) {
      try {
      try {
         ((CallableStatement)super.statement).setArray(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public ResultSetMetaData getMetaData() {
      try {
      try {
         return ((CallableStatement)super.statement).getMetaData();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setDate(int var1, Date var2, Calendar var3) {
      try {
      try {
         ((CallableStatement)super.statement).setDate(var1, var2, var3);
      } catch (SQLException var5) {
         throw this.getSQLException((SQLException)var5);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setTime(int var1, Time var2, Calendar var3) {
      try {
      try {
         ((CallableStatement)super.statement).setTime(var1, var2, var3);
      } catch (SQLException var5) {
         throw this.getSQLException((SQLException)var5);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setTimestamp(int var1, Timestamp var2, Calendar var3) {
      try {
      try {
         ((CallableStatement)super.statement).setTimestamp(var1, var2, var3);
      } catch (SQLException var5) {
         throw this.getSQLException((SQLException)var5);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setNull(int var1, int var2, String var3) {
      try {
      try {
         ((CallableStatement)super.statement).setNull(var1, var2, var3);
      } catch (SQLException var5) {
         throw this.getSQLException((SQLException)var5);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setURL(int var1, URL var2) {
      try {
      try {
         ((CallableStatement)super.statement).setURL(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public ParameterMetaData getParameterMetaData() {
      try {
      try {
         return ((CallableStatement)super.statement).getParameterMetaData();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setRowId(int var1, RowId var2) {
      try {
      try {
         ((CallableStatement)super.statement).setRowId(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setNString(int var1, String var2) {
      try {
      try {
         ((CallableStatement)super.statement).setNString(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setNCharacterStream(int var1, Reader var2, long var3) {
      try {
      try {
         ((CallableStatement)super.statement).setNCharacterStream(var1, var2, var3);
      } catch (SQLException var6) {
         throw this.getSQLException((SQLException)var6);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setNClob(int var1, NClob var2) {
      try {
      try {
         ((CallableStatement)super.statement).setNClob(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setClob(int var1, Reader var2, long var3) {
      try {
      try {
         ((CallableStatement)super.statement).setClob(var1, var2, var3);
      } catch (SQLException var6) {
         throw this.getSQLException((SQLException)var6);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setBlob(int var1, InputStream var2, long var3) {
      try {
      try {
         ((CallableStatement)super.statement).setBlob(var1, var2, var3);
      } catch (SQLException var6) {
         throw this.getSQLException((SQLException)var6);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setNClob(int var1, Reader var2, long var3) {
      try {
      try {
         ((CallableStatement)super.statement).setNClob(var1, var2, var3);
      } catch (SQLException var6) {
         throw this.getSQLException((SQLException)var6);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setSQLXML(int var1, SQLXML var2) {
      try {
      try {
         ((CallableStatement)super.statement).setSQLXML(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setObject(int var1, Object var2, int var3, int var4) {
      try {
      try {
         ((CallableStatement)super.statement).setObject(var1, var2, var3, var4);
      } catch (SQLException var6) {
         throw this.getSQLException((SQLException)var6);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setAsciiStream(int var1, InputStream var2, long var3) {
      try {
      try {
         ((CallableStatement)super.statement).setAsciiStream(var1, var2, var3);
      } catch (SQLException var6) {
         throw this.getSQLException((SQLException)var6);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setBinaryStream(int var1, InputStream var2, long var3) {
      try {
      try {
         ((CallableStatement)super.statement).setBinaryStream(var1, var2, var3);
      } catch (SQLException var6) {
         throw this.getSQLException((SQLException)var6);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setCharacterStream(int var1, Reader var2, long var3) {
      try {
      try {
         ((CallableStatement)super.statement).setCharacterStream(var1, var2, var3);
      } catch (SQLException var6) {
         throw this.getSQLException((SQLException)var6);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setAsciiStream(int var1, InputStream var2) {
      try {
      try {
         ((CallableStatement)super.statement).setAsciiStream(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setBinaryStream(int var1, InputStream var2) {
      try {
      try {
         ((CallableStatement)super.statement).setBinaryStream(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setCharacterStream(int var1, Reader var2) {
      try {
      try {
         ((CallableStatement)super.statement).setCharacterStream(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setNCharacterStream(int var1, Reader var2) {
      try {
      try {
         ((CallableStatement)super.statement).setNCharacterStream(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setClob(int var1, Reader var2) {
      try {
      try {
         ((CallableStatement)super.statement).setClob(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setBlob(int var1, InputStream var2) {
      try {
      try {
         ((CallableStatement)super.statement).setBlob(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setNClob(int var1, Reader var2) {
      try {
      try {
         ((CallableStatement)super.statement).setNClob(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setObject(int var1, Object var2, SQLType var3, int var4) {
      try {
      try {
         ((CallableStatement)super.statement).setObject(var1, var2, var3, var4);
      } catch (SQLException var6) {
         throw this.getSQLException((SQLException)var6);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setObject(int var1, Object var2, SQLType var3) {
      try {
      try {
         ((CallableStatement)super.statement).setObject(var1, var2, var3);
      } catch (SQLException var5) {
         throw this.getSQLException((SQLException)var5);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public long executeLargeUpdate() {
      try {
      try {
         return ((CallableStatement)super.statement).executeLargeUpdate();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void registerOutParameter(int var1, int var2) {
      try {
      try {
         ((CallableStatement)super.statement).registerOutParameter(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void registerOutParameter(int var1, int var2, int var3) {
      try {
      try {
         ((CallableStatement)super.statement).registerOutParameter(var1, var2, var3);
      } catch (SQLException var5) {
         throw this.getSQLException((SQLException)var5);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean wasNull() {
      try {
      try {
         return ((CallableStatement)super.statement).wasNull();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public String getString(int var1) {
      try {
      try {
         return ((CallableStatement)super.statement).getString(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean getBoolean(int var1) {
      try {
      try {
         return ((CallableStatement)super.statement).getBoolean(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public byte getByte(int var1) {
      try {
      try {
         return ((CallableStatement)super.statement).getByte(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public short getShort(int var1) {
      try {
      try {
         return ((CallableStatement)super.statement).getShort(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int getInt(int var1) {
      try {
      try {
         return ((CallableStatement)super.statement).getInt(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public long getLong(int var1) {
      try {
      try {
         return ((CallableStatement)super.statement).getLong(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public float getFloat(int var1) {
      try {
      try {
         return ((CallableStatement)super.statement).getFloat(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public double getDouble(int var1) {
      try {
      try {
         return ((CallableStatement)super.statement).getDouble(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public BigDecimal getBigDecimal(int var1, int var2) {
      try {
      try {
         return ((CallableStatement)super.statement).getBigDecimal(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public byte[] getBytes(int var1) {
      try {
      try {
         return ((CallableStatement)super.statement).getBytes(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Date getDate(int var1) {
      try {
      try {
         return ((CallableStatement)super.statement).getDate(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Time getTime(int var1) {
      try {
      try {
         return ((CallableStatement)super.statement).getTime(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Timestamp getTimestamp(int var1) {
      try {
      try {
         return ((CallableStatement)super.statement).getTimestamp(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Object getObject(int var1) {
      try {
      try {
         return ((CallableStatement)super.statement).getObject(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public BigDecimal getBigDecimal(int var1) {
      try {
      try {
         return ((CallableStatement)super.statement).getBigDecimal(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Object getObject(int var1, Map var2) {
      try {
      try {
         return ((CallableStatement)super.statement).getObject(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Ref getRef(int var1) {
      try {
      try {
         return ((CallableStatement)super.statement).getRef(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Blob getBlob(int var1) {
      try {
      try {
         return ((CallableStatement)super.statement).getBlob(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Clob getClob(int var1) {
      try {
      try {
         return ((CallableStatement)super.statement).getClob(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Array getArray(int var1) {
      try {
      try {
         return ((CallableStatement)super.statement).getArray(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Date getDate(int var1, Calendar var2) {
      try {
      try {
         return ((CallableStatement)super.statement).getDate(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Time getTime(int var1, Calendar var2) {
      try {
      try {
         return ((CallableStatement)super.statement).getTime(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Timestamp getTimestamp(int var1, Calendar var2) {
      try {
      try {
         return ((CallableStatement)super.statement).getTimestamp(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void registerOutParameter(int var1, int var2, String var3) {
      try {
      try {
         ((CallableStatement)super.statement).registerOutParameter(var1, var2, var3);
      } catch (SQLException var5) {
         throw this.getSQLException((SQLException)var5);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void registerOutParameter(String var1, int var2) {
      try {
      try {
         ((CallableStatement)super.statement).registerOutParameter(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void registerOutParameter(String var1, int var2, int var3) {
      try {
      try {
         ((CallableStatement)super.statement).registerOutParameter(var1, var2, var3);
      } catch (SQLException var5) {
         throw this.getSQLException((SQLException)var5);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void registerOutParameter(String var1, int var2, String var3) {
      try {
      try {
         ((CallableStatement)super.statement).registerOutParameter(var1, var2, var3);
      } catch (SQLException var5) {
         throw this.getSQLException((SQLException)var5);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public URL getURL(int var1) {
      try {
      try {
         return ((CallableStatement)super.statement).getURL(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setURL(String var1, URL var2) {
      try {
      try {
         ((CallableStatement)super.statement).setURL(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setNull(String var1, int var2) {
      try {
      try {
         ((CallableStatement)super.statement).setNull(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setBoolean(String var1, boolean var2) {
      try {
      try {
         ((CallableStatement)super.statement).setBoolean(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setByte(String var1, byte var2) {
      try {
      try {
         ((CallableStatement)super.statement).setByte(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setShort(String var1, short var2) {
      try {
      try {
         ((CallableStatement)super.statement).setShort(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setInt(String var1, int var2) {
      try {
      try {
         ((CallableStatement)super.statement).setInt(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setLong(String var1, long var2) {
      try {
      try {
         ((CallableStatement)super.statement).setLong(var1, var2);
      } catch (SQLException var5) {
         throw this.getSQLException((SQLException)var5);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setFloat(String var1, float var2) {
      try {
      try {
         ((CallableStatement)super.statement).setFloat(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setDouble(String var1, double var2) {
      try {
      try {
         ((CallableStatement)super.statement).setDouble(var1, var2);
      } catch (SQLException var5) {
         throw this.getSQLException((SQLException)var5);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setBigDecimal(String var1, BigDecimal var2) {
      try {
      try {
         ((CallableStatement)super.statement).setBigDecimal(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setString(String var1, String var2) {
      try {
      try {
         ((CallableStatement)super.statement).setString(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setBytes(String var1, byte[] var2) {
      try {
      try {
         ((CallableStatement)super.statement).setBytes(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setDate(String var1, Date var2) {
      try {
      try {
         ((CallableStatement)super.statement).setDate(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setTime(String var1, Time var2) {
      try {
      try {
         ((CallableStatement)super.statement).setTime(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setTimestamp(String var1, Timestamp var2) {
      try {
      try {
         ((CallableStatement)super.statement).setTimestamp(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setAsciiStream(String var1, InputStream var2, int var3) {
      try {
      try {
         ((CallableStatement)super.statement).setAsciiStream(var1, var2, var3);
      } catch (SQLException var5) {
         throw this.getSQLException((SQLException)var5);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setBinaryStream(String var1, InputStream var2, int var3) {
      try {
      try {
         ((CallableStatement)super.statement).setBinaryStream(var1, var2, var3);
      } catch (SQLException var5) {
         throw this.getSQLException((SQLException)var5);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setObject(String var1, Object var2, int var3, int var4) {
      try {
      try {
         ((CallableStatement)super.statement).setObject(var1, var2, var3, var4);
      } catch (SQLException var6) {
         throw this.getSQLException((SQLException)var6);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setObject(String var1, Object var2, int var3) {
      try {
      try {
         ((CallableStatement)super.statement).setObject(var1, var2, var3);
      } catch (SQLException var5) {
         throw this.getSQLException((SQLException)var5);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setObject(String var1, Object var2) {
      try {
      try {
         ((CallableStatement)super.statement).setObject(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setCharacterStream(String var1, Reader var2, int var3) {
      try {
      try {
         ((CallableStatement)super.statement).setCharacterStream(var1, var2, var3);
      } catch (SQLException var5) {
         throw this.getSQLException((SQLException)var5);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setDate(String var1, Date var2, Calendar var3) {
      try {
      try {
         ((CallableStatement)super.statement).setDate(var1, var2, var3);
      } catch (SQLException var5) {
         throw this.getSQLException((SQLException)var5);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setTime(String var1, Time var2, Calendar var3) {
      try {
      try {
         ((CallableStatement)super.statement).setTime(var1, var2, var3);
      } catch (SQLException var5) {
         throw this.getSQLException((SQLException)var5);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setTimestamp(String var1, Timestamp var2, Calendar var3) {
      try {
      try {
         ((CallableStatement)super.statement).setTimestamp(var1, var2, var3);
      } catch (SQLException var5) {
         throw this.getSQLException((SQLException)var5);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setNull(String var1, int var2, String var3) {
      try {
      try {
         ((CallableStatement)super.statement).setNull(var1, var2, var3);
      } catch (SQLException var5) {
         throw this.getSQLException((SQLException)var5);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public String getString(String var1) {
      try {
      try {
         return ((CallableStatement)super.statement).getString(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean getBoolean(String var1) {
      try {
      try {
         return ((CallableStatement)super.statement).getBoolean(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public byte getByte(String var1) {
      try {
      try {
         return ((CallableStatement)super.statement).getByte(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public short getShort(String var1) {
      try {
      try {
         return ((CallableStatement)super.statement).getShort(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int getInt(String var1) {
      try {
      try {
         return ((CallableStatement)super.statement).getInt(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public long getLong(String var1) {
      try {
      try {
         return ((CallableStatement)super.statement).getLong(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public float getFloat(String var1) {
      try {
      try {
         return ((CallableStatement)super.statement).getFloat(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public double getDouble(String var1) {
      try {
      try {
         return ((CallableStatement)super.statement).getDouble(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public byte[] getBytes(String var1) {
      try {
      try {
         return ((CallableStatement)super.statement).getBytes(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Date getDate(String var1) {
      try {
      try {
         return ((CallableStatement)super.statement).getDate(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Time getTime(String var1) {
      try {
      try {
         return ((CallableStatement)super.statement).getTime(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Timestamp getTimestamp(String var1) {
      try {
      try {
         return ((CallableStatement)super.statement).getTimestamp(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Object getObject(String var1) {
      try {
      try {
         return ((CallableStatement)super.statement).getObject(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public BigDecimal getBigDecimal(String var1) {
      try {
      try {
         return ((CallableStatement)super.statement).getBigDecimal(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Object getObject(String var1, Map var2) {
      try {
      try {
         return ((CallableStatement)super.statement).getObject(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Ref getRef(String var1) {
      try {
      try {
         return ((CallableStatement)super.statement).getRef(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Blob getBlob(String var1) {
      try {
      try {
         return ((CallableStatement)super.statement).getBlob(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Clob getClob(String var1) {
      try {
      try {
         return ((CallableStatement)super.statement).getClob(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Array getArray(String var1) {
      try {
      try {
         return ((CallableStatement)super.statement).getArray(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Date getDate(String var1, Calendar var2) {
      try {
      try {
         return ((CallableStatement)super.statement).getDate(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Time getTime(String var1, Calendar var2) {
      try {
      try {
         return ((CallableStatement)super.statement).getTime(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Timestamp getTimestamp(String var1, Calendar var2) {
      try {
      try {
         return ((CallableStatement)super.statement).getTimestamp(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public URL getURL(String var1) {
      try {
      try {
         return ((CallableStatement)super.statement).getURL(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public RowId getRowId(int var1) {
      try {
      try {
         return ((CallableStatement)super.statement).getRowId(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public RowId getRowId(String var1) {
      try {
      try {
         return ((CallableStatement)super.statement).getRowId(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setRowId(String var1, RowId var2) {
      try {
      try {
         ((CallableStatement)super.statement).setRowId(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setNString(String var1, String var2) {
      try {
      try {
         ((CallableStatement)super.statement).setNString(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setNCharacterStream(String var1, Reader var2, long var3) {
      try {
      try {
         ((CallableStatement)super.statement).setNCharacterStream(var1, var2, var3);
      } catch (SQLException var6) {
         throw this.getSQLException((SQLException)var6);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setNClob(String var1, NClob var2) {
      try {
      try {
         ((CallableStatement)super.statement).setNClob(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setClob(String var1, Reader var2, long var3) {
      try {
      try {
         ((CallableStatement)super.statement).setClob(var1, var2, var3);
      } catch (SQLException var6) {
         throw this.getSQLException((SQLException)var6);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setBlob(String var1, InputStream var2, long var3) {
      try {
      try {
         ((CallableStatement)super.statement).setBlob(var1, var2, var3);
      } catch (SQLException var6) {
         throw this.getSQLException((SQLException)var6);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setNClob(String var1, Reader var2, long var3) {
      try {
      try {
         ((CallableStatement)super.statement).setNClob(var1, var2, var3);
      } catch (SQLException var6) {
         throw this.getSQLException((SQLException)var6);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public NClob getNClob(int var1) {
      try {
      try {
         return ((CallableStatement)super.statement).getNClob(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public NClob getNClob(String var1) {
      try {
      try {
         return ((CallableStatement)super.statement).getNClob(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setSQLXML(String var1, SQLXML var2) {
      try {
      try {
         ((CallableStatement)super.statement).setSQLXML(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public SQLXML getSQLXML(int var1) {
      try {
      try {
         return ((CallableStatement)super.statement).getSQLXML(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public SQLXML getSQLXML(String var1) {
      try {
      try {
         return ((CallableStatement)super.statement).getSQLXML(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public String getNString(int var1) {
      try {
      try {
         return ((CallableStatement)super.statement).getNString(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public String getNString(String var1) {
      try {
      try {
         return ((CallableStatement)super.statement).getNString(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Reader getNCharacterStream(int var1) {
      try {
      try {
         return ((CallableStatement)super.statement).getNCharacterStream(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Reader getNCharacterStream(String var1) {
      try {
      try {
         return ((CallableStatement)super.statement).getNCharacterStream(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Reader getCharacterStream(int var1) {
      try {
      try {
         return ((CallableStatement)super.statement).getCharacterStream(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Reader getCharacterStream(String var1) {
      try {
      try {
         return ((CallableStatement)super.statement).getCharacterStream(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setBlob(String var1, Blob var2) {
      try {
      try {
         ((CallableStatement)super.statement).setBlob(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setClob(String var1, Clob var2) {
      try {
      try {
         ((CallableStatement)super.statement).setClob(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setAsciiStream(String var1, InputStream var2, long var3) {
      try {
      try {
         ((CallableStatement)super.statement).setAsciiStream(var1, var2, var3);
      } catch (SQLException var6) {
         throw this.getSQLException((SQLException)var6);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setBinaryStream(String var1, InputStream var2, long var3) {
      try {
      try {
         ((CallableStatement)super.statement).setBinaryStream(var1, var2, var3);
      } catch (SQLException var6) {
         throw this.getSQLException((SQLException)var6);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setCharacterStream(String var1, Reader var2, long var3) {
      try {
      try {
         ((CallableStatement)super.statement).setCharacterStream(var1, var2, var3);
      } catch (SQLException var6) {
         throw this.getSQLException((SQLException)var6);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setAsciiStream(String var1, InputStream var2) {
      try {
      try {
         ((CallableStatement)super.statement).setAsciiStream(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setBinaryStream(String var1, InputStream var2) {
      try {
      try {
         ((CallableStatement)super.statement).setBinaryStream(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setCharacterStream(String var1, Reader var2) {
      try {
      try {
         ((CallableStatement)super.statement).setCharacterStream(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setNCharacterStream(String var1, Reader var2) {
      try {
      try {
         ((CallableStatement)super.statement).setNCharacterStream(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setClob(String var1, Reader var2) {
      try {
      try {
         ((CallableStatement)super.statement).setClob(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setBlob(String var1, InputStream var2) {
      try {
      try {
         ((CallableStatement)super.statement).setBlob(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setNClob(String var1, Reader var2) {
      try {
      try {
         ((CallableStatement)super.statement).setNClob(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Object getObject(int var1, Class var2) {
      try {
      try {
         return ((CallableStatement)super.statement).getObject(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Object getObject(String var1, Class var2) {
      try {
      try {
         return ((CallableStatement)super.statement).getObject(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setObject(String var1, Object var2, SQLType var3, int var4) {
      try {
      try {
         ((CallableStatement)super.statement).setObject(var1, var2, var3, var4);
      } catch (SQLException var6) {
         throw this.getSQLException((SQLException)var6);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setObject(String var1, Object var2, SQLType var3) {
      try {
      try {
         ((CallableStatement)super.statement).setObject(var1, var2, var3);
      } catch (SQLException var5) {
         throw this.getSQLException((SQLException)var5);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void registerOutParameter(int var1, SQLType var2) {
      try {
      try {
         ((CallableStatement)super.statement).registerOutParameter(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void registerOutParameter(int var1, SQLType var2, int var3) {
      try {
      try {
         ((CallableStatement)super.statement).registerOutParameter(var1, var2, var3);
      } catch (SQLException var5) {
         throw this.getSQLException((SQLException)var5);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void registerOutParameter(int var1, SQLType var2, String var3) {
      try {
      try {
         ((CallableStatement)super.statement).registerOutParameter(var1, var2, var3);
      } catch (SQLException var5) {
         throw this.getSQLException((SQLException)var5);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void registerOutParameter(String var1, SQLType var2) {
      try {
      try {
         ((CallableStatement)super.statement).registerOutParameter(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void registerOutParameter(String var1, SQLType var2, int var3) {
      try {
      try {
         ((CallableStatement)super.statement).registerOutParameter(var1, var2, var3);
      } catch (SQLException var5) {
         throw this.getSQLException((SQLException)var5);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void registerOutParameter(String var1, SQLType var2, String var3) {
      try {
      try {
         ((CallableStatement)super.statement).registerOutParameter(var1, var2, var3);
      } catch (SQLException var5) {
         throw this.getSQLException((SQLException)var5);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   public HikariProxyCallableStatement(ProxyConnection var1, CallableStatement var2) {
      super(var1, var2);
   }
}
