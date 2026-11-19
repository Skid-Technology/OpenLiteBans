package litebans.command;

import java.io.Closeable;
import java.sql.SQLException;
import litebans.config.Message;
import litebans.core.LiteBansCore;
import litebans.core.MessageSender;
import litebans.database.DatabaseManager;
import litebans.database.EntryReader;
import litebans.database.PunishmentStorage;
import litebans.lib.kotlin.collections.ArraysKt;
import litebans.lib.kotlin.io.CloseableKt;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.platform.LiteBansPlatform;
import litebans.platform.Sender;
import litebans.punishment.DupeIpLookup;
import litebans.punishment.EntryPlaceholders;
import litebans.punishment.PlayerHistory;
import litebans.punishment.PunishmentService;
import org.jetbrains.annotations.NotNull;

public final class DupeIpCommand extends BaseCommand {
   public DupeIpCommand(@NotNull LiteBansPlatform var1) {
      super("dupeip", var1);
   }

   @Override
   public void execute(@NotNull Sender var1, @NotNull String[] var2) {
      CommandRunnable var4 = this;
      String var3 = PunishmentService.lookupUuid(var4, (String)ArraysKt.getObject(var2));
      CommandRunnable var5 = this;
      int var27 = ArgParser.getInt(var2, 1, ((LiteBansCore)var5.getPlatform().getModule(LiteBansCore.class)).getConfig().getNotifyDupeipScanLimit());
      var5 = this;
      DatabaseManager var29 = (DatabaseManager)var5.getPlatform().getModule(DatabaseManager.class);

      try {
         PunishmentStorage var7 = var29.openStorage();
         Closeable var8 = var7;
         Throwable var9 = null;

         try {
            DupeIpLookup var14 = new DupeIpLookup(var3, var7, var1, false, var27).getDupeIpLookup();
            if (!var14.isFlag2()) {
               boolean var10000;
               label83: {
                  label98: {
                     if (!var1.isConsole()) {
                        CommandRunnable var15 = this;
                        if (((LiteBansCore)var15.getPlatform().getModule(LiteBansCore.class)).getConfig().isSecurityDupeip()) {
                           break label98;
                        }
                     }

                     String var16 = "litebans.dupeip.viewip";
                     if (var1.hasPermission(var16)) {
                        var10000 = false;
                        break label83;
                     }
                  }

                  var10000 = true;
               }

               boolean var20 = var10000;
               String var30 = var14.isFlag5() ? Message.DUPEIP_MULTIPLE_ADDRESSES.getString("num", var14.getIntValue2()) : var14.getString2();
               CharSequence var31 = var20 ? Message.DUPEIP_START_NO_IP : Message.DUPEIP_START;
               CharSequence var10001 = EntryPlaceholders.getString(var31, "ip", var30);
               PlayerHistory var10002 = var14.getPlayerHistory();
               Intrinsics.handleObject(var10002);
               var31 = EntryReader.getCharSequence(var7, var10001, var10002, false, 2, null);
               MessageSender.handleSenderDefault(CommandRunnable.messageSender, var1, var31, null, 4, null);
               MessageSender.handleSenderDefault(CommandRunnable.messageSender, var1, var14.getString3(), null, 4, null);
               MessageSender.handleSenderDefault(CommandRunnable.messageSender, var1, Message.DUPEIP_END, null, 4, null);
            }
         } catch (Throwable var24) {
            var9 = var24;
            throw var24;
         } finally {
            CloseableKt.handleCloseable(var8, var9);
         }
      } catch (Exception var26) {
         if (!(var26 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var26); }
         if (!var29.isConnectionError(var26)) {
            throw var26;
         }
      }
   }
}
