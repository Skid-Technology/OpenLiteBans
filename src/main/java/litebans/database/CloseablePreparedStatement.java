package litebans.database;

import java.io.Closeable;
import java.sql.PreparedStatement;
import org.jetbrains.annotations.NotNull;

public final class CloseablePreparedStatement extends DelegatingPreparedStatement {
   private final Closeable closeable;

   public CloseablePreparedStatement(@NotNull PreparedStatement var1, @NotNull Closeable var2) {
      super(var1);
      this.closeable = var2;
   }

   @Override
   public void close() {
      try {
         super.close();
      } finally {
         Closeable var2 = this.closeable;

         try {
            var2.close();
         } catch (Exception var7) {
            throw new RuntimeException(var7);
         }
      }
   }
}
