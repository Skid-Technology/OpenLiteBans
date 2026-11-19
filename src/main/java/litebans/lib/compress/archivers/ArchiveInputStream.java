package litebans.lib.compress.archivers;

import java.io.InputStream;

public abstract class ArchiveInputStream extends InputStream {
   private final byte[] byteArray = new byte[1];
   private static final int intValue = 255;
   private long longValue = 0L;

   public abstract ArchiveEntry getArchiveEntry() throws java.io.IOException;

   @Override
   public int read() {
      try {
         int var1 = this.read(this.byteArray, 0, 1);
         return var1 == -1 ? -1 : this.byteArray[0] & 0xFF;
      } catch (java.io.IOException var2) {
         throw new RuntimeException(var2);
      }
   }

   public void handleInt(int var1) {
      this.handleLong((long)var1);
   }

   public void handleLong(long var1) {
      if (var1 != -1L) {
         this.longValue += var1;
      }
   }

   public void handleLong2(long var1) {
      this.longValue -= var1;
   }

   public long getLongValue() {
      return this.longValue;
   }
}
