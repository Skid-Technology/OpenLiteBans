package litebans.randomid;

import java.nio.ByteBuffer;
import java.util.UUID;
import org.jetbrains.annotations.NotNull;

public final class UuidBytes {
   public static final byte[] getByteArray(@NotNull UUID var0) {
      return ByteBuffer.wrap(new byte[16]).putLong(var0.getMostSignificantBits()).putLong(var0.getLeastSignificantBits()).array();
   }

   public static final UUID getUUID(@NotNull byte[] var0) {
      if (var0.length != 16) {
         Integer var6 = var0.length;
         throw new IllegalArgumentException(var6.toString());
      } else {
         ByteBuffer var1 = ByteBuffer.wrap(var0);
         long var2 = var1.getLong();
         long var4 = var1.getLong();
         return new UUID(var2, var4);
      }
   }
}
