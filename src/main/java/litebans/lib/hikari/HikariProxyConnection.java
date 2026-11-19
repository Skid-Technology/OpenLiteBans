package litebans.lib.hikari;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.sql.Wrapper;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

public final class HikariProxyConnection extends ProxyConnection implements Wrapper, AutoCloseable, Connection {
   @Override
   public Statement createStatement() {
      try {
      try {
         return super.createStatement();
      } catch (Exception var2) {
         if (!(var2 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var2); }
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public PreparedStatement prepareStatement(String var1) {
      try {
      try {
         return super.prepareStatement(var1);
      } catch (Exception var3) {
         if (!(var3 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var3); }
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public CallableStatement prepareCall(String var1) {
      try {
      try {
         return super.prepareCall(var1);
      } catch (Exception var3) {
         if (!(var3 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var3); }
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public String nativeSQL(String var1) {
      try {
      try {
         return super.connection.nativeSQL(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setAutoCommit(boolean var1) {
      try {
      try {
         super.setAutoCommit(var1);
      } catch (Exception var3) {
         if (!(var3 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var3); }
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean getAutoCommit() {
      try {
      try {
         return super.connection.getAutoCommit();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void commit() {
      try {
      try {
         super.commit();
      } catch (Exception var2) {
         if (!(var2 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var2); }
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void rollback() {
      try {
      try {
         super.rollback();
      } catch (Exception var2) {
         if (!(var2 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var2); }
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
   public DatabaseMetaData getMetaData() {
      try {
      try {
         return super.getMetaData();
      } catch (Exception var2) {
         if (!(var2 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var2); }
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setReadOnly(boolean var1) {
      try {
      try {
         super.setReadOnly(var1);
      } catch (Exception var3) {
         if (!(var3 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var3); }
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean isReadOnly() {
      try {
      try {
         return super.connection.isReadOnly();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setCatalog(String var1) {
      try {
      try {
         super.setCatalog(var1);
      } catch (Exception var3) {
         if (!(var3 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var3); }
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public String getCatalog() {
      try {
      try {
         return super.connection.getCatalog();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setTransactionIsolation(int var1) {
      try {
      try {
         super.setTransactionIsolation(var1);
      } catch (Exception var3) {
         if (!(var3 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var3); }
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int getTransactionIsolation() {
      try {
      try {
         return super.connection.getTransactionIsolation();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public SQLWarning getWarnings() {
      try {
      try {
         return super.connection.getWarnings();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void clearWarnings() {
      try {
      try {
         super.connection.clearWarnings();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Statement createStatement(int var1, int var2) {
      try {
      try {
         return super.createStatement(var1, var2);
      } catch (Exception var4) {
         if (!(var4 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var4); }
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public PreparedStatement prepareStatement(String var1, int var2, int var3) {
      try {
      try {
         return super.prepareStatement(var1, var2, var3);
      } catch (Exception var5) {
         if (!(var5 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var5); }
         throw this.getSQLException((SQLException)var5);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public CallableStatement prepareCall(String var1, int var2, int var3) {
      try {
      try {
         return super.prepareCall(var1, var2, var3);
      } catch (Exception var5) {
         if (!(var5 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var5); }
         throw this.getSQLException((SQLException)var5);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Map getTypeMap() {
      try {
      try {
         return super.connection.getTypeMap();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setTypeMap(Map var1) {
      try {
      try {
         super.connection.setTypeMap(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setHoldability(int var1) {
      try {
      try {
         super.connection.setHoldability(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int getHoldability() {
      try {
      try {
         return super.connection.getHoldability();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Savepoint setSavepoint() {
      try {
      try {
         return super.connection.setSavepoint();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Savepoint setSavepoint(String var1) {
      try {
      try {
         return super.connection.setSavepoint(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void rollback(Savepoint var1) {
      try {
      try {
         super.rollback(var1);
      } catch (Exception var3) {
         if (!(var3 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var3); }
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void releaseSavepoint(Savepoint var1) {
      try {
      try {
         super.connection.releaseSavepoint(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Statement createStatement(int var1, int var2, int var3) {
      try {
      try {
         return super.createStatement(var1, var2, var3);
      } catch (Exception var5) {
         if (!(var5 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var5); }
         throw this.getSQLException((SQLException)var5);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public PreparedStatement prepareStatement(String var1, int var2, int var3, int var4) {
      try {
      try {
         return super.prepareStatement(var1, var2, var3, var4);
      } catch (Exception var6) {
         if (!(var6 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var6); }
         throw this.getSQLException((SQLException)var6);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public CallableStatement prepareCall(String var1, int var2, int var3, int var4) {
      try {
      try {
         return super.prepareCall(var1, var2, var3, var4);
      } catch (Exception var6) {
         if (!(var6 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var6); }
         throw this.getSQLException((SQLException)var6);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public PreparedStatement prepareStatement(String var1, int var2) {
      try {
      try {
         return super.prepareStatement(var1, var2);
      } catch (Exception var4) {
         if (!(var4 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var4); }
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public PreparedStatement prepareStatement(String var1, int[] var2) {
      try {
      try {
         return super.prepareStatement(var1, var2);
      } catch (Exception var4) {
         if (!(var4 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var4); }
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public PreparedStatement prepareStatement(String var1, String[] var2) {
      try {
      try {
         return super.prepareStatement(var1, var2);
      } catch (Exception var4) {
         if (!(var4 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var4); }
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Clob createClob() {
      try {
      try {
         return super.connection.createClob();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Blob createBlob() {
      try {
      try {
         return super.connection.createBlob();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public NClob createNClob() {
      try {
      try {
         return super.connection.createNClob();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public SQLXML createSQLXML() {
      try {
      try {
         return super.connection.createSQLXML();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean isValid(int var1) {
      try {
      try {
         return super.connection.isValid(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setClientInfo(String var1, String var2) {
      try {
      super.connection.setClientInfo(var1, var2);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setClientInfo(Properties var1) {
      try {
      super.connection.setClientInfo(var1);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public String getClientInfo(String var1) {
      try {
      try {
         return super.connection.getClientInfo(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Properties getClientInfo() {
      try {
      try {
         return super.connection.getClientInfo();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Array createArrayOf(String var1, Object[] var2) {
      try {
      try {
         return super.connection.createArrayOf(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Struct createStruct(String var1, Object[] var2) {
      try {
      try {
         return super.connection.createStruct(var1, var2);
      } catch (SQLException var4) {
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setSchema(String var1) {
      try {
      try {
         super.setSchema(var1);
      } catch (Exception var3) {
         if (!(var3 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var3); }
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public String getSchema() {
      try {
      try {
         return super.connection.getSchema();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void abort(Executor var1) {
      try {
      try {
         super.connection.abort(var1);
      } catch (SQLException var3) {
         throw this.getSQLException((SQLException)var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setNetworkTimeout(Executor var1, int var2) {
      try {
      try {
         super.setNetworkTimeout(var1, var2);
      } catch (Exception var4) {
         if (!(var4 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var4); }
         throw this.getSQLException((SQLException)var4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int getNetworkTimeout() {
      try {
      try {
         return super.connection.getNetworkTimeout();
      } catch (SQLException var2) {
         throw this.getSQLException((SQLException)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   public HikariProxyConnection(PoolEntry var1, Connection var2, FastList var3, ProxyLeakTask var4, long var5, boolean var7, boolean var8) {
      super(var1, var2, var3, var4, var5, var7, var8);
   }
}
