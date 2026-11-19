package litebans.lib.compress.archivers.zip;

import java.util.zip.ZipException;

public final class JarMarker implements ZipExtraField {
   private static final ZipShort zipShort = new ZipShort(51966);
   private static final ZipShort zipShort2 = new ZipShort(0);
   private static final byte[] byteArray = new byte[0];
   private static final JarMarker INSTANCE = new JarMarker();

   @Override
   public ZipShort getZipShort() {
      return zipShort;
   }

   @Override
   public ZipShort getZipShort2() {
      return zipShort2;
   }

   @Override
   public ZipShort getZipShort3() {
      return zipShort2;
   }

   @Override
   public byte[] getByteArray() {
      return byteArray;
   }

   @Override
   public byte[] getByteArray2() {
      return byteArray;
   }

   @Override
   public void handleByteArray(byte[] var1, int var2, int var3) {
      try {
      if (var3 != 0) {
         throw new ZipException("JarMarker doesn't expect any data");
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   @Override
   public void handleByteArray2(byte[] var1, int var2, int var3) {
      this.handleByteArray(var1, var2, var3);
   }
}
