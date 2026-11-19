package litebans.punishment;

import litebans.core.Placeholders;
import litebans.lib.kotlin.jvm.internal.DefaultConstructorMarker;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.text.Regex;
import litebans.lib.kotlin.text.StringsKt;
import org.jetbrains.annotations.Nullable;

public final class WildcardPattern {
   private WildcardPattern() {
   }

   public final boolean checkString(@Nullable String var1, @Nullable String var2) {
      return var1 != null && Intrinsics.checkObject(var1, var2);
   }

   public final boolean checkBoolean(boolean var1, @Nullable String var2, @Nullable String var3, boolean var4) {
      if (var1 && var3 != null && var2 != null) {
         if (Intrinsics.checkObject(var3, var2)) {
            return true;
         }

         if (var4) {
            CharSequence var5 = var3;
            if (new Regex(Placeholders.Companion.getString(var2, "%", ".*")).checkCharSequence(var5)) {
               return true;
            }
         }
      }

      return false;
   }

   public final boolean checkString(@Nullable String var1) {
      return var1 != null && (StringsKt.checkCharSequence3(var1, '%', false, 2, null) || StringsKt.checkCharSequence3(var1, '_', false, 2, null));
   }

   public WildcardPattern(DefaultConstructorMarker var1) {
      this();
   }
}
