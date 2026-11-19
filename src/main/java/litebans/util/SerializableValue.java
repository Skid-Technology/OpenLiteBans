package litebans.util;

import java.io.Serializable;

public final class SerializableValue implements Serializable {
   public boolean flag;

   @Override
   public String toString() {
      return String.valueOf(this.flag);
   }
}
