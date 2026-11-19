package litebans.lib.kotlin.text;

import java.io.Serializable;
import java.util.regex.Pattern;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class RegexSerialized implements Serializable {
   public static final RegexSerializedCompanion Companion = new RegexSerializedCompanion(null);
   private final String pattern;
   private final int intValue;
   private static final long longValue = 0L;

   public RegexSerialized(@NotNull String pattern, int intValue) {
      Intrinsics.handleObject3(pattern, "pattern");
      this.pattern = pattern;
      this.intValue = intValue;
   }

   private final Object getObject() {
      return new Regex(Pattern.compile(this.pattern, this.intValue));
   }
}
