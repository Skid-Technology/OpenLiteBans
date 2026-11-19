package litebans.chat;

import com.google.common.base.Preconditions;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Locale;
import java.util.Set;

public class BaseComponentSerializer {
   public final VersionedComponentSerializer versionedComponentSerializer;

   public void handleJsonObject(JsonObject var1, BaseComponent var2, JsonDeserializationContext var3) {
      var2.handleChatStyle((ChatStyle)var3.deserialize(var1, ChatStyle.class));
      JsonElement var4 = var1.get("insertion");
      if (var4 != null) {
         var2.setInsertion(var4.getAsString());
      }

      JsonObject var5;
      boolean var6 = (var5 = var1.getAsJsonObject("click_event")) != null;
      if (!var6) {
         var5 = var1.getAsJsonObject("clickEvent");
      }

      if (var5 != null) {
         ClickAction var7 = ClickAction.getClickAction(var5.get("action").getAsString().toUpperCase(Locale.ROOT));
         if (var6) {
            switch (JsonComponentWhenMappings.switchMap[var7.ordinal()]) {
               case 1:
                  var2.setClickEvent(new ClickEvent(var7, var5.get("url").getAsString()));
                  break;
               case 2:
               case 3:
                  var2.setClickEvent(new ClickEvent(var7, var5.get("command").getAsString()));
                  break;
               case 4:
                  int var8 = var5.get("page").getAsInt();
                  Preconditions.checkArgument(var8 >= 0, "Page number has to be positive");
                  var2.setClickEvent(new ClickEvent(var7, Integer.toString(var8)));
                  break;
               default:
                  var2.setClickEvent(new ClickEvent(var7, var5.has("value") ? var5.get("value").getAsString() : ""));
            }
         } else {
            var2.setClickEvent(new ClickEvent(var7, var5.has("value") ? var5.get("value").getAsString() : ""));
         }
      }

      JsonObject var13;
      boolean var14 = (var13 = var1.getAsJsonObject("hover_event")) != null;
      if (!var14) {
         var13 = var1.getAsJsonObject("hoverEvent");
      }

      if (var13 != null) {
         HoverEvent var9 = null;
         HoverAction var10 = HoverAction.getHoverAction(var13.get("action").getAsString().toUpperCase(Locale.ROOT));
         if (!var14 && !var13.has("contents")) {
            JsonElement var16 = var13.get("value");
            if (var16 != null) {
               BaseComponent[] var17;
               if (var16.isJsonArray()) {
                  var17 = (BaseComponent[])var3.deserialize(var16, BaseComponent[].class);
               } else {
                  var17 = new BaseComponent[]{(BaseComponent)var3.deserialize(var16, BaseComponent.class)};
               }

               var9 = new HoverEvent(var10, var17);
            }
         } else {
            JsonElement var11 = var13.get(var14 ? "value" : "contents");
            if (var11 != null) {
               HoverContent[] var12;
               if (var11.isJsonArray()) {
                  var12 = (HoverContent[])var3.deserialize(var11, HoverEvent.getClass(var10, true));
               } else {
                  var12 = new HoverContent[]{(HoverContent)var3.deserialize(var11, HoverEvent.getClass(var10, false))};
               }

               var9 = new HoverEvent(var10, new ArrayList<>(Arrays.asList(var12)));
            }
         }

         if (var9 != null) {
            var2.setHoverEvent(var9);
         }
      }

      JsonElement var15 = var1.get("extra");
      if (var15 != null) {
         var2.handleList(Arrays.asList((BaseComponent[])var3.deserialize(var15, BaseComponent[].class)));
      }
   }

