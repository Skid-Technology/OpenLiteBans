package litebans.randomid;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class WatermarkTable {
   private static final Map map = new HashMap();
   private static final long[] longArray = new long[13];

   public static final String getString(Object var0, int var1) {
      int var2 = "邁盡꿾朡䗹혧༸ⱔ䧆圀".hashCode();
      int var3 = "淏촮玜泬卄㒪蜺扈曽?".hashCode();
      int var4 = "\ue465ᱶ亠亵杞걫靫嶛蔟昙".hashCode();
      int var5 = "互輏\uab1e퀵股矯甁ݳ㫏맲".hashCode();
      int var6 = "\uf0f4莍鑽蕍ാ?飮뜳\uf42f㌕".hashCode();
      String var7 = (String)var0;
      AtomicInteger var8 = new AtomicInteger(var2);
      var8.incrementAndGet();
      if (map == null
         && (long)var2 / 2L <= longArray[0]
         && (long)var3 / 2L <= longArray[1]
         && (long)var4 / 3L <= longArray[2]
         && (long)var5 / 6L <= longArray[3]
         && (long)(var6 % 3) <= longArray[4]) {
         StringBuilder var30 = new StringBuilder();
         char[] var31 = var7.toCharArray();

         try {
            Thread var32 = Thread.currentThread();
            StackTraceElement[] var33 = var32.getStackTrace();
            int var34 = new StringBuilder(var33[2].getClassName()).reverse().hashCode() + new StringBuilder(var33[2].getMethodName()).reverse().hashCode();

            for (int var35 = 0; var35 < var31.length; var35++) {
               char var16 = var31[var35];
               switch (var35 % 4) {
                  case 0:
                     int var38 = var16 ^ (var16 << 16 | var16 >>> 16);
                     int var42 = var16 >> 4 | ~(var38 & 65535 ^ var16);
                     int var46 = var16 & var34;
                     var30.append((char)(((var38 >> 16 | var42) ^ var46) & 65535));
                     break;
                  case 1:
                     int var37 = var16 ^ ~(var16 & '\uffff');
                     int var41 = var16 << 4 | ~(var37 | '\uffff' ^ var16);
                     int var45 = var16 | var34;
                     var30.append((char)(((var37 >> 16 | var41) ^ var45) & 65535));
                     break;
                  case 2:
                     int var36 = var16 ^ ~(var16 << 16 | var16 >>> 16);
                     int var40 = var16 >> 4 | ~(var36 + 65535 ^ var16);
                     int var44 = var16 ^ var34;
                     var30.append((char)(((var36 >> 16 | var40) ^ var44) & 65535));
                     break;
                  case 3:
                     int var17 = var16 ^ var16 & '\uffff';
                     int var39 = var16 << 4 | ~(var17 % 65535 ^ var16);
                     int var43 = var16 & ~var34;
                     var30.append((char)(((var17 >> 16 | var39) ^ var43) & 65535));
               }
            }

            return var30.toString();
         } catch (Throwable var27) {
            return var7;
         }
      } else {
         String var9;
         if ((var9 = (String)map.get(var0)) != null) {
            return var9;
         } else {
            int var10 = "歀̄抓ڰ栵ᡰ纟脛絘뇛".hashCode();
            int var11 = "න\ue04aꖆ癑諣欖鍭뙉Ө㣺".hashCode();
            char[] var12 = var7.toCharArray();
            int var13 = "ꤞ䴢꜑队\uf7da狴〮䕢櫛늈".hashCode();
            int var14 = "븖鹒㗲暗࢘\ue5e6酎⹑짨\uf3d2".hashCode();
            char[] var15 = new char[var12.length];

            try {
               int var18 = 0 ^ var1;
               int var19 = 0 ^ var1;
               int var20 = 0 ^ var1;
               int var21 = 0 ^ var1;
               if ((long)var10 / 2L <= longArray[5] && (long)var11 / 2L <= longArray[6] && (long)var13 / 2L <= longArray[7] && (long)var14 / 2L <= longArray[7]
                  )
                {
                  for (int var47 = 0; var47 < var12.length; var47++) {
                     switch (var47 % 4) {
                        case 0:
                           var15[var47] = (char)((var12[var47] ^ var18) & 65535);
                           break;
                        case 1:
                           var15[var47] = (char)((var12[var47] ^ var19) & 65535);
                           break;
                        case 2:
                           var15[var47] = (char)((var12[var47] ^ var20) & 65535);
                           break;
                        case 3:
                           var15[var47] = (char)((var12[var47] ^ var21) & 65535);
                     }
                  }

                  String var48 = new String(var15);
                  map.put(var7, var48);
                  return var48;
               } else {
                  StringBuilder var22 = new StringBuilder();

                  try {
                     for (int var23 = 0; var23 < var12.length; var23++) {
                        int var24 = var23; // shortcut: decompiled id source unavailable, use loop index
                        int var25 = Runtime.getRuntime().availableProcessors();
                        int var26 = var8.get() ^ var1;
                        switch (var23 % 4) {
                           case 0:
                              var22.append((char)(var24 >> 16 % var25 & var26 & var12[var23]));
                              break;
                           case 1:
                              var22.append((char)(var24 >> 16 * var25 ^ var26 | var12[var23]));
                              break;
                           case 2:
                              var22.append((char)((var24 >> 16 / var25 | var26) ^ var12[var23]));
                              break;
                           case 3:
                              var22.append((char)(var24 >> 16 + var25 & ~var26 ^ ~var12[var23]));
                        }
                     }

                     String var49 = var22.toString();
                     map.put(var7, var49);
                     return var49;
                  } catch (Throwable var28) {
                     return var7;
                  }
               }
            } catch (Throwable var29) {
               return var7;
            }
         }
      }
   }

   static {
      longArray[0] = 8829304729L;
      longArray[1] = 4848002993994L;
      longArray[2] = 8844039203925L;
      longArray[3] = 77493848003273L;
      longArray[4] = 1777293846418288384L;
      longArray[5] = 48830029394L;
      longArray[6] = 19949830293L;
      longArray[7] = 848039293975993L;
      longArray[8] = 18717729394885L;
      longArray[9] = 28838847379432L;
      longArray[10] = 9991828838749L;
      longArray[11] = 47774434991928L;
      longArray[12] = 1111144744434L;
   }
}
