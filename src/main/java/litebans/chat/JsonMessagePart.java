package litebans.chat;

import litebans.lib.kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;

public final class JsonMessagePart {
   private final String string;
   private final String string2;
   private final String string3;
   private final String string4;
   private final boolean flag;
   private final boolean flag2;

   public JsonMessagePart(@NotNull String var1, @NotNull String var2, @NotNull String var3, @NotNull String var4, boolean var5, boolean var6) {
      this.string = var1;
      this.string2 = var2;
      this.string3 = var3;
      this.string4 = var4;
      this.flag = var5;
      this.flag2 = var6;
   }

   public JsonMessagePart(String var1, String var2, String var3, String var4, boolean var5, boolean var6, int var7, DefaultConstructorMarker var8) {
      this(var1, var2, var3, ((var7 & 8) != 0 ? "" : var4), ((var7 & 16) != 0 ? false : var5), ((var7 & 32) != 0 ? false : var6));
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

   public final boolean isFlag() {
      return this.flag;
   }

   public final boolean isFlag2() {
      return this.flag2;
   }
}
