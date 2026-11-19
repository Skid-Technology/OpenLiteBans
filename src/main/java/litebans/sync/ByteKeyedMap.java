package litebans.sync;

import java.util.Map;
import org.jetbrains.annotations.NotNull;

public final class ByteKeyedMap {
   private final byte byteValue;
   private final Map map;

   public ByteKeyedMap(byte var1, @NotNull Map var2) {
      this.byteValue = var1;
      this.map = var2;
   }

   public final byte getByteValue() {
      return this.byteValue;
   }

   public final Map getMap() {
      return this.map;
   }
}
