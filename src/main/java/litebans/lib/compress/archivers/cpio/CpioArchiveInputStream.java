package litebans.lib.compress.archivers.cpio;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import litebans.lib.compress.archivers.ArchiveInputStream;
import litebans.lib.compress.archivers.zip.ZipEncoding;
import litebans.lib.compress.archivers.zip.ZipEncodingHelper;
import litebans.lib.compress.utils.IOUtils;

public class CpioArchiveInputStream extends ArchiveInputStream implements CpioConstants {
   @Override
   public litebans.lib.compress.archivers.ArchiveEntry getArchiveEntry() {
      return this.cpioArchiveEntry;
   }

   private boolean flag = false;
   private CpioArchiveEntry cpioArchiveEntry;
   private long longValue2 = 0L;
   private boolean flag2 = false;
   private final byte[] byteArray2 = new byte[4096];
   private long longValue3 = 0L;
   private final InputStream inputStream;
   private final byte[] byteArray3 = new byte[2];
   private final byte[] byteArray4 = new byte[4];
   private final byte[] byteArray5 = new byte[6];
   private final int intValue24;
   private final ZipEncoding zipEncoding;
   public final String string5;

   public CpioArchiveInputStream(InputStream var1) {
      this(var1, 512, "US-ASCII");
   }

   public CpioArchiveInputStream(InputStream var1, String var2) {
      this(var1, 512, var2);
   }

   public CpioArchiveInputStream(InputStream var1, int var2, String var3) {
      this.inputStream = var1;
      if (var2 <= 0) {
         throw new IllegalArgumentException("blockSize must be bigger than 0");
      } else {
         this.intValue24 = var2;
         this.string5 = var3;
         this.zipEncoding = ZipEncodingHelper.getZipEncoding(var3);
      }
   }

   @Override
   public int available() {
      this.run();
      return this.flag2 ? 0 : 1;
   }

   @Override
   public void close() {
      try {
      if (!this.flag) {
         this.inputStream.close();
         this.flag = true;
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   private final void run() {
      try {
      if (this.flag) {
         throw new IOException("Stream closed");
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   private final void handleInt2(int var1) {
      if (var1 > 0) {
         this.getInt(this.byteArray4, 0, var1);
      }
   }

   @Override
   public int read(byte[] var1, int var2, int var3) {
      try {
      this.run();
      if (var2 < 0 || var3 < 0 || var2 > var1.length - var3) {
         throw new IndexOutOfBoundsException();
      } else if (var3 == 0) {
         return 0;
      } else if (this.cpioArchiveEntry == null || this.flag2) {
         return -1;
      } else if (this.longValue2 == this.cpioArchiveEntry.getSize()) {
         this.handleInt2(this.cpioArchiveEntry.getInt());
         this.flag2 = true;
         if (this.cpioArchiveEntry.getShortValue7() == 2 && this.longValue3 != this.cpioArchiveEntry.getLong()) {
            throw new IOException("CRC Error. Occured at byte: " + this.getLongValue());
         } else {
            return -1;
         }
      } else {
         int var4 = (int)Math.min((long)var3, this.cpioArchiveEntry.getSize() - this.longValue2);
         if (var4 < 0) {
            return -1;
         } else {
            int var5 = this.getInt(var1, var2, var4);
            if (this.cpioArchiveEntry.getShortValue7() == 2) {
               for (int var6 = 0; var6 < var5; var6++) {
                  this.longValue3 = this.longValue3 + (long)(var1[var6] & 255);
                  this.longValue3 &= 4294967295L;
               }
            }

            if (var5 > 0) {
               this.longValue2 += (long)var5;
            }

            return var5;
         }
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   private final int getInt(byte[] var1, int var2, int var3) {
      try {
      int var4 = IOUtils.getInt(this.inputStream, var1, var2, var3);
      this.handleInt(var4);
      if (var4 < var3) {
         throw new EOFException();
      } else {
         return var4;
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public long skip(long var1) {
      if (var1 < 0L) {
         throw new IllegalArgumentException("Negative skip length");
      } else {
         this.run();
         int var3 = (int)Math.min(var1, 2147483647L);
         int var4 = 0;

         while (var4 < var3) {
            int var5 = var3 - var4;
            if (var5 > this.byteArray2.length) {
               var5 = this.byteArray2.length;
            }

            var5 = this.read(this.byteArray2, 0, var5);
            if (var5 == -1) {
               this.flag2 = true;
               break;
            }

            var4 += var5;
         }

         return (long)var4;
      }
   }
}
