package litebans.punishment;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import litebans.command.ArgUtils;
import litebans.lib.kotlin.jvm.internal.DefaultConstructorMarker;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.text.StringsKt;
import litebans.platform.Sender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class ServerScopeParser {
   private ServerScopeParser() {
   }

   public final String getString() {
      return ServerScope.getString2();
   }

   public final ServerScope getServerScope() {
      return ServerScope.getInstance();
   }

   public final boolean checkString(@Nullable String var1) {
      return Intrinsics.checkObject(var1, this.getString()) || StringsKt.checkString(var1, "global", true) || StringsKt.checkString(var1, "all", true);
   }

   public final boolean checkString2(@Nullable String var1) {
      return StringsKt.checkString(var1, "local", true) || StringsKt.checkString(var1, "this", true);
   }

   public final ServerScope getServerScope(@NotNull Sender var1, @NotNull List var2, @NotNull String var3, boolean var4) {
      Iterator var5 = var2.iterator();

      while (var5.hasNext()) {
         String var6 = (String)var5.next();
         if (var6.length() >= 7 && StringsKt.checkString(var6.toLowerCase(Locale.ENGLISH), "server:", false, 2, null)) {
            String var7 = var6.substring(7);
            var7 = this.getString(var7, var3, var1);
            if (var4) {
               var5.remove();
            }

            return new ServerScope(var7);
         }

         if (Intrinsics.checkObject(var6, "--")) {
            return this.getServerScope();
         }

         if (StringsKt.checkCharSequence(var6, '-', false, 2, null) && StringsKt.checkCharSequence3(var6, 'g', false, 2, null)) {
            if (var4) {
               var5.remove();
            }

            return new ServerScope(this.getString());
         }
      }

      return this.getServerScope();
   }

   public static ServerScope getServerScopeDefault(ServerScopeParser var0, Sender var1, List var2, String var3, boolean var4, int var5, Object var6) {
      if ((var5 & 8) != 0) {
         var4 = true;
      }

      return var0.getServerScope(var1, var2, var3, var4);
   }

   public final String getString(@Nullable String var1) {
      return var1 != null && !this.checkString(var1) ? this.getString2(var1) : this.getString();
   }

   public final String getString2(@NotNull String var1) {
      return ArgUtils.getString(var1, 32);
   }

   public final String getString(@NotNull String var1, @NotNull String var2, @NotNull Sender var3) {
      if (this.checkString(var1)) {
         return "global";
      } else if (this.checkString2(var1)) {
         String var4 = var3.getServerName();
         String var10000 = var4;
         if (var4 == null) {
            var10000 = var2;
         }

         return var10000;
      } else {
         return this.getString2(var1);
      }
   }

   public final boolean checkString(@NotNull String var1, @NotNull String var2) {
      return !this.checkString(var1) && !this.checkString(var2) ? StringsKt.checkString(this.getString2(var1), this.getString2(var2), true) : true;
   }

   public ServerScopeParser(DefaultConstructorMarker var1) {
      this();
   }
}
