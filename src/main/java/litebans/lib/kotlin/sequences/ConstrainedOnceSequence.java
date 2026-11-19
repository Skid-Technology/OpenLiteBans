package litebans.lib.kotlin.sequences;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReference;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class ConstrainedOnceSequence implements Sequence {
   private final AtomicReference atomicReference;

   public ConstrainedOnceSequence(@NotNull Sequence sequence) {
      Intrinsics.handleObject3(sequence, "sequence");
      this.atomicReference = new AtomicReference<>(sequence);
   }

   @Override
   public Iterator iterator() {
      Sequence var10000 = (Sequence)this.atomicReference.getAndSet(null);
      if (var10000 == null) {
         throw new IllegalStateException("This sequence can be consumed only once.");
      } else {
         Sequence var1 = var10000;
         return var1.iterator();
      }
   }
}
