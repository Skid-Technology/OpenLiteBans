package litebans.lib.compress.archivers.zip;

public class UnicodePathExtraField extends AbstractUnicodeExtraField {
   public static final ZipShort zipShort = new ZipShort(25461);

   @Override
   public ZipShort getZipShort() {
      return zipShort;
   }
}
