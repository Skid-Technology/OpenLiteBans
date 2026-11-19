package litebans.lib.compress.archivers.zip;

public class X0016_CertificateIdForCentralDirectory extends PKWareExtraHeader {
   private int intValue2;
   private PkwareHashAlgorithm pkwareHashAlgorithm;

   public X0016_CertificateIdForCentralDirectory() {
      super(new ZipShort(21));
   }

   @Override
   public void handleByteArray2(byte[] var1, int var2, int var3) {
      this.handleInt(4, var3);
      super.handleByteArray2(var1, var2, var3);
      this.intValue2 = ZipShort.getInt(var1, var2);
      this.pkwareHashAlgorithm = PkwareHashAlgorithm.getPkwareHashAlgorithm(ZipShort.getInt(var1, var2 + 2));
   }
}
