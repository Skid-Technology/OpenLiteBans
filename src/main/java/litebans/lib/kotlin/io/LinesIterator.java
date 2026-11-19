package litebans.lib.kotlin.io;

import java.util.Iterator;
import java.util.NoSuchElementException;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.jvm.internal.KMappedMarker;

public final class LinesIterator implements Iterator, KMappedMarker {
   private String string;
   private boolean flag;
   public final LinesSequence linesSequence;

   public LinesIterator(LinesSequence var1) {
      this.linesSequence = var1;
   }

   @Override
   public boolean hasNext() {
      try {
      if (this.string == null && !this.flag) {
         this.string = LinesSequence.getBufferedReader(this.linesSequence).readLine();
         if (this.string == null) {
            this.flag = true;
         }
      }

      return this.string != null;
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   public String getString() {
      if (!this.hasNext()) {
         throw new NoSuchElementException();
      } else {
         String var1 = this.string;
         this.string = null;
         Intrinsics.handleObject(var1);
         return var1;
      }
   }

   @Override
   public void remove() {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   @Override
   public Object next() {
      return this.getString();
   }
}
