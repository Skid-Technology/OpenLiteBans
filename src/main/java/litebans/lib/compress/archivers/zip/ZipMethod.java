package litebans.lib.compress.archivers.zip;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public enum ZipMethod {
   STORED(0),
   UNSHRINKING(1),
   EXPANDING_LEVEL_1(2),
   EXPANDING_LEVEL_2(3),
   EXPANDING_LEVEL_3(4),
   EXPANDING_LEVEL_4(5),
   IMPLODING(6),
   TOKENIZATION(7),
   DEFLATED(8),
   ENHANCED_DEFLATED(9),
   PKWARE_IMPLODING(10),
   BZIP2(12),
   LZMA(14),
   XZ(95),
   JPEG(96),
   WAVPACK(97),
   PPMD(98),
   AES_ENCRYPTED(99),
   UNKNOWN;
   public static final int intValue = -1;
   private final int intValue2;
   private static final Map map;
   private static final ZipMethod[] zipMethodArray = new ZipMethod[]{
      STORED,
      UNSHRINKING,
      EXPANDING_LEVEL_1,
      EXPANDING_LEVEL_2,
      EXPANDING_LEVEL_3,
      EXPANDING_LEVEL_4,
      IMPLODING,
      TOKENIZATION,
      DEFLATED,
      ENHANCED_DEFLATED,
      PKWARE_IMPLODING,
      BZIP2,
      LZMA,
      XZ,
      JPEG,
      WAVPACK,
      PPMD,
      AES_ENCRYPTED,
      UNKNOWN
   };

   public static final ZipMethod getZipMethod(String var0) {
      return Enum.valueOf(ZipMethod.class, var0);
   }

   private ZipMethod() {
      this(-1);
   }

   private ZipMethod(int var3) {
      this.intValue2 = var3;
   }

   public int getIntValue2() {
      return this.intValue2;
   }

   public static final ZipMethod getZipMethod(int var0) {
      return (ZipMethod)map.get(var0);
   }

   static {
      HashMap var0 = new HashMap();

      for (ZipMethod var4 : values()) {
         var0.put(var4.getIntValue2(), var4);
      }

      map = Collections.unmodifiableMap(var0);
   }
}
