package litebans.lib.kotlin.sequences;

import litebans.lib.kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public class SequencesKt___SequencesKt extends SequencesKt___SequencesJvmKt {
   public static final Iterable getIterable(@NotNull Sequence var0) {
      Intrinsics.handleObject3(var0, "<this>");
      return new SequenceIterable(var0);
   }
}
