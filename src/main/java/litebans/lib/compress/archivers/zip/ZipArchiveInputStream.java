package litebans.lib.compress.archivers.zip;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.math.BigInteger;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import java.util.zip.ZipException;
import litebans.lib.compress.archivers.ArchiveInputStream;
import litebans.lib.compress.utils.IOUtils;
import litebans.lib.compress.utils.InputStreamStatistics;
import litebans.randomid.RadixEncoder;

public class ZipArchiveInputStream extends ArchiveInputStream implements InputStreamStatistics {
   @Override
   public litebans.lib.compress.archivers.ArchiveEntry getArchiveEntry() {
      return this.zipCurrentEntry == null ? null : ZipCurrentEntry.getZipArchiveEntry(this.zipCurrentEntry);
   }

   private final ZipEncoding zipEncoding;
   public final String string;
   private final boolean flag;
   private final InputStream inputStream;
   private final Inflater inflater = new Inflater(true);
   private final ByteBuffer byteBuffer = ByteBuffer.allocate(512);
   private ZipCurrentEntry zipCurrentEntry = null;
   private boolean flag2 = false;
   private boolean flag3 = false;
   private ByteArrayInputStream byteArrayInputStream = null;
   private boolean flag4 = false;
   private long longValue2 = 0L;
   private final boolean flag5;
   private static final int intValue2 = 30;
   private static final int intValue3 = 46;
   private static final long longValue3 = 4294967296L;
   private final byte[] byteArray2 = new byte[30];
   private final byte[] byteArray3 = new byte[1024];
   private final byte[] byteArray4 = new byte[2];
   private final byte[] byteArray5 = new byte[4];
   private final byte[] byteArray6 = new byte[16];
   private int intValue4 = 0;
   private static final String string2 = " while reading a stored entry using data descriptor. Either the archive is broken or it can not be read using ZipArchiveInputStream and you must use ZipFile. A common cause for this is a ZIP archive containing a ZIP archive. See http://commons.apache.org/proper/commons-compress/zip.html#ZipArchiveInputStream_vs_ZipFile";
   private static final byte[] byteArray7 = ZipLong.INSTANCE2.getByteArray();
   private static final byte[] byteArray8 = ZipLong.INSTANCE.getByteArray();
   private static final byte[] byteArray9 = ZipLong.INSTANCE3.getByteArray();
   private static final byte[] byteArray10 = new byte[]{65, 80, 75, 32, 83, 105, 103, 32, 66, 108, 111, 99, 107, 32, 52, 50};
   private static final BigInteger bigInteger = BigInteger.valueOf(Long.MAX_VALUE);

   public ZipArchiveInputStream(InputStream var1) {
      this(var1, "UTF8");
   }

   public ZipArchiveInputStream(InputStream var1, String var2) {
      this(var1, var2, true);
   }

   public ZipArchiveInputStream(InputStream var1, String var2, boolean var3) {
      this(var1, var2, var3, false);
   }

   public ZipArchiveInputStream(InputStream var1, String var2, boolean var3, boolean var4) {
      this(var1, var2, var3, var4, false);
   }

   public ZipArchiveInputStream(InputStream var1, String var2, boolean var3, boolean var4, boolean var5) {
      this.string = var2;
      this.zipEncoding = ZipEncodingHelper.getZipEncoding(var2);
      this.flag = var3;
      this.inputStream = new PushbackInputStream(var1, this.byteBuffer.capacity());
      this.flag4 = var4;
      this.flag5 = var5;
      ((Buffer)this.byteBuffer).limit(0);
   }

