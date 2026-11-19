package litebans.lib.hikari;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.security.AccessControlException;
import java.util.Properties;
import java.util.TreeSet;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import javax.sql.DataSource;

public class HikariConfig implements HikariConfigMXBean {
   private static final HikariLogger hikariLogger = HikariLogger.getHikariLogger(HikariConfig.class);
   private static final char[] charArray = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
   private static final long longValue = TimeUnit.SECONDS.toMillis(30L);
   private static final long longValue2 = TimeUnit.SECONDS.toMillis(5L);
   private static final long longValue3 = Long.getLong("com.zaxxer.hikari.timeoutMs.floor", 250L);
   private static final long longValue4 = TimeUnit.MINUTES.toMillis(10L);
   private static final long longValue5 = TimeUnit.MINUTES.toMillis(30L);
   private static final long longValue6 = 0L;
   private static final int intValue = 10;
   private static boolean flag = false;
   private volatile String string;
   private volatile long longValue7;
   private volatile long longValue8;
   private volatile long longValue9;
   private volatile long longValue10;
   private volatile long longValue11;
   private volatile int intValue2;
   private volatile int intValue3;
   private volatile String string2;
   private volatile String string3;
   private long longValue12;
   private String string4;
   private String string5;
   private String string6;
   private String string7;
   private String string8;
   private String string9;
   private String string10;
   private String string11;
   private String string12;
   private String string13;
   private boolean flag2;
   private boolean flag3;
   private boolean flag4;
   private boolean flag5;
   private boolean flag6;
   private DataSource dataSource;
   private Properties properties = new Properties();
   private ThreadFactory threadFactory;
   private ScheduledExecutorService scheduledExecutorService;
   private Properties properties2 = new Properties();
   private long longValue13;
   private volatile boolean flag7;

   public HikariConfig() {
      this.intValue3 = -1;
      this.intValue2 = -1;
      this.longValue11 = longValue5;
      this.longValue7 = longValue;
      this.longValue8 = longValue2;
      this.longValue9 = longValue4;
      this.longValue12 = 1L;
      this.flag2 = true;
      this.longValue13 = 0L;
      String var1 = System.getProperty("hikaricp.configurationFile");
      if (var1 != null) {
         this.handleString5(var1);
      }
   }

   @Override
   public String getString() {
      return this.string;
   }

   @Override
   public void setString(String var1) {
      this.string = var1;
   }

   @Override
   public long getLongValue7() {
      return this.longValue7;
   }

   @Override
   public void handleLong(long var1) {
      if (var1 == 0L) {
         this.longValue7 = 2147483647L;
      } else {
         if (var1 < longValue3) {
            throw new IllegalArgumentException("connectionTimeout cannot be less than " + longValue3 + "ms");
         }

         this.longValue7 = var1;
      }
   }

   @Override
   public long getLongValue9() {
      return this.longValue9;
   }

   @Override
   public void handleLong3(long var1) {
      if (var1 < 0L) {
         throw new IllegalArgumentException("idleTimeout cannot be negative");
      } else {
         this.longValue9 = var1;
      }
   }

   @Override
   public long getLongValue10() {
      return this.longValue10;
   }

   @Override
   public void setLongValue10(long var1) {
      this.longValue10 = var1;
   }

   @Override
   public long getLongValue11() {
      return this.longValue11;
   }

   @Override
   public void setLongValue11(long var1) {
      this.longValue11 = var1;
   }

   @Override
   public int getIntValue2() {
      return this.intValue2;
   }

   @Override
   public void handleInt2(int var1) {
      if (var1 < 1) {
         throw new IllegalArgumentException("maxPoolSize cannot be less than 1");
      } else {
         this.intValue2 = var1;
      }
   }

   @Override
   public int getIntValue3() {
      return this.intValue3;
   }

   @Override
   public void handleInt(int var1) {
      if (var1 < 0) {
         throw new IllegalArgumentException("minimumIdle cannot be negative");
      } else {
         this.intValue3 = var1;
      }
   }

   public String getString3() {
      return this.string3;
   }

   @Override
   public void setString3(String var1) {
      this.string3 = var1;
   }

   public String getString2() {
      return this.string2;
   }

   @Override
   public void setString2(String var1) {
      this.string2 = var1;
   }

   @Override
   public long getLongValue8() {
      return this.longValue8;
   }

   @Override
   public void handleLong2(long var1) {
      if (var1 < longValue3) {
         throw new IllegalArgumentException("validationTimeout cannot be less than " + longValue3 + "ms");
      } else {
         this.longValue8 = var1;
      }
   }

   public String getString5() {
      return this.string5;
   }

   public void handleString(String var1) {
      this.run4();
      this.string5 = var1;
   }

   public String getString4() {
      return this.string4;
   }

   public void handleString2(String var1) {
      this.run4();
      this.string4 = var1;
   }

   public DataSource getDataSource() {
      return this.dataSource;
   }

   public void handleDataSource(DataSource var1) {
      this.run4();
      this.dataSource = var1;
   }

   public String getString6() {
      return this.string6;
   }

