package litebans.lib.kotlin;

import java.io.Serializable;

public final class InitializedLazyImpl implements Lazy, Serializable {
   private final Object object;

   public InitializedLazyImpl(Object var1) {
      this.object = var1;
   }

   @Override
   public Object getValue() {
      return this.object;
   }

   @Override
   public boolean isInitialized() {
      return true;
   }

   @Override
   public String toString() {
      return String.valueOf(this.getValue());
   }
}
