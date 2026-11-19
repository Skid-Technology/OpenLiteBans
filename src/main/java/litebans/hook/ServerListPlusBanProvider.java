package litebans.hook;

import java.io.Closeable;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import litebans.database.DatabaseManager;
import litebans.database.EntryReader;
import litebans.database.PunishmentStorage;
import litebans.lib.kotlin.io.CloseableKt;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.platform.LiteBansPlatform;
import litebans.punishment.PunishmentEntry;
import litebans.punishment.PunishmentType;
import litebans.util.WeakMapThreadLocal;
import net.minecrell.serverlistplus.core.ServerListPlusCore;
import net.minecrell.serverlistplus.core.player.PlayerIdentity;
import net.minecrell.serverlistplus.core.player.ban.BanProvider;
import net.minecrell.serverlistplus.core.plugin.ServerListPlusPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class ServerListPlusBanProvider implements BanProvider {
   private final LiteBansPlatform liteBansPlatform;
   private final ThreadLocal threadLocal;
   private final AtomicBoolean atomicBoolean;

   public ServerListPlusBanProvider(@NotNull LiteBansPlatform var1) {
      this.liteBansPlatform = var1;
      this.threadLocal = new WeakMapThreadLocal();
      this.atomicBoolean = new AtomicBoolean();
   }

   public final ThreadLocal getThreadLocal() {
      return this.threadLocal;
   }

   public final AtomicBoolean getAtomicBoolean() {
      return this.atomicBoolean;
   }

   public final PunishmentEntry getPunishmentEntry(@NotNull UUID var1) {
      WeakHashMap var2 = (WeakHashMap)this.getThreadLocal().get();
      DatabaseManager var3 = (DatabaseManager)this.liteBansPlatform.getModule(DatabaseManager.class);
      PunishmentEntry var4 = null;
      DatabaseManager var5 = var3;

      try {
         PunishmentStorage var7 = var5.openStorage();
         Closeable var8 = var7;
         Throwable var9 = null;

         try {
            var4 = EntryReader.getPunishmentEntry(var7, var1.toString(), null, PunishmentType.BAN, var3.getString(), false, false, 48, null);
            if (var4 != null ? var4.matches(var1.toString(), null, DatabaseManager.currentTimeMillisDefault(var3, false, 1, null)) : false) {
               Intrinsics.handleObject(var2);
               Map var10000 = var2;
               Intrinsics.handleObject(var4);
               var10000.put(var1, var4);
            } else {
               var4 = null;
            }
         } catch (Throwable var16) {
            var9 = var16;
            throw var16;
         } finally {
            CloseableKt.handleCloseable(var8, var9);
         }
      } catch (Exception var18) {
         if (!(var18 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var18); }
         if (!var3.isConnectionError(var18)) {
            throw var18;
         }
      }

      return var4;
   }

   public String getBanReason(@NotNull PlayerIdentity var1) {
      UUID var4 = var1.getUuid();
      PunishmentEntry var2 = (PunishmentEntry)((WeakHashMap)this.getThreadLocal().get()).get(var4);
      if (var2 != null) {
         CharSequence var10000 = var2.getReason();
         if (var10000 != null) {
            return var10000.toString();
         }
      }

      PunishmentEntry var7 = this.getPunishmentEntry(var1.getUuid());
      return (var7 != null ? var7.getReason() : "null").toString();
   }

   public boolean isBanned(@NotNull PlayerIdentity var1) {
      PunishmentEntry var2 = this.getPunishmentEntry(var1.getUuid());
      return var2 != null;
   }

   public String getBanOperator(@NotNull PlayerIdentity var1) {
      UUID var4 = var1.getUuid();
      PunishmentEntry var2 = (PunishmentEntry)((WeakHashMap)this.getThreadLocal().get()).get(var4);
      if (var2 != null) {
         return var2.getExecutorName();
      } else {
         PunishmentEntry var3 = this.getPunishmentEntry(var1.getUuid());
         return var3 != null ? var3.getExecutorName() : null;
      }
   }

   public Date getBanExpiration(@NotNull PlayerIdentity var1) {
      UUID var4 = var1.getUuid();
      PunishmentEntry var2 = (PunishmentEntry)((WeakHashMap)this.getThreadLocal().get()).get(var4);
      if (var2 != null) {
         return var2.getDateEnd() <= 0L ? null : new Date(var2.getDateEnd());
      } else {
         PunishmentEntry var3 = this.getPunishmentEntry(var1.getUuid());
         if (var3 != null) {
            return var3.isPermanent() ? null : new Date(var3.getDateEnd());
         } else {
            return null;
         }
      }
   }

   public final void handleObject(@Nullable Object var1) {
      Intrinsics.handleObject(var1, "");
      ServerListPlusPlugin var2 = (ServerListPlusPlugin)var1;
      ServerListPlusCore var10000 = var2.getCore();
      if (var10000 == null) {
         throw new NullPointerException("core");
      } else {
         ServerListPlusCore var3 = var10000;
         var3.setBanProvider(this);
         this.liteBansPlatform.getLogger().info("Registered as ServerListPlus ban provider.");
      }
   }
}
