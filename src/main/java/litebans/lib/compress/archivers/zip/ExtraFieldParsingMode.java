package litebans.lib.compress.archivers.zip;

import java.util.Arrays;
import java.util.zip.ZipException;

public enum ExtraFieldParsingMode implements ExtraFieldParsingBehavior {
   BEST_EFFORT(UnparseableExtraFieldImpl.INSTANCE3) {
      @Override
      public ZipExtraField getZipExtraField(ZipExtraField var1, byte[] var2, int var3, int var4, boolean var5) {
         return ExtraFieldParsingMode.getZipExtraField3(var1, var2, var3, var4, var5);
      }
   },
   STRICT_FOR_KNOW_EXTRA_FIELDS(UnparseableExtraFieldImpl.INSTANCE3),
   ONLY_PARSEABLE_LENIENT(UnparseableExtraFieldImpl.INSTANCE2) {
      @Override
      public ZipExtraField getZipExtraField(ZipExtraField var1, byte[] var2, int var3, int var4, boolean var5) {
         return ExtraFieldParsingMode.getZipExtraField3(var1, var2, var3, var4, var5);
      }
   },
   ONLY_PARSEABLE_STRICT(UnparseableExtraFieldImpl.INSTANCE2),
   DRACONIC(UnparseableExtraFieldImpl.INSTANCE);
   private final UnparseableExtraFieldImpl unparseableExtraFieldImpl;
   private static final ExtraFieldParsingMode[] extraFieldParsingModeArray = new ExtraFieldParsingMode[]{
      BEST_EFFORT, STRICT_FOR_KNOW_EXTRA_FIELDS, ONLY_PARSEABLE_LENIENT, ONLY_PARSEABLE_STRICT, DRACONIC
   };

   public static final ExtraFieldParsingMode getExtraFieldParsingMode(String var0) {
      return Enum.valueOf(ExtraFieldParsingMode.class, var0);
   }

   private ExtraFieldParsingMode(UnparseableExtraFieldImpl var3) {
      this.unparseableExtraFieldImpl = var3;
   }

   @Override
   public ZipExtraField getZipExtraField(byte[] var1, int var2, int var3, boolean var4, int var5) {
      return this.unparseableExtraFieldImpl.getZipExtraField(var1, var2, var3, var4, var5);
   }

   @Override
   public ZipExtraField getZipExtraField(ZipShort var1) {
      return ExtraFieldUtils.getZipExtraField(var1);
   }

   @Override
   public ZipExtraField getZipExtraField(ZipExtraField var1, byte[] var2, int var3, int var4, boolean var5) {
      return ExtraFieldUtils.getZipExtraField(var1, var2, var3, var4, var5);
   }

   private static final ZipExtraField getZipExtraField2(ZipExtraField var0, byte[] var1, int var2, int var3, boolean var4) {
      try {
         return ExtraFieldUtils.getZipExtraField(var0, var1, var2, var3, var4);
      } catch (Exception var7) {
         if (!(var7 instanceof ZipException)) { throw litebans.util.Unreachable.rethrow(var7); }
         UnrecognizedExtraField var6 = new UnrecognizedExtraField();
         var6.setZipShort(var0.getZipShort());
         if (var4) {
            var6.handleByteArray(Arrays.copyOfRange(var1, var2, var2 + var3));
         } else {
            var6.handleByteArray2(Arrays.copyOfRange(var1, var2, var2 + var3));
         }

         return var6;
      }
   }

   ExtraFieldParsingMode(UnparseableExtraFieldImpl var3, ExtraFieldParsingModeHelper var4) {
      this(var3);
   }

   public static final ZipExtraField getZipExtraField3(ZipExtraField var0, byte[] var1, int var2, int var3, boolean var4) {
      return getZipExtraField2(var0, var1, var2, var3, var4);
   }
}
