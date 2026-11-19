package litebans.command;

import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import litebans.config.Config;
import litebans.core.LiteBansCore;
import litebans.core.MessageSender;
import litebans.database.DatabaseManager;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.text.Regex;
import litebans.lib.kotlin.text.StringsKt;
import litebans.platform.LiteBansPlatform;
import litebans.platform.Sender;
import litebans.punishment.LoginKickResult;
import litebans.punishment.MuteManager;
import litebans.punishment.MuteMessageTask;
import litebans.punishment.PunishmentEntry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class MuteChatHandler extends CommandRunnable {
   public static final MuteChatHandlerCompanion Companion = new MuteChatHandlerCompanion(null);
   private ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();

   public MuteChatHandler(@NotNull LiteBansPlatform var1) {
      super(var1);
   }

   public final ConcurrentHashMap getConcurrentHashMap() {
      return this.concurrentHashMap;
   }

   public final void setConcurrentHashMap(@NotNull ConcurrentHashMap var1) {
      this.concurrentHashMap = var1;
   }

   public final void handleObject(@NotNull Object var1, @NotNull String var2, @NotNull Sender var3, boolean var4) {
      UUID var5 = var3.getUuid();
      CommandRunnable var7 = this;
      String var6 = ((LiteBansCore)var7.getPlatform().getModule(LiteBansCore.class)).getSenderIp(var3, var1);
      CommandRunnable var8 = this;
      MuteManager var10000 = (MuteManager)var8.getPlatform().getModule(MuteManager.class);
      String var10003 = var3.getServerName();
      if (var10003 == null) {
         CommandRunnable var9 = this;
         var10003 = ((DatabaseManager)var9.getPlatform().getModule(DatabaseManager.class)).getString();
      }

      PunishmentEntry var18 = var10000.getActiveMute(var5, var6, var10003);
      var8 = this;
      LiteBansCore var20 = (LiteBansCore)var8.getPlatform().getModule(LiteBansCore.class);
      byte var22 = 100;
      if (var20.isDebugLevel(var22)) {
         var20.debug("[+] Mute for " + var3.getName() + " is " + var18);
      }

      if (var18 != null) {
         if (!var4 && var2.length() != 0 && var2.charAt(0) == '/') {
            CharSequence var26 = var2;
            char[] var23 = new char[]{' '};
            String var21 = (String)StringsKt.getList(var26, var23, false, 0, 6, null).get(0);
            CommandRunnable var10 = this;
            Config var24 = ((LiteBansCore)var10.getPlatform().getModule(LiteBansCore.class)).getConfig();
            String var25 = var2.toLowerCase(Locale.ROOT);
            if (ArgUtils.checkCharSequence(var25)) {
               var25 = ArgUtils.getString(var25);
            }

            if (StringsKt.checkCharSequence3(var21, ':', false, 2, null) && var24.isMutesPreventBypass()) {
               var25 = this.getString(var25);
            }

            Iterable var11 = var24.getMutesCommandBlacklist();
            boolean var27;
            if (var11 instanceof Collection && ((Collection)var11).isEmpty()) {
               var27 = false;
            } else {
               Iterator var13 = var11.iterator();

               while (true) {
                  if (!var13.hasNext()) {
                     var27 = false;
                     break;
                  }

                  Object var14 = var13.next();
                  String var15 = (String)var14;
                  if (StringsKt.checkString(var25, var15, false, 2, null)) {
                     var27 = true;
                     break;
                  }
               }
            }

            if (var27 || var24.getSet().contains(var25)) {
               this.handleObject(var1, var3, var2, var18);
            }
         } else {
            this.handleObject(var1, var3, var2, var18);
         }
      }
   }

   public static void handleObjectDefault(MuteChatHandler var0, Object var1, String var2, Sender var3, boolean var4, int var5, Object var6) {
      if ((var5 & 8) != 0) {
         var4 = false;
      }

      var0.handleObject(var1, var2, var3, var4);
   }

   private final String getString(String var1) {
      CharSequence var2 = var1;
      Regex var3 = new Regex("^/[^ ]+:");
      String var4 = "/";
      return var3.getString2(var2, var4);
   }

   public final void handleObject(@NotNull Object var1, @NotNull Sender var2, @Nullable String var3, @Nullable PunishmentEntry var4) {
      LoginKickResult.handlePlatformBridge(this.getPlatform().createBridge(), var1, null, null, false, null, 30, null);
      if (var4 != null) {
         CommandRunnable var5 = this;
         MuteManager var14 = (MuteManager)var5.getPlatform().getModule(MuteManager.class);
         MessageSender var7 = CommandRunnable.messageSender;
         Sender var8 = var2;

         try {
            var7.getThreadLocal().set(var8);
            LiteBansPlatform var10002 = var14.platform;
            CharSequence var10004 = var3;
            Intrinsics.handleObject(var3);
            new MuteMessageTask(var10002, var4, var10004).run();
         } finally {
            var7.getThreadLocal().remove();
         }
      }
   }
}
