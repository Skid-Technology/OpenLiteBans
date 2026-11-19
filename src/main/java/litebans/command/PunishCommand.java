package litebans.command;

import java.util.List;
import litebans.config.ConfigFile;
import litebans.config.Message;
import litebans.config.TemplateGroup;
import litebans.core.LiteBansCore;
import litebans.core.MessageSender;
import litebans.database.EntryReader;
import litebans.database.PunishmentStorage;
import litebans.lib.kotlin.collections.ArraysKt;
import litebans.lib.kotlin.collections.CollectionsKt;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.text.StringsKt;
import litebans.platform.LiteBansPlatform;
import litebans.platform.Sender;
import litebans.platform.SenderRunnable2;
import litebans.punishment.EntryPlaceholders;
import litebans.punishment.PunishmentEntry;
import litebans.punishment.PunishmentRequest;
import litebans.sync.Throttler;
import org.jetbrains.annotations.NotNull;

public abstract class PunishCommand extends BaseCommand {
   public PunishCommand(@NotNull String var1, @NotNull LiteBansPlatform var2) {
      super(var1, var2);
   }

   public final void forwardCommand(@NotNull PunishmentStorage var1, @NotNull PunishmentEntry var2, @NotNull PunishmentRequest var3) {
      PunishmentRequest var4 = var3;
      if (var3.hasTemplate()) {
         Throttler var6 = (Throttler)var1.getPlatform().getModule(Throttler.class);
         List var7 = CollectionsKt.getList();
         if (var3.getTemplateGroup() != null) {
            if (var3.isFlag14()) {
               TemplateGroup var10000 = var3.getTemplateGroup();
               Intrinsics.handleObject(var10000);
               var7 = var10000.getTemplateData(var3.getIntValue()).getList();
            }
         } else {
            var7 = var3.getTemplate().getTemplateData(var3.getIntValue()).getList();
            if (var7.isEmpty()) {
               var7 = var3.getTemplate().getDefaults().getList();
            }
         }

         for (Object var9o : var7) {
            String var9 = (String) var9o;
            String var10 = var9;
            boolean var11 = false;
            if (StringsKt.checkString(var9, "console:", false, 2, null)) {
               var11 = true;
               var10 = StringsKt.getString(var9, "console:", null, 2, null);
            }

            if (StringsKt.checkCharSequence(var10, '/', false, 2, null)) {
               var10 = var10.substring(1);
            }

            CharSequence var23 = var10;
            char[] var12 = new char[]{' '};
            String var13 = (String)CollectionsKt.getObject(StringsKt.getList(var23, var12, false, 0, 6, null));
            boolean var21 = this.isPunishCommand(var13);
            if (var11 && !StringsKt.checkCharSequence3(var10, "--sender", false, 2, null) && var21) {
               var10 = var10 + " --sender-uuid=" + var4.getSender().getUuidString() + " --sender=" + var4.getSender().getName();
            }

            if (var21) {
               var10 = var10 + " --stack=" + (var3.getStack() + 1);
            }

            var10 = EntryReader.getCharSequence(var1, EntryReader.getCharSequence(var1, var10, var2, false, 2, null), var4.getPlayerHistory(), false, 2, null)
               .toString();
            var10 = EntryPlaceholders.getString(var1, var10, var3);
            Intrinsics.handleObject(var6);
            CommandRunnable var18 = this;
            ((LiteBansCore)var18.getPlatform().getModule(LiteBansCore.class)).debug(10, var10);
            Sender var22 = var11 ? var1.getPlatform().getConsole() : this.getCurrentSender();
            if (var22.isPlayer()) {
               var6.release((byte)3, var22.getUuidString());
            }

            var1.getPlatform().runSync(new SenderRunnable2(var22, var10));
         }
      }
   }

   private final boolean isPunishCommand(String var1) {
      if (!Intrinsics.checkObject(var1, "ban") && !Intrinsics.checkObject(var1, "kick")) {
         String[] var4 = new String[]{
            "mute",
            "warn",
            "unban",
            "unmute",
            "unwarn",
            "tempban",
            "tempmute",
            "ipban",
            "banip",
            "ban-ip",
            "ipmute",
            "muteip",
            "tempipban",
            "tempbanip",
            "tempipmute",
            "tempmuteip"
         };
         if (!ArraysKt.checkObjectArray(var4, var1)) {
            return false;
         }
      }

      return true;
   }

   public final void handlePunishmentRequest(@NotNull PunishmentRequest var1) {
      CommandRunnable var3 = this;
      ConfigFile var10000 = ((LiteBansCore)var3.getPlatform().getModule(LiteBansCore.class)).getMessagesLoader().getConfigFile();
      if ((var10000 != null ? var10000.getThrowable() : null) != null) {
         MessageSender.handleSenderDefault(CommandRunnable.messageSender, var1.getSender(), Message.RELOAD_FAIL_CONFIG_MESSAGES, null, 4, null);
      }

      CommandRunnable var2 = this;
      if (((LiteBansCore)var2.getPlatform().getModule(LiteBansCore.class)).getTemplateManager().getConfigFile() == null) {
         MessageSender.handleSenderDefault(CommandRunnable.messageSender, var1.getSender(), Message.RELOAD_FAIL_CONFIG_TEMPLATES, null, 4, null);
      }
   }
}
