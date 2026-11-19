package litebans.geoip;

import java.io.File;
import java.util.List;
import java.util.logging.Logger;
import litebans.config.Config;
import litebans.core.LiteBansCore;
import litebans.lib.kotlin.collections.CollectionsKt;
import litebans.lib.kotlin.io.FilesKt;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.text.Regex;
import litebans.lib.kotlin.text.StringsKt;
import litebans.update.ChecksumMismatchException;
import litebans.update.Updater;

public final class GeoIpDownloader implements Runnable {
   public final LiteBansCore liteBansCore;
   public final File file;
   public final Config config;

   public GeoIpDownloader(LiteBansCore var1, File var2, Config var3) {
      this.liteBansCore = var1;
      this.file = var2;
      this.config = var3;
   }

   @Override
   public final void run() {
      try {
         Logger var1 = this.liteBansCore.platform.getLogger();
         File var2 = this.file;
         Config var3 = this.config;
         LiteBansCore var4 = this.liteBansCore;
         File[] var7 = var2.listFiles();
         String var8 = "GeoLite2-Country.mmdb";
         File var11 = new File(var2, var8);
         if (var7 == null || var7.length < var3.getGeoipDownloadApi().length + 1 || !var11.exists()) {
            var1.info("Downloading GeoIP API...");
            String[] var9 = var3.getGeoipDownloadApi();
            int var10 = 0;

            for (int var12 = var9.length; var10 < var12; var10++) {
               String var13 = var9[var10];
               CharSequence var14 = var13;
               Regex var15 = new Regex(":(?!(//))");
               byte var16 = 2;
               List var17 = var15.getList(var14, var16);
               String var21 = (String)var17.get(0);
               String var22 = (String)CollectionsKt.getObject(var17, 1);
               if (Intrinsics.checkObject(var22, "d4f611abd54f725c67ade1983e5637140fac151dbbbe223ce894b7100d2a090c")) {
                  var22 = "81035e738fc392bcfb00be5d39f50fde56692ba305b21338412da9c0fc98e3fb";
               }

               String var23 = var2.getAbsolutePath() + File.separator + var21.substring(StringsKt.getInt2(var21, '/', 0, false, 6, null) + 1);
               Updater.handleStringDefault(LiteBansCore.updater, var23, var21, null, null, 12, null);
               File var18 = new File(var23);
               String var19 = LiteBansCore.updater.getString(FilesKt.getByteArray(var18));
               if (var22 != null && !Intrinsics.checkObject(var19, var22)) {
                  var18.delete();
                  throw new ChecksumMismatchException(var18, var22, var19);
               }
            }

            if (!var11.exists()) {
               var1.info("Downloading GeoIP database (" + var8 + ")...");
               if (StringsKt.checkCharSequence3(var3.getGeoipDownloadDb(), "YOUR_LICENSE_KEY", false, 2, null)
                  || StringsKt.checkCharSequence3(var3.getGeoipDownloadDb(), "/geolite.maxmind.", false, 2, null)) {
                  var1.warning("GeoIP download URL has not yet been configured.");
                  var1.warning("https://gitlab.com/ruany/LiteBans/-/wikis/GeoIP#geoip-download-issue");
                  return;
               }
            }

            Updater.handleStringDefault(LiteBansCore.updater, var11.getAbsolutePath(), var3.getGeoipDownloadDb(), var8, null, 8, null);
         }

         var4.setGeoIp(new GeoIp(var4.platform, var2, var11));
      } catch (Exception var20) {
         this.liteBansCore.logException(var20);
      }
   }
}
