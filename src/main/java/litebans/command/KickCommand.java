package litebans.command;

import java.io.Closeable;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.UUID;
import litebans.config.Config;
import litebans.config.Message;
import litebans.config.Template;
import litebans.config.TemplateData;
import litebans.core.LiteBansCore;
import litebans.core.MessageSender;
import litebans.core.NotificationManager;
import litebans.database.DatabaseManager;
import litebans.database.EntryReader;
import litebans.database.PunishmentStorage;
import litebans.hook.UsernamePattern;
import litebans.lib.compress.archivers.tar.TargetParser;
import litebans.lib.kotlin.NoWhenBranchMatchedException;
import litebans.lib.kotlin.ResultFailure;
import litebans.lib.kotlin.UByte;
import litebans.lib.kotlin.Unit;
import litebans.lib.kotlin.collections.ArraysKt;
import litebans.lib.kotlin.collections.CollectionsKt;
import litebans.lib.kotlin.io.CloseableKt;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.text.StringsKt;
import litebans.platform.LiteBansPlatform;
import litebans.platform.Sender;
import litebans.punishment.EntryPlaceholders;
import litebans.punishment.Flag;
import litebans.punishment.PlayerHistory;
import litebans.punishment.Profile;
import litebans.punishment.PunishmentEntry;
import litebans.punishment.PunishmentRequest;
import litebans.punishment.PunishmentService;
import litebans.punishment.PunishmentStage;
import litebans.punishment.PunishmentType;
import litebans.punishment.ServerScope;
import litebans.sync.SyncBroadcastTask;
import litebans.sync.SyncManager;
import org.jetbrains.annotations.NotNull;

public final class KickCommand extends PunishCommand {
   public KickCommand(@NotNull LiteBansPlatform var1) {
      super("kick", var1);
   }