   public void handleJsonObject(JsonObject var1, BaseComponent var2, JsonSerializationContext var3) {
      boolean var4 = false;
      if (VersionedComponentSerializer.threadLocal.get() == null) {
         var4 = true;
         VersionedComponentSerializer.threadLocal.set(Collections.newSetFromMap(new IdentityHashMap()));
      }

      try {
         Preconditions.checkArgument(!((Set)VersionedComponentSerializer.threadLocal.get()).contains(var2), "Component loop");
         ((Set)VersionedComponentSerializer.threadLocal.get()).add(var2);
         ChatStyleSerializer.handleChatStyle(var2.getStyle(), var1);
         if (var2.getInsertion() != null) {
            var1.addProperty("insertion", var2.getInsertion());
         }

         if (var2.getClickEvent() != null) {
            JsonObject var5 = new JsonObject();
            String var6 = var2.getClickEvent().getAction().toString().toLowerCase(Locale.ROOT);
            var5.addProperty("action", var6.toLowerCase(Locale.ROOT));
            switch (JsonComponentWhenMappings.switchMap2[this.versionedComponentSerializer.getChatComponentVersion().ordinal()]) {
               case 1:
                  ClickAction var7 = ClickAction.getClickAction(var6.toUpperCase(Locale.ROOT));
                  switch (JsonComponentWhenMappings.switchMap[var7.ordinal()]) {
                     case 1:
                        var5.addProperty("url", var2.getClickEvent().getValue());
                        break;
                     case 2:
                     case 3:
                        var5.addProperty("command", var2.getClickEvent().getValue());
                        break;
                     case 4:
                        var5.addProperty("page", Integer.parseInt(var2.getClickEvent().getValue()));
                        break;
                     default:
                        var5.addProperty("value", var2.getClickEvent().getValue());
                  }

                  var1.add("click_event", var5);
                  break;
               case 2:
                  var5.addProperty("value", var2.getClickEvent().getValue());
                  var1.add("clickEvent", var5);
                  break;
               default:
                  throw new IllegalArgumentException("Unknown version " + this.versionedComponentSerializer.getChatComponentVersion());
            }
         }

         if (var2.getHoverEvent() != null) {
            JsonObject var11 = new JsonObject();
            var11.addProperty("action", var2.getHoverEvent().getAction().toString().toLowerCase(Locale.ROOT));
            if (var2.getHoverEvent().isLegacy()) {
               var11.add("value", var3.serialize(var2.getHoverEvent().getContents().get(0)));
            } else {
               switch (JsonComponentWhenMappings.switchMap2[this.versionedComponentSerializer.getChatComponentVersion().ordinal()]) {
                  case 1:
                     var11.add(
                        "value",
                        var3.serialize(
                           var2.getHoverEvent().getContents().size() == 1 ? var2.getHoverEvent().getContents().get(0) : var2.getHoverEvent().getContents()
                        )
                     );
                     break;
                  case 2:
                     var11.add(
                        "contents",
                        var3.serialize(
                           var2.getHoverEvent().getContents().size() == 1 ? var2.getHoverEvent().getContents().get(0) : var2.getHoverEvent().getContents()
                        )
                     );
                     break;
                  default:
                     throw new IllegalArgumentException("Unknown version " + this.versionedComponentSerializer.getChatComponentVersion());
               }
            }

            switch (JsonComponentWhenMappings.switchMap2[this.versionedComponentSerializer.getChatComponentVersion().ordinal()]) {
               case 1:
                  var1.add("hover_event", var11);
                  break;
               case 2:
                  var1.add("hoverEvent", var11);
                  break;
               default:
                  throw new IllegalArgumentException("Unknown version " + this.versionedComponentSerializer.getChatComponentVersion());
            }
         }

         if (var2.getExtra() != null) {
            var1.add("extra", var3.serialize(var2.getExtra()));
         }
      } finally {
         ((Set)VersionedComponentSerializer.threadLocal.get()).remove(var2);
         if (var4) {
            VersionedComponentSerializer.threadLocal.set(null);
         }
      }
   }

   public BaseComponentSerializer(VersionedComponentSerializer var1) {
      this.versionedComponentSerializer = var1;
   }
}
