package litebans.lib.kotlin.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public class CollectionsKt__CollectionsKt extends CollectionsKt__CollectionsJVMKt {
   public static final Collection getCollection(@NotNull Object[] var0) {
      Intrinsics.handleObject3(var0, "<this>");
      return new ArrayAsCollection(var0, false);
   }

   public static final List getList() {
      return EmptyList.INSTANCE;
   }

   public static final List getList(@NotNull Object[] elements) {
      Intrinsics.handleObject3(elements, "elements");
      return elements.length > 0 ? ArraysKt.getList(elements) : CollectionsKt.getList();
   }

   public static final List getList2(@NotNull Object[] elements) {
      Intrinsics.handleObject3(elements, "elements");
      return elements.length == 0 ? new ArrayList() : new ArrayList(new ArrayAsCollection(elements, true));
   }

   public static final ArrayList getArrayList(@NotNull Object[] elements) {
      Intrinsics.handleObject3(elements, "elements");
      return elements.length == 0 ? new ArrayList() : new ArrayList(new ArrayAsCollection(elements, true));
   }

   public static final int getInt(@NotNull List var0) {
      Intrinsics.handleObject3(var0, "<this>");
      return var0.size() - 1;
   }

   public static final List getList(@NotNull List var0) {
      Intrinsics.handleObject3(var0, "<this>");
      List var10000;
      switch (var0.size()) {
         case 0:
            var10000 = CollectionsKt.getList();
            break;
         case 1:
            var10000 = CollectionsKt.getList(var0.get(0));
            break;
         default:
            var10000 = var0;
      }

      return var10000;
   }

   public static final void run() {
      throw new ArithmeticException("Index overflow has happened.");
   }

   public static final void run2() {
      throw new ArithmeticException("Count overflow has happened.");
   }
}
