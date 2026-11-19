package litebans.database;

import java.util.Locale;
import litebans.lib.kotlin.jvm.internal.DefaultConstructorMarker;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class DatabaseDriver {
   private DatabaseDriver() {
   }

   public final String getString() {
      return DatabaseManager.getString22();
   }

   public final String getString2() {
      return DatabaseManager.getString3();
   }

   public final String getString3() {
      return DatabaseManager.getString4();
   }

   public final boolean checkString(@NotNull String var1) {
      return Intrinsics.checkObject(var1, "mysql") || Intrinsics.checkObject(var1, "mariadb");
   }

   public final boolean checkString2(@NotNull String var1) {
      return !Intrinsics.checkObject(var1, "sqlite") && !Intrinsics.checkObject(var1, "h2");
   }

   public final String getString(@NotNull String var1) {
      String var2 = var1.toLowerCase(Locale.ENGLISH);
      switch (var2.hashCode()) {
         case -894935028:
            if (var2.equals("sqlite")) {
               return "SQLite";
            }
            break;
         case 3274:
            if (var2.equals("h2")) {
               return "H2";
            }
            break;
         case 104382626:
            if (var2.equals("mysql")) {
               return "MySQL";
            }
            break;
         case 106616951:
            if (var2.equals("pgsql")) {
               return "PostgreSQL";
            }
            break;
         case 839186932:
            if (var2.equals("mariadb")) {
               return "MariaDB";
            }
      }

      return var1;
   }

   public DatabaseDriver(DefaultConstructorMarker var1) {
      this();
   }
}
