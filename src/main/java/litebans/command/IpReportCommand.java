package litebans.command;

import java.io.Closeable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import litebans.config.Message;
import litebans.core.LiteBansCore;
import litebans.database.DatabaseManager;
import litebans.database.PunishmentStorage;
import litebans.lib.kotlin.io.CloseableKt;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.platform.LiteBansPlatform;
import litebans.platform.Sender;
import litebans.punishment.DupeIpLookup;
import litebans.punishment.PunishmentService;
import org.jetbrains.annotations.NotNull;

public final class IpReportCommand extends BaseCommand {
   public IpReportCommand(@NotNull LiteBansPlatform var1) {
      super("ipreport", var1);
   }

   @Override
   public boolean isArgsRequired() {
      return false;
   }

   @Override
   public void execute(@NotNull Sender var1, @NotNull String[] var2) {
      Object[] var4 = this.getPlatform().getOnlineSenders();
      Object[] var6 = var4;
      Collection var7 = new ArrayList();
      int var9 = 0;

      for (int var10 = var4.length; var9 < var10; var9++) {
         Object var11 = var6[var9];
         Sender var12 = (Sender)var11;
         if (!Intrinsics.checkObject(var12, var1)) {
            var7.add(var11);
         }
      }

      List var3 = (List)var7;
      CommandRunnable var30 = this;
      DatabaseManager var31 = (DatabaseManager)var30.getPlatform().getModule(DatabaseManager.class);

      try {
         PunishmentStorage var32 = var31.openStorage();
         Closeable var33 = var32;
         Throwable var8 = null;

         try {
            PunishmentStorage var34 = var32;
            CharSequence var13 = var32.getCharSequence(Message.IPREPORT_START.getString("num", var3.size()));
            PunishmentService.reply(this, var13);

            for (Object var17 : var3) {
               Sender var18 = (Sender)var17;
               DupeIpLookup var20 = new DupeIpLookup(var18.getName(), var34, null, false, 0, 28, null).getDupeIpLookup();
               if (!var20.isFlag2()) {
                  int var10000 = var20.getIntValue3();
                  CommandRunnable var21 = this;
                  if (var10000 >= ((LiteBansCore)var21.getPlatform().getModule(LiteBansCore.class)).getConfig().getIpreportMinimumAccounts()) {
                     CommandRunnable var36 = this;
                     CharSequence var10001 = var34.getCharSequence2(Message.IPREPORT_ENTRY, var18);
                     CharSequence[] var35 = new CharSequence[]{"result", var20.getString3()};
                     PunishmentService.reply(var36, ArgUtils.getString(var10001, var35));
                  }
               }
            }
         } catch (Throwable var27) {
            var8 = var27;
            throw var27;
         } finally {
            CloseableKt.handleCloseable(var33, var8);
         }
      } catch (Exception var29) {
         if (!(var29 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var29); }
         if (!var31.isConnectionError(var29)) {
            throw var29;
         }
      }
   }
}