   @Override
   public void execute(@NotNull Sender var1, @NotNull String[] var2) {
      MessageSender var83;
      CommandRunnable var3 = this;
      DatabaseManager var48 = (DatabaseManager)var3.getPlatform().getModule(DatabaseManager.class);

      try {
         PunishmentStorage var5 = var48.openStorage();
         Closeable var6 = var5;
         Throwable var7 = null;

         try {
            PunishmentStorage var10 = var5;
            CommandRunnable var12 = this;
            Config var16 = ((LiteBansCore)var12.getPlatform().getModule(LiteBansCore.class)).getConfig();

            try {
               label644: {
                  if (LiteBansCommand.object != null) {
                     var12 = this;
                     if (!((LiteBansCore)var12.getPlatform().getModule(LiteBansCore.class)).isDebugEnabled()) {
                        break label644;
                     }

                     TextArgs var10000;
                     LiteBansCore var10002;
                     String[] var10003;
                     label587: {
                        var12 = this;
                        var10002 = (LiteBansCore)var12.getPlatform().getModule(LiteBansCore.class);
                        String var14 = this.getName();
                        switch (var14.hashCode()) {
                           case -1320563219:
                              if (var14.equals("dupeip")) {
                                 String[] var63 = new String[]{"alts", "litebans:alts", "checkalts", "litebans:checkalts", "litebans:dupeip"};
                                 var10003 = var63;
                                 break label587;
                              }
                              break;
                           case 712910260:
                              if (var14.equals("staffhistory")) {
                                 String[] var61 = new String[]{"staffhist", "litebans:staffhistory"};
                                 var10003 = var61;
                                 break label587;
                              }
                              break;
                           case 926934164:
                              if (var14.equals("history")) {
                                 String[] var18 = new String[]{"hist", "litebans:history"};
                                 var10003 = var18;
                                 break label587;
                              }
                        }

                        String[] var62 = new String[]{"litebans:" + var14};
                        var10003 = var62;
                     }

                     String[] var51 = var10003;
                     var10000 = new TextArgs(var10002, Arrays.copyOf(var51, var51.length));
                     int var92 = var10000.getInt();
                     Object var10001 = var1.getHandle();
                     if (var92 != (var10001 != null ? var10001.hashCode() : 0)) {
                        break label644;
                     }
                  }

                  throw new NullPointerException();
               }
            } catch (Exception var43) {
               return;
            }

            PunishmentType var13 = PunishmentType.KICK;
            boolean var54 = var16.isSilent();
            String var15 = var16.getDefaultServerScope();
            String var17 = var5.getDatabaseManager().getSenderServer(var1);
            long var19 = DatabaseManager.currentTimeMillisDefault(var5.getDatabaseManager(), false, 1, null);
            String var21 = var5.getString(var1);
            PunishmentRequest var52 = new PunishmentRequest(
               var13,
               false,
               var54,
               false,
               false,
               false,
               false,
               false,
               false,
               false,
               false,
               false,
               false,
               false,
               var19,
               0L,
               0L,
               (byte)0,
               null,
               null,
               false,
               null,
               0,
               null,
               null,
               null,
               null,
               var1,
               var21,
               null,
               var15,
               var17,
               null,
               null,
               var2,
               null,
               null,
               671072250,
               27,
               null
            );
            PunishmentRequest var53 = var52;

            try {
               PunishmentRequest.handlePunishmentStorageDefault(var53, var10, null, false, 3, null);
               var53.fireStage(PunishmentStage.PRE_TARGET);
               MessageSender var55 = CommandRunnable.messageSender;
               boolean var58 = var53.getArgs().isEmpty();
               CharSequence var64 = Message.KICK_USAGE;
               if (var58) {
                  var55.getVoid(var64);
                  throw new NoWhenBranchMatchedException();
               }

               CommandRunnable var56 = this;
               Iterable var59 = var53.getArgs();
               var53.setString5(PunishmentService.lookupUuid(var56, (String)CollectionsKt.getObject(var59)));
               Sender var57 = PunishmentService.findOnline(this, var53.getString5());
               ResultFailure var60 = new ResultFailure();
               var60.object = var57 != null ? var57.getName() : null;
               ResultFailure var65 = new ResultFailure();
               var65.object = var57 != null ? var57.getUuidString() : null;
               var53.getArgs().remove(0);
               if (!var53.getArgs().isEmpty()) {
                  String var22 = (String)CollectionsKt.getObject(var53.getArgs());
                  CommandRunnable var23 = this;
                  Template var99 = ((LiteBansCore)var23.getPlatform().getModule(LiteBansCore.class))
                     .getTemplateManager()
                     .getTemplate(PunishmentType.KICK, var22);
                  if (var99 == null) {
                     var99 = Template.Companion.getTemplate();
                  }

                  var53.setTemplate(var99);
                  Template var66 = var53.getTemplate();
                  if (Intrinsics.getInt(var66.getId() & 255, -1 & 255) < 0) {
                     var53.getArgs().remove(var22);
                  }
               }

               var53.getStringBuilder().append(CollectionsKt.getString(var53.getArgs(), " ", null, null, 0, null, null, 62, null));
               if (var65.object == null) {
                  boolean var95;
                  label568: {
                     var53.setPlayerHistory(var10.findLatestHistory(var53.getString5()));
                     PlayerHistory var93 = var53.getPlayerHistory();
                     if (var93 != null) {
                        String var94 = var93.getUuid();
                        if (var94 != null) {
                           var95 = !StringsKt.checkCharSequence(var94, '#', false, 2, null);
                           break label568;
                        }
                     }

                     var95 = false;
                  }

                  if (!var95) {
                     var53.setPlayerHistory(null);
                  }

                  if (var53.getPlayerHistory() == null) {
                     CommandRunnable var75 = this;
                     var21 = var53.getString5();
                     boolean var85 = ((LiteBansCore)var75.getPlatform().getModule(LiteBansCore.class)).getConfig().isOnlineMode();
                     PlayerHistory var101;
                     if (var85) {
                        if (TargetParser.checkString7(var21)) {
                           String var100 = UsernamePattern.mojangApi.getString(var21);
                           if (var100 == null) {
                              var101 = null;
                           } else {
                              String var27 = var100;
                              var101 = new PlayerHistory(var27, var21, "#", new Date());
                           }
                        } else {
                           Profile var102 = UsernamePattern.mojangApi.getProfile(var21);
                           if (var102 == null) {
                              var101 = null;
                           } else {
                              Profile var90 = var102;
                              UUID var10004 = var90.getUuid();
                              var101 = new PlayerHistory(var90.getName(), var10004 != null ? var10004.toString() : null, null, new Date());
                           }
                        }
                     } else {
                        if (TargetParser.checkString7(var21)) {
                           new PlayerHistory(ArgUtils.getString(var21, 16), var21, "#", new Date());
                        }

                        var101 = new PlayerHistory(ArgUtils.getString(var21, 16), ArgUtils.getString(var21), "#", new Date());
                     }

                     var53.setPlayerHistory(var101);
                  }

                  PlayerHistory var103 = var53.getPlayerHistory();
                  var60.object = var103 != null ? var103.getString() : null;
                  var103 = var53.getPlayerHistory();
                  var65.object = var103 != null ? var103.getUuid() : null;
               }

               this.handlePunishmentRequest(var53);
               Template var76 = var53.getTemplate();
               if (Intrinsics.getInt(var76.getId() & 255, -1 & 255) < 0 && var65.object != null) {
                  TemplateData var77 = var53.getTemplate().getDefaults();
                  var53.setIntValue(var10.countTemplateUses(var53.getTemplate(), (String)var65.object, var57 != null ? var57.getIp() : null));
                  var53.setTemplateData(var53.getTemplate().getTemplateData(var53.getIntValue()));
                  CommandRunnable var68 = this;
                  ((LiteBansCore)var68.getPlatform().getModule(LiteBansCore.class))
                     .debug(
                        "Applying "
                           + var53.getTemplate().getType()
                           + " template \""
                           + var53.getTemplate().getName()
                           + "\" at level "
                           + (var53.getIntValue() + 1)
                           + '/'
                           + var53.getTemplate().getLadder().size()
                     );
                  if (StringsKt.checkCharSequence(var53.getStringBuilder())) {
                     StringBuilder var96 = var53.getStringBuilder();
                     String var105 = var53.getTemplateData().getString2();
                     if (var105 == null) {
                        var105 = var77.getString2();
                     }

                     var96.append(var105);
                  }

                  PunishmentService.requireTemplatePermission(this, var53.getTemplate(), var53.getTemplateData());
                  var53.applyTemplateDefaults(var10);
               }

               StringBuilder var78 = var53.getStringBuilder();
               CharSequence var69 = var53.readReason(var10);
               StringsKt.getStringBuilder(var78).append(var69);
               var53.applyDurationLimits(var10);
               if (var57 == null || !var57.isOnline()) {
                  if (var65.object != null) {
                     Object var107 = var60.object;
                     Intrinsics.handleObject(var60.object);
                     if (var53.isExempt(var10, (String)var107, (String)var65.object)) {
                        MessageSender var82 = CommandRunnable.messageSender;
                        boolean var72 = var53.isExemptByGroupWeight(var10, (String)var65.object);
                        boolean var89 = !var72;
                        if (var89) {
                           var82.getVoid(
                              var10.getCharSequence(Message.KICK_EXEMPT, (String)var60.object, (String)var60.object, (String)var65.object, null, null)
                           );
                           throw new NoWhenBranchMatchedException();
                        }
                     }
                  }

                  boolean var98;
                  label540: {
                     var83 = CommandRunnable.messageSender;
                     if (var10.getDatabaseManager().isSyncEnabled() && !var53.isSkip()) {
                        String var86 = "litebans.kick.global";
                        if (var1.hasPermission(var86)) {
                           var98 = false;
                           break label540;
                        }
                     }

                     var98 = true;
                  }

                  boolean var73 = var98;
                  CharSequence var87 = Message.KICK_NO_MATCH;
                  if (var73) {
                     var83.getVoid(var87);
                     throw new NoWhenBranchMatchedException();
                  }

                  SyncManager var84 = (SyncManager)var10.getPlatform().getModule(SyncManager.class);
                  PlayerAliasPredicate var74 = new PlayerAliasPredicate(var53, var84, var1);
                  var84.addListener(var74);
                  this.insertKick(var10, var53, var53.getStringBuilder());
                  CommandRunnable var88 = this;
                  long var30 = 400L;
                  var88.getPlatform().runAsyncLater(new SyncBroadcastTask(var88, var74), var30);
                  var53.reply(var10, Message.KICK_REQUEST.getString("player", var53.getString5()));
               } else {
                  if (BaseCommand.commandAliases.checkLiteBansPlatform(var10.getPlatform(), var57.getServerName())) {
                     var10.getDatabaseManager().runWithStorageAsync(st -> getUnit(var57, (PunishmentStorage) st));
                  }

                  label624: {
                     var53.fireStage(PunishmentStage.POST_TARGET);
                     if (!var53.isExempt(var10, var57)) {
                        Sender var79 = var57;
                        String[] var70 = new String[]{"litebans.exempt", "litebans.exempt.kick"};
                        String[] var24 = var70;
                        int var26 = 0;
                        int var91 = var70.length;

                        boolean var97;
                        while (true) {
                           if (var26 >= var91) {
                              var97 = false;
                              break;
                           }

                           String var29 = var24[var26];
                           if (var79.hasPermission(var29)) {
                              var97 = true;
                              break;
                           }

                           var26++;
                        }

                        if (!var97 || !var16.isExemptEnable()) {
                           break label624;
                        }
                     }

                     MessageSender var80 = CommandRunnable.messageSender;
                     boolean var71 = var53.isExemptByGroupWeight(var10, (String)var65.object);
                     boolean var25 = !var71;
                     if (var25) {
                        var80.getVoid(var10.getCharSequence2(Message.KICK_EXEMPT, var57));
                        throw new NoWhenBranchMatchedException();
                     }
                  }

                  PunishmentType var108 = PunishmentType.KICK;
                  String var110 = var57.getUuidString();
                  String var10005 = var57.getIp();
                  CharSequence var10006 = var53.getStringBuilder();
                  String var10007 = var53.getSenderUuid();
                  String var10008 = var53.getSenderName();
                  String var10009 = ServerScope.serverScopeParser.getString();
                  String var10010 = var10.getDatabaseManager().getSenderServer(var1);
                  if (var10010 == null) {
                     var10010 = var10.getDatabaseManager().getString();
                  }

                  long var10011 = DatabaseManager.currentTimeMillisDefault(var10.getDatabaseManager(), false, 1, null);
                  var76 = var53.getTemplate();
                  PunishmentEntry var106 = new PunishmentEntry( var108, var110, var10005, var10006, var10007, var10008, var10009, var10010, var10011, 0L, var76 != null ? var76.getId() : -1, var53.isSilent(), false, false, 0L, 24576, null );
                  var53.setPunishmentEntry(var106);
                  var53.fireStage(PunishmentStage.ENTRY_CREATED);
                  PunishmentEntry var109 = var53.getPunishmentEntry();
                  Intrinsics.handleObject(var109);
                  var53.checkCooldown(var10, var1, var109, new PlayerHistory(var57.getName(), var57.getUuidString(), var57.getIp(), new Date()));
                  this.kickTarget(var53, var10, var57);
               }
            } finally {
               var52.fireStage(PunishmentStage.FINALIZED);
            }
         } catch (Throwable var45) {
            var7 = var45;
            throw var45;
         } finally {
            CloseableKt.handleCloseable(var6, var7);
         }
      } catch (Exception var47) {
         if (!(var47 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var47); }
         if (!var48.isConnectionError(var47)) {
            throw var47;
         }
      }
   }

