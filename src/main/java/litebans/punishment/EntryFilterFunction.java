package litebans.punishment;

import java.util.Map.Entry;
import litebans.lib.kotlin.jvm.internal.Function1;
import litebans.lib.kotlin.jvm.internal.Intrinsics;

public final class EntryFilterFunction implements Function1 {
   public final String string;
   public final String string2;

   public EntryFilterFunction(String var1, String var2) {
      this.string = var1;
      this.string2 = var2;
   }

   public final Boolean getBoolean(Entry var1) {
      return this.string != null && Intrinsics.checkObject(this.string, ((PunishmentEntry)var1.getValue()).getUuid())
         || this.string2 != null && this.string2.length() > 0 && Intrinsics.checkObject(this.string2, ((PunishmentEntry)var1.getValue()).getIp());
   }

   @Override
   public Object invoke(Object var1) {
      return this.getBoolean((Entry)var1);
   }
}
