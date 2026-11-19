package litebans.lib.kotlin.ranges;

import java.util.NoSuchElementException;
import litebans.lib.kotlin.collections.IntIterator;

public final class IntProgressionIterator extends IntIterator {
   private final int intValue;
   private final int intValue2;
   private boolean flag;
   private int intValue3;

   public IntProgressionIterator(int var1, int var2, int var3) {
      this.intValue = var3;
      this.intValue2 = var2;
      this.flag = this.intValue > 0 ? var1 <= var2 : var1 >= var2;
      this.intValue3 = this.flag ? var1 : this.intValue2;
   }

   @Override
   public boolean hasNext() {
      return this.flag;
   }

   @Override
   public int getInt() {
      int var1 = this.intValue3;
      if (var1 == this.intValue2) {
         if (!this.flag) {
            throw new NoSuchElementException();
         }

         this.flag = false;
      } else {
         this.intValue3 = this.intValue3 + this.intValue;
      }

      return var1;
   }
}
