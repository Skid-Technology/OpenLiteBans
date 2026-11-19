package litebans.config;

import com.google.common.base.Charsets;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.Collection;
import java.util.List;
import litebans.lib.kotlin.jvm.internal.MetadataAnnotation;
import litebans.platform.LiteBansPlatform;
import org.jetbrains.annotations.Nullable;

@MetadataAnnotation(
   a = 1
)
public class GenericConfigFile extends ConfigFile {
   private ConfigNode configNode;
   private final ConfigFormat configFormat;

   public GenericConfigFile(LiteBansPlatform var1) {
      super(var1);
      this.configFormat = this.getConfigFormat();
   }

   public GenericConfigFile(LiteBansPlatform var1, ConfigFormat var2) {
      super(var1);
      this.configFormat = var2;
   }

   @Override
   public boolean checkString(String var1) {
      return this.configNode.getObject(var1) != null;
   }

   @Override
   public Object getObject(String var1) {
      return this.configNode.getObject(var1);
   }

   @Override
   public Object getObject(String var1, Object var2) {
      return this.configNode.getObject(var1, var2);
   }

   @Override
   public boolean checkString2(String var1) {
      return this.configNode.checkString(var1);
   }

   @Override
   public boolean checkString(String var1, boolean var2) {
      Object var3 = this.getObject(var1, var2);
      return var3 instanceof Boolean ? (Boolean)var3 : var2;
   }

   @Override
   public double getDouble(String var1) {
      return this.configNode.getDouble(var1);
   }

   @Override
   public int getInt(String var1, int var2) {
      return this.configNode.getInt(var1, var2);
   }

   @Override
   public Collection getCollection(boolean var1) {
      return this.configNode.getCollection();
   }

   @Override
   public long getLong(String var1, long var2) {
      return this.configNode.getLong(var1, var2);
   }

   public ConfigFormat getConfigFormat() {
      ConfigFormat var1 = this.getFile() != null && !this.getFile().getName().endsWith(".yml") ? getConfigFormat2() : getConfigFormat3();
      if (var1 == null) {
         ConfigFormat var2 = getConfigFormat2();
         if (var2 == null) {
            throw new AssertionError("No configuration provider (yaml/json)");
         } else {
            return var2;
         }
      } else {
         return var1;
      }
   }

   public static ConfigFormat getConfigFormat2() {
      return ConfigFormat.getConfigFormat(JsonConfigFormat.class);
   }

   public static ConfigFormat getConfigFormat3() {
      return ConfigFormat.getConfigFormat(YamlConfigFormat.class);
   }

   @Override
   public String getString(String var1) {
      return this.configNode.getString2(var1);
   }

   @Override
   public String getString(String var1, String var2) {
      try {
         return this.configNode.getString(var1, var2);
      } catch (ClassCastException var4) {
         this.liteBansPlatform.getLogger().severe("CCE getString(\"" + var1 + "\")");
         throw var4;
      }
   }

   @Override
   public List getList(String var1) {
      return this.configNode.getList(var1);
   }

   @Override
   public boolean checkString3(String var1) {
      return this.checkString(var1);
   }

   @Override
   public ConfigFile getConfigFile(File var1) {
      this.setThrowable(null);
      File var2 = var1.getParentFile();
      if (var1.getName().endsWith(".yml") && this.configFormat == getConfigFormat2()) {
         return this;
      } else {
         this.setFile(var1);

         try {
            if (var2 != null && !var2.exists() && !var2.mkdirs()) {
               throw new IOException("Could not create folder: " + var2.getAbsolutePath());
            }

            if (!var1.exists() && !var1.createNewFile()) {
               throw new IOException("Could not create file: " + var1.getAbsolutePath());
            }

            this.getConfigFile(new FileInputStream(var1));
         } catch (Exception var4) {
            this.setThrowable(var4);
            this.run();
         }

         return this;
      }
   }

   private final void run() {
      this.configNode = null;
   }

   @Override
   public ConfigFile getConfigFile(InputStream var1) {
      this.setThrowable(null);

      try {
         this.getConfigFile3();
         this.configNode = this.configFormat.getConfigNode(new InputStreamReader(var1, Charsets.UTF_8));
      } catch (Exception var3) {
         this.setThrowable(var3);
         this.run();
      }

      return this;
   }

   @Override
   public ConfigFile getConfigFile() {
      this.getConfigFile(this.getFile());
      return this;
   }

   @Override
   public ConfigFile getConfigFile2() {
      return this.getConfigFile2(this.getFile());
   }

   @Override
   public ConfigFile getConfigFile2(File var1) {
      this.setThrowable(null);

      try {
         StringWriter var2 = new StringWriter();
         this.configFormat.handleConfigNode(this.configNode, var2);
         String var3 = var2.toString();
         if (var3.isEmpty()) {
            return this;
         }

         this.handleFile(var1, var3);
      } catch (Exception var4) {
         this.setThrowable(var4);
      }

      return this;
   }

   @Nullable
   @Override
   public ConfigSection getConfigSection(String var1) {
      ConfigNode var2;
      try {
         var2 = this.configNode.getConfigNode2(var1);
      } catch (ClassCastException var4) {
         this.handleString(var1, var4);
         return null;
      }

      return var2 == null ? null : new GenericConfigSection(this, var2);
   }

   public void handleString(String var1, ClassCastException var2) {
      if (var2.getMessage() != null && var2.getMessage().length() >= 8) {
         String var3 = var2.getMessage().substring(6);
         String var4 = var3.substring(0, var3.indexOf(32)).replace("java.lang.", "");
         this.liteBansPlatform
            .getLogger()
            .warning(
               "Expected a config section at \""
                  + var1
                  + "\" but found a "
                  + var4
                  + " (in "
                  + this.getFile().getName()
                  + "). This section will be treated as empty. Define it as \""
                  + var1
                  + ": {}\" if you want an empty node list."
            );
      }
   }

   @Override
   public ConfigFile getConfigFile(String var1, Object var2) {
      this.configNode.handleString(var1, var2);
      return this;
   }

   @Override
   public ConfigFile getConfigFile3() {
      this.run();
      this.setThrowable(null);
      return this;
   }
}
