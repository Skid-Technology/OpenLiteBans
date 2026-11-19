package litebans.core;

import litebans.chat.JsonMessageParser;
import litebans.command.AbortException;
import litebans.command.ArgUtils;
import litebans.command.CommandRunnable;
import litebans.lib.kotlin.jvm.internal.DefaultConstructorMarker;
import litebans.platform.LiteBansPlatform;
import litebans.platform.Sender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class MessageSender {
   private MessageSender() {
   }

   public final ThreadLocal getThreadLocal() {
      return CommandRunnable.getThreadLocal();
   }

   public final Void getVoid(@Nullable Object var1) {
      throw new AbortException(var1);
   }

   public final void handleSender(@NotNull Sender var1, @NotNull CharSequence var2, @NotNull LiteBansPlatform var3) {
      if (var2.length() != 0) {
         JsonMessageParser var4 = (JsonMessageParser)var3.getModule(JsonMessageParser.class);
         short var7 = 4096;
         String var5 = ArgUtils.getString(var2.toString(), var7);
         String var10000 = var4.getString(var1, var5);
         if (var10000 != null) {
            String var6 = var10000;
            if (!var1.isPlayer() && var3.getPlatformId() == 0 && var3.isEnabled() && !var3.isDisabling()) {
               var3.runSync(() -> var1.sendMessage(var6));
            } else {
               var1.sendMessage(var6);
            }
         }
      }
   }

   public static void handleSenderDefault(MessageSender var0, Sender var1, CharSequence var2, LiteBansPlatform var3, int var4, Object var5) {
      if ((var4 & 4) != 0) {
         var3 = PluginInstance.Companion.getLiteBansPlatform();
      }

      var0.handleSender(var1, var2, var3);
   }

   private static final void handleSender(Sender var0, String var1) {
      var0.sendMessage(var1);
   }

   public MessageSender(DefaultConstructorMarker var1) {
      this();
   }
}
