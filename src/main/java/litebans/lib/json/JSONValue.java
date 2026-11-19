package litebans.lib.json;

import java.io.Reader;
import java.io.StringReader;
import java.util.List;
import java.util.Map;

public class JSONValue {
   public static final Object getObject(Reader var0) {
      try {
         JSONParser var1 = new JSONParser();
         return var1.getObject(var0);
      } catch (Exception var2) {
         return null;
      }
   }

   public static final Object getObject(String var0) {
      StringReader var1 = new StringReader(var0);
      return getObject(var1);
   }

   public static final String getString(Object var0) {
      if (var0 == null) {
         return "null";
      } else if (var0 instanceof String) {
         return "\"" + getString((String)var0) + "\"";
      } else if (var0 instanceof Double) {
         return !((Double)var0).isInfinite() && !((Double)var0).isNaN() ? var0.toString() : "null";
      } else if (var0 instanceof Float) {
         return !((Float)var0).isInfinite() && !((Float)var0).isNaN() ? var0.toString() : "null";
      } else if (var0 instanceof Number) {
         return var0.toString();
      } else if (var0 instanceof Boolean) {
         return var0.toString();
      } else if (var0 instanceof JSONAware) {
         return ((JSONAware)var0).getString();
      } else if (var0 instanceof Map) {
         return JSONObject.getString((Map)var0);
      } else {
         return var0 instanceof List ? JSONArray.getString((List)var0) : var0.toString();
      }
   }

   public static final String getString(String var0) {
      if (var0 == null) {
         return null;
      } else {
         StringBuffer var1 = new StringBuffer();
         handleString(var0, var1);
         return var1.toString();
      }
   }

   public static final void handleString(String var0, StringBuffer var1) {
      for (int var2 = 0; var2 < var0.length(); var2++) {
         char var3 = var0.charAt(var2);
         switch (var3) {
            case '\b':
               var1.append("\\b");
               continue;
            case '\t':
               var1.append("\\t");
               continue;
            case '\n':
               var1.append("\\n");
               continue;
            case '\f':
               var1.append("\\f");
               continue;
            case '\r':
               var1.append("\\r");
               continue;
            case '"':
               var1.append("\\\"");
               continue;
            case '/':
               var1.append("\\/");
               continue;
            case '\\':
               var1.append("\\\\");
               continue;
         }

         if (var3 >= 0 && var3 <= 31 || var3 >= 127 && var3 <= 159 || var3 >= 8192 && var3 <= 8447) {
            String var4 = Integer.toHexString(var3);
            var1.append("\\u");

            for (int var5 = 0; var5 < 4 - var4.length(); var5++) {
               var1.append('0');
            }

            var1.append(var4.toUpperCase());
         } else {
            var1.append(var3);
         }
      }
   }
}
