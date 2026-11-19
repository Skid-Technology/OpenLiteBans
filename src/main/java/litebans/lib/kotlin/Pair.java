package litebans.lib.kotlin;

import java.io.Serializable;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

public final class Pair implements Serializable {
   private final Object object;
   private final Object object2;

   public Pair(Object var1, Object var2) {
      this.object = var1;
      this.object2 = var2;
   }

   public final Object getObject() {
      return this.object;
   }

   public final Object getObject2() {
      return this.object2;
   }

   @Override
   public String toString() {
      return "" + '(' + this.object + ", " + this.object2 + ')';
   }

   public final Object getObject3() {
      return this.object;
   }

   public final Object getObject22() {
      return this.object2;
   }

   @Override
   public int hashCode() {
      int var1 = this.object == null ? 0 : this.object.hashCode();
      return var1 * 31 + (this.object2 == null ? 0 : this.object2.hashCode());
   }

   @Override
   public boolean equals(@Nullable Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof Pair)) {
         return false;
      } else {
         Pair var2 = (Pair)var1;
         return !Intrinsics.checkObject(this.object, var2.object) ? false : Intrinsics.checkObject(this.object2, var2.object2);
      }
   }
}
