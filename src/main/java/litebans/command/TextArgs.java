package litebans.command;

import java.util.Arrays;
import litebans.core.LiteBansCore;
import org.jetbrains.annotations.NotNull;

public final class TextArgs {
   private final LiteBansCore liteBansCore;
   private final CharSequence[] charSequenceArray;

   public TextArgs(@NotNull LiteBansCore var1, @NotNull CharSequence[] var2) {
      this.liteBansCore = var1;
      this.charSequenceArray = var2;
   }

   public final LiteBansCore getLiteBansCore() {
      return this.liteBansCore;
   }

   public final CharSequence[] getCharSequenceArray() {
      return this.charSequenceArray;
   }

   public final int getInt() {
      CharSequence[] var1 = this.charSequenceArray;
      return this.liteBansCore.getInt(Arrays.copyOf(var1, var1.length));
   }
}
