package litebans.platform.bukkit;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import litebans.config.ConfigFile;
import litebans.config.ConfigSection;
import org.bukkit.configuration.ConfigurationSection;

public class BukkitConfigSection implements ConfigSection {
   private final BukkitConfigFile bukkitConfigFile;
   private final ConfigurationSection configurationSection;

   public BukkitConfigSection(BukkitConfigFile var1, ConfigurationSection var2) {
      this.bukkitConfigFile = var1;
      this.configurationSection = var2;
   }

   @Override
   public ConfigSection getConfigSection(String var1) {
      ConfigurationSection var2 = this.configurationSection.getConfigurationSection(var1);
      return var2 == null ? null : new BukkitConfigSection(this.bukkitConfigFile, var2);
   }

   @Override
   public ConfigFile getConfigFile(String var1, Object var2) {
      this.configurationSection.set(var1, var2);
      return this.bukkitConfigFile;
   }

   @Override
   public Collection getCollection(boolean var1) {
      return (Collection)(this.configurationSection == null ? Collections.emptyList() : this.configurationSection.getKeys(var1));
   }

   @Override
   public Object getObject(String var1) {
      return this.configurationSection.get(var1);
   }

   @Override
   public Object getObject(String var1, Object var2) {
      return this.configurationSection.get(var1, var2);
   }

   @Override
   public boolean checkString(String var1) {
      return this.configurationSection.contains(var1);
   }

   @Override
   public boolean checkString2(String var1) {
      return this.configurationSection.getBoolean(var1);
   }

   @Override
   public boolean checkString(String var1, boolean var2) {
      return this.configurationSection.getBoolean(var1, var2);
   }

   @Override
   public double getDouble(String var1) {
      return this.configurationSection.getDouble(var1);
   }

   @Override
   public int getInt(String var1, int var2) {
      return this.configurationSection.getInt(var1, var2);
   }

   @Override
   public long getLong(String var1, long var2) {
      return this.configurationSection.getLong(var1, var2);
   }

   @Override
   public String getString(String var1) {
      return this.configurationSection.getString(var1);
   }

   @Override
   public String getString(String var1, String var2) {
      return this.configurationSection.getString(var1, var2);
   }

   @Override
   public List getList(String var1) {
      return this.configurationSection.getStringList(var1);
   }

   @Override
   public boolean checkString3(String var1) {
      return this.configurationSection.contains(var1);
   }
}
