package litebans.lib.json;

import java.io.Reader;

public class JsonLexer {
   public static final int intValue = -1;
   private static final int intValue2 = 16384;
   public static final int intValue3 = 0;
   public static final int intValue4 = 2;
   private static final int[] intArray = new int[]{0, 0, 1, 1};
   private static final String string = "\t\u0000\u0001\u0007\u0001\u0007\u0002\u0000\u0001\u0007\u0012\u0000\u0001\u0007\u0001\u0000\u0001\t\b\u0000\u0001\u0006\u0001\u0019\u0001\u0002\u0001\u0004\u0001\n\n\u0003\u0001\u001a\u0006\u0000\u0004\u0001\u0001\u0005\u0001\u0001\u0014\u0000\u0001\u0017\u0001\b\u0001\u0018\u0003\u0000\u0001\u0012\u0001\u000b\u0002\u0001\u0001\u0011\u0001\f\u0005\u0000\u0001\u0013\u0001\u0000\u0001\r\u0003\u0000\u0001\u000e\u0001\u0014\u0001\u000f\u0001\u0010\u0005\u0000\u0001\u0015\u0001\u0000\u0001\u0016ﾂ\u0000";
   private static final char[] charArray = getCharArray(
      "\t\u0000\u0001\u0007\u0001\u0007\u0002\u0000\u0001\u0007\u0012\u0000\u0001\u0007\u0001\u0000\u0001\t\b\u0000\u0001\u0006\u0001\u0019\u0001\u0002\u0001\u0004\u0001\n\n\u0003\u0001\u001a\u0006\u0000\u0004\u0001\u0001\u0005\u0001\u0001\u0014\u0000\u0001\u0017\u0001\b\u0001\u0018\u0003\u0000\u0001\u0012\u0001\u000b\u0002\u0001\u0001\u0011\u0001\f\u0005\u0000\u0001\u0013\u0001\u0000\u0001\r\u0003\u0000\u0001\u000e\u0001\u0014\u0001\u000f\u0001\u0010\u0005\u0000\u0001\u0015\u0001\u0000\u0001\u0016ﾂ\u0000"
   );
   private static final int[] intArray2 = getIntArray();
   private static final String string2 = "\u0002\u0000\u0002\u0001\u0001\u0002\u0001\u0003\u0001\u0004\u0003\u0001\u0001\u0005\u0001\u0006\u0001\u0007\u0001\b\u0001\t\u0001\n\u0001\u000b\u0001\f\u0001\r\u0005\u0000\u0001\f\u0001\u000e\u0001\u000f\u0001\u0010\u0001\u0011\u0001\u0012\u0001\u0013\u0001\u0014\u0001\u0000\u0001\u0015\u0001\u0000\u0001\u0015\u0004\u0000\u0001\u0016\u0001\u0017\u0002\u0000\u0001\u0018";
   private static final int[] intArray3 = getIntArray2();
   private static final String string3 = "\u0000\u0000\u0000\u001b\u00006\u0000Q\u0000l\u0000\u0087\u00006\u0000¢\u0000½\u0000Ø\u00006\u00006\u00006\u00006\u00006\u00006\u0000ó\u0000Ď\u00006\u0000ĩ\u0000ń\u0000ş\u0000ź\u0000ƕ\u00006\u00006\u00006\u00006\u00006\u00006\u00006\u00006\u0000ư\u0000ǋ\u0000Ǧ\u0000Ǧ\u0000ȁ\u0000Ȝ\u0000ȷ\u0000ɒ\u00006\u00006\u0000ɭ\u0000ʈ\u00006";
   private static final int[] intArray4 = new int[]{
      2,
      2,
      3,
      4,
      2,
      2,
      2,
      5,
      2,
      6,
      2,
      2,
      7,
      8,
      2,
      9,
      2,
      2,
      2,
      2,
      2,
      10,
      11,
      12,
      13,
      14,
      15,
      16,
      16,
      16,
      16,
      16,
      16,
      16,
      16,
      17,
      18,
      16,
      16,
      16,
      16,
      16,
      16,
      16,
      16,
      16,
      16,
      16,
      16,
      16,
      16,
      16,
      16,
      16,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      4,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      4,
      19,
      20,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      20,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      5,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      21,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      22,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      23,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      16,
      16,
      16,
      16,
      16,
      16,
      16,
      16,
      -1,
      -1,
      16,
      16,
      16,
      16,
      16,
      16,
      16,
      16,
      16,
      16,
      16,
      16,
      16,
      16,
      16,
      16,
      16,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      24,
      25,
      26,
      27,
      28,
      29,
      30,
      31,
      32,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      33,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      34,
      35,
      -1,
      -1,
      34,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      36,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      37,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      38,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      39,
      -1,
      39,
      -1,
      39,
      -1,
      -1,
      -1,
      -1,
      -1,
      39,
      39,
      -1,
      -1,
      -1,
      -1,
      39,
      39,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      33,
      -1,
      20,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      20,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      35,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      38,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      40,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      41,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      42,
      -1,
      42,
      -1,
      42,
      -1,
      -1,
      -1,
      -1,
      -1,
      42,
      42,
      -1,
      -1,
      -1,
      -1,
      42,
      42,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      43,
      -1,
      43,
      -1,
      43,
      -1,
      -1,
      -1,
      -1,
      -1,
      43,
      43,
      -1,
      -1,
      -1,
      -1,
      43,
      43,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      44,
      -1,
      44,
      -1,
      44,
      -1,
      -1,
      -1,
      -1,
      -1,
      44,
      44,
      -1,
      -1,
      -1,
      -1,
      44,
      44,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1,
      -1
   };
   private static final int intValue5 = 0;
   private static final int intValue6 = 1;
   private static final int intValue7 = 2;
   private static final String[] stringArray = new String[]{
      "Unkown internal scanner error", "Error: could not match input", "Error: pushback value was too large"
   };
   private static final int[] intArray5 = getIntArray3();
   private static final String string4 = "\u0002\u0000\u0001\t\u0003\u0001\u0001\t\u0003\u0001\u0006\t\u0002\u0001\u0001\t\u0005\u0000\b\t\u0001\u0000\u0001\u0001\u0001\u0000\u0001\u0001\u0004\u0000\u0002\t\u0002\u0000\u0001\t";
   private Reader reader;
   private int intValue8;
   private int intValue9 = 0;
   private char[] charArray2 = new char[16384];
   private int intValue10;
   private int intValue11;
   private int intValue12;
   private int intValue13;
   private int intValue14;
   private int intValue15;
   private int intValue16;
   private boolean flag = true;
   private boolean flag2;
   private StringBuffer stringBuffer = new StringBuffer();

