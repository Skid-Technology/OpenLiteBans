package litebans.lib.compress.archivers.zip;

public enum ZipCommentSource {
   COMMENT,
   UNICODE_EXTRA_FIELD;
   private static final ZipCommentSource[] zipCommentSourceArray = new ZipCommentSource[]{COMMENT, UNICODE_EXTRA_FIELD};

   public static final ZipCommentSource getZipCommentSource(String var0) {
      return Enum.valueOf(ZipCommentSource.class, var0);
   }
}
