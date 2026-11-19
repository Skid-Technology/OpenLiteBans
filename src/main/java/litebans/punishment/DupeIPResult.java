package litebans.punishment;

import java.util.Date;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class DupeIPResult {
   private final CharSequence prefix;
   private final String name;
   private final String uuid;
   private final Date date;
   private final PunishmentEntry ban;
   private final PunishmentEntry mute;

   public DupeIPResult(
      @NotNull CharSequence var1,
      @NotNull String var2,
      @NotNull String var3,
      @NotNull Date var4,
      @Nullable PunishmentEntry var5,
      @Nullable PunishmentEntry var6
   ) {
      this.prefix = var1;
      this.name = var2;
      this.uuid = var3;
      this.date = var4;
      this.ban = var5;
      this.mute = var6;
   }

   public final CharSequence getPrefix() {
      return this.prefix;
   }

   public final String getName() {
      return this.name;
   }

   public final String getUuid() {
      return this.uuid;
   }

   public final PunishmentEntry getBan() {
      return this.ban;
   }

   @Override
   public String toString() {
      return "DupeIPResult(prefix="
         + this.prefix
         + ", name="
         + this.name
         + ", uuid="
         + this.uuid
         + ", date="
         + this.date
         + ", ban="
         + this.ban
         + ", mute="
         + this.mute
         + ')';
   }

   @Override
   public int hashCode() {
      int var1 = this.prefix.hashCode();
      var1 = var1 * 31 + this.name.hashCode();
      var1 = var1 * 31 + this.uuid.hashCode();
      var1 = var1 * 31 + this.date.hashCode();
      var1 = var1 * 31 + (this.ban == null ? 0 : this.ban.hashCode());
      return var1 * 31 + (this.mute == null ? 0 : this.mute.hashCode());
   }

   @Override
   public boolean equals(@Nullable Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof DupeIPResult)) {
         return false;
      } else {
         DupeIPResult var2 = (DupeIPResult)var1;
         if (!Intrinsics.checkObject(this.prefix, var2.prefix)) {
            return false;
         } else if (!Intrinsics.checkObject(this.name, var2.name)) {
            return false;
         } else if (!Intrinsics.checkObject(this.uuid, var2.uuid)) {
            return false;
         } else if (!Intrinsics.checkObject(this.date, var2.date)) {
            return false;
         } else {
            return !Intrinsics.checkObject(this.ban, var2.ban) ? false : Intrinsics.checkObject(this.mute, var2.mute);
         }
      }
   }
}
