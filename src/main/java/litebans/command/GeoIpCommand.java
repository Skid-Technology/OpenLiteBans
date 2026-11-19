package litebans.command;

import java.io.Closeable;
import java.sql.SQLException;
import litebans.config.Message;
import litebans.core.LiteBansCore;
import litebans.core.MessageSender;
import litebans.database.DatabaseManager;
import litebans.database.EntryReader;
import litebans.database.PunishmentStorage;
import litebans.lib.compress.archivers.tar.TargetParser;
import litebans.lib.kotlin.NoWhenBranchMatchedException;
import litebans.lib.kotlin.collections.ArraysKt;
import litebans.lib.kotlin.io.CloseableKt;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.text.StringsKt;
import litebans.platform.LiteBansPlatform;
import litebans.platform.Sender;
import litebans.punishment.PlayerHistory;
import litebans.punishment.PunishmentService;
import org.jetbrains.annotations.NotNull;

public final class GeoIpCommand extends BaseCommand {
   public GeoIpCommand(@NotNull LiteBansPlatform var1) {
      super("geoip", var1);
   }

   @Override
   public void execute(@NotNull Sender var1, @NotNull String[] var2) {
      MessageSender var3 = CommandRunnable.messageSender;
      CommandRunnable var4 = this;
      boolean var36 = ((LiteBansCore)var4.getPlatform().getModule(LiteBansCore.class)).getConfig().isGeoipEnable();
      CharSequence var5 = Message.GEOIP_DISABLED;
      boolean var8 = !var36;
      if (var8) {
         var3.getVoid(var5);
         throw new NoWhenBranchMatchedException();
      } else {
         var4 = this;
         String var35 = PunishmentService.lookupUuid(var4, (String)ArraysKt.getObject(var2));
         Sender var38 = PunishmentService.findOnline(this, var35);
         String var39 = null;
         Object var6 = Message.GEOIP_MESSAGE;
         if (var38 != null) {
            var35 = var38.getName();
            var39 = var38.getIp();
            CommandRunnable var42 = this;
            DatabaseManager var43 = (DatabaseManager)var42.getPlatform().getModule(DatabaseManager.class);

            try {
               PunishmentStorage var48 = var43.openStorage();
               Closeable var49 = var48;
               Throwable var50 = null;

               try {
                  var6 = var48.getCharSequence2((CharSequence)var6, var38);
               } catch (Throwable var29) {
                  var50 = var29;
                  throw var29;
               } finally {
                  CloseableKt.handleCloseable(var49, var50);
               }
            } catch (Exception var31) {
               if (!(var31 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var31); }
               if (!var43.isConnectionError(var31)) {
                  throw var31;
               }
            }
         } else if (TargetParser.INSTANCE.checkString2(var35)) {
            var39 = var35;
         } else {
            CommandRunnable var7 = this;
            DatabaseManager var41 = (DatabaseManager)var7.getPlatform().getModule(DatabaseManager.class);

            try {
               PunishmentStorage var9 = var41.openStorage();
               Closeable var10 = var9;
               Throwable var11 = null;

               try {
                  label179: {
                     PlayerHistory var16 = var9.findLatestHistory(var35);
                     if (var16 != null) {
                        String var10000 = var16.getIp();
                        if (var10000 != null ? !StringsKt.checkCharSequence(var10000, '#', false, 2, null) : true) {
                           var39 = var16.getIp();
                           var10000 = var16.getString();
                           Intrinsics.handleObject(var10000);
                           var35 = var10000;
                           var6 = EntryReader.getCharSequence(var9, (CharSequence)var6, var16, false, 2, null);
                           break label179;
                        }
                     }

                     throw new SilentAbortException();
                  }
               } catch (Throwable var32) {
                  var11 = var32;
                  throw var32;
               } finally {
                  CloseableKt.handleCloseable(var10, var11);
               }
            } catch (Exception var34) {
               if (!(var34 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var34); }
               if (!var41.isConnectionError(var34)) {
                  throw var34;
               }
            }
         }

         if (var39 == null) {
            throw new SilentAbortException();
         } else {
            CommandRunnable var45 = this;
            String var44 = ((LiteBansCore)var45.getPlatform().getModule(LiteBansCore.class)).getCountry(var39);
            CharSequence var52 = Intrinsics.checkObject(var44, "geoip.unavailable")
               ? Message.GEOIP_UNAVAILABLE
               : (Intrinsics.checkObject(var44, "geoip.error") ? Message.GEOIP_NONE_FOUND : (CharSequence)var6);
            CharSequence[] var46 = new CharSequence[]{"target", var35};
            var52 = ArgUtils.getString(var52, var46);
            CharSequence[] var47 = new CharSequence[]{"result", var44};
            var6 = ArgUtils.getString(var52, var47);
            CommandRunnable.messageSender.getVoid(var6);
            throw new NoWhenBranchMatchedException();
         }
      }
   }
}
