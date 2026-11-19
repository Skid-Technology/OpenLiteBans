package litebans.lib.compress.archivers.zip;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CodingErrorAction;

public class NioZipEncoding implements ZipEncoding, CharsetAccessor {
   private final Charset charset;
   private final boolean flag;
   private static final char charValue = '?';
   private static final byte[] byteArray = new byte[]{63};
   private static final String string = String.valueOf('?');
   private static final char[] charArray = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

   public NioZipEncoding(Charset var1, boolean var2) {
      this.charset = var1;
      this.flag = var2;
   }

   @Override
   public String getString(byte[] var1) {
      try {
      return this.getCharsetDecoder().decode(ByteBuffer.wrap(var1)).toString();
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   private final CharsetDecoder getCharsetDecoder() {
      return !this.flag
         ? this.charset.newDecoder().onMalformedInput(CodingErrorAction.REPORT).onUnmappableCharacter(CodingErrorAction.REPORT)
         : this.charset.newDecoder().onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE).replaceWith(string);
   }
}
