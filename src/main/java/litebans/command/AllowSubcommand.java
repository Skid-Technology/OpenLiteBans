package litebans.command;

import java.util.Date;
import java.util.UUID;
import litebans.config.Message;
import litebans.core.LiteBansCore;
import litebans.core.MessageSender;
import litebans.database.DatabaseManager;
import litebans.database.EntryReader;
import litebans.database.PunishmentStorage;
import litebans.hook.UsernamePattern;
import litebans.lib.compress.archivers.tar.TargetParser;
import litebans.lib.kotlin.NoWhenBranchMatchedException;
import litebans.lib.kotlin.Unit;
import litebans.lib.kotlin.collections.ArraysKt;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.platform.LiteBansPlatform;
import litebans.punishment.PlayerHistory;
import litebans.punishment.Profile;
import litebans.punishment.PunishmentService;
import org.jetbrains.annotations.NotNull;

public final class AllowSubcommand extends CommandRunnable {
   private final String[] stringArray;
   private final boolean flag;

   public AllowSubcommand(@NotNull String[] var1, @NotNull LiteBansPlatform var2, boolean var3) {
      super(var2);
      this.stringArray = var1;
      this.flag = var3;
   }

   @Override
   public void run() {
      if (this.flag) {
         CommandRunnable var10000 = this;
         String[] var1 = new String[]{"litebans.admin", "litebans.allow"};
         PunishmentService.requireAnyPermission(var10000, var1);
      }

      MessageSender var8 = CommandRunnable.messageSender;
      boolean var2 = this.stringArray.length < 2;
      CharSequence var3 = Message.ALLOW_USAGE;
      if (var2) {
         var8.getVoid(var3);
         throw new NoWhenBranchMatchedException();
      } else {
         CommandRunnable var9 = this;
         ((DatabaseManager)var9.getPlatform().getModule(DatabaseManager.class)).runWithStorageAsync(st -> getUnit(this, (PunishmentStorage) st));
      }
   }

   private static final Unit getUnit(AllowSubcommand var0, PunishmentStorage var1) {
      MessageSender var2 = CommandRunnable.messageSender;
      boolean var3 = var1.isClosed();
      CharSequence var4 = Message.ERROR_NO_DATABASE;
      if (var3) {
         var2.getVoid(var4);
         throw new NoWhenBranchMatchedException();
      } else {
         String var10000 = (String)ArraysKt.getObject(var0.stringArray, 2);
         if (var10000 == null) {
            CommandRunnable.messageSender.getVoid(Message.ALLOW_USAGE);
            throw new NoWhenBranchMatchedException();
         } else {
            String var16 = var10000;
            MessageSender var17 = CommandRunnable.messageSender;
            boolean var19 = TargetParser.INSTANCE.checkString2(var16);
            CharSequence var5 = Message.ALLOW_USAGE;
            if (var19) {
               var17.getVoid(var5);
               throw new NoWhenBranchMatchedException();
            } else {
               PlayerHistory var18 = var1.findLatestHistory(var16);
               byte var20 = TargetParser.INSTANCE.getTarget(var16).getType();
               PlayerHistory var30;
               if ((var18 != null ? var18.getUuid() : null) != null) {
                  var30 = var18;
               } else if (var20 == 1) {
                  var30 = new PlayerHistory(var16, var16, null, null, 12, null);
               } else {
                  CommandRunnable var6 = var0;
                  if (((LiteBansCore)var6.getPlatform().getModule(LiteBansCore.class)).getConfig().isOnlineMode()) {
                     Profile var22 = UsernamePattern.mojangApi.getProfile2(var16);
                     UUID var10004 = var22.getUuid();
                     var30 = EntryReader.getPlayerHistory(
                        var1, new PlayerHistory(var22.getName(), var10004 != null ? var10004.toString() : null, null, new Date()), false, 2, null
                     );
                  } else {
                     if (var20 != 0) {
                        CommandRunnable.messageSender.getVoid(Message.ERROR_NO_UUID);
                        throw new NoWhenBranchMatchedException();
                     }

                     var30 = EntryReader.getPlayerHistory(var1, new PlayerHistory(var16, ArgUtils.getString(var16), null, null, 12, null), false, 2, null);
                  }
               }

               PlayerHistory var21 = var30;
               MessageSender var23 = CommandRunnable.messageSender;
               boolean var7 = var21.getUuid() == null;
               CharSequence var8 = Message.ERROR_NO_UUID;
               if (var7) {
                  var23.getVoid(var8);
                  throw new NoWhenBranchMatchedException();
               } else {
                  var10000 = var21.getUuid();
                  Intrinsics.handleObject(var10000);
                  UUID var24 = UUID.fromString(var10000);
                  Intrinsics.handleObject(var24);
                  var7 = EntryReader.checkPunishmentStorage(var1, var24, null, 2, null);
                  String var26 = var0.stringArray[1];
                  switch (var26.hashCode()) {
                     case -934610812:
                        if (var26.equals("remove")) {
                           MessageSender var28 = CommandRunnable.messageSender;
                           CharSequence var29 = EntryReader.getCharSequence(var1, Message.ALLOW_ERROR_REMOVE, var21, false, 2, null);
                           boolean var13 = !var7;
                           if (var13) {
                              var28.getVoid(var29);
                              throw new NoWhenBranchMatchedException();
                           }

                           var1.deleteSyncRecords(var24);
                           PunishmentService.reply(var0, EntryReader.getCharSequence(var1, Message.ALLOW_REMOVED, var21, false, 2, null));
                        }
                        break;
                     case 96417:
                        if (var26.equals("add")) {
                           MessageSender var27 = CommandRunnable.messageSender;
                           CharSequence var10 = EntryReader.getCharSequence(var1, Message.ALLOW_ERROR_ADD, var21, false, 2, null);
                           if (var7) {
                              var27.getVoid(var10);
                              throw new NoWhenBranchMatchedException();
                           }

                           EntryReader.getResultSet(var1, var24, 0, 2, null);
                           PunishmentService.reply(var0, EntryReader.getCharSequence(var1, Message.ALLOW_ADDED, var21, false, 2, null));
                        }
                        break;
                     case 94627080:
                        if (var26.equals("check")) {
                           Message var9 = Message.getMessage2("ALLOW_CHECK_" + var7);
                           CommandRunnable var32 = var0;
                           Intrinsics.handleObject(var9);
                           PunishmentService.reply(var32, EntryReader.getCharSequence(var1, var9, var21, false, 2, null));
                        }
                  }

                  return Unit.INSTANCE;
               }
            }
         }
      }
   }
}
