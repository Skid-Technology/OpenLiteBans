package litebans.chat;

// $VF: synthetic class
public class JsonComponentWhenMappings {
   public static final int[] switchMap;
   public static final int[] switchMap2 = new int[ChatComponentVersion.values().length];

   static {
      try {
         switchMap2[ChatComponentVersion.V1_21_5.ordinal()] = 1;
      } catch (NoSuchFieldError var5) {
      }

      try {
         switchMap2[ChatComponentVersion.V1_16.ordinal()] = 2;
      } catch (NoSuchFieldError var4) {
      }

      switchMap = new int[ClickAction.values().length];

      try {
         switchMap[ClickAction.OPEN_URL.ordinal()] = 1;
      } catch (NoSuchFieldError var3) {
      }

      try {
         switchMap[ClickAction.RUN_COMMAND.ordinal()] = 2;
      } catch (NoSuchFieldError var2) {
      }

      try {
         switchMap[ClickAction.SUGGEST_COMMAND.ordinal()] = 3;
      } catch (NoSuchFieldError var1) {
      }

      try {
         switchMap[ClickAction.CHANGE_PAGE.ordinal()] = 4;
      } catch (NoSuchFieldError var0) {
      }
   }
}
