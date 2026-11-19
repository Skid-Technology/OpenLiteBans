package litebans.lib.json;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class JSONObject extends HashMap implements Map, JSONAware, JSONStreamAware {
   private static final long longValue = -503443796854799292L;

   public static final String getString(Map var0) {
      if (var0 == null) {
         return "null";
      } else {
         StringBuffer var1 = new StringBuffer();
         boolean var2 = true;
         Iterator var3 = var0.entrySet().iterator();
         var1.append('{');

         while (var3.hasNext()) {
            if (var2) {
               var2 = false;
            } else {
               var1.append(',');
            }

            Entry var4 = (Entry)var3.next();
            getString(String.valueOf(var4.getKey()), var4.getValue(), var1);
         }

         var1.append('}');
         return var1.toString();
      }
   }

   public String getString() {
      return getString(this);
   }

   private static final String getString(String var0, Object var1, StringBuffer var2) {
      var2.append('"');
      if (var0 == null) {
         var2.append("null");
      } else {
         JSONValue.handleString(var0, var2);
      }

      var2.append('"').append(':');
      var2.append(JSONValue.getString(var1));
      return var2.toString();
   }

   public String toString() {
      return this.getString();
   }
}
