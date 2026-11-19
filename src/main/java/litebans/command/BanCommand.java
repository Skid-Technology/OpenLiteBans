package litebans.command;

import java.io.Closeable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import litebans.api.impl.EventDispatcher;
import litebans.chat.ChatColorCode;
import litebans.config.Config;
import litebans.config.Message;
import litebans.config.StoredTemplateData;
import litebans.config.Template;
import litebans.config.TemplateData;
import litebans.config.TemplateGroup;
import litebans.config.TemplateManager;
import litebans.core.CoreFieldAccess;
import litebans.core.LiteBansCore;
import litebans.core.MessageSender;
import litebans.core.NotificationManager;
import litebans.core.Placeholders;
import litebans.database.DatabaseManager;
import litebans.database.EntryReader;
import litebans.database.PunishmentStorage;
import litebans.database.Table;
import litebans.hook.MojangApi;
import litebans.hook.UsernamePattern;
import litebans.lib.compress.archivers.tar.TargetParser;
import litebans.lib.kotlin.KotlinVersion;
import litebans.lib.kotlin.NoWhenBranchMatchedException;
import litebans.lib.kotlin.Pair;
import litebans.lib.kotlin.ResultFailure;
import litebans.lib.kotlin.UninitializedPropertyAccessException;
import litebans.lib.kotlin.Unit;
import litebans.lib.kotlin.collections.ArraysKt;
import litebans.lib.kotlin.collections.CollectionsKt;
import litebans.lib.kotlin.io.CloseableKt;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.jvm.internal.TypeIntrinsics;
import litebans.lib.kotlin.text.RegexReplace;
import litebans.lib.kotlin.text.StringsKt;
import litebans.platform.LiteBansPlatform;
import litebans.platform.Sender;
import litebans.platform.SenderRunnable1;
import litebans.punishment.ConsoleFunction;
import litebans.punishment.DupeIPResult;
import litebans.punishment.DupeIpLookup;
import litebans.punishment.DurationParser;
import litebans.punishment.DurationUnit;
import litebans.punishment.EntryPlaceholders;
import litebans.punishment.EntryPlaceholdersWhenMappings;
import litebans.punishment.ExecutorInfo;
import litebans.punishment.Flag;
import litebans.punishment.MuteManager;
import litebans.punishment.PlayerHistory;
import litebans.punishment.Profile;
import litebans.punishment.PunishmentEntry;
import litebans.punishment.PunishmentNotifier;
import litebans.punishment.PunishmentRequest;
import litebans.punishment.PunishmentRequestWhenMappings;
import litebans.punishment.PunishmentRequestWhenMappings2;
import litebans.punishment.PunishmentService;
import litebans.punishment.PunishmentStage;
import litebans.punishment.PunishmentType;
import litebans.punishment.ServerScope;
import litebans.randomid.LongBitSet;
import litebans.sync.Throttler;
import litebans.util.DecimalFormats;
import litebans.util.SerializableValue;
import litebans.util.Unreachable;
import org.jetbrains.annotations.NotNull;

public final class BanCommand extends PunishCommand {
   public static final BanCommandCompanion Companion = new BanCommandCompanion(null);

   public BanCommand(@NotNull LiteBansPlatform var1) {
      super("ban", var1);
   }

   @Override
   public boolean isArgsRequired() {
      return false;
   }

