package litebans.util;

import java.io.Serializable;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import litebans.lib.kotlin.Lazy;
import litebans.lib.kotlin.LazyKt;
import litebans.lib.kotlin.jvm.internal.Function0;
import org.jetbrains.annotations.NotNull;

public final class SoftLazy implements Lazy, Serializable {
   private final Function0 function0;
   private volatile Reference reference;

   public SoftLazy(@NotNull Function0 var1) {
      this.function0 = var1;
      this.reference = new SoftReference(null);
   }

   // $VF: Extended synchronized range to monitorexit
   @Override
   public Object getValue() {
      Object var1 = this.reference.get();
      if (var1 != null) {
         return var1;
      } else {
         synchronized (this) {
            Object var4 = this.reference.get();
            Object var10000;
            if (var4 != null) {
               var10000 = var4;
            } else {
               Object var5 = this.function0.invoke();
               this.reference = new SoftReference<>(var5);
               var10000 = var5;
            }

            return var10000;
         }
      }
   }

   @Override
   public boolean isInitialized() {
      return this.reference.get() != null;
   }

   @Override
   public String toString() {
      return String.valueOf(this.isInitialized() ? this.getValue() : null);
   }

   private final Object getObject2() {
      return LazyKt.getLazy(this.getValue());
   }
}