   public void handleString(String var1, Object var2) {
      this.run4();
      this.properties.put(var1, var2);
   }

   public String getString7() {
      return this.string7;
   }

   public Properties getProperties() {
      return this.properties;
   }

   public String getString8() {
      return this.string8;
   }

   public String getString10() {
      return this.string10;
   }

   public void handleString3(String var1) {
      this.run4();
      this.string10 = var1;
   }

   public boolean isFlag2() {
      return this.flag2;
   }

   public boolean isFlag6() {
      return this.flag6;
   }

   public long getLongValue12() {
      return this.longValue12;
   }

   public void handleLong4(long var1) {
      this.run4();
      this.longValue12 = var1;
   }

   public boolean isFlag4() {
      return this.flag4;
   }

   public long getLongValue13() {
      return this.longValue13;
   }

   public void setLongValue13(long var1) {
      this.longValue13 = var1;
   }

   public boolean isFlag3() {
      return this.flag3;
   }

   public boolean isFlag5() {
      return this.flag5;
   }

   @Override
   public String getString11() {
      return this.string11;
   }

   public void handleString4(String var1) {
      this.run4();
      this.string11 = var1;
   }

   public ScheduledExecutorService getScheduledExecutorService() {
      return this.scheduledExecutorService;
   }

   public String getString13() {
      return this.string13;
   }

   public String getString12() {
      return this.string12;
   }

   public String getString9() {
      return this.string9;
   }

   public ThreadFactory getThreadFactory() {
      return this.threadFactory;
   }

   public void handleThreadFactory(ThreadFactory var1) {
      this.run4();
      this.threadFactory = var1;
   }

   public void run() {
      this.flag7 = true;
   }

   public void handleHikariConfig(HikariConfig var1) {
      for (Field var5 : HikariConfig.class.getDeclaredFields()) {
         if (!Modifier.isFinal(var5.getModifiers())) {
            var5.setAccessible(true);

            try {
               var5.set(var1, var5.get(this));
            } catch (Exception var7) {
               throw new RuntimeException("Failed to copy HikariConfig state: " + var7.getMessage(), var7);
            }
         }
      }

      var1.flag7 = false;
   }

   public void run2() {
      if (this.string11 == null) {
         this.string11 = this.getString14();
      } else if (this.flag5 && this.string11.contains(":")) {
         throw new IllegalArgumentException("poolName cannot contain ':' when used with JMX");
      }

      this.string = UtilityElf.getString(this.string);
      this.string4 = UtilityElf.getString(this.string4);
      this.string5 = UtilityElf.getString(this.string5);
      this.string13 = UtilityElf.getString(this.string13);
      this.string6 = UtilityElf.getString(this.string6);
      this.string7 = UtilityElf.getString(this.string7);
      this.string8 = UtilityElf.getString(this.string8);
      this.string10 = UtilityElf.getString(this.string10);
      if (this.dataSource != null) {
         if (this.string6 != null) {
            hikariLogger.handleString4("%s - using dataSource and ignoring dataSourceClassName.", new Object[]{this.string11});
         }
      } else if (this.string6 != null) {
         if (this.string8 != null) {
            hikariLogger.handleString2("%s - cannot use driverClassName and dataSourceClassName together.", new Object[]{this.string11});
            throw new IllegalStateException("cannot use driverClassName and dataSourceClassName together.");
         }

         if (this.string10 != null) {
            hikariLogger.handleString4("{} - using dataSourceClassName and ignoring jdbcUrl.", new Object[]{this.string11});
         }
      } else if (this.string10 == null && this.string7 == null) {
         if (this.string8 != null) {
            hikariLogger.handleString2("{} - jdbcUrl is required with driverClassName.", new Object[]{this.string11});
            throw new IllegalArgumentException("jdbcUrl is required with driverClassName.");
         }

         hikariLogger.handleString2("{} - dataSource or dataSourceClassName or jdbcUrl is required.", new Object[]{this.string11});
         throw new IllegalArgumentException("dataSource or dataSourceClassName or jdbcUrl is required.");
      }

      this.run3();
      if (hikariLogger.check() || flag) {
         this.run5();
      }
   }

