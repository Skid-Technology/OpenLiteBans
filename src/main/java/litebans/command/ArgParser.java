package litebans.command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import litebans.lib.kotlin.Triple;
import litebans.lib.kotlin.collections.ArraysKt;
import litebans.lib.kotlin.collections.CollectionsKt;
import litebans.lib.kotlin.jvm.internal.Function1;
import litebans.lib.kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

public final class ArgParser {
   private static final byte[] byteArray = new byte[0];

   public static final byte[] getByteArray() {
      return byteArray;
   }

   public static final List getList(@NotNull String[] var0) {
      List var10000;
      if (var0.length == 0) {
         var10000 = CollectionsKt.getList();
      } else {
         String[] var3 = var0;
         Collection var4 = new ArrayList(var0.length);
         int var6 = 0;

         for (int var7 = var0.length; var6 < var7; var6++) {
            String var8 = var3[var6];
            var4.add(var8.toLowerCase(Locale.ENGLISH));
         }

         var10000 = (List)var4;
      }

      return var10000;
   }

   public static final Integer getInteger(@NotNull String[] var0, int var1) {
      String var10000 = (String)ArraysKt.getObject(var0, var1);
      return var10000 != null ? StringsKt.getInteger(var10000) : null;
   }

   public static final int getInt(@NotNull String[] var0, int var1, int var2) {
      Integer var10000 = getInteger(var0, var1);
      return var10000 != null ? var10000 : var2;
   }

   public static final int getInt(@NotNull String[] var0, int var1) {
      int var2 = var0.length + -1;
      if (0 <= var2) {
         do {
            int var3 = var2--;
            Integer var10000 = StringsKt.getInteger(var0[var3]);
            if (var10000 != null) {
               return var10000;
            }
         } while (0 <= var2);
      }

      return var1;
   }

   public static final double getDouble(@NotNull String[] var0, int var1, double var2) {
      String var10000 = (String)ArraysKt.getObject(var0, var1);
      if (var10000 != null) {
         Double var4 = StringsKt.getDouble(var10000);
         if (var4 != null) {
            return var4;
         }
      }

      return var2;
   }

   public static final boolean checkByteArray(@NotNull byte[] var0, @NotNull byte[] var1, int var2, int var3) {
      int var4 = var3 - var2;
      int var5 = 0;
      int var6 = var2;

      while (var5 < var4) {
         if (var0[var5++] != var1[var6++]) {
            return false;
         }
      }

      return true;
   }

   public static final boolean checkByteArray(@NotNull byte[] var0, @NotNull byte[] var1, int var2, int var3, int var4) {
      int var5 = var2 + (var4 - var3);
      int var6 = var2;
      int var7 = var3;

      while (var6 < var5) {
         if (var0[var6++] != var1[var7++]) {
            return false;
         }
      }

      return true;
   }

   public static final void handleTriple(@NotNull Triple var0, @NotNull Function1 var1) {
      var1.invoke(var0.getObject());
      var1.invoke(var0.getObject2());
      var1.invoke(var0.getObject3());
   }
}
