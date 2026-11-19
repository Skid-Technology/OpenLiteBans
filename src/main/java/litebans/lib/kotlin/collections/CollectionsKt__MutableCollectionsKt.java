package litebans.lib.kotlin.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;
import litebans.lib.kotlin.jvm.internal.Function1;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.jvm.internal.TypeIntrinsics;
import org.jetbrains.annotations.NotNull;

public class CollectionsKt__MutableCollectionsKt extends CollectionsKt__MutableCollectionsJVMKt {
   public static final boolean checkCollection(@NotNull Collection collection, @NotNull Iterable elements) {
      Intrinsics.handleObject3(collection, "<this>");
      Intrinsics.handleObject3(elements, "elements");
      if (elements instanceof Collection) {
         return collection.addAll((Collection)elements);
      } else {
         boolean var2 = false;

         for (Object var4 : elements) {
            if (collection.add(var4)) {
               var2 = true;
            }
         }

         return var2;
      }
   }

   public static final boolean checkCollection(@NotNull Collection collection, @NotNull Object[] elements) {
      Intrinsics.handleObject3(collection, "<this>");
      Intrinsics.handleObject3(elements, "elements");
      return collection.addAll(ArraysKt.getList(elements));
   }

   public static final boolean checkIterable(@NotNull Iterable iterable, @NotNull Function1 predicate) {
      Intrinsics.handleObject3(iterable, "<this>");
      Intrinsics.handleObject3(predicate, "predicate");
      return checkIterable(iterable, predicate, true);
   }

   private static final boolean checkIterable(Iterable var0, Function1 var1, boolean var2) {
      boolean var3 = false;
      Iterator var4 = var0.iterator();

      while (var4.hasNext()) {
         if ((Boolean)var1.invoke(var4.next()) == var2) {
            var4.remove();
            var3 = true;
         }
      }

      return var3;
   }

   public static final boolean checkList(@NotNull List list, @NotNull Function1 predicate) {
      Intrinsics.handleObject3(list, "<this>");
      Intrinsics.handleObject3(predicate, "predicate");
      return checkList(list, predicate, false);
   }

   private static final boolean checkList(List var0, Function1 var1, boolean var2) {
      if (!(var0 instanceof RandomAccess)) {
         Intrinsics.handleObject(var0, "");
         return checkIterable(TypeIntrinsics.getIterable(var0), var1, var2);
      } else {
         int var3 = 0;
         int var4 = 0;
         int var5 = CollectionsKt.getInt(var0);
         if (var4 <= var5) {
            while (true) {
               Object var6 = var0.get(var4);
               if ((Boolean)var1.invoke(var6) != var2) {
                  if (var3 != var4) {
                     var0.set(var3, var6);
                  }

                  var3++;
               }

               if (var4 == var5) {
                  break;
               }

               var4++;
            }
         }

         if (var3 >= var0.size()) {
            return false;
         } else {
            var4 = CollectionsKt.getInt(var0);
            var5 = var3;
            if (var3 <= var4) {
               while (true) {
                  var0.remove(var4);
                  if (var4 == var5) {
                     break;
                  }

                  var4--;
               }
            }

            return true;
         }
      }
   }
}
