package litebans.lib.json;

public class JsonToken {
   public static final int intValue = 0;
   public static final int intValue2 = 1;
   public static final int intValue3 = 2;
   public static final int intValue4 = 3;
   public static final int intValue5 = 4;
   public static final int intValue6 = 5;
   public static final int intValue7 = 6;
   public static final int intValue8 = -1;
   public int intValue9 = 0;
   public Object object = null;

   public JsonToken(int var1, Object var2) {
      this.intValue9 = var1;
      this.object = var2;
   }

   public String toString() {
      StringBuffer var1 = new StringBuffer();
      switch (this.intValue9) {
         case -1:
            var1.append("END OF FILE");
            break;
         case 0:
            var1.append("VALUE(").append(this.object).append(")");
            break;
         case 1:
            var1.append("LEFT BRACE({)");
            break;
         case 2:
            var1.append("RIGHT BRACE(})");
            break;
         case 3:
            var1.append("LEFT SQUARE([)");
            break;
         case 4:
            var1.append("RIGHT SQUARE(])");
            break;
         case 5:
            var1.append("COMMA(,)");
            break;
         case 6:
            var1.append("COLON(:)");
      }

      return var1.toString();
   }
}
