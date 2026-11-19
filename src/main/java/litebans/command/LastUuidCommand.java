package litebans.command;

import java.io.Closeable;
import java.sql.SQLException;
import litebans.config.Message;
import litebans.database.DatabaseManager;
import litebans.database.EntryReader;
import litebans.database.PunishmentStorage;
import litebans.lib.kotlin.collections.ArraysKt;
import litebans.lib.kotlin.io.CloseableKt;
import litebans.platform.LiteBansPlatform;
import litebans.platform.Sender;
import litebans.punishment.PlayerHistory;
import litebans.punishment.PunishmentService;
import org.jetbrains.annotations.NotNull;

public final class LastUuidCommand extends BaseCommand {
   public LastUuidCommand(@NotNull LiteBansPlatform var1) {
      super("lastuuid", var1);
   }

   @Override
   public void execute(@NotNull Sender var1, @NotNull String[] var2) {
      CommandRunnable var4 = this;
      String var3 = PunishmentService.lookupUuid(var4, (String)ArraysKt.getObject(var2));
      var4 = this;
      DatabaseManager var21 = (DatabaseManager)var4.getPlatform().getModule(DatabaseManager.class);

      try {
         PunishmentStorage var6 = var21.openStorage();
         Closeable var7 = var6;
         Throwable var8 = null;

         try {
            PlayerHistory var13 = PunishmentService.resolveTargetDefault(var6, var3, false, 2, null);
            PunishmentService.reply(this, EntryReader.getCharSequence(var6, Message.LASTUUID_MESSAGE, var13, false, 2, null));
         } catch (Throwable var17) {
            var8 = var17;
            throw var17;
         } finally {
            CloseableKt.handleCloseable(var7, var8);
         }
      } catch (Exception var19) {
         if (!(var19 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var19); }
         if (!var21.isConnectionError(var19)) {
            throw var19;
         }
      }
   }
}
