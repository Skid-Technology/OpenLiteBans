package litebans.platform.fabric;

import java.io.File;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import litebans.lib.json.JSONObject;
import litebans.lib.json.JSONParser;

public class FabricModInfo {
   private String id;
   private String name;
   private String version;

   public FabricModInfo(String var1) {
      try {
         String var2 = "fabric.mod.json";
         JarInputStream var3 = new JarInputStream(Files.newInputStream(new File(var1).toPath()));

         JarEntry var4;
         while ((var4 = var3.getNextJarEntry()) != null) {
            if (var4.getName().endsWith(var2)) {
               JSONObject var5 = (JSONObject)new JSONParser().getObject(new InputStreamReader(var3));
               this.id = (String)var5.get("id");
               this.name = (String)var5.get("name");
               this.version = (String)var5.get("version");
               break;
            }
         }

         if (this.id == null) {
            throw new AssertionError("Missing " + var2);
         }
      } catch (Throwable var6) {
         throw new RuntimeException(var6);
      }
   }

   @Override
   public String toString() {
      return "FabricMeta(id=" + this.getId() + ", name=" + this.getName() + ", version=" + this.getVersion() + ")";
   }

   public String getId() {
      return this.id;
   }

   public String getName() {
      return this.name;
   }

   public String getVersion() {
      return this.version;
   }
}
