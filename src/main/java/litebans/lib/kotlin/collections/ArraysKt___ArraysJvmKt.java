package litebans.lib.kotlin.collections;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public class ArraysKt___ArraysJvmKt extends ArraysKt__ArraysKt {
   public static final List getList(@NotNull Object[] var0) {
      Intrinsics.handleObject3(var0, "<this>");
      return ArraysUtilJVM.getList(var0);
   }

   public static final Object[] getObjectArray(@NotNull Object[] objectArray, @NotNull Object[] destination, int intValue, int intValue2, int intValue3) {
      Intrinsics.handleObject3(objectArray, "<this>");
      Intrinsics.handleObject3(destination, "destination");
      System.arraycopy(objectArray, intValue2, destination, intValue, intValue3 - intValue2);
      return destination;
   }

   public static Object[] getObjectArray(Object[] var0, Object[] var1, int var2, int var3, int var4, int var5, Object var6) {
      if ((var5 & 2) != 0) {
         var2 = 0;
      }

      if ((var5 & 4) != 0) {
         var3 = 0;
      }

      if ((var5 & 8) != 0) {
         var4 = var0.length;
      }

      return ArraysKt.getObjectArray(var0, var1, var2, var3, var4);
   }

   public static final byte[] getByteArray(@NotNull byte[] byteArray, @NotNull byte[] destination, int intValue, int intValue2, int intValue3) {
      Intrinsics.handleObject3(byteArray, "<this>");
      Intrinsics.handleObject3(destination, "destination");
      System.arraycopy(byteArray, intValue2, destination, intValue, intValue3 - intValue2);
      return destination;
   }

   public static final byte[] getByteArray(@NotNull byte[] var0, int var1, int var2) {
      Intrinsics.handleObject3(var0, "<this>");
      ArraysKt.handleInt(var2, var0.length);
      return Arrays.copyOfRange(var0, var1, var2);
   }

   public static final void handleObjectArray(@NotNull Object[] var0) {
      Intrinsics.handleObject3(var0, "<this>");
      if (var0.length > 1) {
         Arrays.sort(var0);
      }
   }

   public static final void handleObjectArray(@NotNull Object[] objectArray, @NotNull Comparator comparator) {
      Intrinsics.handleObject3(objectArray, "<this>");
      Intrinsics.handleObject3(comparator, "comparator");
      if (objectArray.length > 1) {
         Arrays.sort(objectArray, comparator);
      }
   }
}
