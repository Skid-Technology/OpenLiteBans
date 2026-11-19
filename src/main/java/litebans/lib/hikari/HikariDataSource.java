package litebans.lib.hikari;

import java.io.Closeable;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;
import javax.sql.DataSource;

public class HikariDataSource extends HikariConfig implements DataSource, Closeable {
   private static final HikariLogger hikariLogger2 = HikariLogger.getHikariLogger(HikariDataSource.class);
   private final AtomicBoolean atomicBoolean = new AtomicBoolean();
   private final HikariPool hikariPool;
   private volatile HikariPool hikariPool2;

   public HikariDataSource() {
      this.hikariPool = null;
   }

   public HikariDataSource(HikariConfig var1) {
      var1.run2();
      var1.handleHikariConfig(this);
      this.hikariPool2 = this.hikariPool = new HikariPool(this);
      this.run();
   }

   @Override
   public Connection getConnection() throws SQLException {
      if (this.check()) {
         throw new SQLException("HikariDataSource " + this + " has been closed.");
      } else if (this.hikariPool != null) {
         return this.hikariPool.getConnection();
      } else {
         HikariPool var1 = this.hikariPool2;
         if (var1 == null) {
            synchronized (this) {
               var1 = this.hikariPool2;
               if (var1 == null) {
                  this.run2();

                  try {
                     this.hikariPool2 = var1 = new HikariPool(this);
                     this.run();
                  } catch (PoolInitializationException var5) {
                     if (var5.getCause() instanceof SQLException) {
                        throw (SQLException)var5.getCause();
                     }

                     throw var5;
                  }
               }
            }
         }

         return var1.getConnection();
      }
   }

   @Override
   public Connection getConnection(String var1, String var2) throws SQLException {
      throw new SQLFeatureNotSupportedException();
   }

   @Override
   public PrintWriter getLogWriter() throws SQLException {
      HikariPool var1 = this.hikariPool2;
      return var1 != null ? var1.getDataSource().getLogWriter() : null;
   }

   @Override
   public void setLogWriter(PrintWriter var1) throws SQLException {
      HikariPool var2 = this.hikariPool2;
      if (var2 != null) {
         var2.getDataSource().setLogWriter(var1);
      }
   }

   @Override
   public void setLoginTimeout(int var1) throws SQLException {
      HikariPool var2 = this.hikariPool2;
      if (var2 != null) {
         var2.getDataSource().setLoginTimeout(var1);
      }
   }

   @Override
   public int getLoginTimeout() throws SQLException {
      HikariPool var1 = this.hikariPool2;
      return var1 != null ? var1.getDataSource().getLoginTimeout() : 0;
   }

   @Override
   public Logger getParentLogger() throws SQLFeatureNotSupportedException {
      throw new SQLFeatureNotSupportedException();
   }

   @Override
   public Object unwrap(Class var1) throws SQLException {
      if (var1.isInstance(this)) {
         return this;
      } else {
         HikariPool var2 = this.hikariPool2;
         if (var2 != null) {
            DataSource var3 = var2.getDataSource();
            if (var1.isInstance(var3)) {
               return var3;
            }

            if (var3 != null) {
               return var3.unwrap(var1);
            }
         }

         throw new SQLException("Wrapped DataSource is not an instance of " + var1);
      }
   }

   @Override
   public boolean isWrapperFor(Class var1) throws SQLException {
      if (var1.isInstance(this)) {
         return true;
      } else {
         HikariPool var2 = this.hikariPool2;
         if (var2 != null) {
            DataSource var3 = var2.getDataSource();
            if (var1.isInstance(var3)) {
               return true;
            }

            if (var3 != null) {
               return var3.isWrapperFor(var1);
            }
         }

         return false;
      }
   }

   public HikariPoolMXBean getHikariPool2() {
      return this.hikariPool2;
   }

   @Override
   public void close() {
      if (!this.atomicBoolean.getAndSet(true)) {
         HikariPool var1 = this.hikariPool2;
         if (var1 != null) {
            try {
               var1.run4();
            } catch (Exception var3) {
               hikariLogger2.handleString4("%s - Interrupted during closing", new Object[]{this.getString11(), var3});
               Thread.currentThread().interrupt();
            }
         }
      }
   }

   public boolean check() {
      return this.atomicBoolean.get();
   }

   @Override
   public String toString() {
      return "HikariDataSource (" + this.hikariPool2 + ")";
   }
}
