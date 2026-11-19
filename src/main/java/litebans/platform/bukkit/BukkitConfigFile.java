package litebans.platform.bukkit;

import com.google.common.base.Charsets;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Collection;
import java.util.List;
import litebans.config.ConfigFile;
import litebans.config.ConfigSection;
import litebans.lib.kotlin.jvm.internal.MetadataAnnotation;
import litebans.platform.LiteBansPlatform;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.Nullable;

@MetadataAnnotation(
   a = 0
)
public class BukkitConfigFile extends ConfigFile {
   private FileConfiguration fileConfiguration;

   public BukkitConfigFile(LiteBansPlatform var1) {
      super(var1);
   }

   @Override
   public ConfigFile getConfigFile(File var1) {
      try {
         this.setThrowable(null);
         this.setFile(var1);
         File var2 = var1.getParentFile();
         if (var2 != null && !var2.exists() && !var2.mkdirs()) {
            throw new IOException("Could not create folder: " + var2.getAbsolutePath());
         } else if (!var1.exists() && !var1.createNewFile()) {
            throw new IOException("Could not create file: " + var1.getAbsolutePath());
         } else {
            try {
               this.getConfigFile(new FileInputStream(var1));
            } catch (Exception var4) {
               if (!(var4 instanceof FileNotFoundException)) {
                  this.setThrowable(var4);
               }
            }

            return this;
         }
      } catch (Throwable var5) {
         throw new RuntimeException(var5);
      }
   }

   @Override
   public ConfigFile getConfigFile(InputStream var1) {
      this.setThrowable(null);

      try {
         this.fileConfiguration = new YamlConfiguration();
         this.handleReader(new InputStreamReader(var1, Charsets.UTF_8));
      } catch (Exception var3) {
         this.setThrowable(var3);
      }

      return this;
   }

   @Override
   public ConfigFile getConfigFile() {
      try {
         return this.getConfigFile(this.getFile());
      } catch (Throwable var2) {
         throw new RuntimeException(var2);
      }
   }

   @Override
   public ConfigFile getConfigFile2() {
      return this.getConfigFile2(this.getFile());
   }

   @Override
   public ConfigFile getConfigFile2(File var1) {
      this.setThrowable(null);

      try {
         this.handleFile(var1, this.fileConfiguration.saveToString());
      } catch (Exception var3) {
         this.setThrowable(var3);
      }

      return this;
   }

   @Nullable
   @Override
   public ConfigSection getConfigSection(String var1) {
      ConfigurationSection var2 = this.fileConfiguration.getConfigurationSection(var1);
      return var2 == null ? null : new BukkitConfigSection(this, var2);
   }

   @Override
   public Collection getCollection(boolean var1) {
      return this.fileConfiguration.getKeys(var1);
   }

   @Override
   public ConfigFile getConfigFile(String var1, Object var2) {
      this.fileConfiguration.set(var1, var2);
      return this;
   }

   @Override
   public ConfigFile getConfigFile3() {
      try {
         this.fileConfiguration.loadFromString("");
         this.setThrowable(null);
         return this;
      } catch (Throwable var2) {
         throw new RuntimeException(var2);
      }
   }

   private final void handleReader(Reader var1) {
      try {
         StringBuilder var2 = new StringBuilder();
         BufferedReader var3 = var1 instanceof BufferedReader ? (BufferedReader)var1 : new BufferedReader(var1);

         String var4;
         try {
            while ((var4 = var3.readLine()) != null) {
               var2.append(var4);
               var2.append('\n');
            }
         } catch (Throwable var7) {
            if (var3 != null) {
               try {
                  var3.close();
               } catch (Throwable var6) {
                  var7.addSuppressed(var6);
               }
            }

            throw var7;
         }

         if (var3 != null) {
            var3.close();
         }

         this.fileConfiguration.loadFromString(var2.toString());
      } catch (Throwable var8) {
         throw new RuntimeException(var8);
      }
   }

   @Override
   public Object getObject(String var1) {
      return this.fileConfiguration.get(var1);
   }

   @Override
   public Object getObject(String var1, Object var2) {
      return this.fileConfiguration.get(var1, var2);
   }

   @Override
   public boolean checkString2(String var1) {
      return this.fileConfiguration.getBoolean(var1);
   }

   @Override
   public boolean checkString(String var1, boolean var2) {
      return this.fileConfiguration.getBoolean(var1, var2);
   }

   @Override
   public double getDouble(String var1) {
      return this.fileConfiguration.getDouble(var1);
   }

   @Override
   public int getInt(String var1, int var2) {
      return this.fileConfiguration.getInt(var1, var2);
   }

   @Override
   public long getLong(String var1, long var2) {
      return this.fileConfiguration.getLong(var1, var2);
   }

   @Override
   public String getString(String var1) {
      return this.fileConfiguration.getString(var1);
   }

   @Override
   public String getString(String var1, String var2) {
      return this.fileConfiguration.getString(var1, var2);
   }

   @Override
   public List getList(String var1) {
      return this.fileConfiguration.getStringList(var1);
   }

   @Override
   public boolean checkString3(String var1) {
      return this.fileConfiguration.isSet(var1);
   }
}
