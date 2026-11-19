package litebans.update;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import org.jetbrains.annotations.Nullable;

public final class TrustAllHostnameVerifier implements HostnameVerifier {
   @Override
   public boolean verify(@Nullable String var1, @Nullable SSLSession var2) {
      return true;
   }
}
