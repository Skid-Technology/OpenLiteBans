package litebans.webhook;

import org.jetbrains.annotations.Nullable;

public final class WebhookField {
   private final String string;
   private final String string2;
   private final boolean flag;
   public final Webhook webhook;

   public WebhookField(@Nullable Webhook var1, @Nullable String var2, String var3, boolean var4) {
      this.webhook = var1;
      this.string = var2;
      this.string2 = var3;
      this.flag = var4;
   }

   public final String getString() {
      return this.string;
   }

   public final String getString2() {
      return this.string2;
   }

   public final boolean isFlag() {
      return this.flag;
   }
}
