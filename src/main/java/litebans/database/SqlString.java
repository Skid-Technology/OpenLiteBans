package litebans.database;

import litebans.core.LiteBansCore;
import litebans.core.NotificationManager;
import litebans.core.Placeholders;
import litebans.core.PluginInstance;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

public final class SqlString implements CharSequence {
   private final String string;

   public static int getInt(String var0) {
      return var0.length();
   }

   public int getInt() {
      return getInt(this.string);
   }

   public static char getChar(String var0, int var1) {
      return var0.charAt(var1);
   }

   public char getChar(int var1) {
      return getChar(this.string, var1);
   }

   public static CharSequence getCharSequence(String var0, int var1, int var2) {
      return var0.subSequence(var1, var2);
   }

   @Override
   public CharSequence subSequence(int var1, int var2) {
      return getCharSequence(this.string, var1, var2);
   }

   public static String getString(String var0) {
      String var1 = var0;
      if (StringsKt.checkCharSequence3(var0, '{', false, 2, null)) {
         LiteBansCore var2 = (LiteBansCore)PluginInstance.Companion.getLiteBansPlatform().getModule(LiteBansCore.class);
         NotificationManager var3 = (NotificationManager)PluginInstance.Companion.getLiteBansPlatform().getModule(NotificationManager.class);
         String var4 = var2.getConfig().getSqlDriver();
         String var5 = "1";
         String var6 = "0";
         if (Intrinsics.checkObject(var4, "pgsql")) {
            var5 = "B'1'";
            var6 = "B'0'";
         }

         var1 = Placeholders.Companion.getString(var0, "{true}", var5);
         var1 = Placeholders.Companion.getString(var1, "{false}", var6);
         var1 = Placeholders.Companion.getString(var1, "{column.id}", !Intrinsics.checkObject(var4, "sqlite") ? "id" : "rowid as id");
         var1 = Placeholders.Companion.getString(var1, "{=?}", "" + var3.getCharSequence2() + '?' + var3.getCharSequence4());
      }

      return var1;
   }

   @Override
   public String toString() {
      return getString(this.string);
   }

   public static final String getString(String var0, @NotNull CharSequence var1) {
      CharSequence[] var2 = new CharSequence[]{getSqlString(var0), var1};
      CharSequence[] var4 = var2;
      int var6 = 0;

      for (int var7 = var2.length; var6 < var7; var6++) {
         CharSequence var8 = var4[var6];
         if (var8.length() <= 0) {
            String var11 = "Failed requirement.";
            throw new IllegalArgumentException(var11.toString());
         }
      }

      return getString2(getString(var0) + " AND " + var1);
   }

   public static final String getString2(String var0, @NotNull CharSequence var1) {
      CharSequence[] var2 = new CharSequence[]{getSqlString(var0), var1};
      CharSequence[] var4 = var2;
      int var6 = 0;

      for (int var7 = var2.length; var6 < var7; var6++) {
         CharSequence var8 = var4[var6];
         if (var8.length() <= 0) {
            String var11 = "Failed requirement.";
            throw new IllegalArgumentException(var11.toString());
         }
      }

      return getString2(getString(var0) + " OR " + var1);
   }

   public static final String getString3(String var0, @NotNull CharSequence var1) {
      CharSequence[] var2 = new CharSequence[]{getSqlString(var0), var1};
      CharSequence[] var4 = var2;
      int var6 = 0;

      for (int var7 = var2.length; var6 < var7; var6++) {
         CharSequence var8 = var4[var6];
         if (var8.length() <= 0) {
            String var11 = "Failed requirement.";
            throw new IllegalArgumentException(var11.toString());
         }
      }

      SqlFalse.handleCharSequence(var1);
      return getString2(getString(var0) + " WHERE " + var1 + " IS NULL");
   }

