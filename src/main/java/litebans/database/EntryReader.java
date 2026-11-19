package litebans.database;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import litebans.chat.ChatColorCode;
import litebans.config.Config;
import litebans.config.Message;
import litebans.core.LiteBansCore;
import litebans.core.NotificationManager;
import litebans.lib.compress.archivers.tar.TargetParser;
import litebans.lib.kotlin.Pair;
import litebans.lib.kotlin.collections.CollectionsKt;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.text.StringsKt;
import litebans.platform.PlatformBridge;
import litebans.platform.Sender;
import litebans.platform.bukkit.BukkitBridge;
import litebans.platform.bukkit.VaultHook;
import litebans.punishment.EntryPlaceholders;
import litebans.punishment.ExecutorInfo;
import litebans.punishment.PlayerHistory;
import litebans.punishment.PunishmentEntry;
import litebans.punishment.PunishmentType;
import litebans.punishment.ServerScope;
import litebans.punishment.ServerScopeParser;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class EntryReader {
   public static boolean checkPunishmentStorage(@NotNull PunishmentStorage var0) {
      return !var0.isClosed();
   }

   public static DatabaseManager getDatabaseManager(@NotNull PunishmentStorage var0) {
      return (DatabaseManager)var0.getPlatform().getModule(DatabaseManager.class);
   }

   public static PlayerHistory getPlayerHistory(PunishmentStorage var0, PlayerHistory var1, boolean var2, int var3, Object var4) {
      if (var4 != null) {
         throw new UnsupportedOperationException("");
      } else {
         if ((var3 & 2) != 0) {
            var2 = true;
         }

         return var0.getPlayerHistory(var1, var2);
      }
   }

   public static void handlePunishmentStorage(PunishmentStorage var0, String var1, String var2, String var3, boolean var4, boolean var5, int var6, Object var7) {
      if (var7 != null) {
         throw new UnsupportedOperationException("");
      } else {
         if ((var6 & 8) != 0) {
            var4 = true;
         }

         if ((var6 & 16) != 0) {
            var5 = true;
         }

         var0.recordLogin(var1, var2, var3, var4, var5);
      }
   }

   public static ResultSet getResultSet(PunishmentStorage var0, UUID var1, int var2, int var3, Object var4) {
      if (var4 != null) {
         throw new UnsupportedOperationException("");
      } else {
         if ((var3 & 2) != 0) {
            var2 = 0;
         }

         return var0.insertSyncRecord(var1, var2);
      }
   }

   public static boolean checkPunishmentStorage(PunishmentStorage var0, UUID var1, PunishmentType var2, int var3, Object var4) {
      if (var4 != null) {
         throw new UnsupportedOperationException("");
      } else {
         if ((var3 & 2) != 0) {
            var2 = PunishmentType.BAN;
         }

         return var0.existsByUuid(var1, var2);
      }
   }

   public static List getList(PunishmentStorage var0, String var1, int var2, int var3, Object var4) {
      if (var4 != null) {
         throw new UnsupportedOperationException("");
      } else {
         if ((var3 & 2) != 0) {
            var2 = 256;
         }

         return var0.findHistoryByIp(var1, var2);
      }
   }

   public static PunishmentEntry getPunishmentEntry(PunishmentStorage var0, PunishmentEntry var1, boolean var2, int var3, Object var4) {
      if (var4 != null) {
         throw new UnsupportedOperationException("");
      } else {
         if ((var3 & 2) != 0) {
            var2 = false;
         }

         return var0.findActiveFor(var1, var2);
      }
   }

   public static PunishmentEntry getPunishmentEntry(
      PunishmentStorage var0, String var1, String var2, PunishmentType var3, boolean var4, boolean var5, int var6, Object var7
   ) {
      if (var7 != null) {
         throw new UnsupportedOperationException("");
      } else {
         if ((var6 & 2) != 0) {
            var2 = null;
         }

         if ((var6 & 4) != 0) {
            var3 = PunishmentType.BAN;
         }

         if ((var6 & 8) != 0) {
            var4 = false;
         }

         if ((var6 & 16) != 0) {
            var5 = false;
         }

         return var0.findActive(var1, var2, var3, var4, var5);
      }
   }

   public static PunishmentEntry getPunishmentEntry(
      PunishmentStorage var0, String var1, String var2, PunishmentType var3, String var4, boolean var5, boolean var6, int var7, Object var8
   ) {
      if (var8 != null) {
         throw new UnsupportedOperationException("");
      } else {
         if ((var7 & 2) != 0) {
            var2 = null;
         }

         if ((var7 & 4) != 0) {
            var3 = PunishmentType.BAN;
         }

         if ((var7 & 16) != 0) {
            var5 = false;
         }

         if ((var7 & 32) != 0) {
            var6 = false;
         }

         return var0.findActive(var1, var2, var3, var4, var5, var6);
      }
   }

   public static PunishmentEntry getPunishmentEntry(PunishmentStorage var0, String var1, PunishmentType var2, String var3, boolean var4, int var5, Object var6) {
      if (var6 != null) {
         throw new UnsupportedOperationException("");
      } else {
         if ((var5 & 4) != 0) {
            var3 = "__ALL__";
         }

         if ((var5 & 8) != 0) {
            var4 = true;
         }

         return var0.findById(var1, var2, var3, var4);
      }
   }

   public static PunishmentEntry getPunishmentEntry(PunishmentStorage var0, long var1, PunishmentType var3, String var4, boolean var5, int var6, Object var7) {
      if (var7 != null) {
         throw new UnsupportedOperationException("");
      } else {
         if ((var6 & 8) != 0) {
            var5 = true;
         }

         return var0.findById(var1, var3, var4, var5);
      }
   }

   public static long getLong(PunishmentStorage var0, Table var1, String var2, boolean var3, int var4, Object var5) {
      if (var5 != null) {
         throw new UnsupportedOperationException("");
      } else {
         if ((var4 & 2) != 0) {
            var2 = "__ALL__";
         }

         if ((var4 & 4) != 0) {
            var3 = false;
         }

         return var0.countActive(var1, var2, var3);
      }
   }

   public static PunishmentEntry getPunishmentEntry(PunishmentStorage var0, String var1, String var2, String var3, PunishmentType var4, int var5, Object var6) {
      if (var6 != null) {
         throw new UnsupportedOperationException("");
      } else {
         if ((var5 & 8) != 0) {
            var4 = PunishmentType.BAN;
         }

         return var0.findNameBan(var1, var2, var3, var4);
      }
   }

   public static List getList2(PunishmentStorage var0, String var1, int var2, int var3, Object var4) {
      if (var4 != null) {
         throw new UnsupportedOperationException("");
      } else {
         if ((var3 & 2) != 0) {
            var2 = 8;
         }

         return var0.findHistoryByTarget(var1, var2);
      }
   }

   public static void handlePunishmentStorage(
      PunishmentStorage var0, String var1, String var2, CharSequence var3, PunishmentEntry var4, boolean var5, int var6, Object var7
   ) {
      if (var7 != null) {
         throw new UnsupportedOperationException("");
      } else {
         if ((var6 & 16) != 0) {
            var5 = false;
         }

         var0.deactivate(var1, var2, var3, var4, var5);
      }
   }

   public static void handlePunishmentStorage(
      PunishmentStorage var0, PunishmentEntry var1, String var2, String var3, CharSequence var4, boolean var5, int var6, Object var7
   ) {
      if (var7 != null) {
         throw new UnsupportedOperationException("");
      } else {
         if ((var6 & 2) != 0) {
            var2 = null;
         }

         if ((var6 & 4) != 0) {
            var3 = null;
         }

         if ((var6 & 8) != 0) {
            var4 = "";
         }

         if ((var6 & 16) != 0) {
            var5 = false;
         }

         var0.removeEntry(var1, var2, var3, var4, var5);
      }
   }

   public static void handlePunishmentStorage(PunishmentStorage var0, boolean var1, int var2, Object var3) {
      if (var3 != null) {
         throw new UnsupportedOperationException("");
      } else {
         if ((var2 & 1) != 0) {
            var1 = false;
         }

         var0.expireEntries(var1);
      }
   }

   public static List getList(@NotNull PunishmentStorage var0, @NotNull ResultSet var1, @NotNull PunishmentType var2) {
      try {
      if (!var1.next()) {
         return CollectionsKt.getList();
      } else {
         ArrayList var3 = new ArrayList();
         ArrayList var4 = var3;

         do {
            var4.add(var0.readEntry(var1, var2));
         } while (var1.next());

         return var3;
      }
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }

   public static List getList(
      @NotNull PunishmentStorage var0, @NotNull PlayerHistory var1, @NotNull PunishmentType var2, @NotNull String var3, boolean var4, int var5
   ) {
      return var0.findEntries(var1.getUuid(), var1.getIp(), var2, var3, var4, var5);
   }

   public static List getList(@NotNull PunishmentStorage var0, @NotNull PunishmentEntry var1, int var2) {
      return var0.findEntries(var1.getUuid(), var1.getIp(), var1.getType(), var1.getServerScope(), var1.isActive(), var2);
   }

   public static List getList(@NotNull PunishmentStorage var0, @NotNull Sender var1, @NotNull PunishmentType var2, boolean var3, int var4) {
      String var10001 = var1.getUuidString();
      String var10002 = var1.getIp();
      String var10004 = var1.getServerName();
      if (var10004 == null) {
         var10004 = var0.getDatabaseManager().getString();
      }

      return var0.findEntries(var10001, var10002, var2, var10004, var3, var4);
   }

   public static String getString(@NotNull PunishmentStorage var0, @NotNull Sender var1) {
      Config var2 = ((LiteBansCore)var0.getPlatform().getModule(LiteBansCore.class)).getConfig();
      return var1.isConsole() ? var2.getConsoleSenderName() : (var2.isUseDisplayNames() ? var1.getDisplayName() : var1.getName());
   }

   public static CharSequence getCharSequence(@NotNull PunishmentStorage var0, @NotNull CharSequence var1, @NotNull Sender var2) {
      return getCharSequence(var0, var1, var0.getString(var2), var2.getUuidString(), false, 4, null);
   }

   public static CharSequence getCharSequence(
      @NotNull PunishmentStorage var0, @NotNull CharSequence var1, @Nullable String var2, @Nullable String var3, boolean var4
   ) {
      if (var1.length() == 0) {
         return var1;
      } else {
         NotificationManager var6 = (NotificationManager)var0.getPlatform().getModule(NotificationManager.class);
         LiteBansCore var7 = (LiteBansCore)var0.getPlatform().getModule(LiteBansCore.class);
         Object var8 = var2;
         String var9 = "";
         String var10 = "";
         if (var3 != null && (EntryPlaceholders.checkCharSequence(var1, "executorPrefix") || EntryPlaceholders.checkCharSequence(var1, "executorSuffix"))) {
            byte var11 = var0.getPlatform().getPlatformId();
            if (var11 == 0) {
               PlatformBridge var10000 = var0.getPlatform().createBridge();
               Intrinsics.handleObject(var10000, "");
               VaultHook var12 = ((BukkitBridge)var10000).getVaultHook();
               Sender var13 = var6.findOnlineByUuid(var3);
               if (var13 != null) {
                  var9 = var12.getString(var13.getHandle());
                  var10 = var12.getString2(var13.getHandle());
               } else if (TargetParser.checkString7(var3)) {
                  try {
                     UUID var14 = UUID.fromString(var3);
                     var9 = var12.getString(var14);
                     var10 = var12.getString2(var14);
                  } catch (IllegalArgumentException var15) {
                     var7.handleObject(var15, 0);
                  }
               }
            } else {
               Pair var16 = EntryPlaceholders.getPair(var0, var3);
               var9 = (String)var16.getObject();
               var10 = (String)var16.getObject2();
            }
         }

         if (Intrinsics.checkObject(var9, "null")) {
            var9 = "";
         }

         if (Intrinsics.checkObject(var10, "null")) {
            var10 = "";
         }

         if (var4) {
            var9 = Message.getString(var9, true);
            var10 = Message.getString(var10, true);
            var8 = var2 + ChatColorCode.RESET;
         }

         return EntryPlaceholders.getString(
            EntryPlaceholders.getString(
               EntryPlaceholders.getString(EntryPlaceholders.getString(var1, "executorUUID", var3), "executorPrefix", var9), "executorSuffix", var10
            ),
            "executor",
            var8
         );
      }
   }

   public static CharSequence getCharSequence(PunishmentStorage var0, CharSequence var1, String var2, String var3, boolean var4, int var5, Object var6) {
      if (var6 != null) {
         throw new UnsupportedOperationException("");
      } else {
         if ((var5 & 4) != 0) {
            var4 = true;
         }

         return var0.getCharSequence(var1, var2, var3, var4);
      }
   }

   public static CharSequence getCharSequence(@NotNull PunishmentStorage var0, @NotNull CharSequence var1) {
      return EntryPlaceholders.getString(var0, var1);
   }

   public static CharSequence getCharSequence(@NotNull PunishmentStorage var0, @NotNull CharSequence var1, @Nullable PlayerHistory var2, boolean var3) {
      return var2 == null ? var1 : EntryPlaceholders.getString(var0, var2, var1, var3);
   }

   public static CharSequence getCharSequence(PunishmentStorage var0, CharSequence var1, PlayerHistory var2, boolean var3, int var4, Object var5) {
      if (var5 != null) {
         throw new UnsupportedOperationException("");
      } else {
         if ((var4 & 2) != 0) {
            var3 = true;
         }

         return var0.getCharSequence(var1, var2, var3);
      }
   }

   public static CharSequence getCharSequence2(@NotNull PunishmentStorage var0, @NotNull CharSequence var1, @Nullable Sender var2) {
      return var2 != null ? EntryPlaceholders.getStringDefault(var0, var2, var1, false, 4, null) : var1;
   }

   public static CharSequence getCharSequence(@NotNull PunishmentStorage var0, @NotNull CharSequence var1, @NotNull ExecutorInfo var2) {
      return var2.getSender() == null
         ? var0.getCharSequence(var1, var2.getString2(), var2.getString3(), var2.getString4(), var2.getString5(), null)
         : var0.getCharSequence2(var1, var2.getSender());
   }

   public static CharSequence getCharSequence(
      @NotNull PunishmentStorage var0,
      @NotNull CharSequence var1,
      @Nullable String var2,
      @Nullable String var3,
      @Nullable String var4,
      @Nullable String var5,
      @Nullable String var6
   ) {
      return EntryPlaceholders.getStringDefault(var0, var2, var3, var4, var5, var6, new Date(), var1, false, 128, null);
   }

   public static CharSequence getCharSequence(@NotNull PunishmentStorage var0, @NotNull CharSequence var1, @Nullable PunishmentEntry var2, boolean var3) {
      return var2 != null && !StringsKt.checkCharSequence(var1) ? EntryPlaceholders.getCharSequenceDefault(var0, var2, var1, 0L, var3, 4, null) : var1;
   }

   public static CharSequence getCharSequence(PunishmentStorage var0, CharSequence var1, PunishmentEntry var2, boolean var3, int var4, Object var5) {
      if (var5 != null) {
         throw new UnsupportedOperationException("");
      } else {
         if ((var4 & 2) != 0) {
            var3 = true;
         }

         return var0.getCharSequence(var1, var2, var3);
      }
   }

   public static CharSequence getCharSequence(
      @NotNull PunishmentStorage var0, @NotNull CharSequence var1, @Nullable PunishmentEntry var2, @Nullable PlayerHistory var3, boolean var4
   ) {
      return var0.getCharSequence(var0.getCharSequence(var1, var2, false), var3, var4);
   }

   public static CharSequence getCharSequence(
      PunishmentStorage var0, CharSequence var1, PunishmentEntry var2, PlayerHistory var3, boolean var4, int var5, Object var6
   ) {
      if (var6 != null) {
         throw new UnsupportedOperationException("");
      } else {
         if ((var5 & 4) != 0) {
            var4 = true;
         }

         return var0.getCharSequence(var1, var2, var3, var4);
      }
   }

   public static boolean checkPunishmentStorage(@NotNull PunishmentStorage var0, @NotNull PunishmentEntry var1, @NotNull Sender var2) {
      DatabaseManager var3 = (DatabaseManager)var0.getPlatform().getModule(DatabaseManager.class);
      if (var1.matches(var2.getUuidString(), var2.getIp(), DatabaseManager.currentTimeMillisDefault(var3, false, 1, null))) {
         ServerScopeParser var10000 = ServerScope.serverScopeParser;
         String var10001 = var1.getServerScope();
         if (var10001 == null) {
            var10001 = ServerScope.serverScopeParser.getString();
         }

         String var10002 = var2.getServerName();
         if (var10002 == null) {
            var10002 = var3.getString();
         }

         if (var10000.checkString(var10001, var10002)) {
            return true;
         }
      }

      return false;
   }

   public static boolean checkPunishmentStorage(@NotNull PunishmentStorage var0, @Nullable PunishmentEntry var1) {
      return var1 != null
         ? var1.matches(var1.getUuid(), var1.getIp(), DatabaseManager.currentTimeMillisDefault(var0.getDatabaseManager(), false, 1, null))
         : false;
   }
}
