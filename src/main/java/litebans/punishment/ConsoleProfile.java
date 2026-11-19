package litebans.punishment;

import java.util.UUID;

public final class ConsoleProfile {
   private static final UUID uuid = new UUID(0L, 0L);
   private static final String string = uuid.toString();
   private static final Profile profile = new Profile(null, uuid);

   public static final UUID getUuid() {
      return uuid;
   }

   public static final String getString() {
      return string;
   }

   public static final Profile getProfile() {
      return profile;
   }
}