   private static final int[] getIntArray() {
      int[] var0 = new int[45];
      byte var1 = 0;
      getInt(
         "\u0002\u0000\u0002\u0001\u0001\u0002\u0001\u0003\u0001\u0004\u0003\u0001\u0001\u0005\u0001\u0006\u0001\u0007\u0001\b\u0001\t\u0001\n\u0001\u000b\u0001\f\u0001\r\u0005\u0000\u0001\f\u0001\u000e\u0001\u000f\u0001\u0010\u0001\u0011\u0001\u0012\u0001\u0013\u0001\u0014\u0001\u0000\u0001\u0015\u0001\u0000\u0001\u0015\u0004\u0000\u0001\u0016\u0001\u0017\u0002\u0000\u0001\u0018",
         var1,
         var0
      );
      return var0;
   }

   private static final int getInt(String var0, int var1, int[] var2) {
      int var3 = 0;
      int var4 = var1;
      int var5 = var0.length();

      while (var3 < var5) {
         int var6 = var0.charAt(var3++);
         char var7 = var0.charAt(var3++);

         do {
            var2[var4++] = var7;
         } while (--var6 > 0);
      }

      return var4;
   }

   private static final int[] getIntArray2() {
      int[] var0 = new int[45];
      byte var1 = 0;
      getInt2(
         "\u0000\u0000\u0000\u001b\u00006\u0000Q\u0000l\u0000\u0087\u00006\u0000¢\u0000½\u0000Ø\u00006\u00006\u00006\u00006\u00006\u00006\u0000ó\u0000Ď\u00006\u0000ĩ\u0000ń\u0000ş\u0000ź\u0000ƕ\u00006\u00006\u00006\u00006\u00006\u00006\u00006\u00006\u0000ư\u0000ǋ\u0000Ǧ\u0000Ǧ\u0000ȁ\u0000Ȝ\u0000ȷ\u0000ɒ\u00006\u00006\u0000ɭ\u0000ʈ\u00006",
         var1,
         var0
      );
      return var0;
   }

