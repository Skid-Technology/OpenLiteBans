package litebans.util;

import litebans.core.LiteBansCore;

public final class LogTask implements Runnable {
   public final LiteBansCore liteBansCore;
   public final String string;

   public LogTask(LiteBansCore var1, String var2) {
      this.liteBansCore = var1;
      this.string = var2;
   }

   @Override
   public final void run() {
      this.liteBansCore.platform.getLogger().info(this.string);
   }
}
