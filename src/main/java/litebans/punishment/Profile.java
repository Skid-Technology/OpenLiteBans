package litebans.punishment;

import java.util.UUID;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

public final class Profile {
   private final String name;
   private final UUID uuid;

   public Profile(@Nullable String var1, @Nullable UUID var2) {
      this.name = var1;
      this.uuid = var2;
   }

   public final String getName() {
      return this.name;
   }

   public final UUID getUuid() {
      return this.uuid;
   }

   public final boolean check() {
      return Intrinsics.checkObject(this, ConsoleProfile.getProfile());
   }

   @Override
   public String toString() {
      return "Profile(name=" + this.name + ", uuid=" + this.uuid + ')';
   }

   @Override
   public int hashCode() {
      int var1 = this.name == null ? 0 : this.name.hashCode();
      return var1 * 31 + (this.uuid == null ? 0 : this.uuid.hashCode());
   }

   @Override
   public boolean equals(@Nullable Object var1) {
      if (this == var1) {
         return true;
      } else if (!(var1 instanceof Profile)) {
         return false;
      } else {
         Profile var2 = (Profile)var1;
         return !Intrinsics.checkObject(this.name, var2.name) ? false : Intrinsics.checkObject(this.uuid, var2.uuid);
      }
   }
}
