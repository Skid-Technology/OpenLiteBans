package litebans.util;

import java.text.DecimalFormat;

public class DecimalFormats {
   public static final DecimalFormat decimalFormat = new DecimalFormat("#.#");
   public static final DecimalFormat decimalFormat2 = new DecimalFormat("#.##");
   public static final DecimalFormat decimalFormat3 = new DecimalFormat("#.###");

   public static String getString(double var0) {
      return decimalFormat.format(var0);
   }

   public static String getString2(double var0) {
      return decimalFormat2.format(var0);
   }
}
