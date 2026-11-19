package litebans.lib.compress.archivers.zip;

public interface ZipExtraField {
   int intValue = 4;

   ZipShort getZipShort();

   ZipShort getZipShort2();

   ZipShort getZipShort3();

   byte[] getByteArray();

   byte[] getByteArray2();

   void handleByteArray(byte[] var1, int var2, int var3);

   void handleByteArray2(byte[] var1, int var2, int var3);
}
