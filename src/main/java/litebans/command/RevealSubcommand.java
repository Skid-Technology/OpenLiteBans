package litebans.command;

import litebans.chat.ChatColorCode;
import litebans.core.LiteBansCore;
import litebans.core.MessageSender;
import litebans.lib.kotlin.NoWhenBranchMatchedException;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.platform.LiteBansPlatform;
import litebans.punishment.PunishmentService;
import litebans.randomid.Shuffler;
import org.jetbrains.annotations.NotNull;

public final class RevealSubcommand extends CommandRunnable {
   private final String[] stringArray;

   public RevealSubcommand(@NotNull String[] var1, @NotNull LiteBansPlatform var2) {
      super(var2);
      this.stringArray = var1;
   }

   @Override
   public void run() {
      CommandRunnable var10000 = this;
      String[] var1 = new String[]{"litebans.admin", "litebans.reveal"};
      PunishmentService.requireAnyPermission(var10000, var1);
      if (Intrinsics.checkObject(this.stringArray[1], "seed") && this.getCurrentSender().isConsole()) {
         CommandRunnable var5 = this;
         CommandRunnable.messageSender
            .getVoid(
               ChatColorCode.getString2(String.valueOf(((LiteBansCore)var5.getPlatform().getModule(LiteBansCore.class)).getConfig().getSecurityObscureSeed()))
            );
         throw new NoWhenBranchMatchedException();
      } else {
         CommandRunnable var2 = this;
         Shuffler var4 = ((LiteBansCore)var2.getPlatform().getModule(LiteBansCore.class)).getShuffler();
         if (Intrinsics.checkObject(this.stringArray[1], "web") && this.getCurrentSender().isConsole()) {
            StringBuilder var7 = new StringBuilder();

            for (int var3 = 0; var3 < 1000; var3++) {
               var7.append((String)var4.getHashMap().get(var3));
            }

            CommandRunnable.messageSender.getVoid(ChatColorCode.getString2("" + var4.getIntValue() + ':' + var7));
            throw new NoWhenBranchMatchedException();
         } else {
            long var6 = var4.getLong(this.stringArray[1]);
            if (var6 == -1L) {
               CommandRunnable.messageSender.getVoid(ChatColorCode.getString3("Invalid ID."));
               throw new NoWhenBranchMatchedException();
            } else {
               MessageSender.handleSenderDefault(
                  CommandRunnable.messageSender, this.getCurrentSender(), ChatColorCode.getString2(String.valueOf(var6)), null, 4, null
               );
            }
         }
      }
   }
}
