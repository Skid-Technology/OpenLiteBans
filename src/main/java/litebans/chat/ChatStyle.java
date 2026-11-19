package litebans.chat;

import java.awt.Color;

public final class ChatStyle implements Cloneable {
   private ChatColor chatColor;
   private Color color;
   private String string;
   private Boolean booleanValue;
   private Boolean booleanValue2;
   private Boolean booleanValue3;
   private Boolean booleanValue4;
   private Boolean booleanValue5;

   public ChatColor getChatColor() {
      return this.chatColor;
   }

   public boolean check() {
      return this.chatColor != null;
   }

   public Color getColor() {
      return this.color;
   }

   public boolean check2() {
      return this.color != null;
   }

   public String getString() {
      return this.string;
   }

   public boolean check3() {
      return this.string != null;
   }

   public boolean check4() {
      return this.booleanValue != null && this.booleanValue;
   }

   public Boolean getBooleanValue() {
      return this.booleanValue;
   }

   public boolean check5() {
      return this.booleanValue2 != null && this.booleanValue2;
   }

   public Boolean getBooleanValue2() {
      return this.booleanValue2;
   }

   public boolean check6() {
      return this.booleanValue3 != null && this.booleanValue3;
   }

   public Boolean getBooleanValue3() {
      return this.booleanValue3;
   }

   public boolean check7() {
      return this.booleanValue4 != null && this.booleanValue4;
   }

   public Boolean getBooleanValue4() {
      return this.booleanValue4;
   }

   public boolean check8() {
      return this.booleanValue5 != null && this.booleanValue5;
   }

   public Boolean getBooleanValue5() {
      return this.booleanValue5;
   }

   public ChatStyle getChatStyle() {
      return new ChatStyle(
         this.chatColor, this.color, this.string, this.booleanValue, this.booleanValue2, this.booleanValue3, this.booleanValue4, this.booleanValue5
      );
   }

   public static ChatStyleBuilder getChatStyleBuilder() {
      return new ChatStyleBuilder();
   }

   public void setChatColor(ChatColor var1) {
      this.chatColor = var1;
   }

   public void setColor(Color var1) {
      this.color = var1;
   }

   public void setString(String var1) {
      this.string = var1;
   }

   public void setBooleanValue(Boolean var1) {
      this.booleanValue = var1;
   }

   public void setBooleanValue2(Boolean var1) {
      this.booleanValue2 = var1;
   }

   public void setBooleanValue3(Boolean var1) {
      this.booleanValue3 = var1;
   }

   public void setBooleanValue4(Boolean var1) {
      this.booleanValue4 = var1;
   }

   public void setBooleanValue5(Boolean var1) {
      this.booleanValue5 = var1;
   }

   public ChatStyle(ChatColor var1, Color var2, String var3, Boolean var4, Boolean var5, Boolean var6, Boolean var7, Boolean var8) {
      this.chatColor = var1;
      this.color = var2;
      this.string = var3;
      this.booleanValue = var4;
      this.booleanValue2 = var5;
      this.booleanValue3 = var6;
      this.booleanValue4 = var7;
      this.booleanValue5 = var8;
   }

   public ChatStyle() {
   }

   @Override
   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof ChatStyle)) {
         return false;
      } else {
         ChatStyle var2 = (ChatStyle)var1;
         Boolean var3 = this.booleanValue;
         Boolean var4 = var2.booleanValue;
         if (var3 == null ? var4 == null : var3.equals(var4)) {
            Boolean var5 = this.booleanValue2;
            Boolean var6 = var2.booleanValue2;
            if (var5 == null ? var6 == null : var5.equals(var6)) {
               Boolean var7 = this.booleanValue3;
               Boolean var8 = var2.booleanValue3;
               if (var7 == null ? var8 == null : var7.equals(var8)) {
                  Boolean var9 = this.booleanValue4;
                  Boolean var10 = var2.booleanValue4;
                  if (var9 == null ? var10 == null : var9.equals(var10)) {
                     Boolean var11 = this.booleanValue5;
                     Boolean var12 = var2.booleanValue5;
                     if (var11 == null ? var12 == null : var11.equals(var12)) {
                        ChatColor var13 = this.getChatColor();
                        ChatColor var14 = var2.getChatColor();
                        if (var13 == null ? var14 == null : var13.equals(var14)) {
                           Color var15 = this.getColor();
                           Color var16 = var2.getColor();
                           if (var15 == null ? var16 == null : var15.equals(var16)) {
                              String var17 = this.getString();
                              String var18 = var2.getString();
                              return var17 == null ? var18 == null : var17.equals(var18);
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

   @Override
   public int hashCode() {
      int var2 = 1;
      Boolean var3 = this.booleanValue;
      var2 = var2 * 59 + (var3 == null ? 43 : var3.hashCode());
      Boolean var4 = this.booleanValue2;
      var2 = var2 * 59 + (var4 == null ? 43 : var4.hashCode());
      Boolean var5 = this.booleanValue3;
      var2 = var2 * 59 + (var5 == null ? 43 : var5.hashCode());
      Boolean var6 = this.booleanValue4;
      var2 = var2 * 59 + (var6 == null ? 43 : var6.hashCode());
      Boolean var7 = this.booleanValue5;
      var2 = var2 * 59 + (var7 == null ? 43 : var7.hashCode());
      ChatColor var8 = this.getChatColor();
      var2 = var2 * 59 + (var8 == null ? 43 : var8.hashCode());
      Color var9 = this.getColor();
      var2 = var2 * 59 + (var9 == null ? 43 : var9.hashCode());
      String var10 = this.getString();
      return var2 * 59 + (var10 == null ? 43 : var10.hashCode());
   }

   @Override
   public Object clone() {
      return this.getChatStyle();
   }
}
