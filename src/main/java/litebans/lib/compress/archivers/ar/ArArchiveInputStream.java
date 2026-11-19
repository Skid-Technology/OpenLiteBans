package litebans.lib.compress.archivers.ar;

import java.io.InputStream;
import litebans.lib.compress.archivers.ArchiveInputStream;

public class ArArchiveInputStream extends ArchiveInputStream {
   @Override
   public litebans.lib.compress.archivers.ArchiveEntry getArchiveEntry() {
      return this.arArchiveEntry;
   }

   private final InputStream inputStream;
   private long longValue2 = 0L;
   private boolean flag;
   private ArArchiveEntry arArchiveEntry = null;
   private byte[] byteArray2 = null;
   private long longValue3 = -1L;
   private static final int intValue2 = 0;
   private static final int intValue3 = 16;
   private static final int intValue4 = 16;
   private static final int intValue5 = 12;
   private static final int intValue6 = 28;
   private static final int intValue7 = 6;
   private static final int intValue8 = 34;
   private static final int intValue9 = 6;
   private static final int intValue10 = 40;
   private static final int intValue11 = 8;
   private static final int intValue12 = 48;
   private static final int intValue13 = 10;
   private final byte[] byteArray3 = new byte[58];
   public static final String string = "#1/";
   private static final int intValue14 = "#1/".length();
   private static final String string2 = "^#1/\\d+";
   private static final String string3 = "//";
   private static final String string4 = "^/\\d+";

   public ArArchiveInputStream(InputStream var1) {
      this.inputStream = var1;
      this.flag = false;
   }

   @Override
   public void close() {
      try {
      if (!this.flag) {
         this.flag = true;
         this.inputStream.close();
      }

      this.arArchiveEntry = null;
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int read(byte[] var1, int var2, int var3) {
      try {
      if (var3 == 0) {
         return 0;
      } else if (this.arArchiveEntry == null) {
         throw new IllegalStateException("No current ar entry");
      } else {
         long var4 = this.longValue3 + this.arArchiveEntry.getLongValue2();
         if (var3 >= 0 && this.longValue2 < var4) {
            int var6 = (int)Math.min((long)var3, var4 - this.longValue2);
            int var7 = this.inputStream.read(var1, var2, var6);
            this.handleLong3((long)var7);
            return var7;
         } else {
            return -1;
         }
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   private final void handleLong3(long var1) {
      this.handleLong(var1);
      if (var1 > 0L) {
         this.longValue2 += var1;
      }
   }
}