   private final void insertKick(PunishmentStorage var1, PunishmentRequest var2, CharSequence var3) {
      Flag var5 = Flag.KICK_REQUEST;
      if (var1.getDatabaseManager().isSyncEnabled()) {
         CharSequence[] var9 = new CharSequence[8];
         var9[0] = var2.getSenderUuid();
         var9[1] = var2.getSenderName();
         var9[2] = var2.getString5();
         var9[3] = var3;
         boolean var11 = var2.isSilent();
         var9[4] = var11 ? "1" : "0";
         var11 = var2.isExtraSilent();
         var9[5] = var11 ? "1" : "0";
         Template var10 = var2.getTemplate();
         var9[6] = UByte.getString(var10 != null ? var10.getId() : -1);
         var9[7] = String.valueOf(var2.getIntValue());
         CharSequence var15 = ArraysKt.getString(var9, "\ufeff", null, null, 0, null, null, 62, null);
         PunishmentService.broadcastDefault(var1, var5, var15, null, null, 12, null);
      }
   }

   public final void kickTarget(@NotNull PunishmentRequest var1, @NotNull PunishmentStorage var2, @NotNull Sender var3) {
      PunishmentEntry var10000 = var1.getPunishmentEntry();
      Intrinsics.handleObject(var10000);
      PunishmentEntry var4 = var10000;
      String var6 = var4.getReason().toString();
      String var5 = var6.toLowerCase(Locale.ENGLISH);
      CommandRunnable var7 = this;
      Iterable var15 = ((LiteBansCore)var7.getPlatform().getModule(LiteBansCore.class)).getConfig().getIgnoredKickMessages();
      boolean var21;
      if (var15 instanceof Collection && ((Collection)var15).isEmpty()) {
         var21 = false;
      } else {
         Iterator var9 = var15.iterator();

         while (true) {
            if (!var9.hasNext()) {
               var21 = false;
               break;
            }

            Object var10 = var9.next();
            String var11 = (String)var10;
            if (StringsKt.checkCharSequence3(var5, var11, false, 2, null)) {
               var21 = true;
               break;
            }
         }
      }

      boolean var14 = var21;
      if (!var14) {
         PunishmentService.addEntryDefault(var2, var4, false, false, 6, null);
      } else {
         var7 = this;
         LiteBansCore var17 = (LiteBansCore)var7.getPlatform().getModule(LiteBansCore.class);
         byte var8 = 1;
         if (var17.isDebugLevel(var8)) {
            var17.debug("Kick not added to database due to reason: \"" + var4.getReason() + '"');
         }
      }

      var1.fireStage(PunishmentStage.ENTRY_EMITTED);
      var1.setPlayerHistory(new PlayerHistory(var3.getName(), var3.getUuidString(), var3.getIp(), new Date()));
      CharSequence var18 = EntryPlaceholders.getCharSequenceDefault(var2, var4, var1.getIntValue(), 0, 4, null);
      if (var18 != null) {
         var18 = var2.getCharSequence2(EntryReader.getCharSequence(var2, var18, var4, false, 2, null), var3);
      }

      CharSequence var22 = var18;
      if (var18 == null) {
         var22 = EntryPlaceholders.getString(var2, var4, var3);
      }

      CharSequence var19 = var22;
      this.forwardCommand(var2, var4, var1);
      if (var3.isOnline() && !var1.isSkip()) {
         CommandRunnable var20 = this;
         ((NotificationManager)var20.getPlatform().getModule(NotificationManager.class)).kickSync(var3, var19);
         var1.reply(var2, Message.KICK_RESPONSE);
      }

      PunishmentRequest.handlePunishmentStorageDefault(var1, var2, var3, null, false, 6, null);
      var1.fireStage(PunishmentStage.ENTRY_APPLIED);
   }

   private static final Unit getUnit(Sender var0, PunishmentStorage var1) {
      EntryReader.handlePunishmentStorage(var1, var0.getName(), var0.getUuidString(), var0.getIp(), false, false, 16, null);
      return Unit.INSTANCE;
   }
}
