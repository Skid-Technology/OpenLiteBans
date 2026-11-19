package litebans.lib.kotlin.collections;

import java.util.Iterator;
import litebans.lib.kotlin.jvm.internal.KMappedMarker;

public abstract class IntIterator implements Iterator, KMappedMarker {
   public abstract int getInt();

   @Override
   public void remove() {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   @Override
   public Object next() {
      return this.getInt();
   }
}
