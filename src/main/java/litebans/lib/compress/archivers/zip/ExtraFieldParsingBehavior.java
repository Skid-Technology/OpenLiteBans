package litebans.lib.compress.archivers.zip;

public interface ExtraFieldParsingBehavior extends UnparseableExtraField {
   ZipExtraField getZipExtraField(ZipShort var1);

   ZipExtraField getZipExtraField(ZipExtraField var1, byte[] var2, int var3, int var4, boolean var5);
}
