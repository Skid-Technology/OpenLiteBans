package litebans.punishment;

import java.io.Closeable;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
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
import litebans.lib.kotlin.ResultFailure;
import litebans.lib.kotlin.UByte;
import litebans.lib.kotlin.Unit;
import litebans.lib.kotlin.collections.CollectionsKt;
import litebans.lib.kotlin.io.CloseableKt;
import litebans.lib.kotlin.jvm.internal.Function1;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.jvm.internal.TypeIntrinsics;
import litebans.platform.LiteBansPlatform;
import litebans.platform.PlatformBridge;
import litebans.platform.bungee.BungeeBridge;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.PendingConnection;
import net.md_5.bungee.api.event.LoginEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class ProxyLoginCheck extends LoginCheck {
   private final ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
   private final ConcurrentHashMap concurrentHashMap2 = new ConcurrentHashMap();

   public ProxyLoginCheck(@NotNull LiteBansPlatform var1) {
      super(var1);
   }

   public final ConcurrentHashMap getConcurrentHashMap() {
      return this.concurrentHashMap;
   }

   public final ConcurrentHashMap getConcurrentHashMap2() {
      return this.concurrentHashMap2;
   }

   public final void handlePunishmentStorage(
      @NotNull PunishmentStorage var1, @NotNull String var2, @NotNull UUID var3, @Nullable String var4, @NotNull String var5, @NotNull Collection var6
   ) {
      int var7 = (int)var3.getMostSignificantBits();
      Collection var8 = (Collection)this.concurrentHashMap.get(var7);
      if (!var6.isEmpty()) {
         for (Object var12 : var6) {
            PunishmentEntry var13 = (PunishmentEntry)var12;
            this.concurrentHashMap2.put(var13, EntryPlaceholders.getCharSequence(var1, var13, var2, var3.toString(), var4, var5, -1));
         }

         Integer var10 = var7;
         this.concurrentHashMap.put(var10, this.getCollection(var6, var8));
      }
   }

   private final Collection getCollection(Collection var1, Collection var2) {
      if (var2 == null || var2.isEmpty()) {
         return var1;
      } else {
         List var3 = CollectionsKt.getList(var2);

         for (Object var5o : var1) {
            PunishmentEntry var5 = (PunishmentEntry) var5o;
            Iterable var6 = var2;
            boolean var10000;
            if (var6 instanceof Collection && ((Collection)var6).isEmpty()) {
               var10000 = true;
            } else {
               label58: {
                  for (Object var9 : var6) {
                     PunishmentEntry var10 = (PunishmentEntry)var9;
                     if (var10.getId() == var5.getId()) {
                        var10000 = false;
                        break label58;
                     }
                  }

                  var10000 = true;
               }
            }

            if (var10000) {
               var3.add(var5);
            }
         }

         return var3;
      }
   }

   public final void handleLoginEvent(@NotNull LoginEvent var1) {
      boolean var62;
      BungeeBridge var2;
      PendingConnection var3;
      UUID var4;
      String var5;
      String var6;
      String var59;
      label235: {
         PlatformBridge var10000 = this.getPlatform().createBridge();
         Intrinsics.handleObject(var10000, "");
         var2 = (BungeeBridge)var10000;
         var3 = var1.getConnection();
         var4 = var3.getUniqueId();
         var5 = var4.toString();
         var6 = var3.getName();
         InetSocketAddress var57 = var3.getAddress();
         if (var57 != null) {
            InetAddress var58 = var57.getAddress();
            if (var58 != null) {
               var59 = var58.getHostAddress();
               break label235;
            }
         }

         var59 = null;
      }

      String var7 = var59;
      Intrinsics.handleObject(var3);
      ServerInfo var60 = var2.getServerInfo(var3);
      if (var60 == null) {
         throw new AssertionError("No default server");
      } else {
         ServerInfo var8 = var60;
         ResultFailure var9 = new ResultFailure();
         String var10 = var8.getName();
         CommandRunnable var11 = this;
         DatabaseManager var37 = (DatabaseManager)var11.getPlatform().getModule(DatabaseManager.class);

         try {
            PunishmentStorage var13 = var37.openStorage();
            Closeable var14 = var13;
            Throwable var15 = null;

            try {
               CommandRunnable var20 = this;
               byte var22 = ((LiteBansCore)var20.getPlatform().getModule(LiteBansCore.class)).getConfig().getUsernameMaxLength();
               int var38 = var6.length();
               if (Intrinsics.getInt(var38, var22 & 255) > 0) {
                  Intrinsics.handleObject(var6);
                  this.handlePunishmentStorage(var13, var6, var22);
                  if (this.checkPunishmentStorage(
                     var13,
                     var1,
                     var5,
                     var6,
                     ArgUtils.getString(
                        ChatColorCode.RED,
                        "Invalid username.\nMaximum "
                           + ChatColorCode.GREEN
                           + UByte.getString(var22)
                           + ' '
                           + ChatColorCode.RED
                           + "characters, found "
                           + ChatColorCode.YELLOW
                           + ChatColorCode.UNDERLINE
                           + var6.length()
                     ),
                     null,
                     "KICK_OTHER",
                     false
                  )) {
                     return;
                  }
               }

               var9.object = var13.findEntries(var5, var7, PunishmentType.BAN, "__ALL__", true, 0);
               var20 = this;
               if (((LiteBansCore)var20.getPlatform().getModule(LiteBansCore.class)).getConfig().isBanNames()) {
                  Intrinsics.handleObject(var6);
                  List var40 = var13.getList(var6, var5, "__ALL__", PunishmentType.BAN, true);
                  if (!var40.isEmpty()) {
                     var59 = (String)var9.object;
                     Intrinsics.handleObject(var9.object, "");
                     TypeIntrinsics.getList(var59).addAll(var40);
                  }
               }

               var20 = this;
               LiteBansCore var42 = (LiteBansCore)var20.getPlatform().getModule(LiteBansCore.class);
               byte var21 = 3;
               if (var42.isDebugLevel(var21)) {
                  var42.debug("Found " + ((List)var9.object).size() + " ban(s) for " + var6 + " (uuid: '" + var4 + "').");
               }

               var20 = this;
               if (((LiteBansCore)var20.getPlatform().getModule(LiteBansCore.class)).isDebugLevel(10)) {
                  Iterable var44 = (Iterable)var9.object;
                  CommandRunnable var46 = this;
                  LiteBansCore var47 = (LiteBansCore)var46.getPlatform().getModule(LiteBansCore.class);

                  for (Object var26 : var44) {
                     var47.debug(var26);
                  }
               }

               label202: {
                  if (!((Collection)var9.object).isEmpty()) {
                     Intrinsics.handleObject(var4);
                     if (var13.existsByUuid(var4, PunishmentType.BAN)) {
                        var62 = true;
                        break label202;
                     }
                  }

                  var62 = false;
               }

               boolean var45 = var62;
               if (var45) {
                  Iterable var48 = (Iterable)var9.object;
                  Collection var53 = new ArrayList();

                  for (Object var29 : var48) {
                     PunishmentEntry var30 = (PunishmentEntry)var29;
                     if (Intrinsics.checkObject(var30.getUuid(), var5)) {
                        var53.add(var29);
                     }
                  }

                  var9.object = (List)var53;
               }

               String var49 = this.getString(var7);
               if (var49 != null && !var45) {
                  Intrinsics.handleObject(var10);
                  Intrinsics.handleObject(var6);
                  this.checkPunishmentStorage(var13, var1, var49, var10, var5, var6);
               }

               PunishmentEntry var59e = null;
               label186: {
                  for (Object var55 : (Iterable)var9.object) {
                     PunishmentEntry var56 = (PunishmentEntry)var55;
                     ServerScopeParser var63 = ServerScope.serverScopeParser;
                     Intrinsics.handleObject(var10);
                     String var10002 = var56.getServerScope();
                     if (var10002 == null) {
                        var10002 = ServerScope.serverScopeParser.getString();
                     }

                     if (var63.checkString(var10, var10002) && this.checkString(var10)) {
                        var59e = var56;
                        break label186;
                     }
                  }

                  var59e = null;
               }

               PunishmentEntry var23 = var59e;
               CommandRunnable var51 = this;
               LiteBansCore var52 = (LiteBansCore)var51.getPlatform().getModule(LiteBansCore.class);
               byte var54 = 3;
               if (var52.isDebugLevel(var54)) {
                  var52.debug("Ban for " + var10 + " (uncached): " + var23);
               }

               if (var23 != null) {
                  Intrinsics.handleObject(var6);
                  this.checkPunishmentStorage(var13, var1, var5, var6, var7, var10, var23);
               } else {
                  Intrinsics.handleObject(var6);
                  Intrinsics.handleObject(var4);
                  Intrinsics.handleObject(var10);
                  this.handlePunishmentStorage(var13, var6, var4, var7, var10, (Collection)var9.object);
               }
            } catch (Throwable var34) {
               var15 = var34;
               throw var34;
            } finally {
               CloseableKt.handleCloseable(var14, var15);
            }
         } catch (Exception var36) {
            if (!(var36 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var36); }
            if (!var37.isConnectionError(var36)) {
               throw var36;
            }
         }
      }
   }

   @Override
   public void handleObject(@NotNull Object var1) {
      boolean var24;
      try {
         PlayerInfo var2 = this.getPlayerInfo(var1);
         String var21 = var2.getString2();
         String var4 = var2.getUuid2();
         String var5 = var2.getIp2();
         String var6 = this.getString(var1);
         boolean var7 = false;
         if (this.checkString(var6)) {
            Collection var10000 = (Collection)this.concurrentHashMap.get(this.getInt(var4));
            PunishmentEntry var26;
            if (var10000 != null) {
               Iterable var10 = var10000;
               Iterator var12 = var10.iterator();
               PunishmentEntry varFound = null;

               while (true) {
                  if (!var12.hasNext()) {
                     varFound = null;
                     break;
                  }

                  Object var13;
                  label66: {
                     var13 = var12.next();
                     PunishmentEntry var14 = (PunishmentEntry)var13;
                     if (var14.matches(var4, var5, System.currentTimeMillis())) {
                        ServerScopeParser var23 = ServerScope.serverScopeParser;
                        String var10002 = var14.getServerScope();
                        if (var10002 == null) {
                           var10002 = ServerScope.serverScopeParser.getString();
                        }

                        if (var23.checkString(var6, var10002)) {
                           var24 = true;
                           break label66;
                        }
                     }

                     var24 = false;
                  }

                  if (var24) {
                     varFound = (PunishmentEntry) var13;
                     break;
                  }
               }

               var26 = varFound;
            } else {
               var26 = null;
            }

            PunishmentEntry var8 = var26;
            if (var8 != null) {
               CommandRunnable var9 = this;
               if (!((NotificationManager)var9.getPlatform().getModule(NotificationManager.class)).checkPunishmentEntry(var8, var21)) {
                  var7 = this.checkObject(var1, var4, var21, var5, var6, var8);
               }
            }
         }

         CommandRunnable var22 = this;
         ((NotificationManager)var22.getPlatform().getModule(NotificationManager.class)).handleString(var21, var5, var4, var6, var7);
      } catch (OperationCancelledException var15) {
         CommandRunnable var20 = this;
         LiteBansCore.handleObjectDefault((LiteBansCore)var20.getPlatform().getModule(LiteBansCore.class), var15, 0, 2, null);
      } catch (NullPointerException var16) {
         LiteBansPlatform var18 = this.getPlatform();
         if (var18.isEnabled() && !var18.isDisabling()) {
            throw var16;
         }

         CommandRunnable var19 = this;
         LiteBansCore.handleObjectDefault((LiteBansCore)var19.getPlatform().getModule(LiteBansCore.class), var16, 0, 2, null);
      } catch (Exception var17) {
         if (!(var17 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var17); }
         CommandRunnable var3 = this;
         ((DatabaseManager)var3.getPlatform().getModule(DatabaseManager.class)).reportError(var17);
      }
   }

   public final int getInt(@NotNull String var1) {
      UUID var3 = UUID.fromString(var1);
      return (int)var3.getMostSignificantBits();
   }

   public final boolean checkObject(
      @NotNull Object var1, @NotNull String var2, @NotNull String var3, @Nullable String var4, @Nullable String var5, @NotNull PunishmentEntry var6
   ) {
      CharSequence var10000 = (CharSequence)this.concurrentHashMap2.get(var6);
      if (var10000 == null) {
         var10000 = "null";
      }

      CharSequence var7 = var10000;
      CommandRunnable var8 = this;
      LiteBansCore var13 = (LiteBansCore)var8.getPlatform().getModule(LiteBansCore.class);
      byte var9 = 3;
      if (var13.isDebugLevel(var9)) {
         var13.debug("Ban for " + var5 + " (cached): " + var6);
      }

      LoginKickResult.handlePlatformBridge(this.getPlatform().createBridge(), var1, var7, null, false, null, 28, null);
      var8 = this;
      if (((LiteBansCore)var8.getPlatform().getModule(LiteBansCore.class)).getConfig().isNotifyBannedPlayerJoin()) {
         var8 = this;
         ((DatabaseManager)var8.getPlatform().getModule(DatabaseManager.class)).runWithStorageAsync(st -> getUnit(var6, var2, var3, var4, var5, this, (PunishmentStorage) st));
      }

      this.handleObject(var1, o -> getCharSequence(this, var6, var7, (PunishmentStorage) o));
      return true;
   }

   @Override
   public void handleObject(@NotNull Object var1, @NotNull Function1 var2) {
      if (!(var1 instanceof LoginEvent)) {
         super.handleObject(var1, var2);
      }
   }

   private static final Unit getUnit(PunishmentEntry var0, String var1, String var2, String var3, String var4, ProxyLoginCheck var5, PunishmentStorage var6) {
      CharSequence var7 = var6.getCharSequence(
         EntryReader.getCharSequence(var6, Message.NOTIFY_BANNED_PLAYER_JOIN, var0, false, 2, null), var1, var1, var2, var3, var4
      );
      CommandRunnable var8 = var5;
      ((NotificationManager)var8.getPlatform().getModule(NotificationManager.class)).notifyBannedJoin(var2, var7, true);
      return Unit.INSTANCE;
   }

   private static final CharSequence getCharSequence(ProxyLoginCheck var0, PunishmentEntry var1, CharSequence var2, PunishmentStorage var3) {
      return var0.getCharSequence(var3, var1, var2);
   }
}
