package litebans.chat;

import litebans.lib.json.JSONObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class JsonMessageTemplate {
   private final String string;
   private final JSONObject jsonObject;
   private final JsonMessagePart[] jsonMessagePartArray;

   public JsonMessageTemplate(@NotNull String var1, @Nullable JSONObject var2, @NotNull JsonMessagePart[] var3) {
      this.string = var1;
      this.jsonObject = var2;
      this.jsonMessagePartArray = var3;
   }

   public final String getString() {
      return this.string;
   }

   public final JSONObject getJsonObject() {
      return this.jsonObject;
   }

   public final JsonMessagePart[] getJsonMessagePartArray() {
      return this.jsonMessagePartArray;
   }
}
