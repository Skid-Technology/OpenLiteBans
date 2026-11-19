package litebans.geoip;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Driver;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import litebans.config.Config;
import litebans.core.LiteBansCore;
import litebans.lib.kotlin.Lazy;
import litebans.lib.kotlin.LazyHelper;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.text.StringsKt;
import litebans.platform.LiteBansPlatform;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class GeoIp {
   public static final GeoIpCompanion Companion = new GeoIpCompanion(null);
   private final LiteBansPlatform liteBansPlatform;
   private Object object;
   private Method method;
   private Method method2;
   private Method method3;
   public static final String string = "geoip.unavailable";
   public static final String string2 = "geoip.error";

   public GeoIp(@NotNull LiteBansPlatform var1, @NotNull File var2, @NotNull File var3) {
      this.liteBansPlatform = var1;

      try {
         File[] var10000 = var2.listFiles(GeoIp::checkFile);
         Intrinsics.handleObject(var10000);
         File[] var4 = var10000;
         File[] var8 = var4;
         Collection var9 = new ArrayList(var4.length);
         int var11 = 0;

         for (int var12 = var4.length; var11 < var12; var11++) {
            File var13 = var8[var11];
            var9.add(var13.toURI().toURL());
         }

         Collection var6 = (List)var9;
         URL[] var5 = /* cast-fix */(URL[])(var6.toArray(new URL[0]));
         Class var23 = new URLClassLoader(var5, Driver.class.getClassLoader()).loadClass("com.maxmind.geoip2.DatabaseReader$Builder");
         Class[] var7 = new Class[]{File.class};
         Constructor var24 = var23.getConstructor(var7);
         Object[] var19 = new Object[]{var3};
         Object var18 = var24.newInstance(var19);
         this.object = var18.getClass().getMethod("build").invoke(var18);
         Class var10001 = (Class)this.object;
         Intrinsics.handleObject(this.object);
         var10001 = var10001.getClass();
         var7 = new Class[]{InetAddress.class};
         this.method = var10001.getMethod("country", var7);
         Method var25 = this.method;
         if (this.method == null) {
            Intrinsics.handleString3("");
            var25 = null;
         }

         Class var21 = var25.getReturnType();
         this.method2 = var21.getMethod("getCountry");
         Method var27 = this.method2;
         if (this.method2 == null) {
            Intrinsics.handleString3("");
            var27 = null;
         }

         this.method3 = var27.getReturnType().getMethod("getName");
         this.liteBansPlatform.getLogger().info("GeoIP support initialized.");
         String var22 = this.getString("8.8.8.8");
         if (!Intrinsics.checkObject(var22, "United States")) {
            this.liteBansPlatform.getLogger().warning("GeoIP database test failed. (" + var22 + ')');
         }
      } catch (Throwable var17) {
         var17.printStackTrace();
      }
   }

   public final String getString(@Nullable String var1) {
      if (var1 == null) {
         return "geoip.unavailable";
      } else if (this.object == null) {
         return "geoip.unavailable";
      } else {
         Object var2 = this.object;

         try {
            InetAddress var3 = InetAddress.getByName(var1);
            Method var10000 = this.method;
            if (this.method == null) {
               Intrinsics.handleString3("");
               var10000 = null;
            }

            Object[] var8 = new Object[]{var3};
            Object var4 = var10000.invoke(var2, var8);
            var10000 = this.method2;
            if (this.method2 == null) {
               Intrinsics.handleString3("");
               var10000 = null;
            }

            var8 = (Object[])var10000.invoke(var4);
            var10000 = this.method3;
            if (this.method3 == null) {
               Intrinsics.handleString3("");
               var10000 = null;
            }

            Object var6 = var10000.invoke(var8);
            String var12 = var6 instanceof String ? (String)var6 : null;
            if ((var6 instanceof String ? (String)var6 : null) == null) {
               var12 = "geoip.error";
            }

            return var12;
         } catch (Exception var7) {
            LiteBansCore var5 = (LiteBansCore)this.liteBansPlatform.getModule(LiteBansCore.class);
            LiteBansCore.handleObjectDefault(var5, var7, 0, 2, null);
            return "geoip.error";
         }
      }
   }

   public final String getString2(@Nullable String var1) {
      if (var1 == null) {
         return null;
      } else {
         LiteBansCore var2 = (LiteBansCore)this.liteBansPlatform.getModule(LiteBansCore.class);
         Config var3 = var2.getConfig();
         if (var3.isGeoipEnable()) {
            Lazy var5 = LazyHelper.getLazy(() -> getString(this, var1));
            if (var3.getGeoipBlacklist().length != 0) {
               String[] var6 = var3.getGeoipBlacklist();
               int var8 = 0;
               int var9 = var6.length;

               boolean var10000;
               while (true) {
                  if (var8 >= var9) {
                     var10000 = false;
                     break;
                  }

                  String var10 = var6[var8];
                  if (StringsKt.checkString(var10, getString(var5), true)) {
                     var10000 = true;
                     break;
                  }

                  var8++;
               }

               if (var10000) {
                  return getString(var5);
               }
            }

            if (var3.getGeoipWhitelist().length != 0) {
               String[] var12 = var3.getGeoipWhitelist();
               int var13 = 0;
               int var14 = var12.length;

               boolean var16;
               while (true) {
                  if (var13 >= var14) {
                     var16 = true;
                     break;
                  }

                  String var15 = var12[var13];
                  if (StringsKt.checkString(var15, getString(var5), true)) {
                     var16 = false;
                     break;
                  }

                  var13++;
               }

               if (var16) {
                  return getString(var5);
               }
            }
         }

         return null;
      }
   }

   private static final boolean checkFile(File var0, String var1) {
      Intrinsics.handleObject(var1);
      return StringsKt.checkString2(var1, ".jar", false, 2, null);
   }

   private static final String getString(GeoIp var0, String var1) {
      return var0.getString(var1);
   }

   private static final String getString(Lazy var0) {
      return (String)var0.getValue();
   }
}
