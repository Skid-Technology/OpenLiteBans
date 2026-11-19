package litebans.chat;

import net.md_5.bungee.chat.ChatVersion;

public class BungeeComponentParser {
   public static net.md_5.bungee.api.chat.BaseComponent[] getBaseComponentArray(CharSequence var0, ChatVersion var1) {
      return net.md_5.bungee.chat.VersionedComponentSerializer.forVersion(var1).parse(var0.toString());
   }
}
