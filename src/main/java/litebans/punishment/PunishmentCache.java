package litebans.punishment;

import java.io.Closeable;
import java.lang.ref.Reference;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ConcurrentHashMap.KeySetView;
import litebans.core.LiteBansCore;
import litebans.core.NotificationManager;
import litebans.core.PluginModule;
import litebans.database.DatabaseManager;
import litebans.database.PunishmentStorage;
import litebans.lib.kotlin.Lazy;
import litebans.lib.kotlin.LazyKt;
import litebans.lib.kotlin.io.CloseableKt;
import litebans.lib.kotlin.jvm.internal.Function1;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.platform.LiteBansPlatform;
import litebans.platform.Sender;
import litebans.sync.SyncManager;
import org.jetbrains.annotations.NotNull;

public final class PunishmentCache extends PluginModule implements Runnable {
   public static final PunishmentCacheCompanion Companion = new PunishmentCacheCompanion(null);
   private final Lazy lazy = LazyKt.getLazy(() -> getDatabaseManager(this));
   public static final long longValue = 48000L;

   public PunishmentCache(@NotNull LiteBansPlatform var1) {
      super(var1);
   }

   public final DatabaseManager getDatabaseManager() {
      Lazy var1 = this.lazy;
      return (DatabaseManager)var1.getValue();
   }

   @Override
   public void onEnable() {
      this.platform.runAsyncTimer(this, 48000L, 48000L);
   }

   @Override
   public void onReload() {
      this.onDisable();
      this.onEnable();
   }

   @Override
   public void run() {
      DatabaseManager var1 = this.getDatabaseManager();

      try {
         PunishmentStorage var3 = var1.openStorage();
         Closeable var4 = var3;
         Throwable var5 = null;

         try {
            this.handlePunishmentStorage(var3);
         } catch (Throwable var12) {
            var5 = var12;
            throw var12;
         } finally {
            CloseableKt.handleCloseable(var4, var5);
         }
      } catch (Exception var14) {
         if (!(var14 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var14); }
         if (!var1.isConnectionError(var14)) {
            throw var14;
         }
      }
   }

   public final void handlePunishmentStorage(@NotNull PunishmentStorage var1) {
      LiteBansCore var2 = (LiteBansCore)var1.getPlatform().getModule(LiteBansCore.class);
      MuteManager var3 = (MuteManager)var1.getPlatform().getModule(MuteManager.class);
      SyncManager var4 = (SyncManager)var1.getPlatform().getModule(SyncManager.class);
      Sender[] var5 = var1.getPlatform().getOnlineSenders();
      Intrinsics.handleObject(var5);
      if (var5.length == 0) {
         if (var2.getConfig().isMutesEnabled()) {
            var3.getMuteChatHandler().getConcurrentHashMap().clear();
         }

         if (var1.getPlatform().getPlatformId() == 1) {
            this.handlePunishmentStorage2(var1);
         }
      } else {
         if (var2.getConfig().isMutesEnabled()) {
            var3.getMuteChatHandler().getConcurrentHashMap().values().removeIf(o -> ((Collection) o).isEmpty());
         }

         if (var1.getPlatform().getPlatformId() == 1) {
            this.checkOnlinePlayers(var1, var5);
         }
      }

      byte var6 = var4.getByteValue();
      var4.setByteValue((byte)(var6 + 1));
      if (var4.getCopyOnWriteArrayList().size() > 8) {
         Iterable var7 = var4.getCopyOnWriteArrayList();
         Collection var10 = new ArrayList();

         for (Object var13 : var7) {
            Reference var14 = (Reference)var13;
            if (var14.get() != null) {
               var10.add(var13);
            }
         }

         List var15 = (List)var10;
         if (var15.isEmpty()) {
            var4.getCopyOnWriteArrayList().clear();
         } else {
            var4.setCopyOnWriteArrayList(new CopyOnWriteArrayList(var15));
         }
      }
   }

