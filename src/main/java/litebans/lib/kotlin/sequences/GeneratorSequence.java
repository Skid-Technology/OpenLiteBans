package litebans.lib.kotlin.sequences;

import java.util.Iterator;
import litebans.lib.kotlin.jvm.internal.Function2;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.text.DelimitedRangesIterator;
import org.jetbrains.annotations.NotNull;

public final class GeneratorSequence implements Sequence {
   private final CharSequence input;
   private final int intValue;
   private final int intValue2;
   private final Function2 getNextMatch;

   public GeneratorSequence(@NotNull CharSequence input, int intValue, int intValue2, @NotNull Function2 getNextMatch) {
      Intrinsics.handleObject3(input, "input");
      Intrinsics.handleObject3(getNextMatch, "getNextMatch");
      this.input = input;
      this.intValue = intValue;
      this.intValue2 = intValue2;
      this.getNextMatch = getNextMatch;
   }

   @Override
   public Iterator iterator() {
      return new DelimitedRangesIterator(this);
   }

   public static final int getInt(GeneratorSequence var0) {
      return var0.intValue;
   }

   public static final CharSequence getCharSequence(GeneratorSequence var0) {
      return var0.input;
   }

   public static final int getInt2(GeneratorSequence var0) {
      return var0.intValue2;
   }

   public static final Function2 getFunction2(GeneratorSequence var0) {
      return var0.getNextMatch;
   }
}