   private final void run3() {
      if (this.longValue11 != 0L && this.longValue11 < TimeUnit.SECONDS.toMillis(30L)) {
         hikariLogger.handleString4("%s - maxLifetime is less than 30000ms, setting to default %dms.", new Object[]{this.string11, longValue5});
         this.longValue11 = longValue5;
      }

      if (this.longValue13 != 0L && this.longValue13 < TimeUnit.SECONDS.toMillis(30L)) {
         hikariLogger.handleString4("%s - keepaliveTime is less than 30000ms, disabling it.", new Object[]{this.string11});
         this.longValue13 = 0L;
      }

      if (this.longValue13 != 0L && this.longValue11 != 0L && this.longValue13 >= this.longValue11) {
         hikariLogger.handleString4("%s - keepaliveTime is greater than or equal to maxLifetime, disabling it.", new Object[]{this.string11});
         this.longValue13 = 0L;
      }

      if (this.longValue10 > 0L && !flag && (this.longValue10 < TimeUnit.SECONDS.toMillis(2L) || this.longValue10 > this.longValue11 && this.longValue11 > 0L)) {
         hikariLogger.handleString4("%s - leakDetectionThreshold is less than 2000ms or more than maxLifetime, disabling it.", new Object[]{this.string11});
         this.longValue10 = 0L;
      }

      if (this.longValue7 < longValue3) {
         hikariLogger.handleString4("%s - connectionTimeout is less than %dms, setting to %dms.", new Object[]{this.string11, longValue3, longValue});
         this.longValue7 = longValue;
      }

      if (this.longValue8 < longValue3) {
         hikariLogger.handleString4("%s - validationTimeout is less than %dms, setting to %dms.", new Object[]{this.string11, longValue3, longValue2});
         this.longValue8 = longValue2;
      }

      if (this.intValue2 < 1) {
         this.intValue2 = 10;
      }

      if (this.intValue3 < 0 || this.intValue3 > this.intValue2) {
         this.intValue3 = this.intValue2;
      }

      if (this.longValue9 + TimeUnit.SECONDS.toMillis(1L) > this.longValue11 && this.longValue11 > 0L && this.intValue3 < this.intValue2) {
         hikariLogger.handleString4("%s - idleTimeout is close to or more than maxLifetime, disabling it.", new Object[]{this.string11});
         this.longValue9 = 0L;
      } else if (this.longValue9 != 0L && this.longValue9 < TimeUnit.SECONDS.toMillis(10L) && this.intValue3 < this.intValue2) {
         hikariLogger.handleString4("%s - idleTimeout is less than 10000ms, setting to default {}ms.", new Object[]{this.string11, longValue4});
         this.longValue9 = longValue4;
      } else if (this.longValue9 != longValue4 && this.longValue9 != 0L && this.intValue3 == this.intValue2) {
         hikariLogger.handleString4(
            "%s - idleTimeout has been set but has no effect because the pool is operating as a fixed size pool.", new Object[]{this.string11}
         );
      }
   }

   private final void run4() {
      if (this.flag7) {
         throw new IllegalStateException("The configuration of the pool is sealed once started. Use HikariConfigMXBean for runtime changes.");
      }
   }

   private final void run5() {
      hikariLogger.handleString("%s - configuration:", new Object[]{this.string11});

      for (Object var3o : new TreeSet(PropertyElf.getSet(HikariConfig.class))) {
         String var3 = (String) var3o;
         try {
            Object var4 = PropertyElf.getObject(var3, this);
            if ("dataSourceProperties".equals(var3)) {
               Properties var5 = PropertyElf.getProperties(this.properties);
               var5.setProperty("password", "<masked>");
               var4 = var5;
            }

            if ("initializationFailTimeout".equals(var3) && this.longValue12 == Long.MAX_VALUE) {
               var4 = "infinite";
            } else if ("transactionIsolation".equals(var3) && this.string13 == null) {
               var4 = "default";
            } else if (var3.matches("scheduledExecutorService|threadFactory") && var4 == null) {
               var4 = "internal";
            } else if (var3.contains("jdbcUrl") && var4 instanceof String) {
               var4 = ((String)var4).replaceAll("([?&;]password=)[^&#;]*(.*)", "$1<masked>$2");
            } else if (var3.contains("password")) {
               var4 = "<masked>";
            } else if (var4 instanceof String) {
               var4 = "\"" + var4 + "\"";
            } else if (var4 == null) {
               var4 = "none";
            }

            hikariLogger.handleString("{}{}", new Object[]{var3 + "................................................".substring(0, 32), var4});
         } catch (Exception var6) {
         }
      }
   }

   private final void handleString5(String var1) {
      File var2 = new File(var1);

      try (java.io.InputStream var3 = var2.isFile() ? new FileInputStream(var2) : this.getClass().getResourceAsStream(var1)) {
         if (var3 == null) {
            throw new IllegalArgumentException("Cannot find property file: " + var1);
         } else {
            Properties var5 = new Properties();
            var5.load((InputStream)var3);
            PropertyElf.handleObject(this, var5);
         }
      } catch (IOException var16) {
         throw new RuntimeException("Failed to read property file", var16);
      }
   }

   private final String getString14() {
      try {
         synchronized (System.getProperties()) {
            String var8 = String.valueOf(Integer.getInteger("com.zaxxer.hikari.pool_number", 0) + 1);
            System.setProperty("com.zaxxer.hikari.pool_number", var8);
            return "HikariPool-" + var8;
         }
      } catch (AccessControlException var7) {
         ThreadLocalRandom var3 = ThreadLocalRandom.current();
         StringBuilder var4 = new StringBuilder("HikariPool-");

         for (int var5 = 0; var5 < 4; var5++) {
            var4.append(charArray[var3.nextInt(62)]);
         }

         hikariLogger.handleString3("assigned random pool name '{}' (security manager prevented access to system properties)", new Object[]{var4});
         return var4.toString();
      }
   }
}
