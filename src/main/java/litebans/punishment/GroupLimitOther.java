package litebans.punishment;

import org.jetbrains.annotations.Nullable;

public final class GroupLimitOther {
   private final boolean templateOnly;

   public GroupLimitOther(boolean var1) {
      this.templateOnly = var1;
   }

   public final boolean isTemplateOnly() {
      return this.templateOnly;
   }

   @Override
   public String toString() {
      return "GroupLimitOther(templateOnly=" + this.templateOnly + ')';
   }

   @Override
   public int hashCode() {
      return Boolean.hashCode(this.templateOnly);
   }

   @Override
   public boolean equals(@Nullable Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof GroupLimitOther)) {
         return false;
      } else {
         GroupLimitOther var2 = (GroupLimitOther)var1;
         return this.templateOnly == var2.templateOnly;
      }
   }
}
