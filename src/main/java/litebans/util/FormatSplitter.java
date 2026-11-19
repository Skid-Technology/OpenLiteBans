package litebans.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import litebans.chat.JsonMessageParser;
import litebans.config.Message;
import litebans.lib.kotlin.Pair;
import litebans.lib.kotlin.TuplesKt;
import litebans.lib.kotlin.jvm.internal.DefaultConstructorMarker;
import litebans.lib.kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

public final class FormatSplitter {
   private FormatSplitter() {
   }

   public final String getString(@NotNull String var1) {
      if (!StringsKt.checkCharSequence3(var1, '#', false, 2, null)) {
         return var1;
      } else {
         StringBuilder var2 = new StringBuilder();
         ArrayList var3 = new ArrayList();
         CharSequence var10000 = Message.HEX_FORMATS;
         char[] var5 = new char[]{'\n'};
         Iterable var28 = StringsKt.getList(var10000, var5, false, 0, 6, null);
         Collection var8 = new ArrayList();

         for (Object var11 : var28) {
            String var12 = (String)var11;
            if (StringsKt.checkCharSequence3(var12, "%s", false, 2, null)) {
               var8.add(var11);
            }
         }

         List var4 = (List)var8;
         Iterable var6 = var4;
         boolean var48;
         if (var6 instanceof Collection && ((Collection)var6).isEmpty()) {
            var48 = false;
         } else {
            Iterator var31 = var6.iterator();

            while (true) {
               if (!var31.hasNext()) {
                  var48 = false;
                  break;
               }

               Object var9 = var31.next();
               String var34 = (String)var9;
               var10000 = var1;
               char var38 = StringsKt.getChar(var34);
               String var13 = "#";
               if (StringsKt.checkCharSequence3(var10000, var38 + var13, false, 2, null)) {
                  var48 = true;
                  break;
               }
            }
         }

         boolean var29 = var48;
         char[] var30 = var1.toCharArray();
         char[] var7 = var30;
         int var32 = 0;
         int var35 = 0;

         for (int var37 = var30.length; var35 < var37; var35++) {
            char var39 = var7[var35];
            int var14 = var32++;
            if (var39 == '#' && var30.length > var14 + 6) {
               StringsKt.getStringBuilder(var2);
               var2.append('#');
               boolean var16 = true;

               for (int var17 = 1; var17 < 7; var17++) {
                  char var18 = var30[var14 + var17];
                  if (!Character.isDigit(var18) && ('a' <= var18 ? var18 >= 'g' : true) && ('A' <= var18 ? var18 >= 'G' : true)) {
                     var16 = false;
                     break;
                  }

                  var2.append(var18);
               }

               if (var16) {
                  String var44 = var2.toString();
                  String var46 = JsonMessageParser.formatSplitter.getString2(var44);
                  if (var29) {
                     for (Object var22 : var4) {
                        String var23 = (String)var22;
                        Collection var25 = var3;
                        Object[] var27 = new Object[]{var44};
                        var25.add(TuplesKt.getPair(String.format(var23, Arrays.copyOf(var27, var27.length)), var46));
                     }
                  }

                  var3.add(TuplesKt.getPair(var44, var46));
               }
            }
         }

         String var33 = var1;

         for (Object var41 : var3) {
            Pair var42 = (Pair)var41;
            String var43 = (String)var42.getObject3();
            String var45 = (String)var42.getObject22();
            var33 = StringsKt.getString(var33, var43, var45, false, 4, null);
         }

         return var33;
      }
   }

   private final String getString2(String var1) {
      StringBuilder var2 = new StringBuilder(22).append('§').append('x');
      StringBuilder var3 = var2;
      CharSequence var5 = var1.substring(1);

      for (int var7 = 0; var7 < var5.length(); var7++) {
         char var8 = var5.charAt(var7);
         var3.append('§').append(var8);
      }

      return var2.toString();
   }

   public FormatSplitter(DefaultConstructorMarker var1) {
      this();
   }
}
