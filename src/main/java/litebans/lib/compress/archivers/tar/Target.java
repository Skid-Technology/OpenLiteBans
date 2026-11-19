package litebans.lib.compress.archivers.tar;

import litebans.lib.kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class Target {
   public static final TargetCompanion Companion = new TargetCompanion(null);
   private final byte type;
   private final String target;
   public static final byte byteValue = 0;
   public static final byte byteValue2 = 1;
   public static final byte byteValue3 = 2;

   public Target(byte var1, @NotNull String var2) {
      this.type = var1;
      this.target = var2;
   }

   public final byte getType() {
      return this.type;
   }

   public final String getTarget() {
      return this.target;
   }

   public final String getString() {
      byte var1 = this.type;
      if (var1 == 0) {
         return "name";
      } else if (var1 == 1) {
         return "uuid";
      } else if (var1 == 2) {
         return "ip";
      } else {
         throw new AssertionError();
      }
   }

   @Override
   public String toString() {
      return "Target(type=" + this.type + ", target=" + this.target + ')';
   }

   @Override
   public int hashCode() {
      int var1 = Byte.hashCode(this.type);
      return var1 * 31 + this.target.hashCode();
   }

   @Override
   public boolean equals(@Nullable Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof Target)) {
         return false;
      } else {
         Target var2 = (Target)var1;
         return this.type != var2.type ? false : Intrinsics.checkObject(this.target, var2.target);
      }
   }
}
