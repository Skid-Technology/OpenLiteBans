package litebans.lib.compress.archivers.zip;

import java.util.zip.ZipException;

public final class UnparseableExtraFieldImpl implements UnparseableExtraField {
   public static final int intValue = 0;
   public static final int intValue2 = 1;
   public static final int intValue3 = 2;
   public static final UnparseableExtraFieldImpl INSTANCE = new UnparseableExtraFieldImpl(0);
   public static final UnparseableExtraFieldImpl INSTANCE2 = new UnparseableExtraFieldImpl(1);
   public static final UnparseableExtraFieldImpl INSTANCE3 = new UnparseableExtraFieldImpl(2);
   private final int intValue4;

   private UnparseableExtraFieldImpl(int var1) {
      this.intValue4 = var1;
   }

   @Override
   public ZipExtraField getZipExtraField(byte[] var1, int var2, int var3, boolean var4, int var5) {
      try {
      switch (this.intValue4) {
         case 0:
            throw new ZipException(
               "Bad extra field starting at " + var2 + ".  Block length of " + var5 + " bytes exceeds remaining data of " + (var3 - 4) + " bytes."
            );
         case 1:
            return null;
         case 2:
            UnparseableExtraFieldData var6 = new UnparseableExtraFieldData();
            if (var4) {
               var6.handleByteArray(var1, var2, var3);
            } else {
               var6.handleByteArray2(var1, var2, var3);
            }

            return var6;
         default:
            throw new ZipException("Unknown UnparseableExtraField key: " + this.intValue4);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }
}