   private final void handlePunishmentStorage2(PunishmentStorage var1) {
      NotificationManager var2 = (NotificationManager)var1.getPlatform().getModule(NotificationManager.class);
      LoginCheck var10000 = var2.getLoginCheck();
      Intrinsics.handleObject(var10000, "");
      ProxyLoginCheck var3 = (ProxyLoginCheck)var10000;
      var3.getConcurrentHashMap().clear();
      var3.getConcurrentHashMap2().clear();
   }

   private final void checkOnlinePlayers(PunishmentStorage var1, Sender[] var2) {
      NotificationManager var3 = (NotificationManager)var1.getPlatform().getModule(NotificationManager.class);
      LoginCheck var10000 = var3.getLoginCheck();
      Intrinsics.handleObject(var10000, "");
      ProxyLoginCheck var4 = (ProxyLoginCheck)var10000;
      var4.getConcurrentHashMap().values().removeIf(o -> checkPunishmentCache(this, var1, var2, (Collection) o));
      if (var4.getConcurrentHashMap().isEmpty()) {
         var4.getConcurrentHashMap2().clear();
      } else {
         ((KeySetView)var4.getConcurrentHashMap2().keySet()).removeIf(o -> checkProxyLoginCheck(var4, (PunishmentEntry) o));
      }
   }

   public final boolean affectsOnline(@NotNull PunishmentStorage var1, @NotNull Sender[] var2, @NotNull PunishmentEntry var3) {
      Sender[] var4 = var2;
      int var6 = 0;
      int var7 = var2.length;

      boolean var10000;
      while (true) {
         if (var6 >= var7) {
            var10000 = true;
            break;
         }

         Sender var8 = var4[var6];
         if (var1.appliesTo(var3, var8)) {
            var10000 = false;
            break;
         }

         var6++;
      }

      return var10000;
   }

   private static final DatabaseManager getDatabaseManager(PunishmentCache var0) {
      return (DatabaseManager)var0.platform.getModule(DatabaseManager.class);
   }

   private static final boolean checkPunishmentCache(PunishmentCache var0, PunishmentStorage var1, Sender[] var2, PunishmentEntry var3) {
      Intrinsics.handleObject(var2);
      Intrinsics.handleObject(var3);
      return var0.affectsOnline(var1, var2, var3);
   }

   private static final boolean checkFunction1(Function1 var0, Object var1) {
      return (Boolean)var0.invoke(var1);
   }

   private static final boolean checkPunishmentCache(PunishmentCache var0, PunishmentStorage var1, Sender[] var2, Collection var3) {
      Intrinsics.handleObject(var3);
      Iterable var4 = var3;
      boolean var10000;
      if (((Collection)var4).isEmpty()) {
         var10000 = false;
      } else {
         for (Object var7 : var4) {
            PunishmentEntry var8 = (PunishmentEntry)var7;
            if (!var0.affectsOnline(var1, var2, var8)) {
               return false;
            }
         }

         var10000 = false;
      }

      return !var10000;
   }

   private static final boolean checkFunction12(Function1 var0, Object var1) {
      return (Boolean)var0.invoke(var1);
   }

   private static final boolean checkProxyLoginCheck(ProxyLoginCheck var0, PunishmentEntry var1) {
      Iterable var2 = var0.getConcurrentHashMap().values();
      boolean var10000;
      if (((Collection)var2).isEmpty()) {
         var10000 = true;
      } else {
         Iterator var4 = var2.iterator();

         while (true) {
            if (!var4.hasNext()) {
               var10000 = true;
               break;
            }

            Object var5 = var4.next();
            Collection var6 = (Collection)var5;
            Intrinsics.handleObject(var6);
            Iterable var8 = var6;
            if (var8 instanceof Collection && ((Collection)var8).isEmpty()) {
               var10000 = false;
            } else {
               label48: {
                  for (Object var11 : var8) {
                     PunishmentEntry var12 = (PunishmentEntry)var11;
                     if (var12.getId() == var1.getId()) {
                        var10000 = true;
                        break label48;
                     }
                  }

                  var10000 = false;
               }
            }

            if (var10000) {
               var10000 = false;
               break;
            }
         }
      }

      return var10000;
   }

   private static final boolean checkFunction13(Function1 var0, Object var1) {
      return (Boolean)var0.invoke(var1);
   }
}
