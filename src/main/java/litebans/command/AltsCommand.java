package litebans.command;

import java.io.Closeable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import litebans.config.Config;
import litebans.config.Message;
import litebans.core.LiteBansCore;
import litebans.core.NotificationManager;
import litebans.database.DatabaseManager;
import litebans.database.EntryReader;
import litebans.database.PunishmentStorage;
import litebans.lib.compress.archivers.tar.TargetParser;
import litebans.lib.kotlin.NoWhenBranchMatchedException;
import litebans.lib.kotlin.Unit;
import litebans.lib.kotlin.io.CloseableKt;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.platform.LiteBansPlatform;
import litebans.punishment.DupeIPResult;
import litebans.punishment.DupeIpLookup;
import litebans.punishment.Flag;
import litebans.punishment.PlayerHistory;
import litebans.punishment.PunishmentEntry;
import litebans.punishment.PunishmentService;
import litebans.util.Unreachable;
import litebans.webhook.DiscordWebhookSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class AltsCommand extends CommandRunnable {
   private final String string;
   private final String string2;
   private final String string3;

   public AltsCommand(@NotNull LiteBansPlatform var1, @NotNull String var2, @NotNull String var3, @Nullable String var4) {
      super(var1);
      this.string = var2;
      this.string2 = var3;
      this.string3 = var4;
   }

   public final String getString() {
      return this.string;
   }

   public final String getString2() {
      return this.string2;
   }

   public final String getString3() {
      return this.string3;
   }

   @Override
   public void run() {
      CommandRunnable var1 = this;
      DatabaseManager var35 = (DatabaseManager)var1.getPlatform().getModule(DatabaseManager.class);

      try {
         PunishmentStorage var3 = var35.openStorage();
         Closeable var4 = var3;
         Throwable var5 = null;

         try {
            CommandRunnable var10 = this;
            Config var14 = ((LiteBansCore)var10.getPlatform().getModule(LiteBansCore.class)).getConfig();
            DupeIpLookup var36 = new DupeIpLookup(this.string2, var3, null, false, 0, 28, null).getDupeIpLookup();
            AltsCommand var11 = this;
            PunishmentStorage var12 = var3;
            CommandRunnable var15 = this;
            Config var19 = ((LiteBansCore)var15.getPlatform().getModule(LiteBansCore.class)).getConfig();
            UUID var10000 = TargetParser.INSTANCE.getUUID(this.getString2());
            if (var10000 != null) {
               UUID var39 = var10000;
               if (var19.getBanAlts() > 1
                  && var3.getDatabaseManager().isFlag3()
                  && var36.isFlag3()
                  && !EntryReader.checkPunishmentStorage(var3, var39, null, 2, null)) {
                  Iterable var20 = var36.getArrayList();
                  Iterator var21 = var20.iterator();
                  DupeIPResult var57 = null;

                  while (true) {
                     if (!var21.hasNext()) {
                        var57 = null;
                        break;
                     }

                     Object var22 = var21.next();
                     DupeIPResult var23 = (DupeIPResult)var22;
                     if (var23.getBan() != null && (var23.getBan().isIpban() || var19.getBanAlts() > 2)) {
                        var57 = var23;
                        break;
                     }
                  }

                  if (var57 != null) {
                     DupeIPResult var16 = var57;
                     PunishmentEntry var10002 = var16.getBan();
                     Intrinsics.handleObject(var10002);
                     PunishmentService.banLinkedAccountDefault(
                        var3, var16, var10002, this.getString(), this.getString3(), this.getString2(), this.getString3(), false, 64, null
                     );
                     if (var19.getBanAlts() > 2) {
                        Iterable var18 = var36.getArrayList();
                        Collection var48 = new ArrayList();

                        for (Object var25 : var18) {
                           DupeIPResult var26 = (DupeIPResult)var25;
                           if (!Intrinsics.checkObject(var26.getUuid(), var11.getString2()) && var26.getBan() == null) {
                              var48.add(var25);
                           }
                        }

                        var18 = (List)var48;
                        var48 = new ArrayList();

                        for (Object var54 : var18) {
                           DupeIPResult var55 = (DupeIPResult)var54;
                           var10000 = TargetParser.INSTANCE.getUUID(var55.getUuid());
                           boolean var59;
                           if (var10000 == null) {
                              var59 = false;
                           } else {
                              UUID var28 = var10000;
                              var59 = !EntryReader.checkPunishmentStorage(var12, var28, null, 2, null);
                           }

                           if (var59) {
                              var48.add(var54);
                           }
                        }

                        List var17 = (List)var48;

                        for (Object var50 : var17) {
                           DupeIPResult var52 = (DupeIPResult)var50;
                           PunishmentService.banLinkedAccount(
                              var12, var52, var16.getBan(), var52.getName(), var11.getString3(), var52.getUuid(), var11.getString3(), true
                           );
                        }
                     }
                  }
               }
            }

            if (!var36.isFlag2()
               && (
                  var36.isFlag3() && var14.isNotifyDupeipOnBannedAccount()
                     || var36.isFlag4() && var14.isNotifyDupeipOnMutedAccount()
                     || var36.getIntValue3() >= var14.getNotifyDupeipOnJoinThreshold()
               )) {
               PlayerHistory var60 = var36.getPlayerHistory();
               if (var60 == null) {
                  Unreachable.getVoid();
                  throw new NoWhenBranchMatchedException();
               }

               PlayerHistory var37 = var60;
               CharSequence var38 = this.getCharSequence(var3, var37, var36);
               CommandRunnable var13 = this;
               NotificationManager var40 = (NotificationManager)var13.getPlatform().getModule(NotificationManager.class);
               NotificationManager.broadcastDefault(var40, var3, var38, Flag.NOTIFY_DUPEIP_JOIN, null, null, false, false, null, 124, null);
               if (var14.isSyncDupeipNotifications() && var3.getDatabaseManager().isSyncEnabled()) {
                  var3.getDatabaseManager().runWithStorageAsync(st -> getUnit(var38, (PunishmentStorage) st));
               }

               CommandRunnable var43 = var36;
               String var47 = ((LiteBansCore)var43.getPlatform().getModule(LiteBansCore.class)).getWebhookManager().getString("alts");
               if (var47.length() > 0) {
                  String var44 = var3.getCharSequence(Message.WEB_ALTS_THUMB, var36.getPlayerHistory(), false).toString();
                  CharSequence var45 = var3.getCharSequence(ArgUtils.getCharSequence(var36.getString3(), Message.DUPEIP_END), var36.getPlayerHistory(), false);
                  CharSequence var51 = var3.getCharSequence(Message.WEB_ALTS_TITLE, var36.getPlayerHistory(), false);
                  DiscordWebhookSender.webhookEmbedBuilder
                     .handlePunishmentStorage(
                        var3,
                        new DiscordWebhookSender(var47),
                        var51,
                        var45,
                        DiscordWebhookSender.webhookEmbedBuilder.getInt(var3, Message.WEB_ALTS_COLOR),
                        var44,
                        null,
                        false
                     );
               }
            }
         } catch (Throwable var32) {
            var5 = var32;
            throw var32;
         } finally {
            CloseableKt.handleCloseable(var4, var5);
         }
      } catch (Exception var34) {
         if (!(var34 instanceof SQLException) || !var35.isConnectionError(var34)) {
            throw litebans.util.Unreachable.rethrow(var34);
         }
      }
   }

   public final CharSequence getCharSequence(@NotNull PunishmentStorage var1, @NotNull PlayerHistory var2, @NotNull DupeIpLookup var3) {
      CharSequence var4 = var3.isFlag5() ? Message.DUPEIP_MULTIPLE_ADDRESSES.getString("num", var3.getIntValue2()) : this.string3;
      return ArgUtils.getCharSequence(
         ArgUtils.getCharSequence(EntryReader.getCharSequence(var1, Message.DUPEIP_START_NO_IP.getString("ip", var4), var2, false, 2, null), var3.getString3()),
         Message.DUPEIP_END
      );
   }

   private static final Unit getUnit(CharSequence var0, PunishmentStorage var1) {
      PunishmentService.broadcastDefault(var1, Flag.NOTIFY_DUPEIP_JOIN, var0, null, null, 12, null);
      return Unit.INSTANCE;
   }
}
