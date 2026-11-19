package litebans.punishment;

import litebans.lib.compress.archivers.tar.TargetParser;
import litebans.lib.kotlin.jvm.internal.DefaultConstructorMarker;
import litebans.lib.kotlin.text.StringsKt;
import litebans.platform.Sender;
import org.jetbrains.annotations.Nullable;

public final class ExecutorInfo {
   private String string;
   private String string2;
   private String string3;
   private String string4;
   private String string5;
   private final Sender sender;
   private final String string6;

   public ExecutorInfo(
      @Nullable String var1,
      @Nullable String var2,
      @Nullable String var3,
      @Nullable String var4,
      @Nullable String var5,
      @Nullable Sender var6,
      @Nullable String var7
   ) {
      this.string = var1;
      this.string2 = var2;
      this.string3 = var3;
      this.string4 = var4;
      this.string5 = var5;
      this.sender = var6;
      this.string6 = var7;
      this.string5 = this.string5 != null ? StringsKt.getString(this.string5, '*', '%', false, 4, null) : null;
   }

   public ExecutorInfo(String var1, String var2, String var3, String var4, String var5, Sender var6, String var7, int var8, DefaultConstructorMarker var9) {
      this(var1, ((var8 & 2) != 0 ? var1 : var2), ((var8 & 4) != 0 ? var1 : var3), var4, var5, ((var8 & 32) != 0 ? null : var6), ((var8 & 64) != 0 ? var1 : var7));
   }

   public final String getString2() {
      return this.string2;
   }

   public final void setString2(@Nullable String var1) {
      this.string2 = var1;
   }

   public final String getString3() {
      return this.string3;
   }

   public final void setString3(@Nullable String var1) {
      this.string3 = var1;
   }

   public final String getString4() {
      return this.string4;
   }

   public final String getString5() {
      return this.string5;
   }

   public final Sender getSender() {
      return this.sender;
   }

   public final String getString6() {
      return this.string6;
   }

   public final boolean check() {
      boolean var10000;
      if (this.string6 != null) {
         String var1 = this.string6;
         var10000 = TargetParser.INSTANCE.checkString2(var1);
      } else {
         var10000 = false;
      }

      return var10000;
   }
}
