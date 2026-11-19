package litebans.chat;

public enum ClickAction {
   OPEN_URL,
   OPEN_FILE,
   RUN_COMMAND,
   SUGGEST_COMMAND,
   CHANGE_PAGE,
   COPY_TO_CLIPBOARD;
   private static final ClickAction[] clickActionArray = getClickActionArray();

   public static ClickAction getClickAction(String var0) {
      return Enum.valueOf(ClickAction.class, var0);
   }

   private static final ClickAction[] getClickActionArray() {
      return new ClickAction[]{OPEN_URL, OPEN_FILE, RUN_COMMAND, SUGGEST_COMMAND, CHANGE_PAGE, COPY_TO_CLIPBOARD};
   }
}
