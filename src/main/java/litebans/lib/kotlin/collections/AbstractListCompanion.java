package litebans.lib.kotlin.collections;

import java.util.Collection;
import java.util.Iterator;
import litebans.lib.kotlin.jvm.internal.DefaultConstructorMarker;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class AbstractListCompanion {
   private AbstractListCompanion() {
   }

   public final void handleInt(int var1, int var2) {
      if (var1 < 0 || var1 >= var2) {
         throw new IndexOutOfBoundsException("index: " + var1 + ", size: " + var2);
      }
   }

   public final void handleInt2(int var1, int var2) {
      if (var1 < 0 || var1 > var2) {
         throw new IndexOutOfBoundsException("index: " + var1 + ", size: " + var2);
      }
   }

   public final void handleInt(int var1, int var2, int var3) {
      if (var1 < 0 || var2 > var3) {
         throw new IndexOutOfBoundsException("fromIndex: " + var1 + ", toIndex: " + var2 + ", size: " + var3);
      } else if (var1 > var2) {
         throw new IllegalArgumentException("fromIndex: " + var1 + " > toIndex: " + var2);
      }
   }

   public final int getInt(@NotNull Collection var1) {
      Intrinsics.handleObject3(var1, "c");
      int var2 = 1;

      for (Object var4 : var1) {
         var2 = 31 * var2 + (var4 != null ? var4.hashCode() : 0);
      }

      return var2;
   }

   public final boolean checkCollection(@NotNull Collection collection, @NotNull Collection other) {
      Intrinsics.handleObject3(collection, "c");
      Intrinsics.handleObject3(other, "other");
      if (collection.size() != other.size()) {
         return false;
      } else {
         Iterator var3 = other.iterator();

         for (Object var5 : collection) {
            Object var6 = var3.next();
            if (!Intrinsics.checkObject(var5, var6)) {
               return false;
            }
         }

         return true;
      }
   }

   public AbstractListCompanion(DefaultConstructorMarker var1) {
      this();
   }
}
