package litebans.command;

import java.util.List;
import litebans.chat.ChatColorCode;
import litebans.config.Message;
import litebans.core.NotificationManager;
import litebans.database.DatabaseManager;
import litebans.database.PunishmentStorage;
import litebans.lib.kotlin.NoWhenBranchMatchedException;
import litebans.lib.kotlin.ResultFailure;
import litebans.lib.kotlin.Unit;
import litebans.lib.kotlin.collections.ArraysKt;
import litebans.lib.kotlin.collections.CollectionsKt;
import litebans.lib.kotlin.text.StringsKt;
import litebans.platform.LiteBansPlatform;
import litebans.punishment.Flag;
import litebans.punishment.PunishmentService;
import org.jetbrains.annotations.NotNull;

public final class BroadcastSubcommand extends CommandRunnable {
   private final String[] stringArray;

   public BroadcastSubcommand(@NotNull String[] var1, @NotNull LiteBansPlatform var2) {
      super(var2);
      this.stringArray = var1;
   }

   @Override
   public void run() {
      CommandRunnable var10000 = this;
      String[] var1 = new String[]{"litebans.admin", "litebans.broadcast"};
      PunishmentService.requireAnyPermission(var10000, var1);
      if (this.stringArray.length <= 1) {
         CommandRunnable.messageSender.getVoid(ChatColorCode.getString3("Usage: /litebans broadcast <message>"));
         throw new NoWhenBranchMatchedException();
      } else {
         List var3 = ArraysKt.getList2(this.stringArray);
         CommandRunnable var2 = this;
         ((DatabaseManager)var2.getPlatform().getModule(DatabaseManager.class)).runWithStorageAsync(st -> getUnit(var3, this, (PunishmentStorage) st));
      }
   }

   private static final boolean checkString(String var0, ResultFailure var1, String var2) {
      boolean var10000;
      if (StringsKt.checkString(var2, var0, false, 2, null)) {
         var1.object = var2.substring(var0.length());
         var10000 = false;
      } else {
         var10000 = true;
      }

      return var10000;
   }

   private static final Unit getUnit(List var0, BroadcastSubcommand var1, PunishmentStorage var2) {
      ResultFailure var3 = new ResultFailure();
      var3.object = "broadcast";
      String var4 = "broadcast-type:";
      CollectionsKt.checkList(var0, o -> checkString(var4, var3, (String) o));
      String var6 = Message.getString(CollectionsKt.getString(var0, " ", null, null, 0, null, null, 62, null), true);
      byte var7 = 10;
      CharSequence var5 = var2.getCharSequence(var6.substring(var7));
      CommandRunnable var16 = var1;
      NotificationManager var17 = (NotificationManager)var16.getPlatform().getModule(NotificationManager.class);
      NotificationManager.broadcastDefault(
         var17, var2, var5, (String)var3.object, null, var1.getCurrentSender().getServerName(), false, false, var1.getCurrentSender(), 52, null
      );
      Flag var18 = Flag.BROADCAST_CUSTOM;
      if (var2.getDatabaseManager().isSyncEnabled()) {
         CharSequence[] var11 = new CharSequence[]{(CharSequence)var3.object, var5};
         CharSequence var15 = ArraysKt.getString(var11, "\ufeff", null, null, 0, null, null, 62, null);
         PunishmentService.broadcastDefault(var2, var18, var15, null, null, 12, null);
      }

      return Unit.INSTANCE;
   }
}
