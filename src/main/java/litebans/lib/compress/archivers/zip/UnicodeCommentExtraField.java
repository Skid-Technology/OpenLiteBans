package litebans.lib.compress.archivers.zip;

public class UnicodeCommentExtraField extends AbstractUnicodeExtraField {
   public static final ZipShort zipShort = new ZipShort(28789);

   @Override
   public ZipShort getZipShort() {
      return zipShort;
   }
}
