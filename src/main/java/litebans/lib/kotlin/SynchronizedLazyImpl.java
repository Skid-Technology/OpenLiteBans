package litebans.lib.kotlin;

import java.io.Serializable;
import litebans.lib.kotlin.jvm.internal.DefaultConstructorMarker;
import litebans.lib.kotlin.jvm.internal.Function0;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class SynchronizedLazyImpl implements Lazy, Serializable {
   private Function0 initializer;
   private volatile Object object;
   private final Object object2;

   public SynchronizedLazyImpl(@NotNull Function0 initializer, @Nullable Object object) {
      Intrinsics.handleObject3(initializer, "initializer");
      this.initializer = initializer;
      this.object = UninitializedValue.INSTANCE;
      Object var10001 = object;
      if (object == null) {
         var10001 = this;
      }

      this.object2 = var10001;
   }

   public SynchronizedLazyImpl(Function0 var1, Object var2, int var3, DefaultConstructorMarker var4) {
      this(var1, ((var3 & 2) != 0 ? null : var2));
   }

   // $VF: Extended synchronized range to monitorexit
   @Override
   public Object getValue() {
      Object var1 = this.object;
      if (var1 != UninitializedValue.INSTANCE) {
         return var1;
      } else {
         Object var2 = this.object2;
         synchronized (var2) {
            Object var4 = this.object;
            if (var4 != UninitializedValue.INSTANCE) {
               return var4;
            }
            Intrinsics.handleObject(this.initializer);
            Object var5 = this.initializer.invoke();
            this.object = var5;
            this.initializer = null;
            return var5;
         }
      }
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
