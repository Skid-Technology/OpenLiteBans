package litebans.lib.kotlin.collections;

import java.util.Collection;
import java.util.Iterator;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.jvm.internal.KMappedMarker;
import org.jetbrains.annotations.NotNull;

public final class ArrayAsCollection implements Collection, KMappedMarker {
   private final Object[] values;
   private final boolean flag;

   public ArrayAsCollection(@NotNull Object[] values, boolean flag) {
      Intrinsics.handleObject3(values, "values");
      this.values = values;
      this.flag = flag;
   }

   public int getInt() {
      return this.values.length;
   }

   @Override
   public boolean isEmpty() {
      return this.values.length == 0;
   }

   @Override
   public boolean contains(Object var1) {
      return ArraysKt.checkObjectArray(this.values, var1);
   }

   @Override
   public boolean containsAll(@NotNull Collection elements) {
      Intrinsics.handleObject3(elements, "elements");
      Iterable var2 = elements;
      boolean var10000;
      if (((Collection)var2).isEmpty()) {
         var10000 = true;
      } else {
         Iterator var4 = var2.iterator();

         while (true) {
            if (!var4.hasNext()) {
               var10000 = true;
               break;
            }

            Object var5 = var4.next();
            if (!this.contains(var5)) {
               var10000 = false;
               break;
            }
         }
      }

      return var10000;
   }

   @Override
   public Iterator iterator() {
      return ArrayIteratorKt.getIterator(this.values);
   }

   @Override
   public final Object[] toArray() {
      return CollectionsKt.getObjectArray(this.values, this.flag);
   }

   @Override
   public boolean add(Object var1) {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   @Override
   public boolean remove(Object var1) {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   @Override
   public boolean addAll(Collection var1) {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   @Override
   public boolean removeAll(Collection var1) {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   @Override
   public boolean retainAll(Collection var1) {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   @Override
   public void clear() {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   @Override
   public final int size() {
      return this.getInt();
   }

   @Override
   public Object[] toArray(Object[] array) {
      Intrinsics.handleObject3(array, "array");
      return CollectionToArray.getObjectArray(this, array);
   }
}
