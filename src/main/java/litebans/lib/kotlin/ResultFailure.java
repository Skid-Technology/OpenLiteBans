package litebans.lib.kotlin;

import java.io.Serializable;

public final class ResultFailure implements Serializable {
   public Object object;

   @Override
   public String toString() {
      return String.valueOf(this.object);
   }
}
