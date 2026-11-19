package litebans.config;

import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TemplateData {
   private final String string;
   private final String string2;
   private final String string3;
   private final String string4;
   private final List list;
   private final List list2;

   public TemplateData(@Nullable String var1, @Nullable String var2, @Nullable String var3, @Nullable String var4, @NotNull List var5, @NotNull List var6) {
      this.string = var1;
      this.string2 = var2;
      this.string3 = var3;
      this.string4 = var4;
      this.list = var5;
      this.list2 = var6;
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

   public final String getString4() {
      return this.string4;
   }

   public final List getList() {
      return this.list;
   }

   public final List getList2() {
      return this.list2;
   }
}
