package litebans.lib.hikari;

import java.lang.management.ManagementFactory;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLTransientConnectionException;
import java.sql.Statement;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public abstract class PoolBase {
   private final HikariLogger hikariLogger = HikariLogger.getHikariLogger(PoolBase.class);
   public final HikariConfig hikariConfig;
   public final String string;
   public volatile String string2;
   public final AtomicReference atomicReference;
   public long longValue;
   public long longValue2;
   public SQLExceptionOverride sqlExceptionOverride;
   private static final String[] stringArray = new String[]{"readOnly", "autoCommit", "isolation", "catalog", "netTimeout", "schema"};
   private static final int intValue = -1;
   private static final int intValue2 = 1;
   private static final int intValue3 = 0;
   private int intValue4;
   private int intValue5;
   private int intValue6;
   private int intValue7;
   private int intValue8;
   private Executor executor;
   private DataSource dataSource;
   private final String string3;
   private final boolean flag;
   private final boolean flag2;
   private final boolean flag3;
   private final boolean flag4;
   private volatile boolean flag5;

   public PoolBase(HikariConfig var1) {
      this.hikariConfig = var1;
      this.intValue4 = -1;
      this.string2 = var1.getString();
      this.string3 = var1.getString12();
      this.flag = var1.isFlag3();
      this.flag2 = var1.isFlag2();
      this.sqlExceptionOverride = (SQLExceptionOverride)UtilityElf.getObject(var1.getString9(), SQLExceptionOverride.class, new Object[0]);
      this.intValue8 = UtilityElf.getInt(var1.getString13());
      this.intValue6 = -1;
      this.intValue5 = -1;
      this.flag3 = var1.getString5() == null;
      this.flag4 = var1.isFlag4();
      this.string = var1.getString11();
      this.longValue = var1.getLongValue7();
      this.longValue2 = var1.getLongValue8();
      this.atomicReference = new AtomicReference();
      this.run9();
   }

   @Override
   public String toString() {
      return this.string;
   }

   public abstract void handlePoolEntry(PoolEntry var1);

   public void handleConnection2(Connection var1, String var2) {
      try {
      if (var1 != null) {
         try {
            this.hikariLogger.handleString("%s - Closing connection %s: %s", new Object[]{this.string, var1.toString(), var2});

            try {
               this.handleConnection(var1, TimeUnit.SECONDS.toMillis(15L));
            } catch (Exception var8) {
            } finally {
               var1.close();
            }
         } catch (Exception var10) {
            this.hikariLogger.handleString("%s - Closing connection %s failed", new Object[]{this.string, var1.toString(), var10});
         }
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   public boolean checkConnection(Connection var1) {
      try {
         try {
            this.handleConnection(var1, this.longValue2);
            int var2 = (int)Math.max(1000L, this.longValue2) / 1000;
            if (this.flag3) {
               return var1.isValid(var2);
            }

            try (Statement var3 = var1.createStatement()) {
               if (this.intValue5 != 1) {
                  this.handleStatement(var3, var2);
               }

               var3.execute(this.hikariConfig.getString5());
            }
         } finally {
            this.handleConnection(var1, (long)this.intValue4);
            if (this.flag4 && !this.flag2) {
               var1.rollback();
            }
         }

         return true;
      } catch (Exception var25) {
         this.atomicReference.set(var25);
         this.hikariLogger
            .handleString4(
               "%s - Failed to validate connection %s (%s). Possibly consider using a shorter maxLifetime value.",
               new Object[]{this.string, var1.toString(), var25.getMessage()}
            );
         return false;
      }
   }

   public Exception getException() {
      return (Exception)this.atomicReference.get();
   }

   public DataSource getDataSource() {
      return this.dataSource;
   }

   public PoolEntry getPoolEntry2() {
      return new PoolEntry(this.getConnection2(), this, this.flag, this.flag2);
   }

   public void handleConnection(Connection var1, ProxyConnection var2, int var3) {
      try {
      byte var4 = 0;
      if ((var3 & 1) != 0 && var2.isFlag2() != this.flag) {
         var1.setReadOnly(this.flag);
         var4 |= 1;
      }

      if ((var3 & 2) != 0 && var2.isFlag3() != this.flag2) {
         var1.setAutoCommit(this.flag2);
         var4 |= 2;
      }

      if ((var3 & 4) != 0 && var2.getIntValue9() != this.intValue8) {
         var1.setTransactionIsolation(this.intValue8);
         var4 |= 4;
      }

      if ((var3 & 8) != 0 && this.string2 != null && !this.string2.equals(var2.getString())) {
         var1.setCatalog(this.string2);
         var4 |= 8;
      }

      if ((var3 & 16) != 0 && var2.getIntValue8() != this.intValue4) {
         this.handleConnection(var1, (long)this.intValue4);
         var4 |= 16;
      }

      if ((var3 & 32) != 0 && this.string3 != null && !this.string3.equals(var2.getString2())) {
         var1.setSchema(this.string3);
         var4 |= 32;
      }

      if (var4 != 0 && this.hikariLogger.check()) {
         this.hikariLogger.handleString("%s - Reset (%s) on connection %s", new Object[]{this.string, this.getString(var4), var1.toString()});
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   public void run8() {
      if (this.executor instanceof ThreadPoolExecutor) {
         ((ThreadPoolExecutor)this.executor).shutdownNow();
      }
   }

   public long getLong() {
      try {
         return this.dataSource != null ? (long)this.dataSource.getLoginTimeout() : TimeUnit.SECONDS.toSeconds(5L);
      } catch (SQLException var1) {
         return TimeUnit.SECONDS.toSeconds(5L);
      }
   }

   public void handleHikariPool(HikariPool var1, boolean var2) {
      if (this.hikariConfig.isFlag5()) {
         try {
            MBeanServer var3 = ManagementFactory.getPlatformMBeanServer();
            ObjectName var4;
            ObjectName var5;
            if ("true".equals(System.getProperty("hikaricp.jmx.register2.0"))) {
               var4 = new ObjectName("com.zaxxer.hikari:type=PoolConfig,name=" + this.string);
               var5 = new ObjectName("com.zaxxer.hikari:type=Pool,name=" + this.string);
            } else {
               var4 = new ObjectName("com.zaxxer.hikari:type=PoolConfig (" + this.string + ")");
               var5 = new ObjectName("com.zaxxer.hikari:type=Pool (" + this.string + ")");
            }

            if (var2) {
               if (!var3.isRegistered(var4)) {
                  var3.registerMBean(this.hikariConfig, var4);
                  var3.registerMBean(var1, var5);
               } else {
                  this.hikariLogger.handleString2("{} - JMX name ({}) is already registered.", new Object[]{this.string, this.string});
               }
            } else if (var3.isRegistered(var4)) {
               var3.unregisterMBean(var4);
               var3.unregisterMBean(var5);
            }
         } catch (Exception var6) {
            this.hikariLogger.handleString4("{} - Failed to {} management beans.", new Object[]{this.string, var2 ? "register" : "unregister", var6});
         }
      }
   }

   private final void run9() {
      String var1 = this.hikariConfig.getString10();
      String var2 = this.hikariConfig.getString2();
      String var3 = this.hikariConfig.getString3();
      String var4 = this.hikariConfig.getString6();
      String var5 = this.hikariConfig.getString8();
      String var6 = this.hikariConfig.getString7();
      Properties var7 = this.hikariConfig.getProperties();
      Object var8 = this.hikariConfig.getDataSource();
      if (var4 != null && var8 == null) {
         var8 = (DataSource)UtilityElf.getObject(var4, DataSource.class, new Object[0]);
         PropertyElf.handleObject(var8, var7);
      } else if (var1 != null && var8 == null) {
         var8 = new DriverDataSource(var1, var5, var7, var2, var3);
      } else if (var6 != null && var8 == null) {
         try {
            InitialContext var9 = new InitialContext();
            var8 = (DataSource)var9.lookup(var6);
         } catch (NamingException var10) {
            throw new PoolInitializationException(var10);
         }
      }

      if (var8 != null) {
         this.handleDataSource((DataSource)var8);
         this.handleDataSource((DataSource)var8, var4, var1);
      }

      this.dataSource = (DataSource)var8;
   }

   private final Connection getConnection2() {
      try {
      ClockSource.getLong2();
      Connection var3 = null;

      try {
         String var4 = this.hikariConfig.getString2();
         String var5 = this.hikariConfig.getString3();
         var3 = var4 == null ? this.dataSource.getConnection() : this.dataSource.getConnection(var4, var5);
         if (var3 == null) {
            throw new SQLTransientConnectionException("DataSource returned null unexpectedly");
         } else {
            this.handleConnection(var3);
            this.atomicReference.set(null);
            return var3;
         }
      } catch (Exception var6) {
         if (var3 != null) {
            this.handleConnection2(var3, "(Failed to create/setup connection)");
         } else if (this.getException() == null) {
            this.hikariLogger.handleString("%s - Failed to create/setup connection: %s", new Object[]{this.string, var6.getMessage()});
         }

         this.atomicReference.set(var6);
         throw var6;
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   private final void handleConnection(Connection var1) {
      try {
      try {
         if (this.intValue4 == -1) {
            this.intValue4 = this.getInt(var1, this.longValue2);
         } else {
            this.handleConnection(var1, this.longValue2);
         }

         if (var1.isReadOnly() != this.flag) {
            var1.setReadOnly(this.flag);
         }

         if (var1.getAutoCommit() != this.flag2) {
            var1.setAutoCommit(this.flag2);
         }

         this.handleConnection2(var1);
         if (this.intValue8 != this.intValue7) {
            var1.setTransactionIsolation(this.intValue8);
         }

         if (this.string2 != null) {
            var1.setCatalog(this.string2);
         }

         if (this.string3 != null) {
            var1.setSchema(this.string3);
         }

         this.handleConnection(var1, this.hikariConfig.getString4(), true);
         this.handleConnection(var1, (long)this.intValue4);
      } catch (SQLException var3) {
         throw new ConnectionSetupException(var3);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   private final void handleConnection2(Connection var1) {
      if (!this.flag5) {
         this.handleConnection3(var1);
         this.handleConnection4(var1);
         this.flag5 = true;
      }
   }

   private final void handleConnection3(Connection var1) {
      try {
      try {
         if (this.flag3) {
            var1.isValid(1);
         } else {
            this.handleConnection(var1, this.hikariConfig.getString5(), false);
         }
      } catch (AbstractMethodError | Exception var3) {
         this.hikariLogger
            .handleString2(
               "%s - Failed to execute%s connection test query (%s).",
               new Object[]{this.string, this.flag3 ? " isValid() for connection, configure" : "", var3.getMessage()}
            );
         throw var3;
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   private final void handleConnection4(Connection var1) {
      try {
      try {
         this.intValue7 = var1.getTransactionIsolation();
         if (this.intValue8 == -1) {
            this.intValue8 = this.intValue7;
         }
      } catch (SQLException var3) {
         this.hikariLogger.handleString4("%s - Default transaction isolation level detection failed (%s).", new Object[]{this.string, var3.getMessage()});
         if (var3.getSQLState() != null && !var3.getSQLState().startsWith("08")) {
            throw var3;
         }
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   private final void handleStatement(Statement var1, int var2) {
      if (this.intValue6 != 0) {
         try {
            var1.setQueryTimeout(var2);
            this.intValue6 = 1;
         } catch (Exception var4) {
            if (this.intValue6 == -1) {
               this.intValue6 = 0;
               this.hikariLogger.handleString3("%s - Failed to set query timeout for statement. (%s)", new Object[]{this.string, var4.getMessage()});
            }
         }
      }
   }

   private final int getInt(Connection var1, long var2) {
      if (this.intValue5 != 0) {
         try {
            int var4 = var1.getNetworkTimeout();
            var1.setNetworkTimeout(this.executor, (int)var2);
            this.intValue5 = 1;
            return var4;
         } catch (AbstractMethodError | Exception var5) {
            if (this.intValue5 == -1) {
               this.intValue5 = 0;
               this.hikariLogger
                  .handleString3("%s - Driver does not support get/set network timeout for connections. (%s)", new Object[]{this.string, var5.getMessage()});
               if (this.longValue2 < TimeUnit.SECONDS.toMillis(1L)) {
                  this.hikariLogger
                     .handleString4(
                        "%s - A validationTimeout of less than 1 second cannot be honored on drivers without setNetworkTimeout() support.",
                        new Object[]{this.string}
                     );
               } else if (this.longValue2 % TimeUnit.SECONDS.toMillis(1L) != 0L) {
                  this.hikariLogger
                     .handleString4(
                        "%s - A validationTimeout with fractional second granularity cannot be honored on drivers without setNetworkTimeout() support.",
                        new Object[]{this.string}
                     );
               }
            }
         }
      }

      return 0;
   }

   private final void handleConnection(Connection var1, long var2) {
      try {
      if (this.intValue5 == 1) {
         var1.setNetworkTimeout(this.executor, (int)var2);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   private final void handleConnection(Connection var1, String var2, boolean var3) {
      try {
      if (var2 != null) {
         try (Statement var4 = var1.createStatement()) {
            var4.execute(var2);
         }

         if (this.flag4 && !this.flag2) {
            if (var3) {
               var1.commit();
            } else {
               var1.rollback();
            }
         }
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   private final void handleDataSource(DataSource var1, String var2, String var3) {
      if ((var2 == null || !var2.contains("Mysql"))
         && (var3 == null || !var3.contains("mysql"))
         && (var1 == null || !var1.getClass().getName().contains("Mysql"))) {
         ThreadFactory var4 = this.hikariConfig.getThreadFactory();
         var4 = (ThreadFactory)(var4 != null ? var4 : new DefaultThreadFactory(this.string + " network timeout executor", true));
         ThreadPoolExecutor var5 = (ThreadPoolExecutor)Executors.newCachedThreadPool(var4);
         var5.setKeepAliveTime(15L, TimeUnit.SECONDS);
         var5.allowCoreThreadTimeOut(true);
         this.executor = var5;
      } else {
         this.executor = new LoggingExecutor(null);
      }
   }

   private final void handleDataSource(DataSource var1) {
      if (this.longValue != 2147483647L) {
         try {
            var1.setLoginTimeout(Math.max(1, (int)TimeUnit.MILLISECONDS.toSeconds(500L + this.longValue)));
         } catch (Exception var3) {
            this.hikariLogger.handleString3("%s - Failed to set login timeout for data source. (%s)", new Object[]{this.string, var3.getMessage()});
         }
      }
   }

   private final String getString(int var1) {
      StringBuilder var2 = new StringBuilder();

      for (int var3 = 0; var3 < stringArray.length; var3++) {
         if ((var1 & 1 << var3) != 0) {
            var2.append(stringArray[var3]).append(", ");
         }
      }

      var2.setLength(var2.length() - 2);
      return var2.toString();
   }
}
