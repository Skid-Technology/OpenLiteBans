package litebans.command;

import java.io.Closeable;
import java.sql.SQLException;
import litebans.config.Message;
import litebans.database.DatabaseManager;
import litebans.database.EntryReader;
import litebans.database.PunishmentStorage;
import litebans.lib.compress.archivers.tar.TargetParser;
import litebans.lib.kotlin.NoWhenBranchMatchedException;
import litebans.lib.kotlin.collections.ArraysKt;
import litebans.lib.kotlin.io.CloseableKt;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.platform.LiteBansPlatform;
import litebans.platform.Sender;
import litebans.punishment.PlayerHistory;
import litebans.punishment.PunishmentEntry;
import litebans.punishment.PunishmentService;
import litebans.punishment.PunishmentType;
import org.jetbrains.annotations.NotNull;

public class CheckBanCommand extends BaseCommand {
   public CheckBanCommand(@NotNull LiteBansPlatform var1) {
      super("checkban", var1);
   }

   public PunishmentType getPunishmentType() {
      return PunishmentType.BAN;
   }

   public Message getMessage() {
      return Message.CHECKBAN_BANNED;
   }

   public Message getMessage2() {
      return Message.CHECKBAN_NO_BAN;
   }

   @Override
   public void execute(@NotNull Sender var1, @NotNull String[] var2) {
      CommandRunnable var4 = this;
      String var3 = PunishmentService.lookupUuid(var4, (String)ArraysKt.getObject(var2));
      String var23 = PunishmentService.parseServerScopeDefault(this, var1, var2, null, 4, null);
      CommandRunnable var5 = this;
      DatabaseManager var24 = (DatabaseManager)var5.getPlatform().getModule(DatabaseManager.class);

      try {
         PunishmentStorage var7 = var24.openStorage();
         Closeable var8 = var7;
         Throwable var9 = null;

         try {
            if (TargetParser.INSTANCE.checkString5(var3)) {
               PunishmentEntry var14 = EntryReader.getPunishmentEntry(
                  var7, PunishmentService.resolveRandomId(this, var3), this.getPunishmentType(), null, false, 12, null
               );
               if (var14 != null) {
                  String var27 = var14.getUuid();
                  if (var27 == null) {
                     var27 = var14.getIp();
                     if (var27 == null) {
                        CommandRunnable.messageSender.getVoid(this.getMessage2());
                        throw new NoWhenBranchMatchedException();
                     }
                  }

                  String var26 = var27;
                  PlayerHistory var16 = PunishmentService.resolveTargetDefault(var7, var26, false, 2, null);
                  PunishmentService.reply(this, EntryReader.getCharSequence(var7, this.getMessage(), var14, var16, false, 4, null));
                  this.handlePunishmentStorage(var7, var14, var16);
                  return;
               }
            }

            PlayerHistory var25 = PunishmentService.resolveTargetDefault(var7, var3, false, 2, null);
            PunishmentEntry var10000 = EntryReader.getPunishmentEntry(
               var7, var25.getUuid(), var25.getIp(), this.getPunishmentType(), var23, false, false, 48, null
            );
            if (var10000 == null) {
               CommandRunnable.messageSender.getVoid(EntryReader.getCharSequence(var7, this.getMessage2(), var25, false, 2, null));
               throw new NoWhenBranchMatchedException();
            }

            PunishmentEntry var15 = var10000;
            PunishmentService.reply(this, EntryReader.getCharSequence(var7, this.getMessage(), var15, var25, false, 4, null));
            this.handlePunishmentStorage(var7, var15, var25);
         } catch (Throwable var20) {
            var9 = var20;
            throw var20;
         } finally {
            CloseableKt.handleCloseable(var8, var9);
         }
      } catch (Exception var22) {
         if (!(var22 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var22); }
         if (!var24.isConnectionError(var22)) {
            throw var22;
         }
      }
   }

   private final void handlePunishmentStorage(PunishmentStorage var1, PunishmentEntry var2, PlayerHistory var3) {
      if (this.getPunishmentType() == PunishmentType.BAN && !Intrinsics.checkObject(var2.getUuid(), var3.getUuid())) {
         String[] var4 = new String[]{"check", null};
         String var10004 = var3.getUuid();
         if (var10004 == null) {
            var10004 = var3.getString();
            if (var10004 == null) {
               return;
            }
         }

         var4[1] = var10004;
      }
   }
}
