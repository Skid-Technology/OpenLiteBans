package litebans.lib.kotlin.io;

import java.io.BufferedReader;
import java.util.Iterator;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;

public final class LinesSequence implements Sequence {
   private final BufferedReader reader;

   public LinesSequence(@NotNull BufferedReader reader) {
      Intrinsics.handleObject3(reader, "reader");
      this.reader = reader;
   }

   @Override
   public Iterator iterator() {
      return new LinesIterator(this);
   }

   public static final BufferedReader getBufferedReader(LinesSequence var0) {
      return var0.reader;
   }
}