   public static final String getString4(String var0, @NotNull CharSequence var1) {
      CharSequence[] var2 = new CharSequence[]{getSqlString(var0), var1};
      CharSequence[] var4 = var2;
      int var6 = 0;

      for (int var7 = var2.length; var6 < var7; var6++) {
         CharSequence var8 = var4[var6];
         if (var8.length() <= 0) {
            String var11 = "Failed requirement.";
            throw new IllegalArgumentException(var11.toString());
         }
      }

      SqlFalse.handleCharSequence3(var1);
      return getString2(getString(var0) + " SET " + var1);
   }

   public static final String getString5(String var0, @NotNull CharSequence var1) {
      CharSequence[] var2 = new CharSequence[]{getSqlString(var0), var1};
      CharSequence[] var4 = var2;
      int var6 = 0;

      for (int var7 = var2.length; var6 < var7; var6++) {
         CharSequence var8 = var4[var6];
         if (var8.length() <= 0) {
            String var11 = "Failed requirement.";
            throw new IllegalArgumentException(var11.toString());
         }
      }

      return getString2(getString(var0) + " CONVERT TO " + var1);
   }

   public static final String getString6(String var0, @NotNull CharSequence var1) {
      CharSequence[] var2 = new CharSequence[]{getSqlString(var0), var1};
      CharSequence[] var4 = var2;
      int var6 = 0;

      for (int var7 = var2.length; var6 < var7; var6++) {
         CharSequence var8 = var4[var6];
         if (var8.length() <= 0) {
            String var11 = "Failed requirement.";
            throw new IllegalArgumentException(var11.toString());
         }
      }

      SqlFalse.handleCharSequence2(var1);
      return getString2(getString(var0) + " ADD COLUMN " + var1);
   }

   public static final String getString7(String var0, @NotNull CharSequence var1) {
      CharSequence[] var2 = new CharSequence[]{getSqlString(var0), var1};
      CharSequence[] var4 = var2;
      int var6 = 0;

      for (int var7 = var2.length; var6 < var7; var6++) {
         CharSequence var8 = var4[var6];
         if (var8.length() <= 0) {
            String var11 = "Failed requirement.";
            throw new IllegalArgumentException(var11.toString());
         }
      }

      SqlFalse.handleCharSequence(var1);
      return getString2(getString(var0) + " DROP COLUMN " + var1);
   }

   public static final String getString8(String var0, @NotNull CharSequence var1) {
      CharSequence[] var2 = new CharSequence[]{getSqlString(var0), var1};
      CharSequence[] var4 = var2;
      int var6 = 0;

      for (int var7 = var2.length; var6 < var7; var6++) {
         CharSequence var8 = var4[var6];
         if (var8.length() <= 0) {
            String var11 = "Failed requirement.";
            throw new IllegalArgumentException(var11.toString());
         }
      }

      return getString2(getString(var0) + " ORDER BY " + var1 + " DESC");
   }

   public static final String getString(String var0, @NotNull Number var1) {
      CharSequence[] var2 = new CharSequence[]{getSqlString(var0)};
      CharSequence[] var4 = var2;
      int var6 = 0;

      for (int var7 = var2.length; var6 < var7; var6++) {
         CharSequence var8 = var4[var6];
         if (var8.length() <= 0) {
            String var11 = "Failed requirement.";
            throw new IllegalArgumentException(var11.toString());
         }
      }

      return getString2(getString(var0) + " LIMIT " + var1);
   }

   public static int getInt2(String var0) {
      return var0.hashCode();
   }

   @Override
   public int hashCode() {
      return getInt2(this.string);
   }

   public static boolean checkString(String var0, Object var1) {
      return !(var1 instanceof SqlString) ? false : Intrinsics.checkObject(var0, ((SqlString)var1).getString());
   }

   @Override
   public boolean equals(Object var1) {
      return checkString(this.string, var1);
   }

   private SqlString(String var1) {
      this.string = var1;
   }

   public static String getString2(@NotNull String var0) {
      return var0;
   }

   public static final SqlString getSqlString(String var0) {
      return new SqlString(var0);
   }

   public final String getString() {
      return this.string;
   }

   @Override
   public int length() {
      return this.getInt();
   }

   @Override
   public final char charAt(int var1) {
      return this.getChar(var1);
   }
}
