package litebans.lib.kotlin.collections;

import java.util.ListIterator;
import java.util.NoSuchElementException;
import litebans.lib.kotlin.jvm.internal.KMappedMarker;

public class AbstractListListIteratorImpl extends AbstractListIteratorImpl implements ListIterator, KMappedMarker {
   public final AbstractList abstractList2;

   public AbstractListListIteratorImpl(AbstractList var1, int var2) {
      super(var1);
      this.abstractList2 = var1;
      AbstractList.Companion.handleInt2(var2, this.abstractList2.size());
      this.setIntValue(var2);
   }

   @Override
   public boolean hasPrevious() {
      return this.getIntValue() > 0;
   }

   @Override
   public int nextIndex() {
      return this.getIntValue();
   }

   @Override
   public Object previous() {
      if (!this.hasPrevious()) {
         throw new NoSuchElementException();
      } else {
         AbstractList var10000 = this.abstractList2;
         this.setIntValue(this.getIntValue() + -1);
         return var10000.get(this.getIntValue());
      }
   }

   @Override
   public int previousIndex() {
      return this.getIntValue() - 1;
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
