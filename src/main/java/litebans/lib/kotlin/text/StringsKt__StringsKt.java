package litebans.lib.kotlin.text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import litebans.lib.kotlin.Pair;
import litebans.lib.kotlin.TuplesKt;
import litebans.lib.kotlin.collections.ArraysKt;
import litebans.lib.kotlin.collections.CollectionsKt;
import litebans.lib.kotlin.jvm.internal.Function2;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.ranges.IntProgression;
import litebans.lib.kotlin.ranges.IntRange;
import litebans.lib.kotlin.ranges.RangesKt;
import litebans.lib.kotlin.sequences.GeneratorSequence;
import litebans.lib.kotlin.sequences.Sequence;
import litebans.lib.kotlin.sequences.SequencesKt;
import org.jetbrains.annotations.NotNull;

public class StringsKt__StringsKt extends StringsKt__StringsJVMKt {
   public static final String getString(@NotNull String string, @NotNull char[] chars) {
      Intrinsics.handleObject3(string, "<this>");
      Intrinsics.handleObject3(chars, "chars");
      CharSequence var4 = string;
      int var6 = 0;
      int var7 = var4.length();

      CharSequence var10000;
      while (true) {
         if (var6 >= var7) {
            var10000 = "";
            break;
         }

         char var8 = var4.charAt(var6);
         if (!ArraysKt.checkCharArray(chars, var8)) {
            var10000 = var4.subSequence(var6, var4.length());
            break;
         }

         var6++;
      }

      return var10000.toString();
   }

   public static final boolean checkCharSequence(@NotNull CharSequence var0) {
      Intrinsics.handleObject3(var0, "<this>");
      CharSequence var1 = var0;
      int var3 = 0;

      boolean var10000;
      while (true) {
         if (var3 >= var1.length()) {
            var10000 = true;
            break;
         }

         char var4 = var1.charAt(var3);
         if (!CharsKt.checkChar(var4)) {
            var10000 = false;
            break;
         }

         var3++;
      }

      return var10000;
   }

   public static final int getInt(@NotNull CharSequence var0) {
      Intrinsics.handleObject3(var0, "<this>");
      return var0.length() - 1;
   }

   public static final String getString(@NotNull CharSequence charSequence, @NotNull IntRange range) {
      Intrinsics.handleObject3(charSequence, "<this>");
      Intrinsics.handleObject3(range, "range");
      return charSequence.subSequence(range.getInteger(), range.getInteger2() + 1).toString();
   }

   public static final String getString(@NotNull String string, char charValue, @NotNull String missingDelimiterValue) {
      Intrinsics.handleObject3(string, "<this>");
      Intrinsics.handleObject3(missingDelimiterValue, "missingDelimiterValue");
      int var3 = StringsKt.getInt(string, charValue, 0, false, 6, null);
      String var10000;
      if (var3 == -1) {
         var10000 = missingDelimiterValue;
      } else {
         byte var5 = 0;
         var10000 = string.substring(var5, var3);
      }

      return var10000;
   }

   public static String getString(String var0, char var1, String var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = var0;
      }

