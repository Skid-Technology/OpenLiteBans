package litebans.lib.hikari;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Map.Entry;
import java.util.logging.Logger;
import javax.sql.DataSource;

public final class DriverDataSource implements DataSource {
   private static final HikariLogger hikariLogger = HikariLogger.getHikariLogger(DriverDataSource.class);
   private static final String string = "password";
   private static final String string2 = "user";
   private final String string3;
   private final Properties properties;
   private Driver driver;

   public DriverDataSource(String var1, String var2, Properties var3, String var4, String var5) {
      this.string3 = var1;
      this.properties = new Properties();

      for (Entry var7 : var3.entrySet()) {
         this.properties.setProperty(var7.getKey().toString(), var7.getValue().toString());
      }

      if (var4 != null) {
         this.properties.put("user", this.properties.getProperty("user", var4));
      }

      if (var5 != null) {
         this.properties.put("password", this.properties.getProperty("password", var5));
      }

      if (var2 != null) {
         Enumeration var14 = DriverManager.getDrivers();

         while (var14.hasMoreElements()) {
            Driver var16 = (Driver)var14.nextElement();
            if (var16.getClass().getName().equals(var2)) {
               this.driver = var16;
               break;
            }
         }

         if (this.driver == null) {
            hikariLogger.handleString4("Registered driver with driverClassName={} was not found, trying direct instantiation.", new Object[]{var2});
            Class var17 = null;
            ClassLoader var8 = Thread.currentThread().getContextClassLoader();

            try {
               if (var8 != null) {
                  try {
                     var17 = var8.loadClass(var2);
                     hikariLogger.handleString("Driver class {} found in Thread context class loader {}", new Object[]{var2, var8});
                  } catch (ClassNotFoundException var12) {
                     hikariLogger.handleString(
                        "Driver class {} not found in Thread context class loader {}, trying classloader {}",
                        new Object[]{var2, var8, this.getClass().getClassLoader()}
                     );
                  }
               }

               if (var17 == null) {
                  var17 = this.getClass().getClassLoader().loadClass(var2);
                  hikariLogger.handleString(
                     "Driver class {} found in the HikariConfig class classloader {}", new Object[]{var2, this.getClass().getClassLoader()}
                  );
               }
            } catch (ClassNotFoundException var13) {
               hikariLogger.handleString(
                  "Failed to load driver class {} from HikariConfig class classloader {}", new Object[]{var2, this.getClass().getClassLoader()}
               );
            }

            if (var17 != null) {
               try {
                  this.driver = (Driver)var17.getDeclaredConstructor().newInstance();
               } catch (Exception var11) {
                  hikariLogger.handleString4("Failed to create instance of driver class {}, trying jdbcUrl resolution", new Object[]{var2, var11});
               }
            }
         }
      }

      String var15 = var1.replaceAll("([?&;]password=)[^&#;]*(.*)", "$1<masked>$2");

      try {
         if (this.driver == null) {
            this.driver = DriverManager.getDriver(var1);
            hikariLogger.handleString("Loaded driver with class name {} for jdbcUrl={}", new Object[]{this.driver.getClass().getName(), var15});
         } else if (!this.driver.acceptsURL(var1)) {
            throw new RuntimeException("Driver " + var2 + " claims to not accept jdbcUrl, " + var15);
         }
      } catch (SQLException var10) {
         throw new RuntimeException("Failed to get driver instance for jdbcUrl=" + var15, var10);
      }
   }

   @Override
   public Connection getConnection() {
      try {
      return this.driver.connect(this.string3, this.properties);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Connection getConnection(String var1, String var2) {
      try {
      Properties var3 = (Properties)this.properties.clone();
      if (var1 != null) {
         var3.put("user", var1);
         if (var3.containsKey("username")) {
            var3.put("username", var1);
         }
      }

      if (var2 != null) {
         var3.put("password", var2);
      }

      return this.driver.connect(this.string3, var3);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public PrintWriter getLogWriter() {
      try {
      throw new SQLFeatureNotSupportedException();
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setLogWriter(PrintWriter var1) {
      try {
      throw new SQLFeatureNotSupportedException();
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void setLoginTimeout(int var1) {
      DriverManager.setLoginTimeout(var1);
   }

   @Override
   public int getLoginTimeout() {
      return DriverManager.getLoginTimeout();
   }

   @Override
   public Logger getParentLogger() {
      try {
      return this.driver.getParentLogger();
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Object unwrap(Class var1) {
      try {
      throw new SQLFeatureNotSupportedException();
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public boolean isWrapperFor(Class var1) {
      return false;
   }
}
