package litebans.importer;

import java.sql.Connection;
import litebans.command.CommandRunnable;
import litebans.config.Message;
import litebans.core.LiteBansCore;
import litebans.core.MessageSender;
import litebans.database.PunishmentStorage;
import litebans.database.Table;
import litebans.platform.LiteBansPlatform;
import litebans.platform.Sender;
import org.jetbrains.annotations.NotNull;

public final class LiteBansImporter extends Importer {
   public LiteBansImporter(@NotNull LiteBansPlatform var1) {
      super(var1, "LiteBans", null, 0, 0L, 28, null);
   }

   @Override
   public void handleString(@NotNull String var1, @NotNull Connection var2, @NotNull PunishmentStorage var3, boolean var4) {
      long var5 = var3.countEntries();
      Table[] var7 = Table.values();
      int var9 = 0;

      for (int var10 = var7.length; var9 < var10; var9++) {
         Table var11 = var7[var9];
         CommandRunnable var14 = this;
         var3.copyTable(var2, var11, ((LiteBansCore)var14.getPlatform().getModule(LiteBansCore.class)).getConfig().isImportSqlite());
      }

      long var15 = var3.countEntries();
      this.getAtomicLong().set(var15 - var5);
   }

   @Override
   public void handleSender(@NotNull Sender var1) {
      MessageSender.handleSenderDefault(
         CommandRunnable.messageSender, var1, Message.IMPORT_FINISH_LITEBANS.getString("amount", this.getAtomicLong().get()), null, 4, null
      );
   }
}
