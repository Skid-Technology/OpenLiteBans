package litebans.api.impl;

import java.io.Closeable;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;
import litebans.api.Database;
import litebans.api.Entry;
import litebans.core.LiteBansCore;
import litebans.database.CloseablePreparedStatement;
import litebans.database.DatabaseManager;
import litebans.database.EntryReader;
import litebans.database.PunishmentStorage;
import litebans.database.Table;
import litebans.lib.compress.archivers.tar.TargetParser;
import litebans.lib.kotlin.collections.CollectionsKt;
import litebans.lib.kotlin.io.CloseableKt;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.text.StringsKt;
import litebans.platform.LiteBansPlatform;
import litebans.punishment.MuteManager;
import litebans.punishment.PlayerHistory;
import litebans.punishment.PunishmentEntry;
import litebans.punishment.PunishmentType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class DatabaseImpl extends Database {
   private final LiteBansPlatform liteBansPlatform;
   private int intValue;

   public DatabaseImpl(@NotNull LiteBansPlatform var1) {
      this.liteBansPlatform = var1;
   }

   public final int getIntValue() {
      return this.intValue;
   }

   @Override
   public boolean isPlayerBanned(@Nullable UUID var1, @Nullable String var2, @Nullable String var3) {
      return this.getEntry(var1, var2, var3, PunishmentType.BAN) != null;
   }

   @Override
   public boolean isPlayerMuted(@Nullable UUID var1, @Nullable String var2, @Nullable String var3) {
      return this.getEntry(var1, var2, var3, PunishmentType.MUTE) != null;
   }

   private final Entry getEntry(UUID var1, String var2, String var3, PunishmentType var4) {
      this.run();
      if (var1 == null && var2 == null) {
         return null;
      } else {
         DatabaseManager var5 = (DatabaseManager)this.liteBansPlatform.getModule(DatabaseManager.class);
         String var10000;
         if (Intrinsics.checkObject(var3, "__DEFAULT__")) {
            var10000 = var5.getString();
         } else {
            var10000 = var3;
            if (var3 == null) {
               var10000 = var5.getString();
            }
         }

         String var6 = var10000;
         if (var1 != null && var4 == PunishmentType.MUTE) {
            MuteManager var7 = (MuteManager)this.liteBansPlatform.getModule(MuteManager.class);
            PunishmentEntry var8 = var7.getActiveMute(var1, var2, var3);
            if (var8 != null) {
               return EventDispatcher.INSTANCE.getEntry(this.liteBansPlatform, var8);
            }
         }

         Closeable var18 = var5.openStorage();
         Throwable var19 = null;

         Object var13;
         try {
            PunishmentStorage var9 = (PunishmentStorage)var18;
            PunishmentEntry var20 = EntryReader.getPunishmentEntry(var9, Objects.toString(var1), var2, var4, var6, false, false, 48, null);
            if (var20 != null) {
               PunishmentEntry var11 = var20;
               return EventDispatcher.INSTANCE.getEntry(this.liteBansPlatform, var11);
            }

            var13 = null;
         } catch (Throwable var16) {
            var19 = var16;
            throw var16;
         } finally {
            CloseableKt.handleCloseable(var18, var19);
         }

         return (Entry)var13;
      }
   }

   private final Entry getEntry(long var1, String var3, PunishmentType var4) {
      this.run();
      if (var1 < 0L) {
         String var18 = "Negative ID: " + var1;
         throw new IllegalArgumentException(var18.toString());
      } else {
         DatabaseManager var5 = (DatabaseManager)this.liteBansPlatform.getModule(DatabaseManager.class);
         String var6 = Intrinsics.checkObject(var3, "__DEFAULT__") ? var5.getString() : var3;
         Closeable var7 = var5.openStorage();
         Throwable var8 = null;

         Object var13;
         try {
            PunishmentStorage var9 = (PunishmentStorage)var7;
            String var10003 = var6;
            if (var6 == null) {
               var10003 = var5.getString();
            }

            PunishmentEntry var10000 = EntryReader.getPunishmentEntry(var9, var1, var4, var10003, false, 8, null);
            if (var10000 != null) {
               PunishmentEntry var11 = var10000;
               return EventDispatcher.INSTANCE.getEntry(this.liteBansPlatform, var11);
            }

            var13 = null;
         } catch (Throwable var16) {
            var8 = var16;
            throw var16;
         } finally {
            CloseableKt.handleCloseable(var7, var8);
         }

         return (Entry)var13;
      }
   }

   @Override
   public boolean isPlayerBanned(@Nullable UUID var1, @Nullable String var2) {
      return this.isPlayerBanned(var1, var2, "__DEFAULT__");
   }

   @Override
   public boolean isPlayerMuted(@Nullable UUID var1, @Nullable String var2) {
      return this.isPlayerMuted(var1, var2, "__DEFAULT__");
   }

   @Override
   public Entry getBan(@Nullable UUID var1, @Nullable String var2, @Nullable String var3) {
      return this.getEntry(var1, var2, var3, PunishmentType.BAN);
   }

   @Override
   public Entry getBan(long var1, @Nullable String var3) {
      return this.getEntry(var1, var3, PunishmentType.BAN);
   }

   @Override
   public Entry getMute(@Nullable UUID var1, @Nullable String var2, @Nullable String var3) {
      return this.getEntry(var1, var2, var3, PunishmentType.MUTE);
   }

   @Override
   public Entry getMute(long var1, @Nullable String var3) {
      return this.getEntry(var1, var3, PunishmentType.MUTE);
   }

   @Override
   public Entry getWarning(@Nullable UUID var1, @Nullable String var2, @Nullable String var3) {
      return this.getEntry(var1, var2, var3, PunishmentType.WARN);
   }

   @Override
   public Entry getWarning(long var1, @Nullable String var3) {
      return this.getEntry(var1, var3, PunishmentType.WARN);
   }

   @Override
   public Entry getKick(@Nullable UUID var1, @Nullable String var2, @Nullable String var3) {
      return this.getEntry(var1, var2, var3, PunishmentType.KICK);
   }

   @Override
   public Entry getKick(long var1, @Nullable String var3) {
      return this.getEntry(var1, var3, PunishmentType.KICK);
   }

   @Override
   public PreparedStatement prepareStatement(@NotNull String var1) {
      String var2 = var1;
      this.run();
      LiteBansCore var3 = (LiteBansCore)this.liteBansPlatform.getModule(LiteBansCore.class);
      DatabaseManager var4 = (DatabaseManager)this.liteBansPlatform.getModule(DatabaseManager.class);
      PunishmentStorage var5 = var4.openStorage();
      if (var5.isClosed()) {
         throw new IllegalStateException("Database connection not available");
      } else {
         if (StringsKt.checkCharSequence3(var1, '{', false, 2, null)) {
            var2 = StringsKt.getString(var1, "{table_prefix}", var3.getConfig().getString6(), false, 4, null);
            Table[] var6 = Table.values();
            int var7 = 0;

            for (int var8 = var6.length; var7 < var8; var7++) {
               Table var9 = var6[var7];
               StringBuilder var10001 = new StringBuilder().append('{');
               String var10 = var9.name();
               var2 = StringsKt.getString(var2, var10001.append(var10.toLowerCase(Locale.ENGLISH)).append('}').toString(), var9.toString(), false, 4, null);
            }
         }

         int var11 = this.intValue++;
         return new CloseablePreparedStatement(var5.prepare(var2).getPreparedStatement(), var5);
      }
   }

   @Override
   public Collection getUsersByIP(@Nullable String var1) {
      this.run();
      if (var1 != null && TargetParser.INSTANCE.checkString2(var1) && !TargetParser.checkString7(var1)) {
         DatabaseManager var2 = (DatabaseManager)this.liteBansPlatform.getModule(DatabaseManager.class);
         Closeable var3 = var2.openStorage();
         Throwable var4 = null;

         Collection var19;
         try {
            PunishmentStorage var5 = (PunishmentStorage)var3;
            List var7 = EntryReader.getList(var5, var1, 0, 2, null);
            if (!var7.isEmpty()) {
               Iterable var8 = var7;
               Collection var11 = new ArrayList();

               for (Object var14 : var8) {
                  PlayerHistory var15 = (PlayerHistory)var14;
                  if (TargetParser.checkString7(var15.getUuid())) {
                     var11.add(var14);
                  }
               }

               var8 = (List)var11;
               var11 = new ArrayList(CollectionsKt.getInt(var8, 10));

               for (Object var27 : var8) {
                  PlayerHistory var28 = (PlayerHistory)var27;
                  var11.add(UUID.fromString(var28.getUuid()));
               }

               return CollectionsKt.getSet((List)var11);
            }

            var19 = CollectionsKt.getList();
         } catch (Throwable var22) {
            var4 = var22;
            throw var22;
         } finally {
            CloseableKt.handleCloseable(var3, var4);
         }

         return var19;
      } else {
         throw new IllegalArgumentException("Target is not an IP address: " + var1);
      }
   }

   @Override
   public String getPlayerName(@NotNull UUID var1) {
      this.run();
      DatabaseManager var2 = (DatabaseManager)this.liteBansPlatform.getModule(DatabaseManager.class);
      Closeable var3 = var2.openStorage();
      Throwable var4 = null;

      String var7;
      try {
         PunishmentStorage var5 = (PunishmentStorage)var3;
         PlayerHistory var10000 = var5.findLatestHistory(var1.toString());
         var7 = var10000 != null ? var10000.getString() : null;
      } catch (Throwable var10) {
         var4 = var10;
         throw var10;
      } finally {
         CloseableKt.handleCloseable(var3, var4);
      }

      return var7;
   }

   @Override
   public UUID getPlayerUUID(@NotNull String var1) {
      this.run();
      DatabaseManager var2 = (DatabaseManager)this.liteBansPlatform.getModule(DatabaseManager.class);
      Closeable var3 = var2.openStorage();
      Throwable var4 = null;

      UUID var9;
      try {
         PunishmentStorage var5 = (PunishmentStorage)var3;
         TargetParser var10000 = TargetParser.INSTANCE;
         PlayerHistory var10001 = var5.findLatestHistory(var1);
         String var7 = var10000.getString(var10001 != null ? var10001.getUuid() : null);
         if (!TargetParser.checkString7(var7)) {
            return null;
         }

         var9 = UUID.fromString(var7);
      } catch (Throwable var12) {
         var4 = var12;
         throw var12;
      } finally {
         CloseableKt.handleCloseable(var3, var4);
      }

      return var9;
   }

   @Override
   public String toString() {
      return "Database";
   }

   private final void run() {
      if (this.liteBansPlatform.isPrimaryThread()) {
         throw new IllegalStateException("Database queries cannot be executed on the main server thread!");
      }
   }
}
