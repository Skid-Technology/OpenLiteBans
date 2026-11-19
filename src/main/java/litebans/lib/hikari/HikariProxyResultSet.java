package litebans.lib.hikari;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLType;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Wrapper;
import java.util.Calendar;
import java.util.Map;

public final class HikariProxyResultSet extends ProxyResultSet implements Wrapper, AutoCloseable, ResultSet {
   @Override
   public boolean isWrapperFor(Class var1) {
      try {
      try {
         return super.resultSet.isWrapperFor(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void close() {
      try {
      super.resultSet.close();
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean next() {
      try {
      try {
         return super.resultSet.next();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean wasNull() {
      try {
      try {
         return super.resultSet.wasNull();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public String getString(int var1) {
      try {
      try {
         return super.resultSet.getString(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean getBoolean(int var1) {
      try {
      try {
         return super.resultSet.getBoolean(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public byte getByte(int var1) {
      try {
      try {
         return super.resultSet.getByte(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public short getShort(int var1) {
      try {
      try {
         return super.resultSet.getShort(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int getInt(int var1) {
      try {
      try {
         return super.resultSet.getInt(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public long getLong(int var1) {
      try {
      try {
         return super.resultSet.getLong(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public float getFloat(int var1) {
      try {
      try {
         return super.resultSet.getFloat(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public double getDouble(int var1) {
      try {
      try {
         return super.resultSet.getDouble(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public BigDecimal getBigDecimal(int var1, int var2) {
      try {
      try {
         return super.resultSet.getBigDecimal(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public byte[] getBytes(int var1) {
      try {
      try {
         return super.resultSet.getBytes(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Date getDate(int var1) {
      try {
      try {
         return super.resultSet.getDate(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Time getTime(int var1) {
      try {
      try {
         return super.resultSet.getTime(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Timestamp getTimestamp(int var1) {
      try {
      try {
         return super.resultSet.getTimestamp(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public InputStream getAsciiStream(int var1) {
      try {
      try {
         return super.resultSet.getAsciiStream(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public InputStream getUnicodeStream(int var1) {
      try {
      try {
         return super.resultSet.getUnicodeStream(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public InputStream getBinaryStream(int var1) {
      try {
      try {
         return super.resultSet.getBinaryStream(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public String getString(String var1) {
      try {
      try {
         return super.resultSet.getString(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean getBoolean(String var1) {
      try {
      try {
         return super.resultSet.getBoolean(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public byte getByte(String var1) {
      try {
      try {
         return super.resultSet.getByte(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public short getShort(String var1) {
      try {
      try {
         return super.resultSet.getShort(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int getInt(String var1) {
      try {
      try {
         return super.resultSet.getInt(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public long getLong(String var1) {
      try {
      try {
         return super.resultSet.getLong(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public float getFloat(String var1) {
      try {
      try {
         return super.resultSet.getFloat(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public double getDouble(String var1) {
      try {
      try {
         return super.resultSet.getDouble(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public BigDecimal getBigDecimal(String var1, int var2) {
      try {
      try {
         return super.resultSet.getBigDecimal(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public byte[] getBytes(String var1) {
      try {
      try {
         return super.resultSet.getBytes(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Date getDate(String var1) {
      try {
      try {
         return super.resultSet.getDate(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Time getTime(String var1) {
      try {
      try {
         return super.resultSet.getTime(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Timestamp getTimestamp(String var1) {
      try {
      try {
         return super.resultSet.getTimestamp(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public InputStream getAsciiStream(String var1) {
      try {
      try {
         return super.resultSet.getAsciiStream(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public InputStream getUnicodeStream(String var1) {
      try {
      try {
         return super.resultSet.getUnicodeStream(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public InputStream getBinaryStream(String var1) {
      try {
      try {
         return super.resultSet.getBinaryStream(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public SQLWarning getWarnings() {
      try {
      try {
         return super.resultSet.getWarnings();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void clearWarnings() {
      try {
      try {
         super.resultSet.clearWarnings();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public String getCursorName() {
      try {
      try {
         return super.resultSet.getCursorName();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public ResultSetMetaData getMetaData() {
      try {
      try {
         return super.resultSet.getMetaData();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Object getObject(int var1) {
      try {
      try {
         return super.resultSet.getObject(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Object getObject(String var1) {
      try {
      try {
         return super.resultSet.getObject(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int findColumn(String var1) {
      try {
      try {
         return super.resultSet.findColumn(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Reader getCharacterStream(int var1) {
      try {
      try {
         return super.resultSet.getCharacterStream(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Reader getCharacterStream(String var1) {
      try {
      try {
         return super.resultSet.getCharacterStream(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public BigDecimal getBigDecimal(int var1) {
      try {
      try {
         return super.resultSet.getBigDecimal(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public BigDecimal getBigDecimal(String var1) {
      try {
      try {
         return super.resultSet.getBigDecimal(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean isBeforeFirst() {
      try {
      try {
         return super.resultSet.isBeforeFirst();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean isAfterLast() {
      try {
      try {
         return super.resultSet.isAfterLast();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean isFirst() {
      try {
      try {
         return super.resultSet.isFirst();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean isLast() {
      try {
      try {
         return super.resultSet.isLast();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void beforeFirst() {
      try {
      try {
         super.resultSet.beforeFirst();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void afterLast() {
      try {
      try {
         super.resultSet.afterLast();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean first() {
      try {
      try {
         return super.resultSet.first();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean last() {
      try {
      try {
         return super.resultSet.last();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int getRow() {
      try {
      try {
         return super.resultSet.getRow();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean absolute(int var1) {
      try {
      try {
         return super.resultSet.absolute(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean relative(int var1) {
      try {
      try {
         return super.resultSet.relative(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean previous() {
      try {
      try {
         return super.resultSet.previous();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setFetchDirection(int var1) {
      try {
      try {
         super.resultSet.setFetchDirection(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int getFetchDirection() {
      try {
      try {
         return super.resultSet.getFetchDirection();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setFetchSize(int var1) {
      try {
      try {
         super.resultSet.setFetchSize(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int getFetchSize() {
      try {
      try {
         return super.resultSet.getFetchSize();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int getType() {
      try {
      try {
         return super.resultSet.getType();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int getConcurrency() {
      try {
      try {
         return super.resultSet.getConcurrency();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean rowUpdated() {
      try {
      try {
         return super.resultSet.rowUpdated();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean rowInserted() {
      try {
      try {
         return super.resultSet.rowInserted();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean rowDeleted() {
      try {
      try {
         return super.resultSet.rowDeleted();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateNull(int var1) {
      try {
      try {
         super.resultSet.updateNull(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateBoolean(int var1, boolean var2) {
      try {
      try {
         super.resultSet.updateBoolean(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateByte(int var1, byte var2) {
      try {
      try {
         super.resultSet.updateByte(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateShort(int var1, short var2) {
      try {
      try {
         super.resultSet.updateShort(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateInt(int var1, int var2) {
      try {
      try {
         super.resultSet.updateInt(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateLong(int var1, long var2) {
      try {
      try {
         super.resultSet.updateLong(var1, var2);
      } catch (SQLException var5) {
         throw this.getSQLException((SQLException)var5);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateFloat(int var1, float var2) {
      try {
      try {
         super.resultSet.updateFloat(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateDouble(int var1, double var2) {
      try {
      try {
         super.resultSet.updateDouble(var1, var2);
      } catch (SQLException var5) {
         throw this.getSQLException((SQLException)var5);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateBigDecimal(int var1, BigDecimal var2) {
      try {
      try {
         super.resultSet.updateBigDecimal(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateString(int var1, String var2) {
      try {
      try {
         super.resultSet.updateString(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateBytes(int var1, byte[] var2) {
      try {
      try {
         super.resultSet.updateBytes(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateDate(int var1, Date var2) {
      try {
      try {
         super.resultSet.updateDate(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateTime(int var1, Time var2) {
      try {
      try {
         super.resultSet.updateTime(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateTimestamp(int var1, Timestamp var2) {
      try {
      try {
         super.resultSet.updateTimestamp(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateAsciiStream(int var1, InputStream var2, int var3) {
      try {
      try {
         super.resultSet.updateAsciiStream(var1, var2, var3);
      } catch (SQLException var5) {
         throw this.getSQLException((SQLException)var5);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateBinaryStream(int var1, InputStream var2, int var3) {
      try {
      try {
         super.resultSet.updateBinaryStream(var1, var2, var3);
      } catch (SQLException var5) {
         throw this.getSQLException((SQLException)var5);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateCharacterStream(int var1, Reader var2, int var3) {
      try {
      try {
         super.resultSet.updateCharacterStream(var1, var2, var3);
      } catch (SQLException var5) {
         throw this.getSQLException((SQLException)var5);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateObject(int var1, Object var2, int var3) {
      try {
      try {
         super.resultSet.updateObject(var1, var2, var3);
      } catch (SQLException var5) {
         throw this.getSQLException((SQLException)var5);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateObject(int var1, Object var2) {
      try {
      try {
         super.resultSet.updateObject(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateNull(String var1) {
      try {
      try {
         super.resultSet.updateNull(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateBoolean(String var1, boolean var2) {
      try {
      try {
         super.resultSet.updateBoolean(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateByte(String var1, byte var2) {
      try {
      try {
         super.resultSet.updateByte(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateShort(String var1, short var2) {
      try {
      try {
         super.resultSet.updateShort(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateInt(String var1, int var2) {
      try {
      try {
         super.resultSet.updateInt(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateLong(String var1, long var2) {
      try {
      try {
         super.resultSet.updateLong(var1, var2);
      } catch (SQLException var5) {
         throw this.getSQLException((SQLException)var5);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateFloat(String var1, float var2) {
      try {
      try {
         super.resultSet.updateFloat(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateDouble(String var1, double var2) {
      try {
      try {
         super.resultSet.updateDouble(var1, var2);
      } catch (SQLException var5) {
         throw this.getSQLException((SQLException)var5);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateBigDecimal(String var1, BigDecimal var2) {
      try {
      try {
         super.resultSet.updateBigDecimal(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateString(String var1, String var2) {
      try {
      try {
         super.resultSet.updateString(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateBytes(String var1, byte[] var2) {
      try {
      try {
         super.resultSet.updateBytes(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateDate(String var1, Date var2) {
      try {
      try {
         super.resultSet.updateDate(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateTime(String var1, Time var2) {
      try {
      try {
         super.resultSet.updateTime(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateTimestamp(String var1, Timestamp var2) {
      try {
      try {
         super.resultSet.updateTimestamp(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateAsciiStream(String var1, InputStream var2, int var3) {
      try {
      try {
         super.resultSet.updateAsciiStream(var1, var2, var3);
      } catch (SQLException var5) {
         throw this.getSQLException((SQLException)var5);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateBinaryStream(String var1, InputStream var2, int var3) {
      try {
      try {
         super.resultSet.updateBinaryStream(var1, var2, var3);
      } catch (SQLException var5) {
         throw this.getSQLException((SQLException)var5);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateCharacterStream(String var1, Reader var2, int var3) {
      try {
      try {
         super.resultSet.updateCharacterStream(var1, var2, var3);
      } catch (SQLException var5) {
         throw this.getSQLException((SQLException)var5);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateObject(String var1, Object var2, int var3) {
      try {
      try {
         super.resultSet.updateObject(var1, var2, var3);
      } catch (SQLException var5) {
         throw this.getSQLException((SQLException)var5);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateObject(String var1, Object var2) {
      try {
      try {
         super.resultSet.updateObject(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void insertRow() {
      try {
      try {
         super.insertRow();
      } catch (Exception var2) {
         if (!(var2 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var2); }
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateRow() {
      try {
      try {
         super.updateRow();
      } catch (Exception var2) {
         if (!(var2 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var2); }
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void deleteRow() {
      try {
      try {
         super.deleteRow();
      } catch (Exception var2) {
         if (!(var2 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var2); }
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void refreshRow() {
      try {
      try {
         super.resultSet.refreshRow();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void cancelRowUpdates() {
      try {
      try {
         super.resultSet.cancelRowUpdates();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void moveToInsertRow() {
      try {
      try {
         super.resultSet.moveToInsertRow();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void moveToCurrentRow() {
      try {
      try {
         super.resultSet.moveToCurrentRow();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Object getObject(int var1, Map var2) {
      try {
      try {
         return super.resultSet.getObject(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Ref getRef(int var1) {
      try {
      try {
         return super.resultSet.getRef(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Blob getBlob(int var1) {
      try {
      try {
         return super.resultSet.getBlob(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Clob getClob(int var1) {
      try {
      try {
         return super.resultSet.getClob(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Array getArray(int var1) {
      try {
      try {
         return super.resultSet.getArray(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Object getObject(String var1, Map var2) {
      try {
      try {
         return super.resultSet.getObject(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Ref getRef(String var1) {
      try {
      try {
         return super.resultSet.getRef(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Blob getBlob(String var1) {
      try {
      try {
         return super.resultSet.getBlob(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Clob getClob(String var1) {
      try {
      try {
         return super.resultSet.getClob(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Array getArray(String var1) {
      try {
      try {
         return super.resultSet.getArray(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Date getDate(int var1, Calendar var2) {
      try {
      try {
         return super.resultSet.getDate(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Date getDate(String var1, Calendar var2) {
      try {
      try {
         return super.resultSet.getDate(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Time getTime(int var1, Calendar var2) {
      try {
      try {
         return super.resultSet.getTime(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Time getTime(String var1, Calendar var2) {
      try {
      try {
         return super.resultSet.getTime(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Timestamp getTimestamp(int var1, Calendar var2) {
      try {
      try {
         return super.resultSet.getTimestamp(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Timestamp getTimestamp(String var1, Calendar var2) {
      try {
      try {
         return super.resultSet.getTimestamp(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public URL getURL(int var1) {
      try {
      try {
         return super.resultSet.getURL(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public URL getURL(String var1) {
      try {
      try {
         return super.resultSet.getURL(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateRef(int var1, Ref var2) {
      try {
      try {
         super.resultSet.updateRef(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateRef(String var1, Ref var2) {
      try {
      try {
         super.resultSet.updateRef(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateBlob(int var1, Blob var2) {
      try {
      try {
         super.resultSet.updateBlob(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateBlob(String var1, Blob var2) {
      try {
      try {
         super.resultSet.updateBlob(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateClob(int var1, Clob var2) {
      try {
      try {
         super.resultSet.updateClob(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateClob(String var1, Clob var2) {
      try {
      try {
         super.resultSet.updateClob(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateArray(int var1, Array var2) {
      try {
      try {
         super.resultSet.updateArray(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateArray(String var1, Array var2) {
      try {
      try {
         super.resultSet.updateArray(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public RowId getRowId(int var1) {
      try {
      try {
         return super.resultSet.getRowId(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public RowId getRowId(String var1) {
      try {
      try {
         return super.resultSet.getRowId(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateRowId(int var1, RowId var2) {
      try {
      try {
         super.resultSet.updateRowId(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateRowId(String var1, RowId var2) {
      try {
      try {
         super.resultSet.updateRowId(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int getHoldability() {
      try {
      try {
         return super.resultSet.getHoldability();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean isClosed() {
      try {
      try {
         return super.resultSet.isClosed();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateNString(int var1, String var2) {
      try {
      try {
         super.resultSet.updateNString(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateNString(String var1, String var2) {
      try {
      try {
         super.resultSet.updateNString(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateNClob(int var1, NClob var2) {
      try {
      try {
         super.resultSet.updateNClob(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateNClob(String var1, NClob var2) {
      try {
      try {
         super.resultSet.updateNClob(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public NClob getNClob(int var1) {
      try {
      try {
         return super.resultSet.getNClob(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public NClob getNClob(String var1) {
      try {
      try {
         return super.resultSet.getNClob(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public SQLXML getSQLXML(int var1) {
      try {
      try {
         return super.resultSet.getSQLXML(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public SQLXML getSQLXML(String var1) {
      try {
      try {
         return super.resultSet.getSQLXML(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateSQLXML(int var1, SQLXML var2) {
      try {
      try {
         super.resultSet.updateSQLXML(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateSQLXML(String var1, SQLXML var2) {
      try {
      try {
         super.resultSet.updateSQLXML(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public String getNString(int var1) {
      try {
      try {
         return super.resultSet.getNString(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public String getNString(String var1) {
      try {
      try {
         return super.resultSet.getNString(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Reader getNCharacterStream(int var1) {
      try {
      try {
         return super.resultSet.getNCharacterStream(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Reader getNCharacterStream(String var1) {
      try {
      try {
         return super.resultSet.getNCharacterStream(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateNCharacterStream(int var1, Reader var2, long var3) {
      try {
      try {
         super.resultSet.updateNCharacterStream(var1, var2, var3);
      } catch (SQLException var6) {
         throw this.getSQLException((SQLException)var6);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateNCharacterStream(String var1, Reader var2, long var3) {
      try {
      try {
         super.resultSet.updateNCharacterStream(var1, var2, var3);
      } catch (SQLException var6) {
         throw this.getSQLException((SQLException)var6);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateAsciiStream(int var1, InputStream var2, long var3) {
      try {
      try {
         super.resultSet.updateAsciiStream(var1, var2, var3);
      } catch (SQLException var6) {
         throw this.getSQLException((SQLException)var6);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateBinaryStream(int var1, InputStream var2, long var3) {
      try {
      try {
         super.resultSet.updateBinaryStream(var1, var2, var3);
      } catch (SQLException var6) {
         throw this.getSQLException((SQLException)var6);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateCharacterStream(int var1, Reader var2, long var3) {
      try {
      try {
         super.resultSet.updateCharacterStream(var1, var2, var3);
      } catch (SQLException var6) {
         throw this.getSQLException((SQLException)var6);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateAsciiStream(String var1, InputStream var2, long var3) {
      try {
      try {
         super.resultSet.updateAsciiStream(var1, var2, var3);
      } catch (SQLException var6) {
         throw this.getSQLException((SQLException)var6);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateBinaryStream(String var1, InputStream var2, long var3) {
      try {
      try {
         super.resultSet.updateBinaryStream(var1, var2, var3);
      } catch (SQLException var6) {
         throw this.getSQLException((SQLException)var6);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateCharacterStream(String var1, Reader var2, long var3) {
      try {
      try {
         super.resultSet.updateCharacterStream(var1, var2, var3);
      } catch (SQLException var6) {
         throw this.getSQLException((SQLException)var6);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateBlob(int var1, InputStream var2, long var3) {
      try {
      try {
         super.resultSet.updateBlob(var1, var2, var3);
      } catch (SQLException var6) {
         throw this.getSQLException((SQLException)var6);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateBlob(String var1, InputStream var2, long var3) {
      try {
      try {
         super.resultSet.updateBlob(var1, var2, var3);
      } catch (SQLException var6) {
         throw this.getSQLException((SQLException)var6);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateClob(int var1, Reader var2, long var3) {
      try {
      try {
         super.resultSet.updateClob(var1, var2, var3);
      } catch (SQLException var6) {
         throw this.getSQLException((SQLException)var6);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateClob(String var1, Reader var2, long var3) {
      try {
      try {
         super.resultSet.updateClob(var1, var2, var3);
      } catch (SQLException var6) {
         throw this.getSQLException((SQLException)var6);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateNClob(int var1, Reader var2, long var3) {
      try {
      try {
         super.resultSet.updateNClob(var1, var2, var3);
      } catch (SQLException var6) {
         throw this.getSQLException((SQLException)var6);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateNClob(String var1, Reader var2, long var3) {
      try {
      try {
         super.resultSet.updateNClob(var1, var2, var3);
      } catch (SQLException var6) {
         throw this.getSQLException((SQLException)var6);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateNCharacterStream(int var1, Reader var2) {
      try {
      try {
         super.resultSet.updateNCharacterStream(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateNCharacterStream(String var1, Reader var2) {
      try {
      try {
         super.resultSet.updateNCharacterStream(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateAsciiStream(int var1, InputStream var2) {
      try {
      try {
         super.resultSet.updateAsciiStream(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateBinaryStream(int var1, InputStream var2) {
      try {
      try {
         super.resultSet.updateBinaryStream(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateCharacterStream(int var1, Reader var2) {
      try {
      try {
         super.resultSet.updateCharacterStream(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateAsciiStream(String var1, InputStream var2) {
      try {
      try {
         super.resultSet.updateAsciiStream(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateBinaryStream(String var1, InputStream var2) {
      try {
      try {
         super.resultSet.updateBinaryStream(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateCharacterStream(String var1, Reader var2) {
      try {
      try {
         super.resultSet.updateCharacterStream(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateBlob(int var1, InputStream var2) {
      try {
      try {
         super.resultSet.updateBlob(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateBlob(String var1, InputStream var2) {
      try {
      try {
         super.resultSet.updateBlob(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateClob(int var1, Reader var2) {
      try {
      try {
         super.resultSet.updateClob(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateClob(String var1, Reader var2) {
      try {
      try {
         super.resultSet.updateClob(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateNClob(int var1, Reader var2) {
      try {
      try {
         super.resultSet.updateNClob(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateNClob(String var1, Reader var2) {
      try {
      try {
         super.resultSet.updateNClob(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Object getObject(int var1, Class var2) {
      try {
      try {
         return super.resultSet.getObject(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Object getObject(String var1, Class var2) {
      try {
      try {
         return super.resultSet.getObject(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateObject(int var1, Object var2, SQLType var3, int var4) {
      try {
      try {
         super.resultSet.updateObject(var1, var2, var3, var4);
      } catch (SQLException var6) {
         throw this.getSQLException((SQLException)var6);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateObject(String var1, Object var2, SQLType var3, int var4) {
      try {
      try {
         super.resultSet.updateObject(var1, var2, var3, var4);
      } catch (SQLException var6) {
         throw this.getSQLException((SQLException)var6);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateObject(int var1, Object var2, SQLType var3) {
      try {
      try {
         super.resultSet.updateObject(var1, var2, var3);
      } catch (SQLException var5) {
         throw this.getSQLException((SQLException)var5);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void updateObject(String var1, Object var2, SQLType var3) {
      try {
      try {
         super.resultSet.updateObject(var1, var2, var3);
      } catch (SQLException var5) {
         throw this.getSQLException((SQLException)var5);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   public HikariProxyResultSet(ProxyConnection var1, ProxyStatement var2, ResultSet var3) {
      super(var1, var2, var3);
   }
}
