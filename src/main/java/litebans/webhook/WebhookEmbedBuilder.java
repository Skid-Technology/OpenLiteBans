package litebans.webhook;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import litebans.chat.ChatColorCode;
import litebans.chat.JsonMessageParser;
import litebans.config.Message;
import litebans.database.PunishmentStorage;
import litebans.lib.kotlin.Pair;
import litebans.lib.kotlin.TuplesKt;
import litebans.lib.kotlin.collections.CollectionsKt;
import litebans.lib.kotlin.jvm.internal.DefaultConstructorMarker;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.text.StringsKt;
import litebans.punishment.PunishmentEntry;
import litebans.punishment.PunishmentRequest;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class WebhookEmbedBuilder {
   private WebhookEmbedBuilder() {
   }

   public final void handlePunishmentStorage(@NotNull PunishmentStorage var1, @NotNull PunishmentRequest var2, @NotNull String var3) {
      Object var21;
      if (var3.length() != 0) {
         DiscordWebhookSender var6 = new DiscordWebhookSender(var3);
         PunishmentEntry var10000 = var2.getPunishmentEntry();
         Intrinsics.handleObject(var10000);
         PunishmentEntry var7 = var10000;
         String var18 = Message.getString("WEB_" + var2.getPunishmentType() + "_TITLE");
         if (var18 == null) {
            var18 = "";
         }

         String var8 = var18;
         String var9 = Message.getString("WEB_" + var2.getPunishmentType() + "_CONTENT");
         int var10 = DiscordWebhookSender.webhookEmbedBuilder.getInt(var1, Message.getString("WEB_" + var2.getPunishmentType() + "_COLOR"));
         String var11 = Message.getString("WEB_" + var2.getPunishmentType() + "_THUMB");
         if ((var11 != null ? !StringsKt.checkCharSequence(var11) : true) && (var11 != null ? StringsKt.checkString(var11, "https://", false, 2, null) : true)) {
            label51: {
               if (var11 != null) {
                  CharSequence var19 = var1.getCharSequence(var11, var2.getSenderName(), var2.getSenderUuid(), false);
                  if (var19 != null) {
                     CharSequence var20 = var1.getCharSequence(var19, var7, var2.getPlayerHistory(), false);
                     if (var20 != null) {
                        var21 = var20.toString();
                        break label51;
                     }
                  }
               }

               var21 = null;
            }

            var11 = /* cast-fix */(String)(var21);
         } else {
            var11 = null;
         }

         String var22 = Message.getString("WEB_" + var2.getPunishmentType() + "_FIELDS");
         if (var22 == null) {
            var22 = "";
         }

         String var12 = var22;
         Pair[] var13 = DiscordWebhookSender.webhookEmbedBuilder.getPairArray(var1, var12, var2);
         var8 = var1.getCharSequence(var1.getCharSequence(var8, var2.getSenderName(), var2.getSenderUuid(), false), var7, var2.getPlayerHistory(), false)
            .toString();
         Intrinsics.handleObject(var9);
         var9 = var1.getCharSequence(var1.getCharSequence(var9, var2.getSenderName(), var2.getSenderUuid(), false), var7, var2.getPlayerHistory(), false)
            .toString();
         DiscordWebhookSender.webhookEmbedBuilder
            .handlePunishmentStorage(var1, var6, var8, var9, var10, var11, var13, !StringsKt.checkCharSequence3(var12, "\n\n", false, 2, null));
      }
   }

   private final Pair[] getPairArray(PunishmentStorage var1, String var2, PunishmentRequest var3) {
      if (StringsKt.checkCharSequence(var2)) {
         return null;
      } else {
         WebhookEmbedBuilder var6 = DiscordWebhookSender.webhookEmbedBuilder;
         CharSequence var7 = var1.getCharSequence(var2, var3.getPunishmentEntry(), var3.getPlayerHistory(), false);
         ArrayList var9 = new ArrayList();
         String var10 = var6.getString(var7);
         Intrinsics.handleObject(var10);
         List var11;
         if (StringsKt.checkCharSequence3(var10, "\n\n", false, 2, null)) {
            CharSequence var10000 = var10;
            String[] var12 = new String[]{"\n\n"};
            var11 = StringsKt.getList(var10000, var12, false, 0, 6, null);
         } else {
            CharSequence var24 = var10;
            char[] var20 = new char[]{'\n'};
            var11 = StringsKt.getList(var24, var20, false, 0, 6, null);
         }

         for (Object var15 : var11) {
            String var16 = (String)var15;
            if (var16.length() != 0) {
               CharSequence var25 = var16;
               char[] var18 = new char[]{':'};
               List var19 = StringsKt.getList(var25, var18, false, 0, 6, null);
               if (var19.size() <= 1) {
                  if (var9.isEmpty()) {
                     var9.add(TuplesKt.getPair(var16, var16));
                  } else {
                     Pair var23 = (Pair)CollectionsKt.getObject3(var9);
                     var9.set(CollectionsKt.getInt(var9), TuplesKt.getPair(var23.getObject(), (String)var23.getObject2() + '\n' + var16));
                  }
               } else {
                  var9.add(TuplesKt.getPair(var19.get(0), var19.get(1)));
               }
            }
         }

         Pair[] var26;
         if (!var9.isEmpty()) {
            Collection var22 = var9;
            var26 = /* cast-fix */(Pair[])(var22.toArray(new Pair[0]));
         } else {
            var26 = null;
         }

         return var26;
      }
   }

   public final int getInt(@NotNull PunishmentStorage var1, @NotNull CharSequence var2) {
      int var3 = 16777215;

      try {
         var3 = Integer.valueOf(StringsKt.getString2(var2.toString(), '#', null, 2, null), 16).intValue();
      } catch (NumberFormatException var4) {
         var1.getPlatform().getLogger().warning("Invalid color format in embed configuration, ignoring.");
      }

      return var3;
   }

   public final String getString(@NotNull CharSequence var1) {
      return ChatColorCode.getString(JsonMessageParser.formatSplitter.getString(var1.toString()));
   }

   public final void handlePunishmentStorage(
      @NotNull PunishmentStorage var1,
      @NotNull DiscordWebhookSender var2,
      @NotNull CharSequence var3,
      @NotNull CharSequence var4,
      int var5,
      @Nullable String var6,
      @Nullable Pair[] var7,
      boolean var8
   ) {
      if (var4.length() != 0 || var7 != null) {
         try {
            Webhook var9 = new Webhook(this.getString(var3), this.getString(var4), null, var5, null, var6, null, null, null, 468, null);
            if (var7 != null) {
               Pair[] var10 = var7;
               int var12 = 0;

               for (int var13 = var7.length; var12 < var13; var12++) {
                  Pair var14 = var10[var12];
                  String var17 = (String)var14.getObject3();
                  String var18 = (String)var14.getObject22();
                  var9.getWebhook(var17, var18, var8);
               }
            }

            Webhook[] var20 = new Webhook[]{var9};
            var2.setList(CollectionsKt.getArrayList(var20));
            var2.run();
         } catch (Exception var19) {
            if (!(var19 instanceof IllegalArgumentException) && !(var19 instanceof MalformedURLException)) {
               var1.getPlatform().getLogger().warning("Attempted to post Discord message but encountered an exception!\nJSON data:" + var2.getJsonObject());
               var1.getDatabaseManager().reportError(var19);
            } else {
               var1.getPlatform()
                  .getLogger()
                  .warning("Unable to post Discord message to invalid URL: '" + var2.getString() + "': " + var19.getLocalizedMessage());
            }
         }
      }
   }

   public WebhookEmbedBuilder(DefaultConstructorMarker var1) {
      this();
   }
}
