package litebans.lib.kotlin.collections;

import java.util.ArrayList;
import java.util.List;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public class ArraysKt__ArraysKt extends ArraysKt__ArraysJVMKt {
   public static final List getList(@NotNull Object[][] var0) {
      Intrinsics.handleObject3(var0, "<this>");
      Object[] var2 = (Object[])var0;
      int var3 = 0;
      int var4 = 0;

      for (int var5 = var2.length; var4 < var5; var4++) {
         Object var6 = var2[var4];
         Object[] var7 = (Object[])var6;
         int var10 = var7.length;
         var3 += var10;
      }

      ArrayList var1 = new ArrayList(var3);
      int var12 = 0;

      for (int var13 = ((Object[])var0).length; var12 < var13; var12++) {
         Object[] var14 = var0[var12];
         CollectionsKt.checkCollection(var1, var14);
      }

      return var1;
   }
}
