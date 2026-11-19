package litebans.importer;

import java.sql.Connection;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;
import litebans.command.ArgUtils;
import litebans.command.CommandRunnable;
import litebans.config.Message;
import litebans.core.LiteBansCore;
import litebans.core.MessageSender;
import litebans.database.DatabaseManager;
import litebans.database.EntryReader;
import litebans.database.PunishmentStorage;
import litebans.hook.UsernamePattern;
import litebans.lib.kotlin.Lazy;
import litebans.lib.kotlin.LazyKt;
import litebans.lib.kotlin.NoWhenBranchMatchedException;
import litebans.lib.kotlin.jvm.internal.DefaultConstructorMarker;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.jvm.internal.KtAnnotation2;
import litebans.lib.kotlin.text.StringsKt;
import litebans.platform.LiteBansPlatform;
import litebans.platform.Sender;
import litebans.punishment.EntryPlaceholders;
import litebans.punishment.Profile;
import litebans.punishment.PunishmentEntry;
import litebans.punishment.PunishmentType;
import litebans.punishment.ServerScope;
import litebans.sync.QueueFeederTask;
import litebans.util.Unreachable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@KtAnnotation2
public abstract class Importer extends CommandRunnable {
   private final String string;
   private String string2;
   private final int intValue;
   private final long longValue;
   private final Logger logger;
   private final Lazy lazy;
   private final Lazy lazy2;
   private final Lazy lazy3;

   public Importer(@NotNull LiteBansPlatform var1, @NotNull String var2, @NotNull String var3, int var4, long var5) {
      super(var1);
      this.string = var2;
      this.string2 = var3;
      this.intValue = var4;
      this.longValue = var5;
      this.logger = var1.getLogger();
      this.lazy = LazyKt.getLazy(Importer::getHashMap2);
      this.lazy2 = LazyKt.getLazy(Importer::getAtomicLong3);
      this.lazy3 = LazyKt.getLazy(Importer::getAtomicLong4);
   }

   public Importer(LiteBansPlatform var1, String var2, String var3, int var4, long var5, int var7, DefaultConstructorMarker var8) {
      this(var1, var2, ((var7 & 4) != 0 ? "" : var3), ((var7 & 8) != 0 ? 4096 : var4), ((var7 & 16) != 0 ? 5000L : var5));
   }

   public final String getString() {
      return this.string;
   }

   public final String getString2() {
      return this.string2;
   }

   public final void setString2(@NotNull String var1) {
      this.string2 = var1;
   }

   public final int getIntValue() {
      return this.intValue;
   }

   public final long getLongValue() {
      return this.longValue;
   }

   public final Logger getLogger() {
      return this.logger;
   }

   public final HashMap getHashMap() {
      Lazy var1 = this.lazy;
      return (HashMap)var1.getValue();
   }

   public final AtomicLong getAtomicLong() {
      Lazy var1 = this.lazy2;
      return (AtomicLong)var1.getValue();
   }

   public final AtomicLong getAtomicLong2() {
      Lazy var1 = this.lazy3;
      return (AtomicLong)var1.getValue();
   }

   public boolean checkString(@NotNull String var1) {
      return StringsKt.checkString(this.string, var1, true);
   }

   public boolean check() {
      return true;
   }

   public boolean check2() {
      return true;
   }

   public void handleSender(@NotNull Sender var1) {
      MessageSender.handleSenderDefault(
         CommandRunnable.messageSender,
         var1,
         EntryPlaceholders.getString(Message.IMPORT_FINISH.getString("bans", this.getAtomicLong()), "ipbans", this.getAtomicLong2()),
         null,
         4,
         null
      );
   }

   public abstract void handleString(@NotNull String var1, @NotNull Connection var2, @NotNull PunishmentStorage var3, boolean var4);

   public Void getVoid() {
      Unreachable.getVoid();
      throw new NoWhenBranchMatchedException();
   }

   public final String getString(@Nullable String var1) {
      if (var1 != null && !StringsKt.checkCharSequence(var1, '#', false, 2, null)) {
         String var2 = (String)this.getHashMap().get(var1);
         if (var2 != null) {
            return var2;
         } else {
            CommandRunnable var4 = this;
            boolean var3 = ((LiteBansCore)var4.getPlatform().getModule(LiteBansCore.class)).getConfig().isOnlineMode();
            String var6;
            if (var3) {
               Profile var10000 = UsernamePattern.mojangApi.getProfile(var1);
               if (var10000 == null) {
                  return "#";
               }

               var6 = String.valueOf(var10000.getUuid());
            } else {
               var6 = ArgUtils.getString(var1);
            }

            String var5 = var6;
            this.getHashMap().put(var1, var5);
            return var5;
         }
      } else {
         return "#";
      }
   }

   public String getString(@NotNull UUID var1) {
      String var10000 = UsernamePattern.mojangApi.getString(var1.toString());
      if (var10000 == null) {
         var10000 = "#";
      }

      return var10000;
   }

