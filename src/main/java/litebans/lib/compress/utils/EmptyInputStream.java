package litebans.lib.compress.utils;

import java.io.InputStream;
import litebans.util.SortComparator1;

public class EmptyInputStream extends InputStream {
   private EmptyInputStream() {
   }

   @Override
   public int read() {
      return 0;
   }

   @Override
   public long skip(long var1) {
      return var1;
   }

   public EmptyInputStream(SortComparator1 var1) {
      this();
   }
}
