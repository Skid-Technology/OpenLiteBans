package litebans.core;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import litebans.lib.kotlin.jvm.internal.DefaultConstructorMarker;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class PlaceholdersCompanion {
   private PlaceholdersCompanion() {
   }

   public final String getString(@NotNull Object var1, @NotNull Object var2, @Nullable Object var3) {
      return this.getString(var1.toString(), var2.toString(), var3);
   }

   public final String getString(@NotNull CharSequence var1, @NotNull String var2, @Nullable Object var3) {
      if (!StringsKt.checkCharSequence3(var1, var2, false, 2, null)) {
         return var1.toString();
      } else {
         Pattern var4 = (Pattern)Placeholders.getMap().get(var2);
         if (var4 == null) {
            var4 = this.getPattern(var2);
         }

         String var5 = var4.matcher(var1).replaceAll(Matcher.quoteReplacement(String.valueOf(var3)));
         Intrinsics.handleObject(var5);
         return var5;
      }
   }

   private final Pattern getPattern(String var1) {
      Pattern var2 = Pattern.compile(var1, 16);
      Map var3 = Placeholders.getMap();
      if (var3.size() > 512) {
         var3.clear();
      }

      var3.put(var1, var2);
      Intrinsics.handleObject(var2);
      return var2;
   }

   public PlaceholdersCompanion(DefaultConstructorMarker var1) {
      this();
   }
}
