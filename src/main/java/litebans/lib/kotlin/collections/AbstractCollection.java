package litebans.lib.kotlin.collections;

import java.util.Collection;
import java.util.Iterator;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.jvm.internal.KMappedMarker;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractCollection implements Collection, KMappedMarker {
   public abstract int getIntValue3();

   @Override
   public abstract Iterator iterator();

   @Override
   public boolean contains(Object var1) {
      Iterable var2 = this;
      boolean var10000;
      if (var2 instanceof Collection && ((Collection)var2).isEmpty()) {
         var10000 = false;
      } else {
         Iterator var4 = var2.iterator();

         while (true) {
            if (!var4.hasNext()) {
               var10000 = false;
               break;
            }

            Object var5 = var4.next();
            if (Intrinsics.checkObject(var5, var1)) {
               var10000 = true;
               break;
            }
         }
      }

      return var10000;
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
   public boolean isEmpty() {
      return this.size() == 0;
   }

   @Override
   public String toString() {
      return CollectionsKt.getString(this, ", ", "[", "]", 0, null, a -> getCharSequence(this, (Object) a), 24, null);
   }

   @Override
   public Object[] toArray() {
      return CollectionToArray.getObjectArray(this);
   }

   @Override
   public Object[] toArray(@NotNull Object[] array) {
      Intrinsics.handleObject3(array, "array");
      return CollectionToArray.getObjectArray(this, array);
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

   private static final CharSequence getCharSequence(AbstractCollection var0, Object var1) {
      return var1 == var0 ? "(this Collection)" : String.valueOf(var1);
   }

   @Override
   public final int size() {
      return this.getIntValue3();
   }
}