package litebans.punishment;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import litebans.lib.kotlin.jvm.internal.DefaultConstructorMarker;
import litebans.lib.kotlin.text.StringsKt;
import org.jetbrains.annotations.Nullable;

public final class PunishmentTypeCompanion {
   private PunishmentTypeCompanion() {
   }

   public final List getList() {
      return PunishmentType.getList();
   }

   public final PunishmentType getPunishmentType(@Nullable String var1) {
      if (var1 == null) {
         return null;
      } else {
         String var2 = StringsKt.getString3(var1.toLowerCase(Locale.ROOT), 's', null, 2, null);
         Iterable var3 = PunishmentType.Companion.getList();
         boolean var4 = true;
         Iterator var7 = var3.iterator();

         Object var10000;
         while (true) {
            if (var7.hasNext()) {
               Object var8 = var7.next();
               if (!StringsKt.checkString(var2, String.valueOf(var8), var4)) {
                  continue;
               }

               var10000 = var8;
               break;
            }

            var10000 = null;
            break;
         }

         return (PunishmentType)var10000;
      }
   }

   public PunishmentTypeCompanion(DefaultConstructorMarker var1) {
      this();
   }
}
