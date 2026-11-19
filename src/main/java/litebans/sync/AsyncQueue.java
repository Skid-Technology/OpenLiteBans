package litebans.sync;

import java.io.Closeable;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import litebans.core.PluginModule;
import litebans.database.DatabaseManager;
import litebans.database.EntryReader;
import litebans.database.PunishmentStorage;
import litebans.lib.kotlin.NoWhenBranchMatchedException;
import litebans.lib.kotlin.io.CloseableKt;
import litebans.platform.LiteBansPlatform;
import litebans.punishment.PlayerInfo;
import litebans.util.Unreachable;
import org.jetbrains.annotations.NotNull;

public final class AsyncQueue extends PluginModule implements Runnable {
   public static final AsyncQueueCompanion Companion = new AsyncQueueCompanion(null);
   private final ArrayBlockingQueue queue = new ArrayBlockingQueue(2048);
   private final AtomicBoolean atomicBoolean = new AtomicBoolean();
   public static final long longValue = 64L;
   public static final int intValue = 2048;

   public AsyncQueue(@NotNull LiteBansPlatform var1) {
      super(var1);
   }

   public final ArrayBlockingQueue getQueue() {
      return this.queue;
   }

   @Override
   public void onEnable() {
      AtomicBoolean var1 = this.atomicBoolean;
      var1.set(false);
      this.platform.runAsyncTimer(this, 64L, 64L);
   }

   @Override
   public void run() {
      AtomicBoolean var1 = this.atomicBoolean;
      if (var1.compareAndSet(false, true)) {
         DatabaseManager var3 = (DatabaseManager)this.platform.getModule(DatabaseManager.class);

         try {
            int var4 = this.getQueue().size() <= 256
               ? 0
               : (this.getQueue().size() <= 512 ? 1 : (this.getQueue().size() <= 1024 ? 2 : (this.getQueue().size() < 2048 ? 3 : 4)));
            short var10000;
            switch (var4) {
               case 0:
                  var10000 = 40;
                  break;
               case 1:
                  var10000 = 60;
                  break;
               case 2:
                  var10000 = 256;
                  break;
               case 3:
                  var10000 = 512;
                  break;
               case 4:
                  var10000 = 2048;
                  break;
               default:
                  Unreachable.getVoid();
                  throw new NoWhenBranchMatchedException();
            }

            short var5 = var10000;
            if (DatabaseManager.hasFreeConnectionsDefault(var3, 0, 1, null) && var4 == 0) {
               var5 = 1;
            }

            DatabaseManager var6 = var3;

            try {
               PunishmentStorage var8 = var6.openStorage();
               Closeable var9 = var8;
               Throwable var10 = null;

               try {
                  PunishmentStorage var13 = var8;
                  int var15 = 1;
                  short var16 = var5;
                  if (var15 <= var5) {
                     while (true) {
                        PlayerInfo var33 = (PlayerInfo)this.queue.poll();
                        if (var33 == null) {
                           return;
                        }

                        PlayerInfo var17 = var33;
                        EntryReader.handlePunishmentStorage(var13, var17.getString(), var17.getUuid(), var17.getIp(), false, false, 24, null);
                        if (var15 == var16) {
                           return;
                        }

                        var15++;
                     }
                  }

                  return;
               } catch (Throwable var28) {
                  var10 = var28;
                  throw var28;
               } finally {
                  CloseableKt.handleCloseable(var9, var10);
               }
            } catch (Exception var30) {
               if (!(var30 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var30); }
               if (var3.isConnectionError(var30)) {
                  return;
               }

               throw var30;
            }
         } catch (Exception var31) {
            if (!(var31 instanceof InterruptedException)) {
               var3.reportError(var31);
               return;
            }
         } finally {
            var1.set(false);
         }
      }
   }

   @Override
   public void onReload() {
      this.onDisable();
      this.onEnable();
   }
}
