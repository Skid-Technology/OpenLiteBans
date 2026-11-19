package litebans.punishment;

import java.util.UUID;
import litebans.command.ArgUtils;
import litebans.command.CommandRunnable;
import litebans.core.LiteBansCore;
import litebans.database.DatabaseManager;
import litebans.database.EntryReader;
import litebans.database.PunishmentStorage;
import litebans.lib.kotlin.Unit;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.platform.LiteBansPlatform;
import litebans.platform.Sender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class MuteSyncTask extends CommandRunnable {
   private final String string;
   private final String string2;
   private final String string3;
   private final String string4;

   public MuteSyncTask(@NotNull LiteBansPlatform var1, @Nullable String var2, @NotNull String var3, @Nullable String var4, @Nullable String var5) {
      super(var1);
      this.string = var2;
      this.string2 = var3;
      this.string3 = var4;
      this.string4 = var5;
   }

   @Override
   public void run() {
      CommandRunnable var1 = this;
      if (((LiteBansCore)var1.getPlatform().getModule(LiteBansCore.class)).getConfig().isMutesEnabled()) {
         var1 = this;
         ((DatabaseManager)var1.getPlatform().getModule(DatabaseManager.class)).runWithStorageAsync(st -> getUnit(this, (PunishmentStorage) st));
      }
   }

   private static final Unit getUnit(MuteSyncTask var0, PunishmentStorage var1) {
      String var10000 = var0.string4;
      if (var0.string4 == null) {
         CommandRunnable var6 = var0;
         String var7 = var0.string2;
         Object[] var9 = var6.getPlatform().getOnlineSenders();
         int var10 = 0;
         int var11 = var9.length;

         Sender var14 = null;
         while (true) {
            if (var10 >= var11) {
               var14 = null;
               break;
            }

            Object var12 = var9[var10];
            Sender var13 = (Sender)var12;
            if (Intrinsics.checkObject(var13.getUuidString(), var7)) {
               var14 = var13;
               break;
            }

            var10++;
         }

         var10000 = var14 != null ? var14.getServerName() : null;
         if (var10000 == null) {
            CommandRunnable var5 = var0;
            var10000 = ((LiteBansCore)var5.getPlatform().getModule(LiteBansCore.class)).getConfig().getString2();
         }
      }

      String var2 = ArgUtils.getString(var10000, 32);
      PunishmentEntry var3 = EntryReader.getPunishmentEntry(var1, var0.string2, var0.string3, PunishmentType.MUTE, var2, false, false, 48, null);
      if (var3 == null && var0.string != null) {
         CommandRunnable var4 = var0;
         if (((LiteBansCore)var4.getPlatform().getModule(LiteBansCore.class)).getConfig().isBanNames()) {
            var3 = var1.findNameBan(var0.string, var0.string2, var2, PunishmentType.MUTE);
         }
      }

      CommandRunnable var16 = var0;
      LiteBansCore var17 = (LiteBansCore)var16.getPlatform().getModule(LiteBansCore.class);
      byte var20 = 3;
      if (var17.isDebugLevel(var20)) {
         var17.debug("Active mute for " + var0.string2 + " on server " + var2 + ": " + var3);
      }

      if (var3 != null) {
         var16 = var0;
         ((MuteManager)var16.getPlatform().getModule(MuteManager.class)).cacheMute(var0.string2, var3);
      } else {
         var16 = var0;
         ((MuteManager)var16.getPlatform().getModule(MuteManager.class)).getMuteChatHandler().getConcurrentHashMap().remove(UUID.fromString(var0.string2));
      }

      return Unit.INSTANCE;
   }
}
