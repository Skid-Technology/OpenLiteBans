package litebans.lib.kotlin.text;

import java.nio.charset.Charset;

public final class Charsets {
   public static final Charsets INSTANCE = new Charsets();
   public static final Charset charset = Charset.forName("UTF-8");
   public static final Charset charset2 = Charset.forName("UTF-16");
   public static final Charset charset3 = Charset.forName("UTF-16BE");
   public static final Charset charset4 = Charset.forName("UTF-16LE");
   public static final Charset charset5 = Charset.forName("US-ASCII");
   public static final Charset charset6 = Charset.forName("ISO-8859-1");

   private Charsets() {
   }
}