   private static final int getInt2(String var0, int var1, int[] var2) {
      int var3 = 0;
      int var4 = var1;
      int var5 = var0.length();

      while (var3 < var5) {
         int var6 = var0.charAt(var3++) << 16;
         var2[var4++] = var6 | var0.charAt(var3++);
      }

      return var4;
   }

   private static final int[] getIntArray3() {
      int[] var0 = new int[45];
      byte var1 = 0;
      getInt3(
         "\u0002\u0000\u0001\t\u0003\u0001\u0001\t\u0003\u0001\u0006\t\u0002\u0001\u0001\t\u0005\u0000\b\t\u0001\u0000\u0001\u0001\u0001\u0000\u0001\u0001\u0004\u0000\u0002\t\u0002\u0000\u0001\t",
         var1,
         var0
      );
      return var0;
   }

   private static final int getInt3(String var0, int var1, int[] var2) {
      int var3 = 0;
      int var4 = var1;
      int var5 = var0.length();

      while (var3 < var5) {
         int var6 = var0.charAt(var3++);
         char var7 = var0.charAt(var3++);

         do {
            var2[var4++] = var7;
         } while (--var6 > 0);
      }

      return var4;
   }

   public int getIntValue15() {
      return this.intValue15;
   }

   public JsonLexer(Reader var1) {
      this.reader = var1;
   }

   private static final char[] getCharArray(String var0) {
      char[] var1 = new char[65536];
      int var2 = 0;
      int var3 = 0;

      while (var2 < 90) {
         int var4 = var0.charAt(var2++);
         char var5 = var0.charAt(var2++);

         do {
            var1[var3++] = var5;
         } while (--var4 > 0);
      }

      return var1;
   }

