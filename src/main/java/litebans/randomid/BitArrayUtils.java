package litebans.randomid;

import java.util.Collection;
import java.util.List;
import litebans.lib.kotlin.UByte;
import litebans.lib.kotlin.collections.ArraysKt;
import litebans.lib.kotlin.collections.CollectionsKt;
import org.jetbrains.annotations.NotNull;

public final class BitArrayUtils {
   public static final int getInt(@NotNull int[] var0, @NotNull LongBitSet var1) {
      return var0.length <= 1 ? ArraysKt.getInt(var0) : var0[var1.getInt(var0.length)];
   }

   public static final byte getByte(@NotNull byte[] var0, @NotNull LongBitSet var1) {
      return var0.length <= 1 ? ArraysKt.getByte(var0) : var0[var1.getInt2(var0.length)];
   }

   public static final byte getByte2(@NotNull byte[] var0, @NotNull LongBitSet var1) {
      return var0[var1.getInt2(var0.length)];
   }

   public static final byte getByte3(@NotNull byte[] var0, @NotNull LongBitSet var1) {
      return UByteArrayUtils.getInt(var0) <= 1
         ? UByte.getByte(ArraysKt.getByte(var0))
         : UByteArrayUtils.getByte(var0, var1.getInt(UByteArrayUtils.getInt(var0)));
   }

   public static final Object getObject(@NotNull Object[] var0, @NotNull LongBitSet var1) {
      return var0.length <= 1 ? ArraysKt.getObject(var0) : var0[var1.getInt(var0.length)];
   }

   public static final byte[] getByteArray(@NotNull byte[] var0, @NotNull LongBitSet var1) {
      byte[] var3 = var0;

      for (int var5 = ArraysKt.getInt(var0); 0 < var5; var5--) {
         int var6 = var1.getInt2(var5 + 1);
         byte var7 = var3[var5];
         var3[var5] = var3[var6];
         var3[var6] = var7;
      }

      return var0;
   }

   public static final byte[] getByteArray(@NotNull byte[] var0, @NotNull LongBitSet var1, int var2) {
      byte[] var4 = var0;

      for (int var6 = 0; var6 < var2; var6++) {
         int var9 = var1.getInt2(var4.length);
         int var10 = var1.getInt2(var4.length - 1);
         byte var11 = var4[var10];
         var4[var9] = var11;
         var4[var10 + 1] = var11;
      }

      return var0;
   }

   public static final List getList(@NotNull List var0, @NotNull LongBitSet var1) {
      List var3 = var0;

      for (int var5 = CollectionsKt.getInt(var0); 0 < var5; var5--) {
         int var6 = var1.getInt2(var5 + 1);
         var3.set(var6, var3.set(var5, var3.get(var6)));
      }

      return var0;
   }

   public static final Object getObject(@NotNull List var0, @NotNull LongBitSet var1) {
      return var0.size() <= 1 ? CollectionsKt.getObject(var0) : var0.get(var1.getInt2(var0.size()));
   }

   public static final Object getObject(@NotNull Collection var0, @NotNull LongBitSet var1) {
      return var0.size() <= 1 ? CollectionsKt.getObject(var0) : CollectionsKt.getObject(var0, var1.getInt2(var0.size()));
   }

   public static final Object getObject2(@NotNull Collection var0, @NotNull LongBitSet var1) {
      return var0.isEmpty() ? null : getObject(var0, var1);
   }

   public static final void handleByteArray(@NotNull byte[] var0, @NotNull LongBitSet var1, @NotNull Number var2) {
      var0[var1.getInt2(var0.length)] = var2.byteValue();
   }

   public static final List getList2(@NotNull List var0, @NotNull LongBitSet var1) {
      var0.remove(var1.getInt2(var0.size()));
      return var0;
   }
}
