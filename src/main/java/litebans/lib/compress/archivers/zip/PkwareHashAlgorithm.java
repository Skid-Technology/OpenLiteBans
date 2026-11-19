package litebans.lib.compress.archivers.zip;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum PkwareHashAlgorithm {
   NONE(0),
   CRC32(1),
   MD5(32771),
   SHA1(32772),
   RIPEND160(32775),
   SHA256(32780),
   SHA384(32781),
   SHA512(32782);
   private final int intValue;
   private static final Map map;
   private static final PkwareHashAlgorithm[] pkwareHashAlgorithmArray = new PkwareHashAlgorithm[]{NONE, CRC32, MD5, SHA1, RIPEND160, SHA256, SHA384, SHA512};

   public static final PkwareHashAlgorithm getPkwareHashAlgorithm(String var0) {
      return Enum.valueOf(PkwareHashAlgorithm.class, var0);
   }

   private PkwareHashAlgorithm(int var3) {
      this.intValue = var3;
   }

   public int getIntValue() {
      return this.intValue;
   }

   public static final PkwareHashAlgorithm getPkwareHashAlgorithm(int var0) {
      return (PkwareHashAlgorithm)map.get(var0);
   }

   static {
      HashMap var0 = new HashMap();

      for (PkwareHashAlgorithm var4 : values()) {
         var0.put(var4.getIntValue(), var4);
      }

      map = Collections.unmodifiableMap(var0);
   }
}
