package litebans.lib.hikari;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
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

public final class HikariProxyPreparedStatement extends ProxyPreparedStatement implements Wrapper, AutoCloseable, Statement, PreparedStatement {
   @Override
   public boolean isWrapperFor(Class var1) {
      try {
      try {
         return ((PreparedStatement)super.statement).isWrapperFor(var1);
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
         return ((PreparedStatement)super.statement).getMaxFieldSize();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setMaxFieldSize(int var1) {
      try {
      try {
         ((PreparedStatement)super.statement).setMaxFieldSize(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int getMaxRows() {
      try {
      try {
         return ((PreparedStatement)super.statement).getMaxRows();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setMaxRows(int var1) {
      try {
      try {
         ((PreparedStatement)super.statement).setMaxRows(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setEscapeProcessing(boolean var1) {
      try {
      try {
         ((PreparedStatement)super.statement).setEscapeProcessing(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int getQueryTimeout() {
      try {
      try {
         return ((PreparedStatement)super.statement).getQueryTimeout();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setQueryTimeout(int var1) {
      try {
      try {
         ((PreparedStatement)super.statement).setQueryTimeout(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void cancel() {
      try {
      try {
         ((PreparedStatement)super.statement).cancel();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public SQLWarning getWarnings() {
      try {
      try {
         return ((PreparedStatement)super.statement).getWarnings();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void clearWarnings() {
      try {
      try {
         ((PreparedStatement)super.statement).clearWarnings();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setCursorName(String var1) {
      try {
      try {
         ((PreparedStatement)super.statement).setCursorName(var1);
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
         return ((PreparedStatement)super.statement).getUpdateCount();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean getMoreResults() {
      try {
      try {
         return ((PreparedStatement)super.statement).getMoreResults();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setFetchDirection(int var1) {
      try {
      try {
         ((PreparedStatement)super.statement).setFetchDirection(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int getFetchDirection() {
      try {
      try {
         return ((PreparedStatement)super.statement).getFetchDirection();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setFetchSize(int var1) {
      try {
      try {
         ((PreparedStatement)super.statement).setFetchSize(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int getFetchSize() {
      try {
      try {
         return ((PreparedStatement)super.statement).getFetchSize();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int getResultSetConcurrency() {
      try {
      try {
         return ((PreparedStatement)super.statement).getResultSetConcurrency();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int getResultSetType() {
      try {
      try {
         return ((PreparedStatement)super.statement).getResultSetType();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void addBatch(String var1) {
      try {
      try {
         ((PreparedStatement)super.statement).addBatch(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void clearBatch() {
      try {
      try {
         ((PreparedStatement)super.statement).clearBatch();
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
         return ((PreparedStatement)super.statement).getMoreResults(var1);
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
         return ((PreparedStatement)super.statement).getResultSetHoldability();
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
         ((PreparedStatement)super.statement).setPoolable(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean isPoolable() {
      try {
      try {
         return ((PreparedStatement)super.statement).isPoolable();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void closeOnCompletion() {
      try {
      try {
         ((PreparedStatement)super.statement).closeOnCompletion();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean isCloseOnCompletion() {
      try {
      try {
         return ((PreparedStatement)super.statement).isCloseOnCompletion();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public long getLargeUpdateCount() {
      try {
      try {
         return ((PreparedStatement)super.statement).getLargeUpdateCount();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setLargeMaxRows(long var1) {
      try {
      try {
         ((PreparedStatement)super.statement).setLargeMaxRows(var1);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public long getLargeMaxRows() {
      try {
      try {
         return ((PreparedStatement)super.statement).getLargeMaxRows();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public long[] executeLargeBatch() {
      try {
      try {
         return ((PreparedStatement)super.statement).executeLargeBatch();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public long executeLargeUpdate(String var1) {
      try {
      try {
         return ((PreparedStatement)super.statement).executeLargeUpdate(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public long executeLargeUpdate(String var1, int var2) {
      try {
      try {
         return ((PreparedStatement)super.statement).executeLargeUpdate(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public long executeLargeUpdate(String var1, int[] var2) {
      try {
      try {
         return ((PreparedStatement)super.statement).executeLargeUpdate(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public long executeLargeUpdate(String var1, String[] var2) {
      try {
      try {
         return ((PreparedStatement)super.statement).executeLargeUpdate(var1, var2);
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
         ((PreparedStatement)super.statement).setNull(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setBoolean(int var1, boolean var2) {
      try {
      try {
         ((PreparedStatement)super.statement).setBoolean(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setByte(int var1, byte var2) {
      try {
      try {
         ((PreparedStatement)super.statement).setByte(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setShort(int var1, short var2) {
      try {
      try {
         ((PreparedStatement)super.statement).setShort(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setInt(int var1, int var2) {
      try {
      try {
         ((PreparedStatement)super.statement).setInt(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setLong(int var1, long var2) {
      try {
      try {
         ((PreparedStatement)super.statement).setLong(var1, var2);
      } catch (SQLException var5) {
         throw this.getSQLException((SQLException)var5);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setFloat(int var1, float var2) {
      try {
      try {
         ((PreparedStatement)super.statement).setFloat(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setDouble(int var1, double var2) {
      try {
      try {
         ((PreparedStatement)super.statement).setDouble(var1, var2);
      } catch (SQLException var5) {
         throw this.getSQLException((SQLException)var5);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setBigDecimal(int var1, BigDecimal var2) {
      try {
      try {
         ((PreparedStatement)super.statement).setBigDecimal(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setString(int var1, String var2) {
      try {
      try {
         ((PreparedStatement)super.statement).setString(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setBytes(int var1, byte[] var2) {
      try {
      try {
         ((PreparedStatement)super.statement).setBytes(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setDate(int var1, Date var2) {
      try {
      try {
         ((PreparedStatement)super.statement).setDate(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setTime(int var1, Time var2) {
      try {
      try {
         ((PreparedStatement)super.statement).setTime(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setTimestamp(int var1, Timestamp var2) {
      try {
      try {
         ((PreparedStatement)super.statement).setTimestamp(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setAsciiStream(int var1, InputStream var2, int var3) {
      try {
      try {
         ((PreparedStatement)super.statement).setAsciiStream(var1, var2, var3);
      } catch (SQLException var5) {
         throw this.getSQLException((SQLException)var5);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setUnicodeStream(int var1, InputStream var2, int var3) {
      try {
      try {
         ((PreparedStatement)super.statement).setUnicodeStream(var1, var2, var3);
      } catch (SQLException var5) {
         throw this.getSQLException((SQLException)var5);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setBinaryStream(int var1, InputStream var2, int var3) {
      try {
      try {
         ((PreparedStatement)super.statement).setBinaryStream(var1, var2, var3);
      } catch (SQLException var5) {
         throw this.getSQLException((SQLException)var5);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void clearParameters() {
      try {
      try {
         ((PreparedStatement)super.statement).clearParameters();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setObject(int var1, Object var2, int var3) {
      try {
      try {
         ((PreparedStatement)super.statement).setObject(var1, var2, var3);
      } catch (SQLException var5) {
         throw this.getSQLException((SQLException)var5);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setObject(int var1, Object var2) {
      try {
      try {
         ((PreparedStatement)super.statement).setObject(var1, var2);
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
         ((PreparedStatement)super.statement).addBatch();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setCharacterStream(int var1, Reader var2, int var3) {
      try {
      try {
         ((PreparedStatement)super.statement).setCharacterStream(var1, var2, var3);
      } catch (SQLException var5) {
         throw this.getSQLException((SQLException)var5);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setRef(int var1, Ref var2) {
      try {
      try {
         ((PreparedStatement)super.statement).setRef(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setBlob(int var1, Blob var2) {
      try {
      try {
         ((PreparedStatement)super.statement).setBlob(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setClob(int var1, Clob var2) {
      try {
      try {
         ((PreparedStatement)super.statement).setClob(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setArray(int var1, Array var2) {
      try {
      try {
         ((PreparedStatement)super.statement).setArray(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public ResultSetMetaData getMetaData() {
      try {
      try {
         return ((PreparedStatement)super.statement).getMetaData();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setDate(int var1, Date var2, Calendar var3) {
      try {
      try {
         ((PreparedStatement)super.statement).setDate(var1, var2, var3);
      } catch (SQLException var5) {
         throw this.getSQLException((SQLException)var5);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setTime(int var1, Time var2, Calendar var3) {
      try {
      try {
         ((PreparedStatement)super.statement).setTime(var1, var2, var3);
      } catch (SQLException var5) {
         throw this.getSQLException((SQLException)var5);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setTimestamp(int var1, Timestamp var2, Calendar var3) {
      try {
      try {
         ((PreparedStatement)super.statement).setTimestamp(var1, var2, var3);
      } catch (SQLException var5) {
         throw this.getSQLException((SQLException)var5);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setNull(int var1, int var2, String var3) {
      try {
      try {
         ((PreparedStatement)super.statement).setNull(var1, var2, var3);
      } catch (SQLException var5) {
         throw this.getSQLException((SQLException)var5);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setURL(int var1, URL var2) {
      try {
      try {
         ((PreparedStatement)super.statement).setURL(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public ParameterMetaData getParameterMetaData() {
      try {
      try {
         return ((PreparedStatement)super.statement).getParameterMetaData();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setRowId(int var1, RowId var2) {
      try {
      try {
         ((PreparedStatement)super.statement).setRowId(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setNString(int var1, String var2) {
      try {
      try {
         ((PreparedStatement)super.statement).setNString(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setNCharacterStream(int var1, Reader var2, long var3) {
      try {
      try {
         ((PreparedStatement)super.statement).setNCharacterStream(var1, var2, var3);
      } catch (SQLException var6) {
         throw this.getSQLException((SQLException)var6);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setNClob(int var1, NClob var2) {
      try {
      try {
         ((PreparedStatement)super.statement).setNClob(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setClob(int var1, Reader var2, long var3) {
      try {
      try {
         ((PreparedStatement)super.statement).setClob(var1, var2, var3);
      } catch (SQLException var6) {
         throw this.getSQLException((SQLException)var6);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setBlob(int var1, InputStream var2, long var3) {
      try {
      try {
         ((PreparedStatement)super.statement).setBlob(var1, var2, var3);
      } catch (SQLException var6) {
         throw this.getSQLException((SQLException)var6);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setNClob(int var1, Reader var2, long var3) {
      try {
      try {
         ((PreparedStatement)super.statement).setNClob(var1, var2, var3);
      } catch (SQLException var6) {
         throw this.getSQLException((SQLException)var6);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setSQLXML(int var1, SQLXML var2) {
      try {
      try {
         ((PreparedStatement)super.statement).setSQLXML(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setObject(int var1, Object var2, int var3, int var4) {
      try {
      try {
         ((PreparedStatement)super.statement).setObject(var1, var2, var3, var4);
      } catch (SQLException var6) {
         throw this.getSQLException((SQLException)var6);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setAsciiStream(int var1, InputStream var2, long var3) {
      try {
      try {
         ((PreparedStatement)super.statement).setAsciiStream(var1, var2, var3);
      } catch (SQLException var6) {
         throw this.getSQLException((SQLException)var6);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setBinaryStream(int var1, InputStream var2, long var3) {
      try {
      try {
         ((PreparedStatement)super.statement).setBinaryStream(var1, var2, var3);
      } catch (SQLException var6) {
         throw this.getSQLException((SQLException)var6);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setCharacterStream(int var1, Reader var2, long var3) {
      try {
      try {
         ((PreparedStatement)super.statement).setCharacterStream(var1, var2, var3);
      } catch (SQLException var6) {
         throw this.getSQLException((SQLException)var6);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setAsciiStream(int var1, InputStream var2) {
      try {
      try {
         ((PreparedStatement)super.statement).setAsciiStream(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setBinaryStream(int var1, InputStream var2) {
      try {
      try {
         ((PreparedStatement)super.statement).setBinaryStream(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setCharacterStream(int var1, Reader var2) {
      try {
      try {
         ((PreparedStatement)super.statement).setCharacterStream(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setNCharacterStream(int var1, Reader var2) {
      try {
      try {
         ((PreparedStatement)super.statement).setNCharacterStream(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setClob(int var1, Reader var2) {
      try {
      try {
         ((PreparedStatement)super.statement).setClob(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setBlob(int var1, InputStream var2) {
      try {
      try {
         ((PreparedStatement)super.statement).setBlob(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setNClob(int var1, Reader var2) {
      try {
      try {
         ((PreparedStatement)super.statement).setNClob(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setObject(int var1, Object var2, SQLType var3, int var4) {
      try {
      try {
         ((PreparedStatement)super.statement).setObject(var1, var2, var3, var4);
      } catch (SQLException var6) {
         throw this.getSQLException((SQLException)var6);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setObject(int var1, Object var2, SQLType var3) {
      try {
      try {
         ((PreparedStatement)super.statement).setObject(var1, var2, var3);
      } catch (SQLException var5) {
         throw this.getSQLException((SQLException)var5);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public long executeLargeUpdate() {
      try {
      try {
         return ((PreparedStatement)super.statement).executeLargeUpdate();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   public HikariProxyPreparedStatement(ProxyConnection var1, PreparedStatement var2) {
      super(var1, var2);
   }
}
