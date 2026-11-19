package litebans.lib.compress.archivers.zip;

public enum ZipNameSource {
   NAME,
   NAME_WITH_EFS_FLAG,
   UNICODE_EXTRA_FIELD;
   private static final ZipNameSource[] zipNameSourceArray = new ZipNameSource[]{NAME, NAME_WITH_EFS_FLAG, UNICODE_EXTRA_FIELD};

   public static final ZipNameSource getZipNameSource(String var0) {
      return Enum.valueOf(ZipNameSource.class, var0);
   }
}
