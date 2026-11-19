package litebans.update;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.util.zip.GZIPInputStream;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import litebans.command.ArgUtils;
import litebans.command.CommandRegistrationTask;
import litebans.command.CommandRunnable;
import litebans.command.LiteBansCommand;
import litebans.command.OperationCancelledException;
import litebans.command.TextArgs;
import litebans.config.Message;
import litebans.core.LiteBansCore;
import litebans.core.MessageSender;
import litebans.core.NotificationManager;
import litebans.core.PluginInstance;
import litebans.database.DatabaseManager;
import litebans.lib.compress.archivers.ArchiveEntry;
import litebans.lib.compress.archivers.ArchiveInputStream;
import litebans.lib.compress.archivers.ArchiveStreamFactory;
import litebans.lib.compress.archivers.tar.TarArchiveEntry;
import litebans.lib.compress.archivers.tar.TarArchiveInputStream;
import litebans.lib.kotlin.collections.CollectionsKt;
import litebans.lib.kotlin.io.ByteStreamsKt;
import litebans.lib.kotlin.io.CloseableKt;
import litebans.lib.kotlin.io.FilesKt;
import litebans.lib.kotlin.jvm.internal.DefaultConstructorMarker;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.text.StringsKt;
import litebans.platform.ClassLoadDiagnostics;
import litebans.platform.LiteBansPlatform;
import litebans.platform.Sender;
import litebans.randomid.AlphabetCodec;
import litebans.randomid.LongBitSet;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class Updater {
   private Updater() {
   }

   public final int getInt() {
      return LiteBansCore.getIntValue4();
   }

   public final void handleInt(int var1) {
      LiteBansCore.handleInt(var1);
   }

   private final long getLong(String var1) {
      return (long)Double.parseDouble(var1.substring(2));
   }

   public final void handleString(@NotNull String var1, @NotNull String var2, @NotNull String var3, @NotNull Object[] var4) {
      try {
      boolean var138;
      int var139;
      TrustAllHostnameVerifier var5 = new TrustAllHostnameVerifier();
      TrustManagerFactory var6 = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
      KeyStore var7 = KeyStore.getInstance(KeyStore.getDefaultType());

      File var98 = new File(var1);
      if (!var98.exists()) {
         URLConnection var99 = new URL(var2).openConnection();
         if (!var5.verify(var2, null) || PluginInstance.Companion.getLiteBansPlatform().getModules() == LiteBansPlatform.pluginModuleArray) {
            Intrinsics.handleObject(var99, "");
            ((HttpsURLConnection)var99).setHostnameVerifier(var5);
         }

         InputStream var100 = var99.getInputStream();
         short var12 = 8192;
         Object var10 = var100 instanceof BufferedInputStream ? (BufferedInputStream)var100 : new BufferedInputStream(var100, var12);
         if (StringsKt.checkString2(var2, ".gz", false, 2, null)) {
            var10 = new GZIPInputStream((InputStream)var10);
         }

         if (StringsKt.checkString2(var2, "tar.gz", false, 2, null)) {
            ArchiveInputStream var144 = new ArchiveStreamFactory("UTF-8").getArchiveInputStream("tar", (InputStream)var10);
            Intrinsics.handleObject(var144, "");
            var10 = (TarArchiveInputStream)var144;
            this.handleTarArchiveInputStream((TarArchiveInputStream)var10, var3);
         }

         Closeable var101 = (Closeable)var10;
         Throwable var102 = null;

         try {
            try {
               FilesKt.handleFile(var98, ByteStreamsKt.getByteArray((InputStream)var10));
            } catch (Exception var87) {
               Boolean.valueOf(LiteBansCore.updater.checkFile(var98, var87));
            }
         } catch (Throwable var88) {
            var102 = var88;
            throw var88;
         } finally {
            CloseableKt.handleCloseable(var101, var102);
         }
      }
      } catch (Exception var145) {
         throw new RuntimeException(var145);
      }
   }

   public static void handleStringDefault(Updater var0, String var1, String var2, String var3, Object[] var4, int var5, Object var6) {
      if ((var5 & 4) != 0) {
         var3 = var1;
      }

      if ((var5 & 8) != 0) {
         var4 = new Object[0];
      }

      var0.handleString(var1, var2, var3, var4);
   }

   public final String getString(@NotNull byte[] var1) {
      try {
         MessageDigest var2 = MessageDigest.getInstance("SHA-256");
         if (var2 != null) {
            var2.reset();
            byte[] var3 = var2.digest(var1);
            var2.reset();
            return this.getString2(var3);
         } else {
            return "";
         }
      } catch (Exception var4) {
         throw new RuntimeException(var4);
      }
   }

   public final String getString2(@Nullable byte[] var1) {
      BigInteger var2 = new BigInteger(1, var1);
      StringBuilder var3 = new StringBuilder(var2.toString(16));

      while (var3.length() < 64) {
         var3.insert(0, '0');
      }

      return var3.toString();
   }

   private final boolean checkFile(File var1, Exception var2) throws Exception {
      try {
         var1.delete();
      } finally {
         throw var2;
      }
   }

   private final void handleTarArchiveInputStream(TarArchiveInputStream var1, String var2) throws IOException {
      boolean var3 = false;

      while (true) {
         ArchiveEntry var5 = var1.getArchiveEntry();
         TarArchiveEntry var10000 = var5 instanceof TarArchiveEntry ? (TarArchiveEntry)var5 : null;
         if ((var5 instanceof TarArchiveEntry ? (TarArchiveEntry)var5 : null) == null) {
            break;
         }

         TarArchiveEntry var4 = var10000;
         if ((Intrinsics.checkObject(var4.getName(), var2) || StringsKt.checkString2(var4.getName(), '/' + var2, false, 2, null)) && !var4.isDirectory()) {
            var3 = true;
            break;
         }
      }

      if (!var3) {
         throw new IOException("Could not find \"" + var2 + "\" in archive");
      }
   }

   public Updater(DefaultConstructorMarker var1) {
      this();
   }
}