   public final void handlePunishmentStorage(@NotNull PunishmentStorage var1, @NotNull PunishmentEntry var2, @NotNull AtomicLong var3, @NotNull AtomicLong var4) {
      String var5 = var2.getIp();
      if (EntryReader.getPunishmentEntry(var1, null, var5, var2.getType(), false, false, 24, null) == null) {
         var1.insertEntry(var2);
         if (var2.getType() == PunishmentType.BAN) {
            AtomicLong[] var6 = new AtomicLong[]{var3, var4};
            var6 = var6;
            int var8 = 0;

            for (int var9 = var6.length; var8 < var9; var8++) {
               AtomicLong var10 = var6[var8];
               var10.incrementAndGet();
            }
         }
      } else {
         this.getPlatform().getLogger().warning("Ignoring duplicate ban for " + var5);
      }
   }

   public final void handlePunishmentStorage(
      @NotNull PunishmentStorage var1,
      @Nullable String var2,
      @NotNull String var3,
      long var4,
      long var6,
      @Nullable String var8,
      @Nullable UUID var9,
      @Nullable UUID var10,
      @Nullable String var11,
      @NotNull AtomicLong var12,
      @NotNull PunishmentType var13
   ) {
      String var10000;
      label57: {
         if (var9 != null) {
            var10000 = var9.toString();
            if (var10000 != null) {
               break label57;
            }
         }

         var10000 = this.getString(var2);
      }

      String var14;
      label52: {
         var14 = var10000;
         if (var10 != null) {
            var10000 = var10.toString();
            if (var10000 != null) {
               break label52;
            }
         }

         var10000 = this.getString(var8);
      }

      String var15 = var10000;
      if (var11 != null || !StringsKt.checkString(var14, "#", false, 2, null)) {
         CommandRunnable var17 = this;
         PunishmentEntry var16 = EntryReader.getPunishmentEntry(
            var1, var14, var11, var13, ((DatabaseManager)var17.getPlatform().getModule(DatabaseManager.class)).getString(), false, false, 48, null
         );
         if (var13 != PunishmentType.WARN && var16 != null && Intrinsics.checkObject(var16.getUuid(), var14)) {
            this.getPlatform().getLogger().warning("Ignoring duplicate " + var13 + " for " + var2 + " (ip=" + var11 + ')');
         } else {
            boolean var21 = var11 != null;
            CommandRunnable var19 = this;
            String var18 = ((LiteBansCore)var19.getPlatform().getModule(LiteBansCore.class)).getConfig().getString2();
            CommandRunnable var20 = this;
            String var22 = ServerScope.serverScopeParser
               .getString(
                  ((LiteBansCore)var20.getPlatform().getModule(LiteBansCore.class)).getConfig().getDefaultServerScope(), var18, this.getPlatform().getConsole()
               );
            PunishmentEntry var23 = new PunishmentEntry(
               var13, var14, var11, var3, var15, var8, var22, var18, var4, var6, (byte)0, false, var21, false, 0L, 27648, null
            );
            var1.insertEntry(var23);
            if (var13 == PunishmentType.BAN) {
               var12.incrementAndGet();
               if (var21) {
                  this.getAtomicLong2().incrementAndGet();
               }
            }
         }

         if (var2 != null && var14 != null) {
            new QueueFeederTask(this.getPlatform(), var2, var14, "#").run();
         }

         if (var8 != null && var15 != null) {
            new QueueFeederTask(this.getPlatform(), var8, var15, "#").run();
         }
      }
   }

   public static void handlePunishmentStorageDefault(
      Importer var0,
      PunishmentStorage var1,
      String var2,
      String var3,
      long var4,
      long var6,
      String var8,
      UUID var9,
      UUID var10,
      String var11,
      AtomicLong var12,
      PunishmentType var13,
      int var14,
      Object var15
   ) {
      if (var15 != null) {
         throw new UnsupportedOperationException("");
      } else {
         if ((var14 & 64) != 0) {
            var9 = null;
         }

         if ((var14 & 128) != 0) {
            var10 = null;
         }

         if ((var14 & 256) != 0) {
            var11 = null;
         }

         if ((var14 & 512) != 0) {
            var12 = var0.getAtomicLong();
         }

         if ((var14 & 1024) != 0) {
            var13 = PunishmentType.BAN;
         }

         var0.handlePunishmentStorage(var1, var2, var3, var4, var6, var8, var9, var10, var11, var12, var13);
      }
   }

   private static final HashMap getHashMap2() {
      return new HashMap();
   }

   private static final AtomicLong getAtomicLong3() {
      return new AtomicLong();
   }

   private static final AtomicLong getAtomicLong4() {
      return new AtomicLong();
   }

   @Override
   public void run() {
      this.getVoid();
   }
}
