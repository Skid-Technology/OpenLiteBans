package litebans.lib.kotlin.text;

import java.util.Iterator;
import java.util.NoSuchElementException;
import litebans.lib.kotlin.Pair;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.jvm.internal.KMappedMarker;
import litebans.lib.kotlin.ranges.IntRange;
import litebans.lib.kotlin.ranges.RangesKt;
import litebans.lib.kotlin.sequences.GeneratorSequence;

public final class DelimitedRangesIterator implements Iterator, KMappedMarker {
   private int intValue;
   private int intValue2;
   private int intValue3;
   private IntRange intRange;
   private int intValue4;
   public final GeneratorSequence generatorSequence;

   public DelimitedRangesIterator(GeneratorSequence var1) {
      this.generatorSequence = var1;
      this.intValue = -1;
      this.intValue2 = RangesKt.getInt(GeneratorSequence.getInt(var1), 0, GeneratorSequence.getCharSequence(var1).length());
      this.intValue3 = this.intValue2;
   }

   private final void run() {
      if (this.intValue3 < 0) {
         this.intValue = 0;
         this.intRange = null;
      } else {
         label27: {
            label26: {
               if (GeneratorSequence.getInt2(this.generatorSequence) > 0) {
                  this.intValue4++;
                  if (this.intValue4 >= GeneratorSequence.getInt2(this.generatorSequence)) {
                     break label26;
                  }
               }

               if (this.intValue3 <= GeneratorSequence.getCharSequence(this.generatorSequence).length()) {
                  Pair var1 = (Pair)GeneratorSequence.getFunction2(this.generatorSequence)
                     .invoke(GeneratorSequence.getCharSequence(this.generatorSequence), this.intValue3);
                  if (var1 == null) {
                     this.intRange = new IntRange(this.intValue2, StringsKt.getInt(GeneratorSequence.getCharSequence(this.generatorSequence)));
                     this.intValue3 = -1;
                  } else {
                     int var2 = ((Number)var1.getObject3()).intValue();
                     int var3 = ((Number)var1.getObject22()).intValue();
                     this.intRange = RangesKt.getIntRange(this.intValue2, var2);
                     this.intValue2 = var2 + var3;
                     this.intValue3 = this.intValue2 + (var3 == 0 ? 1 : 0);
                  }
                  break label27;
               }
            }

            this.intRange = new IntRange(this.intValue2, StringsKt.getInt(GeneratorSequence.getCharSequence(this.generatorSequence)));
            this.intValue3 = -1;
         }

         this.intValue = 1;
      }
   }

   public IntRange getIntRange() {
      if (this.intValue == -1) {
         this.run();
      }

      if (this.intValue == 0) {
         throw new NoSuchElementException();
      } else {
         IntRange var10000 = this.intRange;
         Intrinsics.handleObject(this.intRange, "");
         IntRange var1 = var10000;
         this.intRange = null;
         this.intValue = -1;
         return var1;
      }
   }

   @Override
   public boolean hasNext() {
      if (this.intValue == -1) {
         this.run();
      }

      return this.intValue == 1;
   }

   @Override
   public void remove() {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
   }

   @Override
   public Object next() {
      return this.getIntRange();
   }
}
