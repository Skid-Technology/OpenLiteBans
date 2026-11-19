package litebans.lib.compress.archivers.zip;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.zip.ZipException;

public class ExtraFieldUtils {
   private static final int intValue = 4;
   private static final Map map = new ConcurrentHashMap();

   public static final void handleClass(Class var0) {
      try {
         ZipExtraField var1 = (ZipExtraField)var0.newInstance();
         map.put(var1.getZipShort(), var0);
      } catch (ClassCastException var2) {
         throw new RuntimeException(var0 + " doesn't implement ZipExtraField");
      } catch (InstantiationException var3) {
         throw new RuntimeException(var0 + " is not a concrete class");
      } catch (IllegalAccessException var4) {
         throw new RuntimeException(var0 + "'s no-arg constructor is not public");
      }
   }

   public static final ZipExtraField getZipExtraField(ZipShort var0) {
      ZipExtraField var1 = getZipExtraField2(var0);
      if (var1 != null) {
         return var1;
      } else {
         UnrecognizedExtraField var2 = new UnrecognizedExtraField();
         var2.setZipShort(var0);
         return var2;
      }
   }

   public static final ZipExtraField getZipExtraField2(ZipShort var0) {
      try {
      Class var1 = (Class)map.get(var0);
      return var1 != null ? (ZipExtraField)var1.newInstance() : null;
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   public static final ZipExtraField[] getZipExtraFieldArray(byte[] var0, boolean var1, ExtraFieldParsingBehavior var2) {
      try {
      ArrayList var3 = new ArrayList();
      int var4 = 0;

      while (var4 <= var0.length - 4) {
         ZipShort var5 = new ZipShort(var0, var4);
         int var6 = new ZipShort(var0, var4 + 2).getIntValue();
         if (var4 + 4 + var6 > var0.length) {
            ZipExtraField var10 = var2.getZipExtraField(var0, var4, var0.length - var4, var1, var6);
            if (var10 != null) {
               var3.add(var10);
            }
            break;
         }

         try {
            ZipExtraField var7 = Objects.requireNonNull(var2.getZipExtraField(var5), "createExtraField must not return null");
            var3.add(Objects.requireNonNull(var2.getZipExtraField(var7, var0, var4 + 4, var6, var1), "fill must not return null"));
            var4 += var6 + 4;
         } catch (Exception var8) {
            if (!(var8 instanceof IllegalAccessException) && !(var8 instanceof InstantiationException)) {
               throw litebans.util.Unreachable.rethrow(var8);
            }

            throw (ZipException)new ZipException(var8.getMessage()).initCause(var8);
         }
      }

      ZipExtraField[] var9 = new ZipExtraField[var3.size()];
      return /* cast-fix */(ZipExtraField[])(var3.toArray(var9));
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   public static final byte[] getByteArray(ZipExtraField[] var0) {
      boolean var1 = var0.length > 0 && var0[var0.length - 1] instanceof UnparseableExtraFieldData;
      int var2 = var1 ? var0.length - 1 : var0.length;
      int var3 = 4 * var2;

      for (ZipExtraField var7 : var0) {
         var3 += var7.getZipShort2().getIntValue();
      }

      byte[] var8 = new byte[var3];
      int var9 = 0;

      for (int var10 = 0; var10 < var2; var10++) {
         System.arraycopy(var0[var10].getZipShort().getByteArray(), 0, var8, var9, 2);
         System.arraycopy(var0[var10].getZipShort2().getByteArray(), 0, var8, var9 + 2, 2);
         var9 += 4;
         byte[] var12 = var0[var10].getByteArray();
         if (var12 != null) {
            System.arraycopy(var12, 0, var8, var9, var12.length);
            var9 += var12.length;
         }
      }

      if (var1) {
         byte[] var11 = var0[var0.length - 1].getByteArray();
         if (var11 != null) {
            System.arraycopy(var11, 0, var8, var9, var11.length);
         }
      }

      return var8;
   }

   public static final byte[] getByteArray2(ZipExtraField[] var0) {
      boolean var1 = var0.length > 0 && var0[var0.length - 1] instanceof UnparseableExtraFieldData;
      int var2 = var1 ? var0.length - 1 : var0.length;
      int var3 = 4 * var2;

      for (ZipExtraField var7 : var0) {
         var3 += var7.getZipShort3().getIntValue();
      }

      byte[] var8 = new byte[var3];
      int var9 = 0;

      for (int var10 = 0; var10 < var2; var10++) {
         System.arraycopy(var0[var10].getZipShort().getByteArray(), 0, var8, var9, 2);
         System.arraycopy(var0[var10].getZipShort3().getByteArray(), 0, var8, var9 + 2, 2);
         var9 += 4;
         byte[] var12 = var0[var10].getByteArray2();
         if (var12 != null) {
            System.arraycopy(var12, 0, var8, var9, var12.length);
            var9 += var12.length;
         }
      }

      if (var1) {
         byte[] var11 = var0[var0.length - 1].getByteArray2();
         if (var11 != null) {
            System.arraycopy(var11, 0, var8, var9, var11.length);
         }
      }

      return var8;
   }

   public static final ZipExtraField getZipExtraField(ZipExtraField var0, byte[] var1, int var2, int var3, boolean var4) {
      try {
      try {
         if (var4) {
            var0.handleByteArray(var1, var2, var3);
         } else {
            var0.handleByteArray2(var1, var2, var3);
         }

         return var0;
      } catch (ArrayIndexOutOfBoundsException var6) {
         throw (ZipException)new ZipException("Failed to parse corrupt ZIP extra field of type " + Integer.toHexString(var0.getZipShort().getIntValue()))
            .initCause(var6);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   static {
      handleClass(AsiExtraField.class);
      handleClass(X5455_ExtendedTimestamp.class);
      handleClass(X7875_NewUnix.class);
      handleClass(JarMarker.class);
      handleClass(UnicodeCommentExtraField.class);
      handleClass(UnicodePathExtraField.class);
      handleClass(Zip64ExtendedInformationExtraField.class);
      handleClass(X000A_NTFS.class);
      handleClass(X0014_X509Certificates.class);
      handleClass(X0016_CertificateIdForCentralDirectory.class);
      handleClass(X0015_CertificateIdForFile.class);
      handleClass(X0017_StrongEncryptionHeader.class);
      handleClass(X0019_EncryptionRecipientCertificateList.class);
      handleClass(ResourceAlignmentExtraField.class);
   }
}
