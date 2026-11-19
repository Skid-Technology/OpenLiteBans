package litebans.lib.json;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class JSONParser {
   public static final int intValue = 0;
   public static final int intValue2 = 1;
   public static final int intValue3 = 2;
   public static final int intValue4 = 3;
   public static final int intValue5 = 4;
   public static final int intValue6 = 5;
   public static final int intValue7 = 6;
   public static final int intValue8 = -1;
   private LinkedList linkedList;
   private JsonLexer jsonLexer = new JsonLexer((Reader)null);
   private JsonToken jsonToken = null;
   private int intValue9 = 0;

   private final int getInt(LinkedList var1) {
      if (var1.size() == 0) {
         return -1;
      } else {
         Integer var2 = (Integer)var1.getFirst();
         return var2;
      }
   }

   public void run() {
      this.jsonToken = null;
      this.intValue9 = 0;
      this.linkedList = null;
   }

   public void handleReader(Reader var1) {
      this.jsonLexer.handleReader(var1);
      this.run();
   }

   public int getInt() {
      return this.jsonLexer.getIntValue15();
   }

   public Object getObject(String var1) {
      return this.getObject(var1, (ContainerFactory)null);
   }

   public Object getObject(String var1, ContainerFactory var2) {
      try {
      StringReader var3 = new StringReader(var1);

      try {
         return this.getObject(var3, var2);
      } catch (Exception var5) {
         if (!(var5 instanceof IOException)) { throw litebans.util.Unreachable.rethrow(var5); }
         throw new JsonParseException(-1, 2, var5);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   public Object getObject(Reader var1) {
      return this.getObject(var1, (ContainerFactory)null);
   }

   public Object getObject(Reader var1, ContainerFactory var2) {
      try {
      this.handleReader(var1);
      LinkedList var3 = new LinkedList();
      LinkedList var4 = new LinkedList();

      try {
         do {
            this.run2();
            label64:
            switch (this.intValue9) {
               case -1:
                  throw new JsonParseException(this.getInt(), 1, this.jsonToken);
               case 0:
                  switch (this.jsonToken.intValue9) {
                     case 0:
                        this.intValue9 = 1;
                        var3.addFirst(new Integer(this.intValue9));
                        var4.addFirst(this.jsonToken.object);
                        break label64;
                     case 1:
                        this.intValue9 = 2;
                        var3.addFirst(new Integer(this.intValue9));
                        var4.addFirst(this.getMap(var2));
                        break label64;
                     case 2:
                     default:
                        this.intValue9 = -1;
                        break label64;
                     case 3:
                        this.intValue9 = 3;
                        var3.addFirst(new Integer(this.intValue9));
                        var4.addFirst(this.getList(var2));
                        break label64;
                  }
               case 1:
                  if (this.jsonToken.intValue9 == -1) {
                     return var4.removeFirst();
                  }

                  throw new JsonParseException(this.getInt(), 1, this.jsonToken);
               case 2:
                  switch (this.jsonToken.intValue9) {
                     case 0:
                        if (this.jsonToken.object instanceof String) {
                           String var15 = (String)this.jsonToken.object;
                           var4.addFirst(var15);
                           this.intValue9 = 4;
                           var3.addFirst(new Integer(this.intValue9));
                        } else {
                           this.intValue9 = -1;
                        }
                        break label64;
                     case 2:
                        if (var4.size() > 1) {
                           var3.removeFirst();
                           var4.removeFirst();
                           this.intValue9 = this.getInt(var3);
                        } else {
                           this.intValue9 = 1;
                        }
                     case 5:
                        break label64;
                     default:
                        this.intValue9 = -1;
                        break label64;
                  }
               case 3:
                  switch (this.jsonToken.intValue9) {
                     case 0:
                        List var14 = (List)var4.getFirst();
                        var14.add(this.jsonToken.object);
                        break label64;
                     case 1:
                        List var13 = (List)var4.getFirst();
                        Map var18 = this.getMap(var2);
                        var13.add(var18);
                        this.intValue9 = 2;
                        var3.addFirst(new Integer(this.intValue9));
                        var4.addFirst(var18);
                        break label64;
                     case 2:
                     default:
                        this.intValue9 = -1;
                        break label64;
                     case 3:
                        List var12 = (List)var4.getFirst();
                        List var19 = this.getList(var2);
                        var12.add(var19);
                        this.intValue9 = 3;
                        var3.addFirst(new Integer(this.intValue9));
                        var4.addFirst(var19);
                        break label64;
                     case 4:
                        if (var4.size() > 1) {
                           var3.removeFirst();
                           var4.removeFirst();
                           this.intValue9 = this.getInt(var3);
                        } else {
                           this.intValue9 = 1;
                        }
                     case 5:
                        break label64;
                  }
               case 4:
                  switch (this.jsonToken.intValue9) {
                     case 0:
                        var3.removeFirst();
                        String var11 = (String)var4.removeFirst();
                        Map var17 = (Map)var4.getFirst();
                        var17.put(var11, this.jsonToken.object);
                        this.intValue9 = this.getInt(var3);
                        break;
                     case 1:
                        var3.removeFirst();
                        String var10 = (String)var4.removeFirst();
                        Map var16 = (Map)var4.getFirst();
                        Map var8 = this.getMap(var2);
                        var16.put(var10, var8);
                        this.intValue9 = 2;
                        var3.addFirst(new Integer(this.intValue9));
                        var4.addFirst(var8);
                        break;
                     case 2:
                     case 4:
                     case 5:
                     default:
                        this.intValue9 = -1;
                        break;
                     case 3:
                        var3.removeFirst();
                        String var5 = (String)var4.removeFirst();
                        Map var6 = (Map)var4.getFirst();
                        List var7 = this.getList(var2);
                        var6.put(var5, var7);
                        this.intValue9 = 3;
                        var3.addFirst(new Integer(this.intValue9));
                        var4.addFirst(var7);
                     case 6:
                  }
            }

            if (this.intValue9 == -1) {
               throw new JsonParseException(this.getInt(), 1, this.jsonToken);
            }
         } while (this.jsonToken.intValue9 != -1);
      } catch (Exception var9) {
         if (!(var9 instanceof IOException)) { throw litebans.util.Unreachable.rethrow(var9); }
         throw var9;
      }

      throw new JsonParseException(this.getInt(), 1, this.jsonToken);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   private final void run2() {
      this.jsonToken = this.jsonLexer.getJsonToken();
      if (this.jsonToken == null) {
         this.jsonToken = new JsonToken(-1, null);
      }
   }

   private final Map getMap(ContainerFactory var1) {
      if (var1 == null) {
         return new JSONObject();
      } else {
         Map var2 = var1.getMap();
         return (Map)(var2 == null ? new JSONObject() : var2);
      }
   }

   private final List getList(ContainerFactory var1) {
      if (var1 == null) {
         return new JSONArray();
      } else {
         List var2 = var1.getList();
         return (List)(var2 == null ? new JSONArray() : var2);
      }
   }
}
