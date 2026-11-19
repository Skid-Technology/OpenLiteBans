package litebans.sync;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import litebans.command.AltsCommand;
import litebans.core.LiteBansCore;
import litebans.core.PluginModule;
import litebans.database.DatabaseManager;
import litebans.lib.compress.archivers.tar.TargetParser;
import litebans.lib.kotlin.Lazy;
import litebans.lib.kotlin.LazyKt;
import litebans.lib.kotlin.NoWhenBranchMatchedException;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.platform.LiteBansPlatform;
import litebans.punishment.PlayerInfo;
import litebans.util.Unreachable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class SyncQueue extends PluginModule implements Runnable {
   public static final SyncQueueCompanion Companion = new SyncQueueCompanion(null);
   private final Lazy lazy = LazyKt.getLazy(() -> getAsyncQueue(this));
   private final ArrayBlockingQueue queue = new ArrayBlockingQueue(512);
   private final AtomicBoolean atomicBoolean = new AtomicBoolean();
   public static final long longValue = 80L;
   public static final long longValue2 = 30L;
   public static final int intValue = 512;

   public SyncQueue(@NotNull LiteBansPlatform var1) {
      super(var1);
   }

   private final AsyncQueue getAsyncQueue() {
      Lazy var1 = this.lazy;
      return (AsyncQueue)var1.getValue();
   }

   @Override
   public void onEnable() {
      LiteBansCore var1 = (LiteBansCore)this.platform.getModule(LiteBansCore.class);
      var1.onEnable();
      long var2 = 80L;
      if (var1.getConfig().getBanAlts() > 1) {
         var2 = 30L;
      }

      this.platform.runAsyncTimer(this, var2, var2);
   }

   @Override
   public void onDisable() {
      this.queue.clear();
      AtomicBoolean var1 = this.atomicBoolean;
      var1.set(false);
   }

   @Override
   public void onReload() {
      this.onDisable();
      this.onEnable();
   }

   @Override
   public void run() {
      AtomicBoolean var1 = this.atomicBoolean;
      if (var1.compareAndSet(false, true)) {
         DatabaseManager var3 = (DatabaseManager)this.platform.getModule(DatabaseManager.class);

         try {
            int var4 = this.queue.size() >= 256 ? 3 : (this.queue.size() >= 128 ? 2 : (this.queue.size() >= 64 ? 1 : 0));
            byte var10000;
            switch (var4) {
               case 0:
                  var10000 = 20;
                  break;
               case 1:
                  var10000 = 40;
                  break;
               case 2:
                  var10000 = 50;
                  break;
               case 3:
                  var10000 = 80;
                  break;
               default:
                  Unreachable.getVoid();
                  throw new NoWhenBranchMatchedException();
            }

            byte var5 = var10000;
            if (!var3.hasFreeConnections(2) || var4 > 2) {
               int var6 = 1;
               if (var6 > var5) {
                  return;
               }

               while (true) {
                  PlayerInfo var17 = (PlayerInfo)this.queue.peek();
                  if (var17 == null) {
                     return;
                  }

                  PlayerInfo var7 = var17;
                  if (this.isQueued(var7)) {
                     return;
                  }

                  PlayerInfo var8 = (PlayerInfo)this.queue.poll();
                  if (var8 != var7) {
                     Intrinsics.handleObject(var8);
                     if (this.isQueued(var8)) {
                        this.queue.offer(var8);
                        return;
                     }
                  }

                  Intrinsics.handleObject(var8);
                  new AltsCommand(this.platform, var8.getString(), var8.getUuid(), var8.getIp()).run();
                  if (var6 == var5) {
                     return;
                  }

                  var6++;
               }
            }
         } catch (Exception var15) {
            if (!(var15 instanceof InterruptedException)) {
               var3.reportError(var15);
               return;
            }

            return;
         } finally {
            var1.set(false);
         }
      }
   }

   private final boolean isQueued(PlayerInfo var1) {
      Iterable var2 = this.getAsyncQueue().getQueue();
      boolean var10000;
      if (var2 instanceof Collection && ((Collection)var2).isEmpty()) {
         var10000 = false;
      } else {
         Iterator var4 = var2.iterator();

         while (true) {
            if (!var4.hasNext()) {
               var10000 = false;
               break;
            }

            Object var5 = var4.next();
            PlayerInfo var6 = (PlayerInfo)var5;
            if (Intrinsics.checkObject(var6.getString(), var1.getString())
               || Intrinsics.checkObject(var6.getUuid(), var1.getUuid())
               || Intrinsics.checkObject(var6.getIp(), var1.getIp())) {
               var10000 = true;
               break;
            }
         }
      }

      return var10000;
   }

   public final void queueJoin(@Nullable String var1, @Nullable String var2, @Nullable String var3) {
      Throttler var4 = (Throttler)this.platform.getModule(Throttler.class);
      LiteBansCore var5 = (LiteBansCore)this.platform.getModule(LiteBansCore.class);
      if (var5.getConfig().isNotifyDupeipOnJoin() && !var4.isThrottled((byte)0, var2)) {
         ArrayBlockingQueue var10000 = this.queue;
         Intrinsics.handleObject(var1);
         Intrinsics.handleObject(var2);
         var10000.offer(new PlayerInfo(var1, TargetParser.getString2(var2), var3));
         if (var5.isDebugEnabled()) {
            var5.debug("Offer " + var1);
         }
      }
   }

   private static final AsyncQueue getAsyncQueue(SyncQueue var0) {
      return (AsyncQueue)var0.platform.getModule(AsyncQueue.class);
   }
}
