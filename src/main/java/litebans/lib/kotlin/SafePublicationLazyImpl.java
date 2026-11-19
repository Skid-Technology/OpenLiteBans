package litebans.lib.kotlin;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import litebans.lib.kotlin.jvm.internal.Function0;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class SafePublicationLazyImpl implements Lazy, Serializable {
   public static final SafePublicationLazyImplCompanion Companion = new SafePublicationLazyImplCompanion(null);
   private volatile Function0 initializer;
   private volatile Object _value;
   private final Object object;
   private static final AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = AtomicReferenceFieldUpdater.newUpdater(
      SafePublicationLazyImpl.class, Object.class, "_value"
   );

   public SafePublicationLazyImpl(@NotNull Function0 initializer) {
      Intrinsics.handleObject3(initializer, "initializer");
      this.initializer = initializer;
      this._value = UninitializedValue.INSTANCE;
      this.object = UninitializedValue.INSTANCE;
   }

   @Override
   public Object getValue() {
      Object var1 = this._value;
      if (var1 != UninitializedValue.INSTANCE) {
         return var1;
      } else {
         Function0 var2 = this.initializer;
         if (var2 != null) {
            Object var3 = var2.invoke();
            if (atomicReferenceFieldUpdater.compareAndSet(this, UninitializedValue.INSTANCE, (UninitializedValue)var3)) {
               this.initializer = null;
               return var3;
            }
         }

         return this._value;
      }
   }

   @Override
   public boolean isInitialized() {
      return this._value != UninitializedValue.INSTANCE;
   }

   @Override
   public String toString() {
      return this.isInitialized() ? String.valueOf(this.getValue()) : "Lazy value not initialized yet.";
   }

   private final Object getObject2() {
      return new InitializedLazyImpl(this.getValue());
   }
}
