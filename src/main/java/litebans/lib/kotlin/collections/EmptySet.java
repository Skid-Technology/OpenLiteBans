package litebans.lib.kotlin.collections;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.jvm.internal.KMappedMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class EmptySet implements Set, Serializable, KMappedMarker {
   public static final EmptySet INSTANCE = new EmptySet();
   private static final long longValue = 3406603774387020532L;

   private EmptySet() {
   }

   @Override
   public boolean equals(@Nullable Object var1) {
      return var1 instanceof Set && ((Set)var1).isEmpty();
   }

   @Override
   public int hashCode() {
      return 0;
   }

   @Override
   public String toString() {
      return "[]";
   }

   public int getInt() {
      return 0;
   }

   @Override
   public boolean isEmpty() {
      return true;
   }

   public boolean checkVoid(@NotNull Void element) {
      Intrinsics.handleObject3(element, "element");
      return false;
   }

   @Override
   public boolean containsAll(@NotNull Collection elements) {
      Intrinsics.handleObject3(elements, "elements");
      return elements.isEmpty();
   }

   @Override
   public Iterator iterator() {
      return EmptyIterator.INSTANCE;
   }

   private final Object getObject() {
      return INSTANCE;
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
   public final boolean contains(Object var1) {
      return !(var1 instanceof Void) ? false : this.checkVoid((Void)var1);
   }

   @Override
   public boolean add(Object var1) {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   @Override
   public Object[] toArray(Object[] array) {
      Intrinsics.handleObject3(array, "array");
      return CollectionToArray.getObjectArray(this, array);
   }

   @Override
   public Object[] toArray() {
      return CollectionToArray.getObjectArray(this);
   }
}
