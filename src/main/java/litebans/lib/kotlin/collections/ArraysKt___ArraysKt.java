package litebans.lib.kotlin.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import litebans.lib.kotlin.jvm.internal.Function1;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.ranges.RangesKt;
import litebans.lib.kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ArraysKt___ArraysKt extends ArraysKt___ArraysJvmKt {
   public static final boolean checkObjectArray(@NotNull Object[] var0, Object var1) {
      Intrinsics.handleObject3(var0, "<this>");
      return ArraysKt.getInt(var0, var1) >= 0;
   }

   public static final boolean checkCharArray(@NotNull char[] var0, char var1) {
      Intrinsics.handleObject3(var0, "<this>");
      return ArraysKt.getInt(var0, var1) >= 0;
   }

   public static final Object getObject(@NotNull Object[] var0) {
      Intrinsics.handleObject3(var0, "<this>");
      if (var0.length == 0) {
         throw new NoSuchElementException("Array is empty.");
      } else {
         return var0[0];
      }
   }

   public static final byte getByte(@NotNull byte[] var0) {
      Intrinsics.handleObject3(var0, "<this>");
      if (var0.length == 0) {
         throw new NoSuchElementException("Array is empty.");
      } else {
         return var0[0];
      }
   }

   public static final int getInt(@NotNull int[] var0) {
      Intrinsics.handleObject3(var0, "<this>");
      if (var0.length == 0) {
         throw new NoSuchElementException("Array is empty.");
      } else {
         return var0[0];
      }
   }

   public static final Object getObject2(@NotNull Object[] var0) {
      Intrinsics.handleObject3(var0, "<this>");
      return var0.length == 0 ? null : var0[0];
   }

   public static final Object getObject(@NotNull Object[] var0, int var1) {
      Intrinsics.handleObject3(var0, "<this>");
      return (0 <= var1 ? var1 < var0.length : false) ? var0[var1] : null;
   }

   public static final int getInt(@NotNull Object[] var0, Object var1) {
      Intrinsics.handleObject3(var0, "<this>");
      if (var1 == null) {
         int var2 = 0;

         for (int var3 = var0.length; var2 < var3; var2++) {
            if (var0[var2] == null) {
               return var2;
            }
         }
      } else {
         int var4 = 0;

         for (int var5 = var0.length; var4 < var5; var4++) {
            if (Intrinsics.checkObject(var1, var0[var4])) {
               return var4;
            }
         }
      }

      return -1;
   }

   public static final int getInt(@NotNull char[] var0, char var1) {
      Intrinsics.handleObject3(var0, "<this>");
      int var2 = 0;

      for (int var3 = var0.length; var2 < var3; var2++) {
         if (var1 == var0[var2]) {
            return var2;
         }
      }

      return -1;
   }

   public static final Object getObject3(@NotNull Object[] var0) {
      Intrinsics.handleObject3(var0, "<this>");
      if (var0.length == 0) {
         throw new NoSuchElementException("Array is empty.");
      } else {
         return var0[ArraysKt.getInt(var0)];
      }
   }

   public static final char getChar(@NotNull char[] var0) {
      Intrinsics.handleObject3(var0, "<this>");
      switch (var0.length) {
         case 0:
            throw new NoSuchElementException("Array is empty.");
         case 1:
            return var0[0];
         default:
            throw new IllegalArgumentException("Array has more than one element.");
      }
   }

   public static final int getInt(@NotNull Object[] var0) {
      Intrinsics.handleObject3(var0, "<this>");
      return var0.length - 1;
   }

   public static final int getInt(@NotNull byte[] var0) {
      Intrinsics.handleObject3(var0, "<this>");
      return var0.length - 1;
   }

   public static final Collection getCollection(@NotNull Object[] objectArray, @NotNull Collection destination) {
      Intrinsics.handleObject3(objectArray, "<this>");
      Intrinsics.handleObject3(destination, "destination");
      int var2 = 0;

      for (int var3 = objectArray.length; var2 < var3; var2++) {
         Object var4 = objectArray[var2];
         destination.add(var4);
      }

      return destination;
   }

   public static final Collection getCollection(@NotNull char[] charArray, @NotNull Collection destination) {
      Intrinsics.handleObject3(charArray, "<this>");
      Intrinsics.handleObject3(destination, "destination");
      int var2 = 0;

      for (int var3 = charArray.length; var2 < var3; var2++) {
         char var4 = charArray[var2];
         destination.add(var4);
      }

      return destination;
   }

   public static final HashSet getHashSet(@NotNull char[] var0) {
      Intrinsics.handleObject3(var0, "<this>");
      return (HashSet)ArraysKt.getCollection(var0, new HashSet(MapsKt.getInt(RangesKt.getInt2(var0.length, 128))));
   }

   public static final List getList2(@NotNull Object[] var0) {
      Intrinsics.handleObject3(var0, "<this>");
      return new ArrayList(CollectionsKt.getCollection(var0));
   }

   public static final Set getSet(@NotNull Object[] var0) {
      Intrinsics.handleObject3(var0, "<this>");
      Set var10000;
      switch (var0.length) {
         case 0:
            var10000 = SetsKt.getSet();
            break;
         case 1:
            var10000 = SetsKt.getSet(var0[0]);
            break;
         default:
            var10000 = (Set)ArraysKt.getCollection(var0, new LinkedHashSet(MapsKt.getInt(var0.length)));
      }

      return var10000;
   }

   public static final Appendable getAppendable(
      @NotNull Object[] objectArray,
      @NotNull Appendable buffer,
      @NotNull CharSequence separator,
      @NotNull CharSequence prefix,
      @NotNull CharSequence postfix,
      int intValue,
      @NotNull CharSequence truncated,
      @Nullable Function1 function1
   ) {
      try {
      Intrinsics.handleObject3(objectArray, "<this>");
      Intrinsics.handleObject3(buffer, "buffer");
      Intrinsics.handleObject3(separator, "separator");
      Intrinsics.handleObject3(prefix, "prefix");
      Intrinsics.handleObject3(postfix, "postfix");
      Intrinsics.handleObject3(truncated, "truncated");
      buffer.append(prefix);
      int var8 = 0;
      int var9 = 0;

      for (int var10 = objectArray.length; var9 < var10; var9++) {
         Object var11 = objectArray[var9];
         if (++var8 > 1) {
            buffer.append(separator);
         }

         if (intValue >= 0 && var8 > intValue) {
            break;
         }

         StringsKt.handleAppendable(buffer, var11, function1);
      }

      if (intValue >= 0 && var8 > intValue) {
         buffer.append(truncated);
      }

      buffer.append(postfix);
      return buffer;
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   public static final Appendable getAppendable(
      @NotNull byte[] byteArray,
      @NotNull Appendable buffer,
      @NotNull CharSequence separator,
      @NotNull CharSequence prefix,
      @NotNull CharSequence postfix,
      int intValue,
      @NotNull CharSequence truncated,
      @Nullable Function1 function1
   ) {
      try {
      Intrinsics.handleObject3(byteArray, "<this>");
      Intrinsics.handleObject3(buffer, "buffer");
      Intrinsics.handleObject3(separator, "separator");
      Intrinsics.handleObject3(prefix, "prefix");
      Intrinsics.handleObject3(postfix, "postfix");
      Intrinsics.handleObject3(truncated, "truncated");
      buffer.append(prefix);
      int var8 = 0;
      int var9 = 0;

      for (int var10 = byteArray.length; var9 < var10; var9++) {
         byte var11 = byteArray[var9];
         if (++var8 > 1) {
            buffer.append(separator);
         }

         if (intValue >= 0 && var8 > intValue) {
            break;
         }

         if (function1 != null) {
            buffer.append((CharSequence)function1.invoke(var11));
         } else {
            buffer.append(String.valueOf((int)var11));
         }
      }

      if (intValue >= 0 && var8 > intValue) {
         buffer.append(truncated);
      }

      buffer.append(postfix);
      return buffer;
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   public static final String getString(
      @NotNull Object[] objectArray,
      @NotNull CharSequence separator,
      @NotNull CharSequence prefix,
      @NotNull CharSequence postfix,
      int intValue,
      @NotNull CharSequence truncated,
      @Nullable Function1 function1
   ) {
      Intrinsics.handleObject3(objectArray, "<this>");
      Intrinsics.handleObject3(separator, "separator");
      Intrinsics.handleObject3(prefix, "prefix");
      Intrinsics.handleObject3(postfix, "postfix");
      Intrinsics.handleObject3(truncated, "truncated");
      return ((StringBuilder)ArraysKt.getAppendable(objectArray, new StringBuilder(), separator, prefix, postfix, intValue, truncated, function1)).toString();
   }

   public static String getString(
      Object[] var0, CharSequence var1, CharSequence var2, CharSequence var3, int var4, CharSequence var5, Function1 var6, int var7, Object var8
   ) {
      if ((var7 & 1) != 0) {
         var1 = ", ";
      }

      if ((var7 & 2) != 0) {
         var2 = "";
      }

      if ((var7 & 4) != 0) {
         var3 = "";
      }

      if ((var7 & 8) != 0) {
         var4 = -1;
      }

      if ((var7 & 16) != 0) {
         var5 = "...";
      }

      if ((var7 & 32) != 0) {
         var6 = null;
      }

      return ArraysKt.getString(var0, var1, var2, var3, var4, var5, var6);
   }

   public static final String getString(
      @NotNull byte[] byteArray,
      @NotNull CharSequence separator,
      @NotNull CharSequence prefix,
      @NotNull CharSequence postfix,
      int intValue,
      @NotNull CharSequence truncated,
      @Nullable Function1 function1
   ) {
      Intrinsics.handleObject3(byteArray, "<this>");
      Intrinsics.handleObject3(separator, "separator");
      Intrinsics.handleObject3(prefix, "prefix");
      Intrinsics.handleObject3(postfix, "postfix");
      Intrinsics.handleObject3(truncated, "truncated");
      return ((StringBuilder)ArraysKt.getAppendable(byteArray, new StringBuilder(), separator, prefix, postfix, intValue, truncated, function1)).toString();
   }

   public static String getString(
      byte[] var0, CharSequence var1, CharSequence var2, CharSequence var3, int var4, CharSequence var5, Function1 var6, int var7, Object var8
   ) {
      if ((var7 & 1) != 0) {
         var1 = ", ";
      }

      if ((var7 & 2) != 0) {
         var2 = "";
      }

      if ((var7 & 4) != 0) {
         var3 = "";
      }

      if ((var7 & 8) != 0) {
         var4 = -1;
      }

      if ((var7 & 16) != 0) {
         var5 = "...";
      }

      if ((var7 & 32) != 0) {
         var6 = null;
      }

      return ArraysKt.getString(var0, var1, var2, var3, var4, var5, var6);
   }

   public static final int getInt2(@NotNull int[] var0) {
      Intrinsics.handleObject3(var0, "<this>");
      int var1 = 0;
      int var2 = 0;

      for (int var3 = var0.length; var2 < var3; var2++) {
         int var4 = var0[var2];
         var1 += var4;
      }

      return var1;
   }
}
