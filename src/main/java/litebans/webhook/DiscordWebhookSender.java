package litebans.webhook;

import java.io.Closeable;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import litebans.lib.json.JSONArray;
import litebans.lib.json.JSONObject;
import litebans.lib.json.JSONParser;
import litebans.lib.kotlin.collections.CollectionsKt;
import litebans.lib.kotlin.io.CloseableKt;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.text.Charsets;
import litebans.lib.kotlin.text.StringsKt;
import litebans.update.BuildInfo;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class DiscordWebhookSender {
   public static final WebhookEmbedBuilder webhookEmbedBuilder = new WebhookEmbedBuilder(null);
   private final String string;
   private String string2;
   private String string3;
   private String string4;
   private boolean flag;
   private List list;
   private final JSONObject jsonObject;
   private static final Character[] characterArray;

   public DiscordWebhookSender(@NotNull String var1) {
      this.string = var1;
      this.list = CollectionsKt.getList();
      this.jsonObject = new JSONObject();
   }

   public final String getString() {
      return this.string;
   }

   public final void setList(@NotNull List var1) {
      this.list = var1;
   }

   public final JSONObject getJsonObject() {
      return this.jsonObject;
   }

   public final String getString(@Nullable String var1, boolean var2) {
      if (var1 == null) {
         return null;
      } else {
         String var3 = var1;
         if (!var2) {
            Character[] var4 = characterArray;
            int var5 = 0;

            for (int var6 = var4.length; var5 < var6; var5++) {
               char var7 = var4[var5];
               if (StringsKt.checkCharSequence3(var3, var7, false, 2, null)) {
                  var3 = StringsKt.getString(var3, String.valueOf(var7), "" + '\\' + var7, false, 4, null);
               }
            }
         }

         return var3;
      }
   }

   // $VF: Could not verify finally blocks. A semaphore variable has been added to preserve control flow.
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   public final void run() {
      try {
      this.jsonObject.put("content", this.getString(this.string2, false));
      this.jsonObject.put("username", this.string3);
      this.jsonObject.put("avatar_url", this.string4);
      this.jsonObject.put("tts", this.flag);
      Map var1 = this.jsonObject;
      String var2 = "embeds";
      Iterable var3 = this.list;
      Collection var6 = new ArrayList(CollectionsKt.getInt(var3, 10));

      for (Object var9 : var3) {
         Webhook var10 = (Webhook)var9;
         var6.add(this.getJSONObject(var10));
      }

      List var35 = (List)var6;

      Object var10000;
      try {
         var10000 = new JSONParser().getObject(JSONArray.getString(var35));
      } catch (Throwable var33) {
         System.err.println(JSONArray.getString(var35));
         throw var33;
      }

      var3 = (Iterable)var10000;
      var1.put(var2, var3);
      JSONObject var34 = this.jsonObject;
      var10000 = URI.create(this.string).toURL().openConnection();
      Intrinsics.handleObject(var10000, "");
      HttpsURLConnection var37 = (HttpsURLConnection)var10000;
      var37.addRequestProperty("Content-Type", "application/json");
      var37.addRequestProperty("User-Agent", "Java (LiteBans " + BuildInfo.getString2() + ')');
      var37.setConnectTimeout(80000);
      var37.setReadTimeout(80000);
      var37.setDoOutput(true);
      var37.setRequestMethod("POST");
      HttpURLConnection var4 = var37;
      boolean var23 = false /* VF: Semaphore variable */;

      try {
         var23 = true;
         Closeable var38 = var4.getOutputStream();
         Throwable var7 = null;

         try {
            OutputStream var39 = (OutputStream)var38;
            Intrinsics.handleObject(var39);
            var39.write(var34.toString().getBytes(Charsets.charset));
         } catch (Throwable var30) {
            var7 = var30;
            throw var30;
         } finally {
            CloseableKt.handleCloseable(var38, var7);
         }

         var23 = false;
      } finally {
         if (var23) {
            try {
               var4.getInputStream().close();
               var4.disconnect();
            } catch (Throwable var29) {
               throw new RuntimeException(var29);
            }
         }
      }

      var4.getInputStream().close();
      var4.disconnect();
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   private final JSONObject getJSONObject(Webhook var1) {
      JSONObject var2 = new JSONObject();
      var2.put("title", this.getString(var1.getString(), true));
      var2.put("description", this.getString(var1.getString2(), true));
      Map var5 = var2;
      String var6 = "url";
      String var10000 = var1.getString3();
      if (var10000 == null) {
         var10000 = "";
      }

      String var7 = var10000;
      var5.put(var6, var7);
      WebhookFooter var26 = var1.getWebhookFooter();
      WebhookThumbnail var27 = var1.getWebhookThumbnail();
      var7 = var1.getString4();
      WebhookAuthor var8 = var1.getWebhookAuthor();
      List var9 = var1.getList();
      int var10 = var1.getIntValue();
      if (var26 != null) {
         JSONObject var11 = new JSONObject();
         var11.put("text", var26.getString());
         var11.put("icon_url", var26.getString2());
         var2.put("footer", var11);
      }

      if (var27 != null) {
         JSONObject var29 = new JSONObject();
         var29.put("url", var27.getString());
         var2.put("image", var29);
      }

      if (var8 != null) {
         JSONObject var30 = new JSONObject();
         var30.put("name", var8.getString());
         var30.put("url", var8.getString2());
         var30.put("icon_url", var8.getString3());
         var2.put("author", var30);
      }

      if (var7 != null) {
         JSONObject var31 = new JSONObject();
         var31.put("url", var7);
         var2.put("thumbnail", var31);
      }

      Integer var12 = var10;
      var2.put("color", var12);
      Map var32 = var2;
      String var13 = "fields";
      Iterable var33 = var9;
      Collection var16 = new ArrayList(CollectionsKt.getInt(var33, 10));

      for (Object var19 : var33) {
         WebhookField var20 = (WebhookField)var19;
         JSONObject var23 = new JSONObject();
         var23.put("name", var20.getString());
         var23.put("value", var20.getString2());
         var23.put("inline", var20.isFlag());
         var16.add(var23);
      }

      List var34 = (List)var16;

      Object var50;
      try {
         var50 = new JSONParser().getObject(JSONArray.getString(var34));
      } catch (Throwable var25) {
         System.err.println(JSONArray.getString(var34));
         throw var25;
      }

      var32.put(var13, var50);
      return var2;
   }

   static {
      Character[] var0 = new Character[]{'_', '*', '`', '~', '#', '-'};
      characterArray = var0;
   }
}
