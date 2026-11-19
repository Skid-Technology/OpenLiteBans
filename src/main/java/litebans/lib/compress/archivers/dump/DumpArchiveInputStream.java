package litebans.lib.compress.archivers.dump;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import litebans.lib.compress.archivers.ArchiveException;
import litebans.lib.compress.archivers.ArchiveInputStream;
import litebans.lib.compress.archivers.zip.ZipEncoding;
import litebans.lib.compress.archivers.zip.ZipEncodingHelper;
import litebans.util.IndexedMessage;
import litebans.util.SortComparator9;

public class DumpArchiveInputStream extends ArchiveInputStream {
   @Override
   public litebans.lib.compress.archivers.ArchiveEntry getArchiveEntry() {
      return this.dumpArchiveEntry;
   }

   private DumpArchiveSummary dumpArchiveSummary;
   private DumpArchiveEntry dumpArchiveEntry;
   private boolean flag;
   private boolean flag2;
   private long longValue2;
   private long longValue3;
   private int intValue2;
   private final byte[] byteArray2 = new byte[1024];
   private byte[] byteArray3;
   private int intValue3;
   public TapeInputStream tapeInputStream;
   private final Map map = new HashMap();
   private final Map map2 = new HashMap();
   private Queue queue;
   private final ZipEncoding zipEncoding;
   public final String string;

   public DumpArchiveInputStream(InputStream var1) {
      this(var1, null);
   }

   public DumpArchiveInputStream(InputStream var1, String var2) {
      try {
      this.tapeInputStream = new TapeInputStream(var1);
      this.flag2 = false;
      this.string = var2;
      this.zipEncoding = ZipEncodingHelper.getZipEncoding(var2);

      try {
         byte[] var3 = this.tapeInputStream.getByteArray();
         if (!DumpArchiveUtil.checkByteArray(var3)) {
            throw new UnrecognizedFormatException();
         }

         this.dumpArchiveSummary = new DumpArchiveSummary(var3, this.zipEncoding);
         this.tapeInputStream.handleInt(this.dumpArchiveSummary.getIntValue5(), this.dumpArchiveSummary.check());
         this.byteArray3 = new byte[4096];
         this.run();
         this.run2();
      } catch (IOException var4) {
         throw new ArchiveException(var4.getMessage(), var4);
      }

      IndexedMessage var5 = new IndexedMessage(2, 2, 4, ".");
      this.map.put(2, var5);
      this.queue = new PriorityQueue(10, new SortComparator9(this));
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public long getLongValue() {
      return this.tapeInputStream.getLongValue();
   }

   private final void run() {
      try {
      byte[] var1 = this.tapeInputStream.getByteArray();
      if (!DumpArchiveUtil.checkByteArray(var1)) {
         throw new InvalidFormatException();
      } else {
         this.dumpArchiveEntry = DumpArchiveEntry.getDumpArchiveEntry(var1);
         if (DumpSegmentType.CLRI != this.dumpArchiveEntry.getDumpSegmentType()) {
            throw new InvalidFormatException();
         } else if (this.tapeInputStream.skip(1024L * (long)this.dumpArchiveEntry.getInt()) == -1L) {
            throw new EOFException();
         } else {
            this.intValue2 = this.dumpArchiveEntry.getInt();
         }
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   private final void run2() {
      try {
      byte[] var1 = this.tapeInputStream.getByteArray();
      if (!DumpArchiveUtil.checkByteArray(var1)) {
         throw new InvalidFormatException();
      } else {
         this.dumpArchiveEntry = DumpArchiveEntry.getDumpArchiveEntry(var1);
         if (DumpSegmentType.BITS != this.dumpArchiveEntry.getDumpSegmentType()) {
            throw new InvalidFormatException();
         } else if (this.tapeInputStream.skip(1024L * (long)this.dumpArchiveEntry.getInt()) == -1L) {
            throw new EOFException();
         } else {
            this.intValue2 = this.dumpArchiveEntry.getInt();
         }
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public int read(byte[] var1, int var2, int var3) {
      try {
      if (var3 == 0) {
         return 0;
      } else {
         int var4 = 0;
         if (!this.flag2 && !this.flag && this.longValue3 < this.longValue2) {
            if (this.dumpArchiveEntry == null) {
               throw new IllegalStateException("No current dump entry");
            } else {
               if ((long)var3 + this.longValue3 > this.longValue2) {
                  var3 = (int)(this.longValue2 - this.longValue3);
               }

               while (var3 > 0) {
                  int var5 = var3 > this.byteArray2.length - this.intValue3 ? this.byteArray2.length - this.intValue3 : var3;
                  if (this.intValue3 + var5 <= this.byteArray2.length) {
                     System.arraycopy(this.byteArray2, this.intValue3, var1, var2, var5);
                     var4 += var5;
                     this.intValue3 += var5;
                     var3 -= var5;
                     var2 += var5;
                  }

                  if (var3 > 0) {
                     if (this.intValue2 >= 512) {
                        byte[] var6 = this.tapeInputStream.getByteArray();
                        if (!DumpArchiveUtil.checkByteArray(var6)) {
                           throw new InvalidFormatException();
                        }

                        this.dumpArchiveEntry = DumpArchiveEntry.getDumpArchiveEntry(var6);
                        this.intValue2 = 0;
                     }

                     if (!this.dumpArchiveEntry.checkInt(this.intValue2++)) {
                        int var7 = this.tapeInputStream.read(this.byteArray2, 0, this.byteArray2.length);
                        if (var7 != this.byteArray2.length) {
                           throw new EOFException();
                        }
                     } else {
                        Arrays.fill(this.byteArray2, (byte)0);
                     }

                     this.intValue3 = 0;
                  }
               }

               this.longValue3 += (long)var4;
               return var4;
            }
         } else {
            return -1;
         }
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void close() {
      if (!this.flag) {
         this.flag = true;
         this.tapeInputStream.close();
      }
   }
}
