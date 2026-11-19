package litebans.platform;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import litebans.lib.kotlin.jvm.internal.KtAnnotation2;
import litebans.lib.kotlin.jvm.internal.KtAnnotation3;
import litebans.lib.kotlin.jvm.internal.MetadataAnnotation;
import litebans.util.DecimalFormats;

public class ClassLoadDiagnostics {
   private final LiteBansPlatform liteBansPlatform;
   private boolean flag = true;
   private boolean flag2 = true;
   private boolean flag3 = false;
   private boolean flag4 = false;
   private String string;
   private String string2;
   private List list;

   public ClassLoadDiagnostics(LiteBansPlatform var1) {
      this.liteBansPlatform = var1;
   }

   public static String getString(Class var0) {
      URL var1 = null;

      try {
         ProtectionDomain var2 = var0.getProtectionDomain();
         CodeSource var3 = var2.getCodeSource();
         var1 = var3.getLocation();
         return var1.toURI().getPath();
      } catch (URISyntaxException var4) {
         var4.printStackTrace();
         throw new AssertionError("CodeSource.getLocation(): " + var1);
      }
   }

   public static boolean checkString(String var0) {
      try {
         Class.forName(var0);
         return true;
      } catch (ClassNotFoundException var1) {
         return false;
      }
   }

   private static final String getString(Throwable var0) {
      StringWriter var1 = null;

      String var2;
      try {
         var1 = new StringWriter();
         handleThrowable(var0, var1);
         var2 = var1.toString();
      } finally {
         if (var1 != null) {
            try {
               var1.close();
            } catch (IOException var8) {
            }
         }
      }

      return var2;
   }

   private static final void handleThrowable(Throwable var0, StringWriter var1) {
      PrintWriter var2 = null;

      try {
         var2 = new PrintWriter(var1);

         while (var0 != null) {
            var2.println(var0);
            StackTraceElement[] var3 = var0.getStackTrace();

            for (StackTraceElement var7 : var3) {
               var2.println("\tat " + var7);
            }

            var0 = var0.getCause();
            if (var0 != null) {
               var2.print("Caused by: ");
            }
         }
      } finally {
         if (var2 != null) {
            var2.close();
         }
      }
   }

   public void handleString(String var1, boolean var2) {
      for (Object var5o : this.getList(var1, "")) {
         String var5 = (String) var5o;
         try {
            Class.forName(var5);
            if (var2) {
               Thread.sleep(System.currentTimeMillis() & 16L);
            }
         } catch (Throwable var7) {
            this.handleString(var5, var7);
         }
      }
   }

   public List getList(File var1, List var2, String var3) {
      File[] var4 = var1.listFiles();
      if (var4 == null) {
         return var2;
      } else {
         for (File var8 : var4) {
            if (var8.isDirectory() && !var8.getPath().equals(var1.getPath())) {
               String var11 = var3.equals("") ? var8.getName() : var3 + "." + var8.getName();
               this.getList(var8, var2, var11);
            } else if (var8.getName().endsWith(".class")) {
               String var9 = var8.getName();
               var9 = var9.substring(0, var9.length() - 6);
               var2.add(var3 + "." + var9);
            }
         }

         return var2;
      }
   }

   public List getList(String var1, String var2) {
      long var3 = System.nanoTime();
      var2 = var2.replace('.', '/');
      if (this.flag && this.list != null && var2.equals(this.string2) && var1.equals(this.string)) {
         return this.list;
      } else {
         List var5 = new ArrayList();
         File var6 = new File(var1);
         JarInputStream var7 = null;

         try {
            if (!var6.isDirectory()) {
               var7 = new JarInputStream(new FileInputStream(var6));

               JarEntry var8;
               while ((var8 = var7.getNextJarEntry()) != null) {
                  String var9 = var8.getName();
                  if (var9.startsWith(var2) && var9.endsWith(".class")) {
                     var9 = var9.replace('/', '.');
                     var9 = var9.substring(0, var9.length() - 6);
                     var5.add(var9);
                  }
               }
            } else {
               var5 = this.getList(var6, var5, "");
            }
         } catch (Exception var18) {
            var18.printStackTrace();
         } finally {
            if (var7 != null) {
               try {
                  var7.close();
               } catch (IOException var17) {
                  var17.printStackTrace();
               }
            }
         }

         if (this.flag) {
            this.string = var1;
            this.string2 = var2;
            this.list = (List)var5;
         }

         if (this.flag3) {
            System.out.println("1: returned in " + DecimalFormats.getString((double)(System.nanoTime() - var3) / 1000.0 / 1000.0) + " ms");
         }

         return (List)var5;
      }
   }

   public boolean checkString2(String var1) {
      return var1.contains("mixin.");
   }

