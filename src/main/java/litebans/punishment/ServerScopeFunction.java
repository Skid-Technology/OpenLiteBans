package litebans.punishment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import litebans.command.ArgUtils;
import litebans.command.CommandRunnable;
import litebans.config.Message;
import litebans.core.LiteBansCore;
import litebans.core.MessageSender;
import litebans.database.PunishmentStorage;
import litebans.lib.kotlin.Unit;
import litebans.lib.kotlin.jvm.internal.Function1;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.platform.Sender;

public final class ServerScopeFunction implements Function1 {
   public final LockdownTask lockdownTask;
   public final Sender sender;

   public ServerScopeFunction(LockdownTask var1, Sender var2) {
      this.lockdownTask = var1;
      this.sender = var2;
   }

   public final void handlePunishmentStorage(PunishmentStorage var1) {
      CharSequence var25;
      if (this.lockdownTask.getCharSequence() != null) {
         var25 = Message.LOCKDOWN_MESSAGE;
         CharSequence[] var3 = new CharSequence[]{"serverScope", this.lockdownTask.getString(), "reason", this.lockdownTask.getCharSequence()};
         var25 = ArgUtils.getString(var25, var3);
      } else {
         var25 = Message.LOCKDOWN_END;
      }

      CharSequence var2 = var25;
      Sender var18 = this.sender;
      Intrinsics.handleObject(var18);
      var2 = var1.getCharSequence(var2, var18);
      Object[] var19 = var1.getPlatform().getOnlineSenders();
      LockdownTask var4 = this.lockdownTask;
      Object[] var6 = var19;
      Collection var7 = new ArrayList();
      int var9 = 0;

      for (int var10 = var19.length; var9 < var10; var9++) {
         Object var11 = var6[var9];
         Sender var12 = (Sender)var11;
         ServerScopeParser var26 = ServerScope.serverScopeParser;
         String var10001 = var12.getServerName();
         if (var10001 == null) {
            CommandRunnable var14 = var4;
            var10001 = ((LiteBansCore)var14.getPlatform().getModule(LiteBansCore.class)).getConfig().getString2();
         }

         if (var26.checkString(var10001, var4.getString())) {
            var7.add(var11);
         }
      }

      for (Object var23 : (List)var7) {
         Sender var24 = (Sender)var23;
         Intrinsics.handleObject(var24);
         MessageSender.handleSenderDefault(CommandRunnable.messageSender, var24, var2, null, 4, null);
      }

      CommandRunnable var21 = this.lockdownTask;
      if (((LiteBansCore)var21.getPlatform().getModule(LiteBansCore.class)).getConfig().isNotifyNotifyConsole()) {
         if (var1.getPlatform().getPlatformId() == 3) {
            var1.getPlatform().getLogger().info(var2.toString());
         } else {
            var18 = var1.getPlatform().getConsole();
            MessageSender.handleSenderDefault(CommandRunnable.messageSender, var18, var2, null, 4, null);
         }
      }
   }

   @Override
   public Object invoke(Object var1) {
      this.handlePunishmentStorage((PunishmentStorage)var1);
      return Unit.INSTANCE;
   }
}
