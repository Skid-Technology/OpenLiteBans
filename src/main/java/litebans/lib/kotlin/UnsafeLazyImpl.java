package litebans.lib.kotlin;

import java.io.Serializable;
import litebans.lib.kotlin.jvm.internal.Function0;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class UnsafeLazyImpl implements Lazy, Serializable {
   private Function0 initializer;
   private Object object;

   public UnsafeLazyImpl(@NotNull Function0 initializer) {
      Intrinsics.handleObject3(initializer, "initializer");
      this.initializer = initializer;
      this.object = UninitializedValue.INSTANCE;
   }

   @Override
   public Object getValue() {
      if (this.object == UninitializedValue.INSTANCE) {
         Function0 var10001 = this.initializer;
         Intrinsics.handleObject(this.initializer);
         this.object = var10001.invoke();
         this.initializer = null;
      }

      return this.object;
   }

   @Override
   public boolean isInitialized() {
      return this.object != UninitializedValue.INSTANCE;
   }

   @Override
   public String toString() {
      return this.isInitialized() ? String.valueOf(this.getValue()) : "Lazy value not initialized yet.";
   }

   private final Object getObject2() {
      return new InitializedLazyImpl(this.getValue());
   }
}
