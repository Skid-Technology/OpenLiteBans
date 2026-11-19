package litebans.platform.fabric;

import litebans.lib.kotlin.jvm.internal.DefaultConstructorMarker;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.punishment.PlayerInfo;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class FabricPreLoginEvent {
   private final PlayerInfo data;
   private final Object login;
   private String cancelReason;

   public FabricPreLoginEvent(@NotNull PlayerInfo var1, @NotNull Object var2, @Nullable String var3) {
      this.data = var1;
      this.login = var2;
      this.cancelReason = var3;
   }

   public FabricPreLoginEvent(PlayerInfo var1, Object var2, String var3, int var4, DefaultConstructorMarker var5) {
      this(var1, var2, ((var4 & 4) != 0 ? null : var3));
   }

   public final PlayerInfo getData() {
      return this.data;
   }

   public final String getCancelReason() {
      return this.cancelReason;
   }

   public final void setCancelReason(@Nullable String var1) {
      this.cancelReason = var1;
   }

   @Override
   public String toString() {
      return "FabricPreLoginEvent(data=" + this.data + ", login=" + this.login + ", cancelReason=" + this.cancelReason + ')';
   }

   @Override
   public int hashCode() {
      int var1 = this.data.hashCode();
      var1 = var1 * 31 + this.login.hashCode();
      return var1 * 31 + (this.cancelReason == null ? 0 : this.cancelReason.hashCode());
   }

   @Override
   public boolean equals(@Nullable Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof FabricPreLoginEvent)) {
         return false;
      } else {
         FabricPreLoginEvent var2 = (FabricPreLoginEvent)var1;
         if (!Intrinsics.checkObject(this.data, var2.data)) {
            return false;
         } else {
            return !Intrinsics.checkObject(this.login, var2.login) ? false : Intrinsics.checkObject(this.cancelReason, var2.cancelReason);
         }
      }
   }
}
