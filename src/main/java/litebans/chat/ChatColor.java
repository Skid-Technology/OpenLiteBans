package litebans.chat;

import com.google.common.base.Preconditions;
import java.awt.Color;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

public final class ChatColor {
   public static final char charValue = 167;
   public static final String string = "0123456789AaBbCcDdEeFfKkLlMmNnOoRrXx";
   public static final Pattern pattern = Pattern.compile("(?i)" + String.valueOf('§') + "[0-9A-FK-ORX]");
   private static final Map map = new HashMap();
   private static final Map map2 = new HashMap();
   public static final ChatColor BLACK = new ChatColor('0', "black", new Color(0));
   public static final ChatColor DARK_BLUE = new ChatColor('1', "dark_blue", new Color(170));
   public static final ChatColor DARK_GREEN = new ChatColor('2', "dark_green", new Color(43520));
   public static final ChatColor DARK_AQUA = new ChatColor('3', "dark_aqua", new Color(43690));
   public static final ChatColor DARK_RED = new ChatColor('4', "dark_red", new Color(11141120));
   public static final ChatColor DARK_PURPLE = new ChatColor('5', "dark_purple", new Color(11141290));
   public static final ChatColor GOLD = new ChatColor('6', "gold", new Color(16755200));
   public static final ChatColor GRAY = new ChatColor('7', "gray", new Color(11184810));
   public static final ChatColor DARK_GRAY = new ChatColor('8', "dark_gray", new Color(5592405));
   public static final ChatColor BLUE = new ChatColor('9', "blue", new Color(5592575));
   public static final ChatColor GREEN = new ChatColor('a', "green", new Color(5635925));
   public static final ChatColor AQUA = new ChatColor('b', "aqua", new Color(5636095));
   public static final ChatColor RED = new ChatColor('c', "red", new Color(16733525));
   public static final ChatColor LIGHT_PURPLE = new ChatColor('d', "light_purple", new Color(16733695));
   public static final ChatColor YELLOW = new ChatColor('e', "yellow", new Color(16777045));
   public static final ChatColor WHITE = new ChatColor('f', "white", new Color(16777215));
   public static final ChatColor OBFUSCATED = new ChatColor('k', "obfuscated");
   public static final ChatColor BOLD = new ChatColor('l', "bold");
   public static final ChatColor STRIKETHROUGH = new ChatColor('m', "strikethrough");
   public static final ChatColor UNDERLINE = new ChatColor('n', "underline");
   public static final ChatColor ITALIC = new ChatColor('o', "italic");
   public static final ChatColor RESET = new ChatColor('r', "reset");
   private static int intValue = 0;
   private final String string2;
   private final String string3;
   private final int intValue2;
   private final Color color;

   private ChatColor(char var1, String var2) {
      this(var1, var2, null);
   }

   private ChatColor(char var1, String var2, Color var3) {
      this.string3 = var2;
      this.string2 = new String(new char[]{'§', var1});
      this.intValue2 = intValue++;
      this.color = var3;
      map.put(var1, this);
      map2.put(var2.toUpperCase(Locale.ROOT), this);
   }

   private ChatColor(String var1, String var2, int var3) {
      this.string3 = var1;
      this.string2 = var2;
      this.intValue2 = -1;
      this.color = new Color(var3);
   }

   @Override
   public int hashCode() {
      byte var1 = 7;
      return 53 * var1 + Objects.hashCode(this.string2);
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         ChatColor var2 = (ChatColor)var1;
         return Objects.equals(this.string2, var2.string2);
      } else {
         return false;
      }
   }

   @Override
   public String toString() {
      return this.string2;
   }

   public static ChatColor getChatColor(char var0) {
      return (ChatColor)map.get(var0);
   }

   public static ChatColor getChatColor(String var0) {
      Preconditions.checkArgument(var0 != null, "string cannot be null");
      if (var0.length() == 7 && var0.charAt(0) == '#') {
         int var8;
         try {
            var8 = Integer.parseInt(var0.substring(1), 16);
         } catch (NumberFormatException var7) {
            throw new IllegalArgumentException("Illegal hex string " + var0);
         }

         StringBuilder var2 = new StringBuilder("§x");

         for (char var6 : var0.substring(1).toCharArray()) {
            var2.append('§').append(var6);
         }

         return new ChatColor(var0, var2.toString(), var8);
      } else {
         ChatColor var1 = (ChatColor)map2.get(var0.toUpperCase(Locale.ROOT));
         if (var1 != null) {
            return var1;
         } else {
            throw new IllegalArgumentException("Could not parse ChatColor " + var0);
         }
      }
   }

   public String getString3() {
      return this.string3;
   }

   public Color getColor() {
      return this.color;
   }
}
