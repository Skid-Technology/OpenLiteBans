package litebans.command;

import java.util.UUID;
import litebans.chat.ChatColorCode;
import litebans.config.Message;
import litebans.core.MessageSender;
import litebans.database.DatabaseManager;
import litebans.database.PunishmentStorage;
import litebans.hook.UsernamePattern;
import litebans.lib.kotlin.NoWhenBranchMatchedException;
import litebans.lib.kotlin.Unit;
import litebans.lib.kotlin.text.StringsKt;
import litebans.platform.LiteBansPlatform;
import litebans.punishment.Profile;
import org.jetbrains.annotations.NotNull;

public final class AddLoginSubcommand extends CommandRunnable {
   private final String[] stringArray;

   public AddLoginSubcommand(@NotNull String[] var1, @NotNull LiteBansPlatform var2) {
      super(var2);
      this.stringArray = var1;
   }

   @Override
   public void run() {
      MessageSender var1 = CommandRunnable.messageSender;
      boolean var2 = this.stringArray.length < 2;
      CharSequence var3 = ChatColorCode.getString3("$command add-login <name> <UUID> <IP>");
      if (var2) {
         var1.getVoid(var3);
         throw new NoWhenBranchMatchedException();
      } else {
         CommandRunnable var8 = this;
         ((DatabaseManager)var8.getPlatform().getModule(DatabaseManager.class)).runWithStorageAsync(st -> getUnit(this, (PunishmentStorage) st));
      }
   }

   private final void run2() {
      MessageSender.handleSenderDefault(
         CommandRunnable.messageSender, this.getCurrentSender(), ChatColorCode.getString2("Login record(s) added."), null, 4, null
      );
   }

   private static final Unit getUnit(AddLoginSubcommand var0, PunishmentStorage var1) {
      MessageSender var2 = CommandRunnable.messageSender;
      boolean var3 = var1.isClosed();
      CharSequence var4 = Message.ERROR_NO_DATABASE;
      if (var3) {
         var2.getVoid(var4);
         throw new NoWhenBranchMatchedException();
      } else if (var0.stringArray.length != 2) {
         String var10 = null;
         if (var0.stringArray.length >= 4) {
            var10 = var0.stringArray[3];
         }

         var1.recordLogin(var0.stringArray[1], var0.stringArray[2], var10, false, true);
         var0.run2();
         return Unit.INSTANCE;
      } else {
         CharSequence var10000 = var0.stringArray[1];
         char[] var11 = new char[]{','};

         for (Object var12o : StringsKt.getList(var10000, var11, false, 0, 6, null)) {
            String var12 = (String) var12o;
            Profile var14 = UsernamePattern.mojangApi.getProfile(var12);
            if (var14 != null) {
               UUID var15 = var14.getUuid();
               if (var15 != null) {
                  String var16 = var15.toString();
                  if (var16 != null) {
                     String var13 = var16;
                     var1.recordLogin(var12, var13, null, false, true);
                  }
               }
            }
         }

         var0.run2();
         return Unit.INSTANCE;
      }
   }
}
