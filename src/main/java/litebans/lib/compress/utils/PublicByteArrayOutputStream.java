package litebans.lib.compress.utils;

import java.io.ByteArrayOutputStream;

public final class PublicByteArrayOutputStream extends ByteArrayOutputStream {
   public PublicByteArrayOutputStream(int var1) {
      super(var1);
   }

   @Override
   public byte[] toByteArray() {
      return this.buf;
   }
}
