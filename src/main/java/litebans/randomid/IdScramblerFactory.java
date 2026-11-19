package litebans.randomid;

import java.util.Collection;
import java.util.Iterator;
import litebans.command.ArgParser;
import litebans.lib.kotlin.Pair;
import litebans.lib.kotlin.TuplesKt;
import litebans.lib.kotlin.UShort;
import litebans.lib.kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;

public final class IdScramblerFactory {
   private IdScramblerFactory() {
   }

   public final Pair getPair() {
      return IdScramblerState.getPair();
   }

   public final Pair getPair(@NotNull byte[] var1, int var2, int var3, @NotNull IdScrambler var4, boolean var5) {
      int var6 = var1.length - var2;
      int var7 = var5 ? Math.min(1600, Math.min(var1.length / 2, var6)) : var6;
      int var8 = 0;

      while (var8 < var7) {
         int var9 = var8++;
         Iterable var10 = var4.getList();
         boolean var16;
         if (var10 instanceof Collection && ((Collection)var10).isEmpty()) {
            var16 = false;
         } else {
            Iterator var12 = var10.iterator();

            while (true) {
               if (!var12.hasNext()) {
                  var16 = false;
                  break;
               }

               Object var13 = var12.next();
               IdScramblerState var14 = (IdScramblerState)var13;
               if (var14.checkByteArray(var1, var9, var9 + var2)) {
                  var16 = true;
                  break;
               }
            }
         }

         if (!var16) {
            short var15 = this.getShort(var1, var7, var9, var2, var3, var5);
            if ((var15 & '\uffff') > var3) {
               return this.getPair(var2, var1, var9, var15);
            }
         }
      }

      return this.getPair();
   }

   private final Pair getPair(int var1, byte[] var2, int var3, short var4) {
      byte[] var5 = new byte[var1];
      System.arraycopy(var2, var3, var5, 0, var1);
      return TuplesKt.getPair(UShort.getUShort(var4), var5);
   }

   private final short getShort(byte[] var1, int var2, int var3, int var4, int var5, boolean var6) {
      short var7 = 0;
      int var8 = Integer.MIN_VALUE;
      int var9 = var6 ? 568 : Integer.MAX_VALUE;
      byte[] var10 = var1;
      int var12 = 0;
      int var13 = 0;

      for (int var14 = var1.length; var13 < var14; var13++) {
         byte var10000 = var10[var13];
         int var17 = var12++;
         if (var17 > var9 && (var7 & '\uffff') < var5) {
            return var7;
         }

         if ((var8 <= var17 ? var17 < var2 : false) && ArgParser.checkByteArray(var1, var1, var3, var17, var17 + var4)) {
            var8 = var17 + var4;
            var7 = UShort.getShort((short)(var7 + 1));
         }
      }

      return var7;
   }

   public IdScramblerFactory(DefaultConstructorMarker var1) {
      this();
   }
}
