package litebans.webhook;

import java.util.ArrayList;
import java.util.List;
import litebans.lib.kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class Webhook {
   private String string;
   private String string2;
   private String string3;
   private int intValue;
   private WebhookFooter webhookFooter;
   private String string4;
   private WebhookThumbnail webhookThumbnail;
   private WebhookAuthor webhookAuthor;
   private final List list;

   public Webhook(
      @Nullable String var1,
      @Nullable String var2,
      @Nullable String var3,
      int var4,
      @Nullable WebhookFooter var5,
      @Nullable String var6,
      @Nullable WebhookThumbnail var7,
      @Nullable WebhookAuthor var8,
      @NotNull List var9
   ) {
      this.string = var1;
      this.string2 = var2;
      this.string3 = var3;
      this.intValue = var4;
      this.webhookFooter = var5;
      this.string4 = var6;
      this.webhookThumbnail = var7;
      this.webhookAuthor = var8;
      this.list = var9;
   }

   public Webhook(
      String var1,
      String var2,
      String var3,
      int var4,
      WebhookFooter var5,
      String var6,
      WebhookThumbnail var7,
      WebhookAuthor var8,
      List var9,
      int var10,
      DefaultConstructorMarker var11
   ) {
      this(((var10 & 1) != 0 ? null : var1), ((var10 & 2) != 0 ? null : var2), ((var10 & 4) != 0 ? null : var3), ((var10 & 8) != 0 ? 16777215 : var4), ((var10 & 16) != 0 ? null : var5), ((var10 & 32) != 0 ? null : var6), ((var10 & 64) != 0 ? null : var7), ((var10 & 128) != 0 ? null : var8), ((var10 & 256) != 0 ? new ArrayList() : var9));
   }

   public final String getString() {
      return this.string;
   }

   public final String getString2() {
      return this.string2;
   }

   public final String getString3() {
      return this.string3;
   }

   public final int getIntValue() {
      return this.intValue;
   }

   public final WebhookFooter getWebhookFooter() {
      return this.webhookFooter;
   }

   public final String getString4() {
      return this.string4;
   }

   public final WebhookThumbnail getWebhookThumbnail() {
      return this.webhookThumbnail;
   }

   public final WebhookAuthor getWebhookAuthor() {
      return this.webhookAuthor;
   }

   public final List getList() {
      return this.list;
   }

   public final Webhook getWebhook(@Nullable String var1, @Nullable String var2, boolean var3) {
      this.list.add(new WebhookField(this, var1, var2, var3));
      return this;
   }

   public Webhook() {
      this(null, null, null, 0, null, null, null, null, null, 511, null);
   }
}
