package litebans.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public final class ConfigNode {
   private static final char charValue = '.';
   public final Map map = new LinkedHashMap();
   private final ConfigNode configNode;

   public ConfigNode() {
      this(null);
   }

   public ConfigNode(ConfigNode var1) {
      this(new LinkedHashMap(), var1);
   }

   public ConfigNode(Map var1, ConfigNode var2) {
      this.configNode = var2;

      for (Object var4o : var1.entrySet()) {
         Entry var4 = (Entry) var4o;
         String var5 = var4.getKey() == null ? "null" : var4.getKey().toString();
         if (var4.getValue() instanceof Map) {
            this.map.put(var5, new ConfigNode((Map)var4.getValue(), var2 == null ? null : var2.getConfigNode2(var5)));
         } else {
            this.map.put(var5, var4.getValue());
         }
      }
   }

   private final ConfigNode getConfigNode(String var1) {
      int var2 = var1.indexOf(46);
      if (var2 == -1) {
         return this;
      } else {
         String var3 = var1.substring(0, var2);
         Object var4 = this.map.get(var3);
         if (var4 == null) {
            var4 = new ConfigNode(this.configNode == null ? null : this.configNode.getConfigNode2(var3));
            this.map.put(var3, var4);
         }

         return (ConfigNode)var4;
      }
   }

   private final String getString(String var1) {
      int var2 = var1.indexOf(46);
      return var2 == -1 ? var1 : var1.substring(var2 + 1);
   }

   public Object getObject(String var1, Object var2) {
      ConfigNode var3 = this.getConfigNode(var1);
      Object var4;
      if (var3 == this) {
         var4 = this.map.get(var1);
      } else {
         var4 = var3.getObject(this.getString(var1), var2);
      }

      if (var4 == null && var2 instanceof ConfigNode) {
         this.map.put(var1, var2);
      }

      return var4 != null ? var4 : var2;
   }

   public Object getObject(String var1) {
      return this.getObject(var1, this.getObject2(var1));
   }

   public Object getObject2(String var1) {
      return this.configNode == null ? null : this.configNode.getObject(var1);
   }

   public void handleString(String var1, Object var2) {
      if (var2 instanceof Map) {
         var2 = new ConfigNode((Map)var2, this.configNode == null ? null : this.configNode.getConfigNode2(var1));
      }

      ConfigNode var3 = this.getConfigNode(var1);
      if (var3 == this) {
         if (var2 == null) {
            this.map.remove(var1);
         } else {
            this.map.put(var1, var2);
         }
      } else {
         var3.handleString(this.getString(var1), var2);
      }
   }

   public ConfigNode getConfigNode2(String var1) {
      Object var2 = this.getObject2(var1);
      return (ConfigNode)this.getObject(
         var1, var2 instanceof ConfigNode ? var2 : new ConfigNode(this.configNode == null ? null : this.configNode.getConfigNode2(var1))
      );
   }

   public Collection getCollection() {
      return new LinkedHashSet(this.map.keySet());
   }

   public int getInt(String var1, int var2) {
      Object var3 = this.getObject(var1, var2);
      return var3 instanceof Number ? ((Number)var3).intValue() : var2;
   }

   public long getLong(String var1, long var2) {
      Object var4 = this.getObject(var1, var2);
      return var4 instanceof Number ? ((Number)var4).longValue() : var2;
   }

   public double getDouble(String var1) {
      Object var2 = this.getObject2(var1);
      return this.getDouble(var1, var2 instanceof Number ? ((Number)var2).doubleValue() : 0.0);
   }

   public double getDouble(String var1, double var2) {
      Object var4 = this.getObject(var1, var2);
      return var4 instanceof Number ? ((Number)var4).doubleValue() : var2;
   }

   public boolean checkString(String var1) {
      Object var2 = this.getObject2(var1);
      return this.checkString(var1, var2 instanceof Boolean ? (Boolean)var2 : false);
   }

   public boolean checkString(String var1, boolean var2) {
      Object var3 = this.getObject(var1, var2);
      return var3 instanceof Boolean ? (Boolean)var3 : var2;
   }

   public String getString2(String var1) {
      Object var2 = this.getObject2(var1);
      return this.getString(var1, var2 instanceof String ? (String)var2 : "");
   }

   public String getString(String var1, String var2) {
      Object var3 = this.getObject(var1, var2);
      return var3 instanceof String ? (String)var3 : var2;
   }

   public List getList(String var1) {
      List var2 = this.getList2(var1);
      ArrayList var3 = new ArrayList();

      for (Object var5 : var2) {
         if (var5 instanceof String) {
            var3.add((String)var5);
         }
      }

      return var3;
   }

   public List getList2(String var1) {
      Object var2 = this.getObject2(var1);
      return this.getList(var1, var2 instanceof List ? (List)var2 : Collections.EMPTY_LIST);
   }

   public List getList(String var1, List var2) {
      Object var3 = this.getObject(var1, var2);
      return var3 instanceof List ? (List)var3 : var2;
   }
}
