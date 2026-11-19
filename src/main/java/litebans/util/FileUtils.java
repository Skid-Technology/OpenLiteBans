package litebans.util;

import java.io.File;
import java.io.IOException;
import org.jetbrains.annotations.NotNull;

public final class FileUtils {
   public static final File getFile(@NotNull File var0) {
      try {
      if (!var0.exists() && !var0.createNewFile()) {
         throw new IOException("Could not create file: " + var0.getAbsolutePath());
      } else {
         return var0;
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   public static final File getFile2(@NotNull File var0) {
      try {
      if (var0.exists() && !var0.delete()) {
         throw new IOException("Could not delete file: " + var0.getAbsolutePath());
      } else {
         return var0;
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   public static final File getFile3(@NotNull File var0) {
      try {
      if (!var0.exists() && !var0.mkdirs()) {
         throw new IOException("mkdir failed: " + var0.getAbsolutePath());
      } else if (!var0.isDirectory()) {
         throw new IOException("Not a directory: " + var0.getAbsolutePath());
      } else {
         return var0;
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }
}
