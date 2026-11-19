package litebans.lib.kotlin.sequences;

import java.util.Iterator;
import litebans.lib.kotlin.jvm.internal.KMappedMarker;

public final class SequenceIterable implements Iterable, KMappedMarker {
   public final Sequence sequence;

   public SequenceIterable(Sequence var1) {
      this.sequence = var1;
   }

   @Override
   public Iterator iterator() {
      return this.sequence.iterator();
   }
}
