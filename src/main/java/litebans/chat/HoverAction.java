package litebans.chat;

public enum HoverAction {
   SHOW_TEXT;
   private static final HoverAction[] hoverActionArray = getHoverActionArray();

   public static HoverAction getHoverAction(String var0) {
      return Enum.valueOf(HoverAction.class, var0);
   }

   private static final HoverAction[] getHoverActionArray() {
      return new HoverAction[]{SHOW_TEXT};
   }
}
