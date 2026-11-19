package litebans.command;

import java.io.Closeable;
import java.sql.SQLException;
import java.util.List;
import litebans.config.Message;
import litebans.core.LiteBansCore;
import litebans.core.MessageSender;
import litebans.core.NotificationManager;
import litebans.database.DatabaseManager;
import litebans.database.EntryReader;
import litebans.database.PunishmentStorage;
import litebans.lib.kotlin.NoWhenBranchMatchedException;
import litebans.lib.kotlin.collections.ArraysKt;
import litebans.lib.kotlin.io.CloseableKt;
import litebans.lib.kotlin.text.StringsKt;
import litebans.platform.LiteBansPlatform;
import litebans.platform.Sender;
import litebans.punishment.EntryPlaceholders;
import litebans.punishment.PlayerHistory;
import litebans.punishment.PunishmentService;
import org.jetbrains.annotations.NotNull;

public final class IpHistoryCommand extends BaseCommand {
   public IpHistoryCommand(@NotNull LiteBansPlatform var1) {
      super("iphistory", var1);
   }

   @Override
   public void execute(@NotNull Sender var1, @NotNull String[] var2) {
      MessageSender var3 = CommandRunnable.messageSender;
      CommandRunnable var4 = this;
      boolean var29 = ((LiteBansCore)var4.getPlatform().getModule(LiteBansCore.class)).getConfig().isSecurityIphistory() && !var1.isConsole();
      CharSequence var5 = Message.ERROR_CONSOLE;
      if (var29) {
         var3.getVoid(var5);
         throw new NoWhenBranchMatchedException();
      } else {
         var4 = this;
         String var28 = PunishmentService.lookupUuid(var4, (String)ArraysKt.getObject(var2));
         int var31 = ArgParser.getInt(var2, 1, 10);
         CommandRunnable var33 = this;
         DatabaseManager var34 = (DatabaseManager)var33.getPlatform().getModule(DatabaseManager.class);

         try {
            PunishmentStorage var7 = var34.openStorage();
            Closeable var8 = var7;
            Throwable var9 = null;

            try {
               PunishmentStorage var12 = var7;
               List var14 = var7.findHistory(var28);
               MessageSender var15 = CommandRunnable.messageSender;
               boolean var16 = var14.isEmpty();
               CharSequence var17 = Message.IPHISTORY_ERROR_NO_HISTORY.getString("target", var28);
               if (var16) {
                  var15.getVoid(var17);
                  throw new NoWhenBranchMatchedException();
               }

               PunishmentService.reply(
                  this, var7.getCharSequence(EntryPlaceholders.getString(Message.IPHISTORY_START.getString("target", var28), "limit", var31))
               );
               int var32 = Math.min(var31, var14.size());
               int var35 = 0;

               for (int var36 = var32; var35 < var36; var35++) {
                  PlayerHistory var37 = (PlayerHistory)var14.get(var35);
                  String var10000 = var37.getIp();
                  if (var10000 != null) {
                     String var18 = var10000;
                     if (StringsKt.checkCharSequence(var18, '#', false, 2, null)) {
                        if (var32 < var14.size()) {
                           var32++;
                        }
                     } else {
                        PunishmentService.reply(
                           this,
                           EntryPlaceholders.getString(
                              EntryReader.getCharSequence(var12, Message.IPHISTORY_ENTRY.getString("ip", var18), var37, false, 2, null),
                              "date",
                              () -> getObject(this, var37)
                           )
                        );
                     }
                  }
               }
            } catch (Throwable var25) {
               var9 = var25;
               throw var25;
            } finally {
               CloseableKt.handleCloseable(var8, var9);
            }
         } catch (Exception var27) {
            if (!(var27 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var27); }
            if (!var34.isConnectionError(var27)) {
               throw var27;
            }
         }
      }
   }

   private static final Object getObject(IpHistoryCommand var0, PlayerHistory var1) {
      CommandRunnable var2 = var0;
      return ((NotificationManager)var2.getPlatform().getModule(NotificationManager.class)).formatDate(var1.getDate().getTime(), true);
   }
}
