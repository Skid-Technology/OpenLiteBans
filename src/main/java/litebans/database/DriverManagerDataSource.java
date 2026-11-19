package litebans.database;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.logging.Logger;
import javax.sql.DataSource;
import litebans.lib.hikari.HikariConfig;
import litebans.lib.kotlin.NoWhenBranchMatchedException;
import litebans.util.Unreachable;
import org.jetbrains.annotations.NotNull;

public final class DriverManagerDataSource implements DataSource {
   private final Driver driver;
   private final HikariConfig hikariConfig;

   public DriverManagerDataSource(@NotNull Driver var1, @NotNull HikariConfig var2) {
      this.driver = var1;
      this.hikariConfig = var2;
      this.setLoginTimeout((int)this.hikariConfig.getLongValue7());
   }

   @Override
   public Connection getConnection() {
      try {
      return this.driver.connect(this.hikariConfig.getString10(), this.hikariConfig.getProperties());
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public Connection getConnection(@NotNull String var1, @NotNull String var2) {
      return this.getConnection();
   }

   @Override
   public int getLoginTimeout() {
      return DriverManager.getLoginTimeout();
   }

   @Override
   public void setLoginTimeout(int var1) {
      DriverManager.setLoginTimeout(var1);
   }

   @Override
   public PrintWriter getLogWriter() {
      Unreachable.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   public Void getVoid(@NotNull PrintWriter var1) {
      Unreachable.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public Object unwrap(@NotNull Class var1) {
      Unreachable.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   @Override
   public boolean isWrapperFor(@NotNull Class var1) {
      return false;
   }

   @Override
   public Logger getParentLogger() {
      return null;
   }

   @Override
   public void setLogWriter(PrintWriter var1) {
      this.getVoid(var1);
   }
}
