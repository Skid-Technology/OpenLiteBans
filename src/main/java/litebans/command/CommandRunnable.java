package litebans.command;

import litebans.core.LiteBansCore;
import litebans.core.MessageSender;
import litebans.core.NotificationManager;
import litebans.database.DatabaseManager;
import litebans.lib.kotlin.NoWhenBranchMatchedException;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.platform.LiteBansPlatform;
import litebans.platform.Sender;
import litebans.punishment.MuteManager;
import litebans.util.Unreachable;
import org.jetbrains.annotations.NotNull;

public abstract class CommandRunnable implements Runnable {
   public static final MessageSender messageSender = new MessageSender(null);
   private final LiteBansPlatform liteBansPlatform;
   private static final ThreadLocal threadLocal = new ThreadLocal();

   public CommandRunnable(@NotNull LiteBansPlatform var1) {
      this.liteBansPlatform = var1;
   }

   public final LiteBansPlatform getPlatform() {
      return this.liteBansPlatform;
   }

   public final LiteBansCore getLiteBansCore() {
      return (LiteBansCore)this.getPlatform().getModule(LiteBansCore.class);
   }

   public final MuteManager getMuteManager() {
      return (MuteManager)this.getPlatform().getModule(MuteManager.class);
   }

   public final NotificationManager getNotificationManager() {
      return (NotificationManager)this.getPlatform().getModule(NotificationManager.class);
   }

   public final DatabaseManager getDatabaseManager() {
      return (DatabaseManager)this.getPlatform().getModule(DatabaseManager.class);
   }

   public final Sender getCurrentSender() {
      Object var10000 = threadLocal.get();
      Intrinsics.handleObject(var10000);
      return (Sender)var10000;
   }

   @Override
   public void run() {
      Unreachable.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   public void run3() {
   }

   public void run4() {
   }

   public static final void handleSender(@NotNull Sender var0, @NotNull CharSequence var1, @NotNull LiteBansPlatform var2) {
      messageSender.handleSender(var0, var1, var2);
   }

   public static final ThreadLocal getThreadLocal() {
      return threadLocal;
   }
}
