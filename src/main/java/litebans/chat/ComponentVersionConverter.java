package litebans.chat;

public class ComponentVersionConverter {
   public static String getString(String var0, ChatComponentVersion var1) {
      return VersionedComponentSerializer.getVersionedComponentSerializer(var1).getString(TextComponent.getBaseComponentArray(var0));
   }
}
