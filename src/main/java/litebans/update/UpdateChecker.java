package litebans.update;

import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import litebans.core.LiteBansCore;
import litebans.core.PluginModule;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.text.StringsKt;
import litebans.platform.LiteBansPlatform;
import org.jetbrains.annotations.NotNull;

public final class UpdateChecker extends PluginModule {
   public static final UpdateCheckerCompanion Companion = new UpdateCheckerCompanion(null);
   private ScheduledExecutorService scheduledExecutorService;
   private static String string;

   public UpdateChecker(@NotNull LiteBansPlatform var1) {
      super(var1);
   }

   @Override
   public void onDisable() {
      ScheduledExecutorService var1 = this.scheduledExecutorService;
      if (var1 != null) {
         try {
            var1.shutdown();
            var1.awaitTermination(1L, TimeUnit.SECONDS);
         } catch (Throwable var3) {
            var3.printStackTrace();
         }
      }

      this.scheduledExecutorService = null;
   }

   @Override
   public void onEnable() {
      LiteBansPlatform var1 = this.platform;
      LiteBansCore var2 = (LiteBansCore)var1.getModule(LiteBansCore.class);
      var2.onEnable();
      String var3 = string;
      if (var3 != null && var3.length() != 0) {
         ThreadPoolExecutor var10000 = var1.getExecutor();
         Intrinsics.handleObject(var10000, "");
         ThreadFactory var4 = var10000.getThreadFactory();
         this.scheduledExecutorService = Executors.newScheduledThreadPool(1, var4);
         Object[] var6 = new Object[]{var3, var1};
         ScheduledExecutorService var7 = this.scheduledExecutorService;
         Intrinsics.handleObject(this.scheduledExecutorService);
         var7.scheduleAtFixedRate(new UpdateCleanupTask(Arrays.copyOf(var6, var6.length)), 0L, 2L, TimeUnit.DAYS);
         string = "";
      }
   }

   static {
      string = null;
   }
}
