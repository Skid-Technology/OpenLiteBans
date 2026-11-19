package litebans.lib.hikari;

import java.util.ResourceBundle;
import java.util.function.Supplier;
import java.util.logging.Filter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public class HikariLogger extends Logger {
   public static boolean flag = false;
   private static Logger logger = Logger.getLogger("HikariLogger");
   private static HikariLogger HIKARILOGGER = new HikariLogger("HikariLogger");

   public HikariLogger(String var1) {
      super(var1, null);
   }

   public static HikariLogger getHikariLogger(Class var0) {
      return HIKARILOGGER;
   }

   public static void handleLogger(Logger var0) {
      logger = var0;
   }

   @Override
   public void addHandler(Handler var1) {
      logger.addHandler(var1);
   }

   @Override
   public void config(String var1) {
      logger.config(var1);
   }

   public void handleString(String var1, Object[] var2) {
   }

   @Override
   public void entering(String var1, String var2) {
      logger.entering(var1, var2);
   }

   @Override
   public void entering(String var1, String var2, Object var3) {
      logger.entering(var1, var2, var3);
   }

   @Override
   public void entering(String var1, String var2, Object[] var3) {
      logger.entering(var1, var2, var3);
   }

   public void handleString2(String var1, Object[] var2) {
      logger.log(Level.SEVERE, String.format(var1, var2));
   }

   @Override
   public void exiting(String var1, String var2) {
      logger.exiting(var1, var2);
   }

   @Override
   public void exiting(String var1, String var2, Object var3) {
      logger.exiting(var1, var2, var3);
   }

   @Override
   public void fine(String var1) {
      logger.fine(var1);
   }

   @Override
   public void fine(Supplier var1) {
      logger.fine(var1);
   }

   @Override
   public void finer(String var1) {
      logger.finer(var1);
   }

   @Override
   public void finer(Supplier var1) {
      logger.finer(var1);
   }

   @Override
   public void finest(String var1) {
   }

   @Override
   public void finest(Supplier var1) {
   }

   @Override
   public Filter getFilter() {
      return logger.getFilter();
   }

   @Override
   public void setFilter(Filter var1) {
      logger.setFilter(var1);
   }

   @Override
   public Handler[] getHandlers() {
      return logger.getHandlers();
   }

   @Override
   public Level getLevel() {
      return logger.getLevel();
   }

   @Override
   public void setLevel(Level var1) {
      logger.setLevel(var1);
   }

   @Override
   public String getName() {
      return logger.getName();
   }

   @Override
   public Logger getParent() {
      return logger.getParent();
   }

   @Override
   public void setParent(Logger var1) {
      logger.setParent(var1);
   }

   @Override
   public ResourceBundle getResourceBundle() {
      return logger.getResourceBundle();
   }

   @Override
   public void setResourceBundle(ResourceBundle var1) {
      logger.setResourceBundle(var1);
   }

   @Override
   public String getResourceBundleName() {
      return logger.getResourceBundleName();
   }

   @Override
   public boolean getUseParentHandlers() {
      return logger.getUseParentHandlers();
   }

   @Override
   public void setUseParentHandlers(boolean var1) {
      logger.setUseParentHandlers(var1);
   }

   public void handleString3(String var1, Object[] var2) {
      this.log(Level.INFO, String.format(var1, var2));
   }

   @Override
   public void info(String var1) {
      logger.info(var1);
   }

   @Override
   public void info(Supplier var1) {
      logger.info(var1);
   }

   public boolean check() {
      return false;
   }

   @Override
   public boolean isLoggable(Level var1) {
      return logger.isLoggable(var1);
   }

   @Override
   public void log(LogRecord var1) {
      logger.log(var1);
   }

   @Override
   public void log(Level var1, String var2) {
      logger.log(var1, var2);
   }

   @Override
   public void log(Level var1, Supplier var2) {
      logger.log(var1, var2);
   }

   @Override
   public void log(Level var1, String var2, Object var3) {
      logger.log(var1, var2, var3);
   }

   @Override
   public void log(Level var1, String var2, Object[] var3) {
      logger.log(var1, var2, var3);
   }

   @Override
   public void log(Level var1, String var2, Throwable var3) {
      logger.log(var1, var2, var3);
   }

   @Override
   public void log(Level var1, Throwable var2, Supplier var3) {
      logger.log(var1, var2, var3);
   }

   @Override
   public void logp(Level var1, String var2, String var3, String var4) {
      logger.logp(var1, var2, var3, var4);
   }

   @Override
   public void logp(Level var1, String var2, String var3, Supplier var4) {
      logger.logp(var1, var2, var3, var4);
   }

   @Override
   public void logp(Level var1, String var2, String var3, String var4, Object var5) {
      logger.logp(var1, var2, var3, var4, var5);
   }

   @Override
   public void logp(Level var1, String var2, String var3, String var4, Object[] var5) {
      logger.logp(var1, var2, var3, var4, var5);
   }

   @Override
   public void logp(Level var1, String var2, String var3, String var4, Throwable var5) {
      logger.logp(var1, var2, var3, var4, var5);
   }

   @Override
   public void logp(Level var1, String var2, String var3, Throwable var4, Supplier var5) {
      logger.logp(var1, var2, var3, var4, var5);
   }

   @Override
   public void logrb(Level var1, String var2, String var3, ResourceBundle var4, String var5, Object[] var6) {
      logger.logrb(var1, var2, var3, var4, var5, var6);
   }

   @Override
   public void logrb(Level var1, String var2, String var3, ResourceBundle var4, String var5, Throwable var6) {
      logger.logrb(var1, var2, var3, var4, var5, var6);
   }

   @Override
   public void removeHandler(Handler var1) {
      logger.removeHandler(var1);
   }

   @Override
   public void severe(String var1) {
      logger.severe(var1);
   }

   @Override
   public void severe(Supplier var1) {
      logger.severe(var1);
   }

   @Override
   public void throwing(String var1, String var2, Throwable var3) {
      logger.throwing(var1, var2, var3);
   }

   public void handleString4(String var1, Object[] var2) {
      this.log(Level.WARNING, String.format(var1, var2));
   }

   @Override
   public void warning(String var1) {
      logger.warning(var1);
   }

   @Override
   public void warning(Supplier var1) {
      logger.warning(var1);
   }
}
