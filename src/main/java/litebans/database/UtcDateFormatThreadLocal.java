package litebans.database;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

public final class UtcDateFormatThreadLocal extends ThreadLocal {
   public SimpleDateFormat getSimpleDateFormat() {
      SimpleDateFormat var1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      var1.setTimeZone(TimeZone.getTimeZone("UTC"));
      return var1;
   }

   @Override
   public Object initialValue() {
      return this.getSimpleDateFormat();
   }
}
