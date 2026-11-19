package litebans.lib.compress.archivers.tar;

import java.util.HashSet;
import java.util.Locale;
import java.util.UUID;
import litebans.hook.UsernamePattern;
import litebans.lib.kotlin.collections.ArraysKt;
import litebans.lib.kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class TargetParser {
   public static final TargetParser INSTANCE = new TargetParser();
   private static final HashSet hashSet = ArraysKt.getHashSet("0123456789.*%_".toCharArray());
   private static final HashSet hashSet2 = ArraysKt.getHashSet("0123456789ABCDEFMVZabcdefmvz".toCharArray());
   private static final HashSet hashSet3 = ArraysKt.getHashSet("MVZmvz".toCharArray());

   private TargetParser() {
   }

   public final String getString(@Nullable String var1) {
      return var1 == null ? null : getString2(var1);
   }

   public static final String getString2(@NotNull String var0) {
      String var1 = var0;
      if (checkString7(var0)) {
         String var2 = UsernamePattern.mojangApi.getString3(var0);
         var1 = var2.toLowerCase(Locale.ENGLISH);
      }

      return var1;
   }

   public final UUID getUUID(@NotNull String var1) {
      String var2 = getString2(var1);
      return checkString7(var2) ? UUID.fromString(var2) : null;
   }

   public final boolean checkString(@Nullable String var1) {
      boolean var10000;
      if (var1 != null && var1.length() != 0) {
         boolean var2;
         try {
            Double.parseDouble(var1);
            var2 = true;
         } catch (NumberFormatException var3) {
            var2 = false;
         }

         var10000 = var2;
      } else {
         var10000 = false;
      }

      return var10000;
   }

   public final boolean checkString2(@Nullable String var1) {
      return var1 != null
         && !StringsKt.checkCharSequence(var1, '.', false, 2, null)
         && (StringsKt.checkCharSequence3(var1, '.', false, 2, null) && this.checkString3(var1) || StringsKt.checkCharSequence3(var1, ':', false, 2, null));
   }

   public final boolean checkString3(@NotNull String var1) {
      if (var1.length() <= 15) {
         CharSequence var2 = var1;
         int var4 = 0;

         boolean var10000;
         while (true) {
            if (var4 >= var2.length()) {
               var10000 = true;
               break;
            }

            char var5 = var2.charAt(var4);
            if (!hashSet.contains(var5)) {
               var10000 = false;
               break;
            }

            var4++;
         }

         if (var10000) {
            return true;
         }
      }

      return false;
   }

   public final boolean checkString4(@Nullable String var1) {
      boolean var4;
      CharSequence var2 = var1;
      boolean var10000;
      if (var2 == null || var2.length() == 0) {
         var10000 = false;
      } else {
         try {
            Long.parseLong(var1);
            var4 = true;
         } catch (NumberFormatException var3) {
            var4 = false;
         }

         var10000 = var4;
      }

      return var10000;
   }

   public final boolean checkString5(@Nullable String var1) {
      return this.checkString4(var1) || this.checkString6(var1);
   }

   public final boolean checkString6(@Nullable String var1) {
      if (var1 != null && var1.length() >= 6 && var1.length() <= 21) {
         if (var1.length() % 3 != 0) {
            CharSequence var2 = var1;
            int var4 = 0;

            boolean var10000;
            while (true) {
               if (var4 >= var2.length()) {
                  var10000 = true;
                  break;
               }

               char var5 = var2.charAt(var4);
               if (hashSet3.contains(var5)) {
                  var10000 = false;
                  break;
               }

               var4++;
            }

            if (var10000) {
               return false;
            }
         }

         CharSequence var7 = var1;

         for (int var8 = 0; var8 < var7.length(); var8++) {
            char var9 = var7.charAt(var8);
            if (!hashSet2.contains(var9)) {
               return false;
            }
         }

         return true;
      } else {
         return false;
      }
   }

   public static final boolean checkString7(@Nullable String var0) {
      if (var0 == null) {
         return false;
      } else {
         int var1 = var0.length();
         if (var1 == 36 || var1 == 32) {
            CharSequence var2 = var0;
            int var4 = 0;

            boolean var10000;
            while (true) {
               if (var4 >= var2.length()) {
                  var10000 = true;
                  break;
               }

               char var5 = var2.charAt(var4);
               if (!Character.isLetterOrDigit(var5) && var5 != '-') {
                  var10000 = false;
                  break;
               }

               var4++;
            }

            if (var10000) {
               return true;
            }
         }

         return false;
      }
   }

   public final Target getTarget(@NotNull String var1) {
      String var2 = var1;
      byte var3 = 0;
      if (this.checkString2(var1)) {
         var3 = 2;
         var2 = var1.toLowerCase(Locale.ENGLISH);
      } else if (checkString7(var1)) {
         var2 = getString2(var1);
         var3 = 1;
      }

      return new Target(var3, var2);
   }
}
