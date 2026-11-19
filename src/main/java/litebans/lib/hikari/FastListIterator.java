package litebans.lib.hikari;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class FastListIterator implements Iterator {
   private int intValue;
   public final FastList fastList;

   public FastListIterator(FastList var1) {
      this.fastList = var1;
   }

   @Override
   public boolean hasNext() {
      return this.intValue < FastList.getInt(this.fastList);
   }

   @Override
   public Object next() {
      if (this.intValue < FastList.getInt(this.fastList)) {
         return FastList.getObjectArray(this.fastList)[this.intValue++];
      } else {
         throw new NoSuchElementException("No more elements in FastList");
      }
   }
}
