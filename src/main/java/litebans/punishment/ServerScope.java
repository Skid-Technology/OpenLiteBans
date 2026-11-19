package litebans.punishment;

import org.jetbrains.annotations.Nullable;

public final class ServerScope {
   public static final ServerScopeParser serverScopeParser = new ServerScopeParser(null);
   private final String string;
   private static final String string2 = "*";
   private static final ServerScope INSTANCE = new ServerScope("");
   private static final int intValue = 7;

   public ServerScope(@Nullable String var1) {
      this.string = var1;
   }

   public final String getString() {
      return this.string;
   }

   public static final String getString2() {
      return string2;
   }

   public static final ServerScope getInstance() {
      return INSTANCE;
   }
}