   @Override
   public void execute(@NotNull Sender var1, @NotNull String[] var2) {
      Sender var121;
      boolean var411;
      boolean var412;
      boolean var416;
      CommandRunnable var4 = this;
      LiteBansCore var3 = (LiteBansCore)var4.getPlatform().getModule(LiteBansCore.class);
      Map var108 = this.getPlatform().getSenderCache();
      int var5 = var108.size();

      try {
         if (var3.isDebugEnabled()) {
            String var79 = "[c] " + var5;
            var3.debug(var79);
         }
      } catch (UninitializedPropertyAccessException var107) {
         if (Intrinsics.checkObject(CoreFieldAccess.getObject(var3), Message.getString(var3, false, true))) {
            var3.handleByte((byte)49);
         }

         CommandRunnable var7 = this;
         ((DatabaseManager)var7.getPlatform().getModule(DatabaseManager.class)).reportError(var107);
         return;
      }

      CommandRunnable var6 = this;
      DatabaseManager var109 = (DatabaseManager)var6.getPlatform().getModule(DatabaseManager.class);

      try {
         PunishmentStorage var8 = var109.openStorage();
         Closeable var9 = var8;
         Throwable var10 = null;

         try {
            PunishmentStorage var13 = var8;
            OperationCancelledException var15 = new OperationCancelledException();
            if (var8.isClosed()) {
               MessageSender var110 = CommandRunnable.messageSender;
               boolean var111 = var3.getCommandAlias() != null;
               CharSequence var112 = ChatColorCode.RED + "[LiteBans]" + ChatColorCode.DARK_RED + " Plugin access has been disabled!";
               String var116 = "litebans.admin";
               boolean var114 = var1.hasPermission(var116);
               CharSequence var435;
               if (!var114) {
                  var435 = var112;
               } else {
                  CharSequence var122 = ChatColorCode.RED
                     + "[LiteBans]"
                     + ChatColorCode.LIGHT_PURPLE
                     + " If you have bought LiteBans, contact Ruan on SpigotMC.";
                  if (var122 != null && var122.length() > 0) {
                     CharSequence var139 = "\n";
                     CharSequence var131 = "" + var112 + var139;
                     var435 = "" + var131 + var122;
                  } else {
                     var435 = var112;
                  }
               }

               var112 = ArgUtils.getCharSequence(var435, var3.getString());
               if (var111) {
                  var110.getVoid(var112);
                  throw new NoWhenBranchMatchedException();
               }

               CommandRunnable.messageSender.getVoid(Message.ERROR_NO_DATABASE);
               throw new NoWhenBranchMatchedException();
            }

            try {
               if (LiteBansCommand.object == null) {
                  throw var15;
               }

               Config var16 = var3.getConfig();
               Sender[] var17 = var13.getPlatform().getOnlineSenders();
               String var18 = var16.getDefaultServerScope();
               PunishmentType var20 = PunishmentType.BAN;
               boolean var21 = var16.isPunishIp();
               boolean var22 = var16.isSilent();
               String var23 = var13.getDatabaseManager().getSenderServer(var1);
               long var26 = DatabaseManager.currentTimeMillisDefault(var13.getDatabaseManager(), false, 1, null);
               String var28 = var13.getString(var1);
               PunishmentRequest var19 = new PunishmentRequest(
                  var20,
                  var21,
                  var22,
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
                  var26,
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
                  var28,
                  null,
                  var18,
                  var23,
                  null,
                  null,
                  var2,
                  null,
                  null,
                  671072248,
                  27,
                  null
               );
               PunishmentRequest var115 = var19;

               try {
                  PunishmentRequest.handlePunishmentStorageDefault(var115, var13, null, false, 3, null);
                  MessageSender var117 = CommandRunnable.messageSender;
                  boolean var123 = var115.getPunishmentType().getPunishmentType() == PunishmentType.MUTE && !var16.isMutesEnabled();
                  CharSequence var24 = Message.MUTE_DISABLED;
                  if (var123) {
                     var117.getVoid(var24);
                     throw new NoWhenBranchMatchedException();
                  }

                  MessageSender var118 = CommandRunnable.messageSender;
                  boolean var124 = var115.isCustomSender() && var1.isPlayer() && var16.isSecuritySenderCustom();
                  var24 = Message.ERROR_CONSOLE;
                  if (var124) {
                     var118.getVoid(var24);
                     throw new NoWhenBranchMatchedException();
                  }

                  MessageSender var119 = CommandRunnable.messageSender;
                  boolean var125 = var115.isModify() && var1.isPlayer() && var16.isSecurityModify();
                  var24 = Message.ERROR_CONSOLE;
                  if (var125) {
                     var119.getVoid(var24);
                     throw new NoWhenBranchMatchedException();
                  }

                  int var22i = var3.getLongBitSet().getInt(10);
                  switch (var22i) {
                     case 0:
                     case 1:
                        int var127 = var3.getLongBitSet().getInt(10);
                        if (var127 == 1) {
                           if (var3.isDebugEnabled()) {
                              StringBuilder var407;
                              TextArgs var436;
                              String[] var10004;
                              label4184: {
                                 var407 = new StringBuilder().append("Timeout after ");
                                 String var178 = this.getName();
                                 switch (var178.hashCode()) {
                                    case -1320563219:
                                       if (var178.equals("dupeip")) {
                                          String[] var229 = new String[]{"alts", "litebans:alts", "checkalts", "litebans:checkalts", "litebans:dupeip"};
                                          var10004 = var229;
                                          break label4184;
                                       }
                                       break;
                                    case 712910260:
                                       if (var178.equals("staffhistory")) {
                                          String[] var227 = new String[]{"staffhist", "litebans:staffhistory"};
                                          var10004 = var227;
                                          break label4184;
                                       }
                                       break;
                                    case 926934164:
                                       if (var178.equals("history")) {
                                          String[] var226 = new String[]{"hist", "litebans:history"};
                                          var10004 = var226;
                                          break label4184;
                                       }
                                 }

                                 String[] var228 = new String[]{"litebans:" + var178};
                                 var10004 = var228;
                              }

                              String[] var240 = var10004;
                              var436 = new TextArgs(var3, Arrays.copyOf(var240, var240.length));
                              String var36 = var407.append(KotlinVersion.getString(var436.getInt())).append(" seconds").toString();
                              var3.debug(var36);
                           }
                        } else {
                           var13.getDatabaseManager().onDisable();
                        }
                        break;
                     case 2:
                        CoreFieldAccess.handleLiteBansCore(var3, Message.getString(var3.getConfig(), false, true));
                        break;
                     case 3:
                     case 4:
                     case 5:
                     case 6:
                     case 7:
                        byte var126 = var3.getByteValue4();
                        if (var126 == 1 && new LongBitSet(0L, 1, null).getInt((int)var3.getLongValue()) == 0
                           || var126 == 2 && (long)var17.length >= var3.getLongValue()
                           || var126 == 3 && EntryReader.getLong(var13, Table.BANS, null, true, 2, null) >= var3.getLongValue()) {
                           var3.setLongBitSet(new LongBitSet(0L, 1, null));
                        }

                        int var134 = 0;

                        while (var134 < var115.getArgs().size()) {
                           var28 = (String)var115.getArgs().get(var134);
                           String var25 = var28;
                           if (var25.length() == 0) {
                              var134++;
                           } else if (!Intrinsics.checkObject(var25, "duration:permanent")
                              || !var115.isModify() && (var115.check2() || var115.getPunishmentType().getPunishmentType() == PunishmentType.WARN)) {
                              var25 = Placeholders.Companion.getString(var25, "\\-", "-");
                              boolean var145 = var134 == var115.getArgs().size() - 1;
                              String var29 = RegexReplace.getString(DurationUnit.durationParser.getRegex2(), var25, "");
                              Template var30 = this.resolveTemplate(var115, var25);
                              if (var30 != null) {
                                 this.applyTemplate(var115, var30);
                              } else if ((var115.isModify() || !var115.check2())
                                 && var115.getPunishmentType().getPunishmentType() != PunishmentType.WARN
                                 && var115.getDateEnd() == -1L
                                 && Character.isDigit(var25.charAt(0))
                                 && !StringsKt.checkCharSequence3(var25, ':', false, 2, null)
                                 && !StringsKt.checkCharSequence3(var25, '%', false, 2, null)
                                 && !StringsKt.checkCharSequence3(var25, '*', false, 2, null)
                                 && TargetParser.INSTANCE.checkString(var29)
                                 && !TargetParser.checkString7(var25)) {
                                 if (var115.getString5().length() == 0 && var25.length() > 1) {
                                    String var199 = ChatColorCode.getString(var25);
                                    Intrinsics.handleObject(var17);
                                    Intrinsics.handleObject(var199);
                                    String var35 = var199;
                                    boolean var37 = true;
                                    Sender[] var39 = var17;
                                    int var40 = 0;
                                    int var41 = var17.length;

                                    Sender var10000;
                                    while (true) {
                                       if (var40 >= var41) {
                                          var10000 = null;
                                          break;
                                       }

                                       Sender var42 = var39[var40];
                                       if (StringsKt.checkString(var42.getName(), var35, var37)) {
                                          var10000 = var42;
                                          break;
                                       }

                                       var40++;
                                    }

                                    Sender var33 = var10000;
                                    if (var33 != null) {
                                       String var225 = var33.getName();
                                       var115.setString5(var225);
                                       if (BaseCommand.commandAliases.checkLiteBansPlatform(var13.getPlatform(), var115.getServerOrigin())) {
                                          var13.getDatabaseManager().runWithStorageAsync(st -> getUnit(var33, (PunishmentStorage) st));
                                       }

                                       var134++;
                                       continue;
                                    }

                                    var115.setPlayerHistory(var13.findLatestHistory(var25));
                                    if (var115.getPlayerHistory() != null) {
                                       String var10001;
                                       label4113: {
                                          PlayerHistory var224 = var115.getPlayerHistory();
                                          if (var224 != null) {
                                             var35 = var224.getString();
                                             var10001 = var35;
                                             if (var35 != null) {
                                                break label4113;
                                             }
                                          }

                                          var10001 = "";
                                       }

                                       var115.setString5(var10001);
                                       var134++;
                                       continue;
                                    }
                                 }

                                 Double var200 = StringsKt.getDouble(var29);
                                 if (var200 == null) {
                                    if (var115.getString5().length() == 0) {
                                       var115.setString5(var25);
                                    }

                                    var134++;
                                    continue;
                                 }

                                 String var215 = RegexReplace.getString(DurationUnit.durationParser.getRegex(), var25, "");
                                 DurationUnit var34 = DurationUnit.durationParser.getDurationUnit(var215);
                                 if (var34 == null) {
                                    if (var145) {
                                       if (var115.getString5().length() == 0) {
                                          var115.setString5(var25);
                                       } else {
                                          this.applyTemplate(var115, var25);
                                       }

                                       var134++;
                                       continue;
                                    }

                                    Object var262 = var115.getArgs().get(++var134);
                                    String var238 = (String)var262;
                                    var34 = DurationUnit.durationParser.getDurationUnit(var238);
                                    if (var34 == null) {
                                       if (var115.getString5().length() == 0) {
                                          var115.setString5(var25);
                                          var262 = RegexReplace.getString(DurationUnit.durationParser.getRegex(), var238, "");
                                          DurationUnit var38 = DurationUnit.durationParser.getDurationUnit((String)var262);
                                          if (var38 == null) {
                                             this.applyTemplate(var115, var238);
                                          } else {
                                             Double var302 = StringsKt.getDouble(RegexReplace.getString(DurationUnit.durationParser.getRegex2(), var238, ""));
                                             if (var302 == null) {
                                                this.applyTemplate(var115, var238);
                                             } else {
                                                var115.setDuration(var38.getLong(var302));
                                                var115.setDateEnd(var115.getDateStart() + var115.getDuration());
                                                var115.setReason(DecimalFormats.getString2(var302) + ' ' + var38.getCharSequence(var302));
                                             }
                                          }
                                       } else {
                                          var115.getStringBuilder().append(var25).append(' ').append(var238).append(' ');
                                       }

                                       var134++;
                                       continue;
                                    }
                                 }

                                 var115.setDuration(var34.getLong(var200));
                                 var115.setDateEnd(var115.getDateStart() + var115.getDuration());
                                 var115.setReason(DecimalFormats.getString2(var200) + ' ' + var34.getCharSequence(var200));
                              } else if (var115.getString5().length() == 0) {
                                 var115.setString5(var25);
                              } else {
                                 this.applyTemplate(var115, var25);
                                 CommandRunnable var31 = this;
                                 if (((NotificationManager)var31.getPlatform().getModule(NotificationManager.class)).getIntValue() == 0) {
                                    throw var15;
                                 }
                              }

                              var134++;
                           } else {
                              var115.setDateEnd(-2L);
                              var115.setTemporary(false);
                              var134++;
                           }
                        }

                        if (var115.getDateEnd() > 0L) {
                           var115.setTemporary(true);
                        }
                  }

                  CommandRunnable var128 = this;
                  boolean var141 = PunishmentRequest.isWildcardTargetDefault(var115, null, 1, null);
                  boolean var216 = var115.isIpBan() && !((LiteBansCore)var128.getPlatform().getModule(LiteBansCore.class)).getConfig().isPunishIp();
                  switch (PunishmentRequestWhenMappings.switchMap[var115.getPunishmentType().ordinal()]) {
                     case 1:
                     case 2:
                     case 3:
                        String var180 = var115.isModify() ? "litebans.modify" : "litebans." + var115.getPunishmentType();
                        String[] var204 = new String[]{var180, var180 + ".own"};
                        PunishmentService.requireAnyPermission(var128, var204);
                        break;
                     case 4:
                     case 5:
                        String[] var408;
                        if (var115.isTemporary()) {
                           String[] var202 = new String[]{
                              "litebans.temp" + var115.getPunishmentType(),
                              "litebans." + var115.getPunishmentType(),
                              "litebans.ip" + var115.getPunishmentType()
                           };
                           var408 = var202;
                        } else {
                           String[] var203 = new String[]{"litebans." + var115.getPunishmentType(), "litebans.ip" + var115.getPunishmentType()};
                           var408 = var203;
                        }

                        String[] var179 = var408;
                        if (var216) {
                           String var230 = "litebans.ip" + var115.getPunishmentType();
                           Sender var264 = var128.getCurrentSender();
                           MessageSender var304 = CommandRunnable.messageSender;
                           boolean var315 = !var264.hasPermission(var230);
                           CharSequence var332 = Message.ERROR_NO_PERMISSION;
                           if (var315) {
                              var304.getVoid(var332);
                              throw new NoWhenBranchMatchedException();
                           }
                        }

                        PunishmentService.requireAnyPermission(var128, Arrays.copyOf(var179, var179.length));
                        break;
                     case 6:
                        String var201 = "litebans." + var115.getPunishmentType();
                        Sender var241 = var128.getCurrentSender();
                        MessageSender var284 = CommandRunnable.messageSender;
                        boolean var303 = !var241.hasPermission(var201);
                        CharSequence var314 = Message.ERROR_NO_PERMISSION;
                        if (var303) {
                           var284.getVoid(var314);
                           throw new NoWhenBranchMatchedException();
                        }
                        break;
                     default:
                        Unreachable.getVoid();
                        throw new NoWhenBranchMatchedException();
                  }

                  if (!var115.check2() && var141) {
                     String var162 = "litebans.ip" + var115.getPunishmentType() + ".wildcard";
                     Sender var205 = var128.getCurrentSender();
                     MessageSender var242 = CommandRunnable.messageSender;
                     boolean var265 = !var205.hasPermission(var162);
                     CharSequence var285 = Message.ERROR_NO_PERMISSION;
                     if (var265) {
                        var242.getVoid(var285);
                        throw new NoWhenBranchMatchedException();
                     }
                  }

                  var115.fireStage(PunishmentStage.PRE_TARGET);
                  if (var115.getString5().length() == 0) {
                     PunishmentType var130 = var115.getPunishmentType();
                     boolean var138 = var115.check2();
                     Message var159 = Message.BAN_USAGE;
                     CharSequence var431;
                     if (var138) {
                        var431 = Message.getMessage2(var130 + "_USAGE");
                     } else {
                        Message var432;
                        switch (PunishmentRequestWhenMappings2.intArray[var130.ordinal()]) {
                           case 1:
                              var432 = var159 == Message.BAN_EXAMPLE ? Message.WARN_EXAMPLE : Message.WARN_USAGE;
                              break;
                           case 2:
                              var432 = var159 == Message.BAN_EXAMPLE ? Message.MUTE_EXAMPLE : Message.MUTE_USAGE;
                              break;
                           case 3:
                              var432 = Message.KICK_USAGE;
                              break;
                           default:
                              var432 = var159;
                        }

                        var431 = var432;
                     }

                     CharSequence var198 = var431;
                     MessageSender var160 = CommandRunnable.messageSender;
                     boolean var177 = var130 == PunishmentType.KICK;
                     if (var177) {
                        var160.getVoid(var198);
                        throw new NoWhenBranchMatchedException();
                     }

                     Message var214 = Message.BAN_EXAMPLE;
                     if (var138) {
                        var431 = Message.getMessage2(var130 + "_USAGE");
                     } else {
                        Message var434;
                        switch (PunishmentRequestWhenMappings2.intArray[var130.ordinal()]) {
                           case 1:
                              var434 = var214 == Message.BAN_EXAMPLE ? Message.WARN_EXAMPLE : Message.WARN_USAGE;
                              break;
                           case 2:
                              var434 = var214 == Message.BAN_EXAMPLE ? Message.MUTE_EXAMPLE : Message.MUTE_USAGE;
                              break;
                           case 3:
                              var434 = Message.KICK_USAGE;
                              break;
                           default:
                              var434 = var214;
                        }

                        var431 = var434;
                     }

                     CharSequence var161 = var431;
                     CommandRunnable.messageSender.getVoid(ArgUtils.getCharSequence(var198, !var138, var161));
                     throw new NoWhenBranchMatchedException();
                  }

                  String var409;
                  label4083: {
                     this.handlePunishmentRequest(var115);
                     var115.setString5(var115.getString(this));
                     var121 = PunishmentService.findOnline(this, var115.getString5());
                     if (var121 != null) {
                        String var135 = var121.getServerName();
                        if (var135 != null) {
                           var409 = ArgUtils.getString(var135, 32);
                           break label4083;
                        }
                     }

                     var409 = null;
                  }

                  var23 = var409;
                  label4069:
                  if (!ServerScope.serverScopeParser.checkString(var18) && !StringsKt.checkString(var115.getServerScope(), var18, true)) {
                     if (ServerScope.serverScopeParser.checkString2(var18)) {
                        var409 = var115.getServerScope();
                        String var136 = var115.getServerOrigin();
                        String var437 = var136;
                        if (var136 == null) {
                           var437 = var23;
                           if (var23 == null) {
                              var437 = var13.getDatabaseManager().getString();
                           }
                        }

                        if (StringsKt.checkString(var409, var437, true)) {
                           break label4069;
                        }
                     }

                     String[] var142 = new String[]{
                        "litebans.server." + var115.getServerScope(),
                        "litebans.server." + var115.getServerScope() + '.' + var115.getPunishmentType().getPunishmentType()
                     };
                     Sender var163 = var1;
                     String[] var181 = Arrays.copyOf(var142, var142.length);
                     String[] var217 = var181;
                     int var243 = 0;
                     int var266 = var181.length;

                     while (true) {
                        if (var243 >= var266) {
                           var411 = false;
                           break;
                        }

                        String var286 = var217[var243];
                        if (var163.hasPermission(var286)) {
                           var411 = true;
                           break;
                        }

                        var243++;
                     }

                     if (!var411) {
                        var115.setServerScope(ServerScope.serverScopeParser.getString(var18, var13.getDatabaseManager().getString(), var1));
                     }
                  }

                  boolean var137 = ServerScope.serverScopeParser.checkString(var13.getDatabaseManager().getString(), var115.getServerScope())
                     || var23 != null && ServerScope.serverScopeParser.checkString(var23, var115.getServerScope());
                  if (var3.isDebugEnabled()) {
                     String var260 = var115.getServerScope();
                     var3.debug(var260);
                  }

                  ExecutorInfo var143;
                  if (var121 != null) {
                     String var452 = var115.getString5();
                     String var10003 = var121.getName();
                     String var170 = var121.getDisplayName();
                     String var187 = var121.getName();
                     var143 = new ExecutorInfo(
                        var452,
                        var10003,
                        var3.getConfig().isUseDisplayNames() ? var170 : var187,
                        var121.getUuidString(),
                        var121.getIp(),
                        var121,
                        null,
                        64,
                        null
                     );
                     var115.setPlayerHistory(new PlayerHistory(var143.getString2(), var143.getString4(), var143.getString5(), new Date(var115.getDateStart())));
                  } else {
                     label4048: {
                        var115.setPlayerHistory(var13.findLatestHistory(var115.getString5()));
                        PlayerHistory var146 = var115.getPlayerHistory();
                        if (var146 != null) {
                           String var164 = var146.getUuid();
                           if (var164 != null) {
                              var412 = !StringsKt.checkCharSequence(var164, '#', false, 2, null);
                              break label4048;
                           }
                        }

                        var412 = false;
                     }

                     if (!var412) {
                        var115.setPlayerHistory(null);
                     }

                     if (var115.getPlayerHistory() == null) {
                        var28 = var115.getString5();
                        if (TargetParser.INSTANCE.checkString2(var28)) {
                           var115.setIpBan(true);
                           MessageSender var150 = CommandRunnable.messageSender;
                           String var185 = "litebans.ip" + var115.getPunishmentType().getPunishmentType();
                           boolean var168 = !var1.hasPermission(var185);
                           CharSequence var186 = Message.ERROR_NO_PERMISSION;
                           if (var168) {
                              var150.getVoid(var186);
                              throw new NoWhenBranchMatchedException();
                           }

                           var115.setPlayerHistory(new PlayerHistory("#offline#", "#offline#", var115.getString5(), new Date()));
                        } else {
                           if (var115.check2()) {
                              var28 = PunishmentService.resolveRandomId(this, var115.getString5());
                              if (!Intrinsics.checkObject(var28, var115.getString5())) {
                                 PunishmentEntry var165 = EntryReader.getPunishmentEntry(
                                    var13, var28, var115.getPunishmentType(), null, !var115.isDelete(), 4, null
                                 );
                                 if (var115.isDelete() || (var165 != null ? var13.appliesToTarget(var165) : false)) {
                                    String var438;
                                    label4031: {
                                       var115.setPlayerHistory(var13.findLatestHistory(var165 != null ? var165.getUuid() : null));
                                       if (var165 != null) {
                                          String var182 = var165.getServerScope();
                                          var438 = var182;
                                          if (var182 != null) {
                                             break label4031;
                                          }
                                       }

                                       var438 = var115.getServerScope();
                                    }

                                    var115.setServerScope(var438);
                                    var115.setPunishmentEntry2(var165);
                                 }
                              }

                              if (var115.getPlayerHistory() == null && TargetParser.INSTANCE.checkString4(var115.getString5())) {
                                 PunishmentEntry var166 = EntryReader.getPunishmentEntry(
                                    var13, var115.getString5(), var115.getPunishmentType(), null, !var115.isDelete(), 4, null
                                 );
                                 if (var115.isDelete() || (var166 != null ? var13.appliesToTarget(var166) : false)) {
                                    String var439;
                                    label4012: {
                                       var115.setPlayerHistory(var13.findLatestHistory(var166 != null ? var166.getUuid() : null));
                                       if (var166 != null) {
                                          String var183 = var166.getServerScope();
                                          var439 = var183;
                                          if (var183 != null) {
                                             break label4012;
                                          }
                                       }

                                       var439 = var115.getServerScope();
                                    }

                                    var115.setServerScope(var439);
                                    var115.setPunishmentEntry2(var166);
                                 }
                              }
                           }

                           if (var115.getPlayerHistory() == null) {
                              CommandRunnable var167 = this;
                              String var184 = var115.getString5();
                              boolean var206 = ((LiteBansCore)var167.getPlatform().getModule(LiteBansCore.class)).getConfig().isOnlineMode();
                              PlayerHistory var441;
                              if (var206) {
                                 if (TargetParser.checkString7(var184)) {
                                    String var440 = UsernamePattern.mojangApi.getString(var184);
                                    if (var440 == null) {
                                       var441 = null;
                                    } else {
                                       String var267 = var440;
                                       var441 = new PlayerHistory(var267, var184, "#", new Date());
                                    }
                                 } else {
                                    Profile var442 = UsernamePattern.mojangApi.getProfile(var184);
                                    if (var442 == null) {
                                       var441 = null;
                                    } else {
                                       Profile var268 = var442;
                                       UUID var468 = var268.getUuid();
                                       var441 = new PlayerHistory(var268.getName(), var468 != null ? var468.toString() : null, null, new Date());
                                    }
                                 }
                              } else {
                                 if (TargetParser.checkString7(var184)) {
                                    new PlayerHistory(ArgUtils.getString(var184, 16), var184, "#", new Date());
                                 }

                                 var441 = new PlayerHistory(ArgUtils.getString(var184, 16), ArgUtils.getString(var184), "#", new Date());
                              }

                              PlayerHistory var149 = var441;
                              if (var149 == null) {
                                 var115.fail(var13, Message.ERROR_NO_UUID.getString("player", var115.getString5()));
                                 throw new NoWhenBranchMatchedException();
                              }

                              var115.setPlayerHistory(var149);
                           }
                        }

                        PlayerHistory var151 = var115.getPlayerHistory();
                        if ((var151 != null ? var151.getUuid() : null) != null) {
                           PlayerHistory var413 = var115.getPlayerHistory();
                           Intrinsics.handleObject(var413);
                           var409 = var413.getUuid();
                           Intrinsics.handleObject(var409);
                           if (!StringsKt.checkCharSequence(var409, '#', false, 2, null)) {
                              TargetParser var415 = TargetParser.INSTANCE;
                              PlayerHistory var152 = var115.getPlayerHistory();
                              if (!var415.checkString5(var152 != null ? var152.getString() : null)
                                 && BaseCommand.commandAliases.checkLiteBansPlatform(var13.getPlatform(), var115.getServerOrigin())) {
                                 var13.getDatabaseManager().runWithStorageAsync(st -> getUnit(var115, (PunishmentStorage) st));
                              }
                           }
                        }
                     }

                     PlayerHistory var153 = var115.getPlayerHistory();
                     String var10002 = var153 != null ? var153.getString() : null;
                     PlayerHistory var154 = var115.getPlayerHistory();
                     String var10005 = var154 != null ? var154.getUuid() : null;
                     PlayerHistory var155 = var115.getPlayerHistory();
                     var143 = new ExecutorInfo(var10002, null, null, var10005, var155 != null ? var155.getIp() : null, null, var115.getString5(), 38, null);
                     if (var143.check()) {
                        label4004: {
                           PlayerHistory var156 = var115.getPlayerHistory();
                           if (var156 != null) {
                              String var169 = var156.getString();
                              if (var169 != null) {
                                 var416 = StringsKt.checkCharSequence(var169, '#', false, 2, null);
                                 break label4004;
                              }
                           }

                           var416 = false;
                        }

                        if (var416) {
                           if (var3.getConfig().isSecurityIpban()) {
                              var3.debug("Hiding IP address from broadcast: " + var143.getString5());
                              var143.setString2(Message.BAN_HIDDEN_IP.toString());
                           } else {
                              var143.setString2(var143.getString5());
                           }

                           var143.setString3(var143.getString2());
                           var28 = var143.getString5();
                           String var443 = var28;
                           if (var28 == null) {
                              var443 = "";
                           }

                           var115.setString5(var443);
                        }
                     }
                  }

                  boolean var158 = PunishmentRequest.isWildcardTargetDefault(var115, null, 1, null);
                  MessageSender var171 = CommandRunnable.messageSender;
                  boolean var188 = var158 && !var1.isConsole() && var3.getConfig().getSecurityIpbanCommand() >= 1;
                  CharSequence var207 = Message.ERROR_CONSOLE;
                  if (var188) {
                     var171.getVoid(var207);
                     throw new NoWhenBranchMatchedException();
                  }

                  var171 = CommandRunnable.messageSender;
                  var188 = var115.isIpBan() && var3.getConfig().getSecurityIpbanCommand() >= 2 && !var1.isConsole();
                  var207 = Message.ERROR_CONSOLE;
                  if (var188) {
                     var171.getVoid(var207);
                     throw new NoWhenBranchMatchedException();
                  }

                  String var173 = var115.getServerOrigin();
                  String var444 = var173;
                  if (var173 == null) {
                     var444 = var23;
                     if (var23 == null) {
                        var444 = var13.getDatabaseManager().getString();
                     }
                  }

                  var115.setServerOrigin(var444);
                  var115.setSenderName(ArgUtils.getString(var115.getSenderName(), 128));
                  var115.setSenderUuid(ArgUtils.getString(var115.getSenderUuid(), 36));
                  var188 = var115.isCustomSender();
                  String var209 = var115.getSenderName();
                  String var218 = var115.getSenderUuid();
                  CommandRunnable var244 = this;
                  ((DatabaseManager)var244.getPlatform().getModule(DatabaseManager.class))
                     .runWithStorageAsync(new ConsoleFunction(var188, var209, this, var218, var1));
                  var115.fireStage(PunishmentStage.POST_TARGET);
                  if (var115.hasTemplate() && var143.getString4() != null) {
                     List var174 = var115.getTemplate().getLadder();
                     if (var115.getTemplateGroup() == null) {
                        var115.setIntValue(var13.countTemplateUses(var115.getTemplate(), var143.getString4(), var143.getString5()));
                        var115.setTemplateData(var115.getTemplate().getTemplateData(var115.getIntValue()));
                     } else {
                        TemplateGroup var417 = var115.getTemplateGroup();
                        Intrinsics.handleObject(var417);
                        TemplateGroup var191 = var417;
                        var174 = var191.getLadder();
                        Pair var210 = var13.computeTemplateStep(var143.getString4(), var143.getString5(), var191, var115.getTemplate());
                        var115.setIntValue((int)Math.floor(((Number)var210.getObject2()).doubleValue()));
                        var115.setTemplateData(var191.getTemplateData(var115.getIntValue()));
                        double var47 = ((Number)var210.getObject()).doubleValue();
                        if (var47 <= 0.0 || var115.getIntValue() != (int)Math.floor(var47)) {
                           var115.setFlag14(true);
                        }

                        String var245 = DecimalFormats.getString2(var47);
                        String var269 = DecimalFormats.getString2(((Number)var210.getObject2()).doubleValue());
                        int var287 = Math.min(var191.getLadder().size(), var115.getIntValue() + 1);
                        var3.debug(
                           "Applying "
                              + var191.getType()
                              + " template group "
                              + var191.getName()
                              + ": weight "
                              + var245
                              + " => "
                              + var269
                              + " ("
                              + var287
                              + '/'
                              + var174.size()
                              + ')'
                        );
                     }

                     int var188i = Math.min(var174.size(), var115.getIntValue() + 1);
                     var3.debug(
                        "Applying "
                           + var115.getTemplate().getType()
                           + " template \""
                           + var115.getTemplate().getName()
                           + "\" at level "
                           + var188i
                           + '/'
                           + var174.size()
                     );
                     TemplateData var219 = var115.getTemplateData();
                     Intrinsics.handleObject(var219, "");
                     StoredTemplateData var211 = (StoredTemplateData)var219;
                     TemplateData var231 = var115.getTemplate().getDefaults();
                     Intrinsics.handleObject(var231, "");
                     StoredTemplateData var220 = (StoredTemplateData)var231;
                     Long var246 = var211.getLongValue();
                     Long var418 = var246;
                     if (var246 == null) {
                        var418 = var220.getLongValue();
                     }

                     Long var232 = var418;
                     if (var232 != null) {
                        if (var232 > 0L) {
                           var115.setDateEnd(var115.getDateStart() + var232);
                           var115.setTemporary(true);
                           long var49 = var232;
                           var115.setReason(DurationParser.getStringDefault(DurationUnit.durationParser, var49, false, 2, null));
                        } else {
                           var115.setDateEnd(-1L);
                           var115.setTemporary(false);
                           var115.setReason(Message.FOREVER);
                        }
                     }

                     if (var115.getTemplate().isIpTemplate()) {
                        var115.setIpBan(true);
                     }

                     PunishmentService.requireTemplatePermission(this, var115.getTemplate(), var115.getTemplateData());
                     var115.applyTemplateDefaults(var13);
                  }

                  var171 = CommandRunnable.messageSender;
                  boolean var193 = var115.isTemporary() && !var115.check2() && var115.getDateEnd() <= 0L;
                  var207 = Message.ERROR_NO_DURATION;
                  if (var193) {
                     var171.getVoid(var207);
                     throw new NoWhenBranchMatchedException();
                  }

                  CharSequence var176 = var115.readReason(var13);
                  String var453 = var143.getString2();
                  Intrinsics.handleObject(var453);
                  String var461 = var143.getString4();
                  Intrinsics.handleObject(var461);
                  var115.handlePunishmentStorage(var13, var453, var461);
                  if (var115.check2()) {
                     SerializableValue var197 = new SerializableValue();
                     PunishmentEntry var213 = null;
                     PunishmentEntry var222 = var115.getPunishmentEntry2();
                     PunishmentEntry var446 = var222;
                     if (var222 == null) {
                        var446 = var13.findActive(var143.getString4(), var143.getString5(), var115.getPunishmentType(), var115.getServerScope(), true, true);
                     }

                     var115.setPunishmentEntry(var446);
                     PunishmentEntry var236 = var115.getPunishmentEntry();
                     var216 = Intrinsics.checkObject(var236 != null ? var236.getExecutorUUID() : null, var1.getUuidString());
                     String var237 = var216 ? ".own" : "";
                     if (var115.getPunishmentEntry() != null && !var216 && var3.getConfig().isExemptUseGroupWeights()) {
                        MessageSender var249 = CommandRunnable.messageSender;
                        PunishmentEntry var271 = var115.getPunishmentEntry();
                        boolean var272 = !var115.isExemptByGroupWeight(var13, var271 != null ? var271.getExecutorUUID() : null);
                        CharSequence var293 = Message.ERROR_NO_PERMISSION;
                        if (var272) {
                           var249.getVoid(var293);
                           throw new NoWhenBranchMatchedException();
                        }
                     }

                     PunishmentEntry var250 = var115.getPunishmentEntry();
                     if ((var250 != null ? var250.getUuid() : null) != null) {
                        PunishmentEntry var251 = var115.getPunishmentEntry();
                        if (var251 != null && var251.getTemplate() != -1) {
                           TemplateManager var422 = var3.getTemplateManager();
                           PunishmentEntry var447 = var115.getPunishmentEntry();
                           Intrinsics.handleObject(var447);
                           Template var252 = var422.getTemplate(var447);
                           if (var252 != null) {
                              PunishmentEntry var294 = var115.getPunishmentEntry();
                              var453 = var294 != null ? var294.getUuid() : null;
                              var294 = var115.getPunishmentEntry();
                              int var273 = var13.countTemplateUses(var252, var453, var294 != null ? var294.getIp() : null);
                              TemplateData var296 = var252.getTemplateData(var273);
                              String var323 = var296.getString4();
                              var409 = var323;
                              if (var323 == null) {
                                 var409 = var252.getDefaults().getString4();
                              }

                              String var310 = var409;
                              MessageSender var324 = CommandRunnable.messageSender;
                              boolean var338 = var310 != null && !var1.hasPermission(var310);
                              CharSequence var347 = Message.ERROR_NO_PERMISSION;
                              if (var338) {
                                 var324.getVoid(var347);
                                 throw new NoWhenBranchMatchedException();
                              }
                           }
                        }
                     }

                     if (var3.isDebugEnabled()) {
                        StringBuilder var424 = new StringBuilder().append("[0xff] ");
                        PunishmentEntry var448 = var115.getPunishmentEntry();
                        String var261 = var424.append(var448 != null ? var448.getExecutorUUID() : null)
                           .append(' ')
                           .append(var1.getUuidString())
                           .append(" (x=")
                           .append(var216)
                           .append(')')
                           .toString();
                        var3.debug(var261);
                     }

                     if (var115.isModify()) {
                        PunishmentEntry var274 = var115.getPunishmentEntry();
                        if (var274 == null) {
                           var115.fail(var13, Message.BAN_ERROR_UNBAN_FAIL);
                           throw new NoWhenBranchMatchedException();
                        }

                        CommandRunnable var275 = this;
                        String var297 = "litebans.modify" + var237;
                        Sender var325 = var275.getCurrentSender();
                        MessageSender var348 = CommandRunnable.messageSender;
                        boolean var360 = !var325.hasPermission(var297);
                        CharSequence var365 = Message.ERROR_NO_PERMISSION;
                        if (var360) {
                           var348.getVoid(var365);
                           throw new NoWhenBranchMatchedException();
                        }

                        PunishmentType var298 = var115.getPunishmentType();
                        CharSequence var449;
                        switch (EntryPlaceholdersWhenMappings.switchMap[var298.getPunishmentType().ordinal()]) {
                           case 1:
                              var449 = Message.DEFAULT_UNBAN_REASON;
                              break;
                           case 2:
                              var449 = Message.DEFAULT_UNMUTE_REASON;
                              break;
                           case 3:
                              var449 = Message.DEFAULT_UNWARN_REASON;
                              break;
                           default:
                              var449 = null;
                        }

                        CharSequence var276 = Intrinsics.checkObject(var176, var449) ? var274.getReason() : var176;
                        long var54 = var115.getDateEnd() == -1L ? var274.getDateEnd() : var115.getDateEnd();
                        var115.applyDurationLimits(var13);
                        var13.updateDurationAndReason(var276, var54, var274);
                        ResultFailure var326 = new ResultFailure();
                        var326.object = new PunishmentEntry(
                           var274.getType(),
                           var274.getUuid(),
                           var274.getIp(),
                           var276,
                           var274.getExecutorUUID(),
                           var274.getExecutorName(),
                           var274.getServerScope(),
                           var274.getServerOrigin(),
                           var274.getDateStart(),
                           var54,
                           var274.getTemplate(),
                           var274.isSilent(),
                           var274.isIpban(),
                           var274.isActive(),
                           var274.getId(),
                           null
                        );
                        var115.reply(var13, EntryReader.getCharSequence(var13, Message.BAN_MODIFY_SUCCESS, (PunishmentEntry)var326.object, false, 2, null));
                     } else if (var115.isDelete()) {
                        PunishmentEntry var277 = var115.getPunishmentEntry();
                        if (var277 == null) {
                           var115.fail(var13, Message.BAN_ERROR_UNBAN_FAIL);
                           throw new NoWhenBranchMatchedException();
                        }

                        CommandRunnable var278 = this;
                        String var299 = "litebans.delete" + var237;
                        Sender var327 = var278.getCurrentSender();
                        MessageSender var349 = CommandRunnable.messageSender;
                        boolean var361 = !var327.hasPermission(var299);
                        CharSequence var366 = Message.ERROR_NO_PERMISSION;
                        if (var361) {
                           var349.getVoid(var366);
                           throw new NoWhenBranchMatchedException();
                        }

                        var115.applyDurationLimits(var13);
                        EntryReader.handlePunishmentStorage(var13, var277, null, null, null, true, 14, null);
                        var115.reply(var13, Message.BAN_DELETE_SUCCESS);
                     } else {
                        var244 = this;
                        String var279 = "litebans." + var115.getPunishmentType() + var237;
                        Sender var311 = var244.getCurrentSender();
                        MessageSender var339 = CommandRunnable.messageSender;
                        boolean var350 = !var311.hasPermission(var279);
                        CharSequence var362 = Message.ERROR_NO_PERMISSION;
                        if (var350) {
                           var339.getVoid(var362);
                           throw new NoWhenBranchMatchedException();
                        }

                        if (var115.getPunishmentEntry2() == null && var16.isUnbanAllHistory() && var115.getPunishmentType() != PunishmentType.UNWARN) {
                           List var256 = var13.findHistoryByIp(var143.getString6(), 1024);
                           if (!var256.isEmpty()) {
                              HashSet var280 = new HashSet();

                              for (Object var312o : var256) {
                                 PlayerHistory var312 = (PlayerHistory) var312o;
                                 String var328 = var312.getUuid2();
                                 String var340 = var312.getIp2();
                                 PunishmentEntry var351 = EntryReader.getPunishmentEntry(
                                    var13, var328, var340, var115.getPunishmentType(), var115.getServerScope(), false, true, 16, null
                                 );
                                 if (var351 != null && !CollectionsKt.checkIterable(var280, var328) && !var351.isIpban()) {
                                    if (var3.isDebugEnabled()) {
                                       var3.debug(var351);
                                    }

                                    var213 = var351;
                                    var197.flag = true;
                                    EntryReader.handlePunishmentStorage(var13, var115.getSenderUuid(), var115.getSenderName(), var176, var351, false, 16, null);
                                    PunishmentService.addEntry(var13, var351, true, var137);
                                    Intrinsics.handleObject(var328);
                                    var280.add(var328);
                                 }
                              }
                           }
                        }

                        if (var115.getPunishmentEntry() == null) {
                           if (!var197.flag) {
                              byte var330 = 1;
                              if (var3.isDebugLevel(var330)) {
                                 CharSequence var367 = "No active "
                                    + var115.getPunishmentType().getPunishmentType()
                                    + " found for uuid:"
                                    + var143.getString4()
                                    + " / ip:"
                                    + var143.getString5()
                                    + " on server:"
                                    + var115.getServerScope()
                                    + " (target: '"
                                    + var143.getString2()
                                    + "')";
                                 boolean var370 = !Intrinsics.checkObject(var115.getString5(), var143.getString4());
                                 CharSequence var429;
                                 if (var370) {
                                    CharSequence var380 = "Try targeting by UUID instead of name.";
                                    if (var380.length() > 0) {
                                       CharSequence var388 = "\n";
                                       CharSequence var382 = "" + var367 + var388;
                                       var429 = "" + var382 + var380;
                                    } else {
                                       var429 = var367;
                                    }
                                 } else {
                                    var429 = var367;
                                 }

                                 var3.debug(var429);
                              }

                              PunishmentType var331 = var115.getPunishmentType();
                              CharSequence var430;
                              switch (EntryPlaceholdersWhenMappings.switchMap[var331.getPunishmentType().ordinal()]) {
                                 case 1:
                                    var430 = Message.BAN_ERROR_UNBAN_FAIL;
                                    break;
                                 case 2:
                                    var430 = Message.MUTE_ERROR_UNMUTE_FAIL;
                                    break;
                                 case 3:
                                    var430 = Message.WARN_ERROR_UNWARN_FAIL;
                                    break;
                                 default:
                                    Unreachable.getVoid();
                                    throw new NoWhenBranchMatchedException();
                              }

                              CharSequence var313 = var430;
                              if (var115.getPunishmentType() == PunishmentType.UNBAN
                                 && !var143.check()
                                 && var137
                                 && !TargetParser.checkString7(var143.getString6())) {
                                 String var341 = "litebans.unban.queue";
                                 if (var1.hasPermission(var341) && !var115.isNoQueue()) {
                                    this.trimUnbanQueue(var143);
                                    var313 = Message.UNBAN_QUEUED;
                                 }
                              }

                              var115.fail(var13, var313);
                              throw new NoWhenBranchMatchedException();
                           }
                        } else {
                           if (var3.isDebugEnabled()) {
                              var3.debug(var115.getPunishmentEntry());
                           }

                           if (var115.getPunishmentType() == PunishmentType.UNWARN && var115.getPunishmentEntry2() == null) {
                              PunishmentEntry var451 = var115.getPunishmentEntry();
                              Intrinsics.handleObject(var451);
                              EntryReader.handlePunishmentStorage(var13, var451, var115.getSenderUuid(), var115.getSenderName(), var176, false, 16, null);
                           } else {
                              var444 = var115.getSenderUuid();
                              var453 = var115.getSenderName();
                              PunishmentEntry var470 = var115.getPunishmentEntry();
                              Intrinsics.handleObject(var470);
                              var13.deactivate(var444, var453, var176, var470, var115.getPunishmentEntry2() != null);
                           }

                           if (var115.getPunishmentType() == PunishmentType.UNWARN) {
                              var115.reply(var13, var13.getCharSequence2(Message.WARN_UNWARN_SUCCESS, var121));
                           }

                           Flag var329 = Flag.Companion.getFlag(var115.getPunishmentType());
                           if (var13.getDatabaseManager().isSyncEnabled()) {
                              PunishmentEntry var425 = var115.getPunishmentEntry();
                              Intrinsics.handleObject(var425);
                              PunishmentEntry var369 = var425;
                              CharSequence[] var387 = new CharSequence[2];
                              MojangApi var458 = UsernamePattern.mojangApi;
                              var461 = var143.getString4();
                              if (var461 == null) {
                                 var461 = "";
                              }

                              var387[0] = var458.getString2(var461);
                              var387[1] = var369.isIpban() ? String.valueOf(var143.getString5()) : "";
                              CharSequence var59 = ArraysKt.getString(var387, "\ufeff", null, null, 0, null, null, 62, null);
                              PunishmentService.broadcastDefault(var13, var329, var59, null, null, 12, null);
                           }

                           EventDispatcher var426 = EventDispatcher.INSTANCE;
                           LiteBansPlatform var459 = var13.getPlatform();
                           PunishmentEntry var466 = var115.getPunishmentEntry();
                           Intrinsics.handleObject(var466);
                           var426.handlePunishmentStorage2(var13, var459, var466);
                        }

                        if (var115.getPunishmentEntry() == null) {
                           var115.setPunishmentEntry(var213);
                        }

                        if (var115.getPunishmentEntry() != null) {
                           PunishmentType var281 = var115.getPunishmentType();
                           CharSequence var427;
                           switch (EntryPlaceholdersWhenMappings.switchMap[var281.getPunishmentType().ordinal()]) {
                              case 1:
                                 var427 = Message.UNBAN_BROADCAST;
                                 break;
                              case 2:
                                 var427 = Message.UNMUTE_BROADCAST;
                                 break;
                              case 3:
                                 var427 = Message.UNWARN_BROADCAST;
                                 break;
                              default:
                                 Unreachable.getVoid();
                                 throw new NoWhenBranchMatchedException();
                           }

                           CharSequence var257 = var427;
                           CharSequence var258 = EntryReader.getCharSequence(var13, var257, var115.getSenderName(), var115.getSenderUuid(), false, 4, null);
                           CharSequence var259 = var13.getCharSequence(var258, var143);
                           new PunishmentNotifier(
                                 var115, var121, var115.getPlayerHistory(), var259, var13.getPlatform(), null, false, var115.isSilent(), 32, null
                              )
                              .run();
                           if (var115.getPunishmentType() == PunishmentType.UNMUTE) {
                              if (var137) {
                                 CommandRunnable var282 = this;
                                 ((MuteManager)var282.getPlatform().getModule(MuteManager.class)).uncache(var115.getPunishmentEntry());
                              }

                              Flag var301 = Flag.UNMUTE;
                              if (var13.getDatabaseManager().isSyncEnabled()) {
                                 PunishmentEntry var428 = var115.getPunishmentEntry();
                                 Intrinsics.handleObject(var428);
                                 PunishmentEntry var352 = var428;
                                 CharSequence[] var377 = new CharSequence[2];
                                 MojangApi var460 = UsernamePattern.mojangApi;
                                 var461 = var143.getString4();
                                 if (var461 == null) {
                                    var461 = "";
                                 }

                                 var377[0] = var460.getString2(var461);
                                 var377[1] = var352.isIpban() ? String.valueOf(var143.getString5()) : "";
                                 CharSequence var392 = ArraysKt.getString(var377, "\ufeff", null, null, 0, null, null, 62, null);
                                 PunishmentService.broadcastDefault(var13, var301, var392, null, null, 12, null);
                              }
                           }

                           CommandRunnable var283 = this;
                           Boolean.valueOf(
                              ((NotificationManager)var283.getPlatform().getModule(NotificationManager.class))
                                 .checkPunishmentEntry(var115.getPunishmentEntry())
                           );
                        } else {
                           LiteBansCore.handleObjectDefault(var3, null, 0, 2, null);
                        }
                     }
                  } else {
                     if (var115.getPunishmentType() == PunishmentType.WARN) {
                        Template var194 = var115.getTemplate();
                        if (Intrinsics.getInt(var194.getId() & 255, -1 & 255) >= 0) {
                           DurationParser var195 = DurationUnit.durationParser;
                           long var61 = var115.getDateStart();
                           String var233 = var16.getWarningsExpireAfter();
                           double var63 = DurationParser.getDoubleDefault(var195, var233, 0.0, 2, null);
                           var115.setDateEnd((long)(var63 < 1.0 ? -1.0 : (double)var61 + var63));
                        }
                     }

                     var115.applyDurationLimits(var13);
                     var115.setDateEnd(Math.max(var115.getDateEnd(), 0L));
                     PunishmentEntry var196 = new PunishmentEntry(var19, var143, var176);
                     var19.setPunishmentEntry(var196);
                     var115.fireStage(PunishmentStage.ENTRY_CREATED);
                     var115.checkCooldown(var13, var1, var196, var115.getPlayerHistory());
                     CharSequence var234 = var143.getString3();
                     boolean var247 = var115.isFlag7();
                     PlayerHistory var270 = var115.getPlayerHistory();
                     switch (BanCommandWhenMappings.switchMap[var196.getType().ordinal()]) {
                        case 1:
                        case 2:
                           PunishmentEntry var333 = EntryReader.getPunishmentEntry(
                              var13, var196.getUuid(), var196.getIp(), var196.getType(), var196.getServerScope(), false, false, 48, null
                           );
                           if (var333 != null
                              && (
                                 !ServerScope.serverScopeParser.checkString(var333.getServerScope())
                                    || ServerScope.serverScopeParser.checkString(var196.getServerScope())
                              )
                              && (!var333.isIpban() || var196.isIpban())
                              && (
                                 !var333.isIpban()
                                    || !var196.isIpban()
                                    || Intrinsics.checkObject(var196.getUuid(), var333.getUuid()) && Intrinsics.checkObject(var196.getIp(), var333.getIp())
                              )) {
                              Sender var342 = this.getCurrentSender();
                              String var43 = "litebans.override";
                              if (!var342.hasPermission(var43)) {
                                 CharSequence var346 = EntryReader.getCharSequence(
                                    var13,
                                    EntryReader.getCharSequence(
                                       var13,
                                       var196.getType() == PunishmentType.BAN ? Message.BAN_PREVIOUS_BAN_EXISTING : Message.MUTE_PREVIOUS_MUTE_EXISTING,
                                       var333,
                                       false,
                                       2,
                                       null
                                    ),
                                    var270,
                                    false,
                                    2,
                                    null
                                 );
                                 CommandRunnable.messageSender.getVoid(var346);
                                 throw new NoWhenBranchMatchedException();
                              }

                              if (var247) {
                                 CommandRunnable var353 = this;
                                 boolean var343 = ((LiteBansCore)var353.getPlatform().getModule(LiteBansCore.class)).getConfig().isOverrideLowerDuration()
                                    && !var333.isPermanent()
                                    && (var196.isPermanent() || var196.getDuration() > var333.getDuration());
                                 MessageSender var354 = CommandRunnable.messageSender;
                                 CharSequence var44 = EntryReader.getCharSequence(
                                    var13,
                                    var196.getType() == PunishmentType.BAN ? Message.BAN_ERROR_NO_OVERRIDE : Message.MUTE_ERROR_NO_OVERRIDE,
                                    var270,
                                    false,
                                    2,
                                    null
                                 );
                                 boolean var51 = !var343;
                                 if (var51) {
                                    var354.getVoid(var44);
                                    throw new NoWhenBranchMatchedException();
                                 }
                              }

                              EntryReader.handlePunishmentStorage(
                                 var13, var19.getSenderUuid(), var19.getSenderName(), var196.getReason(), var333, false, 16, null
                              );
                              CharSequence var445 = EntryReader.getCharSequence(
                                 var13,
                                 var196.getType() == PunishmentType.BAN ? Message.BAN_PREVIOUS_BAN_REMOVED : Message.MUTE_PREVIOUS_MUTE_REMOVED,
                                 var333,
                                 false,
                                 2,
                                 null
                              );
                              CharSequence[] var355 = new CharSequence[4];
                              var355[0] = "bannedPlayer";
                              Intrinsics.handleObject(var234);
                              var355[1] = var234;
                              var355[2] = "mutedPlayer";
                              var355[3] = var234;
                              CharSequence var344 = EntryReader.getCharSequence(var13, ArgUtils.getString(var445, var355), var270, false, 2, null);
                              if (StringsKt.checkCharSequence3(var344, '#', false, 2, null)) {
                                 var344 = Placeholders.Companion.getString(var344, "#offline#", var196.getIp());
                              }

                              MessageSender.handleSenderDefault(CommandRunnable.messageSender, this.getCurrentSender(), var344, null, 4, null);
                              PunishmentService.addEntry(var13, var333, true, var137);
                           }
                     }

                     if (var115.getPunishmentType() == PunishmentType.WARN && var121 != null && var137) {
                        var196.setWarned(true);
                     }

                     PunishmentService.addEntryDefault(var13, var196, false, false, 6, null);
                     var115.handlePunishmentStorage(var13, var121, var143, var137);
                     this.queueUnban(var115, var143);
                     var115.fireStage(PunishmentStage.ENTRY_EMITTED);

                     try {
                        PunishmentStorage var221 = var13;
                        PunishmentRequest var248 = var115;
                        this.forwardCommand(var13, var196, var115);
                        if (!var115.isSkip()) {
                           PunishmentType var288 = var115.getPunishmentType();
                           CharSequence var455;
                           switch (EntryPlaceholdersWhenMappings.switchMap[var288.getPunishmentType().ordinal()]) {
                              case 1:
                                 var455 = Message.BAN_RESPONSE;
                                 break;
                              case 2:
                                 var455 = Message.MUTE_RESPONSE;
                                 break;
                              case 3:
                                 var455 = Message.WARN_RESPONSE;
                                 break;
                              case 4:
                                 var455 = Message.KICK_RESPONSE;
                                 break;
                              default:
                                 Unreachable.getVoid();
                                 throw new NoWhenBranchMatchedException();
                           }

                           var115.reply(var13, EntryPlaceholders.getString(var455, "tempDuration", var115.getReason()));
                           switch (BanCommandWhenMappings.switchMap[var196.getType().ordinal()]) {
                              case 1:
                                 PunishmentService.enforce(var13, var196, var115.getIntValue());
                                 break;
                              case 2:
                                 BanCommand var317 = this;
                                 PunishmentStorage var335 = var13;
                                 Sender[] var358 = var13.getPlatform().getOnlineSenders();
                                 Intrinsics.handleObject(var358);
                                 if (var358.length != 0) {
                                    Sender[] var372 = var358;
                                    Collection var375 = new ArrayList();
                                    int var381 = 0;

                                    for (int var385 = var358.length; var381 < var385; var381++) {
                                       Sender var391 = var372[var381];
                                       Intrinsics.handleObject(var391);
                                       if (var335.appliesTo(var196, var391)) {
                                          var375.add(var391);
                                       }
                                    }

                                    for (Object var376 : (List)var375) {
                                       Sender var379 = (Sender)var376;
                                       CommandRunnable var386 = var317;
                                       ((MuteManager)var386.getPlatform().getModule(MuteManager.class)).cacheMute(var379.getUuidString(), var196);
                                    }
                                 }
                                 break;
                              case 3:
                                 BanCommand var316 = this;
                                 PunishmentStorage var334 = var13;
                                 PunishmentEntry var357 = var196;
                                 CommandRunnable var45 = this;
                                 if (((LiteBansCore)var45.getPlatform().getModule(LiteBansCore.class)).getConfig().getWarningsActions().length != 0) {
                                    Throttler var368 = (Throttler)var13.getPlatform().getModule(Throttler.class);
                                    if (var196.getUuid() == null) {
                                       CommandRunnable var371 = this;
                                       LiteBansCore.handleObjectDefault((LiteBansCore)var371.getPlatform().getModule(LiteBansCore.class), "null", 0, 2, null);
                                    } else {
                                       List var46 = var13.getList(var196, 512);
                                       PlayerHistory var374 = var13.findLatestHistory(var196.getUuid());
                                       if (var374 == null) {
                                          CommandRunnable var378 = this;
                                          LiteBansCore.handleObjectDefault((LiteBansCore)var378.getPlatform().getModule(LiteBansCore.class), "null", 0, 2, null);
                                       } else {
                                          String var52 = var374.getString();
                                          int var53 = var46.size();
                                          CommandRunnable var56 = this;
                                          LiteBansCore var383 = (LiteBansCore)var56.getPlatform().getModule(LiteBansCore.class);
                                          int var57 = 10;
                                          if (var383.isDebugLevel(var57)) {
                                             var383.debug("Active warnings: " + var53);
                                          }

                                          CommandRunnable var389 = this;
                                          String[] var384 = ((LiteBansCore)var389.getPlatform().getModule(LiteBansCore.class)).getConfig().getWarningsActions();
                                          var57 = 0;

                                          for (int var58 = var384.length; var57 < var58; var57++) {
                                             String var65 = var384[var57];
                                             if (!StringsKt.checkCharSequence(var65)) {
                                                CharSequence var419 = var65;
                                                char[] var67 = new char[]{':'};
                                                Collection var393 = StringsKt.getList(var419, var67, false, 2, 2, null);
                                                String[] var66 = /* cast-fix */(String[])(var393.toArray(new String[0]));
                                                boolean var394 = false;
                                                if (StringsKt.checkCharSequence2(var66[0], '+', false, 2, null)) {
                                                   var394 = true;
                                                   var66[0] = var66[0].substring(0, var66[0].length() - 1);
                                                }

                                                if (StringsKt.checkCharSequence3(var65, ':', false, 2, null) && TargetParser.INSTANCE.checkString4(var66[0])) {
                                                   int var68 = Integer.parseInt(var66[0]);
                                                   if (var68 < 1 && !var394) {
                                                      var334.getPlatform()
                                                         .getLogger()
                                                         .warning("Warning action is invalid (threshold " + var68 + " < 1): '" + var65 + "'. Ignored.");
                                                   } else if (var53 == var68 || var394 && var53 > var68) {
                                                      ResultFailure var69 = new ResultFailure();
                                                      var69.object = var66[1];
                                                      if (StringsKt.checkCharSequence((CharSequence)var69.object, '/', false, 2, null)) {
                                                         CharSequence var70 = (CharSequence)var69.object;
                                                         byte var71 = 1;
                                                         int var72 = var70.length();
                                                         var69.object = var70.subSequence(var71, var72).toString();
                                                      }

                                                      if (EntryPlaceholders.checkCharSequence((CharSequence)var69.object, "list")) {
                                                         StringBuilder var395 = new StringBuilder();

                                                         for (Object var74 : var46) {
                                                            PunishmentEntry var75 = (PunishmentEntry)var74;
                                                            var395.append('\n')
                                                               .append(EntryReader.getCharSequence(var334, Message.WARN_LIST_ENTRY, var75, false, 2, null));
                                                         }

                                                         var69.object = EntryPlaceholders.getString((CharSequence)var69.object, "list", var395);
                                                      }

                                                      var69.object = EntryReader.getCharSequence(
                                                         var334,
                                                         EntryPlaceholders.getString(
                                                            EntryReader.getCharSequence(var334, (CharSequence)var69.object, var357, false, 2, null),
                                                            "player",
                                                            var52
                                                         ),
                                                         var374,
                                                         false,
                                                         2,
                                                         null
                                                      );
                                                      CommandRunnable var396 = var316;
                                                      ((LiteBansCore)var396.getPlatform().getModule(LiteBansCore.class)).debug(10, var69.object);
                                                      CommandRunnable var399 = var316;
                                                      Sender var397 = ((LiteBansCore)var399.getPlatform().getModule(LiteBansCore.class))
                                                            .getConfig()
                                                            .isWarningsActionsExecuteAsConsole()
                                                         ? var334.getPlatform().getConsole()
                                                         : var316.getCurrentSender();
                                                      Intrinsics.handleObject(var397);
                                                      var368.release((byte)3, var397.getUuidString());
                                                      var334.getPlatform().runSync(new SenderRunnable1(var397, var69));
                                                      var399 = var316;
                                                      if (((LiteBansCore)var399.getPlatform().getModule(LiteBansCore.class))
                                                         .getConfig()
                                                         .isWarningsDeleteWarningsAfterAction()) {
                                                         var399 = var316;
                                                         if (var65
                                                            == ArraysKt.getObject3(
                                                               ((LiteBansCore)var399.getPlatform().getModule(LiteBansCore.class))
                                                                  .getConfig()
                                                                  .getWarningsActions()
                                                            )) {
                                                            for (Object var404 : var46) {
                                                               PunishmentEntry var405 = (PunishmentEntry)var404;
                                                               EntryReader.handlePunishmentStorage(
                                                                  var334, var405, var357.getExecutorUUID(), var357.getExecutorName(), null, false, 24, null
                                                               );
                                                            }
                                                         }
                                                      }
                                                   }
                                                } else {
                                                   var334.getPlatform()
                                                      .getLogger()
                                                      .warning("Warning action is invalid (bad format): '" + var65 + "'. Ignored.");
                                                }
                                             }
                                          }
                                       }
                                    }
                                 }
                                 break;
                              case 4:
                                 Unreachable.getVoid();
                                 throw new NoWhenBranchMatchedException();
                              default:
                                 Unreachable.getVoid();
                                 throw new NoWhenBranchMatchedException();
                           }

                           CommandRunnable var289 = this;
                           NotificationManager var305 = (NotificationManager)var289.getPlatform().getModule(NotificationManager.class);
                           var305.handlePunishmentStorage(var13, var115);
                        }

                        if (!var115.isSkip()) {
                           CommandRunnable var290 = this;
                           if (((LiteBansCore)var290.getPlatform().getModule(LiteBansCore.class)).getConfig().getBanAlts() > 0
                              && var196.getType() == PunishmentType.BAN
                              && var196.isIpban()
                              && var196.getUuid() != null) {
                              MessageSender var291 = CommandRunnable.messageSender;
                              boolean var306 = !var13.getDatabaseManager().isFlag3();
                              CharSequence var318 = Message.BAN_ALTS_GUARDRAIL_DISCLAIMER;
                              if (var306) {
                                 var291.getVoid(var318);
                                 throw new NoWhenBranchMatchedException();
                              }

                              DupeIpLookup var292 = new DupeIpLookup(var196.getUuid(), var13, null, false, 0, 28, null).getDupeIpLookup();
                              if (var292.isFlag3()) {
                                 MessageSender var307 = CommandRunnable.messageSender;
                                 boolean var319 = var292.getIntValue3() > 10 && !var115.isConfirm();
                                 if (var319) {
                                    var307.getVoid(Message.BAN_ALTS_GUARDRAIL_BAN_MANY.getString("list", var292.getString3()));
                                    throw new NoWhenBranchMatchedException();
                                 }

                                 MessageSender var308 = CommandRunnable.messageSender;
                                 Iterable var320 = var292.getArrayList();
                                 boolean var420;
                                 if (var320 instanceof Collection && ((Collection)var320).isEmpty()) {
                                    var420 = false;
                                 } else {
                                    Iterator var345 = var320.iterator();

                                    while (true) {
                                       if (!var345.hasNext()) {
                                          var420 = false;
                                          break;
                                       }

                                       Object var359 = var345.next();
                                       DupeIPResult var364 = (DupeIPResult)var359;
                                       if (Intrinsics.checkObject(var364.getUuid(), var248.getSenderUuid())) {
                                          var420 = true;
                                          break;
                                       }
                                    }
                                 }

                                 boolean var321 = var420;
                                 CharSequence var336 = Message.BAN_ALTS_GUARDRAIL_BAN_SELF;
                                 if (var321) {
                                    var308.getVoid(var336);
                                    throw new NoWhenBranchMatchedException();
                                 }

                                 for (Object var322o : var292.getArrayList()) {
                                    DupeIPResult var322 = (DupeIPResult) var322o;
                                    UUID var421 = TargetParser.INSTANCE.getUUID(var322.getUuid());
                                    if (var421 != null) {
                                       UUID var337 = var421;
                                       if (!Intrinsics.checkObject(var322.getUuid(), var196.getUuid())
                                          && (var322.getBan() == null || !Intrinsics.checkObject(var322.getBan().getUuid(), var322.getUuid()))
                                          && !EntryReader.checkPunishmentStorage(var221, var337, null, 2, null)) {
                                          var248.handlePunishmentStorage(var221, var322.getName(), var322.getUuid());
                                          PlayerHistory var463 = var248.getPlayerHistory();
                                          var461 = var463 != null ? var463.getString() : null;
                                          PlayerHistory var469 = var248.getPlayerHistory();
                                          PunishmentService.banLinkedAccountDefault(
                                             var221, var322, var196, var461, var469 != null ? var469.getIp() : null, null, null, false, 112, null
                                          );
                                       }
                                    }
                                 }
                              }
                           }
                        }
                     } finally {
                        Flag var235 = Flag.Companion.getFlag(var115.getPunishmentType());
                        if (var13.getDatabaseManager().isSyncEnabled()) {
                           CharSequence[] var356 = new CharSequence[2];
                           MojangApi var454 = UsernamePattern.mojangApi;
                           var461 = var143.getString4();
                           if (var461 == null) {
                              var461 = "";
                           }

                           var356[0] = var454.getString2(var461);
                           var356[1] = var196.isIpban() ? String.valueOf(var143.getString5()) : "";
                           CharSequence var60 = ArraysKt.getString(var356, "\ufeff", null, null, 0, null, null, 62, null);
                           PunishmentService.broadcastDefault(var13, var235, var60, null, null, 12, null);
                        }
                     }

                     var115.fireStage(PunishmentStage.ENTRY_APPLIED);
                  }
               } finally {
                  var115.fireStage(PunishmentStage.FINALIZED);
               }
            } catch (OperationCancelledException var103) {
            }
         } catch (Throwable var104) {
            var10 = var104;
            throw var104;
         } finally {
            CloseableKt.handleCloseable(var9, var10);
         }
      } catch (Exception var106) {
         if (!(var106 instanceof SQLException)) { throw litebans.util.Unreachable.rethrow(var106); }
         if (!var109.isConnectionError(var106)) {
            throw var106;
         }
      }
   }

