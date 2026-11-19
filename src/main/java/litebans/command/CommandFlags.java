package litebans.command;

import litebans.config.Message;
import litebans.lib.compress.archivers.tar.TargetParser;
import litebans.lib.kotlin.NoWhenBranchMatchedException;
import litebans.lib.kotlin.jvm.internal.Intrinsics;
import litebans.lib.kotlin.text.StringsKt;
import litebans.platform.Sender;
import litebans.punishment.PunishmentRequest;
import litebans.punishment.PunishmentType;
import org.jetbrains.annotations.NotNull;

public final class CommandFlags {
   private final PunishmentRequest punishmentRequest;

   public CommandFlags(@NotNull PunishmentRequest var1) {
      this.punishmentRequest = var1;
   }

   public final void parse(@NotNull String[] var1, boolean var2, boolean var3) {
      PunishmentRequest var4 = this.punishmentRequest;
      boolean var6 = false;
      int var7 = 0;

      label197:
      for (int var8 = var1.length; var7 < var8; var7++) {
         String var9 = var1[var7];
         boolean var11 = true;
         if (StringsKt.checkCharSequence(var9, '-', false, 2, null) && var9.length() > 1 && !var6) {
            if (Intrinsics.checkObject(var9, "--")) {
               var6 = true;
            } else {
               char[] var12 = new char[]{'-'};
               String var10 = StringsKt.getString(var9, var12);
               if (!Intrinsics.checkObject(var10, "s")
                  && !Intrinsics.checkObject(var10, "s:true")
                  && !Intrinsics.checkObject(var10, "s:" + Message.STRINGS_TRUE)) {
                  if (!Intrinsics.checkObject(var10, "s:false") && !Intrinsics.checkObject(var10, "s:" + Message.STRINGS_FALSE)) {
                     label207: {
                        if (StringsKt.checkString(var10, "sender", false, 2, null) || StringsKt.checkString(var10, "server-origin", false, 2, null)) {
                           if (var3) {
                              break label207;
                           }

                           Sender var23 = var4.getSender();
                           String var25 = "litebans.admin";
                           if (var23.hasPermission(var25)) {
                              break label207;
                           }
                        }

                        if (StringsKt.checkString(var10, "stack=", false, 2, null)) {
                           var4.setStack(Byte.parseByte(StringsKt.getString(var10, "stack=", null, 2, null)));
                           if (var4.getStack() > 32) {
                              CommandRunnable.messageSender.getVoid(Message.ERROR_STACK_OVERFLOW);
                              throw new NoWhenBranchMatchedException();
                           }
                           continue;
                        }

                        label159: {
                           switch (var10.hashCode()) {
                              case -1335458389:
                                 if (var10.equals("delete")) {
                                    var4.setDelete(true);
                                    break label159;
                                 }
                                 break;
                              case -1068795718:
                                 if (var10.equals("modify")) {
                                    var4.setModify(true);
                                    break label159;
                                 }
                                 break;
                              case -439474683:
                                 if (var10.equals("no-queue")) {
                                    var4.setNoQueue(true);
                                    break label159;
                                 }
                                 break;
                              case 3202370:
                                 if (var10.equals("hide")) {
                                    if (!var3) {
                                       Sender var27 = var4.getSender();
                                       String var30 = "litebans.admin";
                                       if (!var27.hasPermission(var30)) {
                                          break label159;
                                       }
                                    }

                                    var4.setHidden(true);
                                    break label159;
                                 }
                                 break;
                              case 3532159:
                                 if (var10.equals("skip")) {
                                    if (!var3) {
                                       Sender var26 = var4.getSender();
                                       String var14 = "litebans.admin";
                                       if (!var26.hasPermission(var14)) {
                                          break label159;
                                       }
                                    }

                                    var4.setSkip(true);
                                    break label159;
                                 }
                                 break;
                              case 951117504:
                                 if (var10.equals("confirm")) {
                                    var4.setConfirm(true);
                                    break label159;
                                 }
                           }

                           var11 = false;
                        }

                        if (var11) {
                           continue;
                        }

                        var12 = var10.toCharArray();
                        int var28 = 0;
                        int var31 = var12.length;

                        while (var28 < var31) {
                           char var32 = var12[var28];
                           switch (var32) {
                              case 'I':
                              case 'M':
                              case 'N':
                              case 'S':
                              case 'T':
                              case 'U':
                              case 'W':
                              case 'd':
                              case 'm':
                              case 'p':
                                 var28++;
                                 break;
                              default:
                                 if (var2) {
                                    var4.getArgs().add(var9);
                                 }
                                 continue label197;
                           }
                        }

                        char[] var29 = var12;
                        int var33 = 0;

                        for (int var16 = var12.length; var33 < var16; var33++) {
                           char var34 = var29[var33];
                           switch (var34) {
                              case 'I':
                                 var4.setIpBan(true);
                                 break;
                              case 'M':
                                 var4.setPunishmentType(PunishmentType.MUTE);
                                 break;
                              case 'N':
                                 var4.setFlag7(true);
                                 break;
                              case 'S':
                                 if (!var3) {
                                    Sender var35 = var4.getSender();
                                    String var36 = "litebans.extrasilent";
                                    if (!var35.hasPermission(var36)) {
                                       break;
                                    }
                                 }

                                 var4.setSilent(true);
                                 var4.setExtraSilent(true);
                                 break;
                              case 'T':
                                 var4.setTemporary(true);
                                 break;
                              case 'U':
                                 var4.run();
                                 break;
                              case 'W':
                                 var4.setPunishmentType(PunishmentType.WARN);
                                 var4.setTemporary(false);
                                 break;
                              case 'd':
                                 var4.setDelete(true);
                                 break;
                              case 'm':
                                 var4.setModify(true);
                                 break;
                              case 'p':
                                 if (!var3) {
                                    Sender var20 = var4.getSender();
                                    String var21 = "litebans.public";
                                    if (!var20.hasPermission(var21)) {
                                       continue;
                                    }
                                 }

                                 var4.setSilent(false);
                                 var4.setExtraSilent(false);
                           }
                        }
                        continue;
                     }

                     if (StringsKt.checkString(var10, "sender=", false, 2, null)) {
                        var4.setSenderName(var10.substring(7));
                        var4.setCustomSender(true);
                     } else if (StringsKt.checkString(var10, "sender-name=", false, 2, null)) {
                        var4.setSenderName(var10.substring(12));
                        var4.setCustomSender(true);
                     } else if (StringsKt.checkString(var10, "sender-uuid=", false, 2, null)) {
                        var4.setSenderUuid(TargetParser.getString2(var10.substring(12)));
                        var4.setCustomSender(true);
                     } else if (StringsKt.checkString(var10, "server-origin=", false, 2, null)) {
                        var4.setServerOrigin(var10.substring(14));
                     }
                  } else {
                     var4.setSilent(false);
                     var4.setExtraSilent(false);
                  }
               } else {
                  if (!var3) {
                     Sender var22 = var4.getSender();
                     String[] var13 = new String[]{"litebans.notify", "litebans.notify.silent"};
                     String[] var15 = var13;
                     int var17 = 0;
                     int var18 = var13.length;

                     boolean var10000;
                     while (true) {
                        if (var17 >= var18) {
                           var10000 = false;
                           break;
                        }

                        String var19 = var15[var17];
                        if (var22.hasPermission(var19)) {
                           var10000 = true;
                           break;
                        }

                        var17++;
                     }

                     if (!var10000) {
                        continue;
                     }
                  }

                  var4.setSilent(true);
               }
            }
         } else if (var2) {
            var4.getArgs().add(var9);
         }
      }

      if (var4.isModify()) {
         var4.run();
      }
   }

   public static void parseDefault(CommandFlags var0, String[] var1, boolean var2, boolean var3, int var4, Object var5) {
      if ((var4 & 1) != 0) {
         var1 = var0.punishmentRequest.getStringArray();
      }

      if ((var4 & 2) != 0) {
         var2 = true;
      }

      if ((var4 & 4) != 0) {
         var3 = false;
      }

      var0.parse(var1, var2, var3);
   }

   public final boolean isWildcardIp(@NotNull String var1) {
      return StringsKt.checkCharSequence(var1, '*', false, 2, null)
         && !StringsKt.checkCharSequence2(var1, '*', false, 2, null)
         && var1.length() > 3
         && !StringsKt.checkCharSequence3(var1, '.', false, 2, null)
         && !StringsKt.checkCharSequence3(var1, ':', false, 2, null);
   }
}
