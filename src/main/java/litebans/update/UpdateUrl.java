package litebans.update;

import org.jetbrains.annotations.NotNull;

public final class UpdateUrl {
   public static final UpdateUrl INSTANCE = new UpdateUrl();
   private static String string = "litebans";
   private static String string2 = "?resourceId=";
   private static String string3 = "redacted";
   private static String string4 = "&pluginVersion=";
   private static String string5 = "&v=";

   private UpdateUrl() {
   }

   public final String getString() {
      return string;
   }

   public final void handleString(@NotNull String var1) {
      string = var1;
   }

   public final String getString2() {
      return string2;
   }

   public final void handleString2(@NotNull String var1) {
      string2 = var1;
   }

   public final String getString3() {
      return string3;
   }

   public final void handleString3(@NotNull String var1) {
      string3 = var1;
   }

   public final String getString4() {
      return string4;
   }

   public final void handleString4(@NotNull String var1) {
      string4 = var1;
   }

   public final String getString5() {
      return string5;
   }

   public final void handleString5(@NotNull String var1) {
      string5 = var1;
   }
}
