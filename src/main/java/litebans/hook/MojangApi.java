package litebans.hook;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.UUID;
import javax.net.ssl.HttpsURLConnection;
import litebans.core.LiteBansCore;
import litebans.core.Placeholders;
import litebans.core.PluginInstance;
import litebans.lib.compress.archivers.tar.TargetParser;
import litebans.lib.json.JSONObject;
import litebans.lib.kotlin.io.CloseableKt;
import litebans.lib.kotlin.jvm.internal.DefaultConstructorMarker;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.text.Charsets;
import litebans.punishment.ConsoleProfile;
import litebans.punishment.Profile;
import org.jetbrains.annotations.NotNull;

public final class MojangApi {
   private MojangApi() {
   }

   public final String getString(@NotNull String var1) {
      if (!this.checkString(var1) && TargetParser.checkString7(var1) && !Intrinsics.checkObject(var1, ConsoleProfile.getString())) {
         String var2 = this.getString2(var1);

         try {
            URLConnection var10000 = new URL("https://sessionserver.mojang.com/session/minecraft/profile/" + var2).openConnection();
            Intrinsics.handleObject(var10000, "");
            HttpsURLConnection var3 = (HttpsURLConnection)var10000;
            short var18 = 10000;
            var3.setConnectTimeout(var18);
            var3.setReadTimeout(var18);
            InputStream var19 = var3.getInputStream();
            Charset var5 = Charsets.charset;
            Reader var20 = new InputStreamReader(var19, var5);
            short var22 = 512;
            Closeable var21 = var20 instanceof BufferedReader ? (BufferedReader)var20 : new BufferedReader(var20, var22);
            Throwable var23 = null;

            String var11;
            try {
               BufferedReader var6 = (BufferedReader)var21;
               JSONObject var8 = (JSONObject) UsernamePattern.getJsonParser().getObject(var6);
               Intrinsics.handleObject(var8, "");
               Object var9 = var8.get("name");
               String var10 = var9 instanceof String ? (String)var9 : null;
               if (var10 == null) {
                  return null;
               }

               UsernamePattern.mojangApi.handleJSONObject(var8);
               var11 = var10;
            } catch (Throwable var15) {
               var23 = var15;
               throw var15;
            } finally {
               CloseableKt.handleCloseable(var21, var23);
            }

            return var11;
         } catch (Exception var17) {
            LiteBansCore var4 = (LiteBansCore)PluginInstance.Companion.getLiteBansPlatform().getModule(LiteBansCore.class);
            var4.debug(1, var17);
            return null;
         }
      } else {
         return null;
      }
   }

   public final Profile getProfile(@NotNull String var1) {
      Profile var2 = this.getProfile2(var1);
      return !var2.check() ? var2 : null;
   }

   public final Profile getProfile2(@NotNull String var1) {
      LiteBansCore var2 = (LiteBansCore)PluginInstance.Companion.getLiteBansPlatform().getModule(LiteBansCore.class);
      CharSequence var3 = var1.toLowerCase(Locale.ROOT);
      if (!UsernamePattern.getRegex().checkCharSequence(var3)) {
         var2.debug(1, "Username \"" + var1 + "\" invalid");
         return ConsoleProfile.getProfile();
      } else {
         try {
            URLConnection var10000 = new URL("https://api.mojang.com/users/profiles/minecraft/" + var1).openConnection();
            Intrinsics.handleObject(var10000, "");
            HttpsURLConnection var19 = (HttpsURLConnection)var10000;
            short var4 = 10000;
            var19.setConnectTimeout(var4);
            var19.setReadTimeout(var4);
            InputStream var20 = var19.getInputStream();
            Charset var5 = Charsets.charset;
            Reader var21 = new InputStreamReader(var20, var5);
            short var23 = 512;
            Closeable var22 = var21 instanceof BufferedReader ? (BufferedReader)var21 : new BufferedReader(var21, var23);
            Throwable var24 = null;

            Profile var12;
            try {
               BufferedReader var6 = (BufferedReader)var22;
               JSONObject var8 = (JSONObject) UsernamePattern.getJsonParser().getObject(var6);
               Intrinsics.handleObject(var8, "");
               Object var9 = var8.get("name");
               String var10 = var9 instanceof String ? (String)var9 : null;
               Object var11 = var8.get("id");
               var9 = var11 instanceof String ? (String)var11 : null;
               if (var10 == null || var9 == null) {
                  return ConsoleProfile.getProfile();
               }

               UsernamePattern.mojangApi.handleJSONObject(var8);
               var12 = new Profile(var10, UUID.fromString(UsernamePattern.mojangApi.getString3((String)var9)));
            } catch (Throwable var16) {
               var24 = var16;
               throw var16;
            } finally {
               CloseableKt.handleCloseable(var22, var24);
            }

            return var12;
         } catch (Exception var18) {
            var2.debug(1, var18);
            return ConsoleProfile.getProfile();
         }
      }
   }

   public final boolean checkString(@NotNull String var1) {
      String var2 = this.getString3(var1);
      return var2.length() == 36 && var2.charAt(14) == '3';
   }

   public final String getString(@NotNull UUID var1) {
      return this.getString2(var1.toString());
   }

   public final String getString2(@NotNull String var1) {
      return var1.length() != 36 ? var1 : Placeholders.Companion.getString(var1, "-", "");
   }

   public final String getString3(@NotNull String var1) {
      if (var1.length() != 32) {
         return var1;
      } else {
         StringBuilder var2 = new StringBuilder(36);
         int var3 = 0;
         int var4 = 0;
         int var5 = 0;
         byte var6 = 8;
         int var7 = 0;

         for (int var8 = var1.length(); var7 < var8; var7++) {
            char var9 = var1.charAt(var7);
            var3++;
            var2.append(var9);
            if (++var4 >= var6) {
               var4 = 0;
               if (var3 < 32) {
                  var2.append('-');
               }

               if (++var5 == 1) {
                  var6 = 4;
               } else if (var5 >= 4) {
                  var6 = 12;
               }
            }
         }

         return var2.toString();
      }
   }

   private final void handleJSONObject(JSONObject var1) {
      Object var3 = var1.get("cause");
      String var2 = var3 instanceof String ? (String)var3 : null;
      Object var4 = var1.get("errorMessage");
      var3 = var4 instanceof String ? (String)var4 : null;
      if (var2 != null ? var2.length() != 0 : false) {
         throw new RuntimeException((String)var3);
      }
   }

   public MojangApi(DefaultConstructorMarker var1) {
      this();
   }
}
