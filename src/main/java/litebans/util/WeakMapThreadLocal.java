package litebans.util;

import java.util.WeakHashMap;

public final class WeakMapThreadLocal extends ThreadLocal {
   public WeakHashMap getWeakHashMap() {
      return new WeakHashMap();
   }

   @Override
   public Object initialValue() {
      return this.getWeakHashMap();
   }
}
