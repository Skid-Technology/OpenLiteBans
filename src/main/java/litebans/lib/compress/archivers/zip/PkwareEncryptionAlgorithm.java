package litebans.lib.compress.archivers.zip;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum PkwareEncryptionAlgorithm {
   DES(26113),
   RC2pre52(26114),
   TripleDES168(26115),
   TripleDES192(26121),
   AES128(26126),
   AES192(26127),
   AES256(26128),
   RC2(26370),
   RC4(26625),
   UNKNOWN(65535);
   private final int intValue;
   private static final Map map;
   private static final PkwareEncryptionAlgorithm[] pkwareEncryptionAlgorithmArray = new PkwareEncryptionAlgorithm[]{
      DES, RC2pre52, TripleDES168, TripleDES192, AES128, AES192, AES256, RC2, RC4, UNKNOWN
   };

   public static final PkwareEncryptionAlgorithm getPkwareEncryptionAlgorithm(String var0) {
      return Enum.valueOf(PkwareEncryptionAlgorithm.class, var0);
   }

   private PkwareEncryptionAlgorithm(int var3) {
      this.intValue = var3;
   }

   public int getIntValue() {
      return this.intValue;
   }

   public static final PkwareEncryptionAlgorithm getPkwareEncryptionAlgorithm(int var0) {
      return (PkwareEncryptionAlgorithm)map.get(var0);
   }

   static {
      HashMap var0 = new HashMap();

      for (PkwareEncryptionAlgorithm var4 : values()) {
         var0.put(var4.getIntValue(), var4);
      }

      map = Collections.unmodifiableMap(var0);
   }
}
