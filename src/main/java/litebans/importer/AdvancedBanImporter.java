package litebans.importer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Locale;
import litebans.command.CommandRunnable;
import litebans.core.LiteBansCore;
import litebans.database.DatabaseManager;
import litebans.database.EntryReader;
import litebans.database.PunishmentStorage;
import litebans.hook.UsernamePattern;
import litebans.lib.compress.archivers.tar.TargetParser;
import litebans.lib.kotlin.io.AutoCloseableKt;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.text.StringsKt;
import litebans.platform.LiteBansPlatform;
import litebans.punishment.PunishmentEntry;
import litebans.punishment.PunishmentType;
import litebans.punishment.ServerScope;
import litebans.sync.QueueFeederTask;
import org.jetbrains.annotations.NotNull;

public final class AdvancedBanImporter extends Importer {
   public AdvancedBanImporter(@NotNull LiteBansPlatform var1) {
      super(var1, "AdvancedBan", null, 0, 0L, 28, null);
   }

   @Override
   public void handleString(@NotNull String var1, @NotNull Connection var2, @NotNull PunishmentStorage var3, boolean var4) {
      this.handleString("ban", PunishmentType.BAN, var2, var3);
      this.handleString("mute", PunishmentType.MUTE, var2, var3);
   }

   private final void handleString(String var1, PunishmentType var2, Connection var3, PunishmentStorage var4) {
      try {
         String var5 = StringsKt.getString(var1, Locale.ENGLISH);
         String var8 = "WHERE punishmentType='"
            + var5
            + "' OR punishmentType='Temp"
            + var1
            + "' OR punishmentType='TEMP_"
            + var5
            + "' OR punishmentType='Ip"
            + var1
            + "' OR punishmentType='IP_"
            + var5
            + "' OR punishmentType='Tempip"
            + var1
            + "' OR punishmentType='TEMP_IP_"
            + var5
            + '\'';
         handleConnectionDefault(this, var3, "SELECT * FROM Punishments " + var8, var1, var4, var2, false, 32, null);
         this.handleConnection(var3, "SELECT * FROM PunishmentHistory " + var8, var1, var4, var2, false);
      } catch (Exception var7) {
         CommandRunnable var6 = this;
         ((DatabaseManager)var6.getPlatform().getModule(DatabaseManager.class)).reportError(var7);
      }
   }

   private final void handleConnection(Connection var1, String var2, String var3, PunishmentStorage var4, PunishmentType var5, boolean var6) {
      try {
      PreparedStatement var7 = var1.prepareStatement(var2);
      AutoCloseable var8 = var7;
      Throwable var9 = null;

      try {
         ResultSet var12 = var7.executeQuery();
         AutoCloseable var13 = var12;
         Throwable var14 = null;

         try {
            while (var12.next()) {
               String var17 = var12.getString("name");
               String var18 = UsernamePattern.mojangApi.getString3(var12.getString("uuid"));
               String var19 = var12.getString("operator");
               String var20 = var12.getString("reason");
               String var21 = var12.getString("punishmentType");
               Intrinsics.handleObject(var21);
               String var23 = var21.toLowerCase(Locale.ENGLISH);
               String var22 = null;
               boolean var24 = StringsKt.checkCharSequence3(var23, "ip" + var3, false, 2, null);
               if (TargetParser.INSTANCE.checkString2(var18) && !var24) {
                  this.getLogger().warning("IP was stored in UUID column for " + var21 + '!');
                  var24 = true;
               }

               if (var24) {
                  var22 = var18;
                  var18 = this.getString(var17);
               }

               if (!TargetParser.checkString7(var18)) {
                  this.getLogger().warning("Username ('" + var18 + "') was stored in UUID column for " + var21 + '!');
                  var18 = this.getString(var18);
               }

               if (!TargetParser.checkString7(var18)) {
                  this.getLogger().warning("Failed to import " + var21 + " for " + var18);
                  var18 = null;
               }

               long var27 = var12.getLong("start");
               long var29 = Math.max(var12.getLong("end"), 0L);
               CommandRunnable var31 = this;
               String var33 = ((LiteBansCore)var31.getPlatform().getModule(LiteBansCore.class)).getConfig().getString2();
               CommandRunnable var32 = this;
               String var46 = ServerScope.serverScopeParser
                  .getString(
                     ((LiteBansCore)var32.getPlatform().getModule(LiteBansCore.class)).getConfig().getDefaultServerScope(),
                     var33,
                     this.getPlatform().getConsole()
                  );
               if (var6 && EntryReader.getPunishmentEntry(var4, var18, null, null, false, false, 30, null) != null) {
                  this.getLogger().warning("Ignoring duplicate " + var3 + " for " + var18);
               } else {
                  Intrinsics.handleObject(var20);
                  PunishmentEntry var47 = new PunishmentEntry(
                     var5, var18, var22, var20, this.getString(var19), var19, var46, var33, var27, var29, (byte)0, false, var24, var6, 0L, 19456, null
                  );
                  var4.insertEntry(var47);
                  if (var5 == PunishmentType.BAN) {
                     this.getAtomicLong().incrementAndGet();
                  }
               }

               if (var18 != null) {
                  LiteBansPlatform var10002 = this.getPlatform();
                  Intrinsics.handleObject(var17);
                  new QueueFeederTask(var10002, var17, var18, var22).run();
               }
            }
         } catch (Throwable var42) {
            var14 = var42;
            throw var42;
         } finally {
            AutoCloseableKt.handleAutoCloseable(var13, var14);
         }
      } catch (Throwable var44) {
         var9 = var44;
         throw var44;
      } finally {
         AutoCloseableKt.handleAutoCloseable(var8, var9);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   public static void handleConnectionDefault(
      AdvancedBanImporter var0, Connection var1, String var2, String var3, PunishmentStorage var4, PunishmentType var5, boolean var6, int var7, Object var8
   ) {
      if ((var7 & 32) != 0) {
         var6 = true;
      }

      var0.handleConnection(var1, var2, var3, var4, var5, var6);
   }
}
