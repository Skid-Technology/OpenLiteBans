package litebans.config;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class GenericConfigSection implements ConfigSection {
   private final GenericConfigFile genericConfigFile;
   private final ConfigNode configNode;

   public GenericConfigSection(GenericConfigFile var1, ConfigNode var2) {
      this.genericConfigFile = var1;
      this.configNode = var2;
   }

   @Override
   public Collection getCollection(boolean var1) {
      return (Collection)(this.configNode == null ? Collections.emptyList() : this.configNode.getCollection());
   }

   @Override
   public ConfigSection getConfigSection(String var1) {
      ConfigNode var2;
      try {
         var2 = this.configNode.getConfigNode2(var1);
      } catch (ClassCastException var4) {
         this.genericConfigFile.handleString(var1, var4);
         return null;
      }

      return var2 == null ? null : new GenericConfigSection(this.genericConfigFile, var2);
   }

   @Override
   public ConfigFile getConfigFile(String var1, Object var2) {
      this.configNode.handleString(var1, var2);
      return this.genericConfigFile;
   }

   @Override
   public Object getObject(String var1, Object var2) {
      return this.configNode.getObject(var1, var2);
   }

   @Override
   public Object getObject(String var1) {
      return this.configNode.getObject(var1);
   }

   @Override
   public int getInt(String var1, int var2) {
      return this.configNode.getInt(var1, var2);
   }

   @Override
   public long getLong(String var1, long var2) {
      return this.configNode.getLong(var1, var2);
   }

   @Override
   public double getDouble(String var1) {
      return this.configNode.getDouble(var1);
   }

   @Override
   public boolean checkString(String var1) {
      return this.configNode.checkString(var1);
   }

   @Override
   public boolean checkString2(String var1) {
      return this.configNode.checkString(var1);
   }

   @Override
   public boolean checkString(String var1, boolean var2) {
      return this.configNode.checkString(var1, var2);
   }

   @Override
   public String getString(String var1) {
      return this.configNode.getString2(var1);
   }

   @Override
   public String getString(String var1, String var2) {
      return this.configNode.getString(var1, var2);
   }

   @Override
   public List getList(String var1) {
      return this.configNode.getList(var1);
   }

   @Override
   public boolean checkString3(String var1) {
      return this.configNode.map.containsKey(var1);
   }
}
