package litebans.config;

import com.google.common.base.Charsets;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import litebans.platform.ClassLoadDiagnostics;
import litebans.platform.LiteBansPlatform;
import lombok.NonNull;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class ConfigFile implements ConfigSection, Closeable {
   public LiteBansPlatform liteBansPlatform;
   private File file;
   private Throwable throwable;
   private String string = "";

   public ConfigFile(LiteBansPlatform var1) {
      this.liteBansPlatform = var1;
   }

   public static InputStream getInputStream(@NotNull String var0, @Nullable String var1) {
      if (var1 == null) {
         var1 = ClassLoadDiagnostics.getString(ConfigFile.class);
      }

      try {
         JarInputStream var2 = new JarInputStream(Files.newInputStream(new File(var1).toPath()));

         JarEntry var3;
         while ((var3 = var2.getNextJarEntry()) != null) {
            if (var3.getName().endsWith(var0)) {
               return var2;
            }
         }

         var2.close();
         return null;
      } catch (Exception var4) {
         throw new RuntimeException(var4);
      }
   }

   public static String getString2(@NonNull String resource, @Nullable String string) {
      if (resource == null) {
         throw new NullPointerException("resource is marked non-null but is null");
      } else {
         try {
            InputStream var2 = getInputStream(resource, string);

            String var3;
            try {
               var3 = getString(var2);
            } catch (Throwable var6) {
               if (var2 != null) {
                  try {
                     var2.close();
                  } catch (Throwable var5) {
                     var6.addSuppressed(var5);
                  }
               }

               throw var6;
            }

            if (var2 != null) {
               var2.close();
            }

            return var3;
         } catch (IOException var7) {
            var7.printStackTrace();
            return "#Header 404" + System.lineSeparator();
         }
      }
   }

   public static String getString(@Nullable InputStream var0) {
      String var1 = System.lineSeparator();
      if (var0 == null) {
         return "";
      } else {
         BufferedReader var2 = new BufferedReader(new InputStreamReader(var0), 1024);

         try {
            StringBuilder var3 = new StringBuilder();

            while (true) {
               String var4 = var2.readLine();
               if (var4 == null || !var4.startsWith("#")) {
                  return var3.toString();
               }

               var3.append(var4).append(var1);
            }
         } catch (IOException var5) {
            throw new RuntimeException(var5);
         } finally {
            if (var2 != null) {
               try {
                  var2.close();
               } catch (IOException var6) {
               }
            }
         }
      }
   }

   public static void handleFile(@NotNull File var0, @NotNull String var1, @Nullable String var2, boolean var3) {
      try {
         InputStream var4 = getInputStream(var1, var2);

         label132: {
            try {
               if (var4 == null) {
                  break label132;
               }

               File var5 = new File(var0, var1);
               int var6 = var1.lastIndexOf(47);
               File var7 = new File(var0, var1.substring(0, var6 >= 0 ? var6 : 0));
               if (!var7.exists() && !var7.mkdirs()) {
                  throw new IOException("mkdir failed: " + var7.getAbsolutePath());
               }

               if (!var5.exists() || var5.length() == 0L || var3) {
                  FileOutputStream var8 = new FileOutputStream(var5);

                  try {
                     byte[] var9 = new byte[8192];

                     int var10;
                     while ((var10 = var4.read(var9)) > 0) {
                        var8.write(var9, 0, var10);
                     }
                  } finally {
                     if (var8 != null) {
                        var8.close();
                     }
                  }
               }
            } catch (Throwable var18) {
               if (var4 != null) {
                  try {
                     var4.close();
                  } catch (Throwable var16) {
                     var18.addSuppressed(var16);
                  }
               }

               throw var18;
            }

            if (var4 != null) {
               var4.close();
            }

            return;
         }

         if (var4 != null) {
            var4.close();
         }
      } catch (Throwable var19) {
         throw new RuntimeException(var19);
      }
   }

   public static String getString2(String var0) {
      String var1 = System.lineSeparator();
      StringBuilder var2 = new StringBuilder();
      String[] var3 = var0.split("\\r?\\n");

      for (String var7 : var3) {
         if (!var7.startsWith("#")) {
            var2.append(var7).append(var1);
         }
      }

      return var2.toString();
   }

   public ConfigFile getConfigFile4() {
      if (this.throwable != null) {
         throw new RuntimeException(this.throwable);
      } else {
         return this;
      }
   }

   @Override
   public boolean checkString(String var1) {
      return this.getObject(var1) != null;
   }

   @Override
   public boolean checkString3(String var1) {
      return this.getObject(var1) != null;
   }

   public abstract ConfigFile getConfigFile(File var1);

   public abstract ConfigFile getConfigFile(InputStream var1);

   public abstract ConfigFile getConfigFile();

   public abstract ConfigFile getConfigFile2();

   public abstract ConfigFile getConfigFile2(File var1);

   public ConfigFile getConfigFile(String var1) {
      this.string = var1;
      return this;
   }

   public abstract ConfigFile getConfigFile3();

   @Override
   public void close() {
      this.getConfigFile3();
   }

   public void handleFile(File var1, String var2) {
      try {
         OutputStreamWriter var3 = new OutputStreamWriter(new FileOutputStream(var1), Charsets.UTF_8);

         try {
            var3.write(this.getString() + getString2(var2));
            var3.flush();
         } finally {
            if (var3 != null) {
               var3.close();
            }
         }
      } catch (IOException var5) {
         throw new RuntimeException(var5);
      }
   }

   public File getFile() {
      return this.file;
   }

   public void setFile(File var1) {
      this.file = var1;
   }

   public Throwable getThrowable() {
      return this.throwable;
   }

   public void setThrowable(Throwable var1) {
      this.throwable = var1;
   }

   public String getString() {
      return this.string;
   }
}
