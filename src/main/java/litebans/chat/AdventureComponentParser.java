package litebans.chat;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.jetbrains.annotations.NotNull;

public class AdventureComponentParser {
   @NotNull
   public static net.kyori.adventure.text.TextComponent getTextComponent(CharSequence var0) {
      return getLegacyComponentSerializer().deserialize(var0.toString());
   }

   public static Component getComponent(CharSequence var0) {
      return GsonComponentSerializer.gson().deserialize(var0.toString());
   }

   @NotNull
   public static LegacyComponentSerializer getLegacyComponentSerializer() {
      return LegacyComponentSerializer.builder().useUnusualXRepeatedCharacterHexFormat().build();
   }
}
