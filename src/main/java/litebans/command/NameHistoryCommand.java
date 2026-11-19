package litebans.command;

import java.io.Closeable;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import litebans.config.Message;
import litebans.core.MessageSender;
import litebans.database.DatabaseManager;
import litebans.database.EntryReader;
import litebans.database.PunishmentStorage;
import litebans.lib.kotlin.NoWhenBranchMatchedException;
import litebans.lib.kotlin.collections.ArraysKt;
import litebans.lib.kotlin.collections.CollectionsKt;
import litebans.lib.kotlin.collections.MapsKt;
import litebans.lib.kotlin.io.CloseableKt;
import litebans.lib.kotlin.ranges.RangesKt;
import litebans.platform.LiteBansPlatform;
import litebans.platform.Sender;
import litebans.punishment.EntryPlaceholders;
import litebans.punishment.PlayerHistory;
import litebans.punishment.PunishmentService;
import org.jetbrains.annotations.NotNull;

public final class NameHistoryCommand extends BaseCommand {
   public NameHistoryCommand(@NotNull LiteBansPlatform var1) {
      super("namehistory", var1);
   }

   @Override
   public void execute(@NotNull Sender var1, @NotNull String[] var2) {
      CommandRunnable var4 = this;
      String var3 = PunishmentService.lookupUuid(var4, (String)ArraysKt.getObject(var2));
      int var32 = ArgParser.getInt(var2, 1, 10);
      CommandRunnable var5 = this;
      DatabaseManager var33 = (DatabaseManager)var5.getPlatform().getModule(DatabaseManager.class);

      try {
         PunishmentStorage var7 = var33.openStorage();
         Closeable var8 = var7;
         Throwable var9 = null;

         try {
            PunishmentStorage var12 = var7;
            List var14 = var7.findHistory(var3);
            MessageSender var15 = CommandRunnable.messageSender;
            boolean var16 = var14.isEmpty();
            CharSequence var17 = Message.IPHISTORY_ERROR_NO_HISTORY;
            if (var16) {
               var15.getVoid(var17);
               throw new NoWhenBranchMatchedException();
            }

            PunishmentService.reply(
               this, var7.getCharSequence(EntryPlaceholders.getString(Message.NAMEHISTORY_START.getString("target", var3), "limit", var32))
            );
            Iterable var34 = var14;
            int var18 = RangesKt.getInt(MapsKt.getInt(CollectionsKt.getInt(var34, 10)), 16);
            Map var20 = new LinkedHashMap(var18);

            for (Object var23 : var34) {
               PlayerHistory var24 = (PlayerHistory)var23;
               var20.put(var24.getString(), var23);
            }

            for (Object var19 : CollectionsKt.getList(var20.values(), var32)) {
               PlayerHistory var37 = (PlayerHistory)var19;
               PunishmentService.reply(this, EntryReader.getCharSequence(var12, Message.NAMEHISTORY_ENTRY, var37, false, 2, null));
            }
         } catch (Throwable var29) {
            var9 = var29;
            throw var29;
         } finally {
            CloseableKt.handleCloseable(var8, var9);
         }
      } catch (Exception var31) {
         if (!(var31 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var31); }
         if (!var33.isConnectionError(var31)) {
            throw var31;
         }
      }
   }
}
