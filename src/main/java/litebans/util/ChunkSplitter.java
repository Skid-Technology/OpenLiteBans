package litebans.util;

import java.util.Collection;
import litebans.lib.kotlin.jvm.internal.DefaultConstructorMarker;
import litebans.lib.kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

public final class ChunkSplitter {
   private ChunkSplitter() {
   }

   public final String[] getStringArray(@NotNull String var1, int var2) {
      CharSequence var10000 = var1;
      char[] var3 = new char[]{'\ufeff'};
      Collection var6 = StringsKt.getList(var10000, var3, false, var2, 2, null);
      return /* cast-fix */(String[])(var6.toArray(new String[0]));
   }

   public static String[] getStringArrayDefault(ChunkSplitter var0, String var1, int var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         var2 = 2;
      }

      return var0.getStringArray(var1, var2);
   }

   public ChunkSplitter(DefaultConstructorMarker var1) {
      this();
   }
}
