package litebans.lib.kotlin.collections;

import java.util.ListIterator;
import java.util.NoSuchElementException;
import litebans.lib.kotlin.jvm.internal.KMappedMarker;

public final class EmptyIterator implements ListIterator, KMappedMarker {
   public static final EmptyIterator INSTANCE = new EmptyIterator();

   private EmptyIterator() {
   }

   @Override
   public boolean hasNext() {
      return false;
   }

   @Override
   public boolean hasPrevious() {
      return false;
   }

   @Override
   public int nextIndex() {
      return 0;
   }

   @Override
   public int previousIndex() {
      return -1;
   }

   public Void getVoid() {
      throw new NoSuchElementException();
   }

   public Void getVoid2() {
      throw new NoSuchElementException();
   }

   @Override
   public void remove() {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   @Override
   public Object next() {
      return this.getVoid();
   }

   @Override
   public Object previous() {
      return this.getVoid2();
   }

   @Override
   public void set(Object var1) {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   @Override
   public void add(Object var1) {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }
}
