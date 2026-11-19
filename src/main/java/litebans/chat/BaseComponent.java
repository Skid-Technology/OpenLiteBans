package litebans.chat;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseComponent {
   public BaseComponent baseComponent;
   private ChatStyle style = new ChatStyle();
   private String insertion;
   private List extra;
   private ClickEvent clickEvent;
   private HoverEvent hoverEvent;
   private transient boolean reset;

   @Deprecated
   public BaseComponent() {
   }

   public BaseComponent(BaseComponent var1) {
      this.handleBaseComponent(var1, JsonMode.ALL, true);
      if (var1.getExtra() != null) {
         for (Object var3o : var1.getExtra()) {
            BaseComponent var3 = (BaseComponent) var3o;
            this.handleBaseComponent(var3.getBaseComponent());
         }
      }
   }

   public void handleBaseComponent(BaseComponent var1, JsonMode var2, boolean var3) {
      if (var2 == JsonMode.EVENTS || var2 == JsonMode.ALL) {
         if (var3 || this.clickEvent == null) {
            this.setClickEvent(var1.getClickEvent());
         }

         if (var3 || this.hoverEvent == null) {
            this.setHoverEvent(var1.getHoverEvent());
         }
      }

      if (var2 == JsonMode.FORMATTING || var2 == JsonMode.ALL) {
         if (var3 || !this.style.check()) {
            this.handleChatColor(var1.getChatColor2());
         }

         if (var3 || !this.style.check2()) {
            this.handleColor(var1.getColor());
         }

         if (var3 || !this.style.check3()) {
            this.handleString(var1.getString());
         }

         if (var3 || this.style.getBooleanValue() == null) {
            this.handleBoolean(var1.getBoolean());
         }

         if (var3 || this.style.getBooleanValue2() == null) {
            this.handleBoolean2(var1.getBoolean2());
         }

         if (var3 || this.style.getBooleanValue3() == null) {
            this.handleBoolean3(var1.getBoolean3());
         }

         if (var3 || this.style.getBooleanValue4() == null) {
            this.handleBoolean4(var1.getBoolean4());
         }

         if (var3 || this.style.getBooleanValue5() == null) {
            this.handleBoolean5(var1.getBoolean5());
         }

         if (var3 || this.insertion == null) {
            this.setInsertion(var1.getInsertion());
         }
      }
   }

   public abstract BaseComponent getBaseComponent();

   public static String getString(BaseComponent[] var0) {
      StringBuilder var1 = new StringBuilder();

      for (BaseComponent var5 : var0) {
         var1.append(var5.getString2());
      }

      return var1.toString();
   }

   public void handleChatColor(ChatColor var1) {
      this.style.setChatColor(var1);
   }

   public ChatColor getChatColor() {
      if (!this.style.check()) {
         return this.baseComponent == null ? ChatColor.WHITE : this.baseComponent.getChatColor();
      } else {
         return this.style.getChatColor();
      }
   }

   public ChatColor getChatColor2() {
      return this.style.getChatColor();
   }

   public void handleColor(Color var1) {
      this.style.setColor(var1);
   }

   public Color getColor() {
      return this.style.getColor();
   }

   public void handleString(String var1) {
      this.style.setString(var1);
   }

   public String getString() {
      return this.style.getString();
   }

   public void handleBoolean(Boolean var1) {
      this.style.setBooleanValue(var1);
   }

   public boolean check() {
      return this.style.getBooleanValue() != null ? this.style.check4() : this.baseComponent != null && this.baseComponent.check();
   }

   public Boolean getBoolean() {
      return this.style.getBooleanValue();
   }

   public void handleBoolean2(Boolean var1) {
      this.style.setBooleanValue2(var1);
   }

   public boolean check2() {
      return this.style.getBooleanValue2() != null ? this.style.check5() : this.baseComponent != null && this.baseComponent.check2();
   }

   public Boolean getBoolean2() {
      return this.style.getBooleanValue2();
   }

   public void handleBoolean3(Boolean var1) {
      this.style.setBooleanValue3(var1);
   }

   public boolean check3() {
      return this.style.getBooleanValue3() != null ? this.style.check6() : this.baseComponent != null && this.baseComponent.check3();
   }

   public Boolean getBoolean3() {
      return this.style.getBooleanValue3();
   }

   public void handleBoolean4(Boolean var1) {
      this.style.setBooleanValue4(var1);
   }

   public boolean check4() {
      return this.style.getBooleanValue4() != null ? this.style.check7() : this.baseComponent != null && this.baseComponent.check4();
   }

   public Boolean getBoolean4() {
      return this.style.getBooleanValue4();
   }

   public void handleBoolean5(Boolean var1) {
      this.style.setBooleanValue5(var1);
   }

   public boolean check5() {
      return this.style.getBooleanValue5() != null ? this.style.check8() : this.baseComponent != null && this.baseComponent.check5();
   }

   public Boolean getBoolean5() {
      return this.style.getBooleanValue5();
   }

   public void handleChatStyle(ChatStyle var1) {
      if (var1.check()) {
         this.handleChatColor(var1.getChatColor());
      }

      if (var1.check2()) {
         this.handleColor(var1.getColor());
      }

      if (var1.check3()) {
         this.handleString(var1.getString());
      }

      if (var1.getBooleanValue() != null) {
         this.handleBoolean(var1.getBooleanValue());
      }

      if (var1.getBooleanValue2() != null) {
         this.handleBoolean2(var1.getBooleanValue2());
      }

      if (var1.getBooleanValue3() != null) {
         this.handleBoolean3(var1.getBooleanValue3());
      }

      if (var1.getBooleanValue4() != null) {
         this.handleBoolean4(var1.getBooleanValue4());
      }

      if (var1.getBooleanValue5() != null) {
         this.handleBoolean5(var1.getBooleanValue5());
      }
   }

   public void handleList(List var1) {
      for (Object var3o : var1) {
         BaseComponent var3 = (BaseComponent) var3o;
         var3.baseComponent = this;
      }

      this.extra = var1;
   }

   public void handleBaseComponent(BaseComponent var1) {
      if (this.extra == null) {
         this.extra = new ArrayList();
      }

      var1.baseComponent = this;
      this.extra.add(var1);
   }

   public String getString2() {
      StringBuilder var1 = new StringBuilder();
      this.handleStringBuilder(var1);
      return var1.toString();
   }

   public void handleStringBuilder(StringBuilder var1) {
      if (this.extra != null) {
         for (Object var3o : this.extra) {
            BaseComponent var3 = (BaseComponent) var3o;
            var3.handleStringBuilder(var1);
         }
      }
   }

   public void handleStringBuilder2(StringBuilder var1) {
      var1.append(this.getChatColor());
      if (this.check()) {
         var1.append(ChatColor.BOLD);
      }

      if (this.check2()) {
         var1.append(ChatColor.ITALIC);
      }

      if (this.check3()) {
         var1.append(ChatColor.UNDERLINE);
      }

      if (this.check4()) {
         var1.append(ChatColor.STRIKETHROUGH);
      }

      if (this.check5()) {
         var1.append(ChatColor.OBFUSCATED);
      }
   }

   public void setInsertion(String var1) {
      this.insertion = var1;
   }

   public void setClickEvent(ClickEvent var1) {
      this.clickEvent = var1;
   }

   public void setHoverEvent(HoverEvent var1) {
      this.hoverEvent = var1;
   }

   public void setReset(boolean var1) {
      this.reset = var1;
   }

   @Override
   public String toString() {
      return "BaseComponent(style="
         + this.getStyle()
         + ", insertion="
         + this.getInsertion()
         + ", extra="
         + this.getExtra()
         + ", clickEvent="
         + this.getClickEvent()
         + ", hoverEvent="
         + this.getHoverEvent()
         + ", reset="
         + this.isReset()
         + ")";
   }

   @Override
   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof BaseComponent)) {
         return false;
      } else {
         BaseComponent var2 = (BaseComponent)var1;
         if (!var2.checkObject(this)) {
            return false;
         } else {
            ChatStyle var3 = this.getStyle();
            ChatStyle var4 = var2.getStyle();
            if (var3 == null ? var4 == null : var3.equals(var4)) {
               String var5 = this.getInsertion();
               String var6 = var2.getInsertion();
               if (var5 == null ? var6 == null : var5.equals(var6)) {
                  List var7 = this.getExtra();
                  List var8 = var2.getExtra();
                  if (var7 == null ? var8 == null : var7.equals(var8)) {
                     ClickEvent var9 = this.getClickEvent();
                     ClickEvent var10 = var2.getClickEvent();
                     if (var9 == null ? var10 == null : var9.equals(var10)) {
                        HoverEvent var11 = this.getHoverEvent();
                        HoverEvent var12 = var2.getHoverEvent();
                        return var11 == null ? var12 == null : var11.equals(var12);
                     } else {
                        return false;
                     }
                  } else {
                     return false;
                  }
               } else {
                  return false;
               }
            } else {
               return false;
            }
         }
      }
   }

   public boolean checkObject(Object var1) {
      return var1 instanceof BaseComponent;
   }

   @Override
   public int hashCode() {
      int var2 = 1;
      ChatStyle var3 = this.getStyle();
      var2 = var2 * 59 + (var3 == null ? 43 : var3.hashCode());
      String var4 = this.getInsertion();
      var2 = var2 * 59 + (var4 == null ? 43 : var4.hashCode());
      List var5 = this.getExtra();
      var2 = var2 * 59 + (var5 == null ? 43 : var5.hashCode());
      ClickEvent var6 = this.getClickEvent();
      var2 = var2 * 59 + (var6 == null ? 43 : var6.hashCode());
      HoverEvent var7 = this.getHoverEvent();
      return var2 * 59 + (var7 == null ? 43 : var7.hashCode());
   }

   public ChatStyle getStyle() {
      return this.style;
   }

   public String getInsertion() {
      return this.insertion;
   }

   public List getExtra() {
      return this.extra;
   }

   public ClickEvent getClickEvent() {
      return this.clickEvent;
   }

   public HoverEvent getHoverEvent() {
      return this.hoverEvent;
   }

   public boolean isReset() {
      return this.reset;
   }
}
