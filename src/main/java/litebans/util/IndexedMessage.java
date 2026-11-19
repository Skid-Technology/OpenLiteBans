package litebans.util;

public class IndexedMessage {
   private final int intValue;
   private final int intValue2;
   private final int intValue3;
   private final String string;

   public IndexedMessage(int var1, int var2, int var3, String var4) {
      this.intValue = var1;
      this.intValue2 = var2;
      this.intValue3 = var3;
      this.string = var4;
   }

   @Override
   public String toString() {
      return String.format("[%d]: %s", this.intValue, this.string);
   }
}
