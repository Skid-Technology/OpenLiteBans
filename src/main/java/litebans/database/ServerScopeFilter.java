package litebans.database;

import litebans.lib.kotlin.KotlinAssertionError;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.platform.LiteBansPlatform;
import litebans.punishment.ServerScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ServerScopeFilter implements CharSequence {
   private final LiteBansPlatform liteBansPlatform;
   private String string;
   private final byte byteValue;

   public ServerScopeFilter(@NotNull LiteBansPlatform var1, @Nullable String var2) {
      this.liteBansPlatform = var1;
      this.string = var2;
      this.string = ServerScope.serverScopeParser.getString(this.string);
      byte var10001;
      if (Intrinsics.checkObject(this.string, "__ALL__")) {
         var10001 = 0;
      } else {
         String var5 = this.getDatabaseManager().getString();
         byte var6 = 1;
         if (this.string != null && !ServerScope.serverScopeParser.checkString(this.string)) {
            var6++;
         }

         if (this.liteBansPlatform.isProxy() && !Intrinsics.checkObject(var5, this.string)) {
            var6++;
         }

         var10001 = var6;
      }

      this.byteValue = var10001;
   }

   public final String getString() {
      return this.string;
   }

   public final DatabaseManager getDatabaseManager() {
      return (DatabaseManager)this.liteBansPlatform.getModule(DatabaseManager.class);
   }

   public void handleQueryStatement(@NotNull QueryStatement var1) {
      if (this.byteValue >= 2) {
         var1.getQueryStatement(this.string);
      }

      if (this.byteValue >= 3) {
         var1.getQueryStatement(this.getDatabaseManager().getString());
      }
   }

   public int getInt() {
      return this.toString().length();
   }

   public char getChar(int var1) {
      return this.toString().charAt(var1);
   }

   @Override
   public CharSequence subSequence(int var1, int var2) {
      return this.toString().subSequence(var1, var2);
   }

   @Override
   public String toString() {
      String var10000;
      switch (this.byteValue) {
         case 0:
            var10000 = "";
            break;
         case 1:
            var10000 = " AND(server_scope='*')";
            break;
         case 2:
            var10000 = " AND(server_scope='*' OR server_scope=?)";
            break;
         case 3:
            var10000 = " AND(server_scope='*' OR server_scope=? OR server_scope=?)";
            break;
         default:
            throw new KotlinAssertionError();
      }

      return var10000;
   }

   @Override
   public final int length() {
      return this.getInt();
   }

   @Override
   public final char charAt(int var1) {
      return this.getChar(var1);
   }
}
