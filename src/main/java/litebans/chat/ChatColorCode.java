package litebans.chat;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import org.jetbrains.annotations.NotNull;

public enum ChatColorCode implements CharSequence {
   BLACK('0', "black"),
   DARK_BLUE('1', "dark_blue"),
   DARK_GREEN('2', "dark_green"),
   DARK_AQUA('3', "dark_aqua"),
   DARK_RED('4', "dark_red"),
   DARK_PURPLE('5', "dark_purple"),
   GOLD('6', "gold"),
   GRAY('7', "gray"),
   DARK_GRAY('8', "dark_gray"),
   BLUE('9', "blue"),
   GREEN('a', "green"),
   AQUA('b', "aqua"),
   RED('c', "red"),
   LIGHT_PURPLE('d', "light_purple"),
   YELLOW('e', "yellow"),
   WHITE('f', "white"),
   OBFUSCATED('k', "obfuscated"),
   BOLD('l', "bold"),
   STRIKETHROUGH('m', "strikethrough"),
   UNDERLINE('n', "underline"),
   ITALIC('o', "italic"),
   RESET('r', "reset"),
   MAGIC('x', "magic");
   public static final char charValue = 167;
   public static final String string = "0123456789AaBbCcDdEeFfKkLlMmNnOoRrXx";
   public static final Pattern pattern = Pattern.compile("(?i)(?<!<@)[&§][0-9A-FK-ORX]");
   private static final Map map = new HashMap();
   private final char charValue2;
   private final String string2;
   private final String string3;
   private static final ChatColorCode[] chatColorCodeArray = getChatColorCodeArray();

   public static ChatColorCode getChatColorCode(String var0) {
      return Enum.valueOf(ChatColorCode.class, var0);
   }

   private ChatColorCode(char var3, String var4) {
      this.charValue2 = var3;
      this.string3 = var4;
      this.string2 = new String(new char[]{'§', var3});
   }

   public static String getString(CharSequence var0) {
      return var0 == null ? null : pattern.matcher(var0).replaceAll("");
   }

   public static String getString(char var0, String var1) {
      char[] var2 = var1.toCharArray();

      for (int var3 = 0; var3 < var2.length - 1; var3++) {
         if (var2[var3] == var0 && "0123456789AaBbCcDdEeFfKkLlMmNnOoRrXx".indexOf(var2[var3 + 1]) > -1) {
            var2[var3] = 167;
            var2[var3 + 1] = Character.toLowerCase(var2[var3 + 1]);
         }
      }

      return new String(var2);
   }

   @Override
   public char charAt(int var1) {
      return this.toString().charAt(var1);
   }

   @Override
   public int length() {
      return this.toString().length();
   }

   @Override
   public CharSequence subSequence(int var1, int var2) {
      return this.toString().subSequence(var1, var2);
   }

   @NotNull
   @Override
   public String toString() {
      return this.string2;
   }

   public static String getString(String var0) {
      return GOLD + var0;
   }

   public static String getString2(String var0) {
      return GREEN + var0;
   }

   public static String getString3(String var0) {
      return RED + var0;
   }

   public static String getString4(String var0) {
      return LIGHT_PURPLE + var0;
   }

   public static String getString5(String var0) {
      return YELLOW + var0;
   }

   public static String getString6(String var0) {
      return UNDERLINE + var0;
   }

   private static final ChatColorCode[] getChatColorCodeArray() {
      return new ChatColorCode[]{
         BLACK,
         DARK_BLUE,
         DARK_GREEN,
         DARK_AQUA,
         DARK_RED,
         DARK_PURPLE,
         GOLD,
         GRAY,
         DARK_GRAY,
         BLUE,
         GREEN,
         AQUA,
         RED,
         LIGHT_PURPLE,
         YELLOW,
         WHITE,
         OBFUSCATED,
         BOLD,
         STRIKETHROUGH,
         UNDERLINE,
         ITALIC,
         RESET,
         MAGIC
      };
   }

   static {
      for (ChatColorCode var3 : values()) {
         map.put(var3.charValue2, var3);
      }
   }
}