      return StringsKt.getString(var0, var1, var2);
   }

   public static final String getString2(@NotNull String string, char charValue, @NotNull String missingDelimiterValue) {
      Intrinsics.handleObject3(string, "<this>");
      Intrinsics.handleObject3(missingDelimiterValue, "missingDelimiterValue");
      int var3 = StringsKt.getInt(string, charValue, 0, false, 6, null);
      String var10000;
      if (var3 == -1) {
         var10000 = missingDelimiterValue;
      } else {
         int var5 = var3 + 1;
         int var6 = string.length();
         var10000 = string.substring(var5, var6);
      }

      return var10000;
   }

   public static String getString2(String var0, char var1, String var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = var0;
      }

      return StringsKt.getString2(var0, var1, var2);
   }

   public static final String getString(@NotNull String string, @NotNull String delimiter, @NotNull String missingDelimiterValue) {
      Intrinsics.handleObject3(string, "<this>");
      Intrinsics.handleObject3(delimiter, "delimiter");
      Intrinsics.handleObject3(missingDelimiterValue, "missingDelimiterValue");
      int var3 = StringsKt.getInt(string, delimiter, 0, false, 6, null);
      String var10000;
      if (var3 == -1) {
         var10000 = missingDelimiterValue;
      } else {
         int var5 = var3 + delimiter.length();
         int var6 = string.length();
         var10000 = string.substring(var5, var6);
      }

      return var10000;
   }

   public static String getString(String var0, String var1, String var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = var0;
      }

      return StringsKt.getString(var0, var1, var2);
   }

   public static final String getString3(@NotNull String string, char charValue, @NotNull String missingDelimiterValue) {
      Intrinsics.handleObject3(string, "<this>");
      Intrinsics.handleObject3(missingDelimiterValue, "missingDelimiterValue");
      int var3 = StringsKt.getInt2(string, charValue, 0, false, 6, null);
      String var10000;
      if (var3 == -1) {
         var10000 = missingDelimiterValue;
      } else {
         byte var5 = 0;
         var10000 = string.substring(var5, var3);
      }

      return var10000;
   }

   public static String getString3(String var0, char var1, String var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = var0;
      }

      return StringsKt.getString3(var0, var1, var2);
   }

   public static final String getString4(@NotNull String string, char charValue, @NotNull String missingDelimiterValue) {
      Intrinsics.handleObject3(string, "<this>");
      Intrinsics.handleObject3(missingDelimiterValue, "missingDelimiterValue");
      int var3 = StringsKt.getInt2(string, charValue, 0, false, 6, null);
      String var10000;
      if (var3 == -1) {
         var10000 = missingDelimiterValue;
      } else {
         int var5 = var3 + 1;
         int var6 = string.length();
         var10000 = string.substring(var5, var6);
      }

      return var10000;
   }

   public static String getString4(String var0, char var1, String var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = var0;
      }

      return StringsKt.getString4(var0, var1, var2);
   }

   public static final boolean checkCharSequence(
      @NotNull CharSequence charSequence, int intValue, @NotNull CharSequence other, int intValue2, int intValue3, boolean flag
   ) {
      Intrinsics.handleObject3(charSequence, "<this>");
      Intrinsics.handleObject3(other, "other");
      if (intValue2 >= 0 && intValue >= 0 && intValue <= charSequence.length() - intValue3 && intValue2 <= other.length() - intValue3) {
         for (int var6 = 0; var6 < intValue3; var6++) {
            if (!CharsKt.checkChar(charSequence.charAt(intValue + var6), other.charAt(intValue2 + var6), flag)) {
               return false;
            }
         }

         return true;
      } else {
         return false;
      }
   }

   public static final boolean checkCharSequence(@NotNull CharSequence var0, char var1, boolean var2) {
      Intrinsics.handleObject3(var0, "<this>");
      return var0.length() > 0 && CharsKt.checkChar(var0.charAt(0), var1, var2);
   }

   public static boolean checkCharSequence(CharSequence var0, char var1, boolean var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = false;
      }

      return StringsKt.checkCharSequence(var0, var1, var2);
   }

   public static final boolean checkCharSequence2(@NotNull CharSequence var0, char var1, boolean var2) {
      Intrinsics.handleObject3(var0, "<this>");
      return var0.length() > 0 && CharsKt.checkChar(var0.charAt(StringsKt.getInt(var0)), var1, var2);
   }

   public static boolean checkCharSequence2(CharSequence var0, char var1, boolean var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = false;
      }

      return StringsKt.checkCharSequence2(var0, var1, var2);
   }

   public static final boolean checkCharSequence(@NotNull CharSequence charSequence, @NotNull CharSequence prefix, boolean flag) {
      Intrinsics.handleObject3(charSequence, "<this>");
      Intrinsics.handleObject3(prefix, "prefix");
      return !flag && charSequence instanceof String && prefix instanceof String
         ? StringsKt.checkString((String)charSequence, (String)prefix, false, 2, null)
         : StringsKt.checkCharSequence(charSequence, 0, prefix, 0, prefix.length(), flag);
   }

   public static boolean checkCharSequence(CharSequence var0, CharSequence var1, boolean var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = false;
      }

      return StringsKt.checkCharSequence(var0, var1, var2);
   }

   public static final boolean checkCharSequence2(@NotNull CharSequence charSequence, @NotNull CharSequence suffix, boolean flag) {
      Intrinsics.handleObject3(charSequence, "<this>");
      Intrinsics.handleObject3(suffix, "suffix");
      return !flag && charSequence instanceof String && suffix instanceof String
         ? StringsKt.checkString2((String)charSequence, (String)suffix, false, 2, null)
         : StringsKt.checkCharSequence(charSequence, charSequence.length() - suffix.length(), suffix, 0, suffix.length(), flag);
   }

   public static boolean checkCharSequence2(CharSequence var0, CharSequence var1, boolean var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = false;
      }

      return StringsKt.checkCharSequence2(var0, var1, var2);
   }

   public static final int getInt(@NotNull CharSequence charSequence, @NotNull char[] chars, int intValue, boolean flag) {
      Intrinsics.handleObject3(charSequence, "<this>");
      Intrinsics.handleObject3(chars, "chars");
      if (!flag && chars.length == 1 && charSequence instanceof String) {
         char var13 = ArraysKt.getChar(chars);
         return ((String)charSequence).indexOf(var13, intValue);
      } else {
         int var4 = RangesKt.getInt(intValue, 0);
         int var5 = StringsKt.getInt(charSequence);
         if (var4 <= var5) {
            while (true) {
               char var6 = charSequence.charAt(var4);
               char[] var7 = chars;
               int var9 = 0;
               int var10 = chars.length;

               boolean var10000;
               while (true) {
                  if (var9 >= var10) {
                     var10000 = false;
                     break;
                  }

                  char var11 = var7[var9];
                  if (CharsKt.checkChar(var11, var6, flag)) {
                     var10000 = true;
                     break;
                  }

                  var9++;
               }

               if (var10000) {
                  return var4;
               }

               if (var4 == var5) {
                  break;
               }

               var4++;
            }
         }

         return -1;
      }
   }

   public static final int getInt2(@NotNull CharSequence charSequence, @NotNull char[] chars, int intValue, boolean flag) {
      Intrinsics.handleObject3(charSequence, "<this>");
      Intrinsics.handleObject3(chars, "chars");
      if (!flag && chars.length == 1 && charSequence instanceof String) {
         char var12 = ArraysKt.getChar(chars);
         return ((String)charSequence).lastIndexOf(var12, intValue);
      } else {
         for (int var4 = RangesKt.getInt2(intValue, StringsKt.getInt(charSequence)); -1 < var4; var4--) {
            char var5 = charSequence.charAt(var4);
            char[] var6 = chars;
            int var8 = 0;
            int var9 = chars.length;

            boolean var10000;
            while (true) {
               if (var8 >= var9) {
                  var10000 = false;
                  break;
               }

               char var10 = var6[var8];
               if (CharsKt.checkChar(var10, var5, flag)) {
                  var10000 = true;
                  break;
               }

               var8++;
            }

            if (var10000) {
               return var4;
            }
         }

         return -1;
      }
   }

   private static final int getInt(CharSequence var0, CharSequence var1, int var2, int var3, boolean var4, boolean var5) {
      IntProgression var6 = !var5
         ? new IntRange(RangesKt.getInt(var2, 0), RangesKt.getInt2(var3, var0.length()))
         : RangesKt.getIntProgression(RangesKt.getInt2(var2, StringsKt.getInt(var0)), RangesKt.getInt(var3, 0));
      if (var0 instanceof String && var1 instanceof String) {
         int var10 = var6.getIntValue();
         int var11 = var6.getIntValue2();
         int var12 = var6.getIntValue3();
         if (var12 > 0 && var10 <= var11 || var12 < 0 && var11 <= var10) {
            while (true) {
               if (StringsKt.checkString((String)var1, 0, (String)var0, var10, ((String)var1).length(), var4)) {
                  return var10;
               }

               if (var10 == var11) {
                  break;
               }

               var10 += var12;
            }
         }
      } else {
         int var7 = var6.getIntValue();
         int var8 = var6.getIntValue2();
         int var9 = var6.getIntValue3();
         if (var9 > 0 && var7 <= var8 || var9 < 0 && var8 <= var7) {
            while (true) {
               if (StringsKt.checkCharSequence(var1, 0, var0, var7, var1.length(), var4)) {
                  return var7;
               }

               if (var7 == var8) {
                  break;
               }

               var7 += var9;
            }
         }
      }

      return -1;
   }

   public static int getIntDefault(CharSequence var0, CharSequence var1, int var2, int var3, boolean var4, boolean var5, int var6, Object var7) {
      if ((var6 & 16) != 0) {
         var5 = false;
      }

      return getInt(var0, var1, var2, var3, var4, var5);
   }

   private static final Pair getPair(CharSequence var0, Collection var1, int var2, boolean var3, boolean var4) {
      if (!var3 && var1.size() == 1) {
         String var15 = (String)CollectionsKt.getObject2(var1);
         int var17 = !var4 ? StringsKt.getInt(var0, var15, var2, false, 4, null) : StringsKt.getInt2(var0, var15, var2, false, 4, null);
         return var17 < 0 ? null : TuplesKt.getPair(var17, var15);
      } else {
         IntProgression var5 = !var4
            ? new IntRange(RangesKt.getInt(var2, 0), var0.length())
            : RangesKt.getIntProgression(RangesKt.getInt2(var2, StringsKt.getInt(var0)), 0);
         if (var0 instanceof String) {
            int var6 = var5.getIntValue();
            int var7 = var5.getIntValue2();
            int var8 = var5.getIntValue3();
            if (var8 > 0 && var6 <= var7 || var8 < 0 && var7 <= var6) {
               while (true) {
                  Iterable var10 = var1;
                  Iterator var12 = var10.iterator();

                  Object var10000;
                  while (true) {
                     if (!var12.hasNext()) {
                        var10000 = null;
                        break;
                     }

                     Object var13 = var12.next();
                     String var14 = (String)var13;
                     if (StringsKt.checkString(var14, 0, (String)var0, var6, var14.length(), var3)) {
                        var10000 = var13;
                        break;
                     }
                  }

                  String var9 = (String)var10000;
                  if (var9 != null) {
                     return TuplesKt.getPair(var6, var9);
                  }

                  if (var6 == var7) {
                     break;
                  }

                  var6 += var8;
               }
            }
         } else {
            int var16 = var5.getIntValue();
            int var18 = var5.getIntValue2();
            int var19 = var5.getIntValue3();
            if (var19 > 0 && var16 <= var18 || var19 < 0 && var18 <= var16) {
               while (true) {
                  Iterable var21 = var1;
                  Iterator var22 = var21.iterator();

                  Object var25;
                  while (true) {
                     if (!var22.hasNext()) {
                        var25 = null;
                        break;
                     }

                     Object var23 = var22.next();
                     String var24 = (String)var23;
                     if (StringsKt.checkCharSequence(var24, 0, var0, var16, var24.length(), var3)) {
                        var25 = var23;
                        break;
                     }
                  }

                  String var20 = (String)var25;
                  if (var20 != null) {
                     return TuplesKt.getPair(var16, var20);
                  }

                  if (var16 == var18) {
                     break;
                  }

                  var16 += var19;
               }
            }
         }

         return null;
      }
   }

   public static final int getInt(@NotNull CharSequence var0, char var1, int var2, boolean var3) {
      Intrinsics.handleObject3(var0, "<this>");
      int var10000;
      if (!var3 && var0 instanceof String) {
         var10000 = ((String)var0).indexOf(var1, var2);
      } else {
         char[] var4 = new char[]{var1};
         var10000 = StringsKt.getInt(var0, var4, var2, var3);
      }

      return var10000;
   }

   public static int getInt(CharSequence var0, char var1, int var2, boolean var3, int var4, Object var5) {
      if ((var4 & 2) != 0) {
         var2 = 0;
      }

      if ((var4 & 4) != 0) {
         var3 = false;
      }

      return StringsKt.getInt(var0, var1, var2, var3);
   }

   public static final int getInt(@NotNull CharSequence charSequence, @NotNull String string, int intValue, boolean flag) {
      Intrinsics.handleObject3(charSequence, "<this>");
      Intrinsics.handleObject3(string, "string");
      return !flag && charSequence instanceof String
         ? ((String)charSequence).indexOf(string, intValue)
         : getIntDefault(charSequence, string, intValue, charSequence.length(), flag, false, 16, null);
   }

   public static int getInt(CharSequence var0, String var1, int var2, boolean var3, int var4, Object var5) {
      if ((var4 & 2) != 0) {
         var2 = 0;
      }

      if ((var4 & 4) != 0) {
         var3 = false;
      }

      return StringsKt.getInt(var0, var1, var2, var3);
   }

   public static final int getInt2(@NotNull CharSequence var0, char var1, int var2, boolean var3) {
      Intrinsics.handleObject3(var0, "<this>");
      int var10000;
      if (!var3 && var0 instanceof String) {
         var10000 = ((String)var0).lastIndexOf(var1, var2);
      } else {
         char[] var4 = new char[]{var1};
         var10000 = StringsKt.getInt2(var0, var4, var2, var3);
      }

      return var10000;
   }

   public static int getInt2(CharSequence var0, char var1, int var2, boolean var3, int var4, Object var5) {
      if ((var4 & 2) != 0) {
         var2 = StringsKt.getInt(var0);
      }

      if ((var4 & 4) != 0) {
         var3 = false;
      }

      return StringsKt.getInt2(var0, var1, var2, var3);
   }

   public static final int getInt2(@NotNull CharSequence charSequence, @NotNull String string, int intValue, boolean flag) {
      Intrinsics.handleObject3(charSequence, "<this>");
      Intrinsics.handleObject3(string, "string");
      return !flag && charSequence instanceof String
         ? ((String)charSequence).lastIndexOf(string, intValue)
         : getInt(charSequence, string, intValue, 0, flag, true);
   }

   public static int getInt2(CharSequence var0, String var1, int var2, boolean var3, int var4, Object var5) {
      if ((var4 & 2) != 0) {
         var2 = StringsKt.getInt(var0);
      }

      if ((var4 & 4) != 0) {
         var3 = false;
      }

      return StringsKt.getInt2(var0, var1, var2, var3);
   }

   public static final boolean checkCharSequence3(@NotNull CharSequence charSequence, @NotNull CharSequence other, boolean flag) {
      Intrinsics.handleObject3(charSequence, "<this>");
      Intrinsics.handleObject3(other, "other");
      return other instanceof String
         ? StringsKt.getInt(charSequence, (String)other, 0, flag, 2, null) >= 0
         : getIntDefault(charSequence, other, 0, charSequence.length(), flag, false, 16, null) >= 0;
   }

   public static boolean checkCharSequence3(CharSequence var0, CharSequence var1, boolean var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = false;
      }

      return StringsKt.checkCharSequence3(var0, var1, var2);
   }

   public static final boolean checkCharSequence3(@NotNull CharSequence var0, char var1, boolean var2) {
      Intrinsics.handleObject3(var0, "<this>");
      return StringsKt.getInt(var0, var1, 0, var2, 2, null) >= 0;
   }

   public static boolean checkCharSequence3(CharSequence var0, char var1, boolean var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = false;
      }

      return StringsKt.checkCharSequence3(var0, var1, var2);
   }

   private static final Sequence getSequence(CharSequence var0, char[] var1, int var2, boolean var3, int var4) {
      StringsKt.handleInt(var4);
      List var5 = new ArrayList();
      for (char var6 : var1) {
         var5.add(var6);
      }
      return new GeneratorSequence(
         var0, var2, var4,
         (Function2) (cs, idx) -> getPair(var1, var3, (CharSequence) cs, ((Number) idx).intValue())
      );
   }

   public static Sequence getSequenceDefault(CharSequence var0, char[] var1, int var2, boolean var3, int var4, int var5, Object var6) {
      if ((var5 & 2) != 0) {
         var2 = 0;
      }

      if ((var5 & 4) != 0) {
         var3 = false;
      }

      if ((var5 & 8) != 0) {
         var4 = 0;
      }

      return getSequence(var0, var1, var2, var3, var4);
   }

   private static final Sequence getSequence(CharSequence var0, String[] var1, int var2, boolean var3, int var4) {
      StringsKt.handleInt(var4);
      List var5 = ArraysKt.getList(var1);
      return new GeneratorSequence(
         var0, var2, var4,
         (Function2) (cs, idx) -> getPair(var5, var3, (CharSequence) cs, ((Number) idx).intValue())
      );
   }

   public static Sequence getSequenceDefault(CharSequence var0, String[] var1, int var2, boolean var3, int var4, int var5, Object var6) {
      if ((var5 & 2) != 0) {
         var2 = 0;
      }

      if ((var5 & 4) != 0) {
         var3 = false;
      }

      if ((var5 & 8) != 0) {
         var4 = 0;
      }

      return getSequence(var0, var1, var2, var3, var4);
   }

   public static final void handleInt(int var0) {
      if (var0 < 0) {
         String var1 = "Limit must be non-negative, but was " + var0;
         throw new IllegalArgumentException(var1.toString());
      }
   }

   public static final List getList(@NotNull CharSequence charSequence, @NotNull String[] delimiters, boolean flag, int intValue) {
      Intrinsics.handleObject3(charSequence, "<this>");
      Intrinsics.handleObject3(delimiters, "delimiters");
      if (delimiters.length == 1) {
         String var4 = delimiters[0];
         if (var4.length() != 0) {
            return getList(charSequence, var4, flag, intValue);
         }
      }

      Iterable var14 = SequencesKt.getIterable(getSequenceDefault(charSequence, delimiters, 0, flag, intValue, 2, null));
      Collection var7 = new ArrayList(CollectionsKt.getInt(var14, 10));

      for (Object var10 : var14) {
         IntRange var11 = (IntRange)var10;
         var7.add(StringsKt.getString(charSequence, var11));
      }

      return (List)var7;
   }

   public static List getList(CharSequence var0, String[] var1, boolean var2, int var3, int var4, Object var5) {
      if ((var4 & 2) != 0) {
         var2 = false;
      }

      if ((var4 & 4) != 0) {
         var3 = 0;
      }

      return StringsKt.getList(var0, var1, var2, var3);
   }

   public static final List getList(@NotNull CharSequence charSequence, @NotNull char[] delimiters, boolean flag, int intValue) {
      Intrinsics.handleObject3(charSequence, "<this>");
      Intrinsics.handleObject3(delimiters, "delimiters");
      if (delimiters.length == 1) {
         return getList(charSequence, String.valueOf(delimiters[0]), flag, intValue);
      } else {
         Iterable var4 = SequencesKt.getIterable(getSequenceDefault(charSequence, delimiters, 0, flag, intValue, 2, null));
         Collection var7 = new ArrayList(CollectionsKt.getInt(var4, 10));

         for (Object var10 : var4) {
            IntRange var11 = (IntRange)var10;
            var7.add(StringsKt.getString(charSequence, var11));
         }

         return (List)var7;
      }
   }

   public static List getList(CharSequence var0, char[] var1, boolean var2, int var3, int var4, Object var5) {
      if ((var4 & 2) != 0) {
         var2 = false;
      }

      if ((var4 & 4) != 0) {
         var3 = 0;
      }

      return StringsKt.getList(var0, var1, var2, var3);
   }

   private static final List getList(CharSequence var0, String var1, boolean var2, int var3) {
      StringsKt.handleInt(var3);
      int var4 = 0;
      int var5 = StringsKt.getInt(var0, var1, var4, var2);
      if (var5 != -1 && var3 != 1) {
         boolean var6 = var3 > 0;
         ArrayList var7 = new ArrayList(var6 ? RangesKt.getInt2(var3, 10) : 10);

         do {
            var7.add(var0.subSequence(var4, var5).toString());
            var4 = var5 + var1.length();
            if (var6 && var7.size() == var3 - 1) {
               break;
            }

            var5 = StringsKt.getInt(var0, var1, var4, var2);
         } while (var5 != -1);

         var7.add(var0.subSequence(var4, var0.length()).toString());
         return var7;
      } else {
         return CollectionsKt.getList(var0.toString());
      }
   }

   private static final Pair getPair(char[] var0, boolean var1, CharSequence var2, int var3) {
      Intrinsics.handleObject3(var2, "$this$DelimitedRangesSequence");
      int var4 = StringsKt.getInt(var2, var0, var3, var1);
      return var4 < 0 ? null : TuplesKt.getPair(var4, 1);
   }

   private static final Pair getPair(List var0, boolean var1, CharSequence var2, int var3) {
      Intrinsics.handleObject3(var2, "$this$DelimitedRangesSequence");
      Pair var10000 = getPair(var2, var0, var3, var1, false);
      if (var10000 != null) {
         Pair var4 = var10000;
         var10000 = TuplesKt.getPair(var4.getObject(), ((String)var4.getObject2()).length());
      } else {
         var10000 = null;
      }

      return var10000;
   }
}
