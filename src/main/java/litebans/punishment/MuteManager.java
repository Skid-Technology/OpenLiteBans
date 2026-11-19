package litebans.punishment;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import litebans.command.CommandRunnable;
import litebans.command.MuteChatHandler;
import litebans.config.Message;
import litebans.core.CoreFieldAccess;
import litebans.core.LiteBansCore;
import litebans.core.PluginModule;
import litebans.database.DatabaseManager;
import litebans.database.PunishmentStorage;
import litebans.database.QueryRunner;
import litebans.database.QueryStatement;
import litebans.database.SqlStorage;
import litebans.database.Table;
import litebans.lib.compress.archivers.tar.TargetParser;
import litebans.lib.kotlin.NoWhenBranchMatchedException;
import litebans.lib.kotlin.collections.CollectionsKt;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.platform.LiteBansPlatform;
import litebans.platform.Sender;
import litebans.util.Unreachable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class MuteManager extends PluginModule {
   public MuteChatHandler muteChatHandler;

   public MuteManager(@NotNull LiteBansPlatform var1) {
      super(var1);
   }

   public final MuteChatHandler getMuteChatHandler() {
      if (this.muteChatHandler != null) {
         return this.muteChatHandler;
      } else {
         Intrinsics.handleString3("");
         return null;
      }
   }

   public final void setMuteChatHandler(@NotNull MuteChatHandler var1) {
      this.muteChatHandler = var1;
   }

   @Override
   public void onEnable() {
      this.setMuteChatHandler(new MuteChatHandler(this.platform));
   }

   public final void handlePunishmentStorage(@NotNull PunishmentStorage var1) {
      Sender[] var2 = var1.getPlatform().getOnlineSenders();
      Intrinsics.handleObject(var2);
      if (var2.length != 0 && !var1.isClosed()) {
         if (var1 instanceof SqlStorage) {
            SqlStorage var3 = (SqlStorage)var1;
            SqlStorage var5 = var3;
            Table var6 = Table.MUTES;
            Sender[] var7 = var2;
            List var10000;
            if (var2.length == 0) {
               var10000 = CollectionsKt.getList();
            } else {
               ArrayList var9 = new ArrayList(Math.max(1, var2.length / SqlStorage.sqlWhereBuilder.getInt()));
               int var10 = 0;
               int var11 = 0;
               HashSet var12 = new HashSet(var2.length);
               ArrayList var13 = new ArrayList(var2.length * 2);

               while (true) {
                  StringBuilder var16 = new StringBuilder(
                     "SELECT * FROM " + var6 + " WHERE(" + "active=" + var5.getNotificationManager().getCharSequence() + " AND(until<1 OR until>?)" + ")AND("
                  );

                  for (int var14 = var7.length; var10 < var14; var11++) {
                     Sender var15 = var7[var10];
                     String var17 = var15.getIp();
                     var16.append("uuid=?");
                     var13.add(var15.getUuidString());
                     if (var17 != null && !var12.contains(var17)) {
                        var16.append(" OR(ipban=").append(var5.getNotificationManager().getCharSequence()).append(" AND ").append("ip=?)");
                        var13.add(var17);
                        var12.add(var17);
                     }

                     if (var11 > SqlStorage.sqlWhereBuilder.getInt()) {
                        break;
                     }

                     if (var10 != var14 - 1) {
                        var16.append(" OR ");
                     }

                     var10++;
                  }

                  var11 = 0;
                  var16.append(")AND(server_scope='*' OR server_scope=? OR server_scope=?)");
                  String var36 = var16.toString();
                  LiteBansCore var39 = var5.getLiteBansCore();
                  if (var39.isDebugEnabled()) {
                     var39.debug(var10 + ": " + var36);
                  }

                  QueryStatement var40 = var5.prepare(var36);
                  QueryRunner.getQueryStatement(var40);
                  QueryStatement var18 = var40;

                  for (Object var27 : var13) {
                     String var28 = (String)var27;
                     var18.getQueryStatement(var28);
                  }

                  String var20 = var7[0].getServerName();
                  var40.getQueryStatement(var20);
                  var20 = var5.getDatabaseManager().getString();
                  var40.getQueryStatement(var20);
                  var9.add(var40);
                  if (var10 >= var7.length) {
                     var10000 = var9;
                     break;
                  }

                  var13.clear();
               }
            }

            List var4 = var10000;
            ConcurrentHashMap var29 = new ConcurrentHashMap();

            try {
            for (Object var31 : var4) {
               QueryStatement var32 = (QueryStatement)var31;
               ResultSet var33 = QueryRunner.getResultSet2(var32);
               if (var33.next()) {
                  while (true) {
                     PunishmentEntry var34 = var3.readEntry(var33, PunishmentType.MUTE);
                     String var35 = var34.getUuid();
                     if (var35 != null && var35.length() == 36) {
                        var29.put(UUID.fromString(var35), var34);
                     }

                     Object[] var37 = ((SqlStorage)var1).getPlatform().getOnlineSenders();
                     Object[] var41 = var37;
                     Collection var43 = new ArrayList();
                     int var46 = 0;

                     for (int var21 = var37.length; var46 < var21; var46++) {
                        Object var22 = var41[var46];
                        Sender var23 = (Sender)var22;
                        if (var34.matches(
                           var23.getUuidString(), var23.getIp(), DatabaseManager.currentTimeMillisDefault(var1.getDatabaseManager(), false, 1, null)
                        )) {
                           var43.add(var22);
                        }
                     }

                     for (Object var44 : (List)var43) {
                        Sender var19 = (Sender)var44;
                        var29.put(var19.getUuid(), var34);
                     }

                     if (!var33.next()) {
                        break;
                     }
                  }
               }
            }
            } catch (Exception var50) {
               throw new RuntimeException(var50);
            }

            if (this.getMuteChatHandler().getConcurrentHashMap().size() != var29.size()) {
               this.getMuteChatHandler().setConcurrentHashMap(var29);
            }
         }
      }
   }

   public final void cacheMute(@NotNull String var1, @NotNull PunishmentEntry var2) {
      try {
         UUID var3 = UUID.fromString(TargetParser.getString2(var1));
         this.getMuteChatHandler().getConcurrentHashMap().put(var3, var2);
      } catch (IllegalArgumentException var5) {
         LiteBansCore var4 = (LiteBansCore)this.platform.getModule(LiteBansCore.class);
         LiteBansCore.handleObjectDefault(var4, var5, 0, 2, null);
      }
   }

   public final void syncMute(@Nullable String var1, @Nullable String var2, @Nullable String var3, @Nullable String var4) {
      LiteBansPlatform var10002 = this.platform;
      Intrinsics.handleObject(var2);
      new MuteSyncTask(var10002, var1, var2, var3, var4).run();
   }

   public final PunishmentEntry getActiveMute(@NotNull UUID var1, @Nullable String var2, @Nullable String var3) {
      LiteBansCore var4 = (LiteBansCore)this.platform.getModule(LiteBansCore.class);
      DatabaseManager var5 = (DatabaseManager)this.platform.getModule(DatabaseManager.class);
      if (Intrinsics.checkObject(CoreFieldAccess.getObject(var4), Message.getString(var4, false, true))) {
         return null;
      } else {
         PunishmentEntry var6 = (PunishmentEntry)this.getMuteChatHandler().getConcurrentHashMap().get(var1);
         if (var6 != null) {
            String var7 = var1.toString();
            if (var6.matches(var7, var2, DatabaseManager.currentTimeMillisDefault(var5, false, 1, null))) {
               ServerScopeParser var10000 = ServerScope.serverScopeParser;
               String var10001 = var6.getServerScope();
               if (var10001 == null) {
                  var10001 = ServerScope.serverScopeParser.getString();
               }

               String var10002 = var3;
               if (var3 == null) {
                  var10002 = var5.getString();
               }

               if (var10000.checkString(var10001, var10002)) {
                  return var6;
               }
            } else if (Intrinsics.checkObject(var2, var6.getIp())) {
               this.getMuteChatHandler().getConcurrentHashMap().remove(var1);
            }

            if (var4.isDebugEnabled()) {
               var4.debug("M! " + var1);
            }
         }

         return null;
      }
   }

   public final void syncMute(@Nullable String var1, @Nullable String var2) {
      if (var1 != null) {
         MuteSyncTask var3 = new MuteSyncTask(this.platform, null, var1, var2, null);
         CommandRunnable var5 = var3;
         Object[] var7 = var5.getPlatform().getOnlineSenders();
         int var8 = 0;
         int var9 = var7.length;

         Object var10000;
         while (true) {
            if (var8 >= var9) {
               var10000 = null;
               break;
            }

            Object var10 = var7[var8];
            Sender var11 = (Sender)var10;
            if (Intrinsics.checkObject(var11.getUuidString(), var1)) {
               var10000 = var10;
               break;
            }

            var8++;
         }

         Sender var4 = (Sender)var10000;
         if (var4 != null) {
            var3.run();
         }
      }
   }

   public final boolean uncache(@Nullable PunishmentEntry var1) {
      if ((var1 != null ? var1.getType() : null) == PunishmentType.MUTE) {
         boolean var2 = CollectionsKt.checkIterable(this.getMuteChatHandler().getConcurrentHashMap().entrySet(), o -> checkPunishmentEntry(var1, (Entry) o));
         if (var2) {
            this.syncMute(var1.getUuid(), var1.getIp());
         }

         return var2;
      } else {
         return false;
      }
   }

   @Override
   public void onDisable() {
      this.getMuteChatHandler().getConcurrentHashMap().clear();
   }

   private static final boolean checkPunishmentEntry(PunishmentEntry var0, Entry var1) {
      return var0.getUuid() != null && Intrinsics.checkObject(var0.getUuid(), ((PunishmentEntry)var1.getValue()).getUuid())
         || var0.isIpban() && var0.getIp() != null && Intrinsics.checkObject(var0.getIp(), ((PunishmentEntry)var1.getValue()).getIp());
   }
}