   private final void applyTemplate(PunishmentRequest var1, String var2) {
      Template var3 = this.resolveTemplate(var1, var2);
      if (var3 == null) {
         var1.getStringBuilder().append(var2).append(' ');
      } else {
         this.applyTemplate(var1, var3);
      }
   }

   private final void applyTemplate(PunishmentRequest var1, Template var2) {
      var1.setTemplate(var2);
      CommandRunnable var3 = this;
      var1.setTemplateGroup(
         ((LiteBansCore)var3.getPlatform().getModule(LiteBansCore.class)).getTemplateManager().getTemplateGroup(var1.getPunishmentType(), var2)
      );
   }

   private final Template resolveTemplate(PunishmentRequest var1, String var2) {
      Template var10000;
      if (var1.getStringBuilder().length() <= 0 && var1.getString5().length() != 0 && !var1.hasTemplate() && !var1.check2()) {
         CommandRunnable var3 = this;
         var10000 = ((LiteBansCore)var3.getPlatform().getModule(LiteBansCore.class)).getTemplateManager().getTemplate(var1.getPunishmentType(), var2);
      } else {
         var10000 = null;
      }

      return var10000;
   }

   private final void queueUnban(PunishmentRequest var1, ExecutorInfo var2) {
      if (var1.getPunishmentType() == PunishmentType.BAN) {
         CommandRunnable var3 = this;
         Collection var6 = ((NotificationManager)var3.getPlatform().getModule(NotificationManager.class)).getSet();
         String var10000 = var2.getString2();
         if (var10000 != null) {
            String var5 = var10000;
            var10000 = var5.toLowerCase(Locale.ENGLISH);
         } else {
            var10000 = null;
         }

         String var4 = var10000;
         TypeIntrinsics.getCollection(var6).remove(var4);
      }
   }

