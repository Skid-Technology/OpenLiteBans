package litebans.lib.hikari;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executor;

public abstract class ProxyConnection implements Connection {
   public static final int intValue = 1;
   public static final int intValue2 = 2;
   public static final int intValue3 = 4;
   public static final int intValue4 = 8;
   public static final int intValue5 = 16;
   public static final int intValue6 = 32;
   private static final HikariLogger hikariLogger = HikariLogger.getHikariLogger(ProxyConnection.class);
   private static final Set set = new HashSet();
   private static final Set set2 = new HashSet();
   public Connection connection;
   private final PoolEntry poolEntry;
   private final ProxyLeakTask proxyLeakTask;
   private final FastList fastList;
   private int intValue7;
   private long longValue;
   private boolean flag;
   private boolean flag2;
   private boolean flag3;
   private int intValue8;
   private int intValue9;
   private String string;
   private String string2;

   public ProxyConnection(PoolEntry var1, Connection var2, FastList var3, ProxyLeakTask var4, long var5, boolean var7, boolean var8) {
      this.poolEntry = var1;
      this.connection = var2;
      this.fastList = var3;
      this.proxyLeakTask = var4;
      this.longValue = var5;
      this.flag2 = var7;
      this.flag3 = var8;
   }

   @Override
   public final String toString() {
      return this.getClass().getSimpleName() + '@' + System.identityHashCode(this) + " wrapping " + this.connection;
   }

   public final boolean isFlag3() {
      return this.flag3;
   }

   public final String getString() {
      return this.string;
   }

   public final String getString2() {
      return this.string2;
   }

   public final int getIntValue9() {
      return this.intValue9;
   }

   public final boolean isFlag2() {
      return this.flag2;
   }

   public final int getIntValue8() {
      return this.intValue8;
   }

   public final SQLException getSQLException(SQLException var1) {
      boolean var2 = false;
      SQLException var3 = var1;
      SQLExceptionOverride var4 = this.poolEntry.getHikariPool().sqlExceptionOverride;

      for (int var5 = 0; this.connection != ClosedConnectionProxy.connection && var3 != null && var5 < 10; var5++) {
         String var6 = var3.getSQLState();
         if (var6 != null && var6.startsWith("08") || var3 instanceof SQLTimeoutException || set.contains(var6) || set2.contains(var3.getErrorCode())) {
            if (var4 == null || var4.getSQLExceptionOverrideResult(var3) != SQLExceptionOverrideResult.DO_NOT_EVICT) {
               var2 = true;
            }
            break;
         }

         var3 = var3.getNextException();
      }

      if (var2) {
         SQLException var7 = var3 != null ? var3 : var1;
         hikariLogger.handleString4(
            "%s - Connection %s marked as broken because of SQLSTATE(%s), ErrorCode(%d)",
            new Object[]{this.poolEntry.getString(), this.connection.toString(), var7.getSQLState(), var7.getErrorCode(), var7}
         );
         this.proxyLeakTask.run2();
         this.poolEntry.handleString("(connection is broken)");
         this.connection = ClosedConnectionProxy.connection;
      }

      return var1;
   }

   public final synchronized void handleStatement(Statement var1) {
      this.fastList.remove(var1);
   }

   public final void run() {
      if (this.flag3) {
         this.longValue = ClockSource.getLong2();
      } else {
         this.flag = true;
      }
   }

   private final synchronized Statement getStatement(Statement var1) {
      this.fastList.add(var1);
      return var1;
   }

   private final synchronized void run2() {
      int var1 = this.fastList.size();
      if (var1 > 0) {
         for (int var2 = 0; var2 < var1 && this.connection != ClosedConnectionProxy.connection; var2++) {
            try {
               Statement var3 = (Statement)this.fastList.get(var2);
               Throwable var4 = null;
               if (var3 != null) {
                  if (var4 != null) {
                     try {
                        var3.close();
                     } catch (Throwable var6) {
                        var4.addSuppressed(var6);
                     }
                  } else {
                     var3.close();
                  }
               }
            } catch (SQLException var7) {
               hikariLogger.handleString4(
                  "%s - Connection %s marked as broken because of an exception closing open statements during Connection.close()",
                  new Object[]{this.poolEntry.getString(), this.connection.toString()}
               );
               this.proxyLeakTask.run2();
               this.poolEntry.handleString("(exception closing Statements during Connection.close())");
               this.connection = ClosedConnectionProxy.connection;
            }
         }

         this.fastList.clear();
      }
   }

