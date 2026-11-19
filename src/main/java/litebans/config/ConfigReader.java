package litebans.config;

import java.util.Collection;
import java.util.List;

public interface ConfigReader {
   boolean checkString(String var1);

   Object getObject(String var1);

   Object getObject(String var1, Object var2);

   boolean checkString2(String var1);

   boolean checkString(String var1, boolean var2);

   double getDouble(String var1);

   int getInt(String var1, int var2);

   Collection getCollection(boolean var1);

   long getLong(String var1, long var2);

   String getString(String var1);

   String getString(String var1, String var2);

   List getList(String var1);

   boolean checkString3(String var1);

   ConfigFile getConfigFile(String var1, Object var2);
}
