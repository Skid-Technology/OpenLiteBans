package litebans.database;

import litebans.lib.kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

public final class SqlFalse {
   public static final String getString(@NotNull CharSequence var0) {
      CharSequence[] var1 = new CharSequence[]{var0};
      CharSequence[] var3 = var1;
      int var5 = 0;

      for (int var6 = var1.length; var5 < var6; var5++) {
         CharSequence var7 = var3[var5];
         if (var7.length() <= 0) {
            String var10 = "Failed requirement.";
            throw new IllegalArgumentException(var10.toString());
         }
      }

      return SqlString.getString2(var0 + "={false}");
   }

   public static final void handleCharSequence(@NotNull CharSequence var0) {
      if (StringsKt.checkCharSequence3(var0, ' ', false, 2, null)
         || StringsKt.checkCharSequence3(var0, '=', false, 2, null)
         || StringsKt.checkCharSequence3(var0, '>', false, 2, null)
         || StringsKt.checkCharSequence3(var0, '<', false, 2, null)
         || StringsKt.checkCharSequence3(var0, ',', false, 2, null)) {
         String var1 = "Failed requirement.";
         throw new IllegalArgumentException(var1.toString());
      }
   }

   public static final void handleCharSequence2(@NotNull CharSequence var0) {
      if (!StringsKt.checkCharSequence3(var0, ' ', false, 2, null)) {
         String var1 = "Failed requirement.";
         throw new IllegalArgumentException(var1.toString());
      }
   }

   public static final void handleCharSequence3(@NotNull CharSequence var0) {
      if (!StringsKt.checkCharSequence3(var0, '=', false, 2, null)) {
         String var1 = "Failed requirement.";
         throw new IllegalArgumentException(var1.toString());
      }
   }
}
