package litebans.chat;

public enum ChatComponentVersion {
   V1_16,
   V1_21_5;
   private static final ChatComponentVersion[] chatComponentVersionArray = getChatComponentVersionArray();

   public static ChatComponentVersion getChatComponentVersion(String var0) {
      return Enum.valueOf(ChatComponentVersion.class, var0);
   }

   private static final ChatComponentVersion[] getChatComponentVersionArray() {
      return new ChatComponentVersion[]{V1_16, V1_21_5};
   }
}