   @Override
   public int read(byte[] var1, int var2, int var3) {
      try {
      if (var3 == 0) {
         return 0;
      } else if (this.flag2) {
         throw new IOException("The stream is closed");
      } else if (this.zipCurrentEntry == null) {
         return -1;
      } else if (var2 <= var1.length && var3 >= 0 && var2 >= 0 && var1.length - var2 >= var3) {
         RadixEncoder.handleZipArchiveEntry(ZipCurrentEntry.getZipArchiveEntry(this.zipCurrentEntry));
         if (!this.checkZipArchiveEntry(ZipCurrentEntry.getZipArchiveEntry(this.zipCurrentEntry))) {
            throw new UnsupportedZipFeatureException(ZipFeature.DATA_DESCRIPTOR, ZipCurrentEntry.getZipArchiveEntry(this.zipCurrentEntry));
         } else if (!this.checkZipArchiveEntry2(ZipCurrentEntry.getZipArchiveEntry(this.zipCurrentEntry))) {
            throw new UnsupportedZipFeatureException(ZipFeature.UNKNOWN_COMPRESSED_SIZE, ZipCurrentEntry.getZipArchiveEntry(this.zipCurrentEntry));
         } else {
            int var4;
            if (ZipCurrentEntry.getZipArchiveEntry(this.zipCurrentEntry).getMethod() == 0) {
               var4 = this.getInt(var1, var2, var3);
            } else if (ZipCurrentEntry.getZipArchiveEntry(this.zipCurrentEntry).getMethod() == 8) {
               var4 = this.getInt2(var1, var2, var3);
            } else {
               if (ZipCurrentEntry.getZipArchiveEntry(this.zipCurrentEntry).getMethod() != ZipMethod.UNSHRINKING.getIntValue2()
                  && ZipCurrentEntry.getZipArchiveEntry(this.zipCurrentEntry).getMethod() != ZipMethod.IMPLODING.getIntValue2()
                  && ZipCurrentEntry.getZipArchiveEntry(this.zipCurrentEntry).getMethod() != ZipMethod.ENHANCED_DEFLATED.getIntValue2()
                  && ZipCurrentEntry.getZipArchiveEntry(this.zipCurrentEntry).getMethod() != ZipMethod.BZIP2.getIntValue2()) {
                  throw new UnsupportedZipFeatureException(
                     ZipMethod.getZipMethod(ZipCurrentEntry.getZipArchiveEntry(this.zipCurrentEntry).getMethod()),
                     ZipCurrentEntry.getZipArchiveEntry(this.zipCurrentEntry)
                  );
               }

               var4 = ZipCurrentEntry.getInputStream(this.zipCurrentEntry).read(var1, var2, var3);
            }

            if (var4 >= 0) {
               ZipCurrentEntry.getCRC32(this.zipCurrentEntry).update(var1, var2, var4);
               this.longValue2 += (long)var4;
            }

            return var4;
         }
      } else {
         throw new ArrayIndexOutOfBoundsException();
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   private final int getInt(byte[] var1, int var2, int var3) {
      try {
      if (ZipCurrentEntry.checkZipCurrentEntry(this.zipCurrentEntry)) {
         if (this.byteArrayInputStream == null) {
            this.run2();
         }

         return this.byteArrayInputStream.read(var1, var2, var3);
      } else {
         long var4 = ZipCurrentEntry.getZipArchiveEntry(this.zipCurrentEntry).getSize();
         if (ZipCurrentEntry.getLong(this.zipCurrentEntry) >= var4) {
            return -1;
         } else {
            if (this.byteBuffer.position() >= this.byteBuffer.limit()) {
               ((Buffer)this.byteBuffer).position(0);
               int var6 = this.inputStream.read(this.byteBuffer.array());
               if (var6 == -1) {
                  ((Buffer)this.byteBuffer).limit(0);
                  throw new IOException("Truncated ZIP file");
               }

               ((Buffer)this.byteBuffer).limit(var6);
               this.handleInt(var6);
               ZipCurrentEntry var7 = this.zipCurrentEntry;
               ZipCurrentEntry.getLong(var7, ZipCurrentEntry.getLong2(var7) + (long)var6);
            }

            int var8 = Math.min(this.byteBuffer.remaining(), var3);
            if (var4 - ZipCurrentEntry.getLong(this.zipCurrentEntry) < (long)var8) {
               var8 = (int)(var4 - ZipCurrentEntry.getLong(this.zipCurrentEntry));
            }

            this.byteBuffer.get(var1, var2, var8);
            ZipCurrentEntry var9 = this.zipCurrentEntry;
            ZipCurrentEntry.getLong2(var9, ZipCurrentEntry.getLong(var9) + (long)var8);
            return var8;
         }
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   private final int getInt2(byte[] var1, int var2, int var3) {
      try {
      int var4 = this.getInt3(var1, var2, var3);
      if (var4 <= 0) {
         if (this.inflater.finished()) {
            return -1;
         }

         if (this.inflater.needsDictionary()) {
            throw new ZipException("This archive needs a preset dictionary which is not supported by Commons Compress.");
         }

         if (var4 == -1) {
            throw new IOException("Truncated ZIP file");
         }
      }

      return var4;
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   private final int getInt3(byte[] var1, int var2, int var3) {
      try {
      int var4 = 0;

      do {
         if (this.inflater.needsInput()) {
            int var5 = this.getInt();
            if (var5 <= 0) {
               if (var5 == -1) {
                  return -1;
               }
               break;
            }

            ZipCurrentEntry var6 = this.zipCurrentEntry;
            ZipCurrentEntry.getLong(var6, ZipCurrentEntry.getLong2(var6) + (long)this.byteBuffer.limit());
         }

         try {
            var4 = this.inflater.inflate(var1, var2, var3);
         } catch (DataFormatException var7) {
            throw (IOException)new ZipException(var7.getMessage()).initCause(var7);
         }
      } while (var4 == 0 && this.inflater.needsInput());

      return var4;
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void close() {
      try {
      if (!this.flag2) {
         this.flag2 = true;

         try {
            this.inputStream.close();
         } finally {
            this.inflater.end();
         }
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public long skip(long var1) {
      if (var1 >= 0L) {
         long var3 = 0L;

         while (var3 < var1) {
            long var5 = var1 - var3;
            int var7 = this.read(this.byteArray3, 0, (int)((long)this.byteArray3.length > var5 ? var5 : (long)this.byteArray3.length));
            if (var7 == -1) {
               return var3;
            }

            var3 += (long)var7;
         }

         return var3;
      } else {
         throw new IllegalArgumentException();
      }
   }

   private final int getInt() {
      try {
      if (this.flag2) {
         throw new IOException("The stream is closed");
      } else {
         int var1 = this.inputStream.read(this.byteBuffer.array());
         if (var1 > 0) {
            ((Buffer)this.byteBuffer).limit(var1);
            this.handleInt(this.byteBuffer.limit());
            this.inflater.setInput(this.byteBuffer.array(), 0, this.byteBuffer.limit());
         }

         return var1;
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   private final void handleByteArray(byte[] var1) {
      this.handleByteArray(var1, 0);
   }

   private final void handleByteArray(byte[] var1, int var2) {
      try {
      int var3 = var1.length - var2;
      int var4 = IOUtils.getInt(this.inputStream, var1, var2, var3);
      this.handleInt(var4);
      if (var4 < var3) {
         throw new EOFException();
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   private final void run() {
      this.handleByteArray(this.byteArray5);
      ZipLong var1 = new ZipLong(this.byteArray5);
      if (ZipLong.INSTANCE3.equals(var1)) {
         this.handleByteArray(this.byteArray5);
         var1 = new ZipLong(this.byteArray5);
      }

      ZipCurrentEntry.getZipArchiveEntry(this.zipCurrentEntry).setCrc(var1.getLongValue2());
      this.handleByteArray(this.byteArray6);
      ZipLong var2 = new ZipLong(this.byteArray6, 8);
      if (!var2.equals(ZipLong.INSTANCE) && !var2.equals(ZipLong.INSTANCE2)) {
         ZipCurrentEntry.getZipArchiveEntry(this.zipCurrentEntry).setCompressedSize(ZipEightByteInteger.getLong(this.byteArray6));
         ZipCurrentEntry.getZipArchiveEntry(this.zipCurrentEntry).setSize(ZipEightByteInteger.getLong(this.byteArray6, 8));
      } else {
         this.handleByteArray(this.byteArray6, 8, 8);
         ZipCurrentEntry.getZipArchiveEntry(this.zipCurrentEntry).setCompressedSize(ZipLong.getLong(this.byteArray6));
         ZipCurrentEntry.getZipArchiveEntry(this.zipCurrentEntry).setSize(ZipLong.getLong(this.byteArray6, 4));
      }
   }

   private final boolean checkZipArchiveEntry(ZipArchiveEntry var1) {
      return !var1.getGeneralPurposeBit().isFlag2()
         || this.flag4 && var1.getMethod() == 0
         || var1.getMethod() == 8
         || var1.getMethod() == ZipMethod.ENHANCED_DEFLATED.getIntValue2();
   }

   private final boolean checkZipArchiveEntry2(ZipArchiveEntry var1) {
      return var1.getCompressedSize() != -1L
         || var1.getMethod() == 8
         || var1.getMethod() == ZipMethod.ENHANCED_DEFLATED.getIntValue2()
         || var1.getGeneralPurposeBit().isFlag2() && this.flag4 && var1.getMethod() == 0;
   }

   private final void run2() {
      try {
      ByteArrayOutputStream var1 = new ByteArrayOutputStream();
      int var2 = 0;
      boolean var3 = false;
      int var4 = ZipCurrentEntry.checkZipCurrentEntry2(this.zipCurrentEntry) ? 20 : 12;

      while (!var3) {
         int var5 = this.inputStream.read(this.byteBuffer.array(), var2, 512 - var2);
         if (var5 <= 0) {
            throw new IOException("Truncated ZIP file");
         }

         if (var5 + var2 < 4) {
            var2 += var5;
         } else {
            var3 = this.checkByteArrayOutputStream(var1, var2, var5, var4);
            if (!var3) {
               var2 = this.getInt(var1, var2, var5, var4);
            }
         }
      }

      if (ZipCurrentEntry.getZipArchiveEntry(this.zipCurrentEntry).getCompressedSize() != ZipCurrentEntry.getZipArchiveEntry(this.zipCurrentEntry).getSize()) {
         throw new ZipException(
            "compressed and uncompressed size don't match while reading a stored entry using data descriptor. Either the archive is broken or it can not be read using ZipArchiveInputStream and you must use ZipFile. A common cause for this is a ZIP archive containing a ZIP archive. See http://commons.apache.org/proper/commons-compress/zip.html#ZipArchiveInputStream_vs_ZipFile"
         );
      } else {
         byte[] var6 = var1.toByteArray();
         if ((long)var6.length != ZipCurrentEntry.getZipArchiveEntry(this.zipCurrentEntry).getSize()) {
            throw new ZipException(
               "actual and claimed size don't match while reading a stored entry using data descriptor. Either the archive is broken or it can not be read using ZipArchiveInputStream and you must use ZipFile. A common cause for this is a ZIP archive containing a ZIP archive. See http://commons.apache.org/proper/commons-compress/zip.html#ZipArchiveInputStream_vs_ZipFile"
            );
         } else {
            this.byteArrayInputStream = new ByteArrayInputStream(var6);
         }
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   private final boolean checkByteArrayOutputStream(ByteArrayOutputStream var1, int var2, int var3, int var4) {
      boolean var5 = false;

      for (int var6 = 0; !var5 && var6 < var2 + var3 - 4; var6++) {
         if (this.byteBuffer.array()[var6] == byteArray7[0] && this.byteBuffer.array()[var6 + 1] == byteArray7[1]) {
            int var7 = var6;
            if ((var6 < var4 || this.byteBuffer.array()[var6 + 2] != byteArray7[2] || this.byteBuffer.array()[var6 + 3] != byteArray7[3])
               && (this.byteBuffer.array()[var6] != byteArray8[2] || this.byteBuffer.array()[var6 + 3] != byteArray8[3])) {
               if (this.byteBuffer.array()[var6 + 2] == byteArray9[2] && this.byteBuffer.array()[var6 + 3] == byteArray9[3]) {
                  var5 = true;
               }
            } else {
               var7 = var6 - var4;
               var5 = true;
            }

            if (var5) {
               this.handleByteArray(this.byteBuffer.array(), var7, var2 + var3 - var7);
               var1.write(this.byteBuffer.array(), 0, var7);
               this.run();
            }
         }
      }

      return var5;
   }

   private final int getInt(ByteArrayOutputStream var1, int var2, int var3, int var4) {
      int var5 = var2 + var3 - var4 - 3;
      if (var5 > 0) {
         var1.write(this.byteBuffer.array(), 0, var5);
         System.arraycopy(this.byteBuffer.array(), var5, this.byteBuffer.array(), 0, var4 + 3);
         var2 = var4 + 3;
      } else {
         var2 += var3;
      }

      return var2;
   }

   private final void handleByteArray(byte[] var1, int var2, int var3) {
      try {
      ((PushbackInputStream)this.inputStream).unread(var1, var2, var3);
      this.handleLong2((long)var3);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }
}
