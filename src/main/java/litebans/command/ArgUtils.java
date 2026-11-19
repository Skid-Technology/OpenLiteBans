package litebans.command;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import litebans.chat.ChatColorCode;
import litebans.lib.kotlin.collections.CollectionsKt;
import litebans.lib.kotlin.text.Charsets;
import litebans.lib.kotlin.text.Regex;
import litebans.lib.kotlin.text.StringsKt;
import litebans.punishment.EntryPlaceholders;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class ArgUtils {
   public static final String getString(@NotNull CharSequence var0, @NotNull CharSequence[] var1) {
      String var2 = var0.toString();
      int var3 = 0;
      int var4 = var1.length;
      if (var4 % 2 == 1) {
         throw new ArrayIndexOutOfBoundsException("Odd arg length " + var4);
      } else {
         while (var3 < var4) {
            CharSequence var5 = var1[var3++];
            CharSequence var6 = var1[var3++];
            var2 = EntryPlaceholders.getString(var2, var5, var6);
         }

         return var2;
      }
   }

   public static final CharSequence getCharSequence(@NotNull CharSequence var0, boolean var1, @Nullable CharSequence var2) {
      CharSequence var10000;
      if (var2 != null && var2.length() > 0 && var1) {
         CharSequence var4 = "\n";
         CharSequence var3 = "" + var0 + var4;
         var10000 = "" + var3 + var2;
      } else {
         var10000 = var0;
      }

      return var10000;
   }

   public static final CharSequence getCharSequence(@NotNull CharSequence var0, @Nullable CharSequence var1) {
      CharSequence var10000;
      if (var1 != null && var1.length() > 0) {
         CharSequence var3 = "\n";
         CharSequence var2 = "" + var0 + var3;
         var10000 = "" + var2 + var1;
      } else {
         var10000 = var0;
      }

      return var10000;
   }

   public static final byte[] getByteArray(@NotNull StringBuilder var0, @NotNull Charset var1) {
      return var0.toString().getBytes(var1);
   }

   public static final String getString(char var0, @NotNull CharSequence var1) {
      return "" + var0 + var1;
   }

   public static final String getString(@NotNull ChatColorCode var0, @NotNull String var1) {
      return var0 + var1;
   }

   public static final boolean checkCharSequence(@NotNull CharSequence var0) {
      return StringsKt.checkCharSequence3(var0, "  ", false, 2, null);
   }

   public static final String getString(@NotNull CharSequence var0) {
      Regex var2 = new Regex("\\s+");
      String var3 = " ";
      return var2.getString(var0, var3);
   }

   public static final String getString(@NotNull String var0) {
      String var1 = "OfflinePlayer:" + var0;
      return UUID.nameUUIDFromBytes(var1.getBytes(Charsets.charset)).toString();
   }

   public static final String getString(@NotNull String var0, int var1) {
      return var0.length() <= var1 ? var0 : var0.substring(0, var1);
   }

   public static final List getList(@NotNull List var0) {
      String var1 = CollectionsKt.getString(var0, " ", null, null, 0, null, null, 62, null);
      if (!StringsKt.checkCharSequence3(var1, '"', false, 2, null) && !StringsKt.checkCharSequence3(var1, '\'', false, 2, null)) {
         return var0;
      } else {
         ArrayList var2 = new ArrayList();
         StringBuilder var3 = new StringBuilder();
         boolean var4 = false;
         char[] var5 = var1.toCharArray();
         int var6 = 0;

         for (int var7 = var5.length; var6 < var7; var6++) {
            char var8 = var5[var6];
            if (var8 == ' ' && !var4) {
               var2.add(var3.toString());
               StringsKt.getStringBuilder(var3);
            } else if (var8 != '"' && var8 != '\'') {
               var3.append(var8);
            } else {
               var4 = !var4;
               if (!var4) {
                  var2.add(var3.toString());
                  StringsKt.getStringBuilder(var3);
               }
            }
         }

         return var2;
      }
   }
}
