package litebans.chat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class HoverEvent {
   private final HoverAction action;
   private final List contents;
   private boolean legacy = false;

   @Deprecated
   public HoverEvent(HoverAction var1, BaseComponent[] var2) {
      this.action = var1;
      this.contents = new ArrayList<>(Collections.singletonList(new HoverText(var2)));
      this.legacy = true;
   }

   public static Class getClass(HoverAction var0, boolean var1) {
      com.google.common.base.Preconditions.checkArgument(var0 != null, "action");

      switch (var0.ordinal()) {
         case 0:
            return var1 ? HoverText[].class : HoverText.class;
         default:
            throw new UnsupportedOperationException("Action '" + var0.name() + "' not supported");
      }
   }

   public HoverAction getAction() {
      return this.action;
   }

   public List getContents() {
      return this.contents;
   }

   public boolean isLegacy() {
      return this.legacy;
   }

   @Override
   public String toString() {
      return "HoverEvent(action=" + this.getAction() + ", contents=" + this.getContents() + ", legacy=" + this.isLegacy() + ")";
   }

   @Override
   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof HoverEvent)) {
         return false;
      } else {
         HoverEvent var2 = (HoverEvent)var1;
         if (this.isLegacy() != var2.isLegacy()) {
            return false;
         } else {
            HoverAction var3 = this.getAction();
            HoverAction var4 = var2.getAction();
            if (var3 == null ? var4 == null : var3.equals(var4)) {
               List var5 = this.getContents();
               List var6 = var2.getContents();
               return var5 == null ? var6 == null : var5.equals(var6);
            } else {
               return false;
            }
         }
      }
   }

   @Override
   public int hashCode() {
      int var2 = 1;
      var2 = var2 * 59 + (this.isLegacy() ? 79 : 97);
      HoverAction var3 = this.getAction();
      var2 = var2 * 59 + (var3 == null ? 43 : var3.hashCode());
      List var4 = this.getContents();
      return var2 * 59 + (var4 == null ? 43 : var4.hashCode());
   }

   public HoverEvent(HoverAction var1, List var2) {
      this.action = var1;
      this.contents = var2;
   }
}