   private final void trimUnbanQueue(ExecutorInfo var1) {
      CommandRunnable var4 = this;
      if (((NotificationManager)var4.getPlatform().getModule(NotificationManager.class)).getSet().size() >= 128) {
         var4 = this;
         ((NotificationManager)var4.getPlatform().getModule(NotificationManager.class)).getSet().clear();
         this.getPlatform().getLogger().warning("Too many unbanned players in queue! Cleared list to prevent memory leak.");
      }

      String var10000 = var1.getString6();
      if (var10000 != null) {
         String var5 = var10000;
         var10000 = var5.toLowerCase(Locale.ENGLISH);
         if (var10000 != null) {
            String var2 = var10000;
            CommandRunnable var3 = this;
            ((NotificationManager)var3.getPlatform().getModule(NotificationManager.class)).getSet().add(var2);
            return;
         }
      }
   }

   private static final Unit getUnit(Sender var0, PunishmentStorage var1) {
      EntryReader.handlePunishmentStorage(var1, var0.getName(), var0.getUuidString(), var0.getIp(), false, false, 16, null);
      return Unit.INSTANCE;
   }

   private static final Unit getUnit(PunishmentRequest var0, PunishmentStorage var1) {
      PlayerHistory var10001 = var0.getPlayerHistory();
      Intrinsics.handleObject(var10001);
      var1.getPlayerHistory(var10001, false);
      return Unit.INSTANCE;
   }
}
