package litebans.lib.kotlin;

import java.io.Serializable;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

public final class Triple implements Serializable {
   private final Object object;
   private final Object object2;
   private final Object object3;

   public Triple(Object var1, Object var2, Object var3) {
      this.object = var1;
      this.object2 = var2;
      this.object3 = var3;
   }

   public final Object getObject() {
      return this.object;
   }

   public final Object getObject2() {
      return this.object2;
   }

   public final Object getObject3() {
      return this.object3;
   }

   @Override
   public String toString() {
      return "" + '(' + this.object + ", " + this.object2 + ", " + this.object3 + ')';
   }

   public final Object getObject4() {
      return this.object;
   }

   public final Object getObject22() {
      return this.object2;
   }

   public final Object getObject32() {
      return this.object3;
   }

   @Override
   public int hashCode() {
      int var1 = this.object == null ? 0 : this.object.hashCode();
      var1 = var1 * 31 + (this.object2 == null ? 0 : this.object2.hashCode());
      return var1 * 31 + (this.object3 == null ? 0 : this.object3.hashCode());
   }

   @Override
   public boolean equals(@Nullable Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof Triple)) {
         return false;
      } else {
         Triple var2 = (Triple)var1;
         if (!Intrinsics.checkObject(this.object, var2.object)) {
            return false;
         } else {
            return !Intrinsics.checkObject(this.object2, var2.object2) ? false : Intrinsics.checkObject(this.object3, var2.object3);
         }
      }
   }
}
