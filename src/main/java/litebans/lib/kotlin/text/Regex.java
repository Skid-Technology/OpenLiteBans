package litebans.lib.kotlin.text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import litebans.lib.kotlin.collections.CollectionsKt;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.ranges.RangesKt;
import org.jetbrains.annotations.NotNull;

public final class Regex implements Serializable {
   public static final RegexCompanion Companion = new RegexCompanion(null);
   private final Pattern nativePattern;

   public Regex(@NotNull Pattern nativePattern) {
      Intrinsics.handleObject3(nativePattern, "nativePattern");
      this.nativePattern = nativePattern;
   }

   public Regex(@NotNull String pattern) {
      this(Pattern.compile(pattern));
   }

   public final boolean checkCharSequence(@NotNull CharSequence input) {
      Intrinsics.handleObject3(input, "input");
      return this.nativePattern.matcher(input).matches();
   }

   public final String getString(@NotNull CharSequence input, @NotNull String replacement) {
      Intrinsics.handleObject3(input, "input");
      Intrinsics.handleObject3(replacement, "replacement");
      return this.nativePattern.matcher(input).replaceAll(replacement);
   }

   public final String getString2(@NotNull CharSequence input, @NotNull String replacement) {
      Intrinsics.handleObject3(input, "input");
      Intrinsics.handleObject3(replacement, "replacement");
      return this.nativePattern.matcher(input).replaceFirst(replacement);
   }

   public final List getList(@NotNull CharSequence input, int intValue) {
      Intrinsics.handleObject3(input, "input");
      StringsKt.handleInt(intValue);
      Matcher var3 = this.nativePattern.matcher(input);
      if (intValue != 1 && var3.find()) {
         ArrayList var4 = new ArrayList(intValue > 0 ? RangesKt.getInt2(intValue, 10) : 10);
         int var5 = 0;
         int var6 = intValue - 1;

         do {
            var4.add(input.subSequence(var5, var3.start()).toString());
            var5 = var3.end();
         } while ((var6 < 0 || var4.size() != var6) && var3.find());

         var4.add(input.subSequence(var5, input.length()).toString());
         return var4;
      } else {
         return CollectionsKt.getList(input.toString());
      }
   }

   @Override
   public String toString() {
      return this.nativePattern.toString();
   }

   public final Pattern getNativePattern() {
      return this.nativePattern;
   }

   private final Object getObject() {
      return new RegexSerialized(this.nativePattern.pattern(), this.nativePattern.flags());
   }
}
