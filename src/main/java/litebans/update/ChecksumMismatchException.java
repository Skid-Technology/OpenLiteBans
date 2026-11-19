package litebans.update;

import java.io.File;
import org.jetbrains.annotations.NotNull;

public final class ChecksumMismatchException extends RuntimeException {
   public ChecksumMismatchException(@NotNull File var1, @NotNull String var2, @NotNull String var3) {
      super(
         "A downloaded dependency did not match the expected SHA-256 checksum!"
            + System.lineSeparator()
            + "Local checksum    : "
            + var3
            + System.lineSeparator()
            + "Expected checksum : "
            + var2
            + System.lineSeparator()
            + "File              : "
            + var1.getAbsolutePath()
      );
   }
}
