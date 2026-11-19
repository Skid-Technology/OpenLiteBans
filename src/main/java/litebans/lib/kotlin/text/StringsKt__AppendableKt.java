package litebans.lib.kotlin.text;

import litebans.lib.kotlin.jvm.internal.Function1;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class StringsKt__AppendableKt {
   public static final void handleAppendable(@NotNull Appendable var0, Object var1, @Nullable Function1 var2) {
      try {
      Intrinsics.handleObject3(var0, "<this>");
      if (var2 != null) {
         var0.append((CharSequence)var2.invoke(var1));
      } else if (var1 == null ? true : var1 instanceof CharSequence) {
         var0.append((CharSequence)var1);
      } else if (var1 instanceof Character) {
         var0.append((Character)var1);
      } else {
         var0.append(var1.toString());
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }
}
