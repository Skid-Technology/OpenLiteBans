package litebans.database;

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
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Map;
import litebans.lib.kotlin.NoWhenBranchMatchedException;
import org.jetbrains.annotations.Nullable;

public final class EmptyResultSet implements ResultSet {
   public final Void getVoid() {
      throw new UnsupportedOperationException();
   }

   @Override
   public int findColumn(@Nullable String var1) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public NClob getNClob(int var1) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public NClob getNClob(@Nullable String var1) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void updateNString(int var1, @Nullable String var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void updateNString(@Nullable String var1, @Nullable String var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void updateBinaryStream(int var1, @Nullable InputStream var2, int var3) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void updateBinaryStream(@Nullable String var1, @Nullable InputStream var2, int var3) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void updateBinaryStream(int var1, @Nullable InputStream var2, long var3) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void updateBinaryStream(@Nullable String var1, @Nullable InputStream var2, long var3) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void updateBinaryStream(int var1, @Nullable InputStream var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void updateBinaryStream(@Nullable String var1, @Nullable InputStream var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public Statement getStatement() {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void updateTimestamp(int var1, @Nullable Timestamp var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void updateTimestamp(@Nullable String var1, @Nullable Timestamp var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void updateNCharacterStream(int var1, @Nullable Reader var2, long var3) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void updateNCharacterStream(@Nullable String var1, @Nullable Reader var2, long var3) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void updateNCharacterStream(int var1, @Nullable Reader var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void updateNCharacterStream(@Nullable String var1, @Nullable Reader var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void updateInt(int var1, int var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void updateInt(@Nullable String var1, int var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void moveToInsertRow() {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public Date getDate(int var1) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public Date getDate(@Nullable String var1) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public Date getDate(int var1, @Nullable Calendar var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public Date getDate(@Nullable String var1, @Nullable Calendar var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public SQLWarning getWarnings() {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void beforeFirst() {
   }

   @Override
   public void close() {
   }

   @Override
   public void updateFloat(int var1, float var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void updateFloat(@Nullable String var1, float var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public boolean getBoolean(int var1) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public boolean getBoolean(@Nullable String var1) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public boolean isFirst() {
      return false;
   }

   @Override
   public BigDecimal getBigDecimal(int var1, int var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public BigDecimal getBigDecimal(@Nullable String var1, int var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public BigDecimal getBigDecimal(int var1) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public BigDecimal getBigDecimal(@Nullable String var1) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void updateBytes(int var1, @Nullable byte[] var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void updateBytes(@Nullable String var1, @Nullable byte[] var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public boolean isLast() {
      return false;
   }

   @Override
   public void insertRow() {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public Time getTime(int var1) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public Time getTime(@Nullable String var1) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public Time getTime(int var1, @Nullable Calendar var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public Time getTime(@Nullable String var1, @Nullable Calendar var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public boolean rowDeleted() {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public boolean last() {
      return false;
   }

   @Override
   public boolean isAfterLast() {
      return false;
   }

   @Override
   public boolean relative(int var1) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public boolean absolute(int var1) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public SQLXML getSQLXML(int var1) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public SQLXML getSQLXML(@Nullable String var1) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public Object unwrap(@Nullable Class var1) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public boolean next() {
      return false;
   }

   @Override
   public float getFloat(int var1) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public float getFloat(@Nullable String var1) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public boolean wasNull() {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public int getRow() {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public boolean first() {
      return false;
   }

   @Override
   public void updateAsciiStream(int var1, @Nullable InputStream var2, int var3) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void updateAsciiStream(@Nullable String var1, @Nullable InputStream var2, int var3) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void updateAsciiStream(int var1, @Nullable InputStream var2, long var3) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void updateAsciiStream(@Nullable String var1, @Nullable InputStream var2, long var3) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void updateAsciiStream(int var1, @Nullable InputStream var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void updateAsciiStream(@Nullable String var1, @Nullable InputStream var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public URL getURL(int var1) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public URL getURL(@Nullable String var1) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void updateShort(int var1, short var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void updateShort(@Nullable String var1, short var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public int getType() {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void updateNClob(int var1, @Nullable NClob var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void updateNClob(@Nullable String var1, @Nullable NClob var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void updateNClob(int var1, @Nullable Reader var2, long var3) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void updateNClob(@Nullable String var1, @Nullable Reader var2, long var3) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void updateNClob(int var1, @Nullable Reader var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void updateNClob(@Nullable String var1, @Nullable Reader var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void updateRef(int var1, @Nullable Ref var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void updateRef(@Nullable String var1, @Nullable Ref var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void updateObject(int var1, @Nullable Object var2, int var3) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void updateObject(int var1, @Nullable Object var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void updateObject(@Nullable String var1, @Nullable Object var2, int var3) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void updateObject(@Nullable String var1, @Nullable Object var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void setFetchSize(int var1) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void afterLast() {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void updateLong(int var1, long var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void updateLong(@Nullable String var1, long var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public Blob getBlob(int var1) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public Blob getBlob(@Nullable String var1) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void updateClob(int var1, @Nullable Clob var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void updateClob(@Nullable String var1, @Nullable Clob var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void updateClob(int var1, @Nullable Reader var2, long var3) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void updateClob(@Nullable String var1, @Nullable Reader var2, long var3) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void updateClob(int var1, @Nullable Reader var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void updateClob(@Nullable String var1, @Nullable Reader var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public byte getByte(int var1) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public byte getByte(@Nullable String var1) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public String getString(int var1) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public String getString(@Nullable String var1) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void updateSQLXML(int var1, @Nullable SQLXML var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void updateSQLXML(@Nullable String var1, @Nullable SQLXML var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void updateDate(int var1, @Nullable Date var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void updateDate(@Nullable String var1, @Nullable Date var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public int getHoldability() {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public Object getObject(int var1) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public Object getObject(@Nullable String var1) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public Object getObject(int var1, @Nullable Map var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public Object getObject(@Nullable String var1, @Nullable Map var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public Object getObject(int var1, @Nullable Class var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public Object getObject(@Nullable String var1, @Nullable Class var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public boolean previous() {
      return false;
   }

   @Override
   public void updateDouble(int var1, double var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void updateDouble(@Nullable String var1, double var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public long getLong(int var1) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public long getLong(@Nullable String var1) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public Clob getClob(int var1) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public Clob getClob(@Nullable String var1) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void updateBlob(int var1, @Nullable Blob var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void updateBlob(@Nullable String var1, @Nullable Blob var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void updateBlob(int var1, @Nullable InputStream var2, long var3) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void updateBlob(@Nullable String var1, @Nullable InputStream var2, long var3) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void updateBlob(int var1, @Nullable InputStream var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void updateBlob(@Nullable String var1, @Nullable InputStream var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void updateByte(int var1, byte var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void updateByte(@Nullable String var1, byte var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void updateRow() {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void deleteRow() {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public boolean isClosed() {
      return true;
   }

   @Override
   public String getNString(int var1) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public String getNString(@Nullable String var1) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public String getCursorName() {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public Array getArray(int var1) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public Array getArray(@Nullable String var1) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void cancelRowUpdates() {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void updateString(int var1, @Nullable String var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void updateString(@Nullable String var1, @Nullable String var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void setFetchDirection(int var1) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public int getFetchSize() {
      return 0;
   }

   @Override
   public Reader getCharacterStream(int var1) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public Reader getCharacterStream(@Nullable String var1) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public boolean isBeforeFirst() {
      return true;
   }

   @Override
   public void updateBoolean(int var1, boolean var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void updateBoolean(@Nullable String var1, boolean var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void refreshRow() {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public boolean rowUpdated() {
      return false;
   }

   @Override
   public void updateBigDecimal(int var1, @Nullable BigDecimal var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void updateBigDecimal(@Nullable String var1, @Nullable BigDecimal var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public short getShort(int var1) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public short getShort(@Nullable String var1) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public InputStream getAsciiStream(int var1) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public InputStream getAsciiStream(@Nullable String var1) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void updateTime(int var1, @Nullable Time var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void updateTime(@Nullable String var1, @Nullable Time var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public Timestamp getTimestamp(int var1) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public Timestamp getTimestamp(@Nullable String var1) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public Timestamp getTimestamp(int var1, @Nullable Calendar var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public Timestamp getTimestamp(@Nullable String var1, @Nullable Calendar var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public Ref getRef(int var1) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public Ref getRef(@Nullable String var1) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void moveToCurrentRow() {
   }

   @Override
   public int getConcurrency() {
      return 1007;
   }

   @Override
   public void updateRowId(int var1, @Nullable RowId var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void updateRowId(@Nullable String var1, @Nullable RowId var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public Reader getNCharacterStream(int var1) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public Reader getNCharacterStream(@Nullable String var1) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void updateArray(int var1, @Nullable Array var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void updateArray(@Nullable String var1, @Nullable Array var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public byte[] getBytes(int var1) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public byte[] getBytes(@Nullable String var1) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public double getDouble(int var1) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public double getDouble(@Nullable String var1) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public InputStream getUnicodeStream(int var1) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public InputStream getUnicodeStream(@Nullable String var1) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public boolean rowInserted() {
      return false;
   }

   @Override
   public boolean isWrapperFor(@Nullable Class var1) {
      return false;
   }

   @Override
   public int getInt(int var1) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public int getInt(@Nullable String var1) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void updateNull(int var1) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void updateNull(@Nullable String var1) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public RowId getRowId(int var1) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public RowId getRowId(@Nullable String var1) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void clearWarnings() {
   }

   @Override
   public ResultSetMetaData getMetaData() {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public InputStream getBinaryStream(int var1) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public InputStream getBinaryStream(@Nullable String var1) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void updateCharacterStream(int var1, @Nullable Reader var2, int var3) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void updateCharacterStream(@Nullable String var1, @Nullable Reader var2, int var3) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void updateCharacterStream(int var1, @Nullable Reader var2, long var3) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void updateCharacterStream(@Nullable String var1, @Nullable Reader var2, long var3) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void updateCharacterStream(int var1, @Nullable Reader var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public void updateCharacterStream(@Nullable String var1, @Nullable Reader var2) {
      this.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public int getFetchDirection() {
      return 1000;
   }
}
