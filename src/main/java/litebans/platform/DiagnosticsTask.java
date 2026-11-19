package litebans.platform;

import litebans.BukkitPlugin;

public class DiagnosticsTask implements Runnable {
   public final BukkitPlugin bukkitPlugin;

   public DiagnosticsTask(BukkitPlugin var1) {
      this.bukkitPlugin = var1;
   }

   @Override
   public void run() {
      try {
         if (!this.bukkitPlugin.isLegacyServer()) {
            Thread.sleep(1250L);
         }

         ClassLoadDiagnostics var1 = new ClassLoadDiagnostics(this.bukkitPlugin);
         var1.handleString(ClassLoadDiagnostics.getString(ClassLoadDiagnostics.class), true);
      } catch (Throwable var2) {
         throw new RuntimeException(var2);
      }
   }
}
