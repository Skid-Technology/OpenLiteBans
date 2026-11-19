package litebans.punishment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import litebans.command.ArgUtils;
import litebans.command.CommandRunnable;
import litebans.config.Message;
import litebans.core.LiteBansCore;
import litebans.core.NotificationManager;
import litebans.database.DatabaseManager;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.platform.ListenerHandle;
import litebans.platform.LiteBansPlatform;
import litebans.platform.LoginListenerRegistry;
import litebans.platform.Named;
import litebans.platform.Sender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class LockdownTask extends CommandRunnable implements Named {
   private final CharSequence charSequence;
   private final String string;
   private final boolean flag;
   private ListenerHandle listenerHandle;

   public LockdownTask(@NotNull LiteBansPlatform var1, @Nullable CharSequence var2, @NotNull String var3, boolean var4) {
      super(var1);
      this.charSequence = var2;
      this.string = var3;
      this.flag = var4;
   }

   public final CharSequence getCharSequence() {
      return this.charSequence;
   }

   public final String getString() {
      return this.string;
   }

   @Override
   public void run() {
      boolean var29;
      if (this.charSequence != null && this.getPlatform().getPlatformId() != 3) {
         LoginListenerRegistry var5 = (LoginListenerRegistry)this.getPlatform().findModule(LoginListenerRegistry.class);
         this.listenerHandle = var5.getListenerHandle(this);
         if (this.listenerHandle != null) {
            this.listenerHandle.getListenerHandle();
         }
      }

      LockdownTask var1 = this;
      if (this.charSequence != null) {
         String var3 = this.getString(this.string, false);
         Object[] var4 = this.getPlatform().getOnlineSenders();
         Object[] var6 = var4;
         Collection var7 = new ArrayList();
         int var9 = 0;

         for (int var10 = var4.length; var9 < var10; var9++) {
            Object var11 = var6[var9];
            Sender var12 = (Sender)var11;
            ServerScopeParser var10000 = ServerScope.serverScopeParser;
            String var10001 = var12.getServerName();
            if (var10001 == null) {
               CommandRunnable var16 = var1;
               var10001 = ((LiteBansCore)var16.getPlatform().getModule(LiteBansCore.class)).getConfig().getString2();
            }

            label63: {
               label78: {
                  if (!var10000.checkString(var10001, var1.string)) {
                     if (var1.getPlatform().getPlatformId() == 0) {
                        break label78;
                     }

                     CommandRunnable var20 = var1;
                     if (!ServerScope.serverScopeParser
                        .checkString(((LiteBansCore)var20.getPlatform().getModule(LiteBansCore.class)).getConfig().getString2(), var1.string)) {
                        break label78;
                     }
                  }

                  String var28 = "litebans.lockdown.bypass";
                  if (!var12.hasPermission(var28)) {
                     var29 = true;
                     break label63;
                  }
               }

               var29 = false;
            }

            if (var29) {
               var7.add(var11);
            }
         }

         for (Object var26 : (List)var7) {
            Sender var8 = (Sender)var26;
            CommandRunnable var27 = var1;
            NotificationManager var30 = (NotificationManager)var27.getPlatform().getModule(NotificationManager.class);
            Intrinsics.handleObject(var8);
            var30.kick(var8, var3);
         }
      }

      if (this.flag) {
         CommandRunnable var21 = this;
         if (((DatabaseManager)var21.getPlatform().getModule(DatabaseManager.class)).isConnected()) {
            Sender var31 = (Sender)CommandRunnable.messageSender.getThreadLocal().get();
            if (var31 == null) {
               var31 = this.getPlatform().getConsole();
            }

            Sender var22 = var31;
            CommandRunnable var24 = this;
            ((DatabaseManager)var24.getPlatform().getModule(DatabaseManager.class)).runWithStorageAsync(new ServerScopeFunction(this, var22));
         }
      }
   }

   @Override
   public void run3() {
      this.getPlatform().runSync(this);
   }

   @Override
   public void run4() {
      ListenerHandle var3 = this.listenerHandle;
      if (var3 != null) {
         LiteBansPlatform var10000 = this.getPlatform();
         ListenerHandle[] var4 = new ListenerHandle[]{var3};
         var10000.unregisterListeners(var4);
      }
   }

   public final void handleObject(@NotNull Object var1, @NotNull String var2, boolean var3) {
      LoginKickResult.handlePlatformBridge(this.getPlatform().createBridge(), var1, var2, null, var3, "KICK_OTHER", 4, null);
   }

   public static void handleObjectDefault(LockdownTask var0, Object var1, String var2, boolean var3, int var4, Object var5) {
      if ((var4 & 2) != 0) {
         var2 = "";
      }

      if ((var4 & 4) != 0) {
         var3 = true;
      }

      var0.handleObject(var1, var2, var3);
   }

   public final String getString(@NotNull String var1, boolean var2) {
      CharSequence var10000 = var2 ? Message.LOCKDOWN_KICK_MESSAGE_GLOBAL : Message.LOCKDOWN_KICK_MESSAGE;
      CharSequence[] var3 = new CharSequence[4];
      var3[0] = "reason";
      CharSequence var10003 = this.charSequence;
      if (this.charSequence == null) {
         var10003 = "";
      }

      var3[1] = var10003;
      var3[2] = "server";
      var3[3] = var1;
      return Message.getString(ArgUtils.getString(var10000, var3), true);
   }

   @Override
   public String getName() {
      return "lockdown";
   }
}
