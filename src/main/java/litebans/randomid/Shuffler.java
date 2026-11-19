package litebans.randomid;

import java.util.HashMap;
import java.util.Locale;
import litebans.lib.kotlin.text.CharsKt;
import litebans.lib.kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

public final class Shuffler {
   public static final ShufflerCompanion Companion = new ShufflerCompanion(null);
   private final int intValue;
   private final HashMap hashMap = new HashMap();
   private final HashMap hashMap2 = new HashMap();
   public static final int intValue2 = 3;
   public static final int intValue3 = 6;
   public static final long longValue = -1L;

   public Shuffler(long var1) {
      LongBitSet var3 = new LongBitSet(var1);
      this.intValue = 8500 + var3.getInt2(4000);
      this.handleLongBitSet(var3);
   }

   public final int getIntValue() {
      return this.intValue;
   }

   public final HashMap getHashMap() {
      return this.hashMap;
   }

   public final String getString(long var1) {
      try {
         long var3 = var1 + (long)this.intValue;
         String var5 = String.valueOf(var3);
         var5 = this.getString(var5);
         StringBuilder var6 = new StringBuilder();
         StringBuilder var7 = new StringBuilder();
         boolean var8 = true;
         String var9 = var5;
         int var10 = 0;

         for (int var11 = var5.length(); var10 < var11; var10++) {
            char var12 = var9.charAt(var10);
            var7.append(var12);
            if (var7.length() >= 3) {
               if (!var8) {
                  if (StringsKt.checkCharSequence(var7, "000", false, 2, null)) {
                     var6.append("m");
                  } else if (StringsKt.checkCharSequence(var7, "00", false, 2, null)) {
                     var6.append("v");
                  } else if (StringsKt.checkCharSequence(var7, "0", false, 2, null)) {
                     var6.append("z");
                  }
               }

               var6.append(this.getString(var7));
               var7.setLength(0);
               var8 = false;
            }
         }

         if (var7.length() > 0) {
            throw new ShuffleException(null, 1, null);
         } else {
            return var6.toString().toUpperCase(Locale.ROOT);
         }
      } catch (ShuffleException var13) {
         return "error";
      }
   }

   private final String getString(String var1) {
      String var10000;
      switch (var1.length() % 3) {
         case 1:
            var10000 = "00" + var1;
            break;
         case 2:
            var10000 = '0' + var1;
            break;
         default:
            var10000 = var1;
      }

      return var10000;
   }

   private final String getString(int var1, int var2) {
      String var10000;
      switch (var1) {
         case 0:
            var10000 = String.valueOf(var2);
            break;
         case 1:
            var10000 = "" + '0' + var2;
            break;
         case 2:
            var10000 = "00" + var2;
            break;
         case 3:
            var10000 = "000";
            break;
         default:
            throw new ShuffleException(null, 1, null);
      }

      return var10000;
   }

   public final long getLong(@NotNull String var1) {
      try {
         String var2 = var1.toLowerCase(Locale.ROOT);
         StringBuilder var3 = new StringBuilder();
         StringBuilder var4 = new StringBuilder();
         byte var5 = 0;
         int var6 = 0;

         for (int var7 = var2.length(); var6 < var7; var6++) {
            char var8 = var2.charAt(var6);
            switch (var8) {
               case 'm':
                  var5 = 3;
                  break;
               case 'v':
                  var5 = 2;
                  break;
               case 'z':
                  var5 = 1;
                  break;
               default:
                  var4.append(var8);
            }

            if (var4.length() >= 3) {
               var3.append(this.getString(var4, var5));
               var4.setLength(0);
               var5 = 0;
            }
         }

         if (var4.length() > 0) {
            throw new ShuffleException(null, 1, null);
         } else {
            return Long.parseLong(var3.toString()) - (long)this.intValue;
         }
      } catch (ShuffleException var9) {
         return -1L;
      }
   }

   private final void handleLongBitSet(LongBitSet var1) {
      for (int var2 = 0; var2 < 1000; var2++) {
         String var3 = this.getString(var1);

         while (var3.length() != 3 || this.hashMap2.containsKey(var3)) {
            var3 = this.getString(var1);
         }

         Integer var4 = var2;
         this.hashMap.put(var4, var3);
         var4 = var2;
         this.hashMap2.put(var3, var4);
      }
   }

   private final String getString(LongBitSet var1) {
      return Long.toString(var1.getLong() & 4095L, CharsKt.getInt(16));
   }

   private final String getString(StringBuilder var1) {
      String var10000 = (String)this.hashMap.get(Integer.parseInt(var1.toString()));
      if (var10000 == null) {
         throw new ShuffleException("No shuffle input for " + var1);
      } else {
         return var10000;
      }
   }

   private final String getString(StringBuilder var1, int var2) {
      Integer var10000 = (Integer)this.hashMap2.get(var1.toString());
      if (var10000 != null) {
         int var3 = var10000;
         return this.getString(var2, var3);
      } else {
         throw new ShuffleException("No shuffle output for " + var1);
      }
   }
}
