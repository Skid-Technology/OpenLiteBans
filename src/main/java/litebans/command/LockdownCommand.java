package litebans.command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import litebans.config.Message;
import litebans.core.LiteBansCore;
import litebans.core.NotificationManager;
import litebans.database.DatabaseManager;
import litebans.lib.kotlin.NoWhenBranchMatchedException;
import litebans.lib.kotlin.collections.CollectionsKt;
import litebans.lib.kotlin.collections.SetsKt;
import litebans.platform.LiteBansPlatform;
import litebans.platform.Sender;
import litebans.punishment.LockdownTask;
import litebans.punishment.ServerScope;
import litebans.punishment.ServerScopeParser;
import org.jetbrains.annotations.NotNull;

public final class LockdownCommand extends BaseCommand {
   public LockdownCommand(@NotNull LiteBansPlatform var1) {
      super("lockdown", var1);
   }

   @Override
   public void execute(@NotNull Sender var1, @NotNull String[] var2) {
      ArrayList var3 = CollectionsKt.getArrayList(Arrays.copyOf(var2, var2.length));
      List var10002 = var3;
      CommandRunnable var5 = this;
      ServerScope var10 = ServerScopeParser.getServerScopeDefault(
         ServerScope.serverScopeParser, var1, var10002, ((DatabaseManager)var5.getPlatform().getModule(DatabaseManager.class)).getString(), false, 8, null
      );
      String var6 = "local";
      CommandRunnable var7 = this;
      String var15 = ((DatabaseManager)var7.getPlatform().getModule(DatabaseManager.class)).getString();
      CharSequence var9 = var10.getString();
      String var4 = var9 == null || var9.length() == 0 ? ServerScope.serverScopeParser.getString(var6, var15, var1) : var10.getString();
      String var10000 = (String)CollectionsKt.getObject(var3, 0);
      if (var10000 != null) {
         String var8 = var10000;
         var10000 = var8.toLowerCase(Locale.ENGLISH);
         if (var10000 != null) {
            String var11 = var10000;
            if (var3.size() == 1) {
               String[] var12 = new String[]{"stop", "end", "finish", "off", "disable"};
               if (SetsKt.getSet(var12).contains(var11)) {
                  CommandRunnable var20 = this;
                  LockdownTask var22 = ((NotificationManager)var20.getPlatform().getModule(NotificationManager.class)).getLockdownTask();
                  if (var22 != null) {
                     CharSequence var23 = var22.getCharSequence();
                     if (var23 != null) {
                        CharSequence var14 = var23;
                        var7 = this;
                        LiteBansCore var18 = (LiteBansCore)var7.getPlatform().getModule(LiteBansCore.class);
                        if (var18.isDebugEnabled()) {
                           var18.debug(var14);
                        }

                        var7 = this;
                        ((NotificationManager)var7.getPlatform().getModule(NotificationManager.class)).startLockdown(var4, null, true);
                        return;
                     }
                  }

                  CommandRunnable.messageSender.getVoid(Message.LOCKDOWN_ERROR_NOT_ACTIVE);
                  throw new NoWhenBranchMatchedException();
               }
            }

            var6 = Message.getString(CollectionsKt.getString(var3, " ", null, null, 0, null, null, 62, null), true);
            var7 = this;
            ((NotificationManager)var7.getPlatform().getModule(NotificationManager.class)).startLockdown(var4, var6, true);
            return;
         }
      }

      CommandRunnable.messageSender.getVoid(Message.LOCKDOWN_USAGE);
      throw new NoWhenBranchMatchedException();
   }
}