   public List getList(String var1, String var2, Class var3) {
      long var4 = System.nanoTime();
      ArrayList var6 = new ArrayList();

      for (Object var9o : this.getList(var1, var2)) {
         String var9 = (String) var9o;
         if (!this.checkString2(var9)) {
            try {
               Class var10 = Class.forName(var9);
               int var11 = var10.getModifiers();
               if (var3.isAssignableFrom(var10) && !Modifier.isAbstract(var11) && !Modifier.isInterface(var11) && var10 != var3) {
                  var6.add(var10);
               }
            } catch (Throwable var12) {
               this.handleString(var9, var12);
            }
         }
      }

      if (this.flag3) {
         System.out.println("2: returned in " + DecimalFormats.getString((double)(System.nanoTime() - var4) / 1000.0 / 1000.0) + " ms");
      }

      return var6;
   }

   public List getList(Class var1, Class[] var2, Object[] var3) {
      return this.getList(getString(var1), "", var1, var2, var3);
   }

   public List getList(String var1, String var2, Class var3, Class[] var4, Object[] var5) {
      LiteBansPlatform var6 = this.liteBansPlatform;
      long var7 = System.nanoTime();
      List var9 = this.getList(var1, var2, var3);
      ArrayList var10 = new ArrayList();
      Constructor var11 = null;

      for (Object var13o : var9) {
         Class var13 = (Class) var13o;
         if (var13.getAnnotation(KtAnnotation3.class) == null && (this.flag4 || var13.getAnnotation(KtAnnotation2.class) != null)) {
            MetadataAnnotation var14 = /* cast-fix */(MetadataAnnotation)(var13.getAnnotation(MetadataAnnotation.class));
            if (var14 == null || var6 == null || var14.getByte() == var6.getPlatformId()) {
               try {
                  try {
                     var11 = var13.getDeclaredConstructor(var4);
                     if (!Modifier.isPublic(var11.getModifiers())) {
                        var11.setAccessible(true);
                     }
                  } catch (Throwable var17) {
                     var11 = var13.getConstructor(var4);
                  }

                  if (var11 == null) {
                     throw new NoSuchMethodException("Constructor not found: " + var13.getName() + ' ' + Arrays.toString((Object[])var4));
                  }

                  var10.add(var11.newInstance(var5));
               } catch (Throwable var18) {
                  String var16 = null;
                  if (var11 != null) {
                     var16 = Arrays.toString((Object[])var11.getParameterTypes());
                  }

                  System.err.println(var13.getName() + " does not accept " + var16);
                  var18.printStackTrace();
               }
            }
         }
      }

      if (this.flag3) {
         System.out.println("3: returned in " + DecimalFormats.getString((double)(System.nanoTime() - var7) / 1000.0 / 1000.0) + " ms");
      }

      return var10;
   }

   public void handleString(String var1, Throwable var2) {
      try {
         if (!this.flag2 || !this.checkThrowable(var2)) {
            if (this.liteBansPlatform != null) {
               this.handleString2(var1, var2);
            } else {
               this.handleString3(var1, var2);
            }

            throw var2;
         }
      } catch (Throwable var4) {
         throw new RuntimeException(var4);
      }
   }

   private final void handleString2(String var1, Throwable var2) {
      this.liteBansPlatform.getLogger().severe("**Failed to load: " + var1);
      this.liteBansPlatform.getLogger().severe(getString(var2));
   }

   private final void handleString3(String var1, Throwable var2) {
      System.err.println("**Failed to load: " + var1);
      System.err.println(getString(var2));
   }

   public boolean checkThrowable(Throwable var1) {
      Throwable var2 = var1.getCause();
      var2 = var2 == null ? var1 : var2;
      Throwable var3 = var2.getCause();
      var3 = var3 == null ? var2 : var3;
      String var5 = var2.getMessage();
      String var6 = var3.getMessage();
      if (var5 == null) {
         var5 = "";
      }

      if (var6 == null) {
         var6 = "";
      }

      return var1 instanceof ClassNotFoundException
         || var1 instanceof LinkageError
            && (
               var1.getCause() == null
                  || var2 instanceof ClassNotFoundException
                  || var2 instanceof LinkageError
                     && (var5.contains("NoClassDefFoundError") || var5.contains("ClassNotFoundException") || var5.contains("loader constraint violation:"))
                  || var2 instanceof LinkageError && var3 instanceof ClassNotFoundException
                  || var2 instanceof ClassCastException && var6.contains("kotlin.internal.")
            );
   }

   public void handleBoolean(boolean var1) {
      this.flag = var1;
      if (!var1) {
         this.string = null;
         this.string2 = null;
         this.list = null;
      }
   }
}
