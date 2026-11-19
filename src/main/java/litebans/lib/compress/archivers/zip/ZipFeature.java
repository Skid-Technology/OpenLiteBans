package litebans.lib.compress.archivers.zip;

import java.io.Serializable;

public class ZipFeature implements Serializable {
   private static final long longValue = 4112582948775420359L;
   public static final ZipFeature ENCRYPTION = new ZipFeature("encryption");
   public static final ZipFeature COMPRESSION_METHOD = new ZipFeature("compression method");
   public static final ZipFeature DATA_DESCRIPTOR = new ZipFeature("data descriptor");
   public static final ZipFeature SPLITTING = new ZipFeature("splitting");
   public static final ZipFeature UNKNOWN_COMPRESSED_SIZE = new ZipFeature("unknown compressed size");
   private final String string;

   private ZipFeature(String var1) {
      this.string = var1;
   }

   @Override
   public String toString() {
      return this.string;
   }
}
