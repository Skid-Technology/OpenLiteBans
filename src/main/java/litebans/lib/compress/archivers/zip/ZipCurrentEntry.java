package litebans.lib.compress.archivers.zip;

import java.io.InputStream;
import java.util.zip.CRC32;

public final class ZipCurrentEntry {
   private final ZipArchiveEntry zipArchiveEntry = new ZipArchiveEntry();
   private boolean flag;
   private boolean flag2;
   private long longValue;
   private long longValue2;
   private final CRC32 crc32 = new CRC32();
   private InputStream inputStream;

   private ZipCurrentEntry() {
   }

   public static final ZipArchiveEntry getZipArchiveEntry(ZipCurrentEntry var0) {
      return var0.zipArchiveEntry;
   }

   public static final boolean checkZipCurrentEntry(ZipCurrentEntry var0) {
      return var0.flag;
   }

   public static final InputStream getInputStream(ZipCurrentEntry var0) {
      return var0.inputStream;
   }

   public static final CRC32 getCRC32(ZipCurrentEntry var0) {
      return var0.crc32;
   }

   public static final long getLong(ZipCurrentEntry var0) {
      return var0.longValue;
   }

   public static final long getLong2(ZipCurrentEntry var0) {
      return var0.longValue2;
   }

   public static final long getLong(ZipCurrentEntry var0, long var1) {
      return var0.longValue2 = var1;
   }

   public static final long getLong2(ZipCurrentEntry var0, long var1) {
      return var0.longValue = var1;
   }

   public static final boolean checkZipCurrentEntry2(ZipCurrentEntry var0) {
      return var0.flag2;
   }
}
