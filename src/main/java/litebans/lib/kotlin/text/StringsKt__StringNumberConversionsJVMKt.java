package litebans.lib.kotlin.text;

import litebans.lib.kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public class StringsKt__StringNumberConversionsJVMKt extends StringsKt__StringBuilderKt {
   public static final Double getDouble(@NotNull String var0) {
      Intrinsics.handleObject3(var0, "<this>");

      Double var2;
      try {
         var2 = checkString(var0) ? Double.parseDouble(var0) : null;
      } catch (NumberFormatException var3) {
         var2 = null;
      }

      return var2;
   }

   private static final boolean checkString(String var0) {
      int var1 = 0;
      int var2 = var0.length() - 1;
      String var3 = var0;

      int var4;
      for (var4 = var1; var4 <= var2; var4++) {
         char var5 = var3.charAt(var4);
         if (var5 > ' ') {
            break;
         }
      }

      var1 = var4;
      if (var4 > var2) {
         return false;
      } else {
         var3 = var0;

         for (var4 = var2; var4 > var1; var4--) {
            char var23 = var3.charAt(var4);
            if (var23 > ' ') {
               break;
            }
         }

         var2 = var4;
         if (var0.charAt(var1) == '+' || var0.charAt(var1) == '-') {
            var1++;
         }

         if (var1 > var4) {
            return false;
         } else {
            boolean var19 = false;
            if (var0.charAt(var1) == '0') {
               if (++var1 > var4) {
                  return true;
               }

               if ((var0.charAt(var1) | ' ') == 120) {
                  var1++;
                  String var8 = var0;

                  int var9;
                  for (var9 = var1; var9 <= var2; var9++) {
                     char var10 = var8.charAt(var9);
                     if ((var10 - '0' & 65535) >= 10 && ((var10 | ' ') - 97 & 65535) >= 6) {
                        break;
                     }
                  }

                  int var6 = var9;
                  boolean var31 = var1 != var9;
                  int var10000;
                  if (var9 > var2) {
                     var10000 = -1;
                  } else {
                     boolean var34 = false;
                     if (var0.charAt(var9) == '.') {
                        int var7 = ++var9;
                        String var37 = var0;

                        int var11;
                        for (var11 = var6; var11 <= var2; var11++) {
                           char var12 = var37.charAt(var11);
                           if ((var12 - '0' & 65535) >= 10 && ((var12 | ' ') - 97 & 65535) >= 6) {
                              break;
                           }
                        }

                        var6 = var11;
                        var34 = var7 != var11;
                     }

                     var10000 = !var31 && !var34 ? -1 : var6;
                  }

                  var1 = var10000;
                  if (var1 == -1 || var1 > var2) {
                     return false;
                  }

                  var19 = true;
               } else {
                  var1--;
               }
            }

            if (!var19) {
               String var32 = var0;

               int var35;
               for (var35 = var1; var35 <= var2; var35++) {
                  char var38 = var32.charAt(var35);
                  if ((var38 - '0' & 65535) >= 10) {
                     break;
                  }
               }

               int var26 = var35;
               boolean var33 = var1 != var35;
               int var43;
               if (var35 > var2) {
                  var43 = var35;
               } else {
                  boolean var36 = false;
                  if (var0.charAt(var35) == '.') {
                     int var29 = ++var35;
                     String var39 = var0;

                     int var41;
                     for (var41 = var26; var41 <= var2; var41++) {
                        char var42 = var39.charAt(var41);
                        if ((var42 - '0' & 65535) >= 10) {
                           break;
                        }
                     }

                     var26 = var41;
                     var36 = var29 != var41;
                  }

                  if (!var33 && !var36) {
                     String var40 = var2 == var26 + 3 - 1 ? "NaN" : (var2 == var26 + 8 - 1 ? "Infinity" : null);
                     var43 = var40 == null ? -1 : (StringsKt.getInt(var0, var40, var26, false) == var26 ? var2 + 1 : -1);
                  } else {
                     var43 = var26;
                  }
               }

               var1 = var43;
               if (var1 == -1) {
                  return false;
               }

               if (var1 > var2) {
                  return true;
               }
            }

            var4 = var0.charAt(var1++) | ' ';
            if (var4 != (var19 ? 112 : 101)) {
               return !var19 && (var4 == 102 || var4 == 100) && var1 > var2;
            } else if (var1 > var2) {
               return false;
            } else {
               if (var0.charAt(var1) == '+' || var0.charAt(var1) == '-') {
                  if (++var1 > var2) {
                     return false;
                  }
               }

               String var24 = var0;

               int var28;
               for (var28 = var1; var28 <= var2; var28++) {
                  char var30 = var24.charAt(var28);
                  if ((var30 - '0' & 65535) >= 10) {
                     break;
                  }
               }

               if (var28 > var2) {
                  return true;
               } else if (var28 != var2) {
                  return false;
               } else {
                  var4 = var0.charAt(var28) | ' ';
                  return var4 == 102 || var4 == 100;
               }
            }
         }
      }
   }
}
