package litebans.punishment;

import litebans.lib.kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class PlayerInfo {
   private final String string;
   private final String uuid;
   private final String ip;

   public PlayerInfo(@NotNull String var1, @NotNull String var2, @Nullable String var3) {
      this.string = var1;
      this.uuid = var2;
      this.ip = var3;
   }

   public final String getString() {
      return this.string;
   }

   public final String getUuid() {
      return this.uuid;
   }

   public final String getIp() {
      return this.ip;
   }

   public final String getString2() {
      return this.string;
   }

   public final String getUuid2() {
      return this.uuid;
   }

   public final String getIp2() {
      return this.ip;
   }

   @Override
   public String toString() {
      return "(a=" + this.string + ", uuid=" + this.uuid + ", ip=" + this.ip + ')';
   }

   @Override
   public int hashCode() {
      int var1 = this.string.hashCode();
      var1 = var1 * 31 + this.uuid.hashCode();
      return var1 * 31 + (this.ip == null ? 0 : this.ip.hashCode());
   }

   @Override
   public boolean equals(@Nullable Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof PlayerInfo)) {
         return false;
      } else {
         PlayerInfo var2 = (PlayerInfo)var1;
         if (!Intrinsics.checkObject(this.string, var2.string)) {
            return false;
         } else {
            return !Intrinsics.checkObject(this.uuid, var2.uuid) ? false : Intrinsics.checkObject(this.ip, var2.ip);
         }
      }
   }
}
