package litebans.lib.compress.archivers.cpio;

import litebans.lib.compress.archivers.ArchiveEntry;

public class CpioArchiveEntry implements CpioConstants, ArchiveEntry {
   @Override
   public boolean isDirectory() {
      return false;
   }

   @Override
   public String getName() {
      return this.string5;
   }

   private short shortValue7;
   private int intValue24;
   private long longValue2;
   private long longValue3;
   private String string5;

   private final void run() {
      if ((this.shortValue7 & 3) == 0) {
         throw new UnsupportedOperationException();
      }
   }

   public long getLong() {
      this.run();
      return this.longValue2 & 4294967295L;
   }

   @Override
   public long getSize() {
      return this.longValue3;
   }

   public short getShortValue7() {
      return this.shortValue7;
   }

   public int getInt() {
      if (this.intValue24 == 0) {
         return 0;
      } else {
         long var1 = this.longValue3;
         int var3 = (int)(var1 % (long)this.intValue24);
         return var3 > 0 ? this.intValue24 - var3 : 0;
      }
   }

   @Override
   public int hashCode() {
      byte var2 = 1;
      return 31 * var2 + (this.string5 == null ? 0 : this.string5.hashCode());
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 != null && this.getClass() == var1.getClass()) {
         CpioArchiveEntry var2 = (CpioArchiveEntry)var1;
         return this.string5 == null ? var2.string5 == null : this.string5.equals(var2.string5);
      } else {
         return false;
      }
   }
}
