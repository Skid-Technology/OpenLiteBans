package litebans.chat;

public enum JsonMode {
   NONE,
   FORMATTING,
   EVENTS,
   ALL;
   private static final JsonMode[] jsonModeArray = getJsonModeArray();

   public static JsonMode getJsonMode(String var0) {
      return Enum.valueOf(JsonMode.class, var0);
   }

   private static final JsonMode[] getJsonModeArray() {
      return new JsonMode[]{NONE, FORMATTING, EVENTS, ALL};
   }
}
