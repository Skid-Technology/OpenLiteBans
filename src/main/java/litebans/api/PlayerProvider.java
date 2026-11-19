package litebans.api;

import litebans.api.exception.MissingImplementationException;
import lombok.Generated;
import org.jetbrains.annotations.NotNull;

public abstract class PlayerProvider {
   private static PlayerProvider instance;

   public static PlayerProvider get() {
      if (instance == null) {
         throw new MissingImplementationException();
      } else {
         return instance;
      }
   }

   @NotNull
   public abstract String provide(String var1);

   @Generated
   public static void setInstance(PlayerProvider var0) {
      instance = var0;
   }
}
