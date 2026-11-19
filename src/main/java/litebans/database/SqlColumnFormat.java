package litebans.database;

import litebans.lib.kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class SqlColumnFormat {
   public static final SqlColumnFormats sqlColumnFormats = new SqlColumnFormats(null);
   private final String string;
   private final String engine;
   private final String charset;
   private final String unsigned;
   public static final String string2 = "utf8mb4_unicode_ci";
   public static final String string3 = " CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci";
   public static final String string4 = " CHARACTER SET ascii COLLATE ascii_general_ci";

   public SqlColumnFormat(@NotNull String var1, @NotNull String var2, @NotNull String var3, @NotNull String var4) {
      this.string = var1;
      this.engine = var2;
      this.charset = var3;
      this.unsigned = var4;
   }

   public final String getString() {
      return this.string;
   }

   public final String getEngine() {
      return this.engine;
   }

   public final String getCharset() {
      return this.charset;
   }

   public final String getUnsigned() {
      return this.unsigned;
   }

   @Override
   public String toString() {
      return "(a=" + this.string + ", engine=" + this.engine + ", charset=" + this.charset + ", unsigned=" + this.unsigned + ')';
   }

   @Override
   public int hashCode() {
      int var1 = this.string.hashCode();
      var1 = var1 * 31 + this.engine.hashCode();
      var1 = var1 * 31 + this.charset.hashCode();
      return var1 * 31 + this.unsigned.hashCode();
   }

   @Override
   public boolean equals(@Nullable Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof SqlColumnFormat)) {
         return false;
      } else {
         SqlColumnFormat var2 = (SqlColumnFormat)var1;
         if (!Intrinsics.checkObject(this.string, var2.string)) {
            return false;
         } else if (!Intrinsics.checkObject(this.engine, var2.engine)) {
            return false;
         } else {
            return !Intrinsics.checkObject(this.charset, var2.charset) ? false : Intrinsics.checkObject(this.unsigned, var2.unsigned);
         }
      }
   }
}
