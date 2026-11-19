package litebans.chat;

import litebans.platform.ClassLoadDiagnostics;

public class BungeeLegacyComponentParser {
   private static boolean flag = ClassLoadDiagnostics.checkString("net.md_5.bungee.chat.ChatVersion");

   public static boolean isFlag() {
      return flag;
   }

   public static net.md_5.bungee.api.chat.BaseComponent[] getBaseComponentArray(CharSequence var0) {
      return net.md_5.bungee.api.chat.TextComponent.fromLegacyText(var0.toString());
   }

   public static net.md_5.bungee.api.chat.BaseComponent[] getBaseComponentArray2(CharSequence var0) {
      return net.md_5.bungee.chat.ComponentSerializer.parse(var0.toString());
   }
}
