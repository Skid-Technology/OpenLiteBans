package litebans.platform.bungee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.UUID;
import litebans.command.CommandRunnable;
import litebans.core.LiteBansCore;
import litebans.core.NotificationManager;
import litebans.database.DatabaseManager;
import litebans.database.EntryReader;
import litebans.database.PunishmentStorage;
import litebans.hook.UsernamePattern;
import litebans.importer.Importer;
import litebans.lib.kotlin.io.AutoCloseableKt;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.text.StringsKt;
import litebans.platform.LiteBansPlatform;
import litebans.punishment.PunishmentEntry;
import litebans.punishment.PunishmentType;
import litebans.punishment.ServerScope;
import litebans.sync.QueueFeederTask;
import org.jetbrains.annotations.NotNull;

public final class BungeeAdminToolsImporter extends Importer {
   public BungeeAdminToolsImporter(@NotNull LiteBansPlatform var1) {
      super(var1, "BungeeAdminTools", "BAT_", 0, 0L, 24, null);
   }

   @Override
   public boolean checkString(@NotNull String var1) {
      return super.checkString(var1) || StringsKt.checkString(var1, "BAT", true);
   }

   @Override
   public void handleString(@NotNull String var1, @NotNull Connection var2, @NotNull PunishmentStorage var3, boolean var4) {
      try {
      String var10006;
      PreparedStatement var5 = var2.prepareStatement("SELECT * FROM " + this.getString2() + "ban WHERE ban_state=1");
      AutoCloseable var6 = var5;
      Throwable var7 = null;

      try {
         ResultSet var10 = var5.executeQuery();
         AutoCloseable var11 = var10;
         Throwable var12 = null;

         try {
            while (var10.next()) {
               try {
                  String var15 = var10.getString("UUID");
                  String var141 = null;
                  String var17 = null;
                  String var18 = var10.getString("ban_ip");
                  boolean var19 = var18 != null && var18.length() > 0;
                  if (var15 != null) {
                     var141 = UsernamePattern.mojangApi.getString3(var15);

                     try {
                        PreparedStatement var20 = var2.prepareStatement(
                           "SELECT " + this.getString2() + "player,lastip FROM " + this.getString2() + "players WHERE UUID=? ORDER BY lastlogin DESC LIMIT 1"
                        );
                        AutoCloseable var143 = var20;
                        Throwable var22 = null;

                        try {
                           var20.setString(1, var15);
                           ResultSet var25 = var20.executeQuery();
                           AutoCloseable var26 = var25;
                           Throwable var27 = null;

                           try {
                              if (var25.next()) {
                                 var17 = var25.getString(this.getString2() + "player");
                                 var18 = var25.getString("lastip");
                              }
                           } catch (Throwable var131) {
                              var27 = var131;
                              throw var131;
                           } finally {
                              AutoCloseableKt.handleAutoCloseable(var26, var27);
                           }
                        } catch (Throwable var133) {
                           var22 = var133;
                           throw var133;
                        } finally {
                           AutoCloseableKt.handleAutoCloseable(var143, var22);
                        }
                     } catch (Exception var135) {
                        CommandRunnable var21 = this;
                        ((DatabaseManager)var21.getPlatform().getModule(DatabaseManager.class)).reportError(var135);
                     }
                  }

                  if (var17 == null && var141 != null) {
                     var17 = this.getString(UUID.fromString(var141));
                  }

                  String var142 = var10.getString("ban_staff");
                  String var144 = null;
                  if (var142 != null) {
                     try {
                        PreparedStatement var145 = var2.prepareStatement(
                           "SELECT UUID FROM " + this.getString2() + "players WHERE " + this.getString2() + "player=? ORDER BY lastlogin DESC LIMIT 1"
                        );
                        AutoCloseable var147 = var145;
                        Throwable var24 = null;

                        try {
                           var145.setString(1, var142);
                           ResultSet var152 = var145.executeQuery();
                           AutoCloseable var28 = var152;
                           Throwable var29 = null;

                           try {
                              if (var152.next()) {
                                 var144 = UsernamePattern.mojangApi.getString3(var152.getString("UUID"));
                              }
                           } catch (Throwable var126) {
                              var29 = var126;
                              throw var126;
                           } finally {
                              AutoCloseableKt.handleAutoCloseable(var28, var29);
                           }
                        } catch (Throwable var128) {
                           var24 = var128;
                           throw var128;
                        } finally {
                           AutoCloseableKt.handleAutoCloseable(var147, var24);
                        }
                     } catch (Exception var130) {
                        CommandRunnable var23 = this;
                        ((DatabaseManager)var23.getPlatform().getModule(DatabaseManager.class)).reportError(var130);
                     }
                  }

                  String var10000 = var10.getString("ban_reason");
                  if (var10000 == null) {
                     var10000 = "";
                  }

                  String var146 = var10000;
                  CommandRunnable var149 = this;
                  NotificationManager var156 = (NotificationManager)var149.getPlatform().getModule(NotificationManager.class);
                  Intrinsics.handleObject(var10);
                  var149 = this;
                  Date var148 = var156.readDate(
                     var10, "ban_begin", false, ((LiteBansCore)var149.getPlatform().getModule(LiteBansCore.class)).getConfig().getSqlDriver()
                  );
                  long var32 = var148.getTime();
                  CommandRunnable var153 = this;
                  NotificationManager var157 = (NotificationManager)var153.getPlatform().getModule(NotificationManager.class);
                  var153 = this;
                  Date var151 = var157.readDate(
                     var10, "ban_end", false, ((LiteBansCore)var153.getPlatform().getModule(LiteBansCore.class)).getConfig().getSqlDriver()
                  );
                  long var34 = var151.getTime();
                  if (var10.getTimestamp("ban_end") == null) {
                     var34 = -1L;
                  }

                  if (!var19 && EntryReader.getPunishmentEntry(var3, var141, null, null, false, false, 30, null) != null) {
                     this.getLogger().warning("Ignoring duplicate ban for " + var141);
                  } else {
                     CharSequence var10005 = var146;
                     var10006 = var144;
                     if (var144 == null) {
                        var10006 = "#";
                     }

                     PunishmentEntry var158 = new PunishmentEntry( PunishmentType.BAN, var141, var18, var10005, var10006, var142, ServerScope.serverScopeParser.getString(), null, var32, var34, (byte)0, false, var19, false, 0L, 27648, null );

                     PunishmentEntry var155 = var158;
                     var3.insertEntry(var155);
                     Long.valueOf(var19 ? this.getAtomicLong2().incrementAndGet() : this.getAtomicLong().incrementAndGet());
                  }

                  if (var17 != null && var141 != null) {
                     new QueueFeederTask(this.getPlatform(), var17, var141, "#").run();
                  }
               } catch (Exception var136) {
                  CommandRunnable var16 = this;
                  ((DatabaseManager)var16.getPlatform().getModule(DatabaseManager.class)).reportError(var136);
               }
            }
         } catch (Throwable var137) {
            var12 = var137;
            throw var137;
         } finally {
            AutoCloseableKt.handleAutoCloseable(var11, var12);
         }
      } catch (Throwable var139) {
         var7 = var139;
         throw var139;
      } finally {
         AutoCloseableKt.handleAutoCloseable(var6, var7);
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }
}
