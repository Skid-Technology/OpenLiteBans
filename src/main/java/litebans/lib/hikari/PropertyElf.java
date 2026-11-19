package litebans.lib.hikari;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class PropertyElf {
   private static final Pattern pattern = Pattern.compile("(get|is)[A-Z].+");

   private PropertyElf() {
   }

   public static void handleObject(Object var0, Properties var1) {
      if (var0 != null && var1 != null) {
         List var2 = Arrays.asList(var0.getClass().getMethods());
         var1.forEach((k, v) -> handleObject(var0, var2, k, v));
      }
   }

   public static Set getSet(Class var0) {
      HashSet var1 = new HashSet();
      Matcher var2 = pattern.matcher("");

      for (Method var6 : var0.getMethods()) {
         String var7 = var6.getName();
         if (var6.getParameterTypes().length == 0 && var2.reset(var7).matches()) {
            var7 = var7.replaceFirst("(get|is)", "");

            try {
               if (var0.getMethod("set" + var7, var6.getReturnType()) != null) {
                  var7 = Character.toLowerCase(var7.charAt(0)) + var7.substring(1);
                  var1.add(var7);
               }
            } catch (Exception var8) {
            }
         }
      }

      return var1;
   }

   public static Object getObject(String var0, Object var1) {
      try {
         String var2 = "get" + var0.substring(0, 1).toUpperCase(Locale.ENGLISH) + var0.substring(1);
         Method var7 = var1.getClass().getMethod(var2);
         return var7.invoke(var1);
      } catch (Exception var6) {
         try {
            String var3 = "is" + var0.substring(0, 1).toUpperCase(Locale.ENGLISH) + var0.substring(1);
            Method var4 = var1.getClass().getMethod(var3);
            return var4.invoke(var1);
         } catch (Exception var5) {
            return null;
         }
      }
   }

   public static Properties getProperties(Properties var0) {
      Properties var1 = new Properties();
      var0.forEach((k, v) -> handleProperties(var1, k, v));
      return var1;
   }

   private static final void handleObject(Object var0, String var1, Object var2, List var3) {
      HikariLogger var4 = HikariLogger.getHikariLogger(PropertyElf.class);
      String var5 = "set" + var1.substring(0, 1).toUpperCase(Locale.ENGLISH) + var1.substring(1);
      Method var6 = (Method) var3.stream().filter(m -> checkString2(var5, (Method) m)).findFirst().orElse(null);
      if (var6 == null) {
         String var7 = "set" + var1.toUpperCase(Locale.ENGLISH);
         var6 = (Method) var3.stream().filter(m -> checkString(var7, (Method) m)).findFirst().orElse(null);
      }

      if (var6 == null) {
         var4.handleString2("Property %s does not exist on target %s", new Object[]{var1, var0.getClass().toString()});
         throw new RuntimeException(String.format("Property %s does not exist on target %s", var1, var0.getClass()));
      } else {
         try {
            Class var10 = var6.getParameterTypes()[0];
            if (var10 == int.class) {
               var6.invoke(var0, Integer.parseInt(var2.toString()));
            } else if (var10 == long.class) {
               var6.invoke(var0, Long.parseLong(var2.toString()));
            } else if (var10 == short.class) {
               var6.invoke(var0, Short.parseShort(var2.toString()));
            } else if (var10 == boolean.class || var10 == Boolean.class) {
               var6.invoke(var0, Boolean.parseBoolean(var2.toString()));
            } else if (var10 == String.class) {
               var6.invoke(var0, var2.toString());
            } else {
               try {
                  var4.handleString("Try to create a new instance of \"{}\"", new Object[]{var2});
                  var6.invoke(var0, Class.forName(var2.toString()).getDeclaredConstructor().newInstance());
               } catch (ClassNotFoundException | InstantiationException var8) {
                  var4.handleString("Class \"{}\" not found or could not instantiate it (Default constructor)", new Object[]{var2});
                  var6.invoke(var0, var2);
               }
            }
         } catch (Exception var9) {
            var4.handleString2("Failed to set property %s on target %s", new Object[]{var1, var0.getClass().toString(), var9});
            throw new RuntimeException(var9);
         }
      }
   }

   private static final boolean checkString(String var0, Method var1) {
      return var1.getName().equals(var0) && var1.getParameterCount() == 1;
   }

   private static final boolean checkString2(String var0, Method var1) {
      return var1.getName().equals(var0) && var1.getParameterCount() == 1;
   }

   private static final void handleProperties(Properties var0, Object var1, Object var2) {
      var0.setProperty(var1.toString(), var2.toString());
   }

   private static final void handleObject(Object var0, List var1, Object var2, Object var3) {
      if (var0 instanceof HikariConfig && var2.toString().startsWith("dataSource.")) {
         ((HikariConfig)var0).handleString(var2.toString().substring("dataSource.".length()), var3);
      } else {
         handleObject(var0, var2.toString(), var3, var1);
      }
   }
}
