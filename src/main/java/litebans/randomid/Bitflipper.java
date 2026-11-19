package litebans.randomid;

import java.util.ArrayList;
import litebans.command.ArgParser;
import litebans.lib.kotlin.TuplesKt;
import litebans.lib.kotlin.collections.ArraysKt;
import litebans.lib.kotlin.collections.CollectionsKt;
import litebans.lib.kotlin.jvm.internal.DefaultConstructorMarker;
import litebans.lib.kotlin.text.CharsKt;
import org.jetbrains.annotations.NotNull;

public final class Bitflipper {
   private final LongBitSet longBitSet;
   private byte[] table;

   public Bitflipper(@NotNull LongBitSet var1, @NotNull byte[] var2) {
      this.longBitSet = var1;
      this.table = var2;
      if (this.table == ArgParser.getByteArray()) {
         this.table = new byte[8192];
      }
   }

   public Bitflipper(LongBitSet var1, byte[] var2, int var3, DefaultConstructorMarker var4) {
      this(var1, ((var3 & 2) != 0 ? ArgParser.getByteArray() : var2));
   }

   public final byte[] getTable() {
      return this.table;
   }

   public final void setTable(@NotNull byte[] var1) {
      this.table = var1;
   }

   public final int getInt(@NotNull IdScrambler var1, byte var2) {
      if (var2 < 0) {
         this.longBitSet.getLong();
         return 0;
      } else {
         int var3 = this.longBitSet.getInt2(this.table.length);
         if (var1.isFlag2()) {
            if (var1.getList2().isEmpty()) {
               var1.setList2(new ArrayList(this.table.length));
            }

            var1.setList2(CollectionsKt.getList(var1.getList2(), TuplesKt.getPair(var3, var2)));
            return 0;
         } else {
            return this.table[var3];
         }
      }
   }

   @Override
   public String toString() {
      return "Bitflipper(table=" + ArraysKt.getString(this.table, ",", null, null, 0, null, a -> getCharSequence((Byte) a), 30, null) + ')';
   }

   private static final CharSequence getCharSequence(byte var0) {
      return "0b" + Integer.toString(var0, CharsKt.getInt(2));
   }
}