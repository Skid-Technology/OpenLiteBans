package litebans.punishment;

import java.io.Closeable;
import java.sql.SQLException;
import java.util.Locale;
import java.util.UUID;
import litebans.chat.ChatColorCode;
import litebans.command.ArgUtils;
import litebans.command.CommandRunnable;
import litebans.command.OperationCancelledException;
import litebans.config.Message;
import litebans.core.LiteBansCore;
import litebans.core.NotificationManager;
import litebans.database.DatabaseManager;
import litebans.database.EntryReader;
import litebans.database.PunishmentStorage;
import litebans.geoip.GeoIp;
import litebans.lib.compress.archivers.tar.TargetParser;
import litebans.lib.kotlin.NoWhenBranchMatchedException;
import litebans.lib.kotlin.UByte;
import litebans.lib.kotlin.Unit;
import litebans.lib.kotlin.io.CloseableKt;
import litebans.lib.kotlin.jvm.internal.Function1;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.platform.LiteBansPlatform;
import litebans.platform.PlatformBridge;
import litebans.util.Unreachable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class LoginCheck extends CommandRunnable {
   public LoginCheck(@NotNull LiteBansPlatform var1) {
      super(var1);
   }

   public void handleObject(@NotNull Object var1) {
      try {
         CommandRunnable var40 = this;
         LiteBansCore var2 = ((LiteBansCore)var40.getPlatform().getModule(LiteBansCore.class)).requireEnabled();
         PlayerInfo var41 = this.getPlayerInfo(var1);
         String var4 = var41.getString2();
         String var5 = var41.getUuid2();
         String var6 = var41.getIp2();
         byte var7 = var2.getConfig().getUsernameMaxLength();
         String var8 = this.getString(var1);
         boolean var9 = false;
         boolean var10 = false;
         CommandRunnable var11 = this;
         DatabaseManager var42 = (DatabaseManager)var11.getPlatform().getModule(DatabaseManager.class);

         try {
            PunishmentStorage var13 = var42.openStorage();
            Closeable var14 = var13;
            Throwable var15 = null;

            try {
               label145: {
                  int var20 = var4.length();
                  if (Intrinsics.getInt(var20, var7 & 255) > 0) {
                     this.handlePunishmentStorage(var13, var4, var7);
                     if (this.checkPunishmentStorage(
                        var13,
                        var1,
                        var5,
                        var4,
                        ArgUtils.getString(
                           ChatColorCode.RED,
                           "Invalid username.\nMaximum "
                              + ChatColorCode.GREEN
                              + UByte.getString(var7)
                              + ' '
                              + ChatColorCode.getString3("characters")
                              + ", found "
                              + ChatColorCode.getString5(ChatColorCode.getString6(String.valueOf(var4.length())))
                        ),
                        null,
                        "KICK_OTHER",
                        false
                     )) {
                        var10 = true;
                        break label145;
                     }
                  }

                  label141:
                  if (this.checkString(var8)) {
                     PunishmentEntry var44 = EntryReader.getPunishmentEntry(var13, var5, var6, null, var8, false, false, 52, null);
                     byte var22 = 3;
                     if (var2.isDebugLevel(var22)) {
                        var2.debug("Ban for " + var4 + " (uuid: '" + var5 + "') on " + var8 + ": " + var44);
                     }

                     String var21 = this.getString(var6);
                     if (var21 != null) {
                        var9 = this.checkPunishmentStorage(var13, var1, var21, var8, var5, var4);
                     }

                     if (!var9 && var44 != null) {
                        CommandRunnable var45 = this;
                        if (!((NotificationManager)var45.getPlatform().getModule(NotificationManager.class)).checkPunishmentEntry(var44, var4)) {
                           var9 = this.checkPunishmentStorage(var13, var1, var5, var4, var6, var8, var44);
                           break label141;
                        }
                     }

                     if (!var9 && var2.getConfig().isBanNames()) {
                        PunishmentEntry var46 = EntryReader.getPunishmentEntry(var13, var4, var5, var8, null, 8, null);
                        if (var46 != null) {
                           CommandRunnable var23 = this;
                           if (!((NotificationManager)var23.getPlatform().getModule(NotificationManager.class)).checkPunishmentEntry(var46, var4)) {
                              var9 = this.checkPunishmentStorage(var13, var1, var5, var4, var6, var8, var46);
                              if (var9) {
                                 var10 = true;
                              }
                           }
                        }
                     }
                  }
               }
            } catch (Throwable var31) {
               var15 = var31;
               throw var31;
            } finally {
               CloseableKt.handleCloseable(var14, var15);
            }
         } catch (Exception var33) {
            if (!(var33 instanceof SQLException) || !var42.isConnectionError(var33)) {
               throw var33;
            }
         }

         if (var10) {
            return;
         }

         var11 = this;
         ((NotificationManager)var11.getPlatform().getModule(NotificationManager.class)).handleString(var4, var6, var5, var8, var9);
      } catch (OperationCancelledException var34) {
         CommandRunnable var39 = this;
         LiteBansCore.handleObjectDefault((LiteBansCore)var39.getPlatform().getModule(LiteBansCore.class), var34, 0, 2, null);
      } catch (NullPointerException var35) {
         LiteBansPlatform var37 = this.getPlatform();
         if (var37.isEnabled() && !var37.isDisabling()) {
            throw var35;
         }

         CommandRunnable var38 = this;
         LiteBansCore.handleObjectDefault((LiteBansCore)var38.getPlatform().getModule(LiteBansCore.class), var35, 0, 2, null);
      } catch (Exception var36) {
         if (!(var36 instanceof SQLException)) {
            throw var36;
         }

         CommandRunnable var3 = this;
         ((DatabaseManager)var3.getPlatform().getModule(DatabaseManager.class)).reportError(var36);
      }
   }

   public final void handlePunishmentStorage(@NotNull PunishmentStorage var1, @NotNull String var2, byte var3) {
      var1.getPlatform().getLogger().warning("User \"" + var2 + "\" length > " + UByte.getString(var3) + " (invalid username); blocking login.");
   }

   public final String getString(@Nullable String var1) {
      CommandRunnable var2 = this;
      GeoIp var10000 = ((LiteBansCore)var2.getPlatform().getModule(LiteBansCore.class)).getGeoIp();
      return var10000 != null ? var10000.getString2(var1) : null;
   }

   public final boolean checkString(@NotNull String var1) {
      boolean var10000;
      if (this.getPlatform().isProxy()) {
         CommandRunnable var2 = this;
         var10000 = !((LiteBansCore)var2.getPlatform().getModule(LiteBansCore.class))
            .getConfig()
            .getDisableLoginBanCheckServers()
            .contains(var1.toLowerCase(Locale.ENGLISH));
      } else {
         CommandRunnable var3 = this;
         var10000 = !((LiteBansCore)var3.getPlatform().getModule(LiteBansCore.class)).getConfig().isDisableLoginBanCheck();
      }

      return var10000;
   }

   public final boolean checkPunishmentStorage(
      @NotNull PunishmentStorage var1,
      @NotNull Object var2,
      @NotNull String var3,
      @NotNull String var4,
      @NotNull CharSequence var5,
      @Nullable PunishmentEntry var6,
      @NotNull String var7,
      boolean var8
   ) {
      UUID var9 = TargetParser.INSTANCE.getUUID(var3);
      if ((var6 == null || !Intrinsics.checkObject(var6.getUuid(), var3)) && var9 != null && var1.existsByUuid(var9, PunishmentType.BAN) && var8) {
         var1.getPlatform().getLogger().info(var4 + " was permitted to join the server.");
         return false;
      } else {
         this.handleObject(var2, var5, var7);
         return true;
      }
   }

   public static boolean checkPunishmentStorageDefault(
      LoginCheck var0,
      PunishmentStorage var1,
      Object var2,
      String var3,
      String var4,
      CharSequence var5,
      PunishmentEntry var6,
      String var7,
      boolean var8,
      int var9,
      Object var10
   ) {
      if (var10 != null) {
         throw new UnsupportedOperationException("");
      } else {
         if ((var9 & 32) != 0) {
            var7 = "KICK_BANNED";
         }

         if ((var9 & 64) != 0) {
            var8 = true;
         }

         return var0.checkPunishmentStorage(var1, var2, var3, var4, var5, var6, var7, var8);
      }
   }

   public final PlayerInfo getPlayerInfo(@NotNull Object var1) {
      PlatformBridge var10000 = this.getPlatform().createBridge();
      CommandRunnable var2 = this;
      return var10000.getLoginInfo(var1, ((LiteBansCore)var2.getPlatform().getModule(LiteBansCore.class)).getConfig().isOnlinePlayerSelectLimit());
   }

   public final void handleObject(@NotNull Object var1, @NotNull CharSequence var2, @NotNull String var3) {
      LoginKickResult.handlePlatformBridge(this.getPlatform().createBridge(), var1, var2, null, false, var3, 12, null);
   }

   public String getString(@NotNull Object var1) {
      return this.getPlatform().createBridge().getString(var1);
   }

   public void handleObject(@NotNull Object var1, @NotNull Function1 var2) {
      CommandRunnable var3 = this;
      ((DatabaseManager)var3.getPlatform().getModule(DatabaseManager.class)).runWithStorageAsync(st -> getUnit(var1, var2, (PunishmentStorage) st));
   }

   public final boolean checkPunishmentStorage(
      @NotNull PunishmentStorage var1, @NotNull Object var2, @NotNull String var3, @NotNull String var4, @NotNull String var5, @NotNull String var6
   ) {
      if (checkPunishmentStorageDefault(this, var1, var2, var5, var6, EntryPlaceholders.getString(var1, var3, var4), null, null, false, 96, null)) {
         CommandRunnable var7 = this;
         if (((LiteBansCore)var7.getPlatform().getModule(LiteBansCore.class)).getConfig().isNotifyBannedPlayerJoin()) {
            var7 = this;
            NotificationManager var10000 = (NotificationManager)var7.getPlatform().getModule(NotificationManager.class);
            CharSequence var10002 = Message.NOTIFY_BANNED_BLACKLIST;
            CharSequence[] var11 = new CharSequence[]{"geoip", var3, "player", var6};
            var10000.notifyBannedJoin(var5, ArgUtils.getString(var10002, var11), true);
            return true;
         }
      }

      return false;
   }

   public final boolean checkPunishmentStorage(
      @NotNull PunishmentStorage var1,
      @NotNull Object var2,
      @NotNull String var3,
      @NotNull String var4,
      @Nullable String var5,
      @Nullable String var6,
      @NotNull PunishmentEntry var7
   ) {
      CharSequence var8 = EntryPlaceholders.getCharSequence(var1, var7, var4, var3, var5, var6, -1);
      if (checkPunishmentStorageDefault(this, var1, var2, var3, var4, var8, var7, null, false, 96, null)) {
         CommandRunnable var9 = this;
         if (((LiteBansCore)var9.getPlatform().getModule(LiteBansCore.class)).getConfig().isNotifyBannedPlayerJoin()) {
            var1.getDatabaseManager().runWithStorageAsync(st -> getUnit(var7, var3, var4, var5, var6, this, (PunishmentStorage) st));
         }

         if (var1.getPlatform().isProxy()) {
            this.handleObject(var2, st -> getCharSequence(this, var7, var8, (PunishmentStorage) st));
         }

         return true;
      } else {
         return false;
      }
   }

   public final CharSequence getCharSequence(@NotNull PunishmentStorage var1, @NotNull PunishmentEntry var2, @NotNull CharSequence var3) {
      CharSequence var4 = var3;
      if (var2 == null || var2.getTemplate() == -1) {
         var4 = var2.isPermanent() ? Message.BANNED_MESSAGE_SWITCH : Message.BANNED_MESSAGE_SWITCH_TEMP;
      }

      return EntryReader.getCharSequence(var1, var4, var2, false, 2, null);
   }

   public Void getVoid() {
      Unreachable.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   private static final Unit getUnit(Object var0, Function1 var1, PunishmentStorage var2) {
      var2.getPlatform().createBridge().handleObject(var0, (CharSequence)var1.invoke(var2));
      return Unit.INSTANCE;
   }

   private static final Unit getUnit(PunishmentEntry var0, String var1, String var2, String var3, String var4, LoginCheck var5, PunishmentStorage var6) {
      CharSequence var7 = var6.getCharSequence(
         EntryReader.getCharSequence(var6, Message.NOTIFY_BANNED_PLAYER_JOIN, var0, false, 2, null), var1, var1, var2, var3, var4
      );
      CommandRunnable var8 = var5;
      ((NotificationManager)var8.getPlatform().getModule(NotificationManager.class)).notifyBannedJoin(var2, var7, true);
      return Unit.INSTANCE;
   }

   private static final CharSequence getCharSequence(LoginCheck var0, PunishmentEntry var1, CharSequence var2, PunishmentStorage var3) {
      return var0.getCharSequence(var3, var1, var2);
   }

   @Override
   public void run() {
      this.getVoid();
   }
}
