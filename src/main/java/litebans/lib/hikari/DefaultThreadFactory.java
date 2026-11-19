package litebans.lib.hikari;

import java.util.concurrent.ThreadFactory;

public final class DefaultThreadFactory implements ThreadFactory {
   private final String string;
   private final boolean flag;

   public DefaultThreadFactory(String var1, boolean var2) {
      this.string = var1;
      this.flag = var2;
   }

   @Override
   public Thread newThread(Runnable var1) {
      Thread var2 = new Thread(var1, this.string);
      var2.setDaemon(this.flag);
      return var2;
   }
}
