package litebans.config;

import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class StoredTemplateData extends TemplateData {
   private final Long longValue;

   public StoredTemplateData(
      @Nullable Long var1, @Nullable String var2, @Nullable String var3, @Nullable String var4, @Nullable String var5, @NotNull List var6, @NotNull List var7
   ) {
      super(var2, var3, var4, var5, var6, var7);
      this.longValue = var1;
   }

   public final Long getLongValue() {
      return this.longValue;
   }
}
