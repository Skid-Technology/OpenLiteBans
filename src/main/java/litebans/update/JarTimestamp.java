package litebans.update;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import litebans.lib.kotlin.io.CloseableKt;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.text.StringsKt;
import litebans.platform.ClassLoadDiagnostics;

public final class JarTimestamp {
   public static final JarTimestamp INSTANCE = new JarTimestamp();

   private JarTimestamp() {
   }

   public final long getLong() {
      try {
      long var13 = -1L;
      String var2 = ClassLoadDiagnostics.getString(UpdateChecker.class);
      File var3 = new File(var2);
      JarInputStream var4 = new JarInputStream(new FileInputStream(var3));
      Closeable var5 = var4;
      Throwable var6 = null;

      try {
         while (true) {
            JarEntry var9 = var4.getNextJarEntry();
            if (var9 == null) {
               break;
            }

            String var10000 = var9.getName();
            if (var10000 == null) {
               break;
            }

            String var10 = var10000;
            if (var10.length() > 6) {
               var10 = StringsKt.getString(var10, '/', '.', false, 4, null).substring(0, var10.length() - 6);
            }

            long var11 = var9.getTime();
            if (Intrinsics.checkObject(var10, UpdateChecker.class.getName())) {
               var13 = var11;
            }
         }
      } catch (Throwable var17) {
         var6 = var17;
         throw var17;
      } finally {
         JarTimeHelper.handleInt(1);
         CloseableKt.handleCloseable(var5, var6);
         JarTimeHelper.handleInt2(1);
      }

      return var13 / 1000L;
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }
}
