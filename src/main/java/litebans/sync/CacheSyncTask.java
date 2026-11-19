package litebans.sync;

import java.io.Closeable;
import java.lang.ref.Reference;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;
import litebans.command.ArgParser;
import litebans.command.CommandRunnable;
import litebans.core.LiteBansCore;
import litebans.database.DatabaseManager;
import litebans.database.PunishmentStorage;
import litebans.lib.kotlin.io.AutoCloseableKt;
import litebans.lib.kotlin.io.CloseableKt;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.text.StringsKt;
import litebans.platform.LiteBansPlatform;
import litebans.randomid.IdScrambler;
import litebans.randomid.RandomIdState;
import org.jetbrains.annotations.NotNull;

public final class CacheSyncTask extends CommandRunnable implements Runnable {
   private final AtomicBoolean atomicBoolean = new AtomicBoolean();
   private final CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();

   public CacheSyncTask(@NotNull LiteBansPlatform var1) {
      super(var1);
   }

   public final AtomicBoolean getAtomicBoolean() {
      return this.atomicBoolean;
   }

   public final CopyOnWriteArrayList getCopyOnWriteArrayList() {
      return this.copyOnWriteArrayList;
   }

   @Override
   public void run() {
      try {
         DatabaseManager var1 = (DatabaseManager)this.getPlatform().getModule(DatabaseManager.class);
         SyncManager var2 = (SyncManager)this.getPlatform().getModule(SyncManager.class);
         CommandRunnable var3 = this;
         LiteBansCore var10 = (LiteBansCore)var3.getPlatform().getModule(LiteBansCore.class);
         if (var10.isDebugEnabled()) {
            var10.debug(RandomIdState.Companion.getSoftLazy().isInitialized());
         }

         IdScrambler var11 = (IdScrambler)RandomIdState.Companion.getSoftLazy().getValue();
         Intrinsics.handleObject(var1);
         Intrinsics.handleObject(var2);
         this.handleCacheSyncTask(this, var11, var1, var2);
         byte var4 = 100;

         for (int var5 = 0; var5 < var4; var5++) {
            this.handleCacheSyncTask(this, var11, var1, var2);
            Thread.sleep(70L);
         }
      } catch (InterruptedException var9) {
         this.run2();
      }

      this.atomicBoolean.set(false);
   }

   private final void run2() {
      this.copyOnWriteArrayList.clear();
   }

   private final void handleCacheSyncTask(CacheSyncTask var1, IdScrambler var2, DatabaseManager var3, SyncManager var4) {
      boolean var58;
      if (!var1.copyOnWriteArrayList.isEmpty()) {
         byte[][] var10000 = var2.getByteArrayArray();
         if (var10000 == null) {
            int var7 = 0;

            byte[][] var8;
            for (var8 = new byte[4][]; var7 < 4; var7++) {
               var8[var7] = ArgParser.getByteArray();
            }

            var10000 = var8;
         }

         byte[][] var5 = var10000;
         var2.setByteArrayArray(var5);
         Object[] var6 = (Object[])var5;
         int var48 = 0;
         int var9 = var6.length;

         while (true) {
            if (var48 >= var9) {
               var58 = false;
               break;
            }

            Object var10 = var6[var48];
            byte[] var11 = (byte[])var10;
            if (var11.length == 0) {
               var58 = true;
               break;
            }

            var48++;
         }

         if (var58) {
            DatabaseManager var46 = var3;

            try {
               PunishmentStorage var49 = var46.openStorage();
               Closeable var51 = var49;
               Throwable var52 = null;

               try {
                  PunishmentStorage var13 = var49;
                  if (var49.isOpen()) {
                     try {
                        ResultSet var17 = var4.getResultSet(var13);
                        ResultSet var18 = var17;
                        AutoCloseable var20 = var17;
                        Throwable var21 = null;

                        try {
                           while (var18.next()) {
                              var5[var18.getInt("id")] = var18.getBytes("b");
                           }
                        } catch (Throwable var40) {
                           var21 = var40;
                           throw var40;
                        } finally {
                           AutoCloseableKt.handleAutoCloseable(var20, var21);
                        }
                     } catch (SQLException var42) {
                        var3.reportError(var42);
                     }
                  }
               } catch (Throwable var43) {
                  var52 = var43;
                  throw var43;
               } finally {
                  CloseableKt.handleCloseable(var51, var52);
               }
            } catch (Exception var45) {
               if (!(var45 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var45); }
               if (!var3.isConnectionError(var45)) {
                  throw var45;
               }
            }
         }

         try {
            SyncManager var47 = var4;

            for (Object var54 : var1.copyOnWriteArrayList) {
               Byte var12 = (Byte)var54;
               if (var12 >= 0) {
                  Iterable var14 = var47.getCopyOnWriteArrayList();
                  boolean var61;
                  if (var14 instanceof Collection && ((Collection)var14).isEmpty()) {
                     var61 = true;
                  } else {
                     label353: {
                        for (Object var55 : var14) {
                           Reference var56 = (Reference)var55;
                           ByteKeyedMap var59 = (ByteKeyedMap)var56.get();
                           boolean var60;
                           if (var59 != null) {
                              byte var57 = var59.getByteValue();
                              var60 = var12 == var57;
                           } else {
                              var60 = false;
                           }

                           if (var60) {
                              var61 = false;
                              break label353;
                           }
                        }

                        var61 = true;
                     }
                  }

                  if (var61) {
                     var47.loadCacheBucket(var12, var2);
                  }
               }
            }

            var47.checkIdScrambler(var2);
         } catch (IndexOutOfBoundsException var39) {
            var1.handleException(var39, var2, var4);
         }

         var1.run2();
      }
   }

   private final void handleException(Exception var1, IdScrambler var2, SyncManager var3) {
      try {
      String var10;
      Logger var10000;
      StringBuilder var10001;
      label16: {
         var10000 = this.getPlatform().getLogger();
         var10001 = new StringBuilder().append("Cache desync, reloading. (error ");
         var10 = var1.getMessage();
         if (var10 != null) {
            CharSequence var8 = var10;
            char[] var6 = new char[]{' '};
            List var9 = StringsKt.getList(var8, var6, false, 0, 6, null);
            if (var9 != null) {
               var10 = (String)var9.get(1);
               break label16;
            }
         }

         var10 = null;
      }

      var10000.warning(var10001.append(var10).append(')').toString());
      var2.setByteArrayArray(null);
      var3.setIntValue(0);
      CommandRunnable var4 = this;
      LiteBansCore var7 = (LiteBansCore)var4.getPlatform().getModule(LiteBansCore.class);
      if (var7.isDebugEnabled()) {
         var7.debug(var1);
      }

      Thread.sleep(1000L);
   } catch (Exception var9999) { throw litebans.util.Unreachable.rethrow(var9999); }
   }
}