   @Override
   public final void close() {
      try {
      this.run2();
      if (this.connection != ClosedConnectionProxy.connection) {
         this.proxyLeakTask.run2();

         try {
            if (this.flag && !this.flag3) {
               this.connection.rollback();
               this.longValue = ClockSource.getLong2();
               hikariLogger.handleString(
                  "%s - Executed rollback on connection %s due to dirty commit state on close().",
                  new Object[]{this.poolEntry.getString(), this.connection.toString()}
               );
            }

            if (this.intValue7 != 0) {
               this.poolEntry.handleProxyConnection(this, this.intValue7);
               this.longValue = ClockSource.getLong2();
            }

            this.connection.clearWarnings();
         } catch (SQLException var5) {
            if (!this.poolEntry.isFlag()) {
               throw this.getSQLException(var5);
            }
         } finally {
            this.connection = ClosedConnectionProxy.connection;
            this.poolEntry.handleLong(this.longValue);
         }
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean isClosed() {
      return this.connection == ClosedConnectionProxy.connection;
   }

   @Override
   public Statement createStatement() {
      try {
      return ProxyFactory.getStatement(this, this.getStatement(this.connection.createStatement()));
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Statement createStatement(int var1, int var2) {
      try {
      return ProxyFactory.getStatement(this, this.getStatement(this.connection.createStatement(var1, var2)));
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Statement createStatement(int var1, int var2, int var3) {
      try {
      return ProxyFactory.getStatement(this, this.getStatement(this.connection.createStatement(var1, var2, var3)));
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public CallableStatement prepareCall(String var1) {
      try {
      return ProxyFactory.getCallableStatement(this, (CallableStatement)this.getStatement(this.connection.prepareCall(var1)));
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public CallableStatement prepareCall(String var1, int var2, int var3) {
      try {
      return ProxyFactory.getCallableStatement(this, (CallableStatement)this.getStatement(this.connection.prepareCall(var1, var2, var3)));
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public CallableStatement prepareCall(String var1, int var2, int var3, int var4) {
      try {
      return ProxyFactory.getCallableStatement(this, (CallableStatement)this.getStatement(this.connection.prepareCall(var1, var2, var3, var4)));
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public PreparedStatement prepareStatement(String var1) {
      try {
      return ProxyFactory.getPreparedStatement(this, (PreparedStatement)this.getStatement(this.connection.prepareStatement(var1)));
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public PreparedStatement prepareStatement(String var1, int var2) {
      try {
      return ProxyFactory.getPreparedStatement(this, (PreparedStatement)this.getStatement(this.connection.prepareStatement(var1, var2)));
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public PreparedStatement prepareStatement(String var1, int var2, int var3) {
      try {
      return ProxyFactory.getPreparedStatement(this, (PreparedStatement)this.getStatement(this.connection.prepareStatement(var1, var2, var3)));
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public PreparedStatement prepareStatement(String var1, int var2, int var3, int var4) {
      try {
      return ProxyFactory.getPreparedStatement(this, (PreparedStatement)this.getStatement(this.connection.prepareStatement(var1, var2, var3, var4)));
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public PreparedStatement prepareStatement(String var1, int[] var2) {
      try {
      return ProxyFactory.getPreparedStatement(this, (PreparedStatement)this.getStatement(this.connection.prepareStatement(var1, var2)));
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public PreparedStatement prepareStatement(String var1, String[] var2) {
      try {
      return ProxyFactory.getPreparedStatement(this, (PreparedStatement)this.getStatement(this.connection.prepareStatement(var1, var2)));
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public DatabaseMetaData getMetaData() {
      try {
      this.run();
      return ProxyFactory.getDatabaseMetaData(this, this.connection.getMetaData());
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void commit() {
      try {
      this.connection.commit();
      this.flag = false;
      this.longValue = ClockSource.getLong2();
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void rollback() {
      try {
      this.connection.rollback();
      this.flag = false;
      this.longValue = ClockSource.getLong2();
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void rollback(Savepoint var1) {
      try {
      this.connection.rollback(var1);
      this.flag = false;
      this.longValue = ClockSource.getLong2();
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setAutoCommit(boolean var1) {
      try {
      this.connection.setAutoCommit(var1);
      this.flag3 = var1;
      this.intValue7 |= 2;
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setReadOnly(boolean var1) {
      try {
      this.connection.setReadOnly(var1);
      this.flag2 = var1;
      this.flag = false;
      this.intValue7 |= 1;
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setTransactionIsolation(int var1) {
      try {
      this.connection.setTransactionIsolation(var1);
      this.intValue9 = var1;
      this.intValue7 |= 4;
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setCatalog(String var1) {
      try {
      this.connection.setCatalog(var1);
      this.string = var1;
      this.intValue7 |= 8;
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setNetworkTimeout(Executor var1, int var2) {
      try {
      this.connection.setNetworkTimeout(var1, var2);
      this.intValue8 = var2;
      this.intValue7 |= 16;
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setSchema(String var1) {
      try {
      this.connection.setSchema(var1);
      this.string2 = var1;
      this.intValue7 |= 32;
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public final boolean isWrapperFor(Class var1) {
      try {
      return var1.isInstance(this.connection) || this.connection != null && this.connection.isWrapperFor(var1);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public final Object unwrap(Class var1) {
      try {
      if (var1.isInstance(this.connection)) {
         return this.connection;
      } else if (this.connection != null) {
         return this.connection.unwrap(var1);
      } else {
         throw new SQLException("Wrapped connection is not an instance of " + var1);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   static {
      set.add("0A000");
      set.add("57P01");
      set.add("57P02");
      set.add("57P03");
      set.add("01002");
      set.add("JZ0C0");
      set.add("JZ0C1");
      set2.add(500150);
      set2.add(2399);
   }
}
