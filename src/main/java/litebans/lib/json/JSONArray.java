package litebans.lib.json;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JSONArray extends ArrayList implements List, JSONAware, JSONStreamAware {
   private static final long longValue = 3957988303675231981L;

   public static final String getString(List var0) {
      if (var0 == null) {
         return "null";
      } else {
         boolean var1 = true;
         StringBuffer var2 = new StringBuffer();
         Iterator var3 = var0.iterator();
         var2.append('[');

         while (var3.hasNext()) {
            if (var1) {
               var1 = false;
            } else {
               var2.append(',');
            }

            Object var4 = var3.next();
            if (var4 == null) {
               var2.append("null");
            } else {
               var2.append(JSONValue.getString(var4));
            }
         }

         var2.append(']');
         return var2.toString();
      }
   }

   public String getString() {
      return getString(this);
   }

   public String toString() {
      return this.getString();
   }
}
