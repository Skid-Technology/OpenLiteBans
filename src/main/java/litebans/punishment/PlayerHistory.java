package litebans.punishment;

import java.util.Date;
import litebans.lib.kotlin.jvm.internal.DefaultConstructorMarker;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class PlayerHistory {
   private final String string;
   private final String uuid;
   private final String ip;
   private final Date date;

   public PlayerHistory(@Nullable String var1, @Nullable String var2, @Nullable String var3, @NotNull Date var4) {
      this.string = var1;
      this.uuid = var2;
      this.ip = var3;
      this.date = var4;
   }

   public PlayerHistory(String var1, String var2, String var3, Date var4, int var5, DefaultConstructorMarker var6) {
      this(var1, var2, ((var5 & 4) != 0 ? "#" : var3), ((var5 & 8) != 0 ? new Date() : var4));
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

   public final Date getDate() {
      return this.date;
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

   public final Date getDate2() {
      return this.date;
   }

   @Override
   public String toString() {
      return "(a=" + this.string + ", uuid=" + this.uuid + ", ip=" + this.ip + ", date=" + this.date + ')';
   }

   @Override
   public int hashCode() {
      int var1 = this.string == null ? 0 : this.string.hashCode();
      var1 = var1 * 31 + (this.uuid == null ? 0 : this.uuid.hashCode());
      var1 = var1 * 31 + (this.ip == null ? 0 : this.ip.hashCode());
      return var1 * 31 + this.date.hashCode();
   }

   @Override
   public boolean equals(@Nullable Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof PlayerHistory)) {
         return false;
      } else {
         PlayerHistory var2 = (PlayerHistory)var1;
         if (!Intrinsics.checkObject(this.string, var2.string)) {
            return false;
         } else if (!Intrinsics.checkObject(this.uuid, var2.uuid)) {
            return false;
         } else {
            return !Intrinsics.checkObject(this.ip, var2.ip) ? false : Intrinsics.checkObject(this.date, var2.date);
         }
      }
   }
}
