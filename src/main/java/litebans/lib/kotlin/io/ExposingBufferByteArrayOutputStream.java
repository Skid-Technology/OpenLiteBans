package litebans.lib.kotlin.io;

import java.io.ByteArrayOutputStream;

public final class ExposingBufferByteArrayOutputStream extends ByteArrayOutputStream {
   public ExposingBufferByteArrayOutputStream(int var1) {
      super(var1);
   }

   public final byte[] getBuf() {
      return this.buf;
   }
}
