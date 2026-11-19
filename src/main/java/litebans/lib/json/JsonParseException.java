package litebans.lib.json;

public class JsonParseException extends Exception {
   private static final long longValue = -7880698968187728548L;
   public static final int intValue = 0;
   public static final int intValue2 = 1;
   public static final int intValue3 = 2;
   private int intValue4;
   private Object object;
   private int intValue5;

   public JsonParseException(int var1, int var2, Object var3) {
      this.intValue5 = var1;
      this.intValue4 = var2;
      this.object = var3;
   }

   public String toString() {
      StringBuffer var1 = new StringBuffer();
      switch (this.intValue4) {
         case 0:
            var1.append("Unexpected character (").append(this.object).append(") at position ").append(this.intValue5).append(".");
            break;
         case 1:
            var1.append("Unexpected token ").append(this.object).append(" at position ").append(this.intValue5).append(".");
            break;
         case 2:
            var1.append("Unexpected exception at position ").append(this.intValue5).append(": ").append(this.object);
            break;
         default:
            var1.append("Unkown error at position ").append(this.intValue5).append(".");
      }

      return var1.toString();
   }
}
