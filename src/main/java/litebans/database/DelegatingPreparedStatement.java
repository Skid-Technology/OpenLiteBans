package litebans.database;

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
import java.sql.SQLType;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;

public abstract class DelegatingPreparedStatement implements PreparedStatement {
   private final PreparedStatement preparedStatement;

   public DelegatingPreparedStatement(PreparedStatement var1) {
      this.preparedStatement = var1;
   }

   @Override
   public ResultSet executeQuery() {
      try {
      return this.preparedStatement.executeQuery();
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int executeUpdate() {
      try {
      return this.preparedStatement.executeUpdate();
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setNull(int var1, int var2) {
      try {
      this.preparedStatement.setNull(var1, var2);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setBoolean(int var1, boolean var2) {
      try {
      this.preparedStatement.setBoolean(var1, var2);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setByte(int var1, byte var2) {
      try {
      this.preparedStatement.setByte(var1, var2);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setShort(int var1, short var2) {
      try {
      this.preparedStatement.setShort(var1, var2);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setInt(int var1, int var2) {
      try {
      this.preparedStatement.setInt(var1, var2);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setLong(int var1, long var2) {
      try {
      this.preparedStatement.setLong(var1, var2);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setFloat(int var1, float var2) {
      try {
      this.preparedStatement.setFloat(var1, var2);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setDouble(int var1, double var2) {
      try {
      this.preparedStatement.setDouble(var1, var2);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setBigDecimal(int var1, BigDecimal var2) {
      try {
      this.preparedStatement.setBigDecimal(var1, var2);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setString(int var1, String var2) {
      try {
      this.preparedStatement.setString(var1, var2);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setBytes(int var1, byte[] var2) {
      try {
      this.preparedStatement.setBytes(var1, var2);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setDate(int var1, Date var2) {
      try {
      this.preparedStatement.setDate(var1, var2);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setTime(int var1, Time var2) {
      try {
      this.preparedStatement.setTime(var1, var2);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setTimestamp(int var1, Timestamp var2) {
      try {
      this.preparedStatement.setTimestamp(var1, var2);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setAsciiStream(int var1, InputStream var2, int var3) {
      try {
      this.preparedStatement.setAsciiStream(var1, var2, var3);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setUnicodeStream(int var1, InputStream var2, int var3) {
      try {
      this.preparedStatement.setUnicodeStream(var1, var2, var3);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setBinaryStream(int var1, InputStream var2, int var3) {
      try {
      this.preparedStatement.setBinaryStream(var1, var2, var3);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void clearParameters() {
      try {
      this.preparedStatement.clearParameters();
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setObject(int var1, Object var2, int var3) {
      try {
      this.preparedStatement.setObject(var1, var2, var3);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setObject(int var1, Object var2) {
      try {
      this.preparedStatement.setObject(var1, var2);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean execute() {
      try {
      return this.preparedStatement.execute();
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void addBatch() {
      try {
      this.preparedStatement.addBatch();
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setCharacterStream(int var1, Reader var2, int var3) {
      try {
      this.preparedStatement.setCharacterStream(var1, var2, var3);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setRef(int var1, Ref var2) {
      try {
      this.preparedStatement.setRef(var1, var2);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setBlob(int var1, Blob var2) {
      try {
      this.preparedStatement.setBlob(var1, var2);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setClob(int var1, Clob var2) {
      try {
      this.preparedStatement.setClob(var1, var2);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setArray(int var1, Array var2) {
      try {
      this.preparedStatement.setArray(var1, var2);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public ResultSetMetaData getMetaData() {
      try {
      return this.preparedStatement.getMetaData();
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setDate(int var1, Date var2, Calendar var3) {
      try {
      this.preparedStatement.setDate(var1, var2, var3);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setTime(int var1, Time var2, Calendar var3) {
      try {
      this.preparedStatement.setTime(var1, var2, var3);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setTimestamp(int var1, Timestamp var2, Calendar var3) {
      try {
      this.preparedStatement.setTimestamp(var1, var2, var3);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setNull(int var1, int var2, String var3) {
      try {
      this.preparedStatement.setNull(var1, var2, var3);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setURL(int var1, URL var2) {
      try {
      this.preparedStatement.setURL(var1, var2);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public ParameterMetaData getParameterMetaData() {
      try {
      return this.preparedStatement.getParameterMetaData();
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setRowId(int var1, RowId var2) {
      try {
      this.preparedStatement.setRowId(var1, var2);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setNString(int var1, String var2) {
      try {
      this.preparedStatement.setNString(var1, var2);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setNCharacterStream(int var1, Reader var2, long var3) {
      try {
      this.preparedStatement.setNCharacterStream(var1, var2, var3);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setNClob(int var1, NClob var2) {
      try {
      this.preparedStatement.setNClob(var1, var2);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setClob(int var1, Reader var2, long var3) {
      try {
      this.preparedStatement.setClob(var1, var2, var3);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setBlob(int var1, InputStream var2, long var3) {
      try {
      this.preparedStatement.setBlob(var1, var2, var3);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setNClob(int var1, Reader var2, long var3) {
      try {
      this.preparedStatement.setNClob(var1, var2, var3);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setSQLXML(int var1, SQLXML var2) {
      try {
      this.preparedStatement.setSQLXML(var1, var2);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setObject(int var1, Object var2, int var3, int var4) {
      try {
      this.preparedStatement.setObject(var1, var2, var3, var4);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setAsciiStream(int var1, InputStream var2, long var3) {
      try {
      this.preparedStatement.setAsciiStream(var1, var2, var3);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setBinaryStream(int var1, InputStream var2, long var3) {
      try {
      this.preparedStatement.setBinaryStream(var1, var2, var3);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setCharacterStream(int var1, Reader var2, long var3) {
      try {
      this.preparedStatement.setCharacterStream(var1, var2, var3);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setAsciiStream(int var1, InputStream var2) {
      try {
      this.preparedStatement.setAsciiStream(var1, var2);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setBinaryStream(int var1, InputStream var2) {
      try {
      this.preparedStatement.setBinaryStream(var1, var2);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setCharacterStream(int var1, Reader var2) {
      try {
      this.preparedStatement.setCharacterStream(var1, var2);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setNCharacterStream(int var1, Reader var2) {
      try {
      this.preparedStatement.setNCharacterStream(var1, var2);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setClob(int var1, Reader var2) {
      try {
      this.preparedStatement.setClob(var1, var2);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setBlob(int var1, InputStream var2) {
      try {
      this.preparedStatement.setBlob(var1, var2);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setNClob(int var1, Reader var2) {
      try {
      this.preparedStatement.setNClob(var1, var2);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setObject(int var1, Object var2, SQLType var3, int var4) {
      try {
      this.preparedStatement.setObject(var1, var2, var3, var4);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setObject(int var1, Object var2, SQLType var3) {
      try {
      this.preparedStatement.setObject(var1, var2, var3);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public long executeLargeUpdate() {
      try {
      return this.preparedStatement.executeLargeUpdate();
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public ResultSet executeQuery(String var1) {
      try {
      return this.preparedStatement.executeQuery(var1);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int executeUpdate(String var1) {
      try {
      return this.preparedStatement.executeUpdate(var1);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void close() {
      try {
      this.preparedStatement.close();
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int getMaxFieldSize() {
      try {
      return this.preparedStatement.getMaxFieldSize();
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setMaxFieldSize(int var1) {
      try {
      this.preparedStatement.setMaxFieldSize(var1);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int getMaxRows() {
      try {
      return this.preparedStatement.getMaxRows();
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setMaxRows(int var1) {
      try {
      this.preparedStatement.setMaxRows(var1);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setEscapeProcessing(boolean var1) {
      try {
      this.preparedStatement.setEscapeProcessing(var1);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int getQueryTimeout() {
      try {
      return this.preparedStatement.getQueryTimeout();
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setQueryTimeout(int var1) {
      try {
      this.preparedStatement.setQueryTimeout(var1);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void cancel() {
      try {
      this.preparedStatement.cancel();
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public SQLWarning getWarnings() {
      try {
      return this.preparedStatement.getWarnings();
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void clearWarnings() {
      try {
      this.preparedStatement.clearWarnings();
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setCursorName(String var1) {
      try {
      this.preparedStatement.setCursorName(var1);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean execute(String var1) {
      try {
      return this.preparedStatement.execute(var1);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public ResultSet getResultSet() {
      try {
      return this.preparedStatement.getResultSet();
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int getUpdateCount() {
      try {
      return this.preparedStatement.getUpdateCount();
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean getMoreResults() {
      try {
      return this.preparedStatement.getMoreResults();
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setFetchDirection(int var1) {
      try {
      this.preparedStatement.setFetchDirection(var1);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int getFetchDirection() {
      try {
      return this.preparedStatement.getFetchDirection();
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setFetchSize(int var1) {
      try {
      this.preparedStatement.setFetchSize(var1);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int getFetchSize() {
      try {
      return this.preparedStatement.getFetchSize();
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int getResultSetConcurrency() {
      try {
      return this.preparedStatement.getResultSetConcurrency();
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int getResultSetType() {
      try {
      return this.preparedStatement.getResultSetType();
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void addBatch(String var1) {
      try {
      this.preparedStatement.addBatch(var1);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void clearBatch() {
      try {
      this.preparedStatement.clearBatch();
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int[] executeBatch() {
      try {
      return this.preparedStatement.executeBatch();
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Connection getConnection() {
      try {
      return this.preparedStatement.getConnection();
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean getMoreResults(int var1) {
      try {
      return this.preparedStatement.getMoreResults(var1);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public ResultSet getGeneratedKeys() {
      try {
      return this.preparedStatement.getGeneratedKeys();
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int executeUpdate(String var1, int var2) {
      try {
      return this.preparedStatement.executeUpdate(var1, var2);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int executeUpdate(String var1, int[] var2) {
      try {
      return this.preparedStatement.executeUpdate(var1, var2);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int executeUpdate(String var1, String[] var2) {
      try {
      return this.preparedStatement.executeUpdate(var1, var2);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean execute(String var1, int var2) {
      try {
      return this.preparedStatement.execute(var1, var2);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean execute(String var1, int[] var2) {
      try {
      return this.preparedStatement.execute(var1, var2);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean execute(String var1, String[] var2) {
      try {
      return this.preparedStatement.execute(var1, var2);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int getResultSetHoldability() {
      try {
      return this.preparedStatement.getResultSetHoldability();
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean isClosed() {
      try {
      return this.preparedStatement.isClosed();
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setPoolable(boolean var1) {
      try {
      this.preparedStatement.setPoolable(var1);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean isPoolable() {
      try {
      return this.preparedStatement.isPoolable();
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void closeOnCompletion() {
      try {
      this.preparedStatement.closeOnCompletion();
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean isCloseOnCompletion() {
      try {
      return this.preparedStatement.isCloseOnCompletion();
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public long getLargeUpdateCount() {
      try {
      return this.preparedStatement.getLargeUpdateCount();
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Object unwrap(Class var1) {
      try {
      return this.preparedStatement.unwrap(var1);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean isWrapperFor(Class var1) {
      try {
      return this.preparedStatement.isWrapperFor(var1);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }
}
