package litebans.lib.hikari;

import java.util.ResourceBundle;
import java.util.function.Supplier;
import java.util.logging.Filter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class Slf4jJulLogger extends Logger {
   private final org.slf4j.Logger logger;

   public Slf4jJulLogger(org.slf4j.Logger var1, String var2) {
      super(var2, null);
      this.logger = var1;
   }

   @Override
   public void addHandler(Handler var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public void config(String var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public void entering(String var1, String var2) {
      throw new UnsupportedOperationException();
   }

   @Override
   public void entering(String var1, String var2, Object var3) {
      throw new UnsupportedOperationException();
   }

   @Override
   public void entering(String var1, String var2, Object[] var3) {
      throw new UnsupportedOperationException();
   }

   @Override
   public void exiting(String var1, String var2) {
      throw new UnsupportedOperationException();
   }

   @Override
   public void exiting(String var1, String var2, Object var3) {
      throw new UnsupportedOperationException();
   }

   @Override
   public void fine(String var1) {
      this.logger.trace(var1);
   }

   @Override
   public void fine(Supplier var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public void finer(String var1) {
      this.logger.trace(var1);
   }

   @Override
   public void finer(Supplier var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public void finest(String var1) {
   }

   @Override
   public void finest(Supplier var1) {
   }

   @Override
   public Filter getFilter() {
      throw new UnsupportedOperationException();
   }

   @Override
   public void setFilter(Filter var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public Handler[] getHandlers() {
      throw new UnsupportedOperationException();
   }

   @Override
   public Level getLevel() {
      throw new UnsupportedOperationException();
   }

   @Override
   public void setLevel(Level var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public String getName() {
      return this.logger.getName();
   }

   @Override
   public Logger getParent() {
      throw new UnsupportedOperationException();
   }

   @Override
   public void setParent(Logger var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public ResourceBundle getResourceBundle() {
      throw new UnsupportedOperationException();
   }

   @Override
   public void setResourceBundle(ResourceBundle var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public String getResourceBundleName() {
      throw new UnsupportedOperationException();
   }

   @Override
   public boolean getUseParentHandlers() {
      throw new UnsupportedOperationException();
   }

   @Override
   public void setUseParentHandlers(boolean var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public void info(String var1) {
      this.logger.info(var1);
   }

   @Override
   public void info(Supplier var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public boolean isLoggable(Level var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public void log(LogRecord var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public void log(Level var1, String var2) {
      String var3 = var1.getName();
      switch (var3) {
         case "WARNING":
            this.handleString(var2);
            break;
         case "SEVERE":
            this.severe(var2);
            break;
         default:
            this.info(var2);
      }
   }

   @Override
   public void log(Level var1, Supplier var2) {
      throw new UnsupportedOperationException();
   }

   @Override
   public void log(Level var1, String var2, Object var3) {
      throw new UnsupportedOperationException();
   }

   @Override
   public void log(Level var1, String var2, Object[] var3) {
      throw new UnsupportedOperationException();
   }

   @Override
   public void log(Level var1, String var2, Throwable var3) {
      throw new UnsupportedOperationException();
   }

   @Override
   public void log(Level var1, Throwable var2, Supplier var3) {
      throw new UnsupportedOperationException();
   }

   @Override
   public void logp(Level var1, String var2, String var3, String var4) {
      throw new UnsupportedOperationException();
   }

   @Override
   public void logp(Level var1, String var2, String var3, Supplier var4) {
      throw new UnsupportedOperationException();
   }

   @Override
   public void logp(Level var1, String var2, String var3, String var4, Object var5) {
      throw new UnsupportedOperationException();
   }

   @Override
   public void logp(Level var1, String var2, String var3, String var4, Object[] var5) {
      throw new UnsupportedOperationException();
   }

   @Override
   public void logp(Level var1, String var2, String var3, String var4, Throwable var5) {
      throw new UnsupportedOperationException();
   }

   @Override
   public void logp(Level var1, String var2, String var3, Throwable var4, Supplier var5) {
      throw new UnsupportedOperationException();
   }

   @Override
   public void logrb(Level var1, String var2, String var3, ResourceBundle var4, String var5, Object[] var6) {
      throw new UnsupportedOperationException();
   }

   @Override
   public void logrb(Level var1, String var2, String var3, ResourceBundle var4, String var5, Throwable var6) {
      throw new UnsupportedOperationException();
   }

   @Override
   public void removeHandler(Handler var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public void severe(String var1) {
      this.logger.error(var1);
   }

   @Override
   public void severe(Supplier var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public void throwing(String var1, String var2, Throwable var3) {
      throw new UnsupportedOperationException();
   }

   public void handleString(String var1) {
      this.logger.warn(var1);
   }

   @Override
   public void warning(String var1) {
      this.logger.warn(var1);
   }

   @Override
   public void warning(Supplier var1) {
      throw new UnsupportedOperationException();
   }
}