   private final boolean check() {
      try {
      if (this.intValue12 > 0) {
         System.arraycopy(this.charArray2, this.intValue12, this.charArray2, 0, this.intValue13 - this.intValue12);
         this.intValue13 = this.intValue13 - this.intValue12;
         this.intValue11 = this.intValue11 - this.intValue12;
         this.intValue10 = this.intValue10 - this.intValue12;
         this.intValue12 = 0;
      }

      if (this.intValue11 >= this.charArray2.length) {
         char[] var1 = new char[this.intValue11 * 2];
         System.arraycopy(this.charArray2, 0, var1, 0, this.charArray2.length);
         this.charArray2 = var1;
      }

      int var3 = this.reader.read(this.charArray2, this.intValue13, this.charArray2.length - this.intValue13);
      if (var3 > 0) {
         this.intValue13 += var3;
         return false;
      } else if (var3 == 0) {
         int var2 = this.reader.read();
         if (var2 == -1) {
            return true;
         } else {
            this.charArray2[this.intValue13++] = (char)var2;
            return false;
         }
      } else {
         return true;
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   public final void handleReader(Reader var1) {
      this.reader = var1;
      this.flag = true;
      this.flag2 = false;
      this.intValue13 = this.intValue12 = 0;
      this.intValue11 = this.intValue10 = 0;
      this.intValue14 = this.intValue15 = this.intValue16 = 0;
      this.intValue9 = 0;
   }

   public final void setIntValue9(int var1) {
      this.intValue9 = var1;
   }

   public final String getString() {
      return new String(this.charArray2, this.intValue12, this.intValue10 - this.intValue12);
   }

   public final char getChar(int var1) {
      return this.charArray2[this.intValue12 + var1];
   }

   private final void handleInt(int var1) {
      String var2;
      try {
         var2 = stringArray[var1];
      } catch (ArrayIndexOutOfBoundsException var3) {
         var2 = stringArray[0];
      }

      throw new Error(var2);
   }

   public JsonToken getJsonToken() {
      try {
      int var5 = this.intValue13;
      char[] var6 = this.charArray2;
      char[] var7 = charArray;
      int[] var8 = intArray4;
      int[] var9 = intArray3;
      int[] var10 = intArray5;

      while (true) {
         int var4 = this.intValue10;
         this.intValue15 = this.intValue15 + (var4 - this.intValue12);
         int var2 = -1;
         int var14 = this.intValue11 = this.intValue12 = var4;
         this.intValue8 = intArray[this.intValue9];

         int var1;
         while (true) {
            if (var14 < var5) {
               var1 = var6[var14++];
            } else {
               if (this.flag2) {
                  var1 = -1;
                  break;
               }

               this.intValue11 = var14;
               this.intValue10 = var4;
               boolean var11 = this.check();
               var14 = this.intValue11;
               var4 = this.intValue10;
               var6 = this.charArray2;
               var5 = this.intValue13;
               if (var11) {
                  var1 = -1;
                  break;
               }

               var1 = var6[var14++];
            }

            int var15 = var8[var9[this.intValue8] + var7[var1]];
            if (var15 == -1) {
               break;
            }

            this.intValue8 = var15;
            int var12 = var10[this.intValue8];
            if ((var12 & 1) == 1) {
               var2 = this.intValue8;
               var4 = var14;
               if ((var12 & 8) == 8) {
                  break;
               }
            }
         }

         this.intValue10 = var4;
         switch (var2 < 0 ? var2 : intArray2[var2]) {
            case 1:
               throw new JsonParseException(this.intValue15, 0, new Character(this.getChar(0)));
            case 2:
               Long var19 = Long.valueOf(this.getString());
               return new JsonToken(0, var19);
            case 3:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 34:
            case 35:
            case 36:
            case 37:
            case 38:
            case 39:
            case 40:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
               break;
            case 4:
               this.stringBuffer.delete(0, this.stringBuffer.length());
               this.setIntValue9(2);
               break;
            case 5:
               return new JsonToken(1, null);
            case 6:
               return new JsonToken(2, null);
            case 7:
               return new JsonToken(3, null);
            case 8:
               return new JsonToken(4, null);
            case 9:
               return new JsonToken(5, null);
            case 10:
               return new JsonToken(6, null);
            case 11:
               this.stringBuffer.append(this.getString());
               break;
            case 12:
               this.stringBuffer.append('\\');
               break;
            case 13:
               this.setIntValue9(0);
               return new JsonToken(0, this.stringBuffer.toString());
            case 14:
               this.stringBuffer.append('"');
               break;
            case 15:
               this.stringBuffer.append('/');
               break;
            case 16:
               this.stringBuffer.append('\b');
               break;
            case 17:
               this.stringBuffer.append('\f');
               break;
            case 18:
               this.stringBuffer.append('\n');
               break;
            case 19:
               this.stringBuffer.append('\r');
               break;
            case 20:
               this.stringBuffer.append('\t');
               break;
            case 21:
               Double var18 = Double.valueOf(this.getString());
               return new JsonToken(0, var18);
            case 22:
               return new JsonToken(0, null);
            case 23:
               Boolean var17 = Boolean.valueOf(this.getString());
               return new JsonToken(0, var17);
            case 24:
               try {
                  int var16 = Integer.parseInt(this.getString().substring(2), 16);
                  this.stringBuffer.append((char)var16);
                  break;
               } catch (Exception var13) {
                  throw new JsonParseException(this.intValue15, 2, var13);
               }
            default:
               if (var1 == -1 && this.intValue12 == this.intValue11) {
                  this.flag2 = true;
                  return null;
               }

               this.handleInt(1);
         }
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }
}
