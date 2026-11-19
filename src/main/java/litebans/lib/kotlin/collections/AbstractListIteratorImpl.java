package litebans.lib.kotlin.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;
import litebans.lib.kotlin.jvm.internal.KMappedMarker;

public class AbstractListIteratorImpl implements Iterator, KMappedMarker {
   private int intValue;
   public final AbstractList abstractList;

   public AbstractListIteratorImpl(AbstractList var1) {
      this.abstractList = var1;
   }

   public final int getIntValue() {
      return this.intValue;
   }

   public final void setIntValue(int var1) {
      this.intValue = var1;
   }

   @Override
   public boolean hasNext() {
      return this.intValue < this.abstractList.size();
   }

   @Override
   public Object next() {
      if (!this.hasNext()) {
         throw new NoSuchElementException();
      } else {
         AbstractList var10000 = this.abstractList;
         int var1 = this.intValue++;
         return var10000.get(var1);
      }
   }

   @Override
   public void remove() {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }
}
