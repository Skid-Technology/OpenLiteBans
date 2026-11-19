package litebans.api;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Events$Listener {
   public void broadcastSent(@NotNull String var1, @Nullable String var2) {
   }

   public void entryAdded(Entry var1) {
   }

   public void entryRemoved(Entry var1) {
   }

   private static void $$$reportNull$$$0(int var0) {
      throw new IllegalArgumentException(
         String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "message", "litebans/api/Events$Listener", "broadcastSent")
      );
   }
}
