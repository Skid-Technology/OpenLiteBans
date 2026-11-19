package litebans.lib.kotlin.collections;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.jvm.internal.KMappedMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class EmptyList implements List, Serializable, RandomAccess, KMappedMarker {
   public static final EmptyList INSTANCE = new EmptyList();
   private static final long longValue = -7390468764508069838L;

   private EmptyList() {
   }

   @Override
   public boolean equals(@Nullable Object var1) {
      return var1 instanceof List && ((List)var1).isEmpty();
   }

   @Override
   public int hashCode() {
      return 1;
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

   public Void getVoid(int var1) {
      throw new IndexOutOfBoundsException("Empty list doesn't contain element at index " + var1 + '.');
   }

   public int getInt(@NotNull Void element) {
      Intrinsics.handleObject3(element, "element");
      return -1;
   }

   public int getInt2(@NotNull Void element) {
      Intrinsics.handleObject3(element, "element");
      return -1;
   }

   @Override
   public Iterator iterator() {
      return EmptyIterator.INSTANCE;
   }

   @Override
   public ListIterator listIterator() {
      return EmptyIterator.INSTANCE;
   }

   @Override
   public ListIterator listIterator(int var1) {
      if (var1 != 0) {
         throw new IndexOutOfBoundsException("Index: " + var1);
      } else {
         return EmptyIterator.INSTANCE;
      }
   }

   @Override
   public List subList(int var1, int var2) {
      if (var1 == 0 && var2 == 0) {
         return this;
      } else {
         throw new IndexOutOfBoundsException("fromIndex: " + var1 + ", toIndex: " + var2);
      }
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
   public boolean addAll(int var1, Collection var2) {
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
   public Object remove(int var1) {
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
   public Object get(int var1) {
      return this.getVoid(var1);
   }

   @Override
   public final int indexOf(Object var1) {
      return !(var1 instanceof Void) ? -1 : this.getInt((Void)var1);
   }

   @Override
   public final int lastIndexOf(Object var1) {
      return !(var1 instanceof Void) ? -1 : this.getInt2((Void)var1);
   }

   @Override
   public boolean add(Object var1) {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   @Override
   public void add(int var1, Object var2) {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   @Override
   public Object set(int var1, Object var2) {
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
