package litebans.database;

import litebans.lib.kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class DriverLibrary {
   private final String string;
   private final String string2;
   private final String string3;
   private final String string4;
   private final String string5;

   public DriverLibrary(@NotNull String var1, @NotNull String var2, @Nullable String var3, @Nullable String var4, @Nullable String var5) {
      this.string = var1;
      this.string2 = var2;
      this.string3 = var3;
      this.string4 = var4;
      this.string5 = var5;
   }

   public final String getString() {
      return this.string;
   }

   public final String getString2() {
      return this.string2;
   }

   public final String getString3() {
      return this.string3;
   }

   public final String getString5() {
      return this.string5;
   }

   @Override
   public String toString() {
      return this.string + ' ' + this.string4;
   }

   @Override
   public int hashCode() {
      int var1 = this.string.hashCode();
      var1 = var1 * 31 + this.string2.hashCode();
      var1 = var1 * 31 + (this.string3 == null ? 0 : this.string3.hashCode());
      var1 = var1 * 31 + (this.string4 == null ? 0 : this.string4.hashCode());
      return var1 * 31 + (this.string5 == null ? 0 : this.string5.hashCode());
   }

   @Override
   public boolean equals(@Nullable Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof DriverLibrary)) {
         return false;
      } else {
         DriverLibrary var2 = (DriverLibrary)var1;
         if (!Intrinsics.checkObject(this.string, var2.string)) {
            return false;
         } else if (!Intrinsics.checkObject(this.string2, var2.string2)) {
            return false;
         } else if (!Intrinsics.checkObject(this.string3, var2.string3)) {
            return false;
         } else {
            return !Intrinsics.checkObject(this.string4, var2.string4) ? false : Intrinsics.checkObject(this.string5, var2.string5);
         }
      }
   }
}
