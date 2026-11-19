package litebans.chat;

import java.awt.Color;

public final class ChatStyleBuilder {
   private ChatColor chatColor;
   private Color color;
   private String string;
   private Boolean booleanValue;
   private Boolean booleanValue2;
   private Boolean booleanValue3;
   private Boolean booleanValue4;
   private Boolean booleanValue5;

   public ChatStyleBuilder getChatStyleBuilder(ChatColor var1) {
      this.chatColor = var1;
      return this;
   }

   public ChatStyleBuilder getChatStyleBuilder(Color var1) {
      this.color = var1;
      return this;
   }

   public ChatStyleBuilder getChatStyleBuilder(String var1) {
      this.string = var1;
      return this;
   }

   public ChatStyleBuilder getChatStyleBuilder(Boolean var1) {
      this.booleanValue = var1;
      return this;
   }

   public ChatStyleBuilder getChatStyleBuilder2(Boolean var1) {
      this.booleanValue2 = var1;
      return this;
   }

   public ChatStyleBuilder getChatStyleBuilder3(Boolean var1) {
      this.booleanValue3 = var1;
      return this;
   }

   public ChatStyleBuilder getChatStyleBuilder4(Boolean var1) {
      this.booleanValue4 = var1;
      return this;
   }

   public ChatStyleBuilder getChatStyleBuilder5(Boolean var1) {
      this.booleanValue5 = var1;
      return this;
   }

   public ChatStyle getChatStyle() {
      return new ChatStyle(
         this.chatColor, this.color, this.string, this.booleanValue, this.booleanValue2, this.booleanValue3, this.booleanValue4, this.booleanValue5
      );
   }
}
