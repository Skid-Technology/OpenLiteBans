package litebans.lib.kotlin.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.jvm.internal.KMappedMarker;
import org.jetbrains.annotations.NotNull;

public final class ArrayIterator implements Iterator, KMappedMarker {
   private final Object[] array;
   private int intValue;

   public ArrayIterator(@NotNull Object[] array) {
      Intrinsics.handleObject3(array, "array");
      this.array = array;
   }

   @Override
   public boolean hasNext() {
      return this.intValue < this.array.length;
   }

   @Override
   public Object next() {
      try {
         Object[] var10000 = this.array;
         int var1 = this.intValue++;
         return var10000[var1];
      } catch (ArrayIndexOutOfBoundsException var3) {
         this.intValue--;
         throw new NoSuchElementException(var3.getMessage());
      }
   }

   @Override
   public void remove() {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }
}
