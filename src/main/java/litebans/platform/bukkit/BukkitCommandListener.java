package litebans.platform.bukkit;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import litebans.command.ArgUtils;
import litebans.command.MuteChatHandler;
import litebans.config.Config;
import litebans.core.LiteBansCore;
import litebans.database.DatabaseManager;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.text.Regex;
import litebans.lib.kotlin.text.StringsKt;
import litebans.platform.ListenerHandle;
import litebans.platform.LiteBansPlatform;
import litebans.platform.PlatformBridge;
import litebans.platform.Sender;
import litebans.punishment.MuteManager;
import litebans.punishment.PunishmentEntry;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.jetbrains.annotations.NotNull;

public final class BukkitCommandListener implements BukkitListener {
   private final LiteBansPlatform liteBansPlatform;
   private final LiteBansCore liteBansCore;
   private final MuteManager muteManager;
   private final DatabaseManager databaseManager;

   public BukkitCommandListener(@NotNull LiteBansPlatform var1) {
      this.liteBansPlatform = var1;
      this.liteBansCore = (LiteBansCore)this.liteBansPlatform.getModule(LiteBansCore.class);
      this.muteManager = (MuteManager)this.liteBansPlatform.getModule(MuteManager.class);
      this.databaseManager = (DatabaseManager)this.liteBansPlatform.getModule(DatabaseManager.class);
   }

   public final LiteBansPlatform getLiteBansPlatform() {
      return this.liteBansPlatform;
   }

   public final LiteBansCore getLiteBansCore() {
      return this.liteBansCore;
   }

   public final MuteManager getMuteManager() {
      return this.muteManager;
   }

   public final DatabaseManager getDatabaseManager() {
      return this.databaseManager;
   }

   @EventHandler(
      priority = EventPriority.LOW,
      ignoreCancelled = true
   )
   public final void handleAsyncPlayerChatEvent(@NotNull AsyncPlayerChatEvent var1) {
      try {
         Sender var2 = this.liteBansPlatform.getSender(var1.getPlayer());
         LiteBansCore var10000 = this.liteBansCore;
         Intrinsics.handleObject(var2);
         String var7 = var10000.getSenderIp(var2, var1);
         PunishmentEntry var4 = this.muteManager.getActiveMute(var2.getUuid(), var7, this.databaseManager.getString());
         if (var4 != null) {
            this.handleObject(var1, var2, var4);
            return;
         }
      } catch (NullPointerException var6) {
         LiteBansPlatform var3 = this.liteBansPlatform;
         if (var3.isEnabled() && !var3.isDisabling()) {
            throw var6;
         }

         LiteBansCore.handleObjectDefault(this.liteBansCore, var6, 0, 2, null);
      }
   }

   @EventHandler(
      priority = EventPriority.LOW,
      ignoreCancelled = true
   )
   public final void handlePlayerCommandPreprocessEvent(@NotNull PlayerCommandPreprocessEvent var1) {
      Sender var2 = this.liteBansPlatform.getSender(var1.getPlayer());
      LiteBansCore var10000 = this.liteBansCore;
      Intrinsics.handleObject(var2);
      String var3 = var10000.getSenderIp(var2, var1);
      PunishmentEntry var4 = this.muteManager.getActiveMute(var2.getUuid(), var3, this.databaseManager.getString());
      if (var4 != null) {
         CharSequence var17 = var1.getMessage();
         char[] var6 = new char[]{' '};
         String var5 = (String)StringsKt.getList(var17, var6, false, 0, 6, null).get(0);
         Config var14 = this.liteBansCore.getConfig();
         String var7 = var1.getMessage();
         if (ArgUtils.checkCharSequence(var7)) {
            var7 = ArgUtils.getString(var7);
         }

         if (StringsKt.checkCharSequence3(var5, ':', false, 2, null) && var14.isMutesPreventBypass()) {
            CharSequence var8 = var7;
            Regex var9 = new Regex("^/[^ ]+:");
            String var10 = "/";
            var7 = var9.getString2(var8, var10);
         }

         List var15 = var14.getMutesCommandBlacklist();
         Iterable var16 = var15;
         boolean var18;
         if (var16 instanceof Collection && ((Collection)var16).isEmpty()) {
            var18 = false;
         } else {
            Iterator var11 = var16.iterator();

            while (true) {
               if (!var11.hasNext()) {
                  var18 = false;
                  break;
               }

               Object var12 = var11.next();
               String var13 = (String)var12;
               if (StringsKt.checkString2(var7, var13, true)) {
                  var18 = true;
                  break;
               }
            }
         }

         if (var18 || var14.getSet().contains(var7)) {
            this.handleObject(var1, var2, var4);
            return;
         }
      }
   }

   private final void handleObject(Object var1, Sender var2, PunishmentEntry var3) {
      MuteChatHandler var10000 = this.muteManager.getMuteChatHandler();
      PlatformBridge var10003 = this.liteBansPlatform.createBridge();
      Intrinsics.handleObject(var10003, "");
      var10000.handleObject(var1, var2, ((BukkitBridge)var10003).getEventMessage(var1), var3);
   }

   public static void handleObjectDefault(BukkitCommandListener var0, Object var1, Sender var2, PunishmentEntry var3, int var4, Object var5) {
      if ((var4 & 4) != 0) {
         var3 = null;
      }

      var0.handleObject(var1, var2, var3);
   }

   public BukkitCommandListener getBukkitCommandListener() {
      BukkitCommandListener var2 = this;
      LiteBansPlatform var10000 = var2.liteBansPlatform;
      ListenerHandle[] var4 = new ListenerHandle[]{var2};
      var10000.registerListeners(var4);
      return this;
   }

   @Override
   public ListenerHandle getListenerHandle() {
      return this.getBukkitCommandListener();
   }
}
