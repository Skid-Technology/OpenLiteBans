package litebans.command;

import org.jetbrains.annotations.Nullable;

public class AbortException extends RuntimeException {
   private final Object object;

   public AbortException(@Nullable Object var1) {
      super(String.valueOf(var1));
      this.object = var1;
   }

   public final Object getObject() {
      return this.object;
   }
}
