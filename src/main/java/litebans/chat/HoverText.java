package litebans.chat;

import java.util.Arrays;

public class HoverText extends HoverContent {
   private final Object value;

   public HoverText(BaseComponent[] var1) {
      this.value = var1;
   }

   public HoverText(String var1) {
      this.value = var1;
   }

   @Override
   public boolean equals(Object var1) {
      return !(this.value instanceof BaseComponent[])
         ? this.value.equals(var1)
         : var1 instanceof HoverText
            && ((HoverText)var1).value instanceof BaseComponent[]
            && Arrays.equals((Object[])((BaseComponent[])this.value), (Object[])((BaseComponent[])((HoverText)var1).value));
   }

   @Override
   public int hashCode() {
      return this.value instanceof BaseComponent[] ? Arrays.hashCode((Object[])((BaseComponent[])this.value)) : this.value.hashCode();
   }

   public Object getValue() {
      return this.value;
   }

   @Override
   public String toString() {
      return "Text(value=" + this.getValue() + ")";
   }
}
