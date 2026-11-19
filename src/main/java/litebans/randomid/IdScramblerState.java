package litebans.randomid;

import litebans.command.ArgParser;
import litebans.lib.kotlin.Pair;
import litebans.lib.kotlin.TuplesKt;
import litebans.lib.kotlin.UShort;
import litebans.lib.kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;

public final class IdScramblerState {
   public static final IdScramblerFactory idScramblerFactory = new IdScramblerFactory(null);
   private final byte[] byteArray;
   private byte byteValue;
   private short shortValue;
   public static final int intValue = 10;
   public static final int intValue2 = 10;
   public static final int intValue3 = 30;
   public static final int intValue4 = 1600;
   public static final int intValue5 = 600;
   public static final int intValue6 = 16000;
   public static final int intValue7 = 568;
   public static final int intValue8 = 70;
   private static final Pair pair = TuplesKt.getPair(UShort.getUShort((short)0), ArgParser.getByteArray());

   private IdScramblerState(byte[] var1, byte var2, short var3) {
      this.byteArray = var1;
      this.byteValue = var2;
      this.shortValue = var3;
   }

   public IdScramblerState(byte[] var1, byte var2, short var3, int var4, DefaultConstructorMarker var5) {
      this(var1, ((var4 & 2) != 0 ? 0 : var2), ((var4 & 4) != 0 ? 0 : var3), null);
   }

   public final byte[] getByteArray() {
      return this.byteArray;
   }

   public final byte getByteValue() {
      return this.byteValue;
   }

   public final void setByteValue(byte var1) {
      this.byteValue = var1;
   }

   public final int getInt() {
      return this.byteArray.length;
   }

   public final boolean checkByteArray(@NotNull byte[] var1, int var2, int var3) {
      return this.getInt() == var3 - var2 && this.byteArray[0] == var1[var2] ? ArgParser.checkByteArray(this.byteArray, var1, var2, var3) : false;
   }

   public final long getLong() {
      long var1 = (long)(this.getInt() - 2);
      long var3 = (long)this.shortValue & 65535L;
      var1 = var1++ * var1;
      long var5 = var1 * var1 * var1;
      long var7 = var3 * var3;
      long var9 = var7 * var7 * var5 * var5 * var5;
      return (var1 << 30) + (var9 >> 10);
   }

   @Override
   public String toString() {
      return super.toString();
   }

   public IdScramblerState(byte[] var1, byte var2, short var3, DefaultConstructorMarker var4) {
      this(var1, var2, var3);
   }

   public static final Pair getPair() {
      return pair;
   }
}
