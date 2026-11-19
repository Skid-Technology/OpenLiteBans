package litebans.lib.compress.archivers.zip;

import java.util.zip.ZipException;

public class UnsupportedZipFeatureException extends ZipException {
   private final ZipFeature zipFeature;
   private final transient ZipArchiveEntry zipArchiveEntry;
   private static final long longValue = 20161219L;

   public UnsupportedZipFeatureException(ZipFeature var1, ZipArchiveEntry var2) {
      super("Unsupported feature " + var1 + " used in entry " + var2.getName());
      this.zipFeature = var1;
      this.zipArchiveEntry = var2;
   }

   public UnsupportedZipFeatureException(ZipMethod var1, ZipArchiveEntry var2) {
      super("Unsupported compression method " + var2.getMethod() + " (" + var1.name() + ") used in entry " + var2.getName());
      this.zipFeature = ZipFeature.COMPRESSION_METHOD;
      this.zipArchiveEntry = var2;
   }
}
