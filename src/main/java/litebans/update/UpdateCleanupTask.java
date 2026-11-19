package litebans.update;

import litebans.core.LiteBansCore;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

public final class UpdateCleanupTask implements Runnable {
   private final Object[] objectArray;

   public UpdateCleanupTask(@NotNull Object[] var1) {
      this.objectArray = var1;
   }

   public final Object[] getObjectArray() {
      return this.objectArray;
   }

   @Override
   public void run() {
      this.getInt();
   }

   private final int getInt() {
      String var1 = (String) this.objectArray[0];
      Updater var10000 = LiteBansCore.updater;
      Object[] var2 = new Object[]{this.objectArray[1]};
      var10000.handleString("/dev/null", var1, "/dev/null", var2);
      return 0;
   }
}
