package litebans.update;

import java.util.List;
import litebans.lib.kotlin.jvm.internal.DefaultConstructorMarker;
import litebans.lib.kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

public final class VersionParser {
   private VersionParser() {
   }

   public final Version getVersion(@NotNull String var1, @NotNull Integer[] var2) {
      String var3 = var1;
      if (StringsKt.checkString(var1, "git:", false, 2, null)) {
         var3 = StringsKt.getString4(var1, ':', null, 2, null);
      }

      if (StringsKt.checkCharSequence3(var3, '+', false, 2, null)) {
         CharSequence var10000 = var3;
         char[] var4 = new char[]{'+'};
         var3 = (String)StringsKt.getList(var10000, var4, false, 0, 6, null).get(1);
      }

      if (StringsKt.checkCharSequence3(var3, '-', false, 2, null)) {
         CharSequence var11 = var3;
         char[] var9 = new char[]{'-'};
         var3 = (String)StringsKt.getList(var11, var9, false, 0, 6, null).get(0);
      }

      int var10 = var2[0];
      int var5 = var2[1];
      int var6 = var2[2];
      if (StringsKt.checkCharSequence3(var3, '.', false, 2, null)) {
         CharSequence var12 = var3;
         char[] var8 = new char[]{'.'};
         List var7 = StringsKt.getList(var12, var8, false, 0, 6, null);
         if (var7.size() >= 3) {
            Integer var13 = StringsKt.getInteger((String)var7.get(0));
            var10 = var13 != null ? var13 : var10;
            Integer var14 = StringsKt.getInteger((String)var7.get(1));
            var5 = var14 != null ? var14 : var5;
            Integer var15 = StringsKt.getInteger((String)var7.get(2));
            var6 = var15 != null ? var15 : var6;
         }
      } else {
         Integer var16 = StringsKt.getInteger(var3);
         var10 = var16 != null ? var16 : var10;
      }

      return new Version(var10, var5, var6);
   }

   public static Version getVersionDefault(VersionParser var0, String var1, Integer[] var2, int var3, Object var4) {
      if ((var3 & 2) != 0) {
         Integer[] var5 = new Integer[]{1, 21, 5};
         var2 = var5;
      }

      return var0.getVersion(var1, var2);
   }

   public VersionParser(DefaultConstructorMarker var1) {
      this();
   }
}
