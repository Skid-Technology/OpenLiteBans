package litebans.lib.kotlin.text;

import litebans.lib.kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public class StringsKt__StringBuilderJVMKt extends StringsKt__RegexExtensionsKt {
   public static final StringBuilder getStringBuilder(@NotNull StringBuilder var0) {
      Intrinsics.handleObject3(var0, "<this>");
      var0.setLength(0);
      return var0;
   }
}
