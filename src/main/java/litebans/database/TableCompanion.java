package litebans.database;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import litebans.lib.kotlin.jvm.internal.DefaultConstructorMarker;

public final class TableCompanion {
   private TableCompanion() {
   }

   public final List getList() {
      Table[] var1 = Table.values();
      Table[] var3 = var1;
      Collection var4 = new ArrayList();
      int var6 = 0;

      for (int var7 = var1.length; var6 < var7; var6++) {
         Table var8 = var3[var6];
         if (var8.check()) {
            var4.add(var8);
         }
      }

      return (List)var4;
   }

   public TableCompanion(DefaultConstructorMarker var1) {
      this();
   }
}
