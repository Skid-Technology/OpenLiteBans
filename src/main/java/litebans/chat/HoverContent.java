package litebans.chat;

public abstract class HoverContent {
   @Override
   public String toString() {
      return "Content()";
   }

   @Override
   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof HoverContent)) {
         return false;
      } else {
         HoverContent var2 = (HoverContent)var1;
         return var2.checkObject(this);
      }
   }

   public boolean checkObject(Object var1) {
      return var1 instanceof HoverContent;
   }

   @Override
   public int hashCode() {
      return 1;
   }
}
