package litebans.lib.kotlin.sequences;

import litebans.lib.kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public class SequencesKt__SequencesKt extends SequencesKt__SequencesJVMKt {
   public static final Sequence getSequence(@NotNull Sequence var0) {
      Intrinsics.handleObject3(var0, "<this>");
      return var0 instanceof ConstrainedOnceSequence ? var0 : new ConstrainedOnceSequence(var0);
   }
}
