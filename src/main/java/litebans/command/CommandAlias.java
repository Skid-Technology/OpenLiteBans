package litebans.command;

import java.util.Objects;
import litebans.lib.kotlin.KotlinVersion;
import litebans.lib.kotlin.jvm.internal.DefaultConstructorMarker;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

public final class CommandAlias {
   private final int intValue;
   private String string;
   private String string2;

   private CommandAlias(int var1, String var2, String var3) {
      this.intValue = var1;
      this.string = var2;
      this.string2 = var3;
   }

   public final int getIntValue() {
      return this.intValue;
   }

   public final String getString() {
      return this.string;
   }

   public final void setString(@Nullable String var1) {
      this.string = var1;
   }

   public final String getString2() {
      return this.string2;
   }

   public final void setString2(@Nullable String var1) {
      this.string2 = var1;
   }

   @Override
   public String toString() {
      return Objects.toString(this.string);
   }

   public final int getIntValue2() {
      return this.intValue;
   }

   public final boolean check() {
      return this.string != null && this.string2 != null && Integer.compareUnsigned(this.intValue, 65535) < 0;
   }

   public final boolean check2() {
      return !this.check();
   }

   @Override
   public int hashCode() {
      int var1 = KotlinVersion.getInt(this.intValue);
      var1 = var1 * 31 + (this.string == null ? 0 : this.string.hashCode());
      return var1 * 31 + (this.string2 == null ? 0 : this.string2.hashCode());
   }

   @Override
   public boolean equals(@Nullable Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof CommandAlias)) {
         return false;
      } else {
         CommandAlias var2 = (CommandAlias)var1;
         if (this.intValue != var2.intValue) {
            return false;
         } else {
            return !Intrinsics.checkObject(this.string, var2.string) ? false : Intrinsics.checkObject(this.string2, var2.string2);
         }
      }
   }

   public CommandAlias(int var1, String var2, String var3, DefaultConstructorMarker var4) {
      this(var1, var2, var3);
   }
}
