package litebans.randomid;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Map.Entry;
import litebans.command.ArgParser;
import litebans.command.ArgUtils;
import litebans.command.TextArgs;
import litebans.core.LiteBansCore;
import litebans.lib.kotlin.Pair;
import litebans.lib.kotlin.UShort;
import litebans.lib.kotlin.Unit;
import litebans.lib.kotlin.collections.ArraysKt;
import litebans.lib.kotlin.collections.CollectionsKt;
import litebans.lib.kotlin.jvm.internal.DefaultConstructorMarker;
import litebans.lib.kotlin.jvm.internal.Function1;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.jvm.internal.TypeIntrinsics;
import litebans.lib.kotlin.text.Charsets;
import litebans.lib.kotlin.text.StringsKt;
import litebans.platform.LiteBansPlatform;
import litebans.util.SortComparator6;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class IdScrambler {
   public static final IdScramblerCompanion Companion = new IdScramblerCompanion(null);
   private final long longValue;
   private final LongBitSet longBitSet;
   private final byte[] byteArray;
   private final Bitflipper bitflipper;
   private final boolean flag;
   private long longValue2;
   private int intValue;
   private int intValue2;
   private final byte[] byteArray2;
   private final byte[] byteArray3;
   private final byte[] byteArray4;
   private List list;
   private List list2;
   private byte[][] byteArrayArray;
   private boolean flag2;
   private boolean flag3;
   public static final boolean flag4 = false;
   public static final int intValue3 = 44;
   public static final int intValue4 = 80;
   public static final boolean flag5 = false;
   public static final char charValue = '-';
   public static final byte byteValue = 79;
   public static final int intValue5 = 12;
   public static final int intValue6 = 40;
   public static final byte byteValue2 = 127;
   public static final int intValue7 = 128;
   public static final int intValue8 = 1500;
   public static final int intValue9 = 16;
   public static final int intValue10 = 7;
   public static final int intValue11 = -7;
   public static final int intValue12 = 140;
   public static final int intValue13 = 4;
   public static final int intValue14 = 28;
   public static final boolean flag6 = false;
   public static final int intValue15 = 4096;
   public static final int intValue16 = 1024;
   public static final int intValue17 = 512;
   public static final int intValue18 = 2400;
   public static final int intValue19 = 1;
   public static final boolean flag7 = false;
   public static final boolean flag8 = false;
   public static final boolean flag9 = false;
   public static final boolean flag10 = false;
   public static final boolean flag11 = false;
   public static final boolean flag12 = false;
   public static final boolean flag13 = false;
   public static final boolean flag14 = false;
   public static final boolean flag15 = false;

   private IdScrambler(long var1, LongBitSet var3, byte[] var4, Bitflipper var5, boolean var6, long var7, int var9, int var10) {
      this.longValue = var1;
      this.longBitSet = var3;
      this.byteArray = var4;
      this.bitflipper = var5;
      this.flag = var6;
      this.longValue2 = var7;
      this.intValue = var9;
      this.intValue2 = var10;
      this.byteArray2 = new byte[80];
      this.byteArray3 = new byte[80];
      byte[] var11 = new byte[]{32, 16, 8, 4, 2, 1};
      this.byteArray4 = var11;
      this.list = CollectionsKt.getList();
      this.list2 = CollectionsKt.getList();
      this.run();
   }

   public IdScrambler(
      long var1, LongBitSet var3, byte[] var4, Bitflipper var5, boolean var6, long var7, int var9, int var10, int var11, DefaultConstructorMarker var12
   ) {
      this(var1, var3, var4, ((var11 & 8) != 0 ? new Bitflipper(var3, null, 2, null) : var5), ((var11 & 16) != 0 ? false : var6), ((var11 & 32) != 0 ? 0L : var7), ((var11 & 64) != 0 ? 0 : var9), ((var11 & 128) != 0 ? 0 : var10), null);
   }

   public final long getLongValue() {
      return this.longValue;
   }

   public final LongBitSet getLongBitSet() {
      return this.longBitSet;
   }

   public final Bitflipper getBitflipper() {
      return this.bitflipper;
   }

   public final boolean isFlag() {
      return this.flag;
   }

   public final byte[] getByteArray2() {
      return this.byteArray2;
   }

   public final byte[] getByteArray3() {
      return this.byteArray3;
   }

   public final List getList() {
      return this.list;
   }

   public final void setList(@NotNull List var1) {
      this.list = var1;
   }

   public final List getList2() {
      return this.list2;
   }

   public final void setList2(@NotNull List var1) {
      this.list2 = var1;
   }

   public final byte[][] getByteArrayArray() {
      return this.byteArrayArray;
   }

   public final void setByteArrayArray(@Nullable byte[][] var1) {
      this.byteArrayArray = var1;
   }

   public final boolean isFlag2() {
      return this.flag2;
   }

   public final void setFlag2(boolean var1) {
      this.flag2 = var1;
   }

   public final void setFlag3(boolean var1) {
      this.flag3 = var1;
   }

   public final StringBuilder getStringBuilder(@NotNull byte[] var1) {
      StringBuilder var2 = new StringBuilder(var1.length + 1);
      StringBuilder var3 = var2;
      byte[] var5 = var1;
      int var7 = 0;

      for (int var8 = var1.length; var7 < var8; var7++) {
         byte var9 = var5[var7];
         var3.append((char)(this.getByteArray3()[var9] + 44));
      }

      return var2;
   }

   public final void run() {
      this.byteArray2[53] = 1;
      this.byteArray3[1] = 53;
      this.byteArray2[57] = 2;
      this.byteArray3[2] = 57;
      this.byteArray2[71] = 3;
      this.byteArray3[3] = 71;
      this.byteArray2[64] = 4;
      this.byteArray3[4] = 64;
      this.byteArray2[70] = 5;
      this.byteArray3[5] = 70;
      this.byteArray2[61] = 6;
      this.byteArray3[6] = 61;
      this.byteArray2[65] = 7;
      this.byteArray3[7] = 65;
      this.byteArray2[66] = 8;
      this.byteArray3[8] = 66;
      this.byteArray2[67] = 9;
      this.byteArray3[9] = 67;
      this.byteArray2[72] = 10;
      this.byteArray3[10] = 72;
      this.byteArray2[63] = 11;
      this.byteArray3[11] = 63;
      this.byteArray2[55] = 12;
      this.byteArray3[12] = 55;
      this.byteArray2[56] = 13;
      this.byteArray3[13] = 56;
      this.byteArray2[73] = 14;
      this.byteArray3[14] = 73;
      this.byteArray2[51] = 15;
      this.byteArray3[15] = 51;
      this.byteArray2[5] = 16;
      this.byteArray3[16] = 5;
      this.byteArray2[54] = 17;
      this.byteArray3[17] = 54;
      this.byteArray2[68] = 18;
      this.byteArray3[18] = 68;
      this.byteArray2[60] = 19;
      this.byteArray3[19] = 60;
      this.byteArray2[78] = 20;
      this.byteArray3[20] = 78;
      this.byteArray2[77] = 21;
      this.byteArray3[21] = 77;
      this.byteArray2[6] = 22;
      this.byteArray3[22] = 6;
      this.byteArray2[59] = 23;
      this.byteArray3[23] = 59;
      this.byteArray2[7] = 24;
      this.byteArray3[24] = 7;
      this.byteArray2[74] = 25;
      this.byteArray3[25] = 74;
      this.byteArray2[4] = 26;
      this.byteArray3[26] = 4;
      this.byteArray2[76] = 27;
      this.byteArray3[27] = 76;
      this.byteArray2[62] = 28;
      this.byteArray3[28] = 62;
      this.byteArray2[58] = 29;
      this.byteArray3[29] = 58;
      this.byteArray2[8] = 30;
      this.byteArray3[30] = 8;
      this.byteArray2[9] = 31;
      this.byteArray3[31] = 9;
      this.byteArray2[13] = 32;
      this.byteArray3[32] = 13;
      this.byteArray2[12] = 33;
      this.byteArray3[33] = 12;
      this.byteArray2[10] = 34;
      this.byteArray3[34] = 10;
      this.byteArray2[11] = 35;
      this.byteArray3[35] = 11;
      this.byteArray2[75] = 36;
      this.byteArray3[36] = 75;
      this.byteArray2[69] = 37;
      this.byteArray3[37] = 69;
      this.byteArray2[1] = 79;
      this.byteArray3[79] = 1;
   }

   public final int getInt(char var1) {
      return this.getByteArray2()[var1 - 44] << 16;
   }

   public final void handleIdScramblerState(@NotNull IdScramblerState var1) {
      List var10000 = this.list;
      Intrinsics.handleObject(this.list, "");
      TypeIntrinsics.getList(var10000).add(var1);
   }

   public final void run2() {
      Iterable var1 = this.list;
      Collection var4 = new ArrayList();

      for (Object var7 : var1) {
         IdScramblerState var8 = (IdScramblerState)var7;
         if (var8.getByteValue() != 0) {
            var4.add(var7);
         }
      }

      this.list = (List)var4;
   }

   public final void handleByteArray(@NotNull byte[] var1, @NotNull ByteArrayOutputStream var2) {
      try {
      int var3 = 0;

      for (int var4 = var1.length; var3 < var4; var3++) {
         byte var5 = var1[var3];
         if (var5 >= 128 || var5 < -8) {
            Object var10000;
            label32: {
               for (Object var9 : this.list) {
                  IdScramblerState var10 = (IdScramblerState)var9;
                  if (var10.getByteValue() == var5) {
                     var10000 = var9;
                     break label32;
                  }
               }

               var10000 = null;
            }

            IdScramblerState var6 = (IdScramblerState)var10000;
            if (var6 != null) {
               var2.write(var6.getByteArray());
               continue;
            }
         }

         var2.write(var5);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   public final byte[] getByteArray(@NotNull byte[] var1, @NotNull IdScramblerState var2) {
      byte[] var3 = var2.getByteArray();
      byte[] var4 = new byte[var1.length];
      int var5 = 0;
      int var6 = var1.length - var3.length;
      int var7 = Integer.MIN_VALUE;
      boolean var8 = false;
      int var9 = 0;
      byte[] var10 = var1;
      int var12 = 0;
      int var13 = 0;

      for (int var14 = var1.length; var13 < var14; var13++) {
         byte var15 = var10[var13];
         int var17 = var12++;
         if (var17 >= var7) {
            if (var17 < var6 && ArgParser.checkByteArray(var3, var1, var17, var17 + var3.length)) {
               var8 = true;
               int var20 = var5++;
               var4[var20] = var2.getByteValue();
               var9++;
               var7 = var17 + var3.length;
               continue;
            }

            var8 = false;
         }

         if (!var8) {
            int var19 = var5++;
            var4[var19] = var15;
         }
      }

      return var5 == var1.length ? var1 : ArraysKt.getByteArray(var4, 0, var5);
   }

   public final byte[] getByteArray(@NotNull byte[] var1, int var2, int var3, @NotNull Function1 var4) {
      byte[] var5 = var1;
      byte var6 = (byte)var2;
      List var7 = this.list;
      int var8 = 0;

      for (Object var12 : var7) {
         IdScramblerState var13 = (IdScramblerState)var12;
         if (++var8 > var3) {
            return var5;
         }

         boolean var15 = var13.getByteValue() == 0;
         if (var15) {
            byte var16 = var6++;
            var13.setByteValue(var16);
         } else {
            var6 = (byte)(var13.getByteValue() + 1);
         }

         byte[] var17 = this.getByteArray(var5, var13);
         if (var17 != var5) {
            var5 = var17;
            var4.invoke(var13);
         } else if (var15) {
            var13.setByteValue((byte)0);
            var6 += -1;
            var8 += -1;
         }
      }

      CharSequence var10000 = var1.toString();
      return var5;
   }

   public static byte[] getByteArrayDefault(IdScrambler var0, byte[] var1, int var2, int var3, Function1 var4, int var5, Object var6) {
      if ((var5 & 2) != 0) {
         var2 = 128;
      }

      if ((var5 & 4) != 0) {
         var3 = 70;
      }

      if ((var5 & 8) != 0) {
         var4 = a -> getUnit((IdScramblerState) a);
      }

      return var0.getByteArray(var1, var2, var3, var4);
   }

   public final void handleByteArray(@NotNull byte[] var1, @NotNull byte[] var2, boolean var3, boolean var4) {
      if (var4) {
         this.longBitSet.handleLong(this.longValue);
      }

      if (var3) {
         byte[] var5 = var2;
         int var7 = 0;
         int var8 = 0;

         for (int var9 = var2.length; var8 < var9; var8++) {
            byte var10000 = var5[var8];
            int var11 = var7++;
            var2[var11] = this.getByte(var1[var11]);
         }
      } else {
         byte[] var12 = var2;
         int var13 = 0;
         int var14 = 0;

         for (int var15 = var2.length; var14 < var15; var14++) {
            byte var18 = var12[var14];
            int var16 = var13++;
            var2[var16] = this.getByte2(var1[var16]);
         }
      }
   }

   public static void handleByteArrayDefault(IdScrambler var0, byte[] var1, byte[] var2, boolean var3, boolean var4, int var5, Object var6) {
      if ((var5 & 4) != 0) {
         var3 = true;
      }

      if ((var5 & 8) != 0) {
         var4 = true;
      }

      var0.handleByteArray(var1, var2, var3, var4);
   }

   public final void handleByteArray(@NotNull byte[] var1, int var2, boolean var3, boolean var4) {
      for (int var5 = 0; var5 < var2; var5++) {
         int var8 = var5 * 2;
         byte var9 = BitArrayUtils.getByte2(this.byteArray, this.longBitSet);
         if (var4) {
            var9 += BitArrayUtils.getByte2(this.byteArray, this.longBitSet);
         }

         var1[var8] = var9;
         byte var10 = BitArrayUtils.getByte2(this.bitflipper.getTable(), this.longBitSet);
         if (var3) {
            var1[var8 + 1] = var10;
         }
      }
   }

   public final int getInt(@NotNull byte[] var1, int var2, @NotNull CharSequence var3, boolean var4) {
      int var5 = 0;

      for (int var6 = 0; var6 < var2; var6++) {
         int var9 = var6 * 2;
         byte var10 = var1[var9];
         char var12 = var3.charAt(var6);
         byte var14 = (byte)(this.getByteArray2()[var12 - ','] - var10);
         if (var4) {
            byte var11 = var1[var9 + 1];
            var14 ^= var11;
         }

         var5 += this.getInt(var14);
         if (var5 <= -7) {
            return var5;
         }
      }

      return var5;
   }

   public final byte[] getByteArray() {
      byte[] var1 = this.bitflipper.getTable();
      int var2 = this.list2.size();
      IdScramblerParams var3 = new IdScramblerParams(Math.max(1, var2 / 4096), Math.max(1, var2 / 1024), Math.max(1, var2 / 512));
      HashMap var4 = new HashMap(32);
      HashMap var5 = new HashMap(this.bitflipper.getTable().length);

      for (Object var7o : this.list2) {
         Pair var7 = (Pair) var7o;
         int var8 = ((Number)var7.getObject()).intValue();
         byte var9 = ((Number)var7.getObject2()).byteValue();
         byte var10 = 0;
         byte[] var11 = this.byteArray4;
         int var12 = 0;

         for (int var13 = var11.length; var12 < var13; var12++) {
            byte var14 = var11[var12];
            if ((byte)(var9 ^ var14) < var9) {
               var10 |= var14;
            }
         }

         Map var34 = var5;
         Integer var36 = var8;
         Object var15 = var34.get(var36);
         Object var10000;
         if (var15 == null) {
            ArrayList var16 = new ArrayList(32);
            var34.put(var36, var16);
            var10000 = var16;
         } else {
            var10000 = var15;
         }

         ArrayList var33 = (ArrayList)var10000;
         var33.add((byte)var10);
      }

      Map var29 = var5;

      for (Object var31o : var29.entrySet()) {
         Entry var31 = (Entry) var31o;
         Entry var32 = var31;
         ArrayList var35 = (ArrayList)var31.getValue();
         Iterator var37 = var35.iterator();

         while (var37.hasNext()) {
            byte var39 = ((Number)var37.next()).byteValue();
            Map var40 = var4;
            Byte var42 = var39;
            Object var18 = var40.get(var42);
            Object var49;
            if (var18 != null) {
               var49 = var18;
            } else {
               Iterable var20 = var35;
               int var48;
               if (var20 instanceof Collection && ((Collection)var20).isEmpty()) {
                  var48 = 0;
               } else {
                  int var22 = 0;

                  for (Object var24 : var20) {
                     byte var25 = ((Number)var24).byteValue();
                     if (var25 == var39) {
                        if (++var22 < 0) {
                           CollectionsKt.run2();
                        }
                     }
                  }

                  var48 = var22;
               }

               Integer var27 = var48;
               var40.put(var42, var27);
               var49 = var27;
            }

            int var28 = ((Number)var49).intValue();
            this.handleInt(var28, var39, ((Number)var32.getKey()).intValue(), var3);
         }

         Iterable var47 = var4.entrySet();
         Iterator var41 = var47.iterator();
         if (!var41.hasNext()) {
            throw new NoSuchElementException();
         }

         Object var43 = var41.next();
         Object var50;
         if (!var41.hasNext()) {
            var50 = var43;
         } else {
            Entry var17 = (Entry)var43;
            int var44 = ((Number)var17.getValue()).intValue();

            do {
               Object var45 = var41.next();
               Entry var19 = (Entry)var45;
               int var46 = ((Number)var19.getValue()).intValue();
               if (var44 < var46) {
                  var43 = var45;
                  var44 = var46;
               }
            } while (var41.hasNext());

            var50 = var43;
         }

         Entry var38 = (Entry)var50;
         this.handleInt(((Number)var38.getValue()).intValue(), ((Number)var38.getKey()).byteValue(), ((Number)var32.getKey()).intValue(), var3);
         var4.clear();
      }

      this.list2 = CollectionsKt.getList();
      return var1;
   }

   private final void handleInt(int var1, byte var2, int var3, IdScramblerParams var4) {
      if (var2 <= 7 && var1 >= var4.getIntValue() || var2 <= 31 && var1 >= var4.getIntValue2() || var1 > var4.getIntValue3()) {
         this.bitflipper.getTable()[var3] = var2;
      }
   }

   private final int getInt(byte var1) {
      byte var10000;
      switch (var1) {
         case -3:
         case -1:
         case 1:
         case 13:
            var10000 = 3;
            break;
         case 0:
            var10000 = 7;
            break;
         default:
            var10000 = -1;
      }

      return var10000;
   }

   public final byte getByte(byte var1) {
      if (var1 == 79) {
         this.flag3 = !this.flag3;
         return var1;
      } else {
         byte var2 = BitArrayUtils.getByte2(this.byteArray, this.longBitSet);
         int var3 = var1 - var2;
         int var4 = this.bitflipper.getInt(this, (byte)var3);
         return (byte)(var3 ^ var4);
      }
   }

   public final byte getByte2(byte var1) {
      if (var1 == 79) {
         this.flag3 = !this.flag3;
         return 0;
      } else {
         byte var2 = BitArrayUtils.getByte2(this.byteArray, this.longBitSet);
         byte var3 = (byte)this.bitflipper.getInt(this, var1);
         return (byte)((byte)(var1 ^ var3) + var2);
      }
   }

   public final byte[] getByteArray(
      @Nullable LiteBansPlatform var1, @NotNull String var2, boolean var3, boolean var4, @NotNull LongBitSet var5, @NotNull byte[] var6
   ) {
      this.longBitSet.handleLong(this.longValue);
      CharSequence var10000 = var2;
      char[] var8 = new char[]{','};
      List var7 = CollectionsKt.getList(StringsKt.getList(var10000, var8, false, 0, 6, null));
      if (var7.size() <= 1) {
         if (var1 != null) {
            LiteBansCore var44 = (LiteBansCore)var1.getModule(LiteBansCore.class);
            if (var44 != null) {
               LiteBansCore var29 = var44;
               String var30 = var2.toString();
               String[] var10003;
               if (Intrinsics.checkObject(var30, "sort")) {
                  String[] var14 = new String[]{"00"};
                  var10003 = var14;
               } else {
                  String[] var31 = new String[]{"01:" + var30};
                  var10003 = var31;
               }

               String[] var33 = var10003;
               TextArgs var45 = new TextArgs(var29, Arrays.copyOf(var33, var33.length));
               if (var45.getInt() == var29.hashCode()) {
                  throw new IllegalArgumentException();
               }
            }
         }

         IdScrambler var26 = this;
         byte[] var27 = var2.getBytes(Charsets.charset5);
         byte[] var13 = var27;
         int var32 = 0;
         int var34 = 0;

         for (int var36 = var27.length; var34 < var36; var34++) {
            byte var38 = var13[var34];
            int var41 = var32++;
            var27[var41] = var26.getByteArray2()[var38 - 44];
         }

         return var27;
      } else {
         StringBuilder var25 = new StringBuilder(16);
         StringBuilder var9 = new StringBuilder(var2.length());
         IdScrambler var10 = this;
         int var12 = 1500;
         String var16 = (String)var7.get(0);

         while (var7.size() != 1) {
            int var17 = var16.length() + 1;
            int var18 = var12 / (var17 + 4);
            long var19 = var10.getLongBitSet().getLongArray()[0];
            long var21 = var10.getLongBitSet().getLongArray()[1];
            boolean var23 = false;
            var10.handleByteArray(var6, var17, var3, var23);
            StringsKt.getStringBuilder(var25).append(var16).append(',');
            int var24 = var10.getInt(var6, var17, var25, var3);
            if (var24 >= Math.min(7 * var17, var18)) {
               var12 += 4 * var17;
               var7.remove(var16);
               var9.append(var16).append(',');
               var16 = (String)var7.get(0);
            } else {
               var12--;
               var10.getLongBitSet().handleLong(var19, var21);
               if (var7.size() < 10) {
                  var12 -= 128;
               }

               var16 = (String)BitArrayUtils.getObject(var7, var5);
            }
         }

         var9.append((String)var7.get(0));
         var10 = this;
         byte[] var11 = ArgUtils.getByteArray(var9, Charsets.charset5);
         byte[] var15 = var11;
         int var35 = 0;
         int var37 = 0;

         for (int var39 = var11.length; var37 < var39; var37++) {
            byte var40 = var15[var37];
            int var42 = var35++;
            var11[var42] = var10.getByteArray2()[var40 - 44];
         }

         return var11;
      }
   }

   public final void handleByteArray2(@NotNull byte[] var1, int var2, boolean var3, boolean var4) {
      int var5 = var2;
      int var6 = 10;
      if (!(this.list instanceof ArrayList)) {
         this.list = new ArrayList(512);
      }

      if (var3) {
         var6 = 10;
      }

      while (var5-- > 0 && var6 >= 2) {
         int var8 = Math.max(1, this.getInt(var6));
         Pair var9 = IdScramblerState.idScramblerFactory.getPair(var1, var6, var8, this, !var3);
         if (var9 == IdScramblerState.idScramblerFactory.getPair()) {
            var6--;
         } else {
            short var10 = ((UShort)var9.getObject()).getShortValue();
            byte[] var11 = (byte[])var9.getObject2();
            this.handleIdScramblerState(new IdScramblerState(var11, (byte)0, var10, 2, null));
         }
      }

      this.run3();
   }

   public static void handleByteArray2Default(IdScrambler var0, byte[] var1, int var2, boolean var3, boolean var4, int var5, Object var6) {
      if ((var5 & 2) != 0) {
         var2 = 16000;
      }

      if ((var5 & 4) != 0) {
         var3 = false;
      }

      if ((var5 & 8) != 0) {
         var4 = false;
      }

      var0.handleByteArray2(var1, var2, var3, var4);
   }

   private final int getInt(int var1) {
      return 30 / var1 / var1 - 2;
   }

   public final void run3() {
      if (!this.list.isEmpty()) {
         List var10000 = this.list;
         Intrinsics.handleObject(this.list, "");
         List var1 = TypeIntrinsics.getList(var10000);
         if (var1.size() > 1) {
            CollectionsKt.handleList(var1, new SortComparator6());
         }
      }
   }

   private static final Unit getUnit(IdScramblerState var0) {
      return Unit.INSTANCE;
   }

   public IdScrambler(long var1, LongBitSet var3, byte[] var4, Bitflipper var5, boolean var6, long var7, int var9, int var10, DefaultConstructorMarker var11) {
      this(var1, var3, var4, var5, var6, var7, var9, var10);
   }
}
