package litebans.punishment;

import litebans.command.ArgUtils;
import litebans.command.BanCommand;
import litebans.command.CommandRunnable;
import litebans.core.LiteBansCore;
import litebans.database.EntryReader;
import litebans.database.PunishmentStorage;
import litebans.lib.kotlin.Unit;
import litebans.lib.kotlin.jvm.internal.Function1;
import litebans.lib.kotlin.text.StringsKt;
import litebans.platform.Sender;

public final class ConsoleFunction implements Function1 {
   public final boolean flag;
   public final String string;
   public final BanCommand banCommand;
   public final String string2;
   public final Sender sender;

   public ConsoleFunction(boolean var1, String var2, BanCommand var3, String var4, Sender var5) {
      this.flag = var1;
      this.string = var2;
      this.banCommand = var3;
      this.string2 = var4;
      this.sender = var5;
   }

   public final void handlePunishmentStorage(PunishmentStorage var1) {
      if (this.flag) {
         CharSequence var2 = this.string;
         CommandRunnable var3 = this.banCommand;
         byte var6 = ((LiteBansCore)var3.getPlatform().getModule(LiteBansCore.class)).getConfig().getUsernameMaxLength();
         EntryReader.handlePunishmentStorage(var1, ArgUtils.getString(var2.toString(), var6 & 255), this.string2, this.sender.getIp(), false, false, 16, null);
      } else if (this.sender.isPlayer()) {
         CharSequence var4 = this.sender.getName();
         CommandRunnable var7 = this.banCommand;
         byte var8 = ((LiteBansCore)var7.getPlatform().getModule(LiteBansCore.class)).getConfig().getUsernameMaxLength();
         EntryReader.handlePunishmentStorage(
            var1, ArgUtils.getString(var4.toString(), var8 & 255), this.sender.getUuidString(), this.sender.getIp(), false, false, 16, null
         );
      } else if ((!this.sender.isConsole() || StringsKt.checkString(this.string, "CONSOLE", true)) && this.sender.isConsole()) {
         if (this.sender.isConsole()) {
            EntryReader.handlePunishmentStorage(var1, "CONSOLE", "CONSOLE", "#", false, false, 16, null);
         }
      } else {
         CharSequence var5 = this.string;
         CommandRunnable var9 = this.banCommand;
         byte var10 = ((LiteBansCore)var9.getPlatform().getModule(LiteBansCore.class)).getConfig().getUsernameMaxLength();
         EntryReader.handlePunishmentStorage(var1, ArgUtils.getString(var5.toString(), var10 & 255), this.string2, "#", false, false, 16, null);
      }
   }

   @Override
   public Object invoke(Object var1) {
      this.handlePunishmentStorage((PunishmentStorage)var1);
      return Unit.INSTANCE;
   }
}
